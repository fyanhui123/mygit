package com.tapi.tcs.vc.rolelist.web;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.tf.security.authority.SecurityContext;
import com.tapi.tcs.tf.security.authority.User;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.ExcelExportHandler;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.inquiry.vo.StorageInquiryVo;
import com.tapi.tcs.vc.rolelist.service.RolelistService;
import com.tapi.tcs.vc.rolelist.vo.RoleListVo;
@SuppressWarnings("serial")
public class RoleListAction extends TFAction{
	private RolelistService rolelistService;
	List<RoleListVo> pubUserDefinfo;
	private RoleListVo roleListVo;
	private String jsonData;
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public RoleListVo getRoleListVo() {
		return roleListVo;
	}
	public void setRoleListVo(RoleListVo roleListVo) {
		this.roleListVo = roleListVo;
	}
	public void setRolelistService(RolelistService rolelistService) {
		this.rolelistService = rolelistService;
	}
	public List<RoleListVo> getPubUserDefinfo() {
		return pubUserDefinfo;
	}
	public void setPubUserDefinfo(List<RoleListVo> pubUserDefinfo) {
		this.pubUserDefinfo = pubUserDefinfo;
	}
	public String roleTakeListQuery() {
        logger.info("访问/role/roleTakeListQuery.do单证使用人查询信息...");
		return "success";
    }
	public String roleListQuery() {
        logger.info("访问/role/roleListQuery.do单证管理员查询信息...");
		return "success";
    }
	public String roleList()throws Exception {
		try {
			UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
	        if (userInfo == null) {
	            throw new BusinessException("登录超时，请重新登录！ ");
	        }
			Page pageObj = rolelistService.queryRoleList(roleListVo, userInfo, page, rows);
			if(pageObj!=null){
				pubUserDefinfo = (List<RoleListVo>) pageObj.getResult();
		            // 总页数
		        total = (int) pageObj.getTotalPageCount();
		            // 总记录数
		        records = (int) pageObj.getTotalCount();
			}else{
				System.out.println("pageObj+为空");
			}
			return "success";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonData;
		
	}
	public String exportRoleListInquiry()throws Exception{
		logger.info("访问/roleJson/exportRoleListInquiry.do?ajax=true导出excel...");
		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        if (userInfo == null) {
            throw new BusinessException("登录超时，请重新登录！ ");
        }
		try {
			if(total>SysConst.EXCEL_EXPORT_MAX_COUNT){
				throw new BusinessException("导出的数据大于"+SysConst.EXCEL_EXPORT_MAX_COUNT+"条，请缩小查询范围后重新导出！");
			}
			 List<RoleListVo> list = rolelistService.findRoleListInquiryList(roleListVo, userInfo);
			  if(list==null || list.size()==0){
	            	throw new BusinessException("没有查询到符合条件的数据！");
	            }
			  ExcelExportHandler excelExportHandler = new ExcelExportHandler();
	            //获取临时文件存放目录
	          String dirPath = excelExportHandler.getDirPath();
	          String date = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
	         // String fileName = "单证角色-"+date+".xls";
	          if(roleListVo.getUserType().equals("0")){
	        	  String fileName = "单证管理员-"+date+".xls";
	          //表头数组
	            //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 BEGIN
	            String[] title = {"机构代码","机构名称","所属部门名称","用户名称","用户代码","员工代码","状态"};
	            //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 END
	            int size = list.size();
	            //数据二位数组
	            String[][] values = new String[size][];
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				for(int i=0;i<size;i++){
					RoleListVo roleListVo = list.get(i);
					String[] arr = new String[7];
					String status = "";
					arr[0]=roleListVo.getCompanyCode();
					arr[1]=roleListVo.getUnitName();
					arr[2]=roleListVo.getDepartMent();
					arr[3]=roleListVo.getUserName();
					arr[4]=roleListVo.getUserCode();
					arr[5]=roleListVo.getEmployeeId();
					if(roleListVo.getValidKind().equals("1")){
						arr[6]="有效";
					}
					else{
						arr[6]="无效";
					}
					//arr[6]=roleListVo.getValidKind();
					values[i] = arr;
				}
				//创建excel文件
				excelExportHandler.createExcelFile(dirPath+fileName, title, values);
				//返回值：文件存放目录==文件名
				jsonData = dirPath +"=="+ fileName;
		}else{
			 String fileName = "单证使用人-"+date+".xls";
			 //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 BEGIN
            String[] title = {"机构代码","机构名称","用户名称","用户代码","员工代码","状态"};
            //MODIFY BY zhxiao VC-97 关于申请“使用截止期”功能显现的事宜 END
            int size = list.size();
            //数据二位数组
            String[][] values = new String[size][];
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			for(int i=0;i<size;i++){
				RoleListVo roleListVo = list.get(i);
				String[] arr = new String[6];
				String status = "";
				arr[0]=roleListVo.getCompanyCode();
				arr[1]=roleListVo.getUnitName();
				arr[2]=roleListVo.getUserName();
				arr[3]=roleListVo.getUserCode();
				arr[4]=roleListVo.getEmployeeId();
				if(roleListVo.getValidKind().equals("1")){
					arr[5]="有效";
				}
				else{
					arr[5]="无效";
				}
				//arr[5]=roleListVo.getValidKind();
				values[i] = arr;
			}
			//创建excel文件
			excelExportHandler.createExcelFile(dirPath+fileName, title, values);
			//返回值：文件存放目录==文件名
			jsonData = dirPath +"=="+ fileName;
		  }
	     } catch (Exception e) {
			e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
		}
		return "success";
	}
	

}
