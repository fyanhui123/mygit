package com.tapi.tcs.vc.store.dao.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jgeppert.struts2.jquery.components.Select;
import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcDocVersionInfoDao;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.baseinfo.dao.VcTakerDao;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcDocTakerIo;
import com.tapi.tcs.vc.schema.model.VcInvoiceReportDetHlj;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.dao.VcDocTakerIoDao;

public class VcDocTakerIoDaoImpl extends GenericDaoHibernate<VcDocTakerIo>
		implements VcDocTakerIoDao {
	private VcLevelDao vcLevelDao;
	private VcTakerDao vcTakerDao;
	private VcDocVersionInfoDao vcDocVersionInfoDao;
	@Override
	public Page findVcDocTakerIos(VcDocTakerIo vcDocTakerIo, int pageNo,
			int pageSize) throws DaoException {
	try{
		//********************lfengxia 20151106 begin 使用人单证发放与回收查询硬解析问题
		 StringBuffer sb = new StringBuffer(" SELECT t.taker_code,t.accept_org_code,t.doc_ver_code,t.start_num,t.end_num,t.doc_num  from vc_doc_taker_io t ");
		 List<Object> values=new ArrayList<Object>();
		 sb.append("where 1=1 and ");
		 sb.append(" t.opr_type=? ");
		 values.add(vcDocTakerIo.getOprType());
		// 发放时间
		 sb.append("and t.OPR_TIME between to_date(?, 'yyyy-mm-dd hh24:mi:ss') and to_date(?, 'yyyy-mm-dd hh24:mi:ss')");
		 values.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vcDocTakerIo.getProvideStartDate()));
		 values.add(new SimpleDateFormat("yyyy-MM-dd").format(vcDocTakerIo.getProvideEndDate())+" 23:59:59");
		 if (StringUtils.isNotEmpty(vcDocTakerIo.getDocVerCode())) {
			 sb.append(" and t.doc_ver_code=? ");
			 values.add(vcDocTakerIo.getDocVerCode());
		}
		 if (StringUtils.isNotEmpty(vcDocTakerIo.getTakerCode())) {
			 sb.append(" and t.taker_code=? ");
			 values.add(vcDocTakerIo.getTakerCode());
		 }			 
		if (StringUtils.isNotEmpty(vcDocTakerIo.getAcceptOrgCode())) {
			sb.append(" and t.accept_org_code=? ");
			values.add(vcDocTakerIo.getAcceptOrgCode());
		 }else{
			 sb.append(" and ACCEPT_ORG_CODE in (" +
						" SELECT Z.UNIT_CODE " +
						" FROM VC_LEVEL Z " +
						" WHERE Z.UNIT_TYPE = ? " +
						" START WITH Z.UNIT_CODE = ? " +
						" CONNECT BY  Z.PARENT_ORG_ID = PRIOR Z.ID_VC_LEVEL " +
						")"); 		
			 values.add("0");
			 values.add(vcDocTakerIo.getUserComCode());			 
		 }	
		//Page page = this.findBySql(sb.toString(), pageNo, pageSize, values.toArray());
		
		 List<Object[]> list = this.findBySql(sb.toString(), values.toArray());	 
			
		/* list<Object> -- List<VcDocTakerIo>*/
					 
		 List<VcDocTakerIo> listdoc = new ArrayList<VcDocTakerIo>();
		 String errDate = "2999-01-01";
		 for (Object[] obj : list) {
			 VcDocTakerIo docTaker=new VcDocTakerIo();
			 docTaker.setTakerCode((String)obj[0]);
			 docTaker.setAcceptOrgCode((String)obj[1]);
			 docTaker.setDocVerCode((String)obj[2]);
			 docTaker.setStartNum((String)obj[3]);
			 docTaker.setEndNum((String)obj[4]);			 
			 docTaker.setDocNum(((BigDecimal)obj[5]).longValue());				 
			 listdoc.add(docTaker);
		}
	 // getTotoalsize		 
		 int size=0;
		 if(listdoc.size()%pageSize==0){
		 size=listdoc.size()/pageSize ;
		 }else{
		 size=listdoc.size()/pageSize +1;
		 }		
		 Page page = new Page(pageNo, pageSize, size, listdoc);
		 		 		
		//********************lfengxia 20151106 end 使用人单证发放与回收查询硬解析问题

						
		/*QueryRule queryRule = QueryRule.getInstance();
		// 只可查询自己发放的记录。
		queryRule.addEqual("oprCode", vcDocTakerIo.getOprCode());
		// 发放回收标志(P发放/R回收)
		queryRule.addEqual("oprType", vcDocTakerIo.getOprType());
		
		if (StringUtils.isNotEmpty(vcDocTakerIo.getDocVerCode())) {
			queryRule.addEqual("docVerCode", vcDocTakerIo.getDocVerCode());
		}
		
		if (StringUtils.isNotEmpty(vcDocTakerIo.getDocVerName())) {
			queryRule.addEqual("docVerName", vcDocTakerIo.getDocVerName());
		}
		
		// 使用人代码
		if (StringUtils.isNotEmpty(vcDocTakerIo.getTakerCode())) {
			queryRule.addEqual("takerCode", vcDocTakerIo.getTakerCode());
		}
		
		// 使用人名称
		if (StringUtils.isNotEmpty(vcDocTakerIo.getTakerName())) {
			queryRule.addEqual("takerName", vcDocTakerIo.getTakerName());
		}
		
		// 使用人所属机构代码
		if (StringUtils.isNotEmpty(vcDocTakerIo.getAcceptOrgCode())) {
			queryRule.addEqual("acceptOrgCode", vcDocTakerIo.getAcceptOrgCode());
		}else{
			queryRule.addSql(" ACCEPT_ORG_CODE in (" +
					" SELECT Z.UNIT_CODE " +
					" FROM VC_LEVEL Z " +
					" WHERE Z.UNIT_TYPE = '0' " +
					" START WITH Z.UNIT_CODE = '"+ vcDocTakerIo.getUserComCode()+ "' " +
					" CONNECT BY Z.PARENT_ORG_ID = PRIOR Z.ID_VC_LEVEL " +
					")");
		}
				
		// 使用人所属机构代码
		if (StringUtils.isNotEmpty(vcDocTakerIo.getAcceptOrgName())) {
			queryRule.addEqual("acceptOrgName", vcDocTakerIo.getAcceptOrgName());
		}
		
		// 发放时间
		if (vcDocTakerIo.getProvideStartDate() != null) {
			queryRule.addGreaterEqual("oprTime", vcDocTakerIo.getProvideStartDate());
		}
		if (vcDocTakerIo.getProvideEndDate() != null) {
			queryRule.addLessThan("oprTime", DateUtils.addDay(vcDocTakerIo.getProvideEndDate(), +1));
		}
		
		Page page = this.find(queryRule, pageNo, pageSize);*/
	
		 		 	
		List<VcDocTakerIo> vcDocTakerIos = (List<VcDocTakerIo>) page.getResult();
		for (VcDocTakerIo vcDocTakerIo2 : vcDocTakerIos) {
			vcDocTakerIo2.setTakerName(vcTakerDao.getUnitNameByUnitCode(vcDocTakerIo2.getTakerCode()));
			vcDocTakerIo2.setAcceptOrgName(vcLevelDao.getUnitNameByUnitCode(vcDocTakerIo2.getAcceptOrgCode()));
			vcDocTakerIo2.setDocVerName(vcDocVersionInfoDao.getVersionName(vcDocTakerIo2.getDocVerCode()));
		}
		page.setTotalCount(listdoc.size());
		return page;
	}catch(Exception e){
		throw new DaoException("查询数据库时发生异常！", e);
	}
	}

	public void setVcLevelDao(VcLevelDao vcLevelDao) {
		this.vcLevelDao = vcLevelDao;
	}

	public void setVcDocVersionInfoDao(VcDocVersionInfoDao vcDocVersionInfoDao) {
		this.vcDocVersionInfoDao = vcDocVersionInfoDao;
	}

	public void setVcTakerDao(VcTakerDao vcTakerDao) {
		this.vcTakerDao = vcTakerDao;
	}

}
