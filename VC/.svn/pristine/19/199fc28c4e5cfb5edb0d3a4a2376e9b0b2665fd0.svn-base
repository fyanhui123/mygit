package com.tapi.tcs.vc.invoice.service;

import java.util.Date;
import java.util.List;

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
 * 发票数据导出Service
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
public interface InvoiceExportService {

	/**
	 * 发票数据导出分页查询
	 * @param invoiceExportVO
	 * @param comCode
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BusinessException
	 */
	public Page queryInvoiceExport(InvoiceExportVO invoiceExportVO, String comCode, int pageNo, int pageSize) throws BusinessException;
	
	/**
	 * 发票数据导出查询
	 * @param invoiceExportVO
	 * @param comCode
	 * @return
	 * @throws BusinessException
	 */
	public List<VcInvoicePrint> queryInvoiceExport(InvoiceExportVO invoiceExportVO, String comCode) throws BusinessException;
	
	/**
	 * 初始化机构下拉框
	 * @param comCode
	 * @return
	 * @throws BusinessException
	 */
	public List<VcLevel> getAllChildCompany(String comCode) throws BusinessException;
	
	/**
	 * 根据ID数组查询
	 * @param idList
	 * @return
	 * @throws BusinessException
	 */
	public List<VcInvoicePrint> getVcInvoicePrintListByIds(Long[] idList) throws BusinessException;
	
	/**
	 * 更新是否上传平台字段
	 * @param vcInvoicePrintList
	 * @param userCode
	 * @throws BusinessException
	 */
	public void updateIsUploadPlat(Long[] ids, String userCode) throws BusinessException;
	
	/**
	 * 更新上传平台标志（发票打印表和缴销表）
	 * @param vcInvoicePrintTmpList
	 * @param uploadPlatList
	 * @param userCode
	 * @throws BusinessException
	 */
	public void updateUploadPlat(List<VcInvoicePrint> vcInvoicePrintTmpList, List<VcInvoiceRevoke> uploadPlatList, String userCode) throws BusinessException;
	
	/**
	 * 更新发票表
	 * @param vcInvoicePrintList
	 * @throws BusinessException
	 */
	public void updateVcInvoicePrint(List<VcInvoicePrint> vcInvoicePrintList) throws BusinessException;
	
	/**
	 * 拷贝发票开具信息对象
	 * @param srcObject
	 * @param oprCode
	 * @param oprDate
	 * @return
	 */
	public VcInvoicePrint copyVcInvoicePrint(VcInvoicePrint srcObject, String oprCode, Date oprDate);
	
	/**
     * 
     * @param queryVo 根据条件查询发票开具信息表（不包含机构条件）
     * @return
     * @throws DaoException
     * @author wanghuajian
     */
    public List<VcInvoicePrint> queryVcInvoicePrintList(VcInvoicePrint queryVo) throws BusinessException;
    
    /**
	 * 根据机构代码查找VcTaxPayerLogin
	 * @param orgCode
	 * @return
	 * @throws BusinessException
	 */
	public VcTaxPayerLogin findVcTaxPayerLogin(String orgCode) throws BusinessException;
	
	/**
	 * 查找发票缴销信息
	 * @param orgCode
	 * @param startDate
	 * @param endDate
	 * @param isUploadPlat
	 * @return
	 * @throws BusinessException
	 */
	public List<VcInvoiceRevoke> findVcInvoiceRevoke(String orgCode,Date startDate, Date endDate, String isUploadPlat) throws BusinessException;
	
	/**
	 * 更新缴销信息
	 * @param vcInvoiceRevokeList
	 * @param userCode
	 * @throws BusinessException
	 */
	public void updateVcInvoiceRevokeList(List<VcInvoiceRevoke> vcInvoiceRevokeList, String userCode) throws BusinessException;

	/**
	 * 查询贵州地税信息
	 * @param codeType
	 * @param codeCode
	 * @throws BusinessException
	 */
	public String findCodeCnameForGZ(String codeType, String codeCode) throws BusinessException;
	
	/**
	 * 根据机构代码查找对应的管理编码
	 * @param orgCode
	 * @return
	 * @throws BusinessException
	 */
	public VcManageCodeHlj findManageCodeByOrgCode(String orgCode) throws BusinessException;
}
