package com.tapi.tcs.vc.baseinfo.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.vc.rolelist.vo.RoleListVo;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerifiedDet;
import com.tapi.tcs.vc.schema.model.VcDocPrtNoRule;
import com.tapi.tcs.vc.schema.model.VcDocType;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfoMng;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDet;
import com.tapi.tcs.vc.schema.model.VcOrderLaunch;
import com.tapi.tcs.vc.baseinfo.dao.VcDocVersionInfoDao;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.baseinfo.vo.DetileNormalVO;
import com.tapi.tcs.vc.baseinfo.vo.DocVerSimpleVo;
import com.tapi.tcs.vc.baseinfo.vo.DocVersionInfoQueryVo;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;

/**
 * 单证版本信息DAO实现
 * <p>
 * Date: 2013-03-13
 * </p>
 * <p>
 * Module: 单证版本信息
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * @author wanghuajian
 * @version 1.0
 */
public class VcDocVersionInfoDaoImpl extends GenericDaoHibernate<VcDocVersionInfo> implements
        VcDocVersionInfoDao {
    
    private VcLevelDao vcLevelDao;
    public void setVcLevelDao(VcLevelDao vcLevelDao) {
        this.vcLevelDao = vcLevelDao;
    }
    /**
     * 根据保单号，单证类型查询单证明细表（非正常核销，正常核销表）
     */
    @Override
	public Object queryDetail(String bussinessNo, UserInfo userInfo,
			String document,String startNum ,boolean flag,int pageNo,
			int pageSize)
			throws BusinessException {
    	List<DetileNormalVO> resultList = new ArrayList<DetileNormalVO>();
		try{
			List<Object> values = new ArrayList<Object>();
			if(flag){
				StringBuffer sql_a = new StringBuffer("");
				sql_a.append("select rownum as rowno, t.business_no,t.pay_no,t.batch_no from vc_normal_verified_det t where t.id_vc_normal_verification in (");
				sql_a.append(" select t1.id_vc_normal_verification from vc_normal_verification t1  where t1.doc_ver_code = ? ");
				values.add(document);
				sql_a.append(" and t1.doc_num = ? ");
				values.add(startNum);
				sql_a.append("and t1.business_no = ? ");
				
			    values.add(bussinessNo);
			    sql_a.append(")");
			    StringBuffer sql_select = new StringBuffer();
			    sql_select.append("select count(*) from(").append(sql_a);
				sql_select.append(")");
				Long totalCount = 0L;
				List tempCount = this.findBySql(sql_select.toString(), values.toArray());
				Object countObj = tempCount.get(0);
		        totalCount = ((BigDecimal) countObj).longValue();
				StringBuilder sql_count = new StringBuilder(100);
				sql_count.append("select * from(").append(sql_a);
				sql_count.append(") tableView where tableView.rowno between ? and ? ");
				values.add((pageNo - 1) * pageSize + 1);
				values.add(pageNo * pageSize);
				List list = this.findBySql(sql_count.toString(), values.toArray());
				if (list != null && list.size() > 0) {
					DetileNormalVO detileNormal=null;
					for(int i=0;i<list.size();i++){
						Object[] obj = (Object[]) list.get(i);
						detileNormal=new DetileNormalVO();
						detileNormal.setBusinessNo((String) obj[1]);
						detileNormal.setPayNo((String) obj[2]);
						detileNormal.setBatchNo((String) obj[3]);
						resultList.add(detileNormal);
					}
				}
				Page page = new Page(pageNo, pageSize, totalCount, resultList);
				return page;
			}else{
				StringBuffer sql_a = new StringBuffer("");
				sql_a.append(" select rownum as rowno, t.business_no,t.pay_no,t.batch_no from vc_abnormal_verified_det t where t.id_vc_abnormal_verification in (");
				sql_a.append(" select t1.id_vc_abnormal_verification from vc_abnormal_verification t1 where t1.doc_ver_code = ? ");
				values.add(document);
				sql_a.append(" and t1.doc_num = ? ");
				values.add(startNum);
				sql_a.append(" and t1.business_no =? ");
				values.add(bussinessNo);
				sql_a.append(")");
				StringBuffer sql_select = new StringBuffer();
			    sql_select.append("select count(*) from(").append(sql_a);
				sql_select.append(")");
				Long totalCount = 0L;
				List tempCount = this.findBySql(sql_select.toString(), values.toArray());
				Object countObj = tempCount.get(0);
		        totalCount = ((BigDecimal) countObj).longValue();
				StringBuilder sql_count = new StringBuilder(100);
				sql_count.append("select * from(").append(sql_a);
				sql_count.append(") tableView where tableView.rowno between ? and ? ");
				values.add((pageNo - 1) * pageSize + 1);
				values.add(pageNo * pageSize);
				List list = this.findBySql(sql_count.toString(), values.toArray());
				if (list != null && list.size() > 0) {
					DetileNormalVO detileNormal=null;
					for(int i=0;i<list.size();i++){
						Object[] obj = (Object[]) list.get(i);
						detileNormal=new DetileNormalVO();
						detileNormal.setBusinessNo((String) obj[1]);
						detileNormal.setPayNo((String) obj[2]);
						detileNormal.setBatchNo((String) obj[3]);
						resultList.add(detileNormal);
					}
				}
				Page page = new Page(pageNo, pageSize, totalCount, resultList);
				return page;
			}
		}catch(Exception e){
			throw new DaoException("查询单证详细信息时发生异常！", e);
		}
	}
    /**
     * 根据单证版本信息ID查询单证版本信息
     */
    public VcDocVersionInfo getVcDocVersionInfo(Long idVcDocVersionInfo) {
        return (VcDocVersionInfo) super.get(VcDocVersionInfo.class, idVcDocVersionInfo);
    }

    /**
     * 根据查询条件查询满足条件的单证版本信息
     * 
     * @param queryVo
     * @param pageNo
     * @param pageSize
     * @return
     * @author wanghuajian since 2013-4-12上午09:30:40
     */
    public Page queryVcDocVersionInfos(DocVersionInfoQueryVo queryVo, int pageNo, int pageSize)
            throws DaoException {

        try {
            StringBuffer sb = new StringBuffer("from VcDocVersionInfo doc ");

            sb.append("where 1=1 ");
            List values = new ArrayList();
            if (queryVo != null) {
                // 单证版本code
                if (StringUtils.isNotBlank(queryVo.getDocVerCode())) {
                    sb.append(" and doc.docVerCode = ?");
                    values.add(queryVo.getDocVerCode());
                }
             // 单证版本name（完全匹配查询）
                if (StringUtils.isNotBlank(queryVo.getDocVerNameEQ())) {
                    sb.append(" and doc.docVerName = ? ");
                    values.add( queryVo.getDocVerNameEQ());
                }
                // 单证版本name
                if (StringUtils.isNotBlank(queryVo.getDocVerName())) {
                    sb.append(" and doc.docVerName like ? ");
                    values.add("%" + queryVo.getDocVerName() + "%");
                }
                // 状态
                if (StringUtils.isNotBlank(queryVo.getStatus())) {
                    sb.append(" and doc.status = ? ");
                    values.add(queryVo.getStatus());
                }
                
                // 是否征订
                if (StringUtils.isNotBlank(queryVo.getIsOrder())) {
                    sb.append(" and doc.isOrder = ? ");
                    values.add(queryVo.getIsOrder());
                }
                
                // 单证种类
                if (queryVo.getIdVcDocType() != null) {
                    sb.append(" and idVcDocType= ?");
                    values.add(queryVo.getIdVcDocType());
                }
                /*
                 * if (StringUtils.isNotBlank(queryVo.getDocTypeCode())) { sb.append(" and doc.docTypeCode = ? ");
                 * values.add(queryVo.getDocTypeCode()); }
                 */
                // 业务0、财务1区分
                if (StringUtils.isNotBlank(queryVo.getDocType())
                        || StringUtils.isNotBlank(queryVo.getDocTypeCode())) {
                    sb
                            .append(" and exists(select 1 from VcDocType type where type.idVcDocType=doc.idVcDocType  ");
                    if (StringUtils.isNotBlank(queryVo.getDocType())) {
                        sb.append(" and type.docType=?");
                        values.add(queryVo.getDocType());
                    }
                    if (StringUtils.isNotBlank(queryVo.getDocTypeCode())) {
                        sb.append(" and type.docTypeCode=?");
                        values.add(queryVo.getDocTypeCode());
                    }
                    sb.append(") ");
                }
                // 单证险类、险种
                if (queryVo.getInsuTypeId() != null || StringUtils.isNotBlank(queryVo.getInsuTypeCode())
                        || StringUtils.isNotBlank(queryVo.getInsuKind())) {
                    sb
                            .append(" and exists(select 1 from VcDocInsuKind insuKind, VcDocInsuType insuType, VcDocRelInsuKind ref ");
                    sb
                            .append(" where insuKind.insuKindCode=ref.insuKindCode and ref.idVcDocVersionInfo=doc.idVcDocVersionInfo and insuKind.insuType=insuType ");
                    if (StringUtils.isNotBlank(queryVo.getInsuTypeCode())) {
                        sb.append(" and insuType.insuTypeCode=? ");
                        values.add(queryVo.getInsuTypeCode());
                    }
                    if (queryVo.getInsuTypeId() != null) {
                        sb.append(" and insuKind.idVcDocInsuType=? ");
                        values.add(queryVo.getInsuTypeId());
                    }

                    if (StringUtils.isNotBlank(queryVo.getInsuKind())) {
                        sb.append(" and insuKind.insuKindCode=? ");
                        values.add(queryVo.getInsuKind());
                    }
                    sb.append(" ) ");
                }

                // 关联地区
                if (StringUtils.isNotBlank(queryVo.getOrgCode())) {
                    sb
                            .append(" and exists(select 1 from VcDocRelArea area where area.idVcDocVersionInfo=doc.idVcDocVersionInfo and area.orgCode = ?) ");
                    values.add(queryVo.getOrgCode());
                }
            }

            return super.findByHqlNoLimit(sb.toString(), pageNo, pageSize, values.toArray());
        } catch (Exception e) {
            throw new DaoException("查询数据库失败！");
        }
    }
    
    
    /**
     * 单证类型查询[单证明细查询用]
     * 
     * @param queryVo
     * @param userInfo
     *            当前用户信息 
     * @param pageNo
     * @param pageSize
     * @return
     * @author wanghuajian since 2013-4-12上午09:30:40
     */
    public Page queryDocForDocDetInquiry(DocVersionInfoQueryVo queryVo,UserInfo userInfo, int pageNo, int pageSize)
            throws DaoException {
        try {
            StringBuffer sb = new StringBuffer("select doc.idVcDocVersionInfo,doc.docVerCode,doc.docVerName,doc.orgCode,doc.status from VcDocVersionInfo doc ");

            sb.append("where 1=1 ");
            List values = new ArrayList();
            if (queryVo != null) {
                // 单证版本code
                if (StringUtils.isNotBlank(queryVo.getDocVerCode())) {
                    sb.append(" and doc.docVerCode = ?");
                    values.add(queryVo.getDocVerCode());
                }
             
                // 单证版本name
                if (StringUtils.isNotBlank(queryVo.getDocVerName())) {
                    sb.append(" and doc.docVerName like ? ");
                    values.add("%" + queryVo.getDocVerName() + "%");
                }
                // 状态
                if (StringUtils.isNotBlank(queryVo.getStatus())) {
                    sb.append(" and doc.status = ? ");
                    values.add(queryVo.getStatus());
                }
                // 单证种类
                if (queryVo.getIdVcDocType() != null) {
                    sb.append(" and idVcDocType= ?");
                    values.add(queryVo.getIdVcDocType());
                }
               
                /*// 单证险类、险种
                if (queryVo.getInsuTypeId() != null || StringUtils.isNotBlank(queryVo.getInsuTypeCode())
                        || StringUtils.isNotBlank(queryVo.getInsuKind())) {
                    sb.append(" and exists(select 1 from VcDocInsuKind insuKind, VcDocInsuType insuType, VcDocRelInsuKind ref ");
                    sb.append(" where insuKind.insuKindCode=ref.insuKindCode and ref.idVcDocVersionInfo=doc.idVcDocVersionInfo and insuKind.insuType=insuType ");
                    if (StringUtils.isNotBlank(queryVo.getInsuTypeCode())) {
                        sb.append(" and insuType.insuTypeCode=? ");
                        values.add(queryVo.getInsuTypeCode());
                    }
                    if (queryVo.getInsuTypeId() != null) {
                        sb.append(" and insuKind.idVcDocInsuType=? ");
                        values.add(queryVo.getInsuTypeId());
                    }

                    if (StringUtils.isNotBlank(queryVo.getInsuKind())) {
                        sb.append(" and insuKind.insuKindCode=? ");
                        values.add(queryVo.getInsuKind());
                    }
                    sb.append(" ) ");
                }*/
                
                // 单证险类、险种[发票的不判断险类险种]
                if (queryVo.getIdVcDocType()!=13 && (StringUtils.isNotBlank(queryVo.getInsuTypeCode())
                        || StringUtils.isNotBlank(queryVo.getInsuKind()))) {
                    sb.append(" and exists(select 1 from PubRisk risk, VcDocRelInsuKind ref ");
                    sb.append(" where risk.riskCode=ref.insuKindCode and ref.idVcDocVersionInfo=doc.idVcDocVersionInfo ");
                    boolean hasAnd=false;
                    sb.append(" and ((");
                    if (StringUtils.isNotBlank(queryVo.getInsuTypeCode())) {
                        sb.append(" risk.riskClass=? ");
                        values.add(queryVo.getInsuTypeCode());
                        hasAnd=true;
                    }
                    if (StringUtils.isNotBlank(queryVo.getInsuKind())) {
                        if(hasAnd){
                           sb.append(" and risk.riskCode=? ");
                        }else{
                           sb.append(" risk.riskCode=? ");   
                        }
                        values.add(queryVo.getInsuKind());
                    }
                    sb.append(" )or( ref.insuKindCode=? )))");
                    values.add("0000");
                }

                //当前用户机构查询
                if (StringUtils.isNotBlank(userInfo.getComCode())) {
                    ///查询所有上级机构
                   List<VcLevel> orgList = vcLevelDao.queryUpOrgListByUnitCode(userInfo.getComCode(),7);
                   ///查询所有下级机构
                   VcLevel rootLevel = vcLevelDao.getVcLevel("0", userInfo.getComCode());
                   if (rootLevel != null ) {
                       List<VcLevel> lowerOrgList=vcLevelDao.queryOrgZTreeListByRootOrgId(rootLevel.getId(),3);
                       if(lowerOrgList!=null && lowerOrgList.size()>0){
                           orgList.addAll(lowerOrgList);
                       }
                   }               
                   if(orgList!=null && orgList.size()>0){
                      List<String> orgCodeList =new ArrayList<String>();
                      for (VcLevel obj : orgList){
                          orgCodeList.add(obj.getUnitCode());
                      }
                      sb.append(" and exists(select 1 from VcDocRelArea area where area.idVcDocVersionInfo=doc.idVcDocVersionInfo " );
                      sb.append(" and area.orgCode in (?) ) ");
                      values.add(orgCodeList);
                   }
                }
            }

            return super.findByHqlNoLimit(sb.toString(), pageNo, pageSize, values.toArray());
        } catch (Exception e) {
            throw new DaoException("查询数据库失败！");
        }
    }


    /**
     * 根据给定的条件查询（未根据当前用户所属机构过滤）
     * 
     * @param map
     *            条件
     * @return List
     * @author whj
     */
    public List<VcDocVersionInfo> getDocVersionInfoList(Map<String, Object> map) throws DaoException {
        try {
            StringBuffer sb = new StringBuffer("from VcDocVersionInfo v where 1=1");
            List<Object> values = new ArrayList<Object>();
            StringBuffer sbDocType = new StringBuffer();
            StringBuffer sbNotContainDocType = new StringBuffer();
            StringBuffer sbArea = new StringBuffer();
            List<Object> valuesDocType = new ArrayList<Object>();
            List<Object> valuesNotContainDocType = new ArrayList<Object>();
            List<Object> valuesArea = new ArrayList<Object>();         

            for (String key : map.keySet()) {
                if (map.get(key) != null) {
                    // 单证种类代码、单证种类类型（0-单证，1-发票）
                    if ("docTypeCode".equals(key) || "docType".equals(key)) {
                        sbDocType.append(" and type." + key + "  =  ?  ");
                        valuesDocType.add(map.get(key));
                    } else {
                        if ("notConstainTypeCodeArray".equals(key)) {
                            List<String> notConstainTypeCodeList = (List<String>) map
                                    .get("notConstainTypeCodeArray");
                            if (notConstainTypeCodeList.size() > 0) {
                                sbNotContainDocType.append(" and type2.docTypeCode in ( ");
                                sbNotContainDocType.append("? ");
                                valuesNotContainDocType.add(notConstainTypeCodeList.get(0));
                                for (int i = 1; i < notConstainTypeCodeList.size(); i++) {
                                    sbNotContainDocType.append(", ? ");
                                    valuesNotContainDocType.add(notConstainTypeCodeList.get(0));
                                }
                                sbNotContainDocType.append(") ");
                            }
                        } else {
                            if ("docVerCode".equals(key)) {
                                sb.append(" and v.docVerCode  like  ?  ");
                                values.add("%" + map.get(key) + "%");
                            } else {
                                if("isCurOrUpperOrgDoc".equals(key)){  ///只查询当前机构及上级机构的单证                                   
                                    if( StringUtils.isNotBlank((String)map.get("isCurOrUpperOrgDoc"))){
                                        ///查询所有上级机构
                                        List<VcLevel> orgList = vcLevelDao.queryUpOrgListByUnitCode((String)map.get("isCurOrUpperOrgDoc"),7);
                                        if(orgList!=null && orgList.size()>0){
                                           List<String> orgCodeList =new ArrayList<String>();
                                           for (VcLevel obj : orgList){
                                               orgCodeList.add(obj.getUnitCode());
                                           }
                                           sb.append(" and exists(select 1 from VcDocRelArea area where area.idVcDocVersionInfo=v.idVcDocVersionInfo " );
                                           sb.append(" and area.orgCode in (?) ) ");
                                           values.add(orgCodeList);
                                        }
                                    }                                   
                                }else{
                                  sb.append(" and v." + key + "  =  ?  ");
                                  values.add(map.get(key));
                                }
                            }
                        }
                    }
                }
            }
            if (sbDocType.length() > 0) {
                sb.append(" and exists(select 1 from VcDocType type where type.idVcDocType= v.idVcDocType ");
                sb.append(sbDocType).append(" ) ");
                values.addAll(valuesDocType);

            }
            // 不包含的单证种类
            if (sbNotContainDocType.length() > 0) {
                sb
                        .append(" and not exists(select 1 from VcDocType type2 where type2.idVcDocType= v.idVcDocType ");
                sb.append(sbNotContainDocType).append(" ) ");
                values.addAll(valuesNotContainDocType);

            }
            return this.findByHql(sb.toString(), values.toArray());
        } catch (Exception e) {
            throw new DaoException("查询数据库失败！");
        }
    }

    @Override
    public List<VcDocVersionInfo> getBizDocVersionInfoList(String comCode, String docVerCode)
            throws DaoException {
        List<VcDocVersionInfo> vcDocVersionInfoList = null;
        try {
            StringBuffer sql = new StringBuffer("");
            sql.append("select doc_ver_code,doc_ver_name from vc_doc_version_info ver where ver.status='1'");
            sql
                    .append(" and exists (select 1 from vc_doc_type type where type.id_vc_doc_type=ver.id_vc_doc_type");
            sql.append("                   and type.doc_type='0')");
            sql
                    .append(" and exists (select 1 from vc_doc_rel_area area where area.id_vc_doc_version_info=ver.id_vc_doc_version_info");
            sql.append("    and exists (");
            sql.append("           select 1 from vc_level lv where area.org_code=lv.unit_code");
            sql.append("           start with lv.unit_code='" + comCode+ "'  connect by prior lv.parent_org_id=lv.id_vc_level");
            sql.append("    )");
            sql.append(")");
            if (StringUtils.isNotEmpty(docVerCode)) {
                sql.append(" and ver.doc_ver_code like  '%" + docVerCode + "%'");
            }
            List<Object[]> list = this.findBySql(sql.toString(), null);
            if (list != null && list.size() > 0) {
                vcDocVersionInfoList = new ArrayList<VcDocVersionInfo>();
                for (Object[] obj : list) {
                    VcDocVersionInfo vcDocVersionInfo = new VcDocVersionInfo();
                    vcDocVersionInfo.setDocVerCode((String) obj[0]);
                    vcDocVersionInfo.setDocVerName((String) obj[1]);
                    vcDocVersionInfoList.add(vcDocVersionInfo);
                }
            }
        } catch (Exception e) {
            throw new DaoException("查询数据库失败！");
        }
        return vcDocVersionInfoList;
    }
    
    
    /**
     * 根据当前机构、单证类型代码查询【进行征订的业务类单证】
     * @param comCode
     * @param docVerCode
     * @return
     * @throws DaoException
     * @author whj
     * @since 2014-02-25
     */
    public List<VcDocVersionInfo> getOrderDocVersionInfoList(String comCode, String docVerCode) throws DaoException{
        List<VcDocVersionInfo> vcDocVersionInfoList = null;
        try {
            StringBuffer sql = new StringBuffer("");
            List<Object> values=new ArrayList<Object>();
            sql.append("select doc_ver_code,doc_ver_name from vc_doc_version_info ver where ver.status=? ");
            values.add("1");
            sql.append(" and ver.is_Order=? ");
            values.add("1");
            //区域
            sql.append(" and exists (select 1 from vc_doc_rel_area area where area.id_vc_doc_version_info=ver.id_vc_doc_version_info ");
            sql.append("  and exists (");
            sql.append("  select 1 from vc_level lv where area.org_code=lv.unit_code");
            sql.append("  start with lv.unit_code= ?  connect by prior lv.parent_org_code=lv.unit_code ");
            values.add(comCode);
            sql.append("  )");
            sql.append(") ");
            if (StringUtils.isNotEmpty(docVerCode)) {
                sql.append(" and ver.doc_ver_code like ? ");
                values.add("%" + docVerCode + "%");
            }
            List<Object[]> list = this.findBySql(sql.toString(), values.toArray());
            if (list != null && list.size() > 0) {
                vcDocVersionInfoList = new ArrayList<VcDocVersionInfo>();
                for (Object[] obj : list) {
                    VcDocVersionInfo vcDocVersionInfo = new VcDocVersionInfo();
                    vcDocVersionInfo.setDocVerCode((String) obj[0]);
                    vcDocVersionInfo.setDocVerName((String) obj[1]);
                    vcDocVersionInfoList.add(vcDocVersionInfo);
                }
            }
        } catch (Exception e) {
            throw new DaoException("查询数据库失败！");
        }
        return vcDocVersionInfoList;
    }

    /**
     * 根据条件删除单证印刷流水号规则
     * 
     * @param vcDocPrtNoRule
     * @throws Exception
     * @author wanghuajian since 2013-4-15下午04:16:50
     */
    public int deletePrtNoRuleByCondition(VcDocPrtNoRule vcDocPrtNoRule) throws DaoException {
        try {
            if (vcDocPrtNoRule != null && vcDocPrtNoRule.getIdVcDocVersionInfo() != null) {
                StringBuffer sb = new StringBuffer("delete  from VcDocPrtNoRule v where 1=1 ");
                List<Object> values = new ArrayList<Object>();
                // 拼接订单号查询条件
                if (vcDocPrtNoRule.getIdVcDocVersionInfo() != null) {
                    sb.append(" and v.idVcDocVersionInfo= ?");
                    values.add(vcDocPrtNoRule.getIdVcDocVersionInfo());
                }
                return this.executeUpdate(sb.toString(), values);
            }
            return 0;
        } catch (Exception e) {
            throw new DaoException("查询数据库失败！");
        }
    }
    
    /**
     * 根据单证类型代码【级别（非必须）】删除单证管控信息VC_DOC_VERSION_INFO_MNG
     * 
     * @param queryDto
     * @throws Exception
     * @author wanghuajian since 2014-4-8下午04:16:50
     */
    public int deleteDocMng(VcDocVersionInfoMng queryDto) throws DaoException {
        try {
            if (queryDto != null && StringUtils.isNotBlank((queryDto.getDocVerCode()))) {
                StringBuffer sb = new StringBuffer("delete  from VcDocVersionInfoMng v ");
                List<Object> values = new ArrayList<Object>();
                // 拼接订单号查询条件
                sb.append(" where v.docVerCode= ?");
                values.add(queryDto.getDocVerCode());
                if (queryDto.getLevelNo() > 0) {
                    sb.append(" and v.levelNo= ?");
                    values.add(queryDto.getLevelNo());
                }
                return this.executeUpdate(sb.toString(), values);
            }
            return 0;
        } catch (Exception e) {
            throw new DaoException("数据删除异常：" + e.getMessage());
        }
    }

    /**
     * 根据条件查询单证管控信息VC_DOC_VERSION_INFO_MNG
     * 
     * @param queryDto
     * @throws Exception
     * @author wanghuajian since 2014-4-8下午04:16:50
     */
    public List<VcDocVersionInfoMng> queryDocMngList(VcDocVersionInfoMng queryDto) throws DaoException {
        try {
            if (queryDto != null) {
                StringBuffer sb = new StringBuffer("from VcDocVersionInfoMng v  where  1=1 ");
                List<Object> values = new ArrayList<Object>();
                // 拼接订单号查询条件
                if (StringUtils.isNotBlank(queryDto.getDocVerCode())) {
                    sb.append(" and  v.docVerCode= ? ");
                    values.add(queryDto.getDocVerCode());
                }
                if (queryDto.getLevelNo() > 0) {
                    sb.append(" and v.levelNo= ? ");
                    values.add(queryDto.getLevelNo());
                }                
                sb.append(" order by v.docVerCode asc, v.levelNo asc ");
                return this.findByHql(sb.toString(), values.toArray());
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new DaoException("数据查询异常：" + e.getMessage());
        }
    }

    /**
     * 保存单证管控信息VC_DOC_VERSION_INFO_MNG
     * 
     * @param queryDto
     * @throws Exception
     * @author wanghuajian since 2014-4-8下午04:16:50
     */
    public int saveDocMngList(List<VcDocVersionInfoMng> docMngList) throws DaoException {
        try {
            this.saveAll(docMngList);
            return 0;
        } catch (Exception e) {
            throw new DaoException("数据库操作异常：" + e.getMessage());
        }
    }
    
    public List findByHql(String hql, Object... params) {
            return super.findByHql(hql, params);
    }

    /**
     * 根据单证类型代码查找单证类型名称
     * 
     * @param versionCode
     * @return
     * @throws DaoException
     * @author chy
     * @date 2013-04-24
     */
    public String getVersionName(String versionCode) throws DaoException {
        String versionName = "";
        try {
            String sql = "select DOC_VER_NAME from VC_DOC_VERSION_INFO where DOC_VER_CODE = ? ";
            List list = this.findBySql(sql, new Object[]{versionCode});
            if (list != null && list.size() > 0) {
                versionName = (String) list.get(0);
            }
        } catch (Exception e) {
            throw new DaoException("查询单证类型名称时发生错误！", e);
        }
        return versionName;
    }

    /**
     * 根据单证类型查找生成规则
     * 
     * @param versionCode
     * @return
     * @throws DaoException
     * @author chy
     * @date 2013-04-24
     */
    public List<Object[]> getDocNumRule(String versionCode) throws DaoException {
        List<Object[]> list = null;
        try {
            String hql = " from VcDocPrtNoRule rule,VcDocVersionInfo ver"
                    + " where rule.idVcDocVersionInfo=ver.idVcDocVersionInfo" + " and ver.docVerCode='"
                    + versionCode + "'" + " order by rule.sortNum ASC";
            list = this.findByHql(hql, null);
        } catch (Exception e) {
            throw new DaoException("查询数据库出错！", e);
        }
        return list;
    }

    @Override
    public List findBySQL(String sql, Object[] params) throws DaoException {
        try {
            return super.findBySql(sql, params);
        } catch (Exception e) {
            throw new DaoException("查询数据库失败！");
        }
    }

    /**
     * 根据单证类型代码获取单证种类代码
     * @param docVerCode
     * @return
     * @throws DaoException
     */
    public String getDocTypeCode(String docVerCode) throws DaoException {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT t.doc_type_code FROM vc_doc_version_info v ,vc_doc_type t ");
            sql.append(" WHERE v.id_vc_doc_type = t.id_vc_doc_type ");
            sql.append(" AND v.doc_ver_code = ? ");
            Object[] params = { docVerCode };
            List result = this.findBySQL(sql.toString(), params);
            if (result.size() > 0) {
                return (String) result.get(0);
            }
            return null;
        } catch (Exception e) {
            throw new DaoException("查询数据库失败！");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tapi.tcs.vc.baseinfo.dao.VcDocVersionInfoDao#getValidVcDocVersionInfo (java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<VcDocVersionInfo> getValidVcDocVersionInfo(String docVerCode) throws DaoException {
        try {

            QueryRule queryRule = null;
            try {
                queryRule = QueryRule.getInstance();
            } catch (Exception e) {
                throw new DaoException("QueryRule拼接异常!", e);
            }

            if (StringUtils.isNotEmpty(docVerCode)) {
                queryRule.addEqual("docVerCode", docVerCode);
            }

            queryRule.addLike("status", "1");

            return this.find(VcDocVersionInfo.class, queryRule);
        } catch (Exception e) {
            throw new DaoException("查询数据库失败！");
        }
    }

    @Override
    public Page queryDocVerForSelector(DocVerSimpleVo docVerSimpleVo, int pageNo, int pageSize)
            throws DaoException {
        try {
            StringBuffer hql = new StringBuffer();
            List params = new ArrayList();
            hql.append(" select v.docVerCode,v.docVerName from VcDocVersionInfo v, VcDocType d ");
            hql.append(" where v.idVcDocType = d.idVcDocType ");
            if (Beans.isNotEmpty(docVerSimpleVo.getDocTypeCode())) {
                hql.append(" and d.docTypeCode = ? ");
                params.add(docVerSimpleVo.getDocTypeCode());
            }

            if (Beans.isNotEmpty(docVerSimpleVo.getDocVerCode())) {
                hql.append(" and v.docVerCode like ? ");
                params.add("%" + docVerSimpleVo.getDocVerCode() + "%");
            }

            if (Beans.isNotEmpty(docVerSimpleVo.getDocVerName())) {
                hql.append(" and v.docVerName like ? ");
                params.add("%" + docVerSimpleVo.getDocVerName() + "%");
            }

            Page page = this.findByHql(hql.toString(), pageNo, pageSize, params.toArray());

            List<DocVerSimpleVo> docVers = new ArrayList<DocVerSimpleVo>();
            List<Object[]> result = (List<Object[]>) page.getResult();
            for (Object[] object : result) {
                DocVerSimpleVo docVerSimpleVo_ = new DocVerSimpleVo();
                docVerSimpleVo_.setDocVerCode((String) object[0]);
                docVerSimpleVo_.setDocVerName((String) object[1]);
                docVers.add(docVerSimpleVo_);
            }
            page.setData(docVers);
            return page;
        } catch (Exception e) {
            throw new DaoException("查询数据库失败！");
        }
    }

    /**
     * 根据给定的条件查询（根据当前用户所属机构过滤）
     * 
     * @param map
     *            条件
     * @return List
     * @author 使用人单证发放 ljin
     */
    public List<VcDocVersionInfo> getJSONUserSend(Map<String, Object> map) throws DaoException {
        try {
            StringBuffer sb = new StringBuffer("from VcDocVersionInfo v where 1=1");
            List<Object> values = new ArrayList<Object>();
            StringBuffer sbDocType = new StringBuffer();
            StringBuffer sbNotContainDocType = new StringBuffer();
            StringBuffer sbArea = new StringBuffer();
            List<Object> valuesDocType = new ArrayList<Object>();
            List<Object> valuesNotContainDocType = new ArrayList<Object>();
            List<Object> valuesArea = new ArrayList<Object>();
            // 地区关联
            StringBuffer sbOrgCodeLocUp = new StringBuffer();
            List<Object> valuesOrgCodeLocUp = new ArrayList<Object>();

            for (String key : map.keySet()) {
                if (map.get(key) != null) {
                    // 单证种类代码、单证种类类型（0-单证，1-发票）
                    if ("docTypeCode".equals(key) || "docType".equals(key)) {
                        sbDocType.append(" and type." + key + "  =  ?  ");
                        valuesDocType.add(map.get(key));
                    } else {
                        if ("notConstainTypeCodeArray".equals(key)) {
                            List<String> notConstainTypeCodeList = (List<String>) map
                                    .get("notConstainTypeCodeArray");
                            if (notConstainTypeCodeList.size() > 0) {
                                sbNotContainDocType.append(" and type2.docTypeCode in ( ");
                                sbNotContainDocType.append("? ");
                                valuesNotContainDocType.add(notConstainTypeCodeList.get(0));
                                for (int i = 1; i < notConstainTypeCodeList.size(); i++) {
                                    sbNotContainDocType.append(", ? ");
                                    valuesNotContainDocType.add(notConstainTypeCodeList.get(0));
                                }
                                sbNotContainDocType.append(") ");
                            }
                        } else {
                            // 使用地区
                            if ("orgCodeLocalAndUp".equals(key)) {

                                List<String> orgCodeList = null;

                                String orgSql = "SELECT Z.UNIT_CODE  FROM VC_LEVEL Z  WHERE Z.UNIT_TYPE = ?  START WITH Z.UNIT_CODE=? " +
                                		" CONNECT BY  Z.ID_VC_LEVEL =PRIOR  Z.PARENT_ORG_ID";
                                orgCodeList = this.findBySql(orgSql, new Object[]{"0",map.get(key)});
                                if (orgCodeList.size() > 0) {
                                    sbOrgCodeLocUp.append(" and  rel.orgCode in( ");
                                    sbOrgCodeLocUp.append("? ");
                                    valuesOrgCodeLocUp.add(orgCodeList.get(0));
                                    for (int j = 1; j < orgCodeList.size(); j++) {
                                        sbOrgCodeLocUp.append(", ? ");
                                        valuesOrgCodeLocUp.add(orgCodeList.get(j));
                                    }
                                    sbOrgCodeLocUp.append(") ");
                                }

                            } else {
                                if ("docVerCode".equals(key)) {
                                    sb.append(" and v.docVerCode  like  ?  ");
                                    values.add("%" + map.get(key) + "%");
                                } else {
                                    sb.append(" and v." + key + "  =  ?  ");
                                    values.add(map.get(key));
                                }
                            }
                        }
                    }

                }
            }
            if (sbDocType.length() > 0) {
                sb.append(" and exists(select 1 from VcDocType type where type.idVcDocType= v.idVcDocType ");
                sb.append(sbDocType).append(" ) ");
                values.addAll(valuesDocType);

            }
            // 不包含的单证种类
            if (sbNotContainDocType.length() > 0) {
                sb
                        .append(" and not exists(select 1 from VcDocType type2 where type2.idVcDocType= v.idVcDocType ");
                sb.append(sbNotContainDocType).append(" ) ");
                values.addAll(valuesNotContainDocType);

            }

            // 地区关联
            if (sbOrgCodeLocUp.length() > 0) {
                sb
                        .append("and exists(select 1 from VcDocRelArea rel  where rel.idVcDocVersionInfo=v.idVcDocVersionInfo ");
                sb.append(sbOrgCodeLocUp).append(" ) ");
                values.addAll(valuesOrgCodeLocUp);
            }

            return this.findByHql(sb.toString(), values.toArray());
        } catch (Exception e) {
            throw new DaoException("查询数据库失败！");
        }
    }

	

}
