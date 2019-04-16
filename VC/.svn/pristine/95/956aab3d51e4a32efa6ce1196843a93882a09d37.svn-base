package com.tapi.tcs.vc.insucard.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.baseconst.BaseInfoConst;
import com.tapi.tcs.vc.baseinfo.service.VcDocVersionInfoService;
import com.tapi.tcs.vc.baseinfo.vo.DocVersionInfoQueryVo;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.insucard.service.InsuCardImportService;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcInsuCardImportInfo;
import com.tapi.tcs.vc.schema.model.VcInsuranceCard;

/**
 * 
 * @author whj
 * 
 */
public class InsuCardImportAction extends TFAction {
    /**
     * 
     */
    private static final long serialVersionUID = 6898854229412886748L;

    /**
     * 激活卡管理service
     */
    private InsuCardImportService insuCardImportService;

    /**
     * 单证类型service
     */
    private VcDocVersionInfoService vcDocVersionInfoService;

    /**
     * 激活卡管理service
     * 
     * @param insuCardImportService
     *            激活卡管理service
     */
    public void setInsuCardImportService(InsuCardImportService insuCardImportService) {
        this.insuCardImportService = insuCardImportService;
    }

    /**
     * 单证类型service
     * 
     * @param vcDocVersionInfoService
     *            单证类型service
     */
    public void setVcDocVersionInfoService(VcDocVersionInfoService vcDocVersionInfoService) {
        this.vcDocVersionInfoService = vcDocVersionInfoService;
    }

    // *********************************************************************************************//

    /** 单证版本信息 */
    private VcDocVersionInfo docVersionInfoDto;

    /** 单证类型流水 */
    private Long idVcDocVersionInfo;

    /** 单证类型代码 */
    private String docVerCode;

    /**
     * 查询用单证版本信息
     */
    private DocVersionInfoQueryVo queryDto;

    private VcInsuCardImportInfo checkedReturnVo;

    /**
     * excel校验返回数据信息
     */
    private List<VcInsuCardImportInfo> checkedReturnVoList;

    /**
     * 查询结果list
     */
    List<VcDocVersionInfo> resultList;

    /** 上传的文件 */
    private File file;
    /** 文件名称 */
    private String fileFileName;
    /** 文件类型 */
    private String fileContentType;

    /**
     * 进入激活卡查询页面
     * 
     * @return String
     * @author wanghuajian
     * @date 2013-06-14
     */
    public String initInsuCardQuery() {
        return SUCCESS;
    }

    /**
     * 进入导入页面
     * 
     * @return String
     * @author wanghuajian
     * @date 2013-06-17
     * 
     */
    public String initImport() throws BusinessException {
        this.docVersionInfoDto = vcDocVersionInfoService.getVcDocVersionInfo(this.idVcDocVersionInfo);
        return SUCCESS;
    }

    /**
     * 查询激活卡类单证
     * 
     * @return String
     * @throws BusinessException
     *             异常
     * @author wanghuajian
     * @date 2013-06-14
     * 
     */
    public String queryInsuCard() throws BusinessException {
        logger.info("访问/insucard/queryInsuCard.do?ajax=true分页查询激活卡类单证...");
        // 激活卡单证种类
        queryDto.setDocTypeCode(BaseInfoConst.DOC_TYPE_ACTIVATE_CARD);
        queryDto.setStatus("1");
        Page pageObj = vcDocVersionInfoService.queryVcDocVersionInfoByPages(this.getQueryDto(), page, rows);
        // 设置相关数据
        resultList = (List<VcDocVersionInfo>) pageObj.getResult();
        total = (int) pageObj.getTotalPageCount();
        records = (int) pageObj.getTotalCount();
        return SUCCESS;

    }

