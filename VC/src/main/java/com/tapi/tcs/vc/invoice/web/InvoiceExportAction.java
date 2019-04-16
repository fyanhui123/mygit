package com.tapi.tcs.vc.invoice.web;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;

import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.baseinfo.service.InsuKindService;
import com.tapi.tcs.vc.baseinfo.service.VcLevelService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.StringUtil;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.invoice.service.InvoiceExportService;
import com.tapi.tcs.vc.invoice.util.AntZip;
import com.tapi.tcs.vc.invoice.vo.InvoiceDetialXMDTO;
import com.tapi.tcs.vc.invoice.vo.InvoiceExportVO;
import com.tapi.tcs.vc.invoice.vo.InvoiceMainXMDTO;
import com.tapi.tcs.vc.invoice.vo.InvoiceXMDTO;
import com.tapi.tcs.vc.invoice.vo.HeNan.InvoiceDetailHeNanDTO;
import com.tapi.tcs.vc.invoice.vo.HeNan.InvoiceDetailRowHeNanDTO;
import com.tapi.tcs.vc.invoice.vo.HeNan.InvoiceMainHeNanDTO;
import com.tapi.tcs.vc.invoice.vo.HeNan.InvoiceMainRowHeNanDTO;
import com.tapi.tcs.vc.invoice.vo.guizhou.InvoiceGuiZhouKpInfoDTO;
import com.tapi.tcs.vc.invoice.vo.guizhou.InvoiceGuiZhouKpInfoDetDTO;
import com.tapi.tcs.vc.invoice.vo.jiangsu.JSInvoiceDTO;
import com.tapi.tcs.vc.invoice.vo.jiangsu.JSInvoiceDataDTO;
import com.tapi.tcs.vc.invoice.vo.jiangsu.JSInvoiceRequestDTO;
import com.tapi.tcs.vc.invoice.vo.jiangsu.JSInvoiceRequestHeadDTO;
import com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXBTDTO;
import com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXDTO;
import com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXMXDTO;
import com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXMainFileDTO;
import com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXMainFileMxDTO;
import com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXSSQDTO;
import com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXXXDTO;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.VcDocInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoicePrintDet;
import com.tapi.tcs.vc.schema.model.VcInvoicePrintExt;
import com.tapi.tcs.vc.schema.model.VcInvoiceRevoke;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcManageCodeHlj;
import com.tapi.tcs.vc.schema.model.VcTaxPayerLogin;

/**
 * 发票数据导出Action
 * <p>
 * Date 2013-06-06
 * </p>
 * <p>
 * Module：发票
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * @author chy
 * @version 1.0
 */
public class InvoiceExportAction extends TFAction {

	private static final long serialVersionUID = 1;
	/**日志管理*/
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/**发票导出Service*/
	private InvoiceExportService invoiceExportService;
	/**单证种类Service*/
	private InsuKindService insuKindService;
	/**下拉框*/
	private List<Map<String, String>> mapList;
	/**查询列表*/
	private List<VcInvoicePrint> vcInvoicePrintList;
	/**查询条件*/
	private InvoiceExportVO invoiceExportVo;
	/**json字符串*/
	private String jsonData;
	/**选中的id*/
	private String ids;

	/**机构表Service*/
    private VcLevelService vcLevelService;
	
	/**
     * 上一次生成序号的日期
     */
   private static String strLastDate="";
   private static String strLastDate_DL="";
    /**
     * 当天的序号
     */
    private static  int currentDayNum=0; 
    private static  int currentDayNum_DL=0; 
    private static  int serial_HeNan=0; //河南文件导出序号
    
    /**
     * 重庆发票导出文件名生成
     * 
     * @return
     */
    public static String genarateChongQingFileName(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String strNowDate=sdf.format(new Date());
        if(strNowDate.equals(strLastDate)){
            currentDayNum++;
        }else{
            currentDayNum=1; 
            strLastDate=strNowDate;
        }
        //格式化流水号为3位
        DecimalFormat df = new DecimalFormat("0");
        df.setMinimumIntegerDigits(3);
        return strLastDate+"_"+df.format(currentDayNum)+".txt";
    }

    /**
     * 大连发票导出文件名流水号
     * 
     * @return
     */
    public static String countForDL(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String strNowDate=sdf.format(new Date());
        if(strNowDate.equals(strLastDate_DL)){
        	currentDayNum_DL++;
        }else{
        	currentDayNum_DL=1; 
        	strLastDate_DL=strNowDate;
        }
        //格式化流水号为2位
        DecimalFormat df = new DecimalFormat("0");
        df.setMinimumIntegerDigits(2);
        return df.format(currentDayNum_DL);
    }
    
    
	/**
	 * 进入发票导出界面
	 * @return
	 */
	public String preInvoiceExportQuery() {
		logger.info("访问/invoice/preInvoiceExportQuery.do...进入发票导出界面");
		return SUCCESS;
	}
	
	/**
	 * 初始化险类下拉框
	 * @return
	 */
	public String queryJSONInsuTypeList() throws BusinessException {
		logger.info("访问/invoiceJson/getJSONInsuTypeList.do...初始化险类下拉框");
		
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		try{
			// 状态为1的险类
			List<VcDocInsuType> validInsuTypeList = insuKindService.getValidInsuTypeList();
	
			if (validInsuTypeList != null && validInsuTypeList.size() > 0) {
				Map<String, String> map = null;
				for (VcDocInsuType insuType : validInsuTypeList) {
					map = new HashMap<String, String>();
					map.put("label", insuType.getInsuTypeName());
					map.put("value", insuType.getIdVcDocInsuType()+"");
					mapList.add(map);
				}
			}
			this.mapList = mapList;
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}

		return SUCCESS;
	}
	
