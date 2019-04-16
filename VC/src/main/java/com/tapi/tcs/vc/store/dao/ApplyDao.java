/**
 * 
 */
package com.tapi.tcs.vc.store.dao;

import java.util.List;
import java.util.Map;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcApply;
import com.tapi.tcs.vc.schema.model.VcApplyDet;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;

/**
 * @author hanmiao.diao
 *
 */
public interface ApplyDao extends GenericDao<VcApply> {
	/**
	 * 插入表VC_APPLY
	 * @param vcApply 待插入对象
	 * @throws DaoException 异常
	 */
	void insertVcApply(VcApply vcApply) throws DaoException;
	
	/**
	 * 插入表VC_APPLY_DET
	 * @param vcApplyDet 待插入对象
	 * @throws DaoException 异常
	 */
	void insertVcApplyDet(VcApplyDet vcApplyDet) throws DaoException;
	
	/**
	 * 查询申领记录
	 * @param params 查询条件
	 * @param pageNo 分页参数
	 * @param pageSize 分页参数
	 * @return 查询结果
	 * @throws DaoException 异常
	 */
	public Page queryApplyList(Map<String, Object> params, int pageNo, int pageSize) throws DaoException;

	/**
	 * 根据VC_APPLY_ID删除表VC_APPLY_DET中记录
	 * @param id 主键
	 * @throws DaoException 异常
	 */
	void deleteApplyDetByVcApplyId(Long id) throws DaoException;

	/**
	 * 根据VC_APPLY.id_vc_apply获取VC_APPLY_DET中关联明细
	 * @param vcApplyId VC_APPLY.id_vc_apply
	 * @return 明细集合
	 * @throws DaoException 异常
	 */
	List<VcApplyDet> getApplyDetListByVcApplyId(Long vcApplyId) throws DaoException;
	
	/**
	 * 根据申领单号获取信息
	 * @param applyCode 申领单号
	 * @return 申领信息
	 * @throws DaoException 异常
	 */
	VcApply getVcApply(String applyCode) throws DaoException;
	
	/**
	 * 获取单证申领明细
	 * @param vcApplyId VC_APPLY.id_vc_apply
	 * @param docVerCode 单证类型代码
	 * @return 申领明细对象
	 * @throws DaoException 异常
	 */
	VcApplyDet getVcApplyDet(Long vcApplyId, String docVerCode) throws DaoException;

	/**
	 * 查询有效库存明细
	 * @param params 查询参数
	 * @param page 分页参数
	 * @param rows 分页参数
	 * @return 有效库存明细
	 * @throws DaoException 异常
	 */
	Page queryValidStorageList(Map<String, Object> params, int page, int rows) throws DaoException;
	
	/**
	 * 查询有效库存明细
	 * @param params 查询参数
	 * @param page 分页参数
	 * @param rows 分页参数
	 * @return 有效库存明细
	 * @throws DaoException 异常
	 */
	Page queryValidStorageListNew(Map<String, Object> params, int page, int rows) throws DaoException;
	
	/**
	 * 查询可使用单证
	 * @param params
	 * @param page
	 * @param rows
	 * @return
	 * @throws DaoException
	 */
	Page queryAvailableList(Map<String, Object> params, int page, int rows) throws DaoException;

	/**
	 * 查询有效库存数量
	 * @param orgCode 机构代码
	 * @param docVerCode 单证类型代码
	 * @return 有效库存数量
	 * @throws DaoException 异常
	 */
	Long findValidStorageNum(String orgCode, String docVerCode)  throws DaoException;
	
	/**
	 *   辖内有效库存
	 * @param orgCode 机构代码            
	 * @param docVerCode 单证类型代码
	 * @return 有效库存数量
	 * @throws DaoException 异常
	 */
	Long  validStorageNum(String orgCode, String docVerCode)  throws DaoException;
	
	/**
	 * 查询本地有效库存数量
	 * @param orgCode 机构代码
	 * @param docVerCode 单证类型代码
	 * @return 有效库存数量
	 * @throws DaoException 异常
	 */
	Long localValidStorageNum(String orgCode, String docVerCode)  throws DaoException;
	
	/**
	 * 根据单证申领id，查询出该申领单证中的单证类型列表
	 * @param applyId
	 * @param docVerCode
	 * @return
	 * @throws DaoException
	 * @author chy
	 * @date 2013-07-24
	 */
	public List<VcDocVersionInfo> getVcDocVersionInfoList(Long applyId, String docVerCode) throws DaoException;
	
	/**
	 * 根据申领单ID和单证类型查找申请数量
	 * @param applyId
	 * @param docVerCode
	 * @return
	 * @throws DaoException
	 */
	public Integer getApplyNum(Long applyId, String docVerCode) throws DaoException;
	
	/**
	 * 根据单证类型单证号，单证状态查询已经作废的单证
	 * @param applyId
	 * @param docVerCode
	 * @return
	 * @throws DaoException
	 */
	public Page queryAbNormalList(Map<String, Object> params, int pageNo, int pageSize) throws DaoException;
	
}
