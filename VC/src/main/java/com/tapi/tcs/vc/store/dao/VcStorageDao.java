package com.tapi.tcs.vc.store.dao;

import java.util.Date;
import java.util.List;
import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.vo.StorageInfo;

public interface VcStorageDao extends GenericDao<VcStorage> {

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
	public List<VcStorage> findByConditions(String versionCode, String orgCode,
			String startNum, String endNum, String status) throws DaoException;

	/**
	 * 保存方法
	 * 
	 * @param vcStorage
	 * @throws Exception
	 * @author chy
	 * @date 2013-04-09
	 */
	public void saveVcStorage(VcStorage vcStorage) throws DaoException;

	/**
	 * 删除方法
	 * 
	 * @param List
	 *            <VcStorage>
	 * @throws Exception
	 * @author chy
	 * @date 2013-04-09
	 */
	public void deleteVcStorage(List<VcStorage> list) throws DaoException;

	/**
	 * 通过sql查询
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findBySQL(String sql,Object... params) throws DaoException;
	/**
	 * 锁表操作
	 * 
	 * @param id
	 *            主键
	 */
	public void lockVcStorage(Object... id) throws DaoException;

	/**
	 * 判断库存是否存在起止流水为start-end的数据
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
     *       是否包含下级机构的库存
	 * @param docStatus
	 *            单证状态
	 * @return 如果库存存在则返回VcStorage的List集合，否则返回null
	 */
	public List<VcStorage> isExistAll(String start, String end, String docVerCode,
			String pressBatchNo, String orgCode, boolean isContainLowerOrg, String[] docStatus) throws DaoException;

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
	public String splitStorage(String start, String end, String docVerCode,
			String pressBatchNo, String orgCode, String docStatus,
			String changeToStatus, VcStorage vcStorage,String userCode) throws DaoException;

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
	 * @throws Exception 
	 */
	public void mergeStorage(String start, String end, String docVerCode,
			String pressBatchNo, String orgCode, String docStatus,
			String docStatusTo, String userCode,Date dealline) throws DaoException;

	/**
	 * 判断数据库是否包含流水号start-end区间的库存 如 start = 2 ，end = 5
	 * 如果库存有startNum小于等于2并且endNum大于等于5的数据则返回1
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
	public int isExist(String start, String end, String docVerCode,
			String pressBatchNo, String docStatus, String orgCode) throws DaoException;

	/**
	 * 按条件删除VC_STORAGE表中记录
	 * @param startNum 起始流水号 
	 * @param endNum 终止流水号
	 * @param docVerCode 单证类型
	 * @param pressBatchNo 批次
	 * @param docStatus 单证状态
	 * @param orgCode 机构
	 * @throws DaoException 异常
	 */
	public void deleteVcStorage(String startNum, String endNum,
			String docVerCode, String pressBatchNo, String docStatus,
			String orgCode) throws DaoException;
	
	/**
	 * 按条件完全比较
	 * @param startNum 起始流水号
	 * @param endNum 终止流水号
	 * @param docVerCode 单证类型
	 * @param pressBatchNo 印刷版本号
	 * @param docStatus 流水状态
	 * @param orgCode 机构
	 * @return  库存记录
	 * @throws DaoException 异常
	 */
	public VcStorage fullEqual(String startNum, String endNum,
			String docVerCode, String pressBatchNo, String docStatus,
			String orgCode) throws DaoException;
	
	/**
	 * 按维度进行号段合并
	 * @param docVerCode 单证类型
	 * @param pressBatchNo 印刷版本号
	 * @param docStatus 流水状态
	 * @param orgCode 机构
	 * @param oprCode 操作人
	 * @throws DaoException 异常
	 */
	public void mergeStorage(String docVerCode,
			String pressBatchNo, String docStatus, String orgCode, String oprCode) throws DaoException;
	
	/**
	 * 
	 * @param startNum
	 * @param endNum
	 * @param docVerCode
	 * @param pressBatchNo
	 * @param docStatus
	 * @param orgCode
	 * @return
	 * @throws DaoException
	 */
	public VcStorage scopeEqual(String startNum, String endNum,
			String docVerCode, String pressBatchNo, String docStatus,
			String orgCode) throws DaoException;
	
	public List<StorageInfo> findStorage(String userCode, String docVerCode,String orgCode)  throws DaoException;
	
	
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
            String docStatus, String docStatusTo, String userCode) throws DaoException; 
}
