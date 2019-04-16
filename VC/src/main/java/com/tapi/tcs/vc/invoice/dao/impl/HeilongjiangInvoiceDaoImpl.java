package com.tapi.tcs.vc.invoice.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.invoice.dao.HeilongjiangInvoiceDao;
import com.tapi.tcs.vc.invoice.vo.VcInvoiceReportDetHljVo;
import com.tapi.tcs.vc.schema.model.VcInvoiceBuyHlj;
import com.tapi.tcs.vc.schema.model.VcInvoiceReportDetHlj;
import com.tapi.tcs.vc.schema.model.VcInvoiceReportHlj;
import com.tapi.tcs.vc.schema.model.VcManageCodeHlj;
import com.tapi.tcs.vc.schema.model.VcStorage;

public class HeilongjiangInvoiceDaoImpl extends GenericDaoHibernate<VcInvoiceReportHlj> 
	implements HeilongjiangInvoiceDao {

	@Override
	public VcManageCodeHlj findVcManageCodeHljByOrgCode(String orgCode) throws DaoException {
		VcManageCodeHlj vcManageCodeHlj = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("orgCode", orgCode);
			List<VcManageCodeHlj> list = (List<VcManageCodeHlj>)this.find(VcManageCodeHlj.class, queryRule);
			if(list!=null && list.size()!=0){
				vcManageCodeHlj = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询管理码配置表出错！", e);
		}
		return vcManageCodeHlj;
	}
	
	@Override
	public VcInvoiceBuyHlj findVcInvoiceBuyHlj(VcInvoiceBuyHlj conditions) throws DaoException {
		VcInvoiceBuyHlj vcInvoiceBuyHlj = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			if(StringUtils.isNotEmpty(conditions.getDocVerCode())){
				queryRule.addEqual("docVerCode", conditions.getDocVerCode());
			}
			if(StringUtils.isNotEmpty(conditions.getInvoiceCode())){
				queryRule.addEqual("invoiceCode", conditions.getInvoiceCode());
			}
			if(StringUtils.isNotEmpty(conditions.getStartNum())){
				queryRule.addEqual("startNum", conditions.getStartNum());
			}
			List<VcInvoiceBuyHlj> list = (List<VcInvoiceBuyHlj>)this.find(VcInvoiceBuyHlj.class, queryRule);
			if(list!=null && list.size()!=0){
				vcInvoiceBuyHlj = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询领购信息出错！", e);
		}
		return vcInvoiceBuyHlj;
	}
	
	@Override
	public VcInvoiceReportHlj queryLastReportByOrgCode(String orgCode) throws DaoException {
		VcInvoiceReportHlj vcInvoiceReportHlj = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("orgCode", orgCode);
			queryRule.addDescOrder("reportDate");
			List<VcInvoiceReportHlj> list = (List<VcInvoiceReportHlj>)this.find(VcInvoiceReportHlj.class, queryRule);
			if(list!=null && list.size()!=0){
				vcInvoiceReportHlj = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询报表失败！", e);
		}
		return vcInvoiceReportHlj;
	}
	
	@Override
	public List<VcInvoiceReportDetHlj> queryVcInvoiceReportDetHlj(Long idVcInvoiceReportHlj, String detailType) throws DaoException {
		List<VcInvoiceReportDetHlj> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("idVcInvoiceReportHlj", idVcInvoiceReportHlj);
			queryRule.addEqual("detailType", detailType);
			queryRule.addAscOrder("detailOrder");
			list = (List<VcInvoiceReportDetHlj>)this.find(VcInvoiceReportDetHlj.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询报表明细失败！", e);
		}
		return list;
	}
	
	@Override
	public List<VcInvoiceBuyHlj> queryVcInvoiceBuyHljList(String orgCode, Date startDate, Date endDate) throws DaoException {
		List<VcInvoiceBuyHlj> list = null;
		try{
			StringBuffer hql = new StringBuffer();
			List values = new ArrayList();
			hql.append(" from VcInvoiceBuyHlj a where 1=1");
			//机构代码
			hql.append(" and orgCode=?");
			values.add(orgCode);
			//日期
			hql.append(" and buyDate between ? and ?");
			values.add(startDate);
			values.add(endDate);
			hql.append(" order by buyDate asc");
			list = (List<VcInvoiceBuyHlj>)this.findByHql(hql.toString(), values.toArray());
		}catch(Exception e){
			throw new DaoException("查询领购记录失败！", e);
		}
		return list;
	}
	
	@Override
	public List<VcInvoiceReportDetHlj> queryStorage(String orgCode, String docVerCode, String pressBatchNo, String startNum, String endNum) throws DaoException {
		List<VcInvoiceReportDetHlj> list = null;
		try{
			StringBuffer sql = new StringBuffer();
			List values = new ArrayList();
			//1、查询库存表
			sql.append("SELECT DOC_VER_CODE,PRESS_BATCH_NO,START_NUM,END_NUM,DOC_NUM FROM VC_STORAGE s WHERE 1=1");
			//机构查询条件
			//分公司应统计：分公司+分公司直属部门+营业部及其所有下级机构
			if(SysConst.COMCODE_HLJ.equals(orgCode)){
				sql.append(" AND (s.ORG_CODE=? OR s.ORG_CODE IN (");
				sql.append(" SELECT UNIT_CODE FROM VC_LEVEL WHERE COM_TYPE='1' AND VALID_STATUS='1' AND PARENT_ORG_ID=(");
				sql.append(" SELECT ID_VC_LEVEL FROM VC_LEVEL WHERE UNIT_CODE=? AND VALID_STATUS='1'))");
				sql.append(" OR EXISTS(SELECT 1 FROM VC_LEVEL VL WHERE s.ORG_CODE=VL.UNIT_CODE");
				sql.append(" AND VL.UNIT_TYPE='0' AND VL.VALID_STATUS='1' ");
				sql.append(" START WITH VL.UNIT_CODE=? CONNECT BY VL.PARENT_ORG_ID=PRIOR VL.ID_VC_LEVEL))");
				values.add(SysConst.COMCODE_HLJ);
				values.add(SysConst.COMCODE_HLJ);
				values.add(SysConst.COMCODE_HLJ_YYB);
			}else{
				sql.append(" AND EXISTS(SELECT 1 FROM VC_LEVEL L WHERE s.ORG_CODE=L.UNIT_CODE");
				sql.append(" AND L.UNIT_TYPE='0' AND L.VALID_STATUS='1'");
				sql.append(" START WITH L.UNIT_CODE=? CONNECT BY L.PARENT_ORG_ID=PRIOR ID_VC_LEVEL)");
				values.add(orgCode);
			}
			sql.append(" AND S.DOC_STATUS IN (?,?,?)");
			sql.append(" AND S.DOC_VER_CODE=? AND S.PRESS_BATCH_NO=?");
			sql.append(" AND to_number(S.START_NUM) BETWEEN to_number(?) AND to_number(?)");
			sql.append(" AND to_number(S.END_NUM) BETWEEN to_number(?) AND to_number(?)");
			//sql.append(" ORDER BY S.START_NUM ASC");
			values.add("S1");
			values.add("S2");
			values.add("S3");
			values.add(docVerCode);
			values.add(pressBatchNo);
			values.add(startNum);
			values.add(endNum);
			values.add(startNum);
			values.add(endNum);
			//2、查询可使用表
			sql.append(" union ");
			sql.append("(SELECT B.DOC_VER_CODE,B.PRESS_BATCH_NO,MIN(B.DOC_NUM) START_NUM,");
			sql.append("MAX(B.DOC_NUM) END_NUM,COUNT(CC) DOC_NUM");
			sql.append(" FROM (SELECT A.*, (TO_NUMBER(A.DOC_NUM) - ROWNUM) CC");
			sql.append(" FROM (SELECT * FROM VC_AVAILABLE_DOC T WHERE 1 = 1");
			//机构查询条件
			//分公司应统计：分公司+分公司直属部门+营业部及其所有下级机构
			if(SysConst.COMCODE_HLJ.equals(orgCode)){
				sql.append(" AND (T.ORG_CODE=? OR T.ORG_CODE IN (");
				sql.append(" SELECT UNIT_CODE FROM VC_LEVEL WHERE COM_TYPE='1' AND VALID_STATUS='1' AND PARENT_ORG_ID=(");
				sql.append(" SELECT ID_VC_LEVEL FROM VC_LEVEL WHERE UNIT_CODE=? AND VALID_STATUS='1'))");
				sql.append(" OR EXISTS(SELECT 1 FROM VC_LEVEL VL WHERE T.ORG_CODE=VL.UNIT_CODE");
				sql.append(" AND VL.UNIT_TYPE='0' AND VL.VALID_STATUS='1' ");
				sql.append(" START WITH VL.UNIT_CODE=? CONNECT BY VL.PARENT_ORG_ID=PRIOR VL.ID_VC_LEVEL))");
				values.add(SysConst.COMCODE_HLJ);
				values.add(SysConst.COMCODE_HLJ);
				values.add(SysConst.COMCODE_HLJ_YYB);
			}else{
				sql.append(" AND EXISTS(SELECT 1 FROM VC_LEVEL L WHERE T.ORG_CODE=L.UNIT_CODE");
				sql.append(" AND L.UNIT_TYPE='0' AND L.VALID_STATUS='1'");
				sql.append(" START WITH L.UNIT_CODE=? CONNECT BY L.PARENT_ORG_ID=PRIOR ID_VC_LEVEL)");
				values.add(orgCode);
			}
			sql.append(" AND T.DOC_VER_CODE=? AND T.PRESS_BATCH_NO=?");
			sql.append(" AND TO_NUMBER(T.DOC_NUM) BETWEEN TO_NUMBER(?) AND TO_NUMBER(?)");
			sql.append(" AND T.DOC_STATUS='A' ");
			sql.append(" ORDER BY T.DOC_VER_CODE,T.PRESS_BATCH_NO,TO_NUMBER(T.DOC_NUM)) A) B");
			sql.append(" GROUP BY B.DOC_VER_CODE, B.PRESS_BATCH_NO, B.CC)");
			values.add(docVerCode);
			values.add(pressBatchNo);
			values.add(startNum);
			values.add(endNum);
			sql.append(" ORDER BY START_NUM ASC");
			List<Object[]> rs = this.findBySql(sql.toString(), values.toArray());
			if(rs!=null && rs.size()!=0){
				list = new ArrayList<VcInvoiceReportDetHlj>();
				for(Object[] obj : rs){
					VcInvoiceReportDetHlj detail = new VcInvoiceReportDetHlj();
					detail.setDocVerCode((String)obj[0]);
					detail.setInvoiceCode((String)obj[1]);
					detail.setStartNum((String)obj[2]);
					detail.setEndNum((String)obj[3]);
					detail.setTotalNum(((BigDecimal)obj[4]).intValue());
					list.add(detail);
				}
			}
		}catch(Exception e){
			throw new DaoException("查询本期结存失败！", e);
		}
		return list;
	}
	
	@Override
	public List<VcInvoiceReportDetHljVo> queryNextUseDetail(VcInvoiceReportDetHljVo conditions) throws DaoException {
		List<VcInvoiceReportDetHljVo> list = null;
		String orgCode = conditions.getOrgCode();
		try{
			StringBuffer sql = new StringBuffer();
			List values = new ArrayList();
			sql.append("SELECT tmp.*,t2.companycname FROM (");
			sql.append(" SELECT NV.DOC_VER_CODE,NV.PRESS_BATCH_NO AS INVOICE_CODE,1 AS TOTAL_NUM,");
			sql.append(" NV.DOC_NUM AS INVOICE_NO,'U' AS VERIFY_REASON,");
			sql.append(" NV.VERIFIED_TIME AS VERIFY_DATE,decode(IP.AMOUNT,null,0,IP.AMOUNT) AMOUNT,");
			sql.append(" NV.VERIFIED_ORG_CODE");
			sql.append(" FROM VC_NORMAL_VERIFICATION NV,VC_INVOICE_PRINT IP");
			sql.append(" WHERE NV.DOC_VER_CODE=IP.DOC_VER_CODE(+)");
			sql.append(" AND NV.PRESS_BATCH_NO=IP.INVOICE_CODE(+) AND NV.DOC_NUM=IP.INVOICE_NO(+)");
			//机构查询条件
			//分公司应统计：分公司+分公司直属部门+营业部及其所有下级机构
			if(SysConst.COMCODE_HLJ.equals(orgCode)){
				sql.append(" AND (NV.VERIFIED_ORG_CODE=? OR NV.VERIFIED_ORG_CODE IN (");
				sql.append(" SELECT UNIT_CODE FROM VC_LEVEL WHERE COM_TYPE='1' AND VALID_STATUS='1' AND PARENT_ORG_ID=(");
				sql.append(" SELECT ID_VC_LEVEL FROM VC_LEVEL WHERE UNIT_CODE=? AND VALID_STATUS='1'))");
				sql.append(" OR EXISTS(SELECT 1 FROM VC_LEVEL VL WHERE NV.VERIFIED_ORG_CODE=VL.UNIT_CODE");
				sql.append(" AND VL.UNIT_TYPE='0' AND VL.VALID_STATUS='1' ");
				sql.append(" START WITH VL.UNIT_CODE=? CONNECT BY VL.PARENT_ORG_ID=PRIOR VL.ID_VC_LEVEL))");
				values.add(SysConst.COMCODE_HLJ);
				values.add(SysConst.COMCODE_HLJ);
				values.add(SysConst.COMCODE_HLJ_YYB);
			}else{
				sql.append(" AND EXISTS(SELECT 1 FROM VC_LEVEL VL WHERE NV.VERIFIED_ORG_CODE=VL.UNIT_CODE");
				sql.append(" AND VL.UNIT_TYPE='0' AND VL.VALID_STATUS='1' ");
				sql.append(" START WITH VL.UNIT_CODE=? CONNECT BY VL.PARENT_ORG_ID=PRIOR VL.ID_VC_LEVEL)");
				values.add(orgCode);
			}
			sql.append(" AND NV.DOC_VER_CODE=? AND NV.PRESS_BATCH_NO=?");
			values.add(conditions.getDocVerCode());
			values.add(conditions.getInvoiceCode());
			
			//add by zhxiao3 2014-4-11 begin '本期使用'起始时间从上期报表生成时间开始统计，精确到时分秒
			//sql.append(" AND TRUNC(NV.VERIFIED_TIME) BETWEEN ? AND ?");
			//values.add(conditions.getStartDate());
			//values.add(conditions.getEndDate());
			sql.append(" and NV.VERIFIED_TIME > ?");
			values.add(conditions.getStartDate());		
			//add by zhxiao3 2014-4-11 begin '本期使用'起始时间从上期报表生成时间开始统计，精确到时分秒
			
			sql.append(" AND TO_NUMBER(NV.DOC_NUM) BETWEEN TO_NUMBER(?) AND TO_NUMBER(?)");
			values.add(conditions.getStartNum());
			values.add(conditions.getEndNum());
			sql.append(" union");
			sql.append(" SELECT AV.DOC_VER_CODE,AV.PRESS_BATCH_NO AS INVOICE_CODE,1 AS TOTAL_NUM,");
			sql.append(" AV.DOC_NUM AS INVOICE_NO,'C' AS VERIFY_REASON,");
			sql.append(" AV.VERIFIED_TIME VERIFY_DATE,0 AS AMOUNT, AV.VERIFIED_ORG_CODE");
			sql.append(" FROM VC_ABNORMAL_VERIFICATION AV WHERE 1=1");
			//机构查询条件
			//分公司应统计分公司直属部门+营业部及其所有下级机构
			if(SysConst.COMCODE_HLJ.equals(orgCode)){
				sql.append(" AND (AV.VERIFIED_ORG_CODE=? OR AV.VERIFIED_ORG_CODE IN (");
				sql.append(" SELECT UNIT_CODE FROM VC_LEVEL WHERE COM_TYPE='1' AND VALID_STATUS='1' AND PARENT_ORG_ID=(");
				sql.append(" SELECT ID_VC_LEVEL FROM VC_LEVEL WHERE UNIT_CODE=? AND VALID_STATUS='1'))");
				sql.append(" OR EXISTS(SELECT 1 FROM VC_LEVEL VL WHERE AV.VERIFIED_ORG_CODE=VL.UNIT_CODE");
				sql.append(" AND VL.UNIT_TYPE='0' AND VL.VALID_STATUS='1' ");
				sql.append(" START WITH VL.UNIT_CODE=? CONNECT BY VL.PARENT_ORG_ID=PRIOR VL.ID_VC_LEVEL))");
				values.add(SysConst.COMCODE_HLJ);
				values.add(SysConst.COMCODE_HLJ);
				values.add(SysConst.COMCODE_HLJ_YYB);
			}else{
				sql.append(" AND EXISTS(SELECT 1 FROM VC_LEVEL V WHERE AV.VERIFIED_ORG_CODE=V.UNIT_CODE");
				sql.append(" AND V.UNIT_TYPE='0' AND V.VALID_STATUS='1'");
				sql.append(" START WITH V.UNIT_CODE=? CONNECT BY V.PARENT_ORG_ID=PRIOR V.ID_VC_LEVEL)");
				values.add(conditions.getOrgCode());
			}
			sql.append(" AND AV.DOC_VER_CODE=? AND AV.PRESS_BATCH_NO=?");
			values.add(conditions.getDocVerCode());
			values.add(conditions.getInvoiceCode());

			//add by zhxiao3 2014-4-11 begin '本期使用'起始时间从上期报表生成时间开始统计，精确到时分秒
			//sql.append(" AND TRUNC(AV.VERIFIED_TIME) BETWEEN ? AND ?");
			//values.add(conditions.getStartDate());
			//values.add(conditions.getEndDate());
			sql.append(" and AV.VERIFIED_TIME > ?");
			values.add(conditions.getStartDate());
			//add by zhxiao3 2014-4-11 begin '本期使用'起始时间从上期报表生成时间开始统计，精确到时分秒
			
			sql.append(" AND TO_NUMBER(AV.DOC_NUM) BETWEEN TO_NUMBER(?) AND TO_NUMBER(?)");
			values.add(conditions.getStartNum());
			values.add(conditions.getEndNum());
			sql.append(") tmp  left join pub_company t2 on tmp.VERIFIED_ORG_CODE = t2.companycode ");
			sql.append("ORDER BY tmp.VERIFIED_ORG_CODE, tmp.DOC_VER_CODE, tmp.INVOICE_CODE, TO_NUMBER(tmp.INVOICE_NO)");
			List<Object[]> rs = this.findBySql(sql.toString(), values.toArray());
			if(rs!=null && rs.size()!=0){
				list = new ArrayList<VcInvoiceReportDetHljVo>();
				for(Object[] obj : rs){
					VcInvoiceReportDetHljVo detailVo = new VcInvoiceReportDetHljVo();
					detailVo.setDocVerCode((String)obj[0]);
					detailVo.setInvoiceCode((String)obj[1]);
					detailVo.setTotalNum(((BigDecimal)obj[2]).intValue());
					//detailVo.setStartNum((String)obj[3]);
					//detailVo.setEndNum((String)obj[3]);
					detailVo.setInvoiceNo((String)obj[3]);
					detailVo.setVerifyReason(((Character)obj[4]).toString());
					//detailVo.setStartDate((Date)obj[5]);
					//detailVo.setEndDate((Date)obj[6]);
					detailVo.setVerifyDate((Date)obj[5]);
					detailVo.setAmount((BigDecimal)obj[6]);
					detailVo.setOrgCode(obj[7].toString());
					detailVo.setOrgName(obj[8].toString());
					list.add(detailVo);
				}
			}
		}catch(Exception e){
			throw new DaoException("查询本期使用失败！", e);
		}
		return list;
	}
	
	@Override
	public Page queryVcInvoiceReportHljPage(String orgCode, Date startDate, Date endDate, int pageNo, int pageSize) throws DaoException {
		Page page = null;
		try{
			StringBuffer hql = new StringBuffer();
			List values = new ArrayList();
			hql.append(" from VcInvoiceReportHlj a where 1=1");
			//机构
			hql.append(" and orgCode=?");
			values.add(orgCode);
			//日期
			hql.append(" and trunc(reportDate) between ? and ?");
			values.add(startDate);
			values.add(endDate);
			hql.append(" order by reportDate asc");
			page = this.findByHql(hql.toString(), pageNo, pageSize, values.toArray());
		}catch(Exception e){
			throw new DaoException("查询领用存报表失败！", e);
		}
		return page;
	}
	
	@Override
	public Page queryVcInvoiceBuyPage(String orgCode, Date startDate, Date endDate, int pageNo, int pageSize) throws DaoException {
		Page page = null;
		try{
			/*QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("orgCode", orgCode);
			queryRule.addGreaterEqual("buyDate", startDate);
			queryRule.addLessThan("buyDate", DateUtils.addDay(endDate, +1));*/
			StringBuffer hql = new StringBuffer();
			List<Object> values = new ArrayList<Object>();
			hql.append(" from VcInvoiceBuyHlj a where orgCode=?");
			values.add(orgCode);
			hql.append(" and buyDate between ? and ?");
			values.add(startDate);
			values.add(endDate);
			hql.append(" order by buyDate asc");
			page = this.findByHql(hql.toString(), pageNo, pageSize, values.toArray());
		}catch(Exception e){
			throw new DaoException("查询领购信息报错！", e);
		}
		return page;
	}
	
	@Override
	public List<VcStorage> queryVcStorageByInvoiceBuy(final VcInvoiceBuyHlj vcInvoiceBuyHlj) throws DaoException {
		List<VcStorage> list = null;
		try{
			StringBuffer sql = new StringBuffer();
			List<Object> values = new ArrayList<Object>();
			sql.append("SELECT DOC_VER_CODE,PRESS_BATCH_NO,START_NUM,END_NUM,DOC_NUM,DOC_STATUS,ORG_CODE");
			sql.append(" from VC_STORAGE s where 1=1 ");
			String orgCode = vcInvoiceBuyHlj.getOrgCode();
			//分公司应统计分公司直属部门+营业部及其所有下级机构
			if(SysConst.COMCODE_HLJ.equals(orgCode)){
				sql.append(" AND (s.ORG_CODE=? OR s.ORG_CODE IN (");
				sql.append(" SELECT UNIT_CODE FROM VC_LEVEL WHERE COM_TYPE='1' AND VALID_STATUS='1' AND PARENT_ORG_ID=(");
				sql.append(" SELECT ID_VC_LEVEL FROM VC_LEVEL WHERE UNIT_CODE=? AND VALID_STATUS='1'))");
				sql.append(" OR EXISTS(SELECT 1 FROM VC_LEVEL VL WHERE s.ORG_CODE=VL.UNIT_CODE");
				sql.append(" AND VL.UNIT_TYPE='0' AND VL.VALID_STATUS='1' ");
				sql.append(" START WITH VL.UNIT_CODE=? CONNECT BY VL.PARENT_ORG_ID=PRIOR VL.ID_VC_LEVEL))");
				values.add(SysConst.COMCODE_HLJ);
				values.add(SysConst.COMCODE_HLJ);
				values.add(SysConst.COMCODE_HLJ_YYB);
			}else{
				sql.append(" and exists(SELECT 1 FROM VC_LEVEL V WHERE s.ORG_CODE=V.UNIT_CODE");
				sql.append(" AND V.UNIT_TYPE='0' AND V.VALID_STATUS='1'");
				sql.append(" START WITH V.UNIT_CODE=? CONNECT BY V.PARENT_ORG_ID=PRIOR V.ID_VC_LEVEL)");
				values.add(orgCode);
			}
			
			sql.append(" and s.DOC_VER_CODE=?");
			values.add(vcInvoiceBuyHlj.getDocVerCode());
			
			sql.append(" and s.PRESS_BATCH_NO=?");
			values.add(vcInvoiceBuyHlj.getInvoiceCode());
			
			sql.append(" and to_number(s.START_NUM) >= to_number(?)");
			values.add(vcInvoiceBuyHlj.getStartNum());
			
			sql.append(" and to_number(s.END_NUM) <= to_number(?)");
			values.add(vcInvoiceBuyHlj.getEndNum());
			
			sql.append(" and s.DOC_STATUS in ('S1','S2','S3')");
			sql.append(" order by TO_NUMBER(S.START_NUM)");
			
			List<Object[]> rs = this.findBySql(sql.toString(), values.toArray());
			if(rs!=null && rs.size()!=0){
				list = new ArrayList<VcStorage>();
				VcStorage vcStorage = null;
				for(Object[] obj : rs){
					vcStorage = new VcStorage();
					vcStorage.setDocVerCode((String)obj[0]);
					vcStorage.setPressBatchNo((String)obj[1]);
					vcStorage.setStartNum((String)obj[2]);
					vcStorage.setEndNum((String)obj[3]);
					vcStorage.setDocNum(((BigDecimal)obj[4]).longValue());
					vcStorage.setDocStatus((String)obj[5]);
					vcStorage.setOrgCode((String)obj[6]);
					list.add(vcStorage);
				}
			}
		}catch(Exception e){
			throw new DaoException("查询领购信息报错！", e);
		}
		return list;
	}
}
