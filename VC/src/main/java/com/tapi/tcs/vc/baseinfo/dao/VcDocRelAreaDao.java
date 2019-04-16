package com.tapi.tcs.vc.baseinfo.dao;

import java.util.List;

import com.tapi.tcs.vc.schema.model.VcDocRelArea;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;

/**
 * 单证地区关联表DAO接口
 * <p>
 * Date: 2013-03-22
 * </p>
 * <p>
 * Module: 单证地区关联表
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
public interface VcDocRelAreaDao extends GenericDao<VcDocRelArea> {

	/**
	 * 根据ID获取单证地区关联对象
	 * 
	 * @author wanghuajian
	 * 
	 * @param idVcDocRelArea
	 * @return
	 */
	public VcDocRelArea getVcDocRelArea(Long idVcDocRelArea);

	/**
	 * 查询单证地区关联信息
	 * 
	 * @author wanghuanjian
	 * 
	 * @param printery
	 *            单证地区关联查询条件dto
	 * @param currentPage
	 *            当前页码
	 * @param pageNumber
	 *            页面条数
	 * @return
	 */
	public Page queryVcDocRelAreas(VcDocRelArea printery, int currentPage, int pageNumber)throws DaoException;
	
	
	/**
	 * 根据单证类型流水查找关联地区
	 * 
	 * @param idVcDocVersionInfo
	 * @return
	 * @throws Exception
	 * @author wanghuajian
	 * @date 2013-04-27
	 */
	public List<VcLevel> queryRelAreaListByDocId(Long idVcDocVersionInfo) throws DaoException ;
	
	
	/**
	 * 根据单证类型流水删除地区关联信息
	 * @param idVcDocVersionInfo 单证类型流水
	 * @return
	 * @throws Exception
	 * @author wanghuajian
	 * since 2013-6-5 
	 */
	public int deleteByDocVersionInfoId(Long idVcDocVersionInfo)throws DaoException;

}
