/**
 * 
 */
package com.tapi.tcs.vc.subfunc.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tapi.tcs.vc.schema.model.Contract;
import com.tapi.tcs.vc.schema.model.Printing;
import com.tapi.tcs.vc.schema.model.User;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;

/**
 * @author Lincoln
 * 
 */
@ContextConfiguration(locations = { "classpath:spring/**/*.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
public class GenericDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ContractDao contractDao;

	// @Test
	public void testCreateUser() {
		User user = new User();
		user.setUsername("tf1");
		user.setPassword("tf123");
		user.setName("test1");
		user.setDepartment("SH");

		userDao.save(user);

	}

	@Test
	public void testGetUserByName() {
		User user = userDao.getUserByName("tf1");

		Assert.assertNotNull(user);
		System.out.println("User name is " + user.getName());

	}

	// @Test
	public void testCreateContract() throws IOException {
		BufferedImage image = ImageIO.read(new File("renderer-test.png"));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "png", bos);
		byte[] contractCopy = bos.toByteArray();

		for (int i = 20; i < 21; i++) {
			Contract contract = new Contract();
			contract.setContractNo("DocumentPrintTest" + i);
			contract.setContractName("单证印刷测试" + i);
			contract.setContractDate(new Date());
			contract.setDeptCode("SH");
			contract.setContractCopy(contractCopy);

			Printing printing = new Printing();
			printing.setPrintingCode("PrintingTest" + i);
			printing.setPrintingName("印刷厂测试" + i);
			printing.setTaxNo("TaxNo" + i);
			printing.setContact("李明");
			printing.setAddress("新金桥路600号");
			printing.setTel("51105660");
			printing.setEmail("customer@gamil.com");
			printing.setBankCode("ICBC");
			printing.setBankName("工商银行");
			printing.setBankAccountNo("BackAccountNoTest" + i);
			printing.setBankAccountName("印刷厂测试" + i);

			contract.setPrinting(printing);
			contractDao.save(contract);

		}

	}

	@Test
	public void testGetContract() {
		Contract contract = contractDao.get(10l);

		Assert.assertNotNull(contract);
		System.out.println("Contract name is " + contract.getContractName());
	}

	// @Test
	public void testUpdateContract() {
		Contract contract = contractDao.get(11l);
		contract.setContractName("UpdateTest1");

		contractDao.update(contract);

		System.out.println("Update Contract name is " + contract.getContractName());
	}

	@Test
	public void testQueryContracts() throws ParseException {
		String contractName = "单证印刷测试*";

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date contractStartDate = null;
		Date contractEndDate = format.parse("2013-02-21 12:00:00");

		Page page = contractDao.queryContracts(contractName, contractStartDate, contractEndDate, 1, 10);
		List list = page.getResult();

		Assert.assertEquals(2, list.size());

	}

}
