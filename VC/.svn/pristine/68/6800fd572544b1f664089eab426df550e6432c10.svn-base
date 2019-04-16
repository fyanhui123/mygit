package com.tapi.tcs.vc.baseinfo.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.platform.schema.vo.CodeLabelDto;
import com.tapi.tcs.vc.baseinfo.vo.DocInsuKindVo;
import com.tapi.tcs.vc.baseinfo.vo.DocVerSimpleVo;
import com.tapi.tcs.vc.baseinfo.vo.DocVersionInfoQueryVo;
import com.tapi.tcs.vc.baseinfo.vo.ZTreeDto;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.VcDocInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfoMng;

/**
 * 单证版本信息Service实现
 * <p>
 * Date: 2013-03-12
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
 * @since2013-03-22
 * @version 1.0
 */
public interface VcDocVersionInfoService {
	 /**
     * 
     * 
     * @param 
     * @param 
     * 查询单证详细明细信息   
     *       
     */
	public Page queryDetail(String bussinessNo,UserInfo userInfo,String document,String startNum ,
            boolean flag,int pageNo,
			int pageSize) throws BusinessException;
    /**
     * 新增单证版本信息
     * 
     * @param vcDocVersionInfo
     *            单证信息
     * @param userInfo
     *            当前用户
     * @param file
     *            单证样式扫描件
     * @throws BusinessException
     *             异常
     */
	public void createVcDocVersionInfo(VcDocVersionInfo vcDocVersionInfo ,UserInfo userInfo ,File file)
			throws BusinessException;

	 /**
     * 更新单证版本信息
     * 
     * @param vcDocVersionInfo
     *            单证信息
     * @param userInfo
     *            当前用户
     * @param file
     *            单证样式扫描件
     * @throws BusinessException
     *             异常
     */
	public void updateVcDocVersionInfo(VcDocVersionInfo vcDocVersionInfo ,UserInfo userInfo , File file)
			throws BusinessException;

	/**
	 * 根据单证版本信息主键获取单证版本信息
	 */
	public VcDocVersionInfo getVcDocVersionInfo(Long idVcDocVersionInfo)
			throws BusinessException;

	/**
	 * 查询单证版本信息
	 */
	public Page queryVcDocVersionInfoByPages(DocVersionInfoQueryVo queryVo,
			int currentPage, int pageNumber) throws BusinessException;
	
	
	/**
     * 单证类型查询[单证明细查询用]
     * 
     * @author wanghuajian
     * @date 2014-03-17
     * 
     */
    public Page queryDocForDocDetInquiry(DocVersionInfoQueryVo queryVo,UserInfo userInfo,
            int currentPage, int pageNumber) throws BusinessException;
    

	/**
	 * 删除单证版本信息
	 */
	public void deleteVcDocVersionInfos(String idVcDocVersionInfos)
			throws BusinessException;

	/**
	 * 查询单证版本信息
	 */
	public List<VcDocVersionInfo> queryVcDocVersionInfos(
			String idVcDocVersionInfos) throws BusinessException;

	/**
	 * 关联险种信息
	 */
	public void saveRelInsuKind(String selectVcDocVersionInfoIds,
			String selectRelInsuKindCodes, UserInfo userInf) throws BusinessException;

	/**
	 * 关联地区信息
	 */
	public void saveRelArea(String selectVcDocVersionInfoIds,
			String relAreaCodes,UserInfo userInfo) throws BusinessException;

	/**
	 * 启用、停用
	 * 
	 */
	public String updateVcDocVersionInfoStatus(String idVcDocVersionInfos , UserInfo userInfo) throws BusinessException ;


	/**
	 * 单证类型代码自动完成
	 * 
	 * @param codeValue
	 * @return
	 */
	public List queryVcDocVersionInfoForAutoComplete(String codeValue) throws BusinessException ;

	/**
	 * 根据给定的条件查询 wanghuajian
	 */
	public List<VcDocVersionInfo> getDocVersionInfoList(Map<String, Object> map)
			throws BusinessException;
	
	/**
	 * 根据当前机构、单证类型代码查询业务类单证
	 * @param comCode
	 * @param docVerCode
	 * @return
	 * @throws BusinessException
	 * @author chy
	 */
	public List<VcDocVersionInfo> getBizDocVersionInfoList(String comCode, String docVerCode) throws BusinessException;

