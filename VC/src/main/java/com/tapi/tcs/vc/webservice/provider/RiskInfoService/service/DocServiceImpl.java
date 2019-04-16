package com.tapi.tcs.vc.webservice.provider.RiskInfoService.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.baseinfo.dao.InsuKindDao;
import com.tapi.tcs.vc.baseinfo.dao.InsuTypeDao;
import com.tapi.tcs.vc.baseinfo.dao.VcDocRelInsuKindDao;
import com.tapi.tcs.vc.schema.model.VcDocInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;
import com.tapi.tcs.vc.schema.model.VcDocRelInsuKind;
import com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean.CommonProductFactoryResponseHead;

public class DocServiceImpl  implements DocService{
	public InsuTypeDao insuTypeDao;
	public InsuKindDao insuKindDao;
	public VcDocRelInsuKindDao vcDocRelInsuKindDao;
	@Override
	public CommonProductFactoryResponseHead savedocInsuKind(String operation, String classCode,
			String riskCode, String riskNameSimple, String documentType) {
		
		CommonProductFactoryResponseHead res=null;
		try {
			String[] strarr = documentType.split(",");
			Long vcDocInsuTypeID= getinsuTypeid(classCode);
			VcDocInsuKind insuKind=new  VcDocInsuKind();
			 Date sysdate = new Date();
			if(operation.equals("I")){
				// 1、新增险种       2、险种关联单证类型
				    insuKind.setStatus("1");
				    insuKind.setInsuKindCode(riskCode);
				    insuKind.setInsuKindName("新_"+riskNameSimple);
	                insuKind.setDateCreated(sysdate);
	                insuKind.setCreatedBy("0100000090");
	                insuKind.setUpdatedBy("0100000090");
	                insuKind.setDateUpdated(sysdate);
	                insuKind.setIdVcDocInsuType(vcDocInsuTypeID);
	                insuKind.setInsuType(null);
	                saveInsuKind(insuKind);
	                //  2、 险种关联单证类型
	                for(int i=0;i<strarr.length;i++){
	                	 VcDocRelInsuKind  vcDocRelInsuKind=new VcDocRelInsuKind(); 
						  vcDocRelInsuKind.setIdVcDocVersionInfo(Long.parseLong(strarr[i]));
						  vcDocRelInsuKind.setInsuKindCode(riskCode);
						  vcDocRelInsuKind.setCreatedBy("0100000090");
						  vcDocRelInsuKind.setDateCreated(sysdate);
						  vcDocRelInsuKind.setDateUpdated(sysdate);
						  vcDocRelInsuKind.setUpdatedBy("0100000090");
						  vcDocRelInsuKindDao.save(vcDocRelInsuKind);
	                }
	                //responseCode 0000：成功 其他：失败
	                //errorCode 
	                //errorMessage 错误说明
	                res= responseInfo("000","0000","成功");
	                return res;
			}else if(operation.equals("U")){
				//修改险种名称
				if(riskNameSimple!=null||!"".equals(riskNameSimple)){
					 List<VcDocInsuKind> list=insuKindDao.getInsuKindListByEquals("insuKindCode",riskCode);
					 for (int i=0;i<list.size();i++){
						 VcDocInsuKind vcDocInsuKind=list.get(i);
						 vcDocInsuKind.setInsuKindName("新_"+riskNameSimple);
						 vcDocInsuKind.setUpdatedBy("0100000090"); 
						 vcDocInsuKind.setDateCreated(sysdate);
						 insuKindDao.update(vcDocInsuKind);
					 }
				}
				if(strarr.length>0){
					//根据险种代码先删除关联的单证类型     然后再插入 
				      int num=  vcDocRelInsuKindDao.deleteByRiskCode(riskCode);
				      if(num>0){
				      for(int i=0;i<strarr.length;i++){
		                	 VcDocRelInsuKind  vcDocRelInsuKind=new VcDocRelInsuKind(); 
							  vcDocRelInsuKind.setIdVcDocVersionInfo(Long.parseLong(strarr[i]));
							  vcDocRelInsuKind.setInsuKindCode(riskCode);
							  vcDocRelInsuKind.setCreatedBy("0100000090");
							  vcDocRelInsuKind.setDateCreated(sysdate);
							  vcDocRelInsuKind.setDateUpdated(sysdate);
							  vcDocRelInsuKind.setUpdatedBy("0100000090");
							  vcDocRelInsuKindDao.save(vcDocRelInsuKind);
		                }
				} }
				 res= responseInfo("000","0000","成功");
	             return res;
			}else{
				//删除
				List<VcDocInsuKind> vcDocInsuKind = insuKindDao.getInsuKindListByEquals("insuKindCode",riskCode);
				insuKind=vcDocInsuKind.get(0);
				insuKind.setStatus("0");
				insuKind.setUpdatedBy("0100000090"); 
				insuKind.setDateUpdated(sysdate);
			    insuKindDao.update(insuKind); 
			    res= responseInfo("000","0000","成功");
			    return res;
			}
		} catch (Exception e) {
			 res= responseInfo("000","0003",e.getMessage());
			 return res;
		}
	}
	
