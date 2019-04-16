package com.tapi.tcs.vc.baseinfo.dao;

import java.util.List;

import com.tapi.tcs.vc.baseinfo.vo.DocInsuKindVo;
import com.tapi.tcs.vc.schema.model.VcDocRelInsuKind;
import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;

/**
 * 单证险种关联表DAO接口
 * <p>
 * Date: 2013-03-22
 * </p>
 * <p>
 * Module: 单证险种关联表
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
public interface VcDocRelInsuKindDao extends GenericDao<VcDocRelInsuKind> {

	/**
	 * 根据ID获取单证险种关联对象
	 * 
	 * @author wanghuajian
	 * 
	 * @param idVcDocRelInsuKind
	 * @return
	 */
	public VcDocRelInsuKind getVcDocRelInsuKind(Long idVcDocRelInsuKind);

	/**
	 * 查询单证险种关联信息
	 * 
	 * @author wanghuanjian
	 * 
	 * @param printery
	 *            单证险种关联查询条件dto
	 * @param currentPage
	 *            当前页码
	 * @param pageNumber
	 *            页面条数
	 * @return
	 */
	public Page queryVcDocRelInsuKinds(VcDocRelInsuKind printery, int currentPage, int pageNumber)throws DaoException;
	
	
	/**
	 * 根据单证类型流水查找关联险种
	 * 
	 * @param idVcDocVersionInfo
	 * @return
	 * @throws Exception
	 * @author wanghuajian
	 * @date 2013-04-27
	 */
	public List<DocInsuKindVo> queryRelInsuKindListByDocId(Long idVcDocVersionInfo)throws DaoException;
	
	
	/**
	 * 根据单证类型流水删除承单证险种关联信息
	 * @param idVcDocVersionInfo 单证类型流水
	 * @return
	 * @throws Exception
	 * @author wanghuajian
	 * since 2013-6-5 
	 */
	public int deleteByDocVersionInfoId(Long idVcDocVersionInfo)throws DaoException;

	/**
	 * 根据险种删除险种关联单证类型信息
	 * @param riskCode 险种
	 * @return
	 * @throws Exception
	 * @author fyanhui
	 *  
	 */
	
	public int deleteByRiskCode(String  riskCode)throws DaoException;
}
