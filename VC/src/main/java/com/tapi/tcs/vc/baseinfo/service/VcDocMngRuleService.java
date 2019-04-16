package com.tapi.tcs.vc.baseinfo.service;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.vo.VcDocMngRuleShowVo;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.inquiry.vo.DocDetailInquiryVo;
import com.tapi.tcs.vc.schema.model.VcPrintDocVersion;
import com.tapi.tcs.vc.schema.model.VcDocMngRule;

/**
 * 单证管理控制规则维护Service实现
 * <p>
 * Date: 2013-04-23
 * </p>
 * <p>
 * Module: 单证管理控制规则维护
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
public interface VcDocMngRuleService {

	/**
	 * 新增单证管理控制规则
	 */
	public void createVcDocMngRule(VcDocMngRule vcDocMngRule) throws BusinessException ;

	/**
	 * 根据单证管理控制规则主键获取单证管理控制规则
	 */
	public VcDocMngRule getVcDocMngRule(Long idVcDocMngRule) throws BusinessException ;
	
	/**
	 * 查询单证管理控制规则信息
	 */
	public Page queryVcDocMngRuleByPages(VcDocMngRule vcDocMngRule ,int currentPage, int pageNumber) throws Exception ;
	
	/**
	 * 注销、恢复单证管理控制规则
	 */
	public void deleteOrUnDeleteVcDocMngRule(String idVcDocMngRules) throws BusinessException ;
	
	
	/**
	 * 查询并设置默认信息	
	 * @param docQueryDto
	 * @param vcDocMngRuleDto
	 * @return
	 * @author wanghuajian
	 * since 2013-4-23下午08:22:41
	 */
	public List<VcDocMngRuleShowVo> queryListAndSetDefaultConfig(DocDetailInquiryVo docQueryDto,VcDocMngRule vcDocMngRuleDto)
	   throws BusinessException;

	
	
	/**
	 * 保存单证管理控制规则List信息	
	 * @param DocDetailInquiryVo 单证类型条件
	 * @param vcDocMngRuleDto 人员、部门信息
	 * @param vcDocMngRuleList 单证管理控制规则list
	 * @author wanghuajian
	 * since 2013-4-24下午02:44:05
	 */
	public int saveDocMngRuleList(DocDetailInquiryVo docQueryDto,VcDocMngRule vcDocMngRuleDto, List<VcDocMngRule> vcDocMngRuleList, UserInfo userInfo)throws BusinessException;	
	

	
	/**
     * 根据配置机构或人员code查询最大库存时间及库存量
     * @param  mngObjectCode 机构或人员code
     * @return String
     * @throws BusinessException
     * @author wanghuajian
     * @date 2013-06-21
     * 
     */
    public int[] getMngDefaultDate(String mngObjectCode) throws BusinessException ;
}
