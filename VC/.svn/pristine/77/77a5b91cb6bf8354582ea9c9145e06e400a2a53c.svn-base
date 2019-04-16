package com.tapi.tcs.vc.store.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.tf.common.vo.Message;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDocInStore;
import com.tapi.tcs.vc.schema.model.VcDocInStoreDet;
import com.tapi.tcs.vc.store.service.DocInStoreManageService;

/**
 * 印刷入库管理Action
 * <p>
 * Date: 2013-03-28
 * </p>
 * <p>
 * Module: 印刷入库管理
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * @author Leo
 * @version 1.0
 */

public class DocInStoreManageAction extends TFAction {

    private static final long serialVersionUID = -2248031194541049469L;

    /** 上传的文件 */
    private File file;
    /** 文件名称 */
    private String fileFileName;
    /** 文件类型 */
    private String fileContentType;
    /** 印刷入库service */
    private DocInStoreManageService docInStoreManageService;
    /** 印刷入库详细信息list */
    private List<VcDocInStoreDet> vcDocInStoreDet;
    /** 入库申请单状态（0删除/1新建/2等待审批/3审批通过/4审批退回） */
    private String inStoreStatus;
    /** 申请起始日期 */
    private Date applyStartDate;
    /** 申请终止日期 */
    private Date applyEndDate;
    /** 印刷入库 List */
    private List vcDocInStores;
    /** 印刷入库 id */
    private String id;
    /** 印刷入库对象 */
    private VcDocInStore vcDocInStore;
    /** 审批List */
    private List<VcApprove> vcApprove;
    /** 印刷入库单号 */
    private String inStoreAppCode;
    /** 操作类型 */
    private String actionType;
    /** 操作结果 */
    private String result;
    /** 申请原因 */
    private String applyReason;
    /** 页面显示json字符串 */
    private String jsonStr;
    //add by chy 20131029 黑龙江地税 begin
    /**黑龙江地区标志*/
    private String hljBuyFlag;
    /**领购日期*/
    private Date buyDate;
    /**单证发票限额*/
    private BigDecimal moneyLimit;
    /**电子码*/
    private String electronicCode;
    //add by chy 20131029 黑龙江地税 end

