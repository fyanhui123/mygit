package com.tapi.tcs.vc.subfunc.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.invoice.vo.InvoiceDetialXMDTO;
import com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXBTDTO;
import com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXDTO;
import com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXMXDTO;
import com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXMainFileDTO;
import com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXMainFileMxDTO;
import com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXSSQDTO;
import com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXXXDTO;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoicePrintDet;

public class ExportXml {
	
	 public static void main(String[] args) throws Exception {
//		   generateFileSX();
		 testGenerateFileSXBody();
	}
	/**
	 *   山西地区导出文件夹
	 * @param vcInvoicePrintTmpList
	 * @param filePath
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private static String generateFileSX() throws  Exception
	{
		InvoiceSXMainFileDTO  invoiceSXMainFileDTO=new  InvoiceSXMainFileDTO();  //主文件
		List<InvoiceSXMainFileMxDTO>  invoiceMainSXDTOList=new  ArrayList<InvoiceSXMainFileMxDTO>();
			InvoiceSXMainFileMxDTO   invoiceSXMainFileMxDTO=new InvoiceSXMainFileMxDTO();
			List<InvoiceDetialXMDTO> detailList = new ArrayList<InvoiceDetialXMDTO>();
			invoiceSXMainFileMxDTO.setNsrbm("140106792247908");   //纳税人电子档案号
			invoiceSXMainFileMxDTO.setSsq("201307");  //所属期
			invoiceSXMainFileMxDTO.setWjsl("1");//开票信息文件数量
			invoiceSXMainFileMxDTO.setKpjehj("2000.00");//开票金额合计
			invoiceMainSXDTOList.add(invoiceSXMainFileMxDTO); 
		  invoiceSXMainFileDTO.setInvoiceMainSXDTOList(invoiceMainSXDTOList);
		   //返回的json数据格式：文件路径==文件名
			String fileData = "";
			String separator = java.io.File.separator;
		    //对象转换成xml
			String xml =generateSXMainFileXML(invoiceSXMainFileDTO);
			//xml文件完整路径 
			String  mainFileName="D:/都邦保险山西分公司-201307";
			String  fileName="都邦保险山西分公司";
			String  ssq="201307";
			String xmlFullPath =fileName+"-"+ssq+".xml";
			
			//不存在就新建
			File  ifExists=new  File(mainFileName);
			if(!ifExists.exists()){
				ifExists.mkdir();
			}
			//生成xml文件开始
			File xmlFile = new File(mainFileName+separator+xmlFullPath);
			
			FileOutputStream out = new FileOutputStream(xmlFile);
			OutputStreamWriter writer = new OutputStreamWriter(out, "GBK");
			writer.write(xml);
			writer.flush();
			writer.close();
			out.close();
			//生成xml文件结束
			return fileData;
     }
	
	private static String testGenerateFileSXBody() throws  Exception{
		
		// 主文件start
		String  nsrbm="140106792247908";
		Date  ssq=new Date();
		SimpleDateFormat formate=new SimpleDateFormat("yyyyMM");
		String  formatSsq=formate.format(ssq);  //格式化日期
		InvoiceSXMainFileDTO  invoiceSXMainFileDTO=new  InvoiceSXMainFileDTO();  //主文件
		List<InvoiceSXMainFileMxDTO>  invoiceMainSXDTOList=new  ArrayList<InvoiceSXMainFileMxDTO>();
			InvoiceSXMainFileMxDTO   invoiceSXMainFileMxDTO=new InvoiceSXMainFileMxDTO();
			List<InvoiceDetialXMDTO> detailList = new ArrayList<InvoiceDetialXMDTO>();
			invoiceSXMainFileMxDTO.setNsrbm(nsrbm);   //纳税人电子档案号
			invoiceSXMainFileMxDTO.setSsq(formatSsq);  //所属期 
			invoiceSXMainFileMxDTO.setWjsl("1");//开票信息文件数量      开票明细的条数
			invoiceSXMainFileMxDTO.setKpjehj("2000.00");//开票金额
			invoiceMainSXDTOList.add(invoiceSXMainFileMxDTO); 
		  invoiceSXMainFileDTO.setInvoiceMainSXDTOList(invoiceMainSXDTOList);
		// 主文件end
		
		  
		
		List<VcInvoicePrint> vcInvoicePrintTmpList=null;
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df = new DecimalFormat("0.00");
		InvoiceSXDTO  invoiceSXDTO=new  InvoiceSXDTO();      //xml  start
			List<InvoiceSXSSQDTO> invoiceMainSXInfoDTOList=new  ArrayList<InvoiceSXSSQDTO>();  
			InvoiceSXSSQDTO invoiceSXSSQDTO=new  InvoiceSXSSQDTO();  //xml SSQ
			invoiceSXSSQDTO.setNsrbm(nsrbm);   //纳税人电子档案号
			invoiceSXSSQDTO.setSsq(formatSsq);  //所属期
			invoiceSXSSQDTO.setWjbh("1"); //开票信息文件编号
			
			List<InvoiceSXXXDTO>  invoiceSXXXDTOList=new  ArrayList<InvoiceSXXXDTO>();
			List<InvoiceSXXXDTO>  invoiceSXXXDTOList1=new  ArrayList<InvoiceSXXXDTO>();
			InvoiceSXXXDTO  invoiceSXXXDTO=new  InvoiceSXXXDTO();   //xml   xx  start
			InvoiceSXXXDTO  invoiceSXXXDTO1=new  InvoiceSXXXDTO();   //xml   xx  start
			InvoiceSXBTDTO invoiceSXBTDTO=new InvoiceSXBTDTO();     //xml   bt
			InvoiceSXBTDTO invoiceSXBTDTO1=new InvoiceSXBTDTO();     //xml   bt
			
			invoiceSXBTDTO.setNsrsbh("029901665");    //纳税人识别号
			invoiceSXBTDTO.setSkfDM("2434556565");      //收款方代码
			invoiceSXBTDTO.setSkfMc("上海华为");     //收款方名称
			invoiceSXBTDTO.setFkfDm("1224343434");    //付款方代码
			invoiceSXXXDTO.setInvoiceSXBTDTO(invoiceSXBTDTO);    //xml   xx  end1
			
			invoiceSXBTDTO1.setNsrsbh("0299444");    //纳税人识别号
			invoiceSXBTDTO1.setSkfDM("d3sf33");      //收款方代码
			invoiceSXBTDTO1.setSkfMc("中兴软件");     //收款方名称
			invoiceSXBTDTO1.setFkfDm("2343434");    //付款方代码
			invoiceSXXXDTO1.setInvoiceSXBTDTO(invoiceSXBTDTO1);    //xml   xx  end1
			
			List<InvoiceSXMXDTO> invoiceSXMXDTOList=new  ArrayList<InvoiceSXMXDTO>();   //xml   mx  start
			//发票开具明细
					InvoiceSXMXDTO   invoiceSXMXDTO=new  InvoiceSXMXDTO();
					invoiceSXMXDTO.setXh("1");    //序号
					invoiceSXMXDTO.setXmdm("12");//项目代码
					invoiceSXMXDTO.setXmmc("外包项目");//项目名称
					invoiceSXMXDTO.setXmsm("tiannan");//项目说明
					invoiceSXMXDTO.setDw("10");    //单位
					
					InvoiceSXMXDTO   invoiceSXMXDTO1=new  InvoiceSXMXDTO();
					invoiceSXMXDTO1.setXh("2");    //序号
					invoiceSXMXDTO1.setXmdm("13");//项目代码
					invoiceSXMXDTO1.setXmmc("华为外包");//项目名称
					invoiceSXMXDTO1.setXmsm("huawei");//项目说明
					invoiceSXMXDTO1.setDw("20");    //单位
					
					invoiceSXMXDTOList.add(invoiceSXMXDTO1);
					invoiceSXMXDTOList.add(invoiceSXMXDTO);     //xml   mx  end
					invoiceSXXXDTO.setInvoiceSXMXDTOList(invoiceSXMXDTOList);   //xml   xx  end2
					invoiceSXXXDTO1.setInvoiceSXMXDTOList(invoiceSXMXDTOList);   //xml   xx  end2
			invoiceSXXXDTO.setInvoiceSXMXDTOList(invoiceSXMXDTOList);
			invoiceSXXXDTO1.setInvoiceSXMXDTOList(invoiceSXMXDTOList);
			invoiceSXXXDTOList.add(invoiceSXXXDTO); 
			invoiceSXXXDTOList.add(invoiceSXXXDTO1); 
			invoiceSXXXDTOList1.add(invoiceSXXXDTO);
			invoiceSXXXDTOList1.add(invoiceSXXXDTO1); 
			invoiceSXSSQDTO.setInvoiceSXXXDTOList(invoiceSXXXDTOList);
			invoiceSXSSQDTO.setInvoiceSXXXDTOList(invoiceSXXXDTOList1);
			invoiceMainSXInfoDTOList.add(invoiceSXSSQDTO);
			invoiceSXDTO.setInvoiceMainSXInfoDTOList(invoiceMainSXInfoDTOList);  //xml  end
			
			//返回的json数据格式：文件路径==文件名
			String fileData = "";
			String separator = java.io.File.separator;
		    //对象转换成xml
			String mianXml =generateSXMainFileXML(invoiceSXMainFileDTO);
			//xml文件完整路径 
			String  mainFileName="D:/都邦保险山西分公司-201307";
			String  fileName="都邦保险山西分公司";
			String  ssq1="201307";
			String xmlFullPath =nsrbm+".xml";
			//不存在就新建
			File  ifExists=new  File(mainFileName);
			if(!ifExists.exists()){
				ifExists.mkdir();
			}
			//生成xml文件开始
			File xmlFile = new File(mainFileName+separator+xmlFullPath);
			FileOutputStream out = new FileOutputStream(xmlFile);
			OutputStreamWriter writer = new OutputStreamWriter(out, "GBK");
			writer.write(mianXml);
			writer.flush();
			writer.close();
			out.close();
			
			
		     	// 开票文件start
			    String xml =generateSXXML(invoiceSXDTO);
				String kPXmlFullPath =nsrbm+"_1"+".xml";
				File KpXmlFile = new File(mainFileName+separator+kPXmlFullPath);
				FileOutputStream KpOut = new FileOutputStream(KpXmlFile);
				OutputStreamWriter kpwriter = new OutputStreamWriter(KpOut, "GBK");
				kpwriter.write(xml);
				kpwriter.flush();
				kpwriter.close();
				KpOut.close();
			
			System.out.println(xml);
		   // 开票文件end
			
			
			//一条的情况   两条的情况
		/*	if(invoiceSXXXDTOList!=null && invoiceSXXXDTOList.size()>0 ){
				int  j=invoiceSXXXDTOList.size();
				if(j<=1){
					
				}else{
					
				}
			}
			int  j=4;
			// 开票文件start
			String xml =generateSXXML(invoiceSXDTO);
			for(int i=1;i<=j;i++){
				String kPXmlFullPath =nsrbm+"_"+i+".xml";
				File KpXmlFile = new File(mainFileName+separator+kPXmlFullPath);
				FileOutputStream KpOut = new FileOutputStream(KpXmlFile);
				OutputStreamWriter kpwriter = new OutputStreamWriter(KpOut, "GBK");
				kpwriter.write(xml);
				kpwriter.flush();
				kpwriter.close();
				KpOut.close();
			}
			*/
			
