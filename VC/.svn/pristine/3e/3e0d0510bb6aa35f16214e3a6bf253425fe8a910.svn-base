package com.tapi.tcs.vc.insucard.web;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.baseinfo.service.VcDocVersionInfoService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.insucard.service.InsuCardImportService;
import com.tapi.tcs.vc.insucard.service.InsuCardSalesService;
import com.tapi.tcs.vc.insucard.vo.InsuCardSalesQueryVo;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesDetail;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesRecord;
import com.tapi.tcs.vc.schema.model.VcPayFeeAccount;

/**
 * 激活卡销售
 * 
 * @author whj
 * 
 */
public class InsuCardSalesAction extends TFAction {
    /**
     * 
     */
    private static final long serialVersionUID = -8295300940072591704L;

    /**
     * 激活卡销售service
     */
    private InsuCardSalesService insuCardSalesService;

    /**
     * @param insuCardSalesService
     *            激活卡销售service
     */
    public void setInsuCardSalesService(InsuCardSalesService insuCardSalesService) {
        this.insuCardSalesService = insuCardSalesService;
    }

    // *********************************************************************************************//

    /** 激活卡销售查询（查询用） */
    private InsuCardSalesQueryVo queryDto;

    /** 激活卡销售信息（修改、保存） */
    private VcInsuCardSalesRecord insuCardSaleRecordVo;

    /** 激活卡销售主表id */
    private Long idVcInsuCardSalesRecord;

    /** 银行账户信息 */
    private VcPayFeeAccount bankInfoDto;

    /** 激活卡销售查询 结果List */
    private List<VcInsuCardSalesRecord> vcInsuCardSalesRecordList;

    /** 激活卡销售明细表list */
    private List<VcInsuCardSalesDetail> vcInsuCardSalesDetailList;

    /** type 1-保存 2-提交 */
    private String type;

    /** 操作类型 insert-新增 update-修改 view-查看 */
    private String actionType;

    /**
     * 操作结果信息
     */
    private String resultMsg;

    /** 子表多行输入域信息 */
    private String detListJson;

    /** 激活卡销售/退卡标志（P_销售 R-退卡） */
    private String saleRefundFlag;

    // /----------激活卡销售--------------------------------

    /**
     * 进入查询激活卡销售查询页面
     * 
     * @return
     */
    public String initInsuCardSalesQuery() {
        logger.info("访问/insucard/initInsuCardSalesQuery.action进入查询激活卡销售页面...");
        Date nowDate = new Date();
        queryDto = new InsuCardSalesQueryVo();
        queryDto.setSaleStartDate(DateUtils.addMonth(nowDate, -2));
        queryDto.setSaleEndDate(nowDate);
        actionType="query";
        return SUCCESS;
    }

    /**
     * 初始化销售新增
     * 
     * @return
     */
    public String initInsuCardSalesAdd() {
        logger.info("访问/insucard/initInsuCardSalesAdd进入激活卡销售新增页面...");
        actionType = "insert";
        return SUCCESS;
    }

    /**
     * 初始化激活卡销售修改
     * 
     * @return
     */
    public String initInsuCardSalesUpdate() {
        logger.info("访问/insucard/initInsuCardSalesAdd进入激活卡销售修改页面...");
        try {
            insuCardSaleRecordVo = insuCardSalesService.getVcInsuCardSalesRecord(idVcInsuCardSalesRecord);
            vcInsuCardSalesDetailList = insuCardSaleRecordVo.getVcInsuCardSalesDetailList();
            for (VcInsuCardSalesDetail det : vcInsuCardSalesDetailList) {
                det.setVcInsuCardSalesRecord(null);
            }
            this.detListJson = TFJSON.toJSONString(vcInsuCardSalesDetailList);
            insuCardSaleRecordVo.setVcInsuCardSalesDetailList(null);
            actionType = "update";
        } catch (Exception be) {
            be.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            // return "fail";
            return NONE;
        }
        return SUCCESS;
    }

