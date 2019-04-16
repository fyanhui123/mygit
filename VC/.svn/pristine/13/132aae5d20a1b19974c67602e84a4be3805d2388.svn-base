package com.tapi.tcs.vc.webservice.provider.inStorage.component;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.scripting.config.LangNamespaceHandler;

import com.tapi.tcs.vc.webservice.provider.inStorage.bean.GroupDTO;
import com.tapi.tcs.vc.webservice.provider.inStorage.bean.InputStorageRequest;
import com.tapi.tcs.vc.webservice.provider.inStorage.bean.InputStorageRequestDTO;
public class ValidCheck{
	 /***
	  *    校验请求头、请求体内容
	  * **/
	public static  String[] checkNumStatus(InputStorageRequest request){
		 String[] message=new String[2];
		 if(request==null){
			 message[0]="10";
			 message[1]="服务请求不能为空!";
			 return  message;
		 }
		 if(request.getHeadDTO()==null){
			 message[0]="10";
			 message[1]="请求头不能为空!";
			 return  message;
		 }
		 if(StringUtils.isEmpty(request.getHeadDTO().getrEQSERIALNO())){
			 message[0]="10";
			 message[1]="交易流水号不能为空!";
			 return  message;
		 }
		 if(StringUtils.isEmpty(request.getHeadDTO().getrEQUESTTYPE())){
			 message[0]="10";
			 message[1]="请求类型不能为空!";
			 return  message;
		 }
		 if(StringUtils.isEmpty(request.getHeadDTO().getfLOWINTIME())){
			 message[0]="10";
			 message[1]="请求时间不能为空!";
			 return  message;
		 }
		 if(request.getInputStorageRequestDTO()==null){
			 message[0]="10";
			 message[1]="请求体不能为空!";
			 return  message;
		 }
		 InputStorageRequestDTO  body=request.getInputStorageRequestDTO(); 
		 StringBuilder sb=new StringBuilder("");
		 if(StringUtils.isEmpty(body.getfPLXDM())){
			 sb.append("发票类型代码不能为空！"); 
		 } 
		 List<GroupDTO> groupDTO=body.getrESULTLIST();
		 List<String[]> list = new ArrayList<String[]>();
		 if(groupDTO.size()>0){
		 for(int i=0;i<groupDTO.size();i++){
			 String fpdm= groupDTO.get(i).getfPDM();
			 if(fpdm.equals(null)||fpdm.equals("")){
			 sb.append("第"+(i+1)+"个发票代码不能为空！");
			 break;
			 }
			 String qshm= groupDTO.get(i).getqSHM();
			 if(qshm.equals(null)||qshm.equals("")){
				 sb.append("第"+(i+1)+"个起始号码不能为空！");
				 break; 
			 }
			 String zzhm= groupDTO.get(i).getzZHM();
			 if(zzhm.equals(null)||zzhm.equals("")){
				 sb.append("第"+(i+1)+"个终止号码不能为空！");
				 break;
			 }
			 String fpfs= groupDTO.get(i).getfPFS();
			 if(fpfs.equals(null)||fpfs.equals("")){
				 sb.append("第"+(i+1)+"个发票份数不能为空！");
				 break;
			 }
			 String lgryCODE= groupDTO.get(i).getlGRYCODE();
			 if(lgryCODE.equals(null)||lgryCODE.equals("")){
				 sb.append("第"+(i+1)+"个领购人员代码不能为空！");
				 break;
			 }
			 String instCODE= groupDTO.get(i).getiNSTCODE();
			 if(instCODE.equals(null)||instCODE.equals("")){
				 sb.append("第"+(i+1)+"个所属机构不能为空！");
				 break;
			 }
			 String[] arr1 ={groupDTO.get(i).getqSHM(),groupDTO.get(i).getzZHM()};
			 list.add(arr1);
		 }}else{
			 sb.append("领购信息不能为空");
		 }
		 boolean results= isExists(list);
		 if(results){
			 sb.append("存在重复的号段");
		 }
		 if(StringUtils.isNotEmpty(sb.toString())){
				message[0] = "10";
				message[1] = sb.toString();
				return message;
		 }
		 message[0]="0";
		 return message;
	}
	
	 public static boolean isExists(List<String []> list){   
		 for (int i = 0; i < list.size()-1; i++) { 
			 System.out.println(list.size());
			 for (int j = i + 1; j < list.size(); j++) {    
				 Long star_i=Long.parseLong(list.get(i)[0]) ;
				 Long end_i=Long.parseLong(list.get(i)[1]);
				 Long star_j=Long.parseLong(list.get(j)[0]);
				 Long end_j=Long.parseLong(list.get(j)[1]);
				 if((star_i>=star_j && star_i<=end_j)||(star_j>=star_i && star_j<=end_i))
					 return true;            
				    }}       
		           return false;   
		 } 
}
