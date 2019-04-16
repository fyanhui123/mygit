package com.tapi.tcs.vc.common.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.schema.model.VcPrintDocVersion;
import com.tapi.tcs.vc.schema.model.VcPubCode;
import com.tapi.tcs.vc.schema.model.VcPubCodeType;
import com.tapi.tcs.vc.baseinfo.dao.VcPrintDocVersionDao;

import com.tapi.tcs.vc.common.dao.VcPubCodeManagerDao;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.service.PubCodeManagerService;
import com.tapi.tcs.vc.common.vo.VcPubCodeQueryVo;

/**
 * 通用代码维护Service实现
 * <p>
 * Date: 2013-03-16
 * </p>
 * <p>
 * Module: 通用代码维护
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
public class PubCodeManagerServiceImpl implements PubCodeManagerService {

    private VcPubCodeManagerDao vcPubCodeManagerDao;

    public void setVcPubCodeManagerDao(VcPubCodeManagerDao vcPubCodeManagerDao) {
        this.vcPubCodeManagerDao = vcPubCodeManagerDao;
    }

    /**
     * 新增通用代码
     */
    public void createVcPubCodeType(VcPubCodeType vcPubCodeType, UserInfo userInfo) throws BusinessException {
        try {
            String msg = "";
            if (vcPubCodeType == null) {
                msg = "通用代码为空！";
            } else {
                if (StringUtils.isBlank(vcPubCodeType.getCodeType())) {
                    msg = "通用代码类型为空！";
                } else {
                    VcPubCodeQueryVo queryDto_code = new VcPubCodeQueryVo();
                    Page resustPage_code = null;
                    queryDto_code.setCodeTypeEQ(vcPubCodeType.getCodeType());
                    resustPage_code = vcPubCodeManagerDao.queryVcPubCodeTypes(queryDto_code, 1, 3);
                    if (resustPage_code.getResult().size() > 0) {
                        msg = "通用代码:" + vcPubCodeType.getCodeType() + "重复！";
                    }
                }
            }
            if (StringUtils.isNotBlank(msg)) {
                BusinessException be = new BusinessException(msg);
                be.setModule("通用代码_新增通用代码");
                throw be;
            } else {

                vcPubCodeType.setStatus("1");
                vcPubCodeType.setDateCreated(new Date());
                vcPubCodeType.setCreatedBy(userInfo.getUserCode());
                vcPubCodeType.setDateUpdated(new Date());
                vcPubCodeType.setUpdatedBy(userInfo.getUserCode());

                // 设置承印信息
                List<VcPubCode> vcPubCodeList = vcPubCodeType.getVcPubCodeList();
                if (vcPubCodeList != null && vcPubCodeList.size() > 0) {
                    for (VcPubCode VcPubCode : vcPubCodeList) {
                        VcPubCode.setStatus("1");
                        VcPubCode.setDateCreated(new Date());
                        VcPubCode.setCreatedBy(userInfo.getUserCode());
                        VcPubCode.setDateUpdated(new Date());
                        VcPubCode.setUpdatedBy(userInfo.getUserCode());
                        VcPubCode.setVcPubCodeType(vcPubCodeType);

                    }
                }
                vcPubCodeManagerDao.save(vcPubCodeType);

            }
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw e;
        }
    }

    /**
     * 更新通用代码
     */
    public void updateVcPubCodeType(VcPubCodeType vcPubCodeType, UserInfo userInfo) throws BusinessException {
        try {
            VcPubCodeType isExistDto = vcPubCodeManagerDao.getVcPubCodeType(vcPubCodeType
                    .getIdVcPubCodeType());
            // 若该通用代码为空，抛出异常
            if (isExistDto == null) {
                BusinessException be = new BusinessException("不存在通用代码对象！");
                be.setModule("通用代码_更新通用代码");
                throw be;
            }

            // 调用通用代码对应的dao进行更新操作
            isExistDto.setCodeTypeDesc(vcPubCodeType.getCodeTypeDesc());
            isExistDto.setDateUpdated(new Date());
            isExistDto.setUpdatedBy(userInfo.getUserCode());

            // 删除该代码类型的代码
            VcPubCode conditionDto = new VcPubCode();
            conditionDto.setCodeType(isExistDto.getCodeType());
            vcPubCodeManagerDao.deletePubCodeByConditions(conditionDto);

            // 设置代码信息
            List<VcPubCode> vcPubCodeList = vcPubCodeType.getVcPubCodeList();
            isExistDto.setVcPubCodeList(vcPubCodeList);
            if (vcPubCodeList != null && vcPubCodeList.size() > 0) {
                for (VcPubCode vcPubCodeTemp : vcPubCodeList) {
                    vcPubCodeTemp.setStatus("1");
                    vcPubCodeTemp.setDateCreated(new Date());
                    vcPubCodeTemp.setCreatedBy(userInfo.getUserCode());
                    vcPubCodeTemp.setDateUpdated(new Date());
                    vcPubCodeTemp.setUpdatedBy(userInfo.getUserCode());
                    vcPubCodeTemp.setVcPubCodeType(isExistDto);
                }
            }

            vcPubCodeManagerDao.update(isExistDto);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("数据库操作异常！", e);
        }

    }

    /**
     * 根据通用代码主键获取通用代码类
     */
    public VcPubCodeType getVcPubCodeType(Long idVcPubCodeType) throws BusinessException {
        if (idVcPubCodeType == null) {
            BusinessException be = new BusinessException("通用代码主键为空！");
            be.setModule("通用代码_根据主键获取通用代码");
            throw be;
        }
        try {
            return vcPubCodeManagerDao.getVcPubCodeType(idVcPubCodeType);
        } catch (Exception e) {
            throw new BusinessException("数据库操作异常！", e);
        }
    }

    /**
     * 查询通用代码类信息
     */
    public Page queryVcPubCodeTypeByPages(VcPubCodeQueryVo vcPubCodeQueryVo, int currentPage, int pageNumber)
            throws BusinessException {
        try {
            return vcPubCodeManagerDao.queryVcPubCodeTypes(vcPubCodeQueryVo, currentPage, pageNumber);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 删除、恢复通用代码
     */
    public String deleteOrUnDeleteVcPubCodeTypes(String idVcPubCodeTypes) throws BusinessException {
        String result = "操作成功！";
        try {
            if (StringUtils.isNotBlank(idVcPubCodeTypes)) {
                String[] arrIds = idVcPubCodeTypes.split(",");
                Long idVcPubCodeType = null;
                VcPubCodeType isExit = null;
                StringBuffer errMsg = new StringBuffer("");
                for (String strId : arrIds) {
                    idVcPubCodeType = new Long(strId);

                    // 调用通用代码对应的dao进行逻辑删除操作
                    isExit = vcPubCodeManagerDao.getVcPubCodeType(idVcPubCodeType);
                    if (isExit != null) {
                        if ("1".equals(isExit.getStatus())) {
                            isExit.setStatus("0");// 删除
                        } else {
                            isExit.setStatus("1");// 恢复
                        }
                        try {
                            vcPubCodeManagerDao.update(isExit);
                            errMsg.append("代码[" + isExit.getCodeType() + "]删除/恢复成功！\n");
                        } catch (Exception e) {
                            errMsg.append("代码[" + isExit.getCodeType() + "]删除/恢复失败！\n");
                        }
                    }
                }
                result = errMsg.toString();
            }
        } catch (Exception e) {
            throw new BusinessException("数据库操作异常！", e);
        }
        return result;
    }

    /**
     * @return 根据代码类型查询通用代码信息
     * @throws BusinessException
     * @author wanghuajian since 2013-4-13下午01:36:22
     */
    public List<VcPubCode> getVcPubCodeListByCodeType(String codeType) throws BusinessException {
        try {
            return vcPubCodeManagerDao.getVcPubCodeListByCodeType(codeType, null, null, null);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 通过codeType,codeCode翻译代码
     * 
     * @param codeType
     * @param codeCode
     * @return
     * @throws BusinessException
     * @author chy
     * @date 2013-04-24
     */
    public String getCodeCname(String codeType, String codeCode) throws BusinessException {
        String codeName = "";
        try {
            codeName = vcPubCodeManagerDao.getCodeCname(codeType, codeCode);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return codeName;
    }

    /**
     * 根据代码类型查询通用代码信息，并排序
     * 
     * @param codeType
     *            代码类型
     * @param orderParmNames
     *            排序字段名数组，只支持（idVcPubCode，codeCode，codeCName，codeEName）这几个字段
     * @param orderType
     *            （asc desc）
     * @param notContainCodeCodes
     *            不包含的业务代码 codes(String[])
     * @throws BusinessException
     * @author wanghuajian since 2013-4-13下午01:36:22
     */
    public List<VcPubCode> getVcPubCodeListByCodeTypeByOrder(String codeType, String[] orderParmNames,
            String orderType, String[] notContainCodeCodes) throws BusinessException {
        try {
            return vcPubCodeManagerDao.getVcPubCodeListByCodeType(codeType, orderParmNames, orderType,
                    notContainCodeCodes);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
