package com.tapi.tcs.vc.rolelist.dao.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.security.authority.User;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.rolelist.dao.RoleListDao;
import com.tapi.tcs.vc.rolelist.vo.RoleListVo;
import com.tapi.tcs.vc.schema.model.PubUserDef;
public class RoleListDaoImpl extends GenericDaoHibernate<PubUserDef> implements RoleListDao{

	@Override
	public Object queryRoleList(RoleListVo roleListVo, UserInfo userInfo, int pageNo,
			int pageSize ,String queryType) throws DaoException {
		List<RoleListVo> resultList = new ArrayList<RoleListVo>();
		try {
			List<Object> values = new ArrayList<Object>();
			StringBuilder sql_a = new StringBuilder(100);
			sql_a.append(" select rownum as rowno,t1.parent_org_code,");
			sql_a.append("(");
			sql_a.append("select t2.unit_name from vc_level t2 where t2.unit_code = t1.parent_org_code");
			sql_a.append(")");
			sql_a.append(",(select t4.unit_name from vc_level t4 where t4.unit_code in (");
			sql_a.append("select t6.company_code from pub_user_def t6 where t6.employee_id=t1.unit_code)),");
			sql_a.append("t1.unit_name");
			sql_a.append(",(select t8.user_code  from pub_user_def t8 where t1.unit_code = t8.employee_id)");
			sql_a.append(",t1.unit_code,t1.valid_status  from vc_level t1 where t1.unit_type='1'");
			if(roleListVo.getValidKind().equals("2")){
			}else{
				if(StringUtils.isNotEmpty(roleListVo.getValidKind())){
					sql_a.append("and t1.valid_status = ? ");
					values.add(roleListVo.getValidKind());
				}
			}
			if(roleListVo.getCompanyCode().equals(null)||roleListVo.getCompanyCode().equals("")){
				sql_a.append("and t1.parent_org_code like ?");
				values.add(userInfo.getComCode()+"%");
			}else{
				if(StringUtils.isNotEmpty(roleListVo.getUnderCode())){
					if(StringUtils.isNotEmpty(roleListVo.getCompanyCode())){
						sql_a.append("and t1.parent_org_code like ?");
						values.add(roleListVo.getCompanyCode()+"%");
					}
				}else{
					if(StringUtils.isNotEmpty(roleListVo.getCompanyCode())){
						sql_a.append("and t1.parent_org_code = ?");
						values.add(roleListVo.getCompanyCode());
					}
				}
			}
			 if("1".equals(queryType)){
			 StringBuffer sql_select = new StringBuffer();
			 sql_select.append("select count(*) from(").append(sql_a);
			 sql_select.append(")");
			 Long totalCount = 0L;
			 List tempCount = this.findBySql(sql_select.toString(), values.toArray());
			 Object countObj = tempCount.get(0);
	         totalCount = ((BigDecimal) countObj).longValue();
			StringBuilder sql_count = new StringBuilder(100);
			sql_count.append("select * from(").append(sql_a);
			sql_count.append(") tableView where tableView.rowno between ? and ? ");
			values.add((pageNo - 1) * pageSize + 1);
			values.add(pageNo * pageSize);
			List list = this.findBySql(sql_count.toString(), values.toArray());
			if (list != null && list.size() > 0) {
				RoleListVo tempVo = null;
				for (int i = 0; i < list.size(); i++) {
					tempVo = new RoleListVo();
					Object[] obj = (Object[]) list.get(i);
					tempVo.setCompanyCode((String) obj[1]);
					tempVo.setUnitName((String) obj[2]);
					tempVo.setDepartMent((String) obj[3]);
					tempVo.setUserName((String) obj[4]);
					tempVo.setUserCode((String) obj[5]);
					tempVo.setEmployeeId((String) obj[6]);
					tempVo.setValidKind((String) obj[7]);
					resultList.add(tempVo);
				}
				}
			Page page = new Page(pageNo, pageSize, totalCount, resultList);
			return page;
			 }else if("2".equals(queryType)){
			     List list = this.findBySql(sql_a.toString(), values.toArray());
			 	 if (list != null && list.size() > 0) {
					RoleListVo tempVo = null;
					for (int i = 0; i < list.size(); i++) {
						tempVo = new RoleListVo();
						Object[] obj = (Object[]) list.get(i);
						tempVo.setCompanyCode((String) obj[1]);
						tempVo.setUnitName((String) obj[2]);
						tempVo.setDepartMent((String) obj[3]);
						tempVo.setUserName((String) obj[4]);
						tempVo.setUserCode((String) obj[5]);
						tempVo.setEmployeeId((String) obj[6]);
						tempVo.setValidKind((String) obj[7]);
						resultList.add(tempVo);
					}
					}
			 	return resultList;
			 }else{
				 return null;
			 }
		} catch (Exception e) {
			throw new DaoException("查询数据库异常！", e);
		}
		
	 }

