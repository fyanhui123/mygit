package com.tapi.tcs.vc.visausage.dao.impl;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.visausage.dao.AbnormalVerificationDao;

public class AbnormalVerificationDaoImpl extends
		GenericDaoHibernate<VcAbnormalVerification> implements
		AbnormalVerificationDao {

	@Override
	public VcInvoicePrint findVcInvoicePrint(String docVerCode, String invoiceCode, String invoiceNo) throws DaoException {
		VcInvoicePrint vcInvoicePrint = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("docVerCode", docVerCode);//单证类型代码
			queryRule.addEqual("invoiceCode", invoiceCode);//发票代码
			queryRule.addEqual("invoiceNo", invoiceNo);//发票号码
			queryRule.addIn("printKind", new Object[]{"1","2"});//开票类型：1-正常；2-负数；3-作废
			queryRule.addEqual("status", "1");//有效
			List<VcInvoicePrint> list = this.find(VcInvoicePrint.class, queryRule);
			if(list!=null && list.size()>0){
				vcInvoicePrint = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		return vcInvoicePrint;
	}
	
	@Override
	public void saveNewInvoice(VcInvoicePrint vcInvoicePrint) throws DaoException {
		try{
			this.getHibernateTemplate().clear();
			this.save(vcInvoicePrint);
		}catch(Exception e){
			throw new DaoException("保存数据出错！" ,e);
		}
	}
	
	@Override
	public boolean checkDocType(String docVerCode) throws DaoException {
		boolean result = false;
		try{
			String sql = "select 1 from vc_doc_version_info ver where ver.doc_ver_code=?" +
					" and exists(select 1 from vc_doc_type type where type.id_vc_doc_type=ver.id_vc_doc_type" +
					" and type.doc_type='1')";
			List list = this.findBySql(sql, new Object[]{docVerCode});
			if(list!=null && list.size()>0){
				result = true;
			}
		}catch(Exception e){
			throw new DaoException("查询数据库失败！" ,e);
		}
		return result;
	}
	
	@Override
	public PubCompany queryPubCompany(String companyCode) throws DaoException{
		PubCompany pubCompany = new PubCompany();
		try{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("companyCode", companyCode);
		List<PubCompany> list = this.find(PubCompany.class, queryRule);
		if(list!=null && list.size()>0){
			pubCompany = list.get(0);
		}
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		return pubCompany;
	}
	
}
