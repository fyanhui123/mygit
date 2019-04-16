package com.tapi.tcs.vc.webservice.provider.ClassInfoService.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.util.Assert;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.baseinfo.dao.InsuTypeDao;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;
import com.tapi.tcs.vc.webservice.provider.ClassInfoService.bean.ClassInfoProductFactoryRequest;
import com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean.CommonProductFactoryResponseHead;
import com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean.ComonProductFactoryResponse;

public class VcClassInfoServiceImpl implements VcClassInfoService{
	public InsuTypeDao insuTypeDao;
	
	public InsuTypeDao getInsuTypeDao() {
		return insuTypeDao;
	}
	public void setInsuTypeDao(InsuTypeDao insuTypeDao) {
		this.insuTypeDao = insuTypeDao;
	}
	@Override
	public ComonProductFactoryResponse saveClassinfo(
			ClassInfoProductFactoryRequest reqPackage) {
		    ComonProductFactoryResponse resPackage=new ComonProductFactoryResponse();//返回对象
		    CommonProductFactoryResponseHead resHead=new CommonProductFactoryResponseHead();//返回head信息
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		    String time= format.format( new Date());
		    Date date= null;
		try {
			date= format.parse(time);
		    String operation=reqPackage.getCommonProductFactoryRequestHead().getOperation();
		    //归属险类代码
		    String classCode=reqPackage.getClassInfoProductFactoryRequestBody().getPrpdClass().getClassCode();
		    //险种代码
		    String className=reqPackage.getClassInfoProductFactoryRequestBody().getPrpdClass().getClassName();
		    
		    String validind=reqPackage.getClassInfoProductFactoryRequestBody().getPrpdClass().getValidind();
		    VcDocInsuType	docInsuType=new VcDocInsuType();
		    if(operation.equals("I")){
		    	docInsuType.setInsuTypeCode(classCode);
		    	docInsuType.setInsuTypeName("新_"+className);
		    	docInsuType.setStatus("1");
		    	docInsuType.setCreatedBy("0100000090");
		    	docInsuType.setDateCreated(date);
		    	docInsuType.setUpdatedBy("0100000090");
		    	docInsuType.setDateUpdated(date);
		    	saveRiskClass(docInsuType);
		    	
		    	resHead.setRequestType(reqPackage.getCommonProductFactoryRequestHead().getRequestType());
				resHead.setResponseCode("000");
				resHead.setSerialNo(reqPackage.getCommonProductFactoryRequestHead().getSerialNo());
				resHead.setResponseTime(date);
				resHead.setErroCode("0000");
				resHead.setErrorMessage("成功");
				resPackage.setCommonProductFactoryResponseHead(resHead);
		    }else if(operation.equals("U")){
		    	docInsuType.setInsuTypeCode(classCode);
		    	docInsuType.setInsuTypeName("新_"+className);
		    	docInsuType.setDateUpdated(date);
		    	docInsuType.setUpdatedBy("0000000");
		    	updateInsuType(docInsuType);
		    	
		    	resHead.setRequestType(reqPackage.getCommonProductFactoryRequestHead().getRequestType());
				resHead.setResponseCode("000");
				resHead.setSerialNo(reqPackage.getCommonProductFactoryRequestHead().getSerialNo());
				resHead.setResponseTime(date);
				resHead.setErroCode("0000");
				resHead.setErrorMessage("成功");
				resPackage.setCommonProductFactoryResponseHead(resHead);
		    }else if(operation.equals("D")){
		    	docInsuType.setInsuTypeCode(classCode);
		    	docInsuType.setStatus("0");
		    	docInsuType.setDateUpdated(date);
		    	docInsuType.setUpdatedBy("0000000");
		    	updateInsuType(docInsuType);
		    	
		    	resHead.setRequestType(reqPackage.getCommonProductFactoryRequestHead().getRequestType());
				resHead.setResponseCode("000");
				resHead.setSerialNo(reqPackage.getCommonProductFactoryRequestHead().getSerialNo());
				resHead.setResponseTime(date);
				resHead.setErroCode("0000");
				resHead.setErrorMessage("成功");
				resPackage.setCommonProductFactoryResponseHead(resHead);
		    }else {
		    	resHead.setRequestType(reqPackage.getCommonProductFactoryRequestHead().getRequestType());
				resHead.setResponseCode("999");
				resHead.setSerialNo(reqPackage.getCommonProductFactoryRequestHead().getSerialNo());
				resHead.setResponseTime(date);
				resHead.setErroCode("0003");
				resHead.setErrorMessage("请求失败，修改类型识别不出");
				resPackage.setCommonProductFactoryResponseHead(resHead);
		    }
		} catch (Exception e) {
			resHead.setRequestType(reqPackage.getCommonProductFactoryRequestHead().getRequestType());
			resHead.setResponseCode("999");
			resHead.setSerialNo(reqPackage.getCommonProductFactoryRequestHead().getSerialNo());
			resHead.setResponseTime(date);
			resHead.setErroCode("0003");
			resHead.setErrorMessage(e.getMessage());
			resPackage.setCommonProductFactoryResponseHead(resHead);
		}
		return resPackage;
	}
	public void saveRiskClass(VcDocInsuType docInsuType) throws BusinessException{
	try{
        Assert.notNull(docInsuType);
        if (isInsuTypeCodeExist(docInsuType.getInsuTypeCode())) {
            throw new BusinessException("险类代码已存在,请重新设置 ");
        }

        if (isInsuTypeNameExist(docInsuType.getInsuTypeName())) {
            throw new BusinessException("险类名称已存在,请重新设置 ");
        }
        insuTypeDao.save(docInsuType);
	}catch(Exception e){
		throw new BusinessException("保存数据失败！", e);
	}
	}
	 private boolean isInsuTypeNameExist(String insuTypeName) throws BusinessException {
	    	try{
		        List list = insuTypeDao.getInsuTypeListByEquals("insuTypeName", insuTypeName);
		
		        if (list != null && list.size() > 0) {
		            return true;
		        } else {
		            return false;
		        }
	    	}catch(DaoException e){
				throw new BusinessException(e.getMessage(), e);
			}
	    }
	

 @SuppressWarnings("rawtypes")
 private boolean isInsuTypeCodeExist(String insuTypeCode) throws BusinessException {
    try{
			List list = insuTypeDao.getInsuTypeListByEquals("insuTypeCode", insuTypeCode);
			
			if (list != null && list.size() > 0) {
			    return true;
			} else {
			    return false;
			}
    }catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
 }
 
 public void updateInsuType(VcDocInsuType docInsuType) throws BusinessException {
     Assert.notNull(docInsuType);
     if (isInsuTypeNameExist(docInsuType.getInsuTypeName())) {
         throw new BusinessException("险类名称已存在,请重新设置 ");
     }
   try{
 	  List<VcDocInsuType> InsuType = insuTypeDao.getInsuTypeListByEquals("insuTypeCode", docInsuType.getInsuTypeCode());  
     VcDocInsuType vo = InsuType.get(0);
     vo.setDateUpdated(docInsuType.getDateUpdated());
     vo.setUpdatedBy(docInsuType.getUpdatedBy());
     vo.setInsuTypeName(docInsuType.getInsuTypeName());
     insuTypeDao.update(vo);
	}catch(Exception e){
		throw new BusinessException("更新数据失败！", e);
	}
}
}
