package com.tapi.tcs.vc.baseinfo.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;

import com.tapi.tcs.tf.common.helpers.Page;

import com.tapi.tcs.vc.schema.model.VcConstantConfig;
import com.tapi.tcs.vc.schema.model.VcDocMngRule;
import com.tapi.tcs.vc.schema.model.VcLevel;

import com.tapi.tcs.vc.baseinfo.dao.VcConstantConfigDao;
import com.tapi.tcs.vc.baseinfo.dao.VcDocMngRuleDao;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.baseinfo.service.VcDocMngRuleService;
import com.tapi.tcs.vc.baseinfo.vo.VcDocMngRuleShowVo;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.inquiry.vo.DocDetailInquiryVo;

/**
 * 单证管理控制规则维护Service实现
 * <p>
 * Date: 2013-03-16
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

/**
 * @author whj
 * 
 */
public class VcDocMngRuleServiceImpl implements VcDocMngRuleService {

    private VcDocMngRuleDao vcDocMngRuleDao;

    private VcLevelDao vcLevelDao;

    private VcConstantConfigDao vcConstantConfigDao;

    public void setVcDocMngRuleDao(VcDocMngRuleDao vcDocMngRuleDao) {
        this.vcDocMngRuleDao = vcDocMngRuleDao;
    }

    public void setVcLevelDao(VcLevelDao vcLevelDao) {
        this.vcLevelDao = vcLevelDao;
    }

    public void setVcConstantConfigDao(VcConstantConfigDao vcConstantConfigDao) {
        this.vcConstantConfigDao = vcConstantConfigDao;
    }

    /**
     * 新增单证管理控制规则
     */
    public void createVcDocMngRule(VcDocMngRule vcDocMngRule) throws BusinessException {
        String msg = "";
        if (vcDocMngRule == null) {
            msg = "单证管理控制规则信息为空";
        } else {
            if (StringUtils.isBlank(vcDocMngRule.getDocVerCode())
                    || StringUtils.isBlank(vcDocMngRule.getMngObjectCode())) {
                msg = "单证管理控制规则单证代码/使用人、部门为空";
            } else {
                VcDocMngRule queryDto_code = new VcDocMngRule();
                /*
                 * Page resustPage_code = null; queryDto_code.setVcDocMngRuleCode (vcDocMngRule.getVcDocMngRuleCode());
                 * resustPage_code = vcDocMngRuleDao.queryVcDocMngRules(queryDto_code, 1, 5); if
                 * (resustPage_code.getResult().size() > 0) { msg = "单证管理控制规则代码重复！"; }
                 */
            }
        }
        if (StringUtils.isNotBlank(msg)) {
            BusinessException be = new BusinessException(msg);
            be.setModule("单证管理控制规则_新增单证管理控制规则");
            throw be;
        } else {
            vcDocMngRule.setDateCreated(new Date());
            vcDocMngRule.setCreatedBy("Tianan");
            vcDocMngRule.setDateUpdated(new Date());
            vcDocMngRule.setUpdatedBy("Tianan");
            try {
                vcDocMngRuleDao.save(vcDocMngRule);
            } catch (Exception e) {
                throw new BusinessException("数据保存失败！");
            }

        }
    }

    /**
     * 根据单证管理控制规则主键获取单证管理控制规则
     */
    public VcDocMngRule getVcDocMngRule(Long idVcDocMngRule) throws BusinessException {
        if (idVcDocMngRule == null) {
            BusinessException be = new BusinessException("单证管理控制规则主键为空");
            be.setModule("单证管理控制规则_根据主键获取单证管理控制规则");
            throw be;
        }
        return vcDocMngRuleDao.getVcDocMngRule(idVcDocMngRule);
    }

    /**
     * 查询单证管理控制规则信息
     */
    public Page queryVcDocMngRuleByPages(VcDocMngRule vcDocMngRule, int currentPage, int pageNumber)
            throws Exception {
        return vcDocMngRuleDao.queryVcDocMngRules(vcDocMngRule, currentPage, pageNumber);
    }

    /**
     * 注销、恢复单证管理控制规则
     */
    public void deleteOrUnDeleteVcDocMngRule(String idVcDocMngRules) throws BusinessException {

        String[] arrIds = idVcDocMngRules.split(",");
        Long idVcDocMngRule = null;
        VcDocMngRule isExit = null;
        for (String strId : arrIds) {
            idVcDocMngRule = new Long(strId);

            // 调用单证管理控制规则对应的dao进行逻辑删除操作
            isExit = vcDocMngRuleDao.getVcDocMngRule(idVcDocMngRule);
            if (isExit != null) {
                if ("0".equals(isExit.getStatus())) {
                    isExit.setStatus("1");
                } else {
                    isExit.setStatus("0");
                }
                vcDocMngRuleDao.update(isExit);
            }
            // vcDocMngRuleDao.deleteByPK(idVcDocMngRule);
        }
    }

