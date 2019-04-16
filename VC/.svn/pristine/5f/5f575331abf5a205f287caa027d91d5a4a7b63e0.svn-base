package com.tapi.tcs.vc.invoice.web;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormats;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Number;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.invoice.service.HeilongjiangInvoiceService;
import com.tapi.tcs.vc.invoice.vo.VcInvoiceReportDetHljVo;
import com.tapi.tcs.vc.schema.model.VcInvoiceBuyHlj;
import com.tapi.tcs.vc.schema.model.VcInvoiceReportDetHlj;
import com.tapi.tcs.vc.schema.model.VcInvoiceReportHlj;
import com.tapi.tcs.vc.schema.model.VcManageCodeHlj;

/**
 * 黑龙江地税Action
 * <p>
 * Date 2013-10-30
 * </p>
 * <p>
 * Module：发票
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
public class InvoiceReportAction extends TFAction{

	private static final long serialVersionUID = 3415096955809270979L;
	/**日志管理*/
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/**黑龙江地税服务类*/
	private HeilongjiangInvoiceService heilongjiangInvoiceService;
	/**日期起*/
	private Date startDate;
	/**日期止*/
	private Date endDate;
	/**前期结存*/
	private List<VcInvoiceReportDetHljVo> lastLeftList;
	/**上期领购*/
	private List<VcInvoiceReportDetHljVo> lastBuyList;
	/**本期使用*/
	private List<VcInvoiceReportDetHljVo> nextUseList;
	/**本期结存*/
	private List<VcInvoiceReportDetHljVo> nextLeftList;
	/**领用存报表ID*/
	private Long reportId;
	/**json字符串*/
	private String jsonData;
	/**操作类型*/
	private String actionType;
	/**提示信息*/
	private String message;
	/**领用存报表列表*/
	private List<VcInvoiceReportHlj> invoiceReportList;
	/**领购信息列表*/
	private List<VcInvoiceBuyHlj> vcInvoiceBuyHljList;
	
	/**
	 * 进入领用存报表生成界面
	 * @return
	 * @throws BusinessException
	 */
	public String initInvoiceReportGenerate () throws BusinessException {
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			String orgCode = userInfo.getComCode();
			//查询当前机构最后一次报表记录
			VcInvoiceReportHlj vcInvoiceReportHlj = heilongjiangInvoiceService.queryLastReportByOrgCode(orgCode);
			if(vcInvoiceReportHlj!=null){
				//日期起 = 上次报表的结束日期+1
				Calendar cal = Calendar.getInstance();
				cal.setTime(vcInvoiceReportHlj.getEndDate());
				cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
				startDate = cal.getTime();
			}else{
				Calendar cal = Calendar.getInstance();
				cal.set(2013, 10, 9);//黑龙江上线时间：2013-11-09
				startDate = cal.getTime();
			}
			Date nowDate = new Date();
			endDate = nowDate;
			actionType = "before";
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return "fail";
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			this.setMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return "fail";
		}
		return SUCCESS;
	}
	
	/**
	 * 领用存报表生成前查询
	 * @return
	 * @throws BusinessException
	 */
	public String queryReportBeforeGererate() throws BusinessException {
		try{
			//校验日期
			if(!validInputDate(startDate, endDate, true)){
				throw new BusinessException("日期止不能小于日期起，且要小于当前日期");
			}
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//校验机构
			String orgCode = userInfo.getComCode();
			VcManageCodeHlj vcManageCodeHlj = heilongjiangInvoiceService.findManageCodeByOrgCode(orgCode);
			if(vcManageCodeHlj == null){
				throw new BusinessException("当前机构没有权限做领用存报表生成前查询");
			}
			//前期结存
			lastLeftList = heilongjiangInvoiceService.queryLastLeftReportList(orgCode);
			//上期领购
			lastBuyList = heilongjiangInvoiceService.queryLastBuyReportList(orgCode, startDate, endDate);
			//本期使用
			nextUseList = heilongjiangInvoiceService.queryNextUseReportList(orgCode, startDate, endDate, lastLeftList, lastBuyList);
			//本期结存
			nextLeftList = heilongjiangInvoiceService.queryNextLeftReportList(orgCode, lastLeftList, lastBuyList);
			if((lastLeftList==null || lastLeftList.size()==0)
					&& (lastBuyList==null || lastBuyList.size()==0)
					&& (nextUseList==null || nextUseList.size()==0)
					&& (nextLeftList==null || nextLeftList.size()==0)){
				throw new BusinessException("当前日期区间没有找到您需要的信息");
			}
			actionType = "query";
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return "fail";
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			this.setMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return "fail";
		}
		return SUCCESS;
	}
	
	/**
	 * 生成领用存报表
	 * @return
	 * @throws BusinessException
	 */
	public String generateReport() throws BusinessException {
		try{
			if(reportId!=null && reportId!=0){
				throw new BusinessException("领用存报表已生成，请勿重复提交");
			}
			//校验日期
			if(!validInputDate(startDate, endDate, true)){
				throw new BusinessException("日期止不能小于日期起，且要小于当前日期");
			}
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			lastLeftList = (List<VcInvoiceReportDetHljVo>)getRequest().getSession().getAttribute("lastLeftList");
			lastBuyList = (List<VcInvoiceReportDetHljVo>)getRequest().getSession().getAttribute("lastBuyList");
			nextUseList = (List<VcInvoiceReportDetHljVo>)getRequest().getSession().getAttribute("nextUseList");
			nextLeftList = (List<VcInvoiceReportDetHljVo>)getRequest().getSession().getAttribute("nextLeftList");
			getRequest().getSession().removeAttribute("lastLeftList");
			getRequest().getSession().removeAttribute("lastBuyList");
			getRequest().getSession().removeAttribute("nextUseList");
			getRequest().getSession().removeAttribute("nextLeftList");
			if(nextUseList==null || nextUseList.size()==0){
				throw new BusinessException("该日期区间没有查询到本期使用数据，不能生成领用存报表");
			}
			//校验机构
			String orgCode = userInfo.getComCode();
			String userCode = userInfo.getUserCode();
			VcManageCodeHlj vcManageCodeHlj = heilongjiangInvoiceService.findManageCodeByOrgCode(orgCode);
			if(vcManageCodeHlj == null){
				throw new BusinessException("当前机构没有权限做领用存报表生成前查询");
			}
			//组织主表数据
			VcInvoiceReportHlj report = new VcInvoiceReportHlj();
			report.setManageCode(vcManageCodeHlj.getManageCode());
			report.setStartDate(startDate);
			report.setEndDate(endDate);
			report.setReportDate(new Date());
			report.setOrgCode(orgCode);
			report.setOperatorCode(userCode);
			report.setCreatedBy(userCode);
			report.setDateCreated(new Date());
			report.setUpdatedBy(userCode);
			report.setDateUpdated(new Date());
			//组织明细表数据
			this.mergeReportDetail(report, lastLeftList, lastBuyList, nextUseList, nextLeftList);
			heilongjiangInvoiceService.saveVcInvoiceReportHlj(report);
			reportId = report.getId();
			actionType = "generate";
			message = "领用存报表已成功生成";
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return "fail";
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			this.setMessageInfo(TFStrUtils.getJSONErrorMessage("生成领用存报表失败"));
			return "fail";
		}
		return SUCCESS;
	}
	
	/**
	 * 领用存报表导出
	 * @return
	 * @throws BusinessException
	 */
	public String exportReport() throws BusinessException {
		try{
			if(reportId==null){
				throw new BusinessException("请先进行报表生成，再进行导出");
			}
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//校验机构
			String orgCode = userInfo.getComCode();
			VcManageCodeHlj vcManageCodeHlj = heilongjiangInvoiceService.findManageCodeByOrgCode(orgCode);
			if(vcManageCodeHlj == null){
				throw new BusinessException("当前机构没有权限做领用存报表生成前查询");
			}
			
			//根据id查找领用存报表
			VcInvoiceReportHlj vcInvoiceReportHlj = heilongjiangInvoiceService.findVcInvoiceReportHlj(reportId);
			if(vcInvoiceReportHlj==null){
				throw new BusinessException("没有查询到领用存报表");
			}
			List<VcInvoiceReportDetHlj> detailList = vcInvoiceReportHlj.getVcInvoiceReportDetHljList();
			List<VcInvoiceReportDetHlj> lastLeftTemp = new ArrayList<VcInvoiceReportDetHlj>();
			List<VcInvoiceReportDetHlj> lastBuyTemp = new ArrayList<VcInvoiceReportDetHlj>();
			List<VcInvoiceReportDetHlj> nextUseTemp = new ArrayList<VcInvoiceReportDetHlj>();
			List<VcInvoiceReportDetHlj> nextLeftTemp = new ArrayList<VcInvoiceReportDetHlj>();
			if(detailList!=null && detailList.size()!=0){
				for(VcInvoiceReportDetHlj detail : detailList){
					if(SysConst.DETAIL_TYPE_LAST_LEFT.equals(detail.getDetailType())){
						lastLeftTemp.add(detail);
					}else if(SysConst.DETAIL_TYPE_LAST_BUY.equals(detail.getDetailType())){
						lastBuyTemp.add(detail);
					}else if(SysConst.DETAIL_TYPE_NEXT_USE.equals(detail.getDetailType())){
						nextUseTemp.add(detail);
					}else if(SysConst.DETAIL_TYPE_NEXT_LEFT.equals(detail.getDetailType())){
						nextLeftTemp.add(detail);
					}
				}
			}
			
			int row = 0;
			int temp = 0;
			String dirPath = this.getDirPath(orgCode);
			String fileName = "发票领用存报表打印.xls";
			File file = new File(dirPath + java.io.File.separator + fileName);
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet("发票领用存报表打印", 0);
			this.setCellView(sheet, temp, row);
			// 字体
			WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 20, WritableFont.BOLD, false,
					UnderlineStyle.DOUBLE, Colour.BLACK);
			
			// 格式
			WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
			titleFormat.setAlignment(Alignment.CENTRE);
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleFormat.setBorder(Border.ALL, BorderLineStyle.NONE);
			titleFormat.setLocked(true);

			// header
			Label titleLabel = new Label(0, row, "发票领用存报表", titleFormat);
			sheet.addCell(titleLabel);
			sheet.mergeCells(0, row, 10, row);
			row++;
			
			// common
			sheet.addCell(this.getWritableCell("管理编码：", 0, row));
			sheet.addCell(this.getWritableCell(vcManageCodeHlj.getManageCode(), 1, row));
			sheet.mergeCells(1, row, 4, row);
			sheet.addCell(this.getWritableCell("纳税人名称：", 5, row));
			sheet.addCell(this.getWritableCell(vcManageCodeHlj.getTaxpayerName(), 6, row));
			sheet.mergeCells(6, row, 10, row);
			row++;
			row++;
			row++;
			
			// 前期结存
			temp = row;
			sheet.addCell(this.getWritableCell("前期结存", 0, row));
			this.addTableTitleCell(sheet, row, SysConst.DETAIL_TYPE_LAST_LEFT);
			row++;
			if (null != lastLeftTemp && lastLeftTemp.size() > 0) {
				row = this
						.addTableDataCell(lastLeftTemp, sheet, row, SysConst.DETAIL_TYPE_LAST_LEFT);
			}
			sheet.mergeCells(0, temp, 0, row - 1);
			sheet.setRowView(row, 300);
			row++;
			row++;
			
			// 上期领购
			temp = row;
			sheet.addCell(this.getWritableCell("上期领购", 0, row));
			this.addTableTitleCell(sheet, row, SysConst.DETAIL_TYPE_LAST_BUY);
			row++;
			if (null != lastBuyTemp && lastBuyTemp.size() > 0) {
				row = this.addTableDataCell(lastBuyTemp, sheet, row, SysConst.DETAIL_TYPE_LAST_BUY);
			}
			sheet.mergeCells(0, temp, 0, row - 1);
			sheet.setRowView(row, 300);
			row++;
			row++;
			
			// 本期使用
			temp = row;
			sheet.addCell(this.getWritableCell("本期使用", 0, row));
			this.addTableTitleCell(sheet, row, SysConst.DETAIL_TYPE_NEXT_USE);
			row++;
			//根据机构排序分组
			Collections.sort(nextUseTemp,
					new Comparator<VcInvoiceReportDetHlj>() {
						public int compare(VcInvoiceReportDetHlj o1,
								VcInvoiceReportDetHlj o2) {
							int result = 0;
							String orgCode1 = o1.getCompanyCode() == null ? "": o1.getCompanyCode();
							String orgCode2 = o2.getCompanyCode() == null ? "": o2.getCompanyCode();
							System.out.println("orgCode1:" + orgCode1);
							System.out.println("orgCode2:" + orgCode2);
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

			if (null != nextUseTemp && nextUseTemp.size() > 0) {
				row = this.addTableDataCell(nextUseTemp, sheet, row, SysConst.DETAIL_TYPE_NEXT_USE);
			}
			sheet.mergeCells(0, temp, 0, row - 1);
			sheet.setRowView(row, 300);
			row++;
			row++;

			// 本期结存
			temp = row;
			sheet.addCell(this.getWritableCell("本期结存", 0, row));
			this.addTableTitleCell(sheet, row, SysConst.DETAIL_TYPE_NEXT_LEFT);
			row++;
			if (null != nextLeftTemp && nextLeftTemp.size() > 0) {
				row = this
						.addTableDataCell(nextLeftTemp, sheet, row, SysConst.DETAIL_TYPE_NEXT_LEFT);
			}
			sheet.mergeCells(0, temp, 0, row - 1);
			
			workbook.write();
			workbook.close();
			
			actionType = "export";
			//返回值：文件存放目录==文件名
			jsonData = dirPath +"=="+ fileName;
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return "fail";
		}catch(IOException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("生成文件失败！"));
			return "fail";
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return "fail";
		}
		return SUCCESS;
	}
	
	
	/**
	 * 进入领用存报表查询界面
	 * @return
	 * @throws BusinessException
	 */
	public String initInvoiceReportInquiry() throws BusinessException{
		return SUCCESS;
	}
	/**
	 * 分页查询领用存报表
	 * @return
	 * @throws BusinessException
	 */
	public String queryReportPageList() throws BusinessException{
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			String orgCode = userInfo.getComCode();
			//校验日期
			if(!validInputDate(startDate, endDate, false)){
				throw new BusinessException("日期起和日期止都不能为空，且日期止不能小于日期起");
			}
			Page returnPage = heilongjiangInvoiceService.queryVcInvoiceReportHljPage(orgCode, startDate, endDate, page, rows);
			//返回页面的结果集
			invoiceReportList = (List<VcInvoiceReportHlj>)returnPage.getResult();
			//总页数
			total = (int)returnPage.getTotalPageCount();
			//总记录数
			records = (int)returnPage.getTotalCount();
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 查看领用存报表明细
	 * @return
	 * @throws BusinessException
	 */
	public String queryReportDetail() throws BusinessException {
		try{
			if(reportId==null){
				throw new BusinessException("请选择一条领用存报表数据进行明细查看");
			}
			//根据id查找领用存报表
			VcInvoiceReportHlj vcInvoiceReportHlj = heilongjiangInvoiceService.findVcInvoiceReportHlj(reportId);
			if(vcInvoiceReportHlj==null){
				throw new BusinessException("没有查询到领用存报表信息");
			}
			//给日期起和日期止赋值
			startDate = vcInvoiceReportHlj.getStartDate();
			endDate = vcInvoiceReportHlj.getEndDate();
			
			List<VcInvoiceReportDetHlj> detailList = vcInvoiceReportHlj.getVcInvoiceReportDetHljList();
			List<VcInvoiceReportDetHljVo> lastLeftTemp = new ArrayList<VcInvoiceReportDetHljVo>();
			List<VcInvoiceReportDetHljVo> lastBuyTemp = new ArrayList<VcInvoiceReportDetHljVo>();
			List<VcInvoiceReportDetHljVo> nextUseTemp = new ArrayList<VcInvoiceReportDetHljVo>();
			List<VcInvoiceReportDetHljVo> nextLeftTemp = new ArrayList<VcInvoiceReportDetHljVo>();
			if(detailList!=null && detailList.size()!=0){
				int lastLeftTotal = 0;
				int lastBuyTotal = 0;
				int nextLeftTotal = 0;
				int printNum = 0;
				int cancelNum = 0;
				BigDecimal totalAmount = BigDecimal.ZERO;
				for(VcInvoiceReportDetHlj detail : detailList){
					VcInvoiceReportDetHljVo detailVo = new VcInvoiceReportDetHljVo();
					if(SysConst.DETAIL_TYPE_LAST_LEFT.equals(detail.getDetailType())){
						Beans.copy(detailVo, detail);
						detailVo.setOrder(detail.getDetailOrder()+"");
						lastLeftTemp.add(detailVo);
						lastLeftTotal += detailVo.getTotalNum();
					}else if(SysConst.DETAIL_TYPE_LAST_BUY.equals(detail.getDetailType())){
						Beans.copy(detailVo, detail);
						detailVo.setOrder(detail.getDetailOrder()+"");
						lastBuyTemp.add(detailVo);
						lastBuyTotal += detailVo.getTotalNum();
					}else if(SysConst.DETAIL_TYPE_NEXT_USE.equals(detail.getDetailType())){
						Beans.copy(detailVo, detail);
						detailVo.setOrgCode(detail.getCompanyCode());
						detailVo.setOrgName(detail.getPubCompany() == null ? "" : detail.getPubCompany().getCompanyCname() );
						detailVo.setOrder(detail.getDetailOrder()+"");
						nextUseTemp.add(detailVo);
						if(detailVo.getPrintNum()!=null)
							printNum += detailVo.getPrintNum();
						if(detailVo.getCancelNum()!=null)
							cancelNum += detailVo.getCancelNum();
						totalAmount = totalAmount.add(detailVo.getAmount());
					}else if(SysConst.DETAIL_TYPE_NEXT_LEFT.equals(detail.getDetailType())){
						Beans.copy(detailVo, detail);
						detailVo.setOrder(detail.getDetailOrder()+"");
						nextLeftTemp.add(detailVo);
						nextLeftTotal += detailVo.getTotalNum();
					}
				}
				//前期结存
				if(lastLeftTemp!=null && lastLeftTemp.size()!=0){
					//组织合计记录
					VcInvoiceReportDetHljVo detail = new VcInvoiceReportDetHljVo();
					detail.setOrder(SysConst.CHAR_DETAIL_TOTAL);
					detail.setTotalNum(lastLeftTotal);
					lastLeftTemp.add(detail);
					lastLeftList = lastLeftTemp;
				}
				//上期领购
				if(lastBuyTemp!=null && lastBuyTemp.size()!=0){
					//组织合计记录
					VcInvoiceReportDetHljVo detail = new VcInvoiceReportDetHljVo();
					detail.setOrder(SysConst.CHAR_DETAIL_TOTAL);
					detail.setTotalNum(lastBuyTotal);
					lastBuyTemp.add(detail);
					lastBuyList = lastBuyTemp;
				}
				//本期使用
				if(nextUseTemp!=null && nextUseTemp.size()!=0){
					//组织合计记录
					VcInvoiceReportDetHljVo detail = new VcInvoiceReportDetHljVo();
					detail.setOrder(SysConst.CHAR_DETAIL_TOTAL);
					detail.setPrintNum(printNum);
					detail.setCancelNum(cancelNum);
					detail.setAmount(totalAmount);
					nextUseTemp.add(detail);
					nextUseList = nextUseTemp;
				}
				//按照机构排序分组
				Collections.sort(nextUseList, new Comparator<VcInvoiceReportDetHljVo>() {
					public int compare(VcInvoiceReportDetHljVo o1,
							VcInvoiceReportDetHljVo o2) {
						int result = 0;
						String orgCode1 = o1.getOrgCode() == null ? "" : o1.getOrgCode();
						String orgCode2 = o2.getOrgCode() == null ? "" : o2.getOrgCode();
						String startNum1 = o1.getStartNum() == null ? "0" : o1.getStartNum();
						String startNum2 = o2.getStartNum() == null ? "0" : o2.getStartNum();
						if (orgCode1.equals(orgCode2)){
							result = Long.valueOf(startNum1).compareTo(Long.valueOf(startNum2));
						}else {
							result = orgCode1.compareTo(orgCode2);
						}
						return result;
					}
				});
				//本期结存
				if(nextLeftTemp!=null && nextLeftTemp.size()!=0){
					//组织合计记录
					VcInvoiceReportDetHljVo detail = new VcInvoiceReportDetHljVo();
					detail.setOrder(SysConst.CHAR_DETAIL_TOTAL);
					detail.setTotalNum(nextLeftTotal);
					nextLeftTemp.add(detail);
					nextLeftList = nextLeftTemp;
				}
			}
			actionType = "reportDetail";
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return "fail";
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			this.setMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系"));
			return "fail";
		}
		return SUCCESS;
	}
	
	/**
	 * 进入领购查询界面
	 * @return
	 * @throws BusinessException
	 */
	public String initInvoiceBuyQuery() throws BusinessException {
		
		return SUCCESS;
	}
	
	
	/**
	 * 分页查询领购信息
	 * @return
	 * @throws BusinessException
	 */
	public String queryInvoiceBuyPageList() throws BusinessException{
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			//校验日期
			if(!validInputDate(startDate, endDate, false)){
				throw new BusinessException("日期起和日期止都不能为空，且日期止不能小于日期起");
			}
			Page returnPage = heilongjiangInvoiceService.queryVcInvoiceBuyPage(comCode, startDate, endDate, page, rows-1);
			//返回页面的结果集
			vcInvoiceBuyHljList = (List<VcInvoiceBuyHlj>)returnPage.getResult();
			//总页数
			total = (int)returnPage.getTotalPageCount();
			//总记录数
			records = (int)returnPage.getTotalCount();
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 组织领用存报表明细
	 * @param report
	 * @param lastLeftList
	 * @param lastBuyList
	 * @param nextUseList
	 * @param nextLeftList
	 */
	private void mergeReportDetail(VcInvoiceReportHlj report, List<VcInvoiceReportDetHljVo> lastLeftList,
			List<VcInvoiceReportDetHljVo> lastBuyList, List<VcInvoiceReportDetHljVo> nextUseList,
			List<VcInvoiceReportDetHljVo> nextLeftList) {
		List<VcInvoiceReportDetHlj> detaillist = new ArrayList<VcInvoiceReportDetHlj>();
		
		if(lastLeftList!=null && lastLeftList.size()!=0){
			for(VcInvoiceReportDetHljVo detailVo : lastLeftList){
				if(!SysConst.CHAR_DETAIL_TOTAL.equals(detailVo.getOrder())){
					VcInvoiceReportDetHlj detail = new VcInvoiceReportDetHlj();
					Beans.copy(detail, detailVo);
					detail.setVcInvoiceReportHlj(report);
					detail.setDetailOrder(Integer.parseInt(detailVo.getOrder()));
					detail.setDetailType(SysConst.DETAIL_TYPE_LAST_LEFT);
					detail.setCreatedBy(report.getCreatedBy());
					detail.setUpdatedBy(report.getCreatedBy());
					detail.setDateCreated(report.getDateCreated());
					detail.setDateUpdated(report.getDateCreated());
					detaillist.add(detail);
				}
			}
		}
		
		if(lastBuyList!=null && lastBuyList.size()!=0){
			for(VcInvoiceReportDetHljVo detailVo : lastBuyList){
				if(!SysConst.CHAR_DETAIL_TOTAL.equals(detailVo.getOrder())){
					VcInvoiceReportDetHlj detail = new VcInvoiceReportDetHlj();
					Beans.copy(detail, detailVo);
					detail.setVcInvoiceReportHlj(report);
					detail.setDetailOrder(Integer.parseInt(detailVo.getOrder()));
					detail.setDetailType(SysConst.DETAIL_TYPE_LAST_BUY);
					detail.setCreatedBy(report.getCreatedBy());
					detail.setUpdatedBy(report.getCreatedBy());
					detail.setDateCreated(report.getDateCreated());
					detail.setDateUpdated(report.getDateCreated());
					detaillist.add(detail);
				}
			}
		}
		
		if(nextUseList!=null && nextUseList.size()!=0){
			for(VcInvoiceReportDetHljVo detailVo : nextUseList){
				if(!SysConst.CHAR_DETAIL_TOTAL.equals(detailVo.getOrder())){
					VcInvoiceReportDetHlj detail = new VcInvoiceReportDetHlj();
					Beans.copy(detail, detailVo);
					detail.setCompanyCode(detailVo.getOrgCode());//机构代码
					detail.setVcInvoiceReportHlj(report);
					detail.setDetailOrder(Integer.parseInt(detailVo.getOrder()));
					detail.setDetailType(SysConst.DETAIL_TYPE_NEXT_USE);
					detail.setCreatedBy(report.getCreatedBy());
					detail.setUpdatedBy(report.getCreatedBy());
					detail.setDateCreated(report.getDateCreated());
					detail.setDateUpdated(report.getDateCreated());
					detaillist.add(detail);
				}
			}
		}
		
		if(nextLeftList!=null && nextLeftList.size()!=0){
			for(VcInvoiceReportDetHljVo detailVo : nextLeftList){
				if(!SysConst.CHAR_DETAIL_TOTAL.equals(detailVo.getOrder())){
					VcInvoiceReportDetHlj detail = new VcInvoiceReportDetHlj();
					Beans.copy(detail, detailVo);
					detail.setVcInvoiceReportHlj(report);
					detail.setDetailOrder(Integer.parseInt(detailVo.getOrder()));
					detail.setDetailType(SysConst.DETAIL_TYPE_NEXT_LEFT);
					detail.setCreatedBy(report.getCreatedBy());
					detail.setUpdatedBy(report.getCreatedBy());
					detail.setDateCreated(report.getDateCreated());
					detail.setDateUpdated(report.getDateCreated());
					detaillist.add(detail);
				}
			}
		}
		
		report.setVcInvoiceReportDetHljList(detaillist);
	}
	
	/**
	 * 日期校验规则：起始日期和终止日期都不能为空；起始日期不大于终止日期；（终止日期要小于当前日期）
	 * @param fromDate
	 * @param toDate 
	 * @param beforeToday 是否校验 终止日期小于当前日期
	 * @return
	 */
	private boolean validInputDate(Date fromDate, Date toDate, boolean beforeToday){
		boolean isValidDate = true;
		if(fromDate==null || toDate==null){
			isValidDate = false;
		}else{
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			Calendar today = Calendar.getInstance();
			start.setTime(fromDate);
			end.setTime(toDate);
			today.setTime(new Date());
			if(start.compareTo(end)>0){
				isValidDate = false;
			}
			if(beforeToday){
				if(end.compareTo(today)>=0){
					isValidDate = false;
				}
			}
		}
		return isValidDate;
	}
	
	/**
	 * 根据不同机构返回文件存放目录
	 * @param orgCode
	 * @return
	 * @throws Exception
	 */
	private String getDirPath(String orgCode) throws Exception {
		//文件存放路径：不同地区存放不同文件夹
		String filePath = "";
		// 获取路径
		String basePath = this.getClass().getClassLoader().getResource("/").getPath();
		if (basePath.indexOf("WEB-INF/classes") > -1) {
			basePath = basePath.substring(0, basePath.indexOf("WEB-INF/classes"));
		}
		filePath = basePath + SysConst.INVOICE_EXPORT_FILE_PATH + orgCode;
		File dirPath = new File(filePath);
		//如果目录不存在，则创建
		if(!dirPath.isDirectory()){
			dirPath.mkdir();
		}
		//清空目录下前一天的临时文件
		deleteFile(dirPath);
		return filePath;
	}
	
	/**
	 * 删除目录下文件
	 * @param dirPath
	 */
	private void deleteFile(File dirPath) throws Exception{
		logger.info("删除当天之前的文件...");
		File[] fileList = dirPath.listFiles();
		if(fileList!=null && fileList.length>0){
			for(File file : fileList){
				Long time = file.lastModified();
				Calendar nowDate = Calendar.getInstance();
				Calendar calendar = Calendar.getInstance();
				nowDate.setTime(new Date());
				calendar.setTimeInMillis(time);
				//文件的修改日期小于当前日期，则删除（当天的文件不删除）
				if(calendar.get(Calendar.MONTH)<nowDate.get(Calendar.MONTH)
						|| calendar.get(Calendar.DATE)<nowDate.get(Calendar.DATE)){
					file.delete();
				}
			}
		}
	}
	
	/**
	 * 功能:设置单元格的宽度和高度
	 */
	private void setCellView(WritableSheet sheet, int temp, int row) throws Exception {
		sheet.setColumnView(temp++, 12);
		sheet.setColumnView(temp++, 6);
		sheet.setColumnView(temp++, 16);
		sheet.setColumnView(temp++, 8);
		sheet.setColumnView(temp++, 8);
		sheet.setColumnView(temp++, 14);
		sheet.setColumnView(temp++, 14);
		sheet.setColumnView(temp++, 12);
		sheet.setColumnView(temp++, 12);
		sheet.setColumnView(temp++, 14);
		sheet.setColumnView(temp++, 55);
		sheet.setRowView(row, 600);
		sheet.setRowView(row + 1, 300);
	}
	
	/**
	 * 功能:获取添加到sheet中的格式化cell
	 */
	private WritableCell getWritableCell(Object obj, int c, int r) throws Exception {
		WritableCell cell = null;
		WritableCellFormat format = null;
		WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
				UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		if (null != obj) {
			if (obj instanceof Integer) {
				format = new WritableCellFormat(cellFont, NumberFormats.INTEGER);
				format.setAlignment(Alignment.CENTRE);
				format.setBorder(Border.ALL, BorderLineStyle.THIN);
				cell = new Number(c, r, ((Integer) obj).doubleValue(), format);
			} else if (obj instanceof Double) {
				format = new WritableCellFormat(cellFont, NumberFormats.FLOAT);
				format.setAlignment(Alignment.CENTRE);
				format.setBorder(Border.ALL, BorderLineStyle.THIN);
				cell = new Number(c, r, ((Double) obj).doubleValue(), format);
			} else if (obj instanceof BigDecimal) {
				format = new WritableCellFormat(cellFont, NumberFormats.FORMAT3);
				format.setAlignment(Alignment.CENTRE);
				format.setBorder(Border.ALL, BorderLineStyle.THIN);
				cell = new Number(c, r, ((BigDecimal) obj).doubleValue(), format);
			} else if (obj instanceof Date) {
				format = new WritableCellFormat(cellFont, DateFormats.DEFAULT);
				format.setAlignment(Alignment.CENTRE);
				format.setBorder(Border.ALL, BorderLineStyle.THIN);
				cell = new DateTime(c, r, (Date) obj, format);
			} else {
				format = new WritableCellFormat(cellFont);
				format.setAlignment(Alignment.CENTRE);
				format.setVerticalAlignment(VerticalAlignment.CENTRE);
				format.setBorder(Border.ALL, BorderLineStyle.THIN);
				cell = new Label(c, r, (String) obj, format);
			}
		}

		return cell;
	}
	
	/**
	 * 功能:EXCEL导出表标题
	 */
	private void addTableTitleCell(WritableSheet sheet, int row, String type) throws Exception {
		int col = 1;
		if (SysConst.DETAIL_TYPE_NEXT_USE == type) {
			sheet.addCell(this.getWritableCell("序号", col++, row));
			sheet.addCell(this.getWritableCell("发票代码", col++, row));
			sheet.addCell(this.getWritableCell("开具份数", col++, row));
			sheet.addCell(this.getWritableCell("作废份数", col++, row));
			sheet.addCell(this.getWritableCell("发票起号", col++, row));
			sheet.addCell(this.getWritableCell("发票止号", col++, row));
			sheet.addCell(this.getWritableCell("日期起", col++, row));
			sheet.addCell(this.getWritableCell("日期止", col++, row));
			sheet.addCell(this.getWritableCell("开具金额", col++, row));
			sheet.addCell(this.getWritableCell("机构信息", col++, row));
		} else {
			sheet.addCell(this.getWritableCell("序号", col++, row));
			sheet.addCell(this.getWritableCell("发票代码", col++, row));
			sheet.addCell(this.getWritableCell("份数", col++, row));
			sheet.mergeCells(col - 1, row, col, row);
			col++;
			sheet.addCell(this.getWritableCell("发票起号", col++, row));
			sheet.addCell(this.getWritableCell("发票止号", col++, row));
			sheet.addCell(this.getWritableCell("领购日期", col++, row));
			sheet.mergeCells(col - 1, row, col, row);
			col++;
			sheet.addCell(this.getWritableCell("开具金额", col++, row));
			sheet.addCell(this.getWritableCell("机构信息", col++, row));
		}
	}
	
	/**
	 * 功能:EXCEL导出表数据
	 */
	private int addTableDataCell(List<VcInvoiceReportDetHlj> detailList, WritableSheet sheet, int row, String type)
			throws Exception {
		int col = 0;
		if (SysConst.DETAIL_TYPE_NEXT_USE.equals(type)) {
			for (VcInvoiceReportDetHlj detail : detailList) {
				col = 1;
				sheet.addCell(this.getWritableCell(detail.getDetailOrder(), col++, row));
				sheet.addCell(this.getWritableCell(detail.getInvoiceCode(), col++, row));
				sheet.addCell(this.getWritableCell(detail.getPrintNum(), col++, row));
				sheet.addCell(this.getWritableCell(detail.getCancelNum(), col++, row));
				sheet.addCell(this.getWritableCell(detail.getStartNum(), col++, row));
				sheet.addCell(this.getWritableCell(detail.getEndNum(), col++, row));
				sheet.addCell(this.getWritableCell(detail.getStartDate(), col++, row));
				sheet.addCell(this.getWritableCell(detail.getEndDate(), col++, row));
				sheet.addCell(this.getWritableCell(detail.getAmount(), col++, row));
				String orgName = detail.getPubCompany() == null ? "---" : detail.getPubCompany().getCompanyCname();
				//sheet.addCell(this.getWritableCell(detail.getPubCompany().getCompanyCname(), col++, row));//机构名称
				sheet.addCell(this.getWritableCell(orgName, col++, row));//机构名称
				row++;
			}
		} else {
			for (VcInvoiceReportDetHlj detail : detailList) {
				col = 1;
				sheet.addCell(this.getWritableCell(detail.getDetailOrder(), col++, row));
				sheet.addCell(this.getWritableCell(detail.getInvoiceCode(), col++, row));
				sheet.addCell(this.getWritableCell(detail.getTotalNum(), col++, row));
				sheet.mergeCells(col - 1, row, col, row);
				col++;
				sheet.addCell(this.getWritableCell(detail.getStartNum(), col++, row));
				sheet.addCell(this.getWritableCell(detail.getEndNum(), col++, row));
				sheet.addCell(this.getWritableCell(detail.getBuyDate(), col++, row));
				sheet.mergeCells(col - 1, row, col, row);
				col++;
				sheet.addCell(this.getWritableCell("---", col++, row));
				sheet.addCell(this.getWritableCell("---", col++, row));//机构名称
				row++;
			}
		}

		return row;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<VcInvoiceReportDetHljVo> getLastLeftList() {
		return lastLeftList;
	}

	public void setLastLeftList(List<VcInvoiceReportDetHljVo> lastLeftList) {
		this.lastLeftList = lastLeftList;
	}

	public List<VcInvoiceReportDetHljVo> getLastBuyList() {
		return lastBuyList;
	}

	public void setLastBuyList(List<VcInvoiceReportDetHljVo> lastBuyList) {
		this.lastBuyList = lastBuyList;
	}

	public List<VcInvoiceReportDetHljVo> getNextUseList() {
		return nextUseList;
	}

	public void setNextUseList(List<VcInvoiceReportDetHljVo> nextUseList) {
		this.nextUseList = nextUseList;
	}

	public List<VcInvoiceReportDetHljVo> getNextLeftList() {
		return nextLeftList;
	}

	public void setNextLeftList(List<VcInvoiceReportDetHljVo> nextLeftList) {
		this.nextLeftList = nextLeftList;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<VcInvoiceReportHlj> getInvoiceReportList() {
		return invoiceReportList;
	}

	public void setInvoiceReportList(List<VcInvoiceReportHlj> invoiceReportList) {
		this.invoiceReportList = invoiceReportList;
	}

	public List<VcInvoiceBuyHlj> getVcInvoiceBuyHljList() {
		return vcInvoiceBuyHljList;
	}

	public void setVcInvoiceBuyHljList(List<VcInvoiceBuyHlj> vcInvoiceBuyHljList) {
		this.vcInvoiceBuyHljList = vcInvoiceBuyHljList;
	}

	public void setHeilongjiangInvoiceService(
			HeilongjiangInvoiceService heilongjiangInvoiceService) {
		this.heilongjiangInvoiceService = heilongjiangInvoiceService;
	}

}
