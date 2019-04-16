package com.tapi.tcs.vc.baseinfo.dao;

import java.util.List;
import java.util.Map;

import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.VcDocType;
import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;

/**
 * 单证种类维护DAO接口
 * <p>
 * Date: 2013-03-13
 * </p>
 * <p>
 * Module: 单证种类维护
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * @author Lincoln
 * @version 1.0
 */
public interface VcDocTypeDao extends GenericDao<VcDocType> {

	/**
	 * 根据ID获取去单证种类对象
	 * 
	 * @author wanghuajian
	 * 
	 * @param contractId
	 * @return
	 */
	public VcDocType getVcDocType(Long idVcDocType);

	/**
	 * 查询单证种类信息
	 * 
	 * @author wanghuanjian
	 * 
	 * @param vcDocType
	 *            单证种类查询条件dto
	 * @param currentPage
	 *            当前页码
	 * @param pageNumber
	 *            页面条数
	 * @return
	 */
	public Page queryVcDocTypes(VcDocType vcDocType,UserInfo userInfo, int currentPage, int pageNumber)throws DaoException;

	/**
	 * 根据给定的条件查询
	 */
	public List<VcDocType> getDocTypeList(Map<String, Object> map)throws DaoException;
}
