package com.tapi.tcs.vc.invoice.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.invoice.dao.InvoiceExportDao;
import com.tapi.tcs.vc.invoice.service.ChongQingInvoiceService;
import com.tapi.tcs.vc.invoice.util.FtpUtil;
import com.tapi.tcs.vc.invoice.vo.InvoiceExportVO;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;

public class ChongQingInvoiceServiceImpl implements ChongQingInvoiceService {

	/** 日志管理 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/** 机构表Dao */
	private VcLevelDao vcLevelDao;

	private InvoiceExportDao invoiceExportDao;

	/** 上一次生成序号的日期 */
	private static String strLastDate = "";

	/** 当天的序号 */
	private static int currentDayNum = 0;

	/**
	 * 重庆发票导出文件名生成
	 * 
	 * @return
	 */
	public static String genarateChongQingFileName(Date startDate) {
		// 每天序号重置
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String strNowDate = sdf.format(new Date());
		if (strNowDate.equals(strLastDate)) {
			currentDayNum++;
		} else {
			currentDayNum = 1;
			strLastDate = strNowDate;
		}
		// 格式化流水号为3位
		DecimalFormat df = new DecimalFormat("0");
		df.setMinimumIntegerDigits(3);
		// 导出日期+序号
		return sdf.format(startDate) + "_" + df.format(currentDayNum) + ".txt";
	}

