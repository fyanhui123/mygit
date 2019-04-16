package com.tapi.tcs.vc.store.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcDocVersionInfoDao;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcStorageInOut;
import com.tapi.tcs.vc.schema.model.VcStorageInOutDet;
import com.tapi.tcs.vc.store.dao.VcStorageInOutDao;

public class VcStorageInOutDaoImpl extends GenericDaoHibernate<VcStorageInOut> implements VcStorageInOutDao {
    
   private  VcLevelDao vcLevelDao;
   
   private VcDocVersionInfoDao vcDocVersionInfoDao;
    /**
     * 查询申领记录
     * 
     * @param params
     *            查询条件
     * @param pageNo
     *            分页参数
     * @param pageSize
     *            分页参数
     * @return 查询结果
     * @throws DaoException
     *             异常
     */
    public Page queryVcStorageInOutList(VcStorageInOut vcStorageInOut, int pageNo, int pageSize) throws DaoException {        
        Page page = new Page();
        try {
            StringBuffer hql=new StringBuffer();
            List<Object> values=new ArrayList<Object>();
           hql.append("from VcStorageInOut m, VcStorageInOutDet d where m.idVcStorageInOut=d.idVcStorageInOut ");
           
           if (StringUtils.isNotEmpty(vcStorageInOut.getDocVerCode())) {
               hql.append(" and d.docVerCode=? ");
               values.add(vcStorageInOut.getDocVerCode());
           }
           if (StringUtils.isNotEmpty(vcStorageInOut.getStartNumFrom())) {
               hql.append(" and to_number(d.startNum)>=? ");
               values.add(Long.valueOf(vcStorageInOut.getStartNumFrom()));
           }
           if (StringUtils.isNotEmpty(vcStorageInOut.getStartNumTo())) {
               hql.append(" and to_number(d.startNum)<=? ");
               values.add(Long.valueOf(vcStorageInOut.getStartNumTo()));
           }

           if (StringUtils.isNotEmpty(vcStorageInOut.getOperateFlag())) {
               hql.append(" and m.operateFlag=? ");
               values.add(vcStorageInOut.getOperateFlag());
           }

           if (vcStorageInOut.getStartOperateTime() != null) {               
               hql.append(" and m.operateTime>=? ");
               values.add(DateUtils.reset(vcStorageInOut.getStartOperateTime()));
           }

           if (vcStorageInOut.getEndOperateTime() != null) {               
               hql.append(" and m.operateTime<? ");
               values.add(DateUtils.reset(DateUtils.addDay(vcStorageInOut.getEndOperateTime(), 1)));
           }

           if (StringUtils.isNotEmpty(vcStorageInOut.getOprCode())) {              
               hql.append(" and m.oprCode =? ");
               values.add(vcStorageInOut.getOprCode());
           }
           
           hql.append(" order by  m.operateTime desc ");
            
            page =this.findByHqlNoLimit(hql.toString(), pageNo, pageSize, values.toArray());
            List<Object[]> list=(List<Object[]>)page.getResult();
            VcStorageInOut inOut=null;
            VcStorageInOutDet det=null;
            VcStorageInOut newinOut=null;
            List<VcStorageInOut> resultList=new ArrayList<VcStorageInOut>();
            if(list!=null && list.size()>0){
                for(Object[] obj : list){
                     inOut=(VcStorageInOut)obj[0];
                     det=(VcStorageInOutDet)obj[1];
                     newinOut=new VcStorageInOut();
                     BeanUtils.copyProperties(inOut, newinOut);
                     newinOut.setIdVcStorageInOutDet(det.getIdVcStorageInOutDet());
                     newinOut.setDocVerCode(det.getDocVerCode());
                     newinOut.setDocVerName(vcDocVersionInfoDao.getVersionName(det.getDocVerCode()));
                     newinOut.setPressBatchNo(det.getPressBatchNo());
                     newinOut.setStartNum(det.getStartNum());
                     newinOut.setEndNum(det.getEndNum());
                     newinOut.setDocNum(det.getDocNum());                     
                     newinOut.setOrgName(vcLevelDao.getUnitNameByUnitCode(inOut.getOrgCode()));
                     newinOut.setOprName(vcLevelDao.getUnitNameByUnitCode(inOut.getOprCode()));                     
                     resultList.add(newinOut);
                }
            }
            page.setData(resultList);

          /* queryRule = QueryRule.getInstance();
             if (StringUtils.isNotEmpty(vcStorageInOut.getInOutCode())) {
                queryRule.addEqual("inOutCode", vcStorageInOut.getInOutCode());
            }

            if (StringUtils.isNotEmpty(vcStorageInOut.getOperateFlag())) {
                queryRule.addEqual("operateFlag", vcStorageInOut.getOperateFlag());
            }

            if (vcStorageInOut.getStartOperateTime() != null) {
                Date date1 = DateUtils.reset(vcStorageInOut.getStartOperateTime());
                queryRule.addGreaterEqual("operateTime", date1);
            }

            if (vcStorageInOut.getEndOperateTime() != null) {
                Date date1 = DateUtils.reset(DateUtils.addDay(vcStorageInOut.getStartOperateTime(), 1));
                queryRule.addLessThan("operateTime", date1);
            }

            if (StringUtils.isNotEmpty(vcStorageInOut.getOprCode())) {
                queryRule.addEqual("oprCode", vcStorageInOut.getOprCode());
            }
         
            page = this.find(VcStorageInOut.class, queryRule, pageNo, pageSize);*/
        } catch (Exception e) {
            throw new DaoException("查询数据库异常！", e);
        }
        return page;
    }

