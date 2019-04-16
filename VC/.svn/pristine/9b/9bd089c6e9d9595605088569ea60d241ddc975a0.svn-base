package com.tapi.tcs.vc.pub.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.platform.schema.vo.CodeLabelDto;
import com.tapi.tcs.vc.pub.dao.PubBanklocationsDao;
import com.tapi.tcs.vc.pub.dao.PubBanksDao;
import com.tapi.tcs.vc.pub.dao.PubStandardareasDao;
import com.tapi.tcs.vc.pub.service.BankManagerService;
import com.tapi.tcs.vc.schema.model.PubBanklocations;
import com.tapi.tcs.vc.schema.model.PubBanks;
import com.tapi.tcs.vc.schema.model.PubStandardareas;

/**
 * 银行信息Service实现
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
public class BankManagerServiceImpl implements BankManagerService {
    private PubBanksDao pubBanksDao;
    
    private PubStandardareasDao pubStandardareasDao;
    
    private PubBanklocationsDao pubBanklocationsDao;
    
    
    /**
     * @param pubBanksDao the pubBanksDao to set
     */
    public void setPubBanksDao(PubBanksDao pubBanksDao) {
        this.pubBanksDao = pubBanksDao;
    }

    /**
     * @param pubStandardareasDao the pubStandardareasDao to set
     */
    public void setPubStandardareasDao(PubStandardareasDao pubStandardareasDao) {
        this.pubStandardareasDao = pubStandardareasDao;
    }

    /**
     * @param pubBanklocationsDao the pubBanklocationsDao to set
     */
    public void setPubBanklocationsDao(PubBanklocationsDao pubBanklocationsDao) {
        this.pubBanklocationsDao = pubBanklocationsDao;
    }

    /**
     * 根据条件查询银行自動完成組件list
     */   
    public List<CodeLabelDto> queryPubBanksList(PubBanks banksVo) throws BusinessException{
        try{
        List<PubBanks> list=pubBanksDao.queryPubBanksList(banksVo);
       List<CodeLabelDto> codeLableLst = new ArrayList<CodeLabelDto>();
       for (PubBanks tempVo:list) {
           CodeLabelDto codeLabelDto = new CodeLabelDto();
           codeLabelDto.setValue(tempVo.getCode());
           //自动完成组件
           codeLabelDto.setLable(tempVo.getName());
           //下拉框组件
           codeLabelDto.setLabel(tempVo.getName());
           codeLableLst.add(codeLabelDto);
       }
       return codeLableLst;
    } catch (DaoException e) {
        throw new BusinessException("查询异常！", e);
    }
       
   
    }
    
    /**
     * 根据条件查询区域自動完成組件list
     */   
    public List<CodeLabelDto> queryPubStandardareasList(PubStandardareas query) throws BusinessException {      
       try{
           List<PubStandardareas> list=pubStandardareasDao.queryPubStandardareasList(query);
          List<CodeLabelDto> codeLableLst = new ArrayList<CodeLabelDto>();
          for (PubStandardareas tempVo:list) {
              CodeLabelDto codeLabelDto = new CodeLabelDto();
              codeLabelDto.setValue(tempVo.getCode());
              //自动完成组件
              codeLabelDto.setLable(tempVo.getName());
              //下拉框组件
              codeLabelDto.setLabel(tempVo.getName());
              codeLableLst.add(codeLabelDto);
          }
          return codeLableLst;
       } catch (DaoException e) {
           throw new BusinessException("查询异常！", e);
       }
    }

    
    /**
     * 根据条件查询银行支行自動完成組件list
     */   
    public List<CodeLabelDto> queryPubBanklocationsList(PubBanklocations query) throws BusinessException {      
       try{
          List<CodeLabelDto> codeLableLst = new ArrayList<CodeLabelDto>();
          Page page =pubBanklocationsDao.queryPubBanklocationsfeByPage(query,1,200);
          if(page==null || page.getResult()==null){
              return codeLableLst; 
          }
          List<PubBanklocations> list=(List<PubBanklocations>)page.getResult();
         
          for (PubBanklocations tempVo:list) {
              CodeLabelDto codeLabelDto = new CodeLabelDto();
              codeLabelDto.setValue(tempVo.getCode());
              //自动完成组件
              codeLabelDto.setLable(tempVo.getName());
              //下拉框组件
              codeLabelDto.setLabel(tempVo.getName());
              codeLableLst.add(codeLabelDto);
          }
          return codeLableLst;
       } catch (DaoException e) {
           throw new BusinessException("查询异常！", e);
       }
    }



   
}
