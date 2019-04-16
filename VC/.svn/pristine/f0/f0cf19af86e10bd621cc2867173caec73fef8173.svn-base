/**
 * Copyright ®2013 tianan insurance Co. Ltd.<br/>
 * All rights reserved.<br/>
 * ProjectName: tftemplate<br/>
 * Package:  com.tapi.tcs.template.subfunc.web<br/>
 * FileName: ContractAction.java<br/>
 * Description: 天安TF框架<br/>
 */
package com.tapi.tcs.vc.subfunc.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.vc.common.vo.TreeVo;
import com.tapi.tcs.vc.schema.model.Contract;
import com.tapi.tcs.vc.subfunc.service.ContractService;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.vo.Message;

/**
 * 
 * @Description: 印刷合同管理Action
 * @author lianchao
 * @Date: 2013-02-19
 * @version 1.0
 */
public class ContractAction extends TFAction {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2248031194541049469L;

    /**
     * 日志对象
     */
    protected final Logger logger = LoggerFactory.getLogger(ContractAction.class);

    /**
     * 印刷合同Service
     */
    private ContractService contractService;

    /**
     * 印刷合同List
     */
    private List < Contract > contractList;

    /**
     * 印刷合同对象
     */
    private Contract contract;

    /**
     * 合同名
     */
    private String contractName;

    /**
     * 合同起期
     */
    private Date contractStartDate;

    /**
     * 合同止期
     */
    private Date contractEndDate;

    /**
     * 合同文件
     */
    private File contractCopy;

    /**
     * tree List对象
     */
    private List < TreeVo > treeList;

    /**
     * 组件demo
     * 
     * @return struts 配置文件result name
     */
    public String viewListPage() {
        return "viewWidgets";
    }

    /**
     * tree Demo
     * 
     * @author lianchao
     * @date 2013-02-19
     * @return struts 配置文件result name
     */
    public String queryBranchList() {
        treeList = new ArrayList < TreeVo > ();
        TreeVo treeVo = new TreeVo();
        treeVo.setNodeId("0");
        treeVo.setNodeName("角色管理");
        treeVo.setParentNodeId("-1");
        treeVo.setUrl("#");
        // treeVo.setIsChecked("N");
        treeList.add(treeVo);

        TreeVo treeVo2 = new TreeVo();
        treeVo2.setNodeId("1");
        treeVo2.setNodeName("权限维护");
        treeVo2.setParentNodeId("0");
        treeVo2.setUrl("#");
        // treeVo2.setIsChecked("N");
        treeList.add(treeVo2);

        TreeVo treeVo3 = new TreeVo();
        treeVo3.setNodeId("2");
        treeVo3.setNodeName("角色列表");
        treeVo3.setParentNodeId("1");
        treeVo3.setUrl("/subfunc/viewListPage.do?action=viewListPage");
        // treeVo3.setIsChecked("N");
        treeList.add(treeVo3);

        TreeVo treeVo4 = new TreeVo();
        treeVo4.setNodeId("3");
        treeVo4.setNodeName("资源列表");
        treeVo4.setParentNodeId("1");
        treeVo4.setUrl("/subfunc/viewListPage.do?action=viewListPage");
        // treeVo4.setIsChecked("N");
        treeList.add(treeVo4);

        TreeVo treeVo5 = new TreeVo();
        treeVo5.setNodeId("6");
        treeVo5.setNodeName("用户管理");
        treeVo5.setParentNodeId("-1");
        treeVo5.setUrl("#");
        // treeVo5.setIsChecked("N");
        treeList.add(treeVo5);

        TreeVo treeVo6 = new TreeVo();
        treeVo6.setNodeId("7");
        treeVo6.setNodeName("新增用户");
        treeVo6.setParentNodeId("6");
        // treeVo6.setUrl("/subfunc/viewListPage.do?action=viewListPage");
        // treeVo6.setIsChecked("N");
        treeList.add(treeVo6);
        return SUCCESS;
    }

    /**
     * 查询合同信息
     * 
     * @return struts 配置文件result name
     * @throws BusinessException
     */
    public String viewContractList() {
        String action = getRequest().getParameter("action");
        if (action.contains("viewTree")) {
            return "viewTreePage";
        }
        else {
            return "contractList";
        }
    }

