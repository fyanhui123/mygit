package com.tapi.tcs.vc.webservice.provider.inStorage.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;

import com.tapi.tcs.vc.schema.model.VcStorage;

public interface inputStorage extends GenericDao<VcStorage> {
	// 根据使用人代码查询用户信息
	public String queryUserInfo(String userCode, String type, String orgCode)
			throws DaoException;

	public void saveVcStorageList(List<VcStorage> vcStorageList)
			throws DaoException;

	// 根据机构代码查询该机构下有没有发票管理员
	public int queryLevel(String vclevel) throws DaoException;

	// 根据发票类型得到发票代码
	public String queryDocverCode(String type) throws Exception;

	// 查询发票在库存内是否已经存在

	public void inMergeStorage(String start, String end, String docVerCode,
			String pressBatchNo, String orgCode, String docStatus,
			String docStatusTo, String userCode,String flag) throws DaoException;

	// 查询库存内发票信息
	public List<VcStorage> queryVcStroage(String docVerCode, String start,
			String end, String pressBatchNo) throws DaoException;

	public String max(String str1, String str2);

	public String min(String str1, String str2);

	public void saveStorage(VcStorage vcStorage);

	public void deleteStorage(long id) throws DaoException;
	public List<VcStorage> queryStorages(String docVerCode,String strNum,String endNum,String pressBatchNo,String orgCode)throws DaoException;
	public boolean resultType(String docVerCode)throws DaoException;
}
