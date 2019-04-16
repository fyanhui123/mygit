package com.tapi.tcs.vc.baseinfo.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.vc.schema.model.VcDocType;
import com.tapi.tcs.vc.baseinfo.service.VcDocTypeService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.Beans;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;

/**
 * 单证种类维护Action
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
 * 单证种类维护control
 * 
 * @author wanghuajian
 * @version 1.0
 */
public class DocTypeAction extends TFAction {
    private static final long serialVersionUID = -2248031194541049469L;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private VcDocTypeService vcDocTypeService;

    // 单证种类信息
    private VcDocType vcDocTypeDto;
    private List vcDocTypeList;
    private Long idVcDocType;
    private String docTypeCode;// 单证种类代码
    private String docTypeName;// 单证种类名称
    private String docType;// 类型(0-单证，1-发票)
    private String status;// 状态(0-无效，1-有效)

    private List<Map<String, String>> mapList;

    private String selectVcDocTypeIds;

    private String actionType;// 当前业务类型 [insert,view,update,delete]

    private String resultMsg;

    public void setVcDocTypeService(VcDocTypeService vcDocTypeService) {
        this.vcDocTypeService = vcDocTypeService;
    }

    // 单证种类维护==================================================
    /**
     * 初始化进入新增单证种类页面
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String initAddDocType() {
        this.actionType = "insert";
        return "initAddDocType";
    }

    /**
     * 保存新增单证种类
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String saveDocType() throws BusinessException {
        logger.info("访问/saveDocType.?ajax=true分页查询单证种类信息...");
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        try {
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            vcDocTypeService.createVcDocType(vcDocTypeDto, userInfo);
            this.resultMsg = "保存成功";
        } catch (BusinessException e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

    /**
     * 进入查询单证种类页面
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String initQueryDocType() {
        return "initQueryDocType";
    }

    /**
     * 查询单证种类
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String queryDocTypeByPages() throws BusinessException {
        logger.info("访问/baseinfo/queryVcDocType.do?ajax=true分页查询单证种类信息...");
        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            // vcDocType = this.getVcDocType();
            VcDocType queryDto = new VcDocType();
            queryDto.setDocTypeName(docTypeName);
            queryDto.setDocTypeCode(docTypeCode);
            queryDto.setDocType(docType);
            queryDto.setStatus(status);
            Page pageObj = vcDocTypeService.queryVcDocTypeByPages(queryDto, userInfo, page, rows);

            // 设置相关数据
            vcDocTypeList = pageObj.getResult();
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
     * 编辑单证种类
     */
    public String prepareUpdateDocType() throws Exception {
        logger.info("访问/baseinfo/prepareUpdate.do?ajax=true进入单证种类编辑页面...");
        try {
            this.vcDocTypeDto = vcDocTypeService.getVcDocType(this.idVcDocType);
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        this.actionType = "update";
        return "prepareUpdateDocType";
    }

    /**
     * 进入修改单证种类页面
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String updateDocType() throws BusinessException {
        logger.info("访问/baseinfo/updateDocType.do?ajax=true进入单证种类编辑页面...");
        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }

            vcDocTypeService.updateVcDocType(vcDocTypeDto, userInfo);
            this.resultMsg = "保存成功";

        } catch (BusinessException e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
            // }
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

    /**
     * 编辑单证种类
     */
    public String viewDocType() throws Exception {
        logger.info("访问/ggRiskClass/prepareUpdate.action?ajax=true进入单证种类编辑页面...");
        try{
        this.vcDocTypeDto = vcDocTypeService.getVcDocType(this.idVcDocType);
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        this.actionType = "view";
        return "viewDocType";
    }

    /**
     * 注销、恢复单证种类
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String deleteOrUndeleteDocType() throws BusinessException {

        try {
            this.resultMsg = vcDocTypeService.deleteOrUndeleteDocType(this.getSelectVcDocTypeIds());
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
     * 查询满足条件的单证种类下拉列表
     * 
     * @author wanghuajian
     * @date 2013-04-09
     */
    @SuppressWarnings("rawtypes")
    public String queryJSONDocTypeList() throws BusinessException {

        String codeValue = getRequest().getParameter("codeCode");
        String valueType = getRequest().getParameter("valueType");
        // 是否级联
        String trigger = getRequest().getParameter("trigger");
        // 组件样式 select-下拉列表 auto-自动完成组件
        String style = getRequest().getParameter("style");
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        String lableName = "label";
        boolean isNotAuto=true;
        if (StringUtils.isNotEmpty(style) && "auto".equals(style)) {
            lableName = "lable";
            isNotAuto=false;
        } /*
           * else { Map<String, String> map01 = new HashMap<String, String>(); map01.put(lableName, "-请选择-");
           * map01.put("value", ""); mapList.add(map01); }
           */
        Map<String, Object> parMap = new HashMap<String, Object>();
        parMap.put("docType", docType);
        parMap.put("status", "1");
        parMap.put("docTypeCode", codeValue);

        // 状态为1的险类
        try {
            List<VcDocType> validDocTypeList = vcDocTypeService.getDocTypeList(parMap);

            if (validDocTypeList != null && validDocTypeList.size() > 0) {
                Map<String, String> map = null;
                for (Iterator item = validDocTypeList.iterator(); item.hasNext();) {

                    VcDocType docType = (VcDocType) item.next();
                    map = new HashMap<String, String>();
                    if(isNotAuto){
                       map.put(lableName, docType.getDocTypeCode()+"_"+docType.getDocTypeName());  
                    }else{
                       map.put(lableName, docType.getDocTypeName());   
                    }

                    if (StringUtils.isNotEmpty(valueType) && "code".equals(valueType)) {
                        map.put("value", docType.getDocTypeCode());
                    } else {
                        map.put("value", docType.getIdVcDocType().toString());
                    }
                    // 有级联
                    if (!(StringUtils.isNotEmpty(trigger) && "no".equals(trigger))) {
                        map.put("name", docType.getDocType());
                    }
                    mapList.add(map);
                    map = null;
                }
            }

            this.mapList = mapList;
        } catch (BusinessException e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
            // }
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

    /**
     * 按照大类获取单证种类 0-单证，1-发票
     * 
     * @return
     */
    public String initDocTypeListWithCondition() {
        try {
            // 0-单证，1-发票
            String docTypeStr = getRequest().getParameter("docType");
            QueryRule qr = QueryRule.getInstance();
            if (Beans.isNotEmpty(docTypeStr)) {
                qr.addEqual("docType", docType);
            }
            List<VcDocType> docTypeList = vcDocTypeService.queryDocType(qr);
            mapList = new ArrayList<Map<String, String>>();
            Map<String, String> map01 = new HashMap<String, String>();
            map01.put("label", "-请选择-");
            map01.put("value", "");
            mapList.add(map01);

            if (docTypeList != null && docTypeList.size() > 0) {
                Map<String, String> map = null;
                for (Iterator item = docTypeList.iterator(); item.hasNext();) {
                    VcDocType docType = (VcDocType) item.next();
                    map = new HashMap<String, String>();
                    map.put("label", docType.getDocTypeName());
                    map.put("value", docType.getDocTypeCode());
                    mapList.add(map);
                }
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
            // }
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }

        return SUCCESS;

    }

    public VcDocType getVcDocTypeDto() {
        return vcDocTypeDto;
    }

    public void setVcDocTypeDto(VcDocType vcDocTypeDto) {
        this.vcDocTypeDto = vcDocTypeDto;
    }

    public List getVcDocTypeList() {
        return vcDocTypeList;
    }

    public void setVcDocTypeList(List vcDocTypeList) {
        this.vcDocTypeList = vcDocTypeList;
    }

    public Long getIdVcDocType() {
        return idVcDocType;
    }

    public void setIdVcDocType(Long idVcDocType) {
        this.idVcDocType = idVcDocType;
    }

    public String getDocTypeCode() {
        return docTypeCode;
    }

    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDocTypeName() {
        return docTypeName;
    }

    public void setDocTypeName(String docTypeName) {
        this.docTypeName = docTypeName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public List<Map<String, String>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, String>> mapList) {
        this.mapList = mapList;
    }

    public String getSelectVcDocTypeIds() {
        return selectVcDocTypeIds;
    }

    public void setSelectVcDocTypeIds(String selectVcDocTypeIds) {
        this.selectVcDocTypeIds = selectVcDocTypeIds;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

}