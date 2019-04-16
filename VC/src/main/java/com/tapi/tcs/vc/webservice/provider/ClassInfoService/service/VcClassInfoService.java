package com.tapi.tcs.vc.webservice.provider.ClassInfoService.service;

import com.tapi.tcs.vc.webservice.provider.ClassInfoService.bean.ClassInfoProductFactoryRequest;
import com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean.ComonProductFactoryResponse;

public interface VcClassInfoService {
	public ComonProductFactoryResponse saveClassinfo(ClassInfoProductFactoryRequest rs);
}
