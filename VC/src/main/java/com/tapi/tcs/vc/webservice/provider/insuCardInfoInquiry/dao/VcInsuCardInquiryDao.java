package com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.dao;


import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesDetail;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesRecord;
import com.tapi.tcs.vc.schema.model.VcInsuranceCard;
import com.tapi.tcs.vc.schema.model.VcTaker;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.RiskDTO;

public interface VcInsuCardInquiryDao extends GenericDao<VcInsuranceCard>{

	/**
	 * 根据卡号、加密密码查找激活卡信息、单证类型信息
	 * @param cardNO
	 * @param encryptedPW
	 * @return
	 * @throws DaoException
	 */
	public Object[] findVcInsuranceCard(String cardNO, String encryptedPW) throws DaoException;
	
	/**
	 * 查询单证使用人表
	 * @param docVerCode
	 * @param docNum
	 * @return
	 * @throws DaoException
	 */
	public VcTaker findVcTaker(String docVerCode, String docNum) throws DaoException;
	
	/**
	 * 查询激活卡销售信息
	 * @param docVerCode
	 * @param docNum
	 * @throws DaoException
	 */
	public VcInsuCardSalesRecord findSalesInfo(String docVerCode, String docNum) throws DaoException;
	
	/**
	 * 查询险种列表
	 * @param idVcDocVersionInfo
	 * @return
	 * @throws DaoException
	 */
	public List<RiskDTO> findRiskDTOs(Long idVcDocVersionInfo) throws DaoException;
}
