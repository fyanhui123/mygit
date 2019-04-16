package com.tapi.tcs.vc.insucard.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.insucard.dao.VcInsuCardSalesRecordDao;
import com.tapi.tcs.vc.insucard.vo.InsuCardSalesQueryVo;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesRecord;

/**
 * 激活卡销售表DAO实现
 * <p>
 * Date: 2013-06-27
 * </p>
 * <p>
 * Module: 激活卡销售
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

public class VcInsuCardSalesRecordDaoImpl extends GenericDaoHibernate<VcInsuCardSalesRecord> implements
        VcInsuCardSalesRecordDao {

    /**
     * 根据ID获取激活卡销售信息
     * 
     * @author wanghuajian
     * 
     * @param idVcInsuCardSalesRecord
     *            激活卡销售流水
     * @return VcInsuCardSalesRecord
     */
    public VcInsuCardSalesRecord getVcInsuCardSalesRecord(Long idVcInsuCardSalesRecord) {
        return (VcInsuCardSalesRecord) super.get(VcInsuCardSalesRecord.class, idVcInsuCardSalesRecord);
    }

    /**
     * 保存激活卡销售信息及其激活卡信息
     * 
     * @param vcInsuCardSalesRecord
     * 
     * 
     * @author wanghuajian
     */
    public void saveInsuCardSalesRecordAndDet(VcInsuCardSalesRecord vcInsuCardSalesRecord)throws DaoException {
        try{
        super.save(vcInsuCardSalesRecord);
        } catch (Exception e) {
            throw new DaoException("数据保存异常：" + e.getMessage(), e);
        }
    }

    /**
     * 分页查询激活卡销售list
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
    public Page queryInsuCardSalesRecordList(InsuCardSalesQueryVo queryDto, UserInfo userInfo, int pageNo,
            int pageSize) throws DaoException {

        StringBuffer sb = new StringBuffer("from VcInsuCardSalesRecord r where 1=1 ");
        List<Object> values = new ArrayList<Object>();
        if (queryDto != null) {
            if (StringUtils.isNotBlank(queryDto.getOprType())) {
                //销售
                if ("P".equals(queryDto.getOprType())){
                    sb.append("and r.saleRefundFlag = ? ");
                    values.add("P");
                }
               //回收     
              if ("R".equals(queryDto.getOprType())){
                  sb.append("and r.saleRefundFlag = ? ");
                  values.add("R");  
                }
            }
            if (queryDto.getIdVcInsuCardSalesRecord() != null && queryDto.getIdVcInsuCardSalesRecord() > -1) {
                sb.append("and r.idVcInsuCardSalesRecord = ? ");
                values.add(queryDto.getIdVcInsuCardSalesRecord());
            }
            if (StringUtils.isNotBlank(queryDto.getSaleOrgCode())) {
                sb.append("and r.saleOrgCode = ? ");
                values.add(queryDto.getSaleOrgCode());
            }
            if (StringUtils.isNotBlank(queryDto.getSellerCode())) {
                sb.append("and r.sellerCode = ? ");
                values.add(queryDto.getSellerCode());
            }
            if (StringUtils.isNotBlank(queryDto.getSalesRecordCode())) {

                sb.append("and r.salesRecordCode like ? ");
                values.add(queryDto.getSalesRecordCode() + "%");
            }
            if (StringUtils.isNotBlank(queryDto.getSalesRecordStatus())) {

                sb.append("and r.salesRecordStatus = ? ");
                values.add(queryDto.getSalesRecordStatus());
            }
            if (queryDto.getSaleStartDate() != null) {
                Date startDate=DateUtils.reset(queryDto.getSaleStartDate());
                sb.append("and r.saleDate >= ? ");
                values.add(startDate);
            }
            if (queryDto.getSaleEndDate() != null) {
                Date endDate=DateUtils.reset(DateUtils.addDay(queryDto.getSaleEndDate(),1));
                sb.append("and r.saleDate <= ? ");
                values.add(endDate);
            }

            // 子表条件
            if (StringUtils.isNotBlank(queryDto.getDocVerCode())
                    || StringUtils.isNotBlank(queryDto.getPressBatchNo())) {
                sb.append("and exists (select det.idVcInsuCardSalesDetail from VcInsuCardSalesDetail det ");
                sb.append(" where det.idVcInsuCardSalesRecord=r.idVcInsuCardSalesRecord ");
                if (StringUtils.isNotBlank(queryDto.getDocVerCode())) {
                    sb.append(" and det.docVerCode=?  ");
                    values.add(queryDto.getDocVerCode());
                }
                if (StringUtils.isNotBlank(queryDto.getPressBatchNo())) {
                    sb.append(" and det.pressBatchNo=?  ");
                    values.add(queryDto.getPressBatchNo());
                }

                sb.append(") ");
            }

        }
        //根据当前机构查询
        if (userInfo!= null && StringUtils.isNotBlank(userInfo.getComCode())) {
            sb.append("and r.provideOrgCode like ? ");
            values.add(userInfo.getComCode() + "%");
        }
        
        sb.append(" order by r.dateCreated desc ");
        try {
            return super.findByHql(sb.toString(), pageNo, pageSize, values.toArray());
        } catch (Exception e) {
            throw new DaoException("数据库查询异常：" + e.getMessage(), e);
        }
    }

}
