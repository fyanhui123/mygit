package com.tapi.tcs.vc.baseinfo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.vc.schema.model.VcDocInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;
import com.tapi.tcs.vc.schema.model.VcDocRelInsuKind;
import com.tapi.tcs.vc.baseinfo.dao.VcDocRelInsuKindDao;
import com.tapi.tcs.vc.baseinfo.vo.DocInsuKindVo;
import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.tf.common.utils.bean.TFBeanUtils;

/**
 * 单证险种关联DAO实现
 * <p>
 * Date: 2013-03-13
 * </p>
 * <p>
 * Module: 单证险种关联
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
public class VcDocRelInsuKindDaoImpl extends GenericDaoHibernate<VcDocRelInsuKind> implements
        VcDocRelInsuKindDao {

    /**
     * 根据印刷厂ID查询印刷厂信息
     */
    public VcDocRelInsuKind getVcDocRelInsuKind(Long idVcDocRelInsuKind) {
        return (VcDocRelInsuKind) super.get(VcDocRelInsuKind.class, idVcDocRelInsuKind);
    }

    /**
     * 根据查询条件查询满足条件的印刷厂信息
     */
    public Page queryVcDocRelInsuKinds(VcDocRelInsuKind vcDocRelInsuKind, int currentPage, int pageNumber)
            throws DaoException {
        QueryRule queryRule = null;
        try {
            queryRule = QueryRule.getInstance();
        } catch (Exception e) {
            throw new DaoException("QueryRule拼接异常!", e);
        }
        try {
            if (vcDocRelInsuKind != null) {
                /*
                 * if (StringUtils.isNotBlank(vcDocRelInsuKind.getDocVerCode())) { queryRule.addLike("docVerCode",
                 * vcDocRelInsuKind.getDocVerCode()); }
                 */
                if (vcDocRelInsuKind.getIdVcDocVersionInfo() != null) {
                    queryRule.addEqual("idVcDocVersionInfo", vcDocRelInsuKind.getIdVcDocVersionInfo());
                }
                if (StringUtils.isNotBlank(vcDocRelInsuKind.getInsuKindCode())) {
                    queryRule.addLike("insuKindCode", vcDocRelInsuKind.getInsuKindCode());
                }
            }

            return super.find(VcDocRelInsuKind.class, queryRule, currentPage, pageNumber);
        } catch (Exception e) {
            throw new DaoException("查询数据库失败！");
        }
    }

    /**
     * 根据单证类型流水查找关联险种
     * 
     * @param idVcDocVersionInfo
     * @return
     * @throws Exception
     * @author wanghuajian
     * @date 2013-04-27
     */
    public List<DocInsuKindVo> queryRelInsuKindListByDocId(Long idVcDocVersionInfo) throws DaoException {
        try {
            StringBuffer sb = new StringBuffer(100);
            List params = new ArrayList();
            sb.append(" from VcDocRelInsuKind rel,VcDocInsuKind kind,VcDocInsuType type ");
            sb
                    .append(" where rel.insuKindCode=kind.insuKindCode and kind.idVcDocInsuType=type.idVcDocInsuType ");
            sb.append(" and kind.status= ? ");
            params.add("1");
            sb.append(" and rel.idVcDocVersionInfo= ? ");
            params.add(idVcDocVersionInfo);
            List list = this.findByHql(sb.toString(), params.toArray());

            List<DocInsuKindVo> resultList = new ArrayList<DocInsuKindVo>();

            if (list != null && list.size() > 0) {
                VcDocInsuKind kindVo = null;
                VcDocInsuType typeVo = null;
                DocInsuKindVo tempVo = null;
                for (int i = 0; i < list.size(); i++) {
                    tempVo = new DocInsuKindVo();
                    Object[] obj = (Object[]) list.get(i);
                    kindVo = (VcDocInsuKind) obj[1];
                    typeVo = (VcDocInsuType) obj[2];

                    // 值复制
                    TFBeanUtils.copyProperties(kindVo, tempVo);
                    tempVo.setInsuTypeName(typeVo.getInsuTypeName());
                    resultList.add(tempVo);
                }
            }
            return resultList;
        } catch (Exception e) {
            throw new DaoException("查询数据库失败！");
        }
    }

    /**
     * 根据单证类型流水删除险种关联信息
     * 
     * @param idVcDocVersionInfo
     *            单证类型流水
     * @return
     * @throws Exception
     * @author wanghuajian since 2013-6-5
     */
    public int deleteByDocVersionInfoId(Long idVcDocVersionInfo) throws DaoException {
        try {
            if (idVcDocVersionInfo != null) {
                StringBuffer sb = new StringBuffer("delete  from VcDocRelInsuKind rel where 1=1 ");
                List<Object> values = new ArrayList<Object>();
                // 拼接单证流水号
                sb.append(" and rel.idVcDocVersionInfo = ?");
                values.add(idVcDocVersionInfo);

                return this.executeUpdate(sb.toString(), values.toArray());
            }
            return 0;
        } catch (Exception e) {
            throw new DaoException("删除失败！");
        }
    }
    
    public int deleteByRiskCode(String  riskCode) throws DaoException {
        try {
            if (riskCode != null) {
                StringBuffer sb = new StringBuffer("delete  from VcDocRelInsuKind rel where 1=1 ");
                List<Object> values = new ArrayList<Object>();
                // 拼接单证流水号
                sb.append(" and rel.insuKindCode = ?");
                values.add(riskCode);
                return this.executeUpdate(sb.toString(), values.toArray());
            }
            return 0;
        } catch (Exception e) {
            throw new DaoException("删除失败！");
        }
    }
}
