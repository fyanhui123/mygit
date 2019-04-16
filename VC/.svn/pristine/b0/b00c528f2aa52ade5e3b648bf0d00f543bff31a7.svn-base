package com.tapi.tcs.vc.webservice.provider.common.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.PubUserDef;
import com.tapi.tcs.vc.schema.model.VcDocType;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;

/**
 * 基础代码处理DAO
 * <p>
 * Date 2013-05-30
 * </p>
 * <p>
 * Module：Webservice-公共模块
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
public interface VcBaseInfoDao extends GenericDao<VcDocVersionInfo> {

	/**
	 * 把老系统传入的单证种类ID转换成单证种类表ID
	 * @param oldId
	 * @return
	 * @throws DaoException
	 */
	/*public List<Long> translateDocTypeId(List<Long> oldId) throws DaoException;*/
	
	/**
	 * 把老系统传入的产品大类ID转换成险类ID
	 * @param categoryId
	 * @return
	 * @throws DaoException
	 */
	public Long translateInsuTypeId(Long categoryId) throws DaoException;
	
	/**
	 * 把老系统传入的产品ID转换成险种代码
	 * @param productId
	 * @param insuTypeId
	 * @return
	 * @throws DaoException
	 */
	public String translateInsuKindCode(Long productId, Long insuTypeId) throws DaoException;
	
	/**
	 * 把老系统传入的部门id、部门代码转换成机构代码
	 * @param orgId
	 * @return
	 * @throws DaoException
	 */
	/*public String translateOrgCode(Long orgId) throws DaoException;*/
	public String translateOrgCode(Long orgId, String orgCode) throws DaoException;
	
	/**
	 * 根据老系统传入的部门id和部门代码，查找本级及所有下级机构代码列表
	 * @param orgId
	 * @return
	 * @throws DaoException
	 */
	/*public List<String> getOrgCodeListByOldId(Long orgId) throws DaoException;*/
	public List<String> getOrgCodeListByOldId(Long orgId, String orgCode) throws DaoException;
	
	/**
	 * 把老系统传入的用户ID转换成用户代码
	 * @param userId
	 * @return
	 * @throws DaoException
	 */
	public String translateUserCode(Long userId) throws DaoException;
	
	/**
	 * 根据oldEmployeeId查找用户信息
	 * @param userId
	 * @return
	 * @throws DaoException
	 */
	public PubUserDef getPubUserDefByOldId(Long userId) throws DaoException;
	
	/**
	 * 根据单证类型ID查找单证类型code
	 * @param docVerID
	 * @return
	 * @throws DaoException
	 */
	public  String  getDocVerCode(long  docVerID) throws DaoException; 
	
	/**
	 * 根据机构代码查找老系统对应的机构id
	 * @param companyCode
	 * @return
	 * @throws DaoException
	 * @author chy
	 */
	/*public String getCompanyId(String companyCode) throws DaoException;*/
	
	/**
	 * 根据机构代码，查找本级及所有上级机构代码列表
	 * @param orgCode
	 * @return
	 * @throws DaoException
	 */
	public List<String> getOrgCodeListByOrgCode(String orgCode) throws DaoException;

	/**
	 * 根据单证类型代码查找归属单证种类信息
	 * @param docVerCode
	 * @return
	 * @throws DaoException
	 */
	public List<VcDocType> getVcDocType(String docVerCode) throws DaoException;
	
	/**
	 * 查询发票开具信息
	 * @param orgCode
	 * @param invoiceCode
	 * @param invoiceNo
	 * @param printKind
	 * @return
	 * @throws DaoException
	 */
	public VcInvoicePrint findVcInvoicePrint(String orgCode, String invoiceCode, String invoiceNo, String printKind) throws DaoException;
	
	/**
	 * 根据employeeId查找用户名称
	 * @param employeeId
	 * @return
	 * @throws DaoException
	 */
	public String findUserNameByEmployeeId(String employeeId) throws DaoException;
	
	/**
	 * 根据companyCode查找机构名称
	 * @param companyCode
	 * @return
	 * @throws DaoException
	 */
	public String findCompanyNameByCode(String companyCode) throws DaoException;
}
