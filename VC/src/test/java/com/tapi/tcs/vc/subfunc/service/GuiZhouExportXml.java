package com.tapi.tcs.vc.subfunc.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.tapi.tcs.vc.invoice.vo.guizhou.InvoiceGuiZhouKpInfoDTO;
import com.tapi.tcs.vc.invoice.vo.guizhou.InvoiceGuiZhouKpInfoDetDTO;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoicePrintExt;

public class GuiZhouExportXml {

	 public static void main(String[] args) throws Exception {
		 testGeneratorFileGuiZhou();
	} 
	 
  private  static  String  testGeneratorFileGuiZhou() throws Exception{
		 
		 InvoiceGuiZhouKpInfoDTO  infoDTO=new InvoiceGuiZhouKpInfoDTO();
		 List<InvoiceGuiZhouKpInfoDetDTO>  invoiceGuiZhouKpInfoDetDTOList=new  ArrayList<InvoiceGuiZhouKpInfoDetDTO>();
		 infoDTO.setInvoiceGuiZhouKpInfoDetDTOList(invoiceGuiZhouKpInfoDetDTOList);
		 String separator = java.io.File.separator;
		 String  nsrName="";  //纳税人名称
		 String  nsrGlbm="";  //纳税人管理编码
		 String  fpSsq="";   //发票所属期
		     //  开具信息
			 nsrName="天安保险业务部";
			 nsrGlbm="520190002007635";
			 fpSsq="201309";
			 String  bigD="0.00";
			 String  filePath="D:/test";
			 
			 SimpleDateFormat  dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 Date  date=new Date();
			 InvoiceGuiZhouKpInfoDetDTO  infoDetDTO=new InvoiceGuiZhouKpInfoDetDTO();
			 infoDetDTO.setSbh("245454");  //纳税人识别号
			 infoDetDTO.setXmmc("");  //项目名称
			 infoDetDTO.setXmbh("");  //项目编号
			 infoDetDTO.setJsxm("service外包项目");  //结算项目
			 infoDetDTO.setKpr("彭于晏");   //开票人
			 infoDetDTO.setFsrq(dateFormat.format(date));  //开票日期
			 infoDetDTO.setFkf("32887757878799");// 付款方税务登记证号/身份证号/组织机构代码
			 infoDetDTO.setFkfmc("天安保险项目"); //付款方名称
			 infoDetDTO.setKpje(bigD);//开票金额
			 infoDetDTO.setFphm("27378997878");//发票号码
			 infoDetDTO.setZbrbz("");//总包人标志
			 infoDetDTO.setFbrbz("");//分包人标志
			 infoDetDTO.setJslx(""); //结算类型
			 infoDetDTO.setBz("公司项目外派人员工资结算");//备注
			 infoDetDTO.setFpdmqc("2123232323232");//发票代码
			 infoDetDTO.setQtkxxz("");//房动产适用
			 infoDetDTO.setZfbz("0");//作废标志  0为正常发票，1为作废发票
			 infoDetDTO.setSwdjzzh("177263299777");//税务登记证号
			 infoDetDTO.setNsrmc("天安有限责任公司");//纳税人名称
			 infoDetDTO.setFplb("1");//发票类别  
			 // 1，通用机开票，2，建筑业发票，3，不动产发票，4保险业发票，5，保险中介发票，6，国际货运发票
			 infoDetDTO.setHbrq("");//航班日期
			 infoDetDTO.setQyg("");//起运港
			 infoDetDTO.setMdg("");//目的港
			 infoDetDTO.setXhg("");//卸货港
			 infoDetDTO.setTydh("");//托运单号
			 infoDetDTO.setFhr("");//复核人
			 infoDetDTO.setLxdh("");//联系电话
			 infoDetDTO.setDz("");//地址
			 infoDetDTO.setBxd("34343776768686862");//保险单
			 infoDetDTO.setBdh("32342342342423423");//批单号
			 infoDetDTO.setYhzh("1232334342323");//银行帐号
			 infoDetDTO.setYhmc("中国银行上海分行");//银行名称
			 infoDetDTO.setGsdjz("387887787822375712");//工商登记证号
			 infoDetDTO.setZghy("保险行业");//征管行业
			 infoDetDTO.setYhbm("8780000123213");//银行编码
			 // 开具扩展信息

			     infoDetDTO.setCbxz("0782");   //承保险种
				 infoDetDTO.setKxxz("保险业务系统");   //款项性质(使用性质)
				 infoDetDTO.setMj("");//数量
				 infoDetDTO.setDj("");//单价
				 infoDetDTO.setCcs(bigD);//车船税
				 infoDetDTO.setCcsznj(bigD);//车船税滞纳金
				 infoDetDTO.setBxf(bigD);//保险费
			 
			 invoiceGuiZhouKpInfoDetDTOList.add(infoDetDTO);
		 
		 //生成xml  start
		 String  mainXml=generatorGuiZhouXml(infoDTO);
		 String  fullFilePath=filePath+separator+nsrName+"_"+nsrGlbm+"_"+fpSsq+"_"+"开票数据(1)"+".xml";
		 
		 System.out.println(mainXml);
		 
		 File ifExistFile=new File(filePath);
		 if(!ifExistFile.exists()){
			 ifExistFile.mkdir();
		 }
		 
		 File  file=new File(fullFilePath);
		 FileOutputStream  out=new FileOutputStream(file);
		 OutputStreamWriter writer=new  OutputStreamWriter(out);
		 writer.write(mainXml);
		 writer.flush();
		 writer.close();
		 out.close();
		 //生成xml  end

		 
		 //返回数据
		 String  fileDate="";
			 fileDate=filePath+"=="+fullFilePath;	 
		 return fileDate;
}
 
