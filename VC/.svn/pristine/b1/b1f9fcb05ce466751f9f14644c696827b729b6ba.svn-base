package com.tapi.tcs.vc.baseinfo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.vc.schema.model.VcPrintDocVersion;
import com.tapi.tcs.vc.baseinfo.dao.VcPrintDocVersionDao;
import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;

/**
 * 承印单证版本DAO实现
 * <p>
 * Date: 2013-03-21
 * </p>
 * <p>
 * Module: 承印单证版本
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
public class VcPrintDocVersionDaoImpl extends GenericDaoHibernate<VcPrintDocVersion> implements
        VcPrintDocVersionDao {

    /**
     * 根据承印单证版本ID查询承印单证版本信息
     */
    public VcPrintDocVersion getVcPrintDocVersion(Long idVcPrintDocVersion) {
        return (VcPrintDocVersion) super.get(VcPrintDocVersion.class, idVcPrintDocVersion);
    }

    /**
     * 根据查询条件查询满足条件的承印单证版本信息
     */
    public Page queryVcPrintDocVersions(VcPrintDocVersion vcPrintDocVersion, int currentPage, int pageNumber)
            throws DaoException {
        QueryRule queryRule = null;
        try {
            queryRule = QueryRule.getInstance();
        } catch (Exception e) {
            throw new DaoException("QueryRule拼接异常!", e);
        }

        if (vcPrintDocVersion != null) {
            /*
             * if (StringUtils.isNotBlank(vcPrintDocVersion.getDocName())) { queryRule.addLike("docName",
             * vcPrintDocVersion.getDocName()); }
             */
            if (StringUtils.isNotBlank(vcPrintDocVersion.getDocVerCode())) {
                queryRule.addLike("docVerCode", vcPrintDocVersion.getDocVerCode());
            }
            /*
             * if (vcPrintDocVersion.getIdVcDocVersionInfo()!=null) { queryRule.addEqual("idVcDocVersionInfo",
             * vcPrintDocVersion.getIdVcDocVersionInfo()); }
             */
            if (vcPrintDocVersion.getIdVcPrintery() != null) {
                queryRule.addEqual("idVcPrintery", vcPrintDocVersion.getIdVcPrintery());
            }

            if (vcPrintDocVersion.getUnitPrice() > 0) {
                queryRule.addEqual("unitPrice", vcPrintDocVersion.getUnitPrice());
            }
        }
        try {
            return super.find(VcPrintDocVersion.class, queryRule, currentPage, pageNumber);
        } catch (Exception e) {
            throw new DaoException("查询数据出错！", e);
        }
    }

    /**
     * 根据条件删除承印单证版本信息
     * 
     * @param conditionDto
     * @return
     * @throws Exception
     * @author wanghuajian since 2013-4-15下午04:40:55
     */
    public int deleteByConditions(VcPrintDocVersion conditionDto) throws DaoException {
        try {
            if (conditionDto != null && conditionDto.getIdVcPrintery() != null) {
                StringBuffer sb = new StringBuffer("delete  from VcPrintDocVersion v where 1=1 ");
                List<Object> values = new ArrayList<Object>();
                // 拼接订单号查询条件
                if (conditionDto.getIdVcPrintery() != null) {
                    sb.append(" and v.idVcPrintery = ?");
                    values.add(conditionDto.getIdVcPrintery());
                }
                return this.executeUpdate(sb.toString(), values.toArray());
            }
        } catch (Exception e) {
            throw new DaoException("数据删除失败！", e);
        }
        return 0;

    }

    /**
     * 根据印刷厂主键查找承印List
     * 
     * @param printeryId
     * @return vcPrintDocVersionList
     * @throws DaoException
     * @author chy
     * @date 2013-05-20
     */
    public List<VcPrintDocVersion> findVcPrintDocVersionList(Long printeryId) throws DaoException {
        List<VcPrintDocVersion> vcPrintDocVersionList = null;
        try {
            QueryRule queryRule = QueryRule.getInstance();
            queryRule.addEqual("idVcPrintery", printeryId);
            vcPrintDocVersionList = this.find(VcPrintDocVersion.class, queryRule);
        } catch (Exception e) {
            throw new DaoException("查询数据出错！", e);
        }
        return vcPrintDocVersionList;
    }

}
