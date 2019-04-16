package com.tapi.tcs.vc.common.service;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.vo.VcPubCodeQueryVo;
import com.tapi.tcs.vc.schema.model.VcPrintDocVersion;
import com.tapi.tcs.vc.schema.model.VcPubCode;
import com.tapi.tcs.vc.schema.model.VcPubCodeType;

/**
 *通用代码维护Service
 * <p>
 * Date: 2013-03-12
 * </p>
 * <p>
 * Module:通用代码维护
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
public interface PubCodeManagerService {

    /**
     * 新增通用代码
     */
    public void createVcPubCodeType(VcPubCodeType vcPubCodeType, UserInfo userInfo) throws BusinessException;

    /**
     * 更新通用代码
     */
    public void updateVcPubCodeType(VcPubCodeType vcPubCodeType, UserInfo userInfo) throws BusinessException;

    /**
     * 根据通用代码主键获取通用代码
     */
    public VcPubCodeType getVcPubCodeType(Long idVcPubCodeType) throws BusinessException;

    /**
     * 查询通用代码信息
     */
    public Page queryVcPubCodeTypeByPages(VcPubCodeQueryVo vcPubCodeQueryVo, int currentPage, int pageNumber)
            throws BusinessException;

    /**
     * 删除/恢复通用代码
     */
    public String deleteOrUnDeleteVcPubCodeTypes(String idVcPubCodeTypes) throws BusinessException;

    /**
     * @return 根据代码类型查询通用代码信息
     * @throws BusinessException
     * @author wanghuajian since 2013-4-13下午01:36:22
     */
    public List<VcPubCode> getVcPubCodeListByCodeType(String codeType) throws BusinessException;

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
    public String getCodeCname(String codeType, String codeCode) throws BusinessException;

    /**
     * 根据代码类型查询通用代码信息，并排序
     * 
     * @param codeType
     *            代码类型
     * @param orderParmNames
     *            排序字段名数组,只支持（idVcPubCode，codeCode，codeCName，codeEName）这几个字段
     * @param orderType
     *            （asc desc）
     * @param notContainCodeCodes
     *            不包含的业务代码 codes(String[])
     * @throws BusinessException
     * @author wanghuajian since 2013-4-13下午01:36:22
     */
    public List<VcPubCode> getVcPubCodeListByCodeTypeByOrder(String codeType, String[] orderParmNames,
            String orderType, String[] notContainCodeCodes) throws BusinessException;

}
