package com.tapi.tcs.vc.inquiry.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.baseinfo.dao.VcTakerDao;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.VcPubCodeManagerDao;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.inquiry.dao.InquiryManageDAO;
import com.tapi.tcs.vc.inquiry.vo.DocDetailInquiryVo;
import com.tapi.tcs.vc.inquiry.vo.DocInstoreApplyInquiryVo;
import com.tapi.tcs.vc.inquiry.vo.LostVerificationInquiryVo;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDocInStore;

/**
 * 查询统计（单证印刷入库/明细查询）Dao
 * <p>
 * Date 2013-04-11
 * </p>
 * <p>
 * Module：统计查询
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * 
 * @author whj
 * @version 1.0
 */
public class InquiryManageDAOImpl extends GenericDaoHibernate<VcDocInStore> implements InquiryManageDAO {

    private VcLevelDao vcLevelDao;
    private VcTakerDao vcTakerDao;
    
    private VcPubCodeManagerDao vcPubCodeManagerDao;
    /**
     * 审批DAO
     */
    private ApproveDao approveDao;

    public void setVcLevelDao(VcLevelDao vcLevelDao) {
        this.vcLevelDao = vcLevelDao;
    }
    
    /**
     * @param vcTakerDao the vcTakerDao to set
     */
    public void setVcTakerDao(VcTakerDao vcTakerDao) {
        this.vcTakerDao = vcTakerDao;
    }

    public void setApproveDao(ApproveDao approveDao) {
        this.approveDao = approveDao;
    }    

    public void setVcPubCodeManagerDao(VcPubCodeManagerDao vcPubCodeManagerDao) {
        this.vcPubCodeManagerDao = vcPubCodeManagerDao;
    }

