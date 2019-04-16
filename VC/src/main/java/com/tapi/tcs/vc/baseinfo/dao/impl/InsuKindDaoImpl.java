package com.tapi.tcs.vc.baseinfo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.InsuKindDao;
import com.tapi.tcs.vc.schema.model.VcDocInsuKind;

/**
 * 
 * @author Administrator
 * 
 */
public class InsuKindDaoImpl extends GenericDaoHibernate<VcDocInsuKind> implements InsuKindDao {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.template.vcdoc.dao.InsuKindDao#queryInsuKindList(java.lang
     * .Integer, java.lang.String, java.lang.String, int, int)
     */
    public Page queryInsuKindList(Long insuTypeId, String insuKindCode, String insuKindName, int page, int rows)
            throws DaoException {
        QueryRule queryRule = null;
        try {
            queryRule = QueryRule.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("QueryRule拼接异常!", e);
        }
        if (insuTypeId != null) {
            queryRule.addEqual("idVcDocInsuType", insuTypeId);
        }
        if (StringUtils.isNotEmpty(insuKindCode)) {
            queryRule.addEqual("insuKindCode", insuKindCode);
        }

        if (StringUtils.isNotEmpty(insuKindName)) {
            queryRule.addLike("insuKindName", "%" + insuKindName + "%");
        }

        queryRule.addEqual("status", "1");

        return this.find(VcDocInsuKind.class, queryRule, page, rows);
    }

    @SuppressWarnings("unchecked")
    public List<VcDocInsuKind> queryInsuKindListByInsuTypeId(Long insuTypeId) throws DaoException {
        String hql = "from VcDocInsuKind v where v.insuType.id=? and v.status=?";

        Object[] values = new Object[] { insuTypeId, "1" };

        return this.findByHql(hql, values);
    }

    @SuppressWarnings("unchecked")
    public List<VcDocInsuKind> getInsuKindListByEquals(String key, String value) throws DaoException {

        String hql = "from VcDocInsuKind k where k." + key + "=?";

        Object[] values = new Object[] { value };

        return this.findByHql(hql, values);
    }

    /**
     * 根据给定的条件查询
     */
    public List<VcDocInsuKind> getInsuKindList(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer("from VcDocInsuKind v where 1=1 ");
        List<Object> values = new ArrayList<Object>();
        for (String key : map.keySet()) {
            if (map.get(key) != null) {
                if ("insuTypeCode".equals(key)) {
                    sb.append(" and exists(select 1 from VcDocInsuType t where t.idVcDocInsuType=v.idVcDocInsuType ");
                    sb.append(" and t." + key + "=?)");
                    values.add(map.get(key));
                } else {
                    sb.append(" and v." + key + "=?");
                    values.add(map.get(key));
                }
            }
        }
        return this.findByHql(sb.toString(), values.toArray());

    }

    /**
     * 根据险类代码查询险种
     * 
     * @param insuTypeCode
     * @return
     */
    public List<VcDocInsuKind> queryInsuKindListByInsuTypeCode(String insuTypeCode) {
        StringBuffer sb = new StringBuffer(100);
        List<Object> values = new ArrayList<Object>();
        sb.append("from VcDocInsuKind kind where kind.status=?");
        values.add("1");

        if (StringUtils.isNotBlank(insuTypeCode)) {
            sb.append(" and exists( select 1 from VcDocInsuType type ");
            sb.append("where type.idVcDocInsuType=kind.idVcDocInsuType  and type.insuTypeCode=? )");
            values.add(insuTypeCode);
        }
        return this.findByHql(sb.toString(), values.toArray());
    }
}
