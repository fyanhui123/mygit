package com.tapi.tcs.vc.common.dao.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.common.dao.AllotCodeNo;
import com.tapi.tcs.vc.schema.model.VcAllot;
public class AllotCodeNoImpl extends GenericDaoHibernate<VcAllot> implements AllotCodeNo{
	public String getAllotNo(String bizType) throws DaoException {
		StringBuffer bizNo = new StringBuffer("");
		//格式化时间yyyyMMdd
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//格式化流水号为10位
		DecimalFormat df = new DecimalFormat("0");
		df.setMinimumIntegerDigits(10);
		
		//当前日期
		String today = sdf.format(new Date());
		//从sequence取值
		String sql = "select SEQ_VC_ALLOT.nextval nextvalue from dual";
		BigDecimal no = (BigDecimal)this.findBySql(sql,null).get(0);
		bizNo.append(bizType);
		bizNo.append(today);
		bizNo.append(df.format(no));
		return bizNo.toString();
	}
}
