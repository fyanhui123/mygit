/**
 * 
 */
package com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.service;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidRequest;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidResultDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidorRefundDTO;

/**
 * @author whj
 *
 */
public interface VcInsuranceCardDoPaidService {
    
    public List<InsuranceCardDoPaidResultDTO> executeDoPaidOrRefund(InsuranceCardDoPaidRequest request)throws BusinessException;
    
    /**
     * 处理收付缴费/退费信息回写单证（每个业务号作为一个事务进行处理，支持部分成功部分失败）
     * @param request
     * @param dto
     * @return
     * @throws BusinessException
     * @author chy
     * @date 2013-10-15
     */
    public InsuranceCardDoPaidResultDTO executeDoPaidOrRefund(InsuranceCardDoPaidRequest request,
    		InsuranceCardDoPaidorRefundDTO dto) throws BusinessException;
}
