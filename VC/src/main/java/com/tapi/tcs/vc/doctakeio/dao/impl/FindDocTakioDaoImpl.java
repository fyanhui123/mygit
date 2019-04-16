package com.tapi.tcs.vc.doctakeio.dao.impl;

import java.math.BigDecimal;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.baseinfo.dao.VcTakerDao;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.doctakeio.dao.FindDocTakioDao;
import com.tapi.tcs.vc.schema.model.VcDocTakerIo;

public class FindDocTakioDaoImpl extends GenericDaoHibernate<VcDocTakerIo> implements FindDocTakioDao{
	/**
	 * 分页查询发放回收轨迹表
	 */
	public VcLevelDao vcLevelDao;
	public VcTakerDao vcTakerDao;
	@Override
	public Page findDocTakioList(String docVerCode,String docNum, UserInfo userInfo,
			int pageNo, int pageSize,boolean flag) throws DaoException {
		try {
			List<Object> values = new ArrayList<Object>();
			StringBuilder sql_a = new StringBuilder(100);                                                                                                
			sql_a.append(" select rownum as rowno,t.doc_ver_code,t1.doc_ver_name,t.press_batch_no,t.start_num,t.end_num,t.doc_num,t.opr_type,t.opr_code,to_char(t.opr_time,'YYYY-MM-DD HH24:MI:SS'),t.taker_code,t.accept_org_code,t.provide_org_code from vc_doc_taker_io t, vc_doc_version_info t1 ");
			sql_a.append(" where t.doc_ver_code = t1.doc_ver_code");
			if(StringUtils.isNotEmpty(docVerCode)){
				sql_a.append(" and t.doc_ver_code = ? ");
				values.add(docVerCode);
			}
			 if(StringUtils.isNotEmpty(docNum)){
				 sql_a.append(" and ? between t.start_num and t.end_num ");
				 values.add(docNum);
			 }
			 sql_a.append(" and t.provide_org_code like ? ");
			 values.add(userInfo.getComCode()+"%");
			 sql_a.append(" order by t.id_vc_doc_taker_io asc ");
			 List list=null;
			 Long totalCount = 0L;
			 if(flag){
				 StringBuffer sql_select = new StringBuffer();
				 sql_select.append("select count(*) from(").append(sql_a);
				 sql_select.append(")");
				 List tempCount = this.findBySql(sql_select.toString(), values.toArray());
				 Object countObj = tempCount.get(0);
		         totalCount = ((BigDecimal) countObj).longValue(); //总行数
		         StringBuilder sql_count = new StringBuilder(100);
			     sql_count.append("select * from(").append(sql_a);
				 sql_count.append(") tableView where tableView.rowno between ? and ? ");
				 values.add((pageNo - 1) * pageSize + 1);
				 values.add(pageNo * pageSize);
				 list = this.findBySql(sql_count.toString(), values.toArray());
			 }else{
				 list = this.findBySql(sql_a.toString(), values.toArray());
			 }
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 List<VcDocTakerIo> resultList = new ArrayList<VcDocTakerIo>();
			 if (list != null && list.size() > 0){
				 VcDocTakerIo  tempVo = null;
				for (int i = 0; i < list.size(); i++) {
					tempVo = new VcDocTakerIo();
					Object[] obj = (Object[]) list.get(i);
					tempVo.setDocVerCode((String) obj[1]);
					tempVo.setDocVerName((String) obj[2]);
					tempVo.setPressBatchNo((String) obj[3]);
					tempVo.setStartNum((String) obj[4]);
					tempVo.setEndNum((String) obj[5]);
					tempVo.setDocNum(Long.parseLong(obj[6].toString()));
					tempVo.setOprType(((String) obj[7]).equals("P")?"发放":"回收" );
					tempVo.setOprCode(vcLevelDao.getUnitNameByUnitCode((String) obj[8]));  
					/*SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);*/
					tempVo.setOprTime(sdf.parse((obj[9].toString())));
					tempVo.setTakerCode(vcTakerDao.getUnitNameByUnitCode((String) obj[10]));
					tempVo.setAcceptOrgCode(vcLevelDao.getUnitNameByUnitCode((String) obj[11]));
					tempVo.setProvideOrgCode(vcLevelDao.getUnitNameByUnitCode((String) obj[12]));
					resultList.add(tempVo);
				}
			 }
			 Page page = new Page(pageNo, pageSize, totalCount, resultList);
	         return page;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("查询数据库异常！", e);
		}
	}
	public void setVcLevelDao(VcLevelDao vcLevelDao) {
		this.vcLevelDao = vcLevelDao;
	}
	public void setVcTakerDao(VcTakerDao vcTakerDao) {
		this.vcTakerDao = vcTakerDao;
	}
	
}
