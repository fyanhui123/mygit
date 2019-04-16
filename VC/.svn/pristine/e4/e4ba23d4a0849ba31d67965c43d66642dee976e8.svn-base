/**
 * 
 */
package com.tapi.tcs.vc.insucard.service;

import java.io.File;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.VcInsuCardImportInfo;
import com.tapi.tcs.vc.schema.model.VcInsuranceCard;

/**
 * @author Administrator
 * 
 */
public interface InsuCardImportService {

    /**
     * 根据ID获取去激活卡信息
     * 
     * @author wanghuajian
     * 
     * @param idVcInsuranceCard
     *            激活卡流水号
     * @return
     */
    public VcInsuranceCard getVcInsuranceCard(Long idVcInsuranceCard);

    /**
     * 根据ID获取激活卡导入信息
     * 
     * @author wanghuajian
     * 
     * @param idVcInsuCardImportInfo
     *            导入信息流水
     * @return VcInsuCardImportInfo
     */
    public VcInsuCardImportInfo getVcInsuCardImportInfo(Long idVcInsuCardImportInfo);

    /**
     * 查询激活卡信息
     * 
     * @author wanghuanjian
     * 
     * @param vcInsuranceCard
     *            查询条件dto
     * @param currentPage
     *            当前页码
     * @param pageNumber
     *            页面条数
     * @return Page
     * @exception BusinessException
     *                异常
     */
    public Page queryVcInsuranceCardByPages(VcInsuranceCard vcInsuranceCard, int currentPage, int pageNumber)
            throws BusinessException;
    
    
    /**
     * 根据卡号区间查询激活卡数目
     * @param startNo
     * @param endNo
     * @param docVerCode
     * @return Long
     @exception BusinessException
     *                异常
     */
    public Long queryVcInsuranceCardNumbers(String startNo, String endNo, String docVerCode)
            throws BusinessException;

    /**
     * 保存激活卡导入信息及其激活卡信息
     * 
     * @param vcInsuranceCard
     * @param userInfo
     *            当前用户
     * @author wanghuajian
     */
    public void saveInsuCardInfoAndInsuCard(VcInsuCardImportInfo vcInsuCardImportInfo, UserInfo userInfo)
            throws BusinessException;

    /**
     * 修改激活卡信息
     * 
     * @param vcInsuranceCard
     *            待修改vo
     * @param userInfo
     *            当前用户
     * @throws BusinessException
     *             异常
     */
    public void updateVcInsuranceCard(VcInsuranceCard vcInsuranceCard, UserInfo userInfo)
            throws BusinessException;
    
    /**
     * 
     * @param file 待上传文件
     * @param 单证类型代码
     * @param userInfo 当前用户
     * @return
     * @throws BusinessException
     */
   public String doUpLoadInsuranceCard(File file, String docVerCode,UserInfo userInfo)  throws BusinessException;

}
