package com.tapi.tcs.vc.webservice.provider.docDoUsed.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
public interface DocDoUsedDao {
 
	
	/**
	 * 更新核销信息
	 * @param vcNormalVerificationList
	 * @throws DaoException
	 */
	public void updateVcNormalVerificationList(VcNormalVerification  vc) throws DaoException;
	
	/**
	 * 保存核销信息
	 * @param vcNormalVerificationList
	 * @throws DaoException
	 */
	public void saveVcNormalVerificationList(List<VcNormalVerification> vcNormalVerificationList) throws DaoException;
	
	/**
	 * 删除可使用表
	 * @param vcAvailableDocList
	 * @throws DaoException
	 */
	public void deleteVcAvailableDocList(List<VcAvailableDoc> vcAvailableDocList) throws DaoException;

	
	public List<Object[]>  checkVcNormal(String businessNo, String versionCode) throws DaoException;
	
	/****
	 *  查询正常核销表
	 * @param businessNo
	 * @param versionCode
	 * @return
	 * @throws DaoException
	 */
	public List<Object[]>  queryVcNormal(String businessNo, String docVerCode,String  docStatus) throws DaoException;

}
 