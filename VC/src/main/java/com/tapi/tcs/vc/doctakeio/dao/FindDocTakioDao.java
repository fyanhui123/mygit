package com.tapi.tcs.vc.doctakeio.dao;

import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.VcDocTakerIo;
public interface FindDocTakioDao extends GenericDao<VcDocTakerIo>{
	/**
	 * 
	 * @param vcDocTakerIo
	 * @param userInfo
	 * @param pageNo
	 * @param pageSize
	 * @return  查询历史发放轨迹
	 * @throws DaoException
	 */
	public Page findDocTakioList(String docVerCode,String docNum,UserInfo userInfo, int pageNo,
            int pageSize,boolean flag) throws DaoException;
}