	@Override
	public Object queryRoleTakerList(RoleListVo roleListVo, UserInfo userInfo,
			int pageNo, int pageSize, String queryType) throws DaoException {
		List<RoleListVo> resultList = new ArrayList<RoleListVo>();
		try {
			StringBuilder sql_a = new StringBuilder(100);
			List<Object> values = new ArrayList<Object>();
			sql_a.append("select rownum as rowno,t.org_code,");
			sql_a.append("(select t9.unit_name from vc_level t9  where t.org_code=t9.unit_code and t9.valid_status = 1 and t9.unit_type='0'),");
			sql_a.append("t.taker_code,t.taker_name,(");
			sql_a.append("select t8.user_code from pub_user_def t8 where t.taker_code = t8.employee_id");
			sql_a.append("),t.status  from vc_taker t where 1=1 ");
			if(roleListVo.getValidKind().equals("2")){
			}else{
			if(StringUtils.isNotEmpty(roleListVo.getValidKind())){
				sql_a.append("and t.status = ? ");
				values.add(roleListVo.getValidKind());
			}
			}
			if(roleListVo.getCompanyCode().equals(null)||"".equals(roleListVo.getCompanyCode())){
				sql_a.append("and t.org_code like ?");
				values.add(userInfo.getComCode()+"%");
			}else{
				if(StringUtils.isNotEmpty(roleListVo.getUnderCode())){
					if(StringUtils.isNotEmpty(roleListVo.getCompanyCode())){
						sql_a.append("and t.org_code like ?");
						values.add(roleListVo.getCompanyCode()+"%");
					}
					
				}else{
					if(StringUtils.isNotEmpty(roleListVo.getCompanyCode())){
						sql_a.append("and t.org_code = ?");
						values.add(roleListVo.getCompanyCode()
								);
					}
				}
			}
			
 			 if("1".equals(queryType)){
				StringBuffer sql_select = new StringBuffer();
				sql_select.append("select count(*) from(").append(sql_a);
				sql_select.append(")");
			    Long totalCount = 0L;
				List tempCount = this.findBySql(sql_select.toString(), values.toArray());
				Object countObj = tempCount.get(0);
		        totalCount = ((BigDecimal) countObj).longValue();
				StringBuilder sql_count = new StringBuilder(100);
				sql_count.append("select * from(").append(sql_a);
				sql_count.append(") tableView where tableView.rowno between ? and ? ");
				values.add((pageNo - 1) * pageSize + 1);
				values.add(pageNo * pageSize);
				List list = this.findBySql(sql_count.toString(), values.toArray());
				if (list != null && list.size() > 0) {
					RoleListVo tempVo = null;
					for (int i = 0; i < list.size(); i++) {
						tempVo = new RoleListVo();
						Object[] obj = (Object[]) list.get(i);
						tempVo.setCompanyCode((String) obj[1]);
						tempVo.setUnitName((String) obj[2]);
						tempVo.setEmployeeId((String) obj[3]);
						tempVo.setUserName((String) obj[4]);
						tempVo.setUserCode((String) obj[5]);
						tempVo.setValidKind((String) obj[6]);
						resultList.add(tempVo);
					}
					}
				Page page = new Page(pageNo, pageSize, totalCount, resultList);
				return page;
			 }else if("2".equals(queryType)){
				 List list = this.findBySql(sql_a.toString(), values.toArray());
			 	 if (list != null && list.size() > 0) {
					RoleListVo tempVo = null;
					for (int i = 0; i < list.size(); i++) {
						tempVo = new RoleListVo();
						Object[] obj = (Object[]) list.get(i);
						tempVo.setCompanyCode((String) obj[1]);
						tempVo.setUnitName((String) obj[2]);
						tempVo.setEmployeeId((String) obj[3]);
						tempVo.setUserName((String) obj[4]);
						tempVo.setUserCode((String) obj[5]);
						tempVo.setValidKind((String) obj[6]);
						resultList.add(tempVo);
					}
					}
			 	return resultList;
			 }else{
				 return null;
			 }
		} catch (Exception e) {
			throw new DaoException("查询数据库异常！", e);
		}
	}
}
	

	


