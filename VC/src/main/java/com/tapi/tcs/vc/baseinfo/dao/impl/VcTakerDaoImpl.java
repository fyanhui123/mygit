package com.tapi.tcs.vc.baseinfo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcTakerDao;
import com.tapi.tcs.vc.schema.model.PubCode;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcTaker;
import org.omg.CORBA.OBJ_ADAPTER;

public class VcTakerDaoImpl extends GenericDaoHibernate<VcTaker> implements VcTakerDao {

	
	
	@Override
	public VcLevel queryOrgName(String takerCode) throws DaoException{
		try{
			StringBuffer hql = new StringBuffer();
			hql.append(" select l from VcLevel l,VcTaker t ");
			hql.append("where t.orgCode = l.unitCode ");
			hql.append(" and t.takerCode = ? ");
			
			List result = super.findByHql(hql.toString(), takerCode);
			if(result.size()>0){
				VcLevel vcLevel = (VcLevel) result.get(0);
				return vcLevel;
			}
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		return null;
	}
	
	public String getUnitNameByUnitCode(String takerCode) throws DaoException{
		if (StringUtils.isEmpty(takerCode)) {
			return "";
		}

		VcTaker vctaker = this.findUnique("takerCode", takerCode);
		if (vctaker == null) {
			return "";
		}
		return vctaker.getTakerName();
	}
	
	@Override
	public List<VcTaker> getTakerJson(String takerCode, String orgCode) throws DaoException {
			List<VcTaker> lists = null;
			try{
				//queryRule.addEqual("orgCode", orgCode);
				//查询本级及下级机构
				List<Object> values=new ArrayList<Object>();
				StringBuffer sb = new StringBuffer(" select t.taker_code,t.taker_name,t.org_code,t.status,t.created_by,t.date_updated,t.updated_by,t.date_updated ,t.agency_org_code,t.taker_type_code,t.passbook_or_card,t.bank_account,t.bank_account_name,t.bank_code,t.bank_name from vc_taker t where 1=1 ");
				if(StringUtils.isNotEmpty(takerCode)){
					sb.append(" and t.taker_code like ?");
					values.add("%"+takerCode+"%");
				}
				sb.append(" and (t.org_code= ? ");
				values.add(orgCode);
				sb.append(" or t.org_code in (select unit_code from vc_level v  where v.unit_type = '0' and v.valid_status = '1' and v.parent_org_id =(select id_vc_level from vc_level where unit_code = ? and valid_status='1'))) ");
				values.add(orgCode);
				sb.append(" and t.status='1' ");
				List<Object[]> list = this.findBySql(sb.toString(), values.toArray());
				if (list != null && list.size() > 0) {
					lists = new ArrayList<VcTaker>();
					for (Object[] obj : list) {
						VcTaker vctaker = new VcTaker();
						vctaker.setTakerCode((String) obj[0]);
						vctaker.setTakerName((String) obj[1]);
						lists.add(vctaker);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				throw new DaoException("查询数据库失败！");
			}
			return lists;
	}
	
	/**
	 * 根据条件查找可使用人
	 * @param queryDto
	 * @return
	 * @throws DaoException
	 *@author whj
	 *@since Mar 6, 2014
	 */
	@Override
    public List<VcTaker> queryVcTakerList(VcTaker queryDto) throws DaoException {
        List<VcTaker> list = null;
        try{
            QueryRule queryRule = QueryRule.getInstance();
            if(StringUtils.isNotEmpty(queryDto.getTakerCode())){
                queryRule.addEqual("takerCode", queryDto.getTakerCode());
            }
            if(StringUtils.isNotEmpty(queryDto.getTakerName())){
                queryRule.addLike("takerName", "%"+queryDto.getTakerName()+"%");
            }
            if(StringUtils.isNotEmpty(queryDto.getStatus())){
                queryRule.addEqual("status", queryDto.getStatus());
            }
            if(StringUtils.isNotEmpty(queryDto.getOrgCode())){
                queryRule.addEqual("orgCode", queryDto.getOrgCode());
            }
            list = this.find(VcTaker.class, queryRule);
        }catch(Exception e){
            e.printStackTrace();
            throw new DaoException("查询数据库失败！");
        }
        return list;
    }
	
	
	@Override
	public List<VcTaker> getJsonVcTaker(String codeValue, String orgCode) throws DaoException {
		List<VcTaker> lists = new ArrayList<VcTaker>();
		try{
			 List<Object> values=new ArrayList<Object>();
			 StringBuffer sb = new StringBuffer(" select t.* from VC_TAKER t  where 1=1  ");
			 sb.append( " and t.org_code in (" +
					" SELECT Z.UNIT_CODE  FROM VC_LEVEL Z "+
					" WHERE Z.UNIT_TYPE = ? " +
					" START WITH Z.UNIT_CODE = ? "+
					" CONNECT BY Z.PARENT_ORG_ID = PRIOR Z.ID_VC_LEVEL ) "+
					" and t.STATUS = 1");
			values.add("0"); 
			values.add(orgCode);
			List<Object[]> list = this.findBySql(sb.toString(), values.toArray());
            if (list != null && list.size() > 0) {
            	lists = new ArrayList<VcTaker>();
                for (Object[] obj : list) {
                	VcTaker vctaker = new VcTaker();
                	vctaker.setTakerCode((String) obj[1]);
                	vctaker.setTakerName((String) obj[2]);
                	lists.add(vctaker);
                }
            }
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("查询数据库失败！");
		}
		return lists;
	}
	
	/**
	 * 根据单证使用人code获取对象
	 * @param takerCode
	 * @return
	 * @throws DaoException
	 * add by whj 20130918
	 */
    public VcTaker getTakerByTakerCode(String takerCode) throws DaoException {
        if (StringUtils.isEmpty(takerCode)) {
            return null;
        }
        try {
            return this.findUnique("takerCode", takerCode);
        } catch (Exception e) {
            throw new DaoException("查询数据库失败！");
        }
    }
    
    @Override
    public PubCompany findCompanyNameByCode(String companyCode) throws DaoException {
    	PubCompany pubCompany = null;
    	try{
	    	QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("companyCode", companyCode);
			queryRule.addEqual("validInd", "1");
			pubCompany = (PubCompany)this.findUnique(PubCompany.class, queryRule);
    	}catch(Exception e){
    		throw new DaoException("查询机构失败!", e);
    	}
    	return pubCompany;
    }
    
    @Override
    public List<PubCode> queryPubCodeList(String codeType, String codeCode, String remark) throws DaoException {
    	List<PubCode> list = null;
    	try{
    		QueryRule queryRule = QueryRule.getInstance();
    		queryRule.addEqual("id.codeType", codeType);
    		queryRule.addEqual("validind", "1");
    		if(StringUtils.isNotEmpty(remark)){
    			queryRule.addEqual("remark", remark);
    		}
    		if(StringUtils.isNotEmpty(codeCode)){
    			queryRule.addEqual("id.codeCode", codeCode);
    		}
    		list = this.find(PubCode.class, queryRule);
    	}catch(Exception e){
    		throw new DaoException("查询基础数据失败!", e);
    	}
    	return list;
    }
    @Override
    public List<PubCode> queryPubCodeForbusinessSource(String codeType,String channelDetailCode) throws DaoException {
    	List<PubCode> list = null;
		List<Object> values =new ArrayList<Object>();
        StringBuffer sb=new StringBuffer();
    	try{
    		sb.append(" from PubCode t");
    		sb.append(" where t.id.codeType = ?");//'BusinessSource'
			values.add(codeType);
			sb.append(" and t.id.codeCode in (");
			sb.append(" select f.id.outerCodeType from PubCodeTransfer f");
			sb.append(" where f.id.configCode = ?");//'BusinessSource'
			values.add(codeType);
			sb.append(" and f.id.innerCode = ?");
			values.add(channelDetailCode);
			sb.append(" and f.outerCode = ? )");
			values.add("1");
			list = (List<PubCode>)this.findByHql(sb.toString(), values.toArray());
    	}catch(Exception e){
    		throw new DaoException("查询基础数据失败!", e);
    	}
    	return list;
    }
    
    
    @Override
    public List<PubCompany> querySaleOrgCodeList(String companyCode) throws DaoException {
    	List<PubCompany> list = null;
    	try{
    		QueryRule queryRule = QueryRule.getInstance();
    		queryRule.addLike("companyCode", companyCode+"%");
    		queryRule.addEqual("validInd", "1");
    		queryRule.addSql(" length(companyCode) = 10");
    		queryRule.addAscOrder("companyCode");
    		list = this.find(PubCompany.class, queryRule);
    	}catch(Exception e){
    		throw new DaoException("查询基础数据失败!", e);
    	}
    	return list;
    }
}
