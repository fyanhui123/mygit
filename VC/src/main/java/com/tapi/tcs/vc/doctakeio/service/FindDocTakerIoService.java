package com.tapi.tcs.vc.doctakeio.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.VcDocTakerIo;

public interface FindDocTakerIoService {
	/**
	 * 
	 * @param docVerCode
	 * @param userInfo
	 * @param pageNo
	 * @param pageSize
	 * @param flag   --区分是导出还是查询（导出是查询结果全部导出）
	 * @return
	 * @throws BusinessException
	 */
	public Page queryVcDocTakerIoListByPages(String  docVerCode,String docNum,UserInfo userInfo,
			int pageNo, int pageSize,boolean flag)  throws BusinessException;
}
