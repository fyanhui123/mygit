package com.tapi.tcs.vc.doctakeio.service.impl;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.doctakeio.dao.FindDocTakioDao;
import com.tapi.tcs.vc.doctakeio.service.FindDocTakerIoService;
public class FindDocTakerIoServiceImpl   implements FindDocTakerIoService{
		public FindDocTakioDao findDocTakioDao;
	@Override
	public Page queryVcDocTakerIoListByPages(String  docVerCode,String docNum,
			UserInfo userInfo, int pageNo, int pageSize,boolean flag)
			throws BusinessException {
		try {
			return findDocTakioDao.findDocTakioList(docVerCode, docNum, userInfo, pageNo, pageSize,flag);
		} catch (Exception e) {
			 throw new BusinessException("查询数据库异常！", e);
		}	
	}
	public void setFindDocTakioDao(FindDocTakioDao findDocTakioDao) {
		this.findDocTakioDao = findDocTakioDao;
	}
}
