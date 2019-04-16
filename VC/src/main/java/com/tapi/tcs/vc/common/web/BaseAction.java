package com.tapi.tcs.vc.common.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ApplicationContextAware {
	
	private static final long serialVersionUID = 2543857009165067570L;
	protected final Logger logger;
	protected int page;
	protected int rows;
	protected int total;
	protected int records;
	protected String messageInfo;
	ApplicationContext springContext;

	public BaseAction() {
	     logger = LoggerFactory.getLogger(super.getClass());
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public String getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		springContext = applicationContext;
	}

	public ApplicationContext getSpringContext() {
		return springContext;
	}
	public void renderJson(HttpServletResponse response, Object data) {
		try {
			String jsonResponse = "";
			if ((data instanceof String)) {
				jsonResponse = (String) data;
			} else {
				jsonResponse = JSON.toJSONString(data);
			}
			logger.info("Json Result:" + jsonResponse);
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jsonResponse);
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
	}
	//解析请求的Json数据
	public String getRequestPostData(HttpServletRequest request) {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        try {
	        for (int i = 0; i < contentLength;) {
	            int len;
					len = request.getInputStream().read(buffer, i, contentLength - i);
	            if (len == -1) {
	                break;
	            }
	            i += len;
	        }
	        return new String(buffer, "utf-8");
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return "";
    }
	public void setAjaxMessageInfo(String message) {
		HttpServletResponse response = getResponse();
		response.reset();
		response.setContentType("text/json");
		response.setStatus(399);
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(message);
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
