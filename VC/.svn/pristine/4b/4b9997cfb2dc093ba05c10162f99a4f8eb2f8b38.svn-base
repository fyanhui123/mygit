package com.tapi.tcs.vc.invoice.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.invoice.dao.InvoicePurchaseDao;
import com.tapi.tcs.vc.invoice.vo.InvoiceDocVersionDTO;
import com.tapi.tcs.vc.schema.model.VcDocInStore;
import com.tapi.tcs.vc.schema.model.VcInvoicePurchase;
import com.tapi.tcs.vc.schema.model.VcTaxPayerLogin;

public class InvoicePurchaseDaoImpl extends
		GenericDaoHibernate<VcDocInStore> implements InvoicePurchaseDao {

	@Override
	public Page queryInvoicePurchaseBaseInfo(String companyNo, int pageNo,
			int pageSize) throws DaoException {
		try {
			// 机构代码
			String hsql = "from VcTaxPayerInfo v where v.computerNo=? ";
			List values = new ArrayList();
			values.add(companyNo);
			return this.findByHql(hsql.toString(), pageNo, pageSize, values
					.toArray());
		} catch (Exception e) {
			throw new DaoException("查询数据库异常！", e);
		}
	}

	@Override
	public Page queryInvoicePurchasePurchase(String nsrdnbm, int pageNo,
			int pageSize) throws DaoException {
		try {
			// 纳税人电脑编码
			String hsql = "from VcInvoicePurchase v where v.computerNo=? ";
			List values = new ArrayList();
			values.add(nsrdnbm);
			return this.findByHql(hsql.toString(), pageNo, pageSize, values
					.toArray());
		} catch (Exception e) {
			throw new DaoException("查询数据库异常！", e);
		}
	}

	@Override
	public String searchCompanyNoByOrgCode(String orgCode) throws DaoException {
		String companyNO = null;
		try {
			VcTaxPayerLogin vcTaxPayerLogin = new VcTaxPayerLogin();
			List<VcTaxPayerLogin> list = null;
			QueryRule queryRule = QueryRule.getInstance();
			// 机构代码
			queryRule.addEqual("orgCode", orgCode);
			list = this.find(VcTaxPayerLogin.class, queryRule);
			if (list != null && list.size() > 0) {
				vcTaxPayerLogin = list.get(0);
				companyNO = vcTaxPayerLogin.getComputerNo();
			}
		} catch (Exception e) {
			throw new DaoException("查询数据出错！", e);
		}
		return companyNO;
	}

	@Override
	public Page queryInvoicePurchaseImpower(String shortCode,
			int pageNo, int pageSize) throws DaoException {
		try {
			// 发票简码代码
			String hsql = "from VcTaxAuth v where v.invoiceShortCode=? ";
			List values = new ArrayList();
			values.add(shortCode);
			return this.findByHql(hsql.toString(), pageNo, pageSize, values
					.toArray());
		} catch (Exception e) {
			throw new DaoException("查询数据库异常！", e);
		}
	}

	@Override
	public Page queryInvoicePurchaseImpowerDet(String ordId, int pageNo,
			int pageSize) throws BusinessException {
		try {
			// 授权流水号
			Long ordIdId = Long.parseLong(ordId);
			String hsql = "from VcTaxAuthDetail v where v.vcTaxAuth.id=? ";
			List values = new ArrayList();
			values.add(ordIdId);
			return this.findByHql(hsql.toString(), pageNo, pageSize, values
					.toArray());
		} catch (Exception e) {
			throw new DaoException("查询数据库异常！", e);
		}
	}

	@Override
	public Page queryInvoiceDocVersion(
			String orgCode,InvoiceDocVersionDTO invoiceDocVersionDTO, int pageNo, int pageSize)
			throws BusinessException {
		try {
			
			 List<String> orgCodeList = null;
             String orgSql = "SELECT Z.UNIT_CODE  FROM VC_LEVEL Z  WHERE Z.UNIT_TYPE = ?  START WITH Z.UNIT_CODE=? "
                     + " CONNECT BY  Z.ID_VC_LEVEL =PRIOR  Z.PARENT_ORG_ID";
             
             orgCodeList = this.findBySql(orgSql, new Object[]{"0",orgCode});           
     		  
			StringBuilder sb = new StringBuilder("");
			List<Object> values = new ArrayList<Object>();
			// 只查询出财务类单证且单证是启用状态
			sb.append(" from  VcDocVersionInfo vc,VcDocRelArea area,VcDocType doc where vc.idVcDocVersionInfo=area.idVcDocVersionInfo and vc.status='1' ");
			sb.append(" and  vc.idVcDocType=doc.idVcDocType  and  doc.docType='1' ");
			if (StringUtils.isNotBlank(invoiceDocVersionDTO.getDocVerCode())) {
				sb.append(" and  vc.docVerCode like ? ");//单证类型代码
				values.add("%"+invoiceDocVersionDTO.getDocVerCode()+"%");
			}
			if (StringUtils.isNotBlank(invoiceDocVersionDTO.getDocVerName())) {
				sb.append(" and  vc.docVerName like ? ");//单证类型名称
				values.add("%"+invoiceDocVersionDTO.getDocVerName()+"%");
			}
			
			sb.append("  and area.orgCode in(?) ");//机构列表
			values.add(orgCodeList);
			
           /*
			if (StringUtils.isNotBlank(invoiceDocVersionDTO.getOrgCode())) {
				sb.append(" and  area.orgCode=?");
				values.add(invoiceDocVersionDTO.getOrgCode()); // 适用地区
			}
			*/
			// 单证种类
			if (StringUtils.isNotBlank(invoiceDocVersionDTO.getDocTypeCode())) {
			//	sb.append(" and exists(select 1 from  VcDocType type where  type.idVcDocType=vc.idVcDocType ) ");
				sb.append(" and  doc.docTypeCode=? ");
				values.add(invoiceDocVersionDTO.getDocTypeCode());
			}
			return this.findByHql(sb.toString(), pageNo, pageSize, values.toArray());
		} catch (Exception e) {
			throw new DaoException("查询数据库异常！", e);
		}
	}

	/**
	 * 保存印刷入库信息
	 * 
	 * @param vcDocInStore
	 *            印刷入库信息主信息
	 * @param vcDocInStoreDets
	 *            印刷入库信息明细信息
	 */
	@Override
	public void inStoreInvoiceDocVersion(VcDocInStore vcDocInStore) throws DaoException{
		try{
		     this.save(vcDocInStore);
		 }catch(Exception e){
	    	   throw new DaoException("添加数据异常！",e);
	     }
	}
    /*****
     *   根据纳税人领购信息流水查询发票简码代码
     */
	@Override
	public VcInvoicePurchase queryPurchaseInfo(String purId) throws DaoException {
	  try{
		  VcInvoicePurchase vcTaxPurchase =null;
		List<VcInvoicePurchase> list = null;
		QueryRule queryRule = QueryRule.getInstance();
		Long  numberNO=Long.parseLong(purId);
		// 纳税人领购信息流水
		queryRule.addEqual("id", numberNO);
		list = this.find(VcInvoicePurchase.class, queryRule);
		if (list != null && list.size() > 0) {
			vcTaxPurchase = list.get(0);
		} 
		return vcTaxPurchase;
	   }catch (Exception e) {
			throw new DaoException("查询数据库异常！", e);
		}
	}

	@Override
	public void updatePurchaseInfo(String formatDate,String userCode,String  docVerCode,String purchaseId) throws DaoException {
		 try{
			 // 更新领购信息表	
		    String sql = " update  vc_invoice_purchase  t set  t.updated_by='"+userCode+"' , t.date_updated=to_date('"+formatDate+"','YYYY-MM-DD HH24:MI:SS') " +
		      		" , t.in_store_opr='"+userCode+"' ,  t.in_store_date=to_date('"+formatDate+"','YYYY-MM-DD HH24:MI:SS')  , t.in_store_flag='1' , t.doc_ver_code='"+docVerCode+"'  where  t.id_vc_invoice_purchase='"+purchaseId+"'";

			  this.executeUpdateBySql(sql, null);
			}catch(Exception e){
		    	   throw new DaoException("查询数据库异常！",e);
		     }
	}
	
	
}
