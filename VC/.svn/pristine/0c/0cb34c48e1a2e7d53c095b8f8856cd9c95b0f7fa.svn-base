package com.tapi.tcs.vc.invoice.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.tf.common.vo.Message;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.invoice.service.FuJianInvoiceService;
import com.tapi.tcs.vc.invoice.service.InvoicePurchaseService;
import com.tapi.tcs.vc.invoice.vo.InvoiceDocVersionDTO;
import com.tapi.tcs.vc.invoice.vo.fujian.DownloadResponse;
import com.tapi.tcs.vc.schema.model.VcDocInStore;
import com.tapi.tcs.vc.schema.model.VcDocInStoreDet;
import com.tapi.tcs.vc.schema.model.VcInvoicePurchase;
import com.tapi.tcs.vc.schema.model.VcTaxAuth;
import com.tapi.tcs.vc.schema.model.VcTaxAuthDetail;
import com.tapi.tcs.vc.schema.model.VcTaxPayerInfo;

/**
 * 福建领购发票Action
 * <p>
 * Date 2013-08-02
 * </p>
 * <p>
 * Module：领购发票
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * @author 
 * @version 1.0
 */
public class InvoicePurchaseAction extends  TFAction{
    //  福建领购发票service
	private  InvoicePurchaseService  invoicePurchaseService;
    //纳税人基本信息
	List<VcTaxPayerInfo> basaicInfoDTOList;
    private String id;
    //纳税人领购信息
    List<VcInvoicePurchase> invoicePurchaseDTOList;
    // 平台下载接口service
    private FuJianInvoiceService fuJianInvoiceService;
    //纳税人授权信息
    List<VcTaxAuth> vcTaxAuthDTOList;
   //纳税人授权明细信息
    List<VcTaxAuthDetail> vcTaxAuthDetailDTOList;
    //  单证类型的vo
    private  InvoiceDocVersionDTO  invoiceDocVersionDTO;
    //单证类型信息
    List<InvoiceDocVersionDTO> invoiceDocVersionDTOList;
    /** 印刷入库对象 */
    private VcDocInStore vcDocInStore;
    
    /** 印刷入库详细信息list */
    private List<VcDocInStoreDet> vcDocInStoreDet;
    
    /** 操作结果 */
    private String result;
    
    /** 页面显示json字符串 */
    private String jsonStr;

    /** 操作类型 */
    private String actionType;
    
    /**领购机构*/
    private String purchaseOrg; 
    
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getJsonStr() {
		return jsonStr;
	}
	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<VcDocInStoreDet> getVcDocInStoreDet() {
		return vcDocInStoreDet;
	}
	public void setVcDocInStoreDet(List<VcDocInStoreDet> vcDocInStoreDet) {
		this.vcDocInStoreDet = vcDocInStoreDet;
	}
	public VcDocInStore getVcDocInStore() {
		return vcDocInStore;
	}
	public void setVcDocInStore(VcDocInStore vcDocInStore) {
		this.vcDocInStore = vcDocInStore;
	}
	/****
	 * 领购发票主界面
	 * @return  String
	 */
	public  String   queryInvoicePurchase(){
		logger.info("访问/platform/queryInvoicePurchase.do...进入领购发票主界面");
		return  SUCCESS;
	}
	/****
	 *  根据机构代码查询纳税人基本信息/登录信息表 
	 * @return  String
	 * @throws BusinessException
	 */
	public  String  queryInvoicePurchaseBaseInfo() throws  BusinessException{
		try{
			UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
			String   invoicePurchaseOrgCode=getRequest().getParameter("orgCode");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            if(invoicePurchaseOrgCode==null  ||  invoicePurchaseOrgCode.equals("")){
            	throw new BusinessException("机构代码不能为空！ ");
            }
            String operatorCode = userInfo.getUserCode();
            DownloadResponse response = fuJianInvoiceService.invoiceDownload(invoicePurchaseOrgCode);
            fuJianInvoiceService.saveInvoice(response, invoicePurchaseOrgCode, operatorCode);
            Page pages = invoicePurchaseService.queryInvoicePurchaseBaseInfo(invoicePurchaseOrgCode, page,rows);
            basaicInfoDTOList =(List<VcTaxPayerInfo>)pages.getResult();
            total = (int) pages.getTotalPageCount();
            records = (int) pages.getTotalCount(); 

		} catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
	}
	
