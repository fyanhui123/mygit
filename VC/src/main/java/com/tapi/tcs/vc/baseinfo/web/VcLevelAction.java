package com.tapi.tcs.vc.baseinfo.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.baseinfo.service.VcLevelService;
import com.tapi.tcs.vc.baseinfo.service.VcTakerService;
import com.tapi.tcs.vc.baseinfo.vo.DTreeDto;
import com.tapi.tcs.vc.baseinfo.vo.UserVo;
import com.tapi.tcs.vc.baseinfo.vo.ZTreeDto;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.PubUserDef;
import com.tapi.tcs.vc.schema.model.VcAgencyOrg;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcPubCode;
import com.tapi.tcs.vc.schema.model.VcTaker;

public class VcLevelAction extends TFAction {

    private static final long serialVersionUID = 4134898588257320670L;
    /** 日志管理 */
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private VcLevelService vcLevelService;
    
    private VcTakerService vcTakerService;
    /** 查询结果 */
    private List vcLevelList;
    /**中介机构查询列表*/
    private List vcAgencyOrgList;
    /**使用人查询列表*/
    private List vcTakerList;
    /** 上级机构id */
    private String parentOrgId;
    /** 级别单位类型：0-机构，1-员工 */
    private String unitType;
    /** 级别单位对象 */
    private VcLevel vcLevel;
    /** 中介机构对象 */
    private VcAgencyOrg vcAgencyOrg;
    /**使用人对象*/
    private VcTaker vcTaker;
    /**提示信息*/
    private String info;
    /**编辑类型：new-新增,edit-修改*/
    private String editType;
    /** 用户选择组件参数 */
    private String code;
    /** 用户选择组件参数 */
    private String name;
    /** 树形显示到哪一级机构 */
    private int minLevel;
    private List<UserVo> users;
    /**
     * tree List对象
     */
    private List<DTreeDto> treeList;
    /**
     * tree List对象
     */
    private List<ZTreeDto> zTreeList;
    private String jsonResultList;
    // 对应的机构代码返回标签Id
    private String tagCodeId;
    // 对应的机构名字返回标签Id
    private String tagNameId;
    private boolean singleSelect;
    /**
     * 树机构的根(notCurOrg-根节点非当前登录用户机构）
     */
    private String root; 
    /**自动完成/下拉列表组件*/
    private List<Map<String, String>> mapList;
    /**是否只显示机构*/
    private String onlyOrgFlag;
    
    /**结果map信息*/
    private Map<String, Object> resultMap;

    
    /**调用界面*/
    private String pageTableName;
    

