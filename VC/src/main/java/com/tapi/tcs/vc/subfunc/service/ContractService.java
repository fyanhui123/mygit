package com.tapi.tcs.vc.subfunc.service;

import java.util.Date;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.Contract;

/**
 * 印刷合同管理Service
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
public interface ContractService {

    /**
     * 创建合同
     * 
     * @author Lincoln
     * 
     * @param contract 合同对象
     * @throws BusinessException
     */
    public void createOrUpdateContract(Contract contract);

    /**
     * 根据ID获取去合同对象
     * @param contractId 合同对象Id
     * @return 合同对象
     */
    public Contract getContract(Long contractId);

    /**
     * 查询合同信息
     * 
     * @author Lincoln
     * 
     * @param contractName 合同名
     * @param contractStartDate 合同期限起期
     * @param contractEndDate 合同期限止期
     * @param currentPage 当前页码
     * @param pageNumber 页面条数
     * @return Page 分页对象
     * @throws BusinessException
     */
    public Page queryContracts(String contractName, Date contractStartDate, Date contractEndDate, int currentPage,
            int pageNumber);

}
