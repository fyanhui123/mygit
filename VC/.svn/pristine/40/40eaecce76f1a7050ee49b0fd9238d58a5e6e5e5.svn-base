package com.tapi.tcs.vc.common.util;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.schema.model.VcReportRecord;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedRequestDTO;

/**
 * 报文处理公共类
 * @author xshibai
 *
 */
public final class ReportUtils {
	
	/**
	 * dto2xml
	 * @param obj
	 * @param className
	 * @return
	 * @throws BusinessException
	 */
	public static String DTOtoXML(Object obj, String className) throws BusinessException{
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
			//xml = xml.substring(xml.indexOf("\n")+1);
			System.out.println(xml);
			sw.close();
		}catch(Exception e){
			throw new BusinessException("报文转换失败", e);
		}
		return xml;
	}
	
	/**
	 * 组装请求报文
	 * @param dto
	 * @param reportTag 1: Server 2: client
	 * @param reportType I1: 发票核销，D1：单证核销，D2：单证作废，C1：激活卡缴费，C2：激活卡激活';
	 * @param requestXml
	 * @return
	 */
	public static VcReportRecord generateReport( String reportTag,String reportType,String requestXml,String docNum,String docVerCode){
		VcReportRecord report = new VcReportRecord();
		report.setReportTag(reportTag);
		report.setReportType(reportType);
		report.setRequestXml(requestXml);
		report.setCreatedBy("system");
		report.setRequestTime(new Date());
		if (StringUtils.isNotBlank(docNum))
			report.setDocNum(docNum);
		if (StringUtils.isNotBlank(docVerCode))
			report.setDocVerCode(docVerCode);
		return report;
	}
	
	
	public static int getRealLength(String value) {
		int length = 0;
//		try {
			length =  value.getBytes().length;
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return length;
	}
	
	public static int getRealLengthChar(char c) {
		int length = 0;
		length =  String.valueOf(c).getBytes().length;
		return length;
	}
	
	
	/**  
     * 判断是否是一个中文汉字  
     */  
	public static int String_length(String value) {
		  int valueLength = 0;
		  String chinese = "[\u4e00-\u9fa5]";
		  for (int i = 0; i < value.length(); i++) {
		   String temp = value.substring(i, i + 1);
		   if (temp.matches(chinese)) {
		    valueLength += 2;
		   } else {
		    valueLength += 1;
		   }
		  }
		  return valueLength;
		 }
  
    /**  
     * 按字节截取字符串  
     *   
     * @param orignal  
     *            原始字符串  
     * @param count  
     *            截取位数  
     * @return 截取后的字符串  
     * @throws UnsupportedEncodingException  
     *             使用了JAVA不支持的编码格式  
     */  
    public static String substring(String orignal, int count) {  
    	if (StringUtils.isBlank(orignal)) {
			return "";
		}
        // 原始字符不为null，也不是空字符串   
    	char c;
    	int rLen = 0;
    	int index = 0;
    	while (rLen <= count) {
    		if (index >=  orignal.length()) {
				break;
			}
    		c = orignal.charAt(index);
    		if (ReportUtils.getRealLengthChar(c) > 1) {
    			rLen = rLen +2;
			}else {
				rLen++;
			}
    		index ++;
			
		}
    	return orignal.substring(0,index -1);
//    	for (int i = 0; i < count; i++) {   
//            c = orignal.charAt(i);   
//            if (ReportUtils.getRealLengthChar(c) > 1) {
//				iCount ++;
//			}
//        }  
//    	return orignal.substring(0, count - iCount);
    	
//    	
//        if (orignal != null && !"".equals(orignal)) {   
//            
//            if (count > 0 && count < ReportUtils.getRealLength(orignal)) {   
//                StringBuffer buff = new StringBuffer();   
//                char c;
//                for (int i = 0; i < count; i++) {   
//                    c = orignal.charAt(i);   
//                    buff.append(c); 
//                    int len = ReportUtils.getRealLengthChar(c);
//                    if (len > 1) {   
//                        // 遇到中文汉字，截取字节总数减1   
//                        count = count-(len-1);   
//                    }   
//                }   
//                return buff.toString();   
//            }   
//        }   
//        return orignal;   
    }   
  
    public static void main(String[] args) {   
        String s = "我ZWR爱JAVA";
        System.out.println("原始字符串：" + s);    
        System.out.println("截取前1位：" + ReportUtils.substring(s, 1));   
        System.out.println("截取前2位：" + ReportUtils.substring(s, 2));   
        System.out.println("截取前4位：" + ReportUtils.substring(s, 3));   
        System.out.println("截取前6位：" + ReportUtils.substring(s, 4));   
        System.out.println("截取前1位：" + ReportUtils.substring(s, 5));   
        System.out.println("截取前2位：" + ReportUtils.substring(s, 6));   
        System.out.println("截取前4位：" + ReportUtils.substring(s, 7));   
        System.out.println("截取前6位：" + ReportUtils.substring(s, 8));  
        System.out.println("截取前6位：" + ReportUtils.substring(s, 9));  
        System.out.println("截取前6位：" + ReportUtils.substring(s, 10));  
        System.out.println("截取前6位：" + ReportUtils.substring(s, 11));
        System.out.println("截取前6位：" + ReportUtils.substring(s, 12));  
        System.out.println("截取前6位：" + ReportUtils.substring(s, 13));  
        System.out.println("截取前6位：" + ReportUtils.substring(s, 14));  


    }   
	
}
