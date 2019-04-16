package com.tapi.tcs.vc.common.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.vo.VcPubCodeQueryVo;
import com.tapi.tcs.vc.schema.model.VcPubCode;
import com.tapi.tcs.vc.schema.model.VcPubCodeType;

/**
 * 通用代码DAO
 * <p>
 * Date 2013-04-16
 * </p>
 * <p>
 * Module：公共模块
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * 
 * @author wanghuajian
 * @version 1.0
 */
public interface VcPubCodeManagerDao extends GenericDao<VcPubCodeType> {

    /**
     * 根据ID获取通用代码对象
     * 
     * @author wanghuajian
     * 
     * @param idVcPubCodeType
     * @return
     */
    public VcPubCodeType getVcPubCodeType(Long idVcPubCodeType);

    /**
     * 查询通用代码信息
     * 
     * @author wanghuanjian
     * 
     * @param vcPubCodeType
     *            通用代码查询条件dto
     * @param pageNo
     *            当前页码
     * @param pageSize
     *            页面条数
     * @return
     */
    public Page queryVcPubCodeTypes(VcPubCodeQueryVo vcPubCodeQueryVo, int pageNo, int pageSize)
            throws DaoException;

    /**
     * 根据条件通用代码信息
     * 
     * @param conditionDto
     * @return
     * @throws Exception
     * @author wanghuajian since 2013-4-15下午04:40:55
     */
    public int deletePubCodeByConditions(VcPubCode conditionDto) throws DaoException;

    /**
     * 根据代码类型查询通用代码信息，并排序
     * 
     * @param codeType
     *            代码类型
     * @param orderParmNames
     *            排序字段名数组,只支持（idVcPubCode，codeCode，codeCName，codeEName）这几个字段
     * @param orderType
     *            （asc desc）不区分大小写
     * @param notContainCodeCodes
     *            不包含的业务代码codes(String[])
     * @throws BusinessException
     * @author wanghuajian since 2013-4-13下午01:36:22
     */
    public List<VcPubCode> getVcPubCodeListByCodeType(String codeType, String[] orderParmNames,
            String orderType, String[] notContainCodeCodes) throws DaoException;

    /**
     * 通过codeType,codeCode翻译代码
     * 
     * @param codeType
     * @param codeCode
     * @return
     * @throws DaoException
     * @author chy
     * @date 2013-04-24
     */
    public String getCodeCname(String codeType, String codeCode) throws DaoException;

    /**
     * 根据codetype和上级代码查找基础代码
     * 
     * @param codeType
     * @param upperCode
     * @return
     * @throws DaoException
     */
    public List<VcPubCode> getVcPubCodeList(String codeType, String upperCode) throws DaoException;
    
    /**
     *通过codeType、codeCode获取对应的PubCode对象
     * @param codeType
     * @param codeCode
     * @return
     * @throws DaoException
     *@author whj
     *@since May 6, 2015
     */
    public VcPubCode getVcPubCode(String codeType, String codeCode) throws DaoException;
}