    /**
     * <p>
     * 查询印刷入库信息
     * </p>
     * 
     * @param vcDocInStore
     *            印刷入库对象
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page queryVcDocInStoreInquiry(DocInstoreApplyInquiryVo inStoreInquiryDto, UserInfo userInfo,
            int pageNo, int pageSize) throws DaoException {
        StringBuilder sb = new StringBuilder(100);
        sb.append("from Vc_Doc_In_Store_Det t1, Vc_Doc_Version_Info t2, Vc_Doc_In_Store t3, Vc_Doc_Type t4 ");
        sb
                .append("where t2.doc_Ver_Code=t1.doc_Ver_Code and t3.ID_VC_DOC_IN_STORE=t1.ID_VC_DOC_IN_STORE and t4.id_Vc_Doc_Type=t2.id_Vc_Doc_Type ");
        List<Object> values = new ArrayList<Object>();
        if (inStoreInquiryDto != null) {
            // 单证版本
            if (StringUtils.isNotBlank(inStoreInquiryDto.getDocVerCode())) {
                sb.append(" and t2.doc_Ver_Code like ? ");
                values.add(inStoreInquiryDto.getDocVerCode());
            }
            // 单证种类
            if (StringUtils.isNotBlank(inStoreInquiryDto.getDocTypeCode())) {
                sb.append(" and t4.doc_Type_Code like ? ");
                values.add(inStoreInquiryDto.getDocTypeCode());
            }

            // 险类//险种
            if (StringUtils.isNotBlank(inStoreInquiryDto.getInsuType())
                    || StringUtils.isNotBlank(inStoreInquiryDto.getInsuKind())) {
                sb.append(" and exists(select 1 from Vc_Doc_Insu_Kind insuKind, Vc_Doc_Rel_Insu_Kind ref , Vc_Doc_Insu_Type insuType");
                sb.append(" where insuKind.insu_Kind_Code=ref.insu_Kind_Code and  insuKind.id_Vc_Doc_Insu_Type=insuType.id_Vc_Doc_Insu_Type");
                sb.append("  and ref.id_Vc_Doc_Version_Info=t2.id_Vc_Doc_Version_Info ");
                if (StringUtils.isNotBlank(inStoreInquiryDto.getInsuType())) {
                    sb.append(" and insuType.insu_Type_Code=?");
                    values.add(inStoreInquiryDto.getInsuType());
                }
                if (StringUtils.isNotBlank(inStoreInquiryDto.getInsuKind())) {
                    sb.append(" and insuKind.insu_Kind_Code=?");
                    values.add(inStoreInquiryDto.getInsuKind());
                }
                sb.append(" ) ");
            }

            // 申请单号
            if (StringUtils.isNotBlank(inStoreInquiryDto.getInStoreAppCode())) {
                sb.append(" and t3.in_Store_App_Code like ? ");
                values.add(inStoreInquiryDto.getInStoreAppCode() + "%");
            }

            // 申请状态
            if (StringUtils.isNotBlank(inStoreInquiryDto.getInStoreStatus())) {
                sb.append(" and t3.in_Store_Status = ? ");
                values.add(inStoreInquiryDto.getInStoreStatus());
            }

            // 申请时间
            if (inStoreInquiryDto.getApplyStartDate() != null) {
                sb.append(" and t3.apply_Time > ?  ");
                values.add(inStoreInquiryDto.getApplyStartDate());
            }
            if (inStoreInquiryDto.getApplyEndDate() != null) {
                Date endDate = inStoreInquiryDto.getApplyEndDate();
                endDate.setHours(23);
                endDate.setMinutes(59);
                endDate.setSeconds(59);
                sb.append(" and t3.apply_Time < ?  ");
                values.add(endDate);
            }

            // 申请人
            if (StringUtils.isNotBlank(inStoreInquiryDto.getApplyOprCode())) {
                sb.append(" and t3.apply_Opr_Code = ? ");
                values.add(inStoreInquiryDto.getApplyOprCode());
            }

            // 申请机构
            if (StringUtils.isNotBlank(inStoreInquiryDto.getApplyOrgCode())) {
                if (inStoreInquiryDto.getIsContainSubOrg() != null
                        && "1".equals(inStoreInquiryDto.getIsContainSubOrg())) {
                   // 机构及下级机构匹配
                    sb.append(" and exists (select lev1.Id_vc_level from Vc_Level lev1 ");
                    sb.append("where t3.apply_Org_Code =lev1.unit_code and lev1.LEVEL_NO<=3 and lev1.unit_type='0' ");
                    sb.append(" Start with lev1.UNIT_CODE=? ");
                    values.add(inStoreInquiryDto.getApplyOrgCode());
                    sb.append(" connect by PRIOR lev1.ID_VC_LEVEL=lev1.PARENT_ORG_ID) ");
                } else {
                    //当前机构匹配
                    sb.append(" and t3.apply_Org_Code = ? ");
                    values.add(inStoreInquiryDto.getApplyOrgCode());
                }
            }

            // 当前用户(总公司、分公司单证管理员)权限控制,只能查看本机构及下级机构
            if (StringUtils.isBlank(inStoreInquiryDto.getApplyOrgCode()) && userInfo.getComCode() != null) {
                // 机构及下级机构匹配
                sb.append(" and exists (select lev2.Id_vc_level from Vc_Level lev2 ");
                sb.append("where t3.apply_Org_Code =lev2.unit_code and lev2.LEVEL_NO<=3 and lev2.unit_type='0' ");
                sb.append(" Start with lev2.UNIT_CODE=? ");
                values.add(userInfo.getComCode());
                sb.append(" connect by PRIOR lev2.ID_VC_LEVEL=lev2.PARENT_ORG_ID) ");
            }
        }
        
        try {
        StringBuffer sql_count = new StringBuffer();
        sql_count.append("select count(*) ").append(sb);
        Long totalCount = 0L;
        List tempCount = this.findBySql(sql_count.toString(), values.toArray());
        Object countObj = tempCount.get(0);
        totalCount = ((BigDecimal) countObj).longValue();
        

        // 查询分页列表
        //0-3
        StringBuffer sql_select = new StringBuffer("SELECT t2.DOC_VER_NAME,t2.DOC_VER_CODE, t4.DOC_TYPE_CODE,t4.DOC_TYPE_NAME, ");
        //4-11
        sql_select.append("t1.ID_VC_DOC_IN_STORE_DET,t1.PRESS_BATCH_NO,t1.START_NUM,t1.END_NUM,t1.TOTAL_AMOUNT,t1.PRINTERY_CODE,t1.UNIT_PRICE,t1.PRINTING_FEE ,");
        //12-18
        sql_select.append("t3.ID_Vc_Doc_In_Store,t3.IN_STORE_APP_CODE,t3.APPLY_OPR_CODE,t3.APPLY_ORG_CODE,t3.IN_STORE_STATUS,t3.APPLY_TIME,t3.CHECK_TIME ,");
        sql_select.append(" rowNum numId ");
        
        StringBuffer sql_query = new StringBuffer();
        sql_query.append("select * from(");
        sql_query.append(sql_select).append(sb);
        sql_query.append(") tableView where tableView.numId between ? and ?");
        values.add((pageNo - 1) * pageSize + 1);
        values.add(pageNo * pageSize);
       
       
        List list  = this.findBySql(sql_query.toString(), values.toArray());
           

            List<DocInstoreApplyInquiryVo> resultList = new ArrayList<DocInstoreApplyInquiryVo>();
            if (list != null && list.size() > 0) {
                DocInstoreApplyInquiryVo tempVo = null;
                for (int i = 0; i < list.size(); i++) {                                       
                    tempVo = new DocInstoreApplyInquiryVo();
                    Object[] tempObj = (Object[]) list.get(i);

                    tempVo.setDocVerName((String) tempObj[0]);
                    tempVo.setDocVerCode((String) tempObj[1]);

                    tempVo.setDocTypeCode((String) tempObj[2]);
                    tempVo.setDocTypeName((String) tempObj[3]);

                    tempVo.setDocInStoreDetId(((BigDecimal) tempObj[4]).longValue());
                    tempVo.setPressBatchNo((String) tempObj[5]);
                    tempVo.setStartNum((String) tempObj[6]);
                    tempVo.setEndNum((String) tempObj[7]);
                    tempVo.setTotalAmount(((BigDecimal) tempObj[8]).longValue());
                    tempVo.setPrinteryCode((String) tempObj[9]);
                    tempVo.setUnitPrice((BigDecimal) tempObj[10]);
                    tempVo.setPrintingFee((BigDecimal) tempObj[11]);

                    tempVo.setInStoreAppCode((String) tempObj[13]);
                    if (tempObj[14] != null) {
                        tempVo.setApplyOprCode((String) tempObj[14]);
                        tempVo.setApplyOprName(vcLevelDao.getUnitNameByUnitCode((String) tempObj[14]));
                    }

                    if (tempObj[15] != null) {
                        tempVo.setApplyOrgCode((String) tempObj[15]);
                        tempVo.setApplyOrgName(vcLevelDao.getUnitNameByUnitCode((String) tempObj[15]));
                    }

                    tempVo.setInStoreStatus((String) tempObj[16]);
                    tempVo.setApplyTime((Date) tempObj[17]);

                    // 入库申请单状态（0删除/1新建/2待审批/3审批退回/4审批通过
                    if ("4".equals((String) tempObj[16])) {
                        // 查询审批信息 通过主表逐渐和审批类型

                        QueryRule queryRule = QueryRule.getInstance();
                        queryRule = QueryRule.getInstance();
                        queryRule.addEqual("applyId", ((BigDecimal) tempObj[12]).longValue());
                        // 审批的申请类型 I-印刷入库审批
                        queryRule.addEqual("applyType", "I");
                        // 审批状态（0提交/1审批退回/2审批通过）
                        queryRule.addEqual("checkStatus", "1");
                        // 按提交时间排序
                        queryRule.addDescOrder("checkTime");
                        List<VcApprove> approves = approveDao.find(queryRule);
                        if (approves != null && approves.size() > 0) {
                            // 审批通过的时间即为入库时间
                            tempVo.setInStoreTime(approves.get(0).getCheckTime());
                        }
                    }
                    resultList.add(tempVo);
                }
            }
            Page page = new Page(pageNo, pageSize, totalCount, resultList);
            return page;
        } catch (Exception e) {
            throw new DaoException("查询数据库异常！", e);
        }
    }
    
    public List<DocInstoreApplyInquiryVo> findVcDocInStore(DocInstoreApplyInquiryVo inStoreInquiryDto, String comCode) throws DaoException {
        List<DocInstoreApplyInquiryVo> resultList = null;
        //0-3
        StringBuffer sb = new StringBuffer("SELECT t2.DOC_VER_NAME,t2.DOC_VER_CODE, t4.DOC_TYPE_CODE,t4.DOC_TYPE_NAME, ");
        //4-11
        sb.append("t1.ID_VC_DOC_IN_STORE_DET,t1.PRESS_BATCH_NO,t1.START_NUM,t1.END_NUM,t1.TOTAL_AMOUNT,t1.PRINTERY_CODE,t1.UNIT_PRICE,t1.PRINTING_FEE ,");
        //12-18
        sb.append("t3.ID_Vc_Doc_In_Store,t3.IN_STORE_APP_CODE,t3.APPLY_OPR_CODE,t3.APPLY_ORG_CODE,t3.IN_STORE_STATUS,t3.APPLY_TIME,t3.CHECK_TIME ");
        
        sb.append(" from Vc_Doc_In_Store_Det t1, Vc_Doc_Version_Info t2, Vc_Doc_In_Store t3, Vc_Doc_Type t4 ");
        sb
                .append(" where t2.doc_Ver_Code=t1.doc_Ver_Code and t3.ID_VC_DOC_IN_STORE=t1.ID_VC_DOC_IN_STORE and t4.id_Vc_Doc_Type=t2.id_Vc_Doc_Type ");
        List<Object> values = new ArrayList<Object>();
        if (inStoreInquiryDto != null) {
            // 单证版本
            if (StringUtils.isNotBlank(inStoreInquiryDto.getDocVerCode())) {
                sb.append(" and t2.doc_Ver_Code like ? ");
                values.add(inStoreInquiryDto.getDocVerCode());
            }
            // 单证种类
            if (StringUtils.isNotBlank(inStoreInquiryDto.getDocTypeCode())) {
                sb.append(" and t4.doc_Type_Code like ? ");
                values.add(inStoreInquiryDto.getDocTypeCode());
            }

            // 险类//险种
            if (StringUtils.isNotBlank(inStoreInquiryDto.getInsuType())
                    || StringUtils.isNotBlank(inStoreInquiryDto.getInsuKind())) {
                sb.append(" and exists(select 1 from Vc_Doc_Insu_Kind insuKind, Vc_Doc_Rel_Insu_Kind ref , Vc_Doc_Insu_Type insuType");
                sb.append(" where insuKind.insu_Kind_Code=ref.insu_Kind_Code and  insuKind.id_Vc_Doc_Insu_Type=insuType.id_Vc_Doc_Insu_Type");
                sb.append("  and ref.id_Vc_Doc_Version_Info=t2.id_Vc_Doc_Version_Info ");
                if (StringUtils.isNotBlank(inStoreInquiryDto.getInsuType())) {
                    sb.append(" and insuType.insu_Type_Code=? ");
                    values.add(inStoreInquiryDto.getInsuType());
                }
                if (StringUtils.isNotBlank(inStoreInquiryDto.getInsuKind())) {
                    sb.append(" and insuKind.insu_Kind_Code=? ");
                    values.add(inStoreInquiryDto.getInsuKind());
                }
                sb.append(" ) ");
            }

            // 申请单号
            if (StringUtils.isNotBlank(inStoreInquiryDto.getInStoreAppCode())) {
                sb.append(" and t3.in_Store_App_Code like ? ");
                values.add(inStoreInquiryDto.getInStoreAppCode() + "%");
            }

            // 申请状态
            if (StringUtils.isNotBlank(inStoreInquiryDto.getInStoreStatus())) {
                sb.append(" and t3.in_Store_Status = ? ");
                values.add(inStoreInquiryDto.getInStoreStatus());
            }

            // 申请时间
            if (inStoreInquiryDto.getApplyStartDate() != null) {
                sb.append(" and t3.apply_Time > ?  ");
                values.add(inStoreInquiryDto.getApplyStartDate());
            }
            if (inStoreInquiryDto.getApplyEndDate() != null) {
                Date endDate = inStoreInquiryDto.getApplyEndDate();
                endDate.setHours(23);
                endDate.setMinutes(59);
                endDate.setSeconds(59);
                sb.append(" and t3.apply_Time < ?  ");
                values.add(endDate);
            }

            // 申请人
            if (StringUtils.isNotBlank(inStoreInquiryDto.getApplyOprCode())) {
                sb.append(" and t3.apply_Opr_Code = ? ");
                values.add(inStoreInquiryDto.getApplyOprCode());
            }

            // 申请机构
            if (StringUtils.isNotBlank(inStoreInquiryDto.getApplyOrgCode())) {
                if (inStoreInquiryDto.getIsContainSubOrg() != null
                        && "1".equals(inStoreInquiryDto.getIsContainSubOrg())) {
                   // 机构及下级机构匹配
                    sb.append(" and exists (select lev1.Id_vc_level from Vc_Level lev1 ");
                    sb.append(" where t3.apply_Org_Code =lev1.unit_code and lev1.LEVEL_NO<=3 and lev1.unit_type='0' ");
                    sb.append(" Start with lev1.UNIT_CODE=? ");
                    values.add(inStoreInquiryDto.getApplyOrgCode());
                    sb.append(" connect by PRIOR lev1.ID_VC_LEVEL=lev1.PARENT_ORG_ID) ");
                } else {
                    //当前机构匹配
                    sb.append(" and t3.apply_Org_Code = ? ");
                    values.add(inStoreInquiryDto.getApplyOrgCode());
                }
            }

            // 当前用户(总公司、分公司单证管理员)权限控制,只能查看本机构及下级机构
            if (StringUtils.isBlank(inStoreInquiryDto.getApplyOrgCode()) && comCode != null) {
                // 机构及下级机构匹配
                sb.append(" and exists (select lev2.Id_vc_level from Vc_Level lev2 ");
                sb.append("where t3.apply_Org_Code =lev2.unit_code and lev2.LEVEL_NO<=3 and lev2.unit_type='0' ");
                sb.append(" Start with lev2.UNIT_CODE=? ");
                values.add(comCode);
                sb.append(" connect by PRIOR lev2.ID_VC_LEVEL=lev2.PARENT_ORG_ID) ");
            }
        }
       
        try {
            
            List list = this.findBySql(sb.toString(), values.toArray());          
            if (list != null && list.size() > 0) {
                resultList = new ArrayList<DocInstoreApplyInquiryVo>();
                DocInstoreApplyInquiryVo tempVo = null;
                for (int i = 0; i < list.size(); i++) {                                       
                    tempVo = new DocInstoreApplyInquiryVo();
                    Object[] tempObj = (Object[]) list.get(i);

                    tempVo.setDocVerName((String) tempObj[0]);
                    tempVo.setDocVerCode((String) tempObj[1]);

                    tempVo.setDocTypeCode((String) tempObj[2]);
                    tempVo.setDocTypeName((String) tempObj[3]);

                    tempVo.setDocInStoreDetId(((BigDecimal) tempObj[4]).longValue());
                    tempVo.setPressBatchNo((String) tempObj[5]);
                    tempVo.setStartNum((String) tempObj[6]);
                    tempVo.setEndNum((String) tempObj[7]);
                    tempVo.setTotalAmount(((BigDecimal) tempObj[8]).longValue());
                    tempVo.setPrinteryCode((String) tempObj[9]);
                    tempVo.setUnitPrice((BigDecimal) tempObj[10]);
                    tempVo.setPrintingFee((BigDecimal) tempObj[11]);

                    tempVo.setInStoreAppCode((String) tempObj[13]);
                    if (tempObj[14] != null) {
                        tempVo.setApplyOprCode((String) tempObj[14]);
                        tempVo.setApplyOprName(vcLevelDao.getUnitNameByUnitCode((String) tempObj[14]));
                    }

                    if (tempObj[15] != null) {
                        tempVo.setApplyOrgCode((String) tempObj[15]);
                        tempVo.setApplyOrgName(vcLevelDao.getUnitNameByUnitCode((String) tempObj[15]));
                    }

                    tempVo.setInStoreStatus((String) tempObj[16]);
                    tempVo.setApplyTime((Date) tempObj[17]);

                    // 入库申请单状态（0删除/1新建/2待审批/3审批退回/4审批通过
                    if ("4".equals((String) tempObj[16])) {
                        // 查询审批信息 通过主表逐渐和审批类型

                        QueryRule queryRule = QueryRule.getInstance();
                        queryRule = QueryRule.getInstance();
                        queryRule.addEqual("applyId", ((BigDecimal) tempObj[12]).longValue());
                        // 审批的申请类型 I-印刷入库审批
                        queryRule.addEqual("applyType", "I");
                        // 审批状态（0提交/1审批退回/2审批通过）
                        queryRule.addEqual("checkStatus", "1");
                        // 按提交时间排序
                        queryRule.addDescOrder("checkTime");
                        List<VcApprove> approves = approveDao.find(queryRule);
                        if (approves != null && approves.size() > 0) {
                            // 审批通过的时间即为入库时间
                            tempVo.setInStoreTime(approves.get(0).getCheckTime());
                        }
                    }
                    resultList.add(tempVo);
                }
            }
        }catch(Exception e){
            throw new DaoException("查询数据库异常！" ,e);
        }
        return resultList;
    }
 
    
    /**
     * 单证明细查询统计
     * 
     * @param queryDto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page queryDocDetailInquiryListByPages_old(DocDetailInquiryVo queryDto, UserInfo userInfo, int pageNo,
            int pageSize) throws DaoException {

    	List<Object> values = new ArrayList<Object>();
    	
    	String statusValue = "";
    	if (queryDto.getArrStatus() != null) {
            for (String status : queryDto.getArrStatus()) {
                statusValue = statusValue + ",'" + status + "'";
            }
            if(statusValue.length()>0){
            	statusValue = statusValue.substring(1);
            }
        }
    	String docStatusSql = "";
    	List<Object> statusList = new ArrayList<Object>();
    	StringBuffer docStatus = new StringBuffer();
    	if (queryDto.getArrStatus() != null && queryDto.getArrStatus().length!=0) {
    		docStatus.append(" and DOC_STATUS IN ( ");
            for (String status : queryDto.getArrStatus()) {
            	docStatus.append(" ? ,");
            	statusList.add(status);
            }
            // 替换最后一个，
            docStatus.replace(docStatus.length() - 1, docStatus.length(), " ) ");
            docStatusSql = docStatus.toString();
            // sql.append(" ) ");
        }
    	
        StringBuilder sql_s = new StringBuilder(100);
        sql_s.append(" (SELECT S.DOC_VER_CODE, S.START_NUM, S.END_NUM, S.DOC_STATUS STATUS, ");
        sql_s
                .append("S.ORG_CODE, S.MODIFY_USER_CODE OPR_CODE, '-1' as OWNER,S.MODIFY_TIME OPERATETIME , '-1' BUSINESS_NO ");
        sql_s.append(" FROM VC_STORAGE S  WHERE 1=1 ");
        //add by chy 20131122 begin
        if (StringUtils.isNotBlank(queryDto.getDocVerCode())) {
            sql_s.append(" AND S.DOC_VER_CODE = ?");
            values.add(queryDto.getDocVerCode());
        }
        /*if(statusValue.length()>0){
        	sql_s.append(" AND S.DOC_STATUS in (?)");
            values.add(statusValue);
        }*/
        if(docStatusSql.length() > 0){
        	sql_s.append(docStatusSql);
        	values.addAll(statusList);
        }
        // 操作时间
        if (queryDto.getOperateStartDate() != null) {
        	sql_s.append(" and S.MODIFY_TIME >= ?  ");
            values.add(queryDto.getOperateStartDate());
        }
        if (queryDto.getOperateEndDate() != null) {
            Date endDate = queryDto.getOperateEndDate();
            endDate.setHours(23);
            endDate.setMinutes(59);
            endDate.setSeconds(59);
            sql_s.append(" and S.MODIFY_TIME <= ?  ");
            values.add(endDate);
        }
        // 操作人
        if (StringUtils.isNotBlank(queryDto.getOprCode())) {
        	sql_s.append(" and S.MODIFY_USER_CODE = ? ");
            values.add(queryDto.getOprCode());
        }
        sql_s.append(" ) ");
        //add by chy 20131122 end

        StringBuilder sql_a = new StringBuilder(100);
        sql_a
                .append("  (SELECT A.DOC_VER_CODE, A.DOC_NUM START_NUM, A.DOC_NUM END_NUM, A.DOC_STATUS STATUS, ");
        sql_a.append(" A.ORG_CODE, A.TAKER_CODE OPR_CODE, A.TAKER_CODE as OWNER, A.PROVIDE_TIME OPERATETIME , '-1' BUSINESS_NO ");
        sql_a.append(" FROM VC_AVAILABLE_DOC A  WHERE 1=1");
        //add by chy 20131122 begin
        if (StringUtils.isNotBlank(queryDto.getDocVerCode())) {
        	sql_a.append(" AND A.DOC_VER_CODE = ?");
            values.add(queryDto.getDocVerCode());
        }
        /*if(statusValue.length()>0){
        	sql_a.append(" AND A.DOC_STATUS in (?)");
            values.add(statusValue);
        }*/
        if(docStatusSql.length() > 0){
        	sql_a.append(docStatusSql);
        	values.addAll(statusList);
        }
        // 操作时间
        if (queryDto.getOperateStartDate() != null) {
        	sql_a.append(" and A.PROVIDE_TIME >= ?  ");
            values.add(queryDto.getOperateStartDate());
        }
        if (queryDto.getOperateEndDate() != null) {
            Date endDate = queryDto.getOperateEndDate();
            endDate.setHours(23);
            endDate.setMinutes(59);
            endDate.setSeconds(59);
            sql_a.append(" and A.PROVIDE_TIME <= ?  ");
            values.add(endDate);
        }
        // 操作人
        if (StringUtils.isNotBlank(queryDto.getOprCode())) {
        	sql_a.append(" and A.TAKER_CODE = ? ");
            values.add(queryDto.getOprCode());
        }
        sql_a.append(" ) ");
        //add by chy 20131122 end

