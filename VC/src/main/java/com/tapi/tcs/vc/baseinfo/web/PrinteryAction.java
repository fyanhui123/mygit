/**
 * 
 */
package com.tapi.tcs.vc.baseinfo.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.tapi.tcs.vc.schema.model.VcDocInStore;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcPrintDocVersion;
import com.tapi.tcs.vc.schema.model.VcPrintery;
import com.tapi.tcs.vc.baseinfo.service.VcDocVersionInfoService;
import com.tapi.tcs.vc.baseinfo.service.VcPrinteryService;
import com.tapi.tcs.vc.baseinfo.vo.DocVersionInfoQueryVo;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.SysConst;

import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.tf.common.vo.Message;
import com.tapi.tcs.tf.platform.schema.vo.CodeLabelDto;

/**
 * 单证基础信息维护Action
 * <p>
 * Date: 2013-02-19
 * </p>
 * *
 * <p>
 * Description:
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * 单证基础信息维护control
 * 
 * @author wanghuajian
 * @version 1.0
 */
public class PrinteryAction extends TFAction {
    private static final long serialVersionUID = -2248031194541049439L;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private VcPrinteryService vcPrinteryService;
    private VcDocVersionInfoService vcDocVersionInfoService;

    // 印刷厂信息
    private VcPrintery printeryDto;
    private List printeryList;
    private Long idVcPrintery;
    private String printeryCode;// 单证种类代码
    private String printeryName;// 单证种类名称
    private String status;// 单证种类名称

    // 单证承印信息
    private List<VcPrintDocVersion> printDocVersionList;
    // 单证单证list
    private List<DocVersionInfoQueryVo> docVersionInfoQueryVoList;

    /** json数据 */
    private String jsonData;

    private List<Map<String, String>> mapList;

    private String selectPrinteryIds; // 列表勾选的记录ids

    private String actionType;// 当前业务类型 [insert,view,update,delete]

    private List<CodeLabelDto> codeLabelList;

    /** 上传的文件 */
    private File file;
    /** 文件名称 */
    private String fileFileName;
    /** 文件类型 */
    private String fileContentType;

    private String resultMsg;
    /**
     * 是否发生错误
     */
    private boolean hasError;

    public void setVcPrinteryService(VcPrinteryService vcPrinteryService) {
        this.vcPrinteryService = vcPrinteryService;
    }

    public void setVcDocVersionInfoService(VcDocVersionInfoService vcDocVersionInfoService) {
        this.vcDocVersionInfoService = vcDocVersionInfoService;
    }