	/**
	 * 初始化险种下拉框
	 * @return
	 */
	public String queryJSONInsuKindList () throws BusinessException{
		logger.info("访问/invoiceJson/getJSONInsuKindList.do...初始化险种下拉框");
		//返回页面的下拉框
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		try{
			//组织查询参数
			Map<String, Object> parMap = new HashMap<String, Object>();
			parMap.put("status", "1");
			//查询险种列表
			List<VcDocInsuKind> validInsuKindList = insuKindService.getInsuKindList(parMap);
	
			if (validInsuKindList != null && validInsuKindList.size() > 0) {
				Map<String, String> map = null;
				for (VcDocInsuKind insuKind : validInsuKindList) {
					map = new HashMap<String, String>();
					map.put("label", insuKind.getInsuKindName());
					map.put("value", insuKind.getInsuKindCode());
					//对应的险类
					map.put("name", insuKind.getIdVcDocInsuType()+"");
					mapList.add(map);
				}
			}
			this.mapList = mapList;
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化签单机构下拉框
	 * @return
	 */
	public String queryJSONCompanyList () throws BusinessException {
		logger.info("访问/invoiceJson/getJSONCompanyList.do...初始化签单机构下拉框");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//用户归属机构
			String comCode = userInfo.getComCode();
			//返回页面的下拉框
			List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
			//组织查询参数
			Map<String, Object> parMap = new HashMap<String, Object>();
			List<VcLevel> vcLevelLlist = invoiceExportService.getAllChildCompany(comCode);
			if(vcLevelLlist!=null && vcLevelLlist.size()>0){
				Map<String, String> map = null;
				for (VcLevel vcLevel : vcLevelLlist) {
					map = new HashMap<String, String>();
					map.put("label", vcLevel.getUnitName());
					map.put("value", vcLevel.getUnitCode());
					mapList.add(map);
				}
			}
			this.mapList = mapList;
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 分页查询发票导出数据
	 * @return
	 */
	public String queryInvoiceExport() throws BusinessException {
		logger.info("访问/invoiceJson/queryInvoiceExport.do...分页查询发票导出数据");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//导出机构
			String comCode = invoiceExportVo.getInputCompany();
			if(StringUtils.isEmpty(comCode)){
				//用户归属机构
				comCode = userInfo.getComCode();
			}
			//江苏地区发票代码必填
			/*if(comCode.startsWith(SysConst.COMCODE_JS) && StringUtils.isBlank(invoiceExportVo.getInvoiceCode())){
				throw new BusinessException("发票代码不能为空！");
			}*/
			Page returnPage = invoiceExportService.queryInvoiceExport(invoiceExportVo, comCode, page, rows);
			//返回页面的结果集
			vcInvoicePrintList = (List<VcInvoicePrint>)returnPage.getResult();
			//总页数
			total = (int)returnPage.getTotalPageCount();
			//总记录数
			records = (int)returnPage.getTotalCount();
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 生成TXT文件
	 * @return
	 */
	public String generateFile() throws BusinessException {
		logger.info("访问/invoiceJson/generateFile.do...生成TXT文件");
		try{
			if(invoiceExportVo==null || invoiceExportVo.getPrintDateStart()==null 
					|| invoiceExportVo.getPrintDateEnd()==null){
				throw new BusinessException("打印日期不能为空！");
			}
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			String comCode = invoiceExportVo.getInputCompany();
			if(StringUtils.isEmpty(comCode)){
				comCode = userInfo.getComCode();
			}
			String userCode = userInfo.getUserCode();
			//机构代码
			String orgCode = comCode;
			String orgCode_GZ = orgCode;
			//截取机构代码前4位判断地区
			if(StringUtils.isNotEmpty(orgCode) && orgCode.length()>=4){
				orgCode = orgCode.substring(0, 4);
			}
			//黑龙江地区导出权限控制
			VcManageCodeHlj vcManageCodeHlj = null;
			if(SysConst.COMCODE_HLJ.equals(orgCode)){
				vcManageCodeHlj = invoiceExportService.findManageCodeByOrgCode(comCode);
				if(vcManageCodeHlj==null){
					throw new BusinessException("当前机构没有权限导出发票数据");
				}
			}
			//校验导出条数不能超过1000条
			if(total>SysConst.MAX_EXPORT_COUNT){
				throw new BusinessException("导出的数据大于"+SysConst.MAX_EXPORT_COUNT+"条，请缩小查询范围后重新导出！");
			}     
			List<VcInvoicePrint> vcInvoicePrintTmpList = invoiceExportService.queryInvoiceExport(invoiceExportVo, comCode);
			if(vcInvoicePrintTmpList==null || vcInvoicePrintTmpList.size()<1){
				throw new BusinessException("查询不到发票数据！");
			}
			logger.info("======发票数据导出--地区代码："+orgCode+"======");
			
			//文件存放目录
			String filePath = getDirPath(orgCode);
			//已导出的缴销信息，需更新上传平台标志位（上海）
			List<VcInvoiceRevoke> uploadPlatList = new ArrayList<VcInvoiceRevoke>();
			//浙江发票导出
			if(SysConst.COMCODE_ZJ.equals(orgCode)){
				jsonData = generateTXTFileZJ(vcInvoicePrintTmpList, filePath);
			}
			//厦门发票导出
			else if(SysConst.COMCODE_XM.equals(orgCode)){
				jsonData = generateZIPFileXM(vcInvoicePrintTmpList, filePath);
			}
			//江苏地区
			/*else if(SysConst.COMCODE_JS.equals(orgCode)){
				jsonData = generateXMLFileJS(vcInvoicePrintTmpList, comCode, filePath);
			}*/
			else if(SysConst.COMCODE_JS.equals(orgCode)){
				jsonData = generateExcelJiangsu(vcInvoicePrintTmpList, filePath);
			}
			//河南地区
            else if(SysConst.COMCODE_HeNan.equals(orgCode)){
                jsonData = generateXMLFile_HeNan(vcInvoicePrintTmpList, filePath, comCode);
            }
			//重庆地区
            else if(SysConst.COMCODE_ChongQing.equals(orgCode)){
                jsonData = generateTXTFile_ChongQing(vcInvoicePrintTmpList, filePath, comCode);
            }
			//大连地区
            else if(SysConst.COMCODE_DL.equals(orgCode)){
            	jsonData = generateZIPFile_DL(vcInvoicePrintTmpList, filePath);
            }
			//山西地区
			else if(SysConst.COMCODE_SX.equals(orgCode)){
			    jsonData = generateFileSX(vcInvoicePrintTmpList, filePath);
			}
			//贵州地区
			else if(SysConst.COMCODE_GZ.equals(orgCode)){
			    jsonData = generatorFileGuiZhou(vcInvoicePrintTmpList, filePath, orgCode_GZ);
			}
			//上海地区
			else if(SysConst.COMCODE_SHANGHAI.equals(orgCode)){
				jsonData = generateXmlFileSH(vcInvoicePrintTmpList,uploadPlatList, filePath,userCode);
			}
			//航保出单中心地区
			else if(SysConst.COMCODE_HANGBAO.equals(orgCode)){
				jsonData = generateXmlFileHangBao(vcInvoicePrintTmpList,uploadPlatList, filePath,userCode);
			}
			//黑龙江
			else if(SysConst.COMCODE_HLJ.equals(orgCode)){
				jsonData = generateTXTFileHlj(vcInvoicePrintTmpList, filePath, vcManageCodeHlj.getManageCode());
			}
			//天津地区
			else if(SysConst.COMCODE_TJ.equals(orgCode)){
			    jsonData = generateXmlFileTianJin(vcInvoicePrintTmpList, filePath);
			}
			//北京地区
			else if(SysConst.COMCODE_BEIJING.equals(orgCode)){
				 jsonData = generateXmlFile_BeiJing(vcInvoicePrintTmpList, invoiceExportVo, filePath,comCode);
            }			
			else{
				throw new BusinessException("选择的机构有误，或您无导出发票数据的权限！");
			}
			
			/*int size = vcInvoicePrintTmpList.size();
			Long[] idList = new Long[size];
			for(int i=0;i<size;i++){
				idList[i]=vcInvoicePrintTmpList.get(i).getId();
			}
			//修改发票状态
			invoiceExportService.updateIsUploadPlat(idList, userCode);*/
			//invoiceExportService.updateUploadPlat(vcInvoicePrintTmpList, uploadPlatList, userCode);
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(IOException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("生成文件失败！"));
			return NONE;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 根据不同机构返回文件存放目录
	 * @param orgCode
	 * @return
	 * @throws Exception
	 */
	private String getDirPath(String orgCode) throws Exception {
		//文件存放路径：不同地区存放不同文件夹
		String filePath = "";
		// 获取路径
		String basePath = this.getClass().getClassLoader().getResource("/").getPath();
		if (basePath.indexOf("WEB-INF/classes") > -1) {
			basePath = basePath.substring(0, basePath.indexOf("WEB-INF/classes"));
		}
		filePath = basePath + SysConst.INVOICE_EXPORT_FILE_PATH + orgCode;
		File dirPath = new File(filePath);
		//如果目录不存在，则创建
		if(!dirPath.isDirectory()){
			dirPath.mkdir();
		}
		//清空目录下前一天的临时文件
		deleteFile(dirPath);
		return filePath;
	}
	
	/**
	 * 浙江地区生产txt文件
	 * @param vcInvoicePrintTmpList
	 * @param filePath
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private String generateTXTFileZJ(List<VcInvoicePrint> vcInvoicePrintTmpList, String filePath) throws IOException,Exception{
		//返回的json数据格式：文件路径==文件名
		String fileData = "";
		String separator = java.io.File.separator;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		DecimalFormat dft = new DecimalFormat("0.00");
		
		//txt文件名
		String fileName = "浙江发票导出_"
				+ df.format(invoiceExportVo.getPrintDateStart()) + "_"
				+ df.format(invoiceExportVo.getPrintDateEnd())+".txt";
		File txtFile = new File(filePath + separator + fileName);
		FileOutputStream out = new FileOutputStream(txtFile);
		StringBuffer sb = new StringBuffer("");
		for(VcInvoicePrint vcInvoicePrint : vcInvoicePrintTmpList){
			//打印类型：1-正常；2-负数；3-作废；4-空白作废
			String printKind = vcInvoicePrint.getPrintKind();
			// 发票代码
			sb.append(vcInvoicePrint.getInvoiceCode()).append("\t");
			// 开票日期
			if("3".equals(printKind) || "4".equals(printKind)){
				//作废时打印日期取作废日期
				Date printDate = vcInvoicePrint.getCanceldDate();
				if(printDate==null){
					printDate = vcInvoicePrint.getPrintDate();
				}
				sb.append(df.format(printDate)).append("\t");
			}else{
				sb.append(df.format(vcInvoicePrint.getPrintDate())).append("\t");
			}
			// 发票号码
			//sb.append(Long.valueOf(vcInvoicePrint.getInvoiceNo())).append("\t");
			sb.append(StringUtil.formatNumberLength(vcInvoicePrint.getInvoiceNo(), 8)).append("\t");
			// 付款方名称
			sb.append(vcInvoicePrint.getPayerName()).append("\t");
			// 付款方识别号, 老系统传空
			//if(StringUtils.isNotEmpty(vcInvoicePrint.getPayerCode())){
			//	sb.append(vcInvoicePrint.getPayerCode()).append("\t");
			//}else{
				sb.append("").append("\t");
			//}
			// 项目摘要：老系统写死'保险费'
			//if(StringUtils.isNotEmpty(vcInvoicePrint.getItems())){
			//	sb.append(vcInvoicePrint.getItems()).append("\t");
			//}else{
				sb.append("保险费").append("\t");
			//}
			//开票金额
			sb.append(dft.format(vcInvoicePrint.getAmount()==null?0.00:vcInvoicePrint.getAmount())).append("\t");
			//发票类别:1-正常票,2-作废票,3-空白作废票
			if("1".equals(vcInvoicePrint.getPrintKind()) || "2".equals(vcInvoicePrint.getPrintKind())){
				sb.append("1");
			}else if("3".equals(vcInvoicePrint.getPrintKind())){
				sb.append("2");
			}else if("4".equals(vcInvoicePrint.getPrintKind())){
				sb.append("3");
			}
			sb.append("\r\n");
		}
		out.write(sb.toString().getBytes());
		out.close();
		fileData = filePath + "==" + fileName;
		logger.info("生成浙江发票数据文件路径：========"+filePath+fileName+"===========");
		return fileData;
	}
	
	/**
	 * 厦门地区导出ZIP
	 * @param vcInvoicePrintTmpList
	 * @param filePath
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private String generateZIPFileXM(List<VcInvoicePrint> vcInvoicePrintTmpList, String filePath) 
	throws BusinessException,IOException,Exception{
		//厦门地区每个xml文件最多包含20000条数据
		if(vcInvoicePrintTmpList.size()>20000){
			throw new BusinessException("一次最多只能导出20000条数据！");
		}
		//返回的json数据格式：文件路径==文件名
		String fileData = "";
		String separator = java.io.File.separator;
		
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DecimalFormat df = new DecimalFormat("0.00");

		InvoiceXMDTO invoiceXMDto = new InvoiceXMDTO();
		List<InvoiceMainXMDTO> mainDtoList = new ArrayList<InvoiceMainXMDTO>();
		invoiceXMDto.setInvoiceMainXMDTOList(mainDtoList);
		for(VcInvoicePrint vcInvoicePrint : vcInvoicePrintTmpList){
			//发票开具类型:1-正常;2-负数;3-作废
			String printKind = vcInvoicePrint.getPrintKind();
			if(vcInvoicePrint.getAmount().signum()<0){
				printKind = "2";
			}
			InvoiceMainXMDTO mainDto = new InvoiceMainXMDTO();
			mainDto.setFpdm(StringUtil.formatNumberLength(vcInvoicePrint.getInvoiceCode(), 12));//发票代码
			mainDto.setFphm(StringUtil.formatNumberLength(vcInvoicePrint.getInvoiceNo(), 8));//发票号码
			mainDto.setJddm(StringUtil.formatNumberLength(vcInvoicePrint.getInvoiceCode(), 12));//机打代码
			mainDto.setJdhm(StringUtil.formatNumberLength(vcInvoicePrint.getInvoiceNo(), 8));//机打号码
			if(StringUtils.isNotEmpty(vcInvoicePrint.getPayerName()) && vcInvoicePrint.getPayerName().length()>36){
				mainDto.setFkfmc(vcInvoicePrint.getPayerName().substring(0, 36));//付款方名称
			}else{
				mainDto.setFkfmc(vcInvoicePrint.getPayerName());//付款方名称
			}
			//mainDto.setFkfhm(vcInvoicePrint.getPayerCode());//付款方号码
			//作废票金额为0
			if("3".equals(printKind) || vcInvoicePrint.getAmount()==null){
				mainDto.setHjje("0.00");//合计金额
			}else{
				mainDto.setHjje(df.format(Math.abs(vcInvoicePrint.getAmount().doubleValue())));//合计金额
			}
			mainDto.setKprmc(vcInvoicePrint.getBillerName());//开票人名称
			if("1".equals(printKind)){
				mainDto.setKplxdm("03");//正常开具
			}else if("2".equals(printKind)){
			    //VC-66 厦门发票导出，对于负数票”开票类型代码（kplx_dm）“应为”03：正常开具“
				mainDto.setKplxdm("03");//开具负数发票
			}else if("3".equals(printKind) || "4".equals(printKind)){
				mainDto.setKplxdm("04");//作废发票
			}else{
				throw new BusinessException("发票开具类型有误！");
			}
//			mainDto.setChfpdm(vcInvoicePrint.getCounteractInvoiceCode());//冲红的发票代码
//			mainDto.setChfphm(vcInvoicePrint.getCounteractInvoiceNo());//冲红的发票号码
			if("2".equals(printKind)){
				mainDto.setBchfpdm(vcInvoicePrint.getCounteractedInvoiceCode());//被冲红的发票代码
				mainDto.setBchfphm(vcInvoicePrint.getCounteractedInvoiceNo());//被冲红的发票号码
			}
			//mainDto.setHyfl(vcInvoicePrint.getIndustryCode());//行业分类
			mainDto.setHyfl("3");//行业分类
			mainDto.setKpsj(sf1.format(vcInvoicePrint.getPrintDate()));//开票日期
			//modify by chy 20140217 FIN-122 备注信息不上传地税
			//mainDto.setBz(vcInvoicePrint.getRemark());//备注
			if(vcInvoicePrint.getCanceldDate()!=null){
				mainDto.setZfsj(sf2.format(vcInvoicePrint.getCanceldDate()));//作废时间
			}
			if(vcInvoicePrint.getOperatorDate()!=null){
				mainDto.setCzsj(sf2.format(vcInvoicePrint.getOperatorDate()));//操作时间
			}
			
			List<VcInvoicePrintExt> vcInvoicePrintExtList = vcInvoicePrint.getVcInvoicePrintExtList();
			if(vcInvoicePrintExtList!=null && vcInvoicePrintExtList.size()!=0){
				List<InvoiceDetialXMDTO> detailList = new ArrayList<InvoiceDetialXMDTO>();
				mainDto.setInvoiceDetialXMDTOList(detailList);
				VcInvoicePrintExt vcInvoicePrintExt = vcInvoicePrintExtList.get(0);
				//保费
				BigDecimal premium = new BigDecimal("0.00");
				if(vcInvoicePrintExt.getPremium()!=null){
					premium = vcInvoicePrintExt.getPremium();
				}
				//车船税+滞纳金
				BigDecimal vesselTax = new BigDecimal("0.00");
				if(vcInvoicePrintExt.getTaxAmount()!=null){
					vesselTax = vesselTax.add(vcInvoicePrintExt.getTaxAmount());
				}
				if(vcInvoicePrintExt.getLateFee()!=null){
					vesselTax = vesselTax.add(vcInvoicePrintExt.getLateFee());
				}
				
				//作废明细金额为0
				if(!"3".equals(printKind)){
					//如果保费不等于0，则组织保费明细
					if(premium.compareTo(BigDecimal.ZERO)!=0){
						InvoiceDetialXMDTO detailDto = new InvoiceDetialXMDTO();
						detailDto.setZkfphyfldm("3");//行业分类
						//保费小于0
						if(premium.compareTo(BigDecimal.ZERO)<0){
							detailDto.setZkfpxmdldm("0394");//项目大类
							detailDto.setHjje(df.format(Math.abs(premium.doubleValue())));//合计金额
						}else{
							//保费大于0
							detailDto.setZkfpxmdldm("0393");//项目大类
							detailDto.setHjje(df.format(premium));//合计金额
						}
						detailList.add(detailDto);
					}
					//如果车船税明细不等于0，则组织车船税明细
					if(vesselTax.compareTo(BigDecimal.ZERO)!=0){
						InvoiceDetialXMDTO detailDto = new InvoiceDetialXMDTO();
						detailDto.setZkfphyfldm("3");//行业分类
						//车船税小于0
						if(vesselTax.compareTo(BigDecimal.ZERO)<0){
							detailDto.setZkfpxmdldm("0397");//项目大类
							detailDto.setHjje(df.format(Math.abs(vesselTax.doubleValue())));//合计金额
						}else{
							//车船税大于0
							detailDto.setZkfpxmdldm("0395");//项目大类
							detailDto.setHjje(df.format(vesselTax));//合计金额
						}
						detailList.add(detailDto);
					}
				}
				if(detailList==null || detailList.size()==0){
					//如果明细列表为空，则组织金额等于0的明细信息
					InvoiceDetialXMDTO detailDto = new InvoiceDetialXMDTO();
					detailDto.setXh("1");//序号
					detailDto.setZkfphyfldm("3");//行业分类
					detailDto.setZkfpxmdldm("0393");//项目大类
					detailDto.setHjje("0.00");//合计金额
					detailList.add(detailDto);
				}else{
					//如果明细列表不为空，则给序号赋值
					for(int i=0;i<detailList.size();i++){
						InvoiceDetialXMDTO detailDto = detailList.get(i);
						detailDto.setXh((i+1)+"");
					}
				}
			}else{
				throw new BusinessException("未找到发票开具扩展信息");
			}
			
//			List<VcInvoicePrintDet> vcInvoicePrintDetList = vcInvoicePrint.getVcInvoicePrintDetList();
			//发票开具明细
//			if(vcInvoicePrintDetList!=null && vcInvoicePrintDetList.size()>0){
//				List<InvoiceDetialXMDTO> detailList = new ArrayList<InvoiceDetialXMDTO>();
//				mainDto.setInvoiceDetialXMDTOList(detailList);
//				/*for(VcInvoicePrintDet vcInvoicePrintDet : vcInvoicePrintDetList){
//					InvoiceDetialXMDTO detailDto = new InvoiceDetialXMDTO();
//					detailDto.setXh(vcInvoicePrintDet.getSerialNo()+"");//序号
//					detailDto.setZkfphyfldm(vcInvoicePrintDet.getIndustry());//行业分类
//					detailDto.setZkfpxmdldm(vcInvoicePrintDet.getItemKind());//项目大类
//					detailDto.setXmmx(vcInvoicePrintDet.getItemName());//项目明细
//					if(vcInvoicePrintDet.getUnitPrice()!=null){
//						detailDto.setDj(df.format(vcInvoicePrintDet.getUnitPrice()));//单价
//					}
//					if(vcInvoicePrintDet.getQuantity()!=null){
//						detailDto.setSl(df.format(vcInvoicePrintDet.getQuantity()));//数量
//					}
//					if(vcInvoicePrintDet.getAmount()!=null){
//						detailDto.setHjje(df.format(vcInvoicePrintDet.getAmount()));//合计金额
//					}
//					detailList.add(detailDto);
//				}*/
//				//保费
//				BigDecimal premium = new BigDecimal("0.00");
//				//车船税+滞纳金
//				BigDecimal vesselTax = new BigDecimal("0.00");
//				for(VcInvoicePrintDet vcInvoicePrintDet : vcInvoicePrintDetList){
//					//项目代码：1-保险费、2-代收车船税、3-滞纳金
//					String itemCode = vcInvoicePrintDet.getItemCode();
//					if("1".equals(itemCode)){
//						premium = premium.add(vcInvoicePrintDet.getAmount());
//					}else if("2".equals(itemCode) || "3".equals(itemCode)){
//						vesselTax = vesselTax.add(vcInvoicePrintDet.getAmount());
//					}
//				}
//				//作废明细金额为0
//				if(!"3".equals(printKind)){
//					//如果保费不等于0，则组织保费明细
//					if(premium.compareTo(BigDecimal.ZERO)!=0){
//						InvoiceDetialXMDTO detailDto = new InvoiceDetialXMDTO();
//						detailDto.setZkfphyfldm("3");//行业分类
//						//保费小于0
//						if(premium.compareTo(BigDecimal.ZERO)<0){
//							detailDto.setZkfpxmdldm("0394");//项目大类
//							detailDto.setHjje(df.format(Math.abs(premium.doubleValue())));//合计金额
//						}else{
//							//保费大于0
//							detailDto.setZkfpxmdldm("0393");//项目大类
//							detailDto.setHjje(df.format(premium));//合计金额
//						}
//						detailList.add(detailDto);
//					}
//					//如果车船税明细不等于0，则组织车船税明细
//					if(vesselTax.compareTo(BigDecimal.ZERO)!=0){
//						InvoiceDetialXMDTO detailDto = new InvoiceDetialXMDTO();
//						detailDto.setZkfphyfldm("3");//行业分类
//						//车船税小于0
//						if(vesselTax.compareTo(BigDecimal.ZERO)<0){
//							detailDto.setZkfpxmdldm("0397");//项目大类
//							detailDto.setHjje(df.format(Math.abs(vesselTax.doubleValue())));//合计金额
//						}else{
//							//车船税大于0
//							detailDto.setZkfpxmdldm("0395");//项目大类
//							detailDto.setHjje(df.format(vesselTax));//合计金额
//						}
//						detailList.add(detailDto);
//					}
//				}
//				if(detailList==null || detailList.size()==0){
//					//如果明细列表为空，则组织金额等于0的明细信息
//					InvoiceDetialXMDTO detailDto = new InvoiceDetialXMDTO();
//					detailDto.setXh("1");//序号
//					detailDto.setZkfphyfldm("3");//行业分类
//					detailDto.setZkfpxmdldm("0393");//项目大类
//					detailDto.setHjje("0.00");//合计金额
//					detailList.add(detailDto);
//				}else{
//					//如果明细列表不为空，则给序号赋值
//					for(int i=0;i<detailList.size();i++){
//						InvoiceDetialXMDTO detailDto = detailList.get(i);
//						detailDto.setXh((i+1)+"");
//					}
//				}
//				//循环组织明细信息（共4条）
//				/*InvoiceDetialXMDTO detailDto1 = new InvoiceDetialXMDTO();
//				InvoiceDetialXMDTO detailDto2 = new InvoiceDetialXMDTO();
//				InvoiceDetialXMDTO detailDto3 = new InvoiceDetialXMDTO();
//				InvoiceDetialXMDTO detailDto4 = new InvoiceDetialXMDTO();
//				detailDto1.setXh("1");//序号
//				detailDto1.setZkfphyfldm("3");//行业分类
//				detailDto1.setZkfpxmdldm("0393");//项目大类
//				detailDto1.setHjje("0.00");//合计金额
//				
//				detailDto2.setXh("2");//序号
//				detailDto2.setZkfphyfldm("3");//行业分类
//				detailDto2.setZkfpxmdldm("0395");//项目大类
//				detailDto2.setHjje("0.00");//合计金额
//				
//				detailDto3.setXh("3");//序号
//				detailDto3.setZkfphyfldm("3");//行业分类
//				detailDto3.setZkfpxmdldm("0394");//项目大类
//				detailDto3.setHjje("0.00");//合计金额
//				
//				detailDto4.setXh("4");//序号
//				detailDto4.setZkfphyfldm("3");//行业分类
//				detailDto4.setZkfpxmdldm("0397");//项目大类
//				detailDto4.setHjje("0.00");//合计金额
//				//作废金额为0
//				if(!"3".equals(printKind)){
//					//如果保费为负，则给0394赋值;否则给0393赋值
//					if(premium.doubleValue()<0.00){
//						detailDto3.setHjje(df.format(Math.abs(premium.doubleValue())));
//					}else{
//						detailDto1.setHjje(df.format(premium));
//					}
//					//如果代收车船税为负，则给0397赋值;否则给0395赋值
//					if(vesselTax.doubleValue()<0.00){
//						detailDto4.setHjje(df.format(Math.abs(vesselTax.doubleValue())));
//					}else{
//						detailDto2.setHjje(df.format(vesselTax));
//					}
//				}
//				detailList.add(detailDto1);
//				detailList.add(detailDto2);
//				detailList.add(detailDto3);
//				detailList.add(detailDto4);*/
//			}
			mainDtoList.add(mainDto);
		}
		//对象转换成xml
		String xml = generateXML(invoiceXMDto, "UTF-8");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//文件前缀
		String preName = "厦门发票导出_"
				+ sdf.format(invoiceExportVo.getPrintDateStart()) + "_"
				+ sdf.format(invoiceExportVo.getPrintDateEnd());
		//xml文件完整路径
		String xmlFullPath = filePath+separator+preName+".xml";
		//zip文件完整路径
		String zipFullPath = filePath+separator+preName+".zip";
		//生成xml文件开始
		File xmlFile = new File(xmlFullPath);
		FileOutputStream out = new FileOutputStream(xmlFile);
		OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
		writer.write(xml);
		writer.flush();
		writer.close();
		out.close();
		//生成xml文件结束
		//生成zip文件
		AntZip antZip = new AntZip();
		antZip.doZip(new File[]{xmlFile}, zipFullPath);
		logger.info("生成厦门发票数据文件路径：========"+zipFullPath+"===========");
		
		//删除xml临时文件
		xmlFile.delete();
		
		//数据包大小限制为200k
		File zipFile = new File(zipFullPath);
		long size = zipFile.length();
		if(size>(200*1024)){
			throw new BusinessException("生成的文件超过最大限制，请减少要导出的数据！");
		}
		
		fileData = filePath + "==" + zipFile.getName();
		return fileData;
	}
	
    /**
     * 河南地区导出XML
     * 
     * @param vcInvoicePrintTmpList
     * @param filePath
     * @return
     * @throws IOException
     * @throws Exception
     */
    private String generateXMLFile_HeNan(List<VcInvoicePrint> vcInvoicePrintTmpList, String filePath,String orgCode)
            throws BusinessException, IOException, Exception {

        // 返回的json数据格式：文件路径==文件名
        String fileData = "";
        String separator = java.io.File.separator;
        
        //根据当前用户所属机构在pub_company中查找纳税人识别号
        PubCompany pubCompany=vcLevelService.findPubCompanyByCode(orgCode);
        String nsrsbh = pubCompany.getTaxNumber(); // 纳税人识别号
        String nsrmc = pubCompany.getCompanyCname(); // 纳税人名称
        String swjgdm = "241009007"; // 纳税人税务机关代码

        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DecimalFormat df = new DecimalFormat("0.00");

        // 放发票开具信息
        InvoiceMainHeNanDTO invoiceMainDto = new InvoiceMainHeNanDTO();
        List<InvoiceMainRowHeNanDTO> invoiceMainHeNanList = invoiceMainDto.getInvoiceMainHeNanList();
        // 放发票开具明细信息
        InvoiceDetailHeNanDTO invoiceDetailDto = new InvoiceDetailHeNanDTO();
        List<InvoiceDetailRowHeNanDTO> invoiceDetailHeNanList = invoiceDetailDto.getInvoiceDetialHeNanDTOList();
        for (VcInvoicePrint vcInvoicePrint : vcInvoicePrintTmpList) {

            InvoiceMainRowHeNanDTO mainDto = new InvoiceMainRowHeNanDTO();
            mainDto.setFpdm(vcInvoicePrint.getInvoiceCode());// 发票代码
            mainDto.setFphm(vcInvoicePrint.getInvoiceNo());// 发票号码
            // 付款方纳税人识别号           
            if(vcInvoicePrint.getPayerCode()!=null){
                mainDto.setFkfhm(vcInvoicePrint.getPayerCode());// 付款方纳税人识别号              
            }else{
                mainDto.setFkfhm("");
            }
            if (vcInvoicePrint.getPayerName() != null) {
                mainDto.setFkfmc(vcInvoicePrint.getPayerName());// 付款方纳税人名称
            } else {
                mainDto.setFkfmc("");// 付款方纳税人名称
            }
            // 纳税人识别号 
            if(StringUtils.isNotBlank(vcInvoicePrint.getTaxpayerCode())){
                mainDto.setSkfhm(vcInvoicePrint.getTaxpayerCode());
            }else{
                mainDto.setSkfhm(nsrsbh);
            }
            
            // 纳税人名称
            if (StringUtils.isNotBlank(vcInvoicePrint.getTaxpayerName())) {
                mainDto.setSkfmc(vcInvoicePrint.getTaxpayerName()); // 纳税人名称
            } else {
                mainDto.setSkfmc(nsrmc); // 纳税人名称
            }

            // 开具金额，NUMBER(16,2)
            if (vcInvoicePrint.getAmount() != null) {
                mainDto.setJe(df.format(vcInvoicePrint.getAmount()));
            } else {
                mainDto.setJe("0.00");
            }
            // 备注
            mainDto.setBz("");// 备注
           
            if (vcInvoicePrint.getTaxOrgCode() != null) {
                //mainDto.setSwjgdm(vcInvoicePrint.getTaxOrgCode()); // 纳税人主管税务机关代码
                 mainDto.setSwjgdm(swjgdm);
            } else {
                 mainDto.setSwjgdm(swjgdm); // 纳税人主管税务机关代码
            }

            mainDto.setYfpzldm("");// 原发票种类（红字开具时必须填写此项信息）
            mainDto.setYfphm("");//红字开具时必须填写此项信息）取【被冲红的发票号码】

            // Kplxdm发票开具类型（11，正常开具；12，红字开具； 21，普通作废；22，空白作废）
            // 21，普通作废；22，空白作废如何区分？？？？？？？
            String printKind = vcInvoicePrint.getPrintKind();// 发票开具类型:1-正常;2-负数;3-作废；4-空白作废
            if(vcInvoicePrint.getAmount().signum()<0){
            	printKind = "2";
            }
            if ("1".equals(printKind)) {
                mainDto.setKplxdm("11");// 正常开具
            } else if ("2".equals(printKind)) {
                mainDto.setKplxdm("12");// 开具负数发票
                
                // 原发票号码（红字开具时必须填写此项信息）取【被冲红的发票号码】
                mainDto.setYfphm(vcInvoicePrint.getCounteractedInvoiceNo());
                // 原发票种类（红字开具时必须填写此项信息）
                mainDto.setYfpzldm(vcInvoicePrint.getCounteractedInvoiceCode());
                
               /* // 根据【被冲红的发票代码、被冲红的发票号码】查询原发票种类
                VcInvoicePrint queryVo = new VcInvoicePrint();
                queryVo.setInvoiceCode(vcInvoicePrint.getCounteractedInvoiceCode());
                queryVo.setInvoiceNo(vcInvoicePrint.getCounteractedInvoiceNo());
                List<VcInvoicePrint> tmpList = invoiceExportService.queryVcInvoicePrintList(queryVo);
                if (tmpList == null || tmpList.size() < 1) {
                    mainDto.setYfpzldm("");
                } else {
                    mainDto.setYfpzldm(tmpList.get(0).getInvoiceKindCode());
                }*/

            } else if ("3".equals(printKind)) {
                mainDto.setKplxdm("21");// 作废发票
            } else if ("4".equals(printKind)) {
                mainDto.setKplxdm("22");// 空白发票
            }
            // kpsj 开票日期，DATE，格式：yyyy-mm-dd
            mainDto.setKpsj(sf1.format(vcInvoicePrint.getPrintDate()));// 开票日期

            // 发票行业代码  --金融保险
            mainDto.setFphydm("020302");

            List<VcInvoicePrintExt> vcInvoicePrintExtList = vcInvoicePrint.getVcInvoicePrintExtList();
            if(vcInvoicePrintExtList!=null && vcInvoicePrintExtList.size()!=0){
            	VcInvoicePrintExt VcInvoicePrintExt = vcInvoicePrintExtList.get(0);
            	int serialNo = 1;
            	if(VcInvoicePrintExt.getPremium()!=null && VcInvoicePrintExt.getPremium().compareTo(BigDecimal.ZERO)!=0){
	            	InvoiceDetailRowHeNanDTO detailDto1 = new InvoiceDetailRowHeNanDTO();
	                detailDto1.setFpdm(vcInvoicePrint.getInvoiceCode());// 发票代码
	                detailDto1.setFphm(vcInvoicePrint.getInvoiceNo());// 发票号码
                    detailDto1.setXh((serialNo++)+"");// 序号
                    detailDto1.setXmmc("保险费");
                    detailDto1.setHjje(df.format(VcInvoicePrintExt.getPremium()));// 金额，NUMBER(16,2)
	                invoiceDetailHeNanList.add(detailDto1);
            	}
            	if(VcInvoicePrintExt.getTaxAmount()!=null && VcInvoicePrintExt.getTaxAmount().compareTo(BigDecimal.ZERO)!=0){
	            	InvoiceDetailRowHeNanDTO detailDto1 = new InvoiceDetailRowHeNanDTO();
	                detailDto1.setFpdm(vcInvoicePrint.getInvoiceCode());// 发票代码
	                detailDto1.setFphm(vcInvoicePrint.getInvoiceNo());// 发票号码
                    detailDto1.setXh((serialNo++)+"");// 序号
                    detailDto1.setXmmc("代收车船税");
                    detailDto1.setHjje(df.format(VcInvoicePrintExt.getTaxAmount()));// 金额，NUMBER(16,2)
	                invoiceDetailHeNanList.add(detailDto1);
            	}
            	if(VcInvoicePrintExt.getLateFee()!=null && VcInvoicePrintExt.getLateFee().compareTo(BigDecimal.ZERO)!=0){
	            	InvoiceDetailRowHeNanDTO detailDto1 = new InvoiceDetailRowHeNanDTO();
	                detailDto1.setFpdm(vcInvoicePrint.getInvoiceCode());// 发票代码
	                detailDto1.setFphm(vcInvoicePrint.getInvoiceNo());// 发票号码
                    detailDto1.setXh((serialNo++)+"");// 序号
                    detailDto1.setXmmc("代收车船税滞纳金");
                    detailDto1.setHjje(df.format(VcInvoicePrintExt.getLateFee()));// 金额，NUMBER(16,2)
	                invoiceDetailHeNanList.add(detailDto1);
            	}
            }else{
            	InvoiceDetailRowHeNanDTO detailDto = new InvoiceDetailRowHeNanDTO();
                detailDto.setFpdm(vcInvoicePrint.getInvoiceCode());// 发票代码
                detailDto.setFphm(vcInvoicePrint.getInvoiceNo());// 发票号码
               
                detailDto.setXh("1");// 序号
                // 项目名称
                 detailDto.setXmmc("");
                // 金额，NUMBER(16,2)
                detailDto.setHjje("");// 金额，NUMBER(16,2)
                invoiceDetailHeNanList.add(detailDto);
            }
            /*List<VcInvoicePrintDet> vcInvoicePrintDetList = vcInvoicePrint.getVcInvoicePrintDetList();
            // 发票开具明细
            if (vcInvoicePrintDetList != null && vcInvoicePrintDetList.size() > 0) {
                for (VcInvoicePrintDet vcInvoicePrintDet : vcInvoicePrintDetList) {
                    InvoiceDetailRowHeNanDTO detailDto = new InvoiceDetailRowHeNanDTO();
                    detailDto.setFpdm(vcInvoicePrint.getInvoiceCode());// 发票代码
                    detailDto.setFphm(vcInvoicePrint.getInvoiceNo());// 发票号码
                    if (vcInvoicePrintDet.getSerialNo() == null) {
                        detailDto.setXh("");// 序号
                    } else {
                        detailDto.setXh(vcInvoicePrintDet.getSerialNo() + "");// 序号
                    }

                    // 项目名称
                    if (vcInvoicePrintDet.getItemName() != null) {
                        detailDto.setXmmc(vcInvoicePrintDet.getItemName());// 项目名称
                    } else {
                        detailDto.setXmmc("");
                    }
                    // 金额，NUMBER(16,2)
                    if (vcInvoicePrintDet.getAmount() != null) {
                        detailDto.setHjje(df.format(vcInvoicePrintDet.getAmount()));// 金额，NUMBER(16,2)
                    } else {
                        detailDto.setHjje("0.00");// 金额，NUMBER(16,2)
                    }
                    invoiceDetailHeNanList.add(detailDto);
                }
            }else{
                InvoiceDetailRowHeNanDTO detailDto = new InvoiceDetailRowHeNanDTO();
                detailDto.setFpdm(vcInvoicePrint.getInvoiceCode());// 发票代码
                detailDto.setFphm(vcInvoicePrint.getInvoiceNo());// 发票号码
               
                detailDto.setXh("1");// 序号
                // 项目名称
                 detailDto.setXmmc("");
                // 金额，NUMBER(16,2)
                detailDto.setHjje("");// 金额，NUMBER(16,2)
                invoiceDetailHeNanList.add(detailDto);
            }*/

            invoiceMainHeNanList.add(mainDto);
        }
        // 对象转换成xml
        String mainXml = generateXML(invoiceMainDto, "GB2312");
        String detailXml = generateXML(invoiceDetailDto, "GB2312");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // 文件前缀
       /* String mainPreName = "d_ykpxx(" + sdf.format(invoiceExportVo.getPrintDateStart()) + "-"
                + sdf.format(invoiceExportVo.getPrintDateEnd()) + ")";
        String detPreName = "d_ykpxxmx(" + sdf.format(invoiceExportVo.getPrintDateStart()) + "-"
                + sdf.format(invoiceExportVo.getPrintDateEnd()) + ")";*/
        
        String mainPreName = "d_ykpxx";
        String detPreName = "d_ykpxxmx";

        // xml文件完整路径
        String mainXmlFullPath = filePath + separator + mainPreName + ".xml";
        String detXmlFullPath = filePath + separator + detPreName + ".xml";

        // 生成xml文件开始
        File mainXmlFile = new File(mainXmlFullPath);

        FileOutputStream mainOut = new FileOutputStream(mainXmlFile);
        OutputStreamWriter mainWriter = new OutputStreamWriter(mainOut, "GB2312");
        mainWriter.write(mainXml);
        mainWriter.flush();
        mainWriter.close();
        mainOut.close();
        File detXmlFile = new File(detXmlFullPath);
        FileOutputStream detOut = new FileOutputStream(detXmlFile);
        OutputStreamWriter detWriter = new OutputStreamWriter(detOut, "GB2312");
        detWriter.write(detailXml);
        detWriter.flush();
        detWriter.close();
        detOut.close();
        // 生成xml文件结束

        logger.info("生成河南发票开具信息【d_ykpxx】文件路径" + mainXmlFullPath + "===========");
        logger.info("生成河南发票开具明细信息【d_ykpxxmx】文件路径" + detXmlFullPath + "===========");

        //fileData = filePath + "==" + mainXmlFile.getName()+"@@"+filePath + "==" + detXmlFile.getName();
        //fileData = filePath + "==" + mainXmlFile.getName();
        
       
        //文件打包导出
      //zip文件完整路径
        String zipPreName = "河南发票导出(" + serial_HeNan + ")";
        String zipFullPath = filePath+separator+zipPreName+".zip";
        AntZip antZip = new AntZip();
        antZip.doZip(new File[]{mainXmlFile,detXmlFile}, zipFullPath);       
        //数据包大小限制为200k
        File zipFile = new File(zipFullPath);
        //删除临时文件
        mainXmlFile.delete();
        detXmlFile.delete();
        logger.info("河南发票开具细ZIP文件路径" + zipFullPath + "===========");
        fileData = filePath + "==" + zipFile.getName();
        serial_HeNan++;
        return fileData;
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
    private String generateTXTFile_ChongQing(List<VcInvoicePrint> vcInvoicePrintTmpList, String filePath,String orgCode)
            throws IOException, Exception {
        
      //根据当前用户所属机构在pub_company中查找纳税人识别号
        PubCompany pubCompany=vcLevelService.findPubCompanyByCode(orgCode);
        String nsrsbh = pubCompany.getTaxNumber(); // 纳税人识别号
        String nsrmc = pubCompany.getCompanyCname(); // 纳税人名称
        
        // 返回的json数据格式：文件路径==文件名
        String fileData = "";
        String separator = java.io.File.separator;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DecimalFormat dft = new DecimalFormat("0.00");

        // txt文件名
        /*
         * String fileName = "重庆发票导出_" + sdf.format(invoiceExportVo.getPrintDateStart()) + "_" +
         * sdf.format(invoiceExportVo.getPrintDateEnd()) + ".txt";
         */
        String fileName = InvoiceExportAction.genarateChongQingFileName();
        File txtFile = new File(filePath + separator + fileName);
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
	 * 江苏地区导出xml文件
	 * @param vcInvoicePrintTmpList
	 * @param orgCode
	 * @param filePath
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 * @throws Exception
	 * @author chy
	 */
	/*private String generateXMLFileJS(List<VcInvoicePrint> vcInvoicePrintTmpList, String orgCode, String filePath) 
	throws BusinessException,IOException,Exception{
		//根据当前机构查询管理码
		VcTaxPayerLogin vcTaxPayerLogin = invoiceExportService.findVcTaxPayerLogin(orgCode);
		if(vcTaxPayerLogin==null){
			throw new BusinessException("没有查询到当前机构对应的税务管理码，请确认！");
		}
		//税务管理码
		String managerCode = vcTaxPayerLogin.getComputerNo();
		int size = vcInvoicePrintTmpList.size();
		//江苏地区每个xml文件最多包含200条数据
		if(size>200){
			throw new BusinessException("一次最多只能导出200条数据，请分批导出！");
		}
		//返回的json数据格式：文件路径==文件名
		String fileData = "";
		String separator = java.io.File.separator;
		
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DecimalFormat df = new DecimalFormat("0.00");

		//请求对象
		JSInvoiceRequestDTO request = new JSInvoiceRequestDTO();
		//请求头
		JSInvoiceRequestHeadDTO requestHead = new JSInvoiceRequestHeadDTO();
		requestHead.setYyid(managerCode+"001");//应用服务器ID：税务管理码+3位顺序号
		requestHead.setYwxtid("001");//业务系统id：3位顺序号
		requestHead.setQqsj(timeFmt.format(new Date()));//发起时间
		requestHead.setBwlx("01");//报文类型：01-发票信息上传   02-发票信息查询  03-票号资源获取
		requestHead.setSwglm(managerCode);//税务管理码
		requestHead.setFpfs(size+"");//本次传送的发票份数
		requestHead.setFpdm(invoiceExportVo.getInvoiceCode());//发票代码
		requestHead.setFphmq(vcInvoicePrintTmpList.get(0).getInvoiceNo());//发票号码起，如果为非连续的则为第一张发票的号码
		requestHead.setFphmz(vcInvoicePrintTmpList.get(size-1).getInvoiceNo());//发票号码止，如果为非连续的则为最后一张发票的号码 
		requestHead.setBwlsh(System.currentTimeMillis()+"");//报文流水号
		requestHead.setMbbh("952");//模板编号
		requestHead.setFpzldm("23200074100000");//发票种类代码
		//发票列表
		List<JSInvoiceDTO> invoices = new ArrayList<JSInvoiceDTO>();
		for(VcInvoicePrint vcInvoicePrint : vcInvoicePrintTmpList){
			//发票开具扩展信息
			VcInvoicePrintExt vcInvoicePrintExt = vcInvoicePrint.getVcInvoicePrintExtList().get(0);
			JSInvoiceDTO jSInvoiceDTO = new JSInvoiceDTO();
			JSInvoiceDataDTO data = new JSInvoiceDataDTO();
			//发票代码
			String invoiceCode = formatHaoMa(vcInvoicePrint.getInvoiceCode(),12);
			//发票号码
			String invoiceNo = formatHaoMa(vcInvoicePrint.getInvoiceNo(),8);
			data.setLsh(invoiceCode + invoiceNo);//流水号
			data.setSwglm(managerCode);//税务管理码
			data.setFpdm(invoiceCode);//发票代码
			data.setFphm(invoiceNo);//发票号码
			data.setJddm(invoiceCode);//机打代码
			data.setJdhm(invoiceNo);//机打号码
			//开票日期
			if(vcInvoicePrint.getPrintDate()==null){
				data.setKprq(dateFmt.format(new Date()));
			}else{
				data.setKprq(dateFmt.format(vcInvoicePrint.getPrintDate()));
			}
			data.setHydm(vcInvoicePrint.getIndustryCode());//行业代码--交强险取0321，非交强险取0320？
			data.setHymc(vcInvoicePrint.getIndustryName());//行业名称
			if(StringUtils.isNotEmpty(vcInvoicePrint.getPayerCode())){
				data.setFkfbh(vcInvoicePrint.getPayerCode());//付款方编号
			}
			data.setFkfmc(vcInvoicePrint.getPayerName());//付款方名称
			//金额合计
			if(vcInvoicePrint.getAmount()!=null){
				data.setJine(df.format(vcInvoicePrint.getAmount()));
			}else{
				data.setJine("0.00");
			}
			data.setBz(vcInvoicePrint.getRemark());//备注
			data.setCbxz(vcInvoicePrintExt.getRiskCode());//承保险种名称
			data.setBxdh(vcInvoicePrint.getPolicyNo());//保险单号
			data.setPdh(vcInvoicePrint.getEndorseNo());//批单号
			if(vcInvoicePrintExt.getPremium()!=null){
				data.setBxjine(df.format(vcInvoicePrintExt.getPremium()));//保费金额
			}
			if(vcInvoicePrintExt.getPayDateStart()!=null){
				data.setSjrqq(dateFmt.format(vcInvoicePrintExt.getPayDateStart()));//所缴日期起
			}
			if(vcInvoicePrintExt.getPayDateEnd()!=null){
				data.setSjrqz(dateFmt.format(vcInvoicePrintExt.getPayDateEnd()));//所缴日期止
			}
			if(vcInvoicePrintExt.getTaxAmount()!=null){
				data.setDsccs(df.format(vcInvoicePrintExt.getTaxAmount()));//代收车船税
			}
			if(vcInvoicePrintExt.getLateFee()!=null){
				data.setZnj(df.format(vcInvoicePrintExt.getLateFee()));//滞纳金
			}
			data.setBizhong(vcInvoicePrintExt.getCurrency());//币种
			data.setHuil(vcInvoicePrintExt.getExchangeRate()+"");//汇率
			if(vcInvoicePrintExt.getQuotePriceDate()!=null){
				data.setPjr(dateFmt.format(vcInvoicePrintExt.getQuotePriceDate()));//牌价日
			}
			if(vcInvoicePrintExt.getForCurrAmount()!=null){
				data.setWbjine(df.format(vcInvoicePrintExt.getForCurrAmount()));//外币金额
			}
			data.setKhyh(vcInvoicePrint.getBank());//开户行
			data.setYhzh(vcInvoicePrint.getAccount());//银行账号
			data.setKprmc(vcInvoicePrint.getBillerName());//开票人姓名
			data.setSkfmc(vcInvoicePrint.getRecipientName());//收款方名称
			data.setSkfsbh(vcInvoicePrint.getRecipientCode());//收款方纳税人识别号
			data.setYzm(vcInvoicePrint.getCheckNum());//验证码
			String printKind = vcInvoicePrint.getPrintKind();//发票开具类型:1-正常;2-负数;3-作废
			if("1".equals(printKind)){
				data.setFpzt("1");//1-正常票
			}else if("2".equals(printKind)){
				data.setFpzt("2");//2-红冲票
				String bchfpdm = vcInvoicePrint.getCounteractedInvoiceCode();
				String bchfphm = vcInvoicePrint.getCounteractedInvoiceNo();
				if(StringUtils.isNotEmpty(bchfpdm)){
					data.setYfpdm(formatHaoMa(bchfpdm, 12));//原发票代码
				}
				if(StringUtils.isNotEmpty(bchfphm)){
					data.setYfphm(formatHaoMa(bchfphm, 8));//员发票号码
				}
			}else if("3".equals(printKind)){
				data.setFpzt("3");//3-作废票
				data.setZfrdm(vcInvoicePrint.getCanceledOprCode());//作废人代码
				data.setZfrmc(vcInvoicePrint.getCanceledOpr());//作废人名称
				if(vcInvoicePrint.getCanceldDate()!=null){
					data.setZfrq(dateFmt.format(vcInvoicePrint.getCanceldDate()));//作废日期
				}
			}
			jSInvoiceDTO.setData(data);
			invoices.add(jSInvoiceDTO);
		}
		request.setHead(requestHead);
		request.setInvoices(invoices);
		//对象转换成xml
		String xml = generateXML(request, "gbk");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//文件前缀
		String fileName = "江苏发票导出_"
				+ sdf.format(invoiceExportVo.getPrintDateStart()) + "_"
				+ sdf.format(invoiceExportVo.getPrintDateEnd())+".xml";
		String fullName = filePath+separator+fileName;
		//生成xml文件开始
		File xmlFile = new File(fullName);
		FileOutputStream out = new FileOutputStream(xmlFile);
		OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
		writer.write(xml);
		writer.flush();
		writer.close();
		out.close();
		//生成xml文件结束
		logger.info("生成江苏发票数据文件路径：========"+fullName+"===========");
		
		fileData = filePath + "==" + fileName;
		return fileData;
	}*/
	
	/**
	 * 江苏导出excel文件
	 * @param vcInvoicePrintTmpList
	 * @param filePath
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 * @throws Exception
	 * @author chy
	 */
	private String generateExcelJiangsu(List<VcInvoicePrint> vcInvoicePrintTmpList,
			String filePath) throws BusinessException{
		String fileData = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df = new DecimalFormat("0.00"); // 格式化金额
		//分隔符
		String separator = java.io.File.separator;
		//导出的excel文件名
		String fileName = sdf.format(new Date()) + ".xls";
		//导出文件完整路径
		String fullName = filePath + separator + fileName;
		WritableWorkbook wb=null;
		Workbook wb1 = null;
		FileInputStream in = null;
		try{
			String basePath = this.getClass().getClassLoader().getResource("/").getPath();
			if (basePath.indexOf("WEB-INF/classes") > -1) {
				basePath = basePath.substring(0, basePath.indexOf("WEB-INF/classes"));
			}
			//模板路径
			String modelFilePath = basePath + SysConst.INVOICE_EXPORT_MODEL_PATH + 
				separator + "InvoiceExport_0161_model.xls";
			//创建模板文件
			File modelFile = new File(modelFilePath);
			in = new FileInputStream(modelFile);
			wb1 = Workbook.getWorkbook(in);
			//导出文件
			File file = new File(fullName);
			//根据模板创建要导出的excel
			wb = Workbook.createWorkbook(file, wb1);
			//获取第一个sheet页
			WritableSheet sheet = wb.getSheet(0);
			sheet.setName(sdf.format(new Date()));
			//数据起始行号
			int startNo = 6;
			int size = vcInvoicePrintTmpList.size();
			WritableFont stl = new WritableFont(WritableFont.ARIAL, 8,WritableFont.NO_BOLD, false); 
			WritableCellFormat wcfmt = new WritableCellFormat(stl);
			wcfmt.setWrap(true);
			wcfmt.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfmt.setBorder(Border.ALL, BorderLineStyle.MEDIUM, Colour.WHITE);
			wcfmt.setBackground(Colour.GRAY_25);
			for(int i=0;i<size;i++){
				VcInvoicePrint vcInvoicePrint = vcInvoicePrintTmpList.get(i);
				VcInvoicePrintExt ext = vcInvoicePrint.getVcInvoicePrintExtList().get(0);
				//发票号码
				String invoiceCode = StringUtil.formatNumberLength(vcInvoicePrint.getInvoiceCode(), 20);//BW系统中导出的发票号码为20位
				//发票代码
				String invoiceNo = StringUtil.formatNumberLength(vcInvoicePrint.getInvoiceNo(), 8);
				//行号
				int row = i+startNo;
				//列号
				int col=0;
				//1、凭证号或流水号（发票代码+发票号码）
				sheet.addCell(new Label(col++, row, invoiceCode+invoiceNo ,wcfmt));
				//2、发票代码
				sheet.addCell(new Label(col++, row, invoiceCode ,wcfmt));
				//3、发票号码
				sheet.addCell(new Label(col++, row, invoiceNo ,wcfmt));
				//4、开票日期
				sheet.addCell(new Label(col++, row, dateFmt.format(vcInvoicePrint.getPrintDate()) ,wcfmt));
				//5、行业代码
				//sheet.addCell(new Label(col++, row, vcInvoicePrint.getIndustryCode() ,wcfmt));
				String riskCode = ext.getRiskCode();
				if("C601".equals(riskCode)||"C602".equals(riskCode)||"C603".equals(riskCode)||"C604".equals(riskCode)
						|| "0801".equals(riskCode) || "0806".equals(riskCode)){
					sheet.addCell(new Label(col++, row, "0321" ,wcfmt));//交强险-0321
				}else{
					sheet.addCell(new Label(col++, row, "0320" ,wcfmt));//非交强险-0320
				}
				//6、行业名称
				sheet.addCell(new Label(col++, row, "#" ,wcfmt));
				//7、发票序号
				sheet.addCell(new Label(col++, row, "1" ,wcfmt));
				//8、付款方编号
				sheet.addCell(new Label(col++, row, "#" ,wcfmt));
				//9、付款方名称
				sheet.addCell(new Label(col++, row, vcInvoicePrint.getPayerName() ,wcfmt));
				//10、备注
				//modify by chy 20140217 FIN-122 备注信息不上传地税 begin
				//if(StringUtils.isEmpty(vcInvoicePrint.getRemark())){
					sheet.addCell(new Label(col++, row, "#" ,wcfmt));
				//}else{
				//	sheet.addCell(new Label(col++, row, vcInvoicePrint.getRemark() ,wcfmt));
				//}
				//modify by chy 20140217 FIN-122 备注信息不上传地税 end
				//11、承保险种名称
				sheet.addCell(new Label(col++, row, ext.getRiskName() ,wcfmt));
				//12、保险单号
				sheet.addCell(new Label(col++, row, vcInvoicePrint.getPolicyNo() ,wcfmt));
				//13、批单号
				if(StringUtils.isEmpty(vcInvoicePrint.getEndorseNo())){
					sheet.addCell(new Label(col++, row, "#" ,wcfmt));
				}else{
					sheet.addCell(new Label(col++, row, vcInvoicePrint.getEndorseNo() ,wcfmt));
				}
				//14、所缴日期起
				sheet.addCell(new Label(col++, row, "#" ,wcfmt));
				//15、所缴日期止
				sheet.addCell(new Label(col++, row, "#" ,wcfmt));
				//16、币种
				if(StringUtils.isEmpty(ext.getCurrency())){
					sheet.addCell(new Label(col++, row, "RMB" ,wcfmt));
				}else{
					sheet.addCell(new Label(col++, row, ext.getCurrency() ,wcfmt));
				}
				//17、牌价日
				if(ext.getQuotePriceDate()!=null){
					sheet.addCell(new Label(col++, row, dateFmt.format(ext.getQuotePriceDate()) ,wcfmt));
				}else{
					sheet.addCell(new Label(col++, row, "#" ,wcfmt));
				}
				//18、开户银行
				if(StringUtils.isEmpty(vcInvoicePrint.getBank())){
					sheet.addCell(new Label(col++, row, "#" ,wcfmt));
				}else{
					sheet.addCell(new Label(col++, row, vcInvoicePrint.getBank() ,wcfmt));
				}
				//19、银行账号
				if(StringUtils.isEmpty(vcInvoicePrint.getAccount())){
					sheet.addCell(new Label(col++, row, "#" ,wcfmt));
				}else{
					sheet.addCell(new Label(col++, row, vcInvoicePrint.getAccount() ,wcfmt));
				}
				//20、开票人姓名
				sheet.addCell(new Label(col++, row, vcInvoicePrint.getBillerName() ,wcfmt));
				//21、收款方名称
				sheet.addCell(new Label(col++, row, vcInvoicePrint.getTaxpayerName() ,wcfmt));
				//22、收款方纳税人识别号
				sheet.addCell(new Label(col++, row, vcInvoicePrint.getTaxpayerCode() ,wcfmt));
				//23、验证码
				sheet.addCell(new Label(col++, row, vcInvoicePrint.getCheckNum() ,wcfmt));
				String printKind = "1";
				if("1".equals(vcInvoicePrint.getPrintKind())){
					printKind = "1";//正常票
				}else if("2".equals(vcInvoicePrint.getPrintKind())){
					printKind = "2";//红冲票
				}else if("3".equals(vcInvoicePrint.getPrintKind())){
					printKind = "3";//作废票
				}
				//24、发票状态
				sheet.addCell(new Label(col++, row, printKind ,wcfmt));
				if("3".equals(printKind)){
					//25、作废人代码
					sheet.addCell(new Label(col++, row, vcInvoicePrint.getCanceledOprCode() ,wcfmt));
					//26、作废人名称
					sheet.addCell(new Label(col++, row, vcInvoicePrint.getCanceledOpr() ,wcfmt));
					//27、作废日期
					sheet.addCell(new Label(col++, row, dateFmt.format(vcInvoicePrint.getCanceldDate()) ,wcfmt));
				}else{
					//25、作废人代码
					sheet.addCell(new Label(col++, row, "#" ,wcfmt));
					//26、作废人名称
					sheet.addCell(new Label(col++, row, "#" ,wcfmt));
					//27、作废日期
					sheet.addCell(new Label(col++, row, "#" ,wcfmt));
				}
				if("2".equals(printKind)){
					//28、原发票代码
					sheet.addCell(new Label(col++, row, StringUtil.formatNumberLength(vcInvoicePrint.getCounteractedInvoiceCode(), 20) ,wcfmt));
					//29、原发票号码
					sheet.addCell(new Label(col++, row, StringUtil.formatNumberLength(vcInvoicePrint.getCounteractedInvoiceNo(), 8) ,wcfmt));
				}else{
					//28、原发票代码
					sheet.addCell(new Label(col++, row, "#" ,wcfmt));
					//29、原发票号码
					sheet.addCell(new Label(col++, row, "#" ,wcfmt));
				}
				//30、金额
				if(vcInvoicePrint.getAmount()!=null){
					sheet.addCell(new Label(col++, row, df.format(vcInvoicePrint.getAmount()) ,wcfmt));
				}else{
					sheet.addCell(new Label(col++, row, "0.00" ,wcfmt));
				}
				//31、保险金额
				if(ext.getPremium()!=null){
					sheet.addCell(new Label(col++, row, df.format(ext.getPremium()) ,wcfmt));
				}else{
					sheet.addCell(new Label(col++, row, "" ,wcfmt));
				}
				//32、代收车船税
				if(ext.getTaxAmount()!=null && ext.getTaxAmount().compareTo(BigDecimal.ZERO)!=0){
					sheet.addCell(new Label(col++, row, df.format(ext.getTaxAmount()) ,wcfmt));
				}else{
					sheet.addCell(new Label(col++, row, "" ,wcfmt));
				}
				//33、滞纳金
				if(ext.getLateFee()!=null && ext.getLateFee().compareTo(BigDecimal.ZERO)!=0){
					sheet.addCell(new Label(col++, row, df.format(ext.getLateFee()) ,wcfmt));
				}else{
					sheet.addCell(new Label(col++, row, "" ,wcfmt));
				}
				//34、汇率
				if(ext.getExchangeRate()!=null && ext.getExchangeRate().compareTo(BigDecimal.ZERO)!=0){
					sheet.addCell(new Label(col++, row, ext.getExchangeRate().toString() ,wcfmt));
				}else{
					sheet.addCell(new Label(col++, row, "" ,wcfmt));
				}
				//35、外币金额
				if(ext.getForCurrAmount()!=null && ext.getForCurrAmount().compareTo(BigDecimal.ZERO)!=0){
					sheet.addCell(new Label(col++, row, df.format(ext.getForCurrAmount()) ,wcfmt));
				}else{
					sheet.addCell(new Label(col++, row, "" ,wcfmt));
				}
			}
			wb.write();
		}catch(Exception e){
			throw new BusinessException("创建文件失败！", e);
		}finally{
			try{
				if(wb1!=null)
					wb1.close();
				if(wb!=null)
					wb.close();
				if(in!=null)
					in.close();
			}catch(Exception e){
				throw new BusinessException(e.getMessage(), e);
			}
		}
		logger.info("生成江苏发票数据文件路径：========"+fullName+"===========");
		fileData = filePath + "==" + fileName;
		return fileData;
	}
	
	/**
     * 山西地区导出文件夹
     * 
     * @param vcInvoicePrintTmpList
     * @param filePath
     * @return
     * @throws IOException
     * @throws Exception
     */
    private String generateFileSX(List<VcInvoicePrint> vcInvoicePrintTmpList,
            String filePath) throws BusinessException, IOException, Exception {
        InvoiceSXMainFileDTO invoiceSXMainFileDTO = new InvoiceSXMainFileDTO(); // 主文件
        InvoiceSXMainFileMxDTO invoiceSXMainFileMxDTO = new InvoiceSXMainFileMxDTO();// 主文件明细
        InvoiceSXDTO invoiceSXDTO = null;// 开票信息文件
        InvoiceSXSSQDTO invoiceSXSSQDTO = null;//开票信息所属期模块
        List<InvoiceSXXXDTO> invoiceSXXXDTOList = null;// 开票信息主体模块
        InvoiceSXXXDTO invoiceSXXXDTO = null;
        InvoiceSXBTDTO invoiceSXBTDTO = null;// 开票信息表头
        List<InvoiceSXMXDTO> invoiceSXMXDTOList = null;// 开票信息明细

        String separator = java.io.File.separator;
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd"); // 格式化日期
        SimpleDateFormat sdfSsq = new SimpleDateFormat("yyyyMM"); // 格式化所属期
        DecimalFormat df = new DecimalFormat("0.00"); // 格式化金额
        
        String nsrbm = vcInvoicePrintTmpList.get(0).getTaxpayerCode();//纳税人编码
        String ssq = getSsq();//所属期
        BigDecimal totalAmount = BigDecimal.ZERO;//开票金额合计
        int fileSequence = 1;// 开票信息序号
        String folder =nsrbm + "-" + ssq;//文件夹名称
        //生成文件夹
		File dirPath = new File(filePath + separator + folder);
		if(!dirPath.isDirectory()){
			dirPath.mkdir();
		}
        
        //若导出记录超过50000，拆分list
        List<List<VcInvoicePrint>> list = splitVcInvoicePrintList(vcInvoicePrintTmpList,50000);
        for (List<VcInvoicePrint> vcInvoicePrintList : list) {
            //开票信息文件实例化
            invoiceSXDTO = new InvoiceSXDTO();
            //开票信息所属期模块实例化
            invoiceSXSSQDTO = new InvoiceSXSSQDTO();
            //开票信息主体模块实例化
            invoiceSXXXDTOList = new ArrayList<InvoiceSXXXDTO>();

            invoiceSXSSQDTO.setNsrbm(nsrbm); // 纳税人电子档案号 必填
            invoiceSXSSQDTO.setSsq(ssq); // 所属期 必填
            invoiceSXSSQDTO.setWjbh(String.valueOf(fileSequence)); // 开票信息文件编号 必填
            //开票信息文件封装所属期模块
            invoiceSXDTO.setInvoiceSXSSQDTO(invoiceSXSSQDTO);

            for (VcInvoicePrint vcInvoicePrint : vcInvoicePrintList) {
                //主文件开票金额合计
                if (vcInvoicePrint.getAmount() != null) {
                    BigDecimal amount=vcInvoicePrint.getAmount(); 
                    totalAmount = totalAmount.add(amount);
                }
                //开票信息实例化
                invoiceSXXXDTO = new InvoiceSXXXDTO();
                //开票信息表头实例化
                invoiceSXBTDTO = new InvoiceSXBTDTO();
                //开票信息明细队列实例化
                invoiceSXMXDTOList = new ArrayList<InvoiceSXMXDTO>(); // xml
                //获取开票信息明细数据
                //List<VcInvoicePrintDet> vcInvoicePrintDetList = vcInvoicePrint.getVcInvoicePrintDetList();

                invoiceSXBTDTO.setNsrsbh(formatNullToEmpty(vcInvoicePrint.getTaxpayerCode())); // 纳税人识别号 必填
                invoiceSXBTDTO.setSkfDM(formatNullToEmpty(vcInvoicePrint.getTaxpayerCode())); // 收款方代码
                invoiceSXBTDTO.setSkfMc(formatNullToEmpty(vcInvoicePrint.getTaxpayerName())); // 收款方名称
                invoiceSXBTDTO.setFkfDm(""); // 付款方代码
                invoiceSXBTDTO.setFkfMc(formatNullToEmpty(vcInvoicePrint.getPayerName())); // 付款方名称
                invoiceSXBTDTO.setFpdm(vcInvoicePrint.getInvoiceCode()); // 发票代码	 必填
                invoiceSXBTDTO.setFpmc("山西省地方税务局通用机打发票"); // 发票名称
                invoiceSXBTDTO.setFphm(vcInvoicePrint.getInvoiceNo()); // 发票号码 必填
                invoiceSXBTDTO.setKprq(sf1.format(vcInvoicePrint.getPrintDate())); // 开票日期 必填
                invoiceSXBTDTO.setYfpdm(""); // 原发票代码
                invoiceSXBTDTO.setYfphm(""); // 原发票号码
                invoiceSXBTDTO.setHeje(""); // 合计金额
                invoiceSXBTDTO.setHese(""); // 合计税额 车船税
                invoiceSXBTDTO.setSl("");
                invoiceSXBTDTO.setSkymc(""); // 收款员名称
                invoiceSXBTDTO.setKpymc(""); // 开票员名称
                invoiceSXBTDTO.setHyDm(""); // 行业代码
                invoiceSXBTDTO.setHyMc("保险业"); // 行业名称
    			String printKind = vcInvoicePrint.getPrintKind();//发票开具类型:1-正常;2-负数;3-作废
    			// 发票状态
    			if("1".equals(printKind) || "2".equals(printKind)){
    				invoiceSXBTDTO.setKplx("0");//正常发票 0正票 1作废票 -1红票
    			}else if("3".equals(printKind) ||"4".equals(printKind)){
    				invoiceSXBTDTO.setKplx("1");;//作废发票 0正票 1作废票 -1红票
    			}
                invoiceSXBTDTO.setFpzlDm("214031203"); // 发票种类代码
                invoiceSXBTDTO.setFpzlMc("山西省地方税务局通用机打发票（平推241×177.8）"); // 发票种类名称
                invoiceSXBTDTO.setSwjgDm(""); // 税务机关代码
                invoiceSXBTDTO.setBz(""); // 备注
                invoiceSXBTDTO.setDsjehj(""); // 代收金额合计
                invoiceSXBTDTO.setZjehj(formatAmount(vcInvoicePrint.getAmount())); // 总合计金额(合计金额+代收金额合计)
                //String number = String.valueOf(vcInvoicePrintDetList.size());
                //invoiceSXBTDTO.setMxhs(number); // 明细行数
                invoiceSXBTDTO.setMxhs("1"); // 明细行数
                invoiceSXBTDTO.setFplx("1");// 发票类型  1通用机打发票 2建筑业发票 3销售不动产发票 4卷筒票 5国际货运票
                //封装开票信息主体表头模块
                invoiceSXXXDTO.setInvoiceSXBTDTO(invoiceSXBTDTO);
                InvoiceSXMXDTO invoiceSXMXDTO = new InvoiceSXMXDTO();
                invoiceSXMXDTO.setXh("1"); // 序号
                invoiceSXMXDTO.setXmdm("");// 项目代码
                invoiceSXMXDTO.setXmmc("");// 项目名称
                invoiceSXMXDTO.setXmsm("");// 项目说明
                invoiceSXMXDTO.setDw(""); // 单位
                invoiceSXMXDTO.setDj(""); // 单价
                invoiceSXMXDTO.setShul(""); // 数量
                invoiceSXMXDTO.setJe(formatAmount(vcInvoicePrint.getAmount())); // 金额
                invoiceSXMXDTO.setSl(""); // 税率
                invoiceSXMXDTO.setSe(""); // 税额
                invoiceSXMXDTO.setIsds("N"); // 是否代收项目 Y/N
                invoiceSXMXDTOList.add(invoiceSXMXDTO); // xml mx end

                //封装开票信息明细模块
                invoiceSXXXDTO.setInvoiceSXMXDTOList(invoiceSXMXDTOList);
                //封装开票信息
                invoiceSXXXDTOList.add(invoiceSXXXDTO);
            }
            //开票信息文件封装开票信息主体模块
            invoiceSXDTO.setInvoiceSXXXDTOList(invoiceSXXXDTOList);
            // 开票文件start
            // 对象转换成xml
            String kpXml = generateXML(invoiceSXDTO,"GBK");
            //开票信息文件名
            String invoiceSX = nsrbm + "-" + fileSequence + ".xml";
            // xml文件完整路径
            String kpXmlFullPath =filePath + separator + folder + separator + invoiceSX;
            // 生成xml文件开始
            File kpXmlFile = new File(kpXmlFullPath);
            FileOutputStream kpOut = new FileOutputStream(kpXmlFile);
            OutputStreamWriter kpOwriter = new OutputStreamWriter(kpOut,"gbk");
            kpOwriter.write(kpXml);
            kpOwriter.flush();
            kpOwriter.close();
            kpOut.close();
            // 生成xml文件结束
            // 开票文件end
            fileSequence++;
        }

        //生成主文件信息
        invoiceSXMainFileMxDTO.setNsrbm(nsrbm); // 纳税人电子档案号
        invoiceSXMainFileMxDTO.setSsq(ssq); // 所属期
        invoiceSXMainFileMxDTO.setWjsl(String.valueOf(list.size()));// 开票信息文件数量
        invoiceSXMainFileMxDTO.setKpjehj(totalAmount.toString());// 开票金额合计
        invoiceSXMainFileDTO.setInvoiceMainSXDTO(invoiceSXMainFileMxDTO);
        
        // 主文件start
        // 对象转换成xml
        String mainXml = generateXML(invoiceSXMainFileDTO,"GBK");
        //主文件名
        String mainSX = nsrbm + ".xml";
        // xml文件完整路径
        String mainXmlFullPath = filePath + separator + folder + separator + mainSX;
        // 生成xml文件开始
        File mainXmlFile = new File(mainXmlFullPath);
        FileOutputStream mianOut = new FileOutputStream(mainXmlFile);
        OutputStreamWriter mainOwriter = new OutputStreamWriter(mianOut,"gbk");
        mainOwriter.write(mainXml);
        mainOwriter.flush();
        mainOwriter.close();
        mianOut.close();
        // 生成xml文件结束
        // 主文件 end
        //主文件MD5加密
        String MainFileMD5 = getMD5(mainXmlFullPath); 
        //生成MD5文件 start
        String txtFileName = "MD5.txt";
        File txtFile = new File(filePath + separator + folder + separator + txtFileName);
        FileOutputStream out = new FileOutputStream(txtFile);
        StringBuffer sb = new StringBuffer(MainFileMD5);
        out.write(sb.toString().getBytes());
        out.close();
        //生成MD5文件 end
        //生成压缩文件夹
        AntZip antZip = new AntZip();
        antZip.doZip(filePath + separator + folder);
        //antZip.doZip(filePath);
        //String zipFold = filePath + separator + folder;
        //File dirFile = new File(zipFold);
        //File[] files = dirFile.listFiles();
        //antZip.doZip(files, zipFold);
        // 返回的json数据格式：文件路径==文件名
        String fileData = "";
        fileData = filePath + "==" + folder+".zip"; // 开票文件的路径
        return fileData;
    }
	
    /***
     *  贵州发票导出功能
     * @param vcInvoicePrintList
     * @param filePath
     * @param orgCode 
     * @return
     * @throws Exception
     */
	private String generatorFileGuiZhou(List<VcInvoicePrint> vcInvoicePrintList, String filePath, String orgCode) throws BusinessException, IOException, Exception {
		InvoiceGuiZhouKpInfoDTO infoDTO = new InvoiceGuiZhouKpInfoDTO();
		List<InvoiceGuiZhouKpInfoDetDTO> invoiceGuiZhouKpInfoDetDTOList = new ArrayList<InvoiceGuiZhouKpInfoDetDTO>();
		infoDTO.setInvoiceGuiZhouKpInfoDetDTOList(invoiceGuiZhouKpInfoDetDTOList);
		DecimalFormat format = new DecimalFormat("0.00"); // 格式化金额
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 日期格式化
		String separator = java.io.File.separator;
		
		String nsrmc = invoiceExportService.findCodeCnameForGZ("nsrmc", orgCode); // 纳税人名称
		String sbh = invoiceExportService.findCodeCnameForGZ("sbh", orgCode); // 纳税人管理编码
		String yhbm = invoiceExportService.findCodeCnameForGZ("yhbm", orgCode); //银行编码
		String yhzh = invoiceExportService.findCodeCnameForGZ("yhzh", orgCode); //银行账号
		String yhmc = invoiceExportService.findCodeCnameForGZ("yhmc", orgCode); //银行名称
		
		String swdjzzh = invoiceExportService.findCodeCnameForGZ("swdjzzh", orgCode); //税务登记证号
		String gsdjz = invoiceExportService.findCodeCnameForGZ("gsdjz", orgCode); //工商证号
		String fpSsq = getSsq(); // 所属期

		// 开具信息
		for (VcInvoicePrint vcInvoicePrint : vcInvoicePrintList) {
			// 开具扩展信息
			VcInvoicePrintExt vcInvoicePrintExt = vcInvoicePrint.getVcInvoicePrintExtList().get(0);
			InvoiceGuiZhouKpInfoDetDTO infoDetDTO = new InvoiceGuiZhouKpInfoDetDTO();
			infoDetDTO.setSbh(formatNullToEmpty(sbh)); // 纳税人管理编码
			infoDetDTO.setXmmc(""); // 项目名称
			infoDetDTO.setXmbh(""); // 项目编号
			infoDetDTO.setJsxm(formatNullToEmpty(vcInvoicePrint.getSettleItem())); // 结算项目
			infoDetDTO.setKpr("天安保险"); // 开票人 必填
			infoDetDTO.setFsrq(dateFormat.format(vcInvoicePrint.getPrintDate())); // 开票日期 必填
			infoDetDTO.setFkf("");// 付款方税务登记证号/身份证号/组织机构代码
			infoDetDTO.setFkfmc(vcInvoicePrint.getPayerName()); // 付款方名称 必填
			infoDetDTO.setKpje(format.format(vcInvoicePrint.getAmount()));// 开票金额 必填
			infoDetDTO.setFphm(vcInvoicePrint.getInvoiceNo());// 发票号码 必填
			infoDetDTO.setZbrbz("");// 总包人标志 
			infoDetDTO.setFbrbz("");// 分包人标志
			infoDetDTO.setJslx(""); // 结算类型
			infoDetDTO.setBz("");// 备注
			infoDetDTO.setFpdmqc(vcInvoicePrint.getInvoiceCode());// 发票代码 必填
			infoDetDTO.setQtkxxz("");// 房动产适用
			//发票开具类型：1-正常;2-负数;3-作废
			String printKind = vcInvoicePrint.getPrintKind();
			if("1".equals(printKind) || "2".equals(printKind)){
				infoDetDTO.setZfbz("0");// 作废标志 0为正常发票，1为作废发票
			}else if("3".equals(printKind) ){
				infoDetDTO.setZfbz("1");// 作废标志 0为正常发票，1为作废发票
			}
			infoDetDTO.setSwdjzzh(formatNullToEmpty(swdjzzh));// 税务登记证号
			infoDetDTO.setNsrmc(formatNullToEmpty(nsrmc));// 纳税人名称 必填
			infoDetDTO.setFplb("5");// 发票类别
			// 1，通用机开票，2，建筑业发票，3，不动产发票，4保险业发票，5，保险中介发票，6，国际货运发票
			infoDetDTO.setHbrq("");// 航班日期
			infoDetDTO.setQyg("");// 起运港
			infoDetDTO.setMdg("");// 目的港
			infoDetDTO.setXhg("");// 卸货港
			infoDetDTO.setTydh("");// 托运单号
			infoDetDTO.setFhr("");// 复核人
			infoDetDTO.setLxdh("");// 联系电话
			infoDetDTO.setDz("");// 地址
			infoDetDTO.setBxd(formatNullToEmpty(vcInvoicePrint.getPolicyNo()));// 保险单
			infoDetDTO.setBdh(formatNullToEmpty(vcInvoicePrint.getEndorseNo()));// 批单号
			infoDetDTO.setYhzh(formatNullToEmpty(yhzh));// 银行帐号 必填
			infoDetDTO.setYhmc(formatNullToEmpty(yhmc));// 银行名称 必填
			infoDetDTO.setGsdjz(formatNullToEmpty(gsdjz));// 工商登记证号 必填
			infoDetDTO.setZghy("保险业");// 征管行业 必填
			infoDetDTO.setYhbm(formatNullToEmpty(yhbm));// 银行编码 必填
			infoDetDTO.setCbxz(formatNullToEmpty(vcInvoicePrintExt.getRiskName())); // 承保险种
			infoDetDTO.setKxxz(""); // 款项性质(使用性质)
			infoDetDTO.setMj("");// 数量
			infoDetDTO.setDj("");// 单价
			infoDetDTO.setKpd("");//开票点
			infoDetDTO.setCcs(formatAmount(vcInvoicePrintExt.getTaxAmount()));// 车船税
			infoDetDTO.setCcsznj(formatAmount(vcInvoicePrintExt.getLateFee()));// 车船税滞纳金
			infoDetDTO.setBxf(formatAmount(vcInvoicePrintExt.getPremium()));// 保险费
			invoiceGuiZhouKpInfoDetDTOList.add(infoDetDTO);
		}

		// 生成xml start
		String mainXml = generateXML(infoDTO, "UTF-8");
		String fileName = nsrmc + "_" + sbh+ "_" + fpSsq + "_"+ "发票开具数据"+ ".xml";
		String fullFilePath = filePath + separator + fileName;
		File file = new File(fullFilePath);
		FileOutputStream out = new FileOutputStream(file);
		OutputStreamWriter writer = new OutputStreamWriter(out);
		writer.write(mainXml);
		writer.flush();
		writer.close();
		out.close();
		// 生成xml end

		// 返回数据
		String fileDate = "";
		fileDate = filePath + "==" + fileName;
		return fileDate;
	}
	
	
	/**
	 * 天津发票导出
	 * @param vcInvoicePrintTmpList
	 * @param filePath
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	private String generateXmlFileTianJin(List<VcInvoicePrint> vcInvoicePrintTmpList,
			String filePath) throws BusinessException {
		
		if (vcInvoicePrintTmpList.size() > 50000){
			throw new BusinessException("导出结果超过50000条！");
		}
		String fileData = "";
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat sf2 = new DecimalFormat("000");
		DecimalFormat sf3 = new DecimalFormat("0.00"); // 格式化金额
		String separator = java.io.File.separator;
		
		String SBRQ = sf1.format(new Date());
        BigDecimal totalAmount = getTotalAmount(vcInvoicePrintTmpList);//开票金额合计
		String FileListName = "11410342_FileList.xml";//FileList文件名
		String invoiceInfoName = "11410342_14_InvoiceInfo_";//invoiceInfo文件名
		String xmlFileName = "";
		String zipFileName = filePath + separator + "U_11410342_0001_InvoiceInfo.zip";
        int fileSequence = 1;// 开票信息序号
        
		try {
			// 若导出记录超过2000，拆分list
			List<List<VcInvoicePrint>> list = splitVcInvoicePrintList(vcInvoicePrintTmpList, 2000);
			// 创建FileList文件document
			Document documentFileList = DocumentHelper.createDocument();
			// 创建FileList根节点
			Element WJXX = documentFileList.addElement("WJXX");
			Element WJQD = WJXX.addElement("WJQD");
			//头部信息
			Element FileListBTXX = WJQD.addElement("BTXX");
			FileListBTXX.addAttribute("QYBM", "11410342");// 企业编码
			FileListBTXX.addAttribute("QYMC", "天安财产保险股份有限公司天津分公司");// 企业名称
			FileListBTXX.addAttribute("SWDJZH", "12011474138876X");// 税务登记证号码
			FileListBTXX.addAttribute("SBRQ", SBRQ);// 生成时间
			FileListBTXX.addAttribute("WJLX", "14");// 文件类型 14 -发票五万条记录导入接口
			FileListBTXX.addAttribute("FPHJJE", sf3.format(totalAmount) + "");// 发票合计金额
			FileListBTXX.addAttribute("FPHJFS", vcInvoicePrintTmpList.size() + "");// 发票合计份数
			FileListBTXX.addAttribute("SJLY", "2");// 数据来源 2 -非控软件发票数据上传
			Element FileInfo = WJQD.addElement("FileInfo");//文件清单信息
			Element FileListRow;
			
			for (List<VcInvoicePrint> vcInvoicePrintList : list) {
				
				FileListRow = FileInfo.addElement("Row");
				FileListRow.addAttribute("WJLX","14");
				FileListRow.addAttribute("WJMC",invoiceInfoName + sf2.format(fileSequence) + ".xml");
				
				// 创建InvoiceInfo文件document
				Document documentInvoiceInfo = DocumentHelper.createDocument();
				// 创建InvoiceInfo根节点
				Element FPXX = documentInvoiceInfo.addElement("FPXX");
				// InvoiceInfo头部信息
				Element InvoiceInfoBTXX = FPXX.addElement("BTXX");
				InvoiceInfoBTXX.addAttribute("QYBM", "11410342");// 企业编码
				InvoiceInfoBTXX.addAttribute("QYMC", "天安财产保险股份有限公司天津分公司");// 企业名称
				InvoiceInfoBTXX.addAttribute("SWDJZH", "12011474138876X");// 税务登记证号码
				InvoiceInfoBTXX.addAttribute("SBRQ", SBRQ);// 生成时间
				InvoiceInfoBTXX.addAttribute("WJLX", "14");// 文件类型 14 -发票五万条记录导入接口
				InvoiceInfoBTXX.addAttribute("FPHJJE", sf3.format(totalAmount) + "");// 发票合计金额
				InvoiceInfoBTXX.addAttribute("FPHJFS", vcInvoicePrintTmpList.size() + "");// 发票合计份数
				InvoiceInfoBTXX.addAttribute("SJLY", "2");// 数据来源 2 -非控软件发票数据上传
				Element InvoiceInfoRow;
				for (VcInvoicePrint vcInvoicePrint : vcInvoicePrintList) {
					// 循环插入Row
					InvoiceInfoRow = FPXX.addElement("ROW");
					InvoiceInfoRow.addAttribute("FMC", vcInvoicePrint.getPayerName());// 付款方名称
					InvoiceInfoRow.addAttribute("FSH", "");// 付款方纳税人识别号
					InvoiceInfoRow.addAttribute("XMZY", "保费合计");// 项目摘要
					InvoiceInfoRow.addAttribute("JE", sf3.format(vcInvoicePrint.getAmount()) + "");// 合计金额人民币小写
					InvoiceInfoRow.addAttribute("RQ", sf1.format(vcInvoicePrint.getPrintDate()));// 开票日期
					InvoiceInfoRow.addAttribute("DM", vcInvoicePrint.getInvoiceCode());// 发票代码
					InvoiceInfoRow.addAttribute("HM", vcInvoicePrint.getInvoiceNo());// 发票号码
					InvoiceInfoRow.addAttribute("SMC", vcInvoicePrint.getTaxpayerName());// 纳税人名称
					InvoiceInfoRow.addAttribute("SSH", vcInvoicePrint.getTaxpayerCode());// 纳税人识别号
					String printKind = vcInvoicePrint.getPrintKind();//发票开具类型:1-正常;2-负数;3-作废
					if ("1".equals(printKind) || "2".equals(printKind)){
						InvoiceInfoRow.addAttribute("FPZT", "0");// 发票状态(0:正常填开 1：冲销 2：作废  )
					}else if("3".equals(printKind) || "4".equals(printKind)){
						InvoiceInfoRow.addAttribute("FPZT", "2");// 发票状态(0:正常填开 1：冲销 2：作废  )
					}
					InvoiceInfoRow.addAttribute("CXDM", "");// 冲销发票代码
					InvoiceInfoRow.addAttribute("CXHM", "");// 冲销发票号码
				}
				// 循环list，生成InvoiceInfo文件
				//生成文件命名
				xmlFileName = filePath + separator + invoiceInfoName + sf2.format(fileSequence) + ".xml" ;
				fileSequence++;//文件序列号加1
				File xmlFile1 = new File(xmlFileName);
				//生成xml文件开始
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setEncoding("GBK");//设置编码
				XMLWriter writer = new XMLWriter(new FileOutputStream(xmlFile1), format);
				writer.write(documentInvoiceInfo);
				writer.close();
				//生成xml文件结束
			}
			// 生成FileList文件
			//生成文件命名
			xmlFileName = filePath + separator + FileListName;
			File xmlFile = new File(xmlFileName);
			//生成xml文件开始
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("GBK");//设置编码
			XMLWriter writer = new XMLWriter(new FileOutputStream(xmlFile), format);
			writer.write(documentFileList);
			writer.close();
			//生成xml文件结束
			File directory = new File(filePath);
			File[] files = directory.listFiles(new FileFilter() {
				public boolean accept(File pathname) {
					return pathname.getName().endsWith(".xml");
				}
			});
			//File[] files = directory.listFiles( );
			AntZip antZip = new AntZip();
			antZip.doZip(files, zipFileName);
		}catch(Exception e){
			throw new BusinessException("导出文件失败！", e);
		}
		File zipFile = new File(zipFileName);
		fileData = filePath + "==" + zipFile.getName();
		return fileData;
	}
	
	
	
	/**
	 * 上海发票导出
	 * @param vcInvoicePrintTmpList
	 * @param uploadPlatList
	 * @param filePath
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	private String generateXmlFileSH(List<VcInvoicePrint> vcInvoicePrintTmpList, List<VcInvoiceRevoke> uploadPlatList,
			String filePath, String userCode) throws BusinessException {
		//返回的json数据格式：文件路径==文件名
		String fileData = "";
		try{
			String separator = java.io.File.separator;
			SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFmt = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			DecimalFormat df = new DecimalFormat("0.00");
			Date sysdate = new Date();
	
			int size = vcInvoicePrintTmpList.size();
			
			//查询条件输入的起止时间
			Date startDate = invoiceExportVo.getPrintDateStart();
			Date endDate = invoiceExportVo.getPrintDateEnd();
			
			//查询遗失发票和缴销发票
			String isUploadPlat = "";
			if(StringUtils.isNotEmpty(invoiceExportVo.getInvoiceStatus())){
				isUploadPlat = invoiceExportVo.getInvoiceStatus();
			}
			List<VcInvoiceRevoke> vcInvoiceRevokeList = 
				invoiceExportService.findVcInvoiceRevoke(SysConst.COMCODE_SHANGHAI, startDate, endDate, isUploadPlat);
			
			//已导出的缴销信息，需更新上传平台标志位
			/*List<VcInvoiceRevoke> uploadPlatList = new ArrayList<VcInvoiceRevoke>();*/
			
			//创建document
			Document document = DocumentHelper.createDocument();
			//设置字符集编码
			document.setXMLEncoding("gb2312");
			//创建根节点
			Element root = document.addElement("TYFP");
			root.addAttribute("Ver", "1");
			
			//Head节点
			Element head = root.addElement("Head");
			head.addElement("NSRSBH").setText("310115132234116");//纳税人识别号
			head.addElement("NSRMC").setText("天安财产保险股份有限公司");//纳税人名称
			head.addElement("ZYRJZCH").setText("3101151308033202");//自有软件注册号
			head.addElement("SCSJ").setText(timeFmt.format(sysdate));//生成时间
			head.addElement("SCFS").setText("2");//上传方式（1：实时上传2：非实时上传）
			
			//发票使用情况
			Element  fpsy = root.addElement("FPSY");
			Element kssj = fpsy.addElement("KSSJ");//开始时间
			Element jssj = fpsy.addElement("JSSJ");//结束时间
			Element jls = fpsy.addElement("JLS");//记录数
			Element zpfs = fpsy.addElement("ZPFS");//正票份数
			Element zpje = fpsy.addElement("ZPJE");//正票金额
			Element fpfs = fpsy.addElement("FPFS");//废票份数
			Element tpfs = fpsy.addElement("TPFS");//退票份数
			Element tpje = fpsy.addElement("TPJE");//退票金额
			Element ysfs = fpsy.addElement("YSFS");//遗失份数
			Element jxfs = fpsy.addElement("JXFS");//缴销份数
			
			//开票节点
			Element kp = root.addElement("KP");
			kp.addAttribute("RecNum", size+"");//记录数
			
			Element kpzl = kp.addElement("KPZL");//开票种类
			kpzl.addAttribute("KPZLDM", "22744");//发票种类代码：22744-地税通用机打发票(四联)
			kpzl.addAttribute("DKBZ", "2");//代开标志：1-代开；其他-自开
			kpzl.addAttribute("RecNum", size+"");//记录数
			
			int count1 = 0;//正票份数
			BigDecimal amount1 = new BigDecimal("0.00");//正票金额
			int count2 = 0;//废票份数
			int count3 = 0;//退票份数
			BigDecimal amount3 = new BigDecimal("0.00");//退票金额
			//循环组织开票记录节点
			for(VcInvoicePrint vcInvoicePrint : vcInvoicePrintTmpList){
				//发票开具类型：1-正常;2-负数;3-作废
				String printKind = vcInvoicePrint.getPrintKind();
				//add by chy 20131210 暂定金额为负数时，开票类型为2 begin
				if(vcInvoicePrint.getAmount()!=null && vcInvoicePrint.getAmount().compareTo(BigDecimal.ZERO)<0){
					printKind = "2";
				}
				//add by chy 20131210 暂定金额为负数时，开票类型为2 end
				//开票记录
				Element kpjl = kpzl.addElement("KPJL");
				kpjl.addElement("FPDM").setText(StringUtil.formatNumberLength(vcInvoicePrint.getInvoiceCode(), 12));//发票代码
				kpjl.addElement("FPHM").setText(StringUtil.formatNumberLength(vcInvoicePrint.getInvoiceNo(), 8));//发票号码
				if("1".equals(printKind)){
					kpjl.addElement("KPLX").setText("0");//开票类型( 0-正票  1-废票  2-退票(红字))
					count1++;//正票份数+1
					amount1 = amount1.add(vcInvoicePrint.getAmount());//正票金额累加
				}else if("3".equals(printKind)){
					kpjl.addElement("KPLX").setText("1");//开票类型( 0-正票  1-废票  2-退票(红字))
					count2++;//废票份数+1
				}else if("2".equals(printKind)){
					kpjl.addElement("KPLX").setText("2");//开票类型( 0-正票  1-废票  2-退票(红字))
					count3++;//退票份数+1
					amount3 = amount3.add(vcInvoicePrint.getAmount());//退票金额累加
				}
				kpjl.addElement("KPRQ").setText(timeFmt.format(vcInvoicePrint.getPrintDate()));//开票日期
				kpjl.addElement("HYFLDM").setText("22");//行业分类代码：22-金融保险业
				kpjl.addElement("JE").setText(df.format(vcInvoicePrint.getAmount()));//金额
//				kpjl.addElement("SKRMC").setText(vcInvoicePrint.getRecipientName());//收款人名称
//				kpjl.addElement("SKRSH").setText(vcInvoicePrint.getRecipientCode());//收款人税号
				kpjl.addElement("SKRMC").setText("天安财产保险股份有限公司");//收款人名称
				kpjl.addElement("SKRSH").setText("310115132234116");//收款人税号
				kpjl.addElement("FKRMC").setText(vcInvoicePrint.getPayerName());//付款人名称
				/*if(StringUtils.isNotEmpty(vcInvoicePrint.getPayerCode())){
					kpjl.addElement("FKRSH").setText(vcInvoicePrint.getPayerCode());//付款人 税号
				}*/
				//kpjl.addElement("GHFQYLX").setText("");//购货方企业类型（01-企业  02-机关事业单位  03-个人 04 其他）
				//kpjl.addElement("KPFDZJDH").setText("");//开票方地址及电话
				//kpjl.addElement("KPFYHJZH").setText("");//开票方银行及账号
				//kpjl.addElement("SPFDZJDH").setText("");//受票方地址及电话
				//kpjl.addElement("SPFYHJZH").setText("");//受票方银行及账号
				//kpjl.addElement("SE").setText("");//税额
				//kpjl.addElement("SL").setText("");//税率
				//if(StringUtils.isNotEmpty(vcInvoicePrint.getRemark())){
				//	kpjl.addElement("BZ1").setText(vcInvoicePrint.getRemark());//备注1
				//}
				//kpjl.addElement("BZ2").setText(vcInvoicePrint.getRemark());//备注2
				//kpjl.addElement("BZ3").setText(vcInvoicePrint.getRemark());//备注3
				//kpjl.addElement("BZ4").setText(vcInvoicePrint.getRemark());//备注4
				//kpjl.addElement("BZ5").setText(vcInvoicePrint.getRemark());//备注5
				kpjl.addElement("KPRXM").setText(vcInvoicePrint.getBillerName());//开票人姓名
				//kpjl.addElement("FHRXM").setText("");//复核人姓名
				//红冲票
				if("2".equals(printKind)){
					//有原票数据
					if(StringUtils.isNotEmpty(vcInvoicePrint.getCounteractedInvoiceCode())
							&& StringUtils.isNotEmpty(vcInvoicePrint.getCounteractedInvoiceNo())){
						kpjl.addElement("YFPDM").setText(StringUtil.formatNumberLength(vcInvoicePrint.getCounteractedInvoiceCode(),12));//原发票代码
						kpjl.addElement("YFPHM").setText(StringUtil.formatNumberLength(vcInvoicePrint.getCounteractedInvoiceNo(),8));//原发票号码
						kpjl.addElement("TPLX").setText("1");//退票类型(1-有原票数据  2-无原票数据)
					}else{
						//无原票数据
						kpjl.addElement("YFPDM").setText("");//原发票代码
						kpjl.addElement("YFPHM").setText("");//原发票号码
						kpjl.addElement("TPLX").setText("2");//退票类型(1-有原票数据  2-无原票数据)
					}
				}else{
					kpjl.addElement("YFPDM").setText("");//原发票代码
					kpjl.addElement("YFPHM").setText("");//原发票号码
					kpjl.addElement("TPLX").setText("1");//退票类型(1-有原票数据  2-无原票数据)
				}
				
				//作废票
				if("3".equals(printKind)){
					kpjl.addElement("ZFRQ").setText(sdf.format(vcInvoicePrint.getCanceldDate()));//作废日期
					kpjl.addElement("ZFRXM").setText(vcInvoicePrint.getCanceledOpr());//作废人
				}else{
					kpjl.addElement("ZFRQ").setText("");//作废日期
					kpjl.addElement("ZFRXM").setText("");//作废人
				}
				
				//组织明细信息
				/*List<VcInvoicePrintDet> detList = vcInvoicePrint.getVcInvoicePrintDetList();
				if(detList!=null && detList.size()>0){
					//发票明细
					Element fpmx = kpjl.addElement("FPMX");
					//明细合计金额
					BigDecimal total = new BigDecimal("0.00");
					for(VcInvoicePrintDet vcInvoicePrintDet : detList){
						Element row = fpmx.addElement("Row");
						row.addAttribute("XH", vcInvoicePrintDet.getSerialNo()+"");//序号
						if(StringUtils.isNotEmpty(vcInvoicePrintDet.getItemName())){
							row.addAttribute("HPMC", vcInvoicePrintDet.getItemName());//项目名称
						}else{
							row.addAttribute("HPMC", "保险费");//项目名称
						}
						row.addAttribute("JLDW", "");//计量单位（非必录，节点不能为空）
						row.addAttribute("SL", "");//数量（非必录，节点不能为空）
						row.addAttribute("DJ", "");//单价（非必录，节点不能为空）
						row.addAttribute("JE", df.format(vcInvoicePrintDet.getAmount()));//金额
						total = total.add(vcInvoicePrintDet.getAmount());
					}
					fpmx.addAttribute("RowNum", detList.size()+"");//行数
					fpmx.addAttribute("HJJE", df.format(total));//合计金额
				}*/
				//发票明细
				Element fpmx = kpjl.addElement("FPMX");
				fpmx.addAttribute("RowNum", "1");//行数
				fpmx.addAttribute("HJJE", "");//合计金额
				Element row = fpmx.addElement("Row");
				row.addAttribute("XH", "1");//序号
				row.addAttribute("HPMC", "");//项目名称
				row.addAttribute("JLDW", "");//计量单位（非必录，节点不能为空）
				row.addAttribute("SL", "");//数量（非必录，节点不能为空）
				row.addAttribute("DJ", "");//单价（非必录，节点不能为空）
				row.addAttribute("JE", "");//金额
				row.setText("");
			}
			
			int lostCount = 0;//遗失份数
			int revokeCount = 0;//缴销份数
			int lostRowNum = 0;//遗失行数
			int revokeRowNum = 0;//缴销行数
			if(vcInvoiceRevokeList!=null && vcInvoiceRevokeList.size()!=0){
				//遗失发票
				Element ysfp = root.addElement("YSFP");
				//缴销发票
				Element jxfp = root.addElement("JXFP");
				//遍历发票缴销信息
				for(VcInvoiceRevoke vcInvoiceRevoke : vcInvoiceRevokeList){
					//50-空白缴销；70-遗失
					if("70".equals(vcInvoiceRevoke.getRevokeType())){
						//遗失发票
						Element row = ysfp.addElement("Row");
						row.addElement("FPDM").setText(StringUtil.formatNumberLength(vcInvoiceRevoke.getInvoiceCode(),12));//发票代码
						row.addElement("FPHS").setText(StringUtil.formatNumberLength(vcInvoiceRevoke.getStartNum(),8));//发票号码始
						row.addElement("FPHZ").setText(StringUtil.formatNumberLength(vcInvoiceRevoke.getEndNum(),8));//发票号码止
						row.addElement("YSRQ").setText(sdf.format(vcInvoiceRevoke.getLostDate()));//遗失日期
						row.addElement("DJRQ").setText(sdf.format(vcInvoiceRevoke.getRegisterDate()));//登记日期
						lostCount += vcInvoiceRevoke.getQuantity();
						lostRowNum++;
						uploadPlatList.add(vcInvoiceRevoke);
					}else if("50".equals(vcInvoiceRevoke.getRevokeType())){
						//缴销发票
						Element row = jxfp.addElement("Row");
						row.addElement("FPDM").setText(StringUtil.formatNumberLength(vcInvoiceRevoke.getInvoiceCode(),12));//发票代码
						row.addElement("FPHS").setText(StringUtil.formatNumberLength(vcInvoiceRevoke.getStartNum(),8));//发票号码始
						row.addElement("FPHZ").setText(StringUtil.formatNumberLength(vcInvoiceRevoke.getEndNum(),8));//发票号码止
						row.addElement("DJRQ").setText(sdf.format(vcInvoiceRevoke.getRegisterDate()));//登记日期
						revokeCount += vcInvoiceRevoke.getQuantity();
						revokeRowNum++;
						uploadPlatList.add(vcInvoiceRevoke);
					}
				}
				//更新缴销信息上传平台标志为已上传
				/*invoiceExportService.updateVcInvoiceRevokeList(uploadPlatList,userCode);*/
				//遗失发票行数
				ysfp.addAttribute("RowNum", lostRowNum+"");
				//缴销发票行数
				jxfp.addAttribute("RowNum", revokeRowNum+"");
			}
			
			Calendar startC = Calendar.getInstance();
			startC.setTime(startDate);
			startC.set(Calendar.HOUR, 0);
			startC.set(Calendar.MINUTE, 0);
			startC.set(Calendar.SECOND, 0);
			
			Calendar endC = Calendar.getInstance();
			endC.setTime(endDate);
			endC.set(Calendar.HOUR, 23);
			endC.set(Calendar.MINUTE, 59);
			endC.set(Calendar.SECOND, 59);
			//发票使用：元素赋值
			kssj.setText(timeFmt.format(startC.getTime()));//开始时间
			jssj.setText(timeFmt.format(endC.getTime()));//结束时间
			jls.setText(size+"");//记录数
			zpfs.setText(count1+"");//正票份数
			zpje.setText(amount1.toString());//正票金额
			fpfs.setText(count2+"");//废票份数
			tpfs.setText(count3+"");//退票份数
			tpje.setText(Math.abs(amount3.doubleValue())+"");//退票金额
			ysfs.setText(lostCount+"");//遗失份数
			jxfs.setText(revokeCount+"");//缴销份数
			
			//文件前缀
			/*String fileName = "上海发票导出_"
					+ sdf.format(invoiceExportVo.getPrintDateStart()) + "_"
					+ sdf.format(invoiceExportVo.getPrintDateEnd())+".xml";*/
			String xmlFileName = "TYFP_310115132234116_"+sdf.format(new Date())+".xml";
			//String zipFileName = "TYFP_310115132234116_"+sdf.format(new Date())+".zip";
			String xmlFullName = filePath+separator+xmlFileName;
			//String zipFullName = filePath+separator+zipFileName;
			File xmlFile = new File(xmlFullName);
			//生成xml文件开始
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setLineSeparator("\r\n");
			format.setEncoding("gb2312");//设置编码
			XMLWriter writer = new XMLWriter(new FileOutputStream(xmlFile), format);
			writer.write(document);
			writer.close();
			//生成xml文件结束
			//生成zip文件
			//AntZip antZip = new AntZip();
			//antZip.doZip(new File[]{xmlFile}, zipFullName);
			//logger.info("生成上海发票数据文件路径：========"+zipFullName+"===========");
			logger.info("生成上海发票数据文件路径：========"+xmlFullName+"===========");
			//删除xml文件
			//xmlFile.delete();
			
			fileData = filePath + "==" + xmlFileName;
		}catch(Exception e){
			throw new BusinessException("生成xml文件失败！", e);
		}
		return fileData;
	}
	
	/**
	 * 航保出单中心发票导出
	 * @param vcInvoicePrintTmpList
	 * @param uploadPlatList
	 * @param filePath
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	private String generateXmlFileHangBao(List<VcInvoicePrint> vcInvoicePrintTmpList, List<VcInvoiceRevoke> uploadPlatList,
			String filePath, String userCode) throws BusinessException {
		//返回的json数据格式：文件路径==文件名
		String fileData = "";
		try{
			String separator = java.io.File.separator;
			SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFmt = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			DecimalFormat df = new DecimalFormat("0.00");
			Date sysdate = new Date();
	
			int size = vcInvoicePrintTmpList.size();
			
			//查询条件输入的起止时间
			Date startDate = invoiceExportVo.getPrintDateStart();
			Date endDate = invoiceExportVo.getPrintDateEnd();
			//查询遗失发票和缴销发票
			String isUploadPlat = "";
			if(StringUtils.isNotEmpty(invoiceExportVo.getInvoiceStatus())){
				isUploadPlat = invoiceExportVo.getInvoiceStatus();
			}
			List<VcInvoiceRevoke> vcInvoiceRevokeList = 
				invoiceExportService.findVcInvoiceRevoke(SysConst.COMCODE_HANGBAO, startDate, endDate, isUploadPlat);
			
			//已导出的缴销信息，需更新上传平台标志位
			/*List<VcInvoiceRevoke> uploadPlatList = new ArrayList<VcInvoiceRevoke>();*/
			
			//创建document
			Document document = DocumentHelper.createDocument();
			//设置字符集编码
			document.setXMLEncoding("gb2312");
			//创建根节点
			Element root = document.addElement("TYFP");
			root.addAttribute("Ver", "1");
			
			//Head节点
			Element head = root.addElement("Head");
			head.addElement("NSRSBH").setText("310115312396008");//纳税人识别号
			head.addElement("NSRMC").setText("天安财产保险股份有限公司航运保险中心");//纳税人名称
			head.addElement("ZYRJZCH").setText("310115301759280");//自有软件注册号
			head.addElement("SCSJ").setText(timeFmt.format(sysdate));//生成时间
			head.addElement("SCFS").setText("2");//上传方式（1：实时上传2：非实时上传）
			
			//发票使用情况
			Element  fpsy = root.addElement("FPSY");
			Element kssj = fpsy.addElement("KSSJ");//开始时间
			Element jssj = fpsy.addElement("JSSJ");//结束时间
			Element jls = fpsy.addElement("JLS");//记录数
			Element zpfs = fpsy.addElement("ZPFS");//正票份数
			Element zpje = fpsy.addElement("ZPJE");//正票金额
			Element fpfs = fpsy.addElement("FPFS");//废票份数
			Element tpfs = fpsy.addElement("TPFS");//退票份数
			Element tpje = fpsy.addElement("TPJE");//退票金额
			Element ysfs = fpsy.addElement("YSFS");//遗失份数
			Element jxfs = fpsy.addElement("JXFS");//缴销份数
			
			//开票节点
			Element kp = root.addElement("KP");
			kp.addAttribute("RecNum", size+"");//记录数
			
			Element kpzl = kp.addElement("KPZL");//开票种类
			kpzl.addAttribute("KPZLDM", "22744");//发票种类代码：22744-地税通用机打发票(四联)
			kpzl.addAttribute("DKBZ", "2");//代开标志：1-代开；其他-自开
			kpzl.addAttribute("RecNum", size+"");//记录数
			
			int count1 = 0;//正票份数
			BigDecimal amount1 = new BigDecimal("0.00");//正票金额
			int count2 = 0;//废票份数
			int count3 = 0;//退票份数
			BigDecimal amount3 = new BigDecimal("0.00");//退票金额
			//循环组织开票记录节点
			for(VcInvoicePrint vcInvoicePrint : vcInvoicePrintTmpList){
				//发票开具类型：1-正常;2-负数;3-作废
				String printKind = vcInvoicePrint.getPrintKind();
				//金额为负数时，开票类型为2
				if(vcInvoicePrint.getAmount()!=null && vcInvoicePrint.getAmount().compareTo(BigDecimal.ZERO)<0){
					printKind = "2";
				}
				//开票记录
				Element kpjl = kpzl.addElement("KPJL");
				kpjl.addElement("FPDM").setText(StringUtil.formatNumberLength(vcInvoicePrint.getInvoiceCode(), 12));//发票代码
				kpjl.addElement("FPHM").setText(StringUtil.formatNumberLength(vcInvoicePrint.getInvoiceNo(), 8));//发票号码
				if("1".equals(printKind)){
					kpjl.addElement("KPLX").setText("0");//开票类型( 0-正票  1-废票  2-退票(红字))
					count1++;//正票份数+1
					amount1 = amount1.add(vcInvoicePrint.getAmount());//正票金额累加
				}else if("3".equals(printKind)){
					kpjl.addElement("KPLX").setText("1");//开票类型( 0-正票  1-废票  2-退票(红字))
					count2++;//废票份数+1
				}else if("2".equals(printKind)){
					kpjl.addElement("KPLX").setText("2");//开票类型( 0-正票  1-废票  2-退票(红字))
					count3++;//退票份数+1
					amount3 = amount3.add(vcInvoicePrint.getAmount());//退票金额累加
				}
				kpjl.addElement("KPRQ").setText(timeFmt.format(vcInvoicePrint.getPrintDate()));//开票日期
				kpjl.addElement("HYFLDM").setText("22");//行业分类代码：22-金融保险业
				kpjl.addElement("JE").setText(df.format(vcInvoicePrint.getAmount()));//金额
				kpjl.addElement("SKRMC").setText("天安财产保险股份有限公司");//收款人名称
				kpjl.addElement("SKRSH").setText("310115312396008");//收款人税号
				kpjl.addElement("FKRMC").setText(vcInvoicePrint.getPayerName());//付款人名称
				kpjl.addElement("KPRXM").setText(vcInvoicePrint.getBillerName());//开票人姓名
				//红冲票
				if("2".equals(printKind)){
					//有原票数据
					if(StringUtils.isNotEmpty(vcInvoicePrint.getCounteractedInvoiceCode())
							&& StringUtils.isNotEmpty(vcInvoicePrint.getCounteractedInvoiceNo())){
						kpjl.addElement("YFPDM").setText(StringUtil.formatNumberLength(vcInvoicePrint.getCounteractedInvoiceCode(),12));//原发票代码
						kpjl.addElement("YFPHM").setText(StringUtil.formatNumberLength(vcInvoicePrint.getCounteractedInvoiceNo(),8));//原发票号码
						kpjl.addElement("TPLX").setText("1");//退票类型(1-有原票数据  2-无原票数据)
					}else{
						//无原票数据
						kpjl.addElement("YFPDM").setText("");//原发票代码
						kpjl.addElement("YFPHM").setText("");//原发票号码
						kpjl.addElement("TPLX").setText("2");//退票类型(1-有原票数据  2-无原票数据)
					}
				}else{
					kpjl.addElement("YFPDM").setText("");//原发票代码
					kpjl.addElement("YFPHM").setText("");//原发票号码
					kpjl.addElement("TPLX").setText("1");//退票类型(1-有原票数据  2-无原票数据)
				}
				
				//作废票
				if("3".equals(printKind)){
					kpjl.addElement("ZFRQ").setText(sdf.format(vcInvoicePrint.getCanceldDate()));//作废日期
					kpjl.addElement("ZFRXM").setText(vcInvoicePrint.getCanceledOpr());//作废人
				}else{
					kpjl.addElement("ZFRQ").setText("");//作废日期
					kpjl.addElement("ZFRXM").setText("");//作废人
				}
				
				//发票明细
				Element fpmx = kpjl.addElement("FPMX");
				fpmx.addAttribute("RowNum", "1");//行数
				fpmx.addAttribute("HJJE", "");//合计金额
				Element row = fpmx.addElement("Row");
				row.addAttribute("XH", "1");//序号
				row.addAttribute("HPMC", "");//项目名称
				row.addAttribute("JLDW", "");//计量单位（非必录，节点不能为空）
				row.addAttribute("SL", "");//数量（非必录，节点不能为空）
				row.addAttribute("DJ", "");//单价（非必录，节点不能为空）
				row.addAttribute("JE", "");//金额
				row.setText("");
			}
			
			int lostCount = 0;//遗失份数
			int revokeCount = 0;//缴销份数
			int lostRowNum = 0;//遗失行数
			int revokeRowNum = 0;//缴销行数
			if(vcInvoiceRevokeList!=null && vcInvoiceRevokeList.size()!=0){
				//遗失发票
				Element ysfp = root.addElement("YSFP");
				//缴销发票
				Element jxfp = root.addElement("JXFP");
				//遍历发票缴销信息
				for(VcInvoiceRevoke vcInvoiceRevoke : vcInvoiceRevokeList){
					//50-空白缴销；70-遗失
					if("70".equals(vcInvoiceRevoke.getRevokeType())){
						//遗失发票
						Element row = ysfp.addElement("Row");
						row.addElement("FPDM").setText(StringUtil.formatNumberLength(vcInvoiceRevoke.getInvoiceCode(),12));//发票代码
						row.addElement("FPHS").setText(StringUtil.formatNumberLength(vcInvoiceRevoke.getStartNum(),8));//发票号码始
						row.addElement("FPHZ").setText(StringUtil.formatNumberLength(vcInvoiceRevoke.getEndNum(),8));//发票号码止
						row.addElement("YSRQ").setText(sdf.format(vcInvoiceRevoke.getLostDate()));//遗失日期
						row.addElement("DJRQ").setText(sdf.format(vcInvoiceRevoke.getRegisterDate()));//登记日期
						lostCount += vcInvoiceRevoke.getQuantity();
						lostRowNum++;
						uploadPlatList.add(vcInvoiceRevoke);
					}else if("50".equals(vcInvoiceRevoke.getRevokeType())){
						//缴销发票
						Element row = jxfp.addElement("Row");
						row.addElement("FPDM").setText(StringUtil.formatNumberLength(vcInvoiceRevoke.getInvoiceCode(),12));//发票代码
						row.addElement("FPHS").setText(StringUtil.formatNumberLength(vcInvoiceRevoke.getStartNum(),8));//发票号码始
						row.addElement("FPHZ").setText(StringUtil.formatNumberLength(vcInvoiceRevoke.getEndNum(),8));//发票号码止
						row.addElement("DJRQ").setText(sdf.format(vcInvoiceRevoke.getRegisterDate()));//登记日期
						revokeCount += vcInvoiceRevoke.getQuantity();
						revokeRowNum++;
						uploadPlatList.add(vcInvoiceRevoke);
					}
				}
				//更新缴销信息上传平台标志为已上传
				invoiceExportService.updateVcInvoiceRevokeList(uploadPlatList,userCode);
				//遗失发票行数
				ysfp.addAttribute("RowNum", lostRowNum+"");
				//缴销发票行数
				jxfp.addAttribute("RowNum", revokeRowNum+"");
			}
			
			Calendar startC = Calendar.getInstance();
			startC.setTime(startDate);
			startC.set(Calendar.HOUR, 0);
			startC.set(Calendar.MINUTE, 0);
			startC.set(Calendar.SECOND, 0);
			
			Calendar endC = Calendar.getInstance();
			endC.setTime(endDate);
			endC.set(Calendar.HOUR, 23);
			endC.set(Calendar.MINUTE, 59);
			endC.set(Calendar.SECOND, 59);
			//发票使用：元素赋值
			kssj.setText(timeFmt.format(startC.getTime()));//开始时间
			jssj.setText(timeFmt.format(endC.getTime()));//结束时间
			jls.setText(size+"");//记录数
			zpfs.setText(count1+"");//正票份数
			zpje.setText(amount1.toString());//正票金额
			fpfs.setText(count2+"");//废票份数
			tpfs.setText(count3+"");//退票份数
			tpje.setText(Math.abs(amount3.doubleValue())+"");//退票金额
			ysfs.setText(lostCount+"");//遗失份数
			jxfs.setText(revokeCount+"");//缴销份数
			
			//文件前缀
			String xmlFileName = "TYFP_310115312396008_"+sdf.format(new Date())+".xml";
			String xmlFullName = filePath+separator+xmlFileName;
			File xmlFile = new File(xmlFullName);
			//生成xml文件开始
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setLineSeparator("\r\n");
			format.setEncoding("gb2312");//设置编码
			XMLWriter writer = new XMLWriter(new FileOutputStream(xmlFile), format);
			writer.write(document);
			writer.close();
			//生成xml文件结束
			logger.info("生成航保出单中心发票数据文件路径：========"+xmlFullName+"===========");
			//删除xml文件
			//xmlFile.delete();
			
			fileData = filePath + "==" + xmlFileName;
		}catch(Exception e){
			throw new BusinessException("生成xml文件失败！", e);
		}
		return fileData;
	}
	
	/**
	 * 大连地区导出zip文件
	 * @param vcInvoicePrintTmpList
	 * @param filePath
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private String generateZIPFile_DL(List<VcInvoicePrint> vcInvoicePrintTmpList, String filePath) throws BusinessException,IOException,Exception{
		//文件最多包含5000条数据
		if(vcInvoicePrintTmpList.size()>5000){
			throw new BusinessException("抱歉！数据超过5000行，不能导出！");
		}
		String fileData = "";
		String count = InvoiceExportAction.countForDL();
		String separator = java.io.File.separator;
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		DecimalFormat dft = new DecimalFormat("0.00");
		
		//txt文件名
		StringBuffer fileName = new StringBuffer();
		fileName.append("210202736400605");
		fileName.append(sf1.format(new Date()));
		//2位流水号
		fileName.append(count);
		String txtFileName = fileName + ".txt";
		File txtFile = new File(filePath + separator + txtFileName);
		FileOutputStream out = new FileOutputStream(txtFile);
		StringBuffer sb = new StringBuffer("");
		int totalCount = vcInvoicePrintTmpList.size();
		//第一行为记录数
		sb.append(totalCount).append("\r\n");
		for(VcInvoicePrint vcInvoicePrint : vcInvoicePrintTmpList){
			// 发票代码
			sb.append(vcInvoicePrint.getInvoiceCode()).append("\t");
			// 发票号码
			sb.append(StringUtil.formatNumberLength(vcInvoicePrint.getInvoiceNo(),8)).append("\t");
			
			String printKind = vcInvoicePrint.getPrintKind();//发票开具类型:1-正常;2-负数;3-作废
			// 发票状态
			if("1".equals(printKind) || "2".equals(printKind)){
				sb.append("10").append("\t");//正常发票
			}else if("3".equals(printKind)){
				sb.append("11").append("\t");//作废发票
			}
			// 收款单位名称
			sb.append("天安财产保险股份有限公司大连分公司").append("\t");
			// 收款单位识别号
			sb.append("210202736400605").append("\t");
			// 付款方名称
			sb.append(formatNullToEmpty(vcInvoicePrint.getPayerName())).append("\t");
			// 付款方识别号
			sb.append("").append("\t");
			// 开票日期
			sb.append(formatDateForDL(vcInvoicePrint.getPrintDate())).append("\t");
			// 合计金额（小写）
			//sb.append(dft.format(vcInvoicePrint.getAmount()==null?0.00:vcInvoicePrint.getAmount())).append("\t");
			sb.append(formatAmount(vcInvoicePrint.getAmount())).append("\t");
			// 开票人
			//begin 开票人字符不超过9个 2014-6-11 by zhxiao
			if(formatNullToEmpty(vcInvoicePrint.getBillerName()).length()>=9){
				sb.append(vcInvoicePrint.getBillerName().substring(0, 9)).append("\t");
			}else{
				sb.append(formatNullToEmpty(vcInvoicePrint.getBillerName())).append("\t");
			}
			//end 开票人字符不超过9个 2014-6-11 by zhxiao
			// 发票开具扩展信息
			VcInvoicePrintExt vcInvoicePrintExt = vcInvoicePrint.getVcInvoicePrintExtList().get(0);
			// 项目一内容
			sb.append("保险费").append("\t");
			// 项目一金额
			sb.append(formatAmount(vcInvoicePrintExt.getPremium())).append("\t");
			// 项目二内容 
			sb.append("代收车船税").append("\t");
			// 项目二金额
			sb.append(formatAmount(vcInvoicePrintExt.getTaxAmount())).append("\t");
			// 项目三内容
			sb.append("滞纳金").append("\t");
			// 项目三金额
			sb.append(formatAmount(vcInvoicePrintExt.getLateFee()));
			sb.append("\r\n");
		}
		out.write(sb.toString().getBytes());
		out.close();
		
		// MD5转换
		//得到生成txt文件的MD5
		String txtMD5 = getMD5(filePath + separator + txtFileName);
		//生成ZIP文件名
		String zipFileName = filePath+separator+ digest(digest("123456") + txtMD5) + ".zip";
		// 生成zip文件
		AntZip antZip = new AntZip();
		antZip.doZip(new File[]{txtFile}, zipFileName);
		logger.info("生成厦门发票数据文件路径：========"+filePath+"===========");
		//删除txt临时文件
		//txtFile.delete();
		File zipFile = new File(zipFileName);
		fileData = filePath + "==" + zipFile.getName();
		return fileData;
	}
	
	/**
	 * 黑龙江地区生产txt文件
	 * @param vcInvoicePrintTmpList
	 * @param filePath
	 * @param manageCode
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private String generateTXTFileHlj(List<VcInvoicePrint> vcInvoicePrintTmpList, String filePath, String manageCode) throws IOException,Exception{
		//返回的json数据格式：文件路径==文件名
		String fileData = "";
		String separator = java.io.File.separator;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		DecimalFormat dft = new DecimalFormat("0.00");
		
		//txt文件名
		String fileName = manageCode + "_"
				+ df.format(invoiceExportVo.getPrintDateStart()) + "_"
				+ df.format(invoiceExportVo.getPrintDateEnd())+".txt";
		File txtFile = new File(filePath + separator + fileName);
		FileOutputStream out = new FileOutputStream(txtFile);
		StringBuffer sb = new StringBuffer("");
		int i=1;
		for(VcInvoicePrint vcInvoicePrint : vcInvoicePrintTmpList){
			//打印类型：1-正常；2-负数；3-作废；4-空白作废
			String printKind = vcInvoicePrint.getPrintKind();
			//0-打印；1-作废
			String type="";
			if("1".equals(printKind) || "2".equals(printKind)){
				type="0";
			}else if("3".equals(printKind) || "4".equals(printKind)){
				type="1";
			}
			//管理编码
			sb.append(manageCode).append("\t");
			//序号
			sb.append(i++).append("\t");
			sb.append(type).append("\t");
			//发票代码
			sb.append(vcInvoicePrint.getInvoiceCode()).append("\t");
			//发票号码
			sb.append(StringUtil.formatNumberLength(vcInvoicePrint.getInvoiceNo(), 8)).append("\t");
			//打印时间
			if("0".equals(type)){
				sb.append(df.format(vcInvoicePrint.getPrintDate())).append("\t");
			}else if("1".equals(type)){
				sb.append(df.format(vcInvoicePrint.getCanceldDate())).append("\t");
			}
			//付款方名称
			sb.append(vcInvoicePrint.getPayerName()).append("\t");
			if("0".equals(type)){
				sb.append("保险发票").append("\t");
				sb.append("1").append("\t");// 数量
			}else if("1".equals(type)){
				sb.append("作废").append("\t");
				sb.append("1").append("\t");// 数量
			}
			sb.append(vcInvoicePrint.getAmount()).append("\t");
			sb.append(vcInvoicePrint.getAmount()).append("\t");
			sb.append(StringUtils.trimToEmpty(vcInvoicePrint.getBillerName())).append("\t");
			sb.append("\n");
		}
		out.write(sb.toString().getBytes());
		out.close();
		fileData = filePath + "==" + fileName;
		logger.info("生成黑龙江发票数据文件路径：========"+filePath+fileName+"===========");
		return fileData;
	}
	
	/**
     * 北京发票导出
     * @param vcInvoicePrintTmpList
     * @param uploadPlatList
     * @param filePath
     * @param userCode
     * @return
     * @throws BusinessException
     */ 
    private String generateXmlFile_BeiJing(List<VcInvoicePrint> vcInvoicePrintTmpList, InvoiceExportVO invoiceVo,
            String filePath, String orgCode) throws BusinessException {
        // 返回的json数据格式：文件路径==文件名
        String fileData = "";
        // 根据当前用户所属机构在pub_company中查找纳税人识别号
        PubCompany pubCompany = vcLevelService.findPubCompanyByCode(orgCode);
        try {
            String separator = java.io.File.separator;
            SimpleDateFormat timeFmt = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            DecimalFormat sf3 = new DecimalFormat("0.00"); // 格式化金额

            // 创建InvoiceInfo文件document
            Document documentInvoiceInfo = DocumentHelper.createDocument();
            // 创建InvoiceInfo根节点
            Element schema = documentInvoiceInfo.addElement("SCHEMA");

            int countZC = 0;// 正常票份数
            int countFP = 0;// 废票份数
            int countTP = 0;// 退票份数
            int countCP = 0;// 错票份数
            BigDecimal amountZC = new BigDecimal(0.00);// 正票金额
            BigDecimal amountTP = new BigDecimal(0.00);// 退票金额
            Element record;
            Element mx;
            for (VcInvoicePrint vcInvoicePrint : vcInvoicePrintTmpList) {
                record = schema.addElement("RECORD");
                // 发票开具类型：1-正常;2-负数;3-作废
                String printKind = vcInvoicePrint.getPrintKind();
                if (vcInvoicePrint.getAmount() != null && vcInvoicePrint.getAmount().compareTo(BigDecimal.ZERO) < 0) {
                    printKind = "2";
                }
                // 开票记录
                record.addAttribute("FPDM", StringUtil.formatNumberLength(vcInvoicePrint.getInvoiceCode(), 12));// 发票代码
                record.addAttribute("FPHM", StringUtil.formatNumberLength(vcInvoicePrint.getInvoiceNo(), 8));// 发票号码
                // 发票开具类型：1-正常;2-负数;3-作废
                if ("1".equals(printKind)) {
                    record.addAttribute("KPLX", "1"); // 1、正常票，2、退票，3废票， 4错票
                    countZC++;// 正票份数+1
                    amountZC = amountZC.add(vcInvoicePrint.getAmount());// 正票金额累加
                } else if ("3".equals(printKind)) {
                    record.addAttribute("KPLX", "3"); // 1、正常票，2、退票，3废票， 4错票
                    countFP++;// 废票份数+1
                } else if ("2".equals(printKind)) {
                    // 退票对应的正常票的12位发票代码
                    record.addAttribute("TPDM", StringUtil.formatNumberLength(vcInvoicePrint.getCounteractedInvoiceCode(), 12));
                    // 退票对应的正常票的8位发票号
                    record.addAttribute("TPHM", StringUtil.formatNumberLength(vcInvoicePrint.getCounteractedInvoiceNo(), 8));
                    record.addAttribute("KPLX", "2"); // 1、正常票，2、退票，3废票， 4错票
                    countTP++;// 退票份数+1
                    amountTP = amountTP.add(vcInvoicePrint.getAmount());// 退票金额累加
                }
                record.addAttribute("KPRQ", sdf.format(vcInvoicePrint.getPrintDate()));// 开票日期
                record.addAttribute("KPR", vcInvoicePrint.getBillerName());// 开票人姓名
                record.addAttribute("HY", "金融保险业");// 行业分类代码：22-金融保险业
                record.addAttribute("SKDW", vcInvoicePrint.getRecipientName());
                record.addAttribute("FKDW", vcInvoicePrint.getPayerName());
                record.addAttribute("KPJE", sf3.format(vcInvoicePrint.getAmount().abs()));
                record.addAttribute("BZ", "");

                List<VcInvoicePrintExt> vcInvoicePrintExtList = vcInvoicePrint.getVcInvoicePrintExtList();
                if (vcInvoicePrintExtList != null && vcInvoicePrintExtList.size() != 0) {
                    VcInvoicePrintExt VcInvoicePrintExt = vcInvoicePrintExtList.get(0);
                    if (VcInvoicePrintExt.getPremium() != null
                            && VcInvoicePrintExt.getPremium().compareTo(BigDecimal.ZERO) != 0) {
                        mx = record.addElement("MX");
                        mx.addAttribute("XM", "保险费");
                        mx.addAttribute("JE", sf3.format( VcInvoicePrintExt.getPremium().abs()));// 金额
                        mx.addAttribute("DJ", "");
                        mx.addAttribute("SL", "");

                    }
                    if (VcInvoicePrintExt.getTaxAmount() != null
                            && VcInvoicePrintExt.getTaxAmount().compareTo(BigDecimal.ZERO) != 0) {
                        mx = record.addElement("MX");
                        mx.addAttribute("XM", "代收车船税");
                        mx.addAttribute("JE", sf3.format(VcInvoicePrintExt.getTaxAmount().abs()));// 金额
                        mx.addAttribute("DJ", "");
                        mx.addAttribute("SL", "");
                    }
                    if (VcInvoicePrintExt.getLateFee() != null
                            && VcInvoicePrintExt.getLateFee().compareTo(BigDecimal.ZERO) != 0) {
                        mx = record.addElement("MX");
                        mx.addAttribute("XM", "滞纳金");
                        mx.addAttribute("JE", sf3.format(VcInvoicePrintExt.getLateFee().abs()));// 金额
                        mx.addAttribute("DJ", "");
                        mx.addAttribute("SL", "");
                    }
                }
            }
            // InvoiceInfo头部信息
            schema.addAttribute("CZSJ", timeFmt.format(new Date()));
            schema.addAttribute("KPQSSJ", sdf.format(invoiceVo.getPrintDateStart()));
            schema.addAttribute("KPJZSJ", sdf.format(invoiceVo.getPrintDateEnd()));
            schema.addAttribute("ZCKPFS", countZC + "");// 正常票份数
            schema.addAttribute("TPFS", countTP + "");// 退票份数
            schema.addAttribute("FPFS", countFP + "");// 废票份数
            schema.addAttribute("CPFS", countCP + "");// 错票份数
            schema.addAttribute("ZCFPJE", sf3.format(amountZC));// 正常票合计金额
            schema.addAttribute("TPJE", sf3.format(amountTP.abs()));// 退票合计金额(显示为正数)
            schema.addAttribute("RECORDCOUNT", vcInvoicePrintTmpList.size() + "");// 记录条数
            //modify by zhxiao3 ITEA-46875调整平谷营服计算机代码 begin
            if(pubCompany.getCompanyCode().equals("018032")){
            	schema.addAttribute("JSJDM", "12756363");// 计算机代码
            }else{
            	schema.addAttribute("JSJDM", "06A94350");// 计算机代码
            }
            //modify by zhxiao3 ITEA-46875调整平谷营服计算机代码 end
            schema.addAttribute("NSRSBH", pubCompany.getTaxNumber());
            // xml文件完整路径
            String mainXmlFullPath = filePath + separator + "BeiJingInvoice_" + sdf.format(new Date()) + ".xml";
            File xmlFile1 = new File(mainXmlFullPath);
            // 生成xml文件开始
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("GBK");// 设置编码
            XMLWriter writer = new XMLWriter(new FileOutputStream(xmlFile1), format);
            writer.write(documentInvoiceInfo);
            writer.close();
            // 生成xml文件结束
            logger.info("生成北京发票开具信息文件路径" + mainXmlFullPath + "===========");
            fileData = filePath + "==" + xmlFile1.getName();
        } catch (Exception e) {
            throw new BusinessException("生成xml文件失败！", e);
        }
        return fileData;
    }
	
	
	/**
	 * md5加密字符串
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private String digest(String value) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(value.getBytes());
		byte[] tmp = md5.digest();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tmp.length; i++) {
			if ((0xff & tmp[i]) < 0x10) {
				sb.append("0" + Integer.toHexString((0xFF & tmp[i])));
			} else {
				sb.append(Integer.toHexString(0xFF & tmp[i]));
			}
		}
		return sb.toString().toLowerCase();
	}
	
	/**
	 * md5加密文件内容
	 * @param value
	 * @return
	 * @throws BusinessException
	 */
	public String getMD5(String filePath) throws BusinessException {
		try {
			String MD5 = "";
			File file = new File(filePath);
			FileInputStream fos = new FileInputStream(file);
			java.security.MessageDigest alga = java.security.MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[8192];// 设置填充大小8M
			int read;// 实际读取的字节数
			while ((read = fos.read(buffer, 0, 1024)) > 0) {
				alga.update(buffer, 0, read);
			}
			byte[] digesta = alga.digest();
			for (int i = 0; i < digesta.length; i++) {
				MD5 += Integer.toHexString((0x000000ff & digesta[i]) | 0xffffff00).substring(6);
			}
			return MD5.toLowerCase();
		} catch (NoSuchAlgorithmException e) {
			throw new BusinessException("MD5加密失败！");
		} catch (IOException e) {
			throw new BusinessException("MD5加密失败！");
		}
	}
	
	
	
	/**
	 * 空值验证
	 * @param value
	 * @return value
	 */
	private String formatNullToEmpty(String value) {
		return null == value ? "" : value;
	}
	/**
	 * 小写金额转换
	 * @param value
	 * @return value
	 */
	private String formatAmount(BigDecimal value) {
		String result;
		if (null == value){
			value = new BigDecimal(0.00);
		}
		DecimalFormat dft = new DecimalFormat("0.00");
		result = dft.format(value);
		return result;
	}
	/**
	 * 打印日期处理
	 * @param date
	 * @return result
	 */
	private String formatDateForDL(Date date) {
		String result;
		if (null == date){
			result = "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		result = format.format(date);
		return result;
	}
	 /**
     * dto转换传xml
     * @param obj 待转换对象
     * @return encoding 编码方式（UTF-8、gb2312）
     * @throws Exception
     */
    private static String generateXML(Object obj,String encoding) throws Exception {
        String xml = "";
        JAXBContext jc = JAXBContext.newInstance(new Class[] {obj.getClass()});
        Marshaller m = jc.createMarshaller();
       
        m.setProperty(Marshaller.JAXB_ENCODING, encoding);
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.setProperty(Marshaller.JAXB_FRAGMENT,false);
        StringWriter sw = new StringWriter();
        m.marshal(obj, sw);
        xml = sw.toString();
//        System.out.println(xml);
        return xml;
    }
	
	/**
	 * 删除目录下文件
	 * @param dirPath
	 */
	private void deleteFile(File dirPath) throws Exception{
		logger.info("删除当天之前的文件...");
		File[] fileList = dirPath.listFiles();
		if(fileList!=null && fileList.length>0){
			for(File file : fileList){
				Long time = file.lastModified();
				Calendar nowDate = Calendar.getInstance();
				Calendar calendar = Calendar.getInstance();
				nowDate.setTime(new Date());
				calendar.setTimeInMillis(time);
				//文件的修改日期小于当前日期，则删除（当天的文件不删除）
				if(calendar.get(Calendar.MONTH)<nowDate.get(Calendar.MONTH)
						|| calendar.get(Calendar.DATE)<nowDate.get(Calendar.DATE)){
					file.delete();
				}
			}
		}
	}
	
	/**
	 * 根据界面输入日期获得所属期
	 * @return 
	 * @throws BusinessException 
	 */
	private String getSsq() throws BusinessException{
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
		Date startDate = invoiceExportVo.getPrintDateStart();
		Date endDate = invoiceExportVo.getPrintDateEnd();
		String strStart = sf.format(startDate);
		String strEnd = sf.format(endDate);
		if (strStart.equals(strEnd)){
			return strStart;
		}else{
			throw new BusinessException("导出日期必须为同一个月份！");
		}
	}
	
	/**
	 * 将一个list拆分成多外list，每个list.size()<=50000
	 * @param vcInvoicePrintTmpList
	 * @return list
	 */
	private List<List<VcInvoicePrint>> splitVcInvoicePrintList(List<VcInvoicePrint> vcInvoicePrintTmpList, int pageCount){
		ArrayList list = new ArrayList();
		if(vcInvoicePrintTmpList.size()<=pageCount){
			list.add(vcInvoicePrintTmpList);
		}else{
			int sum=0;
			boolean flag = true;
			while(flag){
				List<VcInvoicePrint> newList = new ArrayList<VcInvoicePrint>(pageCount);
				for(int count=0;count<pageCount;count++){
					if(sum<vcInvoicePrintTmpList.size()){
						newList.add(vcInvoicePrintTmpList.get(sum));
						sum++;
					}else{
						flag = false;
						break;
					}
				}
				list.add(newList);
			}
		}
		return list;
	}
	
	/**
	 * 计算导出信息总金额
	 * @param vcInvoicePrintTmpList
	 * @return totalAmount
	 */
	private BigDecimal getTotalAmount(List<VcInvoicePrint> vcInvoicePrintTmpList){
		BigDecimal totalAmount = BigDecimal.ZERO;
		if (vcInvoicePrintTmpList.size()>0){
			for(VcInvoicePrint vcInvoicePrint: vcInvoicePrintTmpList){
                if (vcInvoicePrint.getAmount() != null) {
                    BigDecimal amount=vcInvoicePrint.getAmount(); 
                    totalAmount = totalAmount.add(amount);
                }
			}
		}
		return totalAmount;
	}
	
	public void setInvoiceExportService(InvoiceExportService invoiceExportService) {
		this.invoiceExportService = invoiceExportService;
	}

	public void setInsuKindService(InsuKindService insuKindService) {
		this.insuKindService = insuKindService;
	}

	public List<Map<String, String>> getMapList() {
		return mapList;
	}

	public void setMapList(List<Map<String, String>> mapList) {
		this.mapList = mapList;
	}

	public List<VcInvoicePrint> getVcInvoicePrintList() {
		return vcInvoicePrintList;
	}

	public void setVcInvoicePrintList(List<VcInvoicePrint> vcInvoicePrintList) {
		this.vcInvoicePrintList = vcInvoicePrintList;
	}
	
	public InvoiceExportVO getInvoiceExportVo() {
		return invoiceExportVo;
	}

	public void setInvoiceExportVo(InvoiceExportVO invoiceExportVo) {
		this.invoiceExportVo = invoiceExportVo;
	}
	
	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

    /**
     * @param vcLevelService the vcLevelService to set
     */
    public void setVcLevelService(VcLevelService vcLevelService) {
        this.vcLevelService = vcLevelService;
    }
	
	
}