	public String queryOrgTreeList() throws BusinessException {
		try{
			treeList = vcLevelService.queryOrgTreeList();
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

    public String initQueryOrgTree() {
        return SUCCESS;
    }

    /**
     * Ztree初始化
     * 
     * @return
     * @throws BusinessException
     * @author wanghuajian
     * @date 2013-05-13
     */
    public String initQueryOrgZTree() throws BusinessException {
    	logger.info("访问/vclevel/initQueryOrgZTree.do初始化Ztree...");
    	try{
	        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
	        if (userInfo == null) {
	            throw new BusinessException("登录超时，请重新登录！ ");
	        }
	        String rootOrgCode=userInfo.getComCode();
	        //modify by zhxiao3 20141113:VC-135单证库存查询优化 begin
	        //非空判断
	        if(pageTableName!=""&&pageTableName!=null){
	        	 if(rootOrgCode.length() == 4 && pageTableName.equals("storageInquiryPage")){
	 	        	root = "notCurOrg";
	 	        	rootOrgCode="";
	 	        }
	        	 if(rootOrgCode.length() == 6 && pageTableName.equals("storageInquiryPage")){
		 	        	root = "notCurOrg";
		 	        	rootOrgCode=rootOrgCode.substring(0, 4);
		 	        }
	        }
	        //modify by zhxiao3 20141113:VC-135单证库存查询优化 end
	        //树机构的根
	        if(rootOrgCode.length() == 2){
	        	 if(StringUtils.isNotBlank(root) && "notCurOrg".equals(root)){
	 	            rootOrgCode=null;
	 	        }
	        }
	        if(pageTableName!=""&&pageTableName!=null){
	        	if(pageTableName.equals("allotListDtoOrgCodeName")){
	        		if(rootOrgCode.length()==4){
	        			minLevel=2;
	        			rootOrgCode="01";
	        		}
	        		if(rootOrgCode.length()==6){
	        			rootOrgCode=rootOrgCode.substring(0, 4);
	        		}
	        	}
	        }
	        zTreeList = vcLevelService.queryOrgZTreeList(rootOrgCode, minLevel, onlyOrgFlag);
	        jsonResultList = TFJSON.toJSONString(zTreeList);
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
     * 进入权限机构设置
     * 
     * @return
     * @author chy
     * @date 2013-05-06
     */
    public String preLevelSet() {
        logger.info("访问/vclevel/preLevelSet.do进入权限机构设置...");
        return SUCCESS;
    }

    /**
     * 查询下属机构/岗位列表
     * 
     * @return
     * @throws BusinessException
     * @author chy
     */
    public String queryLevelSet() throws BusinessException {
        logger.info("访问/vclevelJson/queryLevelSet.do查询下属机构列表...");
        try{
	        Page returnPage = vcLevelService.queryVcLevelPage(parentOrgId, unitType, page, rows);
	        // 返回页面的结果集
	        vcLevelList = returnPage.getResult();
	        // 总页数
	        total = (int) returnPage.getTotalPageCount();
	        // 总记录数
	        records = (int) returnPage.getTotalCount();
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
     * 查询中介机构列表
     * @return
     * @author chy
     */
    public String queryAgencyOrg() throws BusinessException {
    	logger.info("访问/vclevelJson/queryAgencyOrg.do查询中介机构列表...");
    	try{
    		//归属机构代码 
    		String orgCode = getRequest().getParameter("orgCode");
    		Page returnPage = vcLevelService.queryVcAgencyOrgPage(orgCode, page, rows);
            // 返回页面的结果集
            vcAgencyOrgList = returnPage.getResult();
            // 总页数
            total = (int) returnPage.getTotalPageCount();
            // 总记录数
            records = (int) returnPage.getTotalCount();
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
     * 查询使用人列表
     * @return
     * @author chy
     */
    public String queryTaker() throws BusinessException {
    	logger.info("访问/vclevelJson/queryTaker.do查询使用人列表...");
    	try{
    		//归属机构代码 
    		String orgCode = getRequest().getParameter("orgCode");
    		Page returnPage = vcLevelService.queryVcTakerPage(orgCode, page, rows);
            // 返回页面的结果集
            vcTakerList = returnPage.getResult();
            // 总页数
            total = (int) returnPage.getTotalPageCount();
            // 总记录数
            records = (int) returnPage.getTotalCount();
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
     * 保存机构
     * 
     * @return
     * @throws BusinessException
     * @author chy
     * @date 2013-05-07
     */
    public String saveCompany() throws BusinessException {
        logger.info("访问/vclevel/saveCompany.do保存机构...editType=" + editType);
        try{
	        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
	        if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
	        String userCode = userInfo.getUserCode();
	        info = "-1";
	        if (vcLevel == null)
	            throw new BusinessException("无法收集机构信息！ ");
	        if ("new".equals(editType)) {
	            boolean flag = vcLevelService.checkUnitCode(vcLevel.getUnitCode());
	            if (!flag) {
	                // 级别代码重复
	                info = "0";
	            } else {
	                // 根据父节点ID查询出上级机构信息。从而计算出级别登记、可管理级别登记
	                VcLevel vcLevelTemp = vcLevelService.findVcLevelByPK(vcLevel.getParentOrgId());
	                if (vcLevelTemp != null) {
	                    // 级别单位类型：0-机构，1-员工
	                    vcLevel.setUnitType("0");
	                    vcLevel.setParentOrgCode(vcLevelTemp.getUnitCode());
	                    vcLevel.setLevelNo(vcLevelTemp.getLevelNo() + 1);
	                    vcLevel.setManageLevel(vcLevel.getLevelNo() + 1);
	                    vcLevel.setValidStatus("1");
	                    vcLevel.setCreatedBy(userCode);
	                    vcLevel.setDateCreated(new Date());
	                    vcLevel.setUpdatedBy(userCode);
	                    vcLevel.setDateUpdated(new Date());
	                    vcLevelService.saveVcLevel(vcLevel);
	                    info = "1";
	                }
	            }
	        } else if ("edit".equals(editType)) {
	            VcLevel vcLevelTemp = vcLevelService.findVcLevelByPK(vcLevel.getId());
	            //vcLevelTemp.setUnitName(vcLevel.getUnitName());
	            vcLevelTemp.setComType(vcLevel.getComType());
	            vcLevelTemp.setDisplayNo(vcLevel.getDisplayNo());
	            vcLevelTemp.setUpdatedBy(userCode);
	            vcLevelTemp.setDateUpdated(new Date());
	            vcLevelService.updateVcLevel(vcLevelTemp);
	            info = "1";
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
     * 保存岗位
     * 
     * @return
     * @throws BusinessException
     * @author chy
     * @date 2013-05-07
     */
    public String saveRole() throws BusinessException {
        logger.info("访问/vclevel/saveRole.do保存岗位...editType=" + editType);
        try{
	        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
	        if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
	        String userCode = userInfo.getUserCode();
	        info = "-1";
	        if (vcLevel == null)
	            throw new Exception("无法收集岗位信息！ ");
	        boolean flag;
	        if ("new".equals(editType)) {
	        	String result=vcLevel.getInvoiceFlag();
	        	flag = vcLevelService.checkUser(vcLevel.getUnitCode(),vcLevel.getInvoiceFlag());
	            if (!flag) {
	                // 级别代码重复
	                info = "0";
	            } else {
	                // 根据父节点ID查询出上级机构信息。从而计算出级别登记、可管理级别登记
	                VcLevel vcLevelTemp = vcLevelService.findVcLevelByPK(vcLevel.getParentOrgId());
	                if (vcLevelTemp != null) {
	                    // 级别单位类型：0-机构，1-员工
	                    vcLevel.setUnitType("1");
	                    vcLevel.setParentOrgCode(vcLevelTemp.getUnitCode());
	                    vcLevel.setLevelNo(vcLevelTemp.getLevelNo() + 1);
	                    vcLevel.setManageLevel(vcLevel.getLevelNo() + 1);
	                    vcLevel.setValidStatus("1");
	                    vcLevel.setCreatedBy(userCode);
	                    vcLevel.setDateCreated(new Date());
	                    vcLevel.setUpdatedBy(userCode);
	                    vcLevel.setDateUpdated(new Date());
	                    if(vcLevel.getInvoiceFlag().equals("0")){
	                    	vcLevel.setInvoiceFlag("");
	                    }
	                    vcLevelService.saveVcLevel(vcLevel);
	                    info = "1";
	                }
	            }
	        } else if ("edit".equals(editType)) {
	            VcLevel vcLevelTemp = vcLevelService.findVcLevelByPK(vcLevel.getId());
	            //vcLevelTemp.setUnitName(vcLevel.getUnitName());
	            vcLevelTemp.setDisplayNo(vcLevel.getDisplayNo());
	            vcLevelTemp.setUpdatedBy(userCode);
	            vcLevelTemp.setDateUpdated(new Date());
	            vcLevelService.updateVcLevel(vcLevelTemp);
	            info = "1";
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
     * 保存中介机构
     * @return
     * @author chy
     */
    public String saveAgencyOrg() throws BusinessException {
    	logger.info("访问/vclevel/saveAgencyOrg.do保存中介机构...editType=" + editType);
    	try{
    		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
    		if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
            String userCode = userInfo.getUserCode();
            info = "-1";
            if (vcAgencyOrg == null)
                throw new BusinessException("无法收集中介机构信息！ ");
            if ("new".equals(editType)) {
                boolean flag = vcLevelService.checkAgencyOrgCode(vcAgencyOrg.getAgencyOrgCode());
                if (!flag) {
                    // 中介机构代码重复
                    info = "0";
                } else {
                	vcAgencyOrg.setStatus("1");
                	vcAgencyOrg.setCreatedBy(userCode);
                	vcAgencyOrg.setDateCreated(new Date());
                	vcAgencyOrg.setUpdatedBy(userCode);
                	vcAgencyOrg.setDateUpdated(new Date());
                	vcLevelService.saveVcAgencyOrg(vcAgencyOrg);
                    info = "1";
                }
            } else if ("edit".equals(editType)) {
            	VcAgencyOrg vcAgencyOrgTemp = vcLevelService.findVcAgencyOrgById(vcAgencyOrg.getId());
            	vcAgencyOrgTemp.setBusinessCode(vcAgencyOrg.getBusinessCode());
            	vcAgencyOrgTemp.setBusinessDetailCode(vcAgencyOrg.getBusinessDetailCode());
            	vcAgencyOrgTemp.setIsSalesNet(vcAgencyOrg.getIsSalesNet());
            	vcAgencyOrgTemp.setDisplayNo(vcAgencyOrg.getDisplayNo());
            	vcAgencyOrgTemp.setUpdatedBy(userCode);
            	vcAgencyOrgTemp.setDateUpdated(new Date());
            	vcLevelService.updateVcAgencyOrg(vcAgencyOrgTemp);
                info = "1";
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
     * 保存使用人
     * @return
     */
    public String saveVcTaker() throws BusinessException {
    	logger.info("访问/vclevel/saveVcTaker.do保存使用人...editType=" + editType);
    	try{
    		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
    		if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
            String userCode = userInfo.getUserCode();
            resultMap=new HashMap<String,Object>();
            resultMap.put("flag", "-1");
            if (vcTaker == null)
                throw new BusinessException("无法收集使用人信息！ ");
            if ("new".equals(editType)) {
                //boolean flag = vcLevelService.checkTakerCode(vcTaker.getTakerCode());
                boolean flag =true;///不存在
                VcTaker queryDto=new VcTaker();
                queryDto.setTakerCode(vcTaker.getTakerCode());
                List<VcTaker>  tempTakerList =vcTakerService.queryVcTakerList(queryDto);
                if (tempTakerList!=null && tempTakerList.size()>0){
                    flag=false; 
                }               
                if (!flag) {                    
                    String temOrgName=vcLevelService.getComName(tempTakerList.get(0).getOrgCode());
                    // 中介机构代码重复
                    //使用人代码已存在，当前所属机构temOrgName【】
                    resultMap.put("flag", "0");
                    resultMap.put("msg", "使用人代码已存在，当前所属机构："+temOrgName+"["+tempTakerList.get(0).getOrgCode()+"]");
                } else {
                	//根据机构代码和用户代码判断该用户在UM_USER_DEF表是否存在
                	//boolean existflag = true;
                	//使用人类型为非'外部业务员'时校验用户是否存在
                	//if(!"2".equals(vcTaker.getTakerTypeCode())){
                	//	existflag = vcLevelService.checkUserCode(vcTaker.getTakerCode(), vcTaker.getOrgCode());
                	//}
                	//if(!existflag){
                		//用户不存在
                	//	info = "-1";
                	//}else{
	                	vcTaker.setStatus("1");
	                	vcTaker.setCreatedBy(userCode);
	                	vcTaker.setDateCreated(new Date());
	                	vcTaker.setUpdatedBy(userCode);
	                	vcTaker.setDateUpdated(new Date());
	                	vcLevelService.saveVcTaker(vcTaker);
	                    resultMap.put("flag", "1");	                    
                	//}
                }
            } else if ("edit".equals(editType)) {
            	VcTaker vcTakerTmp = vcLevelService.findVcTakerById(vcTaker.getId());
            	//校验用户名是否正确
            	boolean flag = vcLevelService.checkTakerName(vcTakerTmp.getTakerCode(), vcTaker.getTakerName());
            	if(!flag){
            		throw new BusinessException("用户不存在，请检查用户名是否正确");
            	}
            	vcTakerTmp.setTakerName(vcTaker.getTakerName());
            	vcTakerTmp.setTakerTypeCode(vcTaker.getTakerTypeCode());
            	vcTakerTmp.setAgencyOrgCode(vcTaker.getAgencyOrgCode());
            	vcTakerTmp.setBankAccount(vcTaker.getBankAccount());
            	vcTakerTmp.setBankCode(vcTaker.getBankCode());
            	vcTakerTmp.setBankAccountName(vcTaker.getBankAccountName());
            	vcTakerTmp.setBankName(vcTaker.getBankName());
            	vcTakerTmp.setPassbookOrCard(vcTaker.getPassbookOrCard());
            	vcTakerTmp.setUpdatedBy(userCode);
            	vcTakerTmp.setDateUpdated(new Date());
            	vcLevelService.updateVcTaker(vcTakerTmp);
            	resultMap.put("flag", "1");  
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
     * 进入机构/岗位修改界面
     * 
     * @return
     * @throws BusinessException
     * @author chy
     * @date 2013-05-07
     */
    public String beforeLevelSetUpdate() throws BusinessException {
        logger.info("访问/vclevel/beforeLevelSetUpdate.do进入机构修改界面...");
        try{
	        String id = getRequest().getParameter("id");
	        vcLevel = vcLevelService.findVcLevel(Long.valueOf(id));
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
     * 进入中介机构修改界面
     * 
     * @return
     * @throws BusinessException
     * @author chy
     */
    public String beforeAgencyOrgUpdate() throws BusinessException {
        logger.info("访问/vclevel/beforeAgencyOrgUpdate.do进入中介机构修改界面...");
        try{
	        String id = getRequest().getParameter("id");
	        vcAgencyOrg = vcLevelService.findVcAgencyOrgById(Long.valueOf(id));
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
     * 进入使用人修改界面
     * @return
     * @throws BusinessException
     */
    public String beforeVcTakerUpdate() throws BusinessException{
    	logger.info("访问/vclevel/beforeVcTakerUpdate.do进入使用人修改界面...");
    	try{
    		String id = getRequest().getParameter("id");
    		vcTaker = vcLevelService.findVcTakerById(Long.valueOf(id));
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
     * 校验机构是否存在下级机构或人员
     * 
     * @return
     * @throws BusinessException
     * @author chy
     */
    public String checkChildCompany() throws BusinessException {
        info = "1";
        try{
	        String selectedId = getRequest().getParameter("id");
	        String[] ids = selectedId.split(",");
	        for (String id : ids) {
	            //校验是否可以删除
	            boolean flag = vcLevelService.checkChildCompany(Long.valueOf(id));
	            if (!flag) {
	                info = "0";
	                break;
	            }
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
     * 删除级别设置
     * 
     * @return
     * @throws BusinessException
     * @author chy
     * @date 2013-05-07
     */
    public String deleteLevelSet() throws BusinessException {
        logger.info("访问/vclevel/deleteLevelSet.do删除级别设置...");
        try{
	        // 获取参数
	        String selectedId = getRequest().getParameter("id");
	        String[] ids = selectedId.split(",");
	        for (String id : ids) {
	            vcLevelService.deleteVcLevelByPK(Long.valueOf(id));
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
     * 删除中介机构
     * @return
     * @throws BusinessException
     */
    public String deleteVcAgencyOrg() throws BusinessException {
        logger.info("访问/vclevel/deleteVcAgencyOrg.do删除中介机构...");
        try{
	        // 获取参数
	        String selectedId = getRequest().getParameter("id");
	        String[] ids = selectedId.split(",");
	        for (String id : ids) {
	            vcLevelService.deleteVcAgencyOrg(Long.valueOf(id));
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
     * 删除使用人
     * @return
     */
    public String deleteVcTaker() throws BusinessException {
    	logger.info("访问/vclevel/deleteVcTaker.do删除使用人...");
    	try{
    		 // 获取参数
	        String selectedId = getRequest().getParameter("id");
	        String[] ids = selectedId.split(",");
	        for (String id : ids) {
	            vcLevelService.deleteVcTaker(Long.valueOf(id));
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
     * 人员变更机构前校验
     * 
     * 
     * @throws BusinessException
     * @author whj
     * @date 20131211
     */
    public String checkBeforeChangeOrg() throws BusinessException {
        logger.info("访问/vclevelJson/checkBeforeChangeOrg.do人员机构变更检测...");
        try{
            // 获取参数
            String id = getRequest().getParameter("id");
            String objectFlag = getRequest().getParameter("objectFlag");            
           resultMap =vcLevelService.checkBeforeChangeOrg(Long.valueOf(id),objectFlag); 
          // boolean hasError=(Boolean)resultMap.get("hasError");
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
     * 进入岗位人员机构变更界面
     * 
     * @return
     * @throws BusinessException
     * @author whj
     * @date 2013-12-16
     */
    public String initRoleOrgChange() throws BusinessException {
        logger.info("访问/vclevel/initRoleOrgChange.do进入岗位人员机构变更界面...");
        try{
            String id = getRequest().getParameter("id");
            vcLevel = vcLevelService.findVcLevel(Long.valueOf(id));
            if(vcLevel!=null){             
              PubCompany parentCom=vcLevelService.findPubCompanyByCode(vcLevel.getParentOrgCode());
              if(parentCom!=null){
               vcLevel.setParentOrgName(parentCom.getCompanyCname());
              }
            }else{
                throw new BusinessException("当前人员不存在，请刷新后再试！ "); 
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
     * 岗位人员机构变更
     * 
     * @return
     * @throws BusinessException
     * @author whj
     * @date 2013-12-16
     */
    public String roleOrgChange() throws BusinessException {
        logger.info("访问/vclevel/roleOrgChange.do岗位人员机构变更...");
        try{
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if(userInfo==null){
                throw new BusinessException("登录超时，请重新登录！");
            }
            vcLevelService.executeRoleOrgChange(vcLevel,userInfo);
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
     * 进入使用人机构变更页
     * @return
     * @throws BusinessException
     * @author whj
     * @date 2013-12-16
     */
    public String initTakerOrgChange() throws BusinessException{
        logger.info("访问/vclevel/initTakerOrgChange.do进入使用人机构变更页...");
        try{
            String id = getRequest().getParameter("id");
            vcTaker = vcLevelService.findVcTakerById(Long.valueOf(id));
            if(vcTaker!=null){             
                PubCompany parentCom=vcLevelService.findPubCompanyByCode(vcTaker.getOrgCode());
                if(parentCom!=null){
                   vcTaker.setOrgName(parentCom.getCompanyCname());
                }
            }else{
                throw new BusinessException("当前人员不存在，请刷新后再试！ ");
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
     * 单证使用人机构变更
     * 
     * @return
     * @throws BusinessException
     * @author whj
     * @date 2013-12-16
     */
    public String takerOrgChange() throws BusinessException {
        logger.info("访问/vclevel/takerOrgChange.do单证使用人机构变更...");
        try{
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if(userInfo==null){
                throw new BusinessException("登录超时，请重新登录！");
            }
            vcLevelService.executeTakerOrgChange(vcTaker,userInfo);
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
     * 查询用户 下拉组件 自定义jsp
     * 
     * @return
     */
    public String queryUsersForSelector() throws BusinessException {
    	try{
	        String userCode = getRequest().getParameter("userCode");
	        String userName = getRequest().getParameter("userName");
	        UserVo userVo = new UserVo();
	        userVo.setUserCode(userCode);
	        userVo.setUserName(userName);
	        Page returnPage = vcLevelService.queryUsersForSelector(userVo, page, rows);
	
	        users = (List<UserVo>) returnPage.getResult();
	
	        // 总页数
	        total = (int) returnPage.getTotalPageCount();
	        // 总记录数
	        records = (int) returnPage.getTotalCount();
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
    * 机构代码自动完成组件
    * @return
    * @author chy
    */
    public String queryJSONCompanyList() throws BusinessException {
    	logger.info("访问/vclevelJson/getJSONCompanyList.do...机构代码自动完成组件");
    	try{
    		//机构代码
    		String comCode = getRequest().getParameter("codeCode");
    		//机构类型：0-公司；1-部门
    		String comType = getRequest().getParameter("comType");
    		//上级机构代码
    		String upperComCode = getRequest().getParameter("upperComCode");
    		//机构属性 O机构、D部门、B销售网点
    		String comAttribute = "";
    		if("0".equals(comType)){
    			comAttribute = "O";
    		}else if("1".equals(comType)){
    			comAttribute = "D";
    		}
    		
    		//根据条件查询公共机构表
    		List<PubCompany> list = vcLevelService.getPubCompanyList(comAttribute, upperComCode, comCode);
    		
    		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
    		if(list!=null && list.size()>0){
    			Map<String, String> map = null;
    			for(PubCompany pubCompany : list){
    				 map = new HashMap<String, String>();
    				 map.put("lable", pubCompany.getCompanyCname());
    				 map.put("value", pubCompany.getCompanyCode());
    				 mapList.add(map);
    			}
    		}
    		this.mapList = mapList;
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
     * 提供人员代码自动完成组件数据
     * @return
     */
    public String queryJSONUserList() throws BusinessException {
    	try{
    		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
    		//用户代码
    		String userCode = getRequest().getParameter("codeCode");
    		//机构代码
    		String comCode = getRequest().getParameter("comCode");
    	    if(userInfo.getComCode().equals(comCode)&&userInfo.getComCode().length()!=2){
    	    	 throw new BusinessException("权限不足");
    	    }else{
    	    	List<PubUserDef> list = vcLevelService.getUserList(comCode,userCode);
        		
        		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        		if(list!=null && list.size()>0){
        			Map<String, String> map = null;
        			for(PubUserDef pubUserDef : list){
        				 map = new HashMap<String, String>();
        				 map.put("lable", pubUserDef.getUserName());
        				 //map.put("value", pubUserDef.getUserCode());
        				 map.put("value", pubUserDef.getEmployeeId());
        				 mapList.add(map);
        			}
        		}
        		this.mapList = mapList;
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
     * 提供销售渠道大类下拉组件数据
     * @return
     * @author chy
     */
    public String queryJSONBusiness() throws BusinessException {
    	logger.info("访问/vclevelJson/getJSONBusiness.do提供销售渠道大类下拉组件数据...");
    	try{
    		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
    		//获取所有有效的销售渠道大类基础代码
    		List<VcPubCode> vcPubCodeList = vcLevelService.getVcPubCodeList("BusinessCode");
    		if(vcPubCodeList!=null && vcPubCodeList.size()>0){
    			Map<String, String> map = null;
    			for(VcPubCode vcPubCode : vcPubCodeList){
    				map = new HashMap<String, String>();
    				map.put("value", vcPubCode.getCodeCode());
    				map.put("label", vcPubCode.getCodeCName());
    				mapList.add(map);
    			}
    		}
    		this.mapList = mapList;
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
     * 提供销售渠道小类下拉组件数据
     * @return
     * @author chy
     */
    public String queryJSONBusinessDetail () throws BusinessException {
    	logger.info("访问/vclevelJson/getJSONBusinessDetail.do提供销售渠道小类下拉组件数据...");
    	try{
    		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
    		//获取所有有效的销售渠道小类基础代码
    		List<VcPubCode> vcPubCodeList = vcLevelService.getVcPubCodeList("BusinessDetailCode");
    		if(vcPubCodeList!=null && vcPubCodeList.size()>0){
    			Map<String, String> map = null;
    			for(VcPubCode vcPubCode : vcPubCodeList){
    				map = new HashMap<String, String>();
    				map.put("value", vcPubCode.getCodeCode());
    				map.put("label", vcPubCode.getCodeCName());
    				//对应的大类代码
    				map.put("name", vcPubCode.getUpperCode());
    				mapList.add(map);
    			}
    		}
    		this.mapList = mapList;
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
     * 通用下拉组件
     * @return
     */
    public String queryJSONCodeList() throws BusinessException {
    	logger.info("访问/vclevelJson/getJSONCodeList.do...通用下拉组件");
    	try{
    		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
    		String codeType=getRequest().getParameter("codeType");
    		//获取所有有效的销售渠道大类基础代码
    		List<VcPubCode> vcPubCodeList = vcLevelService.getVcPubCodeList(codeType);
    		if(vcPubCodeList!=null && vcPubCodeList.size()>0){
    			Map<String, String> map = null;
    			for(VcPubCode vcPubCode : vcPubCodeList){
    				map = new HashMap<String, String>();
    				map.put("value", vcPubCode.getCodeCode());
    				map.put("label", vcPubCode.getCodeCName());
    				mapList.add(map);
    			}
    		}
    		this.mapList = mapList;
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
     * 提供所属中介机构下拉框数据
     * @return
     */
    public String queryJSONAgencyOrgList() throws BusinessException {
    	logger.info("访问/vclevelJson/getJSONAgencyOrgList.do...提供所属中介机构下拉框数据");
    	try{
    		String comCode = getRequest().getParameter("comCode");
    		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
    		List<VcAgencyOrg> list = vcLevelService.getVcAgencyOrgList(comCode);
    		if(list!=null && list.size()>0){
    			Map<String, String> map = null;
    			for(VcAgencyOrg vcAgencyOrgTmp : list){
    				map = new HashMap<String, String>();
    				map.put("value", vcAgencyOrgTmp.getAgencyOrgCode());
    				map.put("label", vcAgencyOrgTmp.getAgencyOrgName());
    				mapList.add(map);
    			}
    		}
    		this.mapList = mapList;
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
     * 使用人自动完成
     * @return
     * @throws BusinessException
     */
    public String queryJSONTakerList() throws BusinessException{
    	try{
    		//使用人代码
    		String takerCode = getRequest().getParameter("codeCode");
    		//机构代码
    		String comCode = getRequest().getParameter("comCode");
    		List<PubUserDef> list = vcLevelService.getTakerList(comCode,takerCode);
    		
    		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
    		if(list!=null && list.size()>0){
    			Map<String, String> map = null;
    			for(PubUserDef pubUserDef : list){
    				 map = new HashMap<String, String>();
    				 map.put("lable", pubUserDef.getUserName());
    				 //map.put("value", pubUserDef.getUserCode());
    				 map.put("value", pubUserDef.getEmployeeId());
    				 mapList.add(map);
    			}
    		}
    		this.mapList = mapList;
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

    public String initUsersForSelector() {
        return SUCCESS;
    }

    public void setVcLevelService(VcLevelService vcLevelService) {
        this.vcLevelService = vcLevelService;
    }
    
    

    public void setVcTakerService(VcTakerService vcTakerService) {
        this.vcTakerService = vcTakerService;
    }

    public List<DTreeDto> getTreeList() {
        return treeList;
    }

    public void setTreeList(List<DTreeDto> treeList) {
        this.treeList = treeList;
    }

    public String getTagCodeId() {
        return tagCodeId;
    }

    public void setTagCodeId(String tagCodeId) {
        this.tagCodeId = tagCodeId;
    }

    public String getTagNameId() {
        return tagNameId;
    }

    public void setTagNameId(String tagNameId) {
        this.tagNameId = tagNameId;
    }

    public boolean isSingleSelect() {
        return singleSelect;
    }

    public void setSingleSelect(boolean singleSelect) {
        this.singleSelect = singleSelect;
    }

    public List getVcLevelList() {
        return vcLevelList;
    }

    public void setVcLevelList(List vcLevelList) {
        this.vcLevelList = vcLevelList;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public VcLevel getVcLevel() {
        return vcLevel;
    }

    public String getJsonResultList() {
        return jsonResultList;
    }

    public void setJsonResultList(String jsonResultList) {
        this.jsonResultList = jsonResultList;
    }

    /**
     * @return the zTreeList
     */
    public List<ZTreeDto> getzTreeList() {
        return zTreeList;
    }

    /**
     * @param zTreeList the zTreeList to set
     */
    public void setzTreeList(List<ZTreeDto> zTreeList) {
        this.zTreeList = zTreeList;
    }

    public void setVcLevel(VcLevel vcLevel) {
        this.vcLevel = vcLevel;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getEditType() {
        return editType;
    }

    public void setEditType(String editType) {
        this.editType = editType;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public List<UserVo> getUsers() {
        return users;
    }

    public void setUsers(List<UserVo> users) {
        this.users = users;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Map<String, String>> getMapList() {
		return mapList;
	}

	public void setMapList(List<Map<String, String>> mapList) {
		this.mapList = mapList;
	}

	public List getVcAgencyOrgList() {
		return vcAgencyOrgList;
	}

	public void setVcAgencyOrgList(List vcAgencyOrgList) {
		this.vcAgencyOrgList = vcAgencyOrgList;
	}

	public List getVcTakerList() {
		return vcTakerList;
	}

	public void setVcTakerList(List vcTakerList) {
		this.vcTakerList = vcTakerList;
	}

	public VcAgencyOrg getVcAgencyOrg() {
		return vcAgencyOrg;
	}

	public void setVcAgencyOrg(VcAgencyOrg vcAgencyOrg) {
		this.vcAgencyOrg = vcAgencyOrg;
	}

	public VcTaker getVcTaker() {
		return vcTaker;
	}

	public void setVcTaker(VcTaker vcTaker) {
		this.vcTaker = vcTaker;
	}

    /**
     * @return the root
     */
    public String getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(String root) {
        this.root = root;
    }

	public String getOnlyOrgFlag() {
		return onlyOrgFlag;
	}

	public void setOnlyOrgFlag(String onlyOrgFlag) {
		this.onlyOrgFlag = onlyOrgFlag;
	}

    /**
     * @return the resultMap
     */
    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    /**
     * @param resultMap the resultMap to set
     */
    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

	public String getPageTableName() {
		return pageTableName;
	}

	public void setPageTableName(String pageTableName) {
		this.pageTableName = pageTableName;
	}
	
	
}