    /**
     * 进入印刷入库查询页面
     * 
     * @return
     */
    public String initDocInStoreQuery() {
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
    public String queryDocInStore() throws BusinessException{
        logger.info("访问/queryDocInStore.action?ajax=true分页查询印刷入库申请信息...");
         try{
        // 只可查询本机构的入库申请。
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        String comCode = userInfo.getComCode();
        VcDocInStore vcDocInStore = new VcDocInStore();
        vcDocInStore.setApplyOrgCode(comCode);
        vcDocInStore.setInStoreStatus(inStoreStatus);
        vcDocInStore.setInStoreAppCode(inStoreAppCode);
        vcDocInStore.setApplyStartDate(applyStartDate);
        vcDocInStore.setApplyEndDate(applyEndDate);
        Page pages = docInStoreManageService.queryVcDocInStore(vcDocInStore, page, rows);

        vcDocInStores = pages.getResult();
        // 总页数
        total = (int) pages.getTotalPageCount();
        // 总记录数
        records = (int) pages.getTotalCount();
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
     * 进入印刷入库添加页面
     * 
     * @return
     */
    public String initAddDocInStore() {
        logger.info("访问/store/initAddDocInStore.action进入印刷入库添加页面...");
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        String comName = userInfo.getComName();
        String userName = userInfo.getUserName();
        vcDocInStore = new VcDocInStore();
        vcDocInStore.setApplyOprName(userName);
        vcDocInStore.setApplyOrgName(comName);
        vcDocInStore.setApplyTime(new Date());
        actionType = "add";
        //add by chy 20131029 黑龙江地税 begin
        String comCode = userInfo.getComCode();
        if(StringUtils.isNotEmpty(comCode) && comCode.length()>=4
        		&& SysConst.COMCODE_HLJ.equals(comCode.substring(0, 4))){
        	hljBuyFlag = "1";
        }
        //add by chy 20131029 黑龙江地税 end
        return SUCCESS;
    }

    /**
     * 保存印刷入库
     * 
     * @return
     * @throws Exception
     */
    public String saveDocInStore() throws Exception {
        logger.info("访问/strore/saveDocInStore.action保存印刷入库申请信息...");
        Message message = new Message();
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        String userCode = userInfo.getUserCode();
        String orgCode = userInfo.getComCode();
        try {
            if (this.getFile() != null) {
                if (!fileContentType.equals("image/x-png") && !fileContentType.equals("image/pjpeg")
                        && !fileContentType.equals("image/jpeg") && !fileContentType.equals("image/png")) {
                    throw new BusinessException("请上传jpg或png格式的图片 ");
                }

                if (this.getFile().length() > SysConst.MB * 2) {
                    throw new BusinessException("文件不可超过2M");
                }
            }
            // 保存/提交
            String actionType2 = getRequest().getParameter("actionType2");
            // 文件存放路径
            String realPath = SysConst.DOC_IN_STORE_REG_FORM_SAVE_PATH;
            // 申请原因
            // String applyReason = getRequest().getParameter("applyReason");
            File savefile = null;
            VcDocInStore vcDocInStore = new VcDocInStore();

            if (file != null) {
                savefile = new File(new File(realPath), fileFileName);
            }

            if ("add".equals(actionType)) {
                if (file == null) {
                    throw new BusinessException("文件不能为空");
                }
                if (savefile.exists()) {
                    throw new BusinessException("文件已存在 ");
                }
            } else if ("modify".equals(actionType)) {
                vcDocInStore.setId(Long.valueOf(id));
            }

            // 申请原因
            vcDocInStore.setApplyReason(applyReason);
            // 入库操作人代码
            vcDocInStore.setApplyOprCode(userCode);
            // 申请机构代码
            vcDocInStore.setApplyOrgCode(orgCode);
            //add by chy 20131029 黑龙江地税 begin
            if(buyDate != null){
            	vcDocInStore.setBuyDate(buyDate);
            }
            if(moneyLimit !=null){
            	vcDocInStore.setMoneyLimit(moneyLimit);
            }
            if(StringUtils.isNotEmpty(electronicCode)){
            	vcDocInStore.setElectronicCode(electronicCode);
            }
            //add by chy 20131029 黑龙江地税 end

            if (file != null) {
                // 入库登记表扫描件名称
                vcDocInStore.setFileName(fileFileName);
                // 入库登记表扫描件路径
                vcDocInStore.setFilePath(realPath);
            }

            vcDocInStoreDet = TFJSON.parseArray(jsonStr, VcDocInStoreDet.class);
            result = docInStoreManageService.saveVcDocInStore(vcDocInStore, vcDocInStoreDet, actionType, actionType2,
                    file);

            if (Beans.isNotEmpty(result)) {
                throw new BusinessException(result);
            }

            // 保存文件
            // String realpath =
            // ServletActionContext.getServletContext().getRealPath("/common");
            if (file != null) {
                if (!savefile.getParentFile().exists())
                    savefile.getParentFile().mkdirs();
                FileUtils.copyFile(file, savefile);
            }
            message.setType("success");
            message.setMessage("保存成功!");
        } catch (Exception e) {
            message.setType("error");
            message.setMessage(e.getMessage());
        }
        messageInfo = TFJSON.toJSONString(message);

        if ("success".equals(message.getType())) {
            Date nowDate = new Date();
            applyStartDate = DateUtils.addMonth(nowDate, -2);
            applyEndDate = nowDate;
            return SUCCESS;
        } else {
            vcDocInStore = new VcDocInStore();
            vcDocInStore.setApplyOprName(userInfo.getUserName());
            vcDocInStore.setApplyOrgName(userInfo.getComName());
            vcDocInStore.setApplyTime(new Date());
            vcDocInStore.setApplyReason(applyReason);
            actionType = "add";
            return "fail";
        }
    }

    /**
     * 查看印刷入库详情
     * 
     * @return
     * @throws Exception
     */
    public String viewDocInStore() throws Exception {
        logger.info("访问/strore/viewDocInStore.action查看印刷入库申请信息...");
        List resultList = docInStoreManageService.viewVcDocInStore(Long.valueOf(id));
        vcDocInStore = (VcDocInStore) resultList.get(0);
        vcDocInStoreDet = vcDocInStore.getVcDocInStoreDets();
        vcApprove = (List<VcApprove>) resultList.get(1);
        //add by chy 20131029 黑龙江地税 begin
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        String comCode = userInfo.getComCode();
        if(StringUtils.isNotEmpty(comCode) && comCode.length()>=4
        		&& SysConst.COMCODE_HLJ.equals(comCode.substring(0, 4))){
        	hljBuyFlag = "1";
        }
        //add by chy 20131029 黑龙江地税 end
        if ("modify".equals(actionType)) {
            for (Iterator iterator = vcDocInStoreDet.iterator(); iterator.hasNext();) {
                VcDocInStoreDet vcDocInStoreDet = (VcDocInStoreDet) iterator.next();
                vcDocInStoreDet.setVcDocInStore(null);
            }
            jsonStr = TFJSON.toJSONString(vcDocInStoreDet);
            return "modify";
        } else {
            return SUCCESS;
        }
    }

    /**
     * 删除印刷入库
     * 
     * @return
     */
    public String deleteDocInStore() {
        logger.info("访问/strore/deleteDocInStore.action删除印刷入库申请信息...");
        try {
            result = docInStoreManageService.deleteVcDocInStore(id);
        } catch (Exception e) {
            result = e.getMessage();
        }
        return SUCCESS;
    }

    /**
     * 提交印刷入库申请
     * 
     * @return
     */
    public String submitDocInStore() {
        logger.info("访问/strore/submitDocInStore.action提交印刷入库申请信息...");
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        String userCode = userInfo.getUserCode();
        String orgCode = userInfo.getComCode();
        try {
            result = docInStoreManageService.executeSubmitDocInStore(id, userCode, orgCode);
        } catch (Exception e) {
            result = e.getMessage();
        }
        return SUCCESS;
    }

    /**
     * 进入印刷入库审批查询页面
     * 
     * @return
     */
    public String initDocInStoreApproveQuery() {
        Date nowDate = new Date();
        applyStartDate = DateUtils.addMonth(nowDate, -2);
        applyEndDate = nowDate;
        return SUCCESS;
    }

    /**
     * 印刷入库审批查询
     * 
     * @return
     */
    public String queryDocInStoreApprove() throws BusinessException{
        logger.info("访问/queryDocInStoreApprove.action查询印刷入库审批信息...");
        try{
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        String orgCode = userInfo.getComCode();
        // 只可查询本机构的入库申请。 待完善
        VcDocInStore vcDocInStore = new VcDocInStore();
        vcDocInStore.setInStoreAppCode(inStoreAppCode);
        vcDocInStore.setInStoreStatus(inStoreStatus);
        vcDocInStore.setApplyStartDate(applyStartDate);
        vcDocInStore.setApplyEndDate(applyEndDate);
        vcDocInStore.setCheckOrgCode(orgCode);
        Page pages = docInStoreManageService.queryDocInStoreApprove(vcDocInStore, page, rows);

        vcDocInStores = pages.getResult();

        // 总页数
        total = (int) pages.getTotalPageCount();
        // 总记录数
        records = (int) pages.getTotalCount();
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
     * 进入印刷入库审批页面
     * 
     * @return
     * @throws Exception
     */
    public String initDocInStoreApprove() throws Exception {
        logger.info("访问/store/initDocInStoreApprove.action进入印刷入库审批页面...");
        List resultList = docInStoreManageService.viewVcDocInStore(Long.valueOf(id));
        vcDocInStore = (VcDocInStore) resultList.get(0);
        vcDocInStoreDet = vcDocInStore.getVcDocInStoreDets();
        vcApprove = (List<VcApprove>) resultList.get(1);
        //add by chy 20131029 黑龙江地税 begin
        String comCode = vcDocInStore.getApplyOrgCode();
        if(StringUtils.isNotEmpty(comCode) && comCode.length()>=4
        		&& SysConst.COMCODE_HLJ.equals(comCode.substring(0, 4))){
        	hljBuyFlag = "1";
        }
        //add by chy 20131029 黑龙江地税 end
        return SUCCESS;
    }

    /**
     * 印刷入库审批
     * 
     * @return
     */
    public String approveDocInStore() {
        logger.info("访问/store/approveDocInStore.action印刷入库审批...");
        Message message = new Message();
        try {
            String actionType = getRequest().getParameter("checkStatus");
            String approveOpp = getRequest().getParameter("approveOpp");
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            // 获取当前用户的用户代码
            String userCode = userInfo.getUserCode();
            // 获取当前用户的机构代码
            String comCode = userInfo.getComCode();

            // 2、插入审批记录
            VcApprove vcApprove = new VcApprove();
            vcApprove.setCheckOrgId(comCode);// 审批机构：当前登录用户的机构代码
            vcApprove.setCheckOprId(userCode);// 审批人：当前登录用户代码
            vcApprove.setCheckStatus(actionType);// 审批状态：0提交/1审批退回/2审批同意
            vcApprove.setCheckExpl(approveOpp);// 审批意见

            result = docInStoreManageService.executeApproveDocInStore(id, vcApprove);
        } catch (Exception e) {
            message.setType("error");
            message.setMessage(e.getMessage());
        }
        messageInfo = TFJSON.toJSONString(message);
        return SUCCESS;
    }

    public void setDocInStoreManageService(DocInStoreManageService docInStoreManageService) {
        this.docInStoreManageService = docInStoreManageService;
    }

    public List<VcDocInStoreDet> getVcDocInStoreDet() {
        return vcDocInStoreDet;
    }

    public void setVcDocInStoreDet(List<VcDocInStoreDet> vcDocInStoreDet) {
        this.vcDocInStoreDet = vcDocInStoreDet;
    }

    public void setInStoreStatus(String inStoreStatus) {
        this.inStoreStatus = inStoreStatus;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VcDocInStore getVcDocInStore() {
        return vcDocInStore;
    }

    public void setVcDocInStore(VcDocInStore vcDocInStore) {
        this.vcDocInStore = vcDocInStore;
    }

    public List<VcApprove> getVcApprove() {
        return vcApprove;
    }

    public void setVcApprove(List<VcApprove> vcApprove) {
        this.vcApprove = vcApprove;
    }

    public List getVcDocInStores() {
        return vcDocInStores;
    }

    public void setVcDocInStores(List vcDocInStores) {
        this.vcDocInStores = vcDocInStores;
    }

    public String getInStoreAppCode() {
        return inStoreAppCode;
    }

    public void setInStoreAppCode(String inStoreAppCode) {
        this.inStoreAppCode = inStoreAppCode;
    }

    public String getInStoreStatus() {
        return inStoreStatus;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
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

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

	public String getHljBuyFlag() {
		return hljBuyFlag;
	}

	public void setHljBuyFlag(String hljBuyFlag) {
		this.hljBuyFlag = hljBuyFlag;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public BigDecimal getMoneyLimit() {
		return moneyLimit;
	}

	public void setMoneyLimit(BigDecimal moneyLimit) {
		this.moneyLimit = moneyLimit;
	}

	public String getElectronicCode() {
		return electronicCode;
	}

	public void setElectronicCode(String electronicCode) {
		this.electronicCode = electronicCode;
	}
}
