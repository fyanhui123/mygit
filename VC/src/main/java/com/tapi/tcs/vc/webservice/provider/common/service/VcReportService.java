package com.tapi.tcs.vc.webservice.provider.common.service;

import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcReportRecord;

public interface VcReportService {
	
	public void nestSaveReportNest(Object obj);
	
	public void nestUpdateReport(VcReportRecord record) throws DaoException;

}
