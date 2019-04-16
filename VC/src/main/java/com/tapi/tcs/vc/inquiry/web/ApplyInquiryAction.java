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
import com.tapi.tcs.vc.inquiry.service.ApplyInquiryService;
import com.tapi.tcs.vc.inquiry.vo.ApplyInquiryVo;

/**
 * 单证申领查询统计Action
 * <p>
 * Date 2013-04-11
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
public class ApplyInquiryAction extends TFAction {

    private static final long serialVersionUID = -2201935600076140624L;
    /** 单证申领查询统计vo */
    ApplyInquiryVo applyInquiryVo;
    /** 单证申领查询统计service */
    private ApplyInquiryService applyInquiryService;
    List applyInquiryVoList;
    // 申请时间
    private Date applyStartDate;

    private Date applyEndDate;
    /**返回页面的字符串*/
    private String jsonData;

    /**
     * 进入单证申领统计查询页面
     * 
     * @return
     */
    public String initApplyInquiry() {
        logger.info("访问/applyInquiry/initApplyInquiry.do查询单证申领统计信息...");
        Date nowDate = new Date();
        applyStartDate = DateUtils.addMonth(nowDate, -2);
        applyEndDate = nowDate;
        return SUCCESS;
    }

    /**
     * 分页查询单证申领信息
     * 
     * @return 查询结果
     * @throws BusinessException
     *             异常
     */
    public String queryApplyInquiryListByPages() throws Exception {
        logger.info("访问/applyInquiryJson/queryApplyInquiryListByPages.do?ajax=true分页查询单证申领统计信息...");

        try {
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }

            Page pageObj = applyInquiryService.queryApplyInquiryListByPages(applyInquiryVo, userInfo, page,
                    rows);

            // 返回页面的结果集
            applyInquiryVoList = pageObj.getResult();
            // 总页数
            total = (int) pageObj.getTotalPageCount();
            // 总记录数
            records = (int) pageObj.getTotalCount();
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
     * 单证申领查询统计导出
     * @return
     * @throws BusinessException
     */
    public String exportApplyInquiryList() throws BusinessException {
    	logger.info("访问/applyInquiryJson/exportApplyInquiryList.do?ajax=true单证申领查询统计导出...");
    	try{
    		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
            String comCode = userInfo.getComCode();
            //根据条件查询数据
            List<ApplyInquiryVo> list = applyInquiryService.findApplyInquiryList(applyInquiryVo, comCode);
            if(list==null || list.size()<1){
            	throw new BusinessException("没有查询到符合条件的数据！");
            }else if(list.size()>65000){
            	throw new BusinessException("查询结果超出限制，请缩小查询范围！");
            }
            ExcelExportHandler excelExportHandler = new ExcelExportHandler();
            //获取临时文件存放目录
            String dirPath = excelExportHandler.getDirPath();
            String date = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
            String fileName = "遗失核销统计-"+date+".xls";
            //表头数组
            String[] title = {"申领单号","单证类型代码","单证类型名称","申请数量","申请人","申领机构","申请时间","处理状态"}; 
            int size = list.size();
            //数据二位数组
            String[][] values = new String[size][];
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for(int i=0;i<list.size();i++){
				ApplyInquiryVo applyInquiryVo = list.get(i);
				String[] arr = new String[title.length];
				String status = applyInquiryVo.getApplyStatus();
				String statusTmp = applyInquiryVo.getApplyStatus();
				if ("1".equals(statusTmp)) {
					status = "新建";
				} else if ("2".equals(statusTmp)) {
					status = "等待发放";
				} else if ("3".equals(statusTmp)) {
					status = "发放退回";
				} else if ("4".equals(statusTmp)) {
					status = "等待确认";
				} else if ("5".equals(statusTmp)) {
					status = "确认退回";
				} else if ("6".equals(statusTmp)) {
					status = "已确认";
				} else if ("0".equals(statusTmp)) {
					status = "已删除";
				}
				arr[0] = applyInquiryVo.getApplyCode();
				arr[1] = applyInquiryVo.getDocVerCode();
				arr[2] = applyInquiryVo.getDocVerName();
				arr[3] = applyInquiryVo.getApplyNum()+"";
				arr[4] = applyInquiryVo.getApplyOprCode();
				arr[5] = applyInquiryVo.getApplyOrgCode();
				arr[6] = applyInquiryVo.getApplyTime()==null?"":df.format(applyInquiryVo.getApplyTime());
				arr[7] = status;
				values[i] = arr;
			}
			//创建excel文件
			excelExportHandler.createExcelFile(dirPath+fileName, title, values);
			//返回值：文件存放目录==文件名
			jsonData = dirPath +"=="+ fileName;
    	}catch (BusinessException e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        }catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
    	return SUCCESS;
    }

    public void setApplyInquiryVo(ApplyInquiryVo applyInquiryVo) {
        this.applyInquiryVo = applyInquiryVo;
    }

    public ApplyInquiryVo getApplyInquiryVo() {
        return applyInquiryVo;
    }

    public void setApplyInquiryService(ApplyInquiryService applyInquiryService) {
        this.applyInquiryService = applyInquiryService;
    }

    public List getApplyInquiryVoList() {
        return applyInquiryVoList;
    }

    public void setApplyInquiryVoList(List applyInquiryVoList) {
        this.applyInquiryVoList = applyInquiryVoList;
    }

    public Date getApplyStartDate() {
        return applyStartDate;
    }

    public void setApplyStartDate(Date applyStartDate) {
        this.applyStartDate = applyStartDate;
    }

    public Date getApplyEndDate() {
        return applyEndDate;
    }

    public void setApplyEndDate(Date applyEndDate) {
        this.applyEndDate = applyEndDate;
    }

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

}
