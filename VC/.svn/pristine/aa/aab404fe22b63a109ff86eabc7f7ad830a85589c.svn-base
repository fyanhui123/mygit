package com.tapi.tcs.vc.inquiry.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.tf.security.authority.SecurityContext;
import com.tapi.tcs.tf.security.authority.User;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.inquiry.service.AvailableDocInquiryService;
import com.tapi.tcs.vc.inquiry.vo.StorageInquiryVo;

public class AvailableDocInquiryAction extends TFAction {

	private static final long serialVersionUID = 1L;
	private AvailableDocInquiryService availableDocInquiryService;
	private StorageInquiryVo storageInquiryVo;

	List<StorageInquiryVo> availableDocInquiryVoList;

	private Date applyStartDate;
	private Date applyEndDate;
	/** 返回界面的字符串 */
	private String jsonData;

	/**
	 * 进入使用人单证库存统计查询页面
	 * 
	 * @return
	 */
	public String initAvailableDocInquiry() {
		logger.info("访问/storageInquiry/initAvailableDocInquiry.do查询使用人单证库存统计信息...");
		Date nowDate = new Date();
		applyStartDate = DateUtils.addMonth(nowDate, -2);
		applyEndDate = nowDate;
		return SUCCESS;
	}

	/**
	 * 分页查询使用人单证库存信息
	 * 
	 * @return 查询结果
	 * @throws BusinessException
	 *             异常
	 */
	public String queryAvailableDocInquiryListByPages() throws Exception {
		logger.info("访问/storageInquiryJson/queryAvailableDocInquiryListByPages.do?ajax=true分页查询单证库存统计信息...");
		try {
			HttpSession session = getRequest().getSession();
			UserInfo user = (UserInfo)session.getAttribute("userInfo");
			//User user = SecurityContext.getUser();
			if (StringUtils.isBlank(user.getUserCode())) {
				throw new BusinessException("登录超时或认证失败！");
			}

			Page pageObj = availableDocInquiryService.queryAvailableDocInquiryListByPages(storageInquiryVo,
							user, page, rows);
			if (pageObj != null) {
				availableDocInquiryVoList = (List<StorageInquiryVo>) pageObj.getResult();
				total = (int) pageObj.getTotalPageCount();
				records = (int) pageObj.getTotalCount();
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e
					.getMessage()));
			return NONE;
		} catch (Exception e) {
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils
					.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}

	public List<StorageInquiryVo> getAvailableDocInquiryVoList() {
		return availableDocInquiryVoList;
	}

	public void setAvailableDocInquiryVoList(
			List<StorageInquiryVo> availableDocInquiryVoList) {
		this.availableDocInquiryVoList = availableDocInquiryVoList;
	}

	public StorageInquiryVo getStorageInquiryVo() {
		return storageInquiryVo;
	}

	public void setStorageInquiryVo(StorageInquiryVo storageInquiryVo) {
		this.storageInquiryVo = storageInquiryVo;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public Date getApplyStartDate() {
		return applyStartDate;
	}

	public void setApplyStartDate(Date applyStartDate) {
		this.applyStartDate = applyStartDate;
	}

	public Date getApplyEndDate() {
		return applyEndDate;
	}

	public void setApplyEndDate(Date applyEndDate) {
		this.applyEndDate = applyEndDate;
	}

	public void setAvailableDocInquiryService(
			AvailableDocInquiryService availableDocInquiryService) {
		this.availableDocInquiryService = availableDocInquiryService;
	}

}