	/**
	 * 重庆发票定时导出程序入口 纳税人在每天早上2点之前将前一天的数据上传至采集服务器
	 */
	public void InvoiceExportAutoTask() {
		try {
			String orgCode = SysConst.COMCODE_ChongQing; // "0151"
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			InvoiceExportVO queryDto = new InvoiceExportVO();

			// 重庆发票只取前天的发票打印信息
			Date startDate = DateUtils.reset(DateUtils.addDay(new Date(), -1));
			Date endDate = DateUtils.addDay(startDate, 1);
			//queryDto.setPrintDateStart(startDate);
			//queryDto.setPrintDateEnd(endDate);
			queryDto.setInputCompany(orgCode);
			queryDto.setInvoiceStatus("0"); // 只导出未上传平台的号段

			logger.warn("====重庆发票定时导出开始<<" + sd.format(startDate) + " 至 "
					+ sd.format(endDate) + ">>====");
			List<VcInvoicePrint> vcInvoicePrintTmpList = invoiceExportDao
					.queryInvoiceExport(queryDto, orgCode);
			// 文件存放目录
			String filePath = getDirPath(orgCode);

			if (vcInvoicePrintTmpList != null && vcInvoicePrintTmpList.size() > 0) {
				// 生成导出文件
				generateTXTFile(vcInvoicePrintTmpList, filePath, orgCode,
						startDate);
				// 更新状态
				for (VcInvoicePrint vcInvoicePrint : vcInvoicePrintTmpList) {
					invoiceExportDao.updateIsUploadPlat(vcInvoicePrint.getId(),
							vcInvoicePrint.getUpdatedBy());
				}
			
				// 文件夹上传重庆远程FTP服务器
				FtpUtil ftp = new FtpUtil(SysConst.CHONG_QING_FTP_HOST,
						SysConst.CHONG_QING_FTP_PORT,
						SysConst.CHONG_QING_FTP_USERCODE,
						SysConst.CHONG_QING_FTP_PSASWORD);
				//FTP连接
				ftp.connectServer();
				boolean result = ftp.upload(filePath,SysConst.CHONG_QING_FTP_FILE_NAME);
				logger.warn(result ? "上传成功！" : "上传失败！");
				List list = ftp.getFileList(SysConst.CHONG_QING_FTP_FILE_NAME);
				for (int i = 0; i < list.size(); i++) {
					String name = list.get(i).toString();
					logger.warn("上传文件："+name);
				}
				if(result){
					//如果上传成功，删除文件
					logger.warn("删除生成的文件夹...");
					File dirPath = new File(filePath);
					deleteFile(dirPath);
				}
				ftp.closeServer();
			}
			logger.warn("====重庆发票定时导出结束<<" + sd.format(startDate) + " 至 "
						+ sd.format(endDate) + ">>====");
		} catch (BusinessException be) {
			logger.error(be.getMessage(), be);
		} catch (IOException ie) {
			logger.error(ie.getMessage(), ie);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	/**
	 * 重庆地区生产txt文件
	 * 
	 * @param vcInvoicePrintTmpList
	 * @param filePath
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private String generateTXTFile(List<VcInvoicePrint> vcInvoicePrintTmpList,
			String filePath, String orgCode, Date startDate)
			throws IOException, Exception {

		// 纳税人识别号以重庆分公司为准
		PubCompany pubCompany = vcLevelDao.findPubCompanyByCode(orgCode);
		String nsrsbh = pubCompany.getTaxNumber(); // 纳税人识别号
		String nsrmc = pubCompany.getCompanyCname(); // 纳税人名称

		// 返回的json数据格式：文件路径==文件名
		String fileData = "";
		String separator = java.io.File.separator;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DecimalFormat dft = new DecimalFormat("0.00");

		String fileName = ChongQingInvoiceServiceImpl
				.genarateChongQingFileName(startDate);
		//
		File txtFile = new File(filePath + separator + fileName);
		
		///
		FileOutputStream out = new FileOutputStream(txtFile);
		StringBuffer sb = new StringBuffer("");

		for (VcInvoicePrint vcInvoicePrint : vcInvoicePrintTmpList) {
			
			String printKind = vcInvoicePrint.getPrintKind();// 发票开具类型:1-正常;2-负数;3-作废；4-空白作废
			if(vcInvoicePrint.getAmount().signum()<0){
            	printKind = "2";
            }
            // 发票代码（半角，12位）
            sb.append(vcInvoicePrint.getInvoiceCode()).append("|@|");
            // 发票号码（半角,8位）
            sb.append(vcInvoicePrint.getInvoiceNo()).append("|@|");
            
//         // 收款方纳税人编码（半角，不定长，至少8位，不能为空）
//            if (StringUtils.isNotBlank(vcInvoicePrint.getTaxpayerCode())) {
//                sb.append(vcInvoicePrint.getTaxpayerCode()).append("|@|");
//            } else {
//                sb.append(nsrsbh).append("|@|");
//            }
//            // 收款方纳税人名称（不定长，不能为空）
//            if (StringUtils.isNotBlank(vcInvoicePrint.getTaxpayerName() )) {
//                sb.append(vcInvoicePrint.getTaxpayerName()).append("|@|");
//            } else {
//                sb.append(nsrmc).append("|@|");
//            }
//            
            //lfengxia 20150804 begin
            
            //收款方纳税人编码（半角，不定长，至少8位，不能为空）
            sb.append("01044315").append("|@|");
           //收款方纳税人名称（不定长，不能为空）
            sb.append("天安财产保险股份有限公司重庆分公司").append("|@|");
   
            //lfengxia 20150804 begin
            
            // 付款方纳税人名称（不定长，空白作废发票可以为空）
            if (vcInvoicePrint.getPayerName() != null) {
                sb.append(vcInvoicePrint.getPayerName()).append("|@|");
            } else {
                sb.append("空白").append("|@|");
            }
            // 发票合计金额（半角，不定长，保留两位小数，空白作废发票为0，冲红发票必须为负数）
            if (vcInvoicePrint.getAmount() != null) {
                sb.append(dft.format(vcInvoicePrint.getAmount())).append("|@|");
            } else {
                sb.append("0.00").append("|@|");
            }
            // 开票日期（半角，格式yyyy-mm-dd HH:mi:ss，19位，24小时制，不能为空）
              //针对作废发票取创建时间
            
            String operateDate = "";
            
            if (printKind.equals("3")) {
            	if (vcInvoicePrint.getDateCreated() != null) {
            		operateDate = df.format(vcInvoicePrint.getDateCreated());
            	}
			}else {
				if (vcInvoicePrint.getPrintDate() != null) {
            		operateDate = df.format(vcInvoicePrint.getPrintDate());
            	}
			}                      
            if (StringUtils.isNotBlank(operateDate) ) {
                sb.append(operateDate).append("|@|");
            } else {
                sb.append(" ").append("|@|");
            }
            
            
            
            
            // 开票员（不定长，打印发票票面上的开票人，不能为空）
            if (StringUtils.isNotBlank(vcInvoicePrint.getBillerName())) {
                sb.append(vcInvoicePrint.getBillerName()).append("|@|");
            } else {
            	
            	//空白作废发票为作废人名称   by lfengxia
                sb.append(vcInvoicePrint.getCanceledOpr()).append("|@|");
            }
            
            // 发票类型（半角，1位：1:正常发票；2：作废发票；3：空白作废发票；4：冲红发票）
                       
            String newType = " ";
            if ("1".equals(printKind)) {// 1-正常
                newType = "1";// 1正常发票
            } else if ("2".equals(printKind)) {// 2-负数
                newType = "4";// 4冲红发票
            } else if ("3".equals(printKind)) {// 3-作废
                newType = "2";// 2作废发票
            } else if ("4".equals(printKind)) {// 4-空白作废
                newType = "3";// 3空白作废发票
            }
            sb.append(newType).append("|@|");

            // 被冲红发票代码（半角，12位，非冲红发票用空格代替）
            if (vcInvoicePrint.getCounteractedInvoiceCode() != null) {
                sb.append(vcInvoicePrint.getCounteractedInvoiceCode()).append("|@|");
            } else {
                sb.append(" ").append("|@|");
            }
            // 被冲红发票号码（半角，8位，非冲红发票用空格代替）
            if (vcInvoicePrint.getCounteractedInvoiceNo() != null) {
                sb.append(vcInvoicePrint.getCounteractedInvoiceNo()).append("|@|\r\n");
            } else {
                sb.append(" ").append("|@|\r");
            }
        }
        //System.out.println(sb.toString());
        out.write(sb.toString().getBytes());
        out.close();
        fileData = filePath + "==" + fileName;
        logger.info("生成重庆发票数据文件路径：========" + filePath + fileName + "===========");
        return fileData;
    }

	/**
	 * 根据不同机构返回文件存放目录
	 * 
	 * @param orgCode
	 * @return
	 * @throws Exception
	 */
	private String getDirPath(String orgCode) throws Exception {
		// 文件存放路径：不同地区存放不同文件夹
		String filePath = "";
		// 获取路径
		String basePath = this.getClass().getClassLoader().getResource("/").getPath();
		if (basePath.indexOf("WEB-INF/classes") > -1) {
			basePath = basePath.substring(0,basePath.indexOf("WEB-INF/classes"));
		}
		filePath = basePath + SysConst.INVOICE_EXPORT_FILE_PATH + orgCode;
		File dirPath = new File(filePath);
		// 如果目录不存在，则创建
		if (!dirPath.isDirectory()) {
			dirPath.mkdir();
		}
		// 清空目录下前一天的临时文件
		//deleteFile(dirPath);
		logger.error("测试路径:" + filePath);
		logger.info("测试路径:" + filePath);
		return filePath;
	}

	/**
	 * 删除目录下文件
	 * 
	 * @param dirPath
	 */
	private void deleteFile(File dirPath) throws Exception {
		File[] fileList = dirPath.listFiles();
		if (fileList != null && fileList.length > 0) {
			for (File file : fileList) {
				Long time = file.lastModified();
				Calendar nowDate = Calendar.getInstance();
				Calendar calendar = Calendar.getInstance();
				nowDate.setTime(new Date());
				calendar.setTimeInMillis(time);
				file.delete();
			}
		}
	}

	public void setVcLevelDao(VcLevelDao vcLevelDao) {
		this.vcLevelDao = vcLevelDao;
	}

	public void setInvoiceExportDao(InvoiceExportDao invoiceExportDao) {
		this.invoiceExportDao = invoiceExportDao;
	}

}
