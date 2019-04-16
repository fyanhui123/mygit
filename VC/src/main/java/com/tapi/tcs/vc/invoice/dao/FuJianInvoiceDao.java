package com.tapi.tcs.vc.invoice.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoiceRevoke;
import com.tapi.tcs.vc.schema.model.VcTaxAuth;
import com.tapi.tcs.vc.schema.model.VcTaxPayerInfo;
import com.tapi.tcs.vc.schema.model.VcTaxPayerLogin;
import com.tapi.tcs.vc.schema.model.VcInvoicePurchase;

public interface FuJianInvoiceDao extends GenericDao<VcTaxPayerLogin>{

	/**
	 * 根据机构部代码查询纳税人登录信息
	 * @param orgCode
	 * @return
	 * @throws DaoException
	 */
	public VcTaxPayerLogin findVcTaxPayerLogin(String orgCode) throws DaoException;
	
	/**
	 * 根据纳税人电脑编码查找纳税人基本信息
	 * @param computerNo
	 * @return
	 * @throws DaoException
	 */
	public VcTaxPayerInfo findVcTaxPayerInfo(String computerNo) throws DaoException;
	
	/**
	 * 根据领购序号查找领购信息表
	 * @param serialNo
	 * @return
	 * @throws DaoException
	 */
	public VcInvoicePurchase getVcInvoicePurchaseBySerialNo(String serialNo) throws DaoException;
	
	/**
	 * 根据纳税人电脑编码查找领购信息
	 * @param computerNo
	 * @return
	 * @throws DaoException
	 */
	public List<VcInvoicePurchase> findVcInvoicePurchaseList(String computerNo) throws DaoException;
	
	/**
	 * 根据发票简码代码查找授权信息
	 * @param invoiceShortCode
	 * @param computerNo
	 * @return
	 * @throws DaoException
	 */
	public List<VcTaxAuth> findVcTaxAuthByShortCode(String invoiceShortCodd) throws DaoException;
	
	/**
	 * 查询纳税人登录信息列表
	 * @return
	 * @throws DaoException
	 */
	public List<VcTaxPayerLogin> findVcTaxPayerLoginList() throws DaoException;
	
	/**
	 * 根据纳税人电脑编码查询未上传平台的发票信息
	 * @param computerNo
	 * @return
	 * @throws DaoException
	 */
	public List<VcInvoicePrint> findVcInvoicePrintNoUpload(String computerNo) throws DaoException;
	
	/**
	 * 根据纳税人电脑编码查询未上传平台的发票缴销信息
	 * @param computerNo
	 * @return
	 * @throws DaoException
	 */
	public List<VcInvoiceRevoke> findVcInvoiceRevokeNoUpload(String computerNo) throws DaoException;
	
	/**
	 * 根据单证类型代码、发票代码、发票号码查找领购信息
	 * @param vcInvoicePrint
	 * @return
	 * @throws DaoException
	 */
	public VcInvoicePurchase findVcInvoicePurchase(VcInvoicePrint vcInvoicePrint) throws DaoException;
	
	/**
	 * 根据单证类型代码、发票代码、发票号码查找领购信息
	 * @param vcInvoiceRevoke
	 * @return
	 * @throws DaoException
	 */
	public VcInvoicePurchase findVcInvoicePurchase(VcInvoiceRevoke vcInvoiceRevoke) throws DaoException;
	
	/**
	 * 更新发票开具表
	 * @param vcInvoicePrintList
	 * @param isUploadPlat
	 * @throws DaoException
	 */
	public void updateVcInvoicePrintList(List<Long> vcInvoicePrintList, String isUploadPlat) throws DaoException;
	
	/**
	 * 更新发票缴销表
	 * @param vcInvoiceRevokeList
	 * @param isUploadPlat
	 * @throws DaoException
	 */
	public void updateVcInvoiceRevokeList(List<Long> vcInvoiceRevokeList, String isUploadPlat) throws DaoException;
}
