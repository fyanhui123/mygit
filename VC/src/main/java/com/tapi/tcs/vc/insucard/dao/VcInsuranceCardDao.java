package com.tapi.tcs.vc.insucard.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcInsuranceCard;

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
public interface VcInsuranceCardDao extends GenericDao<VcInsuranceCard> {

    /**
     * 根据ID获取去激活卡信息
     * 
     * @author wanghuajian
     * 
     * @param idVcInsuranceCard
     *            激活卡流水号
     * @return
     */
    public VcInsuranceCard getVcInsuranceCard(Long idVcInsuranceCard);

   
    /**
     * 查询激活卡信息
     * 
     * @author wanghuanjian
     * 
     * @param vcInsuranceCard
     *            查询条件dto
     * @param currentPage
     *            当前页码
     * @param pageNumber
     *            页面条数
     * @return Page
     * @exception DaoException
     *                异常
     */
    public Page queryVcInsuranceCardByPages(VcInsuranceCard vcInsuranceCard, int currentPage, int pageNumber)
            throws DaoException;

    
    /**
     * 根据卡号区间查询激活卡数目
     * @param startNo 起始卡号
     * @param endNo 中止卡号
     * @param docVerCode 单证code
     * @return Long 数目
     @exception DaoException 异常
     *                
     */
    public Long queryVcInsuranceCardNumbers(String startNo, String endNo, String docVerCode)
            throws DaoException;

    
    /**
     * 根据起始终止卡号区间及其他条件更新激活卡状态（起始终止卡号均不能为空，待更新的状态也不能为空）
     * 
     * @author wanghuanjian
     * 
     * @param queryDto
     *            查询条件dto
     * @param changeToStatus
     *            待更新至的状态
     * 
     * @return int
     * @exception DaoException
     *                异常
     */
    public int updateVcInsuranceCardByCondition(VcInsuranceCard queryDto, String changeToStatus) throws DaoException;
    
    
    /**
     * 根据条件查询激活卡总面值及记录数
     * 
     * @author wanghuanjian
     * 
     * @param queryDto
     *            查询条件dto
     *@param isOutTime 是否过期 true-过期 false-未过期  null-全部
     *@param  arrStatus String[] 状态，空则过滤全部状态
     * @return double
     * @exception DaoException
     *                异常
     */
    public List<Object> getCardTotalFeeNumByCondition(VcInsuranceCard queryDto, Boolean isOutTime,String[] arrStatus) throws DaoException;
}
