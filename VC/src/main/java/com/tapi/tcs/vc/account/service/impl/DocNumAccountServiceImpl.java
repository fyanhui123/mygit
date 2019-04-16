package com.tapi.tcs.vc.account.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.account.dao.DocNumAccountDao;
import com.tapi.tcs.vc.account.service.DocNumAccountService;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.invoice.util.FtpUtil;
import com.tapi.tcs.vc.schema.model.VcBisAcctImportInfo;
import com.tapi.tcs.vc.schema.model.VcBisDocNumAcct;
import com.tapi.tcs.vc.schema.model.VcInsuranceCard;

public class DocNumAccountServiceImpl implements DocNumAccountService {

	private DocNumAccountDao docNumAccountDao;

	/** 日志管理 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
     * 手工调用文件导入功能
     * 
     * @return
     */
	public void executeImportBisFileByDate(Date date) throws BusinessException{
		String fileName = getFileName(date);
		try {
			saveBisDocNumInfoFile(fileName);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
     * 定时调用文件导入功能
     * 
     * @return
     */
	public void executeImportBisFileClocked(){
		//银保每天2点生成文件，定时任务每天同步前一天的数据
		Date nowDate =  new Date();
		nowDate = DateUtils.addDay(nowDate, -1);
		String fileName = getFileName(nowDate);
		try {
			saveBisDocNumInfoFile(fileName);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
     * 对账任务入口程序
     * 
     * @return
     */
	public void executeBisAcct() throws BusinessException{
		List<VcBisDocNumAcct> list = null;
		List<VcBisDocNumAcct> vcBisDocNumAcctList = null;
		String acctStatus = "";
		Date nowDate = new Date();
		try {
			list = docNumAccountDao.findBisDocNumListForAcct();
			if (list.size() > 0) {
				for (VcBisDocNumAcct tempVo : list) {
					//检查对账
					acctStatus = checkBisDocNumAccount(tempVo);
					tempVo.setAcctStatus(acctStatus);
					tempVo.setDateUpdated(nowDate);
					//保存对账结果
					docNumAccountDao.save(tempVo);
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 文件导入
     * 
     * @return
     */
	public void saveBisDocNumInfoFile(String fileName) throws BusinessException {

		String ftpHost = docNumAccountDao.findCodeCname("BISFTP", "ftpHost");
		String ftpUser = docNumAccountDao.findCodeCname("BISFTP", "ftpUser");
		String ftpPassword = docNumAccountDao.findCodeCname("BISFTP", "ftpPassword");
		String ftPFilePath = docNumAccountDao.findCodeCname("BISFTP", "filePath");
		String ftPFile = ftPFilePath +  fileName;// FTP文件地址
		String downloadFile = getFilePath() + fileName;// 下载文件地址
		Date nowDate = new Date();//当前时间
		HSSFWorkbook workbook = null;//excel容器
		HSSFSheet sheet = null;//sheet页容器
		HSSFRow row = null;//行容器
		HSSFCell cell = null;//列容器
		VcBisAcctImportInfo vcBisAcctImportInfo = new VcBisAcctImportInfo();
		logger.warn("====银保通对账文件读入，开始<<" + new Date() + ">>====");
		// 连接FTP服务器
		FtpUtil ftp = new FtpUtil(ftpHost,21, ftpUser, ftpPassword);
		ftp.connectServer();
		// 下载excel文件
		long result = ftp.downloadFile(ftPFile, downloadFile);
		logger.warn("====银保通对账文件读入，下载文件大小<<" + result + ">>====");
		// 下载文件成功
		if (result > 0) {
			// 读取EXCEL导入数据库
			File f = new File(downloadFile);
			try {
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
				// 打开HSSFWorkbook
				POIFSFileSystem fs = new POIFSFileSystem(in);
				// 创建对Excel工作簿文件的引用
				workbook = new HSSFWorkbook(fs);
			} catch (Exception e) {
				logger.error("====银保通对账文件读入，<<" + "Excell文件读取错误！" + ">>====", e);
			}
			// 创建对工作表的引用
			// 本例是按名引用（假定缺省名为"Sheet1"）
			sheet = workbook.getSheetAt(0);
			// 行数(从0开始,相当于最后一行的索引),列数
			int count_row = sheet.getLastRowNum();
			// excel文件的读取列数
			int count_cell = 5;
			try {
				if (count_row > 1) {
					// 拿到第零行表格，解析列与字段之间的对应关系
					HSSFRow zeroRow = sheet.getRow(0);
					List<VcBisDocNumAcct> list = new ArrayList<VcBisDocNumAcct>();
					VcBisDocNumAcct tempVo = null;
					List<VcBisDocNumAcct> checkTempVoList = null;
					String titleName = null;
					String acctStatus = "";
					for (int i = 1; i <= count_row; i++) {
						row = sheet.getRow(i);
						if (row == null) {
							continue;
						}
						// 有空值
						if (row.getCell(0) == null || StringUtils.isBlank(row.getCell(0).getStringCellValue())) {
							continue;
						}
						tempVo = new VcBisDocNumAcct();
						for (int j = 0; j < count_cell; j++) {
							cell = row.getCell(j);
							if (cell != null) {
								titleName = zeroRow.getCell(j).getStringCellValue();
								if ("单证类型".equals(titleName)) {
									if (StringUtils.isBlank(cell.getStringCellValue())) {
										j = count_cell; // 结束内循环
									} else {
										tempVo.setDocVerCode(cell.getStringCellValue());
									}
								} else if ("流水号".equals(titleName)) {
									if (StringUtils.isBlank(cell.getStringCellValue())) {
										j = count_cell; // 结束内循环
									} else {
										tempVo.setDocNum(cell.getStringCellValue());
									}
								} else if ("机构代码".equals(titleName)) {
									if (StringUtils.isBlank(cell.getStringCellValue())) {
										j = count_cell; // 结束内循环
									} else {
										tempVo.setOrgCode(cell.getStringCellValue());
									}
								} else if ("业务号".equals(titleName)) {
									if (StringUtils.isNotBlank(cell.getStringCellValue())) {
										tempVo.setBusinessNo(cell.getStringCellValue());
									}
								} else if ("状态".equals(titleName)) {
									if (StringUtils.isBlank(cell.getStringCellValue())) {
										j = count_cell; // 结束内循环
									} else {
										tempVo.setDocStatus(cell.getStringCellValue());
									}
								}
							}
						}
//						acctStatus = checkBisDocNumAccount(tempVo);
						checkTempVoList = docNumAccountDao.findBisDocNumList(tempVo);
						if(checkTempVoList.size()==0){
							tempVo.setAcctStatus(acctStatus);
							tempVo.setCreatedBy("0100000090");
							tempVo.setUpdatedBy("0100000090");
							tempVo.setDateCreated(nowDate);
							tempVo.setDateUpdated(nowDate);
							tempVo.setAcctDate(nowDate);
							list.add(tempVo);
						}
					}
					vcBisAcctImportInfo.setFileName(fileName);
					vcBisAcctImportInfo.setCountNum((long)list.size());
					vcBisAcctImportInfo.setAcctDate(nowDate);
					vcBisAcctImportInfo.setCreatedBy("0100000090");
					vcBisAcctImportInfo.setUpdatedBy("0100000090");
					vcBisAcctImportInfo.setDateCreated(nowDate);
					vcBisAcctImportInfo.setDateUpdated(nowDate);
					if(list.size()>0){
						docNumAccountDao.saveBisDocNumAcctList(list);
					}
					docNumAccountDao.save(vcBisAcctImportInfo);
				}
			} catch (Exception e) {
				logger.error("====银保通对账文件读入失败", e);
			}
		}
		logger.warn("====银保通对账文件读入结束<<" + new Date() + ">>====");
	}
	

	
	/**
     * 检查对账数据状态
     * 
     * @return
     */
	public String checkBisDocNumAccount(VcBisDocNumAcct vcBisDocNumAcct)
			throws BusinessException {
		String result = "N";
		List list = null;
		String docStatus = vcBisDocNumAcct.getDocStatus();
		try {
			//"1"为正常出单，"0"为作废
			if (docStatus.equals("1")) {
				list = docNumAccountDao.queryBisDocNumIsUsed(vcBisDocNumAcct);
				if (list.size() > 0) {
					result = "Y";
				}
			}else if(docStatus.equals("0")||docStatus.equals("2")){
				list = docNumAccountDao.queryBisDocNumAnnuld(vcBisDocNumAcct);
				if (list.size() > 0) {
					result = "Y";
				}
			}
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
     * 获取本地文件保存路径
     * 
     * @return
     */
	private String getFilePath(){
		//文件路径
		String filePath = SysConst.BIS_ACCOUNT_FILE_DOWNLOAD_PATH;
		File dirPath = new File(filePath);
		//如果目录不存在，则创建
		if(!dirPath.isDirectory()){
			dirPath.mkdir();
		}
		return filePath;
	}
	
	/**
     * 获取文件名
     * 
     * @return
     */
	private String getFileName(Date date){
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		String fileName = "BIS_VCHR_"+sd.format(date) +".xls";//文件名
		return fileName;
	}
	
	public DocNumAccountDao getDocNumAccountDao() {
		return docNumAccountDao;
	}

	public void setDocNumAccountDao(DocNumAccountDao docNumAccountDao) {
		this.docNumAccountDao = docNumAccountDao;
	}

}
