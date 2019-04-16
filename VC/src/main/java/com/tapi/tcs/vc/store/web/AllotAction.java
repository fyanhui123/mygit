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
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcAllot;
import com.tapi.tcs.vc.schema.model.VcAllotDet;
import com.tapi.tcs.vc.store.service.AllotService;
import com.tapi.tcs.vc.store.service.ApplyService;
import com.tapi.tcs.vc.store.vo.AllotApply;
import com.tapi.tcs.vc.store.vo.StoreStatusValues;
/**
 * 单证申领Action入口
 * 
 * @author hanmiao.diao
 * 
 */
@SuppressWarnings("serial")
public class AllotAction extends TFAction {
	private String deleteList;
	private String submitList;
	private VcAllot vcAllot;
	private AllotService allotService;
	private String jsonStr;
	private List resultList;
	private VcLevelService vcLevelService;
	private String jsonData;
	private VcDocVersionInfoService vcDocVersionInfoService;
	private ApplyService applyService;

	
	
	public String getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(String deleteList) {
		this.deleteList = deleteList;
	}
	public void setApplyService(ApplyService applyService) {
		this.applyService = applyService;
	}
	@SuppressWarnings("rawtypes")
	    private List approveList;
	 
	 @SuppressWarnings("rawtypes")
	public List getApproveList() {
		return approveList;
	}
	@SuppressWarnings("rawtypes")
	public void setApproveList(List approveList) {
		this.approveList = approveList;
	}
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
	public String allotApplyQuery() {
        logger.info("访问/allot/allotApplyQuery.do单证调拨申请...");
        Date sysdate = new Date();
        Date twoMonthAgo = DateUtils.addDay(sysdate, -60);
        getRequest().setAttribute("initStartDate", twoMonthAgo);
        getRequest().setAttribute("initEndDate", sysdate);
		return "success";
    }
	public String allotApproveQuery() {
        logger.info("访问/allot/allotApproveQuery.do单证调拨审批...");
        Date sysdate = new Date();
        Date twoMonthAgo = DateUtils.addDay(sysdate, -60);
        getRequest().setAttribute("initStartDate", twoMonthAgo);
        getRequest().setAttribute("initEndDate", sysdate);
		return "success";
    }
	public String allotInquiryQuery() {
        logger.info("访问/allot/allotInquiryQuery.do单证调拨入库...");
        Date sysdate = new Date();
        Date twoMonthAgo = DateUtils.addDay(sysdate, -60);
        getRequest().setAttribute("initStartDate", twoMonthAgo);
        getRequest().setAttribute("initEndDate", sysdate);
		return "success";
    }
	/**
	 * 选中删除方法
	 * @return
	 * @throws BusinessException
	 */
	  public String deleteAllotList() throws BusinessException {

	        String deleteList = this.deleteList;

	        String[] idArray = deleteList.split(",");

	        String currentUser = "DEMO";

	        try {
	            this.jsonStr = allotService.deleteByAllotId(idArray, currentUser);
	        } 
	    	catch(BusinessException e){
	    	    this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
	       	   return NONE;
	          }catch(Exception e){
	       	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
	        	   return NONE;
	          }
	        return SUCCESS;
	    }
	
	
	  public String viewAllotView() throws BusinessException{
		  String strid = getRequest().getParameter("id");
	        try{
	        if (StringUtils.isNotEmpty(strid)) {
	            Long id = Long.valueOf(strid);

	            // 加载代码对应的名称
	            this.vcAllot = allotService.getVcAllot(id);
	            this.vcAllot.setAllotOrgName(vcLevelService.getUnitNameByUnitCode(vcAllot.getAllotOrgCode()));
	            this.vcAllot.setAllotOprName(vcLevelService.getUnitNameByUnitCode(vcAllot.getAllotOprCode()));
	            this.vcAllot.setProvideOrgName(vcLevelService.getUnitNameByUnitCode(vcAllot.getProvideOrgCode()));
	            // 查询申领表                                                                                            
	            List<VcAllotDet> vcAllotDetList = allotService.getAllotDetListByVcAllotId(Long.valueOf(id));
	            // 有效库存显示
	            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
	            String orgCode = userInfo.getComCode();
	            for(VcAllotDet det:vcAllotDetList){
	            	String docVerCode=det.getDocVerCode();
	            	long  validNum=0l;
	            	try {
	            		  validNum=allotService.localValidStorageNum(orgCode, docVerCode); 
					} catch (Exception e) {
						e.printStackTrace();
						throw new BusinessException("有效库存查询失败！");
					}
	            	det.setValidNum(String.valueOf(validNum));
	            }
	            this.jsonStr = TFJSON.toJSONString(vcAllotDetList);
	            this.approveList = allotService.getVcApprove(id, "B");
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
		//选中新建状态的提交
	  public String submitAllotList() throws BusinessException {
	        String localSubmitList = this.submitList;
	        String[] idArray = localSubmitList.split(",");
	        StringBuffer sb = new StringBuffer();
	       try{
	        for (int i = 0; i < idArray.length; i++) {
	            Long id = Long.valueOf(idArray[i]);
	            VcAllot v = new VcAllot();
	            VcAllot v1 = allotService.getVcAllot(id);
	            List list = allotService.getCloneAllotDetListByVcAllotId(id);
	            Beans.copy(v, v1);
	            v.setAllotDetList(list);

	            v.setAllotStatus(StoreStatusValues.VC_ALLOT_STATUS_ALLOT_AWAITPROVIDE);
	            try {
	                allotService.saveAllot(v);
	                sb.append("申领单号[" + v.getAllotCode() + "]提交成功");
	            } catch (Exception e) {
	                e.printStackTrace();
	                sb.append("申领单号[" + v.getAllotCode() + "]提交失败,失败原因:" + e.getMessage());
	            }
	        }

	        this.jsonStr = sb.toString();
	       }catch(BusinessException e){
	   	      this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
		   	   return NONE;
		      }catch(Exception e){
		   	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
		    	   return NONE;
		      }
	        return SUCCESS;
	    }

	/**
	 * 单证调拨的
	 * @return
	 * @throws BusinessException
	 */
	public String allotQueryInfo() throws BusinessException{
		 logger.info("访问/allot/allotQueryInfo.do单证调拨申请查询方法...");
		 Map<String, Object> params = new HashMap<String, Object>();
		 try {
			       UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
		           String comCode = userInfo.getComCode();
			       AllotApply qc = TFJSON.parseObject(jsonStr, AllotApply.class); 
	 	           params.put("queryStatus", qc.getQueryStatus());//查询的状态
	 	           params.put("startDate", qc.getStartDate());//开始时间
	 	           params.put("endDate", qc.getEndDate());//结束时间
	 	           params.put("allotCode", qc.getAllotCode());//查询的申领单号
	 	           params.put("allotOrgCode", comCode);//申领机构
	 	           Page pageObj = allotService.queryAllotListByPages(params, page, rows);
	 	           List pageResult =  pageObj.getResult();
	 	          resultList = new ArrayList();
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
	 	        	 String allotStatus=pubCodeManagerService.getCodeCname("AllotStatus", vcAllot.getAllotStatus());
	 	        	 vcAllot.setAllotStatusName(allotStatus);
	 	        	 resultList.add(vcAllot);
	 	            }
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
	public String allotAddInfo() {
        logger.info("访问/allot/allotAddInfo.do单证调拨申请添加...");
        try {
        	 UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
 	        if (userInfo == null) {
 	            throw new BusinessException("登录超时，请重新登录！ ");
 	        }
 	        String comCode = userInfo.getComCode();//机构代码
 	        String userCode = userInfo.getUserCode();//用户id
 	        String userName = userInfo.getUserName();//用户名
 	        String comName = userInfo.getComName();//登录人机构名称
 	        VcAllot pageAllot = new VcAllot();
 	        Date sysdate = new Date();
 	        pageAllot.setAllotTime(sysdate);//调拨申请时间
 	        pageAllot.setAllotOprCode(userCode);//调拨操作人代码
 	        pageAllot.setAllotOprName(userName);//调拨操作人 名称
 	        pageAllot.setAllotOrgCode(comCode);//申请机构代码 
 	        pageAllot.setAllotOrgName(comName);//申请机构名称
 	        this.vcAllot = pageAllot;
		} catch (BusinessException e) {
			 this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			 return NONE;
		}catch(Exception e){
			 this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
	      	   return NONE;
		}
		return "success";
    }
	 public String saveAllot() throws BusinessException {
		 try {
			 UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
			 String userCode = userInfo.getUserCode();

			 VcAllot allot = (VcAllot) TFJSON.parseObject(jsonStr, VcAllot.class);
			  allot.setCreatedBy(userCode);
			  allot.setUpdatedBy(userCode);
			  allot.setAllotOrgCode(userInfo.getComCode());
			  allot.setAllotOprCode(userInfo.getUserCode());
	            // 校验录入的单证类型是否存在
	            List<VcAllotDet> list = allot.getAllotDetList();
	            for (Iterator it = list.iterator(); it.hasNext();) {
	            	VcAllotDet vcAllotyDet = (VcAllotDet) it.next();
	                String docVerCode = vcAllotyDet.getDocVerCode();
	                // 判断docVerCode是否存在
	                boolean isExist = vcDocVersionInfoService.isExistValid(docVerCode);
	                if (!isExist) {
	                    this.jsonStr = "单证类型[" + docVerCode + "]不存在 ";
	                    return SUCCESS;
	                }
	            }
	            logger.info(" allot is null ? " + (allot == null));
	            this.jsonStr = allotService.saveAllot(allot);
		} catch (BusinessException e) {
			  this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
          	   return NONE;
		}catch(Exception e){
			   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
           	   return NONE;
		}
		return SUCCESS;
	 }
	public String allotApproveInfo() {
        logger.info("访问/allot/allotAddInfo.do单证调拨申请审批...");
		return "success";
    }
	public String allotStorageInfo() {
        logger.info("访问/allot/allotStorageInfo.do单证调拨申请审批...");
		return "success";
    }
	
}