    /**
     * 根据主表id删除明细表记录
     * 
     * @param id
     *            主表id
     * @throws DaoException
     *             异常
     */
    public void deleteDetByVcStorageInOutId(Long id) throws DaoException {
        try {
            String hql = "delete from VcStorageInOutDet t where t.vcStorageInOut.idVcStorageInOut=?";
            List<Object> values = new ArrayList<Object>();
            values.add(id);
            this.executeUpdate(hql, values.toArray());
        } catch (Exception e) {
            throw new DaoException("删除数据异常！", e);
        }
    }

    /**
     * 根据id获取VC_STORAGE_IN_OUT_DET中关联明细
     * 
     * @param vcStorageInOutId
     * @return 明细集合
     * @throws DaoException
     *             异常
     */
    public List<VcStorageInOutDet> getDetListByVcStorageInOutId(Long vcStorageInOutId) throws DaoException {
        List<VcStorageInOutDet> vcStorageInOutDetList = null;
        try {
            String hql = "from VcStorageInOutDet v where v.vcStorageInOut.idVcStorageInOut=? ";
            Object[] values = new Object[] { vcStorageInOutId };
            vcStorageInOutDetList = this.findByHql(hql, values);
        } catch (Exception e) {
            throw new DaoException("查询数据库异常！", e);
        }
        return vcStorageInOutDetList;
    }

    /**
     * 
     * @param vcStorageInOutId
     * @param docVerCode
     * @return
     * @throws DaoException
     *@author whj
     *@since Apr 3, 2014
     */
    public VcStorageInOut getVcStorageInOut(Long vcStorageInOutId) throws DaoException {
        QueryRule queryRule = null;
        try {
            queryRule = QueryRule.getInstance();
            queryRule.addEqual("idVcStorageInOut", vcStorageInOutId);
            return this.findUnique(queryRule);
        } catch (Exception e) {
            throw new DaoException("查询数据库异常！", e);
        }
    }

    public void setVcLevelDao(VcLevelDao vcLevelDao) {
        this.vcLevelDao = vcLevelDao;
    }

    public void setVcDocVersionInfoDao(VcDocVersionInfoDao vcDocVersionInfoDao) {
        this.vcDocVersionInfoDao = vcDocVersionInfoDao;
    }

    
}