        StringBuilder sql_n = new StringBuilder(100);
        sql_n
                .append("  (SELECT N.DOC_VER_CODE, N.DOC_NUM START_NUM, N.DOC_NUM END_NUM,  N.DOC_STATUS STATUS, ");
        sql_n
                .append(" N.VERIFIED_ORG_CODE ORG_CODE, N.VERIFIED_OPR_CODE OPR_CODE,'-1' as OWNER, N.VERIFIED_TIME OPERATETIME ,N.BUSINESS_NO ");
        sql_n.append(" FROM VC_NORMAL_VERIFICATION N WHERE 1=1");
        //add by chy 20131122 begin
        if (StringUtils.isNotBlank(queryDto.getDocVerCode())) {
        	sql_n.append(" AND N.DOC_VER_CODE = ?");
            values.add(queryDto.getDocVerCode());
        }
        /*if(statusValue.length()>0){
        	sql_n.append(" AND N.DOC_STATUS in (?)");
            values.add(statusValue);
        }*/
        if(docStatusSql.length() > 0){
        	sql_n.append(docStatusSql);
        	values.addAll(statusList);
        }
        // 操作时间
        if (queryDto.getOperateStartDate() != null) {
        	sql_n.append(" and N.VERIFIED_TIME >= ?  ");
            values.add(queryDto.getOperateStartDate());
        }
        if (queryDto.getOperateEndDate() != null) {
            Date endDate = queryDto.getOperateEndDate();
            endDate.setHours(23);
            endDate.setMinutes(59);
            endDate.setSeconds(59);
            sql_n.append(" and N.VERIFIED_TIME <= ?  ");
            values.add(endDate);
        }
        // 操作人
        if (StringUtils.isNotBlank(queryDto.getOprCode())) {
        	sql_n.append(" and N.VERIFIED_OPR_CODE = ? ");
            values.add(queryDto.getOprCode());
        }
        sql_n.append(" ) ");
        //add by chy 20131122 end

        StringBuilder sql_an = new StringBuilder(100);
        sql_an
                .append("  (SELECT AN.DOC_VER_CODE, AN.DOC_NUM START_NUM, AN.DOC_NUM END_NUM, AN.DOC_STATUS STATUS, ");
        sql_an
                .append(" AN.VERIFIED_ORG_CODE ORG_CODE, AN.VERIFIED_OPR_CODE OPR_CODE, '-1' as OWNER,AN.VERIFIED_TIME OPERATETIME ,AN.BUSINESS_NO ");
        sql_an.append(" FROM VC_ABNORMAL_VERIFICATION AN WHERE 1=1");
        //add by chy 20131122 begin
        if (StringUtils.isNotBlank(queryDto.getDocVerCode())) {
        	sql_an.append(" AND AN.DOC_VER_CODE = ?");
            values.add(queryDto.getDocVerCode());
        }
        /*if(statusValue.length()>0){
        	sql_an.append(" AND AN.DOC_STATUS in (?)");
            values.add(statusValue);
        }*/
        if(docStatusSql.length() > 0){
        	sql_an.append(docStatusSql);
        	values.addAll(statusList);
        }
        // 操作时间
        if (queryDto.getOperateStartDate() != null) {
        	sql_an.append(" and AN.VERIFIED_TIME >= ?  ");
            values.add(queryDto.getOperateStartDate());
        }
        if (queryDto.getOperateEndDate() != null) {
            Date endDate = queryDto.getOperateEndDate();
            endDate.setHours(23);
            endDate.setMinutes(59);
            endDate.setSeconds(59);
            sql_an.append(" and AN.VERIFIED_TIME <= ?  ");
            values.add(endDate);
        }
    	// 操作人
        if (StringUtils.isNotBlank(queryDto.getOprCode())) {
        	sql_an.append(" and AN.VERIFIED_OPR_CODE = ? ");
            values.add(queryDto.getOprCode());
        }
        sql_an.append(" ) ");
        //add by chy 20131122 end

        //del by chy 20131122 begin
        /*if (queryDto != null) {
            if (StringUtils.isNotBlank(queryDto.getDocVerCode())) {
                sql_s.append(" WHERE S.DOC_VER_CODE = '").append(queryDto.getDocVerCode()).append("'");
                sql_a.append(" WHERE A.DOC_VER_CODE = '").append(queryDto.getDocVerCode()).append("'");
                sql_n.append(" WHERE N.DOC_VER_CODE = '").append(queryDto.getDocVerCode()).append("'");
                sql_an.append(" WHERE AN.DOC_VER_CODE = '").append(queryDto.getDocVerCode()).append("'");
            }
        }
        sql_s.append(" ) ");
        sql_a.append(" ) ");
        sql_n.append(" ) ");
        sql_an.append(" ) ");*/
        //del by chy 20131122 end

        StringBuilder sql = new StringBuilder(100);

        // sql.append("select ta.*,ver.DOC_VER_Name,type.DOC_TYPE_CODE, type.DOC_TYPE_NAME ");
        sql.append(" from (");
        // if (queryDto.getStatus() != null) {
        sql.append(sql_s).append(" union ").append(sql_a).append(" union ").append(sql_n).append(" union ")
                .append(sql_an);
        // }
        sql.append(") ta, VC_DOC_VERSION_INFO ver, VC_DOC_TYPE type ");
        sql.append(" where ta.DOC_VER_CODE=ver.DOC_VER_CODE and ver.ID_VC_DOC_TYPE=type.ID_VC_DOC_TYPE ");
        //List<Object> values = new ArrayList<Object>();
        if (queryDto != null) {
            // 单证版本
            /*
             * if (StringUtils.isNotBlank(queryDto.getDocVerCode())) { sql.append(" and ver.doc_Ver_Code like ? ");
             * values.add(queryDto.getDocVerCode()); }
             */
            // 单证种类
            if (StringUtils.isNotBlank(queryDto.getDocTypeCode())) {
                sql.append(" and type.doc_Type_Code like ? ");
                values.add(queryDto.getDocTypeCode());
            }

            // 险类// 险种
            if (StringUtils.isNotBlank(queryDto.getInsuType())
                    || StringUtils.isNotBlank(queryDto.getInsuKind())) {
                sql.append(" and exists(");
                sql
                        .append(" select 1 from VC_DOC_INSU_KIND insuKind, VC_DOC_REL_INSU_KIND ref ,VC_DOC_INSU_TYPE insuType ");
                sql
                        .append(" where insuKind.INSU_KIND_CODE=ref.INSU_KIND_CODE and ref.ID_VC_DOC_VERSION_INFO=ver.ID_VC_DOC_VERSION_INFO ");
                sql.append(" and insuKind.ID_VC_DOC_INSU_TYPE=insuType.ID_VC_DOC_INSU_TYPE ");
                if (StringUtils.isNotBlank(queryDto.getInsuType())) {
                    sql.append(" and insuType.INSU_TYPE_CODE=?");
                    values.add(queryDto.getInsuType());
                }
                if (StringUtils.isNotBlank(queryDto.getInsuKind())) {
                    sql.append(" and insuKind.INSU_KIND_CODE=?");
                    values.add(queryDto.getInsuKind());
                }
                sql.append(" ) ");
            }

            // 单证状态
            /*
             * if (StringUtils.isNotBlank(queryDto.getStatus())) { sql.append(" and ta.STATUS IN (?) ");
             * values.add(queryDto.getStatus()); }
             */
            /*if (queryDto.getArrStatus() != null) {
                sql.append(" and ta.STATUS IN ( ");
                for (String status : queryDto.getArrStatus()) {
                    sql.append(" ? ,");
                    values.add(status);
                }
                // 替换最后一个，
                sql.replace(sql.length() - 1, sql.length(), " ) ");
                // sql.append(" ) ");
            }*/

            // 操作时间
            /*if (queryDto.getOperateStartDate() != null) {
                sql.append(" and ta.OPERATETIME >= ?  ");
                values.add(queryDto.getOperateStartDate());
            }
            if (queryDto.getOperateEndDate() != null) {
                Date endDate = queryDto.getOperateEndDate();
                endDate.setHours(23);
                endDate.setMinutes(59);
                endDate.setSeconds(59);
                sql.append(" and ta.OPERATETIME <= ?  ");
                values.add(endDate);
            }*/

            // 起始流水、终止流水
            if (StringUtils.isNotBlank(queryDto.getStartNum())) {
                sql.append(" and (");
                sql.append(" ( ? between ta.START_NUM and ta.END_NUM ) ");
                values.add(queryDto.getStartNum());
                if (StringUtils.isNotBlank(queryDto.getEndNum())) {
                    sql.append(" or ( ? between ta.START_NUM and ta.END_NUM ) ");
                    values.add(queryDto.getEndNum());
                    sql.append(" or ( ? < ta.START_NUM  and  ? > ta.END_NUM ) ");
                    values.add(queryDto.getStartNum());
                    values.add(queryDto.getEndNum());
                } else {
                    sql.append(" or ( ? < =ta.END_NUM ) ");
                    values.add(queryDto.getStartNum());
                }
                sql.append(" ) ");
            } else {
                if (StringUtils.isNotBlank(queryDto.getEndNum())) {
                    sql.append(" and ( ? >= ta.START_NUM ) ");
                    values.add(queryDto.getEndNum());
                }
            }

            // 操作人
           /* if (StringUtils.isNotBlank(queryDto.getOprCode())) {
                sql.append(" and ta.OPR_CODE = ? ");
                values.add(queryDto.getOprCode());
            }*/

            // 所属机构
            if (StringUtils.isNotBlank(queryDto.getOrgCode())) {
                if (queryDto.getIsContainSubOrg() != null && "1".equals(queryDto.getIsContainSubOrg())) {
                    // TODO包含下级的相关处理
                    // sql.append(" and ta.ORG_CODE like ? ");
                    // values.add(queryDto.getOrgCode() + "%");
                    // level是oracle关键字，顾不能用别名level//
                    sql
                            .append(" and exists (select 1 from Vc_Level lev where ta.ORG_CODE =lev.unit_code  Start with lev.unit_code=? ");
                    values.add(queryDto.getOrgCode());
                    sql.append(" connect by PRIOR lev.ID_VC_LEVEL=lev.PARENT_ORG_ID) ");
                } else {
                    sql.append(" and ta.ORG_CODE = ? ");
                    values.add(queryDto.getOrgCode());
                }
            }
        }
        // 当前用户权限控制(总公司、分公司)
        if (userInfo.getComCode() != null) {
            // sql.append(" and ta.ORG_CODE like ? ");
            // values.add(userInfo.getComCode()+"%");
            // level是oracle关键字，不能用别名level//
            sql
                    .append(" and exists (select 1 from Vc_Level le where ta.ORG_CODE =le.unit_code  Start with le.unit_code=? ");
            values.add(userInfo.getComCode());
            sql.append(" connect by PRIOR le.ID_VC_LEVEL=le.PARENT_ORG_ID) ");
        }

        StringBuilder sql_count = new StringBuilder(100);
        sql_count.append("select count(*) ").append(sql);

        Long totalCount = 0L;
        List tempCount = this.findBySql(sql_count.toString(), values.toArray());
        Object countObj = tempCount.get(0);
        totalCount = ((BigDecimal) countObj).longValue();

        // 查询分页列表
        StringBuilder sql_query = new StringBuilder(100);
        sql_query.append("select * from(");
        sql_query.append("select ta.*,ver.DOC_VER_Name,type.DOC_TYPE_CODE, type.DOC_TYPE_NAME,rowNum numId ")
                .append(sql);
        sql_query.append(") tableView where tableView.numId between ? and ?");
        values.add((pageNo - 1) * pageSize + 1);
        values.add(pageNo * pageSize);
        List list = null;
        try {
            list = this.findBySql(sql_query.toString(), values.toArray());
        } catch (Exception e) {
            throw new DaoException("查询数据库异常！", e);
        }

        /*
         * List tempList=this.findBySql(sql.toString(), values.toArray()); Long count=this.getCount(sql.toString(),
         * values.toArray()); Page page = this.findBySql(sql.toString(), pageNo, pageSize,values.toArray());
         * List<DocDetailInquiryVo> resultList = new ArrayList<DocDetailInquiryVo>();
         */

