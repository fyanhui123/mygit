package com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.service;

import com.tapi.tcs.common.webservice.StorageResponse;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.bean.NewInvoiceDocDoAnnuledRequest;

public interface VcNewInvoiceDocDoAnnuledService {
	public StorageResponse executeNewDocDoAnnuled(NewInvoiceDocDoAnnuledRequest request)throws BusinessException;
}
