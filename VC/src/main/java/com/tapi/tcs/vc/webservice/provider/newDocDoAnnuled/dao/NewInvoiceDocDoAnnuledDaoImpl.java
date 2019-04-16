package com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.dao;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
public class NewInvoiceDocDoAnnuledDaoImpl extends GenericDaoHibernate<VcAbnormalVerification> implements NewInvoiceDocDoAnnuledDao {

	@Override
	public VcAbnormalVerification findVcAbNormalVerification(String docVerCode,
			String docNum, String pressBatchNo, String businessNo) throws BusinessException {
		VcAbnormalVerification vcAbNormalVerification = null;
		try {
			List<VcAbnormalVerification> list;
			StringBuffer hql = new StringBuffer("");
			hql.append(" from VcAbnormalVerification v where v.docVerCode=?");
			hql.append(" and v.docNum=? ");
			if(StringUtils.isNotEmpty(pressBatchNo)){
				hql.append(" and v.pressBatchNo='"+pressBatchNo+"'");
			}
			hql.append(" and exists(select 1 from VcAbnormalVerifiedDet det where " +
					"det.vcAbnormalVerification=v.idVcAbnormalVerification ");
			if(StringUtils.isNotEmpty(businessNo)){
				hql.append(" and det.businessNo=? )");
			}else{
				hql.append(" )");
			}
			hql.append(" and v.docStatus in (?,?,?)");
			if(StringUtils.isNotEmpty(businessNo)){
			 list = (List<VcAbnormalVerification>)this.findByHql(hql.toString(), docVerCode,docNum,businessNo,"C1","C2","C3");
			}else{
			 list = (List<VcAbnormalVerification>)this.findByHql(hql.toString(), docVerCode,docNum,"C1","C2","C3");
			}
			if(list!=null && list.size()>0	){
				vcAbNormalVerification = list.get(0);
			}
		} catch (Exception e) {
			throw new BusinessException("查询作废发票异常");
		}
		return vcAbNormalVerification;
	}


}