    /**
     * 分页查询合同信息
     * 
     * @author lianchao @date 2013-02-19
     * @return struts 配置文件result name
     */
    public String queryContractsByPages() {
        logger.info("访问/queryContractsByPages.action?ajax=true分页查询合同信息...");
        Page pageObj  = contractService.queryContracts(contractName, contractStartDate, contractEndDate, page, rows);
        contractList = (List < Contract >) pageObj.getResult();
        total = (int) pageObj.getTotalPageCount();
        records = (int) pageObj.getTotalCount();
        return SUCCESS;
    }

    /**
     * 进入新增印刷厂信息页面
     * 
     * @author lianchao
     * @date 2013-02-19
     * @return struts 配置文件result name
     */
    public String viewEditPage() {
        return "editPage";
    }

    /**
     * 新增印刷厂信息
     * 
     * @author lianchao
     * @date 2013-02-19
     * @return struts 配置文件result name
     */
    public String savePrinting() {
        logger.info("访问/savePrinting.action?ajax=true执行saveOrUpdate印刷厂信息...");
        contract = this.getContract();
        Message message = new Message();
        try {
            if (null != this.getContractCopy()) {
                BufferedImage image = ImageIO.read(this.getContractCopy());
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(image, "png", bos);
                byte[] contractCopys = bos.toByteArray();
                contract.setContractCopy(contractCopys);
            }
            if (null != contract) {
                contractService.createOrUpdateContract(contract);
            }
            message.setType("success");
            message.setMessage("保存合同成功!");
        }
        catch (IOException ioex) {
            logger.error("读取合同扫描件发生IO异常:" + ioex);
            message.setType("error");
            message.setMessage("读取合同扫描件发生IO异常:" + ioex);
        }
        messageInfo = TFJSON.toJSONString(message);
        return "contractList";
    }

    /**
     * 修改印刷厂信息
     * 
     * @author lianchao
     * @date 2013-02-19
     * @return struts 配置文件result name
     */
    public String editPrinting() {
        logger.info("访问/editPrinting.do?ajax=true进入修改印刷厂信息...");
        String action = getRequest().getParameter("action");
        String contractId = getRequest().getParameter("id");
        logger.info("参数为id=" + contractId + "&action=loading");
        if ("loading".equals(action)) {
            try {
                contract = contractService.getContract(Long.valueOf(contractId));
            }
            catch (NumberFormatException e) {
                logger.error("读取合同扫描件发生IO异常:" + e);
            }
            // 到修改页面
            return "editPrinting";
        }
        else {
            // 修改完成到成功页面
            return "contractList";
        }
    }

    /**
     * @return the contractList
     */
    public List < Contract > getContractList() {

        return contractList;
    }

    /**
     * @param contractList the contractList to set
     */
    public void setContractList(List < Contract > contractList) {

        this.contractList = contractList;
    }

    /**
     * @return the contract
     */
    public Contract getContract() {

        return contract;
    }

    /**
     * @param contract the contract to set
     */
    public void setContract(Contract contract) {

        this.contract = contract;
    }

    /**
     * @return the contractName
     */
    public String getContractName() {

        return contractName;
    }

    /**
     * @param contractName the contractName to set
     */
    public void setContractName(String contractName) {

        this.contractName = contractName;
    }

    /**
     * @return the contractStartDate
     */
    public Date getContractStartDate() {

        return contractStartDate;
    }

    /**
     * @param contractStartDate the contractStartDate to set
     */
    public void setContractStartDate(Date contractStartDate) {

        this.contractStartDate = contractStartDate;
    }

    /**
     * @return the contractEndDate
     */
    public Date getContractEndDate() {

        return contractEndDate;
    }

    /**
     * @param contractEndDate the contractEndDate to set
     */
    public void setContractEndDate(Date contractEndDate) {

        this.contractEndDate = contractEndDate;
    }

    /**
     * @return the contractCopy
     */
    public File getContractCopy() {

        return contractCopy;
    }

    /**
     * @param contractCopy the contractCopy to set
     */
    public void setContractCopy(File contractCopy) {

        this.contractCopy = contractCopy;
    }

    /**
     * @return the treeList
     */
    public List < TreeVo > getTreeList() {

        return treeList;
    }

    /**
     * @param treeList the treeList to set
     */
    public void setTreeList(List < TreeVo > treeList) {

        this.treeList = treeList;
    }

    /**
     * @param contractService the contractService to set
     */
    public void setContractService(ContractService contractService) {

        this.contractService = contractService;
    }

}