package com.tapi.tcs.vc.baseinfo.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.dao.VcPrintDocVersionDao;
import com.tapi.tcs.vc.baseinfo.service.VcPrintDocVersionService;
import com.tapi.tcs.vc.schema.model.VcPrintDocVersion;

/**
 * 承印单证版本Service实现
 * <p>
 * Date: 2013-03-16
 * </p>
 * <p>
 * Module: 承印单证版本
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
public class VcPrintDocVersionServiceImpl implements VcPrintDocVersionService {

    private VcPrintDocVersionDao vcPrintDocVersionDao;

    public void setVcPrintDocVersionDao(VcPrintDocVersionDao vcPrintDocVersionDao) {
        this.vcPrintDocVersionDao = vcPrintDocVersionDao;
    }

    /**
     * 新增承印单证版本
     */
    public void createVcPrintDocVersion(VcPrintDocVersion vcPrintDocVersion) throws BusinessException {
        String msg = "";
        if (vcPrintDocVersion == null) {
            msg = "承印单证版本信息为空！ ";
        }
        if (StringUtils.isNotBlank(msg)) {
            BusinessException be = new BusinessException(msg);
            be.setModule("承印单证版本_新增承印单证版本");
            throw be;
        } else {
            vcPrintDocVersion.setDateCreated(new Date());
            vcPrintDocVersion.setCreatedBy("Tianan");
            vcPrintDocVersion.setDateUpdated(new Date());
            vcPrintDocVersion.setUpdatedBy("Tianan");
            try {
                vcPrintDocVersionDao.save(vcPrintDocVersion);
            } catch (Exception e) {
                throw new BusinessException("数据新增失败！", e);
            }
        }
    }

    /**
     * 更新承印单证版本
     */
    public void updateVcPrintDocVersion(VcPrintDocVersion vcPrintDocVersion) throws BusinessException {
        VcPrintDocVersion isExistDto = vcPrintDocVersionDao.getVcPrintDocVersion(vcPrintDocVersion
                .getIdVcPrintDocVersion());
        // 若该承印单证版本为空，抛出异常
        if (isExistDto == null) {
            BusinessException be = new BusinessException("不存在承印单证版本！ ");
            be.setModule("承印单证版本_更新承印单证版本");
            throw be;
        }

        // 调用承印单证版本对应的dao进行更新操作
        // isExistDto.setIdVcPrintery(vcPrintDocVersion.getIdVcPrintery());
        isExistDto.setDocVerCode(vcPrintDocVersion.getDocVerCode());
        isExistDto.setUnitPrice(vcPrintDocVersion.getUnitPrice());
        // isExistDto.setDocName(vcPrintDocVersion.getDocName());
        // isExistDto.setIdVcDocVersionInfo(vcPrintDocVersion.getIdVcDocVersionInfo());

        isExistDto.setDateUpdated(new Date());
        isExistDto.setUpdatedBy("Tianan");
        try {
            vcPrintDocVersionDao.update(isExistDto);
        } catch (Exception e) {
            throw new BusinessException("数据更新失败！", e);
        }

    }

    /**
     * 根据承印单证版本主键获取承印单证版本
     * 
     * @param idVcPrintDocVersion
     *            主键
     * @return
     * @throws BusinessException
     */
    public VcPrintDocVersion getVcPrintDocVersion(Long idVcPrintDocVersion) throws BusinessException {
        if (idVcPrintDocVersion == null) {
            BusinessException be = new BusinessException("承印单证版本主键为空！ ");
            be.setModule("承印单证版本_根据主键获取承印单证版本");
            throw be;
        }
        try {
            return vcPrintDocVersionDao.getVcPrintDocVersion(idVcPrintDocVersion);
        } catch (Exception e) {
            throw new BusinessException("数据查找失败！", e);
        }
    }

    /**
     * 查询承印单证版本信息
     */
    public Page queryVcPrintDocVersionByPages(VcPrintDocVersion vcPrintDocVersion, int currentPage,
            int pageNumber) throws BusinessException {
        try {
            return vcPrintDocVersionDao.queryVcPrintDocVersions(vcPrintDocVersion, currentPage, pageNumber);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 删除承印单证版本
     */
    public void deleteVcPrintDocVersions(String idVcPrintDocVersions) throws BusinessException {

        String[] arrIds = idVcPrintDocVersions.split(",");
        Long idVcPrintDocVersion = null;
        try {
            for (String strId : arrIds) {
                idVcPrintDocVersion = new Long(strId);
                // 调用承印单证版本对应的dao进行删除操作
                vcPrintDocVersionDao.deleteByPK(idVcPrintDocVersion);
            }
        } catch (Exception e) {
            throw new BusinessException("数据删除失败！", e);
        }
    }

    /**
     * 根据印刷厂主键查找承印List
     * 
     * @param printeryId
     * @return map
     * @throws BusinessException
     * @author chy
     * @date 2013-05-20
     */
    public Map<String, String> findVcPrintDocVersionList(Long printeryId) throws BusinessException {
        Map<String, String> map = new HashMap<String, String>();
        try {
            List<VcPrintDocVersion> vcPrintDocVersionList = vcPrintDocVersionDao
                    .findVcPrintDocVersionList(printeryId);
            for (VcPrintDocVersion vcPrintDocVersion : vcPrintDocVersionList) {
                map.put(vcPrintDocVersion.getDocVerCode(), vcPrintDocVersion.getUnitPrice() + "");
            }
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return map;
    }

}
