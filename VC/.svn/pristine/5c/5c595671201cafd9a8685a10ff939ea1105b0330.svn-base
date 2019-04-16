package com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcDocRelInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.bean.DocNumInquiryNewRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.bean.DocNumInquiryNewResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.bean.SingleLibEntity;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.dao.QueryAvailableNumNewDao;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.service.QueryAvailableNumNewService;

public class QueryAvailableNumNewServiceImpl implements QueryAvailableNumNewService {

	private QueryAvailableNumNewDao queryAvailableNumNewDao;
	
	@Override
	public DocNumInquiryNewResponseDTO availableNumInquiryNew(
			DocNumInquiryNewRequestDTO requestBody) throws BusinessException {
		DocNumInquiryNewResponseDTO responseBody = new DocNumInquiryNewResponseDTO();
		List<SingleLibEntity> singleLibEntityList = new ArrayList<SingleLibEntity>();
		try{
			SingleLibEntity requestObj = requestBody.getSingleLibEntity();
			int currentPage = requestObj.getCurrentpage();
			int pageSize = requestObj.getPagesize();
			String docVerCode = requestObj.getDocVerCode();
			Date lastSynTime = requestObj.getLastSynTime();
			String takerCode = "";
			if(StringUtils.isNotBlank(requestObj.getOpAndProxyId())){
				takerCode = requestObj.getOpAndProxyId();
			}
			
			//分页查询可使用流水号
			Page page = queryAvailableNumNewDao.queryVcAvailableDocNew(currentPage, pageSize,lastSynTime, docVerCode, takerCode);
			if(page!=null){
				List<Object[]> vcAvailableDocList = (List<Object[]>)page.getResult();
				int currentpage = (int)page.getCurrentPageNo();
				int pagesize = page.getPageSize();
				int totalcount = (int)page.getTotalCount();
				Date deadLine = new Date();
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
						deadLine = vcAvailableDoc.getDeadline();
						StringBuffer sb = new StringBuffer();
						String endNum = startNum;
						//从当前位置的下一个开始内层循环
						j=i+1;
						while(j<size){
							Object[] obj2 = vcAvailableDocList.get(j);
							VcAvailableDoc vcAvailableDoc2 = (VcAvailableDoc)obj2[0];
							String nextNum = vcAvailableDoc2.getDocNum();
							String orgCode2 = vcAvailableDoc2.getOrgCode();
							String takerCode2 = vcAvailableDoc2.getTakerCode();
							//如果当前流水号等于前一个流水号+1，则结束流水号等于当前流水号；且继续遍历下一个
							//if(Integer.parseInt(nextNum)==(Integer.parseInt(endNum)+1)
							if(Long.valueOf(nextNum)==(Long.valueOf(endNum)+1)
									&&StringUtils.equals(orgCode1,orgCode2)
									&&StringUtils.equals(takerCode1, takerCode2)){
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
						resultObj.setDeadLine(deadLine);//使用截止期
						resultObj.setDocVerCode(docVerCode);//单证类型代码
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

	public void setQueryAvailableNumNewDao(QueryAvailableNumNewDao queryAvailableNumNewDao) {
		this.queryAvailableNumNewDao = queryAvailableNumNewDao;
	}
}
