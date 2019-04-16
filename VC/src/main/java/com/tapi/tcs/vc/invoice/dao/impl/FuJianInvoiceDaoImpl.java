package com.tapi.tcs.vc.invoice.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.invoice.dao.FuJianInvoiceDao;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoiceRevoke;
import com.tapi.tcs.vc.schema.model.VcTaxAuth;
import com.tapi.tcs.vc.schema.model.VcTaxPayerInfo;
import com.tapi.tcs.vc.schema.model.VcTaxPayerLogin;
import com.tapi.tcs.vc.schema.model.VcInvoicePurchase;

public class FuJianInvoiceDaoImpl  extends GenericDaoHibernate<VcTaxPayerLogin> implements FuJianInvoiceDao {

	@Override
	public VcTaxPayerLogin findVcTaxPayerLogin(String orgCode) throws DaoException {
		VcTaxPayerLogin vcTaxPayerLogin = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("orgCode", orgCode);
			queryRule.addEqual("status", "1");
			List<VcTaxPayerLogin> list = this.find(VcTaxPayerLogin.class, queryRule);
			if(list!=null && list.size()>0){
				vcTaxPayerLogin = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询纳税人登录信息出错！", e);
		}
		return vcTaxPayerLogin;
	}
	
	@Override
	public VcTaxPayerInfo findVcTaxPayerInfo(String computerNo) throws DaoException {
		VcTaxPayerInfo vcTaxPayerInfo = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("computerNo", computerNo);
			queryRule.addEqual("status", "1");
			List<VcTaxPayerInfo> list = this.find(VcTaxPayerInfo.class, queryRule);
			if(list!=null && list.size()>0){
				vcTaxPayerInfo = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询纳税人基本信息出错！", e);
		}
		return vcTaxPayerInfo;
	}
	
	@Override
	public VcInvoicePurchase getVcInvoicePurchaseBySerialNo(String serialNo) throws DaoException {
		VcInvoicePurchase vcInvoicePurchase = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("serialNo", serialNo);
			queryRule.addEqual("status", "1");
			vcInvoicePurchase = (VcInvoicePurchase)this.findUnique(VcInvoicePurchase.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询领购信息出错！",e);
		}
		return vcInvoicePurchase;
	}
	
	@Override
	public List<VcInvoicePurchase> findVcInvoicePurchaseList(String computerNo) throws DaoException{
		List<VcInvoicePurchase> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("computerNo", computerNo);
			queryRule.addEqual("status", "1");
			list = (List<VcInvoicePurchase>)this.find(VcInvoicePurchase.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查找领购信息出错！", e);
		}
		return list;
	}
	
	@Override
	public List<VcTaxAuth> findVcTaxAuthByShortCode(String invoiceShortCode) throws DaoException {
		List<VcTaxAuth> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("invoiceShortCode", invoiceShortCode);
			list = (List<VcTaxAuth>)this.find(VcTaxAuth.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查找授权信息出错！", e);
		}
		return list;
	}
	
	@Override
	public List<VcTaxPayerLogin> findVcTaxPayerLoginList() throws DaoException {
		List<VcTaxPayerLogin> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addLike("orgCode", SysConst.COMCODE_FJ+"%");
			queryRule.addEqual("status", "1");
			list = (List<VcTaxPayerLogin>)this.find(VcTaxPayerLogin.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询纳税人登录信息异常！", e);
		}
		return list;
	}
	
	@Override
	public List<VcInvoicePrint> findVcInvoicePrintNoUpload(String computerNo) throws DaoException {
		List<VcInvoicePrint> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//纳税人电脑编码
			queryRule.addEqual("computerNo", computerNo);
			//未上传平台
			queryRule.addNotEqual("isUploadPlat", "1");
			//有效
			queryRule.addEqual("status", "1");
			list = (List<VcInvoicePrint>)this.find(VcInvoicePrint.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询待上传的发票信息异常！", e);
		}
		return list;
	}
	
	@Override
	public List<VcInvoiceRevoke> findVcInvoiceRevokeNoUpload(String computerNo) throws DaoException {
		List<VcInvoiceRevoke> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//纳税人电脑编码
			queryRule.addEqual("computerNo", computerNo);
			//未上传平台
			queryRule.addNotEqual("isUploadPlat", "1");
			//有效
			queryRule.addEqual("status", "1");
			list = (List<VcInvoiceRevoke>)this.find(VcInvoiceRevoke.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询待上传的发票缴销信息异常！", e);
		}
		return list;
	}
	
