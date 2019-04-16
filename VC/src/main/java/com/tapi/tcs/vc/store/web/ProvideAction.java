/**
 * 
 */
package com.tapi.tcs.vc.store.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.json.annotations.JSON;

import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.baseinfo.service.VcLevelService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.service.PubCodeManagerService;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcApply;
import com.tapi.tcs.vc.schema.model.VcApplyDet;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.store.service.ApplyService;
import com.tapi.tcs.vc.store.service.ProvideService;
import com.tapi.tcs.vc.store.vo.QueryCondition;

/**
 * 申领单发放Action入口
 * 
 * @author hanmiao.diao
 * 
 */
public class ProvideAction extends TFAction {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -38087685380091880L;

    // ////////////////////////////////////////////////////////////////

    private ProvideService provideService;

    private ApplyService applyService;

    private VcLevelService vcLevelService;

    private PubCodeManagerService pubCodeManagerService;

    @JSON(serialize = false)
    public ProvideService getProvideService() {
        return provideService;
    }

    public void setProvideService(ProvideService provideService) {
        this.provideService = provideService;
    }

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
    public PubCodeManagerService getPubCodeManagerService() {
        return pubCodeManagerService;
    }

    public void setPubCodeManagerService(PubCodeManagerService pubCodeManagerService) {
        this.pubCodeManagerService = pubCodeManagerService;
    }

    // ////////////////////////////////////////////////////////////////

    /**
     * 分页查询结果
     */
    @SuppressWarnings("rawtypes")
    private List resultList;

    /**
     * VcApply对象
     */
    private VcApply vcApply;

    /**
     * VcApplyDet集合
     */
    private List<VcApplyDet> vcApplyDetList;

    /**
     * json字符串
     */
    private String json;
    
    /**
     * 发放界面单证类型查询列表
     */
    private List<Map<String, String>> mapList;

    @SuppressWarnings("rawtypes")
    public List getResultList() {
        return resultList;
    }

    @SuppressWarnings("rawtypes")
    public void setResultList(List resultList) {
        this.resultList = resultList;
    }

    public VcApply getVcApply() {
        return vcApply;
    }

    public void setVcApply(VcApply vcApply) {
        this.vcApply = vcApply;
    }

    public List<VcApplyDet> getVcApplyDetList() {
        return vcApplyDetList;
    }

