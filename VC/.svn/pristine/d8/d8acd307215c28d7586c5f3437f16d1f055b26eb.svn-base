package com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tapi.tcs.common.webservice.RequestHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.insucard.service.InsuCardSalesService;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesRecord;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidRequest;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidRequestDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidResultDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidorRefundDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.service.VcInsuranceCardDoPaidService;

public class VcInsuranceCardDoPaidServiceImpl implements VcInsuranceCardDoPaidService {

    /**
     * 激活卡销售service
     */
    private InsuCardSalesService insuCardSalesService;

    /**
     * @param insuCardSalesService
     *            the insuCardSalesService to set
     */
    public void setInsuCardSalesService(InsuCardSalesService insuCardSalesService) {
        this.insuCardSalesService = insuCardSalesService;
    }

    /**
     * 激活卡信息查询接口入口程序
     */
    @Override
    public List<InsuranceCardDoPaidResultDTO> executeDoPaidOrRefund(InsuranceCardDoPaidRequest request)
            throws BusinessException {
        InsuranceCardDoPaidRequestDTO requestBody = request.getRequestBody();
        List<InsuranceCardDoPaidorRefundDTO> list = requestBody.getInsuranceCardDoPaidorRefundList();
        if (list == null || list.size() < 1) {
            throw new BusinessException("请求体收付费信息列表为空！");
        }

        List<InsuranceCardDoPaidResultDTO> dealResultList = new ArrayList<InsuranceCardDoPaidResultDTO>();
        InsuranceCardDoPaidResultDTO dealRresultDto = null;

        RequestHeadDTO head = request.getRequestHead();
        UserInfo operater = new UserInfo();
        operater.setUserCode(head.getUser());
        VcInsuCardSalesRecord vcInsuCardSalesRecord = null;
        for (InsuranceCardDoPaidorRefundDTO dto : list) {
            dealRresultDto = new InsuranceCardDoPaidResultDTO();
            dealResultList.add(dealRresultDto);
            dealRresultDto.setBusinessNoDTOs(dto.getBusinessNoDTOs());
            try {
                vcInsuCardSalesRecord = insuCardSalesService.getVcInsuCardSalesRecordByRecordCode(dto
                        .getBusinessNoDTOs());
                if (vcInsuCardSalesRecord == null) {
                    // throw new BusinessException("销售记录【" + dto.getBusinessNoDTOs() + "】不存在");
                    dealRresultDto.setStatus("0");
                    dealRresultDto.setFlag("销售记录【" + dto.getBusinessNoDTOs() + "】不存在");
                    continue;
                }
                if ("P".equals(dto.getPayOrRefund()) && "R".equals(dto.getPayOrRefund())) {
                    dealRresultDto.setStatus("0");
                    dealRresultDto.setFlag("销售记录【" + dto.getBusinessNoDTOs() + "】缴费退费标志异常");
                    continue;
                }
                // 缴费退费标志(P：缴费 R：退费)
                if ("P".equals(dto.getPayOrRefund())) {
                    // 缴费成功
                    if ("1".equals(dto.getPaidStatus())) {
                        // 缴费处理成功后，变更销售申请状态，变更激活卡状态，减少单证库存，单证状态变更为可使用。
                        insuCardSalesService.executeAfterPayFeeSuccess(vcInsuCardSalesRecord
                                .getIdVcInsuCardSalesRecord(), operater);
                        dealRresultDto.setStatus("1");
                        dealRresultDto.setFlag("销售记录【" + dto.getBusinessNoDTOs() + "】缴费回写单证成功");
                    }
                } else {
                    // 退费成功
                    if ("1".equals(dto.getPaidStatus())) {
                        // 退费处理成功后，变更回收申请状态，变更激活卡状态，增加库存，单证状态变更为在库存
                        insuCardSalesService.executeAfterRefundSuccess(vcInsuCardSalesRecord.getIdVcInsuCardSalesRecord(),
                                operater);
                        dealRresultDto.setStatus("1");
                        dealRresultDto.setFlag("销售记录【" + dto.getBusinessNoDTOs() + "】退费回写单证成功");
                    } else {
                        // FailReason: 退费失败原因代码 (01-账号不存在或账号停用 09-其他非账户原因)
                        if ("01".equals(dto.getFailReason())) {
                            // 退费失败后，变更回收申请状态，变更激活卡状态，增加库存，单证状态变更为在库存
                            insuCardSalesService.executeAfterRefundFailed(vcInsuCardSalesRecord.getIdVcInsuCardSalesRecord(),
                                    dto.getFailReason(),dto.getFailDesc(), operater);
                        }
                    }
                }
            } catch (BusinessException be) {
                dealRresultDto.setStatus("0");
                dealRresultDto.setFlag(be.getMessage());
                continue;
            } catch (Exception e) {
                dealRresultDto.setStatus("0");
                dealRresultDto.setFlag("处理异常：" + e.getMessage());
                continue;
            }
        }

        // return "000";
        return dealResultList;
    }
    
