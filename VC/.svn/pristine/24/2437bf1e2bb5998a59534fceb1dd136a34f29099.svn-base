package com.tapi.tcs.vc.subfunc.dao;

import java.util.Date;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.Contract;

/**
 * 印刷合同管理DAO接口
 * <p>
 * Date: 2013-02-19
 * </p>
 * <p>
 * Module: 印刷合同管理
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
public interface ContractDao extends GenericDao<Contract> {

	/**
	 * 根据ID获取去合同对象
	 * 
	 * @author Lincoln
	 * 
	 * @param contractId
	 * @return
	 */
	public Contract getContract(Long contractId);

	/**
	 * 查询合同信息
	 * 
	 * @author Lincoln
	 * 
	 * @param contractName
	 *            合同名
	 * @param contractStartDate
	 *            合同期限起期
	 * @param contractEndDate
	 *            合同期限截期
	 * @param currentPage
	 *            当前页码
	 * @param pageNumber
	 *            页面条数
	 * @return
	 */
	public Page queryContracts(String contractName, Date contractStartDate, Date contractEndDate, int currentPage, int pageNumber);

}
