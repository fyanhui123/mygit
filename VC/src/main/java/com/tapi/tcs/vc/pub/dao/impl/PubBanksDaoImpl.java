package com.tapi.tcs.vc.pub.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.pub.dao.PubBanksDao;
import com.tapi.tcs.vc.schema.model.PubBanks;

/**
 * 银行DAO实现类
 * <p>
 * Date 2013-03-20
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
public class PubBanksDaoImpl extends GenericDaoHibernate<PubBanks> implements PubBanksDao {

    /**
     * 根据条件查询银行list
     */
    @Override
    public List<PubBanks> queryPubBanksList(PubBanks banksVo) throws DaoException {
        StringBuffer sb = new StringBuffer("from PubBanks bb ");
        List<Object> values = new ArrayList<Object>();
        // 有效
        sb.append(" where bb.isactive=? ");
        values.add(1);

        if (banksVo != null) {
            if (StringUtils.isNotBlank(banksVo.getCode())) {
                sb.append(" and bb.code=? ");
                values.add(banksVo.getCode());
            }
            if (StringUtils.isNotBlank(banksVo.getCode())) {
                sb.append(" and bb.name = ?");
                values.add(banksVo.getName());
            }
            if (StringUtils.isNotBlank(banksVo.getCodeNameLike())) {
                sb.append(" and (bb.code like ? or bb.name like ?) ");
                values.add(banksVo.getCodeNameLike() + "%");
                values.add("%" + banksVo.getCodeNameLike() + "%");
            }
        }

        try {
            return super.findByHql(sb.toString(), values.toArray());
        } catch (Exception e) {
            throw new DaoException("数据库查询异常！", e);
        }
    }

}
