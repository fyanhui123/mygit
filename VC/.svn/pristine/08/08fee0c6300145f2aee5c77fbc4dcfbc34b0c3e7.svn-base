package com.tapi.tcs.vc.inquiry.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.baseinfo.service.VcLevelService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.service.PubCodeManagerService;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.ExcelExportHandler;
import com.tapi.tcs.vc.inquiry.service.InquiryManageService;
import com.tapi.tcs.vc.inquiry.vo.DocDetailInquiryVo;
import com.tapi.tcs.vc.inquiry.vo.DocInstoreApplyInquiryVo;
import com.tapi.tcs.vc.inquiry.vo.LostVerificationInquiryVo;
import com.tapi.tcs.vc.schema.model.VcLevel;

public class InquiryManageAction extends TFAction {

    private static final long serialVersionUID = -2248031194541049469L;

    /** 印刷入库查询统计service */
    InquiryManageService inquiryManageService;

    /** 公共代码service */
    PubCodeManagerService pubCodeManagerService;

    /** 机构级别service */
    private VcLevelService vcLevelService;

    /** 印刷入库查询统计vo */
    DocInstoreApplyInquiryVo inStoreInquiryDto;

    /** 单证明细查询统计vo */
    private DocDetailInquiryVo docDetailInquiryDto;

    // 印刷入库查询结果List
    List inStoreInquiryDtoList;

    // 单证明细查询结果List
    List docDetailInquiryDtoList;

    // 统计查询结果List
    List inquiryDtoList;

    // 单证状态List
    List docStatusList;

    /** 遗失核销查询结果list */
    private List<LostVerificationInquiryVo> lostVos;

    private LostVerificationInquiryVo lostVo;

    // 申请时间
    private Date applyStartDate;
    private Date applyEndDate;
    /**返回页面的字符串*/
    private String jsonData;
    
    private String docVerCodes;
    
    
    /**结果信息*/
    private Map<String,String> resultMap;
    
    /**
     * 进入印刷入库查询页面
     * 
     * @return
     */
    public String initDocInStoreInquiry() {
        Date nowDate = new Date();
        applyStartDate = DateUtils.addMonth(nowDate, -2);
        applyEndDate = nowDate;
        return SUCCESS;
    }

