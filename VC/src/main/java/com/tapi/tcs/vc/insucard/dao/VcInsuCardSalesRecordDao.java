package com.tapi.tcs.vc.insucard.dao;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.insucard.vo.InsuCardSalesQueryVo;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesRecord;

/**
 * 激活卡销售表DAO接口
 * <p>
 * Date: 2013-06-18
 * </p>
 * <p>
 * Module: 激活卡销售表
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * @author wanghuajian
 * @version 1.0
 */
public interface VcInsuCardSalesRecordDao extends GenericDao<VcInsuCardSalesRecord> {

   
    /**
     * 根据ID获取激活卡销售信息
     * 
     * @author wanghuajian
     * 
     * @param idVcInsuCardSalesRecord
     *            销售信息流水
     * @return VcInsuCardSalesRecord
     */
    public VcInsuCardSalesRecord getVcInsuCardSalesRecord(Long idVcInsuCardSalesRecord);
    
    /**
     * 分页查询激活卡销售list
     * 
     * @param queryDto 查询条件
     * @param userInfo 当前用户
     * @param pageNo 页号
     * @param pageSize 每页记录数
     * @throws Exception
     */   
    public Page queryInsuCardSalesRecordList(InsuCardSalesQueryVo queryDto,UserInfo userInfo, int pageNo, int pageSize) throws DaoException ;
   
    /**
     * 保存激活卡销售及销售明细表信息
     * 
     * @param vcInsuranceCard
     * @return
     * 
     * @author wanghuajian
     */
    public void saveInsuCardSalesRecordAndDet(VcInsuCardSalesRecord vcInsuCardSalesRecord)throws DaoException ;

}
