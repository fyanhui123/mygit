/**
 * 
 */
package com.tapi.tcs.vc.insucard.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.tapi.service.fin.common.dto.RequestHeadDTO;
import com.tapi.service.fin.common.dto.ResponseHeadDTO;
import com.tapi.service.fin.visa.intf.ActivationDetailDto;
import com.tapi.service.fin.visa.intf.PrePayFeeActCancelCardResponse;
import com.tapi.service.fin.visa.intf.PrePayFeeActCardRequest;
import com.tapi.service.fin.visa.intf.PrePayFeeActCardResponse;
import com.tapi.service.fin.visa.intf.PrePayFeeCancelActCardRequest;
import com.tapi.service.fin.visa.intf.RefundActCardRequest;
import com.tapi.service.fin.visa.intf.RefundActCardResponse;
import com.tapi.service.fin.visa.intf.VisaAccountToPayment;
import com.tapi.service.fin.visa.intf.VisaAccountToPaymentRequest;
import com.tapi.service.fin.visa.intf.VisaAccountToPaymentResponse;
import com.tapi.service.fin.visa.intf.VisaWebService;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.baseinfo.dao.VcTakerDao;
import com.tapi.tcs.vc.common.dao.TakeNoDao;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.insucard.dao.VcInsuCardSalesDetailDao;
import com.tapi.tcs.vc.insucard.dao.VcInsuCardSalesRecordDao;
import com.tapi.tcs.vc.insucard.dao.VcInsuranceCardDao;
import com.tapi.tcs.vc.insucard.dao.VcPayFeeAccountDao;
import com.tapi.tcs.vc.insucard.service.InsuCardSalesService;
import com.tapi.tcs.vc.insucard.vo.InsuCardSalesQueryVo;
import com.tapi.tcs.vc.schema.model.PubCode;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesDetail;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesRecord;
import com.tapi.tcs.vc.schema.model.VcInsuranceCard;
import com.tapi.tcs.vc.schema.model.VcPayFeeAccount;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.dao.VcAvailableDocDao;
import com.tapi.tcs.vc.store.dao.VcStorageDao;

/**
 * 激活卡销售serviceImpl
 * 
 * @author whj
 * 
 */
public class InsuCardSalesServiceImpl implements InsuCardSalesService {
    /**
     * 激活卡信息dao
     */
    private VcInsuranceCardDao vcInsuranceCardDao;

    /** 库存Dao */
    private VcStorageDao vcStorageDao;
    /** 单证销售Dao */
    private VcInsuCardSalesRecordDao vcInsuCardSalesRecordDao;
    /** 单证销售明细表Dao */
    private VcInsuCardSalesDetailDao vcInsuCardSalesDetailDao;
    /** 可使用单证Dao */
    private VcAvailableDocDao vcAvailableDocDao;
   
    /** 机构、岗位人员Dao */
    private VcLevelDao vcLevelDao;

    /** 单证使用人Dao */
    private VcTakerDao vcTakerDao;

    /** 单号生成Dao */
    private TakeNoDao takeNoDao;

    /** 激活卡信息送收复接口service */
    private VisaWebService visaWebService;

    /** 收付费账户接口service */
    private VcPayFeeAccountDao vcPayFeeAccountDao;
   

    /**
     * @param vcInsuranceCardDao
     *            the vcInsuranceCardDao to set
     */
    public void setVcInsuranceCardDao(VcInsuranceCardDao vcInsuranceCardDao) {
        this.vcInsuranceCardDao = vcInsuranceCardDao;
    }

    /**
     * @param vcStorageDao
     *            the vcStorageDao to set
     */
    public void setVcStorageDao(VcStorageDao vcStorageDao) {
        this.vcStorageDao = vcStorageDao;
    }

    /**
     * @param vcInsuCardSalesRecordDao
     *            the vcInsuCardSalesRecordDao to set
     */
    public void setVcInsuCardSalesRecordDao(VcInsuCardSalesRecordDao vcInsuCardSalesRecordDao) {
        this.vcInsuCardSalesRecordDao = vcInsuCardSalesRecordDao;
    }

    /**
     * @param vcInsuCardSalesDetailDao
     *            the vcInsuCardSalesDetailDao to set
     */
    public void setVcInsuCardSalesDetailDao(VcInsuCardSalesDetailDao vcInsuCardSalesDetailDao) {
        this.vcInsuCardSalesDetailDao = vcInsuCardSalesDetailDao;
    }

    /**
     * @param vcAvailableDocDao
     *            the vcAvailableDocDao to set
     */
    public void setVcAvailableDocDao(VcAvailableDocDao vcAvailableDocDao) {
        this.vcAvailableDocDao = vcAvailableDocDao;
    }

    /**
     * @param vcLevelDao
     *            the vcLevelDao to set
     */
    public void setVcLevelDao(VcLevelDao vcLevelDao) {
        this.vcLevelDao = vcLevelDao;
    }

    /**
     * @param vcTakerDao
     *            the vcTakerDao to set
     */
    public void setVcTakerDao(VcTakerDao vcTakerDao) {
        this.vcTakerDao = vcTakerDao;
    }

    /**
     * @param takeNoDao
     *            单号生成Dao
     */
    public void setTakeNoDao(TakeNoDao takeNoDao) {
        this.takeNoDao = takeNoDao;
    }

    /**
     * @param visaWebService
     *            激活卡信息送收复接口service
     */
    public void setVisaWebService(VisaWebService visaWebService) {
        this.visaWebService = visaWebService;
    }

    /**
     * @param vcPayFeeAccountDao
     *            the vcPayFeeAccountDao to set
     */
    public void setVcPayFeeAccountDao(VcPayFeeAccountDao vcPayFeeAccountDao) {
        this.vcPayFeeAccountDao = vcPayFeeAccountDao;
    }

    // /=======================================================================

    /**
     * 根据销售记录主表id获取其vo
     * 
     * @param id
     * @return
     */
    public VcInsuCardSalesRecord getVcInsuCardSalesRecord(Long id) throws BusinessException {
        try {
            VcInsuCardSalesRecord vcInsuCardSalesRecord = vcInsuCardSalesRecordDao.get(id);
            // 退费的银行账户信息
            if ("R".equals(vcInsuCardSalesRecord.getSaleRefundFlag())) {
                vcInsuCardSalesRecord.setVcPayFeeAccount(vcPayFeeAccountDao.getVcPayFeeAccountByBusinessId(id));
            }
            //delete by chy 20131010 退卡人名称从销售表中取 begin
            /*vcInsuCardSalesRecord
                    .setSellerName(vcTakerDao.getUnitNameByUnitCode(vcInsuCardSalesRecord.getSellerCode()));*/
            //delete by chy 20131010 退卡人名称从销售表中取 end
            vcInsuCardSalesRecord.setSaleOrgName(vcLevelDao.getUnitNameByUnitCode(vcInsuCardSalesRecord
                    .getSaleOrgCode()));
            //add by chy 20131025 begin
            //渠道类型
            List<PubCode> channelDetail = vcTakerDao.queryPubCodeList("UnderWriteChannel", vcInsuCardSalesRecord.getChannelDetailCode(), null);
            if(channelDetail!=null && channelDetail.size()!=0){
            	vcInsuCardSalesRecord.setChannelDetailName(channelDetail.get(0).getCodeCname());
            }
            //业务来源
            List<PubCode> businessSourceName = vcTakerDao.queryPubCodeList("BusinessSource", vcInsuCardSalesRecord.getBusinessSource(), null);
            if(businessSourceName!=null && businessSourceName.size()!=0){
            	vcInsuCardSalesRecord.setBusinessSourceName(businessSourceName.get(0).getCodeCname());
            }
            //add by chy 20131025 end
            return vcInsuCardSalesRecord;
        } catch (Exception e) {
            throw new BusinessException("数据库查询异常：" + e.getMessage(), e);
        }
    }

    /**
     * 根据销售记录号获取其vo
     * 
     * @param id
     * @return
     */
    public VcInsuCardSalesRecord getVcInsuCardSalesRecordByRecordCode(String salesRecordCode) throws BusinessException {
        if (StringUtils.isBlank(salesRecordCode)) {
            throw new BusinessException("销售记录号为空！");
        }
        List<VcInsuCardSalesRecord> list = null;
        try {
            QueryRule queryRule = QueryRule.getInstance();
            queryRule.addEqual("salesRecordCode", salesRecordCode);
            list = vcInsuCardSalesRecordDao.find(queryRule);
        } catch (Exception e) {
            throw new BusinessException("数据库查询异常：" + e.getMessage(), e);
        }

        if (list == null || list.size() == 0) {
            // throw new BusinessException("销售记录不存在！");
            return null;
        }
        if (list.size() > 1) {
            throw new DaoException("同一销售记录号对应多条销售记录！");
        }
        return list.get(0);
    }

    /**
     * 根据销售记录明细表id获取其vo
     * 
     * @param id
     * @return
     */
    public VcInsuCardSalesDetail getVcInsuCardSalesDetail(Long id) throws BusinessException {
        try {
            return vcInsuCardSalesDetailDao.get(id);
        } catch (Exception e) {
            throw new BusinessException("数据库查询异常：" + e.getMessage(), e);
        }
    }

