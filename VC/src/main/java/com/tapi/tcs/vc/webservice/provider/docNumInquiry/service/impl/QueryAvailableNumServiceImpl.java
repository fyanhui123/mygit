package com.tapi.tcs.vc.webservice.provider.docNumInquiry.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.webservice.provider.docNumInquiry.bean.DocNumInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumInquiry.bean.DocNumInquiryResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docNumInquiry.bean.SingleLibEntity;
import com.tapi.tcs.vc.webservice.provider.docNumInquiry.dao.QueryAvailableNumDao;
import com.tapi.tcs.vc.webservice.provider.docNumInquiry.service.QueryAvailableNumService;

public class QueryAvailableNumServiceImpl implements QueryAvailableNumService {

	private QueryAvailableNumDao queryAvailableNumDao;
	
	@Override
	public DocNumInquiryResponseDTO availableNumInquiry(
			DocNumInquiryRequestDTO requestBody) throws BusinessException {
		DocNumInquiryResponseDTO responseBody = new DocNumInquiryResponseDTO();
		List<SingleLibEntity> singleLibEntityList = new ArrayList<SingleLibEntity>();
		try{
			SingleLibEntity requestObj = requestBody.getSingleLibEntity();
			int currentPage = requestObj.getCurrentpage();
			int pageSize = requestObj.getPagesize();
			Date lastSynTime = requestObj.getLastSynTime();
			//分页查询可使用流水号
			Page page = queryAvailableNumDao.queryVcAvailableDoc(currentPage, pageSize,lastSynTime);
			if(page!=null){
				List<Object[]> vcAvailableDocList = (List<Object[]>)page.getResult();
				int currentpage = (int)page.getCurrentPageNo();
				int pagesize = page.getPageSize();
				int totalcount = (int)page.getTotalCount();
				if(vcAvailableDocList!=null || vcAvailableDocList.size()!=0){
					int size = vcAvailableDocList.size();
					int i=0;
					int j=0;
					while(i<size){
						Object[] obj1 = vcAvailableDocList.get(i);
						VcAvailableDoc vcAvailableDoc = (VcAvailableDoc)obj1[0];
						VcDocVersionInfo vcDocVersionInfo = (VcDocVersionInfo)obj1[1];
						String startNum = vcAvailableDoc.getDocNum();
						String orgCode1 = vcAvailableDoc.getOrgCode();
						String takerCode1 = vcAvailableDoc.getTakerCode();
						String versionId1 = vcDocVersionInfo.getIdVcDocVersionInfo()+"";
						
						String endNum = startNum;
						//从当前位置的下一个开始内层循环
						j=i+1;
						while(j<size){
							Object[] obj2 = vcAvailableDocList.get(j);
							VcAvailableDoc vcAvailableDoc2 = (VcAvailableDoc)obj2[0];
							VcDocVersionInfo vcDocVersionInfo2 = (VcDocVersionInfo)obj2[1];
							String nextNum = vcAvailableDoc2.getDocNum();
							String orgCode2 = vcAvailableDoc2.getOrgCode();
							String takerCode2 = vcAvailableDoc2.getTakerCode();
							String versionId2 = vcDocVersionInfo2.getIdVcDocVersionInfo()+"";
							//如果当前流水号等于前一个流水号+1，则结束流水号等于当前流水号；且继续遍历下一个
							//if(Integer.parseInt(nextNum)==(Integer.parseInt(endNum)+1)
							if(Long.valueOf(nextNum)==(Long.valueOf(endNum)+1)
									&&StringUtils.equals(orgCode1,orgCode2)
									&&StringUtils.equals(takerCode1, takerCode2)
									&&StringUtils.equals(versionId1, versionId2)){
								endNum = nextNum;
								j++;
								continue;
							}
							//否则外层循环从当前下标（内层）继续
							else{
								i=j;
								break;
							}
						}
						//组织返回对象
						SingleLibEntity resultObj = new SingleLibEntity();
						resultObj.setSingleTypeId(versionId1);//单证类型id
						resultObj.setSingleStartNum(startNum);//起始流水号
						resultObj.setSingleEndNum(endNum);//结束流水号
						resultObj.setDeptId(orgCode1);//机构代码
						resultObj.setOpAndProxyId(takerCode1);//领用人代码
						resultObj.setCurrentpage(currentpage);//当前页
						resultObj.setPagesize(pagesize);//每页大小
						resultObj.setCountRecord(totalcount);//总记录数
						resultObj.setLastSynTime(null);//上次同步时间
						singleLibEntityList.add(resultObj);
						//若最后一个也是连续，则退出外层循环
						if(j>=size){
							break;
						}
					}
					responseBody.setSingleLibEntityList(singleLibEntityList);
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("查询失败！", e);
		}
		return responseBody;
	}

	public void setQueryAvailableNumDao(QueryAvailableNumDao queryAvailableNumDao) {
		this.queryAvailableNumDao = queryAvailableNumDao;
	}
}
