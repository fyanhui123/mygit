package com.tapi.tcs.vc.invoice.dao.impl;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.lob.SerializableClob;
import org.hibernate.transform.Transformers;
import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.invoice.dao.VCInvoiceUploadTaxPlatDao;
import com.tapi.tcs.vc.invoice.vo.guizhou.InvoiceGuiZhouUploadDetailDTO;
import com.tapi.tcs.vc.invoice.vo.guizhou.InvoiceGuiZhouUploadExtDTO;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcPubCode;

public class VCInvoiceUploadTaxPlatDaoImpl extends GenericDaoHibernate<VcInvoicePrint> implements VCInvoiceUploadTaxPlatDao {

	//设置发票数据生成XML格式数据的SQL
	private String invoiceXMLSql;
	
	//生成上传地税需要使用的用户帐户数据
	private String loginPlatXMLSql;
	
	//上传地税的用户帐户总数查询SQL
    private String loginPlatCountSql;
	
	
	private static final String UPDATE_IS_PLAT_FLAG_SQL = "UPDATE VC_INVOICE_PRINT SET IS_UPLOAD_PLAT='1',DATE_UPDATED=sysdate  WHERE INVOICE_CODE=? AND INVOICE_NO=?";
	
	/**
	 * 生成发票XML格式数据
	 * 数据来源vc_invoice_print,vc_invoice_print_ext
	 * @return 发票XML格式数据
	 */
	@SuppressWarnings("unchecked")
	public String generateInvoiceToXML(Object... args) throws DaoException,BusinessException{
		String sql= this.invoiceXMLSql;
		Session session = getSession();
		SQLQuery query = session.createSQLQuery(sql);  
		query.setString(0, args[0].toString());
		query.setString(1, args[1].toString());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		List<Map> list =query.list();    
		
		StringBuilder builder = new StringBuilder(3000);
		if (list !=null && list.size() > 0) {
			for(Map map : list) {
				//Map<String,Object> map2 = (Map<String,Object>)map;
				InvoiceGuiZhouUploadDetailDTO gzUploadDto = getInvoiceDto(map);
				String xml = DTOtoXML(gzUploadDto, InvoiceGuiZhouUploadDetailDTO.class.getName());
				builder.append(xml);
			}
		}
		return builder.toString();
	}
	

