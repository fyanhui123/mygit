package com.tapi.tcs.vc.newInvoice.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcDocVersionInfoDao;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.VcPubCodeManagerDao;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.newInvoice.dao.InvoiceDestroyDao;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcDestroy;

public class InvoiceDestroyDaoImpl extends GenericDaoHibernate<VcDestroy> implements InvoiceDestroyDao{
	
	@Override
	public Page queryAbNormalList(Map<String, Object> params, int pageNo,
			int pageSize) throws DaoException {
		Page page = null;
    	try{
    		String docVerCode = (String) params.get("docVerCode");
    		String orgCode = (String) params.get("orgCode");
    		StringBuffer sql = new StringBuffer();
    		List<Object> values =new ArrayList<Object>();
    		//查询非正常核销表
    		List<Object> value =new ArrayList<Object>();
    		sql.append(" select rowNum as numId,t.doc_ver_code,t.press_batch_no,t.doc_num,t.verified_time,t.doc_status,t.verified_org_code from vc_abnormal_verification t");
    		sql.append(" where 1=1 ");
    		sql.append(" and t.doc_ver_code = ? ");
    		values.add(docVerCode);
    		sql.append(" and t.doc_status= ? ");
    		values.add("JX");
    		sql.append(" and  t.verified_org_code like ?");
    		values.add(orgCode+"%");
			// 查询分页列表
    		 StringBuffer sql_select = new StringBuffer();
    		 sql_select.append("select count(*) from(").append(sql);
			 sql_select.append(")");
			 Long totalCount = 0L;
			 List tempCount = this.findBySql(sql_select.toString(), values.toArray());
			 Object countObj = tempCount.get(0);
	         totalCount = ((BigDecimal) countObj).longValue();
	         
			 StringBuilder sql_count = new StringBuilder(100);
			 sql_count.append("select * from(").append(sql);
			 sql_count.append(") tableView where tableView.numId between ? and ? ");
			 values.add((pageNo - 1) * pageSize + 1);
			 values.add(pageNo * pageSize);
    		 List list = this.findBySql(sql_count.toString(), values.toArray());
    	     page = new Page(pageNo, pageSize, totalCount, list);
    	}catch(Exception e){
    		throw new DaoException("查询数据库异常！ ", e);
    	}
    	return page;
	}
}
