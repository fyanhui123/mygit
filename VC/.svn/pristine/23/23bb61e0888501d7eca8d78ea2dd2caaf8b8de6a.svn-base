package com.tapi.tcs.vc.inquiry.dao;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.security.authority.User;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.inquiry.vo.StorageInquiryVo;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;

public interface AvailableDocInquiryDao extends GenericDao<VcAvailableDoc>{
	
	public Page queryAvailableInquiryList(StorageInquiryVo storageInquiryVo, UserInfo userInfo, int pageNo,
            int pageSize) throws DaoException;
}
