package com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcBaseInfoDao;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.bean.DocNumInquiryOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.bean.DocNumInquiryOldResponse;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.bean.DocNumInquiryOldResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.dao.DocNumQueryDao;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.service.DocNumQueryService;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.vo.DocNumInquiryRequestVO;

public class DocNumQueryServiceImpl implements DocNumQueryService {

	private DocNumQueryDao docNumQueryDao;

	public void setDocNumQueryDao(DocNumQueryDao docNumQueryDao) {
		this.docNumQueryDao = docNumQueryDao;
	}

	private VcBaseInfoDao vcBaseInfoDao;

	public void setVcBaseInfoDao(VcBaseInfoDao vcBaseInfoDao) {
		this.vcBaseInfoDao = vcBaseInfoDao;
	}

	@Override
	public DocNumInquiryOldResponse vcAvailableQuery(DocNumInquiryOldRequestDTO request) throws BusinessException {
		// 根据请求体内容查询、返回内容
		List list = new ArrayList();

		// 把请求体转换成VO对象
		DocNumInquiryRequestVO requestVo = translateRequestVo(request);

		list = docNumQueryDao.vcAvailableQuery(requestVo);
		
		DocNumInquiryOldResponseDTO responseBody = null;
		VcAvailableDoc vcAvailableDoc = null;
		VcAvailableDoc nextVcAvailableDoc = null;
		VcDocVersionInfo vcDocVersionInfo = null;
		List<DocNumInquiryOldResponseDTO> docList = new ArrayList<DocNumInquiryOldResponseDTO>();
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		DocNumInquiryOldResponse response = new DocNumInquiryOldResponse();
		Object[] obj = null;
		Object[] nextObj = null;
		if (list != null && list.size() > 0) {
			String starNum = "";
			String endNum = "";
			int i = 0;
			int local;
			// for(int i=0;i<list.size();i++){
			while (i < list.size()) {
				local = i + 1;
				responseBody = new DocNumInquiryOldResponseDTO();
				// 第一个实体VcAvailableDoc
				obj = (Object[]) list.get(i);
				vcAvailableDoc = (VcAvailableDoc) obj[0];
				// 第二个实体VcDocVersionInfo
				vcDocVersionInfo = (VcDocVersionInfo) obj[1];

				starNum = vcAvailableDoc.getDocNum();
				endNum = starNum;
				while (local < list.size()) {
					nextObj = (Object[]) list.get(local);
					nextVcAvailableDoc = (VcAvailableDoc) nextObj[0];
					if (Long.valueOf(endNum) + 1 == Long
							.valueOf(nextVcAvailableDoc.getDocNum())) {
						endNum = nextVcAvailableDoc.getDocNum();
						local++;
					}
					// 流水号段不连续中断、把起始到当前流水为一号段 分批存储
					else {
						// 单证类型
						String docVerID = vcDocVersionInfo.getIdVcDocVersionInfo().toString();
						responseBody.setDocVerID(docVerID);
						// 单证类型名称
						responseBody.setDocVerName(vcDocVersionInfo.getDocVerName());

						responseBody.setStartNum(starNum);
						responseBody.setEndNum(endNum);

						// 操作员ID
						responseBody.setOperatorID(request.getOperatorID());
						// 使用截止期
						responseBody.setDeadLine(vcAvailableDoc.getDeadline());
						// 发票代码
						responseBody.setPressBatchNo(vcAvailableDoc.getPressBatchNo());
						// 业务号
						responseBody.setBusinessNo("");
						// 版本
						responseBody.setVersion("");
						// 纳税人电脑编码
						responseBody.setTaxpayerComputerCode("");
						docList.add(responseBody);
						i = local;
						break;
					}
				}
				// 流水号段是连续的（一条或者多条都要执行该过程、一条执行执行、多条则是先分割最后还得执行最后一个号段）
				if (local >= list.size()) {
					// 单证类型
					//String docVerID = vcDocVersionInfo.getDocVerCode();
					String docVerID = vcDocVersionInfo.getIdVcDocVersionInfo().toString();
					responseBody.setDocVerID(docVerID);
					// 单证类型名称
					responseBody.setDocVerName(vcDocVersionInfo.getDocVerName());

					responseBody.setStartNum(starNum);
					responseBody.setEndNum(endNum);

					// 操作员ID
					responseBody.setOperatorID(request.getOperatorID());
					// 使用截止期
					responseBody.setDeadLine(vcAvailableDoc.getDeadline());
					// 发票代码
					responseBody.setPressBatchNo(vcAvailableDoc
							.getPressBatchNo());
					// 业务号
					responseBody.setBusinessNo("");
					// 版本
					responseBody.setVersion("");
					// 纳税人电脑编码
					responseBody.setTaxpayerComputerCode("");
					docList.add(responseBody);
					i = local;
				}
			}
			responseHead.setResponseCode("000");
			responseHead.setResponseMessage("查询成功！");
			response.setResponseHead(responseHead);
			response.setResponseBody(docList);
		} else {
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage("没有查询到相关数据！");
			response.setResponseHead(responseHead);
			return response;
		}
		return response;
	}

	/**
	 * 把requestBody转换成requestVo
	 * 
	 * @param request
	 * @return requestVo
	 * @throws Exception
	 */
	private DocNumInquiryRequestVO translateRequestVo(
			DocNumInquiryOldRequestDTO request) throws BusinessException {
		DocNumInquiryRequestVO requestVo = new DocNumInquiryRequestVO();
		try {
			// 转换单证类型代码
			String docVerCode = vcBaseInfoDao.getDocVerCode(request.getDocVerID().longValue());
			// 转换操作员代码
			String operatorCode = vcBaseInfoDao.translateUserCode(request.getOperatorID());
			// 转换机构代码
			//String orgCode = vcBaseInfoDao.translateOrgCode(Long.parseLong(request.getOrgID()));
			String orgCode = vcBaseInfoDao.translateOrgCode(request.getDepID(), request.getDepCode());
			if (StringUtils.isEmpty(orgCode)) {
				throw new BusinessException("机构代码不存在！");
			}
			// 发票版本
			String invoiceVersion = request.getInvoiceVersion();

			requestVo.setDocVerCode(docVerCode);
			requestVo.setOperatorCode(operatorCode);
			requestVo.setOrgCode(orgCode);
			requestVo.setInvoiceVersion(invoiceVersion);

		} catch (DaoException de) {
			de.printStackTrace();
			throw new BusinessException(de.getMessage());
		}
		return requestVo;
	}
}
