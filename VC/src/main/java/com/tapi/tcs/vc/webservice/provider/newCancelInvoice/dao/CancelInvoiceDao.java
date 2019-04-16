package com.tapi.tcs.vc.webservice.provider.newCancelInvoice.dao;


import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;


public interface CancelInvoiceDao extends GenericDao<VcAvailableDoc>{
	public void deleteVcAvailableDocList(List<VcAvailableDoc> vcAvailableDocList) throws DaoException;
}
