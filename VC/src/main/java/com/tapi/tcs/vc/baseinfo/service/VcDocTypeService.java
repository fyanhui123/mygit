package com.tapi.tcs.vc.baseinfo.service;

import java.util.List;
import java.util.Map;

import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.VcDocType;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;

/**
 * 单证种类维护Service实现
 * <p>
 * Date: 2013-03-12
 * </p>
 * <p>
 * Module: 单证种类维护
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
public interface VcDocTypeService {

	/**
	 * 新增单证种类
	 */
	public void createVcDocType(VcDocType vcDocType,UserInfo userInfo) throws BusinessException ;

	/**
	 * 更新单证种类
	 */
	public void updateVcDocType(VcDocType vcDocType,UserInfo userInfo) throws BusinessException ;

	/**
	 * 根据单证种类主键获取单证种类
	 */
	public VcDocType getVcDocType(Long idVcDocType) throws BusinessException ;
	
	/**
	 * 查询单证种类信息
	 */
	public Page queryVcDocTypeByPages(VcDocType vcDocType,UserInfo userInfo, int currentPage, int pageNumber) throws BusinessException ;
	
	/**
	 * 删除单证种类
	 */
	public String deleteOrUndeleteDocType(String ids) throws BusinessException ;
	
	
	/**
	 * 根据给定的条件查询
	 */
    public List<VcDocType> getDocTypeList(Map<String,Object> map) throws BusinessException ;
		
    public List<VcDocType> queryDocType(QueryRule queryRule)throws BusinessException;
	

}
