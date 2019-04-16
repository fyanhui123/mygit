package com.tapi.tcs.vc.baseinfo.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.tf.common.utils.bean.TFBeanUtils;
import com.tapi.tcs.vc.baseinfo.baseconst.BaseInfoConst;
import com.tapi.tcs.vc.baseinfo.dao.VcConstantConfigDao;
import com.tapi.tcs.vc.baseinfo.dao.VcDocMngRuleDao;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.baseinfo.vo.VcDocMngRuleShowVo;
import com.tapi.tcs.vc.inquiry.vo.DocDetailInquiryVo;
import com.tapi.tcs.vc.schema.model.VcConstantConfig;
import com.tapi.tcs.vc.schema.model.VcDocMngRule;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfoMng;
import com.tapi.tcs.vc.schema.model.VcLevel;

/**
 * 单证管理控制规则DAO实现
 * <p>
 * Date: 2013-03-21
 * </p>
 * <p>
 * Module: 单证管理控制规则
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
public class VcDocMngRuleDaoImpl extends GenericDaoHibernate<VcDocMngRule> implements VcDocMngRuleDao {

    private VcConstantConfigDao vcConstantConfigDao;

    /**
     * 机构dao
     */
    private VcLevelDao vcLevelDao;

    public void setVcConstantConfigDao(VcConstantConfigDao vcConstantConfigDao) {
        this.vcConstantConfigDao = vcConstantConfigDao;
    }

    /**
     * @param vcLevelDao
     *            the vcLevelDao to set
     */
    public void setVcLevelDao(VcLevelDao vcLevelDao) {
        this.vcLevelDao = vcLevelDao;
    }

    /**
     * 根据单证管理控制规则ID查询单证管理控制规则信息
     */
    public VcDocMngRule getVcDocMngRule(Long idVcDocMngRule) {
        return (VcDocMngRule) super.get(VcDocMngRule.class, idVcDocMngRule);
    }

    /**
     * 查询并设置默认信息
     * 
     * @param docQueryDto
     * @param vcDocMngRuleDto
     * @return
     * @author wanghuajian
     * @since 2013-4-23下午08:22:41
     */
    public List<VcDocMngRuleShowVo> queryListAndSetDefaultConfig(DocDetailInquiryVo docQueryDto,
            VcDocMngRule vcDocMngRule) throws DaoException {
        if (vcDocMngRule == null || StringUtils.isBlank(vcDocMngRule.getMngObjectCode())) {
            throw new DaoException("参数MngObjectCode不能为空 ");
        }

        // ##########################证管理控制规则的相关查询条件*******************************
        StringBuffer sbRule = new StringBuffer(100);
        List valuesRule = new ArrayList();
        sbRule.append(" and rule.status = ? ");
        valuesRule.add("1");
        /*
         * if (StringUtils.isNotBlank(vcDocMngRule.getDocVerCode())) { sb_rule.append(" and rule.docVerCode = ? ");
         * values_rule.add(vcDocMngRule.getDocVerCode()); }
         */
        if (StringUtils.isNotBlank(vcDocMngRule.getMngType())) {
            sbRule.append(" and rule.mngType = ? ");
            valuesRule.add(vcDocMngRule.getMngType());
        }
        if (StringUtils.isNotBlank(vcDocMngRule.getMngObjectCode())) {
            sbRule.append(" and rule.mngObjectCode = ? ");
            valuesRule.add(vcDocMngRule.getMngObjectCode());
        }
        /*
         * if (StringUtils.isNotBlank(vcDocMngRule.getOrgCode())) { sb_rule.append(" and rule.orgCode = ? ");
         * values_rule.add(vcDocMngRule.getOrgCode()); }
         */
        /*
         * if (vcDocMngRule.getIdVcDocMngRule()!=null) { sb_rule.append(" and rule.idVcDocMngRule = ? ");
         * values_rule.add(vcDocMngRule.getIdVcDocMngRule()); }
         */

        // ###############单证的相关查询条件*************************************
        StringBuffer sbDocCondition = new StringBuffer(100);
        List valuesDoc = new ArrayList();
        // 有效的单证
        sbDocCondition.append(" and doc.status = ? ");
        valuesDoc.add("1");

        if (StringUtils.isNotBlank(docQueryDto.getDocVerCode())) {
            sbDocCondition.append(" and doc.docVerCode = ? ");
            valuesDoc.add(docQueryDto.getDocVerCode());
        }
        // 单证种类
        if (StringUtils.isNotBlank(docQueryDto.getDocTypeCode())) {
            sbDocCondition
                    .append(" and exists(select 1 from VcDocType type where type.idVcDocType=doc.idVcDocType and type.docTypeCode=? ) ");
            valuesDoc.add(docQueryDto.getDocTypeCode());
        }
        // 险类//险种
        if (StringUtils.isNotBlank(docQueryDto.getInsuType())
                || StringUtils.isNotBlank(docQueryDto.getInsuKind())) {
            sbDocCondition
                    .append(" and exists(select 1 from VcDocInsuKind insuKind, VcDocInsuType insuType,VcDocRelInsuKind ref ");
            sbDocCondition
                    .append(" where insuKind.insuKindCode=ref.insuKindCode and ref.idVcDocVersionInfo=doc.idVcDocVersionInfo and insuKind.idVcDocInsuType=insuType.idVcDocInsuType ");
            if (StringUtils.isNotBlank(docQueryDto.getInsuType())) {
                sbDocCondition.append(" and insuType.insuTypeCode=?");
                valuesDoc.add(docQueryDto.getInsuType());
            }
            if (StringUtils.isNotBlank(docQueryDto.getInsuKind())) {
                sbDocCondition.append(" and insuKind.insuKindCode=?");
                valuesDoc.add(docQueryDto.getInsuKind());
            }
            sbDocCondition.append(" ) ");
        }

        // ++++++++++++++++地区 add by whj 2013-06-20/start++++++++++++++++++++++//
        // 上级机构（只查寻到中支级别）
        List<VcLevel> orgList = vcLevelDao.queryUpOrgListByUnitCode(vcDocMngRule.getMngObjectCode(), 3);
        StringBuffer orgCodes = new StringBuffer();
        // 总公司code
        orgCodes.append("'").append(BaseInfoConst.ROOT_ORG_CODE).append("'");
        for (VcLevel obj : orgList) {
            orgCodes.append(", '").append(obj.getUnitCode()).append("'");
        }
        sbDocCondition
                .append(" and exists(select 1 from VcDocRelArea refa where refa.idVcDocVersionInfo=doc.idVcDocVersionInfo ");
        sbDocCondition.append(" and refa.orgCode in (").append(orgCodes).append(") ) ");
        // ++++++++++++++++地区 add by whj 2013-06-20/end++++++++++++++++++++++//

        // 已存在的控制规则查询
        StringBuffer sb_exist = new StringBuffer(100);
        List values_exist = new ArrayList();
        sb_exist.append("from VcDocMngRule rule, VcDocVersionInfo doc where rule.docVerCode=doc.docVerCode ");
        sb_exist.append(sbRule).append(sbDocCondition);
        values_exist.addAll(valuesRule);
        values_exist.addAll(valuesDoc);
        List existList = null;
        try {
            existList = this.findByHql(sb_exist.toString(), values_exist.toArray());
        } catch (Exception e) {
            throw new DaoException("查找存在控制规则出错!", e);
        }

        // 未存在控制规则单证的查询
        StringBuffer sb_notExist = new StringBuffer(100);
        List values_notExist = new ArrayList();
        sb_notExist.append("from VcDocVersionInfo doc where 1=1 ");
        sb_notExist.append(sbDocCondition);
        values_notExist.addAll(valuesDoc);

        sb_notExist.append(" and not exists(");
        sb_notExist.append(" select 1 from VcDocMngRule rule where rule.docVerCode=doc.docVerCode ");
        sb_notExist.append(sbRule);
        values_notExist.addAll(valuesRule);
        sb_notExist.append(" ) ");
        List notExistList = null;
        try {
            notExistList = this.findByHql(sb_notExist.toString(), values_notExist.toArray());
        } catch (Exception e) {
            throw new DaoException("查找未存在控制规则出错!", e);
        }

        VcDocMngRuleShowVo tempVo = null;
        List<VcDocMngRuleShowVo> list = new ArrayList<VcDocMngRuleShowVo>();
        // 查找所配置的机构、用户的级别
        int levelNo = 4;
        try {
            List objectLevelList = vcLevelDao.findByUnitCode(vcDocMngRule.getMngObjectCode());
            if (objectLevelList != null && objectLevelList.size() > 0) {
                VcLevel objLevel = (VcLevel) objectLevelList.get(0);
                levelNo = objLevel.getLevelNo();
            }
        } catch (Exception e) {
            throw new DaoException("查找所配置的机构/用户的级别出错!", e);
        }
        int maxStore = 1;
        int maxStoreTime = 1;
        // 查询最大库存量
        VcConstantConfig maxStoreVo = vcConstantConfigDao.getVcConstantConfig("maxStore", levelNo);
        if (maxStoreVo != null) {
            maxStore = Integer.valueOf(maxStoreVo.getConstantValue());
        }
        // 查询最大库存时间
        VcConstantConfig maxStoreTimeVo = vcConstantConfigDao.getVcConstantConfig("maxStoreTime", levelNo);
        if (maxStoreTimeVo != null) {
            maxStoreTime = Integer.valueOf(maxStoreTimeVo.getConstantValue());
        }

        try {
            // 已存在的权限配置
            if (existList != null && existList.size() > 0) {
                VcDocMngRule ruleVo = null;
                VcDocVersionInfo docVo = null;
                for (int i = 0; i < existList.size(); i++) {
                    tempVo = new VcDocMngRuleShowVo();
                    Object[] obj = (Object[]) existList.get(i);
                    tempVo.setDefaultMaxStoreTime(maxStoreTime);
                    tempVo.setDefaultMaxStore(maxStore);
                    tempVo.setMaxStoreTime(maxStoreTime);
                    ruleVo = (VcDocMngRule) obj[0];
                    docVo = (VcDocVersionInfo) obj[1];
                    TFBeanUtils.copyProperties(ruleVo, tempVo);
                    tempVo.setDocVerName(docVo.getDocVerName());
                    tempVo.setIsChecked("1");
                    list.add(tempVo);
                }
            }
            // 不存在
            if (notExistList != null && notExistList.size() > 0) {
                VcDocVersionInfo docVo = null;
                for (int i = 0; i < notExistList.size(); i++) {
                    tempVo = new VcDocMngRuleShowVo();
                    tempVo.setMaxStore(maxStore);
                    tempVo.setDefaultMaxStore(maxStore);
                    tempVo.setMaxStoreTime(maxStoreTime);
                    tempVo.setDefaultMaxStoreTime(maxStoreTime);
                    docVo = (VcDocVersionInfo) notExistList.get(i);
                    // tempVo.setTakerCode(vcDocMngRule.getTakerCode());
                    tempVo.setMngObjectCode(vcDocMngRule.getMngObjectCode());
                    tempVo.setOrgCode(vcDocMngRule.getOrgCode());
                    tempVo.setMngType(vcDocMngRule.getMngType());
                    tempVo.setDocVerCode(docVo.getDocVerCode());
                    tempVo.setDocVerName(docVo.getDocVerName());
                    tempVo.setIsChecked("0");
                    list.add(tempVo);
                }
            }
        } catch (Exception e) {
            throw new DaoException("管控规则数据转换出错！", e);
        }

        return list;
    }

    /**
     * 根据查询条件查询满足条件的单证管理控制规则信息
     */
    public Page queryVcDocMngRules(VcDocMngRule vcDocMngRule, int currentPage, int pageNumber)
            throws Exception {
        QueryRule queryRule = null;
        try {
            queryRule = QueryRule.getInstance();
        } catch (Exception e) {
            throw new DaoException("QueryRule拼接异常!", e);
        }

        if (vcDocMngRule != null) {
            if (StringUtils.isNotBlank(vcDocMngRule.getDocVerCode())) {
                queryRule.addLike("docVerCode", vcDocMngRule.getDocVerCode());
            }
            if (StringUtils.isNotBlank(vcDocMngRule.getMngType())) {
                queryRule.addLike("mngType", vcDocMngRule.getMngType());
            }
            /*
             * if (StringUtils.isNotBlank(vcDocMngRule.getTakerCode())) { queryRule.addLike("takerCode",
             * vcDocMngRule.getTakerCode()); }
             */
            if (StringUtils.isNotBlank(vcDocMngRule.getMngObjectCode())) {
                queryRule.addLike("mngObjectCode", vcDocMngRule.getMngObjectCode());
            }
            if (StringUtils.isNotBlank(vcDocMngRule.getOrgCode())) {
                queryRule.addLike("orgCode", vcDocMngRule.getOrgCode());
            }
            if (StringUtils.isNotBlank(vcDocMngRule.getStatus())) {
                queryRule.addLike("status", vcDocMngRule.getStatus());
            }

            if (vcDocMngRule.getIdVcDocMngRule() != null) {
                queryRule.addEqual("idVcDocMngRule", vcDocMngRule.getIdVcDocMngRule());
            }

        }
        try {
            return super.find(VcDocMngRule.class, queryRule, currentPage, pageNumber);
        } catch (Exception e) {
            throw new DaoException("查询数据库出错！", e);
        }
    }

    /**
     * 根据条件删除单证管理控制规则信息
     * 
     * @param conditionDto
     * @return
     * @throws Exception
     * @author wanghuajian since 2013-4-15下午04:40:55
     */
    public int deleteByConditions(VcDocMngRule conditionDto) throws Exception {
        try {
            if (conditionDto != null && conditionDto.getIdVcDocMngRule() != null) {
                StringBuffer sb = new StringBuffer("delete  from VcDocMngRule v where 1=1 ");
                List<Object> values = new ArrayList<Object>();
                // 拼接订单号查询条件
                if (conditionDto.getIdVcDocMngRule() != null) {
                    sb.append(" and v.idVcDocMngRule = ?");
                    values.add(conditionDto.getIdVcDocMngRule());
                }
                return this.executeUpdate(sb.toString(), values);
            }
        } catch (Exception e) {
            throw new DaoException("数据删除失败！", e);
        }
        return 0;

    }

    /**
     * 根据条件物理删除（在新增权限配置前删除以前存在的配置）
     * 
     * @param docQueryDto
     * @param vcDocMngRuleDto
     * @return
     * @author wanghuajian since 2013-4-24下午03:18:30
     */
    public int deleteExistByLogic(DocDetailInquiryVo docQueryDto, VcDocMngRule vcDocMngRule)
            throws DaoException {
        StringBuffer sb_rule = new StringBuffer(100);
        List values_rule = new ArrayList();
        sb_rule.append(" and rule.status = ? ");
        values_rule.add("1");
        /*
         * if (StringUtils.isNotBlank(vcDocMngRule.getDocVerCode())) { sb_rule.append(" and rule.docVerCode = ? ");
         * values_rule.add(vcDocMngRule.getDocVerCode()); }
         */
        if (StringUtils.isNotBlank(vcDocMngRule.getMngType())) {
            sb_rule.append(" and rule.mngType = ? ");
            values_rule.add(vcDocMngRule.getMngType());
        }
        /*
         * if (StringUtils.isNotBlank(vcDocMngRule.getTakerCode())) { sb_rule.append(" and rule.takerCode = ? ");
         * values_rule.add(vcDocMngRule.getTakerCode()); }
         */
        if (StringUtils.isNotBlank(vcDocMngRule.getMngObjectCode())) {
            sb_rule.append(" and rule.mngObjectCode = ? ");
            values_rule.add(vcDocMngRule.getMngObjectCode());
        }
        /*
         * if (StringUtils.isNotBlank(vcDocMngRule.getOrgCode())) { sb_rule.append(" and rule.orgCode = ? ");
         * values_rule.add(vcDocMngRule.getOrgCode()); }
         */
        /*
         * if (vcDocMngRule.getIdVcDocMngRule()!=null) { sb_rule.append(" and rule.idVcDocMngRule = ? ");
         * values_rule.add(vcDocMngRule.getIdVcDocMngRule()); }
         */

        // ###############单证的相关查询条件*************************************
        StringBuffer sb_doc = new StringBuffer(100);
        List values_doc = new ArrayList();
        // 有效的单证
        sb_doc.append(" and doc.status = ? ");
        values_doc.add("1");

        if (StringUtils.isNotBlank(docQueryDto.getDocVerCode())) {
            sb_doc.append(" and doc.docVerCode = ? ");
            values_doc.add(docQueryDto.getDocVerCode());
        }
        // 单证种类
        if (StringUtils.isNotBlank(docQueryDto.getDocTypeCode())) {
            sb_doc
                    .append(" and exists(select 1 from VcDocType type where type.idVcDocType=doc.idVcDocType and type.docTypeCode=? ) ");
            values_doc.add(docQueryDto.getDocTypeCode());
        }
        // 险类//险种
        if (StringUtils.isNotBlank(docQueryDto.getInsuType())
                || StringUtils.isNotBlank(docQueryDto.getInsuKind())) {
            sb_doc
                    .append(" and exists(select 1 from VcDocInsuKind insuKind, VcDocInsuType insuType,VcDocRelInsuKind ref ");
            sb_doc
                    .append(" where insuKind.insuKindCode=ref.insuKindCode and ref.idVcDocVersionInfo=doc.idVcDocVersionInfo and insuKind.idVcDocInsuType=insuType.idVcDocInsuType ");
            if (StringUtils.isNotBlank(docQueryDto.getInsuType())) {
                sb_doc.append(" and insuType.insuTypeCode=?");
                values_doc.add(docQueryDto.getInsuType());
            }
            if (StringUtils.isNotBlank(docQueryDto.getInsuKind())) {
                sb_doc.append(" and insuKind.insuKindCode=?");
                values_doc.add(docQueryDto.getInsuKind());
            }
            sb_doc.append(" ) ");
        }

        StringBuffer sb_delete = new StringBuffer(100);
        List values_delete = new ArrayList();
        /*
         * sb_delete.append("update VcDocMngRule rule  "); sb_delete.append("set rule.status=? ,");
         * values_delete.add("0"); sb_delete.append(" rule.updatedBy=? ,"); values_delete.add("Tianan");
         * sb_delete.append(" rule.dateUpdated=? "); values_delete.add(new Date());
         */

        sb_delete.append("delete from  VcDocMngRule rule  ");

        sb_delete.append(" where 1=1 ");
        sb_delete.append(sb_rule);
        values_delete.addAll(values_rule);

        sb_delete.append("and exists(");
        sb_delete.append(" select 1 from VcDocVersionInfo doc where rule.docVerCode=doc.docVerCode ");
        sb_delete.append(sb_doc);
        values_delete.addAll(values_doc);
        sb_delete.append(" ) ");
        try {
            return this.executeUpdate(sb_delete.toString(), values_delete.toArray());
        } catch (Exception e) {
            throw new DaoException("数据删除失败！", e);
        }

    }

    /*
     *  获取最大单证保存天数[查询单证管理控制规则表 VC_DOC_MNG_RULE]
     *  @param docVerCode
      * @param mngType objType 0-机构  1-单证使用人
      * @param mngObjectCode 机构、人员代码
     */
    @SuppressWarnings("unchecked")
    @Override
    public VcDocMngRule getRuleByQueryMaxStoreTime(String docVerCode, String mngType, String mngObjectCode)
            throws DaoException {
        QueryRule queryRule = null;
        try {
            queryRule = QueryRule.getInstance();
        } catch (Exception e) {
            throw new DaoException("QueryRule拼接异常!", e);
        }

        if (StringUtils.isNotEmpty(docVerCode)) {
            queryRule.addEqual("docVerCode", docVerCode);
        }
        if (StringUtils.isNotEmpty(mngType)) {
            queryRule.addEqual("mngType", mngType);
        }
        if (StringUtils.isNotEmpty(mngObjectCode)) {
            queryRule.addEqual("mngObjectCode", mngObjectCode);
        }

        queryRule.addEqual("status", "1");

        List<VcDocMngRule> list = null;
        try {
            list = this.find(VcDocMngRule.class, queryRule);
        } catch (Exception e) {
            throw new DaoException("查询数据库出错！", e);
        }

        // 按规则说是docVerCode+mngType+mngObjectCode能确定唯一
        if (list != null && list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }
    /**
     * 获取最大单证保存天数[查询常量表VC_CONSTANT_CONFIG]
     * @param maxStoreType
     * @param mngObjectCode
     * @return
     * @throws DaoException
     *@author whj
     *@since Apr 9, 2014
     */
	@Override
	public VcConstantConfig   getConfigByQueryMaxExistsTime(String maxStoreType,
			String mngObjectCode) throws DaoException {
		try{
		VcConstantConfig vcConstantConfig=null;
		StringBuffer hql = new StringBuffer(200);
		List<Object> values = new ArrayList<Object>();
		hql.append(" from   VcConstantConfig  c , VcLevel v where c.levelNo=v.levelNo ");
		hql.append(" and  v.unitCode = ? ");
		values.add(mngObjectCode);
		hql.append(" and  c.constantCode= ? ");
		values.add(maxStoreType);
		List result =this.findByHql(hql.toString(),values.toArray());
		if(result.size()>0){
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				Object[] object = (Object[]) iterator.next();
				vcConstantConfig=(VcConstantConfig) object[0];
			}
			return  vcConstantConfig;
		}}
		catch(Exception e){
		   throw  new  DaoException("查询数据库出错！",e);
		}
		return null;
	}
	
	
	/**
     * 根据常量代码、级别查询常量配置表[查询常量表VC_CONSTANT_CONFIG]
     * @param constantCode
     * @param levelNo
     * @return
     * @throws DaoException
     *@author whj
     *@since Apr 9, 2014
     */
	@Override
    public VcConstantConfig getConstantConfig(String constantCode, int levelNo) throws DaoException {
        try {           
            StringBuffer hql = new StringBuffer();
            List<Object> values = new ArrayList<Object>();
            hql.append(" from   VcConstantConfig  c  ");
            hql.append(" where  c.levelNo = ? ");
            values.add(levelNo);
            hql.append(" and  c.constantCode= ? ");
            values.add(constantCode);
            List result = this.findByHql(hql.toString(), values.toArray());
            if (result != null && result.size() > 0) {
                return (VcConstantConfig) result.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new DaoException("查询数据库出错！", e);
        }

    }
	
	
	 /**
     * 根据单证类型、级别查询单证管控信息VC_DOC_VERSION_INFO_MNG
     * 
     * @param docVerCode
     * @param levelNo
     * @throws Exception
     * @author wanghuajian since 2014-4-8下午04:16:50
     */
	@Override
    public VcDocVersionInfoMng getDocVersionInfoMng(String docVerCode, int levelNo) throws DaoException {
        try {
            StringBuffer sb = new StringBuffer("from VcDocVersionInfoMng v  where  1=1 ");
            List<Object> values = new ArrayList<Object>();
            // 拼接查询条件
            if (StringUtils.isNotBlank(docVerCode)) {
                sb.append(" and  v.docVerCode= ? ");
                values.add(docVerCode);
            }
            if (levelNo > 0) {
                sb.append(" and v.levelNo= ? ");
                values.add(levelNo);
            }
            List list = this.findByHql(sb.toString(), values.toArray());
            if (list != null && list.size() > 0) {
                return (VcDocVersionInfoMng)list.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new DaoException("数据查询异常：" + e.getMessage());
        }
    }
	
	
	 /**
	  * 根据单证类型代码、对象类别、对象代码获取最大单证保存天数
	  * <p>
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
	@Override
    public int getMaxStoreTime(String docVerCode,String mngType,String mngObjectCode) throws DaoException {
        //查询单证管理控制规则表 VC_DOC_MNG_RULE
        VcDocMngRule vcDocMngRule=this.getRuleByQueryMaxStoreTime(docVerCode, mngType, mngObjectCode);
        int maxStoreTime=-1;        //防止设置的数值为0的情况
        if (vcDocMngRule != null) {
            maxStoreTime=vcDocMngRule.getMaxStoreTime();
        }
        if(maxStoreTime < 0){
            int levelNo=7;
            if("1".equals(mngType)){//单证使用人级别为6
                levelNo=6; 
            }else{ //机构级别
                VcLevel objLevel = vcLevelDao.getVcLevel("0",mngObjectCode);
                if (objLevel != null) {
                    levelNo = objLevel.getLevelNo();
                }else{
                    throw new DaoException("机构["+mngObjectCode+"]不存在！"); 
                }
            }
            
           // 查询单证管控信息VC_DOC_VERSION_INFO_MNG
            VcDocVersionInfoMng  vcDocVersionInfoMng=this.getDocVersionInfoMng(docVerCode, levelNo); // 查询常量保留时间
            if(vcDocVersionInfoMng!=null){
               maxStoreTime=vcDocVersionInfoMng.getMaxStoreTime();  
            }
            
            if(maxStoreTime < 0){
                VcConstantConfig  vcConstantConfig=this.getConstantConfig("maxStoreTime", levelNo); // 查询常量保留时间
                if(vcConstantConfig!=null){
                    maxStoreTime=Integer.valueOf(vcConstantConfig.getConstantValue());  
                }else{
                    maxStoreTime=0;
                }
            }
        }
        return maxStoreTime;
    }

}