    // 印刷厂维护==================================================
    /**
     * 初始化进入新增印刷厂页面
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String initAddPrintery() {
        this.actionType = "insert";
        return "initAddPrintery";
    }

    /**
     * 保存新增印刷厂
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String savePrintery() throws Exception {
        logger.info("访问/visabaseinfojson/savePrintery.do保存新增印刷厂信息...");
        Message message = new Message();
        this.actionType = "SaveAfter";

        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        if (userInfo == null) {
            throw new Exception("登录超时，请重新登录！ ");
        }
        String realPath = SysConst.PRINTERY_CONTRACT_SAVE_PATH; // 原文件名
        File savefile = null;
        try {
            if (file != null) {
                if (!fileContentType.equals("image/x-png") && !fileContentType.equals("image/pjpeg")
                        && !fileContentType.equals("image/jpeg") && !fileContentType.equals("image/png")) {
                    throw new BusinessException("请上传jpg或png格式的图片 ");
                }
                if (file.length() > SysConst.MB * 2) {
                    throw new BusinessException("文件不可超过2M! ");
                }
                savefile = new File(new File(realPath), fileFileName);
                if (savefile.exists()) {
                    throw new BusinessException("文件已存在! ");
                }
                // 扫描件名称
                printeryDto.setFileName(fileFileName); // 扫描件路径
                printeryDto.setFilePath(realPath);
            }
            printDocVersionList = TFJSON.parseArray(jsonData, VcPrintDocVersion.class);
            printeryDto.setPrintDocVersionList(printDocVersionList);

            vcPrinteryService.createPrintery(printeryDto, userInfo, file);
            message.setType("success");
            message.setMessage("保存成功!");
            this.actionType = "SaveAfter";
            this.resultMsg = "保存成功";
            return SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            message.setType("error");
            message.setMessage(e.getMessage());
            this.actionType = "insert";
            this.hasError = true;
            this.resultMsg = "操作失败，失败原因：" + e.getMessage();
            return "fail";
        }

        /*
         * if ("success".equals(message.getType())) { this.actionType = "SaveAfter"; return SUCCESS; } else {
         * this.actionType = "insert"; return "fail"; }
         */

    }

    /**
     * 进入查询印刷厂页面
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String initQueryPrintery() {
        return "initQueryPrintery";
    }

    /**
     * 查询印刷厂
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String queryPrinteryByPages() throws BusinessException {
        logger.info("访问/visabaseinfojson/queryPrinteryByPages.do?ajax=true分页查询印刷厂信息...");
        VcPrintery queryDto = new VcPrintery();
        queryDto.setPrinteryCode(printeryCode);
        queryDto.setPrinteryName(printeryName);
        queryDto.setStatus(status);
        try {
            Page pageObj = vcPrinteryService.queryPrinteryByPages(queryDto, page, rows);

            // 设置相关数据
            printeryList = pageObj.getResult();
            total = (int) pageObj.getTotalPageCount();
            records = (int) pageObj.getTotalCount();
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;

    }

    /**
     * 编辑印刷厂
     */
    public String prepareUpdatePrintery() throws Exception {
        logger.info("访问/visabaseinfo/prepareUpdatePrintery.do?ajax=true进入印刷厂编辑页面...");
        try {
            this.printeryDto = vcPrinteryService.getPrintery(this.idVcPrintery);
            this.printDocVersionList = this.printeryDto.getPrintDocVersionList();
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        this.actionType = "update";
        return "prepareUpdatePrintery";
    }

    /**
     * 进入修改印刷厂页面
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String updatePrintery() throws Exception {
        logger.info("访问/visabaseinfojson/updatePrintery.do修改印刷厂信息...");
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        if (userInfo == null) {
            throw new Exception("登录超时，请重新登录！ ");
        }
        String realPath = SysConst.PRINTERY_CONTRACT_SAVE_PATH;

        try {
            if (file != null) {
                if (!fileContentType.equals("image/x-png") && !fileContentType.equals("image/pjpeg")) {
                    throw new BusinessException("请上传jpg或png格式的图片 ");
                }
                if (file.length() > SysConst.MB * 2) {
                    throw new BusinessException("文件不可超过2M");
                }
                File savefile = new File(new File(realPath), fileFileName);
                if (savefile.exists()) {
                    throw new BusinessException("文件已存在! ");
                }
                // 扫描件名称
                printeryDto.setFileName(fileFileName);
                // 扫描件路径
                printeryDto.setFilePath(realPath);
            }

            printDocVersionList = TFJSON.parseArray(jsonData, VcPrintDocVersion.class);
            printeryDto.setPrintDocVersionList(printDocVersionList);

            vcPrinteryService.updatePrintery(printeryDto, userInfo, file);
            this.resultMsg = "保存成功";
            this.actionType = "SaveAfter";
            return SUCCESS;

        } catch (Exception e) {
            VcPrintery tempVo = vcPrinteryService.getPrintery(printeryDto.getIdVcPrintery());
            printeryDto.setPrinteryCode(tempVo.getPrinteryCode());
            e.printStackTrace();
            this.resultMsg = "操作失败,失败原因:" + e.getMessage();
            this.actionType = "update";
            this.hasError = true;
            return "fail";
        }

    }

    /**
     * 查看印刷厂
     */
    public String viewPrintery() throws Exception {
        logger.info("访问/visabaseinfo/viewPrintery.do进入印刷厂查看页面...");
        try {
            this.printeryDto = vcPrinteryService.getPrintery(this.idVcPrintery);
            this.printDocVersionList = this.printeryDto.getPrintDocVersionList();

            // 单证承印信息
            List<DocVersionInfoQueryVo> tempList = new ArrayList<DocVersionInfoQueryVo>();
            if (printDocVersionList != null && printDocVersionList.size() > 0) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                List<VcDocVersionInfo> list = null;
                DocVersionInfoQueryVo tempDocVo = null;

                for (VcPrintDocVersion tempVo : printDocVersionList) {
                    tempDocVo = new DocVersionInfoQueryVo();
                    tempDocVo.setDocVerCode(tempVo.getDocVerCode());
                    tempDocVo.setUnitPrice(tempVo.getUnitPrice());
                    if (tempVo.getDocVerCode() != null) {
                        paramMap.put("docVerCode", tempVo.getDocVerCode());
                        // paramMap.put("status", "1");
                        list = vcDocVersionInfoService.getDocVersionInfoList(paramMap);
                        if (list != null && list.size() > 0) {
                            // 设置单证类型名字
                            tempDocVo.setDocVerName((list.get(0)).getDocVerName());
                        }
                    }
                    tempList.add(tempDocVo);
                }
            }
            this.docVersionInfoQueryVoList = tempList;
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        this.actionType = "view";
        return "viewPrintery";
    }

    /**
     * 删除印刷厂
     * 
     * @author wanghuajian
     * @date 2013-03-11
     * 
     */
    public String deleteOrUnDeletePrintery() throws BusinessException {
        try {
            this.resultMsg = vcPrinteryService.deleteOrUnDeletePrintery(this.getSelectPrinteryIds());
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

    /**
     * @return 查询印刷厂承印信息
     * @throws BusinessException
     * @author wanghuajian since 2013-4-13下午01:36:22
     */
    public String queryJsonPrtDocVersionListByPrinteryId() throws BusinessException {
        if (this.getIdVcPrintery() != null) {
            try {
                printDocVersionList = vcPrinteryService.queryJsonPrtDocVersionListByPrinteryId(this
                        .getIdVcPrintery());
            } catch (BusinessException be) {
                this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
                return NONE;
            } catch (Exception e) {
                this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
                return NONE;
            }
        }
        return SUCCESS;

    }

    /**
     * 印刷厂下拉组件
     * 
     * @return
     */
    public String printeryJsonListWithCondition() {
        String docVerCode = getRequest().getParameter("docVerCode");

        mapList = new ArrayList<Map<String, String>>();
        Map params = new HashMap<String, String>();
        params.put("docVerCode", docVerCode);
        try {
            List<VcPrintery> vcPrinterys = vcPrinteryService.queryPrintery(params);

            for (Iterator iterator = vcPrinterys.iterator(); iterator.hasNext();) {
                VcPrintery vcPrintery = (VcPrintery) iterator.next();
                Map<String, String> map = new HashMap<String, String>();
                map.put("label", vcPrintery.getPrinteryName());
                map.put("value", vcPrintery.getPrinteryCode());
                mapList.add(map);
            }
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

    public String findUnitPrice() {
        String printeryCode = getRequest().getParameter("printeryCode");
        String docVerCode = getRequest().getParameter("docVerCode");
        try {
            jsonData = vcPrinteryService.queryDocUnitPrice(printeryCode, docVerCode);
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

    /**
     * 初始化印刷厂下拉框
     * 
     * @return
     * @author chy
     * @date 2013-05-20
     */
    public String getJSONPrinteryList() throws Exception {
        logger.info("访问/baseinfoJson/getJSONPrinteryList.do初始化印刷厂下拉框...");
        try {
            List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();

            /*
             * Map<String, String> map01 = new HashMap<String, String>(); map01.put("label", "-请选择-");
             * map01.put("value", ""); mapList.add(map01);
             */

            List<VcPrintery> vcPrinteryList = vcPrinteryService.findVcPrinteryList();

            for (VcPrintery vcPrintery : vcPrinteryList) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("label", vcPrintery.getPrinteryName());
                map.put("value", vcPrintery.getIdVcPrintery() + "");
                mapList.add(map);
            }
            this.mapList = mapList;
        } catch (BusinessException be) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
            return NONE;
        } catch (Exception e) {
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
            return NONE;
        }
        return SUCCESS;
    }

    public VcPrintery getPrinteryDto() {
        return printeryDto;
    }

    public void setPrinteryDto(VcPrintery printeryDto) {
        this.printeryDto = printeryDto;
    }

    public List getPrinteryList() {
        return printeryList;
    }

    public void setPrinteryList(List printeryList) {
        this.printeryList = printeryList;
    }

    public Long getIdVcPrintery() {
        return idVcPrintery;
    }

    public void setIdVcPrintery(Long idVcPrintery) {
        this.idVcPrintery = idVcPrintery;
    }

    public String getPrinteryCode() {
        return printeryCode;
    }

    public void setPrinteryCode(String printeryCode) {
        this.printeryCode = printeryCode;
    }

    public String getPrinteryName() {
        return printeryName;
    }

    public void setPrinteryName(String printeryName) {
        this.printeryName = printeryName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<VcPrintDocVersion> getPrintDocVersionList() {
        return printDocVersionList;
    }

    public void setPrintDocVersionList(List<VcPrintDocVersion> printDocVersionList) {
        this.printDocVersionList = printDocVersionList;
    }

    public List<DocVersionInfoQueryVo> getDocVersionInfoQueryVoList() {
        return docVersionInfoQueryVoList;
    }

    public void setDocVersionInfoQueryVoList(List<DocVersionInfoQueryVo> docVersionInfoQueryVoList) {
        this.docVersionInfoQueryVoList = docVersionInfoQueryVoList;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getSelectPrinteryIds() {
        return selectPrinteryIds;
    }

    public void setSelectPrinteryIds(String selectPrinteryIds) {
        this.selectPrinteryIds = selectPrinteryIds;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public List<CodeLabelDto> getCodeLabelList() {
        return codeLabelList;
    }

    public void setCodeLabelList(List<CodeLabelDto> codeLabelList) {
        this.codeLabelList = codeLabelList;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    /**
     * @return the hasError
     */
    public boolean isHasError() {
        return hasError;
    }

    /**
     * @param hasError
     *            the hasError to set
     */
    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public List<Map<String, String>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, String>> mapList) {
        this.mapList = mapList;
    }
}