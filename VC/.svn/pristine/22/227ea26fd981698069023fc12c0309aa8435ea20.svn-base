package com.tapi.tcs.vc.webservice.provider.docNumSynchronize.dao;

import java.util.Date;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcDocTakerIo;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;

public interface QueryProvideNumDao {
	/**查询单证发放轨迹表
	 * 
	 * @param docVerCode
	 * @param  takerCode
	 * @return
	 * @throws DaoException
	 */
	public List<VcDocTakerIo> queryVcDocTakerIo(String docVerCode, String takerCode,Date startDate,Date EndDate) throws DaoException;
	
	/**查询单证类型关联单证类型ID
	 * 
	 * @param docVerCode
	 * @return
	 * @throws DaoException
	 */
	public List<VcDocVersionInfo> queryVcDocVersionId(String docVerCode) throws DaoException;
	
	/**根据单证类型代码和起止流水号可使用表单证有效期
	 * 
	 * @param docVerCode
	 * @return
	 * @throws DaoException
	 */
	public List<VcAvailableDoc> queryVcDocDeadline(String docVerCode,String startNum,String endNum) throws DaoException;
	
	


}
