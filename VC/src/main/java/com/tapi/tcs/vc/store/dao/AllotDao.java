/**
 * 
 */
package com.tapi.tcs.vc.store.dao;
import java.util.List;
import java.util.Map;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.rolelist.vo.RoleListVo;
import com.tapi.tcs.vc.schema.model.VcAllot;
import com.tapi.tcs.vc.schema.model.VcAllotDet;
import com.tapi.tcs.vc.schema.model.VcApply;
import com.tapi.tcs.vc.schema.model.VcApplyDet;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
/**
 * @author hanmiao.diao
 *
 */
public interface AllotDao extends GenericDao<VcAllot> {
	public Page queryAllotList(Map<String, Object> params, int pageNo, int pageSize) throws DaoException;
	void insertVcAllot(VcAllot vcAllot) throws DaoException;
	
	
	
	List<VcAllotDet> getAllotDetListByVcAllotId(Long vcAllotId) throws DaoException;
	
	void deleteAllotDetByVcAllotId(Long id) throws DaoException;
	void clear(VcAllotDet vcAllot) throws DaoException;
	
	public List<VcDocVersionInfo> getVcDocVersionInfoList(Long applyId, String docVerCode) throws DaoException;

	VcAllotDet getVcAllotDet(Long vcApplyId, String docVerCode) throws DaoException;
	public String getUnitNameByUnitCode(String unitCode) throws DaoException;
}
