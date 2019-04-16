package com.tapi.tcs.vc.baseinfo.dao;

import java.util.List;

import com.tapi.tcs.vc.schema.model.VcPrintery;
import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;

/**
 * 印刷厂维护DAO接口
 * <p>
 * Date: 2013-03-13
 * </p>
 * <p>
 * Module: 印刷厂维护
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
public interface VcPrinteryDao extends GenericDao<VcPrintery> {

	/**
	 * 根据ID获取去印刷厂对象
	 * 
	 * @author wanghuajian
	 * 
	 * @param idPrintery
	 * @return
	 */
	public VcPrintery getPrintery(Long idPrintery);

	/**
	 * 查询印刷厂信息
	 * 
	 * @author wanghuanjian
	 * 
	 * @param printery
	 *            印刷厂查询条件dto
	 * @param currentPage
	 *            当前页码
	 * @param pageNumber
	 *            页面条数
	 * @return
	 */
	public Page queryPrinterys(VcPrintery printery, int currentPage, int pageNumber) throws DaoException;
	
	
	/**
	 * 保存印刷厂信息及其单证承应信息
	 * 
	 * @param printery
	 * @return
	 * 
	 * @author wanghuajian
	 */
	public void savePrinteryAndPrintVersion(VcPrintery printery)throws DaoException;
	
	public List findByHql(String hql,Object... params);
	
	public String getPrinteryNameByPrinteryCode(String printeryCode)throws DaoException;
	/**
	 * 查询印刷厂列表
	 * @return
	 * @throws Exception
	 * @author chy
	 * @date 2013-05-20
	 */
	public List<VcPrintery> findVcPrinteryList()throws DaoException;

}
