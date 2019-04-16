package com.tapi.tcs.vc.baseinfo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.vc.schema.model.VcDocType;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.baseinfo.dao.VcDocTypeDao;
import com.tapi.tcs.vc.baseinfo.dao.VcDocVersionInfoDao;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.baseinfo.service.VcDocTypeService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
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
public class VcDocTypeServiceImpl implements VcDocTypeService {

    private VcDocTypeDao vcDocTypeDao;

    // 单证类型
    private VcDocVersionInfoDao vcDocVersionInfoDao;

    // 机构、人员
    private VcLevelDao vcLevelDao;

    public void setVcDocTypeDao(VcDocTypeDao vcDocTypeDao) {
        this.vcDocTypeDao = vcDocTypeDao;
    }

    public void setVcDocVersionInfoDao(VcDocVersionInfoDao vcDocVersionInfoDao) {
        this.vcDocVersionInfoDao = vcDocVersionInfoDao;
    }

    public void setVcLevelDao(VcLevelDao vcLevelDao) {
        this.vcLevelDao = vcLevelDao;
    }

    /**
     * 新增单证种类
     */
    public void createVcDocType(VcDocType vcDocType, UserInfo userInfo) throws BusinessException {
        String msg = "";
        if (vcDocType == null) {
            msg = "单证种类信息为空！ ";
        } else {
            if (StringUtils.isBlank(vcDocType.getDocTypeCode())
                    || StringUtils.isBlank(vcDocType.getDocTypeName())) {
                msg = "单证种类代码/名称为空！ ";
            } else {
                VcDocType queryDto_code = new VcDocType();
                Page resustPage_code = null;
                queryDto_code.setDocTypeCode(vcDocType.getDocTypeCode());
                resustPage_code = vcDocTypeDao.queryVcDocTypes(queryDto_code, userInfo, 1, 5);
                if (resustPage_code.getResult().size() > 0) {
                    msg = "单证种类代码已存在！ ";
                }

                VcDocType queryDto_name = new VcDocType();
                Page resustPage_name = null;
                queryDto_name.setDocTypeNameEQ(vcDocType.getDocTypeName());
                resustPage_name = vcDocTypeDao.queryVcDocTypes(queryDto_name, userInfo, 1, 5);
                if (resustPage_name.getResult().size() > 0) {
                    msg = msg + "单证种类名称已存在！";
                }
            }
        }
        if (StringUtils.isNotBlank(msg)) {
            BusinessException be = new BusinessException(msg);
            be.setModule("单证种类_新增单证种类");
            throw be;
        } else {
            vcDocType.setStatus("1");
            vcDocType.setDateCreated(new Date());
            vcDocType.setCreatedBy(userInfo.getUserCode());
            vcDocType.setDateUpdated(new Date());
            vcDocType.setUpdatedBy(userInfo.getUserCode());
            try {
                vcDocTypeDao.save(vcDocType);
            } catch (Exception e) {
                throw new DaoException("查询数据库失败！");
            }
        }
    }

