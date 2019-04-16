package com.tapi.tcs.vc.baseinfo.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tapi.tcs.tf.common.helpers.Page;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.tf.platform.schema.vo.CodeLabelDto;
import com.tapi.tcs.vc.baseinfo.dao.InsuKindDao;
import com.tapi.tcs.vc.baseinfo.dao.VcDocRelAreaDao;
import com.tapi.tcs.vc.baseinfo.dao.VcDocRelInsuKindDao;
import com.tapi.tcs.vc.baseinfo.dao.VcDocVersionInfoDao;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.baseinfo.service.VcDocVersionInfoService;
import com.tapi.tcs.vc.baseinfo.vo.DetileNormalVO;
import com.tapi.tcs.vc.baseinfo.vo.DocInsuKindVo;
import com.tapi.tcs.vc.baseinfo.vo.DocVerSimpleVo;
import com.tapi.tcs.vc.baseinfo.vo.DocVersionInfoQueryVo;
import com.tapi.tcs.vc.baseinfo.vo.ZTreeDto;
import com.tapi.tcs.vc.common.dao.VcPubCodeManagerDao;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.order.dao.OrderManagerDao;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerifiedDet;
import com.tapi.tcs.vc.schema.model.VcDocInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocPrtNoRule;
import com.tapi.tcs.vc.schema.model.VcDocRelArea;
import com.tapi.tcs.vc.schema.model.VcDocRelInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfoMng;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDet;
import com.tapi.tcs.vc.schema.model.VcOrderLaunch;
import com.tapi.tcs.vc.schema.model.VcOrderLaunchDet;
import com.tapi.tcs.vc.schema.model.VcPubCode;
import com.tapi.tcs.vc.webservice.DoucmentTypeService.Dto.RationCode;
import com.tapi.tcs.vc.webservice.DoucmentTypeService.client.DoucmentTypeServiceClient;

/**
 * 单证版本信息Service实现
 * <p>
 * Date: 2013-03-16
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
/**
 * @author whj
 *
 */
/**
 * @author whj
 *
 */
/**
 * @author whj
 * 
 */
public class VcDocVersionInfoServiceImpl implements VcDocVersionInfoService {

    private static final Object Page = null;

	private VcDocVersionInfoDao vcDocVersionInfoDao;

    private InsuKindDao insuKindDao;

    private VcDocRelInsuKindDao vcDocRelInsuKindDao;

    private VcDocRelAreaDao vcDocRelAreaDao;

    private OrderManagerDao orderManagerDao;

    private VcLevelDao vcLevelDao;
    
    private VcPubCodeManagerDao vcPubCodeManagerDao;
    
    public VcPubCodeManagerDao getVcPubCodeManagerDao() {
		return vcPubCodeManagerDao;
	}

	public void setVcPubCodeManagerDao(VcPubCodeManagerDao vcPubCodeManagerDao) {
		this.vcPubCodeManagerDao = vcPubCodeManagerDao;
	}

	public void setVcDocVersionInfoDao(VcDocVersionInfoDao vcDocVersionInfoDao) {
        this.vcDocVersionInfoDao = vcDocVersionInfoDao;
    }

    public InsuKindDao getInsuKindDao() {
        return insuKindDao;
    }

    public void setInsuKindDao(InsuKindDao insuKindDao) {
        this.insuKindDao = insuKindDao;
    }

    public void setVcDocRelInsuKindDao(VcDocRelInsuKindDao vcDocRelInsuKindDao) {
        this.vcDocRelInsuKindDao = vcDocRelInsuKindDao;
    }

    public void setVcDocRelAreaDao(VcDocRelAreaDao vcDocRelAreaDao) {
        this.vcDocRelAreaDao = vcDocRelAreaDao;
    }

    public void setOrderManagerDao(OrderManagerDao orderManagerDao) {
        this.orderManagerDao = orderManagerDao;
    }

