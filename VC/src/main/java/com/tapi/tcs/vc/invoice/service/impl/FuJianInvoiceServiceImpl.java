package com.tapi.tcs.vc.invoice.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.pansky.JTServicePortType;

import com.pansky.framework.util.CodeUtil;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.common.util.StringUtil;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.invoice.dao.FuJianInvoiceDao;
import com.tapi.tcs.vc.invoice.service.FuJianInvoiceService;
import com.tapi.tcs.vc.invoice.util.AntZip;
import com.tapi.tcs.vc.invoice.vo.fujian.DownloadResponse;
import com.tapi.tcs.vc.invoice.vo.fujian.DownloadResponseTitle;
import com.tapi.tcs.vc.invoice.vo.fujian.InvoiceDTO;
import com.tapi.tcs.vc.invoice.vo.fujian.InvoicePrintDTO;
import com.tapi.tcs.vc.invoice.vo.fujian.InvoicePrintDetDTO;
import com.tapi.tcs.vc.invoice.vo.fujian.InvoicePrintExtDTO;
import com.tapi.tcs.vc.invoice.vo.fujian.InvoiceRevokeDTO;
import com.tapi.tcs.vc.invoice.vo.fujian.RequestTitle;
import com.tapi.tcs.vc.invoice.vo.fujian.TaxAuthDetailDTO;
import com.tapi.tcs.vc.invoice.vo.fujian.TaxAuthInfoDTO;
import com.tapi.tcs.vc.invoice.vo.fujian.TaxPayerInfoDTO;
import com.tapi.tcs.vc.invoice.vo.fujian.TaxPurchaseInfoDTO;
import com.tapi.tcs.vc.invoice.vo.fujian.UploadRequest;
import com.tapi.tcs.vc.invoice.vo.fujian.UploadRequestBody;
import com.tapi.tcs.vc.invoice.vo.fujian.UploadResponse;
import com.tapi.tcs.vc.schema.model.VcInvoicePlatLog;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoicePrintDet;
import com.tapi.tcs.vc.schema.model.VcInvoicePrintExt;
import com.tapi.tcs.vc.schema.model.VcInvoicePurchase;
import com.tapi.tcs.vc.schema.model.VcInvoiceRevoke;
import com.tapi.tcs.vc.schema.model.VcTaxAuth;
import com.tapi.tcs.vc.schema.model.VcTaxAuthDetail;
import com.tapi.tcs.vc.schema.model.VcTaxPayerInfo;
import com.tapi.tcs.vc.schema.model.VcTaxPayerLogin;

public class FuJianInvoiceServiceImpl implements FuJianInvoiceService {
	
