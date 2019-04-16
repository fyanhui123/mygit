/**
 * 
 */
package com.tapi.tcs.vc.insucard.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.tapi.service.fin.common.dto.RequestHeadDTO;
import com.tapi.service.fin.visa.intf.ActivationDetailDto;
import com.tapi.service.fin.visa.intf.PrePayFeeActCardRequest;
import com.tapi.service.fin.visa.intf.PrePayFeeActCardResponse;
import com.tapi.service.fin.visa.intf.VisaWebService;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesDetail;

/**
 * @author whj
 *
 */
public class MyCardSaleTest {
    
    /** 激活卡信息送收复接口service */
    private VisaWebService visaWebService;
   
    /**
     * @param args
     */
    public static void main(String[] args) {
        /*MyCardSaleTest test=new MyCardSaleTest();
        test.insucarPayFeeToPayment();*/
        // TODO Auto-generated method stub
        JaxWsProxyFactoryBean  jfb = new  JaxWsProxyFactoryBean();
        jfb.setAddress("http://10.100.133.170:8001/cxfServices/VisaWebService");
        jfb.setServiceClass(VisaWebService.class);
        VisaWebService visaWebService = (VisaWebService)jfb.create();
    }
    
    /**
     * 激活卡付费信息送收复
     */
    public PrePayFeeActCardResponse insucarPayFeeToPayment() {
        JaxWsProxyFactoryBean  jfb = new  JaxWsProxyFactoryBean();
        jfb.setServiceClass(VisaWebService.class);
        jfb.setAddress("http://10.100.133.170:8001/cxfServices/VisaWebService");
        visaWebService = (VisaWebService)jfb.create();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
        String recordCode =sdf.format(new Date());
        
        System.out.println("==============start【" +recordCode + "】========================");
        PrePayFeeActCardRequest request = new PrePayFeeActCardRequest();
        // 请求头
        RequestHeadDTO head = new RequestHeadDTO();
        head.setUser("yhui22");
        head.setPassword("ta8888");
      
        head.setSn(recordCode);

        System.out.println("请求sn：" + head.getSn());
        // 01 承保 02 理赔 03 销管 04 资金 05 费控 06收付 07单证
        head.setSystemCode("07");
        request.setHEAD(head);

        request.setBusinessNo("SC"+recordCode);
        request.setPayeeName("whj");
        request.setPayeeCode("yhui22");
        //0-投保人，1-业务员，2-代理人，3-再保人
        request.setPayeeType("1");
        request.setCurrency("CNY");

        request.setApplyDate(new Date());
        request.setCompanyCode("0164");
        request.setPayEndDate(new Date());

        
        VcInsuCardSalesDetail Temp_1 = new VcInsuCardSalesDetail(); 
        Temp_1.setStartNum("00000001");
        Temp_1.setEndNum("00000010");
        
        VcInsuCardSalesDetail Temp_2 = new VcInsuCardSalesDetail(); 
        Temp_2.setStartNum("00000050");
        Temp_2.setEndNum("00000080");
        List<VcInsuCardSalesDetail> vcInsuCardSalesDetailList = new ArrayList<VcInsuCardSalesDetail>();
        vcInsuCardSalesDetailList.add(Temp_1);
        vcInsuCardSalesDetailList.add(Temp_2);
           // vcInsuCardSalesRecord.getVcInsuCardSalesDetailList();

        List<ActivationDetailDto> activationDetailList = request.getActivationDetailList();
        ActivationDetailDto tempDto = null;
        // 格式化流水号为10位
        DecimalFormat df = new DecimalFormat("0");
        df.setMinimumIntegerDigits(8);
        double totalFee = 0d;
        for (Iterator iterator = vcInsuCardSalesDetailList.iterator(); iterator.hasNext();) {
            VcInsuCardSalesDetail detailTemp = (VcInsuCardSalesDetail) iterator.next();
            String startNum = detailTemp.getStartNum();
            String endNum = detailTemp.getEndNum();

            Long start = Long.valueOf(startNum);
            Long end = Long.valueOf(endNum);
            // 面值
            double value = 1d;

            for (Long k = start; k <= end; k++) {
                tempDto = new ActivationDetailDto();
                tempDto.setCardNo(df.format(k));
                tempDto.setAmount(value);
                activationDetailList.add(tempDto);
            }
            totalFee += value * (end - start + 1);
        }
        request.setPayFee(totalFee);
        PrePayFeeActCardResponse response = visaWebService.prePayFeeActCard(request);
        System.out.println("返回相应sn：" + response.getHEAD().getSn());
        System.out.println("返回相应代码：" + response.getHEAD().getResponseCode());
        System.out.println("++++++++++++end【" + recordCode + "】+++++++++++++++++++");
        return response;
    }

}
