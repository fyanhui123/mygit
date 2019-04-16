package com.tapi.tcs.vc.common.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.vc.common.service.DownloadService;

public class DownloadAction extends TFAction {

    private static final long serialVersionUID = -2297967874542878073L;

    /** 文件存放路径 */
    private String fileUrl;
    /** 文件名 */
    private String fileName;
    /** 文件名 */
    private String type;

    private Long id;

    private DownloadService downloadService;

    public String download() {
        return SUCCESS;
    }

    /**
     * 通过Id下载，用download方法传fileUrl、fileName存在中文乱码情况.
     * 
     * @return
     */
    public String downloadById() {
        String[] fileInfo = downloadService.queryFileById(id, type);
        this.fileUrl = fileInfo[0];
        this.fileName = fileInfo[1];
        return SUCCESS;
    }

    /**
     * struts2自动调用，以向客户端输出文件
     * 
     * @return
     * @throws FileNotFoundException
     */
    public InputStream getInputStream() throws FileNotFoundException {
        if (StringUtils.isNotBlank(fileUrl) && StringUtils.isNotBlank(fileName)) {
            File file = new File(fileUrl, fileName);
            // 如果文件存在返回该文件
            if (file.exists()) {
                return new FileInputStream(file);
                
            }
        }        
       // 如果文件不存在返回错误提示
        String errorDir = ServletActionContext.getServletContext().getRealPath("/common");
        //this.fileName = "文件未找到.txt";
        this.fileName = "FileNotFind.txt";
        return new FileInputStream(new File(errorDir, fileName));

    }

    /**
     * 由struts2自动调用，解决文件名中文乱码问题
     * 
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getFileName() throws UnsupportedEncodingException {
        String filename = this.fileName;
        HttpServletRequest request = ServletActionContext.getRequest();
        String agent = request.getHeader("User-Agent");
        if (null != agent) {
            agent = agent.toLowerCase();
            if (agent.indexOf("firefox") != -1) {
                filename = new String(filename.getBytes(), "iso8859-1");
            } else if (agent.indexOf("msie") != -1) {
                filename = java.net.URLEncoder.encode(filename, "UTF-8");
            } else {
                filename = java.net.URLEncoder.encode(filename, "UTF-8");
            }
        }
        return filename;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDownloadService(DownloadService downloadService) {
        this.downloadService = downloadService;
    }
}