    /**
     * 查询并设置默认信息
     * 
     * @param docQueryDto
     * @param vcDocMngRuleDto
     * @return
     * @author wanghuajian since 2013-4-23下午08:22:41
     */
    public List<VcDocMngRuleShowVo> queryListAndSetDefaultConfig(DocDetailInquiryVo docQueryDto,
            VcDocMngRule vcDocMngRuleDto) throws BusinessException {
        try {
            return vcDocMngRuleDao.queryListAndSetDefaultConfig(docQueryDto, vcDocMngRuleDto);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 保存单证管理控制规则List信息
     * 
     * @param vcDocMngRuleDto
     *            人员、部门信息
     * @param vcDocMngRuleList
     *            单证管理控制规则list
     * @author wanghuajian since 2013-4-24下午02:44:05
     */
    public int saveDocMngRuleList(DocDetailInquiryVo docQueryDto, VcDocMngRule vcDocMngRuleDto,
            List<VcDocMngRule> vcDocMngRuleList, UserInfo userInfo) throws BusinessException {
        try {
            String msg = "";
            if (vcDocMngRuleDto != null) {
                if (StringUtils.isBlank(vcDocMngRuleDto.getMngType())) {
                    msg = "[单证管理控制类型为空！]";
                } else {
                    if (StringUtils.isBlank(vcDocMngRuleDto.getMngObjectCode())) {
                        msg = "[机构/使用人代码为空！]";
                    }
                }
            } else {
                msg = "[单证管理控制Vo信息为空！]";
            }
            if (msg.length() > 0) {
                BusinessException be = new BusinessException(msg);
                be.setModule("单证管理控制_保存单证管理控制规则");
                throw be;
            }

            // 删除已存在的满足当前条件的权限配置
            VcDocMngRule queryVo = new VcDocMngRule();
            queryVo.setMngType(vcDocMngRuleDto.getMngType());
            queryVo.setMngObjectCode(vcDocMngRuleDto.getMngObjectCode());
            queryVo.setStatus("1");

            vcDocMngRuleDao.deleteExistByLogic(docQueryDto, queryVo); // 删除执行

            for (VcDocMngRule docMngRule : vcDocMngRuleList) {
                docMngRule.setMngType(vcDocMngRuleDto.getMngType());

                // docMngRule.setTakerCode(vcDocMngRuleDto.getTakerCode());
                docMngRule.setMngObjectCode(vcDocMngRuleDto.getMngObjectCode());
                docMngRule.setOrgCode(vcDocMngRuleDto.getOrgCode());
                // 管理控制类型(0-部门 1-员工)
                if ("1".equals(vcDocMngRuleDto.getMngType())) {
                    String comCode = vcLevelDao.getComCode(vcDocMngRuleDto.getMngObjectCode());
                    if (StringUtils.isBlank(comCode)) {
                        BusinessException be = new BusinessException("使用人【"
                                + vcDocMngRuleDto.getMngObjectCode() + "】所属机构不存在！ ");
                        be.setModule("单证管理控制_保存单证管理控制规则  ");
                        throw be;
                    } else {
                        docMngRule.setOrgCode(comCode);
                    }
                }
                docMngRule.setStatus("1");

                docMngRule.setCreatedBy(userInfo.getUserCode());
                docMngRule.setDateCreated(new Date());
                docMngRule.setUpdatedBy(userInfo.getUserCode());
                docMngRule.setDateUpdated(new Date());
            }
            vcDocMngRuleDao.saveAll(vcDocMngRuleList);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return 1;

    }

    /**
     * 根据配置机构或人员code查询最大库存时间及库存量
     * 
     * @param mngObjectCode
     *            机构或人员code
     * @return int[]
     * @throws BusinessException
     * @author wanghuajian
     * @date 2013-06-21
     * 
     */
    public int[] getMngDefaultDate(String mngObjectCode) throws BusinessException {

        // 查找所配置的机构、用户的级别
        int levelNo = 4;
        try {
            List objectLevelList = vcLevelDao.findByUnitCode(mngObjectCode);
            if (objectLevelList != null && objectLevelList.size() > 0) {
                VcLevel objLevel = (VcLevel) objectLevelList.get(0);
                levelNo = objLevel.getLevelNo();
            }

            int maxStore = 1;
            int maxStoreTime = 1;
            // 查询最大库存量
            VcConstantConfig maxStoreVo = vcConstantConfigDao.getVcConstantConfig("maxStore", levelNo);
            if (maxStoreVo != null) {
                maxStore = Integer.valueOf(maxStoreVo.getConstantValue());
            }
            // 查询最大库存时间
            VcConstantConfig maxStoreTimeVo = vcConstantConfigDao
                    .getVcConstantConfig("maxStoreTime", levelNo);
            if (maxStoreTimeVo != null) {
                maxStoreTime = Integer.valueOf(maxStoreTimeVo.getConstantValue());
            }
            // 返回
            return new int[] { maxStore, maxStoreTime };
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
