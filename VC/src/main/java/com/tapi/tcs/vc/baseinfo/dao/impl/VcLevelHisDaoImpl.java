package com.tapi.tcs.vc.baseinfo.dao.impl;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelHisDao;
import com.tapi.tcs.vc.schema.model.VcLevelHis;

public class VcLevelHisDaoImpl extends GenericDaoHibernate<VcLevelHis> implements VcLevelHisDao {
    /**
     * 保存
     * 
     * @param vcTakerHis
     *            待保存vo
     */
    public void saveVcLevelHis(VcLevelHis vcLevelHis) throws DaoException {
        try {
            super.save(vcLevelHis);
        } catch (Exception e) {
            throw new DaoException("新增出错！", e);
        }

    }
}
