package com.tapi.tcs.vc.pub.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.pub.dao.PubStandardareasDao;
import com.tapi.tcs.vc.schema.model.PubStandardareas;

/**
 * 银行区域DAO实现类
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
public class PubStandardareasDaoImpl extends GenericDaoHibernate<PubStandardareas> implements PubStandardareasDao {

    /**
     * 根据条件查询区域list
     */
    @Override
    public List<PubStandardareas> queryPubStandardareasList(PubStandardareas queryVo) throws DaoException {
        StringBuffer sb = new StringBuffer("from PubStandardareas bb ");
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
            if (StringUtils.isNotBlank(queryVo.getCodeNameLike())) {
                sb.append(" and (bb.code like ? or bb.name like ?) ");
                values.add(queryVo.getCodeNameLike() + "%");
                values.add("%" + queryVo.getCodeNameLike() + "%");
            }
        }

        try {
            return super.findByHql(sb.toString(), values.toArray());
        } catch (Exception e) {
            throw new DaoException("数据库查询异常！", e);
        }
    }

}
