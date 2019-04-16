package com.tapi.tcs.vc.webservice.provider.outStorage.dao.impl;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.webservice.provider.outStorage.dao.OutStorageDao;
public class OutStorageDaoImpl extends GenericDaoHibernate <VcStorage> implements OutStorageDao {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryInvoice(String start, String end, String docVerCode, String pressBatchNo
	           )throws DaoException {
		List<Object> values =new ArrayList<Object>();
		List<Object> list=null;
		 if(start.length()!=end.length()){
	            throw new DaoException("起始号段" + start + "（长度：" + start.length() + "）与终止号段" + end + "（长度：" + end.length() + "）长度不等，请确认！");
	        }
	      try {
	    	    StringBuffer sb=new StringBuffer("select t.CREATE_TIME ,t.CREATE_USER_CODE,t.CREATED_BY,t.DATE_CREATED ,t.DATE_UPDATED ,t.DEADLINE , t.DOC_NUM , t.DOC_STATUS ,t.DOC_VER_CODE, t.END_NUM ,t.IN_STORE_TIME , t.MODIFY_TIME,t.MODIFY_USER_CODE, t.ORG_CODE ,t.PRESS_BATCH_NO,t.START_NUM ,t.UPDATED_BY from VC_STORAGE t where ");
	    	    sb.append(" t.DOC_VER_CODE = ? ");
	    	    values.add(docVerCode);
	    	    sb.append(" and t.PRESS_BATCH_NO = ? ");
	    	    values.add(pressBatchNo);
	    	    sb.append(" and t.DOC_STATUS in ('S1', 'S2', 'S3') ");
	    	    sb.append(" and t.START_NUM <= ? ");
	    	    values.add(start);
	    	    sb.append(" and t.END_NUM >= ? ");
	    	    values.add(end);
	    	    list=this.findBySql(sb.toString(), values.toArray());
	    	    if(list.size()!=1){
	    	    	
	    	    }else{
	    	    	 for(Iterator iterator = list.iterator(); iterator.hasNext();){
		 					Object[] object = (Object[]) iterator.next();
		 					VcStorage vs=new VcStorage();
		 					vs.setDocVerCode((String) object[0]);
		 					vs.setPressBatchNo((String) object[1]);
		 					vs.setStartNum((String) object[2]);
		 					vs.setEndNum((String) object[3]);
		 					vs.setOrgCode((String) object[4]);
		 					vs.setUpdatedBy((String) object[5]);
		 				}	
	    	    }
	    	   
		} catch (Exception e) {
			 throw new DaoException("发票自动发放前查询库存异常");
		}
		return list;
	}
	@Override
	public String splitStorage1(String start, String end, String docVerCode,
			String pressBatchNo, String orgCode, String docStatus,
			String changeToStatus, VcStorage vcStorage, String userCode) throws DaoException {
		   if (vcStorage == null) {
	            // 判断库存是否存在
	            String[] docStatuses = { docStatus };
	            List<VcStorage> vcStorageList = isExistAll2(start, end, docVerCode, pressBatchNo, orgCode,false, docStatuses);

	            if (vcStorageList.size() != 1) {
	                //return "0";
	                throw new DaoException("拆分库存异常:库存["+start+"—"+end+"]不存在或存在多条库存！");
	            }
	            
	         /*   //判断拆分号段是否已过使用截止期
	            if(DateUtils.compare(DateUtils.reset(vcStorageList.get(0).getDeadline()), DateUtils.reset(new Date())) < 0 && changeToStatus == "PT"){
	            	throw new DaoException("单证["+ docVerCode +"]号段["+start+"—"+end+"]已过使用截止期，无法发放");
	            }
	            */
	            vcStorage = vcStorageList.get(0);
	        }
	      try{
	        Long tempStart = Long.valueOf(vcStorage.getStartNum());
	        Long tempEnd = Long.valueOf(vcStorage.getEndNum());

	        Long start_ = Long.valueOf(start);
	        Long end_ = Long.valueOf(end);

	        DecimalFormat df = new DecimalFormat("0");
	        Date nowDate = new Date();

	        // a. start = tempStart end < tempEnd
	        if (start_ - tempStart == 0 && end_ < tempEnd) {
	            // 原
	            df.setMinimumIntegerDigits(end.length());
	            vcStorage.setStartNum(df.format(end_ + 1));
	            vcStorage.setDocNum(tempEnd - end_); 
	            vcStorage.setModifyTime(nowDate);
	            vcStorage.setModifyUserCode(userCode);
	            vcStorage.setDateUpdated(nowDate);           
	            vcStorage.setUpdatedBy(userCode);
	            this.update(vcStorage);

	            if (Beans.isNotEmpty(changeToStatus)) {
	                VcStorage vcStorageTemp = new VcStorage();
	                Beans.copy(vcStorageTemp, vcStorage);
	                vcStorageTemp.setId(null);
	                vcStorageTemp.setStartNum(start);
	                vcStorageTemp.setEndNum(end);
	                vcStorageTemp.setDocNum(end_ - start_ + 1);
	                vcStorageTemp.setDocStatus(changeToStatus);
	                vcStorageTemp.setCreateTime(nowDate);
	                vcStorageTemp.setModifyTime(nowDate);
	                vcStorageTemp.setDateCreated(nowDate);
	                vcStorageTemp.setDateUpdated(nowDate);
	                vcStorageTemp.setInStoreTime(nowDate);
	                vcStorageTemp.setCreateUserCode(userCode);
	                vcStorageTemp.setModifyUserCode(userCode);
	                vcStorageTemp.setCreatedBy(userCode);
	                vcStorageTemp.setUpdatedBy(userCode);
	                this.save(vcStorageTemp);
	            }

	        }
	        // b. start > tempStart end = tempEnd
	        else if (start_ > tempStart && end_ - tempEnd == 0) {
	            // 原
	            df.setMinimumIntegerDigits(start.length());
	            vcStorage.setEndNum(df.format(start_ - 1));
	            vcStorage.setDocNum(start_ - tempStart);
	            vcStorage.setModifyTime(nowDate);
	            vcStorage.setModifyUserCode(userCode);
	            vcStorage.setDateUpdated(nowDate);
	            vcStorage.setUpdatedBy(userCode);
	            this.update(vcStorage);

	            if (Beans.isNotEmpty(changeToStatus)) {
	                VcStorage vcStorageTemp = new VcStorage();
	                Beans.copy(vcStorageTemp, vcStorage);
	                vcStorageTemp.setId(null);
	                vcStorageTemp.setStartNum(start);
	                vcStorageTemp.setEndNum(end);
	                vcStorageTemp.setDocNum(end_ - start_ + 1);
	                vcStorageTemp.setDocStatus(changeToStatus);
	                vcStorageTemp.setCreateTime(nowDate);
	                vcStorageTemp.setModifyTime(nowDate);
	                vcStorageTemp.setDateCreated(nowDate);
	                vcStorageTemp.setDateUpdated(nowDate);
	                vcStorageTemp.setInStoreTime(nowDate);
	                vcStorageTemp.setCreateUserCode(userCode);
	                vcStorageTemp.setModifyUserCode(userCode);
	                vcStorageTemp.setCreatedBy(userCode);
	                vcStorageTemp.setUpdatedBy(userCode);
	                this.save(vcStorageTemp);
	            }

	        }
	        // c. start = tempStart end = tempEnd
	        else if (start_ - tempStart == 0 && end_ - tempEnd == 0) {
	            if (Beans.isNotEmpty(changeToStatus)) {
	                vcStorage.setDocStatus(changeToStatus);
	                this.update(vcStorage);
	            } else {
	                this.delete(vcStorage);
	            }
	        }
	        // d. start > tempStart end < tempEnd
	        else if (start_ > tempStart && end_ < tempEnd) {
	            // 原2
	            VcStorage vcStorage2 = new VcStorage();
	            Beans.copy(vcStorage2, vcStorage);
	            vcStorage2.setId(null);
	            df.setMinimumIntegerDigits(end.length());
	            vcStorage2.setStartNum(df.format(end_ + 1));
	            vcStorage2.setDocNum(tempEnd - end_);
	            vcStorage.setModifyTime(nowDate);
	            vcStorage.setModifyUserCode(userCode);
	            vcStorage2.setDateUpdated(nowDate);
	            vcStorage.setUpdatedBy(userCode);

	            // 原1
	            df.setMinimumIntegerDigits(start.length());
	            vcStorage.setEndNum(df.format(start_ - 1));
	            vcStorage.setDocNum(start_ - tempStart);
	            vcStorage.setModifyTime(nowDate);
	            vcStorage.setModifyUserCode(userCode);
	            vcStorage.setDateUpdated(nowDate);
	            vcStorage.setUpdatedBy(userCode);

	            if (Beans.isNotEmpty(changeToStatus)) {
	                VcStorage vcStorageTemp = new VcStorage();
	                Beans.copy(vcStorageTemp, vcStorage);

	                vcStorageTemp.setDocStatus(changeToStatus);
	                vcStorageTemp.setId(null);
	                vcStorageTemp.setStartNum(start);
	                vcStorageTemp.setEndNum(end);
	                vcStorageTemp.setDocNum(end_ - start_ + 1);
	                vcStorageTemp.setCreateTime(nowDate);
	                vcStorageTemp.setModifyTime(nowDate);
	                vcStorageTemp.setDateCreated(nowDate);
	                vcStorageTemp.setDateUpdated(nowDate);
	                vcStorageTemp.setInStoreTime(nowDate);
	                vcStorageTemp.setCreateUserCode(userCode);
	                vcStorageTemp.setModifyUserCode(userCode);
	                vcStorageTemp.setCreatedBy(userCode);
	                vcStorageTemp.setUpdatedBy(userCode);
	                this.save(vcStorageTemp);
	            }
	            List<VcStorage> vcStorageTemps = new ArrayList<VcStorage>();
	            vcStorageTemps.add(vcStorage);
	            vcStorageTemps.add(vcStorage2);
	            this.saveAll(vcStorageTemps);
	        }else{
	           throw new DaoException("库存号段拆分异常！");  
	        }// 拆分完毕
	    }catch(DaoException de){
	          throw de;
	      }catch(Exception e){
			throw new DaoException("保存数据时发生异常！", e);
		}
	        return "1";
	}
	/**
     * 判断库存是否存在startNum<=start endNum>=end的数据,可用于拆分前的判断
     * 
     * @param start
     *            起始流水号
     * @param end
     *            终止流水号
     * @param docVerCode
     *            单证类型
     * @param pressBatchNo
     *            批次
     * @param orgCode
     *            机构
     *@param  isContainLowerOrg
     *       是否包含下级机构的库存 true-包含；     false-不包含
     * @param docStatus
     *            单证状态
     * @return 返回VcStorage的List集合
     */
    public List<VcStorage> isExistAll2(String start, String end, String docVerCode, String pressBatchNo, String orgCode,
          boolean isContainLowerOrg,  String[] docStatus) throws DaoException{       
        if(start.length()!=end.length()){
            throw new DaoException("起始号段" + start + "（长度：" + start.length() + "）与终止号段" + end + "（长度：" + end.length() + "）长度不等，请确认！");
        }
       try{
        QueryRule queryRule = QueryRule.getInstance();
        queryRule.addEqual("docVerCode", docVerCode);
        queryRule.addEqual("pressBatchNo", pressBatchNo);
        if(isContainLowerOrg){
          //遗失时由中支公司提交申请，要能访问中支及下属机构库存
            StringBuffer sb=new StringBuffer(" ORG_CODE IN ( ");
            sb.append(" SELECT T.UNIT_CODE  FROM VC_LEVEL T  WHERE T.UNIT_TYPE = '0' ");
            sb.append("  START WITH T.UNIT_CODE ='").append(orgCode).append("' ");
            sb.append(" CONNECT BY T.PARENT_ORG_ID = PRIOR T.ID_VC_LEVEL) ");
            queryRule.addSql(sb.toString());
        }else{
            queryRule.addEqual("orgCode", orgCode);    
        }
        //queryRule.addLike("orgCode", orgCode);
        queryRule.addIn("docStatus", docStatus);
        queryRule.addLessEqual("startNum", start);
        queryRule.addGreaterEqual("endNum", end);
        List<VcStorage> vcStorageList = this.find(queryRule);
        return vcStorageList;
       }catch(Exception e){
			throw new DaoException("查询数据时发生异常！", e);
		}
    }

}
