/**
 * 
 */
package com.tapi.tcs.vc.insucard.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.insucard.vo.InsuCardSalesQueryVo;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesDetail;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesRecord;
import com.tapi.tcs.vc.schema.model.VcPayFeeAccount;

/**
 * 激活卡销售service
 * 
 * @author whj
 * 
 */
public interface InsuCardSalesService {

    /**
     * 根据销售记录主表id获取其vo
     * 
     * @param id
     * @return
     */
    public VcInsuCardSalesRecord getVcInsuCardSalesRecord(Long id) throws BusinessException;

    /**
     * 根据销售记录号获取其vo
     * 
     * @param id
     * @return
     */
    public VcInsuCardSalesRecord getVcInsuCardSalesRecordByRecordCode(String salesRecordCode) throws BusinessException;

    /**
     * 根据销售记录明细表id获取其vo
     * 
     * @param id
     * @return
     */
    public VcInsuCardSalesDetail getVcInsuCardSalesDetail(Long id) throws BusinessException;

    /**
     * 查询激活卡销售
     * 
     * @param queryDto
     *            查询条件
     * @param userInfo
     *            当前用户
     * @param pageNo
     *            页号
     * @param pageSize
     *            每页记录数
     * @throws Exception
     */
    public Page queryInsuCardSalesRecordList(InsuCardSalesQueryVo queryDto, UserInfo userInfo, int pageNo, int pageSize)
            throws BusinessException;
    
    /**
     * 激活卡销售、退卡状态修改
     * @param id 待更新记录id
     * @param changeToStatus 记录待修改的状态
     * @return
     * @throws BusinessException
     */
    public String updateSalesRecordStatus(Long id,String changeToStatus)throws BusinessException;

    /**
     * 保存激活卡销售
     * 
     * @param VcInsuCardSalesRecord
     *            激活卡销售信息
     * @param userInfo
     *            当前用户
     *@param type
     *            1-保存 2-提交
     *@param actionType
     *            add-新增 2-修改
     */
    public String saveInsuCardSales(VcInsuCardSalesRecord vcInsuCardSalesRecord, UserInfo userInfo, String type,
            String actionType) throws BusinessException;

    /**
     * 激活卡销售提交
     * 
     * @param vcInsuCardSalesRecord
     * @param userInfo
     * @return
     * @throws BusinessException
     */
    public String executeSubmitInsuCardSales(VcInsuCardSalesRecord vcInsuCardSalesRecord, UserInfo userInfo)
            throws BusinessException;

    /**
     * 缴费处理成功后，变更销售申请状态，变更激活卡状态，减少单证库存，单证状态变更为可使用。 1:将库存中的销售冻结（ST）记录删除并在可使用表中新增 2：更新激活卡的状态 3：更新激活卡销售状态
     * 
     * @param idVcInsuCardSalesRecord
     *            销售记录主键id
     * @param operater
     *            操作用户信息
     * @return
     * @throws BusinessException
     */
    public String executeAfterPayFeeSuccess(Long idVcInsuCardSalesRecord, UserInfo operater) throws BusinessException;

    /**
     * 退费处理成功后，变更回收申请状态，变更激活卡状态，增加库存，单证状态变更为在库存
     * 
     * @param idVcInsuCardSalesRecord
     *            销售记录主键id
     * @param operater
     *            操作用户信息
     * @return
     * @throws BusinessException
     */
    public String executeAfterRefundSuccess(Long idVcInsuCardSalesRecord, UserInfo operater) throws BusinessException;
    
    /**
     * 退费处理失败分两种情况：
     *   1.账户原因引起的失败，直接将退费单状态改为【账户原因退费失败】
     *   2.非账户原因引起的失败， 变更回收申请状态为【退卡失败】，同时将可使用表冻结的卡状态还原，还原激活卡状态为【1-已付费待激活】
     * 
     * @param idVcInsuCardSalesRecord
     *            销售记录主键id
     * @param failReason  退费失败原因代码 (01-账号不存在或账号停用  09-其他非账户原因)
     * @param failDesc 退费失败详述
     * @param operater
     *            操作用户信息
     * @return
     * @throws BusinessException
     */
    public String executeAfterRefundFailed(Long idVcInsuCardSalesRecord,String failReason, String failDesc,UserInfo operater) throws BusinessException;

    /**
     * 激活卡缴费申请撤销(库存为销售冻结状态还原为库存，销售状态改为6销售撤销)
     * 
     * @param vcInsuCardSalesRecord
     * @param userInfo
     * @return
     * @throws BusinessException
     */
    public String executeCancelPayFee(Long idVcInsuCardSalesRecord, UserInfo userInfo) throws BusinessException;

    /**
     * 激活卡申请退款
     * 
     * @param vcInsuCardSalesRecord
     * @param userInfo
     * @return
     * @throws BusinessException
     */
    public String executeSubmitRefund(Long idVcInsuCardSalesRecord, UserInfo userInfo) throws BusinessException;

    /**
     * 保存激活卡退卡
     * 
     * @param VcInsuCardSalesRecord
     *            激活卡退卡信息
     * @param bankInfo
     *            退费人账户信息
     * @param userInfo
     *            当前用户
     *@param type
     *            1-保存 2-提交
     *@param actionType
     *            add-新增 2-修改
     */
    public String saveInsuCardRefund(VcInsuCardSalesRecord vcInsuCardSalesRecord, VcPayFeeAccount bankInfo,
            UserInfo userInfo, String type, String actionType) throws BusinessException;

    /**
     * 修改同步账户信息 1:修改单证系统退卡纪录的账户信息 2:同步更新收付的账户记录信息
     * 
     * @param idVcInsuCardRefundRecord
     * @param bankInfo
     * @param userInfo
     * @return
     * @throws BusinessException
     */
    public String executeAccountModify(Long idVcInsuCardRefundRecord, VcPayFeeAccount bankInfo, UserInfo userInfo)
            throws BusinessException;

}
