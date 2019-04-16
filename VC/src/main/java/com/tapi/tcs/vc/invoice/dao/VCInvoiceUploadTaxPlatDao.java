package com.tapi.tcs.vc.invoice.dao;

import java.util.List;
import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcPubCode;

/**
 * 发票数据处理
 */
public interface VCInvoiceUploadTaxPlatDao extends GenericDao<VcInvoicePrint> {

	/**
	 * 设置发票数据生成XML格式数据的SQL
	 * 
	 */
	public abstract void setInvoiceXMLSql(String invoiceXMLSql);
	/**
	 * 生成上传地税需要使用的用户帐户数据
	 */
	public abstract void setLoginPlatXMLSql(String loginPlatXMLSql);
	/**
	 * 生成发票XML格式数据
	 * 数据来源vc_invoice_print,vc_invoice_print_ext
	 * @return 发票XML格式数据
	 */
	public abstract String generateInvoiceToXML(Object... args) throws DaoException,BusinessException;
	/**
	 * 生成登录平台用户XML格式数据, 根据机构代码提取小于等于传入机构的所有机构配置
	 * 数据来源：vc_taxpayer_login
	 * @return 登录平台用户XML格式数据
	 */
	public abstract List<String> generateTaxLoginToXML(String companyCode) throws DaoException;
	
	/**
     * 生成登录平台用户XML格式数据
     * 数据来源：vc_taxpayer_login
     * @return 登录平台用户XML格式数据
     */
    public List<String> generateTaxLoginToXML(String companyCode,long startRow,long endRow) throws DaoException;
	
	/**
     * 查询登录平台用户数量
     * 数据来源：vc_taxpayer_login
     * @return 登录平台用户数量
     */
    public long queryTaxLoginCount(String companyCode) throws DaoException;
    
	/**
	 * 根据发票代码和发票号更新是否上传平台标志为已上平台
	 */
	public abstract int updateUploadedPlatFlag(String invoiceCode, String invoiceNo);
	
	/**
     * 更新当前批次数
     * @param resp 上传平台结果
     */
	public void updateCurBatchNo(VcPubCode vcPubCode,String nextBatchNumum)throws DaoException ;
}