	/**
	 * 单证类型代码自动完成 发票
	 * 
	 * @param codeValue
	 * @return
	 */
	public List<CodeLabelDto> queryInvoiceForAutoComplete(String codeValue) throws BusinessException ;

	/**
	 * 根据单证类型代码查找单证类型名称
	 * 
	 * @param versionCode
	 * @return
	 * @throws BusinessException
	 * @author chy
	 * @date 2013-04-24
	 */
	public String getVersionName(String versionCode) throws BusinessException;

	/**
	 * 根据单证类型查找生成规则
	 * 
	 * @param versionCode
	 * @return
	 * @throws Exception
	 * @author chy
	 * @date 2013-04-24
	 */
	public List<Object[]> getDocNumRule(String versionCode) throws BusinessException;

	public List<VcDocVersionInfo> queryDocVersionInfo(String insuType,
			String insuKind, String docTypeCode, String docType)throws BusinessException;
	
	
	/**
	 * 根据单证类型流水查找关联险种
	 * 
	 * @param idVcDocVersionInfo
	 * @return
	 * @throws Exception
	 * @author wanghuajian
	 * @date 2013-04-27
	 */
	public List<DocInsuKindVo> queryRelInsuKindListByDocId(Long idVcDocVersionInfo) throws BusinessException;
	
	/**
	 * 根据单证类型流水查找关联地区
	 * 
	 * @param idVcDocVersionInfo
	 * @return
	 * @throws Exception
	 * @author wanghuajian
	 * @date 2013-04-27
	 */
	public List queryRelAreaListByDocId(Long idVcDocVersionInfo) throws BusinessException;
	
	/**
	 * 是否存在有效的单证类型
	 * @param docVerCode 单证类型代码
	 * @return 是否有效
	 * @throws BusinessException 异常
	 */
	boolean isExistValid(String docVerCode) throws BusinessException;
	
	/**
	 * 单证类型选择查询
	 * @param docVerSimpleVo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page queryDocVerForSelector(DocVerSimpleVo docVerSimpleVo, int pageNo, int pageSize) throws BusinessException;
	
	/**
	 * 根据单证类型流水查询已关联/未关联的险种
	 * @param idVcDocVersionInfo 单证类型流水
	 * @param  type 查询类型（0-已关联的险种  1-未关联的险种） 
	 * @return List<VcDocInsuKind>
	 */
	public List<VcDocInsuKind> queryRelInsuKindList(Long idVcDocVersionInfo,int type) throws BusinessException;
	
	
	
	/**
	 * 根据单证类型流水查询关联的地区树ZTree
	 * 
	 * @param idVcDocVersionInfo
	 *            单证类型流水
	 * @param minLevel
	 *            显示到树形的哪一级
	 * @return List<ZTreeDto>
	 */
	public List<ZTreeDto> queryRelAreaZTree(Long idVcDocVersionInfo, int minLevel) throws BusinessException ;
	
	
	/**
	 * 根据给定的条件查询   使用人单证发放   ljin
	 */
	public List<VcDocVersionInfo> getJSONUserSend(Map<String, Object> map)
			throws BusinessException;
	
	/**
	 * 单证类型代码自动完成
	 * 
	 * @param codeValue
	 * @return
	 */
	public List  autoGetVcDocVersionInfoForAutoComplete(String codeValue,String orgCode)throws BusinessException;
	
	
	
	 /**
     * 根据单证类型代码获取单证种类代码
     * @param docVerCode 单证类型代码
     * @return String 单证类型对应的单证种类代码
     * @author whj 20130922
     */
    public String getDocTypeCode(String docVerCode) throws BusinessException;
    
    /**
     * 单证类型查询【 进行征订的单证】
     * @param comCode
     * @param docVerCode
     * @return
     * @throws BusinessException
     *@author whj
     *@since Feb 25, 2014
     */
    public List<VcDocVersionInfo> getOrderVersionInfoList(String comCode, String docVerCode)
            throws BusinessException;
    
    
    /**
     * 根据条件查询单证管控信息VC_DOC_VERSION_INFO_MNG
     * 
     * @param queryDto
     * @throws Exception
     * @author wanghuajian since 2014-4-8下午04:16:50
     */
    public List<VcDocVersionInfoMng> queryDocMngList(VcDocVersionInfoMng queryDto) throws BusinessException;
    
	public String getUrladdress(String codeCode,String CodeType) throws BusinessException;
}
