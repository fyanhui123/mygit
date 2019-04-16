package com.tapi.tcs.vc.webservice.provider.common.dao.impl;


import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.common.util.ReportUtils;
import com.tapi.tcs.vc.schema.model.VcReportRecord;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcReportDao;


public class VcReportDaoImpl extends GenericDaoHibernate<VcReportRecord> implements VcReportDao {

	@Override
	public void addReport(Object obj) {
		super.save(obj);	
	}

	@Override
	public void updateReport(VcReportRecord record) throws DaoException{
		try{
			if(record!=null ){
				String hql = "update VcReportRecord record set record.errorCode=?," +
					"record.reportResult = ?," + 
					"record.errorDesc = ?," + 
					"record.responseXml = ?," + 
					"record.responseTime = ? where record.id=?";
				this.executeUpdate(hql, new Object[]{record.getErrorCode(), record.getReportResult(), 
						ReportUtils.substring(record.getErrorDesc(), 99),
						record.getResponseXml(), record.getResponseTime()  , record.getId()    });

			}
		}catch(Exception e){
			throw new DaoException(e.getMessage());
		}
		
	}

	
	

}
