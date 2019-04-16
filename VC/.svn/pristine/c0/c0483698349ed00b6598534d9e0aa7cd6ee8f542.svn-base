package com.tapi.tcs.vc.order.web;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.ExcelExportHandler;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.inquiry.vo.DocDetailInquiryVo;
import com.tapi.tcs.vc.order.service.OrderManagerService;
import com.tapi.tcs.vc.order.vo.VcPurchaseVo;

/**
 * 生成采购单Action
 * <p>
 * Date 2013-04-24
 * </p>
 * <p>
 * Module：征订管理
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
public class PurchaseOrderAction extends TFAction{

	private static final long serialVersionUID = 1;
	/**日志管理*/
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/**征订管理Service类注入*/
	private OrderManagerService orderManagerService;
	/**采购单VO*/
	private VcPurchaseVo vcPurchaseVo;
	/**采购单列表*/
	private List vcPurchaseList;
	/**采购单号*/
	private String purchaseCode;
	/**json数据*/
	private String jsonData;
	/**印刷厂ID*/
	private String printeryId;
	/**印刷厂名称*/
	private String printeryName;
	/**申印数量*/
	private Long applyPrintNum;
	
	/**
	 * 进入采购下单查询界面
	 * @return
	 */
	public String prePurchaseOrderQuery() {
		logger.info("访问/orderManager/prePurchaseOrderQuery.do进入采购下单查询界面...");
		return SUCCESS;
	}
	
	/**
	 * 采购下单查询
	 * @return
	 * @throws BusinessException
	 */
	public String queryPurchaseOrder() throws BusinessException {
		logger.info("访问/orderJson/queryPurchaseOrder.do采购下单查询...");
		try{
			if(StringUtils.isEmpty(printeryId)){
				throw new BusinessException("请选择印刷厂！");
			}
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的用户代码
			String userCode = userInfo.getUserCode();
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			//查询Page对象
			vcPurchaseVo = new VcPurchaseVo();
			vcPurchaseVo.setPurchaseCode(purchaseCode);
			vcPurchaseVo.setPrinteryId(printeryId);
			vcPurchaseVo.setPrinteryName(printeryName);
			
			Page returnPage = orderManagerService.queryPurchaseOrder(vcPurchaseVo, userCode, comCode, page, rows);
			
			//返回页面的结果集
			vcPurchaseList = returnPage.getResult();
			//总页数
			total = (int)returnPage.getTotalPageCount();
			//总记录数
			records = (int)returnPage.getTotalCount();
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 采购下单
	 * @return
	 * @throws BusinessException
	 */
	public String executePurchaseOrder() throws BusinessException {
		logger.info("访问/orderJson/executePurchaseOrder.do采购下单动作...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的用户代码
			String userCode = userInfo.getUserCode();
			if(StringUtils.isNotEmpty(jsonData)){
				List<VcPurchaseVo> vcPurchaseVoList = TFJSON.parseArray(jsonData,VcPurchaseVo.class);
				orderManagerService.executePurchaseOrder(vcPurchaseVoList, userCode);
			}
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	 /**
     * 导出待下单的采购明细excel
     * @return
     * @throws BusinessException
     * @author whj
     * @since 2014-03-11
     */
    public String exportPurchaseOrder() throws BusinessException {
        logger.info("访问/orderJson/exportPurchaseOrder.do?ajax=true导出待下单的采购明细excel...");
        try{
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
           //获取当前用户的用户代码
            String userCode = userInfo.getUserCode();
            //获取当前用户的机构代码
            String comCode = userInfo.getComCode();
            //查询Page对象
            VcPurchaseVo  queryVo = new VcPurchaseVo();
            queryVo.setPurchaseCode(purchaseCode);
            queryVo.setPrinteryId(printeryId);
            queryVo.setPrinteryName(printeryName);
            
           List<VcPurchaseVo> list = orderManagerService.queryPurchaseOrderList(queryVo, userCode, comCode);
            if(list==null || list.size()<1){
                throw new BusinessException("没有查询到符合条件的数据！");
            }else if(list.size()>65000){
                throw new BusinessException("查询结果超出限制，请缩小查询范围！");
            }
            ExcelExportHandler excelExportHandler = new ExcelExportHandler();
            //获取临时文件存放目录
            String filePath=excelExportHandler.getDirPath(SysConst.PURCHASE_ORDER_FILE_PATH);
           
            String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String fileName = "采购单_"+date+".xls";
            //表头数组          
            String[] title = {"采购单号","申请机构","申请机构代码","单证类型名称","单证类型代码","申印数量（份）","起始流水号","结束流水号","承印厂家","单价（元）","总金额（元）"}; 
            int size = list.size();
            //数据二位数组
            String[][] values = new String[size][];
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            DecimalFormat df1 = new DecimalFormat("0.00");           
            for(int i=0;i<list.size();i++){
                VcPurchaseVo tempVo = list.get(i);
                String[] arr = new String[title.length];
                arr[0] = tempVo.getPurchaseCode();
                arr[1] = tempVo.getOrgName();
                arr[2] = tempVo.getOrgId();
                arr[3] = tempVo.getVersionName();
                arr[4] = tempVo.getVersionCode();
                arr[5] = tempVo.getApplyPrintNum().toString();
                arr[6] = tempVo.getStartSerialNo();
                arr[7] = tempVo.getEndSerialNo();
                arr[8] = tempVo.getPrinteryName();
                arr[9] = df1.format(tempVo.getUnitPrice());
                arr[10] = df1.format(tempVo.getTotalAmount());
                values[i] = arr;
            }
            //创建excel文件
            excelExportHandler.createExcelFile(filePath+fileName, title, values);
            //返回值：文件存放目录==文件名
            jsonData = filePath +"=="+ fileName;
        }catch(BusinessException e){
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        }catch(Exception e){
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

	public VcPurchaseVo getVcPurchaseVo() {
		return vcPurchaseVo;
	}

	public void setVcPurchaseVo(VcPurchaseVo vcPurchaseVo) {
		this.vcPurchaseVo = vcPurchaseVo;
	}

	public List getVcPurchaseList() {
		return vcPurchaseList;
	}

	public void setVcPurchaseList(List vcPurchaseList) {
		this.vcPurchaseList = vcPurchaseList;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getPrinteryId() {
		return printeryId;
	}

	public void setPrinteryId(String printeryId) {
		this.printeryId = printeryId;
	}

	public String getPrinteryName() {
		return printeryName;
	}

	public void setPrinteryName(String printeryName) {
		this.printeryName = printeryName;
	}

	public Long getApplyPrintNum() {
		return applyPrintNum;
	}

	public void setApplyPrintNum(Long applyPrintNum) {
		this.applyPrintNum = applyPrintNum;
	}

	public void setOrderManagerService(OrderManagerService orderManagerService) {
		this.orderManagerService = orderManagerService;
	}
	public String getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
}
