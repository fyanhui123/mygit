package com.tapi.tcs.vc.common.service;

/**
 * 取号Service接口
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
public interface TakeNoService {
	/**根据业务类型生成业务号*/
	public String getNo(String bizType) throws Exception;
}
