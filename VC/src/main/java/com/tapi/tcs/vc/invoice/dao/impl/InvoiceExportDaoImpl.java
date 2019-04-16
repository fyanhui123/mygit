package com.tapi.tcs.vc.invoice.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.invoice.dao.InvoiceExportDao;
import com.tapi.tcs.vc.invoice.vo.InvoiceExportVO;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoiceRevoke;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcManageCodeHlj;
import com.tapi.tcs.vc.schema.model.VcTaxPayerLogin;

/**
 * 发票数据导出DAO实现类
 * <p>
 * Date 2013-06-06
 * </p>
 * <p>
 * Module：发票数据导出
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * @author chy
 * @version 1.0
 */
public class InvoiceExportDaoImpl extends GenericDaoHibernate<VcInvoicePrint> implements InvoiceExportDao {

	@Override
	public Page queryInvoiceExport(InvoiceExportVO invoiceExportVO, String comCode, int pageNo,
			int pageSize) throws DaoException {
		Page page = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//拼接单证类型代码查询条件
			//如果选择了险类或险种，则必须拼接单证类型查询条件
			/*if((invoiceExportVO.getInsuTypeId()!=null&&invoiceExportVO.getInsuTypeId()>0)
					|| StringUtils.isNotEmpty(invoiceExportVO.getInsuKindCode())){
				List<String> docVerCodeList = invoiceExportVO.getDocVerCodeList();
				queryRule.addIn("docVerCode", docVerCodeList);
			}*/
			//拼接保单号查询条件
			if(StringUtils.isNotEmpty(invoiceExportVO.getPolicyNo())){
				queryRule.addEqual("policyNo", invoiceExportVO.getPolicyNo());
			}
			//拼接批单号查询条件
			if(StringUtils.isNotEmpty(invoiceExportVO.getEndorseNo())){
				queryRule.addEqual("endorseNo", invoiceExportVO.getEndorseNo());
			}
			//拼接付款方查询条件
			if(StringUtils.isNotEmpty(invoiceExportVO.getPayerName())){
				queryRule.addEqual("payerName", invoiceExportVO.getPayerName());
			}
			//拼接打印日期查询条件
			if(invoiceExportVO.getPrintDateStart()!=null){
				queryRule.addGreaterEqual("printDate", invoiceExportVO.getPrintDateStart());
			}
			if(invoiceExportVO.getPrintDateEnd()!=null){
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(invoiceExportVO.getPrintDateEnd());
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				queryRule.addLessThan("printDate", calendar.getTime());
			}
			//拼接签单机构查询条件
			//贵州只查询当前操作机构发票打印信息
			if (SysConst.COMCODE_GZ.equals(comCode.substring(0, 4))){
				queryRule.addSql(" ORG_CODE IN (" +
						"SELECT P.UNIT_CODE FROM Vc_Level P,VC_LEVEL V " +
						"WHERE V.UNIT_CODE = '"+comCode+"' " +
						"AND V.ID_VC_LEVEL = P.PARENT_ORG_ID " +
						"AND P.UNIT_TYPE = '0' AND P.VALID_STATUS = '1'" +
						")" );
			}
			//黑龙江发票查询特殊处理：分公司只导出分公司直属部门 + 营业部及其所有下级机构；其他中支向下递归
			else if(SysConst.COMCODE_HLJ.equals(comCode)){
				queryRule.addSql(" (ORG_CODE='"+SysConst.COMCODE_HLJ+"' or ORG_CODE IN (" +
						" SELECT UNIT_CODE FROM VC_LEVEL WHERE COM_TYPE='1' AND VALID_STATUS='1' AND PARENT_ORG_ID=(" +
						" SELECT ID_VC_LEVEL FROM VC_LEVEL WHERE UNIT_CODE='"+SysConst.COMCODE_HLJ+"' AND VALID_STATUS='1'))" +
						" or ORG_CODE IN (SELECT UNIT_CODE FROM VC_LEVEL " +
						" WHERE UNIT_TYPE = '0' AND VALID_STATUS='1' " +
						" START WITH UNIT_CODE = '"+SysConst.COMCODE_HLJ_YYB+"' " +
						" CONNECT BY PARENT_ORG_ID = PRIOR ID_VC_LEVEL ))" );
			}else{
//			if(StringUtils.isNotEmpty(invoiceExportVO.getInputCompany())){
//				queryRule.addEqual("orgCode", invoiceExportVO.getInputCompany());
//			}else{
				//如果没有选择签单机构，则查询当前用户所属的机构及其所有下级机构
				queryRule.addSql(" ORG_CODE IN (" +
						" SELECT T.UNIT_CODE " +
						" FROM VC_LEVEL T " +
						" WHERE T.UNIT_TYPE = '0' AND T.VALID_STATUS='1' " +
						" START WITH T.UNIT_CODE = '"+comCode+"' " +
						" CONNECT BY T.PARENT_ORG_ID = PRIOR T.ID_VC_LEVEL " 
						+") ");
			}
			
			//modify by zhxiao3 VC-127北京分公司平谷营服单独采集发票打印信息begin
			//北京分公司平谷营销服务部单独采集,北京分公司导出数据不包含平谷
			if(SysConst.COMCODE_BEIJING.equals(comCode)){
				queryRule.addSql(" ORG_CODE NOT IN (" +
						" SELECT T.UNIT_CODE " +
						" FROM VC_LEVEL T " +
						" WHERE T.UNIT_TYPE = '0' AND T.VALID_STATUS='1' " +
						" START WITH T.UNIT_CODE = '"+SysConst.COMCODE_BJ_PINGGU+"' " +
						" CONNECT BY T.PARENT_ORG_ID = PRIOR T.ID_VC_LEVEL " 
						+") ");
			}
			//modify by zhxiao3 VC-127北京分公司平谷营服单独采集发票打印信息end
			
			//拼接状态查询条件
			if(StringUtils.isNotEmpty(invoiceExportVO.getInvoiceStatus())){
				queryRule.addEqual("isUploadPlat", invoiceExportVO.getInvoiceStatus());
			}
			//发票代码
			if(StringUtils.isNotEmpty(invoiceExportVO.getInvoiceCode())){
				queryRule.addEqual("invoiceCode", invoiceExportVO.getInvoiceCode());
			}
			//只能导出有效的数据
			queryRule.addEqual("status", "1");
			page = this.find(VcInvoicePrint.class, queryRule, pageNo, pageSize);
		}catch(Exception de){
			throw new DaoException("发票数据导出查询数据库失败！");
		}
		return page;
	}
	
	@Override
	public List<VcInvoicePrint> queryInvoiceExport(InvoiceExportVO invoiceExportVO, String comCode) throws DaoException {
		List<VcInvoicePrint> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//拼接单证类型代码查询条件
			/*List<String> docVerCodeList = invoiceExportVO.getDocVerCodeList();
			if(docVerCodeList!=null && docVerCodeList.size()>0){
				queryRule.addIn("docVerCode", docVerCodeList);
			}*/
			//拼接保单号查询条件
			if(StringUtils.isNotEmpty(invoiceExportVO.getPolicyNo())){
				queryRule.addEqual("policyNo", invoiceExportVO.getPolicyNo());
			}
			//拼接批单号查询条件
			if(StringUtils.isNotEmpty(invoiceExportVO.getEndorseNo())){
				queryRule.addEqual("endorseNo", invoiceExportVO.getEndorseNo());
			}
			//拼接付款方查询条件
			if(StringUtils.isNotEmpty(invoiceExportVO.getPayerName())){
				queryRule.addEqual("payerName", invoiceExportVO.getPayerName());
			}
			//拼接打印日期查询条件
			if(invoiceExportVO.getPrintDateStart()!=null){
				queryRule.addGreaterEqual("printDate", invoiceExportVO.getPrintDateStart());
			}
			if(invoiceExportVO.getPrintDateEnd()!=null){
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(invoiceExportVO.getPrintDateEnd());
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				queryRule.addLessThan("printDate", calendar.getTime());
			}
			//拼接签单机构查询条件
			//贵州只查询导出当前操作机构发票打印信息
			if (SysConst.COMCODE_GZ.equals(comCode.substring(0, 4))){
				queryRule.addSql(" ORG_CODE IN (" +
						"SELECT P.UNIT_CODE FROM Vc_Level P,VC_LEVEL V " +
						"WHERE V.UNIT_CODE = '"+comCode+"' " +
						"AND V.ID_VC_LEVEL = P.PARENT_ORG_ID " +
						"AND P.UNIT_TYPE = '0' AND P.VALID_STATUS = '1'" +
						")" );
			}
			//黑龙江发票查询特殊处理
			else if(SysConst.COMCODE_HLJ.equals(comCode)){
				queryRule.addSql(" (ORG_CODE='"+SysConst.COMCODE_HLJ+"' or ORG_CODE IN (" +
						" SELECT UNIT_CODE FROM VC_LEVEL WHERE COM_TYPE='1' AND VALID_STATUS='1' AND PARENT_ORG_ID=(" +
						" SELECT ID_VC_LEVEL FROM VC_LEVEL WHERE UNIT_CODE='"+SysConst.COMCODE_HLJ+"' AND VALID_STATUS='1'))" +
						" or ORG_CODE IN (SELECT UNIT_CODE FROM VC_LEVEL " +
						" WHERE UNIT_TYPE = '0' AND VALID_STATUS='1' " +
						" START WITH UNIT_CODE = '"+SysConst.COMCODE_HLJ_YYB+"' " +
						" CONNECT BY PARENT_ORG_ID = PRIOR ID_VC_LEVEL ))" );
			}else{
//			if(StringUtils.isNotEmpty(invoiceExportVO.getInputCompany())){
//				queryRule.addEqual("orgCode", invoiceExportVO.getInputCompany());
//			}else{
				//如果没有选择签单机构，则查询当前用户所属的机构及其所有下级机构
				queryRule.addSql(" ORG_CODE IN (" +
						" SELECT T.UNIT_CODE " +
						" FROM VC_LEVEL T " +
						" WHERE T.UNIT_TYPE = '0' AND T.VALID_STATUS='1' " +
						" START WITH T.UNIT_CODE = '"+comCode+"' " +
						" CONNECT BY T.PARENT_ORG_ID = PRIOR T.ID_VC_LEVEL " 
						+") ");
			}
			
			//modify by zhxiao3 VC-127北京分公司平谷营服单独采集发票打印信息begin
			//北京分公司平谷营销服务部单独采集,北京分公司导出数据不包含平谷
			if(SysConst.COMCODE_BEIJING.equals(comCode)){
				queryRule.addSql(" ORG_CODE NOT IN (" +
						" SELECT T.UNIT_CODE " +
						" FROM VC_LEVEL T " +
						" WHERE T.UNIT_TYPE = '0' AND T.VALID_STATUS='1' " +
						" START WITH T.UNIT_CODE = '"+SysConst.COMCODE_BJ_PINGGU+"' " +
						" CONNECT BY T.PARENT_ORG_ID = PRIOR T.ID_VC_LEVEL " 
						+") ");
			}
			//modify by zhxiao3 VC-127北京分公司平谷营服单独采集发票打印信息
			
			//拼接状态查询条件
			if(StringUtils.isNotEmpty(invoiceExportVO.getInvoiceStatus())){
				queryRule.addEqual("isUploadPlat", invoiceExportVO.getInvoiceStatus());
			}
			//只能导出有效的数据
			queryRule.addEqual("status", "1");
			
			//重庆按时间排序 lfengxia begin
			if(SysConst.COMCODE_ChongQing.equals(comCode)){
				queryRule.addAscOrder("printDate");
			}
			//重庆按时间排序 lfengxia end
			list = this.find(VcInvoicePrint.class, queryRule);
		}catch(Exception de){
			throw new DaoException("发票数据导出查询数据库失败！");
		}
		return list;
	}
	
	@Override
	public List<String> queryDocVersionCode(InvoiceExportVO invoiceExportVO) throws DaoException {
		List<String> list = null;
		try{
			StringBuffer sql = new StringBuffer("");
			sql.append(" select distinct v.doc_ver_code from vc_doc_rel_insu_kind t,vc_doc_insu_kind i,vc_doc_version_info v");
			sql.append(" where t.insu_kind_code=i.insu_kind_code and t.id_vc_doc_version_info=v.id_vc_doc_version_info");
			if(invoiceExportVO.getInsuTypeId()!=null && invoiceExportVO.getInsuTypeId()>0){
				sql.append(" and i.id_vc_doc_insu_type=" + invoiceExportVO.getInsuTypeId());
			}
			if(StringUtils.isNotEmpty(invoiceExportVO.getInsuKindCode())){
				sql.append(" and t.insu_kind_code='"+invoiceExportVO.getInsuKindCode()+"'");
			}
			list = this.findBySql(sql.toString(), null);
		}catch(Exception e){
			throw new DaoException("查询数据库失败！");
		}
		return list;
	}
	
	@Override
	public List<VcLevel> getAllChildCompany(String comCode) throws DaoException {
		List<VcLevel> list = null;
		try{
			String sql = "select UNIT_CODE,UNIT_NAME from VC_LEVEL t where t.UNIT_TYPE='0' " +
					"start with t.UNIT_CODE=? connect by t.PARENT_ORG_ID=prior t.ID_VC_LEVEL";
			Object[] params = {comCode};
			List<Object[]> resultList = this.findBySql(sql, params);
			if(resultList!=null && resultList.size()>0){
				list = new ArrayList<VcLevel>();
				for(Object[] obj : resultList){
					VcLevel vcLevel = new VcLevel();
					vcLevel.setUnitCode((String)obj[0]);
					vcLevel.setUnitName((String)obj[1]);
					list.add(vcLevel);
				}
			}
		}catch(Exception e){
			throw new DaoException("查询数据库时发生异常！");
		}
		return list;
	}
	
	@Override
	public List<VcInvoicePrint> getVcInvoicePrintListByIds(Long[] idList) throws DaoException {
		List<VcInvoicePrint> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addIn("id", idList);
			//只能导出有效的数据
			queryRule.addEqual("status", "1");
			list = this.find(VcInvoicePrint.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询数据库时发生异常！");
		}
		return list;
	}
	
	@Override
	public void updateIsUploadPlat(Long id, String userCode) throws DaoException {
		try{
			/*String sql = "update VC_INVOICE_PRINT set IS_UPLOAD_PLAT='1'," +
					" UPDATED_BY='"+userCode+"',DATE_UPDATED=sysdate" +
					" where ID_VC_INVOICE_PRINT in ("+ids+")";*/
			List values = new ArrayList();
			String sql = "update VC_INVOICE_PRINT set IS_UPLOAD_PLAT=?," +
				" UPDATED_BY=?,DATE_UPDATED=?" +
				" where ID_VC_INVOICE_PRINT =?";
			values.add("1");
			values.add(userCode);
			values.add(new Date());
			values.add(id);
			this.executeUpdateBySql(sql, values.toArray());
		}catch(Exception e){
			throw new DaoException("更新数据库失败！");
		}
	}

	@Override
	public void updateVcInvoicePrint(List<VcInvoicePrint> vcInvoicePrintList) throws DaoException {
		try{
			this.update(vcInvoicePrintList);
		}catch(Exception e){
			throw new DaoException("更新数据库失败！");
		}
	}
	
	/**
	 * 
	 * @param queryVo 根据条件查询发票开具信息表（不包含机构条件）
	 * @return
	 * @throws DaoException
	 * @author wanghuajian
	 */
    public List<VcInvoicePrint> queryVcInvoicePrintList(VcInvoicePrint queryVo) throws DaoException {
        try{
            QueryRule queryRule = QueryRule.getInstance();
           
          //拼接保单号查询条件
            if(StringUtils.isNotEmpty(queryVo.getPolicyNo())){
                queryRule.addEqual("policyNo", queryVo.getPolicyNo());
            }
            //拼接批单号查询条件
            if(StringUtils.isNotEmpty(queryVo.getEndorseNo())){
                queryRule.addEqual("endorseNo", queryVo.getEndorseNo());
            }
            //拼接发票代码查询条件
            if(StringUtils.isNotEmpty(queryVo.getInvoiceCode())){
                queryRule.addEqual("invoiceCode", queryVo.getInvoiceCode());
            }
            //拼接发票号码查询条件
            if(StringUtils.isNotEmpty(queryVo.getInvoiceNo())){
                queryRule.addEqual("invoiceNo", queryVo.getInvoiceNo());
            }
            //拼接付款方查询条件
            if(StringUtils.isNotEmpty(queryVo.getPayerName())){
                queryRule.addEqual("payerName", queryVo.getPayerName());
            }
           
            //只能导出有效的数据
            queryRule.addEqual("status", "1");
           return this.find(queryRule);
        }catch(Exception de){
            throw new DaoException("发票数据查询数据库失败！");
        }
       
    }
    
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
    public List<VcInvoiceRevoke> findVcInvoiceRevoke(String orgCode,Date startDate, Date endDate, String isUploadPlat) throws DaoException {
    	List<VcInvoiceRevoke> list = null;
    	try{
    		QueryRule queryRule = QueryRule.getInstance();
    		//机构代码
    		queryRule.addLike("orgCode", orgCode+"%");
    		//未上传平台
    		if(!"".equals(isUploadPlat)){
    			queryRule.addEqual("isUploadPlat", isUploadPlat);
    		}
    		
    		//登记日期
			queryRule.addGreaterEqual("registerDate", startDate);
			queryRule.addLessThan("registerDate", DateUtils.addDay(endDate, +1));
    		//有效状态
    		queryRule.addEqual("status", "1");
    		list = (List<VcInvoiceRevoke>)this.find(VcInvoiceRevoke.class, queryRule);
    	}catch(Exception e){
    		throw new DaoException("查询发票缴销信息出错！", e);
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
            throw new DaoException("查询贵州地税信息出错！", e);
        }
        return codeCname;
    }
    
    @Override
	public VcManageCodeHlj findVcManageCodeHljByOrgCode(String orgCode) throws DaoException {
		VcManageCodeHlj vcManageCodeHlj = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("orgCode", orgCode);
			List<VcManageCodeHlj> list = (List<VcManageCodeHlj>)this.find(VcManageCodeHlj.class, queryRule);
			if(list!=null && list.size()!=0){
				vcManageCodeHlj = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询管理码配置表出错！", e);
		}
		return vcManageCodeHlj;
	}
    
}