        // List list = page.getResult();
        List resultList = new ArrayList<DocDetailInquiryVo>();
        if (list != null && list.size() > 0) {
            DocDetailInquiryVo tempVo = null;
            for (int i = 0; i < list.size(); i++) {
                tempVo = new DocDetailInquiryVo();
                Object[] obj = (Object[]) list.get(i);

                tempVo.setDocVerCode((String) obj[0]);
                tempVo.setStartNum((String) obj[1]);
                tempVo.setEndNum((String) obj[2]);
                
                // 状态
                tempVo.setStatus((String) obj[3]);
                
                tempVo.setOrgCode((String) obj[4]);
                tempVo.setOrgName(vcLevelDao.getUnitNameByUnitCode((String) obj[4]));
                tempVo.setOprCode((String) obj[5]);
                tempVo.setOprName(vcLevelDao.getPubUseDefNameByCode((String) obj[5]));
                //可使用人
                if (obj[6] != null && !"-1".equals((String) obj[6])) {                    
                    tempVo.setOwner((String) obj[6]);
                    tempVo.setOwnerName(vcTakerDao.getUnitNameByUnitCode((String) obj[6]));
                }
                tempVo.setOperateTime((Date) obj[7]);
                if (obj[8] != null && !"-1".equals((String) obj[8])) {
                    tempVo.setBusinessNo((String) obj[8]);
                }

                tempVo.setDocVerName((String) obj[9]);
                tempVo.setDocTypeCode((String) obj[10]);
                tempVo.setDocTypeName((String) obj[11]);
                resultList.add(tempVo);
            }
        }