	@Override
	public VcInvoicePurchase findVcInvoicePurchase(VcInvoicePrint vcInvoicePrint) throws DaoException {
		VcInvoicePurchase vcInvoicePurchase = null;
		try{
		    StringBuffer sb =new StringBuffer();
            List<Object> values=new ArrayList<Object>();
            sb.append("from VcInvoicePurchase p ");
            ////单证类型代码
            sb.append(" where p.docVerCode=? ");
            values.add(vcInvoicePrint.getDocVerCode());
          //发票代码
            sb.append(" and p.invoiceCode=? ");
            values.add(vcInvoicePrint.getInvoiceCode());
          //发票区间段
            sb.append(" and to_number(p.startNum)<= ? ");
            values.add(Long.valueOf(vcInvoicePrint.getInvoiceNo()));
            sb.append("  and to_number(p.endNum)>= ? ");
            values.add(Long.valueOf(vcInvoicePrint.getInvoiceNo()));
          //已入库
            sb.append(" and p.inStoreFlag=? ");
            values.add("1");
          //有效
            sb.append(" and p.status=? ");
            values.add("1");			
			List<VcInvoicePurchase> list = (List<VcInvoicePurchase>)this.findByHql(sb.toString(), values.toArray());
			if(list!=null && list.size()>0){
				vcInvoicePurchase = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询领购信息异常！", e);
		}
		return vcInvoicePurchase;
	}
	
	@Override
	public VcInvoicePurchase findVcInvoicePurchase(VcInvoiceRevoke vcInvoiceRevoke) throws DaoException {
		VcInvoicePurchase vcInvoicePurchase = null;
		try{
			StringBuffer sb =new StringBuffer();
			List<Object> values=new ArrayList<Object>();
			sb.append("from VcInvoicePurchase p ");
			sb.append(" where p.docVerCode=? ");
			values.add(vcInvoiceRevoke.getDocVerCode());
			sb.append(" and p.invoiceCode=? ");
			values.add(vcInvoiceRevoke.getInvoiceCode());
			
			sb.append(" and to_number(p.startNum)<= ? ");
			values.add(Long.valueOf(vcInvoiceRevoke.getStartNum()));
			sb.append("  and to_number(p.endNum)>= ? ");
			values.add(Long.valueOf(vcInvoiceRevoke.getEndNum()));
			
			sb.append(" and p.inStoreFlag=? ");
			values.add("1");
			sb.append(" and p.status=? ");
			values.add("1");
            
            List<VcInvoicePurchase> list = (List<VcInvoicePurchase>)this.findByHql(sb.toString(), values.toArray());
            if(list!=null && list.size()>0){
                vcInvoicePurchase = list.get(0);
            }
		}catch(Exception e){
			throw new DaoException("查询领购信息异常！", e);
		}
		return vcInvoicePurchase;
	}
	
	@Override
	public void updateVcInvoicePrintList(List<Long> vcInvoicePrintList, String isUploadPlat) throws DaoException {
		try{
			if(vcInvoicePrintList!=null && vcInvoicePrintList.size()>0){
				String hql = "update VcInvoicePrint print set print.isUploadPlat=?," +
						"print.updatedBy=?,print.dateUpdated=? where print.id=?";
				for(Long id:vcInvoicePrintList){
					this.executeUpdate(hql, new Object[]{isUploadPlat, "auto", new Date(), id});
				}
			}
		}catch(Exception e){
			throw new DaoException("更新发票开具信息表异常！", e);
		}
	}
	
	@Override
	public void updateVcInvoiceRevokeList(List<Long> vcInvoiceRevokeList, String isUploadPlat) throws DaoException {
		try{
			if(vcInvoiceRevokeList!=null && vcInvoiceRevokeList.size()>0){
				String hql = "update VcInvoiceRevoke revoke set revoke.isUploadPlat=?," +
						"revoke.updatedBy=?,revoke.dateUpdated=? where revoke.id=?";
				for(Long id : vcInvoiceRevokeList){
					this.executeUpdate(hql, new Object[]{isUploadPlat, "auto", new Date(), id});
				}
			}
		}catch(Exception e){
			throw new DaoException("更新发票缴销信息表异常！", e);
		}
	}
}
