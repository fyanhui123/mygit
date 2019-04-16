package com.tapi.tcs.vc.pub.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.pub.dao.PubBanklocationsDao;
import com.tapi.tcs.vc.schema.model.PubBanklocations;

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
public class PubBanklocationsDaoImpl extends GenericDaoHibernate<PubBanklocations> implements PubBanklocationsDao {

    /**
     * 根据条件分页查询银行支行
     */
    @Override
    public Page queryPubBanklocationsfeByPage(PubBanklocations queryVo, int pageNo,int pageSize) throws DaoException {
        StringBuffer sb = new StringBuffer("from PubBanklocations bb ");
        List<Object> values = new ArrayList<Object>();
        // 有效
        sb.append(" where bb.isactive=? ");
        values.add(1);

        if (queryVo != null) {
            if (StringUtils.isNotBlank(queryVo.getCode())) {
                sb.append(" and bb.code=? ");
                values.add(queryVo.getCode());
            }
            if (StringUtils.isNotBlank(queryVo.getCode())) {
                sb.append(" and bb.name = ?");
                values.add(queryVo.getName());
            }
            //银行编码
            if (StringUtils.isNotBlank(queryVo.getBankCode())) {
                sb.append(" and exists(select pb.urid from PubBanks pb where pb.urid=bb.sybankid and pb.code=?) ");
                values.add(queryVo.getBankCode());
            }
            //区域编码
            if (StringUtils.isNotBlank(queryVo.getAreaCode())) {
                sb.append(" and exists(select area.urid from PubStandardareas area where area.urid=bb.standardareaid and area.code=?) ");
                values.add(queryVo.getAreaCode());
            }
            
            if (StringUtils.isNotBlank(queryVo.getCodeNameLike())) {
                sb.append(" and (bb.code like ? or bb.name like ?) ");
                values.add(queryVo.getCodeNameLike() + "%");
                values.add("%" + queryVo.getCodeNameLike() + "%");
            }
        }

        try {
           //Page page= super.findByHql(sb.toString(), values.toArray());          
            return super.findByHql(sb.toString(),pageNo,pageSize, values.toArray());
        } catch (Exception e) {
            throw new DaoException("数据库查询异常！", e);
        }
    }

}
