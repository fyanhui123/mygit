package com.tapi.tcs.vc.newInvoice.dao.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.newInvoice.dao.QueryInvoiceDao;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcLost;
public class QueryInvoiceDaoImpl extends GenericDaoHibernate<VcAbnormalVerification> implements QueryInvoiceDao  {
	@Override
	public boolean queryInvoice(String employid) throws DaoException {
		List<Object> values =new ArrayList<Object>();
		VcLevel vc=null;
		try {
			StringBuffer sb=new StringBuffer("select t1.employee_id,t.parent_org_code ,t1.company_code,t1.user_code,t.invoice_flag from vc_level t, pub_user_def t1 where t.unit_code = t1.employee_id and t.unit_code = ?  and  t.valid_status= ?  and t.unit_type= ? and t.invoice_flag= ? ");
			values.add(employid);
			values.add("1");
			values.add("1");
			values.add("1");
			List<Object> list=this.findBySql(sb.toString(), values.toArray());
			if(list.size()==0){
				throw new DaoException("未查询到对应的发票管理员");
			}else{
				for(Iterator iterator = list.iterator(); iterator.hasNext();){
					Object[] object = (Object[]) iterator.next();
					vc=new VcLevel();
					vc.setInvoiceFlag((String) object[4]);
				}
			}
		} catch (Exception e) {
			throw new DaoException("查询发票管理员异常！",e);
		}
		if(vc.getInvoiceFlag()!=null||vc.getInvoiceFlag()!=""){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<VcAbnormalVerification> queryVcAbnormalVerification(
			String docVerCode, String start, String pressBatchNo) {
		List<VcAbnormalVerification> vcAbnormalVerifications =null;
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("docVerCode", docVerCode);
			queryRule.addEqual("docNum", start);
			queryRule.addEqual("pressBatchNo", pressBatchNo);
			vcAbnormalVerifications=this.find(VcAbnormalVerification.class,queryRule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vcAbnormalVerifications;
	}
	
}
