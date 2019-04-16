package com.tapi.tcs.vc.store.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcDocMngRuleDao;
import com.tapi.tcs.vc.baseinfo.dao.VcDocVersionInfoDao;
import com.tapi.tcs.vc.baseinfo.dao.VcTakerDao;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcConstantConfig;
import com.tapi.tcs.vc.schema.model.VcDocMngRule;
import com.tapi.tcs.vc.schema.model.VcDocTakerIo;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.schema.model.VcTaker;
import com.tapi.tcs.vc.store.dao.VcAvailableDocDao;
import com.tapi.tcs.vc.store.dao.VcDocTakerIoDao;
import com.tapi.tcs.vc.store.dao.VcStorageDao;
import com.tapi.tcs.vc.store.service.ServantDocManageService;

/**
 * 使用人单证发放/回收Service
 * <p>
 * Date: 2013-04-15
 * </p>
 * <p>
 * Module: 使用人单证发放/回收Service
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * @author Leo
 * @version 1.0
 */
public class ServantDocManageServiceImpl implements ServantDocManageService {
    /** 库存Dao */
    private VcStorageDao vcStorageDao;
    /** 使用人单证发放回收轨迹Dao */
    private VcDocTakerIoDao vcDocTakerIoDao;
    /** 可使用单证Dao */
    private VcAvailableDocDao vcAvailableDocDao;
    /** 单证类型Dao */
    private VcDocVersionInfoDao vcDocVersionInfoDao;
    /** 单证管理控制规则Dao */
    private VcDocMngRuleDao vcDocMngRuleDao;
    /** 单证使用人 Dao*/
    private VcTakerDao vcTakerDao;

	/**
     * 保存使用人单证发放
     * 
     * @param vcDocTakerIos
     *            使用人单证发放List
     * @param userCode
     *            当前操作人
     * @param takerCode
     *            使用人
     * @param acceptOrgCode
     *            使用人机构
     */
    @Override
    public String saveServantDocIssued(List<VcDocTakerIo> vcDocTakerIos, String userCode, String orgCode,
            String takerCode, String acceptOrgCode) throws BusinessException{
        String result = "";
        try{
        // 校验单证信息
        result = checkDoc(vcDocTakerIos);
        if (result.length() > 0) {
            return result;
        }
        // 发放回收机构代码
        StringBuffer erros = new StringBuffer();
        Date nowDate = new Date();
        // 最后需要拆分的库存记录
        List updateStorages = new ArrayList();
        for (int i = 0; i < vcDocTakerIos.size(); i++) {
            VcDocTakerIo vcDocTakerIo = (VcDocTakerIo) vcDocTakerIos.get(i);
            // 发放回收标志(P发放/R回收)
            vcDocTakerIo.setOprType("P");
            vcDocTakerIo.setOprCode(userCode);
            vcDocTakerIo.setOprTime(nowDate);
            vcDocTakerIo.setTakerCode(takerCode);
            vcDocTakerIo.setAcceptOrgCode(acceptOrgCode);
            vcDocTakerIo.setProvideOrgCode(orgCode);
            vcDocTakerIo.setCreatedBy(userCode);
            vcDocTakerIo.setDateCreated(nowDate);
            vcDocTakerIo.setUpdatedBy(userCode);
            vcDocTakerIo.setDateUpdated(nowDate);

            String startStr = vcDocTakerIo.getStartNum();
            String endStr = vcDocTakerIo.getEndNum();

            // 判断是否超过最长库存时间
            String docVerCode = vcDocTakerIo.getDocVerCode();          
            int maxStoreTime=vcDocMngRuleDao.getMaxStoreTime(docVerCode, "1", takerCode);
            Date sysdate = DateUtils.parse(DateUtils.format(new Date(), "yyyy-MM-dd") + " 23:59:59","yyyy-MM-dd HH:mm:ss");
            Date deadline = DateUtils.parse(vcDocTakerIo.getDeadline()+ " 23:59:59", "yyyy-MM-dd HH:mm:ss");
            Date maxStoreDate = DateUtils.addDay(sysdate, maxStoreTime);
            
            if (DateUtils.compare(deadline, maxStoreDate) > 0) {
                erros.append("单证类型[" + docVerCode + "]使用截止期超出最长库存时间 ");
                continue;
            }
            if(DateUtils.compare(sysdate,deadline) > 0){
            	 erros.append("单证类型[" + docVerCode + "]使用截止期不可以小于当前日期 ");
                 continue;
            }
            

            // 用户可能输入10位或16位 需要截取
            /*
             * Long start = startStr.length() == 16 ? Long.valueOf(startStr
             * .substring(6)) : Long.valueOf(startStr); Long end =
             * endStr.length() == 16 ? Long .valueOf(endStr.substring(6)) :
             * Long.valueOf(endStr);
             */
            Long start = Long.valueOf(startStr);
            Long end = Long.valueOf(endStr);
            if((end-start+1) > 10000){
                erros.append("使用人单证发放数量不可超过一万，当前发放号段[" + start +":"+end+ "]数量为"+(end-start+1)+",超过10000！ ");
                continue;
           }
            // 判断库存是否存在
            String[] params = { "S1", "S2", "S3" };
            /*List<VcStorage> vcStorages = vcStorageDao.isExistAll(startStr, endStr, vcDocTakerIo.getDocVerCode(),
                    vcDocTakerIo.getPressBatchNo(), acceptOrgCode, params);*/
            List<VcStorage> vcStorages = vcStorageDao.isExistAll(startStr, endStr, vcDocTakerIo.getDocVerCode(),
                    vcDocTakerIo.getPressBatchNo(), orgCode,false, params);

            if (vcStorages.size() == 1) {
                VcStorage vcStorage = vcStorages.get(0);
                // 需要拆分库存的记录
                List updateStorage = new ArrayList();
                updateStorage.add(vcDocTakerIo);
                updateStorage.add(vcStorage);
                updateStorages.add(updateStorage);
                // saveVcStorage(vcDocTakerIo, vcStorage);
                // vcDocTakerIoDao.save(vcDocTakerIo);
            } else {
                erros.append("库存[" + startStr + "—" + endStr + "]不存在或存在多条库存\n");
            }
            
            //判断拆分号段是否已过使用截止期
            if(DateUtils.compare(DateUtils.reset(vcStorages.get(0).getDeadline()), DateUtils.reset(new Date())) < 0){
            	throw new DaoException("单证["+ docVerCode +"]号段["+start+"—"+end+"！]已过使用截止期，无法发放");
            }
            
        }

        if (erros.length() == 0) {
            saveVcStorage(updateStorages);
        } else {
            return erros.toString();
        }}catch (BusinessException e) {
            throw new BusinessException(e.getMessage(), e);
        }catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }

