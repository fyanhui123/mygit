package com.tapi.tcs.vc.baseinfo.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.tf.platform.schema.vo.CodeLabelDto;
import com.tapi.tcs.vc.baseinfo.baseconst.BaseInfoConst;
import com.tapi.tcs.vc.baseinfo.service.InsuKindService;
import com.tapi.tcs.vc.baseinfo.service.VcConstantConfigService;
import com.tapi.tcs.vc.baseinfo.service.VcDocVersionInfoService;
import com.tapi.tcs.vc.baseinfo.vo.DocInsuKindVo;
import com.tapi.tcs.vc.baseinfo.vo.DocVerSimpleVo;
import com.tapi.tcs.vc.baseinfo.vo.DocVersionInfoQueryVo;
import com.tapi.tcs.vc.baseinfo.vo.ZTreeDto;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.service.PubCodeManagerService;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.rolelist.vo.RoleListVo;
import com.tapi.tcs.vc.schema.model.VcConstantConfig;
import com.tapi.tcs.vc.schema.model.VcDocInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocPrtNoRule;
import com.tapi.tcs.vc.schema.model.VcDocRelArea;
import com.tapi.tcs.vc.schema.model.VcDocRelInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfoMng;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.webservice.DoucmentTypeService.Dto.RationCode;
import com.tapi.tcs.vc.webservice.DoucmentTypeService.client.DoucmentTypeServiceClient;

/**
 * 单证版本信息维护Action
 * <p>
 * Date: 2013-02-19
 * </p>
 * *
 * <p>
 * Description:
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * 单证版本信息维护control
 * 
 * @author wanghuajian
 * @version 1.0
 */
public class DocVersionInfoAction extends TFAction {
    /**
     * 
     */
    private static final long serialVersionUID = -2248031194541049439L;

    /**
     * 
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 
     */
    private VcDocVersionInfoService vcDocVersionInfoService;
    
    /**
     * 险类
     */
    private InsuKindService insuKindService;
    /**
     * 
     */
    private PubCodeManagerService pubCodeManagerService;
    
    /**
     *常量配置表
     */
    private VcConstantConfigService vcConstantConfigService;

    /**
     * 业务类型business、财务类型单证finance
     */
    private String docVersionTypeFlag;

    // 查询用单证版本信息
    private DocVersionInfoQueryVo queryDto;
    // 单证版本信息
    private VcDocVersionInfo docVersionInfoDto;
    private List vcDocVersionInfoList;
    private Long idVcDocVersionInfo;
    private String docVerCode;// 单证版本代码
    private String docVerName;// 单证版本名称
    private String status;// 单证版本状态
    private String docType;// 单证类型(0-单证，1-发票)
    private String docTypeCode;// 单证种类代码

    private String selectVcDocVersionInfoIds; // 列表勾选的记录ids

    private String selectRelInsuKindCodes; // 待关联的险种codes

    private String selectRelAreaCodes; // 待关联的地区codes

    /** selector 参数 */
    private String code;
    /** selector 参数 */
    private String name;
    List<VcDocVersionInfo> resultList;

    private List<Map<String, String>> mapList;

    /** 自动完成返回list */
    private List<CodeLabelDto> codeLabelList;
    // 关联险种信息
    private List<VcDocRelInsuKind> relInsuKindList;
    // 自定义关联险种信息
    private List<DocInsuKindVo> docInsuKindVoList;

    // 已关联险种信息
    private List<VcDocInsuKind> haveRelInsuKindList;
    // 未关联险种信息
    private List<VcDocInsuKind> noRelInsuKindList;
   // 某一险类（或全部）的险种信息
    private List<VcDocInsuKind> oneTypeInsuKindList;
    // 地区关联树形结构
    private List<ZTreeDto> relAreaZTree;

    // 新增保存时关联地区信息
    private List<VcDocRelArea> relAreaList;
    /** 查看详情时的地区信息 */
    private List<VcLevel> vcLevelList;

    // 单证印刷流水号规则
    private List<VcDocPrtNoRule> docPrtNoRuleList;
    
    // 单证管控信息规则
    private List<VcDocVersionInfoMng> docVersionInfoMngList;

    /** 单证类型选择查询结果 */
    private List<DocVerSimpleVo> docVers;

    /** json数据 */
    private String jsonData;
    
    /** json数据 */
    private String jsonMngDate;

    /** 上传的文件 */
    private File file;
    /** 文件名称 */
    private String fileFileName;
    /** 文件类型 */
    private String fileContentType;

    private String actionType;// 当前业务类型 [insert,view,update,delete] Vc查询正常核销表  VcAb查询非正常核销表

    private String resultMsg;
    /**
     * 是否发生错误
     */
    private boolean hasError;
    private List detileNormalVO;
    private String startNum;
    private String businessNo;
    public void setVcDocVersionInfoService(VcDocVersionInfoService vcDocVersionInfoService) {
        this.vcDocVersionInfoService = vcDocVersionInfoService;
    }
    
    
    /**
     * @param insuKindService the insuKindService to set
     */
    public void setInsuKindService(InsuKindService insuKindService) {
        this.insuKindService = insuKindService;
    }


    public void setPubCodeManagerService(PubCodeManagerService pubCodeManagerService) {
        this.pubCodeManagerService = pubCodeManagerService;
    }
    
    

    public void setVcConstantConfigService(VcConstantConfigService vcConstantConfigService) {
        this.vcConstantConfigService = vcConstantConfigService;
    }
    



