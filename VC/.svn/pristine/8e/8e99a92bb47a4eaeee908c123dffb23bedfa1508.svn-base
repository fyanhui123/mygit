package com.tapi.tcs.vc.store.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.TakeNoDao;
import com.tapi.tcs.vc.common.service.ApproveService;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.invoice.service.HeilongjiangInvoiceService;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDocInStore;
import com.tapi.tcs.vc.schema.model.VcDocInStoreDet;
import com.tapi.tcs.vc.schema.model.VcInvoiceBuyHlj;
import com.tapi.tcs.vc.schema.model.VcManageCodeHlj;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.dao.DocInStoreManageDAO;
import com.tapi.tcs.vc.store.dao.VcStorageDao;
import com.tapi.tcs.vc.store.service.DocInStoreManageService;

/**
 * 印刷入库Service
 * <p>
 * Date 2013-03-28
 * </p>
 * <p>
 * Module：印刷入库
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * 
 * @author Leo
 * @version 1.0
 */
public class DocInStoreManageServiceImpl implements DocInStoreManageService {

    /**
     * 印刷入库DAO
     */
    private DocInStoreManageDAO docInStoreManageDAO;
    /**
     * 审批DAO
     */
    private ApproveDao approveDao;
    /**
     * 库存DAO
     */
    private VcStorageDao vcStorageDao;
    /**
     * 取流水号DAO
     */
    private TakeNoDao takeNoDao;

    /**
     * 级别DAO
     */
    private VcLevelDao vcLevelDao;
    
    private HeilongjiangInvoiceService heilongjiangInvoiceService;