        return "操作成功";
    }

    /**
     * 检验单证信息 1.是否为空 2.单证类型是否重复 3.流水号是否正确
     * 
     * @param vcDocTakerIos
     * @return
     */
    private String checkDoc(List<VcDocTakerIo> vcDocTakerIos) throws BusinessException{
        StringBuffer str = new StringBuffer();
        if (vcDocTakerIos == null || vcDocTakerIos.size() == 0) {
            return "单证信息不能为空";
        }       
     try{
        for (Iterator iterator = vcDocTakerIos.iterator(); iterator.hasNext();) {
            VcDocTakerIo vcDocTakerIo = (VcDocTakerIo) iterator.next();
            String startStr = vcDocTakerIo.getStartNum();
            String endStr = vcDocTakerIo.getEndNum();
            /*
             * if (startStr.length() != 10 || endStr.length() != 10 &&
             * (startStr.length() != 16 || endStr.length() != 16)) { str
             * .append("请输入10位或者16位的流水号[" + startStr + "-" + endStr + "]\n");
             * continue; }
             */

            String startStr_ = startStr.length() == 16 ? startStr.substring(6) : startStr;
            String endStr_ = endStr.length() == 16 ? endStr.substring(6) : endStr;

            if (!startStr_.matches("[0-9]*") || !endStr_.matches("[0-9]*")) {
                str.append("流水号[" + startStr + "-" + endStr + "]错误\n");
                continue;
            }

            Long start = Long.valueOf(startStr_);
            Long end = Long.valueOf(endStr_);

            if (end < start) {
                str.append("起始流水号大于终止流水号[" + startStr + "-" + endStr + "]");
                continue;
            }
        }
        return str.toString();
     }catch (Exception e) {
         throw new BusinessException(e.getMessage(), e);
     }
    }

    /**
     * 拆分库存 保存VC_AVAILABLE_DOC
     * 
     * @param updateStorages
     *            包含List List(0) vcDocTakerIo List(1) vcStorage
     */
    public void saveVcStorage(List updateStorages) throws BusinessException{
    	try{
        for (Iterator iterator = updateStorages.iterator(); iterator.hasNext();) {

            List updateStorage = (List) iterator.next();

            VcDocTakerIo vcDocTakerIo = (VcDocTakerIo) updateStorage.get(0);
            VcStorage vcStorage = (VcStorage) updateStorage.get(1);
            // 锁表
            //vcStorageDao.lockVcStorage(vcStorage.getId());

            // 库存表拆分
            Date nowDate = vcDocTakerIo.getOprTime();

            String startStr = vcDocTakerIo.getStartNum();
            String endStr = vcDocTakerIo.getEndNum();
            Long start = startStr.length() == 16 ? Long.valueOf(startStr.substring(6)) : Long.valueOf(startStr);
            Long end = endStr.length() == 16 ? Long.valueOf(endStr.substring(6)) : Long.valueOf(endStr);

            // 拆分
            //vcStorageDao.splitStorage(startStr, endStr, null, null, null, null, null, vcStorage);
            vcStorageDao.splitStorage(startStr, endStr, vcStorage.getDocVerCode(), vcStorage.getPressBatchNo(), vcStorage.getOrgCode(), vcStorage.getDocStatus(), 
                    null, null,vcDocTakerIo.getOprCode());
            // 拆分完毕
            
	        //ADD BY zhxiao3 VC-115-单证库存查询存在统计号段重复的问题  BEGIN
            //保存VC_DOC_TAKER_IO
            vcDocTakerIoDao.save(vcDocTakerIo);
	        //ADD BY zhxiao3 VC-115-单证库存查询存在统计号段重复的问题  end

            //add by whj进行使用人单证发放时，若使用人有归属中介机构，中介机构未保存进系统
            VcTaker takerVo=vcTakerDao.getTakerByTakerCode(vcDocTakerIo.getTakerCode());
            String agencyOrgCode=null;
            if(takerVo!=null){
               agencyOrgCode=takerVo.getAgencyOrgCode(); 
            }
            
            // 保存 VC_AVAILABLE_DOC 数据 start
            Long totalAmount = end - start + 1;
            List<VcAvailableDoc> vcAvailableDocs = new ArrayList<VcAvailableDoc>();
            DecimalFormat df = new DecimalFormat("0");
            df.setMinimumIntegerDigits(startStr.length());
            VcAvailableDoc vcAvailableDoc = null;
            for (int i = 0; i < totalAmount; i++) {
                vcAvailableDoc = new VcAvailableDoc();
                // 单证类型代码
                vcAvailableDoc.setDocVerCode(vcDocTakerIo.getDocVerCode());
                // 单证种类代码 数据库字段已删除
                // vcAvailableDoc.setDocTypeCode(vcDocVersionInfoDao.getDocTypeCode(vcDocTakerIo.getDocVerCode()));
                // 单证流水号
                vcAvailableDoc.setDocNum(df.format(start + i));
                // 印刷批次
                vcAvailableDoc.setPressBatchNo(vcDocTakerIo.getPressBatchNo());
                // 单证状态
                vcAvailableDoc.setDocStatus("A");
                // 使用人代码
                vcAvailableDoc.setTakerCode(vcDocTakerIo.getTakerCode());
                // 使用人归属机构
                vcAvailableDoc.setOrgCode(vcDocTakerIo.getAcceptOrgCode());
                //中介机构
                vcAvailableDoc.setAgentCode(agencyOrgCode);
                // 发放时间
                vcAvailableDoc.setProvideTime(nowDate);
                // 使用截止日期
                vcAvailableDoc.setDeadline(DateUtils.parse(vcDocTakerIo.getDeadline()));
                // 创建人
                vcAvailableDoc.setCreatedBy(vcDocTakerIo.getOprCode());
                // 创建时间
                vcAvailableDoc.setDateCreated(nowDate);
                // 修改人代码
                vcAvailableDoc.setUpdatedBy(vcDocTakerIo.getOprCode());
                // 修改时间
                vcAvailableDoc.setDateUpdated(nowDate);
                
    	        //ADD BY zhxiao3 VC-115-单证库存查询存在统计号段重复的问题  BEGIN
                //使用人单证发放回收轨迹表流水
                vcAvailableDoc.setIdTakerIO(vcDocTakerIo.getId());
    	        //ADD BY zhxiao3 VC-115-单证库存查询存在统计号段重复的问题  END

                vcAvailableDocs.add(vcAvailableDoc);
            }
            vcAvailableDocDao.saveAll(vcAvailableDocs);
            // 保存 VC_AVAILABLE_DOC 数据 end


        }
        }catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }

    }

    /**
     * 查询使用人单证发放/回收
     * 
     * @param vcDocTakerIo
     * @return
     * @throws Exception
     */
    @Override
    public Page queryServantDocIssued(VcDocTakerIo vcDocTakerIo, int pageNo, int pageSize) throws Exception {
    	try{
        return vcDocTakerIoDao.findVcDocTakerIos(vcDocTakerIo, pageNo, pageSize);
    	}catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 保存使用人单证回收
     * 
     * @param vcDocTakerIos
     *            使用人单证List
     * @param userCode
     *            当前操作人
     * @param takerCode
     *            使用人
     * @param acceptOrgCode
     *            使用人机构
     */
    public String saveServantDocRecovery(List<VcDocTakerIo> vcDocTakerIos, String userCode, String orgCode,
            String takerCode, String acceptOrgCode) throws BusinessException{
 
        // 校验单证信息
        String result = checkDoc(vcDocTakerIos);
        if (result.length() > 0) {
            return result;
        }
        try{
        Date nowDate = new Date();
        StringBuffer errors = new StringBuffer();
        List mergeVcStorages = new ArrayList();
        for (int i = 0; i < vcDocTakerIos.size(); i++) {
            VcDocTakerIo vcDocTakerIo = (VcDocTakerIo) vcDocTakerIos.get(i);
            // 发放回收标志(P发放/R回收)
            vcDocTakerIo.setOprType("R");
            vcDocTakerIo.setOprCode(userCode);
            vcDocTakerIo.setOprTime(nowDate);
            vcDocTakerIo.setTakerCode(takerCode);
            vcDocTakerIo.setAcceptOrgCode(acceptOrgCode);
            vcDocTakerIo.setProvideOrgCode(orgCode);
            vcDocTakerIo.setCreatedBy(userCode);
            vcDocTakerIo.setDateCreated(nowDate);
            vcDocTakerIo.setUpdatedBy(userCode);
            vcDocTakerIo.setDateUpdated(nowDate);

            String startStr = vcDocTakerIo.getStartNum();
            String endStr = vcDocTakerIo.getEndNum();

            // 用户可能输入10位或16位 需要截取
            /*
             * Long start = startStr.length() == 16 ? Long.valueOf(startStr
             * .substring(6)) : Long.valueOf(startStr); Long end =
             * endStr.length() == 16 ? Long .valueOf(endStr.substring(6)) :
             * Long.valueOf(endStr);
             */
            Long start = Long.valueOf(startStr);
            Long end = Long.valueOf(endStr);
            // 判断流水对应库存是否有记录
            String sql = " SELECT COUNT(*) FROM VC_STORAGE t " + " WHERE t.doc_ver_code = ? "
                    + " AND t.press_batch_no = ? " + " AND t.doc_status IN ('S1','S2','S3') " + " AND t.org_code = ? "
                    + " AND((T.START_NUM <= ? AND T.END_NUM >= ? )" + "	OR (T.START_NUM <= ? AND T.END_NUM >= ? ))";

            // 判断起止流水号对应库存信息有没有对应的记录
            Object[] objs = { vcDocTakerIo.getDocVerCode(), vcDocTakerIo.getPressBatchNo(),
                    vcDocTakerIo.getAcceptOrgCode(), startStr, startStr, endStr, endStr };
            int amount = Integer.valueOf(vcStorageDao.findBySQL(sql, objs).get(0).toString());
            if (amount > 0) {
                errors.append("流水号[" + startStr + "-" + endStr + "]对应库存有记录，数据错误");
            }
            QueryRule queryRule = QueryRule.getInstance();
            queryRule.addEqual("docStatus", "A");
            Object[] values = { startStr, endStr };
            queryRule.addBetween("docNum", values);
            queryRule.addEqual("docVerCode", vcDocTakerIo.getDocVerCode());
            queryRule.addEqual("pressBatchNo", vcDocTakerIo.getPressBatchNo());
            queryRule.addEqual("takerCode", takerCode);
            List<VcAvailableDoc> vcAvailableDocs = vcAvailableDocDao.find(queryRule);
            // 判断库存是否存在
            int amount_ = vcAvailableDocs.size();
            if (end - start + 1 == amount_) {
                // 保存vcDocTakerIo
                vcDocTakerIo.setStartNum(startStr);
                vcDocTakerIo.setEndNum(endStr);
                // vcDocTakerIoDao.save(vcDocTakerIo);
                // 删除 VC_AVAILABLE_DOC 可用单证记录
                // vcAvailableDocDao.deleteAll(vcAvailableDocs);

                // 增加VC_STORAGE库存记录
                List mergeVcStorage = new ArrayList();
                mergeVcStorage.add(vcDocTakerIo);
                mergeVcStorage.add(vcAvailableDocs);
                mergeVcStorages.add(mergeVcStorage);
            } else {
                errors.append("操作失败，单证状态不对或单证不存在 ");
            }
        }
        if (errors.length() == 0) {
            mergeVcStorage(mergeVcStorages);
        } else {
            return errors.toString();
        }
        }catch(DaoException e){
        	throw new BusinessException(e.getMessage(),e);
        }catch(Exception e){
        	throw new BusinessException(e.getMessage(),e);
        }
        return "操作成功";
    }

    /**
     * 合并库存记录
     * 
     * @param vcDocTakerIo
     */
    public void mergeVcStorage(List mergeVcStorages) throws BusinessException{
        // 需要删除的VcAvailableDoc
        List<VcAvailableDoc> vcAvailableDocsDel = new ArrayList<VcAvailableDoc>();
        // 需要保存的VcDocTakerIo
        List<VcDocTakerIo> vcDocTakerIos = new ArrayList<VcDocTakerIo>();
        try{
        for (Iterator iterator = mergeVcStorages.iterator(); iterator.hasNext();) {
            List mergeVcStorage = (List) iterator.next();
            vcAvailableDocsDel.addAll((Collection<? extends VcAvailableDoc>) mergeVcStorage.get(1));
            VcDocTakerIo vcDocTakerIo = (VcDocTakerIo) mergeVcStorage.get(0);
            vcDocTakerIos.add(vcDocTakerIo);
            String start = vcDocTakerIo.getStartNum();
            String end = vcDocTakerIo.getEndNum();

            // 合并S3的库存
           /* vcStorageDao.mergeStorage(start, end, vcDocTakerIo.getDocVerCode(), vcDocTakerIo.getPressBatchNo(),
                    vcDocTakerIo.getAcceptOrgCode(), "S3", null, vcDocTakerIo.getOprCode()); */
            vcStorageDao.mergeStorage(start, end, vcDocTakerIo.getDocVerCode(), vcDocTakerIo.getPressBatchNo(),
                    vcDocTakerIo.getProvideOrgCode(), "S3", null, vcDocTakerIo.getOprCode(),null);
            

        }
        vcAvailableDocDao.deleteAll(vcAvailableDocsDel);
        vcDocTakerIoDao.saveAll(vcDocTakerIos);
        }catch(DaoException e){
        	throw new BusinessException(e.getMessage(),e);
        }
    }

    /**
     * 获取最长库存时间
     * 
     * @param docVerCode
     * @param orgCode
     * @return
     */
    @Override
    public int getMaxStoreTime(String docVerCode, String orgCode) throws BusinessException{
    	VcDocMngRule rule=null;
    	try{
	        rule = vcDocMngRuleDao.getRuleByQueryMaxStoreTime(docVerCode, "1", orgCode);
	        if (rule == null) {
	            return 0;
	        }
    	}catch(DaoException e){
    		throw new BusinessException(e.getMessage(),e);
    	}
        return rule.getMaxStoreTime();
    }

    public void setVcStorageDao(VcStorageDao vcStorageDao) {
        this.vcStorageDao = vcStorageDao;
    }

    public void setVcDocTakerIoDao(VcDocTakerIoDao vcDocTakerIoDao) {
        this.vcDocTakerIoDao = vcDocTakerIoDao;
    }

    public void setVcAvailableDocDao(VcAvailableDocDao vcAvailableDocDao) {
        this.vcAvailableDocDao = vcAvailableDocDao;
    }

    public void setVcDocVersionInfoDao(VcDocVersionInfoDao vcDocVersionInfoDao) {
        this.vcDocVersionInfoDao = vcDocVersionInfoDao;
    }

    public void setVcDocMngRuleDao(VcDocMngRuleDao vcDocMngRuleDao) {
        this.vcDocMngRuleDao = vcDocMngRuleDao;
    }

    /**
     * @param vcTakerDao the vcTakerDao to set
     */
    public void setVcTakerDao(VcTakerDao vcTakerDao) {
        this.vcTakerDao = vcTakerDao;
    }

	
}
