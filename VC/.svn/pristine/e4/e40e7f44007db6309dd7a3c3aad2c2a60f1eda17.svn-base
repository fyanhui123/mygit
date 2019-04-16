package com.tapi.tcs.vc.invoice.service;

import java.util.Date;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.invoice.vo.VcInvoiceReportDetHljVo;
import com.tapi.tcs.vc.schema.model.VcInvoiceBuyHlj;
import com.tapi.tcs.vc.schema.model.VcInvoiceReportHlj;
import com.tapi.tcs.vc.schema.model.VcManageCodeHlj;

public interface HeilongjiangInvoiceService {

	/**
	 * 保存领购表
	 * @param vcInvoiceBuyHljs
	 * @throws BusinessException
	 */
	public void saveVcInvoiceBuyHljList(List<VcInvoiceBuyHlj> vcInvoiceBuyHljs) throws BusinessException;
	
	/**
	 * 根据机构代码查找对应的管理编码
	 * @param orgCode
	 * @return
	 * @throws BusinessException
	 */
	public VcManageCodeHlj findManageCodeByOrgCode(String orgCode) throws BusinessException;
	
	/**
	 * 根据条件查找领购表
	 * @param conditions
	 * @return
	 * @throws BusinessException
	 */
	public VcInvoiceBuyHlj findVcInvoiceBuyHlj(VcInvoiceBuyHlj conditions) throws BusinessException;
	
	/**
	 * 更新领购表
	 * @param vcInvoiceBuyHlj
	 * @throws BusinessException
	 */
	public void updateVcInvoiceBuyHlj(VcInvoiceBuyHlj vcInvoiceBuyHlj) throws BusinessException;
	
	/**
	 * 根据机构代码查询最后一次报表
	 * @param orgCode
	 * @return
	 * @throws BusinessException
	 */
	public VcInvoiceReportHlj queryLastReportByOrgCode(String orgCode) throws BusinessException;
	
	/**
	 * 查询前期结存
	 * @param orgCode
	 * @return
	 * @throws BusinessException
	 */
	public List<VcInvoiceReportDetHljVo> queryLastLeftReportList(String orgCode) throws BusinessException;
	
	/**
	 * 查询上期领购
	 * @param orgCode
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws BusinessException
	 */
	public List<VcInvoiceReportDetHljVo> queryLastBuyReportList(String orgCode, Date startDate, Date endDate) throws BusinessException;
	
	/**
	 * 查询本期使用
	 * @param orgCode
	 * @param startDate
	 * @param endDate
	 * @param lastLeftList
	 * @param lastBuyList
	 * @return
	 * @throws BusinessException
	 */
	public List<VcInvoiceReportDetHljVo> queryNextUseReportList(String orgCode, Date startDate, Date endDate,
			List<VcInvoiceReportDetHljVo> lastLeftList, List<VcInvoiceReportDetHljVo> lastBuyList) throws BusinessException;
	
	/**
	 * 查询本期结存
	 * @param orgCode 机构代码
	 * @param lastLeftList 前期结存
	 * @param lastBuyList 上期领购
	 * @return
	 * @throws BusinessException
	 */
	public List<VcInvoiceReportDetHljVo> queryNextLeftReportList(String orgCode, List<VcInvoiceReportDetHljVo> lastLeftList
			, List<VcInvoiceReportDetHljVo> lastBuyList) throws BusinessException;
	
	/**
	 * 保存领用存报表
	 * @param vcInvoiceReportHlj
	 * @throws BusinessException
	 */
	public void saveVcInvoiceReportHlj(VcInvoiceReportHlj vcInvoiceReportHlj) throws BusinessException;
	
	/**
	 * 根据id查找VcInvoiceReportHlj
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public VcInvoiceReportHlj findVcInvoiceReportHlj(Long id) throws BusinessException;
	
	/**
	 * 分页查询领用存报表
	 * @param orgCode
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BusinessException
	 */
	public Page queryVcInvoiceReportHljPage(String orgCode, Date startDate, Date endDate, int pageNo, int pageSize) throws BusinessException;
	
	/**
	 * 分页查询领购信息
	 * @param orgCode
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws BusinessException
	 */
	public Page queryVcInvoiceBuyPage(String orgCode, Date startDate, Date endDate, int pageNo, int pageSize) throws BusinessException;
}