    public void setVcLevelDao(VcLevelDao vcLevelDao) {
        this.vcLevelDao = vcLevelDao;
    }
    @Override
	public Page queryDetail(String bussinessNo, UserInfo userInfo,
			String document,String startNum ,boolean flag,int pageNo,
			int pageSize)
			throws BusinessException {
    		//查询明细表（正常核销，非正常核销）
    	Page page = (Page)vcDocVersionInfoDao.queryDetail(bussinessNo, userInfo, document, startNum,flag,pageNo,
    				pageSize);
    	return page;
	}
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
    public void createVcDocVersionInfo(VcDocVersionInfo vcDocVersionInfo, UserInfo userInfo, File file)
            throws BusinessException {
        String msg = "";
        if (vcDocVersionInfo == null) {
            msg = "单证版本vo为空！ ";
        } else {

            DocVersionInfoQueryVo queryDto_code = new DocVersionInfoQueryVo();
            Page resustPage_code = null;
            queryDto_code.setDocVerCode(vcDocVersionInfo.getDocVerCode());
            resustPage_code = vcDocVersionInfoDao.queryVcDocVersionInfos(queryDto_code, 1, 5);
            if (resustPage_code.getResult().size() > 0) {
                msg = "单证版本代码重复！ ";
            }

            DocVersionInfoQueryVo queryDto_name = new DocVersionInfoQueryVo();
            Page resustPage_name = null;
            queryDto_name.setDocVerNameEQ(vcDocVersionInfo.getDocVerName());
            resustPage_name = vcDocVersionInfoDao.queryVcDocVersionInfos(queryDto_name, 1, 5);
            if (resustPage_name.getResult().size() > 0) {
                msg = msg + "单证版本名称重复！ ";
            }

        }
        if (StringUtils.isNotBlank(msg)) {
            BusinessException be = new BusinessException(msg);
            be.setModule("单证版本信息_新增单证版本信息");
            throw be;
        } else {

            // 单证印刷流水号规则表流
            List<VcDocPrtNoRule> docPrtNoRuleList = vcDocVersionInfo.getDocPrtNoRuleList();
            if (docPrtNoRuleList != null && docPrtNoRuleList.size() > 0) {
                for (VcDocPrtNoRule vcDocPrtNoRule : docPrtNoRuleList) {
                    // vcPrintDocVersion.setIdVcPrintery(printery.getIdVcPrintery());
                    vcDocPrtNoRule.setDateCreated(new Date());
                    vcDocPrtNoRule.setCreatedBy(userInfo.getUserCode());
                    vcDocPrtNoRule.setDateUpdated(new Date());
                    vcDocPrtNoRule.setUpdatedBy(userInfo.getUserCode());
                    vcDocPrtNoRule.setDocVersionInfo(vcDocVersionInfo);
                }
            }
            // 单证管控信息处理
            List<VcDocVersionInfoMng> docMngList = vcDocVersionInfo.getDocVersionInfoMngList();
            if (docMngList != null && docMngList.size() > 0) {
                for (VcDocVersionInfoMng vcDocMng : docMngList) {
                    vcDocMng.setDocVerCode(vcDocVersionInfo.getDocVerCode());
                    vcDocMng.setDateCreated(new Date());
                    vcDocMng.setCreatedBy(userInfo.getUserCode());
                    vcDocMng.setDateUpdated(new Date());
                    vcDocMng.setUpdatedBy(userInfo.getUserCode());               
                }
            }

            // 创建人所属机构代码
            vcDocVersionInfo.setOrgCode(userInfo.getComCode());
            vcDocVersionInfo.setStatus("1");

            vcDocVersionInfo.setDateCreated(new Date());
            vcDocVersionInfo.setCreatedBy(userInfo.getUserCode());
            vcDocVersionInfo.setDateUpdated(new Date());
            vcDocVersionInfo.setUpdatedBy(userInfo.getUserCode());
            try {
                vcDocVersionInfoDao.save(vcDocVersionInfo);
                vcDocVersionInfoDao.saveDocMngList(docMngList); //管控信息新增
            } catch (Exception e) {
                throw new BusinessException("数据保存出错！", e);
            }

            // 单证样式扫描件上传服务器
            File savefile = new File(new File(vcDocVersionInfo.getFilePath()), vcDocVersionInfo.getFileName());
            if (!savefile.getParentFile().exists()) {
                savefile.getParentFile().mkdirs();
            }
            try {
                FileUtils.copyFile(file, savefile);
            } catch (Exception e) {
                throw new BusinessException("单证样式扫描件上传失败：" + e.getMessage());
            }
        }
    }

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
    public void updateVcDocVersionInfo(VcDocVersionInfo vcDocVersionInfo, UserInfo userInfo, File file)
            throws BusinessException {
        VcDocVersionInfo isExistDto = vcDocVersionInfoDao.getVcDocVersionInfo(vcDocVersionInfo
                .getIdVcDocVersionInfo());
        // 若该单证版本信息为空，抛出异常
        if (isExistDto == null) {
            BusinessException be = new BusinessException("不存在单证版本信息！");
            be.setModule("单证版本信息_更新单证版本信息");
            throw be;
        }

        DocVersionInfoQueryVo queryDto_name = new DocVersionInfoQueryVo();
        Page resustPage_name = null;
        queryDto_name.setDocVerNameEQ(vcDocVersionInfo.getDocVerName());
        resustPage_name = vcDocVersionInfoDao.queryVcDocVersionInfos(queryDto_name, 1, 5);
        // 重复数
        int num = 0;
        VcDocVersionInfo tempVo = null;
        if (resustPage_name != null && resustPage_name.getResult() != null
                && resustPage_name.getResult().size() > 0) {
            for (int i = 0; i < resustPage_name.getResult().size(); i++) {
                tempVo = (VcDocVersionInfo) resustPage_name.getResult().get(i);
                if (!tempVo.getIdVcDocVersionInfo().equals(vcDocVersionInfo.getIdVcDocVersionInfo())) {
                    num++;
                }
            }
        }
        if (num > 0) {
            BusinessException be = new BusinessException("单证类型信息名称重复！ ");
            be.setModule("单证类型信息_更新单证版本信息");
            throw be;
        }

        // 文件处理
        if (file != null) {
            File oldFile = new File(isExistDto.getFilePath() + "/" + isExistDto.getFileName());
            if (oldFile.isFile() && oldFile.exists()) {
                oldFile.delete();
            }
            File savefile = new File(new File(vcDocVersionInfo.getFilePath()), vcDocVersionInfo.getFileName());
            if (!savefile.getParentFile().exists()) {
                savefile.getParentFile().mkdirs();
            }
            try {
                FileUtils.copyFile(file, savefile);
            } catch (Exception e) {
                throw new BusinessException("单证样式扫描件上传失败： " + e.getMessage());// 文件上传错误
            }
            // 扫描件名称
            isExistDto.setFileName(vcDocVersionInfo.getFileName());
            // 扫描件路径
            isExistDto.setFilePath(vcDocVersionInfo.getFilePath());
        }

        // 调用单证版本信息对应的dao进行更新操作
        isExistDto.setIdVcDocType(vcDocVersionInfo.getIdVcDocType());
        isExistDto.setDocVerName(vcDocVersionInfo.getDocVerName());
        isExistDto.setDocCountCode(vcDocVersionInfo.getDocCountCode());
        isExistDto.setIsAutoGenNo(vcDocVersionInfo.getIsAutoGenNo());
        isExistDto.setIsOrder(vcDocVersionInfo.getIsOrder());
        isExistDto.setValue(vcDocVersionInfo.getValue());
        // isExistDto.setFileName(vcDocVersionInfo.getFileName());
        // isExistDto.setFilePath(vcDocVersionInfo.getFilePath());
        isExistDto.setDateUpdated(new Date());
        isExistDto.setUpdatedBy(userInfo.getUserCode());

        // 单证印刷流水号规则表流
        List<VcDocPrtNoRule> docPrtNoRuleList = vcDocVersionInfo.getDocPrtNoRuleList();
        if (docPrtNoRuleList != null && docPrtNoRuleList.size() > 0) {
            for (VcDocPrtNoRule vcDocPrtNoRule : docPrtNoRuleList) {
                // vcPrintDocVersion.setIdVcPrintery(printery.getIdVcPrintery());
                vcDocPrtNoRule.setDateCreated(new Date());
                vcDocPrtNoRule.setCreatedBy(userInfo.getUserCode());
                vcDocPrtNoRule.setDateUpdated(new Date());
                vcDocPrtNoRule.setUpdatedBy(userInfo.getUserCode());
                vcDocPrtNoRule.setDocVersionInfo(isExistDto);
            }
        }
        // 删除单证印刷流水号规则
        VcDocPrtNoRule vcDocPrtNoRule = new VcDocPrtNoRule();
        vcDocPrtNoRule.setIdVcDocVersionInfo(isExistDto.getIdVcDocVersionInfo());
        try {
            vcDocVersionInfoDao.deletePrtNoRuleByCondition(vcDocPrtNoRule);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
        
        // 单证管控信息处理
        List<VcDocVersionInfoMng> docMngList = vcDocVersionInfo.getDocVersionInfoMngList();
        if (docMngList != null && docMngList.size() > 0) {
            for (VcDocVersionInfoMng vcDocMng : docMngList) {
                vcDocMng.setDocVerCode(isExistDto.getDocVerCode());
                vcDocMng.setDateCreated(new Date());
                vcDocMng.setCreatedBy(userInfo.getUserCode());
                vcDocMng.setDateUpdated(new Date());
                vcDocMng.setUpdatedBy(userInfo.getUserCode());               
            }
        }
        // 删除原单证管控信息 并新增新的管控信息
        try {
            VcDocVersionInfoMng queryDto=new VcDocVersionInfoMng();
            queryDto.setDocVerCode(isExistDto.getDocVerCode());
            vcDocVersionInfoDao.deleteDocMng(queryDto);  //删除
            vcDocVersionInfoDao.saveDocMngList(docMngList); //新增
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }

        // 更新
        isExistDto.setDocPrtNoRuleList(docPrtNoRuleList);
        try {
            vcDocVersionInfoDao.update(isExistDto);
        } catch (Exception e) {
            throw new BusinessException("数据更新出错！", e);
        }

    }

    /**
     * 根据单证版本信息主键获取单证版本信息
     */
    public VcDocVersionInfo getVcDocVersionInfo(Long idVcDocVersionInfo) throws BusinessException {
        if (idVcDocVersionInfo == null) {
            BusinessException be = new BusinessException("单证版本信息主键为空！ ");
            be.setModule("单证版本信息_根据主键获取单证版本信息");
            throw be;
        }
        try {
            VcDocVersionInfo vcDocVersionInfo = vcDocVersionInfoDao.getVcDocVersionInfo(idVcDocVersionInfo);
            vcDocVersionInfo
                    .setCreatedUser(vcLevelDao.getUnitNameByUnitCode(vcDocVersionInfo.getCreatedBy()));
            vcDocVersionInfo
                    .setUpdatedUser(vcLevelDao.getUnitNameByUnitCode(vcDocVersionInfo.getCreatedBy()));
            vcDocVersionInfo.setOrgName(vcLevelDao.getUnitNameByUnitCode(vcDocVersionInfo.getOrgCode()));
            return vcDocVersionInfo;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }

    }

    /**
     * 查询单证版本信息信息
     * 
     * @param queryVo
     *            查询条件
     * @param currentPage
     *            当前页
     * @param pageNumber
     *            总页数
     * @return Page
     * @throws BusinessException
     *             异常信息
     */
    public Page queryVcDocVersionInfoByPages(DocVersionInfoQueryVo queryVo, int currentPage, int pageNumber)
            throws BusinessException {
        try {
            Page page = vcDocVersionInfoDao.queryVcDocVersionInfos(queryVo, currentPage, pageNumber);
            List resultList = page.getResult();
            if (resultList != null && resultList.size() > 0) {
                VcDocVersionInfo vcDocVersionInfo = null;
                for (int i = 0; i < resultList.size(); i++) {
                    vcDocVersionInfo = (VcDocVersionInfo) resultList.get(i);
                    vcDocVersionInfo.setCreatedUser(vcLevelDao.getUnitNameByUnitCode(vcDocVersionInfo
                            .getCreatedBy()));
                    vcDocVersionInfo.setUpdatedUser(vcLevelDao.getUnitNameByUnitCode(vcDocVersionInfo
                            .getCreatedBy()));
                    vcDocVersionInfo.setOrgName(vcLevelDao.getUnitNameByUnitCode(vcDocVersionInfo
                            .getOrgCode()));
                }

            }
            return page;
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }

    }
    
    
    /**
     * 单证类型查询[单证明细查询用]
     * 
     * @author wanghuajian
     * @date 2014-03-17
     * 
     */
    public Page queryDocForDocDetInquiry(DocVersionInfoQueryVo queryVo,UserInfo userInfo,
            int currentPage, int pageNumber) throws BusinessException{
        try {
                Page page = vcDocVersionInfoDao.queryDocForDocDetInquiry(queryVo,userInfo, currentPage, pageNumber);
                List resultList = page.getResult();
                Map<String,String> orgMap=new HashMap<String,String>();
                List<VcDocVersionInfo> newResultList = new ArrayList<VcDocVersionInfo>();
                if (resultList != null && resultList.size() > 0) {                   
                    VcDocVersionInfo vcDocVersionInfo = null;
                    String orgName="";
                    ///doc.idVcDocVersionInfo,doc.docVerCode,doc.docVerName,doc.orgCode,doc.status
                    for (int i = 0; i < resultList.size(); i++) {
                        vcDocVersionInfo=new VcDocVersionInfo();
                        Object[] obj=(Object[]) resultList.get(i);  
                       // vcDocVersionInfo = (VcDocVersionInfo) resultList.get(i); 
                        vcDocVersionInfo.setIdVcDocVersionInfo((Long)obj[0]);
                        vcDocVersionInfo.setDocVerCode((String)obj[1]);
                        vcDocVersionInfo.setDocVerName((String)obj[2]);
                        vcDocVersionInfo.setOrgCode((String)obj[3]);
                        if(StringUtils.isBlank(orgMap.get((String)obj[3]))){
                            orgName=vcLevelDao.getUnitNameByUnitCode((String)obj[3]);
                            vcDocVersionInfo.setOrgName(orgName);
                            orgMap.put((String)obj[3], orgName);
                        }else{
                            vcDocVersionInfo.setOrgName(orgMap.get((String)obj[3]));
                        }
                        newResultList.add(vcDocVersionInfo);
                    }
                }
                page.setData(newResultList);
                return page;
            } catch (DaoException e) {
                throw new BusinessException(e.getMessage(), e);
            }

        }
    /**
     * 删除单证版本信息
     */
    public void deleteVcDocVersionInfos(String idVcDocVersionInfos) throws BusinessException {

        String[] arrIds = idVcDocVersionInfos.split(",");
        Long idVcDocVersionInfo = null;
        try {
            for (String strId : arrIds) {
                idVcDocVersionInfo = new Long(strId);
                // TODO。。。
                /*
                 * 判断当前带删除的单证版本信息是否维护有单证版本，有则不允许删除， 系统同时给出提示：“存在单证版本信息：XXX（XXX为单证版本信息名称）的单证版本，不可删除该单证版本信息！”
                 */
                // 调用单证版本信息对应的dao进行删除操作
                vcDocVersionInfoDao.deleteByPK(idVcDocVersionInfo);
            }
        } catch (Exception e) {
            throw new BusinessException("数据删除出错！", e);
        }
    }

    /**
     * 根据所选的ids字符串查询单证版本信息
     */
    public List<VcDocVersionInfo> queryVcDocVersionInfos(String idVcDocVersionInfos) throws BusinessException {
        try {
            List<VcDocVersionInfo> list = new ArrayList<VcDocVersionInfo>();
            String[] arrIds = idVcDocVersionInfos.split(",");
            Long idVcDocVersionInfo = null;
            VcDocVersionInfo vcDocVersionInfo = null;
            for (String strId : arrIds) {
                idVcDocVersionInfo = new Long(strId);
                vcDocVersionInfo = vcDocVersionInfoDao.getVcDocVersionInfo(idVcDocVersionInfo);
                if (vcDocVersionInfo != null) {
                    list.add(vcDocVersionInfo);
                }
            }

            return list;
        } catch (Exception e) {
            throw new BusinessException("查询异常！", e);
        }
    }

    /**
     * 关联险种信息
     */
    public void saveRelInsuKind(String idVcDocVersionInfos, String selectRelInsuKindCodes, UserInfo userInfo)
            throws BusinessException {
        try {
            List<VcDocVersionInfo> list = new ArrayList<VcDocVersionInfo>();
            String[] arrIds = idVcDocVersionInfos.split(",");
            String[] arrInsuKindCodes = selectRelInsuKindCodes.split(",");
            Long idVcDocVersionInfo = null;
            Long idInsuKind = null;
            VcDocVersionInfo vcDocVersionInfo = null;
            VcDocRelInsuKind vcDocRelInsuKindTemp = null;
            for (String strId : arrIds) {
                idVcDocVersionInfo = new Long(strId);
                vcDocVersionInfo = vcDocVersionInfoDao.getVcDocVersionInfo(idVcDocVersionInfo);
                if (vcDocVersionInfo != null) {
                    // 根据单证流水删除险种关联
                    vcDocRelInsuKindDao.deleteByDocVersionInfoId(idVcDocVersionInfo);
                    for (String strInsuKindCode : arrInsuKindCodes) {
                        if (StringUtils.isNotBlank(strInsuKindCode)) {
                            vcDocRelInsuKindTemp = new VcDocRelInsuKind();
                            vcDocRelInsuKindTemp.setIdVcDocVersionInfo(idVcDocVersionInfo);
                            vcDocRelInsuKindTemp.setInsuKindCode(strInsuKindCode);
                            vcDocRelInsuKindTemp.setDateCreated(new Date());
                            vcDocRelInsuKindTemp.setCreatedBy(userInfo.getUserCode());
                            vcDocRelInsuKindTemp.setDateUpdated(new Date());
                            vcDocRelInsuKindTemp.setUpdatedBy(userInfo.getUserCode());
                            vcDocRelInsuKindDao.save(vcDocRelInsuKindTemp);
                        }
                    }
                }
            }
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 关联地区信息
     */
    public void saveRelArea(String idVcDocVersionInfos, String relAreaCodes, UserInfo userInfo)
            throws BusinessException {
        try {
            List<VcDocVersionInfo> list = new ArrayList<VcDocVersionInfo>();
            String[] arrIds = idVcDocVersionInfos.split(",");
            String[] arrRelAreaCodes = relAreaCodes.split(",");
            Long idVcDocVersionInfo = null;
            VcDocVersionInfo vcDocVersionInfo = null;
            VcDocRelArea vcDocRelAreaTemp = null;
            for (String strId : arrIds) {
                idVcDocVersionInfo = new Long(strId);
                vcDocVersionInfo = vcDocVersionInfoDao.getVcDocVersionInfo(idVcDocVersionInfo);
                if (vcDocVersionInfo != null) {
                    // 根据单证流水删除地区关联
                    vcDocRelAreaDao.deleteByDocVersionInfoId(idVcDocVersionInfo);
                    for (String orgCode : arrRelAreaCodes) {
                        if (StringUtils.isNotBlank(orgCode)) {
                            vcDocRelAreaTemp = new VcDocRelArea();
                            vcDocRelAreaTemp.setIdVcDocVersionInfo(vcDocVersionInfo.getIdVcDocVersionInfo());
                            vcDocRelAreaTemp.setOrgCode(orgCode);
                            vcDocRelAreaTemp.setDateCreated(new Date());
                            vcDocRelAreaTemp.setCreatedBy(userInfo.getUserCode());
                            vcDocRelAreaTemp.setDateUpdated(new Date());
                            vcDocRelAreaTemp.setUpdatedBy(userInfo.getUserCode());
                            vcDocRelAreaDao.save(vcDocRelAreaTemp);
                        }
                    }
                }
            }
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 启用、停用
     * 
     * @throws Exception
     * 
     */
    public String updateVcDocVersionInfoStatus(String idVcDocVersionInfos, UserInfo userInfo)
            throws BusinessException {
    	String flags=null;
    	String staus=null;
        String result = "操作成功！ ";
        if (StringUtils.isNotBlank(idVcDocVersionInfos)) {
            String[] arrIds = idVcDocVersionInfos.split(",");
            Long idVcDocVersionInfo = null;
            VcDocVersionInfo vcDocVersionInfo = null;
            StringBuffer errMsg = new StringBuffer("");
            for (String strId : arrIds) {
                idVcDocVersionInfo = new Long(strId);
                try {
                    vcDocVersionInfo = vcDocVersionInfoDao.getVcDocVersionInfo(idVcDocVersionInfo);
                    flags=vcDocVersionInfo.getStatus();
                } catch (Exception e) {
                    throw new BusinessException("数据查询异常！", e);
                }
                if (vcDocVersionInfo != null) {
                    // (0:停用/1:启用)
                    if ("0".equals(vcDocVersionInfo.getStatus())) {
                        vcDocVersionInfo.setStatus("1"); // 更改为启用
                    } else {
                        // 只可注销单证类型状态为“有效”的单证类型。只可注销没有征订申请的单证类型。注销后单证版本状态变为“停用”。
                        // 需记录最后注销时间、注销人。
                        VcOrderLaunchDet queryDto = new VcOrderLaunchDet();
                        queryDto.setVersionCode(vcDocVersionInfo.getDocVerCode());
                        // queryDto.setValidStatus("1");
                        try {
                            List list = orderManagerDao.queryOrderLaunchDetList(queryDto);
                            if (list.size() > 0) {
                                errMsg.append("存在单证类型[" + vcDocVersionInfo.getDocVerName()
                                        + "]的征订申请，不可停用该单证类型！\n");
                                continue;
                            } else {
                                vcDocVersionInfo.setStatus("0");
                                // vcDocVersionInfoDao.update(vcDocVersionInfo);
                            }
                        } catch (Exception e) {
                            throw new BusinessException("查询异常！", e);
                        }
                    }
                    vcDocVersionInfo.setUpdatedBy(userInfo.getUserCode());
                    vcDocVersionInfo.setDateUpdated(new Date());
                    try {
                        vcDocVersionInfoDao.update(vcDocVersionInfo);
                        errMsg.append("单证类型[" + vcDocVersionInfo.getDocVerName() + "]启用/停用成功！\n");
                        
                    } catch (Exception e) {
                        errMsg.append("单证类型[" + vcDocVersionInfo.getDocVerName() + "]启用/停用成功！\n");
                    }
                    try {
                    	//fyh
                    		if(flags.equals("0")){
                    			staus="1";
                    		}else{
                    			staus="0";
                    		}
                    		VcPubCode vcPubCode =vcPubCodeManagerDao.getVcPubCode("productUrl", "productUrl");
                    		sendDoucmentType("D",vcDocVersionInfo.getIdVcDocVersionInfo().toString(),null,null,staus,vcPubCode.getCodeEName());
            		} catch (Exception e) {
            			errMsg.append("单证类型[" + vcDocVersionInfo.getDocVerName() + "]推送产品工厂失败！\n");
            		}
                }
            }
            result = errMsg.toString();
        }
        return result;
    }
    public void sendDoucmentType(String flag,String id,String docVerCode,String DocVerName,String status,String url){
        RationCode rationCode=new RationCode();
        rationCode.setIdVcDocversionInfos(id);
        rationCode.setDocumentCodes(docVerCode);
        rationCode.setDocumentNames(DocVerName);
        rationCode.setDocumentStates(status);
        DoucmentTypeServiceClient doucmentTypeServiceClient= new DoucmentTypeServiceClient();
        doucmentTypeServiceClient.doucmentTypeService(flag, rationCode,url);
   } 
 
   
    /**
     * 单证类型代码自动完成
     * 
     * @param codeValue
     * @return
     */
    @Override
    public List queryVcDocVersionInfoForAutoComplete(String codeValue) throws BusinessException {
        try {
            QueryRule queryRule = QueryRule.getInstance();
            // 状态 0:停用/1:启用
            queryRule.addEqual("status", "1");
            queryRule.addLike("docVerCode", "*" + codeValue + "*");
            List<VcDocVersionInfo> VcDocVersionInfos = vcDocVersionInfoDao.find(queryRule);
            List<CodeLabelDto> codeLableLst = new ArrayList<CodeLabelDto>();
            for (Iterator iterator = VcDocVersionInfos.iterator(); iterator.hasNext();) {
                VcDocVersionInfo vcDocVersionInfo = (VcDocVersionInfo) iterator.next();
                CodeLabelDto codeLabelDto = new CodeLabelDto();
                codeLabelDto.setValue(vcDocVersionInfo.getDocVerCode());
                // codeLabelDto.setValue(vcDocVersionInfo.getIdVcDocVersionInfo().toString());
                codeLabelDto.setLable(vcDocVersionInfo.getDocVerName());
                codeLableLst.add(codeLabelDto);
            }
            return codeLableLst;
        } catch (Exception e) {
            throw new BusinessException("查询异常！", e);
        }
    }

    /**
     * 单证类型代码自动完成 发票
     * 
     * @param codeValue
     * @return
     */
    @Override
    public List<CodeLabelDto> queryInvoiceForAutoComplete(String codeValue) throws BusinessException {
        try {

            String hql = "from VcDocType t,VcDocVersionInfo v " + "where v.idVcDocType = t.idVcDocType "
                    + "and t.docType = ? " + "and v.docVerCode like ? and t.status = ? ";
            List params = new ArrayList();
            params.add("1");
            params.add("%" + codeValue + "%");
            params.add("1");
            List<VcDocVersionInfo> VcDocVersionInfos = vcDocVersionInfoDao.findByHql(hql, params.toArray());

            List<CodeLabelDto> codeLableLst = new ArrayList<CodeLabelDto>();
            for (Iterator iterator = VcDocVersionInfos.iterator(); iterator.hasNext();) {
                Object[] objs = (Object[]) iterator.next();
                VcDocVersionInfo vcDocVersionInfo = (VcDocVersionInfo) objs[1];
                CodeLabelDto codeLabelDto = new CodeLabelDto();
                codeLabelDto.setValue(vcDocVersionInfo.getDocVerCode());
                // codeLabelDto.setValue(vcDocVersionInfo.getIdVcDocVersionInfo().toString());
                codeLabelDto.setLable(vcDocVersionInfo.getDocVerName());
                codeLableLst.add(codeLabelDto);
            }
            return codeLableLst;
        } catch (Exception e) {
            throw new BusinessException("查询异常！", e);
        }
    }

    /**
     * 根据给定的条件查询
     */
    public List<VcDocVersionInfo> getDocVersionInfoList(Map<String, Object> map) throws BusinessException {
        try {
            return vcDocVersionInfoDao.getDocVersionInfoList(map);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<VcDocVersionInfo> getBizDocVersionInfoList(String comCode, String docVerCode)
            throws BusinessException {
        List<VcDocVersionInfo> list = null;
        try {
            list = vcDocVersionInfoDao.getBizDocVersionInfoList(comCode, docVerCode);
        } catch (DaoException de) {
            de.printStackTrace();
            throw new BusinessException(de.getMessage(),de);
        }
        return list;
    }
    
    /**
     * 单证类型查询【 进行征订的单证】
     * @param comCode
     * @param docVerCode
     * @return
     * @throws BusinessException
     *@author whj
     *@since Feb 25, 2014
     */
    @Override
    public List<VcDocVersionInfo> getOrderVersionInfoList(String comCode, String docVerCode)
            throws BusinessException {
        List<VcDocVersionInfo> list = null;
        try {
            list = vcDocVersionInfoDao.getOrderDocVersionInfoList(comCode, docVerCode);
        } catch (DaoException de) {
            de.printStackTrace();
            throw new BusinessException(de.getMessage(),de);
        }
        return list;
    }

    /**
     * 根据单证类型代码查找单证类型名称
     * 
     * @param versionCode
     * @return
     * @throws BusinessException
     * @author chy
     * @date 2013-04-24
     */
    public String getVersionName(String versionCode) throws BusinessException {
        String versionName = "";
        try {
            versionName = vcDocVersionInfoDao.getVersionName(versionCode);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return versionName;
    }

    /**
     * 根据单证类型查找生成规则
     * 
     * @param versionCode
     * @return
     * @throws BusinessException
     * @author chy
     * @date 2013-04-24
     */
    public List<Object[]> getDocNumRule(String versionCode) throws BusinessException {
        List<Object[]> list = null;
        try {
            list = vcDocVersionInfoDao.getDocNumRule(versionCode);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<VcDocVersionInfo> queryDocVersionInfo(String insuType, String insuKind, String docTypeCode,
            String docType) throws BusinessException {
        try {
            StringBuffer sql = new StringBuffer();
            List params = new ArrayList();
            sql.append(" ( SELECT dv.id_vc_doc_version_info,dv.doc_ver_code,dv.doc_ver_name  "
                    + " FROM vc_doc_version_info dv,vc_doc_insu_kind ik ,vc_doc_rel_insu_kind r "
                    + " WHERE  dv.id_vc_doc_version_info = r.id_vc_doc_version_info "
                    + " AND r.insu_kind_code = ik.insu_kind_code ");
            if (Beans.isNotEmpty(insuType)) {
                sql.append(" AND ik.insu_type_code = ? ");
                params.add(insuType);
            }
            if (Beans.isNotEmpty(insuType)) {
                sql.append(" AND ik.insu_kind_code = ? ");
                params.add(insuType);
            }
            sql.append(" ) " + " UNION "
                    + " ( SELECT dv.id_vc_doc_version_info,dv.doc_ver_code,dv.doc_ver_name   "
                    + " FROM vc_doc_version_info dv,vc_doc_type dt "
                    + " WHERE  dv.id_vc_doc_type=dt.ID_VC_DOC_TYPE ");
            if (Beans.isNotEmpty(docTypeCode)) {
                sql.append(" AND dt.doc_type_code = ? ");
                params.add(docTypeCode);
            }

            if (Beans.isNotEmpty(docType)) {
                sql.append(" AND dt.doc_type = ? ");
                params.add(docType);
            }
            sql.append("  ) ");

            List result = vcDocVersionInfoDao.findBySQL(sql.toString(), params.toArray());
            List<VcDocVersionInfo> returnList = new ArrayList<VcDocVersionInfo>();
            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                Object[] objects = (Object[]) iterator.next();
                VcDocVersionInfo vcDocVersionInfo = new VcDocVersionInfo();
                vcDocVersionInfo.setIdVcDocVersionInfo(((BigDecimal) objects[0]).longValue());
                vcDocVersionInfo.setDocVerCode((String) objects[1]);
                vcDocVersionInfo.setDocVerName((String) objects[2]);
                returnList.add(vcDocVersionInfo);
            }

            /*
             * StringBuffer hql = new StringBuffer();hql.append(
             * " from VcDocRelInsuKind r,VcDocInsuKind ik,VcDocInsuType it,VcDocType dt,VcDocVersionInfo vi " );
             * hql.append(" where ik.idVcDocInsuType = it.idVcDocInsuType ");
             * hql.append(" and r.insuKindCode = ik.insuKindCode ");
             * hql.append(" and vi.idVcDocVersionInfo = r.idVcDocVersionInfo ");
             * hql.append(" and dt.idVcDocType = vi.idVcDocType "); List params = new ArrayList();
             * 
             * if(Beans.isNotEmpty(insuType)){ hql.append(" and it.insuTypeCode = ? "); params.add(insuType); }
             * 
             * if(Beans.isNotEmpty(insuKind)){ hql.append(" and ik.insuKindCode = ? "); params.add(insuKind); }
             * 
             * if(Beans.isNotEmpty(docTypeCode)){ hql.append(" and dt.docTypeCode = ? "); params.add(docTypeCode); }
             * 
             * if(Beans.isNotEmpty(docType)){ hql.append(" and dt.docType = ? "); params.add(docType); }
             * List<VcDocVersionInfo> returnList = new ArrayList<VcDocVersionInfo>(); List result =
             * vcDocVersionInfoDao.findByHql(hql.toString(), params.toArray()); for (Iterator iterator =
             * result.iterator(); iterator.hasNext();) { Object[] objects = (Object[]) iterator.next(); for(int
             * i=0;i<objects.length;i++){ returnList.add((VcDocVersionInfo) objects[4]); } }
             */
            return returnList;
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 根据单证类型流水查找关联险种
     * 
     * @param idVcDocVersionInfo
     * @return
     * @throws Exception
     * @author wanghuajian
     * @date 2013-04-27
     */
    public List<DocInsuKindVo> queryRelInsuKindListByDocId(Long idVcDocVersionInfo) throws BusinessException {
        try {
            return vcDocRelInsuKindDao.queryRelInsuKindListByDocId(idVcDocVersionInfo);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 根据单证类型流水查找关联地区
     * 
     * @param idVcDocVersionInfo
     * @return
     * @throws Exception
     * @author wanghuajian
     * @date 2013-04-27
     */
    public List queryRelAreaListByDocId(Long idVcDocVersionInfo) throws BusinessException {
        try {
            return vcDocRelAreaDao.queryRelAreaListByDocId(idVcDocVersionInfo);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tapi.tcs.vc.baseinfo.service.VcDocVersionInfoService#isExistValid (java.lang.String)
     */
    @Override
    public boolean isExistValid(String docVerCode) throws BusinessException {
        boolean rs = false;
        try {
            List<VcDocVersionInfo> validList = vcDocVersionInfoDao.getValidVcDocVersionInfo(docVerCode);

            if (validList != null && validList.size() == 1) {
                rs = true;
            }
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return rs;
    }

    @Override
    public Page queryDocVerForSelector(DocVerSimpleVo docVerSimpleVo, int pageNo, int pageSize)
            throws BusinessException {
        try {
            return vcDocVersionInfoDao.queryDocVerForSelector(docVerSimpleVo, pageNo, pageSize);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 根据单证类型流水查询已关联/未关联的险种
     * 
     * @param idVcDocVersionInfo
     *            单证类型流水
     * @param type
     *            查询类型（0-已关联的险种 1-未关联的险种）
     * @return List<VcDocInsuKind>
     */
    public List<VcDocInsuKind> queryRelInsuKindList(Long idVcDocVersionInfo, int type)
            throws BusinessException {
        try {
            // insuKindDao.
            StringBuffer hql = new StringBuffer();
            List<Object> params = new ArrayList<Object>();

            hql.append(" from VcDocInsuKind insuKind ");
            hql.append(" where insuKind.status = ? ");
            params.add("1");
            if (type == 0) {
                hql.append(" and exists ");
            } else {
                hql.append(" and not exists ");
            }

            hql.append(" (select 1 from VcDocRelInsuKind rel where  rel.idVcDocVersionInfo= ? ");
            params.add(idVcDocVersionInfo);

            hql.append(" and rel.insuKindCode=insuKind.insuKindCode) ");

            return (List<VcDocInsuKind>) vcDocVersionInfoDao.findByHql(hql.toString(), params.toArray());
        } catch (Exception e) {
            throw new BusinessException("查询数据库异常！", e);
        }
    }

    /**
     * 根据单证类型流水查询关联的地区树ZTree
     * 
     * @param idVcDocVersionInfo
     *            单证类型流水
     * @param minLevel
     *            显示到树形的哪一级
     * @return List<ZTreeDto>
     */
    public List<ZTreeDto> queryRelAreaZTree(Long idVcDocVersionInfo, int minLevel) throws BusinessException {
        try {
            // 查询所有地区树
            List<VcLevel> list = vcLevelDao.queryOrgZTreeListByRootOrgId(new Long(0), minLevel);
            // 查询已关联的地区
            List<VcLevel> relAreaList = vcDocRelAreaDao.queryRelAreaListByDocId(idVcDocVersionInfo);
            Set<String> relOrgCodeSet = new HashSet<String>();
            for (VcLevel relArea : relAreaList) {
                relOrgCodeSet.add(relArea.getUnitCode());
            }

            VcDocRelArea queryDto = new VcDocRelArea();
            queryDto.setIdVcDocVersionInfo(idVcDocVersionInfo);
            List<ZTreeDto> zTreeDtoList = new ArrayList<ZTreeDto>();
            ZTreeDto zTreeDto = null;
            for (VcLevel level : list) {
                zTreeDto = new ZTreeDto();
                zTreeDto.setId(level.getId().toString());
                zTreeDto.setInfo(level.getUnitName());
                zTreeDto.setPid(level.getParentOrgId().toString());
                zTreeDto.setValue(level.getUnitCode());
                if (level.getLevelNo() == 1) {
                    zTreeDto.setOpen(true);
                }
                // 地区已关联则默认树形节点勾选
                if (relOrgCodeSet.contains(level.getUnitCode())) {
                    zTreeDto.setChecked(true);
                } else {
                    zTreeDto.setChecked(false);
                }
                zTreeDtoList.add(zTreeDto);
            }
            return zTreeDtoList;
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }

    }

    /**
     * 根据给定的条件查询 使用人单证发放 ljin
     */
    public List<VcDocVersionInfo> getJSONUserSend(Map<String, Object> map) throws BusinessException {
        try {
            return vcDocVersionInfoDao.getJSONUserSend(map);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List autoGetVcDocVersionInfoForAutoComplete(String codeValue, String orgCode)
            throws BusinessException {
        try {
            StringBuffer sb = new StringBuffer();
            List<Object> params = new ArrayList<Object>();
            sb.append("from VcDocVersionInfo v where 1=1 ");
            if (StringUtils.isNotEmpty(codeValue)) {
                sb.append("and  v.docVerCode like '%" + codeValue + "%'");
            }
            sb.append("and v.status=? ");
            params.add("1");
            sb
                    .append("and exists(select 1 from VcDocRelArea rel,VcTaker tak where rel.orgCode=tak.orgCode and  rel.idVcDocVersionInfo=v.idVcDocVersionInfo ");
            sb.append("and rel.orgCode=?  ) ");
            params.add(orgCode);

            List<VcDocVersionInfo> VcDocVersionInfos = vcDocVersionInfoDao.findByHql(sb.toString(), params
                    .toArray());

            List<CodeLabelDto> codeLableLst = new ArrayList<CodeLabelDto>();
            for (Iterator iterator = VcDocVersionInfos.iterator(); iterator.hasNext();) {
                VcDocVersionInfo vcDocVersionInfo = (VcDocVersionInfo) iterator.next();
                CodeLabelDto codeLabelDto = new CodeLabelDto();
                codeLabelDto.setValue(vcDocVersionInfo.getDocVerCode());
                // codeLabelDto.setValue(vcDocVersionInfo.getIdVcDocVersionInfo().toString());
                codeLabelDto.setLable(vcDocVersionInfo.getDocVerName());
                codeLableLst.add(codeLabelDto);
            }
            return codeLableLst;
        } catch (Exception e) {
            throw new BusinessException("查询数据库异常！", e);
        }
    }
    
    /**
     * 根据单证类型代码获取单证种类代码
     * @param docVerCode 单证类型代码
     * @return String 单证类型对应的单证种类代码
     * @author whj 20130922
     */
    public String getDocTypeCode(String docVerCode) throws BusinessException{
        try{
            return vcDocVersionInfoDao.getDocTypeCode(docVerCode);            
        }catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
    
    
    /**
     * 根据条件查询单证管控信息VC_DOC_VERSION_INFO_MNG
     * 
     * @param queryDto
     * @throws Exception
     * @author wanghuajian since 2014-4-8下午04:16:50
     */
    public List<VcDocVersionInfoMng> queryDocMngList(VcDocVersionInfoMng queryDto) throws BusinessException {
        try {
            return vcDocVersionInfoDao.queryDocMngList(queryDto);
        } catch (DaoException e) {
            throw new BusinessException("数据查询异常：" + e.getMessage());
        }
    }

	@Override
	public String getUrladdress(String codeCode, String CodeType) throws DaoException {
		VcPubCode vcPubCode =vcPubCodeManagerDao.getVcPubCode(codeCode, CodeType);
		return vcPubCode.getCodeEName();
	}
}
