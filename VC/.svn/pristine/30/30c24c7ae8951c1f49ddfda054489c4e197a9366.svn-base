/**
 * 
 */
package com.tapi.tcs.vc.store.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;

import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.baseinfo.service.VcDocVersionInfoService;
import com.tapi.tcs.vc.baseinfo.service.VcLevelService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.service.PubCodeManagerService;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.ExcelExportHandler;
import com.tapi.tcs.vc.schema.model.VcApply;
import com.tapi.tcs.vc.schema.model.VcApplyDet;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.service.AllotService;
import com.tapi.tcs.vc.store.service.ApplyService;
import com.tapi.tcs.vc.store.vo.QueryCondition;
import com.tapi.tcs.vc.store.vo.StoreStatusValues;

/**
 * 单证申领Action入口
 * 
 * @author hanmiao.diao
 * 
 */
public class ApplyAction extends TFAction {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 546999894140413537L;
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(ApplyAction.class);

    /**
     * jsonStr
     */
    private String jsonStr;

    /**
     * 分页查询结果
     */
    @SuppressWarnings("rawtypes")
    private List resultList;
    
    /**
     * 单证申领对象
     */
    private VcApply vcApply;
    
    private VcApplyDet vcApplyDet;
    /**
     * 勾选删除的id
     */
    private String deleteList;
    /**
     * 勾选提交的id
     */
    private String submitList;

    /**
     * 申领单明细集合
     */
    @SuppressWarnings("rawtypes")
    private List vcApplyDetLst;

    /**
     * 历史审批集合
     */
    @SuppressWarnings("rawtypes")
    private List approveList;
    private String jsonData;
    // ////////////////////////////////////
    private ApplyService applyService;
    private AllotService allotService;
    private VcLevelService vcLevelService;

    private VcDocVersionInfoService vcDocVersionInfoService;

    private PubCodeManagerService pubCodeManagerService;

    // ////////////////////////////////////
    
    @JSON(serialize = false)
    public String getJsonData() {
  		return jsonData;
  	}
  	public AllotService getAllotService() {
		return allotService;
	}

	public void setAllotService(AllotService allotService) {
		this.allotService = allotService;
	}

	public void setJsonData(String jsonData) {
  		this.jsonData = jsonData;
  	}
    @SuppressWarnings("rawtypes")
    public List getApproveList() {
        return approveList;
    }

