package com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcInsuranceCard;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDet;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.dao.DocExecuteUsedDao;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardDoActivatedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardDoActivatedResponse;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardDoActivatedResponseDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardInfoDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.dao.InsuranceActivatedDao;

public class InsuranceActivatedServiceImpl implements InsuranceActivatedService {

    /***激活卡接口DAO****/
	private  InsuranceActivatedDao  insuranceActivatedDao;
	/**单证核销接口DAO*/
	private DocExecuteUsedDao docExecuteUsedDao;
	@Override
	public InsuranceCardDoActivatedResponse executeInsuranceActivatedService(
			InsuranceCardDoActivatedRequestDTO request)
			throws BusinessException {
		InsuranceCardDoActivatedResponse response = new  InsuranceCardDoActivatedResponse();
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
	try{
		InsuranceCardDoActivatedResponseDTO responseBody = new InsuranceCardDoActivatedResponseDTO();
		String  activeType=request.getActiveType();  //激活方式
		Date    activeTime=request.getActiveTime();  //激活时间
		InsuranceCardInfoDTO cardINfo=changeToDto(request);
		
		//add by whj 20131007  start 
		//对当前激活卡记录加锁，避免并发操作同一个激活卡
		insuranceActivatedDao.lockInsuranceCard(cardINfo);
		//add by whj 20131007 end  
		
		//激活卡、单证可使用表信息
		Object[] obj=insuranceActivatedDao.findInsuranceCardInfo(cardINfo);
		VcInsuranceCard  vcInsuranceCard=null;
		VcAvailableDoc   vcAvailableDoc=null;
		if(obj != null && obj.length>0){
			  vcInsuranceCard=(VcInsuranceCard) obj[0];
			  vcAvailableDoc=(VcAvailableDoc) obj[1];
		}
		
		//激活卡可用性检查
		Map<String,Object> checkResultMap=this.cardCheck(vcInsuranceCard,vcAvailableDoc);
		boolean isValid=(Boolean)checkResultMap.get("isValid");
		String msg=(String)checkResultMap.get("msg");
		
		//  激活卡不可使用
		if(!isValid){
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(msg);
			responseBody.setStatus("0");
			responseBody.setFlag(null);
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return response;
		}
		
		
		List<VcNormalVerification>  vcNormalVerifications=new  ArrayList<VcNormalVerification>();
		List<VcAvailableDoc>  vcAvailableDocs=new  ArrayList<VcAvailableDoc>();
		String  userCode=vcAvailableDoc.getTakerCode();   //操作人代码
		String  orgCode=vcAvailableDoc.getOrgCode();      //操作人所属机构
		VcNormalVerification vcNormalVerification = new VcNormalVerification();
		vcNormalVerification.setDocVerCode(vcAvailableDoc.getDocVerCode());
		vcNormalVerification.setDocNum(vcAvailableDoc.getDocNum());
		vcNormalVerification.setPressBatchNo(vcAvailableDoc.getPressBatchNo());
		vcNormalVerification.setBusinessNo(cardINfo.getBusinessNo());
		vcNormalVerification.setDocStatus("U1");
		vcNormalVerification.setVerifiedOprCode(userCode);
		vcNormalVerification.setVerifiedOrgCode(orgCode);
		vcNormalVerification.setVerifiedTime(new Date());
		vcNormalVerification.setVerifiedReason("1");
		vcNormalVerification.setCreatedBy(userCode);
		vcNormalVerification.setDateCreated(new Date());
		vcNormalVerification.setUpdatedBy(userCode);
		vcNormalVerification.setDateUpdated(new Date());
		
		//add by whj 20140228 增加核销明细表的保存 begin
		List<VcNormalVerifiedDet> vcNormalVerifiedDetList=new ArrayList<VcNormalVerifiedDet>();
        VcNormalVerifiedDet vcNormalVerifiedDet = new VcNormalVerifiedDet(); 
        vcNormalVerifiedDet.setBusinessNo(cardINfo.getBusinessNo());
        vcNormalVerifiedDet.setPayNo("1");//缴费期次默认1
        vcNormalVerifiedDet.setCounteractFlag("0");//红冲标志
        //vcNormalVerifiedDet.setBatchNo(null);//拆分批次 
        vcNormalVerifiedDet.setCreatedBy(userCode);
        vcNormalVerifiedDet.setUpdatedBy(userCode);
        vcNormalVerifiedDet.setDateCreated(new Date());
        vcNormalVerifiedDet.setDateUpdated(new Date());
        vcNormalVerifiedDet.setVcNormalVerification(vcNormalVerification);
        
        vcNormalVerifiedDetList.add(vcNormalVerifiedDet);
        vcNormalVerification.setVcNormalVerifiedDet(vcNormalVerifiedDetList);
		
		vcNormalVerifications.add(vcNormalVerification);
		vcAvailableDocs.add(vcAvailableDoc);
		
		
		//激活卡信息更新
	//	int  activeTypeValue=Integer.valueOf(activeType).intValue();
		vcInsuranceCard.setCardStatus("9");
		vcInsuranceCard.setActiveTime(activeTime);
		vcInsuranceCard.setActiveType(activeType);    // 字符转成整形
		vcInsuranceCard.setUpdatedBy(userCode);
		vcInsuranceCard.setDateUpdated(new Date());
		insuranceActivatedDao.update(vcInsuranceCard);
		
		
		//保存核销表
		docExecuteUsedDao.saveVcNormalVerificationList(vcNormalVerifications);
		//删除可使用表
		docExecuteUsedDao.deleteVcAvailableDocList(vcAvailableDocs);
		
		
		
		responseHead.setResponseCode("000");
		responseHead.setResponseMessage("激活卡激活成功！");
		responseBody.setStatus("1");
		responseBody.setFlag(null);
		response.setResponseHead(responseHead);
		response.setResponseBody(responseBody);
	}catch(DaoException e){
		   throw new  BusinessException(e.getMessage(),e);
	  }
	  
		return response;
	}
	
