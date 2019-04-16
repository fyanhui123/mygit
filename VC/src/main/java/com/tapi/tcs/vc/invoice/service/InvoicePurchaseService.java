package com.tapi.tcs.vc.invoice.service;

import java.io.File;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.invoice.vo.InvoiceDocVersionDTO;
import com.tapi.tcs.vc.schema.model.VcDocInStore;
import com.tapi.tcs.vc.schema.model.VcDocInStoreDet;

public interface InvoicePurchaseService {

	/*****
     * 查询纳税人基本信息
     */
	public  Page    queryInvoicePurchaseBaseInfo(String  invoicePurchaseOrgCode,int pageNo, int pageSize) throws BusinessException;
	
	/*****
     * 查询发票领购信息
     */
	public  Page    queryInvoicePurchasePurchase(String  nsrdnbm,int pageNo, int pageSize) throws BusinessException;
	
	/*****
     * 查询纳税人授权信息
     */
	public  Page    queryInvoicePurchaseImpower(String  purId,int pageNo, int pageSize) throws BusinessException;
	
	/*****
     * 查询纳税人授权明细信息
     */
	public  Page    queryInvoicePurchaseImpowerDet(String  ordId,int pageNo, int pageSize) throws BusinessException;
	
	/*****
     * 查询单证类型信息表
     */
	public  Page    queryInvoiceDocVersion(String orgCode,InvoiceDocVersionDTO  invoiceDocVersionDTO,int pageNo, int pageSize) throws BusinessException;
	
	
	/**
	 * 保存印刷入库信息
	 * @param vcDocInStore
	 * @param vcDocInStoreDets
	 * @throws Exception 
	 */
	public String executeInStoreInvoiceDocVersion(VcDocInStore vcDocInStore,String purchaseId,String docVerCode) throws Exception;
	
}
