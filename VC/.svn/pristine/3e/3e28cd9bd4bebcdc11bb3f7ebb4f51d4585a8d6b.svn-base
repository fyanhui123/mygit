package com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.dao;

import java.util.Date;
import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcDocRelInsuKind;

public interface QueryAvailableNumNewDao extends GenericDao<VcAvailableDoc> {

	/**查询可使用表
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public Page queryVcAvailableDocNew(int currentPage, int pageSize, Date lastSynTime, String docVerCode, String takerCode) throws DaoException;
	
	/**查询单证险种关联表
	 * 
	 * @param idVcDocVersionInfo
	 * @return
	 * @throws DaoException
	 */
	public List<VcDocRelInsuKind> queryDocRelInsuKindList(Long idVcDocVersionInfo) throws DaoException;

}
