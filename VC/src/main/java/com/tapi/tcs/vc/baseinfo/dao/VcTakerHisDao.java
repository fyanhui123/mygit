package com.tapi.tcs.vc.baseinfo.dao;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcTakerHis;

public interface VcTakerHisDao extends GenericDao<VcTakerHis> {
	
    /**
     * 保存
     * 
     * @param vcTakerHis
     *            待保存vo
     */
    public void saveVcTakerHis(VcTakerHis vcTakerHis) throws DaoException;
}
