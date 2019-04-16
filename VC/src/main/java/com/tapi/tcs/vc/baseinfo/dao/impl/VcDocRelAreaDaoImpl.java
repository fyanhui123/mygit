package com.tapi.tcs.vc.baseinfo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.vc.schema.model.VcDocInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;
import com.tapi.tcs.vc.schema.model.VcDocRelArea;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.baseinfo.dao.VcDocRelAreaDao;
import com.tapi.tcs.vc.baseinfo.vo.DocInsuKindVo;
import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.tf.common.utils.bean.TFBeanUtils;

/**
 * 单证地区关联DAO实现
 * <p>
 * Date: 2013-03-13
 * </p>
 * <p>
 * Module: 单证地区关联
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
public class VcDocRelAreaDaoImpl extends GenericDaoHibernate<VcDocRelArea> implements VcDocRelAreaDao {

    /**
     * 根据印刷厂ID查询印刷厂信息
     */
    public VcDocRelArea getVcDocRelArea(Long idVcDocRelArea) {
        return (VcDocRelArea) super.get(VcDocRelArea.class, idVcDocRelArea);
    }

    /**
     * 根据查询条件查询满足条件的印刷厂信息
     */
    public Page queryVcDocRelAreas(VcDocRelArea vcDocRelArea, int currentPage, int pageNumber) throws DaoException {
        QueryRule queryRule = null;
        try {
            queryRule = QueryRule.getInstance();
        } catch (Exception e) {
            throw new DaoException("QueryRule拼接异常!", e);
        }

        if (vcDocRelArea != null) {
            /*
             * if (StringUtils.isNotBlank(vcDocRelArea.getDocVerCode())) {
             * queryRule.addLike("docVerCode", vcDocRelArea.getDocVerCode()); }
             */
            if (vcDocRelArea.getIdVcDocVersionInfo() != null) {
                queryRule.addEqual("idVcDocVersionInfo", vcDocRelArea.getIdVcDocVersionInfo());
            }
            if (StringUtils.isNotBlank(vcDocRelArea.getOrgCode())) {
                queryRule.addLike("orgCode", vcDocRelArea.getOrgCode());
            }
        }

        return super.find(VcDocRelArea.class, queryRule, currentPage, pageNumber);
    }

    /**
     * 根据单证类型流水查找关联地区
     * 
     * @param idVcDocVersionInfo
     * @return
     * @throws Exception
     * @author wanghuajian
     * @date 2013-04-27
     */
    public List<VcLevel> queryRelAreaListByDocId(Long idVcDocVersionInfo) throws DaoException {
        // 有可能关联地区表获取地区名称等信息
        StringBuffer sb = new StringBuffer(100);
        List params = new ArrayList();
        sb.append("from VcLevel level ");
        sb.append(" where exists");
        sb.append("( select 1 from VcDocRelArea rel  where rel.orgCode=level.unitCode and rel.idVcDocVersionInfo= ? )");
        params.add(idVcDocVersionInfo);
        List<VcLevel> list = this.findByHql(sb.toString(), params.toArray());
        return list;
    }

    /**
     * 根据单证类型流水删除地区关联信息
     * 
     * @param idVcDocVersionInfo
     *            单证类型流水
     * @return
     * @throws Exception
     * @author wanghuajian since 2013-6-5
     */
    public int deleteByDocVersionInfoId(Long idVcDocVersionInfo) throws DaoException {
        if (idVcDocVersionInfo != null) {
            StringBuffer sb = new StringBuffer("delete  from VcDocRelArea rel where 1=1 ");
            List<Object> values = new ArrayList<Object>();
            // 拼接单证流水号
            sb.append(" and rel.idVcDocVersionInfo = ?");
            values.add(idVcDocVersionInfo);

            return this.executeUpdate(sb.toString(), values.toArray());
        }
        return 0;
    }
}