	@SuppressWarnings("rawtypes")
    public void setApproveList(List approveList) {
        this.approveList = approveList;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    @SuppressWarnings("rawtypes")
    public List getResultList() {
        return resultList;
    }

    @SuppressWarnings("rawtypes")
    public void setResultList(List resultList) {
        this.resultList = resultList;
    }

    public VcApplyDet getVcApplyDet() {
		return vcApplyDet;
	}

	public void setVcApplyDet(VcApplyDet vcApplyDet) {
		this.vcApplyDet = vcApplyDet;
	}

	public VcApply getVcApply() {
        return vcApply;
    }

    public void setVcApply(VcApply vcApply) {
        this.vcApply = vcApply;
    }

    public String getDeleteList() {
        return deleteList;
    }

    public void setDeleteList(String deleteList) {
        this.deleteList = deleteList;
    }

    public String getSubmitList() {
        return submitList;
    }

    public void setSubmitList(String submitList) {
        this.submitList = submitList;
    }

    @SuppressWarnings("rawtypes")
    public List getVcApplyDetLst() {
        return vcApplyDetLst;
    }

    @SuppressWarnings("rawtypes")
    public void setVcApplyDetLst(List vcApplyDetLst) {
        this.vcApplyDetLst = vcApplyDetLst;
    }

    // ////////////////////////////////////
    @JSON(serialize = false)
    public ApplyService getApplyService() {
        return applyService;
    }

    public void setApplyService(ApplyService applyService) {
        this.applyService = applyService;
    }

    @JSON(serialize = false)
    public VcLevelService getVcLevelService() {
        return vcLevelService;
    }

    public void setVcLevelService(VcLevelService vcLevelService) {
        this.vcLevelService = vcLevelService;
    }

    @JSON(serialize = false)
    public VcDocVersionInfoService getVcDocVersionInfoService() {
        return vcDocVersionInfoService;
    }

    public void setVcDocVersionInfoService(VcDocVersionInfoService vcDocVersionInfoService) {
        this.vcDocVersionInfoService = vcDocVersionInfoService;
    }

    @JSON(serialize = false)
    public PubCodeManagerService getPubCodeManagerService() {
        return pubCodeManagerService;
    }

    public void setPubCodeManagerService(PubCodeManagerService pubCodeManagerService) {
        this.pubCodeManagerService = pubCodeManagerService;
    }

    // ////////////////////////////////////
    /**
     * excel导出
     * 
     * @return page
     * @throws BusinessException
     *             异常
     */
	public String exportList()throws Exception{
		String strid = getRequest().getParameter("id");
		logger.info("访问/apply/exportList.do?ajax=true导出excel...");
		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        if (userInfo == null) {
            throw new BusinessException("登录超时，请重新登录！ ");
        }
		try {
			  if (StringUtils.isNotEmpty(strid)) {
		            Long id = Long.valueOf(strid);
			  VcApply vcApply = applyService.getVcApply(Long.valueOf(id));
			  if(vcApply==null || vcApply.equals("")){
	            	throw new BusinessException("没有查询到符合条件的数据！");
	            }//申领机构
			     vcApply.setApplyOrgName(vcLevelService.getUnitNameByUnitCode(vcApply.getApplyOrgCode()));
			     //申领操作人名称
			     vcApply.setApplyOprName(vcLevelService.getUnitNameByUnitCode(vcApply.getApplyOprCode()));
			     //发放机构名称
			     vcApply.setProvideOrgName(vcLevelService.getUnitNameByUnitCode(vcApply.getProvideOrgCode()));
			     
			     this.vcApply = vcApply;
				 List<VcApplyDet> detList = applyService.getApplyDetListByVcApplyId(Long.valueOf(id));
				 for (Iterator it = detList.iterator(); it.hasNext();) {
					 VcApplyDet vcApplyDet = (VcApplyDet) it.next();
					 vcApplyDet.setApplyNum(vcApplyDet.getApplyNum());//申请数量
					 vcApplyDet.setDocVerCode(vcApplyDet.getDocVerCode());//单证代码
					 vcApplyDet.setDocVerCodeName(allotService.getUnitNameByUnitCode(vcApplyDet.getDocVerCode()));
					 this.vcApplyDet=vcApplyDet;
					}
				 ExcelExportHandler excelExportHandler = new ExcelExportHandler();
	             //获取临时文件存放目录
	             String dirPath = excelExportHandler.getDirPath1();
	             String date = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
	             // String fileName = "单证角色-"+date+".xls";
	             String fileName = "单证申领-"+date+".xls";
	             
	             String[] title = {"单证名称","需求原因","需求数量","实发数量（由发放单位填写）","起讫号（由发放单位填写）","备注"};
		            //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 END
		            int size = detList.size();
		            String[][] values = new String[size][];
		            for(int i=0;i<size;i++){
		            	VcApplyDet vcApplyDet = detList.get(i);
						String[] arr = new String[6];
						arr[0]=vcApplyDet.getDocVerCodeName();
						arr[1]=vcApply.getApplyReason();
						arr[2]=vcApplyDet.getApplyNum().toString();
						values[i] = arr;
					}
		            excelExportHandler.createExcel(dirPath+fileName, title, values,vcApply);
				//返回值：文件存放目录==文件名
				jsonData = dirPath +"=="+ fileName;
			  }
	     } catch (Exception e) {
			e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
		}
		return "success";
	}
    
    
    /////////////////////////////////////////
    /**
     * 请求申领查询页面
     * 
     * @return page
     * @throws BusinessException
     *             异常
     */
    public String viewApplyQuery() throws BusinessException {
        Date sysdate = new Date();
        Date twoMonthAgo = DateUtils.addDay(sysdate, -60);

        getRequest().setAttribute("initStartDate", twoMonthAgo);
        getRequest().setAttribute("initEndDate", sysdate);
        return SUCCESS;
    }

    /**
     * 请求申领新增页面
     * 
     * @return page
     * @throws BusinessException
     *             异常
     */
    public String viewApplyNew() throws BusinessException {
        // 初始化新增页面的一些数据
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        String comCode = userInfo.getComCode();
        String userCode = userInfo.getUserCode();
        String userName = userInfo.getUserName();
        String comName = userInfo.getComName();

        VcApply pageApply = new VcApply();
        Date sysdate = new Date();
        pageApply.setApplyOrgCode(comCode);
        pageApply.setApplyOrgName(comName);
        pageApply.setApplyOprCode(userCode);
        pageApply.setApplyOprName(userName);

        try {
            String provideOrgCode = vcLevelService.getUpperOrgCode(userCode);
            pageApply.setProvideOrgCode(provideOrgCode);
            pageApply.setProvideOrgName(vcLevelService.getUnitNameByUnitCode(provideOrgCode));
            pageApply.setApplyTime(sysdate);
            this.vcApply = pageApply;
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
     * 请求申领编辑页面
     * 
     * @return page
     * @throws BusinessException
     *             异常
     */
    @SuppressWarnings("rawtypes")
    public String viewApplyEdit() throws BusinessException {
        String strid = getRequest().getParameter("id");
        // 初始化新增页面的一些数据
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        String comCode = userInfo.getComCode();
         try{
        if (StringUtils.isNotEmpty(strid)) {
            Long id = Long.valueOf(strid);

            // 加载代码对应的名称
            vcApply = applyService.getVcApply(id);

            vcApply.setApplyOrgName(vcLevelService.getUnitNameByUnitCode(vcApply.getApplyOrgCode()));
            vcApply.setApplyOprName(vcLevelService.getUnitNameByUnitCode(vcApply.getApplyOprCode()));
            vcApply.setProvideOrgName(vcLevelService.getUnitNameByUnitCode(vcApply.getProvideOrgCode()));

            // 查询申领表
            List<VcApplyDet> vcApplyDetList = applyService.getApplyDetListByVcApplyId(Long.valueOf(id));

            for (Iterator it = vcApplyDetList.iterator(); it.hasNext();) {
                VcApplyDet vcApplyDet = (VcApplyDet) it.next();
                Long num = applyService.getValidStorageNum(comCode, vcApplyDet.getDocVerCode());
                vcApplyDet.setValidNum(String.valueOf(num));
            }

            this.jsonStr = TFJSON.toJSONString(vcApplyDetList);
        }
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
     * 请求申领详情页面
     * 
     * @return page
     * @throws BusinessException
     *             异常
     */
    public String viewApplyView() throws BusinessException {
        String strid = getRequest().getParameter("id");
        try{
        if (StringUtils.isNotEmpty(strid)) {
            Long id = Long.valueOf(strid);

            // 加载代码对应的名称
            this.vcApply = applyService.getVcApply(id);
            this.vcApply.setApplyOrgName(vcLevelService.getUnitNameByUnitCode(vcApply.getApplyOrgCode()));
            this.vcApply.setApplyOprName(vcLevelService.getUnitNameByUnitCode(vcApply.getApplyOprCode()));
            this.vcApply.setProvideOrgName(vcLevelService.getUnitNameByUnitCode(vcApply.getProvideOrgCode()));

            // 查询申领表
            List<VcApplyDet> vcApplyDetList = applyService.getApplyDetListByVcApplyId(Long.valueOf(id));
  
            // 有效库存显示
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            String orgCode = userInfo.getComCode();
            List<VcApplyDet> vcApplyDet=new ArrayList<VcApplyDet>();
            for(VcApplyDet det:vcApplyDetList){
            	String docVerCode=det.getDocVerCode();
            	long  validNum=0l;
            	try {
            		  validNum=applyService.localValidStorageNum(orgCode, docVerCode); 
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("有效库存查询失败！");
				}
            	det.setValidNum(String.valueOf(validNum));
            }
            this.jsonStr = TFJSON.toJSONString(vcApplyDetList);
            this.approveList = applyService.getVcApprove(id, "A");
        }
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
     * 暂存,提交申领信息入口
     * 
     * @return 操作结果
     * @throws BusinessException
     *             异常
     */
    @SuppressWarnings("rawtypes")
    public String saveApply() throws BusinessException {
        if (logger.isInfoEnabled()) {
            logger.info("saveApply() - start"); //$NON-NLS-1$
        }
        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            String userCode = userInfo.getUserCode();

            VcApply apply = (VcApply) TFJSON.parseObject(jsonStr, VcApply.class);
            apply.setCreatedBy(userCode);
            apply.setUpdatedBy(userCode);

            // 校验录入的单证类型是否存在
            List<VcApplyDet> list = apply.getApplyDetList();
            for (Iterator it = list.iterator(); it.hasNext();) {
                VcApplyDet vcApplyDet = (VcApplyDet) it.next();
                String docVerCode = vcApplyDet.getDocVerCode();

                // 判断docVerCode是否存在
                boolean isExist = vcDocVersionInfoService.isExistValid(docVerCode);
                if (!isExist) {
                    this.jsonStr = "单证类型[" + docVerCode + "]不存在 ";
                    return SUCCESS;
                }
            }
            logger.info(" apply is null ? " + (apply == null));
            this.jsonStr = applyService.saveApply(apply);
        }catch(BusinessException e){
    	    this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
     	   return NONE;
          }catch(Exception e){
     	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
     	   return NONE;
          }

        if (logger.isInfoEnabled()) {
            logger.info("saveApply() - end"); //$NON-NLS-1$
        }

        return SUCCESS;
    }

    /**
     * 分页查询申领信息
     * 
     * @return 查询结果
     * @throws BusinessException
     *             异常
     */
    @SuppressWarnings("rawtypes")
    public String queryApplyListByPages() throws BusinessException {
        // 查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        // 获取登录人信息，包括机构等信息，加入查询条件
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        String comCode = userInfo.getComCode();
        // String userCode = userInfo.getUserCode();
        // String userName = userInfo.getUserName();

        // 页面上获取的查询条件
        QueryCondition qc = TFJSON.parseObject(jsonStr, QueryCondition.class);
        try{
        params.put("applyCode", qc.getQueryCode());
        params.put("applyStatus", qc.getQueryStatus());
        params.put("startDate", qc.getStartDate());
        params.put("endDate", qc.getEndDate());
        params.put("applyOrgCode", comCode);

        Page pageObj = applyService.queryApplyListByPages(params, page, rows);

        List pageResult = pageObj.getResult();
        for (Iterator it = pageResult.iterator(); it.hasNext();) {
            VcApply vcApply = (VcApply) it.next();
            vcApply.setApplyOprName(vcLevelService.getUnitNameByUnitCode(vcApply.getApplyOprCode()));
            vcApply.setApplyOrgName(vcLevelService.getUnitNameByUnitCode(vcApply.getApplyOrgCode()));
            vcApply.setProvideOrgName(vcLevelService.getUnitNameByUnitCode(vcApply.getProvideOrgCode()));
            vcApply.setProvideOprName(vcLevelService.getUnitNameByUnitCode(vcApply.getProvideOprCode()));
            vcApply.setApplyStatusZh(pubCodeManagerService.getCodeCname("ApplyStatus", vcApply.getApplyStatus()));
        }

        this.resultList = pageResult;
        total = (int) pageObj.getTotalPageCount();
        records = (int) pageObj.getTotalCount();
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
     * 分页查询有效库存
     * 
     * @return
     * @throws BusinessException
     */
    @SuppressWarnings("rawtypes")
    public String queryValidStorageListByPages() throws BusinessException {
        // 查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        // 获取登录人信息，包括机构等信息，加入查询条件
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        String comCode = userInfo.getComCode();
        // 当前人的机构在这获取并设置查询条件

        // 页面上获取的查询条件
        QueryCondition qc = TFJSON.parseObject(jsonStr, QueryCondition.class);
        try{
        params.put("docVerCode", qc.getDocVerCode());
        params.put("validOrg", qc.getValidOrg());
        params.put("orgCode", comCode);
        //遗失的时候根据状态查询
        if(StringUtils.isNotEmpty(qc.getValidStatus())){
        	params.put("status", qc.getValidStatus());
        }
       if (StringUtils.isNotEmpty(qc.getValidQueryType()) && "3".equals(qc.getValidQueryType())){
          //3-查询当前机构下的库存和可使用表
           qc.setValidOrg(comCode); 
           params.put("validOrg", comCode);
       }

        //modify by chy 20130918 begin
        /*Page pageObj = applyService.queryValidStorageListByPages(params, page, rows);

        List pageList = pageObj.getResult();

            for (Iterator it = pageList.iterator(); it.hasNext();) {
                VcStorage vcStorage = (VcStorage) it.next();
                vcStorage.setDocVerName(vcDocVersionInfoService.getVersionName(vcStorage.getDocVerCode()));
                vcStorage.setDocStatusZh(pubCodeManagerService.getCodeCname("DocStatus", vcStorage.getDocStatus()));
                vcStorage.setOrgName(vcLevelService.getUnitNameByUnitCode(vcStorage.getOrgCode()));
            }*/
        Page pageObj = null;
        List pageList = null;
        //如果机构代码不为空，则调用新方法递归往下查询库存及可使用表
        //如果为空查询非正常核销表状态为C1状态的
        String doc=qc.getDocVerCode().substring(0, 2);//截取单证类型代码前两位看是不是发票
        if(doc.equals("ZP")){
        	pageObj=applyService.queryAbNormalList(params, page, rows);
    		List resultList = pageObj.getResult();
    		pageList = new ArrayList();
    		for(int i=0;i<resultList.size();i++){
    			Object[] obj = (Object[])resultList.get(i);
    			VcStorage vcStorage = new VcStorage();
    			vcStorage.setId(((BigDecimal)obj[0]).longValue());
    			vcStorage.setDocVerCode((String)obj[1]);
    			vcStorage.setPressBatchNo((String)obj[2]);
    			vcStorage.setOrgCode((String)obj[6]);
    			vcStorage.setDocStatus((String)obj[5]);
        		vcStorage.setStartNum((String)obj[3]);
        		vcStorage.setEndNum((String)obj[3]);
        		vcStorage.setDocNum(Long.parseLong("1"));
        		vcStorage.setDeadline(null);
        		vcStorage.setDocVerName(vcDocVersionInfoService.getVersionName(vcStorage.getDocVerCode()));
                vcStorage.setDocStatusZh(pubCodeManagerService.getCodeCname("DocStatus", vcStorage.getDocStatus()));
                vcStorage.setOrgName(vcLevelService.getUnitNameByUnitCode(vcStorage.getOrgCode()));
                pageList.add(vcStorage);
    		}
        }else{

      	  if(StringUtils.isNotEmpty(qc.getValidOrg())){
        		pageObj = applyService.queryValidStorageListByPagesNew(params, page, rows);	
        		List resultList = pageObj.getResult();
            	pageList = new ArrayList();
            	for(int i=0;i<resultList.size();i++){
            		Object[] obj = (Object[])resultList.get(i);
            		VcStorage vcStorage = new VcStorage();
            		vcStorage.setId(((BigDecimal)obj[0]).longValue());
            		vcStorage.setDocVerCode((String)obj[1]);
            		vcStorage.setPressBatchNo((String)obj[2]);
            		vcStorage.setOrgCode((String)obj[3]);
            		vcStorage.setDocStatus((String)obj[4]);
            		vcStorage.setStartNum((String)obj[5]);
            		vcStorage.setEndNum((String)obj[6]);
            		vcStorage.setDocNum(((BigDecimal)obj[7]).longValue());
            		vcStorage.setDeadline((Date)obj[8]);
            		vcStorage.setDocVerName(vcDocVersionInfoService.getVersionName(vcStorage.getDocVerCode()));
                  vcStorage.setDocStatusZh(pubCodeManagerService.getCodeCname("DocStatus", vcStorage.getDocStatus()));
                  vcStorage.setOrgName(vcLevelService.getUnitNameByUnitCode(vcStorage.getOrgCode()));
            		pageList.add(vcStorage);
            	}
        	
        }else{
            //单证出入库查询时用
            //qc.getValidDocStatus不为空时查询指定状态的库存，否则查询S1、S2、S3的库存
            params.put("validDocStatus", qc.getValidDocStatus());
        	pageObj = applyService.queryValidStorageListByPages(params, page, rows);
            pageList = pageObj.getResult();
            for (Iterator it = pageList.iterator(); it.hasNext();) {
                VcStorage vcStorage = (VcStorage) it.next();
                vcStorage.setDocVerName(vcDocVersionInfoService.getVersionName(vcStorage.getDocVerCode()));
                vcStorage.setDocStatusZh(pubCodeManagerService.getCodeCname("DocStatus", vcStorage.getDocStatus()));
                vcStorage.setOrgName(vcLevelService.getUnitNameByUnitCode(vcStorage.getOrgCode()));
            }
        }
        }
        //modify by chy 20130918 end

        this.resultList = pageList;
        total = (int) pageObj.getTotalPageCount();
        records = (int) pageObj.getTotalCount();
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
     * 查询可使用列表
     * @return
     * @throws BusinessException
     */
    public String queryAvailableListByPages() throws BusinessException {
    	// 查询条件
        Map<String, Object> params = new HashMap<String, Object>();
    	// 页面上获取的查询条件
    	QueryCondition qc = TFJSON.parseObject(jsonStr, QueryCondition.class);
    	try{
    		params.put("docVerCode", qc.getDocVerCode());
            params.put("orgCode", qc.getValidOrg());
            params.put("takerCode", qc.getTakerCode());
            
            Page pageObj = applyService.queryAvailableListByPages(params, page, rows);
            if(pageObj!=null){
	        	List resultList = pageObj.getResult();
	        	List pageList = new ArrayList();
	        	for(int i=0;i<resultList.size();i++){
	        		Object[] obj = (Object[])resultList.get(i);
	        		VcStorage vcStorage = new VcStorage();
	        		vcStorage.setId(((BigDecimal)obj[0]).longValue());
	        		vcStorage.setDocVerCode((String)obj[1]);
	        		vcStorage.setPressBatchNo((String)obj[2]);
	        		vcStorage.setOrgCode((String)obj[3]);
	        		vcStorage.setDocStatus((String)obj[4]);
	        		vcStorage.setStartNum((String)obj[5]);
	        		vcStorage.setEndNum((String)obj[6]);
	        		vcStorage.setDocNum(((BigDecimal)obj[7]).longValue());
	        		//MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 BEGIN
	        		vcStorage.setDeadline(((Date)obj[8]));
	        		//MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 END
	        		vcStorage.setDocVerName(vcDocVersionInfoService.getVersionName(vcStorage.getDocVerCode()));
	                vcStorage.setDocStatusZh(pubCodeManagerService.getCodeCname("DocStatus", vcStorage.getDocStatus()));
	                vcStorage.setOrgName(vcLevelService.getUnitNameByUnitCode(vcStorage.getOrgCode()));
	        		pageList.add(vcStorage);
	        	}
	        	this.resultList = pageList;
	            total = (int) pageObj.getTotalPageCount();
	            records = (int) pageObj.getTotalCount();
            }
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
    	return SUCCESS;
    }

    /**
     * 逻辑删除申领信息,改变VC_APPLY中状态字段
     * 
     * @return 操作结果
     * @throws BusinessException
     *             异常
     */
    public String deleteApplyList() throws BusinessException {

        String deleteList = this.deleteList;

        String[] idArray = deleteList.split(",");

        String currentUser = "DEMO";

        try {
            this.jsonStr = applyService.deleteByLogic(idArray, currentUser);
        } 
    	catch(BusinessException e){
    	    this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
       	   return NONE;
          }catch(Exception e){
       	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
        	   return NONE;
          }
        return SUCCESS;
    }

    /**
     * 提交勾选中的新建状态的申领单
     * 
     * @return 操作结果
     * @throws BusinessException
     *             异常
     */
    @SuppressWarnings( { "rawtypes", "unchecked" })
    public String submitApplyList() throws BusinessException {
        String localSubmitList = this.submitList;

        String[] idArray = localSubmitList.split(",");

        StringBuffer sb = new StringBuffer();
       try{
        for (int i = 0; i < idArray.length; i++) {
            Long id = Long.valueOf(idArray[i]);
            VcApply v = new VcApply();
            VcApply v1 = applyService.getVcApply(id);
            List list = applyService.getCloneApplyDetListByVcApplyId(id);
            Beans.copy(v, v1);
            v.setApplyDetList(list);

            v.setApplyStatus(StoreStatusValues.VC_APPLY_STATUS_APPLY_AWAITPROVIDE);
            try {
                applyService.saveApply(v);
                sb.append("申领单号[" + v.getApplyCode() + "]提交成功");
            } catch (Exception e) {
                e.printStackTrace();
                sb.append("申领单号[" + v.getApplyCode() + "]提交失败,失败原因:" + e.getMessage());
            }
        }

        this.jsonStr = sb.toString();
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
     * 获取申领明细
     * 
     * @return json对象
     * @throws BusinessException
     *             异常
     */
    @SuppressWarnings("rawtypes")
    public String queryApplyDetList() throws BusinessException {
        String id = getRequest().getParameter("id");
        try{
        if (StringUtils.isNotEmpty(id)) {
            // 查询申领表
            vcApplyDetLst = applyService.getApplyDetListByVcApplyId(Long.valueOf(id));
            for (Iterator it = vcApplyDetLst.iterator(); it.hasNext();) {
                VcApplyDet det = (VcApplyDet) it.next();
                det.setValidNum("");
                det.setVcApply(null);
            }
        }}catch(BusinessException e){
     	      this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
   	   	   return NONE;
   	      }catch(Exception e){
   	   	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
   	    	   return NONE;
   	      }
        
        return SUCCESS;
    }

    /**
     * 获取当前机构下的有效库存
     * 
     * @return 有效库存
     * @throws BusinessException
     *             异常
     */
    public String queryValidStorageNum() throws BusinessException {
        // 获取当前用户机构 000000
        // String orgCode = StoreStatusValues.user.get("userOrg");
    	try{
        String docVerCode = this.jsonStr;
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
       
        Long num = applyService.getValidStorageNum(userInfo.getComCode(), docVerCode);

        this.jsonStr = String.valueOf(num.longValue());
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
     * 获取当前机构下的有效库存
     * 
     * @return 有效库存
     * @throws BusinessException
     *             异常
     */
    public String queryValidStorageNum2() throws BusinessException {
        // 获取当前用户机构 000000
        // String orgCode = StoreStatusValues.user.get("userOrg");
		String docVerCode = getRequest().getParameter("docVerCode");
		String provideOrgCode = getRequest().getParameter("provideOrgCode");
    	try{
        Long num = applyService.getValidStorageNum(provideOrgCode, docVerCode);

        this.jsonStr = String.valueOf(num.longValue());
    	}catch(BusinessException e){
   	      this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
  	   	   return NONE;
  	      }catch(Exception e){
  	   	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
  	    	   return NONE;
  	      }
        return SUCCESS;
    }

}
