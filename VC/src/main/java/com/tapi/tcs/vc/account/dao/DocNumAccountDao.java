package com.tapi.tcs.vc.account.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcBisDocNumAcct;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;

public interface DocNumAccountDao extends GenericDao<VcBisDocNumAcct>{

	public void saveBisDocNumAcctList(List<VcBisDocNumAcct> list) throws DaoException;
	public List<VcNormalVerification> queryBisDocNumIsUsed(VcBisDocNumAcct vcBisDocNumAcct) throws DaoException ;
	public List<VcAbnormalVerification> queryBisDocNumAnnuld(VcBisDocNumAcct vcBisDocNumAcct) throws DaoException;
	public String findCodeCname(String codeType, String codeCode) throws DaoException;
	public List<VcBisDocNumAcct> findBisDocNumListForAcct() throws DaoException ;
	public List<VcBisDocNumAcct> findBisDocNumList(VcBisDocNumAcct tempVo) throws DaoException;
}
