package com.tapi.tcs.vc.account.dao.impl;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.account.dao.DocNumAccountDao;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcBisDocNumAcct;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;

public class DocNumAccountDaoImpl extends GenericDaoHibernate<VcBisDocNumAcct> implements DocNumAccountDao{
	
	public void saveBisDocNumAcctList(List<VcBisDocNumAcct> list) throws DaoException{
		try{
			super.saveAll(list);
		}catch(Exception e){
			throw new DaoException("导入对账文件时异常！", e);
		}
	}
	
	
	public List<VcNormalVerification> queryBisDocNumIsUsed(VcBisDocNumAcct vcBisDocNumAcct) throws DaoException {
		
		List<VcNormalVerification> list = null;
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("docVerCode", vcBisDocNumAcct.getDocVerCode());
		queryRule.addEqual("docNum", vcBisDocNumAcct.getDocNum());
		queryRule.addEqual("verifiedOrgCode", vcBisDocNumAcct.getOrgCode());
		try{
			list = this.find(VcNormalVerification.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询流水号是否核销时异常！", e);
		}
		return list;
	}
	
	public List<VcAbnormalVerification> queryBisDocNumAnnuld(VcBisDocNumAcct vcBisDocNumAcct) throws DaoException {
		List<VcAbnormalVerification> list = null;
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("docVerCode", vcBisDocNumAcct.getDocVerCode());
		queryRule.addEqual("docNum", vcBisDocNumAcct.getDocNum());
		queryRule.addEqual("verifiedOrgCode", vcBisDocNumAcct.getOrgCode());
		try{
			list = this.find(VcAbnormalVerification.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询流水号是否核销时异常！", e);
		}
		return list;
	}
	
    public String findCodeCname(String codeType, String codeCode) throws DaoException {
        String codeCname = "";
        try {
            String sql = "select c.CODE_C_NAME from VC_PUB_CODE c,VC_PUB_CODE_TYPE ct"
                    + " where c.CODE_TYPE=ct.CODE_TYPE and c.STATUS='1'"
                    + " and ct.STATUS='1' and ct.CODE_TYPE='" + codeType + "'" + " and c.CODE_CODE='"
                    + codeCode + "'";
            List list = this.findBySql(sql, null);
            if (list != null && list.size() > 0) {
                codeCname = (String) list.get(0);
            }
        } catch (Exception e) {
            throw new DaoException("查询FTP连接信息出错！", e);
        }
        return codeCname;
    }
    
    public List<VcBisDocNumAcct> findBisDocNumListForAcct() throws DaoException {
    	List<VcBisDocNumAcct> list = null;
    	QueryRule queryRule = QueryRule.getInstance();
    	queryRule.addIsNull("acctStatus");
    	try{
			list = this.find(VcBisDocNumAcct.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询对账流水号信息时异常！", e);
		}
        return list;
    }
    
    public List<VcBisDocNumAcct> findBisDocNumList(VcBisDocNumAcct tempVo) throws DaoException {
    	List<VcBisDocNumAcct> list = null;
    	QueryRule queryRule = QueryRule.getInstance();
    	queryRule.addEqual("docVerCode",tempVo.getDocVerCode());
    	queryRule.addEqual("docNum",tempVo.getDocNum());
    	queryRule.addEqual("orgCode",tempVo.getOrgCode());
    	queryRule.addEqual("docStatus",tempVo.getDocStatus());
    	try{
			list = this.find(VcBisDocNumAcct.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询对账流水号信息时异常！", e);
		}
        return list;
    }
    
    
}
