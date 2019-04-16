package com.tapi.tcs.vc.baseinfo.dao;

import java.util.List;

import com.tapi.tcs.vc.schema.model.VcPrintDocVersion;
import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;

/**
 * 承印单证版本DAO接口
 * <p>
 * Date: 2013-03-21
 * </p>
 * <p>
 * Module: 承印单证版本
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * @author wanghuajian
 * @version 1.0
 */
/**
 * @author whj
 *
 */
public interface VcPrintDocVersionDao extends GenericDao<VcPrintDocVersion> {

	/**
	 * 根据ID获取去承印单证版本对象
	 * 
	 * @author wanghuajian
	 * 
	 * @param idVcPrintDocVersion
	 * @return
	 */
	public VcPrintDocVersion getVcPrintDocVersion(Long idVcPrintDocVersion);

	/**
	 * 查询承印单证版本信息
	 * 
	 * @author wanghuanjian
	 * 
	 * @param queryDto
	 *            承印单证版本查询条件dto
	 * @param currentPage
	 *            当前页码
	 * @param pageNumber
	 *            页面条数
	 * @return
	 */
	public Page queryVcPrintDocVersions(VcPrintDocVersion queryDto, int currentPage, int pageNumber) throws DaoException;
	
	
	/**
	 * 根据条件删除承印单证版本信息
	 * @param conditionDto
	 * @return
	 * @throws Exception
	 * @author wanghuajian
	 * since 2013-4-15下午04:40:55
	 */
	public int deleteByConditions(VcPrintDocVersion conditionDto)throws DaoException;
	
	/**
	 * 根据印刷厂主键查找承印List
	 * @param printeryId
	 * @return vcPrintDocVersionList
	 * @throws DaoException
	 * @author chy
	 * @date 2013-05-20
	 */
	public List<VcPrintDocVersion> findVcPrintDocVersionList(Long printeryId) throws DaoException;
}
