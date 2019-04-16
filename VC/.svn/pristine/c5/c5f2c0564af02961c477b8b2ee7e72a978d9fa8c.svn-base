package com.tapi.tcs.vc.webservice.provider.docDoUsed.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.dao.DocDoUsedDao;

public class DocDoUsedDaoImpl  extends GenericDaoHibernate<VcNormalVerification>  implements DocDoUsedDao{
      
	@Override
	public void deleteVcAvailableDocList(List<VcAvailableDoc> vcAvailableDocList)
			throws DaoException {
		// TODO Auto-generated method stub
		//核销接口优化 20150918 by lfengxia begin
		try{
			List values = new ArrayList();
			String sql ="delete from VC_AVAILABLE_DOC where ID_VC_AVAILABLE_DOC=?";
			for(VcAvailableDoc vcAvailableDoc : vcAvailableDocList){
			this.getHibernateTemplate().lock(vcAvailableDoc, LockMode.FORCE);
			values.add(vcAvailableDoc.getId());
			int rowNum = this.executeUpdateBySql(sql, values.toArray());
			if(rowNum == 0){
				throw new DaoException("该单证流水号已被占用");
			}
			}					
			//this.deleteAll(vcAvailableDocList);
		}catch (DaoException e) {
			throw e;
			////核销接口优化 20150918 by lfengxia end
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("删除可使用表时发生异常！");
		}
	}

	@Override
	public void saveVcNormalVerificationList(
			List<VcNormalVerification> vcNormalVerificationList)
			throws DaoException {
		// TODO Auto-generated method stub
		try{
			this.saveAll(vcNormalVerificationList);
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("插入核销表时发生异常！");
		}
	}

	@Override
	public void updateVcNormalVerificationList(VcNormalVerification vc) throws DaoException {
		try {
			this.update(vc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("更新核销表时发生异常！");
		}
	}

	@Override
	public List<Object[]> checkVcNormal(String businessNo, String versionCode)
			throws DaoException {
		List<Object[]> vcNormalVerificationList = null;
		StringBuffer hql = new StringBuffer("");
		hql.append(" from VcNormalVerification nv,VcDocVersionInfo ver");
		hql.append(" where nv.docVerCode=ver.docVerCode");
		hql.append(" and ver.idVcDocType=(");
		hql.append(" select t.idVcDocType from VcDocVersionInfo t where t.docVerCode=?");
		hql.append(" )");
		//modify by chy 20130810
		//hql.append(" and nv.businessNo=?");
		hql.append(" and exists(select 1 from VcNormalVerifiedDet det where " +
				"det.idVcNormalVerification=nv.idVcNormalVerification and det.businessNo=?)");
		//modify by chy 20130810 end
		try{
			vcNormalVerificationList = this.findByHql(hql.toString(), versionCode,businessNo);
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("查询业务号是否核销时异常！");
		}
		return vcNormalVerificationList; 
	}
	
	@Override
	public List<Object[]> queryVcNormal(String businessNo, String docVerCode,
			 String docStatus) throws DaoException {
		List<Object[]> vcNormalList = null;
		StringBuffer sb = new StringBuffer("");
		Object[] values = new Object[] { businessNo,docVerCode,docStatus };
		sb.append(" from VcNormalVerification nv,VcDocVersionInfo ver where nv.docVerCode=ver.docVerCode  ");
		sb.append(" and nv.businessNo=?");
		sb.append(" and nv.docVerCode=?");
	//	sb.append(" and nv.docNum=?");
		sb.append(" and nv.docStatus=?");
		try{
			vcNormalList = this.findByHql(sb.toString(), values);
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("查询业务号是否核销时异常！");
		}
		return vcNormalList; 
	}
	
}
