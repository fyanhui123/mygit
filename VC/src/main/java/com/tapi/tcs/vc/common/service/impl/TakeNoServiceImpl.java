package com.tapi.tcs.vc.common.service.impl;

import com.tapi.tcs.vc.common.dao.TakeNoDao;
import com.tapi.tcs.vc.common.service.TakeNoService;

/**
 * 取号service实现类
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
public class TakeNoServiceImpl implements TakeNoService {
	private TakeNoDao takeNoDao;

	/**
	 * 根据业务类型生成业务号
	 */
	public String getNo(String bizType) throws Exception {
		return takeNoDao.getNo(bizType);
	}
	
	public TakeNoDao getTakeNoDao() {
		return takeNoDao;
	}

	public void setTakeNoDao(TakeNoDao takeNoDao) {
		this.takeNoDao = takeNoDao;
	}
}
