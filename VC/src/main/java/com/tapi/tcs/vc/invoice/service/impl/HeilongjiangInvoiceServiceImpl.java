package com.tapi.tcs.vc.invoice.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.invoice.dao.HeilongjiangInvoiceDao;
import com.tapi.tcs.vc.invoice.service.HeilongjiangInvoiceService;
import com.tapi.tcs.vc.invoice.vo.VcInvoiceReportDetHljVo;
import com.tapi.tcs.vc.schema.model.VcInvoiceBuyHlj;
import com.tapi.tcs.vc.schema.model.VcInvoiceReportDetHlj;
import com.tapi.tcs.vc.schema.model.VcInvoiceReportHlj;
import com.tapi.tcs.vc.schema.model.VcManageCodeHlj;
import com.tapi.tcs.vc.schema.model.VcStorage;

public class HeilongjiangInvoiceServiceImpl implements
		HeilongjiangInvoiceService {
	private HeilongjiangInvoiceDao heilongjiangInvoiceDao;

	@Override
	public void saveVcInvoiceBuyHljList(List<VcInvoiceBuyHlj> vcInvoiceBuyHljs) throws BusinessException {
		try{
			heilongjiangInvoiceDao.saveAll(vcInvoiceBuyHljs);
		}catch(Exception e){
			throw new BusinessException("保存领购表出错！", e);
		}
	}
	
	@Override
	public VcManageCodeHlj findManageCodeByOrgCode(String orgCode) throws BusinessException{
		try{
			return heilongjiangInvoiceDao.findVcManageCodeHljByOrgCode(orgCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@Override
	public VcInvoiceBuyHlj findVcInvoiceBuyHlj(VcInvoiceBuyHlj conditions) throws BusinessException {
		VcInvoiceBuyHlj vcInvoiceBuyHlj = null;
		try{
			vcInvoiceBuyHlj = heilongjiangInvoiceDao.findVcInvoiceBuyHlj(conditions);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return vcInvoiceBuyHlj;
	}
	
	@Override
	public void updateVcInvoiceBuyHlj(VcInvoiceBuyHlj vcInvoiceBuyHlj) throws BusinessException {
		try{
			heilongjiangInvoiceDao.update(vcInvoiceBuyHlj);
		}catch(Exception e){
			throw new BusinessException("更新领购表出错！", e);
		}
	}
	
	@Override
	public VcInvoiceReportHlj queryLastReportByOrgCode(String orgCode) throws BusinessException {
		try{
			return heilongjiangInvoiceDao.queryLastReportByOrgCode(orgCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<VcInvoiceReportDetHljVo> queryLastLeftReportList(String orgCode) throws BusinessException {
		List<VcInvoiceReportDetHljVo> resultList = null;
		try{
			//查询最近一次报表
			VcInvoiceReportHlj vcInvoiceReportHlj = this.queryLastReportByOrgCode(orgCode);
			String orgCodeTmp = vcInvoiceReportHlj.getOrgCode();
			if(vcInvoiceReportHlj!=null){
				//根据报表主键，及"本期结存"的明细类型，查询报表明细
				List<VcInvoiceReportDetHlj> detailList = heilongjiangInvoiceDao.
					queryVcInvoiceReportDetHlj(vcInvoiceReportHlj.getId(), SysConst.DETAIL_TYPE_NEXT_LEFT);
				
				if(detailList!=null && detailList.size()!=0){
					//合计份数
					int totalNum = 0;
					resultList = new ArrayList<VcInvoiceReportDetHljVo>();
					for(VcInvoiceReportDetHlj vcInvoiceReportDetHlj : detailList){
						VcInvoiceReportDetHljVo vcInvoiceReportDetHljVo = new VcInvoiceReportDetHljVo();
						Beans.copy(vcInvoiceReportDetHljVo, vcInvoiceReportDetHlj);
						vcInvoiceReportDetHljVo.setOrgCode(orgCodeTmp);	//机构信息
						vcInvoiceReportDetHljVo.setOrder(vcInvoiceReportDetHlj.getDetailOrder()+"");
						resultList.add(vcInvoiceReportDetHljVo);
						totalNum += vcInvoiceReportDetHlj.getTotalNum();
					}
					//组织合计记录
					VcInvoiceReportDetHljVo detailVo = new VcInvoiceReportDetHljVo();
					detailVo.setOrder(SysConst.CHAR_DETAIL_TOTAL);
					detailVo.setTotalNum(totalNum);
					resultList.add(detailVo);
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return resultList;
	}
	
	@Override
	public List<VcInvoiceReportDetHljVo> queryLastBuyReportList(String orgCode, Date startDate, Date endDate) throws BusinessException{
		List<VcInvoiceReportDetHljVo> resultList = null;
		try{
			//根据机构和时间范围查询出领购记录
			List<VcInvoiceBuyHlj> buyList = heilongjiangInvoiceDao.queryVcInvoiceBuyHljList(orgCode, startDate, endDate);
			if(buyList!=null && buyList.size()!=0){
				resultList = new ArrayList<VcInvoiceReportDetHljVo>();
				VcInvoiceReportDetHljVo vcInvoiceReportDetHljVo = null;
				VcInvoiceReportDetHljVo detailTotal = new VcInvoiceReportDetHljVo();
				int totalNum = 0;
				int i=1;
				//组织报表明细信息
				for(VcInvoiceBuyHlj buy : buyList){
					vcInvoiceReportDetHljVo = new VcInvoiceReportDetHljVo();
					vcInvoiceReportDetHljVo.setOrder("" + (i++));
					vcInvoiceReportDetHljVo.setInvoiceCode(buy.getInvoiceCode());
					vcInvoiceReportDetHljVo.setTotalNum(buy.getAmount().intValue());
					vcInvoiceReportDetHljVo.setStartNum(buy.getStartNum());
					vcInvoiceReportDetHljVo.setEndNum(buy.getEndNum());
					vcInvoiceReportDetHljVo.setBuyDate(buy.getBuyDate());
					vcInvoiceReportDetHljVo.setDocVerCode(buy.getDocVerCode());
					resultList.add(vcInvoiceReportDetHljVo);
					totalNum += buy.getAmount().intValue();
				}
				detailTotal.setOrder(SysConst.CHAR_DETAIL_TOTAL);
				detailTotal.setTotalNum(totalNum);
				resultList.add(detailTotal);
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return resultList;
	}
	
	@Override
	public List<VcInvoiceReportDetHljVo> queryNextUseReportList(String orgCode, Date startDate, Date endDate,
			List<VcInvoiceReportDetHljVo> lastLeftList, List<VcInvoiceReportDetHljVo> lastBuyList) throws BusinessException {
		List<VcInvoiceReportDetHljVo> tempDetail = new ArrayList<VcInvoiceReportDetHljVo>();
		List<VcInvoiceReportDetHljVo> resultlist = new ArrayList<VcInvoiceReportDetHljVo>();
		try{
			if(lastLeftList!=null && lastLeftList.size()!=0){
				for(VcInvoiceReportDetHljVo lastLeft : lastLeftList){
					if(!SysConst.CHAR_DETAIL_TOTAL.equals(lastLeft.getOrder())){
						tempDetail.add(lastLeft);
					}
				}
			}
			if(lastBuyList!=null && lastBuyList.size()!=0){
				for(VcInvoiceReportDetHljVo lastBuy : lastBuyList){
					if(!SysConst.CHAR_DETAIL_TOTAL.equals(lastBuy.getOrder())){
						tempDetail.add(lastBuy);
					}
				}
			}
			if(tempDetail.size()!=0){
				for(VcInvoiceReportDetHljVo detail : tempDetail){
					VcInvoiceReportDetHljVo conditions = new VcInvoiceReportDetHljVo();
					conditions.setOrgCode(orgCode);
					//2014-6-4 by zhangxiao begin '本期使用'起始时间从上期报表生成时间开始统计，精确到时分秒
					if(lastLeftList!=null && lastLeftList.size()!=0){
						conditions.setStartDate(lastLeftList.get(0).getDateCreated());
					}
					else{
						conditions.setStartDate(startDate);
					}
					//2014-6-4 by zhangxiao end '本期使用'起始时间从上期报表生成时间开始统计，精确到时分秒
					conditions.setEndDate(endDate);
					conditions.setDocVerCode(detail.getDocVerCode());
					conditions.setInvoiceCode(detail.getInvoiceCode());
					conditions.setStartNum(detail.getStartNum());
					conditions.setEndNum(detail.getEndNum());
					List<VcInvoiceReportDetHljVo> rs = heilongjiangInvoiceDao.queryNextUseDetail(conditions);
					if(rs!=null && rs.size()!=0){
						resultlist.addAll(this.mergeInvoice(rs));
					}
				}
				if(resultlist!=null && resultlist.size()!=0){
					// 排序 根据orgCode和startNum排序
					Collections.sort(resultlist,new Comparator<VcInvoiceReportDetHljVo>() {
						public int compare(VcInvoiceReportDetHljVo o1,VcInvoiceReportDetHljVo o2) {
							int result = 0;
							String orgCode1 = o1.getOrgCode() == null ? "": o1.getOrgCode();
							String orgCode2 = o2.getOrgCode() == null ? "": o2.getOrgCode();
							String startNum1 = o1.getStartNum() == null ? "0": o1.getStartNum();
							String startNum2 = o2.getStartNum() == null ? "0": o2.getStartNum();
							if (orgCode1.equals(orgCode2)) {
								result = Long.valueOf(startNum1).compareTo(Long.valueOf(startNum2));
							} else {
								result = orgCode1.compareTo(orgCode2);
							}
							return result;
						}
					});

					int i=1;
					int printNum = 0;
					int cancelNum = 0;
					BigDecimal totalAmount = BigDecimal.ZERO;
					VcInvoiceReportDetHljVo totalDetail = new VcInvoiceReportDetHljVo();
					for(VcInvoiceReportDetHljVo resultDetail : resultlist){
						resultDetail.setOrder(""+(i++));
						if(resultDetail.getPrintNum()!=null)
							printNum += resultDetail.getPrintNum();
						if(resultDetail.getCancelNum()!=null)
							cancelNum += resultDetail.getCancelNum();
						totalAmount = totalAmount.add(resultDetail.getAmount());
					}
					totalDetail.setOrder(SysConst.CHAR_DETAIL_TOTAL);
					totalDetail.setPrintNum(printNum);
					totalDetail.setCancelNum(cancelNum);
					totalDetail.setAmount(totalAmount);
					resultlist.add(totalDetail);
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("查询本期使用失败！", e);
		}
		return resultlist;
	}
	
	@Override
	public List<VcInvoiceReportDetHljVo> queryNextLeftReportList(String orgCode, List<VcInvoiceReportDetHljVo> lastLeftList
			, List<VcInvoiceReportDetHljVo> lastBuyList) throws BusinessException{
		List<VcInvoiceReportDetHljVo> list = new ArrayList<VcInvoiceReportDetHljVo>();
		List<VcInvoiceReportDetHljVo> tempDetail = new ArrayList<VcInvoiceReportDetHljVo>();
		try{
			if(lastLeftList!=null && lastLeftList.size()!=0){
				for(VcInvoiceReportDetHljVo lastLeft : lastLeftList){
					if(!SysConst.CHAR_DETAIL_TOTAL.equals(lastLeft.getOrder())){
						tempDetail.add(lastLeft);
					}
				}
			}
			if(lastBuyList!=null && lastBuyList.size()!=0){
				for(VcInvoiceReportDetHljVo lastBuy : lastBuyList){
					if(!SysConst.CHAR_DETAIL_TOTAL.equals(lastBuy.getOrder())){
						tempDetail.add(lastBuy);
					}
				}
			}
			if(tempDetail.size()!=0){
				int totalNum = 0;
				int i = 1;
				for(VcInvoiceReportDetHljVo detail : tempDetail){
					List<VcInvoiceReportDetHlj> returnList = heilongjiangInvoiceDao.queryStorage(orgCode, 
							detail.getDocVerCode(), detail.getInvoiceCode(), detail.getStartNum(), detail.getEndNum());
					if(returnList!=null && returnList.size()!=0){
						for(VcInvoiceReportDetHlj vcInvoiceReportDetHlj : returnList){
							VcInvoiceReportDetHljVo vcInvoiceReportDetHljVo = new VcInvoiceReportDetHljVo();
							vcInvoiceReportDetHljVo.setOrder((i++)+"");
							vcInvoiceReportDetHljVo.setDocVerCode(vcInvoiceReportDetHlj.getDocVerCode());
							vcInvoiceReportDetHljVo.setInvoiceCode(vcInvoiceReportDetHlj.getInvoiceCode());
							vcInvoiceReportDetHljVo.setStartNum(vcInvoiceReportDetHlj.getStartNum());
							vcInvoiceReportDetHljVo.setEndNum(vcInvoiceReportDetHlj.getEndNum());
							vcInvoiceReportDetHljVo.setTotalNum(vcInvoiceReportDetHlj.getTotalNum());
							vcInvoiceReportDetHljVo.setBuyDate(detail.getBuyDate());
							list.add(vcInvoiceReportDetHljVo);
							totalNum += vcInvoiceReportDetHlj.getTotalNum();
						}
					}
				}
				if(list!=null && list.size()!=0){
					VcInvoiceReportDetHljVo vcInvoiceReportDetHljVo = new VcInvoiceReportDetHljVo();
					vcInvoiceReportDetHljVo.setOrder(SysConst.CHAR_DETAIL_TOTAL);
					vcInvoiceReportDetHljVo.setTotalNum(totalNum);
					list.add(vcInvoiceReportDetHljVo);
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}
	
	@Override
	public void saveVcInvoiceReportHlj(VcInvoiceReportHlj vcInvoiceReportHlj) throws BusinessException {
		try{
			heilongjiangInvoiceDao.save(vcInvoiceReportHlj);
		}catch(Exception e){
			throw new BusinessException("保存领用存报表失败！", e);
		}
	}
	
	@Override
	public VcInvoiceReportHlj findVcInvoiceReportHlj(Long id) throws BusinessException {
		try{
			return heilongjiangInvoiceDao.get(id);
		}catch(Exception e){
			throw new BusinessException("查询领用存报表失败！", e);
		}
	}
	
	@Override
	public Page queryVcInvoiceReportHljPage(String orgCode, Date startDate, Date endDate, int pageNo, int pageSize) throws BusinessException {
		try{
			return heilongjiangInvoiceDao.queryVcInvoiceReportHljPage(orgCode, startDate, endDate, pageNo, pageSize);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@Override
	public Page queryVcInvoiceBuyPage(String orgCode, Date startDate, Date endDate, int pageNo, int pageSize) throws BusinessException {
		Page returnpage = null;
		try{
			returnpage = heilongjiangInvoiceDao.queryVcInvoiceBuyPage(orgCode, startDate, endDate, pageNo, pageSize);
			if(returnpage!=null){
				List<VcInvoiceBuyHlj> list = (List<VcInvoiceBuyHlj>)returnpage.getResult();
				if(list!=null && list.size()!=0){
					//合计数量
					long totalAmount = 0L;
					//合计剩余数量
					long totalLeft = 0L;
					int orderNo = 1;
					for(VcInvoiceBuyHlj buy : list){
						//剩余数量
						long leftAmount = 0L;
						//当前正在使用
						String nowUse = "";
						//查询领购信息对应的库存记录
						/*List<VcStorage> vcStorageList = heilongjiangInvoiceDao.queryVcStorageByInvoiceBuy(buy);
						if(vcStorageList!=null && vcStorageList.size()!=0){
							int i=0;
							for(VcStorage vcStorage : vcStorageList){
								leftAmount += vcStorage.getDocNum();
								if(i==0){
									nowUse = vcStorage.getStartNum();
								}
								i++;
							}
						}*/
						List<VcInvoiceReportDetHlj> returnList = heilongjiangInvoiceDao.queryStorage(orgCode, 
								buy.getDocVerCode(), buy.getInvoiceCode(), buy.getStartNum(), buy.getEndNum());
						if(returnList!=null && returnList.size()!=0){
							int i=0;
							for(VcInvoiceReportDetHlj detail : returnList){
								leftAmount += detail.getTotalNum();
								if(i==0){
									nowUse = detail.getStartNum();
								}
								i++;
							}
						}
						//序号
						buy.setOrderNo(orderNo++);
						//剩余数量
						buy.setLeftAmount(leftAmount);
						if(leftAmount == 0L){
							//状态
							buy.setStoreStatus("无剩余");
							//当前正在使用
							buy.setNowUse("");
						}else{
							//状态
							buy.setStoreStatus("有剩余");
							//当前正在使用
							buy.setNowUse(nowUse);
						}
						
						totalAmount += buy.getAmount();
						totalLeft += leftAmount;
					}
					//组织合计记录
					VcInvoiceBuyHlj totalBuy = new VcInvoiceBuyHlj();
					totalBuy.setAmount(totalAmount);
					totalBuy.setLeftAmount(totalLeft);
					list.add(totalBuy);
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return returnpage;
	}
	
	/**
	 * 合并号段
	 * @param resultlist
	 * @return
	 * @throws Exception
	 */
	private List<VcInvoiceReportDetHljVo> mergeInvoice(
			List<VcInvoiceReportDetHljVo> resultlist) throws Exception {
		List<VcInvoiceReportDetHljVo> returnlist = new ArrayList<VcInvoiceReportDetHljVo>();
		int size = resultlist.size();
		int i = 0;
		int j = 0;
		while (i < size) {
			VcInvoiceReportDetHljVo detailVo = resultlist.get(i);
			// 1、设置开具份数/作废份数
			if ("U".equals(detailVo.getVerifyReason())) {
				// 打印
				detailVo.setPrintNum(detailVo.getTotalNum());
				detailVo.setCancelNum(0);
			} else if ("C".equals(detailVo.getVerifyReason())) {
				// 作废
				detailVo.setCancelNum(detailVo.getTotalNum());
				detailVo.setPrintNum(0);
			}
			detailVo.setStartNum(detailVo.getInvoiceNo());
			detailVo.setEndNum(detailVo.getInvoiceNo());
			detailVo.setStartDate(detailVo.getVerifyDate());
			detailVo.setEndDate(detailVo.getVerifyDate());
			// 2、合并号段
			j = i + 1;
			while (j < size) {
				VcInvoiceReportDetHljVo nextVo = resultlist.get(j);
				// 号段连续，可以合并
				if (nextVo.getDocVerCode().equals(detailVo.getDocVerCode())
						&& nextVo.getInvoiceCode().equals(
								detailVo.getInvoiceCode())
						&& Integer.parseInt(nextVo.getInvoiceNo()) == (Integer
								.parseInt(detailVo.getEndNum()) + 1)
						&& detailVo.getOrgCode().equals(nextVo.getOrgCode())) { // 合并号段需要拆分机构
					// 号段止=下一个流水号
					detailVo.setEndNum(nextVo.getInvoiceNo());
					// 如果下一条记录的核销时间大于前一个，则日起止取下一个
					if (compareDate(nextVo.getVerifyDate(), detailVo
							.getEndDate()) > 0) {
						detailVo.setEndDate(nextVo.getVerifyDate());
					} else if (compareDate(nextVo.getVerifyDate(), detailVo
							.getStartDate()) < 0) {
						detailVo.setStartDate(nextVo.getVerifyDate());
					}
					// 设置打印/作废份数
					if ("U".equals(nextVo.getVerifyReason())) {
						// 打印
						detailVo.setPrintNum(detailVo.getPrintNum()
								+ nextVo.getTotalNum());
					} else if ("C".equals(nextVo.getVerifyReason())) {
						// 作废
						detailVo.setCancelNum(detailVo.getCancelNum()
								+ nextVo.getTotalNum());
					}
					// 设置金额
					if (nextVo.getAmount() != null
							&& nextVo.getAmount().compareTo(BigDecimal.ZERO) != 0) {
						detailVo.setAmount(detailVo.getAmount().add(
								nextVo.getAmount()));
					}
					j++;
					continue;
				} else {
					i = j;
					break;
				}
			}
			returnlist.add(detailVo);
			// 若最后一个也是连续，则退出外层循环
			if (j >= size) {
				break;
			}
		}
		return returnlist;
	}
	
	/**
	 * 比较两个日期大小
	 * @param fromdate
	 * @param todate
	 * @return
	 */
	private int compareDate(final Date fromdate,final Date todate){
		Calendar fromCal = Calendar.getInstance();
		Calendar toCal = Calendar.getInstance();
		fromCal.setTime(fromdate);
		toCal.setTime(todate);
		return fromCal.compareTo(toCal);
	}
	
	public void setHeilongjiangInvoiceDao(
			HeilongjiangInvoiceDao heilongjiangInvoiceDao) {
		this.heilongjiangInvoiceDao = heilongjiangInvoiceDao;
	}
}
