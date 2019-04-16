package com.tapi.tcs.vc.webservice.provider.newCancelInvoice.dao;
import java.util.List;
import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;


public class CancelInvoiceDaoImpl extends GenericDaoHibernate <VcAvailableDoc> implements CancelInvoiceDao{

	@Override
	public void deleteVcAvailableDocList(List<VcAvailableDoc> vcAvailableDocList)
			throws DaoException {
		try{
			for(int i=0;i<vcAvailableDocList.size();i++){
				this.delete(vcAvailableDocList.get(0));
			}
		}catch(Exception e){
			throw new DaoException("删除异常");
		}
	}
}
