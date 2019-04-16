package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.dao.DocNumStatusInquiryDao;

public class DocNumStatusInquiryDaoImpl extends GenericDaoHibernate<VcAvailableDoc> implements DocNumStatusInquiryDao {
     
	@Override
	public VcAvailableDoc docNumStatusInquiry(String docVerCode, String docNum,
			 String takerCode, String orgCode, String agentCode, String pressBatchNo)
			throws DaoException {
		VcAvailableDoc vcAvailableDoc = null;
		StringBuffer sb=new  StringBuffer();
        List values = new ArrayList();
		sb.append(" from  VcAvailableDoc d where 1=1 ");
		sb.append(" and  d.docVerCode=?");
		values.add(docVerCode);
     //	sb.append(" and d.docStatus='A'");
		//add by chy 20131017 增加印刷批次 begin
		sb.append(" and d.pressBatchNo=?");
		values.add(pressBatchNo);
		//add by chy 20131017 增加印刷批次 end
     	sb.append(" and d.docNum=?");
     	values.add(docNum);
     	if(!StringUtils.isEmpty(takerCode)){
     		sb.append(" and d.takerCode=?");
         	values.add(takerCode);
     	}
     	//外部接口新增中介机构字段，可为空
     	if(!StringUtils.isEmpty(agentCode)){
     		sb.append(" and agentCode=?");
         	values.add(agentCode);
     	}
     	sb.append(" and d.orgCode=?");
     	values.add(orgCode);
		try{
		   List<VcAvailableDoc> list=this.findByHql(sb.toString(),values.toArray());
		   if(list.size()>0 &&  list!=null){
			   vcAvailableDoc=list.get(0);
		   }
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("查询可使用单证表时异常！");
		}
		return vcAvailableDoc; 
  }
}
