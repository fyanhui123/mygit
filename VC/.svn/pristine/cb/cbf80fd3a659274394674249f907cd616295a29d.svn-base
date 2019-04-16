/**
 * 
 */
package com.tapi.tcs.vc.insucard.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.insucard.dao.VcInsuCardImportInfoDao;
import com.tapi.tcs.vc.insucard.dao.VcInsuranceCardDao;
import com.tapi.tcs.vc.insucard.service.InsuCardImportService;
import com.tapi.tcs.vc.schema.model.VcInsuCardImportInfo;
import com.tapi.tcs.vc.schema.model.VcInsuranceCard;

/**
 * 激活卡导入service
 * 
 * @author whj
 * 
 */
public class InsuCardImportServiceImpl implements InsuCardImportService {

    /**
     * 激活卡导入信息dao
     */    
    private  VcInsuCardImportInfoDao vcInsuCardImportInfoDao;
    
    /**
     * 激活卡信息dao
     */
    private  VcInsuranceCardDao vcInsuranceCardDao;
  

    /**
     * @param vcInsuCardImportInfoDao 激活卡导入信息dao
     */
    public void setVcInsuCardImportInfoDao(VcInsuCardImportInfoDao vcInsuCardImportInfoDao) {
        this.vcInsuCardImportInfoDao = vcInsuCardImportInfoDao;
    }

    /**
     * @param vcInsuranceCardDao 激活卡信息dao
     */
    public void setVcInsuranceCardDao(VcInsuranceCardDao vcInsuranceCardDao) {
        this.vcInsuranceCardDao = vcInsuranceCardDao;
    }

    /**
     * 根据ID获取去激活卡信息
     * 
     * @author wanghuajian
     * 
     * @param idVcInsuranceCard
     *            激活卡流水号
     * @return
     */
    public VcInsuranceCard getVcInsuranceCard(Long idVcInsuranceCard){
        return vcInsuranceCardDao.getVcInsuranceCard(idVcInsuranceCard);
    }
    
    /**
     * 根据ID获取激活卡导入信息
     * 
     * @author wanghuajian
     * 
     * @param idVcInsuCardImportInfo
     *            导入信息流水
     * @return VcInsuCardImportInfo
     */
    public VcInsuCardImportInfo getVcInsuCardImportInfo(Long idVcInsuCardImportInfo){
        return vcInsuCardImportInfoDao.getVcInsuCardImportInfo(idVcInsuCardImportInfo);
    }
   
    
    /**
     * 查询激活卡信息
     * 
     * @author wanghuanjian
     * 
     * @param vcInsuranceCard
     *            查询条件dto
     * @param currentPage
     *            当前页码
     * @param pageNumber
     *            页面条数
     * @return Page
     * @exception BusinessException
     *                异常
     */
    public Page queryVcInsuranceCardByPages(VcInsuranceCard vcInsuranceCard, int currentPage, int pageNumber)
            throws BusinessException{
        return vcInsuranceCardDao.queryVcInsuranceCardByPages(vcInsuranceCard, currentPage, pageNumber);
    }    
    
    
    
    
    /**
     * 根据卡号区间查询激活卡数目
     * @param startNo
     * @param endNo
     * @param docVerCode
     * @return Long
     @exception BusinessException
     *                异常
     */
    public Long queryVcInsuranceCardNumbers(String startNo, String endNo, String docVerCode)
            throws BusinessException{
        if(StringUtils.isBlank(startNo) || StringUtils.isBlank(endNo) || StringUtils.isBlank(docVerCode)){
            throw new BusinessException("查询条件不能有空值！ ");
        }
        return vcInsuranceCardDao.queryVcInsuranceCardNumbers(startNo, endNo, docVerCode);
    }

    /**
     * 保存激活卡导入信息及其激活卡信息
     * 
     * @param vcInsuranceCard
     * @param userInfo
     * @return
     * 
     * @author wanghuajian
     */
    public void saveInsuCardInfoAndInsuCard(VcInsuCardImportInfo vcInsuCardImportInfo, UserInfo userInfo)throws BusinessException{
        
        vcInsuCardImportInfoDao.saveInsuCardImpInfoAndInsuKard(vcInsuCardImportInfo);
    }
    

    /**
     * 修改激活卡信息
     * 
     * @param vcInsuranceCard 待修改vo
     * @param userInfo 当前用户
     * @throws BusinessException 异常
     */
    public void updateVcInsuranceCard(VcInsuranceCard vcInsuranceCard, UserInfo userInfo)
            throws BusinessException {

        VcInsuranceCard vo = vcInsuranceCardDao
                .getVcInsuranceCard(vcInsuranceCard.getIdVcInsuranceCard());
        if(vo==null){
            throw new BusinessException("激活卡【CardNo:"+vcInsuranceCard.getIdVcInsuranceCard()+"】不存在！ "); 
        }

        vo.setPassword(vcInsuranceCard.getPassword());
        vo.setEncryptedPw(vcInsuranceCard.getEncryptedPw());
        vo.setValue(vcInsuranceCard.getValue());
        vo.setValidDate(vcInsuranceCard.getValidDate());
        vo.setDateUpdated(new Date());
        vo.setUpdatedBy(userInfo.getUserCode());

        vcInsuranceCardDao.update(vo);
    }
    
