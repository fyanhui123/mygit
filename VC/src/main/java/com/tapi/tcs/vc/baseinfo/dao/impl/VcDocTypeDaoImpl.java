package com.tapi.tcs.vc.baseinfo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.vc.schema.model.VcDocType;
import com.tapi.tcs.vc.baseinfo.dao.VcDocTypeDao;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;

/**
 * 单证种类维护DAO实现
 * <p>
 * Date: 2013-03-13
 * </p>
 * <p>
 * Module: 单证种类维护
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
public class VcDocTypeDaoImpl extends GenericDaoHibernate<VcDocType> implements VcDocTypeDao {

    /**
     * 根据单证种类ID查询单证种类信息
     */
    public VcDocType getVcDocType(Long idVcDocType) {
        return (VcDocType) super.get(VcDocType.class, idVcDocType);
    }

    /**
     * 根据查询条件查询满足条件的单证种类信息
     */
    // public Page queryVcDocTypes(String contractName, Date contractStartDate,
    // Date contractEndDate, int currentPage, int pageNumber) {
    public Page queryVcDocTypes(VcDocType vcDocType, UserInfo userInfo, int currentPage, int pageNumber)
            throws DaoException {
        QueryRule queryRule = null;
        try {
            queryRule = QueryRule.getInstance();
        } catch (Exception e) {
            throw new DaoException("QueryRule拼接异常!", e);
        }
        try {
            if (vcDocType != null) {
                //单证种类名称（完全匹配查询）
                if (StringUtils.isNotBlank(vcDocType.getDocTypeNameEQ())) {
                    queryRule.addEqual("docTypeName", vcDocType.getDocTypeNameEQ());
                }
                if (StringUtils.isNotBlank(vcDocType.getDocTypeName())) {
                    queryRule.addLike("docTypeName", "%"+vcDocType.getDocTypeName()+"%");
                }
                if (StringUtils.isNotBlank(vcDocType.getDocTypeCode())) {
                    queryRule.addLike("docTypeCode", vcDocType.getDocTypeCode());
                }
                if (StringUtils.isNotBlank(vcDocType.getDocType())) {
                    queryRule.addLike("docType", vcDocType.getDocType());
                }
                if (StringUtils.isNotBlank(vcDocType.getStatus())) {
                    queryRule.addLike("status", vcDocType.getStatus());
                }

            }

            return super.find(VcDocType.class, queryRule, currentPage, pageNumber);
        } catch (Exception e) {
            throw new DaoException("查询数据库失败！");
        }
    }

    /**
     * 根据给定的条件查询
     */
    public List<VcDocType> getDocTypeList(Map<String, Object> map) throws DaoException {
        try {
            String hql = "from VcDocType v where 1=1";
            List<Object> values = new ArrayList<Object>();
            for (String key : map.keySet()) {
                if (map.get(key) != null) {
                    hql = hql + " and v." + key + "  like  ?";
                    values.add("%" + map.get(key) + "%");
                }
            }
            hql=hql+" order by v.idVcDocType asc ";
            return this.findByHql(hql, values.toArray());
        } catch (Exception e) {
            throw new DaoException("查询数据库失败！");
        }
    }
}
