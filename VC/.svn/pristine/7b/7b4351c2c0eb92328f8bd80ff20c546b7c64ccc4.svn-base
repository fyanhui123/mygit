package com.tapi.tcs.vc.webservice.provider.docDoUsedOld.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoicePurchase;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;

/**
 * 单证核销接口DAO
 * <p>
 * Date 2013-06-04
 * </p>
 * <p>
 * Module：Webservice-核销接口
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * @author chy
 * @version 1.0
 */
public interface DocExecuteUsedDao extends GenericDao<VcNormalVerification>{

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
	
	/**
	 * 保存发票开具信息
	 * @param vcInvoicePrintList
	 * @throws DaoException
	 */
	public void saveVcInvoicePrintList(List<VcInvoicePrint> vcInvoicePrintList) throws DaoException;
	
	/**
	 * 根据主键查找单证类型信息
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public VcDocVersionInfo getVcDocVersionInfo(Long id) throws DaoException;
	
	/**
	 * 查询领购信息表
	 * @param docVerCode
	 * @param invoiceCode
	 * @param invoiceNo
	 * @return
	 * @throws DaoException
	 */
	public VcInvoicePurchase findVcInvoicePurchase(String docVerCode, String invoiceCode, String invoiceNo) throws DaoException;
}