	public String queryDocDetDocDetInquiry()throws Exception{
    	UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
    	 if (userInfo == null) {
             throw new Exception("登录超时，请重新登录！ ");
         }
    	 try {
			//直接调用查询非正常核销表或者调用正常核销表
    		 String bus=businessNo;  //保单号
    		 String document =docVerCode;  //单证类型
    		 String str=startNum;          //其实号段
    		 String status=actionType;      //单证状态
    		 boolean flag= false;
    		 if(actionType.equals("VcAb")){
    			 flag=false;
    		 }else{
    			 flag=true;
    		 }
    		 Page pageObj = vcDocVersionInfoService.queryDetail(bus, userInfo, document,str, flag,page,
     				rows);	
    		 if(pageObj!=null){
    			 detileNormalVO = (List<RoleListVo>) pageObj.getResult();
 		            // 总页数
 		        total = (int) pageObj.getTotalPageCount();
 		            // 总记录数
 		        records = (int) pageObj.getTotalCount();
    		 }
 		       return "success";
		} catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        }  catch (Exception e) {
        	this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
        	return NONE;
        }
    }
    	
    // 单证版本维护==================================================
    /**
     * 初始化进入新增单证版本页面
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String initAddVcDocVersionInfo() throws Exception {
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        if (userInfo == null) {
            throw new Exception("登录超时，请重新登录！ ");
        }
        this.docVersionInfoDto = new VcDocVersionInfo();
        docVersionInfoDto.setOrgName(userInfo.getComName());
        docVersionInfoDto.setCreatedUser(userInfo.getUserName());
        docVersionInfoMngList=this.getDocMngList(null);
        this.actionType = "insert";
        return "initAddVcDocVersionInfo";
    }

    /**
     * 文件上传处理
     * 
     * @param file
     *            待上传文件
     * @param fileContentType
     *            类型
     * @param realPath
     *            路径
     * @param fileFileName
     *            文件名
     * @param oldFileName
     *            旧文件名
     * @return
     */
    public Map<String, Object> dealFile(File file, String fileContentType, String realPath,
            String fileFileName, String oldFileName) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 处理文件过程是否有异常 yes-有异常 no-无异常
        boolean hasError = false;
        String errorMsg = "";
        if (file != null) {
            if (!fileContentType.equals("image/x-png") && !fileContentType.equals("image/pjpeg")
                    && !fileContentType.equals("image/jpeg") && !fileContentType.equals("image/png")) {
                hasError = true;
                errorMsg = "请上传jpg或png格式的图片!";
            }
            if (file.length() > SysConst.MB * 2) {
                hasError = true;
                errorMsg = "文件不可超过2M!";
            }
            File savefile = null;
            savefile = new File(new File(realPath), fileFileName);
            // if (!oldFileName.equals(fileFileName) && savefile.exists()) {
            if (savefile.exists()) {
                hasError = true;
                errorMsg = "文件已存在!";
            }
        }
        resultMap.put("hasError", hasError);
        resultMap.put("errorMsg", errorMsg);
        return resultMap;
    }

    /**
     * 保存新增单证类型
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String saveVcDocVersionInfo() throws Exception {
        logger.info("访问/baseinfojson/saveVcDocVersionInfo.do保存单证类型信息...");
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        if (userInfo == null) {
            throw new BusinessException("登录超时，请重新登录！ ");
        }
        docPrtNoRuleList = TFJSON.parseArray(jsonData, VcDocPrtNoRule.class);
        docVersionInfoMngList = TFJSON.parseArray(jsonMngDate, VcDocVersionInfoMng.class);
        docVersionInfoDto.setDocPrtNoRuleList(docPrtNoRuleList);
        docVersionInfoDto.setDocVersionInfoMngList(docVersionInfoMngList);

        String realPath = SysConst.DOC_MODE_SAVE_PATH; // 文件保存路径
        try {
            if (file == null) {
                throw new BusinessException("文件不能为空!");
            }
            if (!fileContentType.equals("image/x-png") && !fileContentType.equals("image/pjpeg")
                    && !fileContentType.equals("image/jpeg") && !fileContentType.equals("image/png")) {
                throw new BusinessException("请上传jpg或png格式的图片!");
            }
            if (file.length() > SysConst.MB * 2) {
                throw new BusinessException("文件不可超过2M!");
            }
            File savefile = new File(new File(realPath), fileFileName);
            if (savefile.exists()) {
                throw new BusinessException("文件已存在! ");
            }
            // 扫描件名称
            docVersionInfoDto.setFileName(fileFileName); // 扫描件路径
            docVersionInfoDto.setFilePath(realPath);
            vcDocVersionInfoService.createVcDocVersionInfo(docVersionInfoDto, userInfo, file);
            this.resultMsg = "保存成功!";
            this.actionType = "SaveAfter";
            this.hasError = false;
            try {
            	 String url=vcDocVersionInfoService.getUrladdress("productUrl", "productUrl");
            	 sendDoucmentType(docVersionInfoDto.getDocVerCode(),docVersionInfoDto.getDocVerName(),docVersionInfoDto.getIdVcDocVersionInfo().toString(),"1","I",url);
			} catch (Exception e) {
				logger.error("调用产品工厂接口失败"+e.getMessage());
			}
            return SUCCESS;
        } catch (Exception e) {
            docVersionInfoDto.setOrgName(userInfo.getComName());
            docVersionInfoDto.setCreatedUser(userInfo.getUserName());
            docVersionInfoMngList=this.getDocMngList(null);
            e.printStackTrace();
            this.resultMsg = "操作失败,失败原因:" + e.getMessage();
            this.actionType = "insert";
            this.hasError = true;
            return "fail";
        }
    }

    /**
     * 进入查询单证版本页面
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String initQueryVcDocVersionInfo() {
        return "initQueryVcDocVersionInfo";
    }

    /**
     * 查询单证版本
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String queryVcDocVersionInfoByPages() throws BusinessException {
        logger.info("访问/baseinfojson/queryVcDocVersionInfoByPages.do?ajax=true分页查询单证版本信息...");
        try {
            Page pageObj = vcDocVersionInfoService.queryVcDocVersionInfoByPages(this.getQueryDto(), page,
                    rows);

            // 设置相关数据
            vcDocVersionInfoList = pageObj.getResult();
            total = (int) pageObj.getTotalPageCount();
            records = (int) pageObj.getTotalCount();
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;

    }
    
    
    /**
     * 单证类型查询[单证明细查询用]
     * 
     * @author wanghuajian
     * @date 2014-03-17
     * 
     */

    public String queryDocForDocDetInquiry() throws BusinessException {
        logger.info("访问/baseinfojson/queryDocForDocDetInquiry.do?ajax=true单证类型查询[单证明细查询用]...");        
        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            Page pageObj = vcDocVersionInfoService.queryDocForDocDetInquiry(this.getQueryDto(), userInfo, page, rows);

            // 设置相关数据
            vcDocVersionInfoList = pageObj.getResult();
            total = (int) pageObj.getTotalPageCount();
            records = (int) pageObj.getTotalCount();
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;

    }

    /**
     * 编辑单证版本
     */
    public String prepareUpdateVcDocVersionInfo() throws BusinessException {
        logger.info("访问/baseinfo/prepareUpdateVcDocVersionInfo.do?ajax=true进入单证版本编辑页面...");
        try {
            this.docVersionInfoDto = vcDocVersionInfoService.getVcDocVersionInfo(this.idVcDocVersionInfo);
            docVersionInfoMngList=this.getDocMngList(this.docVersionInfoDto.getDocVerCode());
           
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        docPrtNoRuleList = docVersionInfoDto.getDocPrtNoRuleList();
        this.actionType = "update";
        return "prepareUpdateVcDocVersionInfo";
    }

    /**
     * 进入修改单证版本页面
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String updateVcDocVersionInfo() throws Exception {
        logger.info("访问/baseinfo/updateVcDocVersionInfo.do?ajax=true单证类型编辑保存...");
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        if (userInfo == null) {
            throw new BusinessException("登录超时，请重新登录！ ");
        }
        docPrtNoRuleList = TFJSON.parseArray(jsonData, VcDocPrtNoRule.class);
        docVersionInfoDto.setDocPrtNoRuleList(docPrtNoRuleList);
        docVersionInfoMngList = TFJSON.parseArray(jsonMngDate, VcDocVersionInfoMng.class);       
        docVersionInfoDto.setDocVersionInfoMngList(docVersionInfoMngList);
        try {
            if (file != null) {
                String realPath = SysConst.DOC_MODE_SAVE_PATH; // 文件保存路径
                if (!fileContentType.equals("image/x-png") && !fileContentType.equals("image/pjpeg")
                        && !fileContentType.equals("image/jpeg") && !fileContentType.equals("image/png")) {
                    throw new BusinessException("请上传jpg或png格式的图片!");
                }
                if (file.length() > SysConst.MB * 2) {
                    throw new BusinessException("文件不可超过2M!");
                }
                File savefile = new File(new File(realPath), fileFileName);
                if (savefile.exists()) {
                    throw new BusinessException("文件已存在! ");
                }
                // 扫描件名称
                docVersionInfoDto.setFileName(fileFileName); // 扫描件路径
                docVersionInfoDto.setFilePath(realPath);
            }
            vcDocVersionInfoService.updateVcDocVersionInfo(docVersionInfoDto, userInfo, file);
            this.resultMsg = "保存成功!";
            this.actionType = "SaveAfter";
            this.hasError = false;
            try {
            	//fyh
            	if(null!=docVersionInfoDto.getDocVerName()){
            		String url=vcDocVersionInfoService.getUrladdress("productUrl", "productUrl");
            		sendDoucmentType(docVersionInfoDto.getDocVerCode(),docVersionInfoDto.getDocVerName(),docVersionInfoDto.getIdVcDocVersionInfo().toString(),docVersionInfoDto.getStatus(),"U",url);
            	}
    		} catch (Exception e) {
    			logger.error("调用产品工厂接口失败"+e.getMessage());
    		}
            return SUCCESS;
        } catch (Exception e) {
            VcDocVersionInfo tempVo = vcDocVersionInfoService.getVcDocVersionInfo(docVersionInfoDto
                    .getIdVcDocVersionInfo());
            docVersionInfoDto.setDocVerCode(tempVo.getDocVerCode());
            docVersionInfoDto.setStatus(tempVo.getStatus());
            docVersionInfoDto.setOrgName(tempVo.getOrgName());
            docVersionInfoDto.setCreatedUser(tempVo.getCreatedUser());
            docVersionInfoMngList=this.getDocMngList(tempVo.getDocVerCode());
            e.printStackTrace();
            this.resultMsg = "操作失败,失败原因:" + e.getMessage();
            this.actionType = "update";
            this.hasError = true;
            return "fail";
        }

    }

    /**
     * 查看单证版本
     */
    public String viewVcDocVersionInfo() throws Exception {
        logger.info("访问/baseinfo/viewVcDocVersionInfo.do进入单证版本查看页面...");
        try {
            this.docVersionInfoDto = vcDocVersionInfoService.getVcDocVersionInfo(this.idVcDocVersionInfo);
            docVersionInfoMngList=this.getDocMngList(this.docVersionInfoDto.getDocVerCode());
            docPrtNoRuleList = docVersionInfoDto.getDocPrtNoRuleList();
            if (docPrtNoRuleList != null && docPrtNoRuleList.size() > 0) {
                for (VcDocPrtNoRule prtNoRule : docPrtNoRuleList) {
                    String cName = pubCodeManagerService
                            .getCodeCname("ItemType", prtNoRule.getItemTypeCode());
                    prtNoRule.setItemTypeCode(cName);
                }
            }
            this.docInsuKindVoList = vcDocVersionInfoService.queryRelInsuKindListByDocId(idVcDocVersionInfo);
            this.vcLevelList = vcDocVersionInfoService.queryRelAreaListByDocId(idVcDocVersionInfo);
            this.actionType = "view";
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return "viewVcDocVersionInfo";
    }

    /**
     * 获取单证印刷流水号规则jsonList
     */
    public String queryJsondocPrtNoRuleListByDocId() throws BusinessException {
        logger.info("访问/baseinfo/queryJsondocPrtNoRuleListByDocId.do获取单证印刷流水号规则jsonList...");
        try {
            this.docVersionInfoDto = vcDocVersionInfoService.getVcDocVersionInfo(this.idVcDocVersionInfo);
            docPrtNoRuleList = docVersionInfoDto.getDocPrtNoRuleList();
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }
   
    /**
     * 获取单证管控配置信息Liist
     */
    public List<VcDocVersionInfoMng> getDocMngList(String tempDocVerCode) throws BusinessException {
        try {
            List<VcDocVersionInfoMng> list = null;
            VcDocVersionInfoMng mngDto = null;
            VcConstantConfig tempVo = null;
            if (StringUtils.isNotBlank(tempDocVerCode)) {
                VcDocVersionInfoMng mngQueryDto = new VcDocVersionInfoMng();
                mngQueryDto.setDocVerCode(tempDocVerCode);
                list = vcDocVersionInfoService.queryDocMngList(mngQueryDto);
               
                if (list == null || list.size() <= 0) { 
                    list = new ArrayList<VcDocVersionInfoMng>();
                    for (int i = 1; i <= 6; i++) {
                        mngDto = new VcDocVersionInfoMng();
                        mngDto.setLevelNo(i);
                        mngDto.setLevelRemark(this.getLlevelRemark(i));
                        // 查询最大库存量
                        tempVo = vcConstantConfigService.getVcConstantConfig("maxStore", i);
                        if (tempVo != null) {
                            mngDto.setMaxStore(Integer.valueOf(tempVo.getConstantValue()));
                            mngDto.setMaxStoreC(Integer.valueOf(tempVo.getConstantValue()));
                        } else {
                            mngDto.setMaxStore(60);
                            mngDto.setMaxStoreC(60);
                        }
                        // 查询最大库存时间
                        tempVo = vcConstantConfigService.getVcConstantConfig("maxStoreTime", i);
                        if (tempVo != null) {
                            mngDto.setMaxStoreTime(Integer.valueOf(tempVo.getConstantValue()));
                            mngDto.setMaxStoreTimeC(Integer.valueOf(tempVo.getConstantValue()));
                        } else {
                            mngDto.setMaxStoreTime(30);
                            mngDto.setMaxStoreTimeC(30);
                        }
                        /** 页面标签Id */
                        mngDto.setTagName1("maxStore_" + i);
                        mngDto.setTagName2("maxStoreTime_" + i);
                        list.add(mngDto);
                    }
                }else{
                    for (VcDocVersionInfoMng mngObj : list) {
                        mngObj.setLevelRemark(this.getLlevelRemark(mngObj.getLevelNo()));
                        // 查询最大库存量
                        tempVo = vcConstantConfigService.getVcConstantConfig("maxStore", mngObj.getLevelNo());
                        if (tempVo != null) {                           
                            mngObj.setMaxStoreC(Integer.valueOf(tempVo.getConstantValue()));
                        } else {                           
                            mngObj.setMaxStoreC(60);
                        }
                        // 查询最大库存时间
                        tempVo = vcConstantConfigService.getVcConstantConfig("maxStoreTime", mngObj.getLevelNo());
                        if (tempVo != null) {                           
                            mngObj.setMaxStoreTimeC(Integer.valueOf(tempVo.getConstantValue()));
                        } else {                           
                            mngObj.setMaxStoreTimeC(30);
                        }
                        /** 页面标签Id */
                        mngObj.setTagName1("maxStore_" + mngObj.getLevelNo());
                        mngObj.setTagName2("maxStoreTime_" + mngObj.getLevelNo());
                    }  
                }
            } else {
                list = new ArrayList<VcDocVersionInfoMng>();              
                for (int i = 1; i <= 6; i++) {
                    mngDto = new VcDocVersionInfoMng();
                    mngDto.setLevelNo(i);
                    mngDto.setLevelRemark(this.getLlevelRemark(i));
                    // 查询最大库存量
                    tempVo = vcConstantConfigService.getVcConstantConfig("maxStore", i);
                    if (tempVo != null) {
                        mngDto.setMaxStore(Integer.valueOf(tempVo.getConstantValue()));
                        mngDto.setMaxStoreC(Integer.valueOf(tempVo.getConstantValue()));
                    } else {
                        mngDto.setMaxStore(60);
                        mngDto.setMaxStoreC(60);
                    }
                    // 查询最大库存时间
                    tempVo = vcConstantConfigService.getVcConstantConfig("maxStoreTime", i);
                    if (tempVo != null) {
                        mngDto.setMaxStoreTime(Integer.valueOf(tempVo.getConstantValue()));
                        mngDto.setMaxStoreTimeC(Integer.valueOf(tempVo.getConstantValue()));
                    } else {
                        mngDto.setMaxStoreTime(30);
                        mngDto.setMaxStoreTimeC(30);
                    }
                    /** 页面标签Id */
                    mngDto.setTagName1("maxStore_" + i);
                    mngDto.setTagName2("maxStoreTime_" + i);
                    list.add(mngDto);
                }
            }
            return list;
        } catch (BusinessException be) {
            throw be;
        }

    }
    
    private String getLlevelRemark(int levelNo) {
        String result = "";
        switch (levelNo) {
        case 1:
            result = "级别为一的机构";
            break;
        case 2:
            result = "级别为二的机构";
            break;
        case 3:
            result = "级别为三的机构";
            break;
        case 4:
            result = "级别为四的机构";
            break;
        case 5:
            result = "级别为五的机构";
            break;
        case 6:
            result = "全辖单证使用人";
        }
        return result;

    }

    /**
     * 删除单证版本
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String deleteVcDocVersionInfo() throws BusinessException {
        try{
        vcDocVersionInfoService.deleteVcDocVersionInfos(this.getSelectVcDocVersionInfoIds());
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return "deleteVcDocVersionInfo";
    }

    /**
     * 初始化关联险种
     * 
     * @author wanghuajian
     * @date 2013-03-28
     * 
     */
    public String initRelInsuKind() throws BusinessException {
        try{
        resultList = vcDocVersionInfoService.queryVcDocVersionInfos(this.getSelectVcDocVersionInfoIds());
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return "initRelInsuKind";
    }

    /**
     * 关联险种
     * 
     * @author wanghuajian
     * @date 2013-03-28
     * 
     */
    public String saveRelInsuKind() throws Exception {
        logger.info("访问/baseinfo/saveRelInsuKind.do险种关联...");
        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            
            vcDocVersionInfoService.saveRelInsuKind(this.getSelectVcDocVersionInfoIds(), this
                    .getSelectRelInsuKindCodes(), userInfo);
            this.resultMsg = "操作成功";
        } catch (BusinessException e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }

        /*
         * } catch (Exception e) { e.printStackTrace(); this.resultMsg = "操作失败"; }
         */

        return SUCCESS;
    }

    /**
     * 关联地区
     * 
     * @author wanghuajian
     * @date 2013-03-28
     * 
     */
    public String saveRelArea() throws Exception {
        logger.info("访问/baseinfo/saveRelArea.do地区关联...");
        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new Exception("登录超时，请重新登录！ ");
            }

            vcDocVersionInfoService.saveRelArea(this.getSelectVcDocVersionInfoIds(), this
                    .getSelectRelAreaCodes(), userInfo);
            this.resultMsg = "操作成功";
        } catch (BusinessException e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        /*
         * } catch (Exception e) { e.printStackTrace(); this.resultMsg = "操作失败"; }
         */

        return SUCCESS;
    }

    /**
     * 初始化关联地区
     * 
     * @author wanghuajian
     * @date 2013-03-28
     * 
     */
    public String initRelArea() throws BusinessException {
        try{
        resultList = vcDocVersionInfoService.queryVcDocVersionInfos(this.getSelectVcDocVersionInfoIds());
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return "initRelArea";
    }

    /**
     * 启用、停用
     * 
     * @author wanghuajian
     * @date 2013-03-29
     * 
     */
    public String updateVcDocVersionInfoStatus() throws BusinessException {
        logger.info("访问/baseinfo/updateVcDocVersionInfoStatus.do启用/停用...");
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        try {
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            this.resultMsg = vcDocVersionInfoService.updateVcDocVersionInfoStatus(this
                    .getSelectVcDocVersionInfoIds(), userInfo);
            
           /* try {
            	//fyh
            		String url=vcDocVersionInfoService.getUrladdress("productUrl", "productUrl");
            		sendDoucmentType(null,null,this.getSelectVcDocVersionInfoIds(),"0","D",url);
    		} catch (Exception e) {
    			logger.error("调用产品工厂接口失败"+e.getMessage());
    		}*/
            // this.resultMsg = "操作成功";
        } catch (BusinessException e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        /*
         * } catch (BusinessException e) { e.printStackTrace(); this.resultMsg = "操作失败"; }
         */

        return SUCCESS;
    }

    /**
     * 单证类型代码自动完成
     * 
     * @return
     */
    public String queryVcDocVerLabels() throws BusinessException {
        try {
            String codeValue = getRequest().getParameter("codeCode");
            codeLabelList = vcDocVersionInfoService.queryVcDocVersionInfoForAutoComplete(codeValue);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
    }

    /**
     * 单证类型代码自动完成 发票
     * 
     * @return
     */
    public String queryInvoiceLabels() throws BusinessException {
        try {
            String codeValue = getRequest().getParameter("codeCode");
            codeLabelList = vcDocVersionInfoService.queryInvoiceForAutoComplete(codeValue);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
    }
   
    /**
     * 单证类型自动完成【 进行征订的单证】
     * 
     * @return
     * @throws BusinessException
     * @author whj
     * @since 2014-02-25
     */
    public String queryOrderDocDropDownList() throws BusinessException {
        logger.info("访问/baseinfojson/queryOrderDocDropDownList.do...");
        try {
            // 获取用户机构代码
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            String comCode = userInfo.getComCode();
            List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
            // 输入的单证类型代码
            String docVerCode = getRequest().getParameter("codeCode");
            // 根据条件查询单证类型
            List<VcDocVersionInfo> docVersionInfoList = vcDocVersionInfoService.getOrderVersionInfoList(
                    comCode, docVerCode);
            if (docVersionInfoList != null && docVersionInfoList.size() > 0) {
                Map<String, String> map = null;
                for (VcDocVersionInfo vcDocVersionInfo : docVersionInfoList) {
                    map = new HashMap<String, String>();
                    map.put("lable", vcDocVersionInfo.getDocVerName());
                    map.put("value", vcDocVersionInfo.getDocVerCode());
                    mapList.add(map);
                }
            }
            this.mapList = mapList;
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
    }
    
    /**
     * 单证类型自动完成 业务类单证
     * 
     * @return
     * @throws BusinessException
     * @author chy
     */
    public String queryBizDocDropDownList() throws BusinessException {
        logger.info("访问/baseinfojson/queryBizDocDropDownList.do...");
        try {
            // 获取用户机构代码
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            String comCode = userInfo.getComCode();
            List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
            // 输入的单证类型代码
            String docVerCode = getRequest().getParameter("codeCode");
            // 根据条件查询单证类型
            List<VcDocVersionInfo> docVersionInfoList = vcDocVersionInfoService.getBizDocVersionInfoList(
                    comCode, docVerCode);
            if (docVersionInfoList != null && docVersionInfoList.size() > 0) {
                Map<String, String> map = null;
                for (VcDocVersionInfo vcDocVersionInfo : docVersionInfoList) {
                    map = new HashMap<String, String>();
                    map.put("lable", vcDocVersionInfo.getDocVerName());
                    map.put("value", vcDocVersionInfo.getDocVerCode());
                    mapList.add(map);
                }
            }
            this.mapList = mapList;
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
    }

    /**
     * 单证类型代码下拉框 自动完成组件（发票）
     * 
     * @author
     * @return
     */
    public String queryInvoiceDropDownList() throws BusinessException {
        try {
            mapList = new ArrayList<Map<String, String>>();
            // label
            Map<String, Object> parMap = new HashMap<String, Object>();
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            String comCode = userInfo.getComCode();
            String codeValue = getRequest().getParameter("codeCode");
            // 是否级联
            String trigger = getRequest().getParameter("trigger");
            // 适用地区
            if (StringUtils.isNotEmpty(comCode)) {
                parMap.put("orgCodeLocalAndUp", comCode);
            }
            parMap.put("status", "1");
            parMap.put("docType", "1");

            // List<VcDocVersionInfo> docVersionInfoList = vcDocVersionInfoService.getDocVersionInfoList(parMap);
            List<VcDocVersionInfo> docVersionInfoList = vcDocVersionInfoService.getJSONUserSend(parMap);

            for (Iterator iterator = docVersionInfoList.iterator(); iterator.hasNext();) {
                VcDocVersionInfo vcDocVersionInfo = (VcDocVersionInfo) iterator.next();
                Map<String, String> map = new HashMap<String, String>();
                map.put("label", vcDocVersionInfo.getDocVerName());
                map.put("value", vcDocVersionInfo.getDocVerCode());
                mapList.add(map);
            }
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
    }

    /**
     * 查询满足条件的单证类型(下拉列表/自动完成组建)
     * 
     * @author wanghuajian
     * @date 2013-04-09
     */
    @SuppressWarnings("rawtypes")
    public String queryJSONDocVersionInfoList() throws BusinessException {
        try {
            // 获取用户机构代码
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            String comCode = userInfo.getComCode();
            String codeValue = getRequest().getParameter("codeCode");
            String valueType = getRequest().getParameter("valueType");
            String typeCode = getRequest().getParameter("docTypeCode");
            String docTypeCode = getRequest().getParameter("docTypeCode");
            String isCurOrUpperOrgDoc = getRequest().getParameter("isCurOrUpperOrgDoc");//是否只查询当前机构及其上级机构的单证
            
            // 是否级联
            String trigger = getRequest().getParameter("trigger");
            // 不包含的单证种类codes
            String notContainActivateCard = getRequest().getParameter("notContainActivateCard");
            // 组件样式 select-下拉列表 auto-自动完成组件
            String style = getRequest().getParameter("style");
            List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
            String lableName = "label";
            if (StringUtils.isNotEmpty(style) && "auto".equals(style)) {
                lableName = "lable";
            }
            Map<String, Object> parMap = new HashMap<String, Object>();
            // 状态为1的险类
            parMap.put("status", "1");
            if (StringUtils.isNotEmpty(codeValue)) {
                parMap.put("docVerCode", codeValue);
            }
            // 单证种类code
            if (StringUtils.isNotEmpty(typeCode)) {
                parMap.put("docTypeCode", typeCode);
            }
            if (StringUtils.isNotEmpty(notContainActivateCard)
                    && "yes".equalsIgnoreCase(notContainActivateCard)) {
                List<String> notContainTypeCodeList = new ArrayList<String>();
                notContainTypeCodeList.add(BaseInfoConst.DOC_TYPE_ACTIVATE_CARD);
                parMap.put("notConstainTypeCodeArray", notContainTypeCodeList);
            }
            //add by chy 20130816
            if(StringUtils.isNotEmpty(docTypeCode)){
            	parMap.put("docTypeCode", docTypeCode);
            }
            
            if (StringUtils.isNotEmpty(isCurOrUpperOrgDoc) && "yes".equalsIgnoreCase(isCurOrUpperOrgDoc)) {               
                parMap.put("isCurOrUpperOrgDoc",comCode);
            }

            List<VcDocVersionInfo> docVersionInfoList = vcDocVersionInfoService.getDocVersionInfoList(parMap);

            if (docVersionInfoList != null && docVersionInfoList.size() > 0) {
                Map<String, String> map = null;
                for (Iterator item = docVersionInfoList.iterator(); item.hasNext();) {
                    VcDocVersionInfo docVersionInfo = (VcDocVersionInfo) item.next();
                    map = new HashMap<String, String>();
                    map.put(lableName, docVersionInfo.getDocVerName());
                    if (StringUtils.isNotEmpty(valueType) && "serialId".equals(valueType)) {
                        map.put("value", docVersionInfo.getIdVcDocVersionInfo().toString());
                    } else {
                        map.put("value", docVersionInfo.getDocVerCode());
                    }
                    // 有级联
                    if (!(StringUtils.isNotEmpty(trigger) && "no".equals(trigger))) {
                        map.put("name", docVersionInfo.getVcDocType().getDocTypeCode());
                    }

                    mapList.add(map);
                    map = null;
                }
            }

            this.mapList = mapList;

            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
    }

    public String initDocVerListWithCondition() {
        try {
            String insuType = getRequest().getParameter("insuType");
            String insuKind = getRequest().getParameter("insuKind");
            String docTypeCode = getRequest().getParameter("docTypeCode");
            String docType = getRequest().getParameter("docType");

            mapList = new ArrayList<Map<String, String>>();

            List<VcDocVersionInfo> docVersionInfoList = vcDocVersionInfoService.queryDocVersionInfo(insuType,
                    insuKind, docTypeCode, docType);

            for (Iterator iterator = docVersionInfoList.iterator(); iterator.hasNext();) {
                VcDocVersionInfo vcDocVersionInfo = (VcDocVersionInfo) iterator.next();
                Map<String, String> map = new HashMap<String, String>();
                map.put("label", vcDocVersionInfo.getDocVerName());
                map.put("value", vcDocVersionInfo.getIdVcDocVersionInfo().toString());
                mapList.add(map);
            }
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
    }

    /**
     * 单证类型选择 下拉组件 自定义jsp 查询
     * 
     * @return
     */
    public String queryDocVerForSelector() {
        try {

            String docVerCode = getRequest().getParameter("docVerCode");
            String docVerName = getRequest().getParameter("docVerName");

            DocVerSimpleVo docVerSimpleVo = new DocVerSimpleVo();
            docVerSimpleVo.setDocTypeCode(docTypeCode);
            docVerSimpleVo.setDocVerCode(docVerCode);
            docVerSimpleVo.setDocVerName(docVerName);
            Page returnPage = vcDocVersionInfoService.queryDocVerForSelector(docVerSimpleVo, page, rows);

            // 设置相关数据
            docVers = (List<DocVerSimpleVo>) returnPage.getResult();
            total = (int) returnPage.getTotalPageCount();
            records = (int) returnPage.getTotalCount();
            return SUCCESS;
        } catch (BusinessException e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
    }

    /**
     * 初始化单证类型选择 下拉组件 自定义jsp
     * 
     * @return
     */
    public String initdocVerForSelector() {
        return SUCCESS;
    }

    /******************************** 险种、地区关联相关查询begin ******************************************/
    /******************************** add by whj 2013-06-04 *****************************************/
    /**
     * 查询未关联险种
     * 
     * @return list
     * @author whj
     * @since 2013-06-04
     */
    public String queryAllNoRelInsuKind() throws BusinessException {
        noRelInsuKindList = vcDocVersionInfoService.queryRelInsuKindList(idVcDocVersionInfo, 1);
        return SUCCESS;
    }

    /**
     * 查询某单证已关联险种
     * 
     * @return list
     * @author whj
     * @since
     */
    public String queryHaveRelInsuKind() throws BusinessException {
        haveRelInsuKindList = vcDocVersionInfoService.queryRelInsuKindList(idVcDocVersionInfo, 0);
        return SUCCESS;
    }

    /**
     * 根据险类类查询关联险种
     * 
     * @return list
     * @author whj
     * @since 2013-09-13
     */
    public String queryRelInsuKindByInsuType() throws BusinessException {
        //获取险类
        String insuTypeCode = getRequest().getParameter("insuTypeCode");
        oneTypeInsuKindList = insuKindService.queryInsuKindListByInsuTypeCode(insuTypeCode);
        return SUCCESS;
    }
    
    /**
     * 查询地区关联树
     * 
     * @return list
     * @author whj
     * @since 2013-06-04
     */
    public String queryRelAreaZTree() throws BusinessException {
        // 显示到中支公司，级别为3
        relAreaZTree = vcDocVersionInfoService.queryRelAreaZTree(idVcDocVersionInfo, 3);
        return SUCCESS;
    }

    /**
     * 查询满足条件的单证类型(下拉列表/自动完成组建) 使用人单证发放功能
     * 
     * @author ljin
     * @date 2013-06-22
     */
    @SuppressWarnings("rawtypes")
    public String getJSONUserSend() throws BusinessException {
        String codeValue = getRequest().getParameter("codeCode");
        String valueType = getRequest().getParameter("valueType");
        String typeCode = getRequest().getParameter("docTypeCode");

        // 发票编号 1
        String docType = getRequest().getParameter("docType");

        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        String comCode = userInfo.getComCode();

        // 是否级联
        String trigger = getRequest().getParameter("trigger");
        // 不包含的单证种类codes
        String notContainActivateCard = getRequest().getParameter("notContainActivateCard");
        // 组件样式 select-下拉列表 auto-自动完成组件
        String style = getRequest().getParameter("style");
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        String lableName = "label";
        if (StringUtils.isNotEmpty(style) && "auto".equals(style)) {
            lableName = "lable";
        }
        Map<String, Object> parMap = new HashMap<String, Object>();
        // 状态为1的险类
        parMap.put("status", "1");
        if (StringUtils.isNotEmpty(codeValue)) {
            parMap.put("docVerCode", codeValue);
        }
        // 单证种类code
        if (StringUtils.isNotEmpty(typeCode)) {
            parMap.put("docTypeCode", typeCode);
        }
        if (StringUtils.isNotEmpty(notContainActivateCard) && "yes".equalsIgnoreCase(notContainActivateCard)) {
            List<String> notContainTypeCodeList = new ArrayList<String>();
            notContainTypeCodeList.add(BaseInfoConst.DOC_TYPE_ACTIVATE_CARD);
            parMap.put("notConstainTypeCodeArray", notContainTypeCodeList);
        }
        // 使用地区
        if (StringUtils.isNotEmpty(comCode)) {
            parMap.put("orgCodeLocalAndUp", comCode);
        }

        // 发票编号
        if (StringUtils.isNotEmpty(docType)) {
            parMap.put("docType", docType);
        }

        List<VcDocVersionInfo> docVersionInfoList = vcDocVersionInfoService.getJSONUserSend(parMap);

        if (docVersionInfoList != null && docVersionInfoList.size() > 0) {
            Map<String, String> map = null;
            for (Iterator item = docVersionInfoList.iterator(); item.hasNext();) {
                VcDocVersionInfo docVersionInfo = (VcDocVersionInfo) item.next();
                map = new HashMap<String, String>();
                map.put(lableName, docVersionInfo.getDocVerName());
                if (StringUtils.isNotEmpty(valueType) && "serialId".equals(valueType)) {
                    map.put("value", docVersionInfo.getIdVcDocVersionInfo().toString());
                } else {
                    map.put("value", docVersionInfo.getDocVerCode());
                }
                // 有级联
                if (!(StringUtils.isNotEmpty(trigger) && "no".equals(trigger))) {
                    map.put("name", docVersionInfo.getVcDocType().getDocTypeCode());
                }

                mapList.add(map);
                map = null;
            }
        }

        this.mapList = mapList;

        return SUCCESS;
    }
    public void sendDoucmentType(String docVerCode,String docVerName,String iddoc,String state,String flag,String url){
    	 RationCode rationCode=new RationCode();
         rationCode.setDocumentCodes(docVerCode);
         rationCode.setDocumentNames(docVerName);
         rationCode.setDocumentStates(state);
         rationCode.setIdVcDocversionInfos(iddoc);
         DoucmentTypeServiceClient doucmentTypeServiceClient= new DoucmentTypeServiceClient();
         doucmentTypeServiceClient.doucmentTypeService(flag, rationCode,url);
    } 
    
    /**
     * 单证类型代码自动完成(适用单证申领、回收申请)
     * 
     * @author ljin
     * @date 2013-06-26
     * @return
     */
    public String autoGetVcDocVerLabels() throws BusinessException {
        String codeValue = getRequest().getParameter("codeCode");
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        String orgCode = userInfo.getComCode();
        codeLabelList = vcDocVersionInfoService.autoGetVcDocVersionInfoForAutoComplete(codeValue, orgCode);
        return SUCCESS;
    }

    public List<VcDocInsuKind> getHaveRelInsuKindList() {
        return haveRelInsuKindList;
    }

    public void setHaveRelInsuKindList(List<VcDocInsuKind> haveRelInsuKindList) {
        this.haveRelInsuKindList = haveRelInsuKindList;
    }

    public List<VcDocInsuKind> getNoRelInsuKindList() {
        return noRelInsuKindList;
    }

    public void setNoRelInsuKindList(List<VcDocInsuKind> noRelInsuKindList) {
        this.noRelInsuKindList = noRelInsuKindList;
    }   

    /**
     * @return the oneTypeInsuKindList
     */
    public List<VcDocInsuKind> getOneTypeInsuKindList() {
        return oneTypeInsuKindList;
    }

    /**
     * @param oneTypeInsuKindList the oneTypeInsuKindList to set
     */
    public void setOneTypeInsuKindList(List<VcDocInsuKind> oneTypeInsuKindList) {
        this.oneTypeInsuKindList = oneTypeInsuKindList;
    }

    public List<ZTreeDto> getRelAreaZTree() {
        return relAreaZTree;
    }

    public void setRelAreaZTree(List<ZTreeDto> relAreaZTree) {
        this.relAreaZTree = relAreaZTree;
    }

    /******************************* 险种关联相关查询end ******************************************/

    public String getDocVersionTypeFlag() {
        return docVersionTypeFlag;
    }

    public void setDocVersionTypeFlag(String docVersionTypeFlag) {
        this.docVersionTypeFlag = docVersionTypeFlag;
    }

    public DocVersionInfoQueryVo getQueryDto() {
        return queryDto;
    }

    public void setQueryDto(DocVersionInfoQueryVo queryDto) {
        this.queryDto = queryDto;
    }

    public VcDocVersionInfo getDocVersionInfoDto() {
        return docVersionInfoDto;
    }

    public void setDocVersionInfoDto(VcDocVersionInfo docVersionInfoDto) {
        this.docVersionInfoDto = docVersionInfoDto;
    }

    public List getVcDocVersionInfoList() {
        return vcDocVersionInfoList;
    }

    public void setVcDocVersionInfoList(List vcDocVersionInfoList) {
        this.vcDocVersionInfoList = vcDocVersionInfoList;
    }

    public Long getIdVcDocVersionInfo() {
        return idVcDocVersionInfo;
    }

    public void setIdVcDocVersionInfo(Long idVcDocVersionInfo) {
        this.idVcDocVersionInfo = idVcDocVersionInfo;
    }

    public String getDocVerCode() {
        return docVerCode;
    }

    public void setDocVerCode(String docVerCode) {
        this.docVerCode = docVerCode;
    }

    public String getDocVerName() {
        return docVerName;
    }

    public void setDocVerName(String docVerName) {
        this.docVerName = docVerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocTypeCode() {
        return docTypeCode;
    }

    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode;
    }

    public String getSelectVcDocVersionInfoIds() {
        return selectVcDocVersionInfoIds;
    }

    public void setSelectVcDocVersionInfoIds(String selectVcDocVersionInfoIds) {
        this.selectVcDocVersionInfoIds = selectVcDocVersionInfoIds;
    }

    public String getSelectRelInsuKindCodes() {
        return selectRelInsuKindCodes;
    }

    public void setSelectRelInsuKindCodes(String selectRelInsuKindCodes) {
        this.selectRelInsuKindCodes = selectRelInsuKindCodes;
    }

    public String getSelectRelAreaCodes() {
        return selectRelAreaCodes;
    }

    public void setSelectRelAreaCodes(String selectRelAreaCodes) {
        this.selectRelAreaCodes = selectRelAreaCodes;
    }

    public List<VcDocVersionInfo> getResultList() {
        return resultList;
    }

    public void setResultList(List<VcDocVersionInfo> resultList) {
        this.resultList = resultList;
    }

    public List<VcDocRelInsuKind> getRelInsuKindList() {
        return relInsuKindList;
    }

    public void setRelInsuKindList(List<VcDocRelInsuKind> relInsuKindList) {
        this.relInsuKindList = relInsuKindList;
    }

    public List<DocInsuKindVo> getDocInsuKindVoList() {
        return docInsuKindVoList;
    }

    public void setDocInsuKindVoList(List<DocInsuKindVo> docInsuKindVoList) {
        this.docInsuKindVoList = docInsuKindVoList;
    }

    public List<VcDocRelArea> getRelAreaList() {
        return relAreaList;
    }

    public void setRelAreaList(List<VcDocRelArea> relAreaList) {
        this.relAreaList = relAreaList;
    }

    public List<VcLevel> getVcLevelList() {
        return vcLevelList;
    }

    public void setVcLevelList(List<VcLevel> vcLevelList) {
        this.vcLevelList = vcLevelList;
    }

    public List<VcDocPrtNoRule> getDocPrtNoRuleList() {
        return docPrtNoRuleList;
    }

    public void setDocPrtNoRuleList(List<VcDocPrtNoRule> docPrtNoRuleList) {
        this.docPrtNoRuleList = docPrtNoRuleList;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public List<Map<String, String>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, String>> mapList) {
        this.mapList = mapList;
    }

    public List<CodeLabelDto> getCodeLabelList() {
        return codeLabelList;
    }

    public void setCodeLabelList(List<CodeLabelDto> codeLabelList) {
        this.codeLabelList = codeLabelList;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public List<DocVerSimpleVo> getDocVers() {
        return docVers;
    }

    public void setDocVers(List<DocVerSimpleVo> docVers) {
        this.docVers = docVers;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the hasError
     */
    public boolean isHasError() {
        return hasError;
    }

    /**
     * @param hasError
     *            the hasError to set
     */
    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }


    public List<VcDocVersionInfoMng> getDocVersionInfoMngList() {
        return docVersionInfoMngList;
    }


    public void setDocVersionInfoMngList(List<VcDocVersionInfoMng> docVersionInfoMngList) {
        this.docVersionInfoMngList = docVersionInfoMngList;
    }


    public String getJsonMngDate() {
        return jsonMngDate;
    }


    public void setJsonMngDate(String jsonMngDate) {
        this.jsonMngDate = jsonMngDate;
    }


	public List getDetileNormalVO() {
		return detileNormalVO;
	}


	public void setDetileNormalVO(List detileNormalVO) {
		this.detileNormalVO = detileNormalVO;
	}


    public String getStartNum() {
		return startNum;
	}


	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}


	public String getBusinessNo() {
		return businessNo;
	}


	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}


    
}