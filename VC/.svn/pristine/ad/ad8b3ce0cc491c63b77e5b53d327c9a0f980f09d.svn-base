package com.tapi.tcs.vc.baseinfo.dao;

import java.util.List;
import java.util.Map;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.vo.DetileNormalVO;
import com.tapi.tcs.vc.baseinfo.vo.DocVerSimpleVo;
import com.tapi.tcs.vc.baseinfo.vo.DocVersionInfoQueryVo;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.VcDocPrtNoRule;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfoMng;

/**
 * 单证版本信息DAO接口
 * <p>
 * Date: 2013-03-13
 * </p>
 * <p>
 * Module: 单证版本信息
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * @author wanghuajian
 * @version 1.0
 */

public interface VcDocVersionInfoDao extends GenericDao<VcDocVersionInfo> {

	public Object queryDetail(String bussinessNo,UserInfo userInfo,String document,
			String startNum , boolean flag,int pageNo,
			int pageSize) throws BusinessException;
	
	
	
	/**
	 * 根据ID获取去单证版本信息对象
	 * 
	 * @author wanghuajian
	 * 
	 * @param idVcDocVersionInfo
	 * @return
	 */
	public VcDocVersionInfo getVcDocVersionInfo(Long idVcDocVersionInfo);

	/**
	 * 查询单证版本信息
	 * 
	 * @author wanghuanjian
	 * 
	 * @param queryVo
	 *            单证版本信息查询条件dto
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            页面条数
	 * @return
	 */
	public Page queryVcDocVersionInfos(DocVersionInfoQueryVo queryVo, int pageNo, int pageSize)throws DaoException;
	
	/**
     * 单证类型查询[单证明细查询用]
     * 
     * @author wanghuanjian
     * 
     * @param queryVo
     *            单证版本信息查询条件dto
     * @param userInfo
     *            当前用户信息 
     * @param pageNo
     *            当前页码
     * @param pageSize
     *            页面条数
     * @return
     */
    public Page queryDocForDocDetInquiry(DocVersionInfoQueryVo queryVo, UserInfo userInfo ,int pageNo, int pageSize)throws DaoException;
	
	/**
	 * 根据给定的条件查询
	 * wanghuajian
	 */
	public List<VcDocVersionInfo> getDocVersionInfoList(Map<String,Object> map)throws DaoException;
	
	/**
	 * 根据当前机构、单证类型代码查询业务类单证
	 * @param comCode
	 * @param docVerCode
	 * @return
	 * @throws DaoException
	 * @author chy
	 */
	public List<VcDocVersionInfo> getBizDocVersionInfoList(String comCode, String docVerCode) throws DaoException;
	
	
	/**
	 * 根据条件删除单证印刷流水号规则
	 * wanghuajian
	 */
	public int deletePrtNoRuleByCondition (VcDocPrtNoRule vcDocPrtNoRule) throws DaoException;
	
	/**
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List findByHql(String hql,Object... params);
	
	/**
	 * 根据单证类型代码查找单证类型名称
	 * @param versionCode
	 * @return
	 * @throws DaoException
	 * @author chy
	 * @date 2013-04-24
	 */
	public String getVersionName(String versionCode) throws DaoException;
	
	/**
	 * 根据单证类型查找生成规则
	 * @param versionCode
	 * @return
	 * @throws DaoException
	 * @author chy
	 * @date 2013-04-24
	 */
	public List<Object[]> getDocNumRule(String versionCode) throws DaoException ;
	
	public List findBySQL(String sql,Object[] params) throws DaoException;
	
	/**
	 * 根据单证类型代码获取单证种类代码
	 * @param docVerCode
	 * @return
	 */
	public String getDocTypeCode(String docVerCode) throws DaoException;
	
	/**
	 * 获取有效的单证类型(status=1)
	 * @param docVerCode 单证类型代码
	 * @return 集合
	 * @throws DaoException 异常
	 */
	public List<VcDocVersionInfo> getValidVcDocVersionInfo(String docVerCode) throws DaoException;
	
	/**
	 * 单证类型选择器查询
	 * @param docVerSimpleVo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page queryDocVerForSelector(DocVerSimpleVo docVerSimpleVo, int pageNo, int pageSize)throws DaoException;

	/**
	 * 根据给定的条件查询
	 *     使用人单证发放   ljin
	 */
	public List<VcDocVersionInfo> getJSONUserSend(Map<String,Object> map) throws DaoException;
	
	/**
     * 根据当前机构、单证类型代码查询【进行征订的业务类单证】
     * @param comCode
     * @param docVerCode
     * @return
     * @throws DaoException
     * @author whj
     * @since 2014-02-25
     */
    public List<VcDocVersionInfo> getOrderDocVersionInfoList(String comCode, String docVerCode) throws DaoException;
    
    
    /**
     * 根据单证类型代码【级别（非必须）】删除单证管控信息VC_DOC_VERSION_INFO_MNG
     * 
     * @param queryDto
     * @throws Exception
     * @author wanghuajian since 2014-4-8下午04:16:50
     */
    public int deleteDocMng(VcDocVersionInfoMng queryDto) throws DaoException;

    /**
     * 根据条件删除单证管控信息VC_DOC_VERSION_INFO_MNG
     * 
     * @param queryDto
     * @throws Exception
     * @author wanghuajian since 2014-4-8下午04:16:50
     */
    public List<VcDocVersionInfoMng> queryDocMngList(VcDocVersionInfoMng queryDto) throws DaoException;

    /**
     * 保存单证管控信息VC_DOC_VERSION_INFO_MNG
     * 
     * @param queryDto
     * @throws Exception
     * @author wanghuajian since 2014-4-8下午04:16:50
     */
    public int saveDocMngList(List<VcDocVersionInfoMng> docMngList) throws DaoException;
	
}
