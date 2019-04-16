/**
 * 
 */
package com.tapi.tcs.vc.baseinfo.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.vc.schema.model.VcDocMngRule;
import com.tapi.tcs.vc.baseinfo.service.VcDocMngRuleService;
import com.tapi.tcs.vc.baseinfo.vo.VcDocMngRuleShowVo;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.inquiry.vo.DocDetailInquiryVo;

import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;

/**
 * 单证管理控制规则维护Action
 * <p>
 * Date: 2013-04-23
 * </p>
 * *
 * <p>
 * Description:
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * 单证管理控制规则维护
 * 
 * @author wanghuajian
 * @version 1.0
 */
public class DocMngRuleAction extends TFAction {
    /**   */
    private static final long serialVersionUID = 1741544214178351326L;

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    /** vcDocMngRuleService */
    private VcDocMngRuleService vcDocMngRuleService;

    /** 单证管理控制规则信息 */
    private VcDocMngRule vcDocMngRuleDto;

    /** 单证查询条件 */
    private DocDetailInquiryVo docQueryDto;
    /** 待保存的管控数据list */
    private List<VcDocMngRule> vcDocMngRuleList;
    /** 页面显示的管控数据list */
    private List<VcDocMngRuleShowVo> vcDocMngRuleShowVoList;

    /** json数据 */
    private String jsonData;

    /** 最大库存时间及库存量 */
    private int[] defaultMaxStoreAndStoreTime;

    /** 当前业务类型 [insert,view,update,delete] */
    private String actionType;

    /** 操作返回信息 */
    private String resultMsg;

    /**
     * 
     * @param vcVcDocMngRuleService
     *            管控service
     */
    public void setVcDocMngRuleService(VcDocMngRuleService vcVcDocMngRuleService) {
        this.vcDocMngRuleService = vcVcDocMngRuleService;
    }

    /**
     * 进入查询单证管理控制规则页面
     * 
     * @return String
     * @author wanghuajian
     * @date 2013-04-23
     * 
     */
    public String initQueryDoc() {
        return "initQueryDoc";
    }

