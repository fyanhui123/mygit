package com.tapi.tcs.vc.webservice.provider.newCancelInvoice.service;

import com.tapi.tcs.common.webservice.StorageResponse;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.newCancelInvoice.bean.CancelInvoiceRequest;

public interface VcCancelInvoiceService {
	public StorageResponse saveVcCancelInvoice(CancelInvoiceRequest cancelStorage)throws BusinessException;
}
