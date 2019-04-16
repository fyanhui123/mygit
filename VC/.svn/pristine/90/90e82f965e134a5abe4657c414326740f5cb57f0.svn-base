package com.tapi.tcs.vc.subfunc.dao.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.schema.model.Contract;
import com.tapi.tcs.vc.subfunc.dao.ContractDao;

/**
 * 印刷合同管理DAO实现
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
public class ContractDaoImpl extends GenericDaoHibernate < Contract > implements ContractDao {

    /**
     * 根据合同ID查询合同信息
     * @param contractId 合同Id
     * @return Contract 合同对象
     */
    public Contract getContract(Long contractId) {
        return (Contract) this.get(Contract.class, contractId);
    }

    /**
     * 根据查询条件查询满足条件的合同信息
     * @param contractName 合同名
     * @param contractStartDate 合同起期
     * @param contractEndDate 合同止期
     * @param currentPage 当前页
     * @param pageNumber 每页记录数
     * @return Page 分页对象
     */
    public Page queryContracts(String contractName, Date contractStartDate, Date contractEndDate, int currentPage,
            int pageNumber) {
        QueryRule queryRule = QueryRule.getInstance();

        if (StringUtils.isNotBlank(contractName)) {
            queryRule.addLike("contractName", contractName);
        }
        if (contractStartDate != null) {
            queryRule.addGreaterEqual("contractDate", contractStartDate);
        }
        if (contractEndDate != null) {
            queryRule.addLessEqual("contractDate", contractEndDate);
        }

        return this.find(Contract.class, queryRule, currentPage, pageNumber);
    }
}
