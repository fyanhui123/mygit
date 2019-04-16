package com.tapi.tcs.vc.pub.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.pub.dao.PubRiskDao;
import com.tapi.tcs.vc.schema.model.PubRisk;
import com.tapi.tcs.vc.schema.model.PubRiskClass;

/**
 * 险类DAO实现类
 * <p>
 * Date 2014-03-20
 * </p>
 * <p>
 * Module：公共模块
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * 
 * @author whj
 * @version 1.0
 */
public class PubRiskDaoImpl extends GenericDaoHibernate<PubRisk> implements PubRiskDao {

    /**
     * 根据条件查询险种list
     */
    @Override
    public List<PubRisk> queryPubRiskList(PubRisk pubRiskVo) throws DaoException {
        StringBuffer sb = new StringBuffer("from PubRisk bb ");
        List<Object> values = new ArrayList<Object>();
        // 有效
        sb.append(" where bb.validInd=? ");
        values.add("1");

        if (pubRiskVo != null) {
            if (StringUtils.isNotBlank(pubRiskVo.getRiskCode())) {
                sb.append(" and bb.riskCode=? ");
                values.add(pubRiskVo.getRiskCode());
            }
            if (StringUtils.isNotBlank(pubRiskVo.getRiskCodeLike())) {
                sb.append(" and bb.riskCode like ? ");
                values.add("%"+pubRiskVo.getRiskCode()+"%");
            }
            if (StringUtils.isNotBlank(pubRiskVo.getRiskCname())) {
                sb.append(" and bb.riskCname = ?");
                values.add(pubRiskVo.getRiskCname());
            }
            if (StringUtils.isNotBlank(pubRiskVo.getRiskClass())) {
                sb.append(" and bb.riskClass = ?");
                values.add(pubRiskVo.getRiskClass());
            }
        }
        sb.append(" order by bb.riskCode asc ");
        try {
            return super.findByHql(sb.toString(), values.toArray());
        } catch (Exception e) {
            throw new DaoException("数据库查询异常！", e);
        }
    }
    
    /**
     * 根据条件查询险种list
     */
    @Override
    public List<PubRiskClass> queryPubRiskClassList(PubRiskClass pubRiskClassVo) throws DaoException {
        StringBuffer sb = new StringBuffer("from PubRiskClass bb ");
        List<Object> values = new ArrayList<Object>();
        // 有效
        sb.append(" where bb.validInd=? ");
        values.add("1");

        if (pubRiskClassVo != null) {            
            if (StringUtils.isNotBlank(pubRiskClassVo.getClassCname())) {
                sb.append(" and bb.classCname = ?");
                values.add(pubRiskClassVo.getClassCname());
            }
            if (StringUtils.isNotBlank(pubRiskClassVo.getRiskClass())) {
                sb.append(" and bb.riskClass = ?");
                values.add(pubRiskClassVo.getRiskClass());
            }
            if (StringUtils.isNotBlank(pubRiskClassVo.getRiskClassLike())) {
                sb.append(" and bb.riskClass  like  ?");
                values.add("%"+pubRiskClassVo.getRiskClass()+"%");
            }
        }
        sb.append(" order by bb.riskClass asc ");
        try {
            return super.findByHql(sb.toString(), values.toArray());
        } catch (Exception e) {
            throw new DaoException("数据库查询异常！", e);
        }
    }

}