    // //----------------------------
    /**
     * 文件上传处理
     * 
     * @param file
     *            待上传文件
     * @param fileContentType
     *            类型
     * @return
     */
    public Map<String, Object> checkFile(File file, String fileContentType) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 处理文件过程是否有异常 yes-有异常 no-无异常
        boolean hasError = false;
        String errorMsg = "";
        if (file != null) {
            // aplication/vnd.openxmlformats-officedocument.spreadsheetml.sheet excel2007
            // application/vnd.ms-excel excel2003
            //金山WPS: application/octet-stream
            if (!fileContentType.equals("application/vnd.ms-excel")&&!fileContentType.equals("application/octet-stream")) {
                hasError = true;
                errorMsg = "上传文档格式不是Excel2003/WPS(.xls)";
            }

            if (file.length() > SysConst.MB * 10) {
                hasError = true;
                errorMsg = "文件超过10M";
            }
        } else {
            hasError = true;
            errorMsg = "文件不存在!";
        }
        resultMap.put("hasError", hasError);
        resultMap.put("errorMsg", errorMsg);
        return resultMap;
    }

    /**
     * excl文件上传
     * 
     * @return String
     * @throws BusinessException
     *             异常
     */
    public String upLoadInsuranceCard() throws BusinessException {
        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
        if (userInfo == null) {
            throw new BusinessException("登录超时，请重新登录！ ");
        }

        // 导入信息表
        VcInsuCardImportInfo vcInsuCardImportInfo = new VcInsuCardImportInfo();
        checkedReturnVoList = new ArrayList<VcInsuCardImportInfo>();
        // 当前时间
        Date curDate = new Date();
        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;
        HSSFRow row = null;
        HSSFCell cell = null;
        
        try {
            // 文件格式、大小校验
            Map<String, Object> dealResultMap = this.checkFile(file, fileContentType);
            if ((Boolean) dealResultMap.get("hasError")) {
                throw new BusinessException((String) dealResultMap.get("errorMsg"));
            }

            try {
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                // 打开HSSFWorkbook
                POIFSFileSystem fs = new POIFSFileSystem(in);
                // 创建对Excel工作簿文件的引用
                workbook = new HSSFWorkbook(fs);
            } catch (Exception e) {
                throw new BusinessException("Excell文件读取错误！ ");
            }
        } catch (Exception e) {
            vcInsuCardImportInfo.setImportResult(false);
            vcInsuCardImportInfo.setImportResultDesc("导入失败");
            vcInsuCardImportInfo.setImportResultMsg(e.getMessage());
            checkedReturnVo = vcInsuCardImportInfo;
            checkedReturnVoList.add(checkedReturnVo);
            return SUCCESS;
        }

        // 创建对工作表的引用。
        // 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）
        // sheet = workbook.getSheet("Sheet1");
        sheet = workbook.getSheetAt(0);

        // 行数(从0开始,相当于最后一行的索引),列数
        int count_row = sheet.getLastRowNum();
        if (count_row < 1) {
           /* vcInsuCardImportInfo.setStartCardNo("0");
            vcInsuCardImportInfo.setEndCardNo("0");
            vcInsuCardImportInfo.setImportNum(0L);*/
            vcInsuCardImportInfo.setImportResult(false);
            vcInsuCardImportInfo.setImportResultDesc("导入失败");
            vcInsuCardImportInfo.setImportResultMsg("Excel文档没有记录！ ");
            checkedReturnVo = vcInsuCardImportInfo;
            checkedReturnVoList.add(checkedReturnVo);
            return SUCCESS;
        }
        // int count_cell = sheet.getRow(0).getPhysicalNumberOfCells();
        int count_cell = 5; // excel文件的读取列
        HSSFRow zeroRow = sheet.getRow(0); // 拿到第零行表格，解析列与字段之间的对应关系

        /*
         * Method[] methods = VcInsuranceCard.class.getMethods(); Map<Integer,Method> colMark = new HashMap<Integer,
         * Method>(); //存储方法与字段的对应关系 for (int i = 0; i < zeroRow.getLastCellNum(); i++) { HSSFCell tip =
         * zeroRow.getCell(i); //标题 if (null != tip && tip.getCellType() == HSSFCell.CELL_TYPE_STRING) { String
         * curTipName = tip.getStringCellValue(); for (int j = 0; j < methods.length; j++) { if (("set" +
         * curTipName.toUpperCase().charAt(0) + curTipName.substring(1)) .equals(methods[j].getName())) {
         * colMark.put((int) i, methods[j]); break; } } } }
         */

        List<VcInsuranceCard> list = new ArrayList<VcInsuranceCard>();
        // 数据含有空值,无效数据
        List<VcInsuranceCard> badRecordlist = new ArrayList<VcInsuranceCard>();

        VcInsuranceCard tempVo = null;
        String titleName = null;
        Long maxNo = -9999999999L;
        Long minNo = 9999999999L;
        Long tempNo = 0L;
        String strCarNum;        
        HSSFCell tempcell = null;

        boolean isBadRecord = false;
        for (int i = 1; i <= count_row; i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            // 5列值有一个为空，进入下次循环
            if (row.getCell(0) == null || StringUtils.isBlank(row.getCell(0).getStringCellValue()) // 卡号
                    // || row.getCell(1) == null || StringUtils.isBlank(row.getCell(1).getStringCellValue()) //密码
                    // || row.getCell(2) == null || StringUtils.isBlank(row.getCell(2).getStringCellValue()) //md5
                    //|| row.getCell(3) == null || (row.getCell(3).getDateCellValue())==null  // 有效期
                    //|| row.getCell(4) == null || (row.getCell(4).getNumericCellValue())< 0 // 面值
            ) {
                continue;
            }
            isBadRecord = false;
            tempVo = new VcInsuranceCard();
            for (int j = 0; j < count_cell; j++) {
                cell = row.getCell(j);
               if (cell != null ) {
                    titleName = zeroRow.getCell(j).getStringCellValue();
                    if ("卡号".equals(titleName) ) {
                        strCarNum=cell.getStringCellValue();
                        if(StringUtils.isBlank(strCarNum)||!strCarNum.matches("[0-9]*")){//空或包含数字
                            isBadRecord = true; 
                            j=count_cell; //结束内循环
                         }else{
                            tempNo = Long.valueOf(cell.getStringCellValue());
                            // 获取最大最小卡号
                            if (tempNo > maxNo) {
                                maxNo = tempNo;
                            }
                            if (tempNo < minNo) {
                                minNo = tempNo;
                            }
                            tempVo.setCardNo(cell.getStringCellValue());
                       }
                    } else if ("密码".equals(titleName)) {
                        if(StringUtils.isBlank(cell.getStringCellValue())){
                            isBadRecord = true; 
                            tempVo.setEncryptedPw("error");
                            j=count_cell; //结束内循环
                        }else{
                          tempVo.setPassword(cell.getStringCellValue());
                        }
                    } else if ("md5".equals(titleName)) {
                        if(StringUtils.isBlank(cell.getStringCellValue())){
                            isBadRecord = true; 
                            tempVo.setEncryptedPw("error");
                            j=count_cell; //结束内循环
                        }else{
                           tempVo.setEncryptedPw(cell.getStringCellValue());
                        }
                    } else if ("有效期".equals(titleName)) {
                        if(cell.getDateCellValue()==null){
                            isBadRecord = true; 
                            tempVo.setValidDate(new Date());
                            j=count_cell; //结束内循环
                        }else{
                           tempVo.setValidDate(cell.getDateCellValue());
                        }
                    } else if ("面值".equals(titleName)) {
                        if(cell.getNumericCellValue()<0){
                            isBadRecord = true; 
                            tempVo.setValue(0);
                            j=count_cell; //结束内循环
                        }else{
                           tempVo.setValue((float) cell.getNumericCellValue());
                        }
                    }
              }
            }
           
            tempVo.setDocVerCode(docVerCode);
           
            tempVo.setImportTime(new Date());
            tempVo.setCreatedBy(userInfo.getUserCode());
            tempVo.setUpdatedBy(userInfo.getUserCode());
            tempVo.setDateCreated(curDate);
            tempVo.setDateUpdated(curDate);
            tempVo.setVcInsuCardImportInfo(vcInsuCardImportInfo);
           
           
            if (isBadRecord) {// 无效数据
                tempVo.setCardStatus("-1");
                badRecordlist.add(tempVo);
            }else{
                tempVo.setCardStatus("0"); 
            }
            
            list.add(tempVo);
        }
        vcInsuCardImportInfo.setDocVerCode(docVerCode);

        vcInsuCardImportInfo.setVcInsuranceCardList(list);
        vcInsuCardImportInfo.setStartCardNo(String.valueOf(minNo));
        vcInsuCardImportInfo.setEndCardNo(String.valueOf(maxNo));
        vcInsuCardImportInfo.setImportNum(new Long(list.size()));
        //有错误的记录条数
        vcInsuCardImportInfo.setBadRecordNum(new Long(badRecordlist.size()));
        vcInsuCardImportInfo.setImportTime(curDate);
        vcInsuCardImportInfo.setOprCode(userInfo.getUserCode());
        vcInsuCardImportInfo.setCreatedBy(userInfo.getUserCode());
        vcInsuCardImportInfo.setUpdatedBy(userInfo.getUserCode());
        vcInsuCardImportInfo.setDateCreated(curDate);
        vcInsuCardImportInfo.setDateUpdated(curDate);

        boolean chechResult = true;
        // /校验
        StringBuffer sb = new StringBuffer();
        if ((maxNo - minNo + 1) != list.size()) {
            chechResult = false;
            sb.append("导入卡号不连续!\n");
        }
        Long count = insuCardImportService.queryVcInsuranceCardNumbers(vcInsuCardImportInfo.getStartCardNo(),
                vcInsuCardImportInfo.getEndCardNo(), vcInsuCardImportInfo.getDocVerCode());
        if (count > 0) {
            chechResult = false;
            sb.append("部分卡号已存在!\n");
        }
        if (badRecordlist.size() > 0) {
            chechResult = false;
            sb.append("部分记录信息不完整!\n");
        }
        vcInsuCardImportInfo.setImportResultMsg(sb.toString());

        if (chechResult) {
            // 校验通过
            insuCardImportService.saveInsuCardInfoAndInsuCard(vcInsuCardImportInfo, userInfo);
            vcInsuCardImportInfo.setImportResultDesc("导入成功");
        } else {
            vcInsuCardImportInfo.setImportResultDesc("导入失败");
        }
        vcInsuCardImportInfo.setImportResult(chechResult);
        checkedReturnVo = vcInsuCardImportInfo;
        checkedReturnVo.setVcInsuranceCardList(null);

        checkedReturnVoList.add(checkedReturnVo);

        // insuCardImportService.saveInsuCardInfoAndInsuCard(vcInsuCardImportInfo, userInfo);

        return SUCCESS;

    }

    public VcDocVersionInfo getDocVersionInfoDto() {
        return docVersionInfoDto;
    }

    public void setDocVersionInfoDto(VcDocVersionInfo docVersionInfoDto) {
        this.docVersionInfoDto = docVersionInfoDto;
    }

    public Long getIdVcDocVersionInfo() {
        return idVcDocVersionInfo;
    }

    public void setIdVcDocVersionInfo(Long idVcDocVersionInfo) {
        this.idVcDocVersionInfo = idVcDocVersionInfo;
    }

    /**
     * @return String
     * @return
     */
    public DocVersionInfoQueryVo getQueryDto() {
        return queryDto;
    }

    /**
     * 
     * @param queryDto
     */
    public void setQueryDto(DocVersionInfoQueryVo queryDto) {
        this.queryDto = queryDto;
    }

    public List<VcDocVersionInfo> getResultList() {
        return resultList;
    }

    public void setResultList(List<VcDocVersionInfo> resultList) {
        this.resultList = resultList;
    }

    /**
     * @return the checkedReturnVo
     */
    public VcInsuCardImportInfo getCheckedReturnVo() {
        return checkedReturnVo;
    }

    /**
     * @param checkedReturnVo
     *            the checkedReturnVo to set
     */
    public void setCheckedReturnVo(VcInsuCardImportInfo checkedReturnVo) {
        this.checkedReturnVo = checkedReturnVo;
    }

    /**
     * @return the checkedReturnVoList
     */
    public List<VcInsuCardImportInfo> getCheckedReturnVoList() {
        return checkedReturnVoList;
    }

    /**
     * @param checkedReturnVoList
     *            the checkedReturnVoList to set
     */
    public void setCheckedReturnVoList(List<VcInsuCardImportInfo> checkedReturnVoList) {
        this.checkedReturnVoList = checkedReturnVoList;
    }

    /**
     * @return the docVerCode
     */
    public String getDocVerCode() {
        return docVerCode;
    }

    /**
     * @param docVerCode
     *            the docVerCode to set
     */
    public void setDocVerCode(String docVerCode) {
        this.docVerCode = docVerCode;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file
     *            the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the fileFileName
     */
    public String getFileFileName() {
        return fileFileName;
    }

    /**
     * @param fileFileName
     *            the fileFileName to set
     */
    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    /**
     * @return the fileContentType
     */
    public String getFileContentType() {
        return fileContentType;
    }

    /**
     * @param fileContentType
     *            the fileContentType to set
     */
    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

}
