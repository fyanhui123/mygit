/**
 * 
 */
package com.tapi.tcs.vc.store.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.json.annotations.JSON;
import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.baseinfo.service.VcDocVersionInfoService;
import com.tapi.tcs.vc.baseinfo.service.VcLevelService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.service.PubCodeManagerService;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcAllot;
import com.tapi.tcs.vc.schema.model.VcAllotDet;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.store.service.AllotService;
import com.tapi.tcs.vc.store.vo.AllotApply;

/**
 * 申领单发放Action入口
 * 
 * @author hanmiao.diao
 * 
 */
public class ProvideAllotAction extends TFAction {
	private String submitList;
	private VcAllot vcAllot;
	private AllotService allotService;
	private String jsonStr;
	private List resultList;
	private VcLevelService vcLevelService;
	private String jsonData;
	private VcDocVersionInfoService vcDocVersionInfoService;
	private List<Map<String, String>> mapList;
	 public List<Map<String, String>> getMapList() {
		return mapList;
	}
	public void setMapList(List<Map<String, String>> mapList) {
		this.mapList = mapList;
	}



	@SuppressWarnings("unused")
	private PubCodeManagerService pubCodeManagerService;
	 
	public String getSubmitList() {
		return submitList;
	}
	public void setSubmitList(String submitList) {
		this.submitList = submitList;
	}
	@JSON(serialize = false)
	public VcDocVersionInfoService getVcDocVersionInfoService() {
		return vcDocVersionInfoService;
	}
	public void setVcDocVersionInfoService(
			VcDocVersionInfoService vcDocVersionInfoService) {
		this.vcDocVersionInfoService = vcDocVersionInfoService;
	}
	public PubCodeManagerService getPubCodeManagerService() {
		return pubCodeManagerService;
	}
	public void setPubCodeManagerService(PubCodeManagerService pubCodeManagerService) {
		this.pubCodeManagerService = pubCodeManagerService;
	}
	public void setAllotService(AllotService allotService) {
		this.allotService = allotService;
	}
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public VcAllot getVcAllot() {
		return vcAllot;
	}
	public void setVcAllot(VcAllot vcAllot) {
		this.vcAllot = vcAllot;
	}
	
