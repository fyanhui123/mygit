package com.tapi.tcs.vc.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.VcPubCodeManagerDao;
import com.tapi.tcs.vc.common.vo.VcPubCodeQueryVo;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcPrintDocVersion;
import com.tapi.tcs.vc.schema.model.VcPubCode;
import com.tapi.tcs.vc.schema.model.VcPubCodeType;
import com.tapi.tcs.vc.schema.model.VcPubCodeType;

/**
 * 通用代码DAO实现类
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
 * @author wanghuajian
 * @version 1.0
 */
public class VcPubCodeManagerDaoImpl extends GenericDaoHibernate<VcPubCodeType> implements
        VcPubCodeManagerDao {

    /**
     * 根据ID查询印信息
     */
    public VcPubCodeType getVcPubCodeType(Long idVcPubCodeType) {
        return (VcPubCodeType) super.get(VcPubCodeType.class, idVcPubCodeType);
    }

    /**
     * 根据查询条件查询满足条件的代码
     */
    public Page queryVcPubCodeTypes(VcPubCodeQueryVo vcPubCodeQueryVo, int pageNo, int pageSize)
            throws DaoException {
        StringBuilder hql = new StringBuilder(" from VcPubCodeType type where 1=1 ");

        List values = new ArrayList();

        if (vcPubCodeQueryVo != null) {
             //代码类型(完全匹配查询)
            if (StringUtils.isNotBlank(vcPubCodeQueryVo.getCodeTypeEQ())) {
                hql.append(" and type.codeType = ? ");
                values.add(vcPubCodeQueryVo.getCodeTypeEQ());
            }
            if (StringUtils.isNotBlank(vcPubCodeQueryVo.getCodeType())) {
                hql.append(" and type.codeType like ? ");
                values.add(vcPubCodeQueryVo.getCodeType() + "%");
            }
            if (StringUtils.isNotBlank(vcPubCodeQueryVo.getCodeTypeDesc())) {
                hql.append(" and type.codeTypeDesc like ? ");
                values.add("%" + vcPubCodeQueryVo.getCodeTypeDesc() + "%");
            }
            if (StringUtils.isNotBlank(vcPubCodeQueryVo.getStatus())) {
                hql.append(" and type.status = ? ");
                values.add(vcPubCodeQueryVo.getStatus());
            }
            if (StringUtils.isNotBlank(vcPubCodeQueryVo.getCodeCode())
                    || StringUtils.isNotBlank(vcPubCodeQueryVo.getCodeCName())
                    || StringUtils.isNotBlank(vcPubCodeQueryVo.getCodeEName())) {
                hql
                        .append(" and  exists(select 1 from VcPubCode sub where sub.codeType = type.codeType and sub.status='1' ");
                if (StringUtils.isNotBlank(vcPubCodeQueryVo.getCodeCode())) {
                    hql.append(" and sub.codeCode = ? ");
                    values.add(vcPubCodeQueryVo.getCodeCode());
                }
                if (StringUtils.isNotBlank(vcPubCodeQueryVo.getCodeCName())) {
                    hql.append(" and sub.codeCName like ? ");
                    values.add("%" + vcPubCodeQueryVo.getCodeCName() + "%");
                }
                if (StringUtils.isNotBlank(vcPubCodeQueryVo.getCodeEName())) {
                    hql.append(" and sub.codeEName like ? ");
                    values.add("%" + vcPubCodeQueryVo.getCodeEName() + "%");
                }
                hql.append(" ) ");
            }
        }
        try {
            return super.findByHql(hql.toString(), pageNo, pageSize, values.toArray());
        } catch (Exception e) {
            throw new DaoException("查询数据库出错！", e);
        }
    }

    /**
     * 根据条件通用代码信息
     * 
     * @param conditionDto
     * @return
     * @throws Exception
     * @author wanghuajian since 2013-4-15下午04:40:55
     */
    public int deletePubCodeByConditions(VcPubCode conditionDto) throws DaoException {
        if (conditionDto != null && conditionDto.getCodeType() != null) {
            StringBuffer sb = new StringBuffer("delete  from VcPubCode v where 1=1 ");
            List<Object> values = new ArrayList<Object>();
            // 拼接订单号查询条件
            if (conditionDto.getCodeType() != null) {
                sb.append(" and v.codeType = ?");
                values.add(conditionDto.getCodeType());
            }
            try {
                return this.executeUpdate(sb.toString(), values);
            } catch (Exception e) {
                throw new DaoException("查询数据库出错！", e);
            }
        }
        return 0;

    }

    /**
     * 根据代码类型查询通用代码信息，并排序
     * 
     * @param codeType
     *            代码类型
     * @param orderParmNames
     *            排序字段名数组,只支持（idVcPubCode，codeCode，codeCName，codeEName）这几个字段
     * @param orderType
     *            （asc desc）不区分大小写
     * @param notContainCodeCodes
     *            不包含的业务代码 codes(String[])
     * @throws BusinessException
     * @author wanghuajian since 2013-4-13下午01:36:22
     */
    @SuppressWarnings("unchecked")
    public List<VcPubCode> getVcPubCodeListByCodeType(String codeType, String[] orderParmNames,
            String orderType, String[] notContainCodeCodes) throws DaoException {
        QueryRule queryRule = null;
        try {
            queryRule = QueryRule.getInstance();
        } catch (Exception e) {
            throw new DaoException("QueryRule拼接异常！ ", e);
        }
        queryRule.addEqual("status", "1");

        if (StringUtils.isNotBlank(codeType)) {
            queryRule.addEqual("codeType", codeType);
        }

        if (orderParmNames != null && orderParmNames.length > 0 && StringUtils.isNotBlank(orderType)) {
            for (String parmName : orderParmNames) {
                if (StringUtils.isNotBlank(parmName)) {
                    if ("asc".equalsIgnoreCase(orderType)) {
                        queryRule.addAscOrder(parmName);
                    } else {
                        queryRule.addDescOrder(parmName);
                    }
                }

            }
        }
        // 不包含的业务代码
        if (notContainCodeCodes != null && notContainCodeCodes.length > 0) {
            StringBuffer subSql = new StringBuffer(" code_Code not in (");
            for (String codeCode : notContainCodeCodes) {
                if (StringUtils.isNotBlank(codeCode)) {
                    subSql.append("'").append(codeCode).append("',");
                }
            }
            queryRule.addSql(subSql.substring(0, subSql.length() - 1) + ")");
        }
        try {
            return super.find(VcPubCode.class, queryRule);
        } catch (Exception e) {
            throw new DaoException("查询数据库出错！", e);
        }
    }

    /**
     * 通过codeType,codeCode翻译代码
     * 
     * @param codeType
     * @param codeCode
     * @return
     * @throws DaoException
     * @author chy
     * @date 2013-04-24
     */
    public String getCodeCname(String codeType, String codeCode) throws DaoException {
        String codeCname = "";
        try {
            StringBuffer sb=new StringBuffer();
            List<Object> values=new ArrayList<Object>();
            sb.append("select c.CODE_C_NAME from VC_PUB_CODE c,VC_PUB_CODE_TYPE ct ");
            sb.append(" where c.CODE_TYPE=ct.CODE_TYPE and c.STATUS= ? ");
            values.add("1");
            sb.append(" and ct.STATUS=?  and ct.CODE_TYPE=?  and c.CODE_CODE=? ");
            values.add("1");
            values.add(codeType);
            values.add(codeCode);
            List list = this.findBySql(sb.toString(), values.toArray());
            if (list != null && list.size() > 0) {
                codeCname = (String) list.get(0);
            }
        } catch (Exception e) {
            throw new DaoException("转换代码【" + codeCode + "】时发生异常！", e);
        }
        return codeCname;
    }

    @Override
    public List<VcPubCode> getVcPubCodeList(String codeType, String upperCode) throws DaoException {
        List<VcPubCode> list = null;
        try {
            QueryRule queryRule = QueryRule.getInstance();
            // 代码类型
            queryRule.addEqual("codeType", codeType);
            // 上级代码
            if (StringUtils.isNotEmpty(upperCode)) {
                queryRule.addEqual("upperCode", upperCode);
            }
            queryRule.addEqual("status", "1");
            list = this.find(VcPubCode.class, queryRule);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("查询数据库失败!", e);
        }
        return list;
    }
    
    /**
     *通过codeType、codeCode获取对应的PubCode对象
     * @param codeType
     * @param codeCode
     * @return
     * @throws DaoException
     *@author whj
     *@since May 6, 2015
     */
    public VcPubCode getVcPubCode(String codeType, String codeCode) throws DaoException {
        List<VcPubCode> list = null;       
        List<Object> values = new ArrayList<Object>();
        StringBuffer hql=new StringBuffer();
        hql.append("select p  from VcPubCode p,VcPubCodeType t where p.codeType=t.codeType ");
        hql.append(" and t.status=? and  p.status=? ");
        values.add("1");
        values.add("1");
        hql.append(" and t.codeType=?  and p.codeCode=?  ");           
        values.add(codeType);
        values.add(codeCode);
        list = this.findByHql(hql.toString(), values.toArray());
        if (list != null && list.size() > 0) {
           return  list.get(0);
        }
        return null;        
    }
}
