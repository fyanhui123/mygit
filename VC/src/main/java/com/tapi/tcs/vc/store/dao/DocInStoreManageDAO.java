package com.tapi.tcs.vc.store.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcDocInStore;
import com.tapi.tcs.vc.schema.model.VcDocInStoreDet;
import com.tapi.tcs.vc.schema.model.VcStorage;

public interface DocInStoreManageDAO  extends GenericDao<VcDocInStore> {
	
	/**
	 * <p> 查询印刷入库信息 </p>
	 * @param vcDocInStore 印刷入库对象
	 * @param pageNo 
	 * @param pageSize
	 * @return
	 */
	public Page queryVcDocInStore(VcDocInStore vcDocInStore,int pageNo,int pageSize,String type) throws DaoException;
	
	/**
	 * 通过逐渐查询印刷入库信息
	 * @param id
	 * @return
	 */
	public VcDocInStore findVcDocInStoreByPK(Long id) throws DaoException;
	
	/**
	 * 保存印刷入库信息
	 * @param vcDocInStore 印刷入库信息主信息
	 * @param vcDocInStoreDets 印刷入库信息明细信息
	 */ 
	public void saveVcDocInStore(VcDocInStore vcDocInStore) throws DaoException;
	
	/**
	 *	修改印刷入库信息
	 * @param vcDocInStore 印刷入库信息主信息
	 * @param vcDocInStoreDets 印刷入库信息明细信息
	 */
	public void editVcDocInStore(VcDocInStore vcDocInStore,List<VcDocInStoreDet> vcDocInStoreDets) throws DaoException;
	
	/**
	 * 删除印刷入库信息
	 * @param idList 需删除的印刷入库信息的id
	 */
	public void deleteVcDocInStore(List<Long> idList) throws DaoException;
	
	/**
	 * 查询印刷入库详细信息
	 * @param queryRule
	 * @return
	 */
	public List<VcDocInStoreDet> findVcDocInStoreDet(Long id) throws DaoException;
	
	/**
	 * 保存单证
	 * @param vcStorages
	 */
	public void saveVcStorages(List<VcStorage> vcStorages) throws DaoException;
	
	/**
	 * 锁表操作
	 * @param id
	 */
	public void lockDocInStore(Long id)  throws DaoException;
	
	public void updateBySQL(String sql,Object... params)  throws DaoException;
	
	public List viewVcDocInStore(Long id) throws DaoException;
}