    /**
     * 查询印刷入库信息
     * 
     * @param vcDocInStore
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page queryVcDocInStore(VcDocInStore vcDocInStore, int pageNo, int pageSize) throws BusinessException {
    	try{
        return docInStoreManageDAO.queryVcDocInStore(vcDocInStore, pageNo, pageSize, null);
    	}catch (DaoException e) {

        	throw new BusinessException(e.getMessage(),e);
        }
    }

    /**
     * 保存印刷入库信息
     * 
     * @param vcDocInStore
     * @param vcDocInStoreDets
     * @param actionType
     *            新增/修改
     * @param actionType2
     *            保存/提交
     * @throws Exception
     */
    @Override
    public String saveVcDocInStore(VcDocInStore vcDocInStore, List<VcDocInStoreDet> vcDocInStoreDets,
            String actionType, String actionType2, File file) throws BusinessException{
        String checkResult = checkDoc(vcDocInStoreDets);
        if (checkResult.length() > 0) {
            return checkResult;
        }
     try{
        Date nowDate = new Date();
        // 申请原因
        String applyReason = vcDocInStore.getApplyReason();
        // 入库操作人代码
        String userCode = vcDocInStore.getApplyOprCode();
        // 申请机构代码
        String orgCode = vcDocInStore.getApplyOrgCode();
       
        // 原文件名 修改时可能需要删除
        String fileName_old = "";
        if ("modify".equals(actionType)) {
            String fileName_new = vcDocInStore.getFileName();
            vcDocInStore = docInStoreManageDAO.get(vcDocInStore.getId());
            fileName_old = vcDocInStore.getFileName();

            if (file != null) {
                File savefile = new File(new File(vcDocInStore.getFilePath()), fileName_new);
                if (!fileName_old.equals(fileName_new) && savefile.exists()) {
                    Assert.isTrue(false, "文件已存在 ");
                }
                vcDocInStore.setFileName(fileName_new);
            }
            // 入库申请单状态（0删除/1新建/2等待审批/3审批退回/4审批通过）
            if (!"1".equals(vcDocInStore.getInStoreStatus()) && !"3".equals(vcDocInStore.getInStoreStatus())) {
                return "状态错误，请刷新后重试 ";
            }
            // 删除原vcDocInStoreDets
            String sql = "DELETE FROM vc_doc_in_store_det t WHERE t.id_vc_doc_in_store = ? ";
            docInStoreManageDAO.updateBySQL(sql, vcDocInStore.getId());
            vcDocInStore.setApplyReason(applyReason);
        } else if ("add".equals(actionType)) {
            // 印刷入库申请单号
            String inStoreAppCode = takeNoDao.getNo("DS");
            vcDocInStore.setInStoreAppCode(inStoreAppCode);
            vcDocInStore.setApplyTime(nowDate);
            vcDocInStore.setCreatedBy(userCode);
            vcDocInStore.setDateCreated(nowDate);
        }
        // 将印刷入库申请单号set到详情表
        if (vcDocInStoreDets != null) {
            for (Iterator iterator = vcDocInStoreDets.iterator(); iterator.hasNext();) {
                VcDocInStoreDet vcDocInStoreDet = (VcDocInStoreDet) iterator.next();
                vcDocInStoreDet.setVcDocInStore(vcDocInStore);
                vcDocInStoreDet.setCreateOprCode(vcDocInStore.getApplyOprCode());
                vcDocInStoreDet.setCreateTime(nowDate);
                vcDocInStoreDet.setModifyOprCode(vcDocInStore.getApplyOprCode());
                vcDocInStoreDet.setModifyTime(nowDate);
            }
        }

        vcDocInStore.setVcDocInStoreDets(vcDocInStoreDets);

        // 当操作为提交时
        if ("submit".equals(actionType2)) {
            // 入库申请单状态（0删除/1新建/2等待审批/3审批退回/4审批通过）
            vcDocInStore.setInStoreStatus("2");
            // 审批机构
            String upperOrgCode = vcLevelDao.getUpperOrgCode(userCode);
            vcDocInStore.setCheckOrgCode(upperOrgCode);

        } else if ("save".equals(actionType2)) {// 当操作为保存时
            vcDocInStore.setInStoreStatus("1");
        }

        vcDocInStore.setModifyOprCode(userCode);
        vcDocInStore.setModifyTime(nowDate);
        vcDocInStore.setUpdatedBy(userCode);
        vcDocInStore.setDateUpdated(nowDate);
        docInStoreManageDAO.saveVcDocInStore(vcDocInStore);

        // 当操作为提交时
        if ("submit".equals(actionType2)) {
            VcApprove vcApprove = new VcApprove();
            // 申请编号
            vcApprove.setApplyId(vcDocInStore.getId());
            // I -印刷入库
            vcApprove.setApplyType("I");
            // 审批机构
            vcApprove.setCheckOrgId(orgCode);
            // 审批人
            vcApprove.setCheckOprId(userCode);
            // 审批状态
            vcApprove.setCheckStatus("0");
            // 审批时间
            vcApprove.setCheckTime(nowDate);
            // 审批意见
            vcApprove.setCheckExpl("提交");
            // 创建人
            vcApprove.setCreatedBy(userCode);
            // 创建时间
            vcApprove.setDateCreated(nowDate);
            // 修改人
            vcApprove.setUpdatedBy(userCode);
            // 修改时间
            vcApprove.setDateUpdated(nowDate);
            approveDao.save(vcApprove);
        }

        // 删除文件
        if ("modify".equals(actionType) && file != null) {
            File f = new File(vcDocInStore.getFilePath() + "/" + fileName_old);
            if (f.isFile() && f.exists()) {
                f.delete();
            }
        }}catch(Exception e) {

        	throw new BusinessException(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 检验单证信息 1.是否为空 2.单证类型是否重复 3.流水号是否正确
     * 
     * @param vcDocInStoreDets
     * @return
     */
    private String checkDoc(List<VcDocInStoreDet> vcDocInStoreDets) throws BusinessException{
    	try{
        StringBuffer str = new StringBuffer();
        if (vcDocInStoreDets.size() == 0) {
            return "单证信息不能为空";
        }

        for (Iterator iterator = vcDocInStoreDets.iterator(); iterator.hasNext();) {
            VcDocInStoreDet vcDocInStoreDet = (VcDocInStoreDet) iterator.next();
            String startStr = vcDocInStoreDet.getStartNum();
            String endStr = vcDocInStoreDet.getEndNum();
            /*
             * if (startStr.length() != 10 || endStr.length() != 10 &&
             * (startStr.length() != 16 || endStr.length() != 16)) {
             * str.append("请输入10位或者16位的流水号[" + startStr + "-" + endStr + "]\n");
             * continue; }
             */

            String startStr_ = startStr.length() == 16 ? startStr.substring(6) : startStr;
            String endStr_ = endStr.length() == 16 ? endStr.substring(6) : endStr;

            if (!startStr_.matches("[0-9]*") || !endStr_.matches("[0-9]*")) {
                str.append("流水号[" + startStr + "-" + endStr + "]错误\n");
                continue;
            }

            Long start = Long.valueOf(startStr_);
            Long end = Long.valueOf(endStr_);

            if (end < start) {
                str.append("起始流水号大于终止流水号[" + startStr + "-" + endStr + "]");
                continue;
            }
        }
        return str.toString();
    	}catch(Exception e) {
        	throw new BusinessException(e.getMessage(),e);
        }
    }

    /**
     * 查看印刷入库信息
     * 
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public List viewVcDocInStore(Long id) throws BusinessException {
    	try{
           return docInStoreManageDAO.viewVcDocInStore(id);
    	}catch (DaoException e) {
        	throw new BusinessException(e.getMessage(),e);
        }
    }

    /**
     * 删除印刷入库信息
     * 
     * @param id
     */
    @Override
    public String deleteVcDocInStore(String id) throws BusinessException{
        String result = "操作成功";
        String[] ids = id.split(",");
        try{
        if (ids != null && ids.length > 0) {
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < ids.length; i++) {
                // 锁表操作
                docInStoreManageDAO.lockDocInStore(Long.valueOf(ids[i]));
                VcDocInStore vcDocInStore = docInStoreManageDAO.findVcDocInStoreByPK(Long.valueOf(ids[i]));
                // 入库申请单状态 0删除/1新建/2等待审批/3审批通过/4审批退回
                String inStoreStatus = vcDocInStore.getInStoreStatus();
                if ("3".equals(inStoreStatus) || "1".equals(inStoreStatus)) {

                    vcDocInStore.setInStoreStatus("0");
                    //begin 审计字段（date_updated）未做更新 by zhxiao3 2014-6-18
                    vcDocInStore.setDateUpdated(new Date());
                    //end 审计字段（date_updated）未做更新 by zhxiao3 2014-6-18
                    docInStoreManageDAO.update(vcDocInStore);
                } else if ("0".equals(inStoreStatus)) {
                    str.append("入库申请[" + vcDocInStore.getInStoreAppCode() + "]已删除，不可重复提交\n");
                    continue;
                } else if ("2".equals(inStoreStatus)) {
                    str.append("入库申请[" + vcDocInStore.getInStoreAppCode() + "]已提交，不可删除\n");
                    continue;
                } else if ("4".equals(inStoreStatus)) {
                    str.append("入库申请[" + vcDocInStore.getInStoreAppCode() + "]已审批通过，不可删除\n");
                    continue;
                }
            }
            if (str.length() > 0) {
                result = str.toString();
            }
        }
        }catch (DaoException e) {
        	throw new BusinessException(e.getMessage(),e);
        }
        return result;
    }

    /**
     * 提交印刷入库信息申请
     * 
     * @param id
     * @throws Exception
     */
    @Override
    public String executeSubmitDocInStore(String id, String userCode, String orgCode) throws Exception{
        String result = "操作成功";
        String[] ids = id.split(",");
        try{
        if (ids != null && ids.length > 0) {
            Date nowDate = new Date();
            List<VcApprove> vcApproves = new ArrayList<VcApprove>();
            List<VcDocInStore> vcDocInStores = new ArrayList<VcDocInStore>();
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < ids.length; i++) {
                // 锁表
                docInStoreManageDAO.lockDocInStore(Long.valueOf(ids[i]));
                VcDocInStore vcDocInStore = docInStoreManageDAO.findVcDocInStoreByPK(Long.valueOf(ids[i]));

                // 入库申请单状态 0删除/1新建/2等待审批/3审批退回/4审批通过
                String inStoreStatus = vcDocInStore.getInStoreStatus();
                if ("1".equals(inStoreStatus) || "3".equals(inStoreStatus)) {
                    // 入库申请单状态 2.等待审批
                    vcDocInStore.setInStoreStatus("2");
                    // vcDocInStore.setCheckOprCode(userCode);
                    String upperOrgCode = vcLevelDao.getUpperOrgCode(userCode);
                    vcDocInStore.setCheckOrgCode(upperOrgCode);
                    // vcDocInStore.setCheckTime(nowDate);
                    vcDocInStores.add(vcDocInStore);

                    VcApprove vcApprove = new VcApprove();
                    // 申请编号
                    vcApprove.setApplyId(vcDocInStore.getId());
                    // 03-印刷入库
                    vcApprove.setApplyType("I");
                    // 审批机构
                    vcApprove.setCheckOrgId(orgCode);
                    // 审批人
                    vcApprove.setCheckOprId(userCode);
                    // 审批状态
                    vcApprove.setCheckStatus("0");
                    // 审批时间
                    vcApprove.setCheckExpl("提交");
                    // 审批意见
                    vcApprove.setCheckTime(nowDate);
                    // 创建人
                    vcApprove.setCreatedBy(userCode);
                    // 创建时间
                    vcApprove.setDateCreated(nowDate);
                    // 修改人
                    vcApprove.setUpdatedBy(userCode);
                    // 修改时间
                    vcApprove.setDateUpdated(nowDate);
                    vcApproves.add(vcApprove);

                } else if ("0".equals(inStoreStatus)) {
                    str.append("入库申请[" + vcDocInStore.getInStoreAppCode() + "]已删除，不可提交\n");
                } else if ("2".equals(inStoreStatus)) {
                    str.append("入库申请[" + vcDocInStore.getInStoreAppCode() + "]已提交，不可重复提交\n");
                } else if ("4".equals(inStoreStatus)) {
                    str.append("入库申请[" + vcDocInStore.getInStoreAppCode() + "]已审批通过，不可提交\n");
                }
            }
            if (str.length() > 0) {
                result = str.toString();
            }
            approveDao.saveAll(vcApproves);
            docInStoreManageDAO.saveAll(vcDocInStores);
        }}catch(DaoException e) {
        	throw new BusinessException(e.getMessage(),e);
        }
        return result;
    }

    /**
     * 印刷入库审批页面
     * 
     * @return
     */
    @Override
    public Page queryDocInStoreApprove(VcDocInStore vcDocInStore, int pageNo, int pageSize) throws BusinessException{
    	try{
        return docInStoreManageDAO.queryVcDocInStore(vcDocInStore, pageNo, pageSize, "approve");
    	}catch (DaoException e) {
        	throw new BusinessException(e.getMessage(),e);
        }
    }

    private ApproveService approveService;

    /**
     * 印刷入库审批
     * 
     * @param id
     * @param vcApprove
     */
    @Override
    public String executeApproveDocInStore(String id, VcApprove vcApprove) throws BusinessException{
        
    	String result = "操作成功";
        // 锁表
    	try{
        docInStoreManageDAO.lockDocInStore(Long.valueOf(id));
        VcDocInStore vcDocInStore = docInStoreManageDAO.findVcDocInStoreByPK(Long.valueOf(id));
        String inStoreStatus = vcDocInStore.getInStoreStatus();
     
        if ("2".equals(inStoreStatus)) {
            Date nowTime = new Date();
            // 更新印刷入库基本信息
            // CheckStatus /1审批退回/2审批同意 InStoreStatus 3审批退回/4审批通过
            if ("1".equals(vcApprove.getCheckStatus())) {
                vcDocInStore.setInStoreStatus("3");
            } else if ("2".equals(vcApprove.getCheckStatus())) {
                StringBuffer str = new StringBuffer();
                // 校验库存是否已存在
                List<VcDocInStoreDet> vcDocInStoreDets = vcDocInStore.getVcDocInStoreDets();
                for (Iterator iterator = vcDocInStoreDets.iterator(); iterator.hasNext();) {
                    VcDocInStoreDet vcDocInStoreDet = (VcDocInStoreDet) iterator.next();
                    String start = vcDocInStoreDet.getStartNum();
                    String end = vcDocInStoreDet.getEndNum();
                    
                    //同一印刷批次的号段有交叉
                    StringBuffer sb = new StringBuffer();
                    sb.append(" SELECT COUNT(*) FROM VC_STORAGE t ");
                    sb.append(" WHERE t.doc_ver_code = ?  ");
                    sb.append(" AND t.press_batch_no = ? ");
                    sb.append(" AND ( (t.start_num >= ? AND t.start_num <= ? ) OR (t.start_num <= ? AND t.end_num >= ?)) ");
                    Object[] params = { vcDocInStoreDet.getDocVerCode(), vcDocInStoreDet.getPressBatchNo(), start, end,
                            start, start };

                    int amount = Integer.valueOf(vcStorageDao.findBySQL(sb.toString(), params).get(0).toString());
                    if (amount > 0) {
                        str.append("入库申请[" + vcDocInStore.getInStoreAppCode() + "]中流水号[" + start + "-" + end
                                + "]有部分或全部在库存表存在，请修改后再提交\n");
                        continue;
                    }
                }
                if (str.length() > 0) {
                    return str.toString();
                }
                vcDocInStore.setCheckOprCode(vcApprove.getCheckOprId());
                vcDocInStore.setCheckOrgCode(vcApprove.getCheckOrgId());
                vcDocInStore.setCheckTime(nowTime);
                vcDocInStore.setInStoreStatus("4");
                // 审批通过时 需要增加或合并库存信息
                
                saveVcStorage(vcDocInStore, vcApprove.getCheckOprId());
                //add by chy 20131029 黑龙江地税-入库审批通过时往领购表插入数据 begin
                if(vcDocInStore.getApplyOrgCode().length()>=4
                		&&vcDocInStore.getApplyOrgCode().substring(0,4).equals(SysConst.COMCODE_HLJ)){
                	saveVcNoteBuyHlj(vcDocInStore);
                }
                //add by chy 20131029 黑龙江地税-入库审批通过时往领购表插入数据 end
            }

            docInStoreManageDAO.save(vcDocInStore);

            vcApprove.setApplyId(vcDocInStore.getId());// 申请编号：订单号
            vcApprove.setApplyType("I");// 申请类型：03-印刷入库
            vcApprove.setCheckTime(nowTime);// 审批时间
            // 创建人
            vcApprove.setCreatedBy(vcApprove.getCheckOprId());
            // 创建时间
            vcApprove.setDateCreated(nowTime);
            // 修改人
            vcApprove.setUpdatedBy(vcApprove.getCheckOprId());
            // 修改时间
            vcApprove.setDateUpdated(nowTime);
            approveDao.save(vcApprove);
        } else if ("3".equals(inStoreStatus)) {
            result = "入库申请[" + vcDocInStore.getInStoreAppCode() + "]已审批通过，不可重复审批";
        } else if ("4".equals(inStoreStatus)) {
            result = "入库申请[" + vcDocInStore.getInStoreAppCode() + "]已审批退回，不可重复审批";
        } else {
            result = "入库申请[" + vcDocInStore.getInStoreAppCode() + "]未提交审批 ";
        }
    	}
    	 catch(DaoException e){
        	throw new BusinessException(e.getMessage(),e);
        }
        return result;
    }

    /**
     * 保存库存信息
     * 
     * @param vcDocInStore
     * @param userCode
     *            当前操作人
     */
    private String saveVcStorage(VcDocInStore vcDocInStore, String userCode) throws BusinessException{
        List vcDocInStoreDets = vcDocInStore.getVcDocInStoreDets();
        List vcStorages = new ArrayList<VcStorage>();
        // 提交时已校验 合并
        try{
        for (Iterator iterator = vcDocInStoreDets.iterator(); iterator.hasNext();) {
            VcDocInStoreDet vcDocInStoreDet = (VcDocInStoreDet) iterator.next();

            String start = vcDocInStoreDet.getStartNum();
            String end = vcDocInStoreDet.getEndNum();

            // Date deadline = DateUtils.addDay(new Date(), 90);
            vcStorageDao.mergeStorage(start, end, vcDocInStoreDet.getDocVerCode(), vcDocInStoreDet.getPressBatchNo(),
                    vcDocInStore.getApplyOrgCode(), "S1", null, userCode,null);
        }
         docInStoreManageDAO.saveVcStorages(vcStorages);
        }catch(DaoException e){
        	throw new BusinessException(e.getMessage(),e);
        }
        
        return "操作成功";
    }
    
    /**
     * 保存黑龙江领购表
     * @param vcDocInStore
     * @throws BusinessException
     */
    private void saveVcNoteBuyHlj(VcDocInStore vcDocInStore) throws BusinessException {
    	Date sysdate = new Date();
    	try{
    		List<VcInvoiceBuyHlj> vcInvoiceBuyHljs = new ArrayList<VcInvoiceBuyHlj>();
    		List<VcDocInStoreDet> vcDocInStoreDets = vcDocInStore.getVcDocInStoreDets();
    		for(VcDocInStoreDet vcDocInStoreDet : vcDocInStoreDets){
    			VcInvoiceBuyHlj vcInvoiceBuyHlj = new VcInvoiceBuyHlj();
    			vcInvoiceBuyHlj.setDocVerCode(vcDocInStoreDet.getDocVerCode());
    			vcInvoiceBuyHlj.setBuyDate(vcDocInStore.getBuyDate());
    			vcInvoiceBuyHlj.setInvoiceCode(vcDocInStoreDet.getPressBatchNo());
    			vcInvoiceBuyHlj.setStartNum(vcDocInStoreDet.getStartNum());
    			vcInvoiceBuyHlj.setEndNum(vcDocInStoreDet.getEndNum());
    			vcInvoiceBuyHlj.setAmount(vcDocInStoreDet.getTotalAmount());
    			vcInvoiceBuyHlj.setMoneyLimit(vcDocInStore.getMoneyLimit());
    			vcInvoiceBuyHlj.setBelongField("金融保险业");
    			vcInvoiceBuyHlj.setElectronicCode(vcDocInStore.getElectronicCode());
    			
    			String orgCode = vcDocInStore.getApplyOrgCode();
    			VcManageCodeHlj vcManageCodeHlj = heilongjiangInvoiceService.findManageCodeByOrgCode(orgCode);
    			if(vcManageCodeHlj!=null){
	    			vcInvoiceBuyHlj.setOrgCode(orgCode);
	    			vcInvoiceBuyHlj.setOperatorCode(vcDocInStore.getApplyOprCode());
    			}
    			vcInvoiceBuyHlj.setCreatedBy(vcDocInStore.getCheckOprCode());
    			vcInvoiceBuyHlj.setDateCreated(sysdate);
    			vcInvoiceBuyHlj.setUpdatedBy(vcDocInStore.getCheckOprCode());
    			vcInvoiceBuyHlj.setDateUpdated(sysdate);
    			vcInvoiceBuyHljs.add(vcInvoiceBuyHlj);
    		}
    		heilongjiangInvoiceService.saveVcInvoiceBuyHljList(vcInvoiceBuyHljs);
    	}catch(Exception e){
    		throw new BusinessException("保存领购信息出错！");
    	}
    }

    public void setDocInStoreManageDAO(DocInStoreManageDAO docInStoreManageDAO) {
        this.docInStoreManageDAO = docInStoreManageDAO;
    }

    public void setApproveDao(ApproveDao approveDao) {
        this.approveDao = approveDao;
    }

    public void setApproveService(ApproveService approveService) {
        this.approveService = approveService;
    }

    public void setVcStorageDao(VcStorageDao vcStorageDao) {
        this.vcStorageDao = vcStorageDao;
    }

    public void setTakeNoDao(TakeNoDao takeNoDao) {
        this.takeNoDao = takeNoDao;
    }

    public void setVcLevelDao(VcLevelDao vcLevelDao) {
        this.vcLevelDao = vcLevelDao;
    }

	public void setHeilongjiangInvoiceService(
			HeilongjiangInvoiceService heilongjiangInvoiceService) {
		this.heilongjiangInvoiceService = heilongjiangInvoiceService;
	}
}
