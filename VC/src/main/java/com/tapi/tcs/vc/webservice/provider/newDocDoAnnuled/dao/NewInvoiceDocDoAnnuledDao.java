package com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.dao;


import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
//作废
public interface NewInvoiceDocDoAnnuledDao extends GenericDao<VcAbnormalVerification> {
	public VcAbnormalVerification findVcAbNormalVerification(String docVerCode,
			String docNum,String pressBatchNo,String businessNo)throws  BusinessException;
}
