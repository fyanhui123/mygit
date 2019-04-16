package com.tapi.tcs.vc.inquiry.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.inquiry.dao.ApplyInquiryDao;
import com.tapi.tcs.vc.inquiry.vo.ApplyInquiryVo;
import com.tapi.tcs.vc.inquiry.vo.DocInstoreApplyInquiryVo;
import com.tapi.tcs.vc.schema.model.VcApply;
import com.tapi.tcs.vc.schema.model.VcApplyDet;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcLevel;

/**
 * 单证申领查询统计Dao
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
 * @author zhxiao
 * @version 1.0
 */
public class ApplyInquiryDaoImpl extends GenericDaoHibernate<VcApply> implements ApplyInquiryDao {

    private VcLevelDao vcLevelDao;

    public void setVcLevelDao(VcLevelDao vcLevelDao) {
        this.vcLevelDao = vcLevelDao;
    }

    /**
     * 单证申领查询统计
     * 
     * @param applyInquiryVo
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page queryApplyInquiryList(ApplyInquiryVo applyInquiryVo, UserInfo userInfo, int pageNo, int pageSize)
            throws DaoException {
        StringBuffer sql = new StringBuffer();

        sql.append("from VC_APPLY t1, VC_DOC_VERSION_INFO t2, VC_APPLY_DET t3, VC_DOC_TYPE t4  ");

        sql.append("where  t1.ID_VC_APPLY= t3.ID_VC_APPLY and t3.DOC_VER_CODE = t2.DOC_VER_CODE");
        sql.append(" and t4.ID_VC_DOC_TYPE=t2.ID_VC_DOC_TYPE  ");
        List<Object> values = new ArrayList<Object>();

        // 单证类型代码
        if (StringUtils.isNotEmpty(applyInquiryVo.getDocVerCode())) {
            sql.append(" and t3.doc_Ver_Code like ? ");
            values.add(applyInquiryVo.getDocVerCode() + "%");
        }

        // 单证种类
        if (StringUtils.isNotBlank(applyInquiryVo.getDocTypeCode())) {
            sql.append(" and t4.doc_Type_Code like ? ");
            values.add(applyInquiryVo.getDocTypeCode());
        }

        // 险类//险种
        if (StringUtils.isNotBlank(applyInquiryVo.getInsuTypeCode())
                || StringUtils.isNotBlank(applyInquiryVo.getInsuKindCode())) {
            sql.append(" and exists(select 1 from Vc_Doc_Insu_Kind insuKind, Vc_Doc_Rel_Insu_Kind ref , Vc_Doc_Insu_TYPE insuType  ");
            sql.append(" where insuKind.insu_Kind_Code=ref.insu_Kind_Code and ref.id_Vc_Doc_Version_Info=t2.id_Vc_Doc_Version_Info ");
            sql.append(" and insuKind.ID_VC_DOC_INSU_TYPE=insuType.ID_VC_DOC_INSU_TYPE ");

            if (StringUtils.isNotBlank(applyInquiryVo.getInsuTypeCode())) {
                sql.append(" and insuType.insu_Type_Code=?");
                values.add(applyInquiryVo.getInsuTypeCode());
            }
            if (StringUtils.isNotBlank(applyInquiryVo.getInsuKindCode())) {
                sql.append(" and insuKind.insu_Kind_Code=?");
                values.add(applyInquiryVo.getInsuKindCode());
            }
            sql.append(" ) ");
        }

        String applyCode = applyInquiryVo.getApplyCode();
        // 拼接申领单号
        if (StringUtils.isNotEmpty(applyCode)) {
            sql.append(" and t1.apply_Code like ? ");
            values.add(applyCode + "%");
        }
        // 拼接申请人
        String applyOprCode = applyInquiryVo.getApplyOprCode();
        if (StringUtils.isNotEmpty(applyOprCode)) {
            sql.append(" and t1.apply_Opr_Code = ? ");
            values.add(applyOprCode);
        }
        // 拼接申请处理状态
        String applyStatus = applyInquiryVo.getApplyStatus();
        if (StringUtils.isNotEmpty(applyStatus)) {
            sql.append(" and t1.apply_Status = ? ");
            values.add(applyStatus);
        }
        // 拼接申请时间
        Date applyStartDate = applyInquiryVo.getApplyStartDate();
        if (applyStartDate != null) {

            sql.append(" and t1.apply_Time >= ?  ");
            values.add(applyStartDate);
        }
        Date applyEndDate = applyInquiryVo.getApplyEndDate();
        if (applyEndDate != null) {
            applyEndDate.setHours(23);
            applyEndDate.setMinutes(59);
            applyEndDate.setSeconds(59);
            sql.append(" and t1.apply_Time <= ?  ");
            values.add(applyEndDate);
        }
        // 拼接申请机构
        String applyOrgCode = applyInquiryVo.getApplyOrgCode();
        if (StringUtils.isNotEmpty(applyOrgCode)) {
            if (StringUtils.isNotEmpty(applyInquiryVo.getIsContainSubOrg())
                    && "1".equals(applyInquiryVo.getIsContainSubOrg())) {

                // 机构及下级机构匹配
                sql.append(" and exists (select lev1.Id_vc_level from Vc_Level lev1 ");
                sql.append("where t1.apply_Org_Code =lev1.unit_code and lev1.LEVEL_NO<=5 and lev1.unit_type='0' ");
                sql.append(" Start with lev1.UNIT_CODE=? ");
                values.add(applyOrgCode);
                sql.append(" connect by PRIOR lev1.ID_VC_LEVEL=lev1.PARENT_ORG_ID) ");
            } else {
                sql.append(" and t1.apply_Org_Code = ? ");
                values.add(applyOrgCode);
            }
        }
        // 当前用户权限控制(总公司、分公司)
        if (StringUtils.isEmpty(applyOrgCode) && userInfo.getComCode() != null) {
            // 当前用户所属机构的所用下级机构
            sql.append(" and exists (select lev2.Id_vc_level from Vc_Level lev2 ");
            sql.append("where t1.apply_Org_Code =lev2.unit_code and lev2.LEVEL_NO<=3 and lev2.unit_type='0' ");
            sql.append(" Start with lev2.UNIT_CODE=? ");
            values.add(userInfo.getComCode());
            sql.append(" connect by PRIOR lev2.ID_VC_LEVEL=lev2.PARENT_ORG_ID) ");
        }

        try {
            StringBuffer sql_count = new StringBuffer();
            sql_count.append("select count(*) ").append(sql);
            Long totalCount = 0L;
            List tempCount = this.findBySql(sql_count.toString(), values.toArray());
            Object countObj = tempCount.get(0);
            totalCount = ((BigDecimal) countObj).longValue();
            

            // 查询分页列表
            // 0-4
            StringBuffer sql_select = new StringBuffer(
                    "select t1.APPLY_CODE,t1.APPLY_OPR_CODE,t1.APPLY_ORG_CODE,t1.APPLY_TIME,t1.APPLY_STATUS, ");
            // 5-8
            sql_select.append("t3.DOC_VER_CODE,t3.APPLY_NUM, t2.DOC_VER_NAME ,");
            sql_select.append(" rowNum numId  ");
            
            StringBuffer sql_query = new StringBuffer();
            sql_query.append("select * from(");
            sql_query.append(sql_select).append(sql);
            sql_query.append(") tableView where tableView.numId between ? and ?");
            values.add((pageNo - 1) * pageSize + 1);
            values.add(pageNo * pageSize);

            List list = this.findBySql(sql_query.toString(), values.toArray());

            List<ApplyInquiryVo> resultList = new ArrayList<ApplyInquiryVo>();
            if (list != null && list.size() > 0) {
                ApplyInquiryVo tempVo = null;
                for (int i = 0; i < list.size(); i++) {
                    tempVo = new ApplyInquiryVo();
                    Object[] tempObj = (Object[]) list.get(i);
                    tempVo.setApplyCode((String) tempObj[0]);
                    if (tempObj[1] != null) {
                        // tempVo.setApplyOprCode((String) tempObj[1]);
                        tempVo.setApplyOprCode(vcLevelDao.getUnitNameByUnitCode((String) tempObj[1]));
                    }
                    if (tempObj[2] != null) {
                        tempVo.setApplyOrgCode(vcLevelDao.getUnitNameByUnitCode((String) tempObj[2]));
                    }
                    //tempVo.setApplyOprCode(vcLevelDao.getUnitNameByUnitCode((String) tempObj[1]));
                   // tempVo.setApplyOrgCode(vcLevelDao.getUnitNameByUnitCode((String) tempObj[2]));
                    tempVo.setApplyTime((Date) tempObj[3]);
                    tempVo.setApplyStatus((String) tempObj[4]);
                    tempVo.setDocVerCode((String) tempObj[5]);
                    tempVo.setApplyNum(((BigDecimal) tempObj[6]).intValue());
                    tempVo.setDocVerName((String) tempObj[7]);
                    resultList.add(tempVo);
                }
            }
            Page page = new Page(pageNo, pageSize, totalCount, resultList);
            return page;
        } catch (Exception e) {
            throw new DaoException("查询数据库异常！", e);
        }

    }

    @Override
    public List<ApplyInquiryVo> findApplyInquiryList(ApplyInquiryVo applyInquiryVo, String comCode) throws DaoException {
        // 0-4
        StringBuffer sql = new StringBuffer(
                "select t1.APPLY_CODE,t1.APPLY_OPR_CODE,t1.APPLY_ORG_CODE,t1.APPLY_TIME,t1.APPLY_STATUS, ");
        // 5-8
        sql.append("t3.DOC_VER_CODE,t3.APPLY_NUM, t2.DOC_VER_NAME ");
       
        sql.append("from VC_APPLY t1, VC_DOC_VERSION_INFO t2, VC_APPLY_DET t3, VC_DOC_TYPE t4  ");

        sql.append("where  t1.ID_VC_APPLY= t3.ID_VC_APPLY and t3.DOC_VER_CODE = t2.DOC_VER_CODE");
        sql.append(" and t4.ID_VC_DOC_TYPE=t2.ID_VC_DOC_TYPE ");
        List<Object> values = new ArrayList<Object>();

        // 单证类型代码
        if (StringUtils.isNotEmpty(applyInquiryVo.getDocVerCode())) {
            sql.append(" and t3.doc_Ver_Code like ? ");
            values.add(applyInquiryVo.getDocVerCode() + "%");
        }

        // 单证种类
        if (StringUtils.isNotBlank(applyInquiryVo.getDocTypeCode())) {
            sql.append(" and t4.doc_Type_Code like ? ");
            values.add(applyInquiryVo.getDocTypeCode());
        }

        // 险类//险种
        if (StringUtils.isNotBlank(applyInquiryVo.getInsuTypeCode())
                || StringUtils.isNotBlank(applyInquiryVo.getInsuKindCode())) {
            sql.append(" and exists(select 1 from Vc_Doc_Insu_Kind insuKind, Vc_Doc_Rel_Insu_Kind ref , Vc_Doc_Insu_TYPE insuType  ");
            sql.append(" where insuKind.insu_Kind_Code=ref.insu_Kind_Code and ref.id_Vc_Doc_Version_Info=t2.id_Vc_Doc_Version_Info ");
            sql.append(" and insuKind.ID_VC_DOC_INSU_TYPE=insuType.ID_VC_DOC_INSU_TYPE ");

            if (StringUtils.isNotBlank(applyInquiryVo.getInsuTypeCode())) {
                sql.append(" and insuType.insu_Type_Code=?");
                values.add(applyInquiryVo.getInsuTypeCode());
            }
            if (StringUtils.isNotBlank(applyInquiryVo.getInsuKindCode())) {
                sql.append(" and insuKind.insu_Kind_Code=?");
                values.add(applyInquiryVo.getInsuKindCode());
            }
            sql.append(" ) ");
        }

        String applyCode = applyInquiryVo.getApplyCode();
        // 拼接申领单号
        if (StringUtils.isNotEmpty(applyCode)) {
            sql.append(" and t1.apply_Code like ? ");
            values.add(applyCode + "%");
        }
        // 拼接申请人
        String applyOprCode = applyInquiryVo.getApplyOprCode();
        if (StringUtils.isNotEmpty(applyOprCode)) {
            sql.append(" and t1.apply_Opr_Code = ? ");
            values.add(applyOprCode);
        }
        // 拼接申请处理状态
        String applyStatus = applyInquiryVo.getApplyStatus();
        if (StringUtils.isNotEmpty(applyStatus)) {
            sql.append(" and t1.apply_Status = ? ");
            values.add(applyStatus);
        }
        // 拼接申请时间
        Date applyStartDate = applyInquiryVo.getApplyStartDate();
        if (applyStartDate != null) {

            sql.append(" and t1.apply_Time >= ?  ");
            values.add(applyStartDate);
        }
        Date applyEndDate = applyInquiryVo.getApplyEndDate();
        if (applyEndDate != null) {
            applyEndDate.setHours(23);
            applyEndDate.setMinutes(59);
            applyEndDate.setSeconds(59);
            sql.append(" and t1.apply_Time <= ?  ");
            values.add(applyEndDate);
        }
        // 拼接申请机构
        String applyOrgCode = applyInquiryVo.getApplyOrgCode();
        if (StringUtils.isNotEmpty(applyOrgCode)) {
            if (StringUtils.isNotEmpty(applyInquiryVo.getIsContainSubOrg())
                    && "1".equals(applyInquiryVo.getIsContainSubOrg())) {

                // 机构及下级机构匹配
                sql.append(" and exists (select lev1.Id_vc_level from Vc_Level lev1 ");
                sql.append("where t1.apply_Org_Code =lev1.unit_code and lev1.LEVEL_NO<=5 and lev1.unit_type='0' ");
                sql.append(" Start with lev1.UNIT_CODE=? ");
                values.add(applyOrgCode);
                sql.append(" connect by PRIOR lev1.ID_VC_LEVEL=lev1.PARENT_ORG_ID) ");
            } else {
                sql.append(" and t1.apply_Org_Code = ? ");
                values.add(applyOrgCode);
            }
        }
        // 当前用户权限控制(总公司、分公司)
        if (StringUtils.isEmpty(applyOrgCode) && comCode != null) {
            // 当前用户所属机构的所用下级机构
            sql.append(" and exists (select lev2.Id_vc_level from Vc_Level lev2 ");
            sql.append("where t1.apply_Org_Code =lev2.unit_code and lev2.LEVEL_NO<=3 and lev2.unit_type='0' ");
            sql.append(" Start with lev2.UNIT_CODE=? ");
            values.add(comCode);
            sql.append(" connect by PRIOR lev2.ID_VC_LEVEL=lev2.PARENT_ORG_ID) ");
        }

        List<ApplyInquiryVo> resultList = new ArrayList<ApplyInquiryVo>();
        try {
            List list = this.findBySql(sql.toString(), values.toArray());
            if (list != null && list.size() > 0) {
                ApplyInquiryVo tempVo = null;
                for (int i = 0; i < list.size(); i++) {
                    tempVo = new ApplyInquiryVo();
                    Object[] tempObj = (Object[]) list.get(i);
                    tempVo.setApplyCode((String) tempObj[0]);
                    if (tempObj[1] != null) {
                        // tempVo.setApplyOprCode((String) tempObj[1]);
                        tempVo.setApplyOprCode(vcLevelDao.getUnitNameByUnitCode((String) tempObj[1]));
                    }
                    if (tempObj[2] != null) {
                        tempVo.setApplyOrgCode(vcLevelDao.getUnitNameByUnitCode((String) tempObj[2]));
                    }
                    //tempVo.setApplyOprCode(vcLevelDao.getUnitNameByUnitCode((String) tempObj[1]));
                    //tempVo.setApplyOrgCode(vcLevelDao.getUnitNameByUnitCode((String) tempObj[2]));
                    tempVo.setApplyTime((Date) tempObj[3]);
                    tempVo.setApplyStatus((String) tempObj[4]);
                    tempVo.setDocVerCode((String) tempObj[5]);
                    tempVo.setApplyNum(((BigDecimal) tempObj[6]).intValue());
                    tempVo.setDocVerName((String) tempObj[7]);
                    resultList.add(tempVo);
                }
            }
            return resultList;
        } catch (Exception e) {
            throw new DaoException("查询数据库异常！", e);
        }
    }
}
