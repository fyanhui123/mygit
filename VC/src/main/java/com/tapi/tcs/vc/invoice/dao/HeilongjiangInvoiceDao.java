package com.tapi.tcs.vc.invoice.dao;

import java.util.Date;
import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.invoice.vo.VcInvoiceReportDetHljVo;
import com.tapi.tcs.vc.schema.model.VcInvoiceBuyHlj;
import com.tapi.tcs.vc.schema.model.VcInvoiceReportDetHlj;
import com.tapi.tcs.vc.schema.model.VcInvoiceReportHlj;
import com.tapi.tcs.vc.schema.model.VcManageCodeHlj;
import com.tapi.tcs.vc.schema.model.VcStorage;

public interface HeilongjiangInvoiceDao extends GenericDao<VcInvoiceReportHlj>{

	/**
	 * 根据机构代码查询管理码配置表
	 * @param orgCode
	 * @return
	 * @throws DaoException
	 */
	public VcManageCodeHlj findVcManageCodeHljByOrgCode(String orgCode) throws DaoException;
	
	/**
	 * 根据条件查询领购表
	 * @param conditions
	 * @return
	 * @throws DaoException
	 */
	public VcInvoiceBuyHlj findVcInvoiceBuyHlj(VcInvoiceBuyHlj conditions) throws DaoException;
	
	/**
	 * 根据当前机构代码查询最后一次报表记录
	 * @param orgCode
	 * @return
	 * @throws DaoException
	 */
	public VcInvoiceReportHlj queryLastReportByOrgCode(String orgCode) throws DaoException;
	
	/**
	 * 查询报表明细
	 * @param idVcInvoiceReportHlj
	 * @param detailType
	 * @return
	 * @throws DaoException
	 */
	public List<VcInvoiceReportDetHlj> queryVcInvoiceReportDetHlj(Long idVcInvoiceReportHlj, String detailType) throws DaoException;
	
	/**
	 * 查询领购记录
	 * @param orgCode
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws DaoException
	 */
	public List<VcInvoiceBuyHlj> queryVcInvoiceBuyHljList(String orgCode, Date startDate, Date endDate) throws DaoException;
	
	/**
	 * 查询库存
	 * @param orgCode
	 * @param docVerCode
	 * @param pressBatchNo
	 * @param startNum
	 * @param endNum
	 * @return
	 * @throws DaoException
	 */
	public List<VcInvoiceReportDetHlj> queryStorage(String orgCode, String docVerCode, String pressBatchNo, String startNum, String endNum) throws DaoException;
	
	/**
	 * 查询本期使用
	 * @param conditions
	 * @return
	 * @throws DaoException
	 */
	public List<VcInvoiceReportDetHljVo> queryNextUseDetail(VcInvoiceReportDetHljVo conditions) throws DaoException;
	
	/**
	 * 分页查询领用存报表
	 * @param orgCode
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public Page queryVcInvoiceReportHljPage(String orgCode, Date startDate, Date endDate, int pageNo, int pageSize) throws DaoException;
	
	/**
	 * 分页查询领购信息
	 * @param orgCode
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public Page queryVcInvoiceBuyPage(String orgCode, Date startDate, Date endDate, int pageNo, int pageSize) throws DaoException;
	
	/**
	 * 查询领购信息对应的库存
	 * @param vcInvoiceBuyHlj
	 * @return
	 * @throws DaoException
	 */
	public List<VcStorage> queryVcStorageByInvoiceBuy(final VcInvoiceBuyHlj vcInvoiceBuyHlj) throws DaoException;
}
