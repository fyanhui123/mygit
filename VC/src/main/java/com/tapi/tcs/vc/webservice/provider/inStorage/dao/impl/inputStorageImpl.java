package com.tapi.tcs.vc.webservice.provider.inStorage.dao.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.cxf.transport.Session;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.webservice.provider.inStorage.bean.UserInfoDTO;
import com.tapi.tcs.vc.webservice.provider.inStorage.dao.inputStorage;

public class inputStorageImpl extends GenericDaoHibernate<VcStorage> implements inputStorage  {
	@Override
	public List<VcStorage> queryVcStroage(String docVerCode,String start,String end, String pressBatchNo) throws DaoException {
		List<Object> list=null;
		List<VcStorage> lists= new ArrayList<VcStorage>();
		try {
			List<Object> values =new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			sb.append(" select t.id_vc_storage,t.doc_ver_code,t.press_batch_no,t.start_num,t.end_num,t.doc_status from vc_storage  t ");
			sb.append(" where t.doc_ver_code = ? ");
			values.add(docVerCode);
			sb.append(" and t.press_batch_no = ? ");
			values.add(pressBatchNo);
			//sb.append(" and ( (t.start_num >= ? AND t.start_num <= ? ) OR (t.start_num <= ? AND t.end_num >= ?))  ");
			sb.append(" and (( ? between t.start_num and t.end_num)or( ? between t.start_num and t.end_num) )");
			values.add(start);
			values.add(end);
			list=this.findBySql(sb.toString(), values.toArray());
			//判断在那个区间       根据起止号查出库存内的起止号
			if(list.size()==1){
				 for(Iterator iterator = list.iterator(); iterator.hasNext();){
					 Object[] object = (Object[]) iterator.next();
					 VcStorage vcstorage=new VcStorage();
					 BigDecimal id=(BigDecimal) object[0];
					 long ids=id.longValue();
					 vcstorage.setId(ids);
					 vcstorage.setDocVerCode((String) object[1]);
					 vcstorage.setPressBatchNo((String) object[2]);
					 vcstorage.setStartNum((String) object[3]);
					 vcstorage.setEndNum((String) object[4]);
					 vcstorage.setDocStatus((String) object[5]);
					 lists.add(vcstorage);
				 }
			}else{
				for(Iterator iterator = list.iterator(); iterator.hasNext();){
					 Object[] object = (Object[]) iterator.next();
					 VcStorage vcstorage=new VcStorage();
					 BigDecimal id=(BigDecimal) object[0];
					 long ids=id.longValue();
					 vcstorage.setId(ids);
					 vcstorage.setDocVerCode((String) object[1]);
					 vcstorage.setPressBatchNo((String) object[2]);
					 vcstorage.setStartNum((String) object[3]);
					 vcstorage.setEndNum((String) object[4]);
					 vcstorage.setDocStatus((String) object[5]);
					 lists.add(vcstorage);
				 }
			}
		} catch (Exception e) {
			throw new DaoException("库存查询异常");
		}
		return lists;
	}
	
	public List<VcStorage> queryStorages(String docVerCode,String strNum,String endNum,String pressBatchNo,String orgCode) throws DaoException{
		try {
			DecimalFormat df = new DecimalFormat("0");
	        df.setMinimumIntegerDigits(strNum.length());
		    QueryRule queryRule = null;
		    Long start_ = Long.valueOf(strNum);
	        Long end_ = Long.valueOf(endNum);
		    queryRule = QueryRule.getInstance();
	        queryRule.addEqual("docVerCode", docVerCode);
	        queryRule.addEqual("pressBatchNo", pressBatchNo);
	        queryRule.addEqual("orgCode", orgCode);
	        Object[] values = { "S1", "S2", "S3" };
	        queryRule.addIn("docStatus", values);
	        queryRule.addSql(" (start_num = '" + df.format(end_ + 1) + "' OR end_num = '" + df.format(start_ - 1) + "' )");
	        List<VcStorage> vcStoragesTemp = this.find(queryRule);
		   return vcStoragesTemp;
		} catch (Exception e) {
			throw new DaoException("查询异常");
		}
	}
	
	@SuppressWarnings("unchecked")
	public String queryUserInfo(String userCode, String type,String orgcode) throws DaoException{
		List<Object> values =new ArrayList<Object>();
		UserInfoDTO userInfo=null;
		if(type.equals("1")){
		//根据usercode   查询单证使用人的employid 
		try {
			StringBuffer sb=new StringBuffer(" select t.employee_id,t.user_code from pub_user_def t, vc_taker t3 where t.employee_id = t3.taker_code and t.user_code = ? and t3.status=? and t3.org_code like ? ");
			values.add(userCode);
			values.add("1");
			values.add(orgcode+"%");
			List<Object> list=this.findBySql(sb.toString(), values.toArray());
			if(list.size()!=1){
				throw new DaoException("未查询到对应的单证使用人!");
			}else{
				for(Iterator iterator = list.iterator(); iterator.hasNext();){
					Object[] object = (Object[]) iterator.next();
					userInfo=new UserInfoDTO();
					userInfo.setEmployeeId((String) object[0]);
					userInfo.setUserCode((String) object[1]);
				}
			}
		} catch (Exception e) {
			throw new DaoException("未查询到对应的单证使用人!");
		}
		return userInfo.getEmployeeId();
	}else if(type.equals("2")){	   //查询发票管理员的employid
		try {
			StringBuffer sb=new StringBuffer(" select t1.employee_id,t.parent_org_code ,t1.company_code,t1.user_code from vc_level t, pub_user_def t1 where t.unit_code = t1.employee_id and t1.user_code = ? and t.invoice_flag= ? and  t.valid_status= ? and t1.company_code like ? ");
			values.add(userCode);
			values.add("1");
			values.add("1");
			values.add(orgcode+"%");
			List<Object> list=this.findBySql(sb.toString(), values.toArray());
			if(list.size()!=1){
				throw new DaoException("未查询到对应的发票管理员");
			}else{
				for(Iterator iterator = list.iterator(); iterator.hasNext();){
					Object[] object = (Object[]) iterator.next();
					userInfo=new UserInfoDTO();
					userInfo.setEmployeeId((String) object[0]);
					userInfo.setParentOrgCode((String) object[1]);
					userInfo.setComCode((String) object[2]);
				}
			}
		} catch (Exception e) {
			throw new DaoException("未查询到对应的发票管理员");
		}
		return userInfo.getEmployeeId();
		}else if(type.equals("3")){  //根据usercode 查询使用人的 归属机构
			try {
				StringBuffer sb=new StringBuffer(" select t.company_code,t.user_code from pub_user_def t, vc_taker t3 where t.employee_id = t3.taker_code and t.user_code = ? and t3.status='1' ");
				values.add(userCode);
				List<Object> list=this.findBySql(sb.toString(), values.toArray());
				if(list.size()!=1){
					throw new DaoException("未查到单证使用人归属机构!");
				}else{
					for(Iterator iterator = list.iterator(); iterator.hasNext();){
						Object[] object = (Object[]) iterator.next();
						userInfo=new UserInfoDTO();
						userInfo.setOrgCode((String) object[0]);
						userInfo.setUserCode((String) object[1]);
					}
				}
			} catch (Exception e) {
				throw new DaoException("未查到单证使用人归属机构");
			}
			return userInfo.getOrgCode();
		}else if(type.equals("4")){
			try {   //根据上级机构查询该机构下的发票管理员的 parent_org_code
				StringBuffer sb=new StringBuffer("  select t3.parent_org_code,t3.unit_code from pub_user_def t, vc_level t3 where t.employee_id = t3.unit_code and t.user_code = ? and t3.valid_status = '1'and t3.invoice_flag= ?  ");
				values.add(userCode);
				values.add("1");
				List<Object> list=this.findBySql(sb.toString(), values.toArray());
				if(list.size()>0){
					for(Iterator iterator = list.iterator(); iterator.hasNext();){
						Object[] object = (Object[]) iterator.next();
						userInfo=new UserInfoDTO();
						userInfo.setParentOrgCode((String) object[0]);
					}
				}else{
					throw new DaoException("该机构下未维护发票管理员!");
				}
			} catch (Exception e) {
				throw new DaoException("该机构下未维护发票管理员");
			}
			return userInfo.getParentOrgCode();
		}else if(type.equals("5")){
			try {
				StringBuffer sb=new StringBuffer(" select t1.employee_id, t1.company_code, t1.user_code from  pub_user_def t1 where  t1.user_code = ?  and t1.valid_flag='1' ");
				values.add(userCode);
				List<Object> list=this.findBySql(sb.toString(), values.toArray());
				if(list.size()!=1){
					throw new DaoException("未维护VMS系统电子票开具管理员");
				}else{
					for(Iterator iterator = list.iterator(); iterator.hasNext();){
						Object[] object = (Object[]) iterator.next();
						userInfo=new UserInfoDTO();
						userInfo.setEmployeeId((String) object[0]);
						userInfo.setParentOrgCode((String) object[1]);
						userInfo.setComCode((String) object[2]);
					}
				}
			} catch (Exception e) {
				throw new DaoException("未维护VMS系统管理员");
			}
			return userInfo.getEmployeeId();
		}else{
			throw new DaoException("查询人员类型代码传输错误");
		}
	}
	/**
	 * 入库
	 * @param
	 * @return
	 * @throws DaoException
	 */
	@Override
	public void saveVcStorageList(List<VcStorage> vcStorageList)
			throws DaoException {
		try {
			this.saveAll(vcStorageList);
			getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		} catch (Exception e) {
			throw new DaoException("插入库存表异常!!!");
		}
		
	}
	/**
	 * 验证发票管理员是不是存在
	 * @param  vclevel  机构代码
	 * @return
	 * @throws DaoException
	 */
	@Override
	public int queryLevel(String vclevel) throws DaoException {
		List<Object> values =new ArrayList<Object>();
		try {
			StringBuffer sb=new StringBuffer(" select t.unit_type,t.unit_code,t.unit_name,t.parent_org_id,t.com_type,t.level_no,t.manage_level,t.valid_status,t.remark,t.flag,t.level_code,t.display_no,t.created_by,t.date_created,t.updated_by,t.date_updated,t.parent_org_code,t.invoice_flag from vc_level t");
			sb.append(" where t.parent_org_code = ? ");
			values.add(vclevel);
			sb.append("  and t.unit_type = ? ");
			values.add("1");
			sb.append(" and t.valid_status = ? ");
			values.add("1");
			sb.append(" and t.invoice_flag = ? ");
			values.add("1");
			List<Object> list=this.findBySql(sb.toString(), values.toArray());
			if(list.size()>0){
				return list.size();
			}else{
				return 0;
			}
		} catch (Exception e) {
			throw new DaoException("查询level表异常!!!");
		}
	}
	@Override
	public String queryDocverCode(String type) throws Exception {
		String result=null;
		try {
			//等于0的时候是  ”通用增值税专用发票“
			if(type.equals("0")){
				result ="ZP9999010100";
			}else if(type.equals("1")){
				//等于1的时候是  ”通用增值税普通发票“
				result ="ZP9999010300";
			}
			else if(type.equals("2")){
				//等于2的时候是  ”通用增值税电子普通发票“
				result ="ZP9999020300";
			}else{
				result=null;
				throw new DaoException("单证类型转换异常!!!");
			} 
		} catch (Exception e) {
			throw new DaoException("单证类型转换异常!!!");
		}
		return result;
	}
	@Override
	public void inMergeStorage(String start, String end, String docVerCode,
			String pressBatchNo, String orgCode, String docStatus,
			String docStatusTo, String userCode,String fl) throws DaoException {
		  try{
		        VcStorage vcStorage_ = null;
		        QueryRule queryRule = null;
		        if (Beans.isNotEmpty(docStatusTo)) {
		              vcStorage_ = this.fullEqual(start, end, docVerCode, pressBatchNo, docStatus, orgCode);
		              if (vcStorage_ == null) {
		            	  throw new DaoException("数据错误!");  
		              }
		        }
		        Long start_ = Long.valueOf(start);
		        Long end_ = Long.valueOf(end);
		        
		        
		        Date date = new Date();
		        Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				int currenYear = calendar.get(Calendar.YEAR);
				calendar.set(Calendar.YEAR, currenYear + 2);
				java.sql.Date newDate = new java.sql.Date(calendar.getTimeInMillis());//得到截止日期（两年后）
				
		        DecimalFormat df = new DecimalFormat("0");
		        df.setMinimumIntegerDigits(start.length());
		        String endEnd=df.format(end_ + 1);
		        String stattStart=df.format(start_ - 1);
		        Object[] values = { "S1", "S2", "S3" };
		        List<VcStorage> vcStoragesTemp = this.findStorage(docVerCode, pressBatchNo, orgCode, endEnd, stattStart,values);
		      /*  queryRule = QueryRule.getInstance();
		        queryRule.addEqual("docVerCode", docVerCode);
		        queryRule.addEqual("pressBatchNo", pressBatchNo);
		        queryRule.addEqual("orgCode", orgCode);
		        Object[] values = { "S1", "S2", "S3" };
		        queryRule.addIn("docStatus", values);
		        queryRule.addEqual("docStatus", Beans.isNotEmpty(docStatusTo) ? docStatusTo : docStatus);
		        queryRule.addSql(" (start_num = '" + df.format(end_ + 1) + "' OR end_num = '" + df.format(start_ - 1) + "' )");
		        queryRule.addAscOrder("startNum");
		        List<VcStorage> vcStoragesTemp = this.find(queryRule);*/
		        
		        int size = vcStoragesTemp.size();
		        VcStorage vcStorageSave = new VcStorage();
		        Long startNum = null;
		        Long endNum = null;
		        boolean flag = true;
		        if (size == 1) {
		        	VcStorage vcStorage = (VcStorage) vcStoragesTemp.get(0);
		            Long tempStart = Long.valueOf(vcStorage.getStartNum());
		            Long tempEnd = Long.valueOf(vcStorage.getEndNum());

		            // a. start-1 == tempEnd end+1 != start
		            if (start_ - 1 == tempEnd && end_ + 1 != tempStart) {
		                startNum = tempStart;
		                endNum = end_;
		            }
		            // b. start-1 != tempEnd end+1 == tempStart
		            else if (end_ + 1 == tempStart && start_ - 1 != tempEnd) {
		                startNum = start_;
		                endNum = tempEnd;
		            }
		        } else if (size == 2) {
		            VcStorage vcStorage = (VcStorage) vcStoragesTemp.get(0);
		            VcStorage vcStorage2 = (VcStorage) vcStoragesTemp.get(1);

		            startNum = Long.valueOf(vcStorage.getStartNum());
		            endNum = Long.valueOf(vcStorage2.getEndNum());
		        } else if (size == 0) {
		            if (vcStorage_ != null) {
		                vcStorage_.setDocStatus(docStatusTo);
		                this.update(vcStorage_);
		                flag = false;
		            } else {
		                startNum = start_;
		                endNum = end_;
		            }
		        }else{
		            throw new DaoException("与号段[" + start + "：" + end + "]相邻的库存多于2条！");          
		        }
		        if (flag) {
		            vcStorageSave.setStartNum(df.format(startNum));
		            vcStorageSave.setEndNum(df.format(endNum));
		            vcStorageSave.setDocNum(endNum - startNum + 1);
		            vcStorageSave.setDocVerCode(docVerCode);
		            vcStorageSave.setPressBatchNo(pressBatchNo);
		            vcStorageSave.setDocStatus(Beans.isNotEmpty(docStatusTo) ? docStatusTo : docStatus);
		            vcStorageSave.setOrgCode(orgCode);
		            vcStorageSave.setInStoreTime(date);           
		            vcStorageSave.setDeadline(newDate);
		            vcStorageSave.setCreateUserCode(userCode);
		            vcStorageSave.setCreateTime(date);
		            vcStorageSave.setModifyUserCode(userCode);
		            vcStorageSave.setModifyTime(date);
		            vcStorageSave.setCreatedBy(userCode);
		            vcStorageSave.setDateCreated(date);
		            vcStorageSave.setUpdatedBy(userCode);
		            vcStorageSave.setDateUpdated(date);
		            this.save(vcStorageSave);
		            this.deleteAll(vcStoragesTemp);
		            if (vcStorage_ != null) {
		                this.delete(vcStorage_);
		            }
		        }
		      }catch(DaoException de){
		             throw de;
		      }catch(Exception e){
		    	     throw new DaoException("保存数据时发生异常！");
		      }
	}
	 public VcStorage fullEqual(String startNum, String endNum, String docVerCode, String pressBatchNo,
	            String docStatus, String orgCode) throws DaoException {
	    	try{
	        List<Object> values = new ArrayList<Object>();
	        String hql = " from VcStorage v where v.startNum=? and v.endNum=? and v.docVerCode=? and v.pressBatchNo=? and v.docStatus=? and v.orgCode=?";
	        values.add(startNum);
	        values.add(endNum);
	        values.add(docVerCode);
	        values.add(pressBatchNo);
	        values.add(docStatus);
	        values.add(orgCode);
	        List<VcStorage> list = this.findByHql(hql, values.toArray());
	        if (list != null && list.size() == 1) {
	            return list.get(0);
	        }
	        return null;
	    	}catch(Exception e){
	   	       throw new DaoException("查询数据失败！");
	        }
	    }
	@Override
	public String max(String str1,String str2) {
		 Integer tempStart=Integer.parseInt(str1);//库存内起始号码
    	 Integer start=Integer.parseInt(str2);//起始号码
    	 Integer maxNum=0;
         if(tempStart>=start){
        	 maxNum=tempStart;
    	 }else{
    		 maxNum=start;
    	 }
         String endNum=maxNum.toString();
         String endnums=null;
         if(endNum.length()<str1.length()){
        	  for(int i=endNum.length();i<str1.length();i++){
             	 endnums="0"+endNum;
             	 endNum=endnums;
              }
        	  return endNum;
         }else{
        	 return endNum;
         }
	}
	@Override
	public String min(String str1,String str2) {
	 Integer tempStart=Integer.parseInt(str1);//库存内起始号码
   	 Integer start=Integer.parseInt(str2);//起始号码
   	 Integer minNum=0;
        if(tempStart>=start){
       	 minNum=start;
   	 }else{
   		minNum=tempStart;
   	 }
        String endNum=minNum.toString();
        String endnums=null;
        if(endNum.length()<str1.length()){
       	  for(int i=endNum.length();i<str1.length();i++){
            	 endnums="0"+endNum;
            	 endNum=endnums;
             }
       	  return endNum;
        }else{
       	 return endNum;
        }
	}
	@Override
	public void saveStorage(VcStorage vcStorage) {
		this.save(vcStorage);
	}
	@Override
	public void deleteStorage(long id) throws DaoException {
		List<Object> values = new ArrayList<Object>();
		try {
			this.deleteByPK(VcStorage.class, id);
			getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
	//		getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction().commit();
		} catch (Exception e) {
			throw new DaoException("删除数据出错！");
		}
	}
	public boolean resultType(String docVerCode)throws DaoException{
		boolean result=false;
		try {
			if(docVerCode.endsWith("ZP9999020300")){
				result=true;
			}else{
				 result=false;
			}
		} catch (Exception e) {
			throw new DaoException("数据出错！");
		}
		return result;
	}
	public List<VcStorage> findStorage(String docVerCode,String pressBatchNo,String orgCode,String start_num,String end_num,Object[] docStatus)throws DaoException {
		List<VcStorage> list=null;
		try{
	        List<Object> values = new ArrayList<Object>();
	        String hql = " from VcStorage v where v.docVerCode= ? and v.pressBatchNo= ? AND v.orgCode= ?  and v.docStatus in ( ? ) and  (v.startNum= ? or v.endNum= ? ) ";
	        values.add(docVerCode);
	        values.add(pressBatchNo);
	        values.add(orgCode);
	        values.add(docStatus);
	        values.add(start_num);
	        values.add(end_num);
	        list = this.findByHql(hql, values.toArray());
	    	}catch(Exception e){
	   	       throw new DaoException("查询数据失败！");
	        }
		return list;
	}
}

