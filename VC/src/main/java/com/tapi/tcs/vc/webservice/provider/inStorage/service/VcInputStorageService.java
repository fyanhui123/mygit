package com.tapi.tcs.vc.webservice.provider.inStorage.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.inStorage.bean.InputStorageRequest;
import com.tapi.tcs.vc.webservice.provider.inStorage.bean.InputStorageResponse;

public interface VcInputStorageService {
	public InputStorageResponse saveStorageHandle(
			InputStorageRequest inputStorageRequest) throws BusinessException;

	public String getdocVerCode(String type) throws BusinessException;

}
