package com.tapi.tcs.vc.baseinfo.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.baseinfo.vo.UserVo;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.PubUserDef;
import com.tapi.tcs.vc.schema.model.VcAgencyOrg;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcTaker;

public class VcLevelDaoImpl extends GenericDaoHibernate<VcLevel> implements VcLevelDao {

	/**
	 * 根据用户代码查询用户是否存在
	 */
	@Override
	public List<VcLevel> findByUserCode(String unitCode,String invoiceFlag) throws DaoException {
		List<VcLevel> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("unitCode", unitCode);
			queryRule.addEqual("validStatus", "1");
			queryRule.addEqual("invoiceFlag", invoiceFlag);
			list = this.find(VcLevel.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询数据出错！", e);
		}
		return list;
	}
	/**
	 * 分页查询级别单位设置
	 * 
	 * @param comCode
	 * @param unitType
	 * @param pageNo
	 * @param pageSize
	 * @return page
	 * @throws DaoException
	 * @author chy
	 * @date 2013-05-06
	 */
	public Page queryVcLevelPage(String parentOrgId, String unitType, int pageNo, int pageSize) throws DaoException {
		Page page = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			// 拼接归属机构查询条件
			queryRule.addEqual("parentOrgId", Long.valueOf(parentOrgId));
			// 拼接级别单位类型查询条件
			queryRule.addEqual("unitType", unitType);
			// 当前状态：有效
			//queryRule.addEqual("validStatus", "1");无效的也要查询出来
			// 按显示序号升序
			queryRule.addAscOrder("displayNo");
			page = this.find(VcLevel.class, queryRule, pageNo, pageSize);
		}catch(Exception e){
			throw new DaoException("查询数据失败！", e);
		}
		return page;
	}

	/**
	 * 根据主键查找
	 * 
	 * @param id
	 * @return VcLevel
	 * @throws DaoException
	 * @author chy
	 * @date 2013-05-06
	 */
	public VcLevel findVcLevelByPK(Long id) throws DaoException {
		VcLevel vcLevel = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("id", id);
			queryRule.addEqual("validStatus", "1");
			vcLevel = (VcLevel) this.findUnique(VcLevel.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询数据失败！", e);
		}
		return vcLevel;
	}
	
	@Override
	public VcLevel findVcLevel(Long id) throws DaoException {
		VcLevel vcLevel = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("id", id);
			vcLevel = (VcLevel) this.findUnique(VcLevel.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询数据失败！", e);
		}
		return vcLevel;
	}

	/**
	 * 保存级别设置
	 * 
	 * @param vcLevel
	 * @return
	 * @throws Exception
	 * @author chy
	 * @date 2013-05-07
	 */
	public void saveVcLevel(VcLevel vcLevel) throws DaoException {
		try{
			this.save(vcLevel);
		}catch(Exception e){
			throw new DaoException("保存数据出错！", e);
		}
	}

	/**
	 * 根据级别代码查找
	 * 
	 * @param unitCode
	 * @return list
	 * @throws DaoException
	 * @author chy
	 * @date 2013-05-07
	 */
	public List<VcLevel> findByUnitCode(String unitCode) throws DaoException {
		List<VcLevel> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("unitCode", unitCode);
			queryRule.addEqual("validStatus", "1");
			list = this.find(VcLevel.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询数据出错！", e);
		}
		return list;
	}

	/**
	 * 修改级别设置
	 * 
	 * @param vcLevel
	 * @return
	 * @author chy
	 * @date 2013-05-07
	 */
	public void updateVcLevel(VcLevel vcLevel) throws DaoException {
		try{
			this.update(vcLevel);
		}catch(Exception e){
			throw new DaoException("更新数据出错！", e);
		}
	}

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 * @author chy
	 * @date 2013-05-07
	 */
	public void deleteVcLevelByPK(Long id) throws DaoException {
		try{
			this.deleteByPK(VcLevel.class, id);
		}catch(Exception e){
			throw new DaoException("删除数据出错！", e);
		}
	}

	/**
	 * 根据机构ID查找下级机构或人员
	 * 
	 * @param id
	 * @return list
	 * @author chy
	 * @date 2013-05-08
	 */
	public List<VcLevel> findChileByParentOrgId(Long id) throws DaoException {
		List<VcLevel> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("parentOrgId", id);
			queryRule.addEqual("validStatus", "1");
			list = this.find(VcLevel.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询数据失败！", e);
		}
		return list;
	}
	
	@Override
	public boolean checkIsDeletable(Long id) throws DaoException {
		boolean flag = false;
		try{
			StringBuffer hql = new StringBuffer("");
			hql.append(" from VcLevel v where v.id=?");
			hql.append(" and v.validStatus=?");
			hql.append(" and exists(select 1 from VcLevel t where t.parentOrgId=v.id and t.validStatus=?)");
			List list = this.findByHql(hql.toString(), new Object[]{id,"1","1"});
			//没有找到记录说明可以删除
			if(list==null || list.size()<1){
				flag = true;
			}
		}catch(Exception e){
			throw new DaoException("查询数据失败！", e);
		}
		return flag;
	}

	/**
	 * @Description：获取用户归属机构代码
	 * @param userCode
	 * @return comCode
	 * @author chy
	 * @date 2013-05-13
	 */
	public String getComCode(String userCode) throws DaoException {
		String comCode = "";		
		try{
		    StringBuffer sql=new StringBuffer();
	        List<Object> values= new ArrayList<Object>();
			sql.append("SELECT p.unit_code FROM vc_level p,vc_level c WHERE  ");
			sql.append(" p.id_vc_level=c.parent_org_id AND c.unit_type=? ");
			values.add("1");
			sql.append(" AND c.unit_code=? ");
			values.add(userCode);
			sql.append(" and p.valid_status=? and c.valid_status=? ");
			values.add("1");
			values.add("1");
			List list = this.findBySql(sql.toString(), values.toArray());
			if (list != null && list.size() > 0) {
				comCode = (String) list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询数据失败！", e);
		}
		return comCode;
	}
	
	
	/**
	 * 查询出（ 同一个机构下最大序号）
	 */
	public Integer getMaxDisplayNoFromLevel(String upperCompanyCode) throws DaoException {
		Integer displayNo = 1;
		try{
		    StringBuffer sql=new StringBuffer();
	        List<Object> values= new ArrayList<Object>();
			sql.append("select max(t.display_no) from vc_level t where t.parent_org_code = ?");
			values.add(upperCompanyCode);
			List list = this.findBySql(sql.toString(), values.toArray());
			if (list != null && list.size() > 0) {
				BigDecimal decimal = (BigDecimal)list.get(0);
				displayNo = decimal.intValue();
			}
		}catch(Exception e){
			throw new DaoException("查询数据失败！", e);
		}
		return displayNo;
	}
	/**
	 * @Description：查询使用人机构代码
	 * @param userCode
	 * @return comCode
	 * @author xsb
	 * @date 20150727
	 */
	public String getTakerComCode(String userCode) throws DaoException {
		String comCode = "";		
		try{
			StringBuffer sql=new StringBuffer();
	        List<Object> values= new ArrayList<Object>();
			sql.append("select t.org_code from vc_taker t where t.taker_code = ?");
			values.add(userCode);
			List list = this.findBySql(sql.toString(), values.toArray());
			if (list != null && list.size() > 0) {
				comCode = (String) list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询数据失败！", e);
		}
		return comCode;
	}

	/**
	 * @Description：获取上级审批机构
	 * @param userCode
	 * @return upperOrgCode
	 * @author chy
	 * @date 2013-05-13
	 */
	public String getUpperOrgCode(String userCode) throws DaoException {
		String upperOrgCode = "";
		List<Object> values = new ArrayList<Object>();
		try{
			String sql = "SELECT a.unit_code FROM vc_level a WHERE a.id_vc_level=("
					+ " SELECT b.parent_org_id FROM vc_level b WHERE b.id_vc_level=("
					+ " SELECT  distinct(c.parent_org_id) FROM vc_level c WHERE c.unit_type='1' "
					+ " and c.unit_code=? and c.valid_status='1')"
					+ " and b.valid_status='1') and a.valid_status='1'";
			values.add(userCode);
			List list = this.findBySql(sql, values.toArray());
			if (list != null && list.size() > 0) {
				upperOrgCode = (String) list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("获取上级机构失败！", e);
		}
		return upperOrgCode;
	}

	/**
	 * @Description：获取下级机构机构
	 * @param comCode
	 * @return list
	 * @author chy
	 * @date 2013-05-14
	 */
	public List<String> getChildOrgCode(String comCode) throws DaoException {
		List<String> list = null;
		try{
			String sql = "select c.UNIT_CODE from VC_LEVEL p,VC_LEVEL c" + " where p.ID_VC_LEVEL=c.PARENT_ORG_ID"
					+ " and c.UNIT_TYPE='0' and p.UNIT_TYPE='0'" + " and p.UNIT_CODE='" + comCode + "'";
			list = (List<String>) this.findBySql(sql, null);
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		return list;
	}

	public String findParentOrgByUnitCode(String unitCode) throws DaoException {
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT t2.unit_code FROM vc_level t,vc_level t2 ");
			sql.append(" WHERE t.unit_code = ? ");
			sql.append(" AND t.parent_org_id = t2.id_vc_level ");
			Object[] params = { unitCode };
			List result = super.findBySql(sql.toString(), params);
			if (result.size() > 0) {
				return (String) result.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		return null;
	}

	/**
	 * 根据类型及code查询
	 * 
	 * @param unitType
	 * @param unitCode
	 * @return
	 * @throws Exception
	 */
	public VcLevel getVcLevel(String unitType, String unitCode) throws DaoException {
		VcLevel vcLevel = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("validStatus", "1");
			queryRule.addEqual("unitType", unitType);
			queryRule.addEqual("unitCode", unitCode);
			List<VcLevel> list = this.find(VcLevel.class, queryRule);
			if (list != null && list.size() > 0) {
				vcLevel = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询数据出错！", e);
		}
		return vcLevel;
	}

	/**
	 * 根据根机构code查询到指定级别的机构树
	 * 
	 * @param rootOrgCode根机构
	 * @param unitType
	 *            值为0时只查询机构，否则全部机构与人
	 * @param minLevel
	 *            查询到哪一级机构
	 * @return
	 * @throws Exception
	 * @author whjwanghuajian
	 */
	/*public List<VcLevel> queryOrgZTreeList1(String rootOrgCode, String unitType, int minLevel) throws Exception {
		List<Object> values = new ArrayList<Object>();

		StringBuffer sb = new StringBuffer("from VcLevel node  where node.validStatus = ?");
		values.add("1");
		// 查询到哪一级
		if (minLevel > 0) {
			sb.append(" and node.levelNo<= ? ");
			values.add(minLevel);
		}
		// 类别为机构时
		if (unitType != null && "0".equals(unitType)) {
			sb.append(" and node.unitType= ?  ");
			values.add(unitType);
		}
		// rootOrgCode
		if (StringUtils.isNotBlank(rootOrgCode)) {
			sb.append(" and   node.unitCode like ? )");
			values.add(rootOrgCode + "%");

		}
		return this.findByHql(sb.toString(), values.toArray());

	}*/

	/**
	 * 根据机构code查询到指定级别的机构树(用递归查询)
	 * 
	 * @param rootOrgCode根机构
	 *            (若为空则默认为总公司)
	 * @param minLevel
	 *            查询到哪一级机构（若minLevel<1则不限制）
	 * @return
	 * @throws DaoException
	 * @author wanghuajian
	 */
	public List<VcLevel> queryOrgZTreeList(String rootOrgCode, int minLevel) throws DaoException {
		List<VcLevel> resultList = new ArrayList<VcLevel>();
		try{
			if (StringUtils.isNotBlank(rootOrgCode)) {		
				VcLevel rootLevel = this.getVcLevel("0", rootOrgCode);
				if (rootLevel != null ) {
					 resultList=this.queryOrgZTreeListByRootOrgId(rootLevel.getId(),minLevel);
				} 
			} else {
				 resultList=this.queryOrgZTreeListByRootOrgId(null,minLevel);
			}
		}catch(Exception e){
			throw new DaoException("查询机构数出错！", e);
		}
		return resultList;
	}
	
	/**
	 * 根据机构code查询到指定级别的机构树(用递归查询)
	 * @param rootOrgCode根机构
	 * 					(若为空则默认为总公司)
	 * @param minLevel
	 * 					查询到哪一级机构（若minLevel<1则不限制）
	 * @param onlyOrgFlag 是否只查询机构
	 * @return
	 * @throws DaoException
	 */
	public List<VcLevel> queryOrgZTreeList(String rootOrgCode, int minLevel, String onlyOrgFlag) throws DaoException {
		List<VcLevel> resultList = new ArrayList<VcLevel>();
		try{
			if (StringUtils.isNotBlank(rootOrgCode)) {		
				VcLevel rootLevel = this.getVcLevel("0", rootOrgCode);
				if (rootLevel != null ) {
					 resultList=this.queryOrgZTreeListByRootOrgId(rootLevel.getId(),minLevel, onlyOrgFlag);
				} 
			} else {
				 resultList=this.queryOrgZTreeListByRootOrgId(null,minLevel,onlyOrgFlag);
			}
		}catch(Exception e){
			throw new DaoException("查询机构数出错！", e);
		}
		return resultList;
	}

	/**
	 * 根据根机构id查询到指定级别的机构树，结果包含根节点(用递归查询)
	 * 
	 * @param rootOrgId根机构Id （若<1或null则默认为总公司）
	 * @param minLevel
	 *            查询到哪一级机构（若<1则不限制）
	 * @return
	 * @throws Exception
	 * @author whjwanghuajian
	 */
	public List<VcLevel> queryOrgZTreeListByRootOrgId(Long rootOrgId, int minLevel) throws DaoException {
	  
	    /////注意：HQL不支持 connect by prior，顾用sql查询   
		List<VcLevel> levelList=new ArrayList<VcLevel>();
		try{
			StringBuffer sql = new StringBuffer();
			List<Object> params = new ArrayList<Object>();		
			sql.append(" select ");
			sql.append("vl.ID_VC_LEVEL ,  vl.UNIT_TYPE  ,  vl.UNIT_CODE  ,  vl.UNIT_NAME  ,  vl.PARENT_ORG_ID, ");
			sql.append("  vl.COM_TYPE ,  vl.LEVEL_NO     ,  vl.MANAGE_LEVEL ,  vl.VALID_STATUS , vl.LEVEL_CODE "); 
			sql.append(" from VC_LEVEL vl ");
			// 级别单位类型unitType --**0=机构;1=员工
			sql.append(" where vl.UNIT_TYPE = ? ");
			params.add("0");
			//有效
			sql.append(" and vl.VALID_STATUS = ? ");
			params.add("1");
			// 显示到树形的哪一级
			if (minLevel > 0) {
				sql.append(" and vl.LEVEL_NO <= ? ");
				params.add(minLevel);
			}
			
			if(rootOrgId!=null && rootOrgId>0){		
				sql.append(" start with vl.ID_VC_LEVEL=? ");
				params.add(rootOrgId);
			}else{
				//总公司级别为1
				sql.append(" start with VL.LEVEL_NO=? ");
				params.add(1);			
			}
			sql.append(" connect by prior vl.ID_VC_LEVEL=vl.PARENT_ORG_ID ");
			
			//add by chenyi 增加按显示序号排序
			sql.append(" order by DISPLAY_NO");
			
	         List list=this.findBySql(sql.toString(), params.toArray());
	         
	         VcLevel level=null;
	         if(list!=null && list.size()>0){
		         for(int i=0;i<list.size();i++){
		        	 level=new VcLevel();
		        	 Object[] obj=(Object[])list.get(i);
		        	 level.setId(((BigDecimal)obj[0]).longValue());
		        	 level.setUnitType((String)obj[1]);
		        	 level.setUnitCode((String)obj[2]);
		        	 level.setUnitName((String)obj[3]);
		        	 level.setParentOrgId(((BigDecimal)obj[4]).longValue());
		        	 level.setComType((String)obj[5]);
		        	 level.setLevelNo(((BigDecimal)obj[6]).intValue());
		        	 level.setManageLevel(((BigDecimal)obj[7]).intValue());
		        	 level.setValidStatus((String)obj[8]);
		        	 level.setLevelCode((String)obj[9]);
		        	 levelList.add(level);        	 
		         }
	         }
		}catch(Exception e){
			throw new DaoException("查询机构数出错！", e);
		}
		return levelList;
		
		
		/*StringBuffer hql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();

		hql.append(" from VcLevel vl ");
		// 级别单位类型unitType --**0=机构;1=员工
		hql.append(" where vl.unitType = ? ");
		params.add("0");
		//有效
		hql.append(" and vl.validStatus = ? ");
		params.add("1");
		
		// 显示到树形的哪一级
		if (minLevel > 0) {
			hql.append(" and vl.levelNo <= ? ");
			params.add(minLevel);
		}
		
		if(rootOrgId!=null && rootOrgId>0){		
			hql.append(" start with vl.id= ? ");
			params.add(rootOrgId);
		}else{
			//总公司级别为1
			hql.append(" start with vl.levelNo= ? ");
			params.add(1);
		}
		hql.append(" connect by prior vl.parentOrgId=vl.id ");
		
		return this.findByHql(hql.toString(), params.toArray());*/
	}
	
	/**
	 * 根据根机构id查询到指定级别的机构树，结果包含根节点(用递归查询)
	 * 
	 * @param rootOrgId根机构Id （若<1或null则默认为总公司）
	 * @param minLevel
	 *            查询到哪一级机构（若<1则不限制）
	 *	@param onlyOrgFlag 是否只查询机构
	 * @return
	 * @throws Exception
	 * @author chy
	 */
	public List<VcLevel> queryOrgZTreeListByRootOrgId(Long rootOrgId, int minLevel, String onlyOrgFlag) throws DaoException {

		  
	    /////注意：HQL不支持 connect by prior，顾用sql查询   
		List<VcLevel> levelList=new ArrayList<VcLevel>();
		try{
			StringBuffer sql = new StringBuffer();
			List<Object> params = new ArrayList<Object>();		
			sql.append(" select ");
			sql.append("vl.ID_VC_LEVEL ,  vl.UNIT_TYPE  ,  vl.UNIT_CODE  ,  vl.UNIT_NAME  ,  vl.PARENT_ORG_ID, ");
			sql.append("  vl.COM_TYPE ,  vl.LEVEL_NO     ,  vl.MANAGE_LEVEL ,  vl.VALID_STATUS , vl.LEVEL_CODE "); 
			sql.append(" from VC_LEVEL vl ");
			// 级别单位类型unitType --**0=机构;1=员工
			sql.append(" where vl.UNIT_TYPE = ? ");
			params.add("0");
			//有效
			sql.append(" and vl.VALID_STATUS = ? ");
			params.add("1");
			// 显示到树形的哪一级
			if (minLevel > 0) {
				sql.append(" and vl.LEVEL_NO <= ? ");
				params.add(minLevel);
			}
			//add by chy 20130805 是否只查询机构
			if(StringUtils.isNotEmpty(onlyOrgFlag) && "1".equals(onlyOrgFlag)){
				sql.append(" and vl.COM_TYPE=?");
				params.add("0");
			}
			
			if(rootOrgId!=null && rootOrgId>0){		
				sql.append(" start with vl.ID_VC_LEVEL=? ");
				params.add(rootOrgId);
			}else{
				//总公司级别为1
				sql.append(" start with VL.LEVEL_NO=? ");
				params.add(1);			
			}
			sql.append(" connect by prior vl.ID_VC_LEVEL=vl.PARENT_ORG_ID ");
			
			//add by chenyi 增加按显示序号排序
			sql.append(" order by DISPLAY_NO");
			
	         List list=this.findBySql(sql.toString(), params.toArray());
	         
	         VcLevel level=null;
	         if(list!=null && list.size()>0){
		         for(int i=0;i<list.size();i++){
		        	 level=new VcLevel();
		        	 Object[] obj=(Object[])list.get(i);
		        	 level.setId(((BigDecimal)obj[0]).longValue());
		        	 level.setUnitType((String)obj[1]);
		        	 level.setUnitCode((String)obj[2]);
		        	 level.setUnitName((String)obj[3]);
		        	 level.setParentOrgId(((BigDecimal)obj[4]).longValue());
		        	 level.setComType((String)obj[5]);
		        	 level.setLevelNo(((BigDecimal)obj[6]).intValue());
		        	 level.setManageLevel(((BigDecimal)obj[7]).intValue());
		        	 level.setValidStatus((String)obj[8]);
		        	 level.setLevelCode((String)obj[9]);
		        	 levelList.add(level);        	 
		         }
	         }
		}catch(Exception e){
			throw new DaoException("查询机构数出错！", e);
		}
		return levelList;
		
		
		/*StringBuffer hql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();

		hql.append(" from VcLevel vl ");
		// 级别单位类型unitType --**0=机构;1=员工
		hql.append(" where vl.unitType = ? ");
		params.add("0");
		//有效
		hql.append(" and vl.validStatus = ? ");
		params.add("1");
		
		// 显示到树形的哪一级
		if (minLevel > 0) {
			hql.append(" and vl.levelNo <= ? ");
			params.add(minLevel);
		}
		
		if(rootOrgId!=null && rootOrgId>0){		
			hql.append(" start with vl.id= ? ");
			params.add(rootOrgId);
		}else{
			//总公司级别为1
			hql.append(" start with vl.levelNo= ? ");
			params.add(1);
		}
		hql.append(" connect by prior vl.parentOrgId=vl.id ");
		
		return this.findByHql(hql.toString(), params.toArray());*/
	
	}
	
	/**
     * 根据根机构/人员 code  查询其对应级别的上级机构(用递归start with查询)
     * 
     * @param unitCode 机构/人员code    
     * @param minLevel
     *            查询到1~~minLevel级别间的上级机构（若<1则不限制）
     * @return List
     * @throws Exception
     * @author whjwanghuajian
     * @since 2013-06-20 18：44
     */
    public List<VcLevel> queryUpOrgListByUnitCode(String unitCode, int minLevel) throws DaoException {
      
        /////注意：HQL不支持 connect by prior，顾用sql查询   
    	List<VcLevel> levelList=new ArrayList<VcLevel>();
    	try{
	        StringBuffer sql = new StringBuffer();
	        List<Object> params = new ArrayList<Object>();      
	        sql.append(" select ");
	        sql.append("vl.ID_VC_LEVEL ,  vl.UNIT_TYPE  ,  vl.UNIT_CODE  ,  vl.UNIT_NAME  ,  vl.PARENT_ORG_ID, ");
	        sql.append("  vl.COM_TYPE ,  vl.LEVEL_NO     ,  vl.MANAGE_LEVEL ,  vl.VALID_STATUS , vl.LEVEL_CODE "); 
	          
	       
	        sql.append(" from VC_LEVEL vl ");
	        // 级别单位类型unitType --**0=机构;1=员工
	        sql.append(" where vl.UNIT_TYPE = ? ");
	        params.add("0");
	        //有效
	        sql.append(" and vl.VALID_STATUS = ? ");
	        params.add("1");
	        // 显示到树形的哪一级
	        if (minLevel > 0) {
	            sql.append(" and vl.LEVEL_NO <= ? ");
	            params.add(minLevel);
	        }        
	        sql.append(" start with vl.UNIT_CODE=? ");
	        params.add(unitCode);
	        
	        sql.append(" connect by prior vl.PARENT_ORG_ID= vl.ID_VC_LEVEL ");
	        
	         List list=this.findBySql(sql.toString(), params.toArray());
	         
	         VcLevel level=null;
	         if(list!=null && list.size()>0){
	             for(int i=0;i<list.size();i++){
	                 level=new VcLevel();
	                 Object[] obj=(Object[])list.get(i);
	                 level.setId(((BigDecimal)obj[0]).longValue());
	                 level.setUnitType((String)obj[1]);
	                 level.setUnitCode((String)obj[2]);
	                 level.setUnitName((String)obj[3]);
	                 level.setParentOrgId(((BigDecimal)obj[4]).longValue());
	                 level.setComType((String)obj[5]);
	                 level.setLevelNo(((BigDecimal)obj[6]).intValue());
	                 level.setManageLevel(((BigDecimal)obj[7]).intValue());
	                 level.setValidStatus((String)obj[8]);
	                 level.setLevelCode((String)obj[9]);
	                 levelList.add(level);           
	             }
	         }
        }catch(Exception e){
			throw new DaoException("查询数据出错！", e);
		}
        return levelList;
    }
	

	public String getUnitNameByUnitCode(String unitCode) throws DaoException{
		String unitName = "";
		List<VcLevel> vcLevel = null;
		try{
			if (StringUtils.isEmpty(unitCode)) {
				return "";
			}
	
			/*VcLevel vcLevel = this.findUnique("unitCode", unitCode);
			if (vcLevel == null) {
				return "";
			}*/
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("unitCode", unitCode);
			queryRule.addEqual("validStatus", "1");
			vcLevel = (List<VcLevel>)this.find(VcLevel.class, queryRule);
			if(vcLevel.size()!=0){
				unitName = vcLevel.get(0).getUnitName();
			}
		}catch(Exception e){
			throw new DaoException("查询数据库时发生异常！", e);
		}
		return unitName;
	}
	@Override
	public Page queryUsersForSelector(UserVo userVo, int pageNo, int pageSize) throws DaoException {
		Page page = null;
		StringBuffer hql = new StringBuffer();
		try{
			hql.append(" select l1.unitCode,l1.unitName,l2.unitName from VcLevel l1 , VcLevel l2 ");
			hql.append(" where l1.parentOrgId = l2.id ");
			hql.append(" and l1.unitType = ? ");
			hql.append(" and l1.validStatus = ? ");
			
			
			List params = new ArrayList();
			params.add("1");
			params.add("1");
			
			if(Beans.isNotEmpty(userVo.getUserCode())){
				hql.append(" and l1.unitCode like ? ");
				params.add("%"+userVo.getUserCode()+"%");
			}
			if(Beans.isNotEmpty(userVo.getUserName())){
				hql.append(" and l1.unitName like ? ");
				params.add("%"+userVo.getUserName()+"%");
			}
			
			page = this.findByHql(hql.toString(), pageNo, pageSize, params.toArray());
			List<UserVo> users = new ArrayList<UserVo>();
			List<Object[]> result = (List<Object[]>) page.getResult();
			for (Object[] object : result) {
				UserVo userVo_ = new UserVo();
				userVo_.setUserCode((String) object[0]);
				userVo_.setUserName((String) object[1]);
				userVo_.setOrgName((String) object[2]);
				users.add(userVo_);
			}
			page.setData(users);
		}catch(Exception e){
			throw new DaoException("查询数据出错！", e);
		}
		return page;
	}
	
	@Override
	public List<PubCompany> getPubCompanyList(String comAttribute, String upperComCode, String comCode) throws DaoException {
		List<PubCompany> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//机构属性 O机构、D部门、B销售网点
			/*if(StringUtils.isNotEmpty(comAttribute)){
				queryRule.addEqual("comAttribute", comAttribute);
			}*/
			//上级机构代码
			if(StringUtils.isNotEmpty(upperComCode)){
				queryRule.addEqual("upperCompanyCode", upperComCode);
			}
			//机构代码
			if(StringUtils.isNotEmpty(comCode)){
				queryRule.addLike("companyCode", "%"+comCode+"%");
			}
			list = this.find(PubCompany.class,queryRule);
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		return list;
	}
	
	/*@Override
	public List<PubUserDef> getUserList(String comCode, String userCode) throws DaoException {
		List<PubUserDef> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//机构id
			StringBuffer sb = new StringBuffer("");
			sb.append("company_code in (");
			//sb.append(" select c.companyid from pub_company c");
			sb.append(" select c.companycode from pub_company c");
			sb.append(" start with c.companycode='"+comCode+"'");
			sb.append(" connect by c.uppercompanycode=prior c.companycode");
			sb.append(")");
			queryRule.addSql(sb.toString());
			//用户代码
			if(StringUtils.isNotEmpty(userCode)){
				queryRule.addLike("userCode", "%"+userCode+"%");
			}
			list = this.find(PubUserDef.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询用户失败！", e);
		}
		return list;
	}*/
	@Override
	public List<PubUserDef> getUserList(String comCode, String userCode) throws DaoException {
		List<PubUserDef> list = null;
		try{
			//机构id
			StringBuffer sb = new StringBuffer("");
			//sb.append("select user_code,user_name,company_code from Pub_User_Def u where u.valid_flag='1' ");
			sb.append("select employee_id,user_name,company_code from Pub_User_Def u where u.valid_flag='1' ");
			if(!"01".equals(comCode)){
				sb.append(" and exists (");
				sb.append(" select 1 from PUB_COMPANY c ");
				sb.append(" where u.company_code=c.companyCode");
				sb.append(" start with c.companyCode='"+comCode+"'");
				sb.append(" connect by c.uppercompanycode=prior c.companycode");
				sb.append(")");
			}
			//用户代码
			if(StringUtils.isNotEmpty(userCode)){
				sb.append(" and u.user_Code like '%"+userCode+"%'");
			}
			List resultSet = this.findBySql(sb.toString(), null);
			if(resultSet!=null && resultSet.size()>0){
				list = new ArrayList<PubUserDef>();
				PubUserDef pubUserDef = null;
				for(int i=0;i<resultSet.size();i++){
					Object[] obj = (Object[])resultSet.get(i);
					pubUserDef = new PubUserDef();
					//pubUserDef.setUserCode((String)obj[0]);
					pubUserDef.setEmployeeId((String)obj[0]);
					pubUserDef.setUserName((String)obj[1]);
					pubUserDef.setCompanyCode((String)obj[2]);
					list.add(pubUserDef);
				}
			}
		}catch(Exception e){
			throw new DaoException("查询用户失败！", e);
		}
		return list;
	}
	
	@Override
	public Page queryVcAgencyOrgPage(String orgCode, int pageNo, int pageSize) throws DaoException {
		Page page = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("orgCode", orgCode);
			//queryRule.addEqual("status", "1");无效的也要查询出来
			page = this.find(VcAgencyOrg.class, queryRule, pageNo, pageSize);
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		return page;
	}
	
	@Override
	public List<VcAgencyOrg> findVcAgencyOrgByCode(String agencyOrgCode) throws DaoException{
		List<VcAgencyOrg> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("agencyOrgCode", agencyOrgCode);
			list = this.find(VcAgencyOrg.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		return list;
	}
	
	@Override
	public void saveVcAgencyOrg(VcAgencyOrg vcAgencyOrg) throws DaoException {
		try{
			this.save(vcAgencyOrg);
		}catch(Exception e){
			throw new DaoException("中介机构插入数据库失败！", e);
		}
	}
	
	@Override
	public VcAgencyOrg findVcAgencyOrgById(Long id) throws DaoException {
		VcAgencyOrg vcAgencyOrg = null;
		try{
			vcAgencyOrg = (VcAgencyOrg)this.findUnique(VcAgencyOrg.class, "id", id);
		}catch(Exception e){
			throw new DaoException("查找数据库失败！", e);
		}
		return vcAgencyOrg;
	}
	
	@Override
	public void updateVcAgencyOrg(VcAgencyOrg vcAgencyOrg) throws DaoException {
		try{
			this.update(vcAgencyOrg);
		}catch(Exception e){
			throw new DaoException("更新数据库失败！", e);
		}
	}
	
	@Override
	public void deleteVcAgencyOrg(Long id) throws DaoException {
		try{
			this.deleteByPK(VcAgencyOrg.class, id);
		}catch(Exception e){
			throw new DaoException("删除中介机构失败！", e);
		}
	}
	
	@Override
	public Page queryVcTakerPage(String orgCode, int pageNo, int pageSize)
			throws DaoException {
		Page page = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("orgCode", orgCode);
			//queryRule.addEqual("status", "1");无效的也要查询出来
			page = this.find(VcTaker.class, queryRule, pageNo, pageSize);
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		return page;
	}
	
	@Override
	public List<VcAgencyOrg> getVcAgencyOrgList(String comCode) throws DaoException{
		List<VcAgencyOrg> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("orgCode", comCode);
			queryRule.addEqual("status", "1");
			list = this.find(VcAgencyOrg.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		return list;
	}
	
	@Override
	public List<VcTaker> findVcTakerByCode(String takerCode) throws DaoException {
		List<VcTaker> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("takerCode", takerCode);
			list = this.find(VcTaker.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		return list;
	}
	
	@Override
	public List<PubUserDef> findUmUserDef(String userCode, String comCode) throws DaoException {
		List<PubUserDef> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//queryRule.addEqual("userCode", userCode);
			queryRule.addEqual("employeeId", userCode);
			//queryRule.addSql("company_code = (select companyid from pub_company where companycode='"+comCode+"')");
			queryRule.addEqual("companyCode", comCode);
			queryRule.addEqual("validFlag", "1");
			list = this.find(PubUserDef.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		return list;
	}
	
	@Override
	public void saveVcTaker(VcTaker vcTaker) throws DaoException {
		try{
			this.save(vcTaker);
		}catch(Exception e){
			throw new DaoException("使用人插入数据库失败！", e);
		}
	}
	
	@Override
	public VcTaker findVcTakerById(Long id) throws DaoException {
		VcTaker vcTaker = null;
		try{
			vcTaker = (VcTaker)this.findUnique(VcTaker.class, "id", id);
		}catch(Exception e){
			throw new DaoException("查找数据库失败！", e);
		}
		return vcTaker;
	}
	
	@Override
	public void updateVcTaker(VcTaker vcTaker) throws DaoException {
		try{
			this.update(vcTaker);
		}catch(Exception e){
			throw new DaoException("更新数据失败！", e);
		}
	}
	
	@Override
	public void deleteVcTaker(Long id) throws DaoException {
		try{
			this.deleteByPK(VcTaker.class, id);
		}catch(Exception e){
			throw new DaoException("删除使用人失败！", e);
		}
	}
	
    /**
     * 根据机构代码在pub_Company中查找对应的机构
     * 
     * @param companyCode
     * @return
     * @throws DaoException
     *@author whj
     *@since Nov 1, 2013
     */
    public PubCompany findPubCompanyByCode(String companyCode) throws DaoException {
        PubCompany pubCompany = null;
        try {
            QueryRule queryRule = QueryRule.getInstance();
            queryRule.addEqual("companyCode", companyCode);
            queryRule.addEqual("validInd", "1");
            pubCompany = (PubCompany) this.findUnique(PubCompany.class, queryRule);
        } catch (Exception e) {
            throw new DaoException("查询机构失败!", e);
        }
        return pubCompany;
    }
    
   
   /**
    * 根据员工代码在pub_user_def中查找对应的人员
    * @param userCode
    * @return
    * @throws DaoException
    *@author whj
    *@since Nov 4, 2013
    */
    public PubUserDef findPubUserDefByCode(String userCode) throws DaoException {
        PubUserDef pubUserDef = null;
        try {
            QueryRule queryRule = QueryRule.getInstance();
            queryRule.addEqual("employeeId", userCode);
            queryRule.addEqual("validFlag", "1");
            pubUserDef = (PubUserDef) this.findUnique(PubUserDef.class, queryRule);
        } catch (Exception e) {
            throw new DaoException("查询人员失败!", e);
        }
        return pubUserDef;
    }
    
    /**
     * 根据员工代码在pub_user_def中查找对应的人员名称
     * @param userCode
     * @return
     * @throws DaoException
     *@author whj
     *@since Nov 4, 2013
     */
     public String getPubUseDefNameByCode(String userCode) throws DaoException {         
         try {
             PubUserDef pubUserDef = null;
             QueryRule queryRule = QueryRule.getInstance();
             queryRule.addEqual("employeeId", userCode);
             queryRule.addEqual("validFlag", "1");
             pubUserDef = (PubUserDef) this.findUnique(PubUserDef.class, queryRule);
             if(pubUserDef==null){
                 return "";
             }else{
                 return pubUserDef.getUserName();
             }
         } catch (Exception e) {
             throw new DaoException("查询人员失败!", e);
         }
     }
     
     @Override
	public List<PubUserDef> getTakerList(String comCode, String takerCode)
			throws DaoException {
		List<PubUserDef> list = null;
		try {
			StringBuffer hql = new StringBuffer();
			List<Object> values = new ArrayList<Object>();
			hql.append(" from PubUserDef u where 1=1");
			hql.append(" and u.companyCode=?");
			values.add(comCode);
			hql.append(" and (u.employeeId like ? or u.userCode like ?)");
			values.add("%" + takerCode + "%");
			values.add("%" + takerCode + "%");
			hql.append(" and u.validFlag='1'");
			list = this.findByHql(hql.toString(), values.toArray());
		} catch (Exception e) {
			throw new DaoException("查询数据库失败！", e);
		}
		return list;
	}
     
	@Override
	public PubUserDef findVcTakerInfo(String takerCode, String takerName)
			throws DaoException {
		PubUserDef pubUserDef = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("employeeId", takerCode);
			queryRule.addEqual("userName", takerName);
			queryRule.addEqual("validFlag", "1");
			pubUserDef = (PubUserDef)this.findUnique(PubUserDef.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		return pubUserDef;
	}
	
    
    
    /**
     * 变更机构前校验前
     * @param objectCode 机构人员code
     * @param objectFlag 类型  1-机构 2-岗位人员  3-单证使用人
     * @return map  [hasError:boolean, msg:String]
     * @author whj
     * @date 2013-12-11
     */  
    public Map<String, Object> checkBeforeChangeOrg(String objectCode, String objectFlag) throws DaoException {
        
        Map<String,Object> resultMap=new HashMap<String,Object>();
        StringBuffer msg = new StringBuffer();
        boolean hasError = false;
        
        StringBuffer hql = new StringBuffer();
        List<Object> values = new ArrayList<Object>();
        Long count = 0L;
       
        try {
            // /单证使用人删除
            if ("3".equals(objectFlag)) {
                // 可使用表
                hql.append(" SELECT COUNT(1) FROM VcAvailableDoc a ");
                hql.append(" where a.takerCode  = ? ");
                values.add(objectCode);
                count = this.getCount(hql.toString(), values.toArray());
                if (count > 0) {
                    hasError = true;
                    msg.append("当前人员下存在可使用单证，不可进行该操作！\r\n");
                }
            }
            // /单证管理岗位人员删除
            else if ("2".equals(objectFlag)) {
                // 征订申请
                hql.append(" SELECT COUNT(1) FROM VcOrderLaunch o ");
                hql.append(" where o.modifyUserCode  = ? ");
                values.add(objectCode);
                // 征订处理状态（0新建 1审批通过 2审批退回 3采购中 9待审批）
                hql.append(" and o.flag in('0','9','3') ");
                hql.append(" and o.validStatus =? ");
                values.add("1");
                count = this.getCount(hql.toString(), values.toArray());
                if (count > 0) {
                    hasError = true;
                    msg.append("当前人员有单证征订进行中，不可进行该操作！\r\n");
                }

                // 印刷入库申请
                hql.setLength(0);
                values.clear();
                hql.append(" SELECT COUNT(1) FROM VcDocInStore a ");
                hql.append(" where a.applyOprCode  = ? ");
                values.add(objectCode);
                // 入库申请单状态（0删除/1新建/2待审批/3审批退回/4审批通过）
                hql.append(" and a.inStoreStatus in('1','2') ");
                count = this.getCount(hql.toString(), values.toArray());
                if (count > 0) {
                    hasError = true;
                    msg.append("当前人员有印刷入库进行中，不可进行该操作！\r\n");
                }

                // 单证申领
                hql.setLength(0);
                values.clear();
                hql.append(" SELECT COUNT(1) FROM VcApply a ");
                hql.append(" where a.applyOprCode  = ? ");
                values.add(objectCode);
                // 申领单处理状态（0删除/1新建/2等待发放/3发放退回/4等待确认/6已确认/5确认退回）
                hql.append(" and a.applyStatus  in('1','2','4') ");
                count = this.getCount(hql.toString(), values.toArray());
                if (count > 0) {
                    hasError = true;
                    msg.append("当前人员有单证申领进行中，不可进行该操作！\r\n");
                }

                // 单证回收
                hql.setLength(0);
                values.clear();
                hql.append(" SELECT COUNT(1) FROM VcRecycle r ");
                hql.append(" where r.recycleOprCode  = ? ");
                values.add(objectCode);
                // 回收状态0-删除 1-新建 2-待审批 3-审批退回 4-审批通过
                hql.append(" and r.recycleStatus  in('1','2') ");
                count = this.getCount(hql.toString(), values.toArray());
                if (count > 0) {
                    hasError = true;
                    msg.append("当前人员有单证回收进行中，不可进行该操作！\r\n");
                }

                // 遗失申请
                hql.setLength(0);
                values.clear();
                hql.append(" SELECT COUNT(1) FROM VcLost r ");
                hql.append(" where r.lostOprCode  = ? ");
                values.add(objectCode);
                // 遗失处理状态（0删除/1新建/2待审批/3审批退回/4审批通过）
                hql.append(" and r.lostStatus in ('1','2') ");
                count = this.getCount(hql.toString(), values.toArray());
                if (count > 0) {
                    hasError = true;
                    msg.append("当前人员有单证遗失进行中，不可进行该操作！\r\n");
                }

                // 单证作废 申请
                hql.setLength(0);
                values.clear();
                hql.append(" SELECT COUNT(1) FROM VcCancel c ");
                hql.append(" where c.cancelOprCode  = ? ");
                values.add(objectCode);
                // 作废处理状态（0删除/1新建/2待审批/3审批退回/4审批通过）
                hql.append(" and c.cancelStatus  in ('1','2') ");
                count = this.getCount(hql.toString(), values.toArray());
                if (count > 0) {
                    hasError = true;
                    msg.append("当前人员有单证作废申请未完成，不可进行该操作！\r\n");
                }

                // 单证销毁申请
                hql.setLength(0);
                values.clear();
                hql.append(" SELECT COUNT(1) FROM VcDestroy d ");
                hql.append(" where d.destroyOprCode  = ? ");
                values.add(objectCode);
                // 销毁处理状态（0删除/1新建/2待审批/3审批退回/4审批通过）
                hql.append(" and d.destroyStatus  in ('1','2') ");
                count = this.getCount(hql.toString(), values.toArray());
                if (count > 0) {
                    hasError = true;
                    msg.append("当前人员有单证销毁申请未完成，不可进行该操作！\r\n");
                }
            }

            else if ("1".equals(objectFlag)) {
                // 该机构 下是否有子机构或岗位人员
                hql.append(" SELECT COUNT(1) FROM  VcLevel v ");
                hql.append(" where v.parentOrgCode=? ");
                values.add(objectCode);
                count = this.getCount(hql.toString(), values.toArray());
                if (count > 0) {
                    hasError = true;
                    msg.append("当前机构含有子机构或岗位人员，不可进行该操作！\r\n");
                }
                // 出单员
                hql.setLength(0);
                values.clear();
                hql.append(" SELECT COUNT(1) FROM  VcTaker t ");
                hql.append(" where t.orgCode=? ");
                values.add(objectCode);
                count = this.getCount(hql.toString(), values.toArray());
                if (count > 0) {
                    hasError = true;
                    msg.append("当前机构下存在单证使用人，不可进行该操作！\r\n");
                }
            }            
            resultMap.put("hasError", hasError);
            resultMap.put("msg", msg.toString());
            return resultMap;

        } catch (Exception e) {
            throw new DaoException("查询数据库失败！", e);
        }
    }


}
