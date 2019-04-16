package com.tapi.tcs.vc.store.web;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcDocTakerIo;
import com.tapi.tcs.vc.schema.model.VcStorageInOut;
import com.tapi.tcs.vc.schema.model.VcStorageInOutDet;
import com.tapi.tcs.vc.store.service.VcStorageInOutService;

/**
 * 单证出入库
 * 
 * @author whj
 * 
 */
public class VcStorageInOutAction extends TFAction {
    private static final long serialVersionUID = 4846445590376550384L;

    private static final Logger logger = Logger.getLogger(VcStorageInOutAction.class);

    /**
     * jsonStr
     */
    private String jsonStr;

    /**
     * 出入库对象
     */
    private VcStorageInOut vcStorageInOut;

    /**
     * 出入库对象
     */
    private List<VcStorageInOut> vcStorageInOutList;

    /**
     * 出入库明细集合
     */
    private List<VcStorageInOutDet> inOutDetList;

    private VcStorageInOutService vcStorageInOutService;
    
    
    private String operateType; //I-入库  O-出库
    
    private String  resultMsg;
    private String actionType;

    /**
     * 出入库查询初始化页面
     */
    public String initQueryStorageInOut() throws BusinessException {
        vcStorageInOut= new VcStorageInOut();
        Date sysdate = new Date();
        vcStorageInOut.setOperateTime(sysdate);
        vcStorageInOut.setStartOperateTime(DateUtils.addDay(sysdate, -90));
        vcStorageInOut.setEndOperateTime(sysdate);
        return SUCCESS;
    }

    /**
     * 分页查询申领信息
     * 
     * @return 查询结果
     * @throws BusinessException
     *             异常
     */
    public String queryStorageInOutByPages() throws BusinessException {
        // 获取登录人信息，包括机构等信息，加入查询条件
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        if (userInfo == null) {
            throw new BusinessException("登录超时，请重新登录！ ");
        }
        try {
            Page pageObj = vcStorageInOutService.queryVcStorageInOutList(vcStorageInOut, page, rows);

            vcStorageInOutList = (List<VcStorageInOut>) pageObj.getResult();
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
     * 初始化出库入库新增页面
     * 
     * @throws BusinessException
     *             异常
     */
    public String initStorageInOutAdd() throws BusinessException {
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        if (userInfo == null) {
            throw new BusinessException("登录超时，请重新登录！ ");
        }
        vcStorageInOut = new VcStorageInOut();

        vcStorageInOut.setOrgCode(userInfo.getComCode());
        vcStorageInOut.setOrgName(userInfo.getComName());
        vcStorageInOut.setOprCode(userInfo.getUserCode());
        vcStorageInOut.setOprName(userInfo.getUserName());

        Date sysdate = new Date();
        vcStorageInOut.setOperateTime(sysdate);
        return SUCCESS;
    }

    /**
     * 出库入库操作
     * 
     * @return page
     * @throws BusinessException
     *             异常
     */
    @SuppressWarnings("rawtypes")
    public String executeStorageInOut() throws BusinessException {
        // 初始化新增页面的一些数据
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        if (userInfo == null) {
            throw new BusinessException("登录超时，请重新登录！ ");
        }
        try {
            inOutDetList = TFJSON.parseArray(jsonStr,VcStorageInOutDet.class);
            vcStorageInOut.setVcStorageInOutDets(inOutDetList);
            // OperateFlag 出入库标志 I-入库 O-出库
            vcStorageInOut.setOperateFlag(operateType);
            vcStorageInOutService.executeDocInOut(vcStorageInOut, vcStorageInOut.getOperateFlag(), userInfo);
            actionType="saveSuccess";
            resultMsg="操作成功！";
            //this.setAjaxMessageInfo("操作成功！");
        } catch (BusinessException e) {
            e.printStackTrace();
            vcStorageInOut.setOrgName(userInfo.getComName());           
            vcStorageInOut.setOprName(userInfo.getUserName());
            actionType="saveFail";
            resultMsg=e.getMessage();
            return "fail";
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public VcStorageInOut getVcStorageInOut() {
        return vcStorageInOut;
    }

    public void setVcStorageInOut(VcStorageInOut vcStorageInOut) {
        this.vcStorageInOut = vcStorageInOut;
    }

    public List<VcStorageInOut> getVcStorageInOutList() {
        return vcStorageInOutList;
    }

    public void setVcStorageInOutList(List<VcStorageInOut> vcStorageInOutList) {
        this.vcStorageInOutList = vcStorageInOutList;
    }

    public List<VcStorageInOutDet> getInOutDetList() {
        return inOutDetList;
    }

    public void setInOutDetList(List<VcStorageInOutDet> inOutDetList) {
        this.inOutDetList = inOutDetList;
    }

   

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public void setVcStorageInOutService(VcStorageInOutService vcStorageInOutService) {
        this.vcStorageInOutService = vcStorageInOutService;
    }

   
}
