package com.tapi.tcs.vc.common.vo;

import com.tapi.tcs.vc.schema.model.VcPubCode;

public class VcPubCodeQueryVo extends VcPubCode {
    /**
	 * 
	 */
    private static final long serialVersionUID = 3767022718386444097L;

    /** 代码类型(完全匹配查询使用) */
    private String codeTypeEQ;

    /** 代码类型描述 */
    private String codeTypeDesc;

    public String getCodeTypeDesc() {
        return codeTypeDesc;
    }

    public void setCodeTypeDesc(String codeTypeDesc) {
        this.codeTypeDesc = codeTypeDesc;
    }

    /**
     * @return the codeTypeEQ
     */
    public String getCodeTypeEQ() {
        return codeTypeEQ;
    }

    /**
     * @param codeTypeEQ
     *            the codeTypeEQ to set
     */
    public void setCodeTypeEQ(String codeTypeEQ) {
        this.codeTypeEQ = codeTypeEQ;
    }

}