    public void setVcApplyDetList(List<VcApplyDet> vcApplyDetList) {
        this.vcApplyDetList = vcApplyDetList;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public List<Map<String, String>> getMapList() {
		return mapList;
	}

	public void setMapList(List<Map<String, String>> mapList) {
		this.mapList = mapList;
	}
	
    // //////////////////////////////////
    // ********************************//
    // ********************************//
    // ********************************//
    // //////////////////////////////////
    /**
     * 请求provideQuery.jsp
     * 
     * @return page
     * @throws BusinessException
     *             异常
     */
    public String viewProvideQuery() throws BusinessException {
        Date sysdate = new Date();
        Date twoMonthAgo = DateUtils.addDay(sysdate, -60);

        getRequest().setAttribute("initStartDate", twoMonthAgo);
        getRequest().setAttribute("initEndDate", sysdate);

        return SUCCESS;
    }

    /**
     * 请求provideEdit.jsp
     * 
     * @return page
     * @throws BusinessException
     *             异常
     */
    @SuppressWarnings("rawtypes")
    public String viewProvideEdit() throws BusinessException {
        String id = getRequest().getParameter("id");
     try{
        if (StringUtils.isNotEmpty(id)) {
            // 查询申领表
            VcApply vcApply = applyService.getVcApply(Long.valueOf(id));
            this.vcApply = vcApply;

            // 加载代码对应的名称
            this.vcApply.setApplyOrgName(vcLevelService.getUnitNameByUnitCode(vcApply.getApplyOrgCode()));
            this.vcApply.setApplyOprName(vcLevelService.getUnitNameByUnitCode(vcApply.getApplyOprCode()));
            this.vcApply.setProvideOrgName(vcLevelService.getUnitNameByUnitCode(vcApply.getProvideOrgCode()));

            List<VcApplyDet> vcApplyDetList = applyService.getApplyDetListByVcApplyId(Long.valueOf(id));

            Date sysdate = DateUtils.parse(DateUtils.format(new Date(), "yyyy-MM-dd") + " 23:59:59",
                    "yyyy-MM-dd HH:mm:ss");

            String  mngType=getRequest().getParameter("mngType");           //机构发放
            String  maxStoreType=getRequest().getParameter("maxStoreType");  //最大单证保天数
            
            for (Iterator it = vcApplyDetList.iterator(); it.hasNext();) {
                VcApplyDet vcApplyDet = (VcApplyDet) it.next();

               /*
                 * 
                 *   机构单证发放MNG_TYPE='0'     MNG_OBJECT_CODE =申请机构
                 *   
                 *   使用人单证发放MNG_TYPE='1'    MNG_OBJECT_CODE操作人代码
                 **/
                Date deadline = sysdate; //
                int maxStoreTime = provideService.getMaxStoreTime(vcApplyDet.getDocVerCode(), mngType, vcApply
                        .getApplyOrgCode()); // 查询数据库得到最长库存时间
               
                Date calcDeadline = DateUtils.addDay(deadline, maxStoreTime);
                vcApplyDet.setDeadlineStr(DateUtils.format(calcDeadline, "yyyy-MM-dd"));
            }
            this.json = TFJSON.toJSONString(vcApplyDetList);
        }

        return SUCCESS;
     }catch(BusinessException e){
   	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
	   return NONE;
    }catch(Exception e){
    	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
    	   return NONE;
    }
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
    try{
        // 页面上获取的查询条件
        QueryCondition qc = TFJSON.parseObject(json, QueryCondition.class);

        params.put("applyCode", qc.getQueryCode());
        params.put("applyStatus", qc.getQueryStatus());
        params.put("startDate", qc.getStartDate());
        params.put("endDate", qc.getEndDate());
        params.put("provideOrgCode", comCode);

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
     * 获取申领明细转json
     * 
     * @return json字符串
     * @throws BusinessException
     *             异常
     */
    @SuppressWarnings("rawtypes")
    public String queryApplyDetList() throws BusinessException {
        String id = getRequest().getParameter("id");
   try{
        if (StringUtils.isNotEmpty(id)) {
            // 查询申领表
            List<VcApplyDet> vcApplyDetList = applyService.getApplyDetListByVcApplyId(Long.valueOf(id));
            for (Iterator it = vcApplyDetList.iterator(); it.hasNext();) {
                VcApplyDet vcApplyDet = (VcApplyDet) it.next();
                vcApplyDet.setIdVcApplyDet(null);
                vcApplyDet.setVcApply(null);
            }

            String json = TFJSON.toJSONString(vcApplyDetList);
            logger.info(json);
            this.json = json;

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
     * 保存或者提交发放信息时调用方法
     * 
     * @return 操作结果
     * @throws BusinessException
     *             异常
     */
    @SuppressWarnings("rawtypes")
    public String saveProvide() throws BusinessException {

        try {
            // 获取UM信息
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            String comCode = userInfo.getComCode();
            String userCode = userInfo.getUserCode();

            VcApply provide = (VcApply) TFJSON.parseObject(json, VcApply.class);
            provide.setProvideOprCode(userCode);
            provide.setProvideOrgCode(comCode);
            provide.setUpdatedBy(userCode);

            List<VcApplyDet> provideList = provide.getApplyDetList();
            Date sysdate = DateUtils.parse(DateUtils.format(new Date(), "yyyy-MM-dd") + " 23:59:59",
                    "yyyy-MM-dd HH:mm:ss");

            Long id = provide.getId();
            VcApply vcApply = applyService.getVcApply(id);

            for (Iterator it = provideList.iterator(); it.hasNext();) {
                VcApplyDet vcApplyDet = (VcApplyDet) it.next();
                // 校验截止日期是否符合条件
                
                Date deadline = sysdate; //
                int maxStoreTime = provideService.getMaxStoreTime(vcApplyDet.getDocVerCode(), "0", vcApply
                        .getApplyOrgCode()); // 查询数据库得到最长库存时间
                
                Date calcDeadline = DateUtils.addDay(deadline, maxStoreTime);
                Date pageDeadline = DateUtils.parse(vcApplyDet.getDeadlineStr() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");

                if (DateUtils.compare(pageDeadline, calcDeadline) <= 0) {
                    if (DateUtils.compare(pageDeadline, sysdate) >= 0) {
                        vcApplyDet.setDeadline(pageDeadline);
                    } else {
                        this.json = "单证类型[" + vcApplyDet.getDocVerCode() + "]使用截止期不可以小于当前日期";
                        return SUCCESS;
                    }
                }
                else {
                    this.json = "单证类型[" + vcApplyDet.getDocVerCode() + "]使用截止期超出最长库存时间 ";
                    return SUCCESS;
                }
                
            }

            logger.info("解析provide字符串  " + (provide == null));
            provideService.saveProvide(provide);
            this.json = "操作成功";
        }/* catch (Exception e) {
            e.printStackTrace();
            this.json = e.getMessage();
        }*/
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
     * 单证发放界面-单证类型自动完成组件
     * @return
     * @throws BusinessException
     * @author chy
     * @date 2013-07-24
     */
    public String qeuryJsonDocVersion() throws BusinessException {
    	try{
    		String id = getRequest().getParameter("applyId");
    		String codeValue = getRequest().getParameter("codeCode");
    		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
    		// 根据条件查询单证类型
            List<VcDocVersionInfo> docVersionInfoList = provideService.getVcDocVersionInfoList(Long.valueOf(id), codeValue);
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
     * 获取申请数量
     * @return
     * @throws BusinessException
     */
    public String queryJsonApplyNum() throws BusinessException {
    	try{
    		String id = getRequest().getParameter("applyId");
    		String docVerCode = getRequest().getParameter("docVerCode");
    		Integer applyNum = provideService.getApplyNum(Long.valueOf(id), docVerCode);
    		this.json = applyNum+"";
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
     * 计算使用截止日期
     * @return
     * @throws BusinessException
     */
    public String queryJsonDaedLine() throws BusinessException {
    	try{
    		Date deadline = DateUtils.parse(DateUtils.format(new Date(), "yyyy-MM-dd") + " 23:59:59",
            		"yyyy-MM-dd HH:mm:ss");
    		//单证类型
    		String  docVerCode=getRequest().getParameter("docVerCode");
    		//申请机构
    		String  orgCode=getRequest().getParameter("orgCode");
    		//机构发放
    		String  mngType=getRequest().getParameter("mngType");
    		//最大单证保天数
            String  maxStoreType=getRequest().getParameter("maxStoreType");
    		int maxStoreTime = provideService.getMaxStoreTime(docVerCode, mngType, orgCode); // 查询数据库得到最长库存时间
           
            Date calcDeadline = DateUtils.addDay(deadline, maxStoreTime);
            json = DateUtils.format(calcDeadline, "yyyy-MM-dd");
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