	 private  static  String  generatorFileGuiZhou(List<VcInvoicePrint> vcInvoicePrintList,String filePath) throws Exception{
		 
		 InvoiceGuiZhouKpInfoDTO  infoDTO=new InvoiceGuiZhouKpInfoDTO();
		 List<InvoiceGuiZhouKpInfoDetDTO>  invoiceGuiZhouKpInfoDetDTOList=new  ArrayList<InvoiceGuiZhouKpInfoDetDTO>();
		 infoDTO.setInvoiceGuiZhouKpInfoDetDTOList(invoiceGuiZhouKpInfoDetDTOList);
		 String separator = java.io.File.separator;
		 String  nsrName="";  //纳税人名称
		 String  nsrGlbm="";  //纳税人管理编码
		 String  fpSsq="";   //发票所属期
		 DecimalFormat  format=new DecimalFormat("0.00");  //格式化金额
		     //  开具信息
		 for(VcInvoicePrint  vcInvoicePrint:vcInvoicePrintList){
			 nsrName=vcInvoicePrint.getTaxpayerName();
			 nsrGlbm=vcInvoicePrint.getManageCode();
			 fpSsq=vcInvoicePrint.getBelongStage();
			 
			 // 日期格式化
			 SimpleDateFormat  dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 
			 InvoiceGuiZhouKpInfoDetDTO  infoDetDTO=new InvoiceGuiZhouKpInfoDetDTO();
			 infoDetDTO.setSbh(vcInvoicePrint.getTaxpayerCode());  //纳税人识别号
			 infoDetDTO.setXmmc("");  //项目名称
			 infoDetDTO.setXmbh("");  //项目编号
			 infoDetDTO.setJsxm(vcInvoicePrint.getSettleItem());  //结算项目
			 infoDetDTO.setKpr(vcInvoicePrint.getRecipientOpr());   //开票人
			 if(null!=vcInvoicePrint.getPrintDate()  &&  !("").equals(vcInvoicePrint.getPrintDate())){
				 infoDetDTO.setFsrq(dateFormat.format(vcInvoicePrint.getPrintDate()));  //开票日期 
			 } 
			 infoDetDTO.setFkf(vcInvoicePrint.getPayerCode());// 付款方税务登记证号/身份证号/组织机构代码
			 infoDetDTO.setFkfmc(vcInvoicePrint.getPayerName()); //付款方名称
			 if(vcInvoicePrint.getAmount()!=null){
					infoDetDTO.setKpje(format.format(vcInvoicePrint.getAmount()));// 开票金额	
				}else{
					infoDetDTO.setKpje("");
				}
			 infoDetDTO.setFphm(vcInvoicePrint.getInvoiceNo());//发票号码
			 infoDetDTO.setZbrbz("");//总包人标志
			 infoDetDTO.setFbrbz("");//分包人标志
			 infoDetDTO.setJslx(""); //结算类型
			 infoDetDTO.setBz(vcInvoicePrint.getRemark());//备注
			 infoDetDTO.setFpdmqc(vcInvoicePrint.getInvoiceCode());//发票代码
			 infoDetDTO.setQtkxxz("");//房动产适用
			 infoDetDTO.setZfbz(vcInvoicePrint.getPrintKind());//作废标志  0为正常发票，1为作废发票
			 infoDetDTO.setSwdjzzh(vcInvoicePrint.getPayerCode());//税务登记证号
			 infoDetDTO.setNsrmc(vcInvoicePrint.getTaxpayerName());//纳税人名称
			 infoDetDTO.setFplb(vcInvoicePrint.getInvoiceType());//发票类别  
			 // 1，通用机开票，2，建筑业发票，3，不动产发票，4保险业发票，5，保险中介发票，6，国际货运发票
			 infoDetDTO.setHbrq("");//航班日期
			 infoDetDTO.setQyg("");//起运港
			 infoDetDTO.setMdg("");//目的港
			 infoDetDTO.setXhg("");//卸货港
			 infoDetDTO.setTydh("");//托运单号
			 infoDetDTO.setFhr("");//复核人
			 infoDetDTO.setLxdh("");//联系电话
			 infoDetDTO.setDz("");//地址
			 infoDetDTO.setBxd(vcInvoicePrint.getPolicyNo());//保险单
			 infoDetDTO.setBdh(vcInvoicePrint.getEndorseNo());//批单号
			 infoDetDTO.setYhzh(vcInvoicePrint.getAccount());//银行帐号
			 infoDetDTO.setYhmc(vcInvoicePrint.getBank());//银行名称
			 infoDetDTO.setGsdjz(vcInvoicePrint.getRegistration());//工商登记证号
			 infoDetDTO.setZghy(vcInvoicePrint.getZgIndustry());//征管行业
			 infoDetDTO.setYhbm(vcInvoicePrint.getBankCode());//银行编码
			 // 开具扩展信息
			 for(VcInvoicePrintExt vcInvoicePrintExt:vcInvoicePrint.getVcInvoicePrintExtList()){
				 infoDetDTO.setCbxz(vcInvoicePrintExt.getRiskCode());   //承保险种
				 infoDetDTO.setKxxz(vcInvoicePrintExt.getUseNature());   //款项性质(使用性质)
				 infoDetDTO.setMj("");//数量
				 infoDetDTO.setDj("");//单价
				 if(vcInvoicePrintExt.getTaxAmount()!=null)
					{
						infoDetDTO.setCcs(format.format(vcInvoicePrintExt.getTaxAmount()));// 车船税
					}else{
						infoDetDTO.setCcs("");
					}
					if(vcInvoicePrintExt.getLateFee()!=null){
						infoDetDTO.setCcsznj(format.format(vcInvoicePrintExt.getLateFee()));// 车船税滞纳金
					}else{
						infoDetDTO.setCcsznj("");
					}
					if(vcInvoicePrintExt.getPremium()!=null){
						infoDetDTO.setBxf(format.format(vcInvoicePrintExt.getPremium()));// 保险费
					}else{
						infoDetDTO.setBxf("");
					}
			 }
			 
			 invoiceGuiZhouKpInfoDetDTOList.add(infoDetDTO);
		 }
		 
		 //生成xml  start
		 String  mainXml=generatorGuiZhouXml(infoDTO);
		 String  fullFilePath=filePath+separator+nsrName+"_"+nsrGlbm+"_"+fpSsq+".xml";
		 File  file=new File(fullFilePath);
		 FileOutputStream  out=new FileOutputStream(file);
		 OutputStreamWriter writer=new  OutputStreamWriter(out);
		 writer.write(mainXml);
		 writer.flush();
		 writer.close();
		 out.close();
		 //生成xml  end

		 
		 //返回数据
		 String  fileDate="";
			 fileDate=filePath+"=="+fullFilePath;	 
		 return fileDate;
	 } 
	 
	 /****
	  * 生成贵州xml
	  * @param infoDTO
	  * @return
	  */
	 public  static  String generatorGuiZhouXml(InvoiceGuiZhouKpInfoDTO  infoDTO) throws Exception{
		 String xml="";
		 JAXBContext  jaxb=JAXBContext.newInstance(com.tapi.tcs.vc.invoice.vo.guizhou.InvoiceGuiZhouKpInfoDTO.class);
		 Marshaller   marshaller=jaxb.createMarshaller();
		 marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
		 marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		 StringWriter  sw=new StringWriter();
		 marshaller.marshal(infoDTO, sw);
		 xml=sw.toString();
		 return   xml;
	 }
}