    /**
     * 
     * @param file 待上传文件
     * @param 单证类型代码
     * @param userInfo 当前用户
     * @return
     * @throws BusinessException
     */
   public String doUpLoadInsuranceCard(File file, String docVerCode, UserInfo userInfo)  throws BusinessException{
       // 导入信息表
       VcInsuCardImportInfo vcInsuCardImportInfo = new VcInsuCardImportInfo();
       // 当前时间
       Date curDate = new Date();

       HSSFWorkbook workbook = null;
       HSSFSheet sheet = null;
       HSSFRow row = null;
       HSSFCell cell = null;
       try {
          BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
          // 打开HSSFWorkbook
           POIFSFileSystem fs = new POIFSFileSystem(in);   
           // 创建对Excel工作簿文件的引用
           workbook = new HSSFWorkbook(fs);
       } catch (Exception e) {
           throw new BusinessException("Excell文件读取错误！ ");
       }
     
       // 创建对工作表的引用。
       // 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）
       // sheet = workbook.getSheet("Sheet1");
       sheet = workbook.getSheetAt(0);

       // 行数(从0开始,相当于最后一行的索引),列数
       int count_row = sheet.getLastRowNum();
       if (count_row < 1) {
           return "success";
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
       VcInsuranceCard tempVo = null;
       String titleName = null;
       Long maxNo = 0L;
       Long minNo = 0L;
       Long tempNo = 0L;
       HSSFRow oneRow = sheet.getRow(1);
       HSSFCell tempcell = null;
       for (int i = 0; i < count_cell; i++) {
           tempcell = oneRow.getCell(i);
           if (tempcell != null) {
               titleName = zeroRow.getCell(i).getStringCellValue();
               if ("卡号".equals(titleName)) {
                   // Math.round(tempcell.getNumericCellValue());
                   tempNo = Long.valueOf(tempcell.getStringCellValue());
                   maxNo = tempNo;
                   minNo = tempNo;
               }
           }
       }

       for (int i = 1; i <= count_row; i++) {
           row = sheet.getRow(i);
           if (row == null) {
               continue;
           }
           tempVo = new VcInsuranceCard();
           for (int j = 0; j < count_cell; j++) {
               cell = row.getCell(j);
               if (cell != null) {
                   titleName = zeroRow.getCell(j).getStringCellValue();
                   if ("卡号".equals(titleName)) {
                       tempNo = Long.valueOf(row.getCell(j).getStringCellValue());
                       // 获取最大最小卡号
                       if (tempNo > maxNo) {
                           maxNo = tempNo;
                       }
                       if (tempNo < minNo) {
                           minNo = tempNo;
                       }
                       tempVo.setCardNo(row.getCell(j).getStringCellValue());
                   } else if ("密码".equals(titleName)) {
                       tempVo.setPassword(row.getCell(j).getStringCellValue());
                   } else if ("md5".equals(titleName)) {
                       tempVo.setEncryptedPw(row.getCell(j).getStringCellValue());
                   } else if ("有效期".equals(titleName)) {
                       tempVo.setValidDate(row.getCell(j).getDateCellValue());
                   } else if ("面值".equals(titleName)) {
                       tempVo.setValue((float) row.getCell(j).getNumericCellValue());
                   }
               }
           }
           // tempVo.setIdVcInsuCardImportInfo(111L);
           tempVo.setDocVerCode(docVerCode);
           tempVo.setCardStatus("1");
           tempVo.setImportTime(new Date());
           tempVo.setCreatedBy(userInfo.getUserCode());
           tempVo.setUpdatedBy(userInfo.getUserCode());
           tempVo.setDateCreated(curDate);
           tempVo.setDateUpdated(curDate);
           tempVo.setVcInsuCardImportInfo(vcInsuCardImportInfo);
           list.add(tempVo);
       }
       vcInsuCardImportInfo.setDocVerCode(docVerCode);

       vcInsuCardImportInfo.setVcInsuranceCardList(list);
       vcInsuCardImportInfo.setStartCardNo(String.valueOf(minNo));
       vcInsuCardImportInfo.setEndCardNo(String.valueOf(maxNo));
       vcInsuCardImportInfo.setImportNum(new Long(count_row ));
       vcInsuCardImportInfo.setImportTime(curDate);
       vcInsuCardImportInfo.setOprCode(userInfo.getUserCode());
       vcInsuCardImportInfo.setCreatedBy(userInfo.getUserCode());
       vcInsuCardImportInfo.setUpdatedBy(userInfo.getUserCode());
       vcInsuCardImportInfo.setDateCreated(curDate);
       vcInsuCardImportInfo.setDateUpdated(curDate);

       vcInsuCardImportInfoDao.saveInsuCardImpInfoAndInsuKard(vcInsuCardImportInfo);
       
       return "success";
   }

}
