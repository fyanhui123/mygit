package com.tapi.tcs.vc.invoice.dao;

import java.util.Date;
import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.invoice.vo.InvoiceExportVO;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoiceRevoke;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcManageCodeHlj;
import com.tapi.tcs.vc.schema.model.VcTaxPayerLogin;

/**
 * 发票数据导出DAO
 * <p>
 * Date 2013-06-06
 * </p>
 * <p>
 * Module：发票数据导出
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
public interface InvoiceExportDao extends GenericDao<VcInvoicePrint> {

	/**
	 * 发票数据导出分页查询
	 * @param invoiceExportVO
	 * @param comCode
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BusinessException
	 */
	public Page queryInvoiceExport(InvoiceExportVO invoiceExportVO, String comCode, int pageNo, int pageSize) throws DaoException;
	
	/**
	 * 发票数据导出查询
	 * @param invoiceExportVO
	 * @param comCode
	 * @return
	 * @throws DaoException
	 */
	public List<VcInvoicePrint> queryInvoiceExport(InvoiceExportVO invoiceExportVO, String comCode) throws DaoException;
	
	/**
	 * 根据险类、险种查询单证类型代码
	 * @param invoiceExportVO
	 * @return
	 * @throws DaoException
	 */
	public List<String> queryDocVersionCode(InvoiceExportVO invoiceExportVO) throws DaoException;
	
	/**
	 * 初始化机构下拉框
	 * @param comCode
	 * @return
	 * @throws DaoException
	 */
	public List<VcLevel> getAllChildCompany(String comCode) throws DaoException;
	
	/**
	 * 根据ID数组查询
	 * @param idList
	 * @return
	 * @throws BusinessException
	 */
	public List<VcInvoicePrint> getVcInvoicePrintListByIds(Long[] idList) throws DaoException;
	
	/**
	 * 更新是否上传平台字段
	 * @param vcInvoicePrintList
	 * @param userCode
	 * @throws BusinessException
	 */
	public void updateIsUploadPlat(Long id, String userCode) throws DaoException;
	
	/**
	 * 更新发票表
	 * @param vcInvoicePrintList
	 * @throws DaoException
	 */
	public void updateVcInvoicePrint(List<VcInvoicePrint> vcInvoicePrintList) throws DaoException; 
	
	/**
     * 
     * @param queryVo 根据条件查询发票开具信息表（不包含机构条件）
     * @return
     * @throws DaoException
     * @author wanghuajian
     */
    public List<VcInvoicePrint> queryVcInvoicePrintList(VcInvoicePrint queryVo) throws DaoException ;
    
    /**
	 * 根据机构代码查找VcTaxPayerLogin
	 * @param orgCode
	 * @return
	 * @throws DaoException
	 */
	public VcTaxPayerLogin findVcTaxPayerLogin(String orgCode) throws DaoException;
	
	/**
	 * 查找发票缴销信息
	 * @param orgCode
	 * @param startDate
	 * @param endDate
	 * @param isUploadPlat
	 * @return
	 * @throws DaoException
	 */
	public List<VcInvoiceRevoke> findVcInvoiceRevoke(String orgCode,Date startDate, Date endDate, String isUploadPlat) throws DaoException;

	/**
	 * 查找贵州地税接口信息
	 * @param codeType
	 * @param codeCode
	 * @return
	 * @throws DaoException
	 */
	public String findCodeCname(String codeType, String codeCode)throws DaoException;
	
	/**
	 * 根据机构代码查询管理码配置表
	 * @param orgCode
	 * @return
	 * @throws DaoException
	 */
	public VcManageCodeHlj findVcManageCodeHljByOrgCode(String orgCode) throws DaoException;
}
