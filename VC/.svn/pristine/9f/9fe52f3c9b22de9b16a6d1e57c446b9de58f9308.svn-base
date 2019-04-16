package com.tapi.tcs.vc.webservice.provider.docNumInquiry.dao;

import java.util.Date;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;

public interface QueryAvailableNumDao extends GenericDao<VcAvailableDoc> {

	/**查询可使用表
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public Page queryVcAvailableDoc(int currentPage, int pageSize, Date lastSynTime) throws DaoException;
}
