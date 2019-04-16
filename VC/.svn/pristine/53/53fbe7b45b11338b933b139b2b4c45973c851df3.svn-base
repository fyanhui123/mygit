package com.tapi.tcs.vc.insucard.dao;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesDetail;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesRecord;

/**
 * 激活卡信息DAO接口
 * <p>
 * Date: 2013-06-18
 * </p>
 * <p>
 * Module: 激活卡信息
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
public interface VcInsuCardSalesDetailDao extends GenericDao<VcInsuCardSalesDetail> {

    /**
     * 根据ID获取去激活卡信息
     * 
     * @author wanghuajian
     * 
     * @param idVcInsuCardSalesDetail
     *            激活卡流水号
     * @return
     */
    public VcInsuCardSalesDetail getVcInsuCardSalesDetail(Long idVcInsuCardSalesDetail);

   
    /**
     * 分页查询激活卡销售子表list
     * 
     * @param queryMainDto
     *            销售主表查询条件
     * @param queryDetDto
     *            销售子表查询条件
     * @param userInfo
     *            当前用户
     * @param pageNo
     *            页号
     * @param pageSize
     *            每页记录数
     * @throws Exception
     */
    public Page queryVcInsuCardSalesDetailByPages(VcInsuCardSalesRecord queryMainDto, VcInsuCardSalesDetail queryDetDto, UserInfo userInfo, int pageNo,
            int pageSize) throws DaoException;

}