    /**
     * 查询并设置默认信息
     * 
     * @return String
     * @author wanghuajian
     * @date 2013-04-23
     * 
     */
    public String queryListAndSetDefaultConfig() throws BusinessException {
        logger.info("访问/baseinfoJson/queryListAndSetDefaultConfig.do?ajax=true查询并设置默认信息	...");
        VcDocMngRule queryDto = new VcDocMngRule();

        try {
            // 查询并设置默认信息
            vcDocMngRuleShowVoList = vcDocMngRuleService.queryListAndSetDefaultConfig(docQueryDto,
                    vcDocMngRuleDto);
            rows = 1000;
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
     * 根据配置机构或人员level查询最大库存时间及库存量
     * 
     * @return String
     * @author wanghuajian
     * @date 2013-04-23
     * 
     */
    public String getMngDefaultDate() throws Exception {
        logger.info("访问/baseinfoJson/getMngDefaultDate.do?ajax=true根据配置机构或人员level查询最大库存时间及库存量 ...");
        try {
            String mngObjectCode = this.getRequest().getParameter("MngObjectCode");
            // 查询并设置默认信息
            defaultMaxStoreAndStoreTime = vcDocMngRuleService.getMngDefaultDate(mngObjectCode);
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
     * 保存单证管理控制规则List信息
     * 
     * @return String
     * @author wanghuajian
     * @date 2013-04-23
     * 
     */
    public String saveDocMngRuleList() throws BusinessException {
        logger.info("访问/baseinfoJson/saveDocRuleList.do?ajax=true保存单证管理控制信息	...");
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        try {
            if (userInfo == null) {
                throw new BusinessException("登录超时，请重新登录！ ");
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
            return NONE;
        }
        try {
            vcDocMngRuleList = TFJSON.parseArray(jsonData, VcDocMngRule.class);

            // 查询并设置默认信息
            vcDocMngRuleService.saveDocMngRuleList(docQueryDto, vcDocMngRuleDto, vcDocMngRuleList, userInfo);
            this.resultMsg = "操作成功";
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
     * 查询单证管理控制规则
     * 
     * @return String
     * @author wanghuajian
     * @date 2013-04-23
     * 
     */
    public String queryDocByPages() throws Exception {
        logger.info("访问/visabaseinfojson/queryVcDocMngRuleByPages.do?ajax=true查询单证管理控制规则设置信息...");
        VcDocMngRule queryDto = new VcDocMngRule();
        return SUCCESS;
    }

    /**
     * 初始化进入新增单证管理控制规则页面
     * 
     * @return String
     * @author wanghuajian
     * @date 2013-04-23
     * 
     */
    public String initAddVcDocMngRule() {
        this.actionType = "insert";
        return "initAddVcDocMngRule";
    }

    /**
     * 保存新增单证管理控制规则
     * 
     * @return String
     * @author wanghuajian
     * @date 2013-04-23
     * 
     */
    public String saveVcDocMngRule() throws BusinessException {
        vcDocMngRuleList = TFJSON.parseArray(jsonData, VcDocMngRule.class);
        // vcDocMngRuleDto.setPrintDocVersionList(VcDocMngRuleList);
       
        vcDocMngRuleService.createVcDocMngRule(vcDocMngRuleDto);
       
        return null;
    }

    /**
     * 编辑单证管理控制规则
     */
    public String prepareUpdateVcDocMngRule() throws Exception {
        logger.info("访问/visabaseinfo/prepareUpdateVcDocMngRule.do?ajax=true进入单证管理控制规则编辑页面...");

        this.actionType = "update";
        return "prepareUpdateVcDocMngRule";
    }

    /**
     * 进入修改单证管理控制规则页面
     * 
     * @return String
     * @author wanghuajian
     * @date 2013-04-23
     * 
     */
    public String updateVcDocMngRule() throws Exception {

        return "updateVcDocMngRule";
    }

    /**
     * 
     * 查看单证管理控制规则
     * 
     * @return String
     */
    public String viewVcDocMngRule() throws Exception {
        logger.info("访问/visabaseinfo/viewVcDocMngRule.do进入单证管理控制规则查看页面...");

        this.actionType = "view";
        return "viewVcDocMngRule";
    }

    /**
     * 删除单证管理控制规则
     * 
     * @return String
     * @author wanghuajian
     * @date 2013-04-23
     * 
     */
    public String deleteOrUnDeleteVcDocMngRule() throws BusinessException {
        // vcDocMngRuleService.deleteOrUnDeleteVcDocMngRule(this.getSelectVcDocMngRuleIds());
        return SUCCESS;
    }

    /**
     * @return 查询单证管理控制规则承印信息
     * @throws BusinessException
     * @author wanghuajian since 2013-4-13下午01:36:22
     */
    public String queryJsonPrtDocVersionListByVcDocMngRuleId() throws BusinessException {
        /*
         * if(this.getIdVcDocMngRule()!=null){ VcDocMngRuleList = vcDocMngRuleService
         * .queryJsonPrtDocVersionListByVcDocMngRuleId(this.getIdVcDocMngRule ()); }
         */
        return SUCCESS;

    }

    public VcDocMngRule getVcDocMngRuleDto() {
        return vcDocMngRuleDto;
    }

    public void setVcDocMngRuleDto(VcDocMngRule vcDocMngRuleDto) {
        this.vcDocMngRuleDto = vcDocMngRuleDto;
    }

    /**
     * @return the defaultMaxStoreAndStoreTime
     */
    public int[] getDefaultMaxStoreAndStoreTime() {
        return defaultMaxStoreAndStoreTime;
    }

    /**
     * @param defaultMaxStoreAndStoreTime
     *            the defaultMaxStoreAndStoreTime to set
     */
    public void setDefaultMaxStoreAndStoreTime(int[] defaultMaxStoreAndStoreTime) {
        this.defaultMaxStoreAndStoreTime = defaultMaxStoreAndStoreTime;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public DocDetailInquiryVo getDocQueryDto() {
        return docQueryDto;
    }

    public void setDocQueryDto(DocDetailInquiryVo docQueryDto) {
        this.docQueryDto = docQueryDto;
    }

    public List<VcDocMngRule> getVcDocMngRuleList() {
        return vcDocMngRuleList;
    }

    public void setVcDocMngRuleList(List<VcDocMngRule> vcDocMngRuleList) {
        this.vcDocMngRuleList = vcDocMngRuleList;
    }

    public List<VcDocMngRuleShowVo> getVcDocMngRuleShowVoList() {
        return vcDocMngRuleShowVoList;
    }

    public void setVcDocMngRuleShowVoList(List<VcDocMngRuleShowVo> vcDocMngRuleShowVoList) {
        this.vcDocMngRuleShowVoList = vcDocMngRuleShowVoList;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

}