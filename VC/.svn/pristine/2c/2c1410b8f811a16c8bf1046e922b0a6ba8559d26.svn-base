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
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.inquiry.dao.StorageInquiryDao;
import com.tapi.tcs.vc.inquiry.vo.StorageInquiryVo;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcStorage;

/**
 * 单证库存查询统计Dao
 * <p>
 * Date 2013-04-16
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
public class StorageInquiryDaoImpl extends GenericDaoHibernate<VcStorage> implements StorageInquiryDao {

    /**
     * 单证库存查询统计-机构\使用人
     * 
     * @param storageInquiryVo
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page queryStorageInquiryList(StorageInquiryVo storageInquiryVo, UserInfo userInfo, int pageNo,
            int pageSize) throws DaoException {

        StringBuilder sql_a = new StringBuilder(100);
        // 0-库存 1-单证类型 2-印刷批次 3-单证状态 4-使用人/机构 5-归属机构 6-入库时间
        sql_a
                .append(" SELECT COUNT(T.DOC_NUM) AS DOC_NUM, T.DOC_VER_CODE,T.PRESS_BATCH_NO,T.DOC_STATUS,T.TAKER_CODE AS DOC_TAKER_CODE, T.ORG_CODE, TO_DATE(TO_CHAR(T.PROVIDE_TIME,'yyyy-MM-dd'),'yyyy-MM-dd') AS IN_STORE_TIME");
        sql_a.append(" FROM VC_AVAILABLE_DOC T ");
        sql_a.append(" WHERE T.DOC_STATUS = 'A' ");
        sql_a
                .append(" GROUP BY T.DOC_VER_CODE, T.PRESS_BATCH_NO, T.DOC_STATUS, T.TAKER_CODE,T.ORG_CODE, TO_DATE(TO_CHAR(T.PROVIDE_TIME,'yyyy-MM-dd'),'yyyy-MM-dd')");

        StringBuilder sql_g = new StringBuilder(100);
        sql_g
                .append(" SELECT SUM(G.DOC_NUM) AS DOC_NUM, G.DOC_VER_CODE , G.PRESS_BATCH_NO, G.DOC_STATUS , G.ORG_CODE as DOC_TAKER_CODE, G.ORG_CODE,TO_DATE(TO_CHAR(G.IN_STORE_TIME,'yyyy-MM-dd'),'yyyy-MM-dd') AS IN_STORE_TIME");
        sql_g.append(" FROM VC_STORAGE G");
        sql_g.append(" WHERE G.DOC_STATUS IN ('S1','S2','S3')");
        sql_g
                .append(" GROUP BY G.DOC_VER_CODE, G.PRESS_BATCH_NO, G.DOC_STATUS, G.ORG_CODE, TO_DATE(TO_CHAR(G.IN_STORE_TIME,'yyyy-MM-dd'),'yyyy-MM-dd')");

        StringBuilder sql = new StringBuilder(100);
        sql.append("FROM(");
        if (storageInquiryVo.getTakerDocType().equals("0")) {
            sql.append(sql_g);
        } else {
            sql.append(sql_a);
        }

        sql.append(") TA, VC_DOC_VERSION_INFO ver, VC_DOC_TYPE type ");
        sql.append(" WHERE TA.DOC_VER_CODE=ver.DOC_VER_CODE and ver.ID_VC_DOC_TYPE=type.ID_VC_DOC_TYPE ");

        List<Object> values = new ArrayList<Object>();

        // 单证种类
        if (StringUtils.isNotBlank(storageInquiryVo.getDocTypeCode())) {
            sql.append(" and type.DOC_TYPE_CODE like ? ");
            values.add(storageInquiryVo.getDocTypeCode() + "%");
        }
        // 单证类型代码
        if (StringUtils.isNotEmpty(storageInquiryVo.getDocVerCode())) {
            sql.append(" AND TA.DOC_VER_CODE like ? ");
            values.add(storageInquiryVo.getDocVerCode() + "%");
        }

        // 险类// 险种
        if (StringUtils.isNotBlank(storageInquiryVo.getInsuTypeCode())
                || StringUtils.isNotBlank(storageInquiryVo.getInsuKindCode())) {
            sql.append(" and exists(");
            sql
                    .append(" select 1 from VC_DOC_INSU_KIND insuKind, VC_DOC_REL_INSU_KIND ref ,VC_DOC_INSU_TYPE insuType ");
            sql
                    .append(" where insuKind.INSU_KIND_CODE=ref.INSU_KIND_CODE and ref.ID_VC_DOC_VERSION_INFO=ver.ID_VC_DOC_VERSION_INFO ");
            sql.append(" and insuKind.ID_VC_DOC_INSU_TYPE=insuType.ID_VC_DOC_INSU_TYPE ");
            if (StringUtils.isNotBlank(storageInquiryVo.getInsuTypeCode())) {
                sql.append(" and insuType.INSU_TYPE_CODE=?");
                values.add(storageInquiryVo.getInsuTypeCode());
            }
            if (StringUtils.isNotBlank(storageInquiryVo.getInsuKindCode())) {
                sql.append(" and insuKind.INSU_KIND_CODE=?");
                values.add(storageInquiryVo.getInsuKindCode());
            }
            sql.append(" ) ");
        }

        // 拼接使用人
        if (storageInquiryVo.getTakerDocType().equals("0")
                && StringUtils.isNotEmpty(storageInquiryVo.getOrgCode())) {
            // 机构
            if (storageInquiryVo.getIsContainSubOrg() != null
                    && "1".equals(storageInquiryVo.getIsContainSubOrg())) {
                // 查询库存机构的所有下级机构
                sql
                        .append(" and exists (select 1 from Vc_Level lev where TA.ORG_CODE =lev.unit_code  Start with lev.unit_code=? ");
                values.add(storageInquiryVo.getOrgCode());
                sql.append(" connect by PRIOR lev.ID_VC_LEVEL=lev.PARENT_ORG_ID) ");
            } else {
                sql.append(" AND TA.DOC_TAKER_CODE = ? ");
                values.add(storageInquiryVo.getOrgCode());
            }
        } else if (storageInquiryVo.getTakerDocType().equals("1")
                && StringUtils.isNotEmpty(storageInquiryVo.getTakerCode())) {
            // 使用人
            sql.append(" AND TA.DOC_TAKER_CODE = ? ");
            values.add(storageInquiryVo.getTakerCode());
        }

        // 当前用户权限控制(总公司、分公司)
        if (userInfo.getComCode() != null) {
            // level是oracle关键字，不能用别名level//
            sql
                    .append(" and exists (select 1 from Vc_Level le where ta.ORG_CODE =le.unit_code  Start with le.unit_code=? ");
            values.add(userInfo.getComCode());
            sql.append(" connect by PRIOR le.ID_VC_LEVEL=le.PARENT_ORG_ID) ");
        }

        // 状态
        if (StringUtils.isNotEmpty(storageInquiryVo.getDocStatus())) {
            sql.append(" AND TA.DOC_STATUS = ? ");
            values.add(storageInquiryVo.getDocStatus());
        }
        // 申请时间
        if (storageInquiryVo.getApplyStartDate() != null) {
            sql.append(" AND TA.IN_STORE_TIME >= ?  ");
            values.add(storageInquiryVo.getApplyStartDate());
        }
        if (storageInquiryVo.getApplyEndDate() != null) {
            sql.append(" AND TA.IN_STORE_TIME <= ?  ");
            Date endDate = storageInquiryVo.getApplyEndDate();
            endDate.setHours(23);
            endDate.setMinutes(59);
            endDate.setSeconds(59);
            values.add(endDate);
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
        List list = this.findBySql(sql_query.toString(), values.toArray());

        List<StorageInquiryVo> resultList = new ArrayList<StorageInquiryVo>();
        if (list != null && list.size() > 0) {
            StorageInquiryVo tempVo = null;
            for (int i = 0; i < list.size(); i++) {
                tempVo = new StorageInquiryVo();
                Object[] obj = (Object[]) list.get(i);
                tempVo.setDocNum(Long.parseLong(obj[0].toString()));
                tempVo.setDocVerCode((String) obj[1]);
                tempVo.setPressBatchNo((String) obj[2]);
                tempVo.setDocStatus((String) obj[3]);
                tempVo.setDocTakerCode((String) obj[4]);
                tempVo.setOrgCode((String) obj[5]);
                tempVo.setInStoreTime((Date) obj[6]);
                tempVo.setDocVerName((String) obj[7]);
                resultList.add(tempVo);
            }
        }
        try {
            Page page = new Page(pageNo, pageSize, totalCount, resultList);
            return page;
        } catch (Exception e) {
            throw new DaoException("查询数据库异常！", e);
        }
    }
    
    public List<StorageInquiryVo> findStorageInquiryList(StorageInquiryVo storageInquiryVo, String comCode) throws DaoException {
    	List<StorageInquiryVo> list = null;
    	try{
    		StringBuilder sql_a = new StringBuilder(100);
            // 0-库存 1-单证类型 2-印刷批次 3-单证状态 4-使用人/机构 5-归属机构 6-入库时间
            sql_a
                    .append(" SELECT COUNT(T.DOC_NUM) AS DOC_NUM, T.DOC_VER_CODE,T.PRESS_BATCH_NO,T.DOC_STATUS,T.TAKER_CODE AS DOC_TAKER_CODE, T.ORG_CODE, TO_DATE(TO_CHAR(T.PROVIDE_TIME,'yyyy-MM-dd'),'yyyy-MM-dd') AS IN_STORE_TIME");
            sql_a.append(" FROM VC_AVAILABLE_DOC T ");
            sql_a.append(" WHERE T.DOC_STATUS = 'A' ");
            sql_a
                    .append(" GROUP BY T.DOC_VER_CODE, T.PRESS_BATCH_NO, T.DOC_STATUS, T.TAKER_CODE,T.ORG_CODE, TO_DATE(TO_CHAR(T.PROVIDE_TIME,'yyyy-MM-dd'),'yyyy-MM-dd')");

            StringBuilder sql_g = new StringBuilder(100);
            sql_g
                    .append(" SELECT SUM(G.DOC_NUM) AS DOC_NUM, G.DOC_VER_CODE , G.PRESS_BATCH_NO, G.DOC_STATUS , G.ORG_CODE as DOC_TAKER_CODE, G.ORG_CODE,TO_DATE(TO_CHAR(G.IN_STORE_TIME,'yyyy-MM-dd'),'yyyy-MM-dd') AS IN_STORE_TIME");
            sql_g.append(" FROM VC_STORAGE G");
            sql_g.append(" WHERE G.DOC_STATUS IN ('S1','S2','S3')");
            sql_g
                    .append(" GROUP BY G.DOC_VER_CODE, G.PRESS_BATCH_NO, G.DOC_STATUS, G.ORG_CODE, TO_DATE(TO_CHAR(G.IN_STORE_TIME,'yyyy-MM-dd'),'yyyy-MM-dd')");

            StringBuilder sql = new StringBuilder(100);
            sql.append("FROM(");
            if (storageInquiryVo.getTakerDocType().equals("0")) {
                sql.append(sql_g);
            } else {
                sql.append(sql_a);
            }

            sql.append(") TA, VC_DOC_VERSION_INFO ver, VC_DOC_TYPE type ");
            sql.append(" WHERE TA.DOC_VER_CODE=ver.DOC_VER_CODE and ver.ID_VC_DOC_TYPE=type.ID_VC_DOC_TYPE ");

            List<Object> values = new ArrayList<Object>();

            // 单证种类
            if (StringUtils.isNotBlank(storageInquiryVo.getDocTypeCode())) {
                sql.append(" and type.DOC_TYPE_CODE like ? ");
                values.add(storageInquiryVo.getDocTypeCode() + "%");
            }
            // 单证类型代码
            if (StringUtils.isNotEmpty(storageInquiryVo.getDocVerCode())) {
                sql.append(" AND TA.DOC_VER_CODE like ? ");
                values.add(storageInquiryVo.getDocVerCode() + "%");
            }

            // 险类// 险种
            if (StringUtils.isNotBlank(storageInquiryVo.getInsuTypeCode())
                    || StringUtils.isNotBlank(storageInquiryVo.getInsuKindCode())) {
                sql.append(" and exists(");
                sql
                        .append(" select 1 from VC_DOC_INSU_KIND insuKind, VC_DOC_REL_INSU_KIND ref ,VC_DOC_INSU_TYPE insuType ");
                sql
                        .append(" where insuKind.INSU_KIND_CODE=ref.INSU_KIND_CODE and ref.ID_VC_DOC_VERSION_INFO=ver.ID_VC_DOC_VERSION_INFO ");
                sql.append(" and insuKind.ID_VC_DOC_INSU_TYPE=insuType.ID_VC_DOC_INSU_TYPE ");
                if (StringUtils.isNotBlank(storageInquiryVo.getInsuTypeCode())) {
                    sql.append(" and insuType.INSU_TYPE_CODE=?");
                    values.add(storageInquiryVo.getInsuTypeCode());
                }
                if (StringUtils.isNotBlank(storageInquiryVo.getInsuKindCode())) {
                    sql.append(" and insuKind.INSU_KIND_CODE=?");
                    values.add(storageInquiryVo.getInsuKindCode());
                }
                sql.append(" ) ");
            }

            // 拼接使用人
            if (storageInquiryVo.getTakerDocType().equals("0")
                    && StringUtils.isNotEmpty(storageInquiryVo.getOrgCode())) {
                // 机构
                if (storageInquiryVo.getIsContainSubOrg() != null
                        && "1".equals(storageInquiryVo.getIsContainSubOrg())) {
                    // 查询库存机构的所有下级机构
                    sql
                            .append(" and exists (select 1 from Vc_Level lev where TA.ORG_CODE =lev.unit_code  Start with lev.unit_code=? ");
                    values.add(storageInquiryVo.getOrgCode());
                    sql.append(" connect by PRIOR lev.ID_VC_LEVEL=lev.PARENT_ORG_ID) ");
                } else {
                    sql.append(" AND TA.DOC_TAKER_CODE = ? ");
                    values.add(storageInquiryVo.getOrgCode());
                }
            } else if (storageInquiryVo.getTakerDocType().equals("1")
                    && StringUtils.isNotEmpty(storageInquiryVo.getTakerCode())) {
                // 使用人
                sql.append(" AND TA.DOC_TAKER_CODE = ? ");
                values.add(storageInquiryVo.getTakerCode());
            }

            // 当前用户权限控制(总公司、分公司)
            if (comCode != null) {
                // level是oracle关键字，不能用别名level//
                sql
                        .append(" and exists (select 1 from Vc_Level le where ta.ORG_CODE =le.unit_code  Start with le.unit_code=? ");
                values.add(comCode);
                sql.append(" connect by PRIOR le.ID_VC_LEVEL=le.PARENT_ORG_ID) ");
            }

            // 状态
            if (StringUtils.isNotEmpty(storageInquiryVo.getDocStatus())) {
                sql.append(" AND TA.DOC_STATUS = ? ");
                values.add(storageInquiryVo.getDocStatus());
            }
            // 申请时间
            if (storageInquiryVo.getApplyStartDate() != null) {
                sql.append(" AND TA.IN_STORE_TIME >= ?  ");
                values.add(storageInquiryVo.getApplyStartDate());
            }
            if (storageInquiryVo.getApplyEndDate() != null) {
                sql.append(" AND TA.IN_STORE_TIME <= ?  ");
                Date endDate = storageInquiryVo.getApplyEndDate();
                endDate.setHours(23);
                endDate.setMinutes(59);
                endDate.setSeconds(59);
                values.add(endDate);
            }
            
            StringBuilder sql_query = new StringBuilder(100);
            sql_query.append("select ta.*,ver.DOC_VER_Name,type.DOC_TYPE_CODE, type.DOC_TYPE_NAME ").append(sql);
            List rs = this.findBySql(sql_query.toString(), values.toArray());
            if (rs != null && rs.size() > 0) {
            	list = new ArrayList<StorageInquiryVo>();
                StorageInquiryVo tempVo = null;
                for (int i = 0; i < rs.size(); i++) {
                    tempVo = new StorageInquiryVo();
                    Object[] obj = (Object[]) rs.get(i);
                    tempVo.setDocNum(Long.parseLong(obj[0].toString()));
                    tempVo.setDocVerCode((String) obj[1]);
                    tempVo.setPressBatchNo((String) obj[2]);
                    tempVo.setDocStatus((String) obj[3]);
                    tempVo.setDocTakerCode((String) obj[4]);
                    tempVo.setOrgCode((String) obj[5]);
                    tempVo.setInStoreTime((Date) obj[6]);
                    tempVo.setDocVerName((String) obj[7]);
                    list.add(tempVo);
                }
            }
    	}catch(Exception e){
    		throw new DaoException("查询数据库异常！", e);
    	}
    	return list;
    }
    
    /**
     * 单证库存查询（查询库存表）/导出Excel
     * queryType：1-分页查询；2-导出
     */
    public Object queryStorageInquiryList(StorageInquiryVo storageInquiryVo, UserInfo userInfo, int pageNo,
            int pageSize, String queryType) throws DaoException {
    	List<StorageInquiryVo> resultList = new ArrayList<StorageInquiryVo>();
    	try {
	        StringBuilder sql_g = new StringBuilder(100);
	        sql_g
	                .append(" SELECT G.START_NUM,G.END_NUM,G.DOC_NUM, G.DOC_VER_CODE , G.PRESS_BATCH_NO, G.DOC_STATUS , G.ORG_CODE as DOC_TAKER_CODE, G.ORG_CODE,G.IN_STORE_TIME,G.DEADLINE");
	        sql_g.append(" FROM VC_STORAGE G");
	
	        StringBuilder sql = new StringBuilder(100);
	        sql.append(" FROM(");
	        sql.append(sql_g);
	
	        sql.append(") TA, VC_DOC_VERSION_INFO ver, VC_DOC_TYPE type ");
	        sql.append(" WHERE TA.DOC_VER_CODE=ver.DOC_VER_CODE and ver.ID_VC_DOC_TYPE=type.ID_VC_DOC_TYPE ");
	
	        List<Object> values = new ArrayList<Object>();
	
	        // 单证种类
	        if (StringUtils.isNotBlank(storageInquiryVo.getDocTypeCode())) {
	            sql.append(" and type.DOC_TYPE_CODE like ? ");
	            values.add(storageInquiryVo.getDocTypeCode() + "%");
	        }
	        // 单证类型代码
	        if (StringUtils.isNotEmpty(storageInquiryVo.getDocVerCode())) {
	            sql.append(" AND TA.DOC_VER_CODE like ? ");
	            values.add(storageInquiryVo.getDocVerCode());
	        }
	
	        // 险类// 险种
	        if (StringUtils.isNotBlank(storageInquiryVo.getInsuTypeCode())
	                || StringUtils.isNotBlank(storageInquiryVo.getInsuKindCode())) {
	            sql.append(" and exists(");
	            sql
	                    .append(" select 1 from VC_DOC_INSU_KIND insuKind, VC_DOC_REL_INSU_KIND ref ,VC_DOC_INSU_TYPE insuType ");
	            sql
	                    .append(" where insuKind.INSU_KIND_CODE=ref.INSU_KIND_CODE and ref.ID_VC_DOC_VERSION_INFO=ver.ID_VC_DOC_VERSION_INFO ");
	            sql.append(" and insuKind.ID_VC_DOC_INSU_TYPE=insuType.ID_VC_DOC_INSU_TYPE ");
	            if (StringUtils.isNotBlank(storageInquiryVo.getInsuTypeCode())) {
	                sql.append(" and insuType.INSU_TYPE_CODE=?");
	                values.add(storageInquiryVo.getInsuTypeCode());
	            }
	            if (StringUtils.isNotBlank(storageInquiryVo.getInsuKindCode())) {
	                sql.append(" and insuKind.INSU_KIND_CODE=?");
	                values.add(storageInquiryVo.getInsuKindCode());
	            }
	            sql.append(" ) ");
	        }
	
	        // 拼接机构
	        if (StringUtils.isNotEmpty(storageInquiryVo.getOrgCode())) {
	            // 机构
	            if (storageInquiryVo.getIsContainSubOrg() != null
	                    && "1".equals(storageInquiryVo.getIsContainSubOrg())) {
	                // 查询库存机构的所有下级机构
	                sql.append(" and exists (select 1 from Vc_Level lev where TA.ORG_CODE =lev.unit_code and lev.valid_status='1' and lev.unit_type='0'");
	                sql.append("  Start with lev.unit_code=? ");
	                values.add(storageInquiryVo.getOrgCode());
	                sql.append(" connect by PRIOR lev.ID_VC_LEVEL=lev.PARENT_ORG_ID) ");
	            } else {
	                sql.append(" AND TA.DOC_TAKER_CODE = ? ");
	                values.add(storageInquiryVo.getOrgCode());
	            }
	        }
	        // 当前用户权限控制(总公司、分公司)
	        else  if (userInfo.getComCode() != null) {
	            // level是oracle关键字，不能用别名level//
	        	sql.append(" and exists (select 1 from Vc_Level lev where TA.ORG_CODE =lev.unit_code and lev.valid_status='1' and lev.unit_type='0'");
	            sql.append("  Start with lev.unit_code=? ");
	            values.add(userInfo.getComCode());
	            sql.append(" connect by PRIOR lev.ID_VC_LEVEL=lev.PARENT_ORG_ID) ");
	        }
	
	        // 状态
	        if (StringUtils.isNotEmpty(storageInquiryVo.getDocStatus())) {
	            sql.append(" AND TA.DOC_STATUS = ? ");
	            values.add(storageInquiryVo.getDocStatus());
	        }else {
	        	sql.append(" AND TA.DOC_STATUS IN ('S1','S2','S3')");
	        }
	        // 申请时间
	        if (storageInquiryVo.getApplyStartDate() != null) {
	            sql.append(" AND TA.IN_STORE_TIME >= ?  ");
	            values.add(storageInquiryVo.getApplyStartDate());
	        }
	        if (storageInquiryVo.getApplyEndDate() != null) {
	            sql.append(" AND TA.IN_STORE_TIME < ?  ");
	            Date endDate = DateUtils.addDay(storageInquiryVo.getApplyEndDate(), +1);
	            values.add(endDate);
	        }
	
	        //分页查询
	        if("1".equals(queryType)){
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
		        List list = this.findBySql(sql_query.toString(), values.toArray());
		
		        if (list != null && list.size() > 0) {
		            StorageInquiryVo tempVo = null;
		            for (int i = 0; i < list.size(); i++) {
		                tempVo = new StorageInquiryVo();
		                Object[] obj = (Object[]) list.get(i);
		                tempVo.setStartNum((String)obj[0]);
		                tempVo.setEndNum((String)obj[1]);
		                tempVo.setDocNum(Long.parseLong(obj[2].toString()));
		                tempVo.setDocVerCode((String) obj[3]);
		                tempVo.setPressBatchNo((String) obj[4]);
		                tempVo.setDocStatus((String) obj[5]);
		                tempVo.setDocTakerCode((String) obj[6]);
		                tempVo.setOrgCode((String) obj[7]);
		                tempVo.setInStoreTime((Date) obj[8]);
		                tempVo.setDocVerName((String) obj[10]);
		                //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 BEGIN
		                tempVo.setDeadLine((Date) obj[9]);
		                //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 END
		                resultList.add(tempVo);
		            }
		        }
	            Page page = new Page(pageNo, pageSize, totalCount, resultList);
	            return page;
	        }
	        //导出excel
	        else if("2".equals(queryType)){
		        StringBuilder sql_query = new StringBuilder(100);
		        sql_query.append("select ta.*,ver.DOC_VER_Name,type.DOC_TYPE_CODE, type.DOC_TYPE_NAME ")
		                .append(sql);
		        List list = this.findBySql(sql_query.toString(), values.toArray());
		
		        if (list != null && list.size() > 0) {
		            StorageInquiryVo tempVo = null;
		            for (int i = 0; i < list.size(); i++) {
		                tempVo = new StorageInquiryVo();
		                Object[] obj = (Object[]) list.get(i);
		                tempVo.setStartNum((String)obj[0]);
		                tempVo.setEndNum((String)obj[1]);
		                tempVo.setDocNum(Long.parseLong(obj[2].toString()));
		                tempVo.setDocVerCode((String) obj[3]);
		                tempVo.setPressBatchNo((String) obj[4]);
		                tempVo.setDocStatus((String) obj[5]);
		                tempVo.setDocTakerCode((String) obj[6]);
		                tempVo.setOrgCode((String) obj[7]);
		                tempVo.setInStoreTime((Date) obj[8]);
		                tempVo.setDocVerName((String) obj[10]);
		                //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 BEGIN
		                tempVo.setDeadLine((Date) obj[9]);
		                //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 END
		                resultList.add(tempVo);
		            }
		        }
	        	return resultList;
	        } else{
	        	return null;
	        }
        } catch (Exception e) {
            throw new DaoException("查询数据库异常！", e);
        }
    }
    
    /**
     * 单证库存查询（查询可使用表）/导出Excel
     * queryType：1-查询；2-导出
     */
    public Object queryAvailableInquiryList(StorageInquiryVo storageInquiryVo, UserInfo userInfo, int pageNo,
            int pageSize, String queryType) throws DaoException {
    	List<StorageInquiryVo> resultList = new ArrayList<StorageInquiryVo>();
    	try {
    		List<Object> values = new ArrayList<Object>();
	        StringBuilder sql_a = new StringBuilder(100);
	        // 0-库存 1-单证类型 2-印刷批次 3-单证状态 4-使用人/机构 5-归属机构 6-入库时间
//	        sql_a.append(" SELECT COUNT(T.DOC_NUM) AS DOC_NUM, T.DOC_VER_CODE,T.PRESS_BATCH_NO,T.DOC_STATUS,T.TAKER_CODE AS DOC_TAKER_CODE, T.ORG_CODE, TO_DATE(TO_CHAR(T.PROVIDE_TIME,'yyyy-MM-dd'),'yyyy-MM-dd') AS IN_STORE_TIME");
//	        sql_a.append(" FROM VC_AVAILABLE_DOC T ");
//	        sql_a.append(" GROUP BY T.DOC_VER_CODE, T.PRESS_BATCH_NO, T.DOC_STATUS, T.TAKER_CODE,T.ORG_CODE, TO_DATE(TO_CHAR(T.PROVIDE_TIME,'yyyy-MM-dd'),'yyyy-MM-dd')");
	        sql_a.append("SELECT C.ORG_CODE,C.TAKER_CODE AS DOC_TAKER_CODE,C.DOC_VER_CODE,C.PRESS_BATCH_NO,");
	        sql_a.append("TO_DATE(TO_CHAR(C.PROVIDE_TIME,'yyyy-MM-dd'),'yyyy-MM-dd') IN_STORE_TIME,C.DOC_STATUS,");
	        sql_a.append("MIN(C.DOC_NUM) START_NUM,MAX(C.DOC_NUM) END_NUM,COUNT(C.CC) DOC_NUM,");
	        //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 BEGIN
	        sql_a.append("TO_DATE(TO_CHAR(C.DEADLINE,'yyyy-MM-dd'),'yyyy-MM-dd') DEADLINE");
	        //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 END
	        sql_a.append(" FROM (");
	        sql_a.append("SELECT B.*, (TO_NUMBER(B.DOC_NUM) - ROWNUM) CC FROM(");
	        sql_a.append("SELECT * FROM VC_AVAILABLE_DOC A WHERE 1 = 1");
	        // 单证类型代码
	        if (StringUtils.isNotEmpty(storageInquiryVo.getDocVerCode())) {
	            sql_a.append(" AND A.DOC_VER_CODE like ? ");
	            values.add(storageInquiryVo.getDocVerCode());
	        }
	        // 拼接使用人
	        if (StringUtils.isNotEmpty(storageInquiryVo.getTakerCode())) {
	            // 使用人
	        	sql_a.append(" AND A.TAKER_CODE = ? ");
	            values.add(storageInquiryVo.getTakerCode());
	        }
	
	        // 当前用户权限控制(总公司、分公司)
	        if (userInfo.getComCode() != null) {
	            // level是oracle关键字，不能用别名level//
	        	//modify by zhxiaoITEA-50599单证库存查询top sql优化查询方法 begin
	        	sql_a.append(" and A.ORG_CODE IN (select lev.unit_code from Vc_Level lev where lev.valid_status='1' and lev.unit_type='0'");
	        	////modify by zhxiaoITEA-50599单证库存查询top sql优化查询方法 end
	        	sql_a.append("  Start with lev.unit_code=? ");
	        	values.add(userInfo.getComCode());
	        	sql_a.append(" connect by PRIOR lev.ID_VC_LEVEL=lev.PARENT_ORG_ID) ");
	        }
	
	        // 状态
	        if (StringUtils.isNotEmpty(storageInquiryVo.getDocStatus())) {
	        	sql_a.append(" AND A.DOC_STATUS = ? ");
	            values.add(storageInquiryVo.getDocStatus());
	        }else{
	        	sql_a.append(" AND A.DOC_STATUS = 'A' ");
	        }
	        // 申请时间
	        if (storageInquiryVo.getApplyStartDate() != null) {
	        	sql_a.append(" AND A.PROVIDE_TIME >= ?  ");
	            values.add(storageInquiryVo.getApplyStartDate());
	        }
	        if (storageInquiryVo.getApplyEndDate() != null) {
	        	sql_a.append(" AND A.PROVIDE_TIME < ?  ");
	            Date endDate = DateUtils.addDay(storageInquiryVo.getApplyEndDate(), +1);
	            values.add(endDate);
	        }
	        //ADD BY zhxiao3 VC-115-单证库存查询存在统计号段重复的问题  BEGIN
	        sql_a.append(" ORDER BY A.ID_TAKER_IO,A.ORG_CODE,A.TAKER_CODE,A.DOC_VER_CODE,A.PRESS_BATCH_NO,TO_NUMBER(A.DOC_NUM),A.PROVIDE_TIME");
	        sql_a.append(") B)C ");
	       	sql_a.append("GROUP BY C.ORG_CODE,C.TAKER_CODE,C.DOC_VER_CODE,C.PRESS_BATCH_NO,");
	       	sql_a.append("TO_DATE(TO_CHAR(C.PROVIDE_TIME,'yyyy-MM-dd'),'yyyy-MM-dd'),C.DOC_STATUS,C.CC,C.DEADLINE");
	       	//MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 END
	       	
	        StringBuilder sql = new StringBuilder(100);
	        sql.append(" FROM(");
	        sql.append(sql_a);
	
	        sql.append(") TA, VC_DOC_VERSION_INFO ver, VC_DOC_TYPE type ");
	        sql.append(" WHERE TA.DOC_VER_CODE=ver.DOC_VER_CODE and ver.ID_VC_DOC_TYPE=type.ID_VC_DOC_TYPE ");
	
	        // 单证种类
	        if (StringUtils.isNotBlank(storageInquiryVo.getDocTypeCode())) {
	            sql.append(" and type.DOC_TYPE_CODE like ? ");
	            values.add(storageInquiryVo.getDocTypeCode() + "%");
	        }
	        // 单证类型代码
	        /*if (StringUtils.isNotEmpty(storageInquiryVo.getDocVerCode())) {
	            sql.append(" AND TA.DOC_VER_CODE like ? ");
	            values.add(storageInquiryVo.getDocVerCode() + "%");
	        }*/
	
	        // 险类// 险种
	        if (StringUtils.isNotBlank(storageInquiryVo.getInsuTypeCode())
	                || StringUtils.isNotBlank(storageInquiryVo.getInsuKindCode())) {
	            sql.append(" and exists(");
	            sql
	                    .append(" select 1 from VC_DOC_INSU_KIND insuKind, VC_DOC_REL_INSU_KIND ref ,VC_DOC_INSU_TYPE insuType ");
	            sql
	                    .append(" where insuKind.INSU_KIND_CODE=ref.INSU_KIND_CODE and ref.ID_VC_DOC_VERSION_INFO=ver.ID_VC_DOC_VERSION_INFO ");
	            sql.append(" and insuKind.ID_VC_DOC_INSU_TYPE=insuType.ID_VC_DOC_INSU_TYPE ");
	            if (StringUtils.isNotBlank(storageInquiryVo.getInsuTypeCode())) {
	                sql.append(" and insuType.INSU_TYPE_CODE=?");
	                values.add(storageInquiryVo.getInsuTypeCode());
	            }
	            if (StringUtils.isNotBlank(storageInquiryVo.getInsuKindCode())) {
	                sql.append(" and insuKind.INSU_KIND_CODE=?");
	                values.add(storageInquiryVo.getInsuKindCode());
	            }
	            sql.append(" ) ");
	        }
	
	       /* // 拼接使用人
	        if (StringUtils.isNotEmpty(storageInquiryVo.getTakerCode())) {
	            // 使用人
	            sql.append(" AND TA.DOC_TAKER_CODE = ? ");
	            values.add(storageInquiryVo.getTakerCode());
	        }
	
	        // 当前用户权限控制(总公司、分公司)
	        if (userInfo.getComCode() != null) {
	            // level是oracle关键字，不能用别名level//
	        	sql.append(" and exists (select 1 from Vc_Level lev where TA.ORG_CODE =lev.unit_code and lev.valid_status='1' and lev.unit_type='0'");
	            sql.append("  Start with lev.unit_code=? ");
	        	values.add(userInfo.getComCode());
	            sql.append(" connect by PRIOR lev.ID_VC_LEVEL=lev.PARENT_ORG_ID) ");
	        }
	
	        // 状态
	        if (StringUtils.isNotEmpty(storageInquiryVo.getDocStatus())) {
	            sql.append(" AND TA.DOC_STATUS = ? ");
	            values.add(storageInquiryVo.getDocStatus());
	        }else{
	        	sql.append(" AND TA.DOC_STATUS = 'A' ");
	        }
	        // 申请时间
	        if (storageInquiryVo.getApplyStartDate() != null) {
	            sql.append(" AND TA.IN_STORE_TIME >= ?  ");
	            values.add(storageInquiryVo.getApplyStartDate());
	        }
	        if (storageInquiryVo.getApplyEndDate() != null) {
	            sql.append(" AND TA.IN_STORE_TIME < ?  ");
	            Date endDate = DateUtils.addDay(storageInquiryVo.getApplyEndDate(), +1);
	            values.add(endDate);
	        }*/
	
	        //分页查询
	        if("1".equals(queryType)){
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
		        List list = this.findBySql(sql_query.toString(), values.toArray());
		
		        if (list != null && list.size() > 0) {
		            StorageInquiryVo tempVo = null;
		            for (int i = 0; i < list.size(); i++) {
		                tempVo = new StorageInquiryVo();
		                Object[] obj = (Object[]) list.get(i);
		                tempVo.setOrgCode((String) obj[0]);
		                tempVo.setDocTakerCode((String) obj[1]);
		                tempVo.setDocVerCode((String) obj[2]);
		                tempVo.setPressBatchNo((String) obj[3]);
		                tempVo.setInStoreTime((Date) obj[4]);
		                tempVo.setDocStatus((String) obj[5]);
		                tempVo.setStartNum((String)obj[6]);
		                tempVo.setEndNum((String)obj[7]);
		                tempVo.setDocNum(Long.parseLong(obj[8].toString()));
		                tempVo.setDocVerName((String) obj[10]);
		                //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 BEGIN
		                tempVo.setDeadLine((Date) obj[9]);
		                //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 END
		                resultList.add(tempVo);
		            }
		        }
	            Page page = new Page(pageNo, pageSize, totalCount, resultList);
	            return page;
	        }
	        //导出excel
	        else if("2".equals(queryType)){
		        StringBuilder sql_query = new StringBuilder(100);
		        sql_query.append("select ta.*,ver.DOC_VER_Name,type.DOC_TYPE_CODE, type.DOC_TYPE_NAME")
		                .append(sql);
		        List list = this.findBySql(sql_query.toString(), values.toArray());
		
		        if (list != null && list.size() > 0) {
		            StorageInquiryVo tempVo = null;
		            for (int i = 0; i < list.size(); i++) {
		            	tempVo = new StorageInquiryVo();
		                Object[] obj = (Object[]) list.get(i);
		                tempVo.setOrgCode((String) obj[0]);
		                tempVo.setDocTakerCode((String) obj[1]);
		                tempVo.setDocVerCode((String) obj[2]);
		                tempVo.setPressBatchNo((String) obj[3]);
		                tempVo.setInStoreTime((Date) obj[4]);
		                tempVo.setDocStatus((String) obj[5]);
		                tempVo.setStartNum((String)obj[6]);
		                tempVo.setEndNum((String)obj[7]);
		                tempVo.setDocNum(Long.parseLong(obj[8].toString()));
		                tempVo.setDocVerName((String) obj[10]);
		                //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 BEGIN
		                tempVo.setDeadLine((Date) obj[9]);
		                //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 END
		                resultList.add(tempVo);
		            }
		        }
	        	return resultList;
	        } else{
	        	return null;
	        }
    	} catch (Exception e) {
            throw new DaoException("查询数据库异常！", e);
        }
    }

}
