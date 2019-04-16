package com.tapi.tcs.vc.webservice.provider.common.service.impl;

import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcReportRecord;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcReportDao;
import com.tapi.tcs.vc.webservice.provider.common.service.VcReportService;

public class VcReportServiceImpl implements VcReportService {

	/**
	 * 记录报文DAO
	 */
	private VcReportDao vcReportDao;
	

	public void setVcReportDao(VcReportDao vcReportDao) {
		this.vcReportDao = vcReportDao;
	}

	public VcReportDao getVcReportDao() {
		return vcReportDao;
	}

	@Override
	public void nestSaveReportNest(Object obj) {
		vcReportDao.save(obj);
	}

	@Override
	public void nestUpdateReport(VcReportRecord record) throws DaoException {
		vcReportDao.updateReport(record);
	}
	
	
}
