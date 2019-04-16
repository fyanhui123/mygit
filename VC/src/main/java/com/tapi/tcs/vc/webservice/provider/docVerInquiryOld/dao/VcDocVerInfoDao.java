package com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcDocPrintSet;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.vo.DocVerInquiryRequestVO;

public interface VcDocVerInfoDao extends GenericDao<VcDocVersionInfo>{

	/**
	 * 单证类型查询
	 * @param requestVO
	 * @return
	 * @throws DaoException
	 */
	public List<Object[]> docVerInquiry(DocVerInquiryRequestVO requestVO) throws DaoException;
	
	/**
	 * 根据单证类型、险种、地区查找打印配置表
	 * @param idVcDocVersioninfo
	 * @param insuKindCode
	 * @param orgCode
	 * @return
	 * @throws DaoException
	 */
	public VcDocPrintSet queryVcDocPrintSet(Long idVcDocVersioninfo, String insuKindCode, String orgCode) throws DaoException;
}
