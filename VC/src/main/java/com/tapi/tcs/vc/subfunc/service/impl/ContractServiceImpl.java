package com.tapi.tcs.vc.subfunc.service.impl;

import java.util.Date;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.Contract;
import com.tapi.tcs.vc.subfunc.dao.ContractDao;
import com.tapi.tcs.vc.subfunc.service.ContractService;

/**
 * 【类或接口功能描述】
 * 
 * @author
 * @date 2013-4-18
 * @version 1.0
 */
public class ContractServiceImpl implements ContractService {

    /**
     * 印刷合同dao
     */
    private ContractDao contractDao;
    
    /**
     * @param contractDao the contractDao to set
     */
    public void setContractDao(ContractDao contractDao) {
    
        this.contractDao = contractDao;
    }

    /**
     * 创建更新合同
     * @param contract 合同对象
     */
    public void createOrUpdateContract(Contract contract) {
        contractDao.save(contract);
    }

    /**
     * 根据ID获取去合同对象
     * @param contractId 合同对象Id
     * @return 合同对象
     */
    public Contract getContract(Long contractId) {

        return contractDao.getContract(contractId);
    }

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
            int pageNumber) {

        return contractDao.queryContracts(contractName, contractStartDate, contractEndDate, currentPage, pageNumber);
    }

}
