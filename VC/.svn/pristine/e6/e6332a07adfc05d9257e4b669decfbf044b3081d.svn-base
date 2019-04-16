package com.tapi.tcs.vc.baseinfo.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.schema.model.VcPrintDocVersion;
import com.tapi.tcs.vc.schema.model.VcPrintery;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.baseinfo.dao.VcPrintDocVersionDao;
import com.tapi.tcs.vc.baseinfo.dao.VcPrinteryDao;
import com.tapi.tcs.vc.baseinfo.service.VcPrinteryService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.SysConst;

/**
 * 印刷厂维护Service实现
 * <p>
 * Date: 2013-03-16
 * </p>
 * <p>
 * Module: 印刷厂维护
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
public class VcPrinteryServiceImpl implements VcPrinteryService {

    private VcPrinteryDao vcPrinteryDao;

    private VcPrintDocVersionDao vcPrintDocVersionDao;

    // 机构、人员
    private VcLevelDao vcLevelDao;

    public void setVcPrinteryDao(VcPrinteryDao vcPrinteryDao) {
        this.vcPrinteryDao = vcPrinteryDao;
    }

    public void setVcPrintDocVersionDao(VcPrintDocVersionDao vcPrintDocVersionDao) {
        this.vcPrintDocVersionDao = vcPrintDocVersionDao;
    }

    public void setVcLevelDao(VcLevelDao vcLevelDao) {
        this.vcLevelDao = vcLevelDao;
    }

    /**
     * 新增印刷厂
     * 
     * @param printery
     *            印刷厂信息
     * @param userInfo
     *            当前用户
     * @param file
     *            上传的文件
     * @throws BusinessException
     *             异常
     */
    public void createPrintery(VcPrintery printery, UserInfo userInfo, File file) throws BusinessException {
        String msg = "";
        if (printery == null) {
            msg = "印刷厂信息为空！";
        } else {
            if (StringUtils.isBlank(printery.getPrinteryCode())
                    || StringUtils.isBlank(printery.getPrinteryName())) {
                msg = "印刷厂代码/名称为空！  ";
            } else {
                VcPrintery queryDto_code = new VcPrintery();
                Page resustPage_code = null;
                queryDto_code.setPrinteryCode(printery.getPrinteryCode());
                resustPage_code = vcPrinteryDao.queryPrinterys(queryDto_code, 1, 5);
                if (resustPage_code.getResult().size() > 0) {
                    msg = "印刷厂代码重复！";
                }

                VcPrintery queryDto_name = new VcPrintery();
                Page resustPage_name = null;
                queryDto_name.setPrinteryNameEQ(printery.getPrinteryName());
                resustPage_name = vcPrinteryDao.queryPrinterys(queryDto_name, 1, 5);
                if (resustPage_name.getResult().size() > 0) {
                    msg = msg + "印刷厂名称重复！";
                }
            }
        }
        if (StringUtils.isNotBlank(msg)) {
            BusinessException be = new BusinessException(msg);
            be.setModule("印刷厂_新增印刷厂 ");
            throw be;
        } else {
            printery.setStatus("1");
            printery.setDateCreated(new Date());
            printery.setCreatedBy(userInfo.getComCode());
            printery.setDateUpdated(new Date());
            printery.setUpdatedBy(userInfo.getComCode());

            // 设置承印信息
            List<VcPrintDocVersion> printDocVersionList = printery.getPrintDocVersionList();
            if (printDocVersionList != null && printDocVersionList.size() > 0) {
                for (VcPrintDocVersion vcPrintDocVersion : printDocVersionList) {
                    // vcPrintDocVersion.setIdVcPrintery(printery.getIdVcPrintery());
                    vcPrintDocVersion.setDateCreated(new Date());
                    vcPrintDocVersion.setCreatedBy(userInfo.getComCode());
                    vcPrintDocVersion.setDateUpdated(new Date());
                    vcPrintDocVersion.setUpdatedBy(userInfo.getComCode());

                    vcPrintDocVersion.setVcPrintery(printery);
                    // vcPrintDocVersionDao.save(vcPrintDocVersion);
                }
            }
            try {
                vcPrinteryDao.save(printery);
            } catch (Exception e) {
                throw new BusinessException("数据保存失败！", e);
            }

            if (file != null) {
                File savefile = new File(new File(printery.getFilePath()), printery.getFileName());
                if (!savefile.getParentFile().exists()) {
                    savefile.getParentFile().mkdirs();
                }
                try {
                    FileUtils.copyFile(file, savefile);
                } catch (Exception e) {
                    throw new BusinessException("印刷合同扫描件上传失败！", e);
                }
            }

        }
    }

    /**
     * 更新印刷厂
     * 
     * @param printery
     *            印刷厂信息
     * @param userInfo
     *            当前用户
     * @param file
     *            上传文件
     * @throws Exception
     */
    public void updatePrintery(VcPrintery printery, UserInfo userInfo, File file) throws BusinessException {
        VcPrintery isExistDto = vcPrinteryDao.getPrintery(printery.getIdVcPrintery());
        // 若该印刷厂为空，抛出异常
        if (isExistDto == null) {
            BusinessException be = new BusinessException("不存在印刷厂！ ");
            be.setModule("印刷厂_更新印刷厂 ");
            throw be;
        }

        VcPrintery queryDto_name = new VcPrintery();
        Page resustPage_name = null;
        queryDto_name.setPrinteryNameEQ(printery.getPrinteryName());
        resustPage_name = vcPrinteryDao.queryPrinterys(queryDto_name, 1, 4);

        // 重复数
        int num = 0;
        VcPrintery tempVo = null;
        if (resustPage_name != null && resustPage_name.getResult() != null
                && resustPage_name.getResult().size() > 0) {
            for (int i = 0; i < resustPage_name.getResult().size(); i++) {
                tempVo = (VcPrintery) resustPage_name.getResult().get(i);
                if (!tempVo.getIdVcPrintery().equals(printery.getIdVcPrintery())) {
                    num++;
                }
            }
        }
        if (num > 0) {
            BusinessException be = new BusinessException("印刷厂名称重复！");
            be.setModule("印刷厂_更新印刷厂 ");
            throw be;
        }
        // 文件处理
        if (file != null) {
            File oldFile = new File(isExistDto.getFilePath() + "/" + isExistDto.getFileName());
            if (oldFile.isFile() && oldFile.exists()) {
                oldFile.delete();
            }
            File savefile = new File(new File(printery.getFilePath()), printery.getFileName());
            if (!savefile.getParentFile().exists()) {
                savefile.getParentFile().mkdirs();
            }
            try {
                FileUtils.copyFile(file, savefile);
            } catch (Exception e) {
                throw new BusinessException("印刷合同扫描件上传失败： " + e.getMessage());// 文件上传错误
            }
            // 扫描件名称
            isExistDto.setFileName(printery.getFileName());
            // 扫描件路径
            isExistDto.setFilePath(printery.getFilePath());
        }

        // 调用印刷厂对应的dao进行更新操作
        // isExistDto.setPrinteryCode(printery.getPrinteryCode());
        isExistDto.setPrinteryName(printery.getPrinteryName());
        isExistDto.setBusinCode(printery.getBusinCode());
        isExistDto.setBank(printery.getBank());
        isExistDto.setAccountCode(printery.getAccountCode());
        isExistDto.setCorresponder(printery.getCorresponder());
        isExistDto.setAccountName(printery.getAccountName());
        isExistDto.setAddress(printery.getAddress());
        isExistDto.setEmail(printery.getEmail());
        isExistDto.setFax(printery.getFax());
        isExistDto.setPostCode(printery.getPostCode());
        isExistDto.setTel(printery.getTel());
        isExistDto.setDateUpdated(new Date());
        isExistDto.setUpdatedBy(userInfo.getUserCode());
        isExistDto.setContractTimeLimit(printery.getContractTimeLimit());

        // 删除版本承印信息
        VcPrintDocVersion conditionDto = new VcPrintDocVersion();
        conditionDto.setIdVcPrintery(isExistDto.getIdVcPrintery());
        // 此步值得考虑
        isExistDto.setPrintDocVersionList(null);
        try {
            vcPrintDocVersionDao.deleteByConditions(conditionDto);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }

        // 设置承印信息
        List<VcPrintDocVersion> printDocVersionList = printery.getPrintDocVersionList();
        isExistDto.setPrintDocVersionList(printDocVersionList);
        if (printDocVersionList != null && printDocVersionList.size() > 0) {
            for (VcPrintDocVersion vcPrintDocVersion : printDocVersionList) {
                // vcPrintDocVersion.setIdVcPrintery(printery.getIdVcPrintery());
                vcPrintDocVersion.setDateCreated(new Date());
                vcPrintDocVersion.setCreatedBy(userInfo.getUserCode());
                vcPrintDocVersion.setDateUpdated(new Date());
                vcPrintDocVersion.setUpdatedBy(userInfo.getUserCode());

                vcPrintDocVersion.setVcPrintery(isExistDto);
                // vcPrintDocVersionDao.save(vcPrintDocVersion);
            }
        }
        try {
            vcPrinteryDao.update(isExistDto);
        } catch (Exception e) {
            throw new BusinessException("印刷合同扫描件上传失败！", e);
        }

    }

    /**
     * 根据印刷厂主键获取印刷厂
     */
    public VcPrintery getPrintery(Long idVcPrintery) throws BusinessException {
        if (idVcPrintery == null) {
            BusinessException be = new BusinessException("印刷厂主键为空！");
            be.setModule("印刷厂_根据主键获取印刷厂 ");
            throw be;
        }
        try {
            VcPrintery vcPrintery = vcPrinteryDao.getPrintery(idVcPrintery);
            vcPrintery.setCreatedUser(vcLevelDao.getUnitNameByUnitCode(vcPrintery.getCreatedBy()));
            vcPrintery.setUpdatedUser(vcLevelDao.getUnitNameByUnitCode(vcPrintery.getCreatedBy()));
            return vcPrintery;
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BusinessException("印刷合同扫描件上传失败！", e);
        }

    }

    /**
     * 查询印刷厂信息
     */
    public Page queryPrinteryByPages(VcPrintery printery, int currentPage, int pageNumber)
            throws BusinessException {
        try {
            return vcPrinteryDao.queryPrinterys(printery, currentPage, pageNumber);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 注销、恢复印刷厂
     */
    public String deleteOrUnDeletePrintery(String idVcPrinterys) throws BusinessException {
        String result = "操作成功！ ";
        if (StringUtils.isNotBlank(idVcPrinterys)) {
            String[] arrIds = idVcPrinterys.split(",");
            Long idPrintery = null;
            VcPrintery isExit = null;
            StringBuffer errMsg = new StringBuffer("");
            try {
                for (String strId : arrIds) {
                    idPrintery = new Long(strId);

                    // 调用印刷厂对应的dao进行逻辑删除操作
                    isExit = vcPrinteryDao.getPrintery(idPrintery);
                    if (isExit != null) {
                        if ("0".equals(isExit.getStatus())) {
                            isExit.setStatus("1");
                        } else {
                            isExit.setStatus("0");
                        }
                        // vcPrinteryDao.update(isExit);
                        try {
                            vcPrinteryDao.update(isExit);
                            errMsg.append("印刷厂[" + isExit.getPrinteryName() + "]注销/恢复成功！\n");
                        } catch (Exception e) {
                            errMsg.append("印刷厂[" + isExit.getPrinteryName() + "]注销/恢复失败！\n");
                        }
                    }

                    // vcPrinteryDao.deleteByPK(idPrintery);
                }
            } catch (Exception e) {
                throw new BusinessException("注销、恢复数据库操作失败！", e);
            }
            result = errMsg.toString();
        }
        return result;
    }

    /**
     * @return 查询印刷厂承印信息
     * @throws BusinessException
     * @author wanghuajian since 2013-4-13下午01:36:22
     */
    public List<VcPrintDocVersion> queryJsonPrtDocVersionListByPrinteryId(Long idVcPrintery)
            throws BusinessException {
        QueryRule queryRule = null;
        try {
            queryRule = QueryRule.getInstance();
        } catch (Exception e) {
            throw new BusinessException("QueryRule拼接异常！ ", e);
        }

        if (idVcPrintery != null) {
            queryRule.addEqual("idVcPrintery", idVcPrintery);

        }
        try {
            return vcPrintDocVersionDao.find(queryRule);
        } catch (Exception e) {
            throw new BusinessException("数据查询失败！ ", e);
        }
    }

    @Override
    public List<VcPrintery> queryPrintery(Map params) throws BusinessException {
        StringBuffer hql = new StringBuffer();
        List params_ = new ArrayList();
        hql.append(" from VcPrintery p where p.status = ?");
        params_.add("1");

        if (Beans.isNotEmpty(params.get("docVerCode"))) {
            hql.append(" and exists( select 1 from VcPrintDocVersion r ");
            hql.append(" where r.vcPrintery = p and r.docVerCode = ? )");
            params_.add(params.get("docVerCode"));
        }
        try {
            return vcPrinteryDao.findByHql(hql.toString(), params_.toArray());
        } catch (Exception e) {
            throw new BusinessException("数据查询失败！ ", e);
        }
    }

    @Override
    public String queryDocUnitPrice(String printeryCode, String docVerCode) throws BusinessException {
        if (Beans.isEmpty(docVerCode) || Beans.isEmpty(printeryCode)) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        hql.append("select r.unitPrice from VcPrintery p ,VcPrintDocVersion r ");
        hql.append("where p = r.vcPrintery ");
        hql.append("and p.printeryCode = ? ");
        hql.append("and r.docVerCode = ? ");
        List result = null;
        try {
        	result= vcPrinteryDao.findByHql(hql.toString(), printeryCode, docVerCode);
        } catch (Exception e) {
            throw new BusinessException("数据查询失败！ ", e);
        }
        if (result.size() > 0) {
            return result.get(0) + "";
        }
        return null;
    }

    /**
     * 查询印刷厂列表
     * 
     * @return
     * @throws Exception
     * @author chy
     * @date 2013-05-20
     */
    public List<VcPrintery> findVcPrinteryList() throws Exception {
        try {
            return vcPrinteryDao.findVcPrinteryList();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
