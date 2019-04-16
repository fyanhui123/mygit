package com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.schema.model.PubRisk;
import com.tapi.tcs.vc.schema.model.PubRiskRation;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesRecord;
import com.tapi.tcs.vc.schema.model.VcInsuranceCard;
import com.tapi.tcs.vc.schema.model.VcRiskRation;
import com.tapi.tcs.vc.schema.model.VcTaker;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.PlanDTO;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.RiskDTO;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.dao.VcInsuCardInquiryDao;

public class VcInsuCardInquiryDaoImpl extends GenericDaoHibernate<VcInsuranceCard> implements VcInsuCardInquiryDao {

	@Override
	public Object[] findVcInsuranceCard(String cardNO, String encryptedPW) throws DaoException{
		Object[] obj = null;
		List<Object[]> list = null;
		try{
			StringBuffer hql = new StringBuffer();
			hql.append(" from VcInsuranceCard card,VcDocVersionInfo doc");
			hql.append(" where card.docVerCode = doc.docVerCode");
			hql.append(" and card.cardNo=?");
			if(StringUtils.isNotEmpty(encryptedPW)){
				hql.append(" and card.encryptedPw=?");
				list = this.findByHql(hql.toString(), new Object[]{cardNO, encryptedPW});
			}else{
				list = this.findByHql(hql.toString(), new Object[]{cardNO});
			}
			if(list!=null && list.size()>0){
				obj = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询激活卡数据时出错了！" ,e);
		}
		return obj;
	}
	
	@Override
	public VcTaker findVcTaker(String docVerCode, String docNum) throws DaoException {
		VcTaker vcTaker = null;
		try{
			StringBuffer hql = new StringBuffer("");
			hql.append(" from VcTaker taker");
			hql.append(" where exists (");
			hql.append(" select 1 from VcAvailableDoc a where a.takerCode=taker.takerCode");
			hql.append(" and a.docVerCode=?");
			hql.append(" and a.docNum=?)");
			List<VcTaker> list = (List<VcTaker>)this.findByHql(hql.toString(), new Object[]{docVerCode,docNum});
			if(list!=null && list.size()>0){
				vcTaker = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询销售信息出错了！", e);
		}
		return vcTaker;
	}
	
	@Override
	public VcInsuCardSalesRecord findSalesInfo(String docVerCode, String docNum) throws DaoException {
		VcInsuCardSalesRecord vcInsuCardSalesRecord = null;
		try{
			StringBuffer hql = new StringBuffer("");
			hql.append(" from VcInsuCardSalesRecord record");
			hql.append(" where record.saleRefundFlag='P' and record.salesRecordStatus='4' ");
			hql.append(" and exists(select 1 from VcInsuCardSalesDetail detail");
			hql.append(" where record.idVcInsuCardSalesRecord=detail.idVcInsuCardSalesRecord");
			hql.append(" and detail.docVerCode=? and detail.startNum<=? and detail.endNum>=?)");
			//VC-82 激活卡退卡再销售，系统验证激活卡销售信息仍为初次销售信
			//排序，取最近销售的记录
			hql.append(" order by record.saleDate desc ");
			List<VcInsuCardSalesRecord> list = (List<VcInsuCardSalesRecord>)this
				.findByHql(hql.toString(), docVerCode,docNum,docNum);
			if(list!=null && list.size()!=0){
				vcInsuCardSalesRecord = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询销售信息出错了！", e);
		}
		return vcInsuCardSalesRecord;
	}
	
	@Override
	public List<RiskDTO> findRiskDTOs(Long idVcDocVersionInfo) throws DaoException {
		List<RiskDTO> riskDTOs = new ArrayList<RiskDTO>();
		try{
			StringBuffer hql1 = new StringBuffer("");
			hql1.append(" from PubRisk risk where exists (");
			hql1.append(" select 1 from VcDocRelInsuKind kind where kind.insuKindCode=risk.riskCode");
			hql1.append(" and kind.idVcDocVersionInfo=?");
			hql1.append(" )");
			List<PubRisk> riskList = this.findByHql(hql1.toString(), idVcDocVersionInfo);
			RiskDTO riskDTO = null;
			PlanDTO planDTO = null;
			if(riskList!=null && riskList.size()>0){
				for(PubRisk pubRisk : riskList){
					riskDTO = new RiskDTO();
					riskDTO.setRiskCode(pubRisk.getRiskCode());
					riskDTO.setRiskName(pubRisk.getRiskCname());
					
					//modify by chy 20131014 在单证新增险种定额关系表 begin
					//查询险种对应的方案
					/*StringBuffer hql2 = new StringBuffer("");
					hql2.append(" from PubRiskRation riskRation where ");
					hql2.append(" riskRation.id.riskCode=?");
					List<PubRiskRation> riskRationList = this.findByHql(hql2.toString(), new Object[]{pubRisk.getRiskCode()});
					if(riskRationList!=null && riskRationList.size()>0){
						List<PlanDTO> planDTOs = new ArrayList<PlanDTO>();
						for(PubRiskRation riskRation : riskRationList){
							planDTO = new PlanDTO();
							planDTO.setRationType(riskRation.getRationType());
							planDTO.setRationName(riskRation.getRationName());
							planDTOs.add(planDTO);
						}
						riskDTO.setPlanDTOs(planDTOs);
					}*/
					QueryRule queryRule = QueryRule.getInstance();
					queryRule.addEqual("riskCode", pubRisk.getRiskCode());
					//单证类型id
					queryRule.addEqual("idVcDocVersionInfo", idVcDocVersionInfo);
					queryRule.addEqual("status", "1");
					List<VcRiskRation> vcRiskRationList = (List<VcRiskRation>)this.find(VcRiskRation.class, queryRule);
					if(vcRiskRationList!=null && vcRiskRationList.size()!=0){
						List<PlanDTO> planDTOs = new ArrayList<PlanDTO>();
						for(VcRiskRation vcRiskRation : vcRiskRationList){
							planDTO = new PlanDTO();
							planDTO.setRationType(vcRiskRation.getRationType());
							planDTO.setRationName(vcRiskRation.getRationName());
							planDTOs.add(planDTO);
						}
						riskDTO.setPlanDTOs(planDTOs);
					}
					//modify by chy 20131014 在单证新增险种定额关系表 end
					riskDTOs.add(riskDTO);
				}
			}
		}catch(Exception e){
			throw new DaoException("查询产品数据出错！", e);
		}
		return riskDTOs;
	}
}
