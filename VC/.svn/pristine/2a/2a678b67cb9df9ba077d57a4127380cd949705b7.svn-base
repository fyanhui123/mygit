package com.tapi.tcs.vc.store.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcApply;
import com.tapi.tcs.vc.schema.model.VcApplyDet;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.dao.ApplyDao;

/**
 * ApplyDao实现类
 * 
 * @author hanmiao.diao
 * 
 */
public class ApplyDaoImpl extends GenericDaoHibernate<VcApply> implements ApplyDao {
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.apply.dao.ApplyDao#insertVcApply(com.tapi.tcs.vc.schema
     * .model.VcApply)
     */
    public void insertVcApply(VcApply vcApply) throws DaoException {
    	try{
    		 save(vcApply);
    	}catch(Exception  e){
    		throw new DaoException("添加数据库异常！",e);
    	}
       
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.apply.dao.ApplyDao#insertVcApplyDet(com.tapi.tcs.vc.schema
     * .model.VcApplyDet)
     */
    public void insertVcApplyDet(VcApplyDet vcApplyDet) throws DaoException {
    	try{
    		save(vcApplyDet);
    	}catch(Exception e){
    		throw new DaoException("添加数据库异常!",e);
    	}
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tapi.tcs.vc.apply.dao.ApplyDao#queryApplyList(java.util.Map,
     * int, int)
     */
    public Page queryApplyList(Map<String, Object> params, int pageNo, int pageSize) throws DaoException {
        QueryRule queryRule = null;
        Page page=new Page();
        try {
            queryRule = QueryRule.getInstance();
        String applyCode = (String) params.get("applyCode");
        if (StringUtils.isNotEmpty(applyCode)) {
            queryRule.addEqual("applyCode", applyCode);
        }
        String applyStatus = (String) params.get("applyStatus");
        if (StringUtils.isNotEmpty(applyStatus)) {
            queryRule.addEqual("applyStatus", applyStatus);
        }
        String startDate = (String) params.get("startDate");
        if (StringUtils.isNotEmpty(startDate)) {
            Date date1 = DateUtils.parse(startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            queryRule.addGreaterEqual("applyTime", date1);
        }
        String endDate = (String) params.get("endDate");
        if (StringUtils.isNotEmpty(endDate)) {
            Date date1 = DateUtils.parse(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
            queryRule.addLessEqual("applyTime", date1);
        }
        String applyOrgCode = (String) params.get("applyOrgCode");
        if (StringUtils.isNotEmpty(applyOrgCode)) {
            queryRule.addEqual("applyOrgCode", applyOrgCode);
        }
        String provideOrgCode = (String) params.get("provideOrgCode");
        if (StringUtils.isNotEmpty(provideOrgCode)) {
            queryRule.addEqual("provideOrgCode", provideOrgCode);
        }

        queryRule.addDescOrder("applyTime");
        page=this.find(VcApply.class, queryRule, pageNo, pageSize);
        } catch (Exception e) {
            throw new DaoException("查询数据库异常！", e);
        }
        return page;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.store.dao.ApplyDao#queryValidStorageList(java.util.Map,
     * int, int)
     */
    @Override
    public Page queryValidStorageList(Map<String, Object> params, int page, int rows) throws DaoException {
        QueryRule queryRule = null;
        Page pa=new Page();
        try {
            queryRule = QueryRule.getInstance();
        String docVerCode = (String) params.get("docVerCode");
        String validDocStatus = (String) params.get("validDocStatus");
        String[] docStatus=new String[] { "S1", "S2", "S3" };
        if(StringUtils.isNotBlank(validDocStatus)){
            docStatus=validDocStatus.split(",");  
        }
        if (StringUtils.isNotEmpty(docVerCode)) {
            queryRule.addEqual("docVerCode", docVerCode);
        }
        String validOrg = (String) params.get("validOrg");
        String orgCode = (String) params.get("orgCode");
        if (StringUtils.isNotEmpty(validOrg)) {
           /* queryRule.addSql(" ORG_CODE IN (" + " SELECT T.UNIT_CODE " + " FROM VC_LEVEL T "
                    + " WHERE T.UNIT_TYPE = '0' " + " START WITH T.UNIT_CODE = '" + orgCode + "' "
                    + " CONNECT BY T.PARENT_ORG_ID = PRIOR T.ID_VC_LEVEL " + ") ");*/
            queryRule.addEqual("orgCode", validOrg);
        } else {
            // 
            if (StringUtils.isNotEmpty(orgCode)) {
                queryRule.addEqual("orgCode", orgCode);
            }
        }
       
        queryRule.addIn("docStatus",docStatus);
        pa=this.find(VcStorage.class, queryRule, page, rows);
        }catch (Exception e) {
            throw new DaoException("查询数据库异常！ ", e);
        }
        return pa;
    }
    
    @Override
    public Page queryValidStorageListNew(Map<String, Object> params, int page, int rows) throws DaoException {
    	Page p = null;
    	try{
    		String docVerCode = (String) params.get("docVerCode");
    		String validOrg = (String) params.get("validOrg");
    		String docStatus= (String)params.get("status");
    		StringBuffer sql = new StringBuffer();
    		List<Object> values =new ArrayList<Object>();
    		//查询库存表
    		sql.append("select s.doc_ver_code,s.press_batch_no,s.org_code,s.doc_status,");
    		sql.append(" s.start_num,s.end_num,s.doc_num,s.deadline from vc_storage s where 1=1");
    		if (StringUtils.isNotEmpty(docVerCode)) {
                sql.append(" and s.doc_ver_code=? ");
                values.add(docVerCode);
            }
    		if(StringUtils.isNotEmpty(validOrg)){
    			sql.append(" and exists(select 1 from vc_level v where v.unit_code=s.org_code");
    			sql.append(" and v.unit_type=? and v.valid_status=? ");
    			values.add("0");
    			values.add("1");
    			sql.append(" start with v.unit_code= ?  connect by prior v.id_vc_level=v.parent_org_id)");
    			values.add(validOrg);
    		}
    		if("".equals(docStatus)||null==docStatus){
            sql.append(" and s.doc_status in (?,?,?)");
    		values.add("S1");
    		values.add("S2");
    		values.add("S3");
    		}else{
    			sql.append(" and s.doc_status = ? ");
    			values.add(docStatus);
    		}
    		//查询可使用表
    		sql.append(" union");
    		sql.append(" select b.doc_ver_code,b.press_batch_no,b.org_code,b.doc_status,");
    		sql.append(" min(b.doc_num) start_num,max(b.doc_num) end_num,count(cc) doc_num,b.deadline ");
    		sql.append(" from (select a.*, (to_number(a.doc_num) - rownum) cc");
    		sql.append(" from (select * from vc_available_doc t where 1=1");
    		if (StringUtils.isNotEmpty(docVerCode)) {
                sql.append(" and t.doc_ver_code=? ");
                values.add(docVerCode);
            }
    		if(StringUtils.isNotEmpty(validOrg)){
    			sql.append(" and exists(select 1 from vc_level vl where vl.unit_code=t.org_code");
    			sql.append(" and vl.unit_type=? and vl.valid_status=? ");
    			values.add("0");
                values.add("1");
    			sql.append(" start with vl.unit_code=? connect by prior vl.id_vc_level=vl.parent_org_id)");
    			values.add(validOrg);
    		}
    		sql.append(" and t.doc_status= ? ");
    		if("".equals(docStatus)||null==docStatus){
    			values.add("A");
    		}else{
    			values.add(docStatus);
    		}
    		//values.add("A");
    		sql.append(" order by t.org_code,t.doc_ver_code,t.press_batch_no,to_number(t.doc_num),t.deadline) a) b");
    		sql.append(" group by b.org_code, b.doc_ver_code, b.press_batch_no, b.doc_status, b.deadline,b.cc");
    	
    		StringBuffer sql_count = new StringBuffer(""); 
    		sql_count.append("select count(*)   from(");
    		sql_count.append(sql);
			sql_count.append(")");

			Long totalCount = 0L;
			List tempCount = this.findBySql(sql_count.toString(), values.toArray());
			Object countObj = tempCount.get(0);
			totalCount = ((BigDecimal) countObj).longValue();

			// 查询分页列表
			StringBuilder sql_query = new StringBuilder(100);
			sql_query.append("select numId,doc_ver_code,press_batch_no,org_code,doc_status,start_num,end_num,doc_num,deadline");
			sql_query.append(" from(select  temp.*,rowNum numId  from  (");
			sql_query.append(sql);
			sql_query.append(")temp ) tableView where tableView.numId between ? and ?");
			values.add((page - 1) * rows + 1);
			values.add(page * rows);
    		
    		List list = this.findBySql(sql_query.toString(), values.toArray());
    		p = new Page(page, rows, totalCount, list);
    	}catch(Exception e){
    		throw new DaoException("查询数据库异常！ ", e);
    	}
    	return p;
    }
    
    @Override
    public Page queryAvailableList(Map<String, Object> params, int page, int rows) throws DaoException {
    	Page returnPage = null;
    	List<Object> values = new ArrayList<Object>();
    	//获取查询条件
    	String docVerCode = (String) params.get("docVerCode");
    	String orgCode = (String) params.get("orgCode");
    	String takerCode = (String) params.get("takerCode");
    	//拼接SQL语句
    	StringBuffer sql = new StringBuffer();
    	sql.append(" select b.doc_ver_code,b.press_batch_no,b.org_code,b.doc_status,");
		sql.append(" min(b.doc_num) start_num,max(b.doc_num) end_num,count(cc) doc_num,");
		//MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 BEGIN
		sql.append("to_date(to_char(b.deadline,'yyyy-MM-dd'),'yyyy-MM-dd') deadline");
		sql.append(" from (select a.*, (to_number(a.doc_num) - rownum) cc");
		sql.append(" from (select * from vc_available_doc t where 1=1");
		if (StringUtils.isNotEmpty(docVerCode)) {
            sql.append(" and t.doc_ver_code=? ");
            values.add(docVerCode);
        }
		if(StringUtils.isNotEmpty(orgCode)){
			sql.append(" and t.org_code= ? ");
			values.add(orgCode);
		}
		if(StringUtils.isNotEmpty(takerCode)){
			sql.append(" and t.taker_code= ? ");
			values.add(takerCode);
		}
		sql.append(" and t.doc_status='A'");
		sql.append(" order by t.org_code,t.doc_ver_code,t.press_batch_no,to_number(t.doc_num), t.deadline) a) b");
		sql.append(" group by b.org_code, b.doc_ver_code, b.press_batch_no, b.doc_status, b.cc ,b.deadline");
		try{
			StringBuffer sql_count = new StringBuffer(""); 
    		sql_count.append("select count(*)   from(");
    		sql_count.append(sql);
			sql_count.append(")");

			Long totalCount = 0L;
			List tempCount = this.findBySql(sql_count.toString(),values.toArray());
			Object countObj = tempCount.get(0);
			totalCount = ((BigDecimal) countObj).longValue();

			// 查询分页列表
			StringBuilder sql_query = new StringBuilder(100);
			sql_query.append("select numId,doc_ver_code,press_batch_no,org_code,doc_status,start_num,end_num,doc_num,deadline");
			//MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 END
			sql_query.append(" from(select  temp.*,rowNum numId  from  (");
			sql_query.append(sql);
			sql_query.append(")temp ) tableView where tableView.numId between ? and ?");
			
			values.add((page - 1) * rows + 1);
			values.add(page * rows);
    		
    		List list = this.findBySql(sql_query.toString(), values.toArray());
    		returnPage = new Page(page, rows, totalCount, list);
		}catch(Exception e){
			throw new DaoException("查询数据库异常！ ", e);
		}
    	return returnPage;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.apply.dao.ApplyDao#deleteApplyDetByVcApplyId(java.lang
     * .Long)
     */
    public void deleteApplyDetByVcApplyId(Long id) throws DaoException {
    	try{
	        String hql = "delete from VcApplyDet t where t.vcApply.id=?";
	        List<Object> values = new ArrayList<Object>();
	        values.add(id);
	        this.executeUpdate(hql, values.toArray());
    	}catch(Exception e){
    		throw new DaoException("删除数据异常！",e);
    	}
    }

    @SuppressWarnings("unchecked")
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.apply.dao.ApplyDao#getApplyDetListByVcApplyId(java.lang
     * .Long)
     */
    public List<VcApplyDet> getApplyDetListByVcApplyId(Long vcApplyId) throws DaoException {
    	List<VcApplyDet> vcApplyDetList=null;
    	try{
        String hsql = "from VcApplyDet v where v.vcApply.id=? ";
        Object[] values = new Object[] { vcApplyId };
        vcApplyDetList= this.findByHql(hsql, values);
    	}catch(Exception e){
    		throw new DaoException("查询数据库异常！",e);
    	}
        return vcApplyDetList;
    }

    @SuppressWarnings("unchecked")
    /*
     * (non-Javadoc)
     * 
     * @see com.tapi.tcs.vc.store.dao.ApplyDao#getVcApply(java.lang.String)
     */
    public VcApply getVcApply(String applyCode) throws DaoException {
    	try{
    		String hsql = "from VcApply v where v.applyCode=? ";
            Object[] values = new Object[] { applyCode };

            List<VcApply> vcApplyList = this.findByHql(hsql, values);	
	        if (vcApplyList != null && vcApplyList.size() == 1) {
	            return vcApplyList.get(0);
	        } else {
	            return null;
	        }
        }catch(Exception e){
    		throw new DaoException("查询数据库异常！",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tapi.tcs.vc.store.dao.ApplyDao#getVcApplyDet(java.lang.Long,
     * java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public VcApplyDet getVcApplyDet(Long vcApplyId, String docVerCode) throws DaoException {
    	try{
	        String hsql = "from VcApplyDet v where v.vcApply.id=? and v.docVerCode=?";
	        Object[] values = new Object[] { vcApplyId, docVerCode };
	
	        List<VcApplyDet> vcApplyDetList = this.findByHql(hsql, values);
	
	        if (vcApplyDetList != null && vcApplyDetList.size() == 1) {
	            return vcApplyDetList.get(0);
	        }
        }catch(Exception e){
    		throw new DaoException("查询数据库异常！",e);
        }
        return null;
    }

    @SuppressWarnings( { "rawtypes", "unchecked" })
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.store.dao.ApplyDao#findValidStorageNum(java.lang.String,
     * java.lang.String)
     */
    public Long findValidStorageNum(String orgCode, String docVerCode) throws DaoException {
    	try{
        String hsql = "from VcStorage v where v.orgCode=? and v.docVerCode=?";
        Object[] values = new Object[] { orgCode, docVerCode };

        List<VcStorage> vcStorageList = this.findByHql(hsql, values);

        if (vcStorageList == null || vcStorageList.isEmpty()) {
            return 0l;
        } else {
            long sum = 0;
            for (Iterator it = vcStorageList.iterator(); it.hasNext();) {
                VcStorage vcStorage = (VcStorage) it.next();
                sum = sum + vcStorage.getDocNum();
            }
            return sum;
        }
    	}catch(Exception e){
    		throw new DaoException("查询数据库异常！",e);
        }
    }

    /****
     * 辖内有效库存
     */
    @Override
    public Long validStorageNum(String orgCode, String docVerCode) throws DaoException {
    	List<VcStorage> Lists = new ArrayList<VcStorage>();
        Long validStoreNum = 0L;
        try {
           List<Object> values=new ArrayList<Object>();
           StringBuffer sb = new StringBuffer(" select t1.ID_VC_STORAGE ,   t1.CREATE_TIME ,   t1.CREATE_USER_CODE ,  t1.CREATED_BY    ,   t1.DATE_CREATED  ,   t1.DATE_UPDATED  ,  t1.DEADLINE     ,    t1.DOC_NUM   ,    t1.DOC_STATUS   ,  t1.DOC_VER_CODE  ,  t1.END_NUM   ,   t1.IN_STORE_TIME  ,     t1.MODIFY_TIME    ,    t1.MODIFY_USER_CODE   ,t1.ORG_CODE   ,t1.PRESS_BATCH_NO  , t1.START_NUM   ,  t1.UPDATED_BY from VC_STORAGE t1  where 1=1 ");
           sb.append("and t1.ORG_CODE in("+
           "SELECT T.UNIT_CODE FROM VC_LEVEL T WHERE T.UNIT_TYPE = ? "+
           "START WITH T.UNIT_CODE = ? "+
           "CONNECT BY T.PARENT_ORG_ID = PRIOR T.ID_VC_LEVEL) "+
           "and t1.DEADLINE >= sysdate "+
           "and t1.DOC_VER_CODE = ? "+
           "and T1.DOC_STATUS in ('S1', 'S2', 'S3')");
           values.add('0');
           values.add(orgCode);
           values.add(docVerCode);
           Long vcNum = 0L;
           Long avNum = 0L;
           List<Object[]> vcStorageList  = this.findBySql(sb.toString(), values.toArray());
           if (vcStorageList == null || vcStorageList.isEmpty()) {
                  vcNum = 0L;
              } 
           else{
             Lists = new ArrayList<VcStorage>();
             for(Object[] obj : vcStorageList){
               VcStorage vs=new VcStorage();
               if(null!= obj[7]&& obj[7]!=""){
                 long docNum=Long.parseLong(obj[7].toString()); 
                 vs.setDocNum(docNum);
               }
               Lists.add(vs);
               vcNum = vcNum + vs.getDocNum();
             }
          }
         List<Object> avableValues=new ArrayList<Object>();
         StringBuffer sbav = new StringBuffer("select  t1.ID_VC_AVAILABLE_DOC ,t1.AGENT_CODE      ,t1.CREATED_BY  , t1.DATE_CREATED     ,t1.DATE_UPDATED        , t1.DEADLINE            , t1.DOC_NUM   ,t1.DOC_STATUS ,t1.DOC_VER_CODE  , t1.ID_TAKER_IO  , t1.ORG_CODE , t1.PRESS_BATCH_NO   , t1.PROVIDE_TIME  ,t1.TAKER_CODE  ,t1.UPDATED_BY  from VC_AVAILABLE_DOC t1 where 1=1 and ");
         sbav.append("t1.ORG_CODE IN(");
         sbav.append("SELECT T.UNIT_CODE FROM VC_LEVEL T WHERE T.UNIT_TYPE = ? ");
         avableValues.add('0');
         sbav.append("START WITH T.UNIT_CODE = ? ");
         avableValues.add(orgCode);
         sbav.append("CONNECT BY T.PARENT_ORG_ID = PRIOR T.ID_VC_LEVEL)");
         sbav.append(" and t1.DOC_VER_CODE = ? ");
         avableValues.add(docVerCode);
         sbav.append(" and t1.DOC_STATUS = ? ");
         avableValues.add('A');
         List<VcAvailableDoc> vcAvailableDocList = this.findBySql(sbav.toString(), avableValues.toArray());
              if (vcAvailableDocList == null || vcAvailableDocList.isEmpty()) {
                  avNum = 0L;
              } else {
                  avNum = (long) vcAvailableDocList.size();
              }
              validStoreNum = avNum + vcNum;
        }catch(Exception e){
    		throw new DaoException("查询数据库异常！",e);
        }
        return validStoreNum;
    }

	@Override
	public Long localValidStorageNum(String orgCode, String docVerCode)throws DaoException {
		try{
		    QueryRule queryRule = QueryRule.getInstance();
		   // 使用截止日期大于等于当前日期
	        queryRule.addGreaterEqual("deadline", new Date());
	        // 增加单证类型查询条件
	        queryRule.addEqual("docVerCode", docVerCode);
	        // 增加单证状态查询条件
	        queryRule.addIn("docStatus", new String[] { "S1", "S2", "S3" });
	        //增加机构查询条件
	        queryRule.addEqual("orgCode", orgCode);
	        List<VcStorage> vcStorageList = this.find(VcStorage.class, queryRule);
	        if (vcStorageList == null || vcStorageList.isEmpty()) {
	            return 0l;
	        } else {
	            long sum = 0;
	            for (Iterator it = vcStorageList.iterator(); it.hasNext();) {
	                VcStorage vcStorage = (VcStorage) it.next();
	                sum = sum + vcStorage.getDocNum();
	            }
	            return sum;
	        }
		}catch(Exception e){
    		throw new DaoException("查询数据库异常！",e);
        }
	}
	
	@Override
	public List<VcDocVersionInfo> getVcDocVersionInfoList(Long applyId, String docVerCode) throws DaoException {
		List<VcDocVersionInfo> list = null;
		try{
			String hql = "from VcDocVersionInfo ver where exists (" +
					"select 1 from VcApplyDet det where ver.docVerCode=det.docVerCode and det.idVcApply=?) " +
					"and ver.docVerCode like ?";
			list = (List<VcDocVersionInfo>)this.findByHql(hql, new Object[]{applyId, "%"+docVerCode+"%"});
		}catch(Exception e){
			throw new DaoException("查询数据库异常！", e);
		}
		return list;
	}
	
	@Override
	public Integer getApplyNum(Long applyId, String docVerCode) throws DaoException {
		Integer result = 0;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("vcApply.id", applyId);
			queryRule.addEqual("docVerCode", docVerCode);
			List<VcApplyDet> list = this.find(VcApplyDet.class, queryRule);
			if(list!=null && list.size()>0){
				result = list.get(0).getApplyNum();
			}
		}catch(Exception e){
			throw new DaoException("查询数据库异常！", e);
		}
		return result;
	}

	@Override
	public Page queryAbNormalList(Map<String, Object> params, int pageNo,
			int pageSize) throws DaoException {
		Page page = null;
    	try{
    		String docVerCode = (String) params.get("docVerCode");
    		String orgCode = (String) params.get("orgCode");
    		StringBuffer sql = new StringBuffer();
    		List<Object> values =new ArrayList<Object>();
    		//查询非正常核销表
    		List<Object> value =new ArrayList<Object>();
    		sql.append(" select rowNum as numId,t.doc_ver_code,t.press_batch_no,t.doc_num,t.verified_time,t.doc_status,t.verified_org_code from vc_abnormal_verification t");
    		sql.append(" where 1=1 ");
    		sql.append(" and t.doc_ver_code = ? ");
    		values.add(docVerCode);
    		sql.append(" and t.doc_status= ? ");
    		values.add("C1");
    		sql.append(" and  t.verified_org_code like ?");
    		values.add(orgCode+"%");
			// 查询分页列表
    		 StringBuffer sql_select = new StringBuffer();
    		 sql_select.append("select count(*) from(").append(sql);
			 sql_select.append(")");
			 Long totalCount = 0L;
			 List tempCount = this.findBySql(sql_select.toString(), values.toArray());
			 Object countObj = tempCount.get(0);
	         totalCount = ((BigDecimal) countObj).longValue();
	         
			 StringBuilder sql_count = new StringBuilder(100);
			 sql_count.append("select * from(").append(sql);
			 sql_count.append(") tableView where tableView.numId between ? and ? ");
			 values.add((pageNo - 1) * pageSize + 1);
			 values.add(pageNo * pageSize);
    		 List list = this.findBySql(sql_count.toString(), values.toArray());
    	     page = new Page(pageNo, pageSize, totalCount, list);
    	}catch(Exception e){
    		throw new DaoException("查询数据库异常！ ", e);
    	}
    	return page;
	}

	
}
