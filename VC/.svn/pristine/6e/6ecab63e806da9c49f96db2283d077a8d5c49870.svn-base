/**
 * 
 */
package com.tapi.tcs.vc.baseinfo.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.json.annotations.JSON;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.baseinfo.service.InsuKindService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.schema.model.VcDocInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;

/**
 * @author Administrator
 * 
 */
public class InsuKindAction extends TFAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 6533935808316755937L;

    private InsuKindService insuKindService;

    @JSON(serialize = false)
    public InsuKindService getInsuKindService() {
        return insuKindService;
    }

    private String result;

    private VcDocInsuKind insuKind;

    private List insuKindList;

    private Long id;

    private String insuKindCode;

    private String insuKindName;

    private String insuTypeCode;

    private String deleteList;

    private Long insuTypeId;

    private List<Map<String, String>> mapList;
    
    /** 当前业务类型 [insert,view,update,delete]*/
    private String actionType;

    public List<Map<String, String>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, String>> mapList) {
        this.mapList = mapList;
    }

    public void setInsuKindService(InsuKindService insuKindService) {
        this.insuKindService = insuKindService;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public VcDocInsuKind getInsuKind() {
        return insuKind;
    }

    public void setInsuKind(VcDocInsuKind insuKind) {
        this.insuKind = insuKind;
    }

    public List getInsuKindList() {
        return insuKindList;
    }

    public void setInsuKindList(List insuKindList) {
        this.insuKindList = insuKindList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeleteList() {
        return deleteList;
    }

    public void setDeleteList(String deleteList) {
        this.deleteList = deleteList;
    }

    public String getInsuKindCode() {
        return insuKindCode;
    }

    public void setInsuKindCode(String insuKindCode) {
        this.insuKindCode = insuKindCode;
    }

    public String getInsuKindName() {
        return insuKindName;
    }

    public void setInsuKindName(String insuKindName) {
        this.insuKindName = insuKindName;
    }

    public String getInsuTypeCode() {
        return insuTypeCode;
    }

    public void setInsuTypeCode(String insuTypeCode) {
        this.insuTypeCode = insuTypeCode;
    }

    public Long getInsuTypeId() {
        return insuTypeId;
    }

    public void setInsuTypeId(Long insuTypeId) {
        this.insuTypeId = insuTypeId;
    }
    
    

    /**
     * @return the actionType
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * @param actionType the actionType to set
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    // //////////////////////////////////////
    public String viewEdit() throws BusinessException {
    	try{
	        String strid = getRequest().getParameter("id");
	        Long id = Long.valueOf(strid);
	
	        insuKind = insuKindService.getInsuKind(id);
    	}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}

        return SUCCESS;
    }

    public String viewQuery() throws BusinessException {
        return SUCCESS;
    }

    public String saveInsuKind() throws BusinessException {
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        VcDocInsuKind insuKind = this.insuKind;
        Long id = insuKind.getIdVcDocInsuKind();
        try {
            Date sysdate = new Date();
            String user = userInfo.getUserCode();

            if (id == null) {
                insuKind.setStatus("1");
                insuKind.setDateCreated(sysdate);
                insuKind.setCreatedBy(user);
                insuKind.setUpdatedBy(user);
                insuKind.setDateUpdated(sysdate);
                insuKind.setIdVcDocInsuType(insuKind.getInsuType().getIdVcDocInsuType());
                insuKind.setInsuType(null);
                insuKindService.createInsuKind(insuKind);
                this.result = "保存成功";
            } else {
                VcDocInsuKind dbInsuKind = insuKindService.getInsuKind(id);
                if (dbInsuKind != null && dbInsuKind.getInsuKindName().equals(insuKind.getInsuKindName())) {
                    throw new BusinessException("未做修改");
                }

                insuKind.setDateUpdated(sysdate);
                insuKind.setUpdatedBy(user);

                insuKind.setInsuType(null);

                insuKindService.updateInsuKind(insuKind);
                this.result = "修改成功";

            }
        } catch (Exception e) {
            e.printStackTrace();
            this.result = "操作失败,失败原因:" + e.getMessage();
        }
        return SUCCESS;
    }

    /**
     * 
     * @return
     * @throws BusinessException
     */
    public String queryInsuKindListByPages() throws BusinessException {
    	try{
	        if (logger.isDebugEnabled()) {
	            logger.debug("queryDocInsuTypeListByPages() - start"); //$NON-NLS-1$
	        }
	
	        Page pageObj = insuKindService.queryDocInsuKindListByPages(insuTypeId, insuKindCode, insuKindName, page, rows);
	
	        this.insuKindList = pageObj.getResult();
	        total = (int) pageObj.getTotalPageCount();
	        records = (int) pageObj.getTotalCount();
	
	        if (logger.isDebugEnabled()) {
	            logger.debug("queryDocInsuTypeListByPages() - end"); //$NON-NLS-1$
	        }
    	}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
        return SUCCESS;
    }

    /**
     * 
     * @return
     * @throws BusinessException
     */
    public String deleteInsuKindList() throws BusinessException {
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        String deleteList = this.deleteList;

        String[] idArray = deleteList.split(",");

        String currentUser = userInfo.getUserCode();

        try {
            this.result = insuKindService.deleteByLogic(idArray, currentUser);
        } catch (Exception e) {
            e.printStackTrace();
            this.result = e.getMessage();
        }

        return SUCCESS;
    }

    @SuppressWarnings("rawtypes")
    public String queryJSONInsuTypeList() throws BusinessException {
    	try{
	        // 获取参数
	        String valueType = getRequest().getParameter("valueType");
	
	        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
	
	        // 状态为1的险类
	        List<VcDocInsuType> validInsuTypeList = insuKindService.getValidInsuTypeList();
	
	        if (validInsuTypeList != null && validInsuTypeList.size() > 0) {
	            Map<String, String> map = null;
	            for (Iterator item = validInsuTypeList.iterator(); item.hasNext();) {
	                VcDocInsuType insuType = (VcDocInsuType) item.next();
	                map = new HashMap<String, String>();
	                map.put("label", insuType.getInsuTypeName());
	                if ("serialId".equals(valueType)) {
	                    map.put("value", insuType.getIdVcDocInsuType().toString());
	                } else {
	                    map.put("value", insuType.getInsuTypeCode());
	                }
	
	                mapList.add(map);
	                map = null;
	            }
	        }
	        this.mapList = mapList;
    	}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
      

        return SUCCESS;
    }

    /**
     * 根据条件查询
     * 
     * @return
     * @throws BusinessException
     * @wanghuajian
     */
    @SuppressWarnings("rawtypes")
    public String queryJSONInsuKindList() throws BusinessException {
    	try{
	        // 获取参数
	        String valueType = getRequest().getParameter("valueType");
	        // Long idVcDocInsuType =
	        // Long.getLong(getRequest().getParameter("idVcDocInsuType"));
	        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
	        Map<String, Object> parMap = new HashMap<String, Object>();
	        parMap.put("status", "1");
	        if (StringUtils.isNotBlank(insuTypeCode) && !"null".equals(insuTypeCode)) {
	            parMap.put("insuTypeCode", insuTypeCode);
	        }
	        if (insuTypeId != null) {
	            parMap.put("idVcDocInsuType", insuTypeId);
	        }
	
	        List<VcDocInsuKind> validInsuKindList = insuKindService.getInsuKindList(parMap);
	
	        if (validInsuKindList != null && validInsuKindList.size() > 0) {
	            Map<String, String> map = null;
	            for (Iterator item = validInsuKindList.iterator(); item.hasNext();) {
	                VcDocInsuKind insuKind = (VcDocInsuKind) item.next();
	                map = new HashMap<String, String>();
	                map.put("label", insuKind.getInsuKindName());
	                if ("serialId".equals(valueType)) {
	                    map.put("value", insuKind.getIdVcDocInsuKind().toString());
	                } else {
	                    map.put("value", insuKind.getInsuKindCode());
	                }
	                // map.put("value", insuKind.getInsuKindCode());
	                // map.put("name", insuKind.getInsuTypeCode());
	                map.put("name", insuKind.getInsuType().getInsuTypeCode());
	                mapList.add(map);
	                map = null;
	            }
	        }
	        this.mapList = mapList;
    	}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
       

        return SUCCESS;
    }

    public String initInsuKindListWithCondition() throws BusinessException {
    	try{
	        QueryRule qr = QueryRule.getInstance();
	        if (Beans.isNotEmpty(insuTypeCode)) {
	            qr.addEqual("insuTypeCode", insuTypeCode);
	        }
	        mapList = new ArrayList<Map<String, String>>();
	        /*
	         * List<VcDocInsuKind> validInsuKindList = insuKindService
	         * .queryInsuKindList(qr);
	         */
	        List<VcDocInsuKind> validInsuKindList = insuKindService.queryInsuKindListByInsuTypeCode(insuTypeCode);
	        for (Iterator iterator = validInsuKindList.iterator(); iterator.hasNext();) {
	            VcDocInsuKind vcDocInsuKind = (VcDocInsuKind) iterator.next();
	            Map<String, String> map = new HashMap<String, String>();
	            map.put("label", vcDocInsuKind.getInsuKindName());
	            map.put("value", vcDocInsuKind.getInsuKindCode());
	            mapList.add(map);
	        }
    	}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
        return SUCCESS;
    }

}
