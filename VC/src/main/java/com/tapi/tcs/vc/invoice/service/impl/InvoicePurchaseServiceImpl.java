package com.tapi.tcs.vc.invoice.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.dao.TakeNoDao;
import com.tapi.tcs.vc.invoice.dao.InvoicePurchaseDao;
import com.tapi.tcs.vc.invoice.service.InvoicePurchaseService;
import com.tapi.tcs.vc.invoice.vo.InvoiceDocVersionDTO;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDocInStore;
import com.tapi.tcs.vc.schema.model.VcDocInStoreDet;
import com.tapi.tcs.vc.schema.model.VcDocRelArea;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcInvoicePurchase;
import com.tapi.tcs.vc.schema.model.VcTaxAuth;
import com.tapi.tcs.vc.schema.model.VcTaxAuthDetail;
import com.tapi.tcs.vc.schema.model.VcTaxPayerInfo;

public class InvoicePurchaseServiceImpl implements InvoicePurchaseService {

	private InvoicePurchaseDao invoicePurchaseDao;
	private VcLevelDao vcLevelDao;

	/**
     * 取流水号DAO
     */
    private TakeNoDao takeNoDao;

	@Override
	public Page queryInvoicePurchaseBaseInfo(String invoicePurchaseOrgCode,
			int pageNo, int pageSize) throws BusinessException {
		try {
			// 根据机构获取纳税人电脑编码
			String companyNo = invoicePurchaseDao
					.searchCompanyNoByOrgCode(invoicePurchaseOrgCode);
			// 根据纳税人电脑编码查询纳税人基本信息
			Page page = invoicePurchaseDao.queryInvoicePurchaseBaseInfo(
					companyNo, pageNo, pageSize);
			return page;
		} catch (DaoException e) {
			throw new BusinessException("数据库操作失败！", e);
		}
	}

	@Override
	public Page queryInvoicePurchasePurchase(String nsrdnbm, int pageNo,
			int pageSize) throws BusinessException {
		try {
			// 根据纳税人电脑编码查询纳税人领购信息
			Page page = invoicePurchaseDao.queryInvoicePurchasePurchase(
					nsrdnbm, pageNo, pageSize);
			List<VcInvoicePurchase> vcInvoice=(List<VcInvoicePurchase>)page.getResult();
			VcInvoicePurchase temp=new VcInvoicePurchase();
			List<VcInvoicePurchase>  vcInvoicePurchases=new  ArrayList<VcInvoicePurchase>();
			for(int i=0;i<vcInvoice.size();i++){
				temp=vcInvoice.get(i);
				String  purchaseTypeTemp=temp.getPurchaseType();
				String  purchaseTypeName="";
				if(purchaseTypeTemp.equals("04")){
					purchaseTypeName="纳税人领购";
				}else if(purchaseTypeTemp.equals("94")){
					purchaseTypeName="用户退票";
				}
				String  inStoreFlagTemp= temp.getInStoreFlag();
				String  inStoreFlag="";
				if(inStoreFlagTemp.equals("1")){
					inStoreFlag="已入库";
				}else if(inStoreFlagTemp.equals("0")){
					inStoreFlag="未入库";
				}
				temp.setPurchaseTypeName(purchaseTypeName);   //1.用于界面显示领购类型中文 2.直接入库时要取领购类型代码用于前台校验、如果用purchaseType在界面翻译取得是中文
				temp.setInStoreFlagName(inStoreFlag);       //用于显示系统内状态
				vcInvoicePurchases.add(temp);
			}
			page.setData(vcInvoicePurchases);
			return page;
		} catch (DaoException e) {
			throw new BusinessException("数据库操作失败！", e);
		}
	}

	@Override
	public Page queryInvoicePurchaseImpower(String purId,
			int pageNo, int pageSize) throws BusinessException {
		try {
			
			//根据纳税人领购信息流水查询发票简码代码
			String  shortCode=null;
			VcInvoicePurchase  vcTaxPurchase=invoicePurchaseDao.queryPurchaseInfo(purId);
			if(vcTaxPurchase!=null){
				shortCode=vcTaxPurchase.getInvoiceShortCode();
			}
			// 根据发票简码代码查询纳税人授权信息
			Page page = invoicePurchaseDao.queryInvoicePurchaseImpower(
					shortCode, pageNo, pageSize);
			return page;
		} catch (DaoException e) {
			throw new BusinessException("数据库操作失败！", e);
		}
	}