	/****
	 * 根据纳税人电脑编码查询纳税人领购信息
	 * @return  String
	 * @throws BusinessException
	 */
	public  String  queryInvoicePurchasePurchase() throws  BusinessException{
		try{
			UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
			String   nsrdnbm=getRequest().getParameter("companyN0");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            if(nsrdnbm==null || nsrdnbm.equals("") ){
            	throw new BusinessException("纳税人电脑编码不能为空！ ");
			}
            Page pages = invoicePurchaseService.queryInvoicePurchasePurchase(nsrdnbm, page,rows);
            invoicePurchaseDTOList =(List<VcInvoicePurchase>)pages.getResult();
            total = (int) pages.getTotalPageCount();
            records = (int) pages.getTotalCount(); 
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
	}
	
	/****
	 * 根据发票简码代码查询纳税人授权信息
	 * @return  String
	 * @throws BusinessException
	 */
	public  String  queryInvoicePurchaseImpower() throws  BusinessException{
		try{
			UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
			//纳税人领购信息流水查询转换发票简码代码
			String   purId=getRequest().getParameter("invoiceShortCode");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            if(purId==null || purId.equals("")){
            	throw new BusinessException("纳税人领购信息流水号不能为空！ ");
			}
            Page pages = invoicePurchaseService.queryInvoicePurchaseImpower(purId, page,rows); 
            vcTaxAuthDTOList =(List<VcTaxAuth>)pages.getResult();
            total = (int) pages.getTotalPageCount();
            records = (int) pages.getTotalCount(); 
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
	}
	
	/****
	 * 根据纳税人授权信息流水查询纳税人授权明细信息
	 * @return  String
	 * @throws BusinessException
	 */
	public  String  queryInvoicePurchaseImpowerDet() throws  BusinessException{
		try{
			UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
			//纳税人授权信息流水
			String   ordId=getRequest().getParameter("ordId");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            if(ordId==null || ordId.equals("")){
            	throw new BusinessException("纳税人授权信息流水不能为空！ ");
			}
            Page pages = invoicePurchaseService.queryInvoicePurchaseImpowerDet(ordId, page,rows); 
            vcTaxAuthDetailDTOList =(List<VcTaxAuthDetail>)pages.getResult();
            total = (int) pages.getTotalPageCount();
            records = (int) pages.getTotalCount(); 
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
	}
	
	/****
	 *   查询单证类型信息表
	 * @return  String
	 * @throws BusinessException
	 */
	public  String  queryInvoiceDocVersion() throws  BusinessException{
		try{
			UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            if(StringUtils.isBlank(purchaseOrg)){
            	throw new BusinessException("请选择领购机构！");
            }
            Page pages = invoicePurchaseService.queryInvoiceDocVersion(purchaseOrg,invoiceDocVersionDTO, page,rows); 
            invoiceDocVersionDTOList =(List<InvoiceDocVersionDTO>)pages.getResult();
            total = (int) pages.getTotalPageCount();
            records = (int) pages.getTotalCount(); 
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
	}
	
	
	 /**
     * 保存印刷入库
     * 
     * @return
     * @throws Exception
     */
    public String inStoreInvoiceDocVersion() throws BusinessException {
        Message message = new Message();
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        String userCode = userInfo.getUserCode();
        String orgCode = userInfo.getComCode();
        String  applyReason=null;
        String  purchaseId=getRequest().getParameter("purchaseId");   //发票领购信息流水号
        String  docVerCode=getRequest().getParameter("docVerCode");   // 入库的单证类型
        String  applyN0=null;
        try {
            VcDocInStore vcDocInStore = new VcDocInStore();
            // 申请原因
            vcDocInStore.setApplyReason("福建发票领购入库");
            // 入库操作人代码
            vcDocInStore.setApplyOprCode(userCode);
            // 申请机构代码
            vcDocInStore.setApplyOrgCode(orgCode);
            //vcDocInStore.setApplyOrgCode(purchaseOrg);
           
            applyN0 = invoicePurchaseService.executeInStoreInvoiceDocVersion(vcDocInStore, purchaseId, docVerCode);
            if (applyN0==null) {
                throw new BusinessException("申请单号生成失败！");
            }
            this.jsonStr =applyN0;
        } catch (BusinessException e) {
        	logger.error(e.getMessage(), e);
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return  SUCCESS;
    }
	public List<VcTaxPayerInfo> getBasaicInfoDTOList() {
		return basaicInfoDTOList;
	}

	public void setBasaicInfoDTOList(List<VcTaxPayerInfo> basaicInfoDTOList) {
		this.basaicInfoDTOList = basaicInfoDTOList;
	}

	public void setInvoicePurchaseService(
			InvoicePurchaseService invoicePurchaseService) {
		this.invoicePurchaseService = invoicePurchaseService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<VcInvoicePurchase> getInvoicePurchaseDTOList() {
		return invoicePurchaseDTOList;
	}
	public void setInvoicePurchaseDTOList(
			List<VcInvoicePurchase> invoicePurchaseDTOList) {
		this.invoicePurchaseDTOList = invoicePurchaseDTOList;
	}
	public void setFuJianInvoiceService(FuJianInvoiceService fuJianInvoiceService) {
		this.fuJianInvoiceService = fuJianInvoiceService;
	}
	public List<VcTaxAuth> getVcTaxAuthDTOList() {
		return vcTaxAuthDTOList;
	}
	public void setVcTaxAuthDTOList(List<VcTaxAuth> vcTaxAuthDTOList) {
		this.vcTaxAuthDTOList = vcTaxAuthDTOList;
	}
	
	public List<VcTaxAuthDetail> getVcTaxAuthDetailDTOList() {
		return vcTaxAuthDetailDTOList;
	}
	public void setVcTaxAuthDetailDTOList(
			List<VcTaxAuthDetail> vcTaxAuthDetailDTOList) {
		this.vcTaxAuthDetailDTOList = vcTaxAuthDetailDTOList;
	}
	public InvoiceDocVersionDTO getInvoiceDocVersionDTO() {
		return invoiceDocVersionDTO;
	}
	public void setInvoiceDocVersionDTO(InvoiceDocVersionDTO invoiceDocVersionDTO) {
		this.invoiceDocVersionDTO = invoiceDocVersionDTO;
	}
	
	public List<InvoiceDocVersionDTO> getInvoiceDocVersionDTOList() {
		return invoiceDocVersionDTOList;
	}
	public void setInvoiceDocVersionDTOList(
			List<InvoiceDocVersionDTO> invoiceDocVersionDTOList) {
		this.invoiceDocVersionDTOList = invoiceDocVersionDTOList;
	}
	public String getPurchaseOrg() {
		return purchaseOrg;
	}
	public void setPurchaseOrg(String purchaseOrg) {
		this.purchaseOrg = purchaseOrg;
	}
}