    @Override
	public InsuranceCardDoPaidResultDTO executeDoPaidOrRefund(
			InsuranceCardDoPaidRequest request,
			InsuranceCardDoPaidorRefundDTO dto) throws BusinessException {
		InsuranceCardDoPaidResultDTO dealRresultDto = new InsuranceCardDoPaidResultDTO();
		try {
			RequestHeadDTO head = request.getRequestHead();
			UserInfo operater = new UserInfo();
			operater.setUserCode(head.getUser());
			dealRresultDto.setBusinessNoDTOs(dto.getBusinessNoDTOs());
			VcInsuCardSalesRecord vcInsuCardSalesRecord = insuCardSalesService
					.getVcInsuCardSalesRecordByRecordCode(dto
							.getBusinessNoDTOs());
			if (vcInsuCardSalesRecord == null) {
				throw new BusinessException("销售记录【" + dto.getBusinessNoDTOs()
						+ "】不存在");
			}
			// 缴费退费标志(P：缴费 R：退费)
			if ("P".equals(dto.getPayOrRefund())) {
				// 缴费成功
				if ("1".equals(dto.getPaidStatus())) {
					// 缴费处理成功后，变更销售申请状态，变更激活卡状态，减少单证库存，单证状态变更为可使用。
					insuCardSalesService.executeAfterPayFeeSuccess(
							vcInsuCardSalesRecord.getIdVcInsuCardSalesRecord(),
							operater);
					dealRresultDto.setStatus("1");
					dealRresultDto.setFlag("销售记录【" + dto.getBusinessNoDTOs()
							+ "】缴费回写单证成功");
				}
			} else {
				// 退费成功
				if ("1".equals(dto.getPaidStatus())) {
					// 退费处理成功后，变更回收申请状态，变更激活卡状态，增加库存，单证状态变更为在库存
					insuCardSalesService.executeAfterRefundSuccess(
							vcInsuCardSalesRecord.getIdVcInsuCardSalesRecord(),
							operater);
					dealRresultDto.setStatus("1");
					dealRresultDto.setFlag("销售记录【" + dto.getBusinessNoDTOs()
							+ "】退费回写单证成功");
				} else {
					// FailReason: 退费失败原因代码 (01-账号不存在或账号停用 09-其他非账户原因)
					if ("01".equals(dto.getFailReason())) {
						// 退费失败后，变更回收申请状态，变更激活卡状态，增加库存，单证状态变更为在库存
						insuCardSalesService.executeAfterRefundFailed(
								vcInsuCardSalesRecord
										.getIdVcInsuCardSalesRecord(), dto
										.getFailReason(), dto.getFailDesc(),
								operater);
					}
				}
			}
		} catch (BusinessException be) {
			throw new BusinessException(be.getMessage(), be);
		} catch (Exception e) {
			throw new BusinessException("处理异常：" + e.getMessage(), e);
		}
		return dealRresultDto;
	}
}