	/**
	 * 激活卡可用性检查
	 * @return
	 *@author whj
	 *@since Nov 7, 2013
	 */
	private Map<String,Object> cardCheck(VcInsuranceCard vcInsuranceCard,VcAvailableDoc vcAvailableDoc){
	    boolean isValid=true;
        String msg="";
        String cardStatus="";
        if(isValid && vcInsuranceCard == null){
            isValid=false; 
            msg="激活卡不可用！";
        }else{
           cardStatus=vcInsuranceCard.getCardStatus();  
        }
        
        if(isValid &&  vcAvailableDoc==null){
            isValid=false; 
            msg="激活卡在可使用表不存在！";
        }
        
        if(isValid && "9".equals(cardStatus)){
            isValid=false; 
            msg="激活卡已激活！";
        }
        //1-已付费待激活 22-已退卡再付费待激活
        if(isValid && !("1".equals(cardStatus)||"22".equals(cardStatus))){
            isValid=false; 
            msg="激活卡当前处于不可被激活状态！";
        }
        //是否过期
        if(isValid ){
            Date tempDate=vcInsuranceCard.getValidDate();
           if(tempDate==null){
               isValid=false; 
               msg="激活卡有效期不存在！";
           }else{
              Date date=new Date();
             ///有效期向后顺延3个月(河北分公司吉祥、如意卡不在其中)
               if (!(SysConst.COMCODE_CARD_VALID_NO_DELAY.indexOf(vcAvailableDoc.getOrgCode().substring(0, 4))>-1
                       && SysConst.CARDCODE_VALID_NO_DELAY.indexOf(vcInsuranceCard.getDocVerCode())>-1 )){
                   date=DateUtils.addMonth(date, -3); 
               }
               tempDate=DateUtils.addDay(tempDate, 1);//由于有效期不带时分秒，故天数加一
               if(DateUtils.compare(date, tempDate)>0){
                   isValid=false; 
                   msg="激活卡已过期！";
               }
           }
        }
       Map<String,Object> map=new HashMap<String,Object>();
       map.put("isValid", isValid);
       map.put("msg", msg);
       return map;
	}

	/****
	 *   将参数组装成一个实体
	 * @param request
	 * @return
	 */
	public  InsuranceCardInfoDTO changeToDto(InsuranceCardDoActivatedRequestDTO request){
		InsuranceCardInfoDTO  cardINfo=new InsuranceCardInfoDTO();
		cardINfo.setDocVerCode(request.getDocVerCode());
		cardINfo.setCardNO(request.getCardNO());
		cardINfo.setBusinessNo(request.getBusinessNo());
		return  cardINfo;
	}
	
	public void setInsuranceActivatedDao(InsuranceActivatedDao insuranceActivatedDao) {
		this.insuranceActivatedDao = insuranceActivatedDao;
	}
	
	public void setDocExecuteUsedDao(DocExecuteUsedDao docExecuteUsedDao) {
		this.docExecuteUsedDao = docExecuteUsedDao;
	}
}
