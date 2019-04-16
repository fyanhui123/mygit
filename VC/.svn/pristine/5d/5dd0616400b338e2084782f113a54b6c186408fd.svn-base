package com.tapi.tcs.vc.store.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcStorageInOut;
import com.tapi.tcs.vc.schema.model.VcStorageInOutDet;

public interface VcStorageInOutDao extends GenericDao<VcStorageInOut> {
    /**
     * 分页查询出入库记录
     * 
     * @param params
     *            查询条件
     * @param pageNo
     *            分页参数
     * @param pageSize
     *            分页参数
     * @return 查询结果
     * @throws DaoException
     *             异常
     */
    public Page queryVcStorageInOutList(VcStorageInOut vcStorageInOut, int pageNo, int pageSize) throws DaoException;

    /**
     * 根据主表id删除明细表记录
     * 
     * @param id
     *            主表id
     * @throws DaoException
     *             异常
     */
    public void deleteDetByVcStorageInOutId(Long id) throws DaoException;

    /**
     * 根据id获取VC_STORAGE_IN_OUT_DET中关联明细
     * 
     * @param vcStorageInOutId
     * @return 明细集合
     * @throws DaoException
     *             异常
     */
    public List<VcStorageInOutDet> getDetListByVcStorageInOutId(Long vcStorageInOutId) throws DaoException;

    /**
     * 获取单证出入库记录信息
     * 
     * @param vcStorageInOutId
     * 
     * @param docVerCode
     *            单证类型代码
     * @return 申领明细对象
     * @throws DaoException
     *             异常
     */
    public VcStorageInOut getVcStorageInOut(Long vcStorageInOutId) throws DaoException;

}
