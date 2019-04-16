package com.tapi.tcs.vc.invoice.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;


public interface ChongQingInvoiceService {

    /**
     * 重庆发票定时导出程序入口
     * 纳税人在每天早上2点之前将前一天的数据上传至采集服务器
     */
    public void InvoiceExportAutoTask();
}