	@Override
	public Page queryInvoicePurchaseImpowerDet(String ordId, int pageNo,
			int pageSize) throws BusinessException {

		try {
			// 根据授权流水号查询纳税人授权明细信息
			Page page = invoicePurchaseDao.queryInvoicePurchaseImpowerDet(
					ordId, pageNo, pageSize);
			return page;
		} catch (DaoException e) {
			throw new BusinessException("数据库操作失败！", e);
		}

	}

	@Override
	public Page queryInvoiceDocVersion(
			String orgCode,InvoiceDocVersionDTO invoiceDocVersionDTO, int pageNo, int pageSize)
			throws BusinessException {
		try {
			Page page = invoicePurchaseDao.queryInvoiceDocVersion(
					orgCode,invoiceDocVersionDTO, pageNo, pageSize);
			List list = page.getResult();
			List<InvoiceDocVersionDTO> temps = new ArrayList<InvoiceDocVersionDTO>();
			if (list.size() > 0 && list != null) {
				VcDocVersionInfo vcDocVersionInfo = null;
				VcDocRelArea vcDocRelArea = null;
				InvoiceDocVersionDTO verDTO = null;
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(i);
					vcDocVersionInfo = (VcDocVersionInfo) obj[0];
					vcDocRelArea = (VcDocRelArea) obj[1];
					verDTO = new InvoiceDocVersionDTO();
					verDTO.setDocVerCode(vcDocVersionInfo.getDocVerCode()); // 单证类型代码
					verDTO.setDocVerName(vcDocVersionInfo.getDocVerName()); // 单证类型名称
					verDTO.setOrgName(vcLevelDao
							.getUnitNameByUnitCode(vcDocRelArea.getOrgCode())); // 适用地区名称
					temps.add(verDTO);
				}
			}
			page.setData(temps);
			return page;
		} catch (DaoException e) {
			throw new BusinessException("数据库操作失败！", e);
		}
	}

	/**
	 * 保存印刷入库信息
	 * 
	 * @param vcDocInStore
	 * @param vcDocInStoreDets
	 * @throws Exception
	 */
	@Override
	public String executeInStoreInvoiceDocVersion(VcDocInStore vcDocInStore,String purchaseId,String docVerCode) throws BusinessException {
		
		 String inStoreAppCode=null;
		try {
			//直接提交
			Date nowDate = new Date();
			// 申请原因
			String applyReason = vcDocInStore.getApplyReason();
			// 入库操作人代码
			String userCode = vcDocInStore.getApplyOprCode();
			// 申请机构代码
			String orgCode = vcDocInStore.getApplyOrgCode();
			//扫描件名称
			vcDocInStore.setFileName("-1");
			//扫描件路径
			vcDocInStore.setFilePath("-1");
			
			 // 印刷入库申请单号
			inStoreAppCode= takeNoDao.getNo("DS");
            vcDocInStore.setInStoreAppCode(inStoreAppCode);
            vcDocInStore.setApplyTime(nowDate);  //申请时间
            vcDocInStore.setCreatedBy(userCode); //创建人
            vcDocInStore.setDateCreated(nowDate);//创建时间
            
			// 入库申请单状态（0删除/1新建/2等待审批/3审批退回/4审批通过）
			vcDocInStore.setInStoreStatus("2");
			// 审批机构
			//String upperOrgCode = vcLevelDao.getUpperOrgCode(userCode);
			String upperOrgCode = vcLevelDao.findParentOrgByUnitCode(orgCode);
			vcDocInStore.setCheckOrgCode(upperOrgCode);

			vcDocInStore.setModifyOprCode(userCode);  //修改人代码
			vcDocInStore.setModifyTime(nowDate);  //修改时间
			vcDocInStore.setUpdatedBy(userCode); //修改人
			vcDocInStore.setDateUpdated(nowDate); //修改时间
			
			// 根据发票领购流水号查询发票领购信息
			VcInvoicePurchase  vcTaxPurchase=invoicePurchaseDao.queryPurchaseInfo(purchaseId);
			
			//流水格式化为8位
            DecimalFormat df = new DecimalFormat("0");
            df.setMinimumIntegerDigits(8);
			
			List<VcDocInStoreDet>  vcDocInStoreDets=new ArrayList<VcDocInStoreDet>();
			VcDocInStoreDet  vcDocInStoreDet=new VcDocInStoreDet();
			vcDocInStoreDet.setVcDocInStore(vcDocInStore);   //将印刷入库申请单号set到详情表
			vcDocInStoreDet.setDocVerCode(docVerCode);     //单证类型
			//vcDocInStoreDet.setPressBatchNo(vcTaxPurchase.getInvoiceShortCode()); //印刷批次
			vcDocInStoreDet.setPressBatchNo(vcTaxPurchase.getInvoiceCode()); //印刷批次
			//vcDocInStoreDet.setStartNum(vcTaxPurchase.getStartNum());   //起始流水号
			//vcDocInStoreDet.setEndNum(vcTaxPurchase.getEndNum());      //终止流水号
			vcDocInStoreDet.setStartNum(df.format(Long.valueOf(vcTaxPurchase.getStartNum())));   //起始流水号
			vcDocInStoreDet.setEndNum(df.format(Long.valueOf(vcTaxPurchase.getEndNum())));      //终止流水号
			vcDocInStoreDet.setTotalAmount(Integer.valueOf(vcTaxPurchase.getCopyNum()).longValue());//  入库数量 integer转换成long型
			DecimalFormat dft = new DecimalFormat("0.00");
			BigDecimal  prize=new  BigDecimal(0.00);
			vcDocInStoreDet.setUnitPrice(prize);  //印刷单价 
			vcDocInStoreDet.setPrintingFee(prize); //印刷费用
			vcDocInStoreDet.setCreateOprCode(userCode);  //创建人
			vcDocInStoreDet.setCreateTime(nowDate);  //创建时间
			vcDocInStoreDet.setModifyOprCode(userCode); //修改人
			vcDocInStoreDet.setModifyTime(nowDate);  //修改时间
			vcDocInStoreDets.add(vcDocInStoreDet);
			 // 入库申请明细
			vcDocInStore.setVcDocInStoreDets(vcDocInStoreDets);
			
			invoicePurchaseDao.inStoreInvoiceDocVersion(vcDocInStore);
			// 当操作为提交时、提交入库审批表
				VcApprove vcApprove = new VcApprove();
				// 申请编号
				vcApprove.setApplyId(vcDocInStore.getId());
				// I -印刷入库
				vcApprove.setApplyType("I");
				// 审批机构
				vcApprove.setCheckOrgId(orgCode);
				// 审批人
				vcApprove.setCheckOprId(userCode);
				// 审批状态
				vcApprove.setCheckStatus("0");
				// 审批时间
				vcApprove.setCheckTime(nowDate);
				// 审批意见
				vcApprove.setCheckExpl("提交");
				// 创建人
				vcApprove.setCreatedBy(userCode);
				// 创建时间
				vcApprove.setDateCreated(nowDate);
				// 修改人
				vcApprove.setUpdatedBy(userCode);
				// 修改时间
				vcApprove.setDateUpdated(nowDate);
				invoicePurchaseDao.save(vcApprove);

		 //  更新纳税人领购信息
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String  formatDate=formatter.format(nowDate); 
		    invoicePurchaseDao.updatePurchaseInfo(formatDate,userCode,docVerCode,purchaseId);
		
		} catch (Exception e) {

			throw new BusinessException(e.getMessage(), e);
		}
		return inStoreAppCode;
	}

	public InvoicePurchaseDao getInvoicePurchaseDao() {
		return invoicePurchaseDao;
	}

	public void setInvoicePurchaseDao(InvoicePurchaseDao invoicePurchaseDao) {
		this.invoicePurchaseDao = invoicePurchaseDao;
	}

	public VcLevelDao getVcLevelDao() {
		return vcLevelDao;
	}

	public void setVcLevelDao(VcLevelDao vcLevelDao) {
		this.vcLevelDao = vcLevelDao;
	}
	 
	public TakeNoDao getTakeNoDao() {
		return takeNoDao;
	}

	public void setTakeNoDao(TakeNoDao takeNoDao) {
		this.takeNoDao = takeNoDao;
	}
}