	/**
	 * 生成xml发票报文
	 * @param obj
	 * @param className
	 * @return
	 * @throws BusinessException
	 */
	private String DTOtoXML(Object obj, String className) throws BusinessException{
		String xml = "";
		try{
			JAXBContext jc = JAXBContext.newInstance(new Class[]{Class.forName(className)});
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);

			StringWriter sw = new StringWriter();
			m.marshal(obj, sw);
			xml = sw.toString();
			xml = xml.substring(xml.indexOf("\n")+1);
			//System.out.println(xml);
			sw.close();
		}catch(Exception e){
			throw new BusinessException("贵州发票上传_转换报文失败！", e);
		}
		return xml;
	}
	
	/**
	 * 格式化日期
	 * @param date
	 * @return
	 */
	private String fmtDate(Object date){
		String rtnDate = "";
		String fmtPattern = "yyyy-MM-dd";
		if (date != null) {
			SimpleDateFormat fmt = new SimpleDateFormat(fmtPattern);
			rtnDate = fmt.format(date);			
		}
		return rtnDate;
	}

	/**
	 * 组装贵州发票dto
	 * @param map
	 * @return
	 */
	public InvoiceGuiZhouUploadDetailDTO getInvoiceDto(Map<String,Object> map){
		InvoiceGuiZhouUploadDetailDTO dtoDetail = new InvoiceGuiZhouUploadDetailDTO();
		InvoiceGuiZhouUploadExtDTO dtoExt = new InvoiceGuiZhouUploadExtDTO();
		
		if(map.get("DATE_UPDATED") != null) dtoDetail.setDateUpdated(fmtDate(map.get("DATE_UPDATED")));
		if(map.get("UPDATED_BY") != null) dtoDetail.setUpdatedBy(map.get("UPDATED_BY").toString());
		if(map.get("DATE_CREATED") != null) dtoDetail.setDateCreated(fmtDate(map.get("DATE_CREATED")));
		if(map.get("CREATED_BY") != null) dtoDetail.setCreatedBy(map.get("CREATED_BY").toString());
		if(map.get("IS_UPLOAD_PLAT") != null) dtoDetail.setIsUploadPlat(map.get("IS_UPLOAD_PLAT").toString());
		if(map.get("STATUS") != null) dtoDetail.setStatus(map.get("STATUS").toString());
		if(map.get("ITEMS") != null) dtoDetail.setItems(map.get("ITEMS").toString());
		if(map.get("INVOICE_TYPE") != null) dtoDetail.setInvoiceType(map.get("INVOICE_TYPE").toString());
		if(map.get("INVOICE_KIND_NAME") != null) dtoDetail.setInvoiceKindName(map.get("INVOICE_KIND_NAME").toString());
		if(map.get("INVOICE_KIND_CODE") != null) dtoDetail.setInvoicekindCode(map.get("INVOICE_KIND_CODE").toString());
		if(map.get("BANK_CODE") != null) dtoDetail.setBankCode(map.get("BANK_CODE").toString());
		if(map.get("ZG_INDUSTRY") != null) dtoDetail.setZgIndustry(map.get("ZG_INDUSTRY").toString());
		if(map.get("REGISTRATION") != null) dtoDetail.setRegistration(map.get("REGISTRATION").toString());
		if(map.get("BANK") != null) dtoDetail.setBank(map.get("BANK").toString());
		if(map.get("ACCOUNT") != null) dtoDetail.setAccount(map.get("ACCOUNT").toString());
		if(map.get("PRINT_TYPE_CODE") != null) dtoDetail.setPrintTypeCode(map.get("PRINT_TYPE_CODE").toString());
		if(map.get("INDUSTRY_NAME") != null) dtoDetail.setIndustryName(map.get("INDUSTRY_NAME").toString());
		if(map.get("INDUSTRY_CODE") != null) dtoDetail.setIndustryCode(map.get("INDUSTRY_CODE").toString());
		if(map.get("UPPER_AMOUNT") != null) dtoDetail.setUpperAmount(map.get("UPPER_AMOUNT").toString());
		if(map.get("TAX_RATE") != null) dtoDetail.setTaxRate(map.get("TAX_RATE").toString());
		if(map.get("TAX_AMOUNT") != null) dtoDetail.setTaxAmount(map.get("TAX_AMOUNT").toString());
		if(map.get("CHECK_NUM") != null) dtoDetail.setCheckNum( map.get("CHECK_NUM").toString());
		if(map.get("END_DATE") != null) dtoDetail.setEndDate(fmtDate(map.get("END_DATE")));
		if(map.get("START_DATE") != null) dtoDetail.setStartDate(fmtDate(map.get("START_DATE")));
		if(map.get("MACHINE_PRINTED_NO") != null) dtoDetail.setMachinePrintedNo(map.get("MACHINE_PRINTED_NO").toString());
		if(map.get("MACHINE_PRINTED_CODE") != null) dtoDetail.setMachinePrintedCode(map.get("MACHINE_PRINTED_CODE").toString());
		if(map.get("BELONG_STAGE") != null) dtoDetail.setBelongStage(map.get("BELONG_STAGE").toString());
		if(map.get("SETTLE_ITEM") != null) dtoDetail.setSettleItem(map.get("SETTLE_ITEM").toString());
		if(map.get("MANAGE_CODE") != null) dtoDetail.setManageCode(map.get("MANAGE_CODE").toString());
		if(map.get("TAX_ORG") != null) dtoDetail.setTaxOrg(map.get("TAX_ORG").toString());
		if(map.get("PRINT_KIND") != null) dtoDetail.setPrintKind(map.get("PRINT_KIND").toString());
		if(map.get("OPERATE_DATE") != null) dtoDetail.setOperateDate(fmtDate(map.get("OPERATE_DATE")));
		if(map.get("REMARK") != null) dtoDetail.setRemark(map.get("REMARK").toString());
		if(map.get("CANCELED_DATE") != null) dtoDetail.setCanceledDate(fmtDate(map.get("CANCELED_DATE")));
		if(map.get("CANCELED_OPR_NAME") != null) dtoDetail.setCanceledOprName(map.get("CANCELED_OPR_NAME").toString());
		if(map.get("COUNTERACTED_INVOICE_NO") != null) dtoDetail.setCounteractedInvoiceNo(map.get("COUNTERACTED_INVOICE_NO").toString());
		if(map.get("COUNTERACTED_INVOICE_CODE") != null) dtoDetail.setCounteractedInvoiceCode(map.get("COUNTERACTED_INVOICE_CODE").toString());
		if(map.get("COUNTERACT_INVOICE_NO") != null) dtoDetail.setCounteractInvoiceNo(map.get("COUNTERACT_INVOICE_NO").toString());
		if(map.get("COUNTERACT_INVOICE_CODE") != null) dtoDetail.setCounteractInvoiceCode(map.get("COUNTERACT_INVOICE_CODE").toString());
		if(map.get("RECIPIENT_OPR") != null) dtoDetail.setRecipientOpr(map.get("RECIPIENT_OPR").toString());
		if(map.get("BILLER_NAME") != null) dtoDetail.setBillerName(map.get("BILLER_NAME").toString());
		if(map.get("TAXPAYER_CODE") != null) dtoDetail.setTaxpayerCode(map.get("TAXPAYER_CODE").toString());
		if(map.get("TAXPAYER_NAME") != null) dtoDetail.setTaxpayerName(map.get("TAXPAYER_NAME").toString());
		if(map.get("RECIPIENT_CODE") != null) dtoDetail.setRecipientCode(map.get("RECIPIENT_CODE").toString());
		if(map.get("RECIPIENT_NAME") != null) dtoDetail.setRecipientName(map.get("RECIPIENT_NAME").toString());
		if(map.get("PAYER_CODE") != null) dtoDetail.setPayerCode(map.get("PAYER_CODE").toString());
		if(map.get("PAYER_NAME") != null) dtoDetail.setPayerName(map.get("PAYER_NAME").toString());
		if(map.get("AMOUNT") != null) dtoDetail.setAmount(map.get("AMOUNT").toString());
		if(map.get("PRINT_DATE") != null) dtoDetail.setPrintDate(fmtDate(map.get("PRINT_DATE")));
		if(map.get("INVOICE_NO") != null) dtoDetail.setInvoiceNo(map.get("INVOICE_NO").toString());
		if(map.get("INVOICE_NAME") != null) dtoDetail.setInvoiceName(map.get("INVOICE_NAME").toString());
		if(map.get("INVOICE_CODE") != null) dtoDetail.setInvoiceCode(map.get("INVOICE_CODE").toString());
		if(map.get("ENDORSE_NO") != null) dtoDetail.setEndorseNo(map.get("ENDORSE_NO").toString());
		if(map.get("POLICY_NO") != null) dtoDetail.setPolicyNo(map.get("POLICY_NO").toString());
		if(map.get("ORG_CODE") != null) dtoDetail.setOrgCode(map.get("ORG_CODE").toString());
		if(map.get("DOC_VER_CODE") != null) dtoDetail.setDocVerCode(map.get("DOC_VER_CODE").toString());
		if(map.get("ID_VC_INVOICE_PRINT") != null) dtoDetail.setIdVcInvoicePrint( map.get("ID_VC_INVOICE_PRINT").toString());
		if(map.get("ORIGION_ID") != null) dtoDetail.setOrigionId(map.get("ORIGION_ID").toString());
		if(map.get("CANCELED_OPR_CODE") != null) dtoDetail.setCanceledOprCode(map.get("CANCELED_OPR_CODE").toString());
		if(map.get("COMPUTER_NO") != null) dtoDetail.setComputerNo(map.get("COMPUTER_NO").toString());

		//-------------------------VC_INVOICE_PRINT_EXTT begin------------------------------------
		if(map.get("EXT_APPLY_NAME") != null) dtoExt.setApplyName(map.get("EXT_APPLY_NAME").toString());
		if(map.get("EXT_PREMIUM") != null) dtoExt.setPremium(map.get("EXT_PREMIUM").toString());
		if(map.get("EXT_RISK_NAME") != null) dtoExt.setRiskName(map.get("EXT_RISK_NAME").toString());
		if(map.get("EXT_ID_VC_INVOICE_PRINT") != null) dtoExt.setIdVcInvoicePrint(map.get("EXT_ID_VC_INVOICE_PRINT").toString());
		if(map.get("EXT_ID_VC_INVOICE_PRINT_EXT") != null) dtoExt.setIdVcInvoicePrintExt(map.get("EXT_ID_VC_INVOICE_PRINT_EXT").toString());
		if(map.get("EXT_DATE_UPDATED") != null) dtoExt.setDateUpdated(fmtDate(map.get("EXT_DATE_UPDATED")));
		if(map.get("EXT_UPDATED_BY") != null) dtoExt.setUpdatedBy(map.get("EXT_UPDATED_BY").toString());
		if(map.get("EXT_DATE_CREATED") != null) dtoExt.setDateCreated(fmtDate(map.get("EXT_DATE_CREATED")));
		if(map.get("EXT_CREATED_BY") != null) dtoExt.setCreatedBy(map.get("EXT_CREATED_BY").toString());
		if(map.get("EXT_FORCURR_AMOUNT") != null) dtoExt.setForcurrAmount(map.get("EXT_FORCURR_AMOUNT").toString());
		if(map.get("EXT_QUOTEPRICE_DATE") != null) dtoExt.setQuotepriceDate(fmtDate(map.get("EXT_QUOTEPRICE_DATE")));
		if(map.get("EXT_EXCHANGE_RATE") != null) dtoExt.setExchangeRate(map.get("EXT_EXCHANGE_RATE").toString());
		if(map.get("EXT_CURRENCY") != null) dtoExt.setCurrency(map.get("EXT_CURRENCY").toString());
		if(map.get("EXT_LATE_FEE") != null) dtoExt.setLateFee(map.get("EXT_LATE_FEE").toString());
		if(map.get("EXT_TAX_AMOUNT") != null) dtoExt.setTaxAmount(map.get("EXT_TAX_AMOUNT").toString());
		if(map.get("EXT_PAY_DATE_END") != null) dtoExt.setPayDateEnd(fmtDate(map.get("EXT_PAY_DATE_END")));
		if(map.get("EXT_PAY_DATE_START") != null) dtoExt.setPayDateStart( fmtDate(map.get("EXT_PAY_DATE_START")));
		if(map.get("EXT_FRAME_NO") != null) dtoExt.setFrameNo(map.get("EXT_FRAME_NO").toString());
		if(map.get("EXT_ENGINE_NO") != null) dtoExt.setEngineNo(map.get("EXT_ENGINE_NO").toString());
		if(map.get("EXT_AMOUNT") != null) dtoExt.setAmount(map.get("EXT_AMOUNT").toString());
		if(map.get("EXT_ITEM") != null) dtoExt.setItem(map.get("EXT_ITEM").toString());
		if(map.get("EXT_USE_NATURE") != null) dtoExt.setUseNature(map.get("EXT_USE_NATURE").toString());
		if(map.get("EXT_LAST_END_DATE") != null) dtoExt.setLastEndDate(fmtDate(map.get("EXT_LAST_END_DATE")));
		if(map.get("EXT_QUANTITY") != null) dtoExt.setQuantity(map.get("EXT_QUANTITY").toString());
		if(map.get("EXT_UNIT") != null) dtoExt.setUnit(map.get("EXT_UNIT").toString());
		if(map.get("EXT_CAR_KIND") != null) dtoExt.setCarKind(map.get("EXT_CAR_KIND").toString());
		if(map.get("EXT_LICENSE_NO") != null) dtoExt.setLicenseno(map.get("EXT_LICENSE_NO").toString());
		if(map.get("EXT_CAR_AREA_CODE") != null) dtoExt.setCarAreaCode(map.get("EXT_CAR_AREA_CODE").toString());
		if(map.get("EXT_CAR_AREA_NAME") != null) dtoExt.setCarAreaName(map.get("EXT_CAR_AREA_NAME").toString());
		if(map.get("EXT_RISK_CODE") != null) dtoExt.setRiskCode(map.get("EXT_RISK_CODE").toString());
		if(map.get("EXT_FUEL_CATEGORY") != null) dtoExt.setFuelCategory(map.get("EXT_FUEL_CATEGORY").toString());
		if(map.get("EXT_AREA_NAME") != null) dtoExt.setAreaName(map.get("EXT_AREA_NAME").toString());
		if(map.get("EXT_AREA_CODE") != null) dtoExt.setAreaCode(map.get("EXT_AREA_CODE").toString());
		//-------------------------VC_INVOICE_PRINT_EXTT end------------------------------------
		List<InvoiceGuiZhouUploadExtDTO> extList = new ArrayList<InvoiceGuiZhouUploadExtDTO>();
		extList.add(dtoExt);
		dtoDetail.setVcInvoicePrintExtt(extList);
		return dtoDetail;
	}

	/**
	 * 生成登录平台用户XML格式数据
	 * 数据来源：vc_taxpayer_login
	 * @return 登录平台用户XML格式数据
	 */
	@SuppressWarnings("unchecked")
	public List<String> generateTaxLoginToXML(String companyCode) throws DaoException {
		List list = this.findBySql(this.loginPlatXMLSql, new Object[]{companyCode,0,300});
		char[] buffer = null;
		List<String> resultList = new ArrayList<String>(10);
		for(Object obj : list) {
			SerializableClob sc = (SerializableClob)obj;
			try {
				buffer = new char[(int)sc.length()];
				sc.getCharacterStream().read(buffer);
			} catch (Exception e) {
				new DaoException(e.getMessage(), e);
			}
			resultList.add(String.valueOf(buffer));
		}
		return resultList;
	}
	
	/**
     * 生成登录平台用户XML格式数据
     * 数据来源：vc_taxpayer_login
     * @return 登录平台用户XML格式数据
     */
    @SuppressWarnings("unchecked")
	public List<String> generateTaxLoginToXML(String companyCode,long startRow,long endRow) throws DaoException {
        //List list = this.findBySql(this.loginPlatXMLSql, new Object[]{companyCode,startRow,endRow});
    	Session session = getSession();
    	SQLQuery query = session.createSQLQuery(this.loginPlatXMLSql);  
		query.setString(0, companyCode);
		query.setLong(1, startRow);
		query.setLong(2, endRow);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		List list =query.list();    
        List<String> resultList = new ArrayList<String>(10);
        
        if (list !=null && list.size() > 0) {
			for(Object map : list) {
				Map<String,Object> map2 = (Map<String,Object>)map;
				StringBuilder builder = new StringBuilder();
				builder.append("<VC_TAXPAYER_LOGIN>");
				builder.append("<DATE_UPDATED>" + fmtDate(map2.get("DATE_UPDATED")) + "</DATE_UPDATED>");
				builder.append("<UPDATED_BY>" + map2.get("UPDATED_BY") + "</UPDATED_BY>");
				builder.append("<DATE_CREATED>" + fmtDate(map2.get("DATE_CREATED")) + "</DATE_CREATED>");
				builder.append("<CREATED_BY>" + map2.get("CREATED_BY") + "</CREATED_BY>");
				builder.append("<STATUS>" + map2.get("STATUS") + "</STATUS>");
				builder.append("<ORG_CODE>" + map2.get("ORG_CODE") + "</ORG_CODE>");
				builder.append("<PASSWORD>" + map2.get("PASSWORD") + "</PASSWORD>");
				builder.append("<LOGIN_CODE>" + map2.get("LOGIN_CODE") + "</LOGIN_CODE>");
				builder.append("<COMPUTER_NO>" + map2.get("COMPUTER_NO") + "</COMPUTER_NO>");
				builder.append("<ID_VC_TAXPAYER_LOGIN>" + map2.get("ID_VC_TAXPAYER_LOGIN") + "</ID_VC_TAXPAYER_LOGIN>");
				builder.append("</VC_TAXPAYER_LOGIN>");
				resultList.add(builder.toString());
			}
		}
        return resultList;
    }

    /**
     * 查询登录平台用户数量
     * 数据来源：vc_taxpayer_login
     * @return 登录平台用户数量
     */
    @SuppressWarnings("unchecked")
	public long queryTaxLoginCount(String companyCode) throws DaoException {
        List list = this.findBySql(this.loginPlatCountSql, new Object[]{companyCode});       
        if(list!=null && list.size()>0) {
           return ((BigDecimal)list.get(0)).longValue();
        }
        return 0;
    }

	/**
	 * 根据发票代码和发票号更新是否上传平台标志为已上平台
	 */
	public int updateUploadedPlatFlag(String invoiceCode, String invoiceNo) {
		return this.executeUpdateBySql(UPDATE_IS_PLAT_FLAG_SQL, new Object[]{invoiceCode, invoiceNo});
	}
	
	/**
     * 更新当前批次数
     * 事务非spring控制
     * 
     */
    public void updateCurBatchNo(VcPubCode vcPubCode,String nextBatchNumum)throws DaoException {
        if(vcPubCode!=null){
          String sql= "UPDATE VC_PUB_CODE p   SET p.code_c_name = ?, p.date_updated = ?  WHERE p.id_vc_pub_code = ?";
          this.executeUpdateBySql(sql, new Object[]{nextBatchNumum, new Date(),vcPubCode.getIdVcPubCode()});
        }      
    }
	/**
     * 更新当前批次数
     * 事务非spring控制
     * 
     */
	public void _updateCurBatchNo(VcPubCode vcPubCode,String nextBatchNumum)throws DaoException {
        if(vcPubCode!=null){ 
            Session session=null;
            Transaction tx=null;
            try{ 
             session=this.getSession();             
             tx = session.beginTransaction();
             String sql= "UPDATE VC_PUB_CODE p   SET p.code_c_name = ?, p.date_updated = ?  WHERE p.id_vc_pub_code = ?";
             this.executeUpdateBySql(sql, new Object[]{nextBatchNumum, new Date(),vcPubCode.getIdVcPubCode()});            
             tx.commit(); 
             session.close();
            }catch(Exception e){
                if(session!=null ){
                   session.close();  
                }
                throw new DaoException("贵州 发票上传更新当前批次数至"+nextBatchNumum+"异常！"); 
            }             
        }      
    }
	
	public void setInvoiceXMLSql(String invoiceXMLSql) {
		this.invoiceXMLSql = invoiceXMLSql;
	}

	public void setLoginPlatXMLSql(String loginPlatXMLSql) {
		this.loginPlatXMLSql = loginPlatXMLSql;
	}

	public String getInvoiceXMLSql() {
		return invoiceXMLSql;
	}


	public String getLoginPlatXMLSql() {
		return loginPlatXMLSql;
	}

    public String getLoginPlatCountSql() {
        return loginPlatCountSql;
    }

    public void setLoginPlatCountSql(String loginPlatCountSql) {
        this.loginPlatCountSql = loginPlatCountSql;
    }
	
	

}
