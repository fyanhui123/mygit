package com.tapi.tcs.vc.visausage.dao;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;

public interface AbnormalVerificationDao  extends GenericDao<VcAbnormalVerification> {

	/**
	 * 根据单证类型、发票代码、发票号码查找发票打印信息
	 * @param docVerCode
	 * @param invoiceCode
	 * @param invoiceNo
	 * @return
	 * @throws DaoException
	 */
	public VcInvoicePrint findVcInvoicePrint(String docVerCode, String invoiceCode, String invoiceNo) throws DaoException;
	
	/**
	 * 重新插入一条发票信息
	 * @param vcInvoicePrint
	 * @throws DaoException
	 */
	public void saveNewInvoice(VcInvoicePrint vcInvoicePrint) throws DaoException;
	
	/**
	 * 校验单证类型是否为发票：返回true-是发票
	 * @param docVerCode
	 * @return
	 * @throws DaoException
	 */
	public boolean checkDocType(String docVerCode) throws DaoException;

	/**
	 * 查询当前机构信息
	 * @param companyCode
	 * @return
	 * @throws DaoException
	 */
	public PubCompany queryPubCompany(String companyCode) throws DaoException;
}