    /**
     * 更新单证种类
     */
    public void updateVcDocType(VcDocType vcDocType, UserInfo userInfo) throws BusinessException {

        VcDocType isExistDto = vcDocTypeDao.getVcDocType(vcDocType.getIdVcDocType());
        // 若该单证种类为空，抛出异常
        if (isExistDto == null) {
            BusinessException be = new BusinessException("不存在单证种类！");
            be.setModule("单证种类_更新单证种类");
            throw be;
        }

        /*
         * VcDocType queryDto_code =new VcDocType(); Page resustPage_code = null;
         * queryDto_code.setDocTypeCode(vcDocType.getDocTypeCode()); resustPage_code =
         * vcDocTypeDao.queryVcDocTypes(queryDto_code, 1, 5); if(resustPage_code.getResult().size()>1){
         * BusinessException be = new BusinessException("单证种类代码重复！"); be.setModule("单证种类_更新单证种类"); throw be; }
         */

        VcDocType queryDto_name = new VcDocType();
        Page resustPage_name = null;
        queryDto_name.setDocTypeNameEQ(vcDocType.getDocTypeName());
        try {
            resustPage_name = vcDocTypeDao.queryVcDocTypes(queryDto_name, userInfo, 1, 5);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
        // 重复数
        int num = 0;
        VcDocType tempVo = null;
        if (resustPage_name != null && resustPage_name.getResult() != null
                && resustPage_name.getResult().size() > 0) {
            for (int i = 0; i < resustPage_name.getResult().size(); i++) {
                tempVo = (VcDocType) resustPage_name.getResult().get(i);
                if (!tempVo.getIdVcDocType().equals(vcDocType.getIdVcDocType())) {
                    num++;
                }
            }
        }
        if (num > 0) {
            BusinessException be = new BusinessException("单证种类名称已存在，请重新设置！");
            be.setModule("单证种类_更新单证种类");
            throw be;
        }

        // 调用单证种类对应的dao进行更新操作
        isExistDto.setDocTypeName(vcDocType.getDocTypeName());
        isExistDto.setDateUpdated(new Date());
        isExistDto.setUpdatedBy(userInfo.getUserCode());
        try {
            vcDocTypeDao.update(isExistDto);
        } catch (Exception e) {
            throw new BusinessException("更新数据库失败！", e);
        }
    }

    /**
     * 根据单证种类主键获取单证种类
     */
    public VcDocType getVcDocType(Long idVcDocType) throws BusinessException {
        if (idVcDocType == null) {
            BusinessException be = new BusinessException("单证种类主键为空！ ");
            be.setModule("单证种类_根据主键获取单证种类");
            throw be;
        }
        try {
            VcDocType vcDocType = vcDocTypeDao.getVcDocType(idVcDocType);
            vcDocType.setCreatedUser(vcLevelDao.getUnitNameByUnitCode(vcDocType.getCreatedBy()));
            vcDocType.setUpdatedUser(vcLevelDao.getUnitNameByUnitCode(vcDocType.getCreatedBy()));
            return vcDocType;
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BusinessException("查询数据库失败！", e);
        }

    }

    /**
     * 查询单证种类信息
     */
    public Page queryVcDocTypeByPages(VcDocType vcDocType, UserInfo userInfo, int currentPage, int pageNumber)
            throws BusinessException {
        try {
            return vcDocTypeDao.queryVcDocTypes(vcDocType, userInfo, currentPage, pageNumber);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 注销、恢复单证种类
     */
    public String deleteOrUndeleteDocType(String ids) throws BusinessException {
        String result = "操作成功！ ";
        try {
            if (StringUtils.isNotBlank(ids)) {
                String[] arrIds = ids.split(",");
                Long idVcDocType = null;
                VcDocType isExitDocType = null;
                StringBuffer errMsg = new StringBuffer("");
                for (String strId : arrIds) {
                    idVcDocType = new Long(strId);
                    isExitDocType = vcDocTypeDao.get(idVcDocType);
                    if (isExitDocType != null) {
                        // 注销
                        if ("1".equals(isExitDocType.getStatus())) {
                            QueryRule queryRule = null;
                            try {
                                queryRule = QueryRule.getInstance();
                            } catch (Exception e) {
                                e.printStackTrace();
                                throw new DaoException("QueryRule拼接异常！ ", e);
                            }

                            queryRule.addEqual("idVcDocType", isExitDocType.getIdVcDocType());

                            List<VcDocVersionInfo> list = vcDocVersionInfoDao.find(queryRule);

                            /*
                             * 判断当前带删除的单证种类是否维护有单证版本，有则不允许删除， 系统同时给出提示：“存在单证种类：XXX（XXX为单证种类名称）的单证版本，不可删除该单证种类！”
                             */
                            if (list.size() > 0) {
                                errMsg.append("存在单证种类[" + isExitDocType.getDocTypeName()
                                        + "]的单证类型，不可注销该单证种类！\n");
                                continue;
                            } else {
                                isExitDocType.setStatus("0");

                            }
                        } else {
                            // 恢复
                            isExitDocType.setStatus("1");
                        }
                        try {
                            vcDocTypeDao.update(isExitDocType);
                            errMsg.append("单证种类[" + isExitDocType.getDocTypeName() + "]注销/恢复成功！\n");
                        } catch (Exception e) {
                            errMsg.append("单证种类[" + isExitDocType.getDocTypeName() + "]注销/恢复失败！\n");
                        }
                    }
                }
                result = errMsg.toString();
            }
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BusinessException("查询数据库失败！", e);
        }
        return result;
    }

    /**
     * 根据给定的条件查询
     */
    public List<VcDocType> getDocTypeList(Map<String, Object> map) throws BusinessException {
        try {
            return vcDocTypeDao.getDocTypeList(map);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<VcDocType> queryDocType(QueryRule queryRule) throws BusinessException {
        try {
            return vcDocTypeDao.find(queryRule);
        } catch (Exception e) {
            throw new BusinessException("查询数据库失败！", e);
        }
    }

}
