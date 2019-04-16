/**
 * 
 */
package com.tapi.tcs.vc.pub.service;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.platform.schema.vo.CodeLabelDto;
import com.tapi.tcs.vc.schema.model.PubBanklocations;
import com.tapi.tcs.vc.schema.model.PubBanks;
import com.tapi.tcs.vc.schema.model.PubStandardareas;

/**
 * 银行信息service
 * 
 * @author whj
 *
 */
public interface BankManagerService {
    /**
     * 根据条件查询银行list
     */   
    public  List<CodeLabelDto> queryPubBanksList(PubBanks banksVo) throws BusinessException ;
    
    /**
     * 根据条件查询区域list
     */   
    public  List<CodeLabelDto> queryPubStandardareasList(PubStandardareas query) throws BusinessException ;

    
    /**
     * 根据条件查询银行支行list
     */   
    public  List<CodeLabelDto> queryPubBanklocationsList(PubBanklocations query) throws BusinessException ;


}
