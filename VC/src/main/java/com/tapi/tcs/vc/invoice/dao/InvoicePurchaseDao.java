package com.tapi.tcs.vc.invoice.dao;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.invoice.vo.InvoiceDocVersionDTO;
import com.tapi.tcs.vc.schema.model.VcDocInStore;
import com.tapi.tcs.vc.schema.model.VcInvoicePurchase;
public interface InvoicePurchaseDao  extends GenericDao<VcDocInStore>{ 

	
	/*****
     * 查询纳税人基本信息
     */
	public  Page  queryInvoicePurchaseBaseInfo(String  companyNo,int pageNo, int pageSize) throws DaoException;
	
	/*****
     * 查询发票领购信息
     */
	public  Page  queryInvoicePurchasePurchase(String  nsrdnbm,int pageNo, int pageSize) throws DaoException;
	
	/*****
     * 根据机构查询纳税人电脑编码
     */
	public  String  searchCompanyNoByOrgCode(String orgCode) throws DaoException;
	
	/*****
     * 查询纳税人授权信息
     */
	public  Page  queryInvoicePurchaseImpower(String  shortCode,int pageNo, int pageSize) throws DaoException;
	
	
	/*****
     * 根据纳税人领购信息流水查询发票简码代码
     */
	public  VcInvoicePurchase  queryPurchaseInfo(String  purId) throws DaoException;
	
	/*****
     * 查询纳税人授权明细信息
     */
	public  Page    queryInvoicePurchaseImpowerDet(String  ordId,int pageNo, int pageSize) throws BusinessException;
	
	/*****
     * 查询单证类型信息表
     */
	public  Page    queryInvoiceDocVersion(String  orgCode,InvoiceDocVersionDTO  invoiceDocVersionDTO,int pageNo, int pageSize) throws BusinessException;
	
	
	/**
	 * 保存印刷入库信息
	 * @param vcDocInStore 印刷入库信息主信息
	 * @param vcDocInStoreDets 印刷入库信息明细信息
	 */ 
	public void inStoreInvoiceDocVersion(VcDocInStore vcDocInStore) throws DaoException;
	
	/*****
     * 根据纳税人领购信息流水更新领购信息
     */
	public  void  updatePurchaseInfo(String formatDate,String  userCode,String  docVerCode,String purchaseId) throws DaoException;
	
}