        Page page = new Page(pageNo, pageSize, totalCount, resultList);
        // page.setData(resultList);
        return page;

    }
    
    /**
     * 单证明细查询统计导出功能
     * 
     * @param queryDto
     * @param userInfo
     * @param fyh
     * @return
     */
    public List<DocDetailInquiryVo> queryDocDetailExplore(DocDetailInquiryVo queryDto, UserInfo userInfo) throws DaoException {
    	List<DocDetailInquiryVo> result = null;
        String statusS="S1,S2,S3,ST,RT,PT,LT,CT,DT,OT";
        String statusA="A,JT,LT";
        String statusN="U1,U2,CT,U3";
        String statusAN="C1,C2,C3,L1,L2,D,JX,L3,H,JXT,ZPL,ZPD,ZD";
        List<String> statusSList=new ArrayList<String>();
        List<String> statusAList=new ArrayList<String>();
        List<String> statusNList=new ArrayList<String>();
        List<String> statusANList=new ArrayList<String>();
       
        if (queryDto.getArrStatus() != null && queryDto.getArrStatus().length>0) {
            for (String status : queryDto.getArrStatus()) {
                if ("D".equals(status)) {//用于区分D、DT
                    statusANList.add(status);
                } else {
                    if (statusS.indexOf(status) > -1) {
                        statusSList.add(status);
                    }
                    if (statusA.indexOf(status) > -1) {
                        statusAList.add(status);
                    }
                    if (statusN.indexOf(status) > -1) {
                        statusNList.add(status);
                    }
                    if (statusAN.indexOf(status) > -1) {
                        statusANList.add(status);
                    }
                }
            }
        }
        
        // 所属机构
        //若当前查询条件未包含机构，则机构默认为当前用户的机构
        StringBuffer orgSql=new StringBuffer();
        List<String> orgValues=new ArrayList<String>();
        if (StringUtils.isNotBlank(queryDto.getOrgCode())) {
            if (queryDto.getIsContainSubOrg() != null && "1".equals(queryDto.getIsContainSubOrg())) {
                // 包含下级的相关处理
                // level是oracle关键字，顾不能用别名level//
                orgSql.append(" select lev.unit_code from Vc_Level lev where lev.unit_type=?  Start with lev.unit_code=? ");
                orgValues.add("0");
                orgValues.add(queryDto.getOrgCode());
                orgSql.append(" connect by PRIOR lev.ID_VC_LEVEL=lev.PARENT_ORG_ID ");
            } else {
                orgSql.append(" select lev.unit_code from Vc_Level lev where lev.unit_code=?  ");
                orgValues.add(queryDto.getOrgCode());
            }
        }else{
            if(userInfo==null){
               throw new DaoException("当前用户机构为空");
            }
            // level是oracle关键字，顾不能用别名level//
            orgSql.append("(select lev.unit_code from Vc_Level lev where lev.unit_type=?  Start with lev.unit_code=? ");
            orgValues.add("0");
            orgValues.add(userInfo.getComCode());
            orgSql.append(" connect by PRIOR lev.ID_VC_LEVEL=lev.PARENT_ORG_ID ");
    }
  
       
        
        StringBuilder sql_s = new StringBuilder();
        List<Object> values_s=new ArrayList<Object>();
        sql_s.append(" (SELECT S.DOC_VER_CODE, S.START_NUM, S.END_NUM, S.DOC_STATUS STATUS, S.ORG_CODE, ");
        sql_s.append("S.MODIFY_USER_CODE OPR_CODE, null as OWNER,S.IN_STORE_TIME OPERATETIME , null BUSINESS_NO ");
        sql_s.append(" FROM VC_STORAGE S  WHERE 1=1 ");
        //单证类型
        if (queryDto.getArrDocVerCode()!=null && queryDto.getArrDocVerCode().length>0 ) {
            sql_s.append(" AND S.DOC_VER_CODE in(?) ");
            values_s.add(queryDto.getArrDocVerCode());
        }
        if(statusSList.size()>0){
            sql_s.append(" AND S.DOC_STATUS in (?)");
            values_s.add(statusSList);
        }
       
        // 操作时间
        if (queryDto.getOperateStartDate() != null) {
            sql_s.append(" and S.IN_STORE_TIME >= ?  ");
            values_s.add(queryDto.getOperateStartDate());
        }
        if (queryDto.getOperateEndDate() != null) {
            sql_s.append(" and S.IN_STORE_TIME <  ?  ");
            values_s.add(DateUtils.reset(DateUtils.addDay(queryDto.getOperateEndDate(), 1)));
        }
        // 操作人
        if (StringUtils.isNotBlank(queryDto.getOprCode())) {
            sql_s.append(" and S.MODIFY_USER_CODE = ? ");
            values_s.add(queryDto.getOprCode());
        }
        //机构
        if (orgSql.length()>0) {
            sql_s.append(" and S.ORG_CODE in(").append(orgSql).append(") ");
            values_s.addAll(orgValues);
        }
        
        sql_s.append(" ) ");
       

        StringBuilder sql_a = new StringBuilder(100);
        List<Object> values_a=new ArrayList<Object>();
        sql_a.append("  (SELECT A.DOC_VER_CODE, A.DOC_NUM START_NUM, A.DOC_NUM END_NUM, A.DOC_STATUS STATUS, A.ORG_CODE,");
        sql_a.append("  A.TAKER_CODE OPR_CODE, A.TAKER_CODE as OWNER, A.PROVIDE_TIME OPERATETIME , null BUSINESS_NO ");
        sql_a.append(" FROM VC_AVAILABLE_DOC A  WHERE 1=1");
        //单证类型
        if (queryDto.getArrDocVerCode()!=null && queryDto.getArrDocVerCode().length>0 ) {
            sql_a.append(" AND A.DOC_VER_CODE in(?) ");
            values_a.add(queryDto.getArrDocVerCode());
        }        
        if(statusAList.size()>0){
            sql_a.append(" AND A.DOC_STATUS in (?)");
            values_a.add(statusAList);
        }
        // 操作时间
        if (queryDto.getOperateStartDate() != null) {
            sql_a.append(" and A.PROVIDE_TIME >= ?  ");
            values_a.add(queryDto.getOperateStartDate());
        }
        if (queryDto.getOperateEndDate() != null) {            
            sql_a.append(" and A.PROVIDE_TIME < ?  ");
            values_a.add(DateUtils.reset(DateUtils.addDay(queryDto.getOperateEndDate(), 1)));
        }
        // 操作人
        if (StringUtils.isNotBlank(queryDto.getOprCode())) {
            sql_a.append(" and A.TAKER_CODE = ? ");
            values_a.add(queryDto.getOprCode());
        }
        //机构
        if (orgSql.length()>0) {
            sql_a.append(" and A.ORG_CODE in(").append(orgSql).append(") ");
            values_a.addAll(orgValues);
        }
        sql_a.append(" ) ");
       

        StringBuilder sql_n = new StringBuilder(100);
        List<Object> values_n=new ArrayList<Object>();
        sql_n.append("  (SELECT  N.DOC_VER_CODE, N.DOC_NUM START_NUM, N.DOC_NUM END_NUM,  N.DOC_STATUS STATUS, ");
        sql_n.append(" N.VERIFIED_ORG_CODE ORG_CODE, N.VERIFIED_OPR_CODE OPR_CODE,null as OWNER, N.VERIFIED_TIME OPERATETIME ,N.BUSINESS_NO ");
        sql_n.append(" FROM VC_NORMAL_VERIFICATION N WHERE 1=1");
        //单证类型
        if (queryDto.getArrDocVerCode()!=null && queryDto.getArrDocVerCode().length>0 ) {
            sql_n.append(" AND N.DOC_VER_CODE in(?) ");
            values_n.add(queryDto.getArrDocVerCode());
        }   
        if(statusNList.size()>0){
            sql_n.append(" AND N.DOC_STATUS in (?)");
            values_n.add(statusNList);
        }
        // 操作时间
        if (queryDto.getOperateStartDate() != null) {
            sql_n.append(" and N.VERIFIED_TIME >= ?  ");
            values_n.add(queryDto.getOperateStartDate());
        }
        if (queryDto.getOperateEndDate() != null) {           
            sql_n.append(" and N.VERIFIED_TIME < ?  ");
            values_n.add(DateUtils.reset(DateUtils.addDay(queryDto.getOperateEndDate(), 1)));
        }
     // 业务号
        if (StringUtils.isNotBlank(queryDto.getBusinessNo())) {
            sql_n.append(" and EXISTS(SELECT 1 FROM VC_NORMAL_VERIFIED_DET DET ");
            sql_n.append(" WHERE N.ID_VC_NORMAL_VERIFICATION=DET.ID_VC_NORMAL_VERIFICATION AND DET.BUSINESS_NO = ?) ");
            values_n.add(queryDto.getBusinessNo());
        }
        // 操作人
        if (StringUtils.isNotBlank(queryDto.getOprCode())) {
            sql_n.append(" and N.VERIFIED_OPR_CODE = ? ");
            values_n.add(queryDto.getOprCode());
        }
      //机构
        if (orgSql.length()>0) {
            sql_n.append(" and N.VERIFIED_ORG_CODE in(").append(orgSql).append(") ");
            values_n.addAll(orgValues);
        }
        sql_n.append(" ) ");
        //add by chy 20131122 end

        StringBuilder sql_an = new StringBuilder(100);
        List<Object> values_an=new ArrayList<Object>();
        sql_an.append(" (SELECT AN.DOC_VER_CODE, AN.DOC_NUM START_NUM, AN.DOC_NUM END_NUM, AN.DOC_STATUS STATUS, AN.VERIFIED_ORG_CODE ORG_CODE,");
        sql_an.append(" AN.VERIFIED_OPR_CODE OPR_CODE, null as OWNER,AN.VERIFIED_TIME OPERATETIME ,AN.BUSINESS_NO ");
        sql_an.append(" FROM VC_ABNORMAL_VERIFICATION AN WHERE 1=1");
        //单证类型
        if (queryDto.getArrDocVerCode()!=null && queryDto.getArrDocVerCode().length>0 ) {
            sql_an.append(" AND AN.DOC_VER_CODE in(?) ");
            values_an.add(queryDto.getArrDocVerCode());
        }   
       
        if(statusANList.size()>0){
            sql_an.append(" AND AN.DOC_STATUS in (?)");
            values_an.add(statusANList);
        }
        // 操作时间
        if (queryDto.getOperateStartDate() != null) {
            sql_an.append(" and AN.VERIFIED_TIME >= ?  ");
            values_an.add(queryDto.getOperateStartDate());
        }
        if (queryDto.getOperateEndDate() != null) {            
            sql_an.append(" and AN.VERIFIED_TIME < ?  ");
            values_an.add(DateUtils.reset(DateUtils.addDay(queryDto.getOperateEndDate(), 1)));
        }
        // 业务号
        if (StringUtils.isNotBlank(queryDto.getBusinessNo())) {
            sql_an.append(" and EXISTS(SELECT 1 FROM VC_ABNORMAL_VERIFIED_DET DET ");
            sql_an.append(" WHERE AN.ID_VC_ABNORMAL_VERIFICATION=DET.ID_VC_ABNORMAL_VERIFICATION AND DET.BUSINESS_NO = ?) ");
            values_an.add(queryDto.getBusinessNo());
        }
        // 操作人
        if (StringUtils.isNotBlank(queryDto.getOprCode())) {
            sql_an.append(" and AN.VERIFIED_OPR_CODE = ? ");
            values_an.add(queryDto.getOprCode());
        }
        //机构
        if (orgSql.length()>0) {
            sql_an.append(" and AN.VERIFIED_ORG_CODE in(").append(orgSql).append(") ");
            values_an.addAll(orgValues);
        }
        sql_an.append(" ) ");
        
        
        //==================================组装四段查询==========================
        StringBuilder sql = new StringBuilder(100);
        List<Object> values = new ArrayList<Object>();
        
       // sql.append(" from ("); fyh
        if (queryDto.getArrStatus() != null && queryDto.getArrStatus().length>0) {
            ///业务号存在则不查询库存及可使用表
            if(StringUtils.isBlank(queryDto.getBusinessNo())){
                if(statusSList.size()>0){
                    sql.append(sql_s).append("union"); 
                    values.addAll(values_s);
                }
                if(statusAList.size()>0){
                    sql.append(sql_a).append("union"); 
                    values.addAll(values_a);
                }
            }
            if(statusNList.size()>0){
                sql.append(sql_n).append("union"); 
                values.addAll(values_n);
            }
            if(statusANList.size()>0){
                sql.append(sql_an).append("union"); 
                values.addAll(values_an);
            }
            sql.setLength(sql.length()-5);//去掉最后一个union
        }else{
          ///业务号存在则不查询库存及可使用表
            if(StringUtils.isBlank(queryDto.getBusinessNo())){
                sql.append(sql_s).append("union").append(sql_a).append("union");
                values.addAll(values_s);
                values.addAll(values_a);
            }
            sql.append(sql_n).append("union").append(sql_an);           
            values.addAll(values_n);
            values.addAll(values_an);
        }
        sql.append(") ta, VC_DOC_VERSION_INFO ver, VC_DOC_TYPE type ");
        sql.append(" where ta.DOC_VER_CODE=ver.DOC_VER_CODE and ver.id_vc_doc_type=type.id_vc_doc_type ");     
        if (queryDto != null) {
            // 起始流水、终止流水
            if (StringUtils.isNotBlank(queryDto.getStartNum())) {
                sql.append(" and (");
                sql.append(" ( ? between to_number(ta.START_NUM) and to_number(ta.END_NUM) ) ");
                values.add(Long.valueOf(queryDto.getStartNum()));
                if (StringUtils.isNotBlank(queryDto.getEndNum())) {
                    sql.append(" or ( ? between to_number(ta.START_NUM) and to_number(ta.END_NUM) ) ");
                    values.add(Long.valueOf(queryDto.getEndNum()));
                    sql.append(" or ( ? < to_number(ta.START_NUM)  and  ? > to_number(ta.END_NUM) ) ");
                    values.add(Long.valueOf(queryDto.getStartNum()));
                    values.add(Long.valueOf(queryDto.getEndNum()));
                } else {
                    sql.append(" or ( ? < =to_number(ta.END_NUM) ) ");
                    values.add(Long.valueOf(queryDto.getStartNum()));
                }
                sql.append(" ) ");
            } else {
                if (StringUtils.isNotBlank(queryDto.getEndNum())) {
                    sql.append(" and ( ? >= to_number(ta.START_NUM) ) ");
                    values.add(Long.valueOf(queryDto.getEndNum()));
                }
            }
        }
        	//判断记录数是否大于最大导出记录数
             StringBuilder sql_count = new StringBuilder(100);
             sql_count.append("select count(*) from (").append(sql);
             Long totalCount = 0L;
             try {
            	 List tempCount = this.findBySql(sql_count.toString(), values.toArray());
                 Object countObj = tempCount.get(0);
                 totalCount = ((BigDecimal) countObj).longValue();
			} catch (Exception e) {
				 throw new DaoException("查询数据库异常！", e);
			}
             if(totalCount>SysConst.EXCEL_EXPORT_MAX_COUNT1){
                 throw new DaoException("导出记录数超出"+SysConst.EXCEL_EXPORT_MAX_COUNT1+"条限制，请缩小查询范围！");
             }
             StringBuilder sql_query = new StringBuilder(100);
             sql_query.append("select ta.*,ver.DOC_VER_Name,type.DOC_TYPE_CODE, type.DOC_TYPE_NAME from ( ")
                     .append(sql);
             List list = null;
             list = this.findBySql(sql_query.toString(), values.toArray());
             
             /*
              * List tempList=this.findBySql(sql.toString(), values.toArray()); Long count=this.getCount(sql.toString(),
              * values.toArray()); Page page = this.findBySql(sql.toString(), pageNo, pageSize,values.toArray());
              * List<DocDetailInquiryVo> resultList = new ArrayList<DocDetailInquiryVo>();
              */

             // List list = page.getResult();
             result = new ArrayList<DocDetailInquiryVo>();
             if (list != null && list.size() > 0) {
                 DocDetailInquiryVo tempVo = null;
                 for (int i = 0; i < list.size(); i++) {
                     tempVo = new DocDetailInquiryVo();
                     Object[] obj = (Object[]) list.get(i);

                     tempVo.setDocVerCode((String) obj[0]);
                     tempVo.setStartNum((String) obj[1]);
                     tempVo.setEndNum((String) obj[2]);
                     
                     // 状态
                     tempVo.setStatus((String) obj[3]);
                     
                     tempVo.setOrgCode((String) obj[4]);
                     tempVo.setOrgName(vcLevelDao.getUnitNameByUnitCode((String) obj[4]));
                     tempVo.setOprCode((String) obj[5]);
                     tempVo.setOprName(vcLevelDao.getPubUseDefNameByCode((String) obj[5]));
                      //可使用人
                     if (obj[6] != null && !"-1".equals((String) obj[6])) {                    
                         tempVo.setOwner((String) obj[6]);
                         tempVo.setOwnerName(vcTakerDao.getUnitNameByUnitCode((String) obj[6]));
                     }
                     tempVo.setOperateTime((Date) obj[7]);
                     if (obj[8] != null && !"-1".equals((String) obj[8])) {
                         tempVo.setBusinessNo((String) obj[8]);
                     }

                     tempVo.setDocVerName((String) obj[9]);
                     tempVo.setDocTypeCode((String) obj[10]);
                     tempVo.setDocTypeName((String) obj[11]);
                     result.add(tempVo);
                 }
             }
             return result;
    }
    /**
     * 单证明细查询统计
     * 
     * @param queryDto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page queryDocDetailInquiryListByPages(DocDetailInquiryVo queryDto, UserInfo userInfo, int pageNo,
            int pageSize) throws DaoException {
         /**
         * 状态分组
         * 库存表【S1-印刷入库   S2-申领入库   S3-回收入库   ST-库存冻结   RT-回收在途   PT-发放中   LT-遗失冻结(库存)  CT-作废中（库存作废中） DT-销毁冻结  OT-出库】
         * 可使用表【A-可使用 JT-激活卡退卡冻结   LT-遗失冻结（可使用） 】
         * 正常核销表【U1-已使用  U2-手工已使用登记    CT-作废中（使用作废中）】
         * 非正常核销表【C1-库存作废  C2-已使用作废  C3-已使用作废不统计   L1-库存遗失   L2-可使用遗失    D-销毁  】
         * 
         * 注意：状态一定要维护全，不然有可能出现拼装语句出错的问题
         */
        
        String statusS="S1,S2,S3,ST,RT,PT,LT,CT,DT,OT";
        String statusA="A,JT,LT";
        String statusN="U1,U2,CT,U3";
        String statusAN="C1,C2,C3,L1,L2,D,JX,L3,H,JXT,ZPL,ZPD,ZD";
        List<String> statusSList=new ArrayList<String>();
        List<String> statusAList=new ArrayList<String>();
        List<String> statusNList=new ArrayList<String>();
        List<String> statusANList=new ArrayList<String>();
       
        if (queryDto.getArrStatus() != null && queryDto.getArrStatus().length>0) {
            for (String status : queryDto.getArrStatus()) {
                if ("D".equals(status)) {//用于区分D、DT
                    statusANList.add(status);
                } else {
                    if (statusS.indexOf(status) > -1) {
                        statusSList.add(status);
                    }
                    if (statusA.indexOf(status) > -1) {
                        statusAList.add(status);
                    }
                    if (statusN.indexOf(status) > -1) {
                        statusNList.add(status);
                    }
                    if (statusAN.indexOf(status) > -1) {
                        statusANList.add(status);
                    }
                }
            }
        }
        
        // 所属机构
        //若当前查询条件未包含机构，则机构默认为当前用户的机构
        StringBuffer orgSql=new StringBuffer();
        List<String> orgValues=new ArrayList<String>();
        if (StringUtils.isNotBlank(queryDto.getOrgCode())) {
            if (queryDto.getIsContainSubOrg() != null && "1".equals(queryDto.getIsContainSubOrg())) {
                // 包含下级的相关处理
                // level是oracle关键字，顾不能用别名level//
                orgSql.append(" select lev.unit_code from Vc_Level lev where lev.unit_type=?  Start with lev.unit_code=? ");
                orgValues.add("0");
                orgValues.add(queryDto.getOrgCode());
                orgSql.append(" connect by PRIOR lev.ID_VC_LEVEL=lev.PARENT_ORG_ID ");
            } else {
                orgSql.append(" select lev.unit_code from Vc_Level lev where lev.unit_code=?  ");
                orgValues.add(queryDto.getOrgCode());
            }
        }else{
            if(userInfo==null){
               throw new DaoException("当前用户机构为空");
            }
            // level是oracle关键字，顾不能用别名level//
            orgSql.append(" select lev.unit_code from Vc_Level lev where lev.unit_type=?  Start with lev.unit_code=? ");
            orgValues.add("0");
            orgValues.add(userInfo.getComCode());
            orgSql.append(" connect by PRIOR lev.ID_VC_LEVEL=lev.PARENT_ORG_ID ");
    }
  
       
        
        StringBuilder sql_s = new StringBuilder();
        List<Object> values_s=new ArrayList<Object>();
        sql_s.append(" (SELECT S.DOC_VER_CODE, S.START_NUM, S.END_NUM, S.DOC_STATUS STATUS, S.ORG_CODE, ");
        sql_s.append("S.MODIFY_USER_CODE OPR_CODE, null as OWNER,S.IN_STORE_TIME OPERATETIME , null BUSINESS_NO ");
        sql_s.append(" FROM VC_STORAGE S  WHERE 1=1 ");
        //单证类型
        if (queryDto.getArrDocVerCode()!=null && queryDto.getArrDocVerCode().length>0 ) {
            sql_s.append(" AND S.DOC_VER_CODE in(?) ");
            values_s.add(queryDto.getArrDocVerCode());
        }
        if(statusSList.size()>0){
            sql_s.append(" AND S.DOC_STATUS in (?)");
            values_s.add(statusSList);
        }
       
        // 操作时间
        if (queryDto.getOperateStartDate() != null) {
            sql_s.append(" and S.IN_STORE_TIME >= ?  ");
            values_s.add(queryDto.getOperateStartDate());
        }
        if (queryDto.getOperateEndDate() != null) {
            sql_s.append(" and S.IN_STORE_TIME <  ?  ");
            values_s.add(DateUtils.reset(DateUtils.addDay(queryDto.getOperateEndDate(), 1)));
        }
        // 操作人
        if (StringUtils.isNotBlank(queryDto.getOprCode())) {
            sql_s.append(" and S.MODIFY_USER_CODE = ? ");
            values_s.add(queryDto.getOprCode());
        }
        //机构
        if (orgSql.length()>0) {
            sql_s.append(" and S.ORG_CODE in(").append(orgSql).append(") ");
            values_s.addAll(orgValues);
        }
        
        sql_s.append(" ) ");
       

        StringBuilder sql_a = new StringBuilder(100);
        List<Object> values_a=new ArrayList<Object>();
        sql_a.append("  (SELECT A.DOC_VER_CODE, A.DOC_NUM START_NUM, A.DOC_NUM END_NUM, A.DOC_STATUS STATUS, A.ORG_CODE,");
        sql_a.append("  A.TAKER_CODE OPR_CODE, A.TAKER_CODE as OWNER, A.PROVIDE_TIME OPERATETIME , null BUSINESS_NO ");
        sql_a.append(" FROM VC_AVAILABLE_DOC A  WHERE 1=1");
        //单证类型
        if (queryDto.getArrDocVerCode()!=null && queryDto.getArrDocVerCode().length>0 ) {
            sql_a.append(" AND A.DOC_VER_CODE in(?) ");
            values_a.add(queryDto.getArrDocVerCode());
        }        
        if(statusAList.size()>0){
            sql_a.append(" AND A.DOC_STATUS in (?)");
            values_a.add(statusAList);
        }
        // 操作时间
        if (queryDto.getOperateStartDate() != null) {
            sql_a.append(" and A.PROVIDE_TIME >= ?  ");
            values_a.add(queryDto.getOperateStartDate());
        }
        if (queryDto.getOperateEndDate() != null) {            
            sql_a.append(" and A.PROVIDE_TIME < ?  ");
            values_a.add(DateUtils.reset(DateUtils.addDay(queryDto.getOperateEndDate(), 1)));
        }
        // 操作人
        if (StringUtils.isNotBlank(queryDto.getOprCode())) {
            sql_a.append(" and A.TAKER_CODE = ? ");
            values_a.add(queryDto.getOprCode());
        }
        //机构
        if (orgSql.length()>0) {
            sql_a.append(" and A.ORG_CODE in(").append(orgSql).append(") ");
            values_a.addAll(orgValues);
        }
        sql_a.append(" ) ");
       

        StringBuilder sql_n = new StringBuilder(100);
        List<Object> values_n=new ArrayList<Object>();
        sql_n.append("  (SELECT  N.DOC_VER_CODE, N.DOC_NUM START_NUM, N.DOC_NUM END_NUM,  N.DOC_STATUS STATUS, ");
        sql_n.append(" N.VERIFIED_ORG_CODE ORG_CODE, N.VERIFIED_OPR_CODE OPR_CODE,null as OWNER, N.VERIFIED_TIME OPERATETIME ,N.BUSINESS_NO ");
        sql_n.append(" FROM VC_NORMAL_VERIFICATION N WHERE 1=1");
        //单证类型
        if (queryDto.getArrDocVerCode()!=null && queryDto.getArrDocVerCode().length>0 ) {
            sql_n.append(" AND N.DOC_VER_CODE in(?) ");
            values_n.add(queryDto.getArrDocVerCode());
        }   
        if(statusNList.size()>0){
            sql_n.append(" AND N.DOC_STATUS in (?)");
            values_n.add(statusNList);
        }
        // 操作时间
        if (queryDto.getOperateStartDate() != null) {
            sql_n.append(" and N.VERIFIED_TIME >= ?  ");
            values_n.add(queryDto.getOperateStartDate());
        }
        if (queryDto.getOperateEndDate() != null) {           
            sql_n.append(" and N.VERIFIED_TIME < ?  ");
            values_n.add(DateUtils.reset(DateUtils.addDay(queryDto.getOperateEndDate(), 1)));
        }
     // 业务号
        if (StringUtils.isNotBlank(queryDto.getBusinessNo())) {
            sql_n.append(" and EXISTS(SELECT 1 FROM VC_NORMAL_VERIFIED_DET DET ");
            sql_n.append(" WHERE N.ID_VC_NORMAL_VERIFICATION=DET.ID_VC_NORMAL_VERIFICATION AND DET.BUSINESS_NO = ?) ");
            values_n.add(queryDto.getBusinessNo());
        }
        // 操作人
        if (StringUtils.isNotBlank(queryDto.getOprCode())) {
            sql_n.append(" and N.VERIFIED_OPR_CODE = ? ");
            values_n.add(queryDto.getOprCode());
        }
      //机构
        if (orgSql.length()>0) {
            sql_n.append(" and N.VERIFIED_ORG_CODE in(").append(orgSql).append(") ");
            values_n.addAll(orgValues);
        }
        sql_n.append(" ) ");
        //add by chy 20131122 end

        StringBuilder sql_an = new StringBuilder(100);
        List<Object> values_an=new ArrayList<Object>();
        sql_an.append(" (SELECT AN.DOC_VER_CODE, AN.DOC_NUM START_NUM, AN.DOC_NUM END_NUM, AN.DOC_STATUS STATUS, AN.VERIFIED_ORG_CODE ORG_CODE,");
        sql_an.append(" AN.VERIFIED_OPR_CODE OPR_CODE, null as OWNER,AN.VERIFIED_TIME OPERATETIME ,AN.BUSINESS_NO ");
        sql_an.append(" FROM VC_ABNORMAL_VERIFICATION AN WHERE 1=1");
        //单证类型
        if (queryDto.getArrDocVerCode()!=null && queryDto.getArrDocVerCode().length>0 ) {
            sql_an.append(" AND AN.DOC_VER_CODE in(?) ");
            values_an.add(queryDto.getArrDocVerCode());
        }   
       
        if(statusANList.size()>0){
            sql_an.append(" AND AN.DOC_STATUS in (?)");
            values_an.add(statusANList);
        }
        // 操作时间
        if (queryDto.getOperateStartDate() != null) {
            sql_an.append(" and AN.VERIFIED_TIME >= ?  ");
            values_an.add(queryDto.getOperateStartDate());
        }
        if (queryDto.getOperateEndDate() != null) {            
            sql_an.append(" and AN.VERIFIED_TIME < ?  ");
            values_an.add(DateUtils.reset(DateUtils.addDay(queryDto.getOperateEndDate(), 1)));
        }
        // 业务号
        if (StringUtils.isNotBlank(queryDto.getBusinessNo())) {
            sql_an.append(" and EXISTS(SELECT 1 FROM VC_ABNORMAL_VERIFIED_DET DET ");
            sql_an.append(" WHERE AN.ID_VC_ABNORMAL_VERIFICATION=DET.ID_VC_ABNORMAL_VERIFICATION AND DET.BUSINESS_NO = ?) ");
            values_an.add(queryDto.getBusinessNo());
        }
        // 操作人
        if (StringUtils.isNotBlank(queryDto.getOprCode())) {
            sql_an.append(" and AN.VERIFIED_OPR_CODE = ? ");
            values_an.add(queryDto.getOprCode());
        }
        //机构
        if (orgSql.length()>0) {
            sql_an.append(" and AN.VERIFIED_ORG_CODE in(").append(orgSql).append(") ");
            values_an.addAll(orgValues);
        }
        sql_an.append(" ) ");
        
        
        //==================================组装四段查询==========================
        StringBuilder sql = new StringBuilder(100);
        List<Object> values = new ArrayList<Object>();
        
        sql.append(" from (");
        if (queryDto.getArrStatus() != null && queryDto.getArrStatus().length>0) {
            ///业务号存在则不查询库存及可使用表
            if(StringUtils.isBlank(queryDto.getBusinessNo())){
                if(statusSList.size()>0){
                    sql.append(sql_s).append("union"); 
                    values.addAll(values_s);
                }
                if(statusAList.size()>0){
                    sql.append(sql_a).append("union"); 
                    values.addAll(values_a);
                }
            }
            if(statusNList.size()>0){
                sql.append(sql_n).append("union"); 
                values.addAll(values_n);
            }
            if(statusANList.size()>0){
                sql.append(sql_an).append("union"); 
                values.addAll(values_an);
            }
            sql.setLength(sql.length()-5);//去掉最后一个union
        }else{
          ///业务号存在则不查询库存及可使用表
            if(StringUtils.isBlank(queryDto.getBusinessNo())){
                sql.append(sql_s).append("union").append(sql_a).append("union");
                values.addAll(values_s);
                values.addAll(values_a);
            }
            sql.append(sql_n).append("union").append(sql_an);           
            values.addAll(values_n);
            values.addAll(values_an);
        }
        sql.append(") ta, VC_DOC_VERSION_INFO ver ");
        sql.append(" where ta.DOC_VER_CODE=ver.DOC_VER_CODE  ");       
        if (queryDto != null) {
            // 起始流水、终止流水
            if (StringUtils.isNotBlank(queryDto.getStartNum())) {
                sql.append(" and (");
                sql.append(" ( ? between to_number(ta.START_NUM) and to_number(ta.END_NUM) ) ");
                values.add(Long.valueOf(queryDto.getStartNum()));
                if (StringUtils.isNotBlank(queryDto.getEndNum())) {
                    sql.append(" or ( ? between to_number(ta.START_NUM) and to_number(ta.END_NUM) ) ");
                    values.add(Long.valueOf(queryDto.getEndNum()));
                    sql.append(" or ( ? < to_number(ta.START_NUM)  and  ? > to_number(ta.END_NUM) ) ");
                    values.add(Long.valueOf(queryDto.getStartNum()));
                    values.add(Long.valueOf(queryDto.getEndNum()));
                } else {
                    sql.append(" or ( ? < =to_number(ta.END_NUM) ) ");
                    values.add(Long.valueOf(queryDto.getStartNum()));
                }
                sql.append(" ) ");
            } else {
                if (StringUtils.isNotBlank(queryDto.getEndNum())) {
                    sql.append(" and ( ? >= to_number(ta.START_NUM) ) ");
                    values.add(Long.valueOf(queryDto.getEndNum()));
                }
            }
        }
        
        StringBuilder sql_count = new StringBuilder(100);
        sql_count.append("select count(*) ").append(sql);

        Long totalCount = 0L;
        List tempCount = this.findBySql(sql_count.toString(), values.toArray());
        Object countObj = tempCount.get(0);
        totalCount = ((BigDecimal) countObj).longValue();
        
        
        List<DocDetailInquiryVo> resultList = new ArrayList<DocDetailInquiryVo>();     
        if(totalCount==0){
          return  new Page(pageNo, pageSize, totalCount, resultList);
        }

        // 查询分页列表
        StringBuilder sql_query = new StringBuilder(100);
        sql_query.append("select * from(");
        sql_query.append("select ta.*,ver.DOC_VER_Name,rowNum numId ")
                .append(sql);
        sql_query.append(") tableView where tableView.numId between ? and ?");
        values.add((pageNo - 1) * pageSize + 1);
        values.add(pageNo * pageSize);
        List list = null;
        try {
            list = this.findBySql(sql_query.toString(), values.toArray());
        } catch (Exception e) {
            throw new DaoException("查询数据库异常！", e);
        }
          
        if (list != null && list.size() > 0) {
            Map<String,String> statusMap=new HashMap<String,String>();
            String statusName="";
            DocDetailInquiryVo tempVo = null;
            for (int i = 0; i < list.size(); i++) {
                tempVo = new DocDetailInquiryVo();
                Object[] obj = (Object[]) list.get(i);

                tempVo.setDocVerCode((String) obj[0]);
                tempVo.setStartNum((String) obj[1]);
                tempVo.setEndNum((String) obj[2]);
                
                // 状态
                tempVo.setStatus((String) obj[3]);
               
                if (StringUtils.isNotBlank((String) obj[3])) {
                    if (StringUtils.isNotEmpty(statusMap.get((String) obj[3]))){
                        tempVo.setStatusName(statusMap.get((String) obj[3]));
                    }else{
                        statusName=vcPubCodeManagerDao.getCodeCname("DocStatus", (String) obj[3]);
                        tempVo.setStatusName(statusName); 
                        statusMap.put((String) obj[3], statusName);
                    }
                }
                
                tempVo.setOrgCode((String) obj[4]);
                tempVo.setOrgName(vcLevelDao.getUnitNameByUnitCode((String) obj[4]));
                tempVo.setOprCode((String) obj[5]);
                tempVo.setOprName(vcLevelDao.getPubUseDefNameByCode((String) obj[5]));
                //可使用人
                if (obj[6] != null && !"-1".equals((String) obj[6])) {                    
                    tempVo.setOwner((String) obj[6]);
                    tempVo.setOwnerName(vcTakerDao.getUnitNameByUnitCode((String) obj[6]));
                }
                tempVo.setOperateTime((Date) obj[7]);
                if (obj[8] != null && !"-1".equals((String) obj[8])) {
                    tempVo.setBusinessNo((String) obj[8]);
                }

                tempVo.setDocVerName((String) obj[9]);
                resultList.add(tempVo);
            }
        }

        Page page = new Page(pageNo, pageSize, totalCount, resultList);
        // page.setData(resultList);
        return page;

    }
    
    @Override
    public List<DocDetailInquiryVo> findDocDetailInquiryList(DocDetailInquiryVo queryDto, String comCode) 
		throws DaoException {
    	List<DocDetailInquiryVo> result = null;
    	try{
    		StringBuilder sql_s = new StringBuilder(100);
            sql_s.append(" (SELECT S.DOC_VER_CODE, S.START_NUM, S.END_NUM, S.DOC_STATUS STATUS, ");
            sql_s
                    .append("S.ORG_CODE, S.MODIFY_USER_CODE OPR_CODE, '-1' as OWNER,S.MODIFY_TIME OPERATETIME , '-1' BUSINESS_NO ");
            sql_s.append(" FROM VC_STORAGE S  ");

            StringBuilder sql_a = new StringBuilder(100);
            sql_a
                    .append("  (SELECT A.DOC_VER_CODE, A.DOC_NUM START_NUM, A.DOC_NUM END_NUM, A.DOC_STATUS STATUS, ");
            //sql_a.append(" A.ORG_CODE, A.CREATED_BY OPR_CODE, A.TAKER_CODE as OWNER,a.provide_time OPERATETIME , '-1' BUSINESS_NO ");
            sql_a.append(" A.ORG_CODE, A.TAKER_CODE OPR_CODE, A.TAKER_CODE as OWNER, A.PROVIDE_TIME OPERATETIME , '-1' BUSINESS_NO ");
            sql_a.append(" FROM VC_AVAILABLE_DOC A ");

            StringBuilder sql_n = new StringBuilder(100);
            sql_n
                    .append("  (SELECT N.DOC_VER_CODE, N.DOC_NUM START_NUM, N.DOC_NUM END_NUM,  N.DOC_STATUS STATUS, ");
            sql_n
                    .append(" N.VERIFIED_ORG_CODE ORG_CODE, N.VERIFIED_OPR_CODE OPR_CODE,'-1' as OWNER, N.VERIFIED_TIME OPERATETIME ,N.BUSINESS_NO ");
            sql_n.append(" FROM VC_NORMAL_VERIFICATION N ");

            StringBuilder sql_an = new StringBuilder(100);
            sql_an
                    .append("  (SELECT AN.DOC_VER_CODE, AN.DOC_NUM START_NUM, AN.DOC_NUM END_NUM, AN.DOC_STATUS STATUS, ");
            sql_an
                    .append(" AN.VERIFIED_ORG_CODE ORG_CODE, AN.VERIFIED_OPR_CODE OPR_CODE, '-1' as OWNER,AN.VERIFIED_TIME OPERATETIME ,AN.BUSINESS_NO ");
            sql_an.append(" FROM VC_ABNORMAL_VERIFICATION AN ");

            if (queryDto != null) {
                if (StringUtils.isNotBlank(queryDto.getDocVerCode())) {
                    sql_s.append(" WHERE S.DOC_VER_CODE = '").append(queryDto.getDocVerCode()).append("'");
                    sql_a.append(" WHERE A.DOC_VER_CODE = '").append(queryDto.getDocVerCode()).append("'");
                    sql_n.append(" WHERE N.DOC_VER_CODE = '").append(queryDto.getDocVerCode()).append("'");
                    sql_an.append(" WHERE AN.DOC_VER_CODE = '").append(queryDto.getDocVerCode()).append("'");
                }
            }
            sql_s.append(" ) ");
            sql_a.append(" ) ");
            sql_n.append(" ) ");
            sql_an.append(" ) ");

            StringBuilder sql = new StringBuilder(100);

            // sql.append("select ta.*,ver.DOC_VER_Name,type.DOC_TYPE_CODE, type.DOC_TYPE_NAME ");
            sql.append(" from (");
            // if (queryDto.getStatus() != null) {
            sql.append(sql_s).append(" union ").append(sql_a).append(" union ").append(sql_n).append(" union ")
                    .append(sql_an);
            // }
            sql.append(") ta, VC_DOC_VERSION_INFO ver, VC_DOC_TYPE type ");
            sql.append(" where ta.DOC_VER_CODE=ver.DOC_VER_CODE and ver.ID_VC_DOC_TYPE=type.ID_VC_DOC_TYPE ");
            List<Object> values = new ArrayList<Object>();
            if (queryDto != null) {
                // 单证版本
                /*
                 * if (StringUtils.isNotBlank(queryDto.getDocVerCode())) { sql.append(" and ver.doc_Ver_Code like ? ");
                 * values.add(queryDto.getDocVerCode()); }
                 */
                // 单证种类
                if (StringUtils.isNotBlank(queryDto.getDocTypeCode())) {
                    sql.append(" and type.doc_Type_Code like ? ");
                    values.add(queryDto.getDocTypeCode());
                }

                // 险类// 险种
                if (StringUtils.isNotBlank(queryDto.getInsuType())
                        || StringUtils.isNotBlank(queryDto.getInsuKind())) {
                    sql.append(" and exists(");
                    sql
                            .append(" select 1 from VC_DOC_INSU_KIND insuKind, VC_DOC_REL_INSU_KIND ref ,VC_DOC_INSU_TYPE insuType ");
                    sql
                            .append(" where insuKind.INSU_KIND_CODE=ref.INSU_KIND_CODE and ref.ID_VC_DOC_VERSION_INFO=ver.ID_VC_DOC_VERSION_INFO ");
                    sql.append(" and insuKind.ID_VC_DOC_INSU_TYPE=insuType.ID_VC_DOC_INSU_TYPE ");
                    if (StringUtils.isNotBlank(queryDto.getInsuType())) {
                        sql.append(" and insuType.INSU_TYPE_CODE=?");
                        values.add(queryDto.getInsuType());
                    }
                    if (StringUtils.isNotBlank(queryDto.getInsuKind())) {
                        sql.append(" and insuKind.INSU_KIND_CODE=?");
                        values.add(queryDto.getInsuKind());
                    }
                    sql.append(" ) ");
                }

                // 单证状态
                /*
                 * if (StringUtils.isNotBlank(queryDto.getStatus())) { sql.append(" and ta.STATUS IN (?) ");
                 * values.add(queryDto.getStatus()); }
                 */
                if (queryDto.getArrStatus() != null) {
                    sql.append(" and ta.STATUS IN ( ");
                    for (String status : queryDto.getArrStatus()) {
                        sql.append(" ? ,");
                        values.add(status);
                    }
                    // 替换最后一个，
                    sql.replace(sql.length() - 1, sql.length(), " ) ");
                    // sql.append(" ) ");
                }

                // 操作时间
                if (queryDto.getOperateStartDate() != null) {
                    sql.append(" and ta.OPERATETIME >= ?  ");
                    values.add(queryDto.getOperateStartDate());
                }
                if (queryDto.getOperateEndDate() != null) {
                    Date endDate = queryDto.getOperateEndDate();
                    endDate.setHours(23);
                    endDate.setMinutes(59);
                    endDate.setSeconds(59);
                    sql.append(" and ta.OPERATETIME <= ?  ");
                    values.add(endDate);
                }

                // 起始流水、终止流水
                if (StringUtils.isNotBlank(queryDto.getStartNum())) {
                    sql.append(" and (");
                    sql.append(" ( ? between ta.START_NUM and ta.END_NUM ) ");
                    values.add(queryDto.getStartNum());
                    if (StringUtils.isNotBlank(queryDto.getEndNum())) {
                        sql.append(" or ( ? between ta.START_NUM and ta.END_NUM ) ");
                        values.add(queryDto.getEndNum());
                        sql.append(" or ( ? < ta.START_NUM  and  ? > ta.END_NUM ) ");
                        values.add(queryDto.getStartNum());
                        values.add(queryDto.getEndNum());
                    } else {
                        sql.append(" or ( ? < =ta.END_NUM ) ");
                        values.add(queryDto.getStartNum());
                    }
                    sql.append(" ) ");
                } else {
                    if (StringUtils.isNotBlank(queryDto.getEndNum())) {
                        sql.append(" and ( ? >= ta.START_NUM ) ");
                        values.add(queryDto.getEndNum());
                    }
                }

                // 操作人
                if (StringUtils.isNotBlank(queryDto.getOprCode())) {
                    sql.append(" and ta.OPR_CODE = ? ");
                    values.add(queryDto.getOprCode());
                }

                // 所属机构
                if (StringUtils.isNotBlank(queryDto.getOrgCode())) {
                    if (queryDto.getIsContainSubOrg() != null && "1".equals(queryDto.getIsContainSubOrg())) {
                        // TODO包含下级的相关处理
                        // sql.append(" and ta.ORG_CODE like ? ");
                        // values.add(queryDto.getOrgCode() + "%");
                        // level是oracle关键字，顾不能用别名level//
                        sql
                                .append(" and exists (select 1 from Vc_Level lev where ta.ORG_CODE =lev.unit_code  Start with lev.unit_code=? ");
                        values.add(queryDto.getOrgCode());
                        sql.append(" connect by PRIOR lev.ID_VC_LEVEL=lev.PARENT_ORG_ID) ");
                    } else {
                        sql.append(" and ta.ORG_CODE = ? ");
                        values.add(queryDto.getOrgCode());
                    }
                }
            }
            // 当前用户权限控制(总公司、分公司)
            if (comCode != null) {
                // sql.append(" and ta.ORG_CODE like ? ");
                // values.add(userInfo.getComCode()+"%");
                // level是oracle关键字，不能用别名level//
                sql
                        .append(" and exists (select 1 from Vc_Level le where ta.ORG_CODE =le.unit_code  Start with le.unit_code=? ");
                values.add(comCode);
                sql.append(" connect by PRIOR le.ID_VC_LEVEL=le.PARENT_ORG_ID) ");
            }
            
           //判断记录数是否大于最大导出记录数
            StringBuilder sql_count = new StringBuilder(100);
            sql_count.append("select count(*) ").append(sql);
            Long totalCount = 0L;
            List tempCount = this.findBySql(sql_count.toString(), values.toArray());
            Object countObj = tempCount.get(0);
            totalCount = ((BigDecimal) countObj).longValue();
            if(totalCount>SysConst.EXCEL_EXPORT_MAX_COUNT){
                throw new DaoException("导出记录数超出"+SysConst.EXCEL_EXPORT_MAX_COUNT+"条限制，请缩小查询范围！");
            }
            
            StringBuilder sql_query = new StringBuilder(100);
            sql_query.append("select ta.*,ver.DOC_VER_Name,type.DOC_TYPE_CODE, type.DOC_TYPE_NAME ")
                    .append(sql);
            List list = null;
            list = this.findBySql(sql_query.toString(), values.toArray());

            /*
             * List tempList=this.findBySql(sql.toString(), values.toArray()); Long count=this.getCount(sql.toString(),
             * values.toArray()); Page page = this.findBySql(sql.toString(), pageNo, pageSize,values.toArray());
             * List<DocDetailInquiryVo> resultList = new ArrayList<DocDetailInquiryVo>();
             */

            // List list = page.getResult();
            result = new ArrayList<DocDetailInquiryVo>();
            if (list != null && list.size() > 0) {
                DocDetailInquiryVo tempVo = null;
                for (int i = 0; i < list.size(); i++) {
                    tempVo = new DocDetailInquiryVo();
                    Object[] obj = (Object[]) list.get(i);

                    tempVo.setDocVerCode((String) obj[0]);
                    tempVo.setStartNum((String) obj[1]);
                    tempVo.setEndNum((String) obj[2]);
                    
                    // 状态
                    tempVo.setStatus((String) obj[3]);
                    
                    tempVo.setOrgCode((String) obj[4]);
                    tempVo.setOrgName(vcLevelDao.getUnitNameByUnitCode((String) obj[4]));
                    tempVo.setOprCode((String) obj[5]);
                    tempVo.setOprName(vcLevelDao.getPubUseDefNameByCode((String) obj[5]));
                     //可使用人
                    if (obj[6] != null && !"-1".equals((String) obj[6])) {                    
                        tempVo.setOwner((String) obj[6]);
                        tempVo.setOwnerName(vcTakerDao.getUnitNameByUnitCode((String) obj[6]));
                    }
                    tempVo.setOperateTime((Date) obj[7]);
                    if (obj[8] != null && !"-1".equals((String) obj[8])) {
                        tempVo.setBusinessNo((String) obj[8]);
                    }

                    tempVo.setDocVerName((String) obj[9]);
                    tempVo.setDocTypeCode((String) obj[10]);
                    tempVo.setDocTypeName((String) obj[11]);
                    result.add(tempVo);
                }
            }
    	}catch(DaoException de){
            throw de;
        }catch(Exception e){
    		throw new DaoException("查询数据库异常！", e);
    	}
    	return result;
    }

    @Override
    public Page queryLostVerification(LostVerificationInquiryVo lostVo, int pageNo, int pageSize)
            throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append(" from");
        List params = new ArrayList();
        StringBuffer sqli = new StringBuffer();
        StringBuffer sqlii = new StringBuffer();
        sqli
                .append(" ( SELECT vl.lost_code,dv.doc_ver_code,dv.doc_ver_name,ld.press_batch_no,ld.start_num,ld.end_num,ld.lost_num,vclevel.unit_name,vl.lost_org_code,vl.lost_time,vl.lost_status ");
        sqli
                .append(" FROM vc_lost vl ,vc_lost_det ld,vc_doc_type dt,vc_doc_version_info dv,vc_level vclevel ");
        sqli.append(" WHERE dv.id_vc_doc_type = dt.id_vc_doc_type ");
        sqli.append(" AND vl.id_vc_lost = ld.id_vc_lost ");
        sqli.append(" AND ld.doc_ver_code = dv.doc_ver_code ");
        sqli.append(" AND vl.lost_org_code = vclevel.unit_code ");

        sqlii
                .append(" ( SELECT DISTINCT * FROM ( SELECT vl.lost_code,dv.doc_ver_code,dv.doc_ver_name,ld.press_batch_no,ld.start_num,ld.end_num,ld.lost_num,vclevel.unit_name,vl.lost_org_code,vl.lost_time,vl.lost_status ");
        sqlii
                .append(" FROM vc_lost vl,vc_lost_det ld ,vc_doc_version_info dv,vc_doc_rel_insu_kind r,vc_doc_insu_kind ik,vc_doc_insu_type it,vc_doc_type dt,vc_level vclevel  ");
        sqlii.append(" WHERE vl.id_vc_lost = ld.id_vc_lost ");
        sqlii.append(" AND r.insu_kind_code = ik.insu_kind_code ");
        sqlii.append(" AND it.id_vc_doc_insu_type = ik.id_vc_doc_insu_type ");
        sqlii.append(" AND ld.doc_ver_code =dv.doc_ver_code ");
        sqlii.append(" AND r.id_vc_doc_version_info = dv.id_vc_doc_version_info ");
        sqlii.append(" AND dt.id_vc_doc_type = dv.id_vc_doc_type ");
        sqlii.append(" AND vl.lost_org_code = vclevel.unit_code ");

        // 发票
        if ("1".equals(lostVo.getDocType())) {

            if (Beans.isNotEmpty(lostVo.getDocTypeCode())) {
                sqli.append(" AND dt.doc_type_code = ? ");
                params.add(lostVo.getDocTypeCode());
            }
            if (Beans.isNotEmpty(lostVo.getDocType())) {
                sqli.append(" AND dt.doc_type = ?  ");
                params.add(lostVo.getDocType());
            }
            sqli.append("  ) t ");
            sql.append(sqli);

        } else {

            if (Beans.isEmpty(lostVo.getInsuKind()) && Beans.isEmpty(lostVo.getInsuType())) {

                if (Beans.isNotEmpty(lostVo.getDocTypeCode())) {
                    sqli.append(" AND dt.doc_type_code = ? ");
                    params.add(lostVo.getDocTypeCode());
                }
                if (Beans.isNotEmpty(lostVo.getDocType())) {
                    sqli.append(" AND dt.doc_type = ?  ");
                    params.add(lostVo.getDocType());
                }
                sqli.append(" ) t ");
                sql.append(sqli);
            } else {

                if (Beans.isNotEmpty(lostVo.getDocTypeCode())) {
                    sqlii.append(" AND dt.doc_type_code = ? ");
                    params.add(lostVo.getDocTypeCode());
                }
                if (Beans.isNotEmpty(lostVo.getDocType())) {
                    sqlii.append(" AND dt.doc_type = ?  ");
                    params.add(lostVo.getDocType());
                }

                if (Beans.isNotEmpty(lostVo.getInsuKind())) {
                    sqlii.append(" AND ik.insu_kind_code = ? ");
                    params.add(lostVo.getInsuKind());
                }

                if (Beans.isNotEmpty(lostVo.getInsuType())) {
                    sqlii.append(" AND it.insu_type_code = ? ");
                    params.add(lostVo.getInsuType());
                }
                sqlii.append(" ) ) t ");
                sql.append(sqlii);
            }

        }

        sql.append(" WHERE 1 = 1 ");

        if (Beans.isNotEmpty(lostVo.getLostCode())) {
            sql.append(" AND t.lost_code = ? ");
            params.add(lostVo.getLostCode());
        }

        if (Beans.isNotEmpty(lostVo.getDocVerCode())) {
            sql.append(" AND t.doc_ver_code = ? ");
            params.add(lostVo.getDocVerCode());
        }
        //单证类型多选
        if (Beans.isNotEmpty(lostVo.getStrDocVerCodes())) {
            String[] arrCode=(lostVo.getStrDocVerCodes()).split(",");
            sql.append(" AND t.doc_ver_code in( ?  ");
            params.add(arrCode[0]);
            for(String code : arrCode){
                sql.append(",  ? ");
                params.add(code);
            }
            sql.append(") ");
        }
        
        if (Beans.isNotEmpty(lostVo.getApplyStartDate())) {
            sql.append(" AND t.lost_time  >= ? ");
            params.add(lostVo.getApplyStartDate());
        }

        if (Beans.isNotEmpty(lostVo.getApplyEndDate())) {
            sql.append(" AND t.lost_time  < ? ");
            params.add(DateUtils.addDay(lostVo.getApplyEndDate(), 1));
        }

        sql.append(" AND t.lost_org_code IN (" + " SELECT Z.UNIT_CODE " + " FROM VC_LEVEL Z "
                + " WHERE Z.UNIT_TYPE = '0' " + " START WITH Z.UNIT_CODE = '" + lostVo.getLostOrgCode()
                + "' " + " CONNECT BY Z.PARENT_ORG_ID = PRIOR Z.ID_VC_LEVEL " + ") ");

        if (Beans.isNotEmpty(lostVo.getLostStatus())) {
            sql.append(" AND t.lost_status = ? ");
            params.add(lostVo.getLostStatus());
        }

        StringBuilder sql_count = new StringBuilder(100);
        sql_count.append("select count(*) ").append(sql);
        Long totalCount = 0L;
        try {
            List tempCount = this.findBySql(sql_count.toString(), params.toArray());
            Object countObj = tempCount.get(0);
            totalCount = ((BigDecimal) countObj).longValue();
        } catch (Exception e) {
            throw new DaoException("查询数据库异常！", e);
        }

        // 查询分页列表
        StringBuilder sql_query = new StringBuilder(100);
        sql_query.append(" select * from(");
        sql_query.append(" select t.*,rowNum numId ").append(sql);
        sql_query.append(") t2 where t2.numId between ? and ?");
        params.add((pageNo - 1) * pageSize + 1);
        params.add(pageNo * pageSize);
        List result = null;
        try {
            result = this.findBySql(sql_query.toString(), params.toArray());
        } catch (Exception e) {
            throw new DaoException("查询数据库异常！", e);
        }
        List<LostVerificationInquiryVo> lostVos = new ArrayList<LostVerificationInquiryVo>();
        for (Iterator iterator = result.iterator(); iterator.hasNext();) {
            Object[] object = (Object[]) iterator.next();
            LostVerificationInquiryVo lostVo_ = new LostVerificationInquiryVo();
            lostVo_.setLostCode(object[0].toString());
            lostVo_.setDocVerCode((String) object[1]);
            lostVo_.setDocVerName((String) object[2]);
            lostVo_.setPressBatchNo((String) object[3]);
            lostVo_.setStartNum((String) object[4]);
            lostVo_.setEndNum((String) object[5]);
            lostVo_.setLostNum(((BigDecimal) object[6]).longValue());
            lostVo_.setLostOrgCode((String) object[7]);
            lostVo_.setLostTime((Date) object[9]);
            lostVos.add(lostVo_);
        }
        return new Page(pageNo, pageSize, totalCount, lostVos);
    }
    
    @Override
    public List<LostVerificationInquiryVo> findLostVerification(LostVerificationInquiryVo lostVo) throws DaoException {
    	List<LostVerificationInquiryVo> result = null;
    	try{
    		StringBuffer sql = new StringBuffer();
            sql.append(" from");
            List params = new ArrayList();
            StringBuffer sqli = new StringBuffer();
            StringBuffer sqlii = new StringBuffer();
            sqli
                    .append(" ( SELECT vl.lost_code,dv.doc_ver_code,dv.doc_ver_name,ld.press_batch_no,ld.start_num,ld.end_num,ld.lost_num,vclevel.unit_name,vl.lost_org_code,vl.lost_time,vl.lost_status ");
            sqli
                    .append(" FROM vc_lost vl ,vc_lost_det ld,vc_doc_type dt,vc_doc_version_info dv,vc_level vclevel ");
            sqli.append(" WHERE dv.id_vc_doc_type = dt.id_vc_doc_type ");
            sqli.append(" AND vl.id_vc_lost = ld.id_vc_lost ");
            sqli.append(" AND ld.doc_ver_code = dv.doc_ver_code ");
            sqli.append(" AND vl.lost_org_code = vclevel.unit_code ");

            sqlii
                    .append(" ( SELECT DISTINCT * FROM ( SELECT vl.lost_code,dv.doc_ver_code,dv.doc_ver_name,ld.press_batch_no,ld.start_num,ld.end_num,ld.lost_num,vclevel.unit_name,vl.lost_org_code,vl.lost_time,vl.lost_status ");
            sqlii
                    .append(" FROM vc_lost vl,vc_lost_det ld ,vc_doc_version_info dv,vc_doc_rel_insu_kind r,vc_doc_insu_kind ik,vc_doc_insu_type it,vc_doc_type dt,vc_level vclevel ");
            sqlii.append(" WHERE vl.id_vc_lost = ld.id_vc_lost ");
            sqlii.append(" AND r.insu_kind_code = ik.insu_kind_code ");
            sqlii.append(" AND it.id_vc_doc_insu_type = ik.id_vc_doc_insu_type ");
            sqlii.append(" AND ld.doc_ver_code =dv.doc_ver_code ");
            sqlii.append(" AND r.id_vc_doc_version_info = dv.id_vc_doc_version_info ");
            sqlii.append(" AND dt.id_vc_doc_type = dv.id_vc_doc_type ");
            sqlii.append(" AND vl.lost_org_code = vclevel.unit_code ");

            // 发票
            if ("1".equals(lostVo.getDocType())) {

                if (Beans.isNotEmpty(lostVo.getDocTypeCode())) {
                    sqli.append(" AND dt.doc_type_code = ? ");
                    params.add(lostVo.getDocTypeCode());
                }
                if (Beans.isNotEmpty(lostVo.getDocType())) {
                    sqli.append(" AND dt.doc_type = ?  ");
                    params.add(lostVo.getDocType());
                }
                sqli.append("  ) t ");
                sql.append(sqli);

            } else {

                if (Beans.isEmpty(lostVo.getInsuKind()) && Beans.isEmpty(lostVo.getInsuType())) {

                    if (Beans.isNotEmpty(lostVo.getDocTypeCode())) {
                        sqli.append(" AND dt.doc_type_code = ? ");
                        params.add(lostVo.getDocTypeCode());
                    }
                    if (Beans.isNotEmpty(lostVo.getDocType())) {
                        sqli.append(" AND dt.doc_type = ?  ");
                        params.add(lostVo.getDocType());
                    }
                    sqli.append(" ) t ");
                    sql.append(sqli);
                } else {

                    if (Beans.isNotEmpty(lostVo.getDocTypeCode())) {
                        sqlii.append(" AND dt.doc_type_code = ? ");
                        params.add(lostVo.getDocTypeCode());
                    }
                    if (Beans.isNotEmpty(lostVo.getDocType())) {
                        sqlii.append(" AND dt.doc_type = ?  ");
                        params.add(lostVo.getDocType());
                    }

                    if (Beans.isNotEmpty(lostVo.getInsuKind())) {
                        sqlii.append(" AND ik.insu_kind_code = ? ");
                        params.add(lostVo.getInsuKind());
                    }

                    if (Beans.isNotEmpty(lostVo.getInsuType())) {
                        sqlii.append(" AND it.insu_type_code = ? ");
                        params.add(lostVo.getInsuType());
                    }
                    sqlii.append(" ) ) t ");
                    sql.append(sqlii);
                }

            }

            sql.append(" WHERE 1 = 1 ");

            if (Beans.isNotEmpty(lostVo.getLostCode())) {
                sql.append(" AND t.lost_code = ? ");
                params.add(lostVo.getLostCode());
            }

            if (Beans.isNotEmpty(lostVo.getDocVerCode())) {
                sql.append(" AND t.doc_ver_code = ? ");
                params.add(lostVo.getDocVerCode());
            }
            //单证类型多选
            if (Beans.isNotEmpty(lostVo.getStrDocVerCodes())) {
                String[] arrCode=(lostVo.getStrDocVerCodes()).split(",");
                sql.append(" AND t.doc_ver_code in( ?  ");
                params.add(arrCode[0]);
                for(String code : arrCode){
                    sql.append(",  ? ");
                    params.add(code);
                }
                sql.append(") ");
            }
            
            if (Beans.isNotEmpty(lostVo.getApplyStartDate())) {
                sql.append(" AND t.lost_time  >= ? ");
                params.add(lostVo.getApplyStartDate());
            }

            if (Beans.isNotEmpty(lostVo.getApplyEndDate())) {
                sql.append(" AND t.lost_time  < ? ");
                params.add(DateUtils.addDay(lostVo.getApplyEndDate(), 1));
            }

            sql.append(" AND t.lost_org_code IN (" + " SELECT Z.UNIT_CODE " + " FROM VC_LEVEL Z "
                    + " WHERE Z.UNIT_TYPE = '0' " + " START WITH Z.UNIT_CODE = '" + lostVo.getLostOrgCode()
                    + "' " + " CONNECT BY Z.PARENT_ORG_ID = PRIOR Z.ID_VC_LEVEL " + ") ");

            if (Beans.isNotEmpty(lostVo.getLostStatus())) {
                sql.append(" AND t.lost_status = ? ");
                params.add(lostVo.getLostStatus());
            }
            
            StringBuilder sql_query = new StringBuilder(100);
            sql_query.append(" select t.* ").append(sql);
        	
            List list = this.findBySql(sql_query.toString(), params.toArray());
            if(list!=null && list.size()>0){
	            result = new ArrayList<LostVerificationInquiryVo>();
	            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	                Object[] object = (Object[]) iterator.next();
	                LostVerificationInquiryVo lostVo_ = new LostVerificationInquiryVo();
	                lostVo_.setLostCode(object[0].toString());
	                lostVo_.setDocVerCode((String) object[1]);
	                lostVo_.setDocVerName((String) object[2]);
	                lostVo_.setPressBatchNo((String) object[3]);
	                lostVo_.setStartNum((String) object[4]);
	                lostVo_.setEndNum((String) object[5]);
	                lostVo_.setLostNum(((BigDecimal) object[6]).longValue());
	                lostVo_.setLostOrgCode((String) object[7]);
	                lostVo_.setLostTime((Date) object[9]);
	                result.add(lostVo_);
	            }
            }
    	}catch(Exception e){
    		throw new DaoException("查询数据库异常！", e);
    	}
    	return result;
    }
}
