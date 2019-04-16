package com.tapi.tcs.vc.newInvoice.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcLost;

public interface QueryInvoiceDao extends GenericDao<VcAbnormalVerification>{
	//发票遗失
	//发票缴销
	public boolean queryInvoice(String employid)throws DaoException;
	//查询非正常核销表内该数据是否存在
	public List<VcAbnormalVerification> queryVcAbnormalVerification(String docVerCode,String start,String pressBatchNo);
}
