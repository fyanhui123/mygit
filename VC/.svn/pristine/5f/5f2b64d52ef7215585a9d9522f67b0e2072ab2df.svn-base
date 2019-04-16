package com.tapi.tcs.vc.common.util;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.schema.model.VcApply;

public class ExcelExportHandler {
	/**
	 * 创建Excel文件
	 * @param fileName 文件完整路径(目录+文件名)
	 * @param titles 标题数组
	 * @param arr 数据数组
	 * @return
	 * @throws Exception
	 */
	public void createExcel(String fileName, String[] title, String[][] values,
			VcApply vcApply) throws Exception{
		WritableWorkbook wb = null;
		try{
			//创建WritableWorkbook
			wb = Workbook.createWorkbook(new File(fileName));
            //创建sheet页
			wb.createSheet("Sheet1", 0);
            //获取第一个sheet页
            WritableSheet sheet = wb.getSheet(0);
            
            WritableFont style = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.NO_BOLD, false);
			WritableCellFormat dataFmt = new WritableCellFormat(style);
			dataFmt.setBorder(Border.ALL, BorderLineStyle.THIN);
			dataFmt.setVerticalAlignment(VerticalAlignment.CENTRE);
			dataFmt.setWrap(true);
			dataFmt.setAlignment(Alignment.LEFT);//设置文本水平居左对齐
            
            //设置表头样式
            WritableFont stl = new WritableFont(WritableFont.TIMES, 10,WritableFont.NO_BOLD, false); 
			WritableCellFormat titleFmt = new WritableCellFormat(stl);
			titleFmt.setBorder(Border.ALL, BorderLineStyle.THIN);
			titleFmt.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleFmt.setWrap(true);
			WritableFont titleFont3=new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
			WritableCellFormat titleFmt3= new WritableCellFormat(titleFont3);
			titleFmt3.setBorder(Border.ALL, BorderLineStyle.THIN);
			titleFmt3.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleFmt3.setWrap(true);
			titleFmt3.setAlignment(Alignment.CENTRE);//设置文本水平居中对齐
			//单元格合并方法
			for(int i=0;i<100;i++){
				sheet.setRowView(i,750);
			}
			WritableFont titleFont=new WritableFont(WritableFont.createFont("黑体"),16,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
			WritableCellFormat titleFmt1= new WritableCellFormat(titleFont);
			titleFmt1.setBorder(Border.ALL, BorderLineStyle.THIN);
			titleFmt1.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleFmt1.setWrap(true);
			titleFmt1.setAlignment(Alignment.CENTRE);
			sheet.mergeCells(0,2, 5,2);
			Label lab_00=new Label(0,2,"天安财产保险股份有限公司",titleFmt1);
			sheet.addCell(lab_00);
			sheet.mergeCells(0,3, 5,3);
			Label lab_01=new Label(0,3,"业   务   重   要  单  证  领  用  申  请  表",titleFmt1);
			sheet.addCell(lab_01);
			
			WritableFont titleFont2=new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
			WritableCellFormat titleFmt2= new WritableCellFormat(titleFont2);
			titleFmt2.setBorder(Border.ALL, BorderLineStyle.THIN);
			titleFmt2.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleFmt2.setWrap(true);
			titleFmt2.setAlignment(Alignment.LEFT);//设置文本水平居左对齐
			Label lab_02=new Label(0,4,"领用单位:",titleFmt2);
			sheet.mergeCells(1,4, 2,4);
			sheet.addCell(lab_02);
			Label lab_021=new Label(1,4,vcApply.getApplyOrgName(),titleFmt2);
			sheet.addCell(lab_021);
			Label lab_03=new Label(3,4,"发放单位:",titleFmt2);
			sheet.addCell(lab_03);
			Label lab_031=new Label(4,4,vcApply.getProvideOrgName(),titleFmt2);
			sheet.addCell(lab_031);
			Label lab_04=new Label(5,4,"编号: "+vcApply.getApplyCode(),titleFmt2);
			sheet.addCell(lab_04);
			for(int i=0;i<title.length;i++){
				String titles = title[i];
				sheet.addCell(new Label(i, 5, titles,titleFmt3));
			}
			   sheet.setColumnView(0,29);
			   sheet.setColumnView(1,14);
			   sheet.setColumnView(2,14);
			   sheet.setColumnView(3,20);
			   sheet.setColumnView(4,24);
			   sheet.setColumnView(5,25);
			int c=0;
			//添加值
			for(int j=0;j<values.length;j++){
				for(int k=0;k<values[j].length;k++){
					sheet.addCell(new Label(k, j+6, values[j][k], dataFmt));
				}
				c=j;
			}
			sheet.mergeCells(0,c+7, 0,c+8);
			Label lab_11=new Label(0,c+7,"发单",titleFmt3);
			sheet.addCell(lab_11);
			Label lab_12=new Label(1,c+7,"审批:",titleFmt2);
			sheet.addCell(lab_12);
			Label lab_13=new Label(2,c+7,"经办:",titleFmt2);
			sheet.addCell(lab_13);
			sheet.mergeCells(1,c+8, 2,c+8);
			Label lab_14=new Label(1,c+8,"    年      月     日",titleFmt3);
			sheet.addCell(lab_14);
			
			sheet.mergeCells(3,c+7, 3,c+8);
			Label lab_15=new Label(3,c+7,"领           单",titleFmt3);
			sheet.addCell(lab_15);
			
			Label lab_16=new Label(4,c+7,"审批:",titleFmt2);
			sheet.addCell(lab_16);
			Label lab_17=new Label(5,c+7,"经办:"+vcApply.getApplyOprName(),titleFmt2);
			sheet.addCell(lab_17);
			
			sheet.mergeCells(4,c+8, 5,c+8);
			Label lab_18=new Label(4,c+8,"     年      月     日",titleFmt3);
			sheet.addCell(lab_18);
			wb.write();
		}catch(Exception e){
			throw new BusinessException("生成文件失败！", e);
		}finally {
			try {
				if (wb != null) {
					wb.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * 创建Excel文件
	 * @param fileName 文件完整路径(目录+文件名)
	 * @param titles 标题数组
	 * @param values 数据数组
	 * @return
	 * @throws Exception
	 */
	public void createExcelFile(String fileName, String[] titles, String[][] values) throws Exception{
		WritableWorkbook wb = null;
		try{
			//创建WritableWorkbook
			wb = Workbook.createWorkbook(new File(fileName));
            //创建sheet页
			wb.createSheet("Sheet1", 0);
            //获取第一个sheet页
            WritableSheet sheet = wb.getSheet(0);
            //设置表头样式
            WritableFont stl = new WritableFont(WritableFont.TIMES, 10,WritableFont.NO_BOLD, false); 
			WritableCellFormat titleFmt = new WritableCellFormat(stl);
			titleFmt.setBorder(Border.ALL, BorderLineStyle.THIN);
			titleFmt.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleFmt.setBackground(Colour.LIGHT_GREEN);
			titleFmt.setWrap(true);
			//添加表头
			for(int i=0;i<titles.length;i++){
				String title = titles[i];
				sheet.addCell(new Label(i, 0, title,titleFmt));
				sheet.setColumnView(i, 12);
			}
			//设置数据样式
			WritableFont style = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.NO_BOLD, false);
			WritableCellFormat dataFmt = new WritableCellFormat(style);
			dataFmt.setBorder(Border.ALL, BorderLineStyle.THIN);
			//添加数据
			for(int j=0;j<values.length;j++){
				for(int k=0;k<values[j].length;k++){
					sheet.addCell(new Label(k, j+1, values[j][k], dataFmt));
				}
			}
			wb.write();
		}catch(Exception e){
			throw new BusinessException("生成文件失败！", e);
		}finally {
			try {
				if (wb != null) {
					wb.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getDirPath1() throws Exception {
		String filePath = "";
		// 获取路径
		String basePath = this.getClass().getClassLoader().getResource("/").getPath();
		if (basePath.indexOf("WEB-INF/classes") > -1) {
			basePath = basePath.substring(0, basePath.indexOf("WEB-INF/classes"));
		}
		filePath = basePath + SysConst.APPLY_EXPORT_FILE_PATH;
		File dirPath = new File(filePath);
		//如果目录不存在，则创建
		if(!dirPath.isDirectory()){
			dirPath.mkdir();
		}
		//清空目录下前一天的临时文件
		deleteFile(dirPath);
		return filePath;
	}
	
	
	/**
	 * 获取文件夹路径
	 * @return
	 * @throws Exception
	 */
	public String getDirPath() throws Exception {
		String filePath = "";
		// 获取路径
		String basePath = this.getClass().getClassLoader().getResource("/").getPath();
		if (basePath.indexOf("WEB-INF/classes") > -1) {
			basePath = basePath.substring(0, basePath.indexOf("WEB-INF/classes"));
		}
		filePath = basePath + SysConst.INQUIRY_EXPORT_FILE_PATH;
		File dirPath = new File(filePath);
		//如果目录不存在，则创建
		if(!dirPath.isDirectory()){
			dirPath.mkdir();
		}
		//清空目录下前一天的临时文件
		deleteFile(dirPath);
		return filePath;
	}
	
	/**
     * 获取文件夹路径
     * @return
     * @throws Exception
     */
    public String getDirPath(String path) throws Exception {
        String filePath = "";
        // 获取路径
        String basePath = this.getClass().getClassLoader().getResource("/").getPath();
        if (basePath.indexOf("WEB-INF/classes") > -1) {
            basePath = basePath.substring(0, basePath.indexOf("WEB-INF/classes"));
        }
        filePath = basePath + path;
        File dirPath = new File(filePath);
        //如果目录不存在，则创建
        if(!dirPath.isDirectory()){
            dirPath.mkdir();
        }       
        return filePath;
    }
	
	/**
	 * 删除目录下文件
	 * @param dirPath
	 */
	private void deleteFile(File dirPath) throws Exception{
		File[] fileList = dirPath.listFiles();
		if(fileList!=null && fileList.length>0){
			for(File file : fileList){
				Long time = file.lastModified();
				Calendar nowDate = Calendar.getInstance();
				Calendar calendar = Calendar.getInstance();
				nowDate.setTime(new Date());
				calendar.setTimeInMillis(time);
				//文件的修改日期小于当前日期，则删除（当天的文件不删除）
				if(calendar.get(Calendar.MONTH)<nowDate.get(Calendar.MONTH)
						|| calendar.get(Calendar.DATE)<nowDate.get(Calendar.DATE)){
					file.delete();
				}
			}
		}
	}

}
