package com.tapi.tcs.vc.common.dao.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.common.dao.TakeNoDao;
import com.tapi.tcs.vc.schema.model.VcApprove;

/**
 * 取号DAO实现类
 * <p>
 * Date 2013-04-20
 * </p>
 * <p>
 * Module：公共模块
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
public class TakeNoDaoImpl extends GenericDaoHibernate<VcApprove> implements TakeNoDao {

	/**
	 * 根据业务类型生成业务号
	 * 业务类型2位 + YYYYMMDD + 10位流水号
	 * @param bizType
	 * @return bizNo
	 */
	public String getNo(String bizType) throws DaoException{
		try{
		StringBuffer bizNo = new StringBuffer("");
		//格式化时间yyyyMMdd
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//格式化流水号为10位
		DecimalFormat df = new DecimalFormat("0");
		df.setMinimumIntegerDigits(10);
		
		//当前日期
		String today = sdf.format(new Date());
		//从sequence取值
		String sql = "select SEQ_VC_TAKEBIZNO.nextval nextvalue from dual";
		BigDecimal no = (BigDecimal)this.findBySql(sql,null).get(0);
		bizNo.append(bizType);
		bizNo.append(today);
		bizNo.append(df.format(no));
		return bizNo.toString();
		}
		catch(Exception  e){
    		throw new DaoException("添加数据库异常！",e);
    	}
	}
}