    /**
     * 查询印刷入库
     * 
     * @return
     */
    public String queryDocInStoreInquiryListByPages() throws Exception {
        logger.info("访问/inquiryJson/queryDocInStoreInquiryListByPages.do?ajax=true分页查询印刷入库统计信息...");
        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            // 当前登陆用户机构级别
            VcLevel levelVo = vcLevelService.getVcLevel("0", userInfo.getComCode());
            if (levelVo == null) {
                throw new Exception("当前登陆用户机构级别不存在！");
            }
            if (levelVo.getLevelNo() > 2) {
                throw new Exception("当前登陆用户机构级别不够，无权查询！");
            }
            if (levelVo.getLevelNo() == 0) {
                userInfo.setComCode(null);
            }
            Page pages = inquiryManageService.queryVcDocInStoreInquiry(inStoreInquiryDto, userInfo, page,
                    rows);

            inStoreInquiryDtoList = pages.getResult();

            // 总页数
            total = (int) pages.getTotalPageCount();
            // 总记录数
            records = (int) pages.getTotalCount();
        } catch (BusinessException e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

    /**
     * 进入单证明细查询页面
     * 
     * @return
     * @author wanghuajian since 2013-4-18上午11:23:22
     */
    public String initDocDetailInquiry() throws Exception {
        logger.info("访问/inquiryJson/initDocDetailInquiry.do?ajax=true进入单证明细查询页面...");
        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            // 查询单证状态
            docStatusList = pubCodeManagerService.getVcPubCodeListByCodeTypeByOrder("DocStatus",
                    new String[] { "codeCode" }, "asc", null);

            Date nowDate = new Date();
            applyStartDate = DateUtils.addMonth(nowDate, -3);
            applyEndDate = nowDate;
            if(docDetailInquiryDto==null){
                docDetailInquiryDto = new DocDetailInquiryVo();
            } 
            docDetailInquiryDto.setOrgCode(userInfo.getComCode());
            docDetailInquiryDto.setOrgName(userInfo.getComName());
        } catch (BusinessException e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

    /**
     * 单证明细统计查询前校验
     * 
     * @return
     * @throws BusinessException
     * @author wanghuajian since 2013-4-18上午11:23:50
     */
    public String checkBeforeDetailInquiry() throws Exception {
        try {           
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            resultMap=new HashMap<String,String>();
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            if (docDetailInquiryDto == null) {
                docDetailInquiryDto = new DocDetailInquiryVo();
            }
            if (StringUtils.isNotBlank(docDetailInquiryDto.getOrgCode())) {
                boolean isTrue = vcLevelService.isUpperOrg(docDetailInquiryDto.getOrgCode(), userInfo.getComCode());
                if (!isTrue) {
                    throw new BusinessException("无权查询此机构，请重新选择！ ");
                }
            }
            resultMap.put("status", "0"); 
        } catch (BusinessException e) {           
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {           
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

    /**
     * 单证明细统计查询
     * 
     * @return
     * @throws BusinessException
     * @author wanghuajian since 2013-4-18上午11:23:50
     */
    public String queryDocDetailInquiryListByPages() throws Exception {
        logger.info("访问/inquiryJson/queryDocDetailInquiryListByPages.do?ajax=true单证明细统计查询...");
        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            if(docDetailInquiryDto==null){
            	docDetailInquiryDto = new DocDetailInquiryVo();
            }           
            if(StringUtils.isNotBlank(docVerCodes)){
                docDetailInquiryDto.setArrDocVerCode(docVerCodes.split(","));
            }            
            Page pages = inquiryManageService.queryDocDetailInquiryListByPages(docDetailInquiryDto, userInfo,
                    page, rows);

            docDetailInquiryDtoList = pages.getResult();

            // 总页数
            total = (int) pages.getTotalPageCount();
            // 总记录数
            records = (int) pages.getTotalCount();
        } catch (BusinessException e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }
    
    public String initLostVerificationQuery() {
        applyEndDate = new Date();
        applyStartDate = DateUtils.addMonth(applyEndDate, -2);
        logger.info("访问/inquiryJson/queryLostVerification.do?ajax=true进入遗失核销统计查询...");
        return SUCCESS;
    }

    /**
     * 遗失核销查询统计
     * 
     * @return
     */
    public String queryLostVerification() throws Exception {
        logger.info("访问/inquiryJson/queryLostVerification.do?ajax=true遗失核销统计查询...");
        try {
            if (StringUtils.isBlank(lostVo.getLostOrgCode())) {
                UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
                if (userInfo == null) {
                    throw new BusinessException("登录超时，请重新登录！ ");
                }
                lostVo.setLostOrgCode(userInfo.getComCode());
            }

            Page pages = inquiryManageService.queryLostVerification(lostVo, page, rows);

            lostVos = (List<LostVerificationInquiryVo>) pages.getResult();
            // 总页数
            total = (int) pages.getTotalPageCount();
            // 总记录数
            records = (int) pages.getTotalCount();
        } catch (BusinessException e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }
    
    /**
     * 单证明细查询导出excel
     * @return
     * @throws BusinessException
     * fyh
     */
    public String exportDocDetailInquiry() throws BusinessException {
    	logger.info("访问/inquiryJson/exportDocDetailInquiry.do?ajax=true导出excel...");
    	try{
    		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            if(StringUtils.isNotBlank(docVerCodes)){
	             docDetailInquiryDto.setArrDocVerCode(docVerCodes.split(","));
	         }
            String comCode = userInfo.getComCode();
            //根据查询条件查询出数据
            List<DocDetailInquiryVo> list = inquiryManageService.queryDocDetailExplore(docDetailInquiryDto, userInfo);
            if(list==null || list.size()<1){
            	throw new BusinessException("没有要导出的数据！");
            }else if(list.size()>10000){
            	throw new BusinessException("导出结果超出最大限制（一万条），请缩小查询范围！");
            }
            ExcelExportHandler excelExportHandler = new ExcelExportHandler();
            //获取临时文件存放目录
            String dirPath = excelExportHandler.getDirPath();
            String date = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
            String fileName = "单证明细-"+date+".xls";
            //表头数组
            String[] title = {"单证类型代码","单证类型名称","单证种类","起始流水","终止流水","所属机构","操作人","所属人","操作时间","业务号","单证状态"}; 
            int size = list.size();
            //数据二位数组
            String[][] values = new String[size][];
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			for(int i=0;i<list.size();i++){
				DocDetailInquiryVo docDetailInquiryVo = list.get(i);
				String[] arr = new String[11];
				arr[0] = docDetailInquiryVo.getDocVerCode();//
				arr[1] = docDetailInquiryVo.getDocVerName();
				arr[2] = docDetailInquiryVo.getDocTypeName();
				arr[3] = docDetailInquiryVo.getStartNum();//
				arr[4] = docDetailInquiryVo.getEndNum();//
				arr[5] = docDetailInquiryVo.getOrgName();//所属机构
				arr[6] = docDetailInquiryVo.getOprName();//操作人
				arr[7] = docDetailInquiryVo.getOwnerName();//所属人名称
				arr[8] = docDetailInquiryVo.getOperateTime()==null?"":df.format(docDetailInquiryVo.getOperateTime());
				arr[9] = docDetailInquiryVo.getBusinessNo();
				arr[10] = docDetailInquiryVo.getStatus();//
				values[i] = arr;
			}
			//创建excel文件
			excelExportHandler.createExcelFile(dirPath+fileName, title, values);
			//返回值：文件存放目录==文件名
			jsonData = dirPath +"=="+ fileName;
    	}catch(BusinessException e){
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
    	}catch(Exception e){
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
    	}
    	return SUCCESS;
    }
    
    /**
     * 印刷入库导出excel
     * @return
     * @throws BusinessException
     */
    public String exportDocInStoreInquiry() throws BusinessException {
    	logger.info("访问/inquiryJson/exportDocInStoreInquiry.do?ajax=true导出excel...");
    	try{
    		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            String comCode = userInfo.getComCode();
            // 当前登陆用户机构级别
            VcLevel levelVo = vcLevelService.getVcLevel("0", userInfo.getComCode());
            if (levelVo == null) {
                throw new Exception("当前登陆用户机构级别不存在！");
            }
            if (levelVo.getLevelNo() > 2) {
                throw new Exception("当前登陆用户机构级别不够，无权查询！");
            }
            if (levelVo.getLevelNo() == 0) {
                comCode = null;
            }
            List<DocInstoreApplyInquiryVo> list = inquiryManageService.findVcDocInStore(inStoreInquiryDto, comCode);
            if(list==null || list.size()<1){
            	throw new BusinessException("没有查询到符合条件的数据！");
            }else if(list.size()>65000){
            	throw new BusinessException("查询结果超出限制，请缩小查询范围！");
            }
            ExcelExportHandler excelExportHandler = new ExcelExportHandler();
            //获取临时文件存放目录
            String dirPath = excelExportHandler.getDirPath();
            String date = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
            String fileName = "印刷入库-"+date+".xls";
            //表头数组
            String[] title = {"单证类型代码","单证类型名称","单证种类","申请单号","起始流水","终止流水","印刷批次","数量","费用",
            		"申请机构","申请人","申请时间","申请单状态","入库时间"}; 
            int size = list.size();
            //数据二位数组
            String[][] values = new String[size][];
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for(int i=0;i<list.size();i++){
				DocInstoreApplyInquiryVo docInstoreApplyInquiryVo = list.get(i);
				String[] arr = new String[title.length];
				String status = docInstoreApplyInquiryVo.getInStoreStatus();
				String statusTmp = docInstoreApplyInquiryVo.getInStoreStatus();
				if ("6".equals(statusTmp)) {
					status = "确认";
				}else if ("5".equals(statusTmp)) {
					status = "确认退回";
				} else if ("4".equals(statusTmp)) {
					status = "等待确认";
				} else if ("3".equals(statusTmp)) {
					status = "发放退回";
				} else if ("2".equals(statusTmp)) {
					status = "等待发放";
				}else if ("1".equals(statusTmp)) {
					status = "新建";
				} else if ("0".equals(statusTmp)) {
					status = "删除";
				}
				arr[0] = docInstoreApplyInquiryVo.getDocVerCode();
				arr[1] = docInstoreApplyInquiryVo.getDocVerName();
				arr[2] = docInstoreApplyInquiryVo.getDocTypeName();
				arr[3] = docInstoreApplyInquiryVo.getInStoreAppCode();
				arr[4] = docInstoreApplyInquiryVo.getStartNum();
				arr[5] = docInstoreApplyInquiryVo.getEndNum();
				arr[6] = docInstoreApplyInquiryVo.getPressBatchNo();
				arr[7] = docInstoreApplyInquiryVo.getTotalAmount()+"";
				arr[8] = docInstoreApplyInquiryVo.getPrintingFee()+"";
				arr[9] = docInstoreApplyInquiryVo.getApplyOrgName();
				arr[10] = docInstoreApplyInquiryVo.getApplyOprName();
				arr[11] = docInstoreApplyInquiryVo.getApplyTime()==null?"":df.format(docInstoreApplyInquiryVo.getApplyTime());
				arr[12] = status;
				arr[13] = docInstoreApplyInquiryVo.getInStoreTime()==null?"":df.format(docInstoreApplyInquiryVo.getInStoreTime());
				values[i] = arr;
			}
			//创建excel文件
			excelExportHandler.createExcelFile(dirPath+fileName, title, values);
			//返回值：文件存放目录==文件名
			jsonData = dirPath +"=="+ fileName;
    	}catch(BusinessException e){
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
    	}catch(Exception e){
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
    	}
    	return SUCCESS;
    }
    
    /**
     * 遗失核销统计查询导出excel
     * @return
     * @throws BusinessException
     */
    public String exportLostVerification() throws BusinessException {
    	logger.info("访问/inquiryJson/exportLostVerification.do?ajax=true遗失核销统计查询导出excel...");
    	try{
    		if (StringUtils.isBlank(lostVo.getLostOrgCode())) {
                UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
                if (userInfo == null) {
                    throw new BusinessException("登录超时，请重新登录！ ");
                }
                lostVo.setLostOrgCode(userInfo.getComCode());
            }
    		List<LostVerificationInquiryVo> list = inquiryManageService.findLostVerification(lostVo);
            if(list==null || list.size()<1){
            	throw new BusinessException("没有查询到符合条件的数据！");
            }else if(list.size()>65000){
            	throw new BusinessException("查询结果超出限制，请缩小查询范围！");
            }
            ExcelExportHandler excelExportHandler = new ExcelExportHandler();
            //获取临时文件存放目录
            String dirPath = excelExportHandler.getDirPath();
            String date = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
            String fileName = "遗失核销统计-"+date+".xls";
            //表头数组
            String[] title = {"遗失单号","单证类型名称","单证类型代码","印刷批次","起始流水号","终止流水号","遗失数量","机构名称","遗失时间"}; 
            int size = list.size();
            //数据二位数组
            String[][] values = new String[size][];
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for(int i=0;i<list.size();i++){
				LostVerificationInquiryVo lostVerificationInquiryVo = list.get(i);
				String[] arr = new String[title.length];
				arr[0] = lostVerificationInquiryVo.getLostCode();
				arr[1] = lostVerificationInquiryVo.getDocVerName();
				arr[2] = lostVerificationInquiryVo.getDocVerCode();
				arr[3] = lostVerificationInquiryVo.getPressBatchNo();
				arr[4] = lostVerificationInquiryVo.getStartNum();
				arr[5] = lostVerificationInquiryVo.getEndNum();
				arr[6] = lostVerificationInquiryVo.getLostNum()+"";
				arr[7] = lostVerificationInquiryVo.getLostOrgCode();
				arr[8] = lostVerificationInquiryVo.getLostTime()==null?"":df.format(lostVerificationInquiryVo.getLostTime());
				values[i] = arr;
			}
			//创建excel文件
			excelExportHandler.createExcelFile(dirPath+fileName, title, values);
			//返回值：文件存放目录==文件名
			jsonData = dirPath +"=="+ fileName;
    	}catch(BusinessException e){
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
    	}catch(Exception e){
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
    	}
    	return SUCCESS;
    }

    public List getInStoreInquiryDtoList() {
        return inStoreInquiryDtoList;
    }

    public void setInStoreInquiryDtoList(List inStoreInquiryDtoList) {
        this.inStoreInquiryDtoList = inStoreInquiryDtoList;
    }

    public DocDetailInquiryVo getDocDetailInquiryDto() {
        return docDetailInquiryDto;
    }

    public void setDocDetailInquiryDto(DocDetailInquiryVo docDetailInquiryDto) {
        this.docDetailInquiryDto = docDetailInquiryDto;
    }

    public List getInquiryDtoList() {
        return inquiryDtoList;
    }

    public void setInquiryDtoList(List inquiryDtoList) {
        this.inquiryDtoList = inquiryDtoList;
    }

    public List getDocDetailInquiryDtoList() {
        return docDetailInquiryDtoList;
    }

    public void setDocDetailInquiryDtoList(List docDetailInquiryDtoList) {
        this.docDetailInquiryDtoList = docDetailInquiryDtoList;
    }

    public DocInstoreApplyInquiryVo getInStoreInquiryDto() {
        return inStoreInquiryDto;
    }

    public void setInStoreInquiryDto(DocInstoreApplyInquiryVo inStoreInquiryDto) {
        this.inStoreInquiryDto = inStoreInquiryDto;
    }

    public void setInquiryManageService(InquiryManageService inquiryManageService) {
        this.inquiryManageService = inquiryManageService;
    }

    public void setPubCodeManagerService(PubCodeManagerService pubCodeManagerService) {
        this.pubCodeManagerService = pubCodeManagerService;
    }

    public void setVcLevelService(VcLevelService vcLevelService) {
        this.vcLevelService = vcLevelService;
    }

    public Date getApplyStartDate() {
        return applyStartDate;
    }

    public void setApplyStartDate(Date applyStartDate) {
        this.applyStartDate = applyStartDate;
    }

    public Date getApplyEndDate() {
        return applyEndDate;
    }

    public void setApplyEndDate(Date applyEndDate) {
        this.applyEndDate = applyEndDate;
    }

    public List<LostVerificationInquiryVo> getLostVos() {
        return lostVos;
    }

    public void setLostVos(List<LostVerificationInquiryVo> lostVos) {
        this.lostVos = lostVos;
    }

    public LostVerificationInquiryVo getLostVo() {
        return lostVo;
    }

    public void setLostVo(LostVerificationInquiryVo lostVo) {
        this.lostVo = lostVo;
    }

    public List getDocStatusList() {
        return docStatusList;
    }

    public void setDocStatusList(List docStatusList) {
        this.docStatusList = docStatusList;
    }

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
    


    public String getDocVerCodes() {
        return docVerCodes;
    }

    public void setDocVerCodes(String docVerCodes) {
        this.docVerCodes = docVerCodes;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

   
    
    
}
