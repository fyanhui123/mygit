package com.tapi.tcs.vc.insucard.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.insucard.dao.VcInsuCardSalesDetailDao;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesDetail;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesRecord;

/**
 * 激活卡销售明细信息DAO实现
 * <p>
 * Date: 2013-03-13
 * </p>
 * <p>
 * Module: 激活卡销售明细信息导入
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

public class VcInsuCardSalesDetailDaoImpl extends GenericDaoHibernate<VcInsuCardSalesDetail> implements
        VcInsuCardSalesDetailDao {

    /**
     * 根据ID获取去激活卡销售明细信息信息
     * 
     * @author wanghuajian
     * 
     * @param idVcInsuCardSalesDetail
     *            激活卡销售明细信息流水号
     * @return VcInsuCardSalesDetail
     */
    public VcInsuCardSalesDetail getVcInsuCardSalesDetail(Long idVcInsuCardSalesDetail) {
        return (VcInsuCardSalesDetail) super.get(VcInsuCardSalesDetail.class, idVcInsuCardSalesDetail);
    }

    /**
     * 查询激活卡销售明细信息信息
     * 
     * @author wanghuanjian
     * 
     * @param queryDetDto
     *            查询条件dto
     * @param currentPage
     *            当前页码
     * @param pageNumber
     *            页面条数 *
     * @return
     * @exception DaoException
     *                异常
     */
    public Page queryVcInsuCardSalesDetailByPages_1(VcInsuCardSalesRecord queryMainDto,
            VcInsuCardSalesDetail queryDetDto, int currentPage, int pageNumber) throws DaoException {
        QueryRule queryRule = null;
        queryRule = QueryRule.getInstance();

        if (queryDetDto != null) {
            if (queryDetDto.getIdVcInsuCardSalesRecord() != null
                    && queryDetDto.getIdVcInsuCardSalesRecord() > -1) {
                queryRule.addEqual("idVcInsuCardSalesRecord", queryDetDto.getIdVcInsuCardSalesRecord());
            }
            if (StringUtils.isNotBlank(queryDetDto.getDocVerCode())) {
                queryRule.addEqual("docVerCode", queryDetDto.getDocVerCode());
            }
            /*
             * if (StringUtils.isNotBlank(vcInsuCardSalesDetail.getCardNo())) { queryRule.addLike("cardNo",
             * vcInsuCardSalesDetail.getCardNo() + "%"); } if (vcInsuCardSalesDetail.getImportStartTime() != null) {
             * queryRule.addGreaterEqual("importTime", vcInsuCardSalesDetail.getImportStartTime()); } if
             * (vcInsuCardSalesDetail.getImportEndTime() != null) { queryRule.addLessEqual("importTime",
             * vcInsuCardSalesDetail.getImportEndTime()); }
             */
        }
        return super.find(VcInsuCardSalesDetail.class, queryRule, currentPage, pageNumber);
    }

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
    public Page queryVcInsuCardSalesDetailByPages(VcInsuCardSalesRecord queryMainDto,
            VcInsuCardSalesDetail queryDetDto, UserInfo userInfo, int pageNo, int pageSize)
            throws DaoException {

        QueryRule queryRule = null;
        try {
            queryRule = QueryRule.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("QueryRule拼接异常!", e);
        }
        // 销售详细表条件
        if (queryDetDto != null) {
            if (queryDetDto.getIdVcInsuCardSalesRecord() != null
                    && queryDetDto.getIdVcInsuCardSalesRecord() > -1) {
                queryRule.addEqual("idVcInsuCardSalesRecord", queryDetDto.getIdVcInsuCardSalesRecord());
            }
            if (StringUtils.isNotBlank(queryDetDto.getDocVerCode())) {
                queryRule.addEqual("docVerCode", queryDetDto.getDocVerCode());
            }
            if (StringUtils.isNotBlank(queryDetDto.getPressBatchNo())) {
                queryRule.addEqual("pressBatchNo", queryDetDto.getPressBatchNo());
            }
        }

        // 销售主表条件
        if (queryMainDto != null) {
            QueryRule subQueryRule = queryRule.addSubQueryRule("vcInsuCardSalesRecord");
            if (queryMainDto.getIdVcInsuCardSalesRecord() != null
                    && queryMainDto.getIdVcInsuCardSalesRecord() > -1) {
                subQueryRule.addEqual("idVcInsuCardSalesRecord", queryMainDto.getIdVcInsuCardSalesRecord());
            }
            if (StringUtils.isNotBlank(queryMainDto.getSaleOrgCode())) {
                subQueryRule.addEqual("saleOrgCode", queryMainDto.getSaleOrgCode());
            }
            if (StringUtils.isNotBlank(queryMainDto.getSellerCode())) {
                subQueryRule.addEqual("sellerCode", queryMainDto.getSellerCode());
            }
            if (StringUtils.isNotBlank(queryMainDto.getSalesRecordCode())) {
                subQueryRule.addLike("salesRecordCode", queryMainDto.getSalesRecordCode() + "%");
            }
            if (StringUtils.isNotBlank(queryMainDto.getSalesRecordStatus())) {
                subQueryRule.addEqual("salesRecordStatus", queryMainDto.getSalesRecordStatus());
            }
            if (queryMainDto.getSaleStartDate() != null) {
                subQueryRule.addGreaterEqual("saleDate", queryMainDto.getSaleStartDate());
            }
            if (queryMainDto.getSaleEndDate() != null) {
                subQueryRule.addLessEqual("saleDate", queryMainDto.getSaleEndDate());
            }
        }
        try {
            return super.find(VcInsuCardSalesDetail.class, queryRule, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("数据库查询异常!", e);
        }
    }

}
