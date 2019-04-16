package com.tapi.tcs.vc.baseinfo.dao.impl;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.baseinfo.dao.VcTakerHisDao;
import com.tapi.tcs.vc.schema.model.VcTakerHis;

public class VcTakerHisDaoImpl extends GenericDaoHibernate<VcTakerHis> implements VcTakerHisDao {

	
    
    /**
     * 保存
     * 
     * @param vcTakerHis
     *            待保存vo
     */
    public void saveVcTakerHis(VcTakerHis vcTakerHis) throws DaoException {
        try {
            super.save(vcTakerHis);
        } catch (Exception e) {
            throw new DaoException("新增出错！", e);
        }

    }
}
