/**
 * 
 */
package com.tapi.tcs.vc.subfunc.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tapi.tcs.vc.schema.model.Contract;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;

/**
 * @author Lincoln
 * 
 */
@ContextConfiguration(locations = { "classpath:spring/**/*.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
public class ContractServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ContractService contractService;

	//@Test
	public void testUpdateContract() throws BusinessException {
		Contract contract = new Contract();

		contractService.createOrUpdateContract(contract);

	}
	
	@Test
	public void testQueryContracts() throws Exception {
		String contractName = "印刷";

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date contractStartDate = null;
		Date contractEndDate =  format.parse("2013-02-21 12:00:00");

		Page page = contractService.queryContracts(contractName, contractStartDate, contractEndDate, 1, 10);
		List list = page.getResult();

		Assert.assertEquals(2, list.size());

	}

}
