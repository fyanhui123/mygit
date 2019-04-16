package com.tapi.tcs.vc.webservice.provider.docDoUsedOld.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoicePurchase;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcPurchase;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.dao.DocExecuteUsedDao;

public class DocExecuteUsedDaoImpl extends GenericDaoHibernate<VcNormalVerification> implements DocExecuteUsedDao {

	@Override
	public void deleteVcAvailableDocList(List<VcAvailableDoc> vcAvailableDocList)
			throws DaoException {
		// TODO Auto-generated method stub
		try{
			this.deleteAll(vcAvailableDocList);
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("删除可使用表时发生异常！");
		}
	}

	@Override
	public void saveVcNormalVerificationList(
			List<VcNormalVerification> vcNormalVerificationList)
			throws DaoException {
		// TODO Auto-generated method stub
		try{
			this.saveAll(vcNormalVerificationList);
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("插入核销表时发生异常！");
		}
	}

	@Override
	public void saveVcInvoicePrintList(List<VcInvoicePrint> vcInvoicePrintList)
			throws DaoException {
		// TODO Auto-generated method stub
		try{
			this.saveAll(vcInvoicePrintList);
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("插入发票开具信息表时发生异常！");
		}
	}
	
	@Override
	public VcDocVersionInfo getVcDocVersionInfo(Long id) throws DaoException {
		VcDocVersionInfo vcDocVersionInfo = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("idVcDocVersionInfo", id);
			vcDocVersionInfo = (VcDocVersionInfo)this.findUnique(VcDocVersionInfo.class, queryRule);
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("查找单证类型信息时发生异常！");
		}
		return vcDocVersionInfo;
	}
	
	@Override
	public VcInvoicePurchase findVcInvoicePurchase(String docVerCode, String invoiceCode, String invoiceNo) throws DaoException {
		VcInvoicePurchase vcInvoicePurchase = null;
		try{
			StringBuffer hql = new StringBuffer();
			List<Object> values = new ArrayList<Object>();
			hql.append(" from VcInvoicePurchase p where 1=1 and p.inStoreFlag='1' and p.status='1'");
			hql.append(" and p.docVerCode=? and p.invoiceCode=?");
			values.add(docVerCode);
			values.add(invoiceCode);
			hql.append(" and to_number(p.startNum)<=to_number(?) and to_number(p.endNum)>=to_number(?)");
			values.add(invoiceNo);
			values.add(invoiceNo);
			List<VcInvoicePurchase> list = (List<VcInvoicePurchase>)this.findByHql(hql.toString(), values.toArray());
			if(list!=null && list.size()!=0){
				vcInvoicePurchase = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询领购表失败！", e);
		}
		return vcInvoicePurchase;
	}
}
