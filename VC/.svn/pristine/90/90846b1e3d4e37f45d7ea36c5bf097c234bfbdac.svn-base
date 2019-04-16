package com.tapi.tcs.vc.baseinfo.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.vo.VcDocMngRuleShowVo;
import com.tapi.tcs.vc.inquiry.vo.DocDetailInquiryVo;
import com.tapi.tcs.vc.schema.model.VcConstantConfig;
import com.tapi.tcs.vc.schema.model.VcDocMngRule;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfoMng;

/**
 *单证管理控制规则DAO接口
 * <p>
 * Date: 2013-03-21
 * </p>
 * <p>
 * Module:单证管理控制规则
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

public interface VcDocMngRuleDao extends GenericDao<VcDocMngRule> {

    /**
     * 根据ID获取去承印单证版本对象
     * 
     * @param idVcDocMngRule
     *            id
     * @return VcDocMngRule
     */
    public VcDocMngRule getVcDocMngRule(Long idVcDocMngRule);

    /**
     * 查询承印单证版本信息
     * 
     * @author wanghuanjian
     * 
     * @param queryDto
     *            单证管理控制规则查询条件dto
     * @param currentPage
     *            当前页码
     * @param pageNumber
     *            页面条数
     * @return Page
     * @throws Exception
     *             异常
     */
    public Page queryVcDocMngRules(VcDocMngRule queryDto, int currentPage, int pageNumber) throws Exception;

    /**
     * 根据条件删除承印单证版本信息
     * 
     * @param conditionDto
     *            ''
     * @return int
     * @throws Exception
     *             异常
     * 
     * @author wanghuajian
     * @since 2013-4-15下午04:40:55
     */
    public int deleteByConditions(VcDocMngRule conditionDto) throws Exception;

    /**
     * 查询并设置默认信息
     * 
     * @param docQueryDto
     * @param vcDocMngRuleDto
     * @return
     * @author wanghuajian since 2013-4-23下午08:22:41
     */

    /**
     * 查询并设置默认信息
     * 
     * @param docQueryDto
     *            条件
     * @param vcDocMngRule
     *            条件
     * @return List
     * @throws Exception
     *             异常
     */
    public List<VcDocMngRuleShowVo> queryListAndSetDefaultConfig(DocDetailInquiryVo docQueryDto,
            VcDocMngRule vcDocMngRule) throws DaoException;

    /**
     * 根据条件逻辑删除
     * 
     * @param docQueryDto
     * @param vcDocMngRuleDto
     * @return
     * @author wanghuajian since 2013-4-24下午03:18:30
     */

    /**
     * 根据条件逻辑删除
     * 
     * @param docQueryDto
     *            dto
     * @param vcDocMngRuleDto
     *            dto
     * @return int
     * @throws Exception
     *             异常
     */
    public int deleteExistByLogic(DocDetailInquiryVo docQueryDto, VcDocMngRule vcDocMngRuleDto) throws DaoException;

    /**
     * 查询最长库存时间获取规则对象
     * 
     * @param docVerCode 单证类型    
     * @param mngType objType 0-机构  1-单证使用人
      * @param mngObjectCode 机构、人员代码
     * @return 规则对象
     * @throws Exception
     *             异常
     */
    VcDocMngRule getRuleByQueryMaxStoreTime(String docVerCode, String mngType, String mngObjectCode)
            throws DaoException;
    
    
    /**
     * 查询最长保留时间获取规则对象
     * @param 
	 * @param   maxStoreType  类型
	 * @param   mngObjectCode 申请机构
	 * @return 最大存在时间
	 * @throws exception 异常
     */
    VcConstantConfig getConfigByQueryMaxExistsTime(String maxStoreType,String mngObjectCode)
    throws DaoException;
    
    
    /**
     * 根据常量代码、级别查询常量配置表[查询常量表VC_CONSTANT_CONFIG]
     * @param constantCode
     * @param levelNo
     * @return
     * @throws DaoException
     *@author whj
     *@since Apr 9, 2014
     */    
    public VcConstantConfig getConstantConfig(String constantCode, int levelNo) throws DaoException ;
    
    
     /**
     * 根据单证类型、级别查询单证管控信息VC_DOC_VERSION_INFO_MNG
     * 
     * @param docVerCode
     * @param levelNo
     * @throws Exception
     * @author wanghuajian since 2014-4-8下午04:16:50
     */    
    public VcDocVersionInfoMng getDocVersionInfoMng(String docVerCode, int levelNo) throws DaoException ;
    
    
     /**
      * 根据单证类型代码、对象类别、对象代码获取最大单证保存天数
      *<p>
      * 按以下三步查找，直至查询到为止
      * 1、查询单证管理控制规则表 VC_DOC_MNG_RULE
      * 2、查询单证类型管控信息表VC_DOC_VERSION_INFO_MNG
      * 3、查询常量表VC_CONSTANT_CONFIG
      * <p>
      * @param docVerCode
      * @param mngType  0-机构  1-单证使用人
      * @param mngObjectCode 机构、人员代码
      * @return
      * @throws DaoException
      *@author whj
      *@since Apr 9, 2014
      */   
    public int getMaxStoreTime(String docVerCode,String mngType,String mngObjectCode) throws DaoException ;
}
