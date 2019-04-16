package com.tapi.tcs.vc.inquiry.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.ExcelExportHandler;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.inquiry.service.StorageInquiryService;
import com.tapi.tcs.vc.inquiry.vo.StorageInquiryVo;

/**
 * 单证库存查询统计Action
 * <p>
 * Date 2013-04-16
 * </p>
 * <p>
 * Module：统计查询
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * 
 * @author zhxiao
 * @version 1.0
 */
public class StorageInquiryAction extends TFAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private StorageInquiryService storageInquiryService;
    private StorageInquiryVo storageInquiryVo;

    List<StorageInquiryVo> storageInquiryVoList;

    // 申请时间
    private Date applyStartDate;
    private Date applyEndDate;
    /**返回界面的字符串*/
    private String jsonData;

    /**
     * 进入单证库存查询统计查询页面
     * 
     * @return
     */
    public String initStorageInquiry() {
        logger.info("访问/storageInquiry/initStorageInquiry.do查询单证库存统计信息...");
        Date nowDate = new Date();
        applyStartDate = DateUtils.addMonth(nowDate, -2);
        applyEndDate = nowDate;
        return SUCCESS;
    }

    /**
     * 分页查询单证库存信息
     * 
     * @return 查询结果
     * @throws BusinessException
     *             异常
     */
    public String queryStorageInquiryListByPages() throws Exception {
        logger.info("访问/storageInquiryJson/queryStorageInquiryListByPages.do?ajax=true分页查询单证库存统计信息...");
        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            Page pageObj = storageInquiryService.queryStorageInquiryListByPages(storageInquiryVo, userInfo,
                    page, rows);
            if(pageObj != null){
	            storageInquiryVoList = (List<StorageInquiryVo>) pageObj.getResult();
	            total = (int) pageObj.getTotalPageCount();
	            records = (int) pageObj.getTotalCount();
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }
    
    /**
     * 导出excel
     * @return
     * @throws BusinessException
     */
    public String exportStorageInquiry() throws BusinessException {
    	logger.info("访问/storageInquiryJson/exportStorageInquiry.do?ajax=true导出excel...");
		
    	try{
    		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            //机构代码
            //String comCode = userInfo.getComCode();
            //校验导出条数不能超过最大限制
			if(total>SysConst.EXCEL_EXPORT_MAX_COUNT){
				throw new BusinessException("导出的数据大于"+SysConst.EXCEL_EXPORT_MAX_COUNT+"条，请缩小查询范围后重新导出！");
			}
            //查询出需要导出的数据
            List<StorageInquiryVo> list = storageInquiryService.findStorageInquiryList(storageInquiryVo, userInfo);
            if(list==null || list.size()==0){
            	throw new BusinessException("没有查询到符合条件的数据！");
            }
            ExcelExportHandler excelExportHandler = new ExcelExportHandler();
            //获取临时文件存放目录
            String dirPath = excelExportHandler.getDirPath();
            String date = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
            String fileName = "单证库存-"+date+".xls";
            //表头数组
            //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 BEGIN
            String[] title = {"单证类型代码","单证类型名称","所属机构","机构/使用人","起始号码","终止号码","库存数量","印刷批次","入库时间","使用截止期","状态"};
            //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 END
            int size = list.size();
            //数据二位数组
            String[][] values = new String[size][];
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			for(int i=0;i<size;i++){
				StorageInquiryVo storageInquiryVo = list.get(i);
				String[] arr = new String[11];
				String status = "";
				if("S1".equals(storageInquiryVo.getDocStatus())){
					status = "印刷入库";
				}else if("S2".equals(storageInquiryVo.getDocStatus())){
					status = "申领入库";
				}else if("S3".equals(storageInquiryVo.getDocStatus())){
					status = "回收入库";
				}else if("A".equals(storageInquiryVo.getDocStatus())){
					status = "可使用";
				}else{
					status = storageInquiryVo.getDocStatus();
				}
				arr[0] = storageInquiryVo.getDocVerCode();
				arr[1] = storageInquiryVo.getDocVerName();
				arr[2] = storageInquiryVo.getOrgCode();
				arr[3] = storageInquiryVo.getDocTakerCode();
				arr[4] = storageInquiryVo.getStartNum();
				arr[5] = storageInquiryVo.getEndNum();
				arr[6] = storageInquiryVo.getDocNum()+"";
				arr[7] = storageInquiryVo.getPressBatchNo();
				arr[8] = storageInquiryVo.getInStoreTime()==null?"":df.format(storageInquiryVo.getInStoreTime());
				//MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 BEGIN
				arr[9] = storageInquiryVo.getDeadLine()==null?"":df.format(storageInquiryVo.getDeadLine());
				//MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 END
				arr[10] = status;
				values[i] = arr;
			}
			//创建excel文件
			excelExportHandler.createExcelFile(dirPath+fileName, title, values);
			//返回值：文件存放目录==文件名
			jsonData = dirPath +"=="+ fileName;
    	}catch(BusinessException e	){
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

    public void setStorageInquiryService(StorageInquiryService storageInquiryService) {
        this.storageInquiryService = storageInquiryService;
    }

    public void setApplyStartDate(Date applyStartDate) {
        this.applyStartDate = applyStartDate;
    }

    public Date getApplyStartDate() {
        return applyStartDate;
    }

    public void setApplyEndDate(Date applyEndDate) {
        this.applyEndDate = applyEndDate;
    }

    public Date getApplyEndDate() {
        return applyEndDate;
    }

    public StorageInquiryVo getStorageInquiryVo() {
        return storageInquiryVo;
    }

    public void setStorageInquiryVo(StorageInquiryVo storageInquiryVo) {
        this.storageInquiryVo = storageInquiryVo;
    }

    public List<StorageInquiryVo> getStorageInquiryVoList() {
        return storageInquiryVoList;
    }

    public void setStorageInquiryVoList(List<StorageInquiryVo> storageInquiryVoList) {
        this.storageInquiryVoList = storageInquiryVoList;
    }

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
    
}
