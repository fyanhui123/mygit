package com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;

public interface NewInvoiceDoUsedDao extends GenericDao<VcNormalVerification>{
	
	public List<VcAvailableDoc> checkDocNumIsValid(String docNum, String versionCode,
			 String pressBatchNo) throws DaoException;
	
}
