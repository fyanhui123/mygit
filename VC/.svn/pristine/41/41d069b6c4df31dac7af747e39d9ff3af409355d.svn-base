package com.tapi.tcs.vc.invoice.service;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.invoice.vo.fujian.DownloadResponse;
import com.tapi.tcs.vc.schema.model.VcTaxAuth;
import com.tapi.tcs.vc.schema.model.VcInvoicePurchase;
import com.tapi.tcs.vc.schema.model.VcTaxPayerLogin;

public interface FuJianInvoiceService {

	/**
	 * 福建地税-发票领购数据下载接口
	 * @param orgCode
	 * @return
	 * @throws BusinessException
	 */
	public DownloadResponse invoiceDownload(String orgCode) throws BusinessException;
	
	/**
	 * 福建地税-开票数据上传接口
	 * @param login
	 * @return
	 * @throws BusinessException
	 */
	public void executeInvoiceUploadToPlat(VcTaxPayerLogin login) throws BusinessException;
	
	/**
	 * 保存领购信息、授权信息、授权明细
	 * @param response
	 * @param orgCode
	 * @param operatorCode
	 * @throws BusinessException
	 */
	public void saveInvoice(DownloadResponse response, String orgCode, String operatorCode) throws BusinessException;
	
	/**
	 * 根据纳税人电脑编码查找领购信息
	 * @param computerNo
	 * @return
	 * @throws BusinessException
	 */
	public List<VcInvoicePurchase> findVcInvoicePurchaseList(String computerNo) throws BusinessException;
	
	/**
	 * 根据发票简码代码查找授权信息
	 * @param invoiceShortCode
	 * @return
	 * @throws BusinessException
	 */
	public List<VcTaxAuth> findVcTaxAuthByShortCode(String invoiceShortCode) throws BusinessException;
	
	/**
	 * 查找纳税人登录信息
	 * @return
	 * @throws BusinessException
	 */
	public List<VcTaxPayerLogin> findVcTaxPayerLoginList() throws BusinessException;
}
