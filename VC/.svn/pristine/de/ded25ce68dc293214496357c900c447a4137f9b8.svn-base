package com.tapi.tcs.vc.common.web;

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
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.service.PubCodeManagerService;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.vo.VcPubCodeQueryVo;
import com.tapi.tcs.vc.schema.model.VcPubCode;
import com.tapi.tcs.vc.schema.model.VcPubCodeType;

/**
 * 通用代码维护Action
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
 * 通用代码维护control
 * 
 * @author wanghuajian
 * @version 1.0
 */
public class PubCodeManagerAction extends TFAction {

    private static final long serialVersionUID = -2590512066673542833L;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private PubCodeManagerService pubCodeManagerService;

    // 保存用vo
    private VcPubCodeType vcPubCodeType;
    // 查询条件
    private VcPubCodeQueryVo queryDto;
    private List vcPubCodeTypeList;
    private Long idVcPubCodeType;
    // 通用代码信息
    private List<VcPubCode> vcPubCodeList;

    /** json数据 */
    private String jsonData;

    private String selectVcPubCodeTypeIds; // 列表勾选的记录ids

    private String actionType;// 当前业务类型 [insert,view,update,delete]

    private String resultMsg;

    private List<Map<String, String>> mapList;

    public List<Map<String, String>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, String>> mapList) {
        this.mapList = mapList;
    }

    public void setPubCodeManagerService(PubCodeManagerService pubCodeManagerService) {
        this.pubCodeManagerService = pubCodeManagerService;
    }

    /**
     * 初始化进入新增代码页面
     * 
     * @author wanghuajian
     * @date 2013-04-16
     * 
     */
    public String initAddVcPubCodeType() {
        this.actionType = "insert";
        return "initAddVcPubCodeType";
    }

    /**
     * 保存新增代码
     * 
     * @author wanghuajian
     * @date 2013-04-16
     * 
     */
    public String saveVcPubCodeType() throws Exception {
        logger.info("访问/baseinfo/saveVcPubCodeType.do?ajax=true保存通用代码...");
        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            vcPubCodeList = TFJSON.parseArray(jsonData, VcPubCode.class);
            vcPubCodeType.setVcPubCodeList(vcPubCodeList);

            pubCodeManagerService.createVcPubCodeType(vcPubCodeType, userInfo);
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
     * 进入查询代码页面
     * 
     * @author wanghuajian
     * @date 2013-04-16
     * 
     */
    public String initQueryVcPubCodeType() {
        return "initQueryVcPubCodeType";
    }

    /**
     * 查询代码
     * 
     * @author wanghuajian
     * @date 2013-04-16
     * 
     */
    public String queryVcPubCodeTypeByPages() throws BusinessException {
        logger.info("访问/visabaseinfojson/queryVcPubCodeTypeByPages.do?ajax=true分页查询代码信息...");
        try {
            Page pageObj = pubCodeManagerService.queryVcPubCodeTypeByPages(queryDto, page, rows);

            // 设置相关数据
            vcPubCodeTypeList = pageObj.getResult();
            total = (int) pageObj.getTotalPageCount();
            records = (int) pageObj.getTotalCount();
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
     * 编辑代码
     */
    public String prepareUpdateVcPubCodeType() throws Exception {
        logger.info("访问/visabaseinfo/prepareUpdateVcPubCodeType.do?ajax=true进入代码编辑页面...");
        try {
            this.vcPubCodeType = pubCodeManagerService.getVcPubCodeType(this.idVcPubCodeType);
            this.vcPubCodeList = this.vcPubCodeType.getVcPubCodeList();
        } catch (BusinessException e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }

        this.actionType = "update";
        return "prepareUpdateVcPubCodeType";
    }

    /**
     * 进入修改代码页面
     * 
     * @author wanghuajian
     * @date 2013-04-16
     * 
     */
    public String updateVcPubCodeType() throws Exception {
        logger.info("访问/baseinfo/updateVcPubCodeType.do?ajax=true更新通用代码...");
        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new Exception("登录超时，请重新登录！ ");
            }
            vcPubCodeList = TFJSON.parseArray(jsonData, VcPubCode.class);
            vcPubCodeType.setVcPubCodeList(vcPubCodeList);

            pubCodeManagerService.updateVcPubCodeType(vcPubCodeType, userInfo);
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
     * 查看代码
     */
    public String viewVcPubCodeType() throws Exception {
        logger.info("访问/visabaseinfo/viewVcPubCodeType.do进入代码查看页面...");
        try {
            this.vcPubCodeType = pubCodeManagerService.getVcPubCodeType(this.idVcPubCodeType);
            this.vcPubCodeList = this.vcPubCodeType.getVcPubCodeList();
        } catch (BusinessException e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        this.actionType = "view";
        return "viewVcPubCodeType";
    }

    /**
     * 删除、恢复代码
     * 
     * @author wanghuajian
     * @date 2013-04-16
     * 
     */
    public String deleteOrUnDeleteVcPubCodeTypes() throws BusinessException {
        try {
            this.resultMsg = pubCodeManagerService.deleteOrUnDeleteVcPubCodeTypes(this
                    .getSelectVcPubCodeTypeIds());
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
     * @return 根据代码类型查询代码信息
     * @throws BusinessException
     * @author wanghuajian since 2013-4-13下午01:36:22
     */
    public String queryJsonVcPubCodeListByTypeId() throws Exception {
        // String codeType=getRequest().getParameter("codeType");

        if (idVcPubCodeType != null) {
            try {
                this.vcPubCodeType = pubCodeManagerService.getVcPubCodeType(this.idVcPubCodeType);
                if (vcPubCodeType != null) {
                    this.vcPubCodeList = this.vcPubCodeType.getVcPubCodeList();
                }
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
        return SUCCESS;

    }

    /**
     * @return 根据代码类型查询代码信息
     * @throws BusinessException
     * @author wanghuajian since 2013-4-13下午01:36:22
     */
    public String queryJsonVcPubCodeListByCodeType() throws Exception {
        try {
            String codeType = getRequest().getParameter("codeType");
            if (codeType != null && "".equals(codeType) && "null".equals(codeType)) {
                vcPubCodeList = pubCodeManagerService.getVcPubCodeListByCodeType(codeType);
            }
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
     * 下拉框
     * 
     * @return
     * @throws Exception
     */
    public String initDownList() throws Exception {
        try {
            String codeType = getRequest().getParameter("codeType");
            if (Beans.isNotEmpty(codeType)) {
                vcPubCodeTypeList = new ArrayList();
                vcPubCodeList = pubCodeManagerService.getVcPubCodeListByCodeType(codeType);
                for (Iterator iterator = vcPubCodeList.iterator(); iterator.hasNext();) {
                    VcPubCode vcPubCode = (VcPubCode) iterator.next();
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("label", vcPubCode.getCodeCName());
                    map.put("value", vcPubCode.getCodeCode());
                    vcPubCodeTypeList.add(map);
                }
            }
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

    @SuppressWarnings("rawtypes")
    public String getPubCode() throws BusinessException {
        // 获取参数codeType
        String codeType = getRequest().getParameter("codeType");
        // 不包含的业务代码
        String notContainCodeCode = getRequest().getParameter("notContainCodeCode");
        String[] notContainCodeCodes = {};
        if (StringUtils.isNotBlank(notContainCodeCode)) {
            notContainCodeCodes = notContainCodeCode.split(",");
        }
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        Map<String, String> map01 = new HashMap<String, String>();
        map01.put("label", "-请选择-");
        map01.put("value", "");
        map01.put("name", "");
        mapList.add(map01);

        try {
            if (StringUtils.isNotEmpty(codeType)) {
                List<VcPubCode> pubCodeList = pubCodeManagerService.getVcPubCodeListByCodeTypeByOrder(
                        codeType, new String[] { "codeCode" }, "asc", notContainCodeCodes);
                if (pubCodeList != null && pubCodeList.size() > 0) {
                    Map<String, String> map = null;
                    for (Iterator item = pubCodeList.iterator(); item.hasNext();) {
                        VcPubCode v = (VcPubCode) item.next();
                        map = new HashMap<String, String>();

                        // 系统控制状态只显示中卫，不显示代码
                        // map.put("label",
                        // v.getCodeCode()+"-"+v.getCodeCName());
                        map.put("label", v.getCodeCName());

                        map.put("value", v.getCodeCode());
                        mapList.add(map);
                        map = null;
                    }
                }
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }

        this.mapList = mapList;

        return SUCCESS;
    }

    public VcPubCodeType getVcPubCodeType() {
        return vcPubCodeType;
    }

    public void setVcPubCodeType(VcPubCodeType vcPubCodeType) {
        this.vcPubCodeType = vcPubCodeType;
    }

    public VcPubCodeQueryVo getQueryDto() {
        return queryDto;
    }

    public void setQueryDto(VcPubCodeQueryVo queryDto) {
        this.queryDto = queryDto;
    }

    public List getVcPubCodeTypeList() {
        return vcPubCodeTypeList;
    }

    public void setVcPubCodeTypeList(List vcPubCodeTypeList) {
        this.vcPubCodeTypeList = vcPubCodeTypeList;
    }

    public Long getIdVcPubCodeType() {
        return idVcPubCodeType;
    }

    public void setIdVcPubCodeType(Long idVcPubCodeType) {
        this.idVcPubCodeType = idVcPubCodeType;
    }

    public List<VcPubCode> getVcPubCodeList() {
        return vcPubCodeList;
    }

    public void setVcPubCodeList(List<VcPubCode> vcPubCodeList) {
        this.vcPubCodeList = vcPubCodeList;
    }

    public String getSelectVcPubCodeTypeIds() {
        return selectVcPubCodeTypeIds;
    }

    public void setSelectVcPubCodeTypeIds(String selectVcPubCodeTypeIds) {
        this.selectVcPubCodeTypeIds = selectVcPubCodeTypeIds;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
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