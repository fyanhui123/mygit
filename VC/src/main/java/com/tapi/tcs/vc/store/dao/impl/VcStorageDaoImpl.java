package com.tapi.tcs.vc.store.dao.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcDocMngRuleDao;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.dao.VcStorageDao;
import com.tapi.tcs.vc.store.vo.StorageInfo;

public class VcStorageDaoImpl extends GenericDaoHibernate<VcStorage> implements VcStorageDao {

    private VcDocMngRuleDao vcDocMngRuleDao;

    /**
     * 根据条件查找库存
     * 
     * @param versionCode
     * @param orgCode
     * @param startNum
     * @param endNum
     * @param status
     * @return
     * @throws DaoException
     * @author chy
     * @date 2013-04-09
     */
    @SuppressWarnings("unchecked")
    public List<VcStorage> findByConditions(String versionCode, String orgCode, String startNum, String endNum, String status) throws DaoException{
    	List<VcStorage> list = null;
    	try{
			QueryRule queryRule = QueryRule.getInstance();
			//单证类型代码
			queryRule.addEqual("docVerCode", versionCode);
			// 机构代码
			queryRule.addEqual("orgCode", orgCode);
			// 其实流水号
			queryRule.addEqual("startNum", startNum);
			// 终止流水号
			queryRule.addEqual("endNum", endNum);
			// 单证状态
			queryRule.addEqual("docStatus", status);
			
			list = this.find(VcStorage.class, queryRule);
    	}catch(Exception e){
    		throw new DaoException("查询数据出错！", e);
    	}
		return list;
    }

    /**
     * 保存方法
     * 
     * @param vcStorage
     * @throws DaoException
     * @author chy
     * @date 2013-04-09
     */


    public void saveVcStorage(VcStorage vcStorage) throws DaoException {
    	try{
    		this.save(vcStorage);
    	}catch(Exception e){
			throw new DaoException("保存库存数据出错！", e);
		}
    }

    /**
     * 删除方法
     * 
     * @param List
     *            <VcStorage>
     * @throws Exception
     * @author chy
     * @date 2013-04-09
     */
    public void deleteVcStorage(List<VcStorage> list) throws DaoException {
    	try{
    		this.deleteAll(list);
    	}catch(Exception e){
			throw new DaoException("删除库存数据出错！", e);
		}
    }

    @SuppressWarnings("rawtypes")
    public List findBySQL(String sql, Object... params) throws DaoException {
    	List list = null;
    	try{
    		list = super.findBySql(sql, params);
    	}catch(Exception e){
    		throw new DaoException("查询数据出错！", e);
    	}
    	return list;
    }

    /**
     * 锁表操作
     * 
     * @param id
     *            主键
     */
    @Override
    public void lockVcStorage(Object... values)  throws DaoException{
        StringBuffer paramSql = new StringBuffer();
        try{
        for (int i = 0; i < values.length; i++) {
            paramSql.append("?,");
        }
        paramSql.substring(0, paramSql.length());
        this.findBySql("select * from VC_STORAGE t where t.id_vc_storage in ( "
                + paramSql.substring(0, paramSql.length() - 1) + " ) FOR UPDATE", values);
        }catch(Exception e){
			throw new DaoException("锁表时发生异常！", e);
		}

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
    public List<VcStorage> isExistAll(String start, String end, String docVerCode, String pressBatchNo, String orgCode,
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

    /**
     * 拆分库存
     * 
     * @param start
     *            起始流水号 (10位纯数字流水号 )
     * @param end
     *            终止流水号 (10位纯数字流水号 )
     * @param docVerCode
     *            单证类型
     * @param pressBatchNo
     *            批次
     * @param orgCode
     *            机构
     * @param docStatus
     *            单证状态
     * @param changeToStatus
     *            拆分后对应流水号的状态 为空(null或"")时不保留对应库存
     * @param vcStorage
     *            vcStorage不为空时说明拆分前已做库存是否存在的校验，直接通过start、end、vcStorage拆分
     * @param userCode
     *            当前操作用户
     * @return String "0"库存不存在或存在多条库存 "1"成功
     */
    public String splitStorage(String start, String end, String docVerCode, String pressBatchNo, String orgCode,
            String docStatus, String changeToStatus, VcStorage vcStorage,String userCode) throws DaoException{
        if (vcStorage == null) {
            // 判断库存是否存在
            String[] docStatuses = { docStatus };
            List<VcStorage> vcStorageList = isExistAll(start, end, docVerCode, pressBatchNo, orgCode,false, docStatuses);

            if (vcStorageList.size() != 1) {
                //return "0";
                throw new DaoException("拆分库存异常:库存["+start+"—"+end+"]不存在或存在多条库存！");
            }
            
            //判断拆分号段是否已过使用截止期
            if(DateUtils.compare(DateUtils.reset(vcStorageList.get(0).getDeadline()), DateUtils.reset(new Date())) < 0 && changeToStatus == "PT"){
            	throw new DaoException("单证["+ docVerCode +"]号段["+start+"—"+end+"]已过使用截止期，无法发放");
            }
            
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
     * 合并库存
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
     * @param docStatus
     *            需要合并的单证状态
     * @param docStatusTo
     *            与何种单证状态并成，为空时与docStatus状态的合并
     * @param userCode
     *            当前操作用户
     *@param dealline
     *            库存截止日期
     */
    public void mergeStorage(String start, String end, String docVerCode, String pressBatchNo, String orgCode,
            String docStatus, String docStatusTo, String userCode,Date dealline) throws DaoException{
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
        if(dealline==null){
            int maxStoreTime = vcDocMngRuleDao.getMaxStoreTime(docVerCode, "0", orgCode);
            dealline=DateUtils.reset(DateUtils.addDay(date, maxStoreTime));
        }

        // 使用截止日期
        // int maxStoreTime =
        // vcDocMngRuleDao.getRuleByQueryMaxStoreTime(docVerCode, mngType,
        // mngObjectCode);
        DecimalFormat df = new DecimalFormat("0");
        df.setMinimumIntegerDigits(start.length());
        String endEnd=df.format(end_ + 1);
        String stattStart=df.format(start_ - 1);
        List<VcStorage> vcStoragesTemp = this.findStorage(docVerCode, pressBatchNo, orgCode, endEnd, stattStart,Beans.isNotEmpty(docStatusTo) ? docStatusTo : docStatus);
        /*queryRule = QueryRule.getInstance();
        queryRule.addEqual("docVerCode", docVerCode);
        queryRule.addEqual("pressBatchNo", pressBatchNo);
        queryRule.addEqual("orgCode", orgCode);
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
            ///合并时截止期取较大的
            if(DateUtils.compare(dealline, vcStorage.getDeadline())<0){
                dealline=  DateUtils.reset(vcStorage.getDeadline());
            }
        } else if (size == 2) {
            VcStorage vcStorage = (VcStorage) vcStoragesTemp.get(0);
            VcStorage vcStorage2 = (VcStorage) vcStoragesTemp.get(1);

            startNum = Long.valueOf(vcStorage.getStartNum());
            endNum = Long.valueOf(vcStorage2.getEndNum());
            ///合并时截止期取较大的
            if(DateUtils.compare(dealline, vcStorage.getDeadline())<0){
                dealline=  DateUtils.reset(vcStorage.getDeadline());
            }
            if(DateUtils.compare(dealline, vcStorage2.getDeadline())<0){
                dealline=  DateUtils.reset(vcStorage2.getDeadline());
            }
        } else if (size == 0) {
            if (vcStorage_ != null) {
                vcStorage_.setDeadline(dealline);
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
            vcStorageSave.setDeadline(dealline);
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
    	     throw new DaoException("保存数据时发生异常！", e);
      }
    }

    /**
     * 判断数据库是否包含流水号start-end的库存 如 start = 2 ，end = 5 如果库存有 1-6 或 2-3
     * 或4-6的库存返回都会大于0
     * 
     * @param start
     *            起始流水号
     * @param end
     *            终止流水号
     * @param docVerCode
     *            单证类型
     * @param pressBatchNo
     *            批次
     * @param docStatus
     *            单证状态
     * @param orgCode
     *            机构
     * @return 0- 不包含 大于0-包含
     */
    public int isExist(String start, String end, String docVerCode, String pressBatchNo, String docStatus,
            String orgCode) throws DaoException{
    	try{
        /*String sql = " SELECT COUNT(*) FROM VC_STORAGE t " + " WHERE t.doc_ver_code = ?  "
                + " AND t.press_batch_no = ? " + " AND t.doc_status = ? " + " AND t.org_code = ? "
                + " AND ( (t.start_num <= ? AND t.end_num >= ?) OR (t.start_num <= ? AND t.end_num >= ?)) ";

        Object[] objs = { docVerCode, pressBatchNo, docStatus, orgCode, start, start, end, end };*/
    	StringBuffer sb = new StringBuffer(" SELECT COUNT(*) FROM VC_STORAGE t " );
    	sb.append(" WHERE t.doc_ver_code = ?  AND t.press_batch_no = ? ");
    	sb.append(" AND t.doc_status = ?  AND t.org_code = ? ");
    	sb.append(" AND t.start_num <= ? AND t.end_num >= ? ");
    	
    	/*add by whj 20131115 start*/
    	sb.append(" AND length(t.start_num) =length(?) " );
    	sb.append(" AND length(t.end_num) = length(?) ");
    	/*add by whj 20131115 end*/

    Object[] objs = { docVerCode, pressBatchNo, docStatus, orgCode, start, end,start, end};
        return Integer.valueOf(this.findBySql(sb.toString(), objs).get(0).toString());
    	}catch(Exception e){
   	       throw new DaoException("查询数据时异常！",e);
        }
    }

    @Override
    public void deleteVcStorage(String startNum, String endNum, String docVerCode, String pressBatchNo,
            String docStatus, String orgCode) throws DaoException {
        List<Object> values = new ArrayList<Object>();
        try{
        String hql = "delete from VcStorage v where v.startNum=? and v.endNum=? and v.docVerCode=? and v.pressBatchNo=? and v.docStatus=? and v.orgCode=?";
        values.add(startNum);
        values.add(endNum);
        values.add(docVerCode);
        values.add(pressBatchNo);
        values.add(docStatus);
        values.add(orgCode);

        executeUpdate(hql, values.toArray());
        }catch(Exception e){
   	       throw new DaoException("删除数据失败！",e);
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
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
   	       throw new DaoException("查询数据失败！",e);
        }
    }

    @SuppressWarnings( { "rawtypes", "unchecked" })
    @Override
    public void mergeStorage(String docVerCode, String pressBatchNo, String docStatus, String orgCode, String oprCode)
            throws DaoException {
        QueryRule queryRule = null;
        try {
            queryRule = QueryRule.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("QueryRule拼接异常！ ", e);
        }

        if (StringUtils.isNotEmpty(docVerCode)) {
            queryRule.addEqual("docVerCode", docVerCode);
        } else {
            throw new DaoException("QueryRule查询参数docVerCode缺失");
        }

        if (StringUtils.isNotEmpty(pressBatchNo)) {
            queryRule.addEqual("pressBatchNo", pressBatchNo);
        } else {
            throw new DaoException("QueryRule查询参数pressBatchNo缺失");
        }

        if (StringUtils.isNotEmpty(docStatus)) {
            queryRule.addEqual("docStatus", docStatus);
        } else {
            throw new DaoException("QueryRule查询参数docStatus缺失");
        }

        if (StringUtils.isNotEmpty(orgCode)) {
            queryRule.addEqual("orgCode", orgCode);
        } else {
            throw new DaoException("QueryRule查询参数orgCode缺失");
        }

        queryRule.addAscOrder("startNum");

        List<VcStorage> queryList = this.find(VcStorage.class, queryRule);

        if (queryList != null && queryList.size() > 1) {
            List<VcStorage> clone = new LinkedList<VcStorage>(queryList);

            List<VcStorage> mergerList = cycleMerger(clone);

            System.out.println("合并前queryList.size() " + queryList.size());
            for (Iterator<VcStorage> it = queryList.iterator(); it.hasNext();) {
                VcStorage vcStorage = it.next();
                System.out
                        .println("queryList.vcStorage[" + vcStorage.getStartNum() + "," + vcStorage.getEndNum() + "]");
            }
            System.out.println("===========================================");

            System.out.println("合并后mergerList.size() " + mergerList.size());

            Date sysdate = new Date();

            for (Iterator<VcStorage> it = mergerList.iterator(); it.hasNext();) {
                VcStorage vcStorage = it.next();
                System.out.println("mergerList.vcStorage[" + vcStorage.getStartNum() + "," + vcStorage.getEndNum()
                        + "]");
                vcStorage.setCreateTime(queryList.get(0).getCreateTime());
                vcStorage.setCreateUserCode(queryList.get(0).getCreateUserCode());
                long docNum = Long.valueOf(vcStorage.getEndNum()) - Long.valueOf(vcStorage.getStartNum()) + 1;
                vcStorage.setDocNum(docNum);
                vcStorage.setDocStatus(docStatus);
                vcStorage.setDocVerCode(docVerCode);
                vcStorage.setInStoreTime(queryList.get(0).getInStoreTime());
                vcStorage.setModifyTime(sysdate);
                vcStorage.setModifyUserCode(oprCode);
                vcStorage.setOrgCode(orgCode);
                vcStorage.setPressBatchNo(pressBatchNo);
            }
              try{
                if (queryList.size() != mergerList.size()) {
                    this.deleteAll(queryList);
                    this.saveAll(mergerList);
                }
               } 
    	       catch (Exception e) {
                e.printStackTrace();
                throw new DaoException("库存表合并号段数据写数据异常", e);
             }
        }
    }
    /**
     * 迭代合并
     * 
     * @param queryList
     *            已有的号段集合
     * @return 合并后的集合
     */
    public List<VcStorage> cycleMerger(List<VcStorage> queryList) throws DaoException{
        List<VcStorage> newList = new LinkedList<VcStorage>();
        try{
        if (queryList.size() > 1) {
            boolean flag = false;
            for (int i = 0; i < queryList.size(); i++) {
                VcStorage self = queryList.get(i);
                if (i <= (queryList.size() - 2)) {
                    VcStorage next = queryList.get(i + 1);
                    if ((Integer.valueOf(self.getEndNum()) + 1) == Integer.valueOf(next.getStartNum())) {
                        // 首尾相接
                        VcStorage merger = new VcStorage();
                        merger.setStartNum(self.getStartNum());
                        merger.setEndNum(next.getEndNum());
                        newList.add(merger);
                        newList.addAll(queryList.subList(i + 2, queryList.size()));
                        flag = true;
                        break;
                    } else {
                        newList.add(self);
                    }
                } else {
                    newList.add(self);
                }
            }
            if (flag) {
                newList = cycleMerger(newList);
            }
        } else {
            newList.addAll(queryList);
        }
        }catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("系统异常！", e);
         }
        return newList;
    }

    @Override
    public VcStorage scopeEqual(String startNum, String endNum, String docVerCode, String pressBatchNo,
            String docStatus, String orgCode) throws DaoException {
    	List<Object> values = new ArrayList<Object>();
    	StringBuffer sql = new StringBuffer();
    	try{
       /* QueryRule queryRule = QueryRule.getInstance();
        queryRule.addEqual("docVerCode", docVerCode);
        queryRule.addEqual("pressBatchNo", pressBatchNo);
        String[] values = docStatus.split(",");
        queryRule.addIn("docStatus", values);
        queryRule.addLessEqual("startNum", startNum);
        queryRule.addGreaterEqual("endNum", endNum);
        //单证流水长度比较 add by whj 20130819
        queryRule.addSql("length(START_NUM)=length('"+startNum+"') and length(END_NUM)=length('" +endNum+"')" );
        //20130624 modify by zhxiao3 --单证发放验证流水号归属机构
        queryRule.addEqual("orgCode", orgCode);
        List<VcStorage> vcStorageList = this.find(queryRule);
        if (vcStorageList != null && vcStorageList.size() > 0) {
            return vcStorageList.get(0);
        }*/
    	sql.append(" from VcStorage t where 1=1 ");	
    	sql.append("and t.docVerCode = ? ");
    	values.add(docVerCode);
    	sql.append(" and t.pressBatchNo = ?");
    	values.add(pressBatchNo);
    	sql.append(" and t.docStatus in ('S1', 'S2', 'S3') ");
       // String[] value = docStatus.split(",");	
    	//values.add(docStatus);
    	sql.append(" and t.startNum <= ?");
    	values.add(startNum);
    	sql.append(" and t.endNum >= ?");
    	values.add(endNum);
    	sql.append(" and length(t.startNum) = length(?)");
    	values.add(startNum);
    	sql.append(" and length(t.endNum) = length(?)");
    	values.add(endNum);
    	sql.append(" and t.orgCode = ?");
    	values.add(orgCode);
    	List<VcStorage>  vcStorageList=this.findByHql(sql.toString(), values.toArray());
    	 if (vcStorageList != null && vcStorageList.size() > 0) {
             return vcStorageList.get(0);
         }
    	return null;
    	}catch(Exception e){
   	       throw new DaoException("查询数据失败！",e);
        }
    }

    @Override
    public List<StorageInfo> findStorage(String userCode, String docVerCode, String orgCode) throws DaoException {
    	List<StorageInfo> storageInfos = new ArrayList<StorageInfo>();
    	try{
	        QueryRule qr = QueryRule.getInstance();
	        Object[] params = { "S1", "S2", "S3" };
	        qr.addIn("docStatus", params);
	        qr.addEqual("orgCode", orgCode);
	        qr.addEqual("docVerCode", docVerCode);
	        List<VcStorage> vcStorages = this.find(qr);
	        for (Iterator<VcStorage> iterator = vcStorages.iterator(); iterator.hasNext();) {
	            VcStorage vcStorage = iterator.next();
	            StorageInfo storageInfo = new StorageInfo();
	            storageInfo.setDocVerCode(docVerCode);
	            storageInfo.setPressBatchNo(vcStorage.getPressBatchNo());
	            storageInfo.setUser(vcStorage.getOrgCode());
	            storageInfo.setStartNum(vcStorage.getStartNum());
	            storageInfo.setEndNum(vcStorage.getEndNum());
	            storageInfo.setDocNum(vcStorage.getDocNum());
	            storageInfo.setDocStatus(vcStorage.getDocStatus());
	            storageInfo.setDeadline(DateUtils.format(vcStorage.getInStoreTime()));
	            storageInfos.add(storageInfo);
	        }
    	}catch(Exception e){
    		throw new DaoException("查询数据时出错！", e);
    	}
       return storageInfos;
    }
    
    
    /**
     *单证出入库操作
     *<P>
     *将库存表中状态为docStatus的号段拆分出来合并到状态为docStatusTo的区间段
     * <P>
     * @param start
     *            起始流水号 (10位纯数字流水号 )
     * @param end
     *            终止流水号 (10位纯数字流水号 )
     * @param docVerCode
     *            单证类型
     * @param pressBatchNo
     *            批次
     * @param orgCode
     *            机构
     * @param docStatus
     *            被拆分的单证状态
     * @param docStatusTo
     *            将号段合并到的目标号段状态{S2-入库的记录 OT-出库的记录}
     * @param userCode
     *            当前操作用户
     * @return String 0-库存不存在或存在多条库存   1-成功
     */
    public String executeDocInOut(String start, String end, String docVerCode, String pressBatchNo, String orgCode,
            String docStatus, String docStatusTo, String userCode) throws DaoException {
        VcStorage vcStorage = null;

        // 判断库存是否存在
        if (end.length() != start.length()) {
            throw new DaoException("起始号段" + start + "（长度：" + start.length() + "）与终止号段" + end + "（长度：" + end.length() + "）长度不等，请确认！");
        }
        String[] docStatuses = { docStatus };
        List<VcStorage> vcStorageList = this.isExistAll(start, end, docVerCode, pressBatchNo, orgCode, false,
                docStatuses);
        if (vcStorageList.size() != 1) {
            if ("S2".equals(docStatusTo)) { // /S2为入库
                throw new DaoException("待入库记录[" + start + "—" + end + "]不是出库的记录，不能进行入库或存在多条出库记录！");
            } else { // OT为出库
                throw new DaoException("待出库记录[" + start + "—" + end + "]不存在或存在多条库存！");
            }
        }
        
        try {
            // 拆分=============================================================
            vcStorage = vcStorageList.get(0);

            Long tempStart = Long.valueOf(vcStorage.getStartNum());
            Long tempEnd = Long.valueOf(vcStorage.getEndNum());

            Long start_ = Long.valueOf(start);
            Long end_ = Long.valueOf(end);

            DecimalFormat df = new DecimalFormat("0");

            df.setMinimumIntegerDigits(start.length());
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
            }
            // c. start = tempStart end = tempEnd
            else if (start_ - tempStart == 0 && end_ - tempEnd == 0) {
                this.delete(vcStorage);
            }
            // d. start > tempStart end < tempEnd
            else if (start_ > tempStart && end_ < tempEnd) {
                // 一条记录拆分为两条
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
                this.save(vcStorage2);

                // 原1
                df.setMinimumIntegerDigits(start.length());
                vcStorage.setEndNum(df.format(start_ - 1));
                vcStorage.setDocNum(start_ - tempStart);
                vcStorage.setModifyTime(nowDate);
                vcStorage.setModifyUserCode(userCode);
                vcStorage.setDateUpdated(nowDate);
                vcStorage.setUpdatedBy(userCode);
                this.update(vcStorage);
            }else{
                throw new DaoException("号段异常！"); 
            }

            // 合并=============================================================
            StringBuffer tempHql=new StringBuffer();
            List<Object> tempValues=new ArrayList<Object>();
            tempHql.append(" from VcStorage s where s.docVerCode=? and s.pressBatchNo=?  and s.orgCode= ?  ");
            tempValues.add(docVerCode);
            tempValues.add(pressBatchNo);
            tempValues.add(orgCode);
            tempHql.append(" and s.docStatus=? and (s.startNum=?  or s.endNum= ? ) ");
            tempValues.add(docStatusTo);
            tempValues.add(df.format(end_ + 1));
            tempValues.add(df.format(start_ - 1));
            tempHql.append(" order by s.startNum asc  ");
            List<VcStorage> vcStoragesTemp = this.findByHql(tempHql.toString(), tempValues.toArray());
            int size = (vcStoragesTemp == null) ? 0 : vcStoragesTemp.size();

            Long startNum = null;
            Long endNum = null;
            if (size == 1) {
                VcStorage vcStorageTemp = vcStoragesTemp.get(0);
                tempStart = Long.valueOf(vcStorageTemp.getStartNum());
                tempEnd = Long.valueOf(vcStorageTemp.getEndNum());
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
                this.delete(vcStorageTemp);
            } else if (size == 2) {
                // 第一条是startNum小的记录
                VcStorage vcStorageTemp = vcStoragesTemp.get(0);
                VcStorage vcStorageTemp2 = vcStoragesTemp.get(1);

                startNum = Long.valueOf(vcStorageTemp.getStartNum());
                endNum = Long.valueOf(vcStorageTemp2.getEndNum());
                this.delete(vcStorageTemp);
                this.delete(vcStorageTemp2);
            } else if (size == 0) {
                startNum = start_;
                endNum = end_;
            } else {
                throw new DaoException("与号段[" + start + "：" + end + "]相邻的库存多于2条！");
            }

            // 合并新增的库存记录
            VcStorage vcStorageSave = new VcStorage();
            Date date = new Date();
            vcStorageSave.setStartNum(df.format(startNum));
            vcStorageSave.setEndNum(df.format(endNum));
            vcStorageSave.setDocNum(endNum - startNum + 1);
            vcStorageSave.setDocVerCode(docVerCode);
            vcStorageSave.setPressBatchNo(pressBatchNo);
            vcStorageSave.setDocStatus(docStatusTo);
            vcStorageSave.setOrgCode(orgCode);
            vcStorageSave.setInStoreTime(date);
            int maxStoreTime = 0;
            if ("S2".equals(docStatusTo)) { // /S2为入库（OT为出库）
                maxStoreTime = vcDocMngRuleDao.getMaxStoreTime(docVerCode, "0", orgCode);
            }
            vcStorageSave.setDeadline(DateUtils.addDay(date, maxStoreTime));
            vcStorageSave.setCreateUserCode(userCode);
            vcStorageSave.setCreateTime(date);
            vcStorageSave.setModifyUserCode(userCode);
            vcStorageSave.setModifyTime(date);
            vcStorageSave.setCreatedBy(userCode);
            vcStorageSave.setDateCreated(date);
            vcStorageSave.setUpdatedBy(userCode);
            vcStorageSave.setDateUpdated(date);
            this.save(vcStorageSave);
        } catch (DaoException e) {
            throw e;
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return "1";
    }
    public List<VcStorage> findStorage(String docVerCode,String pressBatchNo,String orgCode,String start_num,String end_num,String docStatus)throws DaoException {
    	List<VcStorage> list=null;
		try{
	        List<Object> values = new ArrayList<Object>();
	        String hql = " from VcStorage v where v.docVerCode= ? and v.pressBatchNo= ? AND v.orgCode= ?  and v.docStatus in ( ? ) and  (v.startNum= ? or v.endNum= ? ) order by v.startNum asc ";
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
    public void setVcDocMngRuleDao(VcDocMngRuleDao vcDocMngRuleDao) {
        this.vcDocMngRuleDao = vcDocMngRuleDao;
    }

}