	public CommonProductFactoryResponseHead responseInfo(String responseCode,String erroCode,String errorMessage){
		CommonProductFactoryResponseHead  head=new CommonProductFactoryResponseHead();
		head.setResponseCode(responseCode);
		head.setErroCode(erroCode);
		head.setErrorMessage(errorMessage);
		return head;
	}
	
	public void saveInsuKind(VcDocInsuKind insuKind) throws BusinessException{
		 if (insuKind.getIdVcDocInsuType() == null) {
	            throw new BusinessException("险类不可以为空!");
	        }
	        if (StringUtils.isEmpty(insuKind.getInsuKindCode()) || StringUtils.isEmpty(insuKind.getInsuKindName())) {
	            throw new BusinessException("险种代码和险种名称不可以为空");
	        }

	        // 判断险种代码是否存在
	        if (isInsuKindCodeExist(insuKind.getInsuKindCode())) {
	            throw new BusinessException("险种代码已存在!");
	        }
	        // 判断险种名称是否存在
	        if (isInsuKindNameExist(insuKind.getInsuKindName())) {
	            throw new BusinessException("险种名称已存在!");
	        }
	        try{
	        	insuKindDao.save(insuKind);
	        }catch(Exception e){
	        	e.printStackTrace();
				throw new BusinessException("保存数据失败！", e);
			}

	}
	
	public Long getinsuTypeid(String classCode) throws BusinessException{
		VcDocInsuType vcDocInsuType=null;
		try {
			List list = insuTypeDao.getInsuTypeListByEquals("insuTypeCode", classCode);
			for (int i=0;i<list.size();i++){
			 vcDocInsuType=(VcDocInsuType) list.get(0);
			}
		} catch (DaoException e) {
			throw new BusinessException("查询险类失败");
		}
		return vcDocInsuType.getIdVcDocInsuType();
	}
	
	 private boolean isInsuKindCodeExist(String insuKindCode) throws BusinessException {
	    	try{
		        List<VcDocInsuKind> insuKindList = insuKindDao.getInsuKindListByEquals("insuKindCode", insuKindCode);
		        if (insuKindList != null && insuKindList.size() > 0) {
		            return true;
		        }
	    	}catch(DaoException e){
				throw new BusinessException(e.getMessage(), e);
			}
	        return false;
	    }

	  private boolean isInsuKindNameExist(String insuKindName) throws BusinessException {
	    	try{
		        List<VcDocInsuKind> insuKindList = insuKindDao.getInsuKindListByEquals("insuKindName", insuKindName);
		        if (insuKindList != null && insuKindList.size() > 0) {
		            return true;
		        }
	    	}catch(DaoException e){
				throw new BusinessException(e.getMessage(), e);
			}
	        return false;
	    }
	  
	
	  
	public InsuTypeDao getInsuTypeDao() {
		return insuTypeDao;
	}
	public void setInsuTypeDao(InsuTypeDao insuTypeDao) {
		this.insuTypeDao = insuTypeDao;
	}
	public InsuKindDao getInsuKindDao() {
		return insuKindDao;
	}
	public void setInsuKindDao(InsuKindDao insuKindDao) {
		this.insuKindDao = insuKindDao;
	}
	public VcDocRelInsuKindDao getVcDocRelInsuKindDao() {
		return vcDocRelInsuKindDao;
	}
	public void setVcDocRelInsuKindDao(VcDocRelInsuKindDao vcDocRelInsuKindDao) {
		this.vcDocRelInsuKindDao = vcDocRelInsuKindDao;
	}
	
}
