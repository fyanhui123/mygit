/**
 * 
 */
package com.tapi.tcs.vc.store.service;

import java.util.List;
import java.util.Map;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcAllot;
import com.tapi.tcs.vc.schema.model.VcAllotDet;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;

/**
 * @author hanmiao.diao
 *
 */
public interface AllotService {
	public Page queryAllotListByPages(Map<String, Object> params, int pageNo, int pageSize) throws BusinessException;
	String saveAllot(VcAllot vcAllot) throws BusinessException;
	
	VcAllot getVcAllot(Long id) throws BusinessException;
	List<VcAllotDet> getCloneAllotDetListByVcAllotId(Long vcAllotId) throws BusinessException;
	List<VcAllotDet> getAllotDetListByVcAllotId(Long vcApplyId) throws BusinessException;
	int getMaxStoreTime(String docVerCode, String mngType, String mngObjectCode) throws BusinessException;
	public List<VcDocVersionInfo> getVcDocVersionInfoList(Long applyId, String docVerCode) throws BusinessException;
	void saveProvide(VcAllot provide) throws BusinessException;
	void saveProvideConfirm(VcAllot vcProvideConfrim) throws BusinessException;
	
	Long  localValidStorageNum(String orgCode, String docVerCode)  throws BusinessException;
	
	List getVcApprove(Long id, String appType) throws BusinessException;
	public String getUnitNameByUnitCode(String unitCode) throws BusinessException;
	Long getValidStorageNum(String orgCode, String docVerCode)  throws BusinessException;
	String deleteByAllotId(String[] idArray, String currentUser) throws BusinessException;

}