		   // 开票文件end
			return  xml;
  }
	
	/**
	 *   山西地区导出文件夹
	 * @param vcInvoicePrintTmpList
	 * @param filePath
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private String generateFileSX(List<VcInvoicePrint> vcInvoicePrintTmpList, String filePath) 
	throws  BusinessException,IOException,Exception
	{
		InvoiceSXMainFileDTO  invoiceSXMainFileDTO=new  InvoiceSXMainFileDTO();  //主文件
		List<InvoiceSXMainFileMxDTO>  invoiceMainSXDTOList=new  ArrayList<InvoiceSXMainFileMxDTO>();
		String  nsrbm="140106792247908";
		String separator = java.io.File.separator;	
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfSsq=new SimpleDateFormat("yyyyMM"); //格式化所属期
		DecimalFormat df = new DecimalFormat("0.00");
		String  ssq="";
		List<InvoiceSXSSQDTO> invoiceMainSXInfoDTOList=new  ArrayList<InvoiceSXSSQDTO>();  
		List<InvoiceSXXXDTO>  invoiceSXXXDTOList=new  ArrayList<InvoiceSXXXDTO>();
		InvoiceSXDTO  invoiceSXDTO=new  InvoiceSXDTO();      //xml  start
		for(VcInvoicePrint vcInvoicePrint : vcInvoicePrintTmpList){
			InvoiceSXMainFileMxDTO   invoiceSXMainFileMxDTO=new InvoiceSXMainFileMxDTO();
			invoiceSXMainFileMxDTO.setNsrbm(nsrbm);   //纳税人电子档案号
			if(vcInvoicePrint.getBelongStage()!=null){
				ssq=sdfSsq.format(vcInvoicePrint.getBelongStage());
			    invoiceSXMainFileMxDTO.setSsq(ssq);  //所属期
			}
			invoiceSXMainFileMxDTO.setWjsl("1");//开票信息文件数量   
			if(vcInvoicePrint.getAccount()!=null){
			    invoiceSXMainFileMxDTO.setKpjehj(ssq);//开票金额合计
			}
			invoiceMainSXDTOList.add(invoiceSXMainFileMxDTO); 

			invoiceSXMainFileDTO.setInvoiceMainSXDTOList(invoiceMainSXDTOList);
		
		    InvoiceSXSSQDTO invoiceSXSSQDTO=new  InvoiceSXSSQDTO();  //xml SSQ
			invoiceSXSSQDTO.setNsrbm(nsrbm);   //纳税人电子档案号
			invoiceSXSSQDTO.setSsq(ssq);  //所属期
			invoiceSXSSQDTO.setWjbh("1"); //开票信息文件编号  
			
			InvoiceSXXXDTO  invoiceSXXXDTO=new  InvoiceSXXXDTO();   //xml   xx  start
			InvoiceSXBTDTO invoiceSXBTDTO=new InvoiceSXBTDTO();     //xml   bt
			
			invoiceSXBTDTO.setNsrsbh(vcInvoicePrint.getTaxpayerCode());    //纳税人识别号
			invoiceSXBTDTO.setSkfDM(vcInvoicePrint.getRecipientCode());      //收款方代码
			invoiceSXBTDTO.setSkfMc(vcInvoicePrint.getRecipientName());     //收款方名称
			invoiceSXBTDTO.setFkfDm(vcInvoicePrint.getPayerCode());    //付款方代码
			invoiceSXBTDTO.setFkfMc(vcInvoicePrint.getPayerName());    //付款方名称
			invoiceSXBTDTO.setFpdm(vcInvoicePrint.getInvoiceCode());      //发票代码
			invoiceSXBTDTO.setFpmc(vcInvoicePrint.getInvoiceName());      //发票名称
			invoiceSXBTDTO.setFphm(vcInvoicePrint.getInvoiceNo());      //发票号码
			if(vcInvoicePrint.getPrintDate()!=null){
			  invoiceSXBTDTO.setKprq(sf1.format(vcInvoicePrint.getPrintDate()));      //开票日期   
			}
			invoiceSXBTDTO.setYfpdm(vcInvoicePrint.getCounteractedInvoiceCode());    //原发票代码
			invoiceSXBTDTO.setYfphm(vcInvoicePrint.getCounteractedInvoiceNo());    //原发票号码
			invoiceSXBTDTO.setHeje(vcInvoicePrint.getUpperAmount());      //合计金额
			if(vcInvoicePrint.getTaxAmount()!=null){
		    invoiceSXBTDTO.setHese(df.format(vcInvoicePrint.getTaxAmount()));      //合计税额	
			}
			if(vcInvoicePrint.getTaxRate()!=null){
				invoiceSXBTDTO.setSl(df.format(vcInvoicePrint.getTaxRate()));         //税率
			}
			invoiceSXBTDTO.setSkymc(vcInvoicePrint.getRecipientOpr());   //收款员名称
			invoiceSXBTDTO.setKpymc(vcInvoicePrint.getBillerName());   //开票员名称
			invoiceSXBTDTO.setHyDm(vcInvoicePrint.getIndustryCode());     //行业代码
			invoiceSXBTDTO.setHyMc(vcInvoicePrint.getIndustryName());     //行业名称
			invoiceSXBTDTO.setKplx(vcInvoicePrint.getPrintKind());     //开票类型  0正票  1作废票  -1红票
			invoiceSXBTDTO.setFpzlDm(vcInvoicePrint.getInvoiceKindCode());  //发票种类代码
			invoiceSXBTDTO.setFpzlMc(vcInvoicePrint.getInvoiceKindName());  //发票种类名称
			invoiceSXBTDTO.setSwjgDm(vcInvoicePrint.getTaxOrgCode());  //税务机关代码
			invoiceSXBTDTO.setBz(vcInvoicePrint.getRemark());          //备注
			invoiceSXBTDTO.setDsjehj("");  // 代收金额合计       ------（发票开具明细信息中的代收车船税的合计）
			if(vcInvoicePrint.getAmount()!=null){
			  invoiceSXBTDTO.setZjehj(df.format(vcInvoicePrint.getAmount()));    //总合计金额	
			}
			
			List<VcInvoicePrintDet> vcInvoicePrintDetList = vcInvoicePrint.getVcInvoicePrintDetList();
			String  number=String.valueOf(vcInvoicePrintDetList.size());
			invoiceSXBTDTO.setMxhs(number);      //明细行数

			invoiceSXBTDTO.setFplx(vcInvoicePrint.getInvoiceType());//发票类型   1通用机打发票  2建筑业发票 3销售不动产发票  4卷筒票  5国际货运票
			invoiceSXXXDTO.setInvoiceSXBTDTO(invoiceSXBTDTO);    //xml   xx  end1
			
			List<InvoiceSXMXDTO> invoiceSXMXDTOList=new  ArrayList<InvoiceSXMXDTO>();   //xml   mx  start
			//发票开具明细
			if(vcInvoicePrintDetList!=null && vcInvoicePrintDetList.size()>0){
				for(VcInvoicePrintDet vcInvoicePrintDet:vcInvoicePrintDetList){
				    String  xh=String.valueOf(vcInvoicePrintDet.getSerialNo()); //integer转换成string
					InvoiceSXMXDTO   invoiceSXMXDTO=new  InvoiceSXMXDTO();
					invoiceSXMXDTO.setXh(xh);    //序号
					invoiceSXMXDTO.setXmdm(vcInvoicePrintDet.getItemCode());//项目代码
					invoiceSXMXDTO.setXmmc(vcInvoicePrintDet.getItemName());//项目名称
					invoiceSXMXDTO.setXmsm(vcInvoicePrintDet.getItemRemark());//项目说明
					invoiceSXMXDTO.setDw(vcInvoicePrintDet.getUnitName());    //单位
					if(vcInvoicePrintDet.getUnitPrice()!=null){
						invoiceSXMXDTO.setDj(df.format(vcInvoicePrintDet.getUnitPrice()));   //单价		
					}
					if(vcInvoicePrintDet.getQuantity()!=null){
						invoiceSXMXDTO.setShul(df.format(vcInvoicePrintDet.getQuantity()));   //数量	
					}
					if(vcInvoicePrintDet.getAmount()!=null){
						invoiceSXMXDTO.setJe(df.format(vcInvoicePrintDet.getAmount()));   //金额
					}
					if(vcInvoicePrintDet.getTaxRate()!=null){
						invoiceSXMXDTO.setSl(df.format(vcInvoicePrintDet.getTaxRate()));  //税率
					}
					if(vcInvoicePrintDet.getTaxAmount()!=null){
						invoiceSXMXDTO.setSe(df.format(vcInvoicePrintDet.getTaxAmount()));  //税额
					}
					invoiceSXMXDTO.setIsds("Y"); //是否代收项目  Y/N
					invoiceSXMXDTOList.add(invoiceSXMXDTO);     //xml   mx  end
				}
			}
			invoiceSXXXDTO.setInvoiceSXMXDTOList(invoiceSXMXDTOList);  //xml   xx  end2
			invoiceSXXXDTOList.add(invoiceSXXXDTO); 
			invoiceSXSSQDTO.setInvoiceSXXXDTOList(invoiceSXXXDTOList);
			invoiceMainSXInfoDTOList.add(invoiceSXSSQDTO);
			invoiceSXDTO.setInvoiceMainSXInfoDTOList(invoiceMainSXInfoDTOList);  //xml  end
			
			}
		
		 // 主文件start
		    //对象转换成xml
			String mainXml = generateSXMainFileXML(invoiceSXMainFileDTO);
			//xml文件完整路径
			String mainXmlFullPath = filePath+separator+nsrbm+".xml"; 
			//生成xml文件开始
				File mainXmlFile = new File(mainXmlFullPath);
				FileOutputStream mianOut = new FileOutputStream(mainXmlFile);
				OutputStreamWriter mainOwriter = new OutputStreamWriter(mianOut, "GBK");
				mainOwriter.write(mainXml);
				mainOwriter.flush();
				mainOwriter.close();
				mianOut.close();
			//生成xml文件结束
	      //主文件  end
		
				//开票文件start
				//对象转换成xml
					if(vcInvoicePrintTmpList!=null  && vcInvoicePrintTmpList.size()>0){
					 int  size=vcInvoicePrintTmpList.size();     //一个主文件对应的开票文件的数量
					  if(size<=50000){
						        String kpXml = generateSXKpXML(invoiceSXDTO);
							    //xml文件完整路径
							    String kpXmlFullPath = filePath+separator+nsrbm+"_1"+".xml";
							    //生成xml文件开始
								File kpXmlFile = new File(kpXmlFullPath);
								FileOutputStream kpOut = new FileOutputStream(kpXmlFile);
								OutputStreamWriter kpOwriter = new OutputStreamWriter(kpOut, "GBK");
								kpOwriter.write(kpXml);
								kpOwriter.flush();
								kpOwriter.close();
								kpOut.close();  
					  }
					  /*else{
						  throw new BusinessException("最多只能有50000条记录！");
					  }*/
					}
				//生成xml文件结束
			    //开票文件end
			
		    //返回的json数据格式：文件路径==文件名    
			String fileData = "";	
			fileData = filePath + "==" + mainXmlFullPath; //主文件的路径
			return  fileData;
	}
	
	/**
	 *  山西主文件 dto转换传xml
	 * @param invoiceSXMainFileDTO
	 * @return
	 * @throws Exception
	 */
	private static String generateSXMainFileXML(InvoiceSXMainFileDTO invoiceSXMainFileDTO) throws Exception {
		String xml = "";
		JAXBContext jc = JAXBContext.newInstance(new Class[] {com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXMainFileDTO.class});
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_ENCODING, "GBK");
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter sw = new StringWriter();
		m.marshal(invoiceSXMainFileDTO, sw);
		xml = sw.toString();
		return xml;
	}
	
	/**
	 *  山西开票文件 dto转换传xml
	 * @param invoiceSXMainFileDTO
	 * @return
	 * @throws Exception
	 */
	private static String generateSXXML(InvoiceSXDTO invoiceSXDTO) throws Exception {
		String xml = "";
		JAXBContext jc = JAXBContext.newInstance(new Class[] {com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXDTO.class});
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_ENCODING, "GBK");
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter sw = new StringWriter();
		m.marshal(invoiceSXDTO, sw);
		xml = sw.toString();
		return xml;
	}
	
	/**
	 *  山西开票文件 dto转换传xml
	 * @param invoiceSXMainFileDTO
	 * @return
	 * @throws Exception
	 */
	private static String generateSXKpXML(InvoiceSXDTO invoiceSXDTO) throws Exception {
		String xml = "";
		JAXBContext jc = JAXBContext.newInstance(new Class[] {com.tapi.tcs.vc.invoice.vo.shanxi.InvoiceSXDTO.class});
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_ENCODING, "GBK");
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter sw = new StringWriter();
		m.marshal(invoiceSXDTO, sw);
		xml = sw.toString();
		return xml;
	}
}