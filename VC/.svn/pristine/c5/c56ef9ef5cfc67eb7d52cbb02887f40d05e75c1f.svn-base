package com.tapi.tcs.vc.pub.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.tf.platform.schema.vo.CodeLabelDto;
import com.tapi.tcs.vc.baseinfo.baseconst.BaseInfoConst;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.pub.service.BankManagerService;
import com.tapi.tcs.vc.pub.service.PubRiskService;
import com.tapi.tcs.vc.schema.model.PubBanklocations;
import com.tapi.tcs.vc.schema.model.PubBanks;
import com.tapi.tcs.vc.schema.model.PubRisk;
import com.tapi.tcs.vc.schema.model.PubRiskClass;
import com.tapi.tcs.vc.schema.model.PubStandardareas;
import com.tapi.tcs.vc.schema.model.VcDocInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;

/**
 *险类险种Action
 * <p>
 * Date: 2014-03-20
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * @author wanghuajian
 * @version 1.0
 */
public class PubRiskAction extends TFAction {    
   
    private static final long serialVersionUID = -7444203410041568321L;


    /**
     * service
     */
    private PubRiskService pubRiskService;
    
    private List<Map<String, String>> mapList;
    
    
    public List<Map<String, String>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, String>> mapList) {
        this.mapList = mapList;
    }

    public void setPubRiskService(PubRiskService pubRiskService) {
        this.pubRiskService = pubRiskService;
    }   
   
    

    /**
     * 险种自动完成组件、下拉框数据组装
     * @return
     * @throws BusinessException
     *@author whj
     *@since Mar 20, 2014
     */
    public String queryJsonPubRiskList() throws BusinessException {
        try{
            // 获取用户机构代码
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");   
            if (userInfo == null) {
                throw new Exception("登录超时，请重新登录！ ");
            }
            String codeValue = getRequest().getParameter("codeCode");//自动完成组件输入值
           // 组件样式 select-下拉列表 auto-自动完成组件
            String style = getRequest().getParameter("style");
            // 是否级联
            String trigger = getRequest().getParameter("trigger");
            List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
            String lableName = "label";
            boolean isNotAuto=true;
            if (StringUtils.isNotEmpty(style) && "auto".equals(style)) {
                lableName = "lable";
                isNotAuto=false;
            }            
            PubRisk pubRiskVo=new PubRisk();
            if(StringUtils.isNotBlank(codeValue)){
                pubRiskVo.setRiskCodeLike(codeValue)  ;
            }
            List<PubRisk> pubRiskList = pubRiskService.queryPubRiskList(pubRiskVo);
    
            if (pubRiskList != null && pubRiskList.size() > 0) {
                Map<String, String> map = null;
                for (Iterator<PubRisk> item = pubRiskList.iterator(); item.hasNext();) {
                    PubRisk risk = item.next();
                    map = new HashMap<String, String>();
                    if(isNotAuto){
                      map.put(lableName, risk.getRiskCode()+"_"+risk.getRiskCname());
                    }else{
                      map.put(lableName, risk.getRiskCname());  
                    }
                    map.put("value", risk.getRiskCode());
                    
                    // 有级联
                    if (!(StringUtils.isNotEmpty(trigger) && "no".equals(trigger))) {
                        map.put("name", risk.getRiskClass());
                    }
                    mapList.add(map);
                    map = null;
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
     * 险类自动完成组件、下拉框数据组装
     * @return
     * @throws BusinessException
     *@author whj
     *@since Mar 20, 2014
     */
    public String queryJsonPubRiskClassList() throws BusinessException {
        try{
            // 获取用户机构代码
            UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo"); 
            if (userInfo == null) {
                throw new Exception("登录超时，请重新登录！ ");
            }
            String codeValue = getRequest().getParameter("codeCode");//自动完成组件输入值
           // 组件样式 select-下拉列表 auto-自动完成组件
            String style = getRequest().getParameter("style");            
            List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
            String lableName = "label";
            boolean isNotAuto=true;
            if (StringUtils.isNotEmpty(style) && "auto".equals(style)) {
                lableName = "lable";
                isNotAuto=false;
            }            
            PubRiskClass pubRiskClassVo=new PubRiskClass();
            if(StringUtils.isNotBlank(codeValue)){
                pubRiskClassVo.setRiskClassLike(codeValue)  ;
            }
            List<PubRiskClass> pubRiskClassList = pubRiskService.queryPubRiskClassList(pubRiskClassVo);
    
            if (pubRiskClassList != null && pubRiskClassList.size() > 0) {
                Map<String, String> map = null;
                for (Iterator<PubRiskClass> item = pubRiskClassList.iterator(); item.hasNext();) {
                    PubRiskClass riskClass = item.next();
                    map = new HashMap<String, String>();
                    if(isNotAuto){
                       map.put(lableName, riskClass.getRiskClass()+"_"+riskClass.getClassCname());
                    }else{
                       map.put(lableName, riskClass.getClassCname());  
                    }
                    map.put("value", riskClass.getRiskClass());
                    mapList.add(map);
                    map = null;
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
    
    
   
}
