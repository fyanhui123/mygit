package com.tapi.tcs.vc.invoice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.invoice.dao.InvoiceExportDao;
import com.tapi.tcs.vc.invoice.service.InvoiceExportService;
import com.tapi.tcs.vc.invoice.vo.InvoiceExportVO;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoicePrintDet;
import com.tapi.tcs.vc.schema.model.VcInvoicePrintExt;
import com.tapi.tcs.vc.schema.model.VcInvoiceRevoke;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcManageCodeHlj;
import com.tapi.tcs.vc.schema.model.VcPubCode;
import com.tapi.tcs.vc.schema.model.VcTaxPayerLogin;

/**
 * 发票数据导出Service实现类
 * <p>
 * Date 2013-06-06
 * </p>
 * <p>
 * Module：发票数据导出
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * @author chy
 * @version 1.0
 */
public class InvoiceExportServiceImpl implements InvoiceExportService {

	/**发票数据导出Dao*/
	private InvoiceExportDao invoiceExportDao;

	@Override
	public Page queryInvoiceExport(InvoiceExportVO invoiceExportVO, String comCode, int pageNo, int pageSize) throws BusinessException {
		Page page = null;
		try{
			//如果险类ID或者险种代码不为空，则查询出对应的单证类型代码
			/*if((invoiceExportVO.getInsuTypeId()!=null&&invoiceExportVO.getInsuTypeId()>0)
					|| StringUtils.isNotEmpty(invoiceExportVO.getInsuKindCode())){
				List<String> versionCodeList = invoiceExportDao.queryDocVersionCode(invoiceExportVO);
				invoiceExportVO.setDocVerCodeList(versionCodeList);
			}*/
			page = invoiceExportDao.queryInvoiceExport(invoiceExportVO, comCode, pageNo, pageSize);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage());
		}
		return page;
	}
	
	@Override
	public List<VcInvoicePrint> queryInvoiceExport(InvoiceExportVO invoiceExportVO, String comCode) throws BusinessException{
		List<VcInvoicePrint> list = null;
		try{
			//如果险类ID或者险种代码不为空，则查询出对应的单证类型代码
			/*if((invoiceExportVO.getInsuTypeId()!=null&&invoiceExportVO.getInsuTypeId()>0)
					|| StringUtils.isNotEmpty(invoiceExportVO.getInsuKindCode())){
				List<String> versionCodeList = invoiceExportDao.queryDocVersionCode(invoiceExportVO);
				invoiceExportVO.setDocVerCodeList(versionCodeList);
			}*/
			list = invoiceExportDao.queryInvoiceExport(invoiceExportVO, comCode);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage());
		}
		return list;
	}
	
	@Override
	public List<VcLevel> getAllChildCompany(String comCode) throws BusinessException {
		List<VcLevel> list = null;
		try{
			list = invoiceExportDao.getAllChildCompany(comCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage());
		}
		return list;
	}
	
	@Override
	public List<VcInvoicePrint> getVcInvoicePrintListByIds(Long[] idList) throws BusinessException {
		List<VcInvoicePrint> list = null;
		try{
			list = invoiceExportDao.getVcInvoicePrintListByIds(idList);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage());
		}
		return list;
	}
	
	@Override
	public void updateIsUploadPlat(Long[] ids, String userCode) throws BusinessException {
		try{
			/*StringBuffer sb = new StringBuffer();
			for(Long id : ids){
				sb.append(","+id);
			}
			invoiceExportDao.updateIsUploadPlat(sb.toString().substring(1), userCode);*/
			for(Long id : ids){
				invoiceExportDao.updateIsUploadPlat(id, userCode);
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage());
		}
	}
	
	@Override
	public void updateUploadPlat(List<VcInvoicePrint> vcInvoicePrintTmpList, 
			List<VcInvoiceRevoke> uploadPlatList, String userCode) throws BusinessException{
		//更新发票打印信息表
		if(vcInvoicePrintTmpList!=null && vcInvoicePrintTmpList.size()!=0){
			for(VcInvoicePrint vcInvoicePrint : vcInvoicePrintTmpList){
				invoiceExportDao.updateIsUploadPlat(vcInvoicePrint.getId(), userCode);
			}
		}
		//更新发票缴销信息表
		if(uploadPlatList!=null && uploadPlatList.size()!=0){
			updateVcInvoiceRevokeList(uploadPlatList,userCode);
		}
	}
	
	@Override
	public void updateVcInvoicePrint(List<VcInvoicePrint> vcInvoicePrintList) throws BusinessException{
		try{
			invoiceExportDao.updateVcInvoicePrint(vcInvoicePrintList);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage());
		}
	}
	
	@Override
	public VcInvoicePrint copyVcInvoicePrint(VcInvoicePrint srcObject, String oprCode, Date oprDate) {
		VcInvoicePrint vcInvoicePrintNew = new VcInvoicePrint();
		//对象拷贝
		Beans.copy(vcInvoicePrintNew, srcObject);
		//扩展信息列表
		List<VcInvoicePrintExt> srcExtList = srcObject.getVcInvoicePrintExtList();
		List<VcInvoicePrintExt> newExtList = null;
		if(srcExtList!=null && srcExtList.size()>0){
			newExtList = new ArrayList<VcInvoicePrintExt>();
			for(VcInvoicePrintExt ext : srcExtList){
				VcInvoicePrintExt extNew = new VcInvoicePrintExt();
				Beans.copy(extNew, ext);
				extNew.setId(null);
				extNew.setVcInvoicePrint(vcInvoicePrintNew);
				extNew.setCreatedBy(oprCode);
				extNew.setUpdatedBy(oprCode);
				extNew.setDateCreated(oprDate);
				extNew.setDateUpdated(oprDate);
				newExtList.add(extNew);
			}
		}
		//明细信息列表
		List<VcInvoicePrintDet> srcDetList = srcObject.getVcInvoicePrintDetList();
		List<VcInvoicePrintDet> newDetList = null;
		if(srcDetList!=null && srcDetList.size()>0){
			newDetList = new ArrayList<VcInvoicePrintDet>();
			for(VcInvoicePrintDet det : srcDetList){
				VcInvoicePrintDet detNew = new VcInvoicePrintDet();
				Beans.copy(detNew, det);
				detNew.setId(null);
				detNew.setVcInvoicePrint(vcInvoicePrintNew);
				detNew.setCreatedBy(oprCode);
				detNew.setUpdatedBy(oprCode);
				detNew.setDateCreated(oprDate);
				detNew.setDateUpdated(oprDate);
				newDetList.add(detNew);
			}
		}
		vcInvoicePrintNew.setId(null);
		vcInvoicePrintNew.setVcInvoicePrintExtList(newExtList);
		vcInvoicePrintNew.setVcInvoicePrintDetList(newDetList);
		vcInvoicePrintNew.setCreatedBy(oprCode);
		vcInvoicePrintNew.setUpdatedBy(oprCode);
		vcInvoicePrintNew.setDateCreated(oprDate);
		vcInvoicePrintNew.setDateUpdated(oprDate);
		return vcInvoicePrintNew;
	}
	
	@Override
	public VcTaxPayerLogin findVcTaxPayerLogin(String orgCode) throws BusinessException{
		VcTaxPayerLogin vcTaxPayerLogin = null;
		try{
			vcTaxPayerLogin = invoiceExportDao.findVcTaxPayerLogin(orgCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return vcTaxPayerLogin;
	}

	public void setInvoiceExportDao(InvoiceExportDao invoiceExportDao) {
		this.invoiceExportDao = invoiceExportDao;
	}
	
	/**
     * 
     * @param queryVo 根据条件查询发票开具信息表（不包含机构条件）
     * @return
     * @throws DaoException
     * @author wanghuajian
     */
    public List<VcInvoicePrint> queryVcInvoicePrintList(VcInvoicePrint queryVo) throws BusinessException {
      try{
        return invoiceExportDao.queryVcInvoicePrintList(queryVo);
      }catch(DaoException de){
          throw new BusinessException(de.getMessage(),de);
      }
    }
    
    @Override
    public List<VcInvoiceRevoke> findVcInvoiceRevoke(String orgCode,Date startDate, Date endDate, String isUploadPlat) throws BusinessException {
    	List<VcInvoiceRevoke> list = null;
    	try{
    		list = invoiceExportDao.findVcInvoiceRevoke(orgCode, startDate, endDate, isUploadPlat);
    	}catch(DaoException de){
            throw new BusinessException(de.getMessage(),de);
        }
    	return list;
    }
    
    @Override
    public void updateVcInvoiceRevokeList(List<VcInvoiceRevoke> vcInvoiceRevokeList,String userCode) throws BusinessException {
    	try{
    		if(vcInvoiceRevokeList!=null && vcInvoiceRevokeList.size()!=0){
    			Date sysdate = new Date();
    			for(VcInvoiceRevoke vcInvoiceRevoke:vcInvoiceRevokeList){
    				vcInvoiceRevoke.setIsUploadPlat("1");
    				vcInvoiceRevoke.setUpdatedBy(userCode);
    				vcInvoiceRevoke.setDateUpdated(sysdate);
    				invoiceExportDao.update(vcInvoiceRevoke);
    			}
    		}
    	}catch(Exception e){
    		throw new BusinessException("更新发票缴销信息出错！", e);
    	}
    }
    
    @Override
    public String findCodeCnameForGZ(String codeType, String codeCode) throws BusinessException{
    	String codeCname = "";
    	try{
    		codeCname = invoiceExportDao.findCodeCname(codeType,codeCode);
    	}catch(DaoException de){
            throw new BusinessException(de.getMessage(),de);
        }
    	return codeCname;
    }
    
    @Override
	public VcManageCodeHlj findManageCodeByOrgCode(String orgCode) throws BusinessException{
		try{
			return invoiceExportDao.findVcManageCodeHljByOrgCode(orgCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
    
}
