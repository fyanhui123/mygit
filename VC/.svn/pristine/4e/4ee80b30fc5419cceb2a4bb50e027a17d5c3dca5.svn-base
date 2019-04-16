package com.tapi.tcs.vc.inquiry.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.security.authority.User;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.baseinfo.dao.VcTakerDao;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.inquiry.dao.AvailableDocInquiryDao;
import com.tapi.tcs.vc.inquiry.service.AvailableDocInquiryService;
import com.tapi.tcs.vc.inquiry.vo.StorageInquiryVo;

public class AvailableDocInquiryServiceImpl implements AvailableDocInquiryService {
	private AvailableDocInquiryDao availableDocInquiryDao;

	public Page queryAvailableDocInquiryListByPages(
			StorageInquiryVo storageInquiryVo, UserInfo user, int pageNo,
			int pageSize) throws BusinessException {

		Page page = availableDocInquiryDao.queryAvailableInquiryList(storageInquiryVo, user, pageNo, pageSize);
		if (page != null) {
			List<StorageInquiryVo> StorageInquiryVos = (List<StorageInquiryVo>) page.getResult();
			List<StorageInquiryVo> resultList = new ArrayList<StorageInquiryVo>();
			if (StorageInquiryVos != null && StorageInquiryVos.size() > 0) {
				//机构名称、人员名称转译
				for (StorageInquiryVo tempVo : StorageInquiryVos) {
					tempVo.setOrgCode(user.getCompanyName());
					tempVo.setDocTakerCode(user.getUserName());
					resultList.add(tempVo);
				}
			}
			page.setData(resultList);
		}
		return page;
	}

	public AvailableDocInquiryDao getAvailableDocInquiryDao() {
		return availableDocInquiryDao;
	}

	public void setAvailableDocInquiryDao(
			AvailableDocInquiryDao availableDocInquiryDao) {
		this.availableDocInquiryDao = availableDocInquiryDao;
	}
}