	private FuJianInvoiceDao fuJianInvoiceDao;
	private JTServicePortType jtServicePortType;
	/**日志管理*/
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void saveInvoice(DownloadResponse response, String orgCode, String operatorCode) throws BusinessException{
		try{
			Date sysdate = new Date();
			//纳税人基本信息
			TaxPayerInfoDTO nsrjbxx = response.getBody().getNsrjbxx();
			//纳税人领购情况
			List<TaxPurchaseInfoDTO> nsrlgqkList = response.getBody().getNsrlgqk();
			//纳税人授权信息
			List<TaxAuthInfoDTO> nsrsqxxList = response.getBody().getNsrsqxx();
			//纳税人电脑编码
			String computerNo = nsrjbxx.getNsrdnbm();
			//所属地市
			String city = nsrjbxx.getSsds();
			SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
			/*
			 * 保存纳税人基本信息
			 * 先根据纳税人电脑编码查询纳税人基本信息表
			 * 若不存在则插入，若存在则更新
			 */
			VcTaxPayerInfo vcTaxPayerInfo = fuJianInvoiceDao.findVcTaxPayerInfo(computerNo);
			if(vcTaxPayerInfo==null){
				VcTaxPayerInfo vcTaxPayerInfoNew = new VcTaxPayerInfo();
				vcTaxPayerInfoNew.setComputerNo(computerNo);//电脑编码
				vcTaxPayerInfoNew.setTaxPayerId(nsrjbxx.getNsrsbh());//纳税人识别号
				vcTaxPayerInfoNew.setTaxPayerName(nsrjbxx.getNsrmc());//纳税人名称
				vcTaxPayerInfoNew.setTaxOrgCode(nsrjbxx.getZgswjgdm());//主管税务机关代码
				vcTaxPayerInfoNew.setTaxOrgName(nsrjbxx.getZgswjgmc());//主管税务机关名称
				vcTaxPayerInfoNew.setTaxPayerStatus(nsrjbxx.getNsrztdm());//纳税人状态
				vcTaxPayerInfoNew.setRegistrationType(nsrjbxx.getSwdjblxdm());//登记表类型
				vcTaxPayerInfoNew.setCollectFlag(nsrjbxx.getDzbz());//代征标志
				vcTaxPayerInfoNew.setBelongCity(nsrjbxx.getSsds());//所属地市
				vcTaxPayerInfoNew.setWebsite(nsrjbxx.getCywz());//查验网址
				vcTaxPayerInfoNew.setStatus("1");
				vcTaxPayerInfoNew.setCreatedBy(operatorCode);
				vcTaxPayerInfoNew.setUpdatedBy(operatorCode);
				vcTaxPayerInfoNew.setDateCreated(sysdate);
				vcTaxPayerInfoNew.setDateUpdated(sysdate);
				try{
					fuJianInvoiceDao.save(vcTaxPayerInfoNew);
				}catch(Exception e){
					throw new BusinessException("保存纳税人基本信息出错！", e);
				}
			}else{
				vcTaxPayerInfo.setTaxPayerId(nsrjbxx.getNsrsbh());//纳税人识别号
				vcTaxPayerInfo.setTaxPayerName(nsrjbxx.getNsrmc());//纳税人名称
				vcTaxPayerInfo.setTaxOrgCode(nsrjbxx.getZgswjgdm());//主管税务机关代码
				vcTaxPayerInfo.setTaxOrgName(nsrjbxx.getZgswjgmc());//主管税务机关名称
				vcTaxPayerInfo.setTaxPayerStatus(nsrjbxx.getNsrztdm());//纳税人状态
				vcTaxPayerInfo.setRegistrationType(nsrjbxx.getSwdjblxdm());//登记表类型
				vcTaxPayerInfo.setCollectFlag(nsrjbxx.getDzbz());//代征标志
				vcTaxPayerInfo.setBelongCity(nsrjbxx.getSsds());//所属地市
				vcTaxPayerInfo.setWebsite(nsrjbxx.getCywz());//查验网址
				vcTaxPayerInfo.setUpdatedBy(operatorCode);
				vcTaxPayerInfo.setDateUpdated(sysdate);
				try{
					fuJianInvoiceDao.update(vcTaxPayerInfo);
				}catch(Exception e){
					throw new BusinessException("更新纳税人基本信息出错！", e);
				}
			}
			//保存纳税人领购信息
			if(nsrlgqkList!=null && nsrlgqkList.size()>0){
				//要保存的领购信息
				List<VcInvoicePurchase> vcTaxPurchaseSaveList = new ArrayList<VcInvoicePurchase>();
				//要删除的领购信息
				List<VcInvoicePurchase> vcTaxPurchaseDeleteList = new ArrayList<VcInvoicePurchase>();
				for(TaxPurchaseInfoDTO nsrlgqk : nsrlgqkList){
					/* 根据发票领购序号查找本地领购信息表；
					 * 若没有查询到数据，则保存该条领购信息；
					 * 若查询到本地领购信息，且未入库，则删除该领购信息
					 * 并重新插入
					 */
					VcInvoicePurchase vcInvoicePurchase = fuJianInvoiceDao.getVcInvoicePurchaseBySerialNo(nsrlgqk.getFplgxh());
					if(vcInvoicePurchase==null || !"1".equals(vcInvoicePurchase.getInStoreFlag())){
						VcInvoicePurchase vcTaxPurchaseNew = new VcInvoicePurchase();
						vcTaxPurchaseNew.setSerialNo(nsrlgqk.getFplgxh());//发票领购序号
						vcTaxPurchaseNew.setInvoiceCode(nsrlgqk.getFpdm());//发票代码
						vcTaxPurchaseNew.setInvoiceName(nsrlgqk.getFpmc());//发票名称
						vcTaxPurchaseNew.setInvoiceShortCode(nsrlgqk.getFpjmdm());//发票简码代码
						vcTaxPurchaseNew.setStartNum(nsrlgqk.getQshm());//起始号码
						vcTaxPurchaseNew.setEndNum(nsrlgqk.getZzhm());//终止号码
						vcTaxPurchaseNew.setCopyNum(Integer.parseInt(nsrlgqk.getFs()));//份数
						vcTaxPurchaseNew.setPileNum(Integer.parseInt(nsrlgqk.getBs()));//本数
						vcTaxPurchaseNew.setUnitNum(Integer.parseInt(nsrlgqk.getMbfs()));//每本份数
						try {
							vcTaxPurchaseNew.setPurchaseDate(dateFmt.parse(nsrlgqk.getLgrq()));//领购日期
						} catch (ParseException e) {
						}
						vcTaxPurchaseNew.setPurchaseType(nsrlgqk.getYwlx());//领购类型
						vcTaxPurchaseNew.setOrgCode(orgCode);//领购机构代码
						vcTaxPurchaseNew.setComputerNo(computerNo);//纳税人电脑编码
						vcTaxPurchaseNew.setBelongCity(city);//所属地市
						vcTaxPurchaseNew.setInStoreFlag("0");//是否已入库：否
						vcTaxPurchaseNew.setStatus("1");//有效
						vcTaxPurchaseNew.setCreatedBy(operatorCode);
						vcTaxPurchaseNew.setUpdatedBy(operatorCode);
						vcTaxPurchaseNew.setDateCreated(sysdate);
						vcTaxPurchaseNew.setDateUpdated(sysdate);
						vcTaxPurchaseSaveList.add(vcTaxPurchaseNew);
					}
					//如果本地领购信息不为空，且未入库则删除
					if(vcInvoicePurchase!=null && !"1".equals(vcInvoicePurchase.getInStoreFlag())){
						vcTaxPurchaseDeleteList.add(vcInvoicePurchase);
					}
				}
				try{
					//删除纳税人领购信息
					if(vcTaxPurchaseDeleteList.size()>0){
						fuJianInvoiceDao.deleteAll(vcTaxPurchaseDeleteList);
					}
					//保存纳税人领购信息
					if(vcTaxPurchaseSaveList.size()>0){
						fuJianInvoiceDao.saveAll(vcTaxPurchaseSaveList);
					}
				}catch(Exception e){
					throw new BusinessException("保存纳税人领购信息出错！", e);
				}
			}
			//保存授权信息及授权明细信息
			if(nsrsqxxList!=null && nsrsqxxList.size()>0){
				List<VcTaxAuth> vcTaxAuthSaveList = new ArrayList<VcTaxAuth>();
				List<VcTaxAuth> vcTaxAuthDeleteList = new ArrayList<VcTaxAuth>();
				for(TaxAuthInfoDTO nsrsqxx : nsrsqxxList){
					String invoiceShortCode = nsrsqxx.getFpjmdm();
					//根据发票简码代码查询出纳税人授权信息
					List<VcTaxAuth> vcTaxAuthList = fuJianInvoiceDao.findVcTaxAuthByShortCode(invoiceShortCode);
					vcTaxAuthDeleteList.addAll(vcTaxAuthList);
					//保存纳税人授权信息
					VcTaxAuth vcTaxAuth = new VcTaxAuth();
					vcTaxAuth.setInvoiceShortCode(invoiceShortCode);//发票简码代码
					vcTaxAuth.setInvoiceName(nsrsqxx.getFpmc());//发票名称
					vcTaxAuth.setPrintTypeCode(nsrsqxx.getKjfsdm());//开具方式代码
					vcTaxAuth.setNegativeFlag(nsrsqxx.getFsfpsx());//负数发票授信
					vcTaxAuth.setCheckOldFlag(nsrsqxx.getJyyjsx());//简易验旧授信
					vcTaxAuth.setLimitAmount(new BigDecimal(nsrsqxx.getDzfpxe()));//单张发票限额
					try{
						vcTaxAuth.setStartDate(dateFmt.parse(nsrsqxx.getQyrq()));//启用日期
						vcTaxAuth.setEndDate(dateFmt.parse(nsrsqxx.getTyrq()));//停用日期
					}catch(Exception e){
					}
					vcTaxAuth.setStatus("1");//状态
					vcTaxAuth.setCreatedBy(operatorCode);
					vcTaxAuth.setUpdatedBy(operatorCode);
					vcTaxAuth.setDateCreated(sysdate);
					vcTaxAuth.setDateUpdated(sysdate);
					//从纳税人授权信息中获取纳税人授权明细
					List<TaxAuthDetailDTO> nsrsqmxList = nsrsqxx.getNsrsqmx();
					if(nsrsqmxList!=null && nsrsqmxList.size()>0){
						List<VcTaxAuthDetail> vcTaxAuthDetailList = new ArrayList<VcTaxAuthDetail>();
						for(TaxAuthDetailDTO nsrsqmx : nsrsqmxList){
							VcTaxAuthDetail vcTaxAuthDetail = new VcTaxAuthDetail();
							vcTaxAuthDetail.setIndustryCode(nsrsqmx.getSyhydm());//适用行业代码
							vcTaxAuthDetail.setIndustryName(nsrsqmx.getSyhymc());//适用行业名称
							vcTaxAuthDetail.setItemCode(nsrsqmx.getSyxmdm());//适用项目代码
							vcTaxAuthDetail.setItemName(nsrsqmx.getSyxmmc());//适用项目名称
							vcTaxAuthDetail.setPrintTemplateCode(nsrsqmx.getTdysdm());//套打样式代码
							vcTaxAuthDetail.setPrintTemplateName(nsrsqmx.getTdysmc());//套打样式名称
							vcTaxAuthDetail.setCreatedBy(operatorCode);
							vcTaxAuthDetail.setUpdatedBy(operatorCode);
							vcTaxAuthDetail.setDateCreated(sysdate);
							vcTaxAuthDetail.setDateUpdated(sysdate);
							vcTaxAuthDetail.setVcTaxAuth(vcTaxAuth);
							vcTaxAuthDetailList.add(vcTaxAuthDetail);
						}
						//保存纳税人授权明细
						vcTaxAuth.setVcTaxAuthDetailList(vcTaxAuthDetailList);
					}
					vcTaxAuthSaveList.add(vcTaxAuth);
				}
				try{
					//删除授权信息及授权明细
					if(vcTaxAuthDeleteList.size()>0){
						fuJianInvoiceDao.deleteAll(vcTaxAuthDeleteList);
					}
					//保存授权信息及授权明细
					if(vcTaxAuthSaveList.size()>0){
						fuJianInvoiceDao.saveAll(vcTaxAuthSaveList);
					}
				}catch(Exception e){
					throw new BusinessException("保存纳税人授权信息出错！", e);
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<VcInvoicePurchase> findVcInvoicePurchaseList(String computerNo) throws BusinessException {
		List<VcInvoicePurchase> list = null;
		try{
			list = fuJianInvoiceDao.findVcInvoicePurchaseList(computerNo);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}
	
	@Override
	public List<VcTaxAuth> findVcTaxAuthByShortCode(String invoiceShortCode) throws BusinessException{
		List<VcTaxAuth> list = null;
		try{
			list = fuJianInvoiceDao.findVcTaxAuthByShortCode(invoiceShortCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}
	
	@Override
	public List<VcTaxPayerLogin> findVcTaxPayerLoginList() throws BusinessException {
		List<VcTaxPayerLogin> list = null;
		try{
			list = fuJianInvoiceDao.findVcTaxPayerLoginList();
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}
	
	@Override
	public DownloadResponse invoiceDownload(String orgCode) throws BusinessException {
		DownloadResponse response = null;
		VcTaxPayerLogin vcTaxPayerLogin = null;
		try{
			//根据机构代码查询纳税人基本信息
			vcTaxPayerLogin = fuJianInvoiceDao.findVcTaxPayerLogin(orgCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		if(vcTaxPayerLogin==null){
			throw new BusinessException("未查到该机构登录地税平台的帐号密码！");
		}
		//组织请求对象
		RequestTitle title = new RequestTitle();
		title.setJkId("jt.nsrlgxx.download");
		title.setNsrdnbm(vcTaxPayerLogin.getComputerNo());
		title.setLoginId(vcTaxPayerLogin.getLoginID());
		title.setPassword(vcTaxPayerLogin.getPassword());
		//把请求对象转换成xml
		String xml = DTOtoXML(title, "com.tapi.tcs.vc.invoice.vo.fujian.RequestTitle");
		logger.info("福建地税发票领购数据下载接口请求报文==="+xml);
		String titleXml = "";
		try {
			//加密请求title
			titleXml = CodeUtil.encodeStr(xml);
		} catch (Exception e) {
			throw new BusinessException("加密请求报文出错！", e);
		}
		String result = "";
		try{
			//调用平台服务
			logger.warn("===福建地税发票领购数据下载开始===");
			result = jtServicePortType.service(titleXml, null);
			logger.warn("===福建地税发票领购数据下载结束===");
			//解密响应报文
			result = CodeUtil.decodeStr(result);
		}catch(Exception e){
			logger.warn("===福建地税发票领购数据下载发生异常===");
			throw new BusinessException("访问平台接口失败！");
		}
		//返回的错误描述
		String errorDesc = getElement(result,"ERROR_DESC");
		if(StringUtils.isNotEmpty(errorDesc)){
			throw new BusinessException(errorDesc);
		}
		try{
			//获取响应内容中的数据
			String data = getElement(result,"DATA");
			String jieMiData = CodeUtil.decodeStr(data);
			byte[] bytes = getBytesFromData(jieMiData);
			//生成ZIP文件
			File zipFile = saveTempFile(bytes, vcTaxPayerLogin.getComputerNo());
			// 从zip文件中获取最终的xml
			String responseXML = AntZip.getXmlFromZipByEncode(zipFile);
			//删除ZIP文件
			if(zipFile.exists()){
				zipFile.delete();
			}
			//解密
			responseXML = CodeUtil.decodeStr(responseXML);
			logger.info("福建地税发票领购数据下载接口响应报文==="+responseXML);
			//xml转换成响应体
			response = (DownloadResponse)XMLtoDTO(responseXML, 
					"com.tapi.tcs.vc.invoice.vo.fujian.DownloadResponse");
		}catch(Exception e){
			throw new BusinessException("解析响应内容失败！", e);
		}
		DownloadResponseTitle responseTitle = response.getTitle();
		if(!"true".equalsIgnoreCase(responseTitle.getSuccess())){
			throw new BusinessException(responseTitle.getErrorDesc());
		}
		return response;
	}
	
	@Override
	public void executeInvoiceUploadToPlat(VcTaxPayerLogin login) throws BusinessException {
		//String result = "处理成功！";
		// try {
		// 查询登录信息
		/*List<VcTaxPayerLogin> loginList = fuJianInvoiceDao
				.findVcTaxPayerLoginList();
		if (loginList == null || loginList.size() < 1) {
			return;
		}*/
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//List<VcInvoicePrint> printList = new ArrayList<VcInvoicePrint>();// 需要更改状态的发票开具列表
		//List<VcInvoiceRevoke> revokeList = new ArrayList<VcInvoiceRevoke>();// 需要更改状态的发票缴销列表
		List<Long> printList = new ArrayList<Long>();// 需要更改状态的发票开具列表
		List<Long> revokeList = new ArrayList<Long>();// 需要更改状态的发票缴销列表
		// 已纳税人电脑编码为单位，循环处理上传平台
		//for (VcTaxPayerLogin login : loginList) {
			try {
				UploadRequest request = new UploadRequest();
				UploadRequestBody body = new UploadRequestBody();
				// 纳税人电脑编码
				String computerNo = login.getComputerNo();
				// 组织请求对象
				RequestTitle title = new RequestTitle();
				title.setJkId("jt.nsrkpxx.upload");
				title.setNsrdnbm(computerNo);
				title.setLoginId(login.getLoginID());
				title.setPassword(login.getPassword());

				int totalCount = 1000;
				/*
				 * 发票开具信息的处理
				 */
				List<VcInvoicePrint> vcInvoicePrintList = fuJianInvoiceDao
						.findVcInvoicePrintNoUpload(computerNo);
				if (vcInvoicePrintList != null && vcInvoicePrintList.size() > 0) {
					for (VcInvoicePrint vcInvoicePrint : vcInvoicePrintList) {
						if(totalCount<0){
							break;
						}
						// 根据单证类型、发票代码、发票号码查询领购信息
						VcInvoicePurchase vcInvoicePurchase = fuJianInvoiceDao
								.findVcInvoicePurchase(vcInvoicePrint);
						if (vcInvoicePurchase == null) {
							logger.warn("发票代码："
									+ vcInvoicePrint.getInvoiceCode()
									+ "，发票号码：" + vcInvoicePrint.getInvoiceNo()
									+ "未找到已入库的领购信息！");
						} else {
							InvoiceDTO fpxx = new InvoiceDTO();
							fpxx.setFpmc(vcInvoicePurchase.getInvoiceName());// 发票名称
							fpxx.setFpjmdm(vcInvoicePurchase
									.getInvoiceShortCode());// 发票简码代码
							fpxx.setSsds(vcInvoicePurchase.getBelongCity());// 所属地市
							fpxx.setCxsx("11");// 查询时效：集团客户为11
							body.getFpxx().add(fpxx);
							body.getFpkjxx()
									.add(generateFpkjxx(vcInvoicePrint));
							printList.add(vcInvoicePrint.getId());
							totalCount--;
						}
					}
				}
				/*
				 * 发票缴销信息的处理
				 */
				List<VcInvoiceRevoke> vcInvoiceRevokeList = fuJianInvoiceDao
						.findVcInvoiceRevokeNoUpload(computerNo);
				if (vcInvoiceRevokeList != null
						&& vcInvoiceRevokeList.size() > 0) {
					for (VcInvoiceRevoke vcInvoiceRevoke : vcInvoiceRevokeList) {
						if(totalCount<0){
							break;
						}
						// 根据单证类型、发票代码、发票号码查询领购信息
						VcInvoicePurchase vcInvoicePurchase = fuJianInvoiceDao
								.findVcInvoicePurchase(vcInvoiceRevoke);
						if (vcInvoicePurchase == null) {
							logger.warn("发票代码："
									+ vcInvoiceRevoke.getInvoiceCode()
									+ "，发票号段：" + vcInvoiceRevoke.getStartNum()
									+ "-" + vcInvoiceRevoke.getEndNum()
									+ "未找到已入库的领购信息！");
						} else {
							InvoiceDTO fpxx = new InvoiceDTO();
							fpxx.setFpmc(vcInvoicePurchase.getInvoiceName());// 发票名称
							fpxx.setFpjmdm(vcInvoicePurchase.getInvoiceShortCode());// 发票简码代码
							fpxx.setSsds(vcInvoicePurchase.getBelongCity());// 所属地市
							fpxx.setCxsx("11");// 查询时效：集团客户为11
							body.getFpxx().add(fpxx);
							InvoiceRevokeDTO fpjxxx = new InvoiceRevokeDTO();
							fpjxxx.setJxSbrq(dateFmt.format(new Date()));// 缴销上报日期
							fpjxxx.setJsLxdm(vcInvoiceRevoke.getRevokeType());// 缴销类型代码
							fpjxxx.setJsQxdm(vcInvoiceRevoke.getRevokeCode());// 缴销情形代码
							fpjxxx.setFpdm(vcInvoiceRevoke.getInvoiceCode());// 发票代码
							fpjxxx.setQshm(vcInvoiceRevoke.getStartNum());// 起始号码
							fpjxxx.setZzhm(vcInvoiceRevoke.getEndNum());// 终止号码
							fpjxxx.setFpfs(vcInvoiceRevoke.getQuantity() + "");// 发票份数
							fpjxxx.setJxr(vcInvoiceRevoke.getRevokeOprName());// 缴销人
							body.getFpjxxx().add(fpjxxx);
							revokeList.add(vcInvoiceRevoke.getId());
							totalCount--;
						}
					}
				}

				if (totalCount != 1000) {
					request.setTitle(title);
					request.setBody(body);

					// 把title转换为xml
					String strTitle = DTOtoXML(title,
							"com.tapi.tcs.vc.invoice.vo.fujian.RequestTitle");
					// 加密请求头
					String jiamiTitle = CodeUtil.encodeStr(strTitle);
					// 把request对象转换成xml
					String requestXML = DTOtoXML(request,
							"com.tapi.tcs.vc.invoice.vo.fujian.UploadRequest");
					logger.warn("福建地税开票数据上传接口请求报文===" + requestXML);
					// 加密请求体
					String jiamiBody = jiamiBody(requestXML, computerNo);
					// 调用平台服务
					String responseXML = jtServicePortType.service(jiamiTitle,
							jiamiBody);
					// 解密
					responseXML = CodeUtil.decodeStr(responseXML);
					logger.warn("福建地税开票数据上传接口响应报文===" + responseXML);
					UploadResponse response = (UploadResponse) XMLtoDTO(
							responseXML,
							"com.tapi.tcs.vc.invoice.vo.fujian.UploadResponse");
					Date sysdate = new Date();
					/*if ("true".equalsIgnoreCase(response.getSuccess())) {
						// 上传成功，状态改为1
						for (VcInvoicePrint print : printList) {
							print.setIsUploadPlat("1");
						}
						for (VcInvoiceRevoke revoke : revokeList) {
							revoke.setIsUploadPlat("1");
						}
					} else {
						// 上传失败，状态改为-1
						for (VcInvoicePrint print : printList) {
							print.setIsUploadPlat("-1");
							print.setUpdatedBy("auto");
							print.setDateUpdated(sysdate);
						}
						for (VcInvoiceRevoke revoke : revokeList) {
							revoke.setIsUploadPlat("-1");
							revoke.setUpdatedBy("auto");
							revoke.setDateUpdated(sysdate);
						}
					}*/
					// 记录平台交互日志
					VcInvoicePlatLog vcInvoicePlatLog = new VcInvoicePlatLog();
					vcInvoicePlatLog.setReportType("2");
					vcInvoicePlatLog.setRequestUuid(title.getRequestUuid());
					vcInvoicePlatLog.setRequestTime(timeFmt.parse(title
							.getRequestTime()));
					vcInvoicePlatLog.setRequestXml(requestXML);
					vcInvoicePlatLog.setResponseUuid(response.getRequestUuid());
					vcInvoicePlatLog.setResponseTime(timeFmt.parse(response
							.getResponseTime()));
					vcInvoicePlatLog.setResponseXml(responseXML);
					vcInvoicePlatLog.setErrorCode(response.getErrorCode());
					vcInvoicePlatLog.setErrorDesc(response.getErrorDesc());
					vcInvoicePlatLog.setCreatedBy("auto");
					vcInvoicePlatLog.setUpdatedBy("auto");
					vcInvoicePlatLog.setDateCreated(sysdate);
					vcInvoicePlatLog.setDateUpdated(sysdate);
					// 保存交互日志
					fuJianInvoiceDao.save(vcInvoicePlatLog);
					// 更新状态
					if (printList.size() > 0) {
						if("true".equalsIgnoreCase(response.getSuccess())){
							fuJianInvoiceDao.updateVcInvoicePrintList(printList, "1");
						}else{
							fuJianInvoiceDao.updateVcInvoicePrintList(printList, "-1");
						}
					}
					if (revokeList.size() > 0) {
						if("true".equalsIgnoreCase(response.getSuccess())){
							fuJianInvoiceDao.updateVcInvoiceRevokeList(revokeList, "1");
						}else{
							fuJianInvoiceDao.updateVcInvoiceRevokeList(revokeList, "-1");
						}
					}
				}
			} catch (Exception e) {
				throw new BusinessException(e.getMessage(), e);
			}
		//}
		// } catch (Exception e) {
		// logger.info(e.getMessage(), e);
		// }
		//return result;
	}
	
	/**
	 * 把对象转换成xml报文
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
			StringWriter sw = new StringWriter();
			m.marshal(obj, sw);
			xml = sw.toString();
			sw.close();
		}catch(Exception e){
			throw new BusinessException("转换报文失败！", e);
		}
		return xml;
	}
	
	/**
	 * 把xml转换成对象
	 * @param xml
	 * @param className
	 * @return
	 * @throws Exception
	 */
	private Object XMLtoDTO(String xml, String className) throws Exception {
		JAXBContext jc = JAXBContext.newInstance(new Class[]{Class.forName(className)});
		Unmarshaller um = jc.createUnmarshaller();
		StringReader sr = new StringReader(xml);
		Object obj = um.unmarshal(sr);
		sr.close();
		return obj;
	}
	
	/**
	 * 获取元素的值
	 * @param report
	 * @param elementName
	 * @return
	 */
	private String getElement(String report,String elementName){
		String result = "";
		int indexStart = report.indexOf("<"+elementName+">");
        int indexEnd  = report.indexOf("</"+elementName+">");
        if(indexStart!=-1){
        	result = report.substring(indexStart+elementName.length()+2, indexEnd); 
        }
        return result;
	}
	
	/**
	 * 从返回的data数据中取得相应的byte数组
	 * @param data
	 * @return
	 */
	private byte[] getBytesFromData(String data){
		String[] byteStrs = data.split(",");
		int len = byteStrs.length;
		byte[] result = new byte[len];
		
		for(int i = 0; i < len; i++){
			String s = byteStrs[i];
			int bint = Integer.parseInt(s);
			result[i] = (byte)bint;
		}
		
		return result;
	}
	
	/**
	 * 保存ZIP文件
	 * @param b
	 * @param computerNo
	 * @return
	 * @throws IOException
	 */
	private File saveTempFile(byte[] b, String computerNo) throws IOException{
		//文件路径
		String filePath = SysConst.FJ_INVOICE_FILE_PATH;
		File dirPath = new File(filePath);
		//如果目录不存在，则创建
		if(!dirPath.isDirectory()){
			dirPath.mkdir();
		}
		String fileName = "06_FPLG_"+computerNo+"_"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
				+ ".ZIP";
		//创建ZIP文件
		File tempFile = new File(filePath + java.io.File.separator + fileName);
		if (!tempFile.exists()) {
			tempFile.createNewFile();
		}

		FileOutputStream out = new FileOutputStream(tempFile);

		out.write(b);

		out.flush();
		out.close();

		return tempFile;
	}
	
	/**
	 * 加密请求报文
	 * @param requestXML
	 * @param computerNo
	 * @return
	 * @throws Exception
	 */
	private String jiamiBody(String requestXML, String computerNo) throws Exception{
		String result = "";
		//文件路径
		String filePath = SysConst.FJ_INVOICE_FILE_PATH;
		File dirPath = new File(filePath);
		//如果目录不存在，则创建
		if(!dirPath.isDirectory()){
			dirPath.mkdir();
		}
		String fileName = "07_FPSC_"+computerNo+"_"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		//创建DAT文件
		File tempFile = new File(filePath + java.io.File.separator + fileName+".DAT");
		if (!tempFile.exists()) {
			tempFile.createNewFile();
		}
		//把xml写入DAT文件
		FileOutputStream out = new FileOutputStream(tempFile);
		out.write(requestXML.getBytes("UTF-8"));
		out.flush();
		out.close();
		//压缩DAT
		AntZip antZip = new AntZip();
		String zipFullPath = filePath + java.io.File.separator + fileName+".zip";
		antZip.doZip(new File[]{tempFile}, zipFullPath);
		//删除dat文件
		if(tempFile.exists()){
			tempFile.delete();
		}
		//从zip文件读取byte字节数组
		File zipFile = new File(zipFullPath);
		FileInputStream input = new FileInputStream(zipFile);
		List<Byte> listContent = new ArrayList<Byte>();
        byte buffer1[] = new byte[1024];
        int len=0;
        while((len = input.read(buffer1)) > 0) 
        {
            for(int i = 0; i < len; i++)
                listContent.add(new Byte(buffer1[i]));
        }
        input.close();
        
        byte result0[] = new byte[listContent.size()];
        for(int i = 0; i < listContent.size(); i++)
            result0[i] = listContent.get(i).byteValue();

        //最终得到以逗号隔开的字符串
        String strResult0 = "";
        for(int i = 0; i < result0.length; i++)
        {
            String single = (new StringBuilder()).append(result0[i]).toString();
            strResult0 = (new StringBuilder(String.valueOf(strResult0))).append(single).append(",").toString();
        }
        strResult0 = strResult0.substring(0, strResult0.length() - 1);
		//删除zip文件
		if(zipFile.exists()){
			zipFile.delete();
		}
		//加密字符串
		result = CodeUtil.encodeStr(strResult0);
		return result;
	}
	
	
	/**
	 * 组织发票开具信息对象
	 * @param vcInvoicePrint
	 * @return
	 */
	private InvoicePrintDTO generateFpkjxx(VcInvoicePrint vcInvoicePrint) {
		InvoicePrintDTO fpkjxx = new InvoicePrintDTO();
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/*
		 * 发票开具信息
		 */
		fpkjxx.setFpdm(vcInvoicePrint.getInvoiceCode());//发票代码
		fpkjxx.setFphm(vcInvoicePrint.getInvoiceNo());//发票号码
		//开票时间
		if(vcInvoicePrint.getPrintDate()==null){
			fpkjxx.setKpsj(dateFmt.format(new Date()));
		}else{
			fpkjxx.setKpsj(dateFmt.format(vcInvoicePrint.getPrintDate()));
		}
		fpkjxx.setJddm(vcInvoicePrint.getInvoiceCode());//机打代码：同发票代码
		fpkjxx.setJdhm(vcInvoicePrint.getInvoiceNo());//机打号码：同发票号码
		if(vcInvoicePrint.getStartDate()!=null){
			fpkjxx.setQssj(dateFmt.format(vcInvoicePrint.getStartDate()));//起始时间
		}
		if(vcInvoicePrint.getEndDate()!=null){
			fpkjxx.setJssj(dateFmt.format(vcInvoicePrint.getEndDate()));//结束时间
		}
		fpkjxx.setJym(vcInvoicePrint.getCheckNum());//校验码
		fpkjxx.setFkfmc(vcInvoicePrint.getPayerName());//付款方名称
		//开票金额
		fpkjxx.setKpje(new BigDecimal(vcInvoicePrint.getAmount().toString()).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
		fpkjxx.setFkfhm("");//付款方号码
		fpkjxx.setDxje(vcInvoicePrint.getUpperAmount());//大写金额
		fpkjxx.setHyflDm("03");//行业分类代码
		fpkjxx.setHyflMc("金融保险业");//行业分类名称
		fpkjxx.setSkfmc(vcInvoicePrint.getRecipientName());//收款方名称
		fpkjxx.setSkfhm(vcInvoicePrint.getRecipientCode());//收款方号码
		fpkjxx.setCzrMc(vcInvoicePrint.getBillerName());//开票人
		//modify by chy 20140217 FIN-122 备注信息不上传地税
		//fpkjxx.setBz(vcInvoicePrint.getRemark());//备注
		if(vcInvoicePrint.getOperatorDate()!=null){
			fpkjxx.setCzsj(timeFmt.format(vcInvoicePrint.getOperatorDate()));//操作时间
		}else{
			fpkjxx.setCzsj(timeFmt.format(new Date()));
		}
		String fpzt = "";
		if("1".equals(vcInvoicePrint.getPrintKind())){//正常开具
			fpzt="21";//21开具-正常
		}else if("2".equals(vcInvoicePrint.getPrintKind())){//负数
			fpzt="23";//23开具-负数
		}else if("3".equals(vcInvoicePrint.getPrintKind())){//作废
			fpzt="22";//22开具-作废
		}
		//负数
		if(vcInvoicePrint.getAmount()!=null && vcInvoicePrint.getAmount().signum() < 0){
			fpzt = "23";
		}
		fpkjxx.setFpzt(fpzt);//发票状态
		fpkjxx.setTdysdm(vcInvoicePrint.getPrintTypeCode());//套打样式代码
		if("22".equals(fpzt)){
			fpkjxx.setZfr(vcInvoicePrint.getCanceledOpr());//作废人
			fpkjxx.setZfrq(dateFmt.format(vcInvoicePrint.getCanceldDate()));//作废时间
		}else if("23".equals(fpzt)){
			fpkjxx.setBchFpdm(vcInvoicePrint.getCounteractedInvoiceCode());//被冲红的发票代码
			fpkjxx.setBchFphm(vcInvoicePrint.getCounteractedInvoiceNo());//被冲红的发票号码
		}
		/*
		 * 发票开具扩展信息、开具明细信息
		 */
		if(!"01".equals(fpkjxx.getTdysdm())){
			if(vcInvoicePrint.getVcInvoicePrintExtList()!=null && vcInvoicePrint.getVcInvoicePrintExtList().size()>0){
				VcInvoicePrintExt ext = vcInvoicePrint.getVcInvoicePrintExtList().get(0);
				//扩展信息
				InvoicePrintExtDTO fpkjext = new InvoicePrintExtDTO();
				//明细信息
				List<InvoicePrintDetDTO> kjmxList = new ArrayList<InvoicePrintDetDTO>();
				InvoicePrintDetDTO kjmx = new InvoicePrintDetDTO();
				fpkjext.setV1(vcInvoicePrint.getPolicyNo());//保单号
				fpkjext.setV2(vcInvoicePrint.getEndorseNo());//批单号
				fpkjext.setV3(ext.getApplyName());//投保人
				kjmxList.add(kjmx);
				kjmx.setKjxmXh("1");
				if("03".equals(fpkjxx.getTdysdm())){
					fpkjext.setV4(ext.getAreaName());//投保地名称
					fpkjext.setV5(ext.getAreaCode());//投保地代码
					kjmx.setKjxmDm(ext.getItemCode());//开具项目代码
					kjmx.setKjxmMc(ext.getItemName());//开具项目名称
					kjmx.setKjnr(ext.getRiskName());//开具内容
					//金额
					kjmx.setJe(new BigDecimal(vcInvoicePrint.getAmount().toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}else if("02".equals(fpkjxx.getTdysdm())){
					fpkjext.setV4(ext.getCarAreaName());//车籍地名称
					fpkjext.setV5(ext.getCarAreaCode());//车籍地代码
					fpkjext.setV6(ext.getLicenseNo());//车牌号码
					fpkjext.setV7(ext.getCarKind());//车辆类别
					fpkjext.setV8(ext.getUnit());//单位
					//fpkjext.setV9(ext.getQuantity()==null?"":(ext.getQuantity().toString()));//数量
					fpkjext.setV9(ext.getQuantity());//数量
					if(ext.getLastEndDate()!=null){
						fpkjext.setV10(dateFmt.format(ext.getLastEndDate()));//上期缴纳交强险截止日期
					}
					fpkjext.setV11(ext.getUseNature());//使用性质
					//fpkjext.setV12(ext.getItem());//项目
					fpkjext.setV12("机动车交强险");//固定为“机动车交强险”不得改变
					//fpkjext.setV13(ext.getAmount());//保险金额
					fpkjext.setV13(ext.getPremium()==null?"":(ext.getPremium().toString()));//保险金额
					fpkjext.setV14(ext.getEngineNo());//发动机号
					fpkjext.setV15(ext.getFrameNo());//车架号
					fpkjext.setV16(ext.getFuelCategory());//能源类别
					
					//代收车船税
					kjmx.setV1(ext.getTaxAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					kjmx.setV2(ext.getTaxYear());//税款所属期年
					kjmx.setV3(ext.getTaxMonthStart());//税款所属期起始月
					kjmx.setV4(ext.getTaxMonthEnd());//税款所属期终止月
					if(ext.getLateFee()!=null){
						kjmx.setV5(new BigDecimal(ext.getLateFee().toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//滞纳金
					}
				}
				fpkjxx.setFpkjExt(fpkjext);
				fpkjxx.setKjmx(kjmxList);
			}
		}
		/*
		 * 发票开具明细信息
		 */
		/*List<VcInvoicePrintDet> detList = vcInvoicePrint.getVcInvoicePrintDetList();
		if(detList!=null && detList.size()>0){
			List<InvoicePrintDetDTO> kjmxList = new ArrayList<InvoicePrintDetDTO>();
			for(VcInvoicePrintDet det : detList){
				InvoicePrintDetDTO kjmx = new InvoicePrintDetDTO();
				kjmx.setKjxmXh(det.getSerialNo()+"");
				if("02".equals(fpkjxx.getTdysdm())){
					if(det.getVesselTax()!=null){
						//代收车船税
						kjmx.setV1(new BigDecimal(det.getVesselTax().toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
					if(StringUtils.isNotEmpty(det.getVesselTax())){
						//代收车船税
						if(StringUtil.isNumber(det.getVesselTax())){
							kjmx.setV1(new BigDecimal(det.getVesselTax()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						}else{
							kjmx.setV1(det.getVesselTax());
						}
					}
					kjmx.setV2(det.getTaxYear());//税款所属期年
					kjmx.setV3(det.getTaxMonthStart());//税款所属期起始月
					kjmx.setV4(det.getTaxMonthEnd());//税款所属期终止月
					if(det.getLateFee()!=null){
						kjmx.setV5(new BigDecimal(det.getLateFee().toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//滞纳金
					}
				}else{
					kjmx.setKjxmDm(det.getItemCode());//开具项目代码
					kjmx.setKjxmMc(det.getItemName());//开具项目名称
					kjmx.setKjnr(det.getContent());//开具内容
					//金额
					if(det.getAmount()!=null){
						kjmx.setJe(new BigDecimal(det.getAmount().toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
				}
				kjmxList.add(kjmx);
			}
			fpkjxx.setKjmx(kjmxList);
		}*/
		return fpkjxx;
	}

	public void setFuJianInvoiceDao(FuJianInvoiceDao fuJianInvoiceDao) {
		this.fuJianInvoiceDao = fuJianInvoiceDao;
	}

	public void setJtServicePortType(JTServicePortType jtServicePortType) {
		this.jtServicePortType = jtServicePortType;
	}
	
}
