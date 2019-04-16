package com.tapi.tcs.vc.store.dao;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcDocTakerIo;

public interface VcDocTakerIoDao extends GenericDao<VcDocTakerIo> {
	
	public Page findVcDocTakerIos(VcDocTakerIo vcDocTakerIo, int pageNo,
			int pageSize) throws DaoException;
}
