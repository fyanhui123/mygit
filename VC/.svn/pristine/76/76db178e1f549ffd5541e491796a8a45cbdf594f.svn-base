package com.tapi.tcs.vc.store.dao.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcAllot;
import com.tapi.tcs.vc.schema.model.VcAllotDet;
import com.tapi.tcs.vc.schema.model.VcApplyDet;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.store.dao.AllotDao;


/**
 * 
 * 
 * @author hanmiao.diao
 * 
 */
public class AllotDaoImpl extends GenericDaoHibernate<VcAllot> implements AllotDao {

	public Page queryAllotList(Map<String, Object> params, int pageNo,
			int pageSize) throws DaoException {
		    QueryRule queryRule = null;
	        Page page=new Page();
	        try {
	        	 queryRule = QueryRule.getInstance();
	        	 String queryStatus = (String) params.get("queryStatus");
	        	 if (StringUtils.isNotEmpty(queryStatus)) {
	                 queryRule.addEqual("allotStatus", queryStatus);
	             }
	             String applyCode = (String) params.get("allotCode");
	             if (StringUtils.isNotEmpty(applyCode)) {
	                 queryRule.addEqual("allotCode", applyCode);
	             }
	             String startDate = (String) params.get("startDate");
	             if (StringUtils.isNotEmpty(startDate)) {
	                 Date date1 = DateUtils.parse(startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
	                 queryRule.addGreaterEqual("allotTime", date1);
	             }
	             String endDate = (String) params.get("endDate");
	             if (StringUtils.isNotEmpty(endDate)) {
	                 Date date1 = DateUtils.parse(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
	                 queryRule.addLessEqual("allotTime", date1);
	             }
	             String allotOrgCode = (String) params.get("allotOrgCode");
	             if(StringUtils.isNotEmpty(allotOrgCode)){
	              queryRule.addEqual("allotOrgCode",allotOrgCode);
	             }
	             String provideOrgCode = (String) params.get("provideOrgCode");
	             if(StringUtils.isNotEmpty(provideOrgCode)){
	              queryRule.addEqual("provideOrgCode",provideOrgCode);
	             }
	             queryRule.addDescOrder("allotTime");
	             page=this.find(VcAllot.class,queryRule,pageNo, pageSize);
			} catch (Exception e) {
				  throw new DaoException("查询数据库异常！", e);
			}
		return page;
	}
	@Override
	public void insertVcAllot(VcAllot vcAllot) throws DaoException {
			try {
				 save(vcAllot);
			} catch (Exception e) {
				throw new DaoException("添加数据库异常！",e);
			}
	}

	@Override
	 public List<VcAllotDet> getAllotDetListByVcAllotId(Long vcAllotId) throws DaoException {
    	List<VcAllotDet> vcAllotDetList=null;
    	try{
        String hsql = "from VcAllotDet v where v.vcallot.id=? ";
        Object[] values = new Object[] { vcAllotId };
        vcAllotDetList= this.findByHql(hsql, values);
    	}catch(Exception e){
    		throw new DaoException("查询数据库异常！",e);
    	}
        return vcAllotDetList;
    }
	   public void deleteAllotDetByVcAllotId(Long id) throws DaoException {
	    	try{
		        String hql = "delete from VcAllotDet t where t.vcallot.id=?";
		        List<Object> values = new ArrayList<Object>();
		        values.add(id);
		        this.executeUpdate(hql, values.toArray());
	    	}catch(Exception e){
	    		throw new DaoException("删除数据异常！",e);
	    	}
	    }
	public void clear(VcAllotDet vcAllot) throws DaoException {
//		getHibernateTemplate().flush();
//		getHibernateTemplate().evict(vcAllot);
//		getSession().evict(vcAllot);
		getHibernateTemplate().getSessionFactory().getCurrentSession().merge(vcAllot);
//		getSession().clear();
	}
	@Override
	public List<VcDocVersionInfo> getVcDocVersionInfoList(Long applyId,
			String docVerCode) throws DaoException {
		List<VcDocVersionInfo> list = null;
		try{
			String hql = "from VcDocVersionInfo ver where exists (" +
					"select 1 from VcAllotDet det where ver.docVerCode=det.docVerCode and det.idVcallot=?) " +
					"and ver.docVerCode like ?";
			list = (List<VcDocVersionInfo>)this.findByHql(hql, new Object[]{applyId, "%"+docVerCode+"%"});
		}catch(Exception e){
			throw new DaoException("查询数据库异常！", e);
		}
		return list;
		
	}
	@Override
	public VcAllotDet getVcAllotDet(Long vcAllotId, String docVerCode)
			throws DaoException {
		try{
	        String hsql = "from VcAllotDet v where v.vcallot.id=? and v.docVerCode =?";
	        Object[] values = new Object[] {vcAllotId, docVerCode };
	
	        List<VcAllotDet> vcAllotDetList = this.findByHql(hsql, values);
	
	        if (vcAllotDetList != null && vcAllotDetList.size() == 1) {
	            return vcAllotDetList.get(0);
	        }
        }catch(Exception e){
    		throw new DaoException("查询数据库异常！",e);
        }
        return null;
	}
	@Override
	public String getUnitNameByUnitCode(String unitCode) throws DaoException {
		String unitName = "";
		try{
			if (StringUtils.isEmpty(unitCode)) {
				return "";
			}
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("docVerCode",unitCode);
			queryRule.addEqual("status", "1");
			VcDocVersionInfo vcLevel = (VcDocVersionInfo)this.findUnique(VcDocVersionInfo.class, queryRule);
			if(vcLevel!=null){
				unitName = vcLevel.getDocVerName();
			}
		}catch(Exception e){
			throw new DaoException("查询数据库时发生异常！", e);
		}
		return unitName;
	}
}
