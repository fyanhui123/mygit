package com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.dao.impl;

import java.util.Date;
import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcInsuranceCard;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardInfoDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.dao.InsuranceActivatedDao;

public class InsuranceActivatedDaoImpl extends GenericDaoHibernate<VcInsuranceCard> implements InsuranceActivatedDao {

	public Object[]  findInsuranceCardInfo(InsuranceCardInfoDTO card) throws DaoException {
		Object[] obj = null;
		//   单证可使用      状态为A、使用截止日期大于当前日期
		try {
			StringBuffer sb=new  StringBuffer();
			sb.append("from  VcInsuranceCard t,VcAvailableDoc d ");
			sb.append(" where t.docVerCode=d.docVerCode and d.docNum=t.cardNo ");
			sb.append(" and t.docVerCode=?");
			sb.append(" and t.cardNo=?");
	     	sb.append(" and d.docStatus='A'");
			List<Object[]>  list=this.findByHql(sb.toString(),new Object[]{card.getDocVerCode(),card.getCardNO()} );
			if(list!=null && list.size()>0 ){
				obj=list.get(0); 
			}
		}catch (Exception e) {
			throw  new  DaoException("查询激活卡信息出错！",e);
		}
		return obj;
	}
   
	
	/**
     * 激活卡信息表锁记录操作
     * 
     * @param InsuranceCardInfoDTO 激活卡信息（单证类型、卡号）
     */
    @Override
    public void lockInsuranceCard(InsuranceCardInfoDTO card) throws DaoException{
        try{
            Object[] obj = {card.getDocVerCode(),card.getCardNO()};
            this.findBySql("SELECT * FROM VC_INSURANCE_CARD C WHERE C.DOC_VER_CODE = ?  AND  C.CARD_NO=?  FOR UPDATE NOWAIT", obj);
        }catch(Exception e){
               throw new DaoException("激活卡激活操作数据库异常！",e);
         }
    }
}
