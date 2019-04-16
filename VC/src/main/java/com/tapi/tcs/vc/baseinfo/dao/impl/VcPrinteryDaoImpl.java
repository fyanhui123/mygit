package com.tapi.tcs.vc.baseinfo.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.vc.schema.model.VcPrintDocVersion;
import com.tapi.tcs.vc.schema.model.VcPrintery;
import com.tapi.tcs.vc.baseinfo.dao.VcPrinteryDao;
import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;

/**
 * 印刷厂维护DAO实现
 * <p>
 * Date: 2013-03-13
 * </p>
 * <p>
 * Module: 印刷厂维护
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
/**
 * @author whj
 *
 */
/**
 * @author whj
 *
 */
/**
 * @author whj
 *
 */
/**
 * @author whj
 *
 */
/**
 * @author whj
 *
 */
/**
 * @author whj
 * 
 */
public class VcPrinteryDaoImpl extends GenericDaoHibernate<VcPrintery> implements VcPrinteryDao {

    /**
     * 根据印刷厂ID查询印刷厂信息
     * 
     * @param idPrintery
     *            id
     * @return VcPrintery
     */
    public VcPrintery getPrintery(Long idPrintery) {
        return (VcPrintery) super.get(VcPrintery.class, idPrintery);
    }

    /**
     * 根据查询条件查询满足条件的印刷厂信息
     * 
     * @param printery
     *            条件
     * @param currentPage
     *            当前页数
     * @param pageNumber
     *            总页数
     * @return Page
     */
    public Page queryPrinterys(VcPrintery printery, int currentPage, int pageNumber) throws DaoException {
        QueryRule queryRule = null;
        try {
            queryRule = QueryRule.getInstance();
        } catch (Exception e) {
            throw new DaoException("QueryRule拼接异常!", e);
        }

        if (printery != null) {
            //名称绝对相等
            if (StringUtils.isNotBlank(printery.getPrinteryNameEQ())) {
                queryRule.addEqual("printeryName", printery.getPrinteryNameEQ());
            }
            //名称like
            if (StringUtils.isNotBlank(printery.getPrinteryName())) {
                queryRule.addLike("printeryName", "%" + printery.getPrinteryName() + "%");
            }
            if (StringUtils.isNotBlank(printery.getPrinteryCode())) {
                queryRule.addLike("printeryCode", printery.getPrinteryCode());
            }
            if (StringUtils.isNotBlank(printery.getStatus())) {
                queryRule.addLike("status", printery.getStatus());
            }
        }
        try {
            return super.find(VcPrintery.class, queryRule, currentPage, pageNumber);
        } catch (Exception e) {
            throw new DaoException("查询数据出错！", e);
        }
    }

    /**
     * 保存印刷厂信息及其单证承应信息
     * 
     * @param printery
     *            待保存vo
     */
    public void savePrinteryAndPrintVersion(VcPrintery printery) throws DaoException {
        try {
            super.save(printery);
        } catch (Exception e) {
            throw new DaoException("查询新增出错！", e);
        }

    }

    /**
     * @param hql
     *            hql语句
     * @param params
     *            参数
     * @return List
     */
    @Override
    public List findByHql(String hql, Object... params) {
        return super.findByHql(hql, params);
    }

    /**
     * 
     * @param printeryCode
     *            code
     * @return String
     */
    public String getPrinteryNameByPrinteryCode(String printeryCode) throws DaoException {

        String sql = " SELECT p.printery_name FROM vc_printery p where p.printery_code = '" + printeryCode
                + "'";
        try {
            List list = this.findBySql(sql, null);
            String printeryName = null;
            if (list.size() > 0) {
                printeryName = (String) list.get(0);
            }
            return printeryName;
        } catch (Exception e) {
            throw new DaoException("查询新增出错！", e);
        }
    }

    /**
     * 查询印刷厂列表
     * 
     * @return List
     * @throws Exception
     *             异常
     * @author chy
     * @date 2013-05-20
     */
    public List<VcPrintery> findVcPrinteryList() throws DaoException {
        List<VcPrintery> vcPrinteryList = null;
        try {
            QueryRule queryRule = QueryRule.getInstance();
            queryRule.addEqual("status", "1");
            vcPrinteryList = this.find(VcPrintery.class, queryRule);
        } catch (Exception e) {
            throw new DaoException("查询新增出错！", e);
        }
        return vcPrinteryList;
    }

}
