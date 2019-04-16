package com.tapi.tcs.vc.webservice.provider.outStorage.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;

import com.tapi.tcs.vc.schema.model.VcStorage;
public interface OutStorageDao  extends GenericDao<VcStorage>{
	//查询库存内是否已经存在该号段
	public List<Object> queryInvoice(String start, String end, String docVerCode, String pressBatchNo)throws DaoException;
	
	/**查询库存内是不是有这些号段
	 * 有的话入库
	 * 拆分库存   删除库存 插入可使用表
	 * 
	 */
	
	 public  String splitStorage1(String start, String end, String docVerCode, String pressBatchNo, String orgCode,
	            String docStatus, String changeToStatus, VcStorage vcStorage,String userCode)throws DaoException;

}