	public String getJsonStr() {
		return jsonStr;
	}
	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}
	 @JSON(serialize = false)
	public VcLevelService getVcLevelService() {
		return vcLevelService;
	}
	public void setVcLevelService(VcLevelService vcLevelService) {
		this.vcLevelService = vcLevelService;
	}
    public String saveProvideConfirm() throws BusinessException {
		
		try {
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			String userCode = userInfo.getUserCode();
			
			VcAllot provideConfrim = (VcAllot) TFJSON.parseObject(jsonStr, VcAllot.class);
			
			logger.info("解析provide字符串  " + (provideConfrim == null?"":provideConfrim.getAllotStatus()));
			
			provideConfrim.setConfirmOprCode(userCode);
			provideConfrim.setUpdatedBy(userCode);
			allotService.saveProvideConfirm(provideConfrim);
			this.jsonStr = "操作成功";
		} catch(BusinessException e){
	     	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
	    	   return NONE;
		    }catch(Exception e){
		    	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
		    	   return NONE;
		    }
		
		return SUCCESS;
	}
	
	
	
	
	public String provideConfirmEdit() throws BusinessException {
		String id = getRequest().getParameter("id");
		try{
		if(StringUtils.isNotEmpty(id)) {
			//查询申领表
			VcAllot vcAllot = allotService.getVcAllot(Long.valueOf(id));
			vcAllot.setAllotOrgName(vcLevelService.getUnitNameByUnitCode(vcAllot.getAllotOrgCode()));
			vcAllot.setAllotOprName(vcLevelService.getUnitNameByUnitCode(vcAllot.getAllotOprCode()));
			vcAllot.setProvideOrgName(vcLevelService.getUnitNameByUnitCode(vcAllot.getProvideOrgCode()));
			this.vcAllot = vcAllot;
			List<VcAllotDet> detList = allotService.getAllotDetListByVcAllotId(Long.valueOf(id));
			for (Iterator it = detList.iterator(); it.hasNext();) {
				VcAllotDet vcAllotDet = (VcAllotDet) it.next();
				vcAllotDet.setDeadlineStr(vcAllotDet.getDeadline()==null?"":DateUtils.format(vcAllotDet.getDeadline(), "yyyy-MM-dd"));
			}
			this.jsonStr = TFJSON.toJSONString(detList);
		}
		}catch(BusinessException e){
	     	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
	    	   return NONE;
		    }catch(Exception e){
		    	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
		    	   return NONE;
		    }
		return SUCCESS;
	}
	
	 public String saveProvide() throws BusinessException {
	        try {
	            // 获取UM信息
	            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
	            String comCode = userInfo.getComCode();
	            String userCode = userInfo.getUserCode();

	            VcAllot provide = (VcAllot) TFJSON.parseObject(jsonStr, VcAllot.class);
	            provide.setProvideOprCode(userCode);
	            provide.setProvideOrgCode(comCode);
	            provide.setUpdatedBy(userCode);

	            List<VcAllotDet> provideList = provide.getAllotDetList();
	            Date sysdate = DateUtils.parse(DateUtils.format(new Date(), "yyyy-MM-dd") + " 23:59:59",
	                    "yyyy-MM-dd HH:mm:ss");

	            Long id = provide.getId();
	            VcAllot vcAllot = allotService.getVcAllot(id);

	            for (Iterator it = provideList.iterator(); it.hasNext();) {
	                VcAllotDet vcAllotDet = (VcAllotDet) it.next();
	                // 校验截止日期是否符合条件
	                
	                Date deadline = sysdate; //
	                int maxStoreTime = allotService.getMaxStoreTime(vcAllotDet.getDocVerCode(), "0", vcAllot
	                        .getAllotOrgCode()); // 查询数据库得到最长库存时间
	                
	                Date calcDeadline = DateUtils.addDay(deadline, maxStoreTime);
	                Date pageDeadline = DateUtils.parse(vcAllotDet.getDeadlineStr() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");

	                if (DateUtils.compare(pageDeadline, calcDeadline) <= 0) {
	                    if (DateUtils.compare(pageDeadline, sysdate) >= 0) {
	                        vcAllotDet.setDeadline(pageDeadline);
	                    } else {
	                        this.jsonStr = "单证类型[" + vcAllotDet.getDocVerCode() + "]使用截止期不可以小于当前日期";
	                        return SUCCESS;
	                    }
	                }
	                else {
	                    this.jsonStr = "单证类型[" + vcAllotDet.getDocVerCode() + "]使用截止期超出最长库存时间 ";
	                    return SUCCESS;
	                }
	                
	            }

	            logger.info("解析provide字符串  " + (provide == null));
	            allotService.saveProvide(provide);
	            this.jsonStr = "操作成功";
	        }/* catch (Exception e) {
	            e.printStackTrace();
	            this.json = e.getMessage();
	        }*/
	        catch(BusinessException e){
	      	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
	     	   return NONE;
	 	    }catch(Exception e){
	 	    	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
	 	    	   return NONE;
	 	    }
	        return SUCCESS;
	    }
	 public String qeuryJsonDocVersion() throws BusinessException {
	    	try{
	    		String id = getRequest().getParameter("allotId");
	    		String codeValue = getRequest().getParameter("codeCode");
	    		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
	    		// 根据条件查询单证类型
	            List<VcDocVersionInfo> docVersionInfoList = allotService.getVcDocVersionInfoList(Long.valueOf(id), codeValue);
	            if (docVersionInfoList != null && docVersionInfoList.size() > 0) {
	                Map<String, String> map = null;
	                for (VcDocVersionInfo vcDocVersionInfo : docVersionInfoList) {
	                    map = new HashMap<String, String>();
	                    map.put("lable", vcDocVersionInfo.getDocVerName());
	                    map.put("value", vcDocVersionInfo.getDocVerCode());
	                    mapList.add(map);
	                }
	            }
	            this.mapList = mapList;
	    	}catch(BusinessException e){
	    		this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
	    		return NONE;
	 	    }catch(Exception e){
	 	    	this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
	 	    	return NONE;
	 	    }
	    	return SUCCESS;
	    }
	public String queryProvideAllotInfo()throws BusinessException{
		   String id = getRequest().getParameter("id");
		     try{
		        if (StringUtils.isNotEmpty(id)) {
		            // 查询申领表
		            VcAllot vcAllot = allotService.getVcAllot(Long.valueOf(id));
		            this.vcAllot = vcAllot;

		            // 加载代码对应的名称
		            this.vcAllot.setAllotOrgName(vcLevelService.getUnitNameByUnitCode(vcAllot.getAllotOrgCode()));
		            this.vcAllot.setAllotOprName(vcLevelService.getUnitNameByUnitCode(vcAllot.getAllotOprCode()));
		            this.vcAllot.setProvideOrgName(vcLevelService.getUnitNameByUnitCode(vcAllot.getProvideOrgCode()));
		            List<VcAllotDet> vcAllotDetList = allotService.getAllotDetListByVcAllotId(Long.valueOf(id));

		            Date sysdate = DateUtils.parse(DateUtils.format(new Date(), "yyyy-MM-dd") + " 23:59:59",
		                    "yyyy-MM-dd HH:mm:ss");

		            String  mngType=getRequest().getParameter("mngType");           //机构发放
		            String  maxStoreType=getRequest().getParameter("maxStoreType");  //最大单证保天数
		            
		            for (Iterator it = vcAllotDetList.iterator(); it.hasNext();) {
		            	VcAllotDet vcAllotDet = (VcAllotDet) it.next();

		               /*
		                 * 
		                 *   机构单证发放MNG_TYPE='0'     MNG_OBJECT_CODE =申请机构
		                 *   
		                 *   使用人单证发放MNG_TYPE='1'    MNG_OBJECT_CODE操作人代码
		                 **/
		                Date deadline = sysdate; //
		                int maxStoreTime = allotService.getMaxStoreTime(vcAllotDet.getDocVerCode(), mngType, vcAllot
		                        .getAllotOrgCode()); // 查询数据库得到最长库存时间
		               
		                Date calcDeadline = DateUtils.addDay(deadline, maxStoreTime);
		                vcAllotDet.setDeadlineStr(DateUtils.format(calcDeadline, "yyyy-MM-dd"));
		            }
		            this.jsonStr = TFJSON.toJSONString(vcAllotDetList);
		        }
		        return SUCCESS;
		     }catch(BusinessException e){
		   	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			   return NONE;
		    }catch(Exception e){
		    	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
		    	   return NONE;
		    }
	}
	
	
	/**
	 * 调拨审批
	 * @return
	 * @throws BusinessException
	 */
	 public String queryAllotListByPages() throws BusinessException {

		 logger.info("访问/allot/allotQueryInfo.do单证调拨审批查询方法...");
		 Map<String, Object> params = new HashMap<String, Object>();
		 try {
			       UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
		           String comCode = userInfo.getComCode();
			       AllotApply qc = TFJSON.parseObject(jsonStr, AllotApply.class); 
	 	           params.put("queryStatus", qc.getQueryStatus());//查询的状态
	 	           params.put("startDate", qc.getStartDate());//开始时间
	 	           params.put("endDate", qc.getEndDate());//结束时间
	 	           params.put("allotCode", qc.getAllotCode());//查询的申领单号
	 	           params.put("provideOrgCode", comCode);//申领机构
	 	           Page pageObj = allotService.queryAllotListByPages(params, page, rows);
	 	           List pageResult =  pageObj.getResult();
	 	           for (Iterator it = pageResult.iterator(); it.hasNext();) {
	 	        	 VcAllot vcAllot = (VcAllot) it.next();
	 	        	 String allotOprName= vcLevelService.getUnitNameByUnitCode(vcAllot.getAllotOprCode());
	 	        	 vcAllot.setAllotOprName(allotOprName);
	 	        	 String allotOrgCode =vcLevelService.getUnitNameByUnitCode(vcAllot.getAllotOrgCode());
	 	        	 vcAllot.setAllotOrgName(allotOrgCode);
	 	        	 String provideOrgName=vcLevelService.getUnitNameByUnitCode(vcAllot.getProvideOrgCode());
	 	        	 vcAllot.setProvideOrgName(provideOrgName);
	 	        	 String ProvideOprName=vcLevelService.getUnitNameByUnitCode(vcAllot.getProvideOprCode());
	 	        	 vcAllot.setProvideOprName(ProvideOprName);
	 	        	 String allotStatus=pubCodeManagerService.getCodeCname("ApplyStatus", vcAllot.getAllotStatus());
	 	        	 vcAllot.setAllotStatusName(allotStatus);
	 	            }
	 	          this.resultList = pageResult;
	 	          total = (int) pageObj.getTotalPageCount();
	 	          records = (int) pageObj.getTotalCount();
	 	       
		} catch (BusinessException e) {
			 this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			 return NONE;
		}catch(Exception e){
			 this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
	      	   return NONE;
		}
		  return SUCCESS;
	    }

}
