/**
 * 
 */
package com.tapi.tcs.vc.baseinfo.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.baseinfo.service.InsuTypeService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;

/**
 * @author Administrator
 *
 */
public class InsuTypeAction extends TFAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InsuTypeAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -8960165672142130408L;
	
	private VcDocInsuType insuType;
	
	@SuppressWarnings("rawtypes")
	private List insuTypeList;
	
	private String insuTypeCode;
	
	private String insuTypeName;
	
	private String result;
	
	private Long id; //
	
	private String deleteList;
	
	private InsuTypeService insuTypeService;
	
	public VcDocInsuType getInsuType() {
		return insuType;
	}

	public void setInsuType(VcDocInsuType insuType) {
		this.insuType = insuType;
	}

	@SuppressWarnings("rawtypes")
	public List getInsuTypeList() {
		return insuTypeList;
	}

	@SuppressWarnings("rawtypes")
	public void setInsuTypeList(List insuTypeList) {
		this.insuTypeList = insuTypeList;
	}
	
	public String getInsuTypeCode() {
		return insuTypeCode;
	}

	public void setInsuTypeCode(String insuTypeCode) {
		this.insuTypeCode = insuTypeCode;
	}

	public String getInsuTypeName() {
		return insuTypeName;
	}

	public void setInsuTypeName(String insuTypeName) {
		this.insuTypeName = insuTypeName;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(String deleteList) {
		this.deleteList = deleteList;
	}

	@JSON(serialize=false)
	public InsuTypeService getInsuTypeService() {
		return insuTypeService;
	}

	public void setInsuTypeService(InsuTypeService insuTypeService) {
		this.insuTypeService = insuTypeService;
	}

	

	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public String viewNew() throws BusinessException {
		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public String viewQuery() throws BusinessException {
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public String viewEdit() throws BusinessException {
		try{
			String strid = getRequest().getParameter("id");
			Long id = Long.valueOf(strid);
			
			VcDocInsuType insuType = insuTypeService.getInsuType(id);
			
			this.insuType = insuType;
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}	
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public String saveInsuType() throws BusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveDocInsuType() - start"); //$NON-NLS-1$
		}
		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
		VcDocInsuType insuTypeVo = this.insuType;
		
		String insuTypeCode = insuTypeVo.getInsuTypeCode();
		String insuTypeName = insuTypeVo.getInsuTypeName();
		Long id = insuTypeVo.getIdVcDocInsuType();
		
		
		
			try {
				if(null == id) {
				    if(StringUtils.isNotEmpty(insuTypeCode) 
			                && StringUtils.isNotEmpty(insuTypeName)) {
					Date sysdate = new Date();
					insuTypeVo.setDateCreated(sysdate);
					insuTypeVo.setCreatedBy(userInfo.getUserCode());
					insuTypeVo.setUpdatedBy(userInfo.getUserCode());
					insuTypeVo.setDateUpdated(sysdate);
					insuTypeVo.setStatus("1");
					insuTypeService.createInsuType(insuTypeVo);
					this.result = "保存成功";
				    }else {
			            this.result = "险类代码和名称不可为空";
			        }
				} else {
				    if(StringUtils.isNotEmpty(insuTypeName)) {
					VcDocInsuType dbInsuType = insuTypeService.getInsuType(id);
					if(dbInsuType!=null 
							&& dbInsuType.getInsuTypeName().equals(insuTypeVo.getInsuTypeName())) {
						throw new BusinessException("未做修改");
					}
					
					Date sysdate = new Date();
					
					insuTypeVo.setDateUpdated(sysdate);
					insuTypeVo.setUpdatedBy(userInfo.getUserCode());

					insuTypeService.updateInsuType(insuTypeVo);
					this.result = "修改成功";
				    }else {
                        this.result = "险类名称不可为空";
                    }
				}	
			} catch (Exception e) {
				e.printStackTrace();
				this.result = "操作失败,失败原因:" +  e.getMessage();
			}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveDocInsuType() - end"); //$NON-NLS-1$
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public String deleteInsuTypeList() throws BusinessException {
	    UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
		
		String deleteList = this.deleteList;
		
		String[] idArray = deleteList.split(",");
		
		String currentUser = userInfo.getUserCode();
		
		try {
			this.result = insuTypeService.deleteByLogic(idArray, currentUser);
		} catch (Exception e) {
			e.printStackTrace();
			this.result = e.getMessage();
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public String queryInsuTypeListByPages() throws BusinessException {
		try{
			if (logger.isDebugEnabled()) {
				logger.debug("queryDocInsuTypeListByPages() - start"); //$NON-NLS-1$
			}
			
			Page pageObj = insuTypeService.queryDocInsuTypeListByPages(insuTypeCode, insuTypeName, page, rows);
	
			this.insuTypeList = pageObj.getResult();
			total = (int) pageObj.getTotalPageCount();
			records = (int) pageObj.getTotalCount();
			
			if (logger.isDebugEnabled()) {
				logger.debug("queryDocInsuTypeListByPages() - end"); //$NON-NLS-1$
			}
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
}
