package com.tapi.tcs.vc.inquiry.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.security.authority.User;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.inquiry.dao.AvailableDocInquiryDao;
import com.tapi.tcs.vc.inquiry.vo.StorageInquiryVo;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;

public class AvailableDocInquiryDaoImpl extends
		GenericDaoHibernate<VcAvailableDoc> implements AvailableDocInquiryDao {

	public Page queryAvailableInquiryList(StorageInquiryVo storageInquiryVo,
			UserInfo userInfo, int pageNo, int pageSize) throws DaoException {

		List<StorageInquiryVo> resultList = new ArrayList<StorageInquiryVo>();
		try {
			List<Object> values = new ArrayList<Object>();
			StringBuilder sql_a = new StringBuilder(100);
			sql_a.append("SELECT C.ORG_CODE,C.TAKER_CODE AS DOC_TAKER_CODE,C.DOC_VER_CODE,C.PRESS_BATCH_NO,");
			sql_a.append("TO_DATE(TO_CHAR(C.PROVIDE_TIME,'yyyy-MM-dd'),'yyyy-MM-dd') IN_STORE_TIME,C.DOC_STATUS,");
			sql_a.append("MIN(C.DOC_NUM) START_NUM,MAX(C.DOC_NUM) END_NUM,COUNT(C.CC) DOC_NUM,");
			sql_a.append("TO_DATE(TO_CHAR(C.DEADLINE,'yyyy-MM-dd'),'yyyy-MM-dd') DEADLINE");
			sql_a.append(" FROM (");
			sql_a.append("SELECT B.*, (TO_NUMBER(B.DOC_NUM) - ROWNUM) CC FROM(");
			sql_a.append("SELECT * FROM VC_AVAILABLE_DOC A WHERE 1 = 1");
			// 单证类型代码
			if (StringUtils.isNotEmpty(storageInquiryVo.getDocVerCode())) {
				sql_a.append(" AND A.DOC_VER_CODE like ? ");
				values.add(storageInquiryVo.getDocVerCode());
			}
			// 拼接使用人
			sql_a.append(" AND A.TAKER_CODE = ? ");
			values.add(userInfo.getUserCode());

			// 拼接使用人归属机构
			if (userInfo.getCompanyCode() != null) {
				sql_a.append(" AND A.ORG_CODE = ? ");
				values.add(userInfo.getCompanyCode());
			}

			// 申请时间
			if (storageInquiryVo.getApplyStartDate() != null) {
				sql_a.append(" AND A.PROVIDE_TIME >= ?  ");
				values.add(storageInquiryVo.getApplyStartDate());
			}
			if (storageInquiryVo.getApplyEndDate() != null) {
				sql_a.append(" AND A.PROVIDE_TIME < ?  ");
				Date endDate = DateUtils.addDay(
						storageInquiryVo.getApplyEndDate(), +1);
				values.add(endDate);
			}
			sql_a.append(" ORDER BY A.ID_TAKER_IO,A.ORG_CODE,A.TAKER_CODE,A.DOC_VER_CODE,A.PRESS_BATCH_NO,TO_NUMBER(A.DOC_NUM),A.PROVIDE_TIME");
			sql_a.append(") B)C ");
			sql_a.append("GROUP BY C.ORG_CODE,C.TAKER_CODE,C.DOC_VER_CODE,C.PRESS_BATCH_NO,");
			sql_a.append("TO_DATE(TO_CHAR(C.PROVIDE_TIME,'yyyy-MM-dd'),'yyyy-MM-dd'),C.DOC_STATUS,C.CC,C.DEADLINE");

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
			// 险类// 险种
			if (StringUtils.isNotBlank(storageInquiryVo.getInsuTypeCode())
					|| StringUtils.isNotBlank(storageInquiryVo.getInsuKindCode())) {
				sql.append(" and exists(");
				sql.append(" select 1 from VC_DOC_INSU_KIND insuKind, VC_DOC_REL_INSU_KIND ref ,VC_DOC_INSU_TYPE insuType ");
				sql.append(" where insuKind.INSU_KIND_CODE=ref.INSU_KIND_CODE and ref.ID_VC_DOC_VERSION_INFO=ver.ID_VC_DOC_VERSION_INFO ");
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

			StringBuilder sql_count = new StringBuilder(100);
			sql_count.append("select count(*) ").append(sql);

			Long totalCount = 0L;
			List tempCount = this.findBySql(sql_count.toString(),
					values.toArray());
			Object countObj = tempCount.get(0);
			totalCount = ((BigDecimal) countObj).longValue();

			// 查询分页列表
			StringBuilder sql_query = new StringBuilder(100);
			sql_query.append("select * from(");
			sql_query.append("select ta.*,ver.DOC_VER_Name,type.DOC_TYPE_CODE, type.DOC_TYPE_NAME,rowNum numId ").append(sql);
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
					tempVo.setStartNum((String) obj[6]);
					tempVo.setEndNum((String) obj[7]);
					tempVo.setDocNum(Long.parseLong(obj[8].toString()));
					tempVo.setDocVerName((String) obj[10]);
					tempVo.setDeadLine((Date) obj[9]);
					resultList.add(tempVo);
				}
			}
			Page page = new Page(pageNo, pageSize, totalCount, resultList);
			return page;

		} catch (Exception e) {
			throw new DaoException("查询数据库异常！", e);
		}

	}
}
