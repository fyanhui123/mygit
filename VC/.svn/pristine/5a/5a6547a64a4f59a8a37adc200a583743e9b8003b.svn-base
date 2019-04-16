package com.tapi.tcs.vc.insucard.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.insucard.dao.VcInsuranceCardDao;
import com.tapi.tcs.vc.schema.model.VcInsuranceCard;

/**
 * 激活卡DAO实现
 * <p>
 * Date: 2013-03-13
 * </p>
 * <p>
 * Module: 激活卡导入
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

public class VcInsuranceCardDaoImpl extends GenericDaoHibernate<VcInsuranceCard> implements VcInsuranceCardDao {

    /**
     * 根据ID获取去激活卡信息
     * 
     * @author wanghuajian
     * 
     * @param idVcInsuranceCard
     *            激活卡流水号
     * @return VcInsuranceCard
     */
    public VcInsuranceCard getVcInsuranceCard(Long idVcInsuranceCard) {
        return (VcInsuranceCard) super.get(VcInsuranceCard.class, idVcInsuranceCard);
    }

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
     *            页面条数 *
     * @return
     * @exception DaoException
     *                异常
     */
    public Page queryVcInsuranceCardByPages(VcInsuranceCard vcInsuranceCard, int currentPage, int pageNumber)
            throws DaoException {
        QueryRule queryRule = null;
        queryRule = QueryRule.getInstance();

        if (vcInsuranceCard != null) {
            if (vcInsuranceCard.getIdVcInsuCardImportInfo() != null && vcInsuranceCard.getIdVcInsuCardImportInfo() > -1) {
                queryRule.addEqual("idVcInsuCardImportInfo", vcInsuranceCard.getIdVcInsuCardImportInfo());
            }
            if (StringUtils.isNotBlank(vcInsuranceCard.getDocVerCode())) {
                queryRule.addEqual("docVerCode", vcInsuranceCard.getDocVerCode());
            }
            if (StringUtils.isNotBlank(vcInsuranceCard.getCardNo())) {
                queryRule.addLike("cardNo", vcInsuranceCard.getCardNo() + "%");
            }
            if (vcInsuranceCard.getImportStartTime() != null) {
                queryRule.addGreaterEqual("importTime", vcInsuranceCard.getImportStartTime());
            }
            if (vcInsuranceCard.getImportEndTime() != null) {
                queryRule.addLessEqual("importTime", vcInsuranceCard.getImportEndTime());
            }
        }
        return super.find(VcInsuranceCard.class, queryRule, currentPage, pageNumber);
    }

    /**
     * 根据卡号区间查询激活卡数目
     * 
     * @param startNo
     *            起始卡号
     * @param endNo
     *            中止卡号
     * @param docVerCode
     *            单证code
     * @return Long 数目
     * @exception DaoException
     *                异常
     * 
     */
    public Long queryVcInsuranceCardNumbers(String startNo, String endNo, String docVerCode) throws DaoException {
        Long count = 0L;
        if (StringUtils.isNotBlank(startNo) && StringUtils.isNotBlank(endNo) && StringUtils.isNotBlank(docVerCode)) {
            StringBuffer sb = new StringBuffer();
            List values = new ArrayList();
            sb.append("select count(*) from VcInsuranceCard card where card.docVerCode = ? ");
            values.add(docVerCode);

            sb.append("and card.cardNo between ? and ? ");
            values.add(startNo);
            values.add(endNo);
            List list = this.findByHql(sb.toString(), values.toArray());
            count = (Long) list.get(0);

        }
        return count;
    }

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
    public int updateVcInsuranceCardByCondition(VcInsuranceCard queryDto, String changeToStatus) throws DaoException {
        if (StringUtils.isBlank(changeToStatus)) {
            throw new DaoException("待更新的状态为空！");
        }
        if (queryDto == null || StringUtils.isBlank(queryDto.getStartCardNum())
                || StringUtils.isBlank(queryDto.getEndCardNum())) {
            throw new DaoException("激活卡起始/终止卡号不存在！");
        }
        StringBuffer sb = new StringBuffer("update VcInsuranceCard c ");
        List<Object> values = new ArrayList<Object>();

        sb.append(" set c.cardStatus=?  ");
        values.add(changeToStatus);

        sb.append(" where c.cardNo between ? and ?  ");
        values.add(queryDto.getStartCardNum());
        values.add(queryDto.getEndCardNum());

        if (queryDto != null) {
            if (StringUtils.isNotBlank(queryDto.getDocVerCode())) {
                sb.append(" and c.docVerCode=?  ");
                values.add(queryDto.getDocVerCode());
            }
            if (StringUtils.isNotBlank(queryDto.getCardNo())) {
                sb.append(" and c.cardNo=?  ");
                values.add(queryDto.getCardNo());
            }
        }
        try {
            return super.executeUpdate(sb.toString(), values.toArray());
        } catch (Exception e) {
            throw new DaoException("更新数据异常：" + e.getMessage(), e);
        }

    }

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
    public List<Object> getCardTotalFeeNumByCondition(VcInsuranceCard queryDto, Boolean isOutTime,String[] arrStatus) throws DaoException {
        if (queryDto == null) {
            throw new DaoException("计算总面值及记录数的查询条件为空！");
        }

        StringBuffer sb = new StringBuffer("select sum(c.value),count(c.idVcInsuCardImportInfo) from VcInsuranceCard c where 1=1 ");
        List<Object> values = new ArrayList<Object>();

        // 卡号
        if (StringUtils.isNotBlank(queryDto.getCardNo())) {
            sb.append(" and c.cardNo=?  ");
            values.add(queryDto.getCardNo());
        }
            
            // 起始卡号
            if (StringUtils.isNotBlank(queryDto.getStartCardNum())) {
                sb.append(" and length(c.cardNo)= length(?) ");
                values.add(queryDto.getStartCardNum());
                sb.append(" and c.cardNo>= ? ");
                values.add(queryDto.getStartCardNum());
            }
            if (StringUtils.isNotBlank(queryDto.getEndCardNum())) {
                sb.append(" and length(c.cardNo)= length(?) ");
                values.add(queryDto.getEndCardNum());
                sb.append(" and c.cardNo<= ? ");
                values.add(queryDto.getEndCardNum());
            }
       

        // 激活卡状态
        if (StringUtils.isNotBlank(queryDto.getCardStatus())) {
            sb.append(" and c.cardStatus=?  ");
            values.add(queryDto.getCardStatus());
        }
        // 单证类型
        if (StringUtils.isNotBlank(queryDto.getDocVerCode())) {
            sb.append(" and c.docVerCode=?  ");
            values.add(queryDto.getDocVerCode());
        }
        if(isOutTime !=null){           
            Date nowTime = DateUtils.reset(new Date());
            if(isOutTime){//过期
                sb.append(" and validDate<= ? ");  
            }else{//未过期
                sb.append(" and c.validDate >=?  ");  
            }
            values.add(nowTime);
        }        
        if (arrStatus != null && arrStatus.length > 0) {
            sb.append(" and c.cardStatus in( ");
            for (int i = 0; i < arrStatus.length - 1; i++) {
                sb.append("? ,");
                values.add(arrStatus[i]);
            }
            sb.append("? ) ");
            values.add(arrStatus[arrStatus.length - 1]);
        }

        try {
           List<Object> resultList= new ArrayList<Object>();
            List list = super.findByHql(sb.toString(), values.toArray());
            if (list == null || list.size() < 1 ) {
                resultList.add(0d);
                resultList.add(0L);
                return resultList;
            } else {
                Object[] object = (Object[]) list.get(0);
                //费用
                if(object[0]==null){
                    resultList.add(0d); 
                }else{
                  resultList.add((Double)object[0]);
                }
                //数目
                resultList.add(((Long) object[1]));
                return resultList;
            
            }
        } catch (Exception e) {
            throw new DaoException("更新数据异常：" + e.getMessage(), e);
        }

    }
    
   
}
