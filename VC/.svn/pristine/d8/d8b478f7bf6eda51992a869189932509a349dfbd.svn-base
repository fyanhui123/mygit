package com.tapi.tcs.vc.doctakeio.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.ExcelExportHandler;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.doctakeio.service.FindDocTakerIoService;
import com.tapi.tcs.vc.schema.model.VcDocTakerIo;

@SuppressWarnings("serial")
public class FindDocTakioAction extends TFAction{
	public FindDocTakerIoService  findDocTakerIoService;
	public List<VcDocTakerIo> listVcDocTakerIo;
	public String  docVerCode; //查询条件单证类型
	public String  docNum;    //单证号
	private String jsonData;
	public  VcDocTakerIo vcDocTakerIo;
	public String findDocTakerio() throws BusinessException {
		logger.info("访问//takerio/findVcDocTakerio.action?ajax=true进入查询使用人单证发放回收轨迹页面...");
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String findVcDocTakerio()throws BusinessException {
		try {
			UserInfo userInfo=(UserInfo) getRequest().getSession().getAttribute("userInfo");
			if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
			Page pag= findDocTakerIoService.queryVcDocTakerIoListByPages(docVerCode,docNum, userInfo, page, rows,true);
			listVcDocTakerIo = (List<VcDocTakerIo>) pag.getResult();
	        total = (int) pag.getTotalPageCount();
	        records = (int) pag.getTotalCount();
		} catch (BusinessException e) {
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
		} catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
		return SUCCESS;
	}
	public String exportDocTakerio()throws Exception{
		logger.info("访问//findVcDocTakerioJson/exportDocTakerio.do?ajax=true导出excel...");
		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        if (userInfo == null) {
            throw new BusinessException("登录超时，请重新登录！ ");
        }
        String docVerCode = getRequest().getParameter("docVerCode");
        String docNum = getRequest().getParameter("docNum");
		try {
			if(total>SysConst.EXCEL_EXPORT_MAX_COUNT){
				throw new BusinessException("导出的数据大于"+SysConst.EXCEL_EXPORT_MAX_COUNT+"条，请缩小查询范围后重新导出！");
			}
			Page pag= findDocTakerIoService.queryVcDocTakerIoListByPages(docVerCode,docNum, userInfo, page, rows,false);
			@SuppressWarnings("unchecked")
			List<VcDocTakerIo> list = (List<VcDocTakerIo>) pag.getResult();
			  if(list==null || list.size()==0){
	            	throw new BusinessException("没有查询到符合条件的数据！");
	            }
			  ExcelExportHandler excelExportHandler = new ExcelExportHandler();
	            //获取临时文件存放目录
	          String dirPath = excelExportHandler.getDirPath();
	          String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        
	            String fileName = "guji-"+date+".xls";
	          //表头数组
	            //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 BEGIN
	            String[] title = {"单证类型","单证类型名称","印刷号","起始流水号","终止流水号","数量","发放/回收类型","操作员","操作时间","使用人","使用人所属机构","发放机构"};
	            //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 END
	            int size = list.size();
	            //数据二位数组
	            String[][] values = new String[size][];
	            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for(int i=0;i<size;i++){
					VcDocTakerIo docTakerIo = list.get(i);
					String[] arr = new String[12];
					String status = "";
					arr[0]=docTakerIo.getDocVerCode();
					arr[1]=docTakerIo.getDocVerName();
					arr[2]=docTakerIo.getPressBatchNo();
					arr[3]=docTakerIo.getStartNum();
					arr[4]=docTakerIo.getEndNum();
					arr[5]=(docTakerIo.getDocNum()).toString();
					arr[6]=docTakerIo.getOprType();
					arr[7]= docTakerIo.getOprCode();
					arr[8] = docTakerIo.getOprTime()==null?"":df.format(docTakerIo.getOprTime());
					arr[9]= docTakerIo.getTakerCode();
					arr[10]=docTakerIo.getAcceptOrgCode();
					arr[11]=docTakerIo.getProvideOrgCode();
					values[i] = arr;
				}
				//创建excel文件
				excelExportHandler.createExcelFile(dirPath+fileName, title, values);
				//返回值：文件存放目录==文件名
				jsonData = dirPath +"=="+ fileName;
	     } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
		}
		return SUCCESS;
	}
	
	
	public FindDocTakerIoService getFindDocTakerIoService() {
		return findDocTakerIoService;
	}
	public void setFindDocTakerIoService(FindDocTakerIoService findDocTakerIoService) {
		this.findDocTakerIoService = findDocTakerIoService;
	}
	public List<VcDocTakerIo> getListVcDocTakerIo() {
		return listVcDocTakerIo;
	}
	public void setListVcDocTakerIo(List<VcDocTakerIo> listVcDocTakerIo) {
		this.listVcDocTakerIo = listVcDocTakerIo;
	}
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public VcDocTakerIo getVcDocTakerIo() {
		return vcDocTakerIo;
	}
	public void setVcDocTakerIo(VcDocTakerIo vcDocTakerIo) {
		this.vcDocTakerIo = vcDocTakerIo;
	}

}