    /**
     * 分页查询激活卡销售list
     * 
     * @param queryDto
     *            查询条件
     * @param userInfo
     *            当前用户
     * @param pageNo
     *            页号
     * @param pageSize
     *            每页记录数
     * @throws Exception
     */
    public Page queryInsuCardSalesRecordList(InsuCardSalesQueryVo queryDto, UserInfo userInfo, int pageNo, int pageSize)
            throws BusinessException {
        try {
            Page page = vcInsuCardSalesRecordDao.queryInsuCardSalesRecordList(queryDto, userInfo, pageNo, pageSize);
            List resultList = page.getResult();
            if (resultList != null && resultList.size() > 0) {
                VcInsuCardSalesRecord vcInsuCardSalesRecord = null;
                for (int i = 0; i < resultList.size(); i++) {
                    vcInsuCardSalesRecord = (VcInsuCardSalesRecord) resultList.get(i);
                    //delete by chy 20131010 退卡人名称从销售表中取 begin
                    /*vcInsuCardSalesRecord.setSellerName(vcTakerDao.getUnitNameByUnitCode(vcInsuCardSalesRecord
                            .getSellerCode()));*/
                    //delete by chy 20131010 退卡人名称从销售表中取 end
                    vcInsuCardSalesRecord.setSaleOrgName(vcLevelDao.getUnitNameByUnitCode(vcInsuCardSalesRecord
                            .getSaleOrgCode()));
                }
            }
            return page;
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
    
    /**
     * 激活卡销售、退卡状态修改
     * @param id 待更新记录id
     * @param changeToStatus 记录待修改的状态
     * @return
     * @throws BusinessException
     */
    public String updateSalesRecordStatus(Long id,String changeToStatus)throws BusinessException{
        try{
            VcInsuCardSalesRecord vcInsuCardSalesRecord = vcInsuCardSalesRecordDao.get(id);
            vcInsuCardSalesRecord.setSalesRecordStatus(changeToStatus);
            vcInsuCardSalesRecordDao.update(vcInsuCardSalesRecord);
        }catch (Exception e) {
            throw new BusinessException("更新操作异常：" + e.getMessage(), e);
        }
       
        return "success";
    }

    /**
     * 保存、修改激活卡销售
     * 
     * @param VcInsuCardSalesRecord
     *            激活卡销售信息
     * @param userInfo
     *            当前用户
     *@param type
     *            1-保存 2-提交
     *@param actionType
     *            add-新增 2-修改
     * @return String
     * @throws BusinessException
     */
    public String saveInsuCardSales(VcInsuCardSalesRecord vcInsuCardSalesRecord, UserInfo userInfo, String type,
            String actionType) throws BusinessException {
        try {
            // 销售详细信息
            List<VcInsuCardSalesDetail> vcInsuCardSalesDetailList = vcInsuCardSalesRecord
                    .getVcInsuCardSalesDetailList();

            VcInsuCardSalesRecord modifySalesRecord = null;
            if ("update".equals(actionType)) {
                modifySalesRecord = vcInsuCardSalesRecordDao.get(vcInsuCardSalesRecord.getIdVcInsuCardSalesRecord());
                if (modifySalesRecord == null) {
                    throw new BusinessException("记录不存在！");
                }
                /** 销售记录状态（0删除/1新建/2等待缴费/3缴费退回/4已缴费成功/5缴费失败/6销售撤销/7撤销失败） */
                if (!"1".equals(modifySalesRecord.getSalesRecordStatus())
                        && !"6".equals(modifySalesRecord.getSalesRecordStatus())) {
                    throw new BusinessException("只有【新建】和【销售撤销】的销售单可以修改，请刷新后重试");
                }
            }

            Date nowDate = new Date();

            //操作人
            String userCode = userInfo.getUserCode();
            //发放机构
            String provideOrgCode = userInfo.getComCode();

            // 校验数据
            if (vcInsuCardSalesDetailList == null || vcInsuCardSalesDetailList.size() == 0) {
                throw new BusinessException("请输入激活卡销售详细信息\n");
            }

            // 同一单证类型同一批次的号段是否有交叉重叠
            Map<String, Object> overLapResultMap = isOverLap(vcInsuCardSalesDetailList);
            if ((Boolean) overLapResultMap.get("isOverLap")) {
                throw new BusinessException((String) overLapResultMap.get("strMsg"));
            }

            // 校验流水号
            List checkResultList = checkData(vcInsuCardSalesRecord, vcInsuCardSalesDetailList, userInfo);
            List<String> checkResult = (List<String>) checkResultList.get(0);
            StringBuffer str = new StringBuffer();
            // 0.成功
            // 1.起始流水号大于终止流水号，请重新输入
            // 2.存在不可用单证状态
            // 3.未找到库存记录
            // 4.起始流水号段对应多条库存记录
            for (int i = 0; i < checkResult.size(); i++) {
                String resultFlag = checkResult.get(i);
                VcInsuCardSalesDetail detailVo = vcInsuCardSalesDetailList.get(i);
                String start = detailVo.getStartNum();
                String end = detailVo.getEndNum();
                if ("1".equals(resultFlag)) {
                    str.append("起始流水号[" + start + "]大于终止流水号[" + end + "]，请重新输入;\n");
                } else if ("2".equals(resultFlag)) {
                    str.append("流水号[" + start + "-" + end + "]存在不可用单证状态;\n");
                } else if ("3".equals(resultFlag)) {
                    str.append("流水号[" + start + "-" + end + "]未找到库存记录;\n");
                } else if ("4".equals(resultFlag)) {
                    str.append("库存记录异常：流水号[" + start + "-" + end + "]存在多个库存记录;\n");
                }else if ("6".equals(resultFlag)) {
                    str.append("部分卡号[" + start + "-" + end + "]在激活卡信息表中不存在;\n");
                }else if ("8".equals(resultFlag)) {
                    str.append("部分卡号[" + start + "-" + end + "]已过激活有效期或已激活;\n");
                }else if ("9".equals(resultFlag)) {
                    str.append("部分卡号[" + start + "-" + end + "]已过库存使用截止期，请回收上缴;\n");
                }
                
            }
            if (str.length() > 0) {
                throw new BusinessException(str.toString());
            }

            // 最后需要拆分的 vcStorage 信息 包括salesDet的 start、end和vcStorage对象
            List updateVcStorages = (List) checkResultList.get(1);

            if ("insert".equals(actionType)) {
                // 销售记录号
                String salesRecordCode = takeNoDao.getNo("CS");
                vcInsuCardSalesRecord.setSalesRecordCode(salesRecordCode);

                // 发放机构
                vcInsuCardSalesRecord.setProvideOrgCode(provideOrgCode);

                // 激活卡销售/退卡标志（P_销售 R-退卡）
                vcInsuCardSalesRecord.setSaleRefundFlag("P");
                // 销售说明
                vcInsuCardSalesRecord.setSaleReason("激活卡销售");
                vcInsuCardSalesRecord.setCreatedBy(userCode);
                vcInsuCardSalesRecord.setDateCreated(nowDate);
                // 销售日期
                vcInsuCardSalesRecord.setSaleDate(nowDate);
                // 缴费截止日期
                vcInsuCardSalesRecord.setPayEndDate(DateUtils.addDay(nowDate, 3)); // 待定
                //add by chy 20131025 如果是间接业务，需要把销售人代码（中介代码）赋值给agencyOrgCode
                if("2".equals(vcInsuCardSalesRecord.getBusinessMode())){
                	vcInsuCardSalesRecord.setAgencyOrgCode(vcInsuCardSalesRecord.getSellerCode());
                }
            } else if ("update".equals(actionType)) {
                // TODO删除已存在的子记录..
                vcInsuCardSalesDetailDao.deleteAll(modifySalesRecord.getVcInsuCardSalesDetailList());
                modifySalesRecord.setSumFee(vcInsuCardSalesRecord.getSumFee());
                vcInsuCardSalesRecord = modifySalesRecord;
            }
            // 修改人代码
            vcInsuCardSalesRecord.setModifyOprCode(userCode);
            // 修改时间
            vcInsuCardSalesRecord.setModifyTime(nowDate);

            vcInsuCardSalesRecord.setUpdatedBy(userCode);
            vcInsuCardSalesRecord.setDateUpdated(nowDate);

            for (Iterator iterator = vcInsuCardSalesDetailList.iterator(); iterator.hasNext();) {
                VcInsuCardSalesDetail detailTemp = (VcInsuCardSalesDetail) iterator.next();
                // detailTemp.setDocStatus(docStatus); //在checkData（）中设置
                detailTemp.setVcInsuCardSalesRecord(vcInsuCardSalesRecord);
                // 创建人
                detailTemp.setCreatedBy(vcInsuCardSalesRecord.getCreatedBy());
                // 创建时间
                detailTemp.setDateCreated(vcInsuCardSalesRecord.getDateCreated());
                // 修改人
                detailTemp.setUpdatedBy(vcInsuCardSalesRecord.getUpdatedBy());
                // 修改时间
                detailTemp.setDateUpdated(vcInsuCardSalesRecord.getDateUpdated());
            }
            vcInsuCardSalesRecord.setVcInsuCardSalesDetailList(vcInsuCardSalesDetailList);

            // 销售记录状态（0删除/1新建/2等待缴费/3缴费退回/4已缴费成功/5缴费失败/6销售撤销/7撤销失败/8等待退费）
            if ("1".equals(type)) { // 1-保存 2-提交
                // 保存
                vcInsuCardSalesRecord.setSalesRecordStatus("1");
            //modify by zhxiao3 VC-128贵州酒厂激活卡【保费为0方案】 begin    
            } else if(vcInsuCardSalesRecord.getSumFee() != 0) {
                // 提交
                vcInsuCardSalesRecord.setSalesRecordStatus("2");
            } else{
            	//销售额为0则直接下发激活卡，不送收付系统
            	vcInsuCardSalesRecord.setSalesRecordStatus("4");
            }
            //modify by zhxiao3 VC-128贵州酒厂激活卡【保费为0方案】 end
            if ("insert".equals(actionType)) {
                vcInsuCardSalesRecordDao.saveInsuCardSalesRecordAndDet(vcInsuCardSalesRecord);
            } else if ("update".equals(actionType)) {
                vcInsuCardSalesRecordDao.update(vcInsuCardSalesRecord);
            }

          //modify by zhxiao3 VC-128贵州酒厂激活卡【保费为0方案】 begin
            //销售额为'0'则不送收付直接下发激活卡
            if ("2".equals(type) && vcInsuCardSalesRecord.getSumFee() == 0 ){
            	
            	VcInsuranceCard queryDto = new VcInsuranceCard();
            	List<VcAvailableDoc> vcAvailableDocList = new ArrayList<VcAvailableDoc>();
            	
            	for (Iterator iterator = updateVcStorages.iterator(); iterator.hasNext();) {
                    List updateVcStorage = (List) iterator.next();
                    String start = updateVcStorage.get(0).toString();
                    String end = updateVcStorage.get(1).toString();
                    Long start_ = Long.valueOf(start);
                    Long end_ = Long.valueOf(end);
                    
                    //拆分并保存库存
                    VcStorage vcStorage = (VcStorage) updateVcStorage.get(2);
                    vcStorageDao.splitStorage(start, end, vcStorage.getDocVerCode(), vcStorage.getPressBatchNo(),
                            userInfo.getComCode(), vcStorage.getDocStatus(), null, null,userCode);
                    
                    //组装vcAvailableDoc数据
                    DecimalFormat df = new DecimalFormat("0");
                    df.setMinimumIntegerDigits(start.length());
                    VcAvailableDoc vcAvailableDoc = null;
                    for (Long i = start_; i <= end_; i++) {
                        vcAvailableDoc = new VcAvailableDoc();
                        vcAvailableDoc.setDocVerCode(vcStorage.getDocVerCode());// 单证类型代码
                        vcAvailableDoc.setDocNum(df.format(i));// 单证流水号
                        vcAvailableDoc.setPressBatchNo(vcStorage.getPressBatchNo());// 印刷批次
                        vcAvailableDoc.setDocStatus("A");// 单证状态
                        vcAvailableDoc.setTakerCode(vcInsuCardSalesRecord.getSellerCode());// 使用人代码
                        vcAvailableDoc.setOrgCode(vcInsuCardSalesRecord.getSaleOrgCode());// 使用人归属机构
                        vcAvailableDoc.setProvideTime(nowDate);// 发放时间
                        vcAvailableDoc.setDeadline(DateUtils.addYear(nowDate, 2));// 使用截止日期
                        vcAvailableDoc.setCreatedBy(vcInsuCardSalesRecord.getModifyOprCode());// 创建人
                        vcAvailableDoc.setDateCreated(nowDate);// 创建时间
                        vcAvailableDoc.setUpdatedBy(vcInsuCardSalesRecord.getModifyOprCode());// 修改人代码
                        vcAvailableDoc.setDateUpdated(nowDate);// 修改时间
                        vcAvailableDocList.add(vcAvailableDoc);
                    }

                    // 更新激活卡状态
                    queryDto.setDocVerCode(vcStorage.getDocVerCode());
                    queryDto.setStartCardNum(start);
                    queryDto.setEndCardNum(end);
                    
                    // 0-库存流转中 1-已付费待激活 20-已销售待付费 21-已退卡已领款 22--已退卡再付费待激活 23-已退卡待退费 9-激活成功
                    vcInsuranceCardDao.updateVcInsuranceCardByCondition(queryDto, "1");
                    // 保存 VC_AVAILABLE_DOC 数据
                    vcAvailableDocDao.saveAll(vcAvailableDocList);
                }
            }
            //modify by zhxiao3 VC-128贵州酒厂激活卡【保费为0方案】 end
            
            // 1.保存 2.提交（提交则缴费信息送收付）,销售额为0则不送收付
            if ("2".equals(type) && vcInsuCardSalesRecord.getSumFee() != 0) {
                PrePayFeeActCardResponse response = insucarPayFeeToPayment(vcInsuCardSalesRecord, userInfo);
                if (response == null || response.getHEAD() == null) {
                    throw new BusinessException("信息送收付处理返回信息为空！");
                }
                String[] result = dealResponseCode(response.getHEAD());
                // 缴费送收付成功
                if ("000".equals(result[0])) {
                    VcInsuranceCard queryDto = new VcInsuranceCard();
                    // 拆分库存销售冻结ST
                    for (Iterator iterator = updateVcStorages.iterator(); iterator.hasNext();) {
                        List updateVcStorage = (List) iterator.next();
                        String start = updateVcStorage.get(0).toString();
                        String end = updateVcStorage.get(1).toString();
                        VcStorage vcStorage = (VcStorage) updateVcStorage.get(2);
                        // vcStorageDao.splitStorage(start, end, null, null, null, null, "ST", vcStorage);
                        vcStorageDao.splitStorage(start, end, vcStorage.getDocVerCode(), vcStorage.getPressBatchNo(),
                                userInfo.getComCode(), vcStorage.getDocStatus(), "ST", null,userCode);

                        // 更新激活卡状态
                        queryDto.setDocVerCode(vcStorage.getDocVerCode());
                        queryDto.setStartCardNum(start);
                        queryDto.setEndCardNum(end);
                        // 0-库存流转中 1-已付费待激活 20-已销售待付费 21-已退卡已领款 22--已退卡再付费待激活 23-已退卡待退费 9-激活成功
                        vcInsuranceCardDao.updateVcInsuranceCardByCondition(queryDto, "20");
                    }
                } else {
                    throw new BusinessException(result[1]);
                }

            }
            return "操作成功";
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("操作异常：" + e.getMessage(), e);
        }
    }

    /**
     * 激活卡销售缴费提交(拆分库存为销售冻结状态，销售状态改为等待缴费)
     * 
     * @param vcInsuCardSalesRecord
     * @param userInfo
     * @return
     * @throws BusinessException
     */
    public String executeSubmitInsuCardSales(VcInsuCardSalesRecord vcInsuCardSalesRecord, UserInfo userInfo)
            throws BusinessException {
        if (vcInsuCardSalesRecord == null) {
            throw new BusinessException("记录不存在！");
        }
        /** 销售记录状态（0删除/1新建/2等待缴费/3缴费退回/4已缴费成功/5缴费失败/6销售撤销/7撤销失败/8等待退费） */
        if (!"1".equals(vcInsuCardSalesRecord.getSalesRecordStatus())
                && !"6".equals(vcInsuCardSalesRecord.getSalesRecordStatus())) {
            throw new BusinessException("只有【新建】和【销售撤销】的销售单可以提交，请刷新后重试");
        }

        Date nowDate = new Date();

        String userCode = userInfo.getUserCode();

        // 修改人代码
        vcInsuCardSalesRecord.setModifyOprCode(userCode);
        // 修改时间
        vcInsuCardSalesRecord.setModifyTime(nowDate);

        // 销售详细信息
        List<VcInsuCardSalesDetail> vcInsuCardSalesDetailList = vcInsuCardSalesRecord.getVcInsuCardSalesDetailList();
        // 校验数据
        if (vcInsuCardSalesDetailList == null || vcInsuCardSalesDetailList.size() == 0) {
            throw new BusinessException("激活卡销售详细信息为空\n");
        }

        // 同一单证类型同一批次的号段是否有交叉重叠
        Map<String, Object> overLapResultMap = isOverLap(vcInsuCardSalesDetailList);
        if ((Boolean) overLapResultMap.get("isOverLap")) {
            throw new BusinessException((String) overLapResultMap.get("strMsg"));
        }

        // 校验流水号
        List checkResultList = checkData(vcInsuCardSalesRecord, vcInsuCardSalesDetailList, userInfo);
        List<String> checkResult = (List<String>) checkResultList.get(0);
        StringBuffer str = new StringBuffer();
        // 0.成功
        // 1.起始流水号大于终止流水号，请重新输入
        // 2.存在不可用单证状态
        // 3.未找到库存记录
        // 4.起始流水号段对应多条库存记录
        for (int i = 0; i < checkResult.size(); i++) {
            String resultFlag = checkResult.get(i);
            VcInsuCardSalesDetail detailVo = vcInsuCardSalesDetailList.get(i);
            String start = detailVo.getStartNum();
            String end = detailVo.getEndNum();
            if ("1".equals(resultFlag)) {
                str.append("起始流水号[" + start + "]大于终止流水号[" + end + "];\n");
            } else if ("2".equals(resultFlag)) {
                str.append("流水号[" + start + "-" + end + "]存在不可用单证状态;\n");
            } else if ("3".equals(resultFlag)) {
                str.append("流水号[" + start + "-" + end + "]未找到库存记录;\n");
            } else if ("4".equals(resultFlag)) {
                str.append("库存记录异常：流水号[" + start + "-" + end + "]存在多个库存记录;\n");
            }else if ("6".equals(resultFlag)) {
                str.append("部分卡号[" + start + "-" + end + "]在激活卡信息表中不存在;\n");
            }else if ("8".equals(resultFlag)) {
                str.append("部分卡号[" + start + "-" + end + "]已过激活有效期或已激活;\n");
            }else if ("9".equals(resultFlag)) {
                str.append("部分卡号[" + start + "-" + end + "]已过库存使用截止期，请回收上缴;\n");
            }
        }
        if (str.length() > 0) {
            throw new BusinessException(str.toString());
        }

        // 最后需要拆分的 vcStorage 信息 包括salesDet的 start、end和vcStorage对象
        List updateVcStorages = (List) checkResultList.get(1);
        
        //modify by zhxiao3 VC-128贵州酒厂激活卡【保费为0方案】 begin
        // 销售记录状态（0删除/1新建/2等待缴费/3缴费退回/4已缴费成功/5缴费失败/6销售撤销/7撤销失败/8等待退费）
        if(vcInsuCardSalesRecord.getSumFee() !=0){
        	vcInsuCardSalesRecord.setSalesRecordStatus("2");
        }else{
        	vcInsuCardSalesRecord.setSalesRecordStatus("4");
        }
        //modify by zhxiao3 VC-128贵州酒厂激活卡【保费为0方案】 end
        // 销售日期
        vcInsuCardSalesRecord.setSaleDate(nowDate);

        try {
        	
        	//激活卡销售费用为0则不送收付
        	if(vcInsuCardSalesRecord.getSumFee() !=0){
        		// 付费信息送收付
        		PrePayFeeActCardResponse response = insucarPayFeeToPayment(vcInsuCardSalesRecord, userInfo);
        		if (response == null || response.getHEAD() == null) {
        			throw new BusinessException("信息送收付处理返回信息为空！");
        		}
        		String[] result = dealResponseCode(response.getHEAD());
        		// 付费信息送收付成功
        		if ("000".equals(result[0])) {
        			vcInsuCardSalesRecordDao.update(vcInsuCardSalesRecord);

        			VcInsuranceCard queryDto = new VcInsuranceCard();
        			for (Iterator iterator = updateVcStorages.iterator(); iterator.hasNext();) {
        				// 拆分库存,销售冻结ST；
        				List updateVcStorage = (List) iterator.next();
        				String start = updateVcStorage.get(0).toString();
        				String end = updateVcStorage.get(1).toString();
        				VcStorage vcStorage = (VcStorage) updateVcStorage.get(2);
        				vcStorageDao.splitStorage(start, end, vcStorage.getDocVerCode(), vcStorage.getPressBatchNo(),
        						userInfo.getComCode(), vcStorage.getDocStatus(), "ST", null,userCode);

        				// 更新激活卡状态为20-已销售待付费
        				queryDto.setDocVerCode(vcStorage.getDocVerCode());
        				queryDto.setStartCardNum(start);
        				queryDto.setEndCardNum(end);
        				// queryDto.setCardStatus("0");
        				// 0-库存流转中 1-已付费待激活 20-已销售待付费 21-已退卡已领款 22--已退卡再付费待激活 23-已退卡待退费 9-激活成功
        				vcInsuranceCardDao.updateVcInsuranceCardByCondition(queryDto, "20");
        			}
        		} else {
        			throw new BusinessException(result[1]);
        		}
            }else{
            	//modify by zhxiao3 VC-128贵州酒厂激活卡【保费为0方案】 begin
            	VcInsuranceCard queryDto = new VcInsuranceCard();
            	List<VcAvailableDoc> vcAvailableDocList = new ArrayList<VcAvailableDoc>();
            	
            	for (Iterator iterator = updateVcStorages.iterator(); iterator.hasNext();) {
                    List updateVcStorage = (List) iterator.next();
                    String start = updateVcStorage.get(0).toString();
                    String end = updateVcStorage.get(1).toString();
                    Long start_ = Long.valueOf(start);
                    Long end_ = Long.valueOf(end);
                    
                    //拆分并保存库存
                    VcStorage vcStorage = (VcStorage) updateVcStorage.get(2);
                    vcStorageDao.splitStorage(start, end, vcStorage.getDocVerCode(), vcStorage.getPressBatchNo(),
                            userInfo.getComCode(), vcStorage.getDocStatus(), null, null,userCode);
                    
                    //组装vcAvailableDoc数据
                    DecimalFormat df = new DecimalFormat("0");
                    df.setMinimumIntegerDigits(start.length());
                    VcAvailableDoc vcAvailableDoc = null;
                    for (Long i = start_; i <= end_; i++) {
                        vcAvailableDoc = new VcAvailableDoc();
                        vcAvailableDoc.setDocVerCode(vcStorage.getDocVerCode());// 单证类型代码
                        vcAvailableDoc.setDocNum(df.format(i));// 单证流水号
                        vcAvailableDoc.setPressBatchNo(vcStorage.getPressBatchNo());// 印刷批次
                        vcAvailableDoc.setDocStatus("A");// 单证状态
                        vcAvailableDoc.setTakerCode(vcInsuCardSalesRecord.getSellerCode());// 使用人代码
                        vcAvailableDoc.setOrgCode(vcInsuCardSalesRecord.getSaleOrgCode());// 使用人归属机构
                        vcAvailableDoc.setProvideTime(nowDate);// 发放时间
                        vcAvailableDoc.setDeadline(DateUtils.addYear(nowDate, 2));// 使用截止日期
                        vcAvailableDoc.setCreatedBy(vcInsuCardSalesRecord.getModifyOprCode());// 创建人
                        vcAvailableDoc.setDateCreated(nowDate);// 创建时间
                        vcAvailableDoc.setUpdatedBy(vcInsuCardSalesRecord.getModifyOprCode());// 修改人代码
                        vcAvailableDoc.setDateUpdated(nowDate);// 修改时间
                        vcAvailableDocList.add(vcAvailableDoc);
                    }
                    
                    // 更新激活卡状态
                    queryDto.setDocVerCode(vcStorage.getDocVerCode());
                    queryDto.setStartCardNum(start);
                    queryDto.setEndCardNum(end);
                    
                    // 0-库存流转中 1-已付费待激活 20-已销售待付费 21-已退卡已领款 22--已退卡再付费待激活 23-已退卡待退费 9-激活成功
                    vcInsuranceCardDao.updateVcInsuranceCardByCondition(queryDto, "1");
                    // 保存 VC_AVAILABLE_DOC 数据
                    vcAvailableDocDao.saveAll(vcAvailableDocList);
                    vcInsuCardSalesRecordDao.update(vcInsuCardSalesRecord);
                }
            }
        	//modify by zhxiao3 VC-128贵州酒厂激活卡【保费为0方案】 end
            return "操作成功";

        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("操作异常：" + e.getMessage(), e);
        }
    }

    /**
     * 收复responseCode处理
     * 
     * @param response
     * @return
     * @throws BusinessException
     */
    String[] dealResponseCode(ResponseHeadDTO head) throws BusinessException {
        String[] result = new String[2];
        if (head.getResponseCode() == null) {
            throw new BusinessException("信息送收付处理返回信息为空！");
        }
        // 000 处理完成 101 数据格式错误 102 重复请求流水号 103 数据不存在 104 请求正在处理 999 处理失败 105 异常失败
        String responseCode = head.getResponseCode();
        result[0] = responseCode;
        if ("000".equals(responseCode)) {
            result[1] = "成功";
        } else {
            if ("104".equals(responseCode)) {
                result[1] = "104【请求正在处理】";
            } else if ("101".equals(responseCode)) {
                result[1] = "101信息送收付处理异常【数据格式错误】";
            } else if ("102".equals(responseCode)) {
                result[1] = "102信息送收付处理异常【重复请求流水号】";
            } else if ("103".equals(responseCode)) {
                result[1] = "103信息送收付处理异常【数据不存在】";
            } else if ("999".equals(responseCode)) {
                result[1] = "999信息送收付处理异常【处理失败】";
            } else if ("105".equals(responseCode)) {
                result[1] = "105信息送收付处理异常【异常失败】";
            } else {
                result[1] = "信息送收付处理异常【无效处理代码】";
            }
            if (StringUtils.isNotBlank(head.getResponseMessage())) {
                result[1] = result[1] + "【" + head.getResponseMessage() + "】";
            }

        }

        return result;
    }

    /**
     * 激活卡付费信息送收复
     */
    private PrePayFeeActCardResponse insucarPayFeeToPayment(VcInsuCardSalesRecord vcInsuCardSalesRecord,
            UserInfo userInfo) throws BusinessException {
        String sn = UUID.randomUUID().toString().replace("-", "");
        PrePayFeeActCardRequest request = new PrePayFeeActCardRequest();
        // 请求头
        RequestHeadDTO head = new RequestHeadDTO();
        head.setUser("VC"); // 用户名
        head.setPassword("VC"); // 密码
        head.setSn(sn);
        head.setSystemCode("07"); // 01 承保 02 理赔 03 销管 04 资金 05 费控 06收付 07单证
        head.setBusinessNo(vcInsuCardSalesRecord.getSalesRecordCode()); //业务号（可不设置）

        request.setHEAD(head);

        request.setBusinessNo(vcInsuCardSalesRecord.getSalesRecordCode());
        //modify by chy 20131010 销售人名称保存在激活卡销售表中 begin
        /*String payeeName = vcTakerDao.getUnitNameByUnitCode(vcInsuCardSalesRecord.getSellerCode());
        if (StringUtils.isBlank(payeeName)) {
            throw new BusinessException("付款人名称为空！");
        }
        request.setPayeeName(payeeName);*/
        request.setPayeeName(vcInsuCardSalesRecord.getSellerName());
        //modify by chy 20131010 销售人名称保存在激活卡销售表中 end
        request.setPayeeCode(vcInsuCardSalesRecord.getSellerCode());
        // 0-投保人，1-业务员，2-代理人，3-再保人
        request.setPayeeType("1");
        request.setCurrency("CNY");

        request.setApplyDate(new Date());
        //业务归属机构取销售人的归属机构
        request.setCompanyCode(vcInsuCardSalesRecord.getSaleOrgCode());
        request.setPayEndDate(vcInsuCardSalesRecord.getPayEndDate());

        List<VcInsuCardSalesDetail> vcInsuCardSalesDetailList = vcInsuCardSalesRecord.getVcInsuCardSalesDetailList();

        List<ActivationDetailDto> activationDetailList = request.getActivationDetailList();
        ActivationDetailDto tempDto = null;
        // 格式化流水号为10位
        DecimalFormat df = new DecimalFormat("0");        
        double totalFee = 0d;
        for (Iterator iterator = vcInsuCardSalesDetailList.iterator(); iterator.hasNext();) {
            VcInsuCardSalesDetail detailTemp = (VcInsuCardSalesDetail) iterator.next();
            String startNum = detailTemp.getStartNum();
            String endNum = detailTemp.getEndNum();

            Long start = Long.valueOf(startNum);
            Long end = Long.valueOf(endNum);
           
          //将Long类型的卡号编号格式化
            df.setMinimumIntegerDigits(startNum.length());
            for (Long k = start; k <= end; k++) {
                tempDto = new ActivationDetailDto();
                tempDto.setCardNo(df.format(k));
               // tempDto.setAmount(value);
             // 获取面值
                VcInsuranceCard feeQueryDto=new VcInsuranceCard();                
                //feeQueryDto.setStartCardNum(tempDto.getCardNo());
               // feeQueryDto.setEndCardNum(tempDto.getCardNo());
                feeQueryDto.setCardNo(tempDto.getCardNo());
                feeQueryDto.setDocVerCode(detailTemp.getDocVerCode());
                List<Object> sumNumList = vcInsuranceCardDao.getCardTotalFeeNumByCondition(feeQueryDto,null,null);
                double fee =(Double) sumNumList.get(0);
                double countNum =(Long) sumNumList.get(1);
                if(countNum!=1){
                 // 6.激活卡信息表中对应激活卡不存在
                 throw new BusinessException("激活卡信息表中对应激活卡【"+ tempDto.getCardNo()+ "】不存在！");
                }             
                tempDto.setAmount(fee);
                activationDetailList.add(tempDto);
            }
           // totalFee += value * (end - start + 1);
        }
        //request.setPayFee(totalFee);
        request.setPayFee(vcInsuCardSalesRecord.getSumFee());
        PrePayFeeActCardResponse response = visaWebService.prePayFeeActCard(request);
        return response;
    }

    /**
     * 缴费成功后 1:将库存中的销售冻结（ST）记录删除并在可使用表中新增 2：更新激活卡的状态 3：更新激活卡销售状态
     * 
     * @param idVcInsuCardSalesRecord
     *            销售记录主键id
     * @param operater
     *            操作用户信息
     * @return
     * @throws BusinessException
     */
    public String executeAfterPayFeeSuccess(Long idVcInsuCardSalesRecord, UserInfo operater) throws BusinessException {
        try {
            VcInsuCardSalesRecord vcInsuCardSalesRecord = vcInsuCardSalesRecordDao
                    .getVcInsuCardSalesRecord(idVcInsuCardSalesRecord);
            if (vcInsuCardSalesRecord == null) {
                throw new BusinessException("销售记录不存在");
            }

            List<VcInsuCardSalesDetail> vcInsuCardSalesDetailList = vcInsuCardSalesRecord
                    .getVcInsuCardSalesDetailList();
            if (vcInsuCardSalesDetailList == null || vcInsuCardSalesDetailList.size() < 1) {
                throw new BusinessException("销售子表信息不存在");
            }
            Date nowDate = new Date();

            // 将销售冻结的库存删除并将此记录插入可使用表中
            VcStorage vcStorage = null;
            // 待删除的库存中的销售冻结记录list
            List<VcStorage> toDeleteVcStorageList = new ArrayList<VcStorage>();
            // 新增的可使用记录List
            List<VcAvailableDoc> vcAvailableDocList = new ArrayList<VcAvailableDoc>();
            // 更新激活卡状态用查询条件
            VcInsuranceCard queryDto = new VcInsuranceCard();
            for (VcInsuCardSalesDetail detailTemp : vcInsuCardSalesDetailList) {
                String startNum = detailTemp.getStartNum();
                String endNum = detailTemp.getEndNum();
                Long start = Long.valueOf(startNum);
                Long end = Long.valueOf(endNum);
                String docStatus = "ST";
                vcStorage = vcStorageDao.fullEqual(startNum, endNum, detailTemp.getDocVerCode(), detailTemp
                        .getPressBatchNo(), docStatus, vcInsuCardSalesRecord.getProvideOrgCode());
                if (vcStorage == null) {
                    throw new BusinessException("销售冻结状态的单证【" + detailTemp.getDocVerCode() + "】【" + startNum + "~~"
                            + endNum + "】在库存中不存在 ");
                }
                toDeleteVcStorageList.add(vcStorage);

                // 保存 VC_AVAILABLE_DOC 数据

                DecimalFormat df = new DecimalFormat("0");
                df.setMinimumIntegerDigits(startNum.length());
                VcAvailableDoc vcAvailableDoc = null;
                for (Long i = start; i <= end; i++) {
                    vcAvailableDoc = new VcAvailableDoc();
                    // 单证类型代码
                    vcAvailableDoc.setDocVerCode(detailTemp.getDocVerCode());
                    // 单证种类代码 数据库字段已删除
                    // vcAvailableDoc.setDocTypeCode(vcDocVersionInfoDao.getDocTypeCode(vcDocTakerIo.getDocVerCode()));
                    // 单证流水号
                    vcAvailableDoc.setDocNum(df.format(i));
                    // 印刷批次
                    vcAvailableDoc.setPressBatchNo(detailTemp.getPressBatchNo());
                    // 单证状态
                    vcAvailableDoc.setDocStatus("A");
                    // 使用人代码
                    vcAvailableDoc.setTakerCode(vcInsuCardSalesRecord.getSellerCode());
                    // 使用人归属机构
                    vcAvailableDoc.setOrgCode(vcInsuCardSalesRecord.getSaleOrgCode());
                    // 发放时间
                    vcAvailableDoc.setProvideTime(nowDate);
                    // 使用截止日期
                    vcAvailableDoc.setDeadline(DateUtils.addYear(nowDate, 2));
                    // 创建人
                    vcAvailableDoc.setCreatedBy(vcInsuCardSalesRecord.getModifyOprCode());
                    // 创建时间
                    vcAvailableDoc.setDateCreated(nowDate);
                    // 修改人代码
                    vcAvailableDoc.setUpdatedBy(vcInsuCardSalesRecord.getModifyOprCode());
                    // 修改时间
                    vcAvailableDoc.setDateUpdated(nowDate);
                    vcAvailableDocList.add(vcAvailableDoc);
                }

                // 更新激活卡状态
                queryDto.setDocVerCode(detailTemp.getDocVerCode());
                queryDto.setStartCardNum(detailTemp.getStartNum());
                queryDto.setEndCardNum(detailTemp.getEndNum());
                queryDto.setCardStatus("1");
                // 0-库存流转中 1-已付费待激活 20-已销售待付费 21-已退卡已领款 22--已退卡再付费待激活 23-已退卡待退费 9-激活成功
                vcInsuranceCardDao.updateVcInsuranceCardByCondition(queryDto, "1");
            }

            // 保存 VC_AVAILABLE_DOC 数据
            vcAvailableDocDao.saveAll(vcAvailableDocList);
            // 删除的库存中的销售冻结记录
            vcStorageDao.deleteAll(toDeleteVcStorageList);
            // 更新销售记录状态
            // 销售记录状态（0删除/1新建/2等待缴费/3缴费退回/4已缴费成功/5缴费失败/6销售撤销/7撤销失败/8等待退费）
            vcInsuCardSalesRecord.setSalesRecordStatus("4");
            //modify by zhxiao3 ITEA-50394激活卡销售成功后审计字段"更新时间"未更新  begin
            vcInsuCardSalesRecord.setDateUpdated(nowDate);
            //modify by zhxiao3 ITEA-50394激活卡销售成功后审计字段"更新时间"未更新  end
            vcInsuCardSalesRecordDao.update(vcInsuCardSalesRecord);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("操作异常：" + e.getMessage(), e);
        }
        return "success";
    }

    /**
     * 
     * 退费处理成功后，变更申请状态，变更激活卡状态，增加库存，单证状态变更为在库存
     * 
     * @param idVcInsuCardSalesRecord
     *            销售记录主键id
     * @operater 操作人信息
     * @return
     * @throws BusinessException
     */
    public String executeAfterRefundSuccess(Long idVcInsuCardSalesRecord, UserInfo operater) throws BusinessException {
        try {
            VcInsuCardSalesRecord vcInsuCardSalesRecord = vcInsuCardSalesRecordDao
                    .getVcInsuCardSalesRecord(idVcInsuCardSalesRecord);
            if (vcInsuCardSalesRecord == null) {
                throw new BusinessException("退卡记录不存在");
            }

            List<VcInsuCardSalesDetail> vcInsuCardSalesDetailList = vcInsuCardSalesRecord
                    .getVcInsuCardSalesDetailList();
            if (vcInsuCardSalesDetailList == null || vcInsuCardSalesDetailList.size() < 1) {
                throw new BusinessException("退卡记录子表信息不存在");
            }

            StringBuffer errorMsg = new StringBuffer();
            List mergeVcStorageAllList = new ArrayList();
            for (VcInsuCardSalesDetail detailTemp : vcInsuCardSalesDetailList) {
                String startNum = detailTemp.getStartNum();
                String endNum = detailTemp.getEndNum();
                Long start = Long.valueOf(startNum);
                Long end = Long.valueOf(endNum);
                // 判断流水对应库存是否有记录
                String sql = " SELECT COUNT(t.ID_VC_STORAGE) FROM VC_STORAGE t " + " WHERE t.doc_ver_code = ? "
                        + " AND t.press_batch_no = ? " + " AND t.doc_status IN ('S1','S2','S3') "
                        + " AND((? BETWEEN T.START_NUM AND T.END_NUM)" + " OR (T.START_NUM BETWEEN ? AND  ? ))";

                // 判断起止流水号对应库存信息有没有对应的记录
                Object[] objs = { detailTemp.getDocVerCode(), detailTemp.getPressBatchNo(), startNum, startNum, endNum };
                int amount = Integer.valueOf(vcStorageDao.findBySQL(sql, objs).get(0).toString());
                if (amount > 0) {
                    errorMsg.append("激活卡[" + startNum + "-" + endNum + "]对应库存有记录，数据错误!\n");
                }

                // 校验可使用表的激活卡数量是否等于销售记录的数量
                QueryRule queryRule = QueryRule.getInstance();
                queryRule.addEqual("docStatus", "JT");
                Object[] values = { startNum, endNum };
                queryRule.addBetween("docNum", values);
                queryRule.addEqual("docVerCode", detailTemp.getDocVerCode());
                queryRule.addEqual("pressBatchNo", detailTemp.getPressBatchNo());
                //queryRule.addEqual("takerCode", vcInsuCardSalesRecord.getSellerCode());
                queryRule.addEqual("orgCode", vcInsuCardSalesRecord.getSaleOrgCode());
                List<VcAvailableDoc> vcAvailableDocList = vcAvailableDocDao.find(queryRule);
                // 判断库存是否存在
                int amount_ = vcAvailableDocList.size();
                if (end - start + 1 == amount_) {
                    // 增加VC_STORAGE库存记录
                    List<Object> mergeVcStorage = new ArrayList<Object>();
                    mergeVcStorage.add(detailTemp);
                    mergeVcStorage.add(vcAvailableDocList);
                    mergeVcStorageAllList.add(mergeVcStorage);
                } else {
                    errorMsg.append("操作失败，单证状态不对或单证不存在 ");
                }
            }

            if (errorMsg.length() > 0) {
                throw new BusinessException(errorMsg.toString());
            }
            // 激活卡发放机构
            String provideOrgCode = vcInsuCardSalesRecord.getProvideOrgCode();
            // 可使用表记录合并到库存表并删除可使用表记录
            mergeVcStorage(mergeVcStorageAllList, provideOrgCode, operater.getUserCode());

            // 更新销售记录状态
            // 记录状态（0删除/1新建/2等待缴费/4已缴费成功/5缴费失败/6销售撤销/7撤销失败 // 21退卡新增/22等待退费/23退费成功/24退费失败(账户原因)/25退费失败(非账户原因)）
            vcInsuCardSalesRecord.setSalesRecordStatus("23");
            //modify by zhxiao3 ITEA-50394激活卡销售成功后审计字段"更新时间"未更新  begin
            Date nowDate = new Date();
            vcInsuCardSalesRecord.setDateUpdated(nowDate);
            //modify by zhxiao3 ITEA-50394激活卡销售成功后审计字段"更新时间"未更新  end
            vcInsuCardSalesRecordDao.update(vcInsuCardSalesRecord);

            // 更新激活卡状态用查询条件
            VcInsuranceCard queryDto = new VcInsuranceCard();
            for (VcInsuCardSalesDetail detailTemp : vcInsuCardSalesDetailList) {
                // 更新激活卡状态
                queryDto.setDocVerCode(detailTemp.getDocVerCode());
                queryDto.setStartCardNum(detailTemp.getStartNum());
                queryDto.setEndCardNum(detailTemp.getEndNum());
                // queryDto.setCardStatus("1");
                // 0-库存流转中 1-已付费待激活 20-已销售待付费 21-已退卡已领款 22--已退卡再付费待激活 23-已退卡待退费 9-激活成功
                vcInsuranceCardDao.updateVcInsuranceCardByCondition(queryDto, "21");
            }
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("操作异常：" + e.getMessage(), e);
        }
        return "success";
    }

    /**
     * 退费处理失败分两种情况： 1.账户原因引起的失败，直接将退费单状态改为【账户原因退费失败】 2.非账户原因引起的失败， 变更回收申请状态为【退卡失败】，同时将可使用表冻结的卡状态还原，还原激活卡状态为【1-已付费待激活】
     * 
     * @param idVcInsuCardSalesRecord
     *            销售记录主键id
     * @param failReason
     *            退费失败原因代码 (01-账号不存在或账号停用 09-其他非账户原因)
     * @param failDesc
     *            退费失败详述
     * @param operater
     *            操作用户信息
     * @return
     * @throws BusinessException
     */
    public String executeAfterRefundFailed(Long idVcInsuCardSalesRecord, String failReason, String failDesc,
            UserInfo operater) throws BusinessException {
        try {
            VcInsuCardSalesRecord vcInsuCardSalesRecord = vcInsuCardSalesRecordDao
                    .getVcInsuCardSalesRecord(idVcInsuCardSalesRecord);
            if (vcInsuCardSalesRecord == null) {
                throw new BusinessException("退卡记录不存在");
            }
            // 账户原因引起的失败
            if ("01".equals(failReason)) {
                // 21退卡新增/22等待退费/23退费成功/24退费失败(账户原因)/25退费失败(非账户原因)
                vcInsuCardSalesRecord.setSalesRecordStatus("24");

                VcPayFeeAccount bankInfo = vcPayFeeAccountDao.getVcPayFeeAccountByBusinessId(idVcInsuCardSalesRecord);
                bankInfo.setMemo(failDesc);
                vcPayFeeAccountDao.save(bankInfo);
            } else {
                // 非账户原因引起的失败

                List<VcInsuCardSalesDetail> vcInsuCardSalesDetailList = vcInsuCardSalesRecord
                        .getVcInsuCardSalesDetailList();
                if (vcInsuCardSalesDetailList == null || vcInsuCardSalesDetailList.size() < 1) {
                    throw new BusinessException("退卡子表信息不存在");
                }
                // 可使用表单证冻结状态还原
                List<Object> values = new ArrayList<Object>();
                StringBuffer sb = new StringBuffer("update vc_available_doc a  ");
                sb.append("set doc_Status=? ");
                sb.append("where a.DOC_VER_CODE=? and a.DOC_NUM between ? and ?");
                String updateSql = sb.toString();
                VcInsuranceCard queryDto = new VcInsuranceCard();
                for (VcInsuCardSalesDetail detail : vcInsuCardSalesDetailList) {
                    values.clear();
                    values.add("A");// 激活卡退卡冻结还原
                    values.add(detail.getDocVerCode());
                    values.add(detail.getStartNum());
                    values.add(detail.getEndNum());
                    vcAvailableDocDao.updateBySql(updateSql, values.toArray());

                    // 更新激活卡状态为23-已退卡待退费
                    queryDto.setDocVerCode(detail.getDocVerCode());
                    queryDto.setStartCardNum(detail.getStartNum());
                    queryDto.setEndCardNum(detail.getEndNum());
                    // 0-库存流转中 1-已付费待激活 20-已销售待付费 21-已退卡已领款 22--已退卡再付费待激活 23-已退卡待退费 9-激活成功
                    vcInsuranceCardDao.updateVcInsuranceCardByCondition(queryDto, "1");
                }
                // 21退卡新增/22等待退费/23退费成功/24退费失败(账户原因)/25退费失败(非账户原因)
                vcInsuCardSalesRecord.setSalesRecordStatus("25");
            }
            vcInsuCardSalesRecordDao.update(vcInsuCardSalesRecord);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("操作异常：" + e.getMessage(), e);
        }
        return "success";
    }

    /**
     * 可使用表的记录合并到库存记录
     * 
     * @param mergeVcStorageAllList
     *            [0-销售详细表 1-销售详细表对应的可使用表中的激活卡]
     * @param provideOrgCode
     *            激活卡发放机构
     * @param curOprCode
     *            当前操作人code
     */
    public void mergeVcStorage(List mergeVcStorageAllList, String provideOrgCode, String curOprCode)
            throws BusinessException {
        // 需要删除的VcAvailableDoc
        List<VcAvailableDoc> toDeletevcAvailableDocList = new ArrayList<VcAvailableDoc>();

        try {
            for (Iterator iterator = mergeVcStorageAllList.iterator(); iterator.hasNext();) {
                List mergeVcStorage = (List) iterator.next();
                // toDeletevcAvailableDocList.addAll((Collection<? extends VcAvailableDoc>) mergeVcStorage.get(1));
                toDeletevcAvailableDocList.addAll((List<VcAvailableDoc>) mergeVcStorage.get(1));
                VcInsuCardSalesDetail salesDetail = (VcInsuCardSalesDetail) mergeVcStorage.get(0);
                // vcDocTakerIos.add(vcDocTakerIo);
                String start = salesDetail.getStartNum();
                String end = salesDetail.getEndNum();

                // 合并S3的库存
                /*
                 * vcStorageDao.mergeStorage(start, end, vcDocTakerIo.getDocVerCode(), vcDocTakerIo.getPressBatchNo(),
                 * vcDocTakerIo.getAcceptOrgCode(), "S3", null, vcDocTakerIo.getOprCode());
                 */
                vcStorageDao.mergeStorage(start, end, salesDetail.getDocVerCode(), salesDetail.getPressBatchNo(),
                        provideOrgCode, "S3", null, curOprCode,null);
            }
            vcAvailableDocDao.deleteAll(toDeletevcAvailableDocList);

        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 根据VcInsuCardSalesDetail.StartNum升序排序
     * 
     * @author whj
     * 
     */
    class SortByStartNum implements Comparator<VcInsuCardSalesDetail> {
        /**
         * 自定义排序比较方法
         * 
         * @param det1
         *            VcInsuCardSalesDetail
         * @param det2
         *            VcInsuCardSalesDetail
         * @return
         */
        public int compare(VcInsuCardSalesDetail det1, VcInsuCardSalesDetail det2) {
            Long start1 = Long.valueOf(det1.getStartNum());
            Long start2 = Long.valueOf(det2.getStartNum());
            if (start1 > start2) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    /**
     * 同一单证类型同一批次的号段是否有交叉重叠
     * 
     * @param salesDets
     *            销售子表list
     * @return Map<String,Object> [isOverLap(Boolean)-是否有交叉重叠 ; strMsg(String)-交叉重叠的提示信息]
     */
    Map<String, Object> isOverLap(List<VcInsuCardSalesDetail> salesDets) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        boolean isOverLap = false;
        StringBuffer strMsg = new StringBuffer();

        // 按起始流水号递增排序
        Collections.sort(salesDets, new SortByStartNum());
        // 同一单证类型的激活卡号区间是否有重叠
        Map<String, Long> map = new HashMap<String, Long>();
        Map<String, String> mapStr = new HashMap<String, String>();

        String startStr = null;
        String endStr = null;
        // key前缀
        String preKey = null;
        for (VcInsuCardSalesDetail det : salesDets) {
            startStr = det.getStartNum();
            endStr = det.getEndNum();
            // 单证类型代码+印刷批次作为key前缀
            preKey = det.getDocVerCode() + det.getPressBatchNo();

            Long start = Long.valueOf(startStr);
            Long end = Long.valueOf(endStr);

            if (map.get(preKey + "Star") == null) {
                map.put(preKey + "Star", -1L);
                map.put(preKey + "End", -1L);
            } else {
                // 有交叉重叠
                if ((Long) map.get(preKey + "End") > start) {
                    isOverLap = true;
                    strMsg.append("流水号区间[" + startStr + "-" + endStr + "]");
                    strMsg.append("与区间[" + mapStr.get(preKey + "Star") + "-" + mapStr.get(preKey + "End") + "]有重叠；\n");
                }
            }
            map.put(preKey + "Star", start);
            map.put(preKey + "End", end);
            mapStr.put(preKey + "Star", startStr);
            mapStr.put(preKey + "End", endStr);
        }
        resultMap.put("isOverLap", isOverLap);
        resultMap.put("strMsg", strMsg.toString());
        return resultMap;

    }

    /**
     * 
     * @param salesRecord
     * @param salesDets
     * @param userInfo当前用户信息
     * @return List(0) 0.成功 1.起始流水号大于终止流水号，请重新输入 2.存在不可用单证状态 3.未找到库存记录
     */
    private List checkData(VcInsuCardSalesRecord salesRecord, List<VcInsuCardSalesDetail> salesDets, UserInfo userInfo)
            throws BusinessException {
        List resultList = new ArrayList();
        try {
            List updateVcStorages = new ArrayList();

            // 0.成功
            // 1.起始流水号大于终止流水号，请重新输入
            // 2.存在不可用单证状态
            // 3.未找到库存记录
            List<String> result = new ArrayList<String>();
            // 获取面值查询用Dto
            VcInsuranceCard feeQueryDto = new VcInsuranceCard();
            double totalFee = 0d;
            for (int i = 0; i < salesDets.size(); i++) {
                VcInsuCardSalesDetail saleDet = salesDets.get(i);

                String startStr = saleDet.getStartNum();
                String endStr = saleDet.getEndNum();

                // 用户可能输入10位或16位 需要截取
                Long start = Long.valueOf(startStr);
                Long end = Long.valueOf(endStr);

                if (start > end) {
                    // 1. 起始流水号大于终止流水号，请重新输入
                    result.add("1");
                    continue;
                }
                // 如果单证状态为“库存（含S1、S2、S3）”，
                // 则：从VC_STORAGE（单证库存表）里使用如下查询条件进行查询：
                // 单证类型代码＋印刷批次＋遗失号段的所有单证流水号＋当前操作员归属机构＋单证状态（＝S1、S2或S3）
                // 当前操作员归属机构 待完善

                String[] parms = { "S1", "S2", "S3" };
                List<VcStorage> vcStorageList = vcStorageDao.isExistAll(startStr, endStr, saleDet.getDocVerCode(),
                        saleDet.getPressBatchNo(), userInfo.getComCode(), false, parms);

                if (vcStorageList.size() == 0) {
                    // 3.未找到库存记录
                    result.add("3");
                    continue;
                } else if (vcStorageList.size() > 1) {
                    // 4.数据库记录异常
                    result.add("4");
                    continue;
                }
                
                //判断拆分号段是否已过使用截止期
                if(DateUtils.compare(DateUtils.reset(vcStorageList.get(0).getDeadline()), DateUtils.reset(new Date())) < 0){
                	// 9.拆分号段使用截止期已过
                    result.add("9");
                    continue;
                	
                }
                
                // 获取费用
                feeQueryDto.setStartCardNum(startStr);
                feeQueryDto.setEndCardNum(endStr);
                feeQueryDto.setDocVerCode(saleDet.getDocVerCode());
                List<Object> sumNumList = vcInsuranceCardDao.getCardTotalFeeNumByCondition(feeQueryDto,null,null);
                double fee =(Double) sumNumList.get(0);
                Long countNum =(Long) sumNumList.get(1);
                if(countNum!=(end - start + 1)){
                 // 6.激活卡信息表中对应激活卡不存在
                    result.add("6");
                    continue;
                }
                saleDet.setFee(fee);
                // 总费用
                totalFee += fee;
                
                //激活卡未激活未过期的数目   
                //0——库存流转中/ 1——已付费待激活/20——已销售待付费/21——已退卡已领款/22——已退卡再付费待激活/23——已退卡待退费/9——激活成功
                String[] arrStatus={"0","21"};
                List<Object> sumNumList2 = vcInsuranceCardDao.getCardTotalFeeNumByCondition(feeQueryDto,false,arrStatus);
                Long countNum2 =(Long) sumNumList2.get(1);
                if(countNum2<(end - start + 1)){
                    // 8.激活卡过期或已激活
                       result.add("8");
                       continue;
                 }
                // 数量
                saleDet.setSaleNum(end - start + 1);
                // 销售前状态
                saleDet.setDocStatus(vcStorageList.get(0).getDocStatus());
                List ids = new ArrayList();

                for (Iterator iterator = vcStorageList.iterator(); iterator.hasNext();) {
                    VcStorage vcStorage = (VcStorage) iterator.next();
                    ids.add(vcStorage.getId());
                }
                // 锁表
                vcStorageDao.lockVcStorage(ids.toArray());
                List<Object> updateVcStorage = new ArrayList<Object>();

                updateVcStorage.add(startStr);
                updateVcStorage.add(endStr);
                updateVcStorage.add(vcStorageList.get(0));
                updateVcStorages.add(updateVcStorage);
                result.add("0");
            }
            salesRecord.setSumFee(totalFee);

            resultList.add(result);
            resultList.add(updateVcStorages);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BusinessException("操作失败！", e);
        }
        return resultList;
    }

    /**
     * 激活卡缴费申请撤销(库存为销售冻结状态还原为库存S3，销售状态改为6销售撤销,激活卡状态：)
     * 
     * @param vcInsuCardSalesRecord
     * @param userInfo
     * @return
     * @throws BusinessException
     */
    public String executeCancelPayFee(Long idVcInsuCardSalesRecord, UserInfo userInfo) throws BusinessException {
        VcInsuCardSalesRecord vcInsuCardSalesRecord = vcInsuCardSalesRecordDao
                .getVcInsuCardSalesRecord(idVcInsuCardSalesRecord);
        if (vcInsuCardSalesRecord == null) {
            throw new BusinessException("销售记录不存在");
        }

        List<VcInsuCardSalesDetail> vcInsuCardSalesDetailList = vcInsuCardSalesRecord.getVcInsuCardSalesDetailList();
        if (vcInsuCardSalesDetailList == null || vcInsuCardSalesDetailList.size() < 1) {
            throw new BusinessException("销售子表信息不存在");
        }

        PrePayFeeActCancelCardResponse response = cancelPayFeeToPayment(vcInsuCardSalesRecord, userInfo);
        if (response == null || response.getHEAD() == null) {
            throw new BusinessException("信息送收付处理返回信息为空！");
        }
        String[] result = dealResponseCode(response.getHEAD());
        if ("000".equals(result[0])) {
            VcStorage vcStorage = null;
            VcInsuranceCard queryDto = new VcInsuranceCard();
            for (VcInsuCardSalesDetail detailTemp : vcInsuCardSalesDetailList) {
                // step1:销售冻结的数据是否存在
                String startNum = detailTemp.getStartNum();
                String endNum = detailTemp.getEndNum();
                String docStatus = "ST";
                vcStorage = vcStorageDao.fullEqual(startNum, endNum, detailTemp.getDocVerCode(), detailTemp
                        .getPressBatchNo(), docStatus, vcInsuCardSalesRecord.getProvideOrgCode());
                if (vcStorage == null) {
                    throw new BusinessException("销售冻结状态的单证【" + detailTemp.getDocVerCode() + "】【" + startNum + "~~"
                            + endNum + "】在库存中不存在 ");
                }
                vcStorageDao.mergeStorage(startNum, endNum, vcStorage.getDocVerCode(), vcStorage.getPressBatchNo(),
                        vcStorage.getOrgCode(), "ST", "S3", userInfo.getUserCode(),vcStorage.getDeadline());

                // 更新激活卡状态
                queryDto.setDocVerCode(vcStorage.getDocVerCode());
                queryDto.setStartCardNum(startNum);
                queryDto.setEndCardNum(endNum);
                // 0-库存流转中 1-已付费待激活 20-已销售待付费 21-已退卡已领款 22--已退卡再付费待激活 23-已退卡待退费 9-激活成功
                vcInsuranceCardDao.updateVcInsuranceCardByCondition(queryDto, "0");
            }
            vcInsuCardSalesRecord.setSalesRecordStatus("6");
            vcInsuCardSalesRecord.setVcInsuCardSalesDetailList(null);
            vcInsuCardSalesRecordDao.update(vcInsuCardSalesRecord);
        } else {
            throw new BusinessException(result[1]);
        }
        return "";

    }

    /**
     * 缴费申请撤销送收复PrePayFeeCancelActCardRequest insucarPayFeeToPayment
     */
    public PrePayFeeActCancelCardResponse cancelPayFeeToPayment(VcInsuCardSalesRecord vcInsuCardSalesRecord,
            UserInfo userInfo) throws BusinessException {
        String sn = UUID.randomUUID().toString().replace("-", "");
        PrePayFeeCancelActCardRequest request = new PrePayFeeCancelActCardRequest();
        // 请求头
        RequestHeadDTO head = new RequestHeadDTO();
        head.setUser("VC");
        head.setPassword("VC");
         // 业务号（可不设置）
        head.setBusinessNo(vcInsuCardSalesRecord.getSalesRecordCode());

        // SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
        // head.setSn(sdf.format(new Date()));

        head.setSn(sn);

        // 01 承保 02 理赔 03 销管 04 资金 05 费控 06收付 07单证
        head.setSystemCode("07");
        request.setHEAD(head);
        // 业务号
        request.setBusinessNo(vcInsuCardSalesRecord.getSalesRecordCode());

        PrePayFeeActCancelCardResponse response = visaWebService.prePayFeeCancelActCard(request);
        
        return response;
    }

    /**
     * 保存激活卡退卡
     * 
     * @param VcInsuCardSalesRecord
     *            激活卡退卡信息
     * @param bankInfo
     *            退费人账户信息
     * @param userInfo
     *            当前用户
     *@param type
     *            1-保存 2-提交
     *@param actionType
     *            add-新增 2-修改
     */
    public String saveInsuCardRefund(VcInsuCardSalesRecord vcInsuCardSalesRecord, VcPayFeeAccount bankInfo,
            UserInfo userInfo, String type, String actionType) throws BusinessException {
        try {
            // 销售详细信息
            List<VcInsuCardSalesDetail> vcInsuCardSalesDetailList = vcInsuCardSalesRecord
                    .getVcInsuCardSalesDetailList();

            VcInsuCardSalesRecord modifySalesRecord = null;
            VcPayFeeAccount modifyBankInfo = null;
            if ("update".equals(actionType)) {
                modifySalesRecord = vcInsuCardSalesRecordDao.get(vcInsuCardSalesRecord.getIdVcInsuCardSalesRecord());
                if (modifySalesRecord == null) {
                    throw new BusinessException("记录不存在！");
                }
                /** 销售记录状态（0删除/1新建/2等待缴费/3缴费退回/4已缴费成功/5缴费失败/6销售撤销/7撤销失败/8等待退费） */
                /** 退卡记录状态（20退卡删除/21退卡新增/22等待退费/23退费成功） */
                if (!"21".equals(modifySalesRecord.getSalesRecordStatus())) {
                    throw new BusinessException("只有【新建】的退卡单可以修改，请刷新后重试");
                }

                modifyBankInfo = vcPayFeeAccountDao.getVcPayFeeAccountByBusinessId(vcInsuCardSalesRecord
                        .getIdVcInsuCardSalesRecord());
                if (modifyBankInfo == null) {
                    throw new BusinessException("退卡记录对应的银行账户信息不存在！");
                }
            }

            Date nowDate = new Date();

            String userCode = userInfo.getUserCode();
      

            // 校验数据
            if (vcInsuCardSalesDetailList == null || vcInsuCardSalesDetailList.size() == 0) {
                throw new BusinessException("请输入激活卡退卡详细信息\n");
            }

            // 同一单证类型同一批次的号段是否有交叉重叠
            Map<String, Object> overLapResultMap = isOverLap(vcInsuCardSalesDetailList);
            if ((Boolean) overLapResultMap.get("isOverLap")) {
                throw new BusinessException((String) overLapResultMap.get("strMsg"));
            }

            // 校验流水号
            List checkResultList = checkAvailableData(vcInsuCardSalesRecord, vcInsuCardSalesDetailList, userInfo);
            List<String> checkResult = (List<String>) checkResultList.get(0);
            StringBuffer str = new StringBuffer();
            // 0.成功
            // 1.起始流水号大于终止流水号，请重新输入
            // 2.存在不可用单证状态
            // 3.未找到库存记录
            // 4.起始流水号段对应多条库存记录
            for (int i = 0; i < checkResult.size(); i++) {
                String resultFlag = checkResult.get(i);
                VcInsuCardSalesDetail detailVo = vcInsuCardSalesDetailList.get(i);
                String start = detailVo.getStartNum();
                String end = detailVo.getEndNum();
                if ("1".equals(resultFlag)) {
                    str.append("起始流水号[" + start + "]大于终止流水号[" + end + "]，请重新输入;\n");
                } else if ("2".equals(resultFlag)) {
                    str.append("可使用表处于区间[" + start + "-" + end + "]的部分激活卡不存在;\n");
                } else if ("4".equals(resultFlag)) {
                    str.append("可使用表卡号处于区间[" + start + "-" + end + "]记录超过实际值;\n");
                }else if ("6".equals(resultFlag)) {
                    str.append("部分卡号[" + start + "-" + end + "]在激活卡信息表中不存在;\n");
                }
            }
            if (str.length() > 0) {
                throw new BusinessException(str.toString());
            }

            if ("insert".equals(actionType)) {
                // 销售记录号
                String salesRecordCode = takeNoDao.getNo("CR");
                vcInsuCardSalesRecord.setSalesRecordCode(salesRecordCode);

                // 退卡机构
                vcInsuCardSalesRecord.setProvideOrgCode(userInfo.getComCode());

                // 激活卡销售/退卡标志（P_销售 R-退卡）
                vcInsuCardSalesRecord.setSaleRefundFlag("R");
                // 销售说明
                vcInsuCardSalesRecord.setSaleReason("激活卡退卡");
                vcInsuCardSalesRecord.setCreatedBy(userCode);
                vcInsuCardSalesRecord.setDateCreated(nowDate);
                // 销售日期
                vcInsuCardSalesRecord.setSaleDate(nowDate);
                // 缴费截止日期
                vcInsuCardSalesRecord.setPayEndDate(DateUtils.addDay(nowDate, 3)); // 待定
                //add by chy 20131025 如果是间接业务，需要把销售人代码（中介代码）赋值给agencyOrgCode
                if("2".equals(vcInsuCardSalesRecord.getBusinessMode())){
                	vcInsuCardSalesRecord.setAgencyOrgCode(vcInsuCardSalesRecord.getSellerCode());
                }
            } else if ("update".equals(actionType)) {
                // TODO删除已存在的子记录..
                vcInsuCardSalesDetailDao.deleteAll(modifySalesRecord.getVcInsuCardSalesDetailList());
                modifySalesRecord.setVcInsuCardSalesDetailList(null);// 此操作必须，不然出现奇怪问题
                // 删除银行账户信息..
                vcPayFeeAccountDao.delete(modifyBankInfo);

                vcInsuCardSalesRecord = modifySalesRecord;
            }
            // 修改人代码
            vcInsuCardSalesRecord.setModifyOprCode(userCode);
            // 修改时间
            vcInsuCardSalesRecord.setModifyTime(nowDate);

            vcInsuCardSalesRecord.setUpdatedBy(userCode);
            vcInsuCardSalesRecord.setDateUpdated(nowDate);

            // 退卡的银行账户信息
            bankInfo.setPayeeCode(vcInsuCardSalesRecord.getSellerCode());
            //modify by chy 20131010 退卡人名称从销售表中取 begin
            /*String sellerName = vcTakerDao.getUnitNameByUnitCode(vcInsuCardSalesRecord.getSellerCode());
            bankInfo.setPayeeName(sellerName);*/
            bankInfo.setPayeeName(vcInsuCardSalesRecord.getSellerName());
            //modify by chy 20131010 退卡人名称从销售表中取 end
            
           //modify by whj 20140222 联行号编码即为支行代码 start
            if(StringUtils.isBlank(bankInfo.getPayeeBankLocationCode())){
                bankInfo.setCnapsCode("999999999999");//默认12个9
                bankInfo.setCnapsName(null);
            }else{
                bankInfo.setCnapsCode(bankInfo.getPayeeBankLocationCode());
                bankInfo.setCnapsName(bankInfo.getPayeeBankLocationName());   
            }
           //modify by whj 20140222 联行号编码即为支行代码 end
            
            bankInfo.setCreatedBy(userCode);
            bankInfo.setDateCreated(nowDate);
            bankInfo.setUpdatedBy(userCode);
            bankInfo.setDateUpdated(nowDate);

            for (Iterator iterator = vcInsuCardSalesDetailList.iterator(); iterator.hasNext();) {
                VcInsuCardSalesDetail detailTemp = (VcInsuCardSalesDetail) iterator.next();
                detailTemp.setDocStatus("A");
                detailTemp.setVcInsuCardSalesRecord(vcInsuCardSalesRecord);
                // 创建人
                detailTemp.setCreatedBy(vcInsuCardSalesRecord.getCreatedBy());
                // 创建时间
                detailTemp.setDateCreated(vcInsuCardSalesRecord.getDateCreated());
                // 修改人
                detailTemp.setUpdatedBy(vcInsuCardSalesRecord.getUpdatedBy());
                // 修改时间
                detailTemp.setDateUpdated(vcInsuCardSalesRecord.getDateUpdated());
            }
            vcInsuCardSalesRecord.setVcInsuCardSalesDetailList(vcInsuCardSalesDetailList);

            // 销售记录状态（20退卡删除/21退卡新增/22等待退费/23退费成功）
            if ("1".equals(type)) { // 1-保存 2-提交
                /** 退卡记录状态（20退卡删除/21退卡新增/22等待退费/23退费成功） */
                vcInsuCardSalesRecord.setSalesRecordStatus("21");
            //modify by zhxiao3 VC-128贵州酒厂激活卡【保费为0方案】 begin
            } else if(vcInsuCardSalesRecord.getSumFee() !=0 ){
                vcInsuCardSalesRecord.setSalesRecordStatus("22");
            } else{
            	vcInsuCardSalesRecord.setSalesRecordStatus("23");
            }
            //modify by zhxiao3 VC-128贵州酒厂激活卡【保费为0方案】 end
            if ("insert".equals(actionType)) {
                vcInsuCardSalesRecordDao.saveInsuCardSalesRecordAndDet(vcInsuCardSalesRecord);
            } else if ("update".equals(actionType)) {
                vcInsuCardSalesRecordDao.update(vcInsuCardSalesRecord);
            }
            
            // 保存银行账户信息
           	Long recordId = vcInsuCardSalesRecord.getIdVcInsuCardSalesRecord();
           	bankInfo.setIdBusiness(recordId);


           	// 1.保存 2.提交（提交则缴费信息送收付）
           	if ("2".equals(type) && vcInsuCardSalesRecord.getSumFee() !=0) {
           		
               	vcPayFeeAccountDao.save(bankInfo);
           		RefundActCardResponse response = doRefundToPayment(vcInsuCardSalesRecord, bankInfo, userInfo);
           		if (response == null || response.getHEAD() == null) {
           			throw new BusinessException("信息送收付处理返回信息为空！");
           		}
           		String[] result = dealResponseCode(response.getHEAD());
           		if ("000".equals(result[0])) {
           			// 可使用表单证冻结
           			List<Object> values = new ArrayList<Object>();
           			StringBuffer sb = new StringBuffer("update vc_available_doc a  ");
           			sb.append("set doc_Status=? ");
           			sb.append("where a.DOC_VER_CODE=? and a.DOC_NUM between ? and ?");
           			String updateSql = sb.toString();
           			VcInsuranceCard queryDto = new VcInsuranceCard();
           			for (VcInsuCardSalesDetail detail : vcInsuCardSalesDetailList) {
           				values.clear();
           				values.add("JT");// 激活卡退卡冻结
           				values.add(detail.getDocVerCode());
           				values.add(detail.getStartNum());
           				values.add(detail.getEndNum());
           				vcAvailableDocDao.updateBySql(updateSql, values.toArray());

           				// 更新激活卡状态为23-已退卡待退费
            			queryDto.setDocVerCode(detail.getDocVerCode());
            			queryDto.setStartCardNum(detail.getStartNum());
            			queryDto.setEndCardNum(detail.getEndNum());
           				// 0-库存流转中 1-已付费待激活 20-已销售待付费 21-已退卡已领款 22--已退卡再付费待激活 23-已退卡待退费 9-激活成功
           				vcInsuranceCardDao.updateVcInsuranceCardByCondition(queryDto, "23");
           			}
           			return "操作成功";
           		} else {
           			throw new BusinessException(result[1]);
           		}
           	//modify by zhxiao3 VC-128贵州酒厂激活卡【保费为0方案】 begin
            }else if("2".equals(type) && vcInsuCardSalesRecord.getSumFee() ==0){
            	StringBuffer errorMsg = new StringBuffer();
                List mergeVcStorageAllList = new ArrayList();
                for (VcInsuCardSalesDetail detailTemp : vcInsuCardSalesDetailList) {
                    String startNum = detailTemp.getStartNum();
                    String endNum = detailTemp.getEndNum();
                    Long start = Long.valueOf(startNum);
                    Long end = Long.valueOf(endNum);
                    // 判断流水对应库存是否有记录
                    String sql = " SELECT COUNT(t.ID_VC_STORAGE) FROM VC_STORAGE t " + " WHERE t.doc_ver_code = ? "
                            + " AND t.press_batch_no = ? " + " AND t.doc_status IN ('S1','S2','S3') "
                            + " AND((? BETWEEN T.START_NUM AND T.END_NUM)" + " OR (T.START_NUM BETWEEN ? AND  ? ))";

                    // 判断起止流水号对应库存信息有没有对应的记录
                    Object[] objs = { detailTemp.getDocVerCode(), detailTemp.getPressBatchNo(), startNum, startNum, endNum };
                    int amount = Integer.valueOf(vcStorageDao.findBySQL(sql, objs).get(0).toString());
                    if (amount > 0) {
                        errorMsg.append("激活卡[" + startNum + "-" + endNum + "]对应库存有记录，数据错误!\n");
                    }

                    //查询流水号vcAvailableDoc信息
                    QueryRule queryRule = QueryRule.getInstance();
                    Object[] values = { startNum, endNum };
                    queryRule.addBetween("docNum", values);
                    queryRule.addEqual("docVerCode", detailTemp.getDocVerCode());
                    List<VcAvailableDoc> vcAvailableDocList = vcAvailableDocDao.find(queryRule);
                    // 判断库存是否存在
                    int amount_ = vcAvailableDocList.size();
                    if (end - start + 1 == amount_) {
                        // 增加VC_STORAGE库存记录
                        List<Object> mergeVcStorage = new ArrayList<Object>();
                        mergeVcStorage.add(detailTemp);
                        mergeVcStorage.add(vcAvailableDocList);
                        mergeVcStorageAllList.add(mergeVcStorage);
                    } else {
                        errorMsg.append("操作失败，单证状态不对或单证不存在 ");
                    }
                }

                if (errorMsg.length() > 0) {
                    throw new BusinessException(errorMsg.toString());
                }
                // 可使用表记录合并到库存表并删除可使用表记录
                mergeVcStorage(mergeVcStorageAllList, vcInsuCardSalesRecord.getProvideOrgCode(), vcInsuCardSalesRecord.getCreatedBy());
            	
            	// 更新激活卡状态用查询条件
                VcInsuranceCard queryDto = new VcInsuranceCard();
                for (VcInsuCardSalesDetail detailTemp : vcInsuCardSalesDetailList) {
                    // 更新激活卡状态
                    queryDto.setDocVerCode(detailTemp.getDocVerCode());
                    queryDto.setStartCardNum(detailTemp.getStartNum());
                    queryDto.setEndCardNum(detailTemp.getEndNum());
                    // queryDto.setCardStatus("1");
                    // 0-库存流转中 1-已付费待激活 20-已销售待付费 21-已退卡已领款 22--已退卡再付费待激活 23-已退卡待退费 9-激活成功
                    vcInsuranceCardDao.updateVcInsuranceCardByCondition(queryDto, "21");
                }
            }
           	//modify by zhxiao3 VC-128贵州酒厂激活卡【保费为0方案】 end
            return "操作成功";
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("操作异常：" + e.getMessage(), e);
        }
    }

    /**
     * 激活卡退卡退款提交(可使用表冻结，激活卡状态为)
     * 
     * @param vcInsuCardSalesRecord
     * @param userInfo
     * @return
     * @throws BusinessException
     */
    public String executeSubmitRefund(Long idVcInsuCardRefundRecord, UserInfo userInfo) throws BusinessException {
        VcInsuCardSalesRecord vcInsuCardRefundRecord = vcInsuCardSalesRecordDao
                .getVcInsuCardSalesRecord(idVcInsuCardRefundRecord);

        if (vcInsuCardRefundRecord == null) {
            throw new BusinessException("记录不存在！");
        }
        /** 退卡记录状态（20退卡删除/21退卡新增/22等待退费/23退费成功） */
        if (!"21".equals(vcInsuCardRefundRecord.getSalesRecordStatus())) {
            throw new BusinessException("只有【退卡新增】的退卡单可以提交退卡，请刷新后重试");
        }

        Date nowDate = new Date();

        String userCode = userInfo.getUserCode();

        // 修改人代码
        vcInsuCardRefundRecord.setModifyOprCode(userCode);
        // 修改时间
        vcInsuCardRefundRecord.setModifyTime(nowDate);

        // 销售详细信息
        List<VcInsuCardSalesDetail> vcInsuCardSalesDetailList = vcInsuCardRefundRecord.getVcInsuCardSalesDetailList();
        // 校验数据
        if (vcInsuCardSalesDetailList == null || vcInsuCardSalesDetailList.size() == 0) {
            throw new BusinessException("激活卡销售详细信息为空\n");
        }

        try {
            // 同一单证类型同一批次的号段是否有交叉重叠
            Map<String, Object> overLapResultMap = isOverLap(vcInsuCardSalesDetailList);
            if ((Boolean) overLapResultMap.get("isOverLap")) {
                throw new BusinessException((String) overLapResultMap.get("strMsg"));
            }

            // 校验流水号
            List checkResultList = checkAvailableData(vcInsuCardRefundRecord, vcInsuCardSalesDetailList, userInfo);
            List<String> checkResult = (List<String>) checkResultList.get(0);
            StringBuffer str = new StringBuffer();
            // 0.成功
            // 1.起始流水号大于终止流水号，请重新输入
            // 2.存在不可用单证状态
            // 3.未找到库存记录
            // 4.起始流水号段对应多条库存记录
            for (int i = 0; i < checkResult.size(); i++) {
                String resultFlag = checkResult.get(i);
                VcInsuCardSalesDetail detailVo = vcInsuCardSalesDetailList.get(i);
                String start = detailVo.getStartNum();
                String end = detailVo.getEndNum();
                if ("1".equals(resultFlag)) {
                    str.append("起始流水号[" + start + "]大于终止流水号[" + end + "]，请重新输入;\n");
                } else if ("2".equals(resultFlag)) {
                    str.append("可使用表处于区间[" + start + "-" + end + "]的部分激活卡不存在;\n");
                } else if ("4".equals(resultFlag)) {
                    str.append("可使用表卡号处于区间[" + start + "-" + end + "]记录超过实际值;\n");
                } else if ("6".equals(resultFlag)) {
                    str.append("部分卡号[" + start + "-" + end + "]在激活卡信息表中不存在;\n");
                }
            }
            if (str.length() > 0) {
                throw new BusinessException(str.toString());
            }
            
            if(vcInsuCardRefundRecord.getSumFee() !=0 ){
                VcPayFeeAccount bankInfo = null;
                try {
                    bankInfo = vcPayFeeAccountDao.getVcPayFeeAccountByBusinessId(idVcInsuCardRefundRecord);
                } catch (DaoException e) {
                    throw new BusinessException(e.getMessage(), e);
                }
                if (bankInfo == null) {
                    throw new BusinessException("银行账户信息不存在");
                }
                
                //modify by whj 20140222 联行号编码即为支行代码 start
                if(StringUtils.isBlank(bankInfo.getPayeeBankLocationCode())){
                    bankInfo.setCnapsCode("999999999999");//默认12个9
                    bankInfo.setCnapsName(null);
                }else{
                    bankInfo.setCnapsCode(bankInfo.getPayeeBankLocationCode());
                    bankInfo.setCnapsName(bankInfo.getPayeeBankLocationName());   
                }          
               //modify by whj 20140222 联行号编码即为支行代码 end
            	
            	RefundActCardResponse response = doRefundToPayment(vcInsuCardRefundRecord, bankInfo, userInfo);
            	if (response == null || response.getHEAD() == null) {
                	throw new BusinessException("信息送收付处理返回信息为空！");
            	}
            	String[] result = dealResponseCode(response.getHEAD());
            	if ("000".equals(result[0])) {
                	/** 退卡记录状态（20退卡删除/21退卡新增/22等待退费/23退费成功） */
                	vcInsuCardRefundRecord.setSalesRecordStatus("22");
                	vcInsuCardRefundRecord.setVcInsuCardSalesDetailList(null);
                	vcInsuCardSalesRecordDao.update(vcInsuCardRefundRecord);

                	// 可使用表单证冻结
                	List<Object> values = new ArrayList<Object>();
                	StringBuffer sb = new StringBuffer("update VC_AVAILABLE_DOC a  ");
                	sb.append("set DOC_STATUS=? ");
                	sb.append("where a.DOC_VER_CODE=? and a.DOC_NUM between ? and ?");
                	String updateSql = sb.toString();
                	VcInsuranceCard queryDto = new VcInsuranceCard();
                	for (VcInsuCardSalesDetail detail : vcInsuCardSalesDetailList) {
                    	values.clear();
                    	values.add("JT");// 激活卡退卡冻结
                    	values.add(detail.getDocVerCode());
                    	values.add(detail.getStartNum());
                    	values.add(detail.getEndNum());
                    	vcAvailableDocDao.updateBySql(updateSql, values.toArray());

                    	// 更新激活卡状态为23-已退卡待退费
                    	queryDto.setDocVerCode(detail.getDocVerCode());
                    	queryDto.setStartCardNum(detail.getStartNum());
                    	queryDto.setEndCardNum(detail.getEndNum());
                    	// 0-库存流转中 1-已付费待激活 20-已销售待付费 21-已退卡已领款 22--已退卡再付费待激活 23-已退卡待退费 9-激活成功
                    	vcInsuranceCardDao.updateVcInsuranceCardByCondition(queryDto, "23");
                	}
                	return "操作成功";
            	} else {
                	throw new BusinessException(result[1]);
            	}
            }else{
            	//modify by zhxiao3 VC-128贵州酒厂激活卡【保费为0方案】 begin
            	/** 退卡记录状态（20退卡删除/21退卡新增/22等待退费/23退费成功） */
            	vcInsuCardRefundRecord.setSalesRecordStatus("23");
            	vcInsuCardRefundRecord.setVcInsuCardSalesDetailList(null);
            	vcInsuCardSalesRecordDao.update(vcInsuCardRefundRecord);
            	
            	StringBuffer errorMsg = new StringBuffer();
                List mergeVcStorageAllList = new ArrayList();
                for (VcInsuCardSalesDetail detailTemp : vcInsuCardSalesDetailList) {
                    String startNum = detailTemp.getStartNum();
                    String endNum = detailTemp.getEndNum();
                    Long start = Long.valueOf(startNum);
                    Long end = Long.valueOf(endNum);
                    // 判断流水对应库存是否有记录
                    String sql = " SELECT COUNT(t.ID_VC_STORAGE) FROM VC_STORAGE t " + " WHERE t.doc_ver_code = ? "
                            + " AND t.press_batch_no = ? " + " AND t.doc_status IN ('S1','S2','S3') "
                            + " AND((? BETWEEN T.START_NUM AND T.END_NUM)" + " OR (T.START_NUM BETWEEN ? AND  ? ))";

                    // 判断起止流水号对应库存信息有没有对应的记录
                    Object[] objs = { detailTemp.getDocVerCode(), detailTemp.getPressBatchNo(), startNum, startNum, endNum };
                    int amount = Integer.valueOf(vcStorageDao.findBySQL(sql, objs).get(0).toString());
                    if (amount > 0) {
                        errorMsg.append("激活卡[" + startNum + "-" + endNum + "]对应库存有记录，数据错误!\n");
                    }

                    //查询流水号vcAvailableDoc信息
                    QueryRule queryRule = QueryRule.getInstance();
                    Object[] values = { startNum, endNum };
                    queryRule.addBetween("docNum", values);
                    queryRule.addEqual("docVerCode", detailTemp.getDocVerCode());
                    List<VcAvailableDoc> vcAvailableDocList = vcAvailableDocDao.find(queryRule);
                    // 判断库存是否存在
                    int amount_ = vcAvailableDocList.size();
                    if (end - start + 1 == amount_) {
                        // 增加VC_STORAGE库存记录
                        List<Object> mergeVcStorage = new ArrayList<Object>();
                        mergeVcStorage.add(detailTemp);
                        mergeVcStorage.add(vcAvailableDocList);
                        mergeVcStorageAllList.add(mergeVcStorage);
                    } else {
                        errorMsg.append("操作失败，单证状态不对或单证不存在 ");
                    }
                }

                if (errorMsg.length() > 0) {
                    throw new BusinessException(errorMsg.toString());
                }
                // 可使用表记录合并到库存表并删除可使用表记录
                mergeVcStorage(mergeVcStorageAllList, vcInsuCardRefundRecord.getProvideOrgCode(), vcInsuCardRefundRecord.getCreatedBy());
            	
            	// 更新激活卡状态用查询条件
                VcInsuranceCard queryDto = new VcInsuranceCard();
                for (VcInsuCardSalesDetail detailTemp : vcInsuCardSalesDetailList) {
                    // 更新激活卡状态
                    queryDto.setDocVerCode(detailTemp.getDocVerCode());
                    queryDto.setStartCardNum(detailTemp.getStartNum());
                    queryDto.setEndCardNum(detailTemp.getEndNum());
                    // queryDto.setCardStatus("1");
                    // 0-库存流转中 1-已付费待激活 20-已销售待付费 21-已退卡已领款 22--已退卡再付费待激活 23-已退卡待退费 9-激活成功
                    vcInsuranceCardDao.updateVcInsuranceCardByCondition(queryDto, "21");
                }
            	return "操作成功";
            }
            //modify by zhxiao3 VC-128贵州酒厂激活卡【保费为0方案】 end
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("操作异常：" + e.getMessage(), e);
        }
    }

    /**
     * 激活卡申请退款
     * 
     */
    public RefundActCardResponse doRefundToPayment(VcInsuCardSalesRecord vcInsuCardSalesRecord,
            VcPayFeeAccount bankInfo, UserInfo userInfo) throws BusinessException {
        String sn = UUID.randomUUID().toString().replace("-", "");       
        RefundActCardRequest request = new RefundActCardRequest();
        BeanUtils.copyProperties(bankInfo, request);
        /*
         * // 收付款人名称 payeeName String payeeName =
         * vcTakerDao.getUnitNameByUnitCode(vcInsuCardSalesRecord.getSellerCode()); if (StringUtils.isBlank(payeeName))
         * { throw new BusinessException("收款人名称为空！"); } request.setPayeeCode(vcInsuCardSalesRecord.getSellerCode());
         * request.setPayeeName(payeeName);
         */
        // 业务归属机构代码
        request.setCompanyCode(vcInsuCardSalesRecord.getSaleOrgCode());
        request.setBusinessNo(vcInsuCardSalesRecord.getSalesRecordCode());
        request.setPayeeBankLocation(bankInfo.getPayeeBankLocationName());
        
        //modify by whj 20140222 联行号编码即为支行代码 start
        if(StringUtils.isBlank(bankInfo.getPayeeBankLocationCode())){
            request.setCnapsCode("999999999999");//默认12个9
            request.setCnapsName(null);
        }else{
            request.setCnapsCode(bankInfo.getPayeeBankLocationCode());
            request.setCnapsName(bankInfo.getPayeeBankLocationName());   
        }
       //modify by whj 20140222 联行号编码即为支行代码 end

        // 请求头
        RequestHeadDTO head = new RequestHeadDTO();
        head.setUser("VC");
        head.setPassword("VC");
        head.setSn(sn);

        // 01 承保 02 理赔 03 销管 04 资金 05 费控 06收付 07单证
        head.setSystemCode("07");
        //业务号（可不设置）
        head.setBusinessNo(vcInsuCardSalesRecord.getSalesRecordCode());

        request.setHEAD(head);

        List<VcInsuCardSalesDetail> vcInsuCardSalesDetailList = vcInsuCardSalesRecord.getVcInsuCardSalesDetailList();
        // 明细信息
        List<ActivationDetailDto> activationDetailList = request.getActivationDetailList();
        ActivationDetailDto tempDto = null;
        // 格式化流水号为10位
        DecimalFormat df = new DecimalFormat("0");
       
        double totalFee = 0d;
        for (Iterator iterator = vcInsuCardSalesDetailList.iterator(); iterator.hasNext();) {
            VcInsuCardSalesDetail detailTemp = (VcInsuCardSalesDetail) iterator.next();
            String startNum = detailTemp.getStartNum();
            String endNum = detailTemp.getEndNum();
            df.setMinimumIntegerDigits(startNum.length());
            Long start = Long.valueOf(startNum);
            Long end = Long.valueOf(endNum);
            // 面值
            for (Long k = start; k <= end; k++) {
                tempDto = new ActivationDetailDto();
                tempDto.setCardNo(df.format(k));
                // 获取面值
                VcInsuranceCard feeQueryDto=new VcInsuranceCard(); 
                feeQueryDto.setCardNo(tempDto.getCardNo());
                feeQueryDto.setDocVerCode(detailTemp.getDocVerCode());
                List<Object> sumNumList = vcInsuranceCardDao.getCardTotalFeeNumByCondition(feeQueryDto,null,null);
                double fee =(Double) sumNumList.get(0);
                double countNum =(Long) sumNumList.get(1);
                if(countNum!=1){
                 // 6.激活卡信息表中对应激活卡不存在
                 throw new BusinessException("激活卡信息表中对应激活卡【"+ tempDto.getCardNo()+ "】不存在！");
                }             
                tempDto.setAmount(fee);
                activationDetailList.add(tempDto);
                //退款总额
                totalFee +=fee;
            }
           
        }
        // request.setPayFee(totalFee);

        RefundActCardResponse response = visaWebService.refundActCard(request);
        return response;
    }

    /**
     * 退卡时校验可使用表
     * 
     * @param salesRecord
     * @param salesDets
     * @param userInfo当前用户信息
     * @return List(0) 0.成功 1.起始流水号大于终止流水号，请重新输入 2.存在不可用单证状态 3.未找到库存记录
     */
    private List checkAvailableData(VcInsuCardSalesRecord salesRecord, List<VcInsuCardSalesDetail> salesDets,
            UserInfo userInfo) throws BusinessException {
        List resultList = new ArrayList();
        try {
            // 0.成功
            // 1.起始流水号大于终止流水号，请重新输入
            // 2.存在不可用单证状态
            // 3.未找到库存记录
            List<String> result = new ArrayList<String>();
            double totalFee = 0d;
            for (int i = 0; i < salesDets.size(); i++) {
                VcInsuCardSalesDetail saleDet = salesDets.get(i);

                String startStr = saleDet.getStartNum();
                String endStr = saleDet.getEndNum();

                // 用户可能输入10位或16位 需要截取
                Long start = Long.valueOf(startStr);
                Long end = Long.valueOf(endStr);

                if (start > end) {
                    // 1. 起始流水号大于终止流水号，请重新输入
                    result.add("1");
                    continue;
                }

                // 校验可使用表的激活卡数量是否等于销售记录的数量
                QueryRule queryRule = QueryRule.getInstance();
                queryRule.addEqual("docStatus", "A");
                Object[] values = { startStr, endStr };
                queryRule.addBetween("docNum", values);
                queryRule.addEqual("docVerCode", saleDet.getDocVerCode());
                queryRule.addEqual("pressBatchNo", saleDet.getPressBatchNo());
                //modify 20140120 退卡只校验机构，不校验人员
                // 退卡人
                //queryRule.addEqual("takerCode", salesRecord.getSellerCode());
                //退卡机构
                queryRule.addEqual("orgCode", salesRecord.getSaleOrgCode());
                List<VcAvailableDoc> vcAvailableDocList = vcAvailableDocDao.find(queryRule);
                // 判断库存是否存在
                int amount_ = vcAvailableDocList.size();
                if (end - start + 1 > amount_) {
                    // 2.区间段部分记录不存在
                    result.add("2");
                    continue;
                } else if (end - start + 1 < amount_) {
                    // 4.可使用表记录异常
                    result.add("4");
                    continue;
                }
                // 数量
                saleDet.setSaleNum(end - start + 1);

                // 获取面值
                VcInsuranceCard queryDto = new VcInsuranceCard();
                queryDto.setStartCardNum(saleDet.getStartNum());
                queryDto.setEndCardNum(saleDet.getEndNum());
                queryDto.setDocVerCode(saleDet.getDocVerCode());
                List<Object> sumNumList = vcInsuranceCardDao.getCardTotalFeeNumByCondition(queryDto,null,null);
                double fee =(Double) sumNumList.get(0);
                double countNum =(Long) sumNumList.get(1);
                if(countNum!=(end - start + 1)){
                 // 6.激活卡信息表中对应激活卡不存在
                    result.add("6");
                    continue;
                }
                
                saleDet.setFee(fee);
                totalFee += fee;
                result.add("0");
            }
            salesRecord.setSumFee(totalFee);
            // 校验信息
            resultList.add(result);
        } catch (Exception e) {
            throw new BusinessException("操作失败:"+e.getMessage(), e);
        }
        return resultList;
    }

    /**
     * 修改同步账户信息 1:修改单证系统退卡纪录的账户信息 2:同步更新收付的账户记录信息
     * 
     * @param idVcInsuCardRefundRecord
     * @param bankInfo
     * @param userInfo
     * @return
     * @throws BusinessException
     */
    public String executeAccountModify(Long idVcInsuCardRefundRecord, VcPayFeeAccount bankInfo, UserInfo userInfo)
            throws BusinessException {
        try {
            VcInsuCardSalesRecord vcInsuCardRefundRecord = vcInsuCardSalesRecordDao
                    .getVcInsuCardSalesRecord(idVcInsuCardRefundRecord);

            if (vcInsuCardRefundRecord == null) {
                throw new BusinessException("退卡记录不存在！");
            }
            /** 新增或退卡成功的记录不能修改（20退卡删除/21退卡新增/22等待退费/23退费成功/24退费失败(账户原因)/25退费失败(非账户原因)） */
            if ("21".equals(vcInsuCardRefundRecord.getSalesRecordStatus()) || "23".equals(vcInsuCardRefundRecord.getSalesRecordStatus())) {
                throw new BusinessException("【新增、退卡成功】的退卡记录不可以提交帳戶修改，请刷新后重试");
            }
            VcPayFeeAccount modifyBankInfo = vcPayFeeAccountDao
                    .getVcPayFeeAccountByBusinessId(idVcInsuCardRefundRecord);
            if (modifyBankInfo == null) {
                throw new BusinessException("待修改同步的银行账户信息不存在！");
            }
            // 删除银行账户信息..
            vcPayFeeAccountDao.delete(modifyBankInfo);

            Date nowDate = new Date();
            // 修改退卡的银行账户信息
            bankInfo.setPayeeCode(vcInsuCardRefundRecord.getSellerCode());
            //modify by chy 20131010 退卡人名称从销售表中取 begin
            /*String sellerName = vcTakerDao.getUnitNameByUnitCode(vcInsuCardRefundRecord.getSellerCode());
            bankInfo.setPayeeName(sellerName);*/
            bankInfo.setPayeeName(vcInsuCardRefundRecord.getSellerName());
            //modify by chy 20131010 退卡人名称从销售表中取 end
            
           //modify by whj 20140222 联行号编码即为支行代码 start
            if(StringUtils.isBlank(bankInfo.getPayeeBankLocationCode())){
                bankInfo.setCnapsCode("999999999999");//默认12个9
                bankInfo.setCnapsName(null);
            }else{
                bankInfo.setCnapsCode(bankInfo.getPayeeBankLocationCode());
                bankInfo.setCnapsName(bankInfo.getPayeeBankLocationName());   
            }            
            //modify by whj 20140222 联行号编码即为支行代码 end
            
            bankInfo.setCreatedBy(userInfo.getUserCode());
            bankInfo.setDateCreated(nowDate);
            bankInfo.setUpdatedBy(userInfo.getUserCode());
            bankInfo.setDateUpdated(nowDate);
            bankInfo.setIdBusiness(idVcInsuCardRefundRecord);
            vcPayFeeAccountDao.save(bankInfo);

            // 同步收付的账户信息
            VisaAccountToPaymentResponse response = doModifyAccountToPayment(vcInsuCardRefundRecord, bankInfo, userInfo);
            if (response == null || response.getHEAD() == null) {
                throw new BusinessException("信息送收付处理返回信息为空！");
            }
            String[] result = dealResponseCode(response.getHEAD());
            if ("000".equals(result[0])) {
                return "操作成功";
            } else {
                throw new BusinessException(result[1]);
            }
        } catch (DaoException de) {
            throw new BusinessException(de.getMessage(), de);
        } catch (BusinessException be) {
            throw be;
        } catch (Exception e) {
            throw new BusinessException("操作异常:" + e.getMessage(), e);
        }
    }

    /**
     * 调用收付接口同步账户信息
     * 
     */
    private VisaAccountToPaymentResponse doModifyAccountToPayment(VcInsuCardSalesRecord vcInsuCardSalesRecord,
            VcPayFeeAccount bankInfo, UserInfo userInfo) throws BusinessException {
        String sn = UUID.randomUUID().toString().replace("-", "");       
        VisaAccountToPaymentRequest request = new VisaAccountToPaymentRequest();
        List<VisaAccountToPayment> bankInfoList = request.getVisaAccountToPayment();

        VisaAccountToPayment toPaymentbank = new VisaAccountToPayment();
        BeanUtils.copyProperties(bankInfo, toPaymentbank);
        // 业务号
        toPaymentbank.setBusinessNo(vcInsuCardSalesRecord.getSalesRecordCode());
        // 支行
        toPaymentbank.setPayeeBankLocation(bankInfo.getPayeeBankLocationName());
        bankInfoList.add(toPaymentbank);
        // 请求头
        RequestHeadDTO head = new RequestHeadDTO();
        head.setUser("VC");
        head.setPassword("VC");
        head.setSn(sn);
        // 01 承保 02 理赔 03 销管 04 资金 05 费控 06收付 07单证
        head.setSystemCode("07");
        // 业务号（可不设置）
        head.setBusinessNo(vcInsuCardSalesRecord.getSalesRecordCode());

        request.setHEAD(head);

        VisaAccountToPaymentResponse response = visaWebService.visaAccountToPayment(request);       
        return response;
    }

}