    /**
     * 激活卡销售查看
     * 
     * @return
     */
    public String viewInsuCardSales() {
        logger.info("访问/insucard/viewInsuCardSales进入激活卡销售查看页面...");
        try {
            insuCardSaleRecordVo = insuCardSalesService.getVcInsuCardSalesRecord(idVcInsuCardSalesRecord);
            vcInsuCardSalesDetailList = insuCardSaleRecordVo.getVcInsuCardSalesDetailList();
            for (VcInsuCardSalesDetail det : vcInsuCardSalesDetailList) {
                det.setVcInsuCardSalesRecord(null);
            }
            this.detListJson = TFJSON.toJSONString(vcInsuCardSalesDetailList);
            insuCardSaleRecordVo.setVcInsuCardSalesDetailList(null);
            actionType = "view";
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
     * 查询激活卡销售
     * 
     * @return
     * @throws Exception
     */
    public String queryInsuCardSales() throws Exception {
        logger.info("访问/insucardJson/queryInsuCardSales查询激活卡销售...");

        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        try {
            Page resultPage = insuCardSalesService.queryInsuCardSalesRecordList(queryDto, userInfo, page, rows);

            vcInsuCardSalesRecordList = (List<VcInsuCardSalesRecord>) resultPage.getResult();

            // 总页数
            total = (int) resultPage.getTotalPageCount();
            // 总记录数
            records = (int) resultPage.getTotalCount();
        } catch (BusinessException be) {
            be.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

    /**
     * 保存激活卡销售
     * 
     * @return
     */
    public String saveInsuCardSales() throws BusinessException {
        logger.info("访问/insucardJson/saveInsuCardSales?保存激活卡销售...");

        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");

        String VcInsuCardSalesDetailsStr = getRequest().getParameter("jsonStr");

        vcInsuCardSalesDetailList = TFJSON.parseArray(VcInsuCardSalesDetailsStr, VcInsuCardSalesDetail.class);
        insuCardSaleRecordVo.setVcInsuCardSalesDetailList(vcInsuCardSalesDetailList);
        try {
            insuCardSalesService.saveInsuCardSales(insuCardSaleRecordVo, userInfo, type, actionType);
            resultMsg = "操作成功！";
            actionType = "savaSuccess";
            return SUCCESS;
        } catch (BusinessException be) {            
            this.setMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            this.detListJson = TFJSON.toJSONString(vcInsuCardSalesDetailList);
            if("insert".equals(actionType)){
                return "insertFail"; 
            }else {
                //"update".equals(actionType)           
                return "updateFail"; 
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.setMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }

    }

    /**
     * 激活卡销售提交
     * 
     * @return
     */
    public String submitInsuCardSales() {
        logger.info("访问/insucardJson/initInsuCardSalesAdd激活卡销售提交...");

        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            insuCardSaleRecordVo = insuCardSalesService.getVcInsuCardSalesRecord(idVcInsuCardSalesRecord);
            insuCardSalesService.executeSubmitInsuCardSales(insuCardSaleRecordVo, userInfo);
            resultMsg = "操作成功！";
            return SUCCESS;
        } catch (BusinessException be) {
            be.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }

    }

    /**
     * 激活卡缴费申请撤销
     * 
     * @return
     */
    public String doCancelPayFee() {
        logger.info("访问/insucardJson/doCancelPayFee激活卡缴费申请撤销...");

        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            insuCardSalesService.executeCancelPayFee(idVcInsuCardSalesRecord, userInfo);
            resultMsg = "操作成功！";
        } catch (BusinessException be) {
            be.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

    /**
     * 逻辑删除激活卡销售、退卡状态修改
     * 
     * @return
     * @throws BusinessException
     */
    public String deleteRecord() throws BusinessException {
        logger.info("访问/insucardJson/deleteRecord激活卡销售、退卡记录状态修改...");
        try {
            VcInsuCardSalesRecord tempVo = insuCardSalesService.getVcInsuCardSalesRecord(idVcInsuCardSalesRecord);
            if (tempVo == null) {
                throw new BusinessException("待删除记录不存在");
            }
            // 记录状态（0删除/1新建/2等待缴费/4已缴费成功/5缴费失败/6销售撤销/7撤销失败 
            //20退卡删除/ 21退卡新增/22等待退费/23退费成功/24退费失败(账户原因)/25退费失败(非账户原因)）
            String status = tempVo.getSalesRecordStatus();
            if ("P".equals(tempVo.getSaleRefundFlag())) {
                if ("1".equals(status) || "6".equals(status)) {
                    insuCardSalesService.updateSalesRecordStatus(idVcInsuCardSalesRecord, "0");
                } else {
                    throw new BusinessException("不是【新建】或【销售撤销】的记录，不能删除！");
                }
            } else {
                if ("21".equals(status) || "25".equals(status)) {
                    insuCardSalesService.updateSalesRecordStatus(idVcInsuCardSalesRecord, "20");
                } else {
                    throw new BusinessException("不是【退卡新增】或【退费失败(非账户原因)】的记录，不能删除！");
                }
            }
            resultMsg = "删除成功！";
        } catch (BusinessException be) {
            be.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

    /**
     * 根据销售主表id获取销售子表jsonList
     */
    public String queryJsonSalesDetByRecordId() throws BusinessException {
        logger.info("访问/baseinfo/queryJsondocPrtNoRuleListByDocId.do获取销售子表jsonList...");
        try {
            this.insuCardSaleRecordVo = insuCardSalesService.getVcInsuCardSalesRecord(idVcInsuCardSalesRecord);
            vcInsuCardSalesDetailList = insuCardSaleRecordVo.getVcInsuCardSalesDetailList();
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
     * 付费成功激活卡销售并处于可激活状态
     * 
     * @return 操作结果
     * @throws BusinessException
     *             异常
     */
    public String paySuccess() throws BusinessException {

        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        if (idVcInsuCardSalesRecord < 1) {
            throw new BusinessException("对象主键不存在！");
        }

        return SUCCESS;
    }

    /** 激活卡退卡操作++++++++++++Start+++++++++++++++++==激活卡退卡操作 */
    /**
     * 进入查询激活卡退卡查询页面
     * 
     * @return
     */
    public String initInsuCardRefundQuery() {
        logger.info("访问/insucard/initInsuCardRefundQuery进入查询激活卡退卡查询 页面...");
        Date nowDate = new Date();
        queryDto = new InsuCardSalesQueryVo();
        queryDto.setSaleStartDate(DateUtils.addMonth(nowDate, -2));
        queryDto.setSaleEndDate(nowDate);
        actionType="query";
        return SUCCESS;
    }

    /**
     * 初始化退卡新增
     * 
     * @return
     */
    public String initInsuCardRefundAdd() {
        logger.info("访问/insucard/initInsuCardRefundAdd进入激活卡退卡新增页面...");
        actionType = "insert";
        return SUCCESS;
    }

    /**
     * 初始化激活卡退费修改
     * 
     * @return
     */
    public String initInsuCardRefundUpdate() {
        logger.info("访问/insucard/initInsuCardSalesAdd进入激活卡退卡修改页面...");
        try {
            insuCardSaleRecordVo = insuCardSalesService.getVcInsuCardSalesRecord(idVcInsuCardSalesRecord);
            vcInsuCardSalesDetailList = insuCardSaleRecordVo.getVcInsuCardSalesDetailList();
            for (VcInsuCardSalesDetail det : vcInsuCardSalesDetailList) {
                det.setVcInsuCardSalesRecord(null);
            }
            this.detListJson = TFJSON.toJSONString(vcInsuCardSalesDetailList);
            insuCardSaleRecordVo.setVcInsuCardSalesDetailList(null);
            bankInfoDto = insuCardSaleRecordVo.getVcPayFeeAccount();
            insuCardSaleRecordVo.setVcPayFeeAccount(null);
            actionType = "update";
        } catch (Exception be) {
            be.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            // return "fail";
            return NONE;
        }
        return SUCCESS;
    }

    /**
     * 激活卡退卡查看
     * 
     * @return
     */
    public String viewInsuCardRefund() {
        logger.info("访问/insucard/viewInsuCardRefund进入激活卡退卡查看页面...");
        try {
            insuCardSaleRecordVo = insuCardSalesService.getVcInsuCardSalesRecord(idVcInsuCardSalesRecord);
            vcInsuCardSalesDetailList = insuCardSaleRecordVo.getVcInsuCardSalesDetailList();
            for (VcInsuCardSalesDetail det : vcInsuCardSalesDetailList) {
                det.setVcInsuCardSalesRecord(null);
            }
            this.detListJson = TFJSON.toJSONString(vcInsuCardSalesDetailList);
            insuCardSaleRecordVo.setVcInsuCardSalesDetailList(null);
            bankInfoDto = insuCardSaleRecordVo.getVcPayFeeAccount();
            insuCardSaleRecordVo.setVcPayFeeAccount(null);
            actionType = "view";
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
     * 保存激活卡退卡记录
     * 
     * @return
     */
    public String saveInsuCardRefund() throws BusinessException {
        logger.info("访问/insucardJson/saveInsuCardSales?保存激活卡退卡...");

        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");

        String VcInsuCardSalesDetailsStr = getRequest().getParameter("jsonStr");

        vcInsuCardSalesDetailList = TFJSON.parseArray(VcInsuCardSalesDetailsStr, VcInsuCardSalesDetail.class);
        insuCardSaleRecordVo.setVcInsuCardSalesDetailList(vcInsuCardSalesDetailList);
        try {
            insuCardSalesService.saveInsuCardRefund(insuCardSaleRecordVo, bankInfoDto, userInfo, type, actionType);
            resultMsg = "操作成功！";
            actionType = "savaSuccess";
            return SUCCESS;
        } catch (BusinessException be) {           
            this.setMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            this.detListJson = TFJSON.toJSONString(vcInsuCardSalesDetailList);
            if("insert".equals(actionType)){
                return "insertFail"; 
            }else {
                //"update".equals(actionType)           
                return "updateFail"; 
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.setMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }

    }

    /**
     * 激活卡申请退款
     * 
     * @return
     */
    public String doRefund() {
        logger.info("访问/insucardJson/initInsuCardSalesAdd激活卡申请退款...");
        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            insuCardSalesService.executeSubmitRefund(idVcInsuCardSalesRecord, userInfo);
            resultMsg = "操作成功！";
        } catch (BusinessException be) {
            be.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

    /**
     * 初始化账户修改修改
     * 
     * @return
     */
    public String initAccountUpdate() {
        logger.info("访问/insucard/initAccountUpdate进入账户修改页面...");
        try {
            insuCardSaleRecordVo = insuCardSalesService.getVcInsuCardSalesRecord(idVcInsuCardSalesRecord);
            insuCardSaleRecordVo.setVcInsuCardSalesDetailList(null);
            bankInfoDto = insuCardSaleRecordVo.getVcPayFeeAccount();
            insuCardSaleRecordVo.setVcPayFeeAccount(null);
            actionType = "update";
        } catch (Exception be) {
            be.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            // return "fail";
            return NONE;
        }
        return SUCCESS;
    }

    /**
     * 保存并同步账户信息
     * 
     * @return
     */

    public String submitAccountModify() {
        logger.info("访问/insucardJson/submitAccountModify保存并同步账户信息...");
        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            insuCardSalesService.executeAccountModify(idVcInsuCardSalesRecord, bankInfoDto, userInfo);
            resultMsg = "操作成功！";
        } catch (BusinessException be) {
            be.printStackTrace();
            // resultMsg = "操作失败：" + be.getMessage();
            // return "fail";
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

    /** 激活卡退卡操作+++++++++++++end++++++++++++++++==激活卡退卡操作 */

    // /------------------------

    /**
     * @return the vcInsuCardSalesRecordList
     */
    public List<VcInsuCardSalesRecord> getVcInsuCardSalesRecordList() {
        return vcInsuCardSalesRecordList;
    }

    /**
     * @return the queryDto
     */
    public InsuCardSalesQueryVo getQueryDto() {
        return queryDto;
    }

    /**
     * @param queryDto
     *            the queryDto to set
     */
    public void setQueryDto(InsuCardSalesQueryVo queryDto) {
        this.queryDto = queryDto;
    }

    /**
     * @param vcInsuCardSalesRecordList
     *            the vcInsuCardSalesRecordList to set
     */
    public void setVcInsuCardSalesRecordList(List<VcInsuCardSalesRecord> vcInsuCardSalesRecordList) {
        this.vcInsuCardSalesRecordList = vcInsuCardSalesRecordList;
    }

    /**
     * @return the insuCardSaleRecordVo
     */
    public VcInsuCardSalesRecord getInsuCardSaleRecordVo() {
        return insuCardSaleRecordVo;
    }

    /**
     * @param insuCardSaleRecordVo
     *            the insuCardSaleRecordVo to set
     */
    public void setInsuCardSaleRecordVo(VcInsuCardSalesRecord insuCardSaleRecordVo) {
        this.insuCardSaleRecordVo = insuCardSaleRecordVo;
    }

    /**
     * @return the vcInsuCardSalesDetailList
     */
    public List<VcInsuCardSalesDetail> getVcInsuCardSalesDetailList() {
        return vcInsuCardSalesDetailList;
    }

    /**
     * @param vcInsuCardSalesDetailList
     *            the vcInsuCardSalesDetailList to set
     */
    public void setVcInsuCardSalesDetailList(List<VcInsuCardSalesDetail> vcInsuCardSalesDetailList) {
        this.vcInsuCardSalesDetailList = vcInsuCardSalesDetailList;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the actionType
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * @param actionType
     *            the actionType to set
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    /**
     * @return the idVcInsuCardSalesRecord
     */
    public Long getIdVcInsuCardSalesRecord() {
        return idVcInsuCardSalesRecord;
    }

    /**
     * @param idVcInsuCardSalesRecord
     *            the idVcInsuCardSalesRecord to set
     */
    public void setIdVcInsuCardSalesRecord(Long idVcInsuCardSalesRecord) {
        this.idVcInsuCardSalesRecord = idVcInsuCardSalesRecord;
    }

    /**
     * @return the resultMsg
     */
    public String getResultMsg() {
        return resultMsg;
    }

    /**
     * @param resultMsg
     *            the resultMsg to set
     */
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    /**
     * @return the detListJson
     */
    public String getDetListJson() {
        return detListJson;
    }

    /**
     * @param detListJson
     *            the detListJson to set
     */
    public void setDetListJson(String detListJson) {
        this.detListJson = detListJson;
    }

    /**
     * @return the bankInfoDto
     */
    public VcPayFeeAccount getBankInfoDto() {
        return bankInfoDto;
    }

    /**
     * @param bankInfoDto
     *            the bankInfoDto to set
     */
    public void setBankInfoDto(VcPayFeeAccount bankInfoDto) {
        this.bankInfoDto = bankInfoDto;
    }

    /**
     * @return the saleRefundFlag
     */
    public String getSaleRefundFlag() {
        return saleRefundFlag;
    }

    /**
     * @param saleRefundFlag
     *            the saleRefundFlag to set
     */
    public void setSaleRefundFlag(String saleRefundFlag) {
        this.saleRefundFlag = saleRefundFlag;
    }

}
