package com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.dao;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;

import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;

public class NewInvoiceDoUsedDaoImpl extends GenericDaoHibernate<VcNormalVerification>  implements  NewInvoiceDoUsedDao {

	@Override
	public List<VcAvailableDoc> checkDocNumIsValid(String docNum,
			String versionCode,String pressBatchNo) throws DaoException {
		List<VcAvailableDoc> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//单证类型代码
			queryRule.addEqual("docVerCode", versionCode);
			//单证流水号
			queryRule.addEqual("docNum", docNum);
			//单证状态 A-可用
			queryRule.addEqual("docStatus", "A");
			//印刷批次
			if(StringUtils.isNotEmpty(pressBatchNo)){
				queryRule.addEqual("pressBatchNo", pressBatchNo);
			}
			list = this.find(VcAvailableDoc.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询单证流水号异常！");
		}
		return list;
	}
	
}
