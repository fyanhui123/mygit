package com.tapi.tcs.vc.invoice.vo.fujian;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Kjmx", propOrder = {
    "kjxmXh",
    "kjxmDm",
    "kjxmMc",
    "kjnr",
    "dwMc",
    "dj",
    "sl",
    "je",
    "v1",
    "v2",
    "v3",
    "v4",
    "v5",
    "v6",
    "v7",
    "v8",
    "v9",
    "v10",
    "v11",
    "v12",
    "v13",
    "v14",
    "v15"
})
public class InvoicePrintDetDTO implements Serializable{

	@XmlElement(name = "KJXM_XH", required = true)
	protected String kjxmXh;
	@XmlElement(name = "KJXM_DM", required = true)
    protected String kjxmDm;
	@XmlElement(name = "KJXM_MC", required = true)
    protected String kjxmMc;
	@XmlElement(name = "KJNR", required = true)
    protected String kjnr;
	@XmlElement(name = "DW_MC", required = true)
    protected String dwMc;
	@XmlElement(name = "DJ", required = true)
    protected String dj;
	@XmlElement(name = "SL", required = true)
    protected String sl;
	@XmlElement(name = "JE", required = true)
    protected String je;
	@XmlElement(name = "V1", required = true)
	protected String v1;
	@XmlElement(name = "V2", required = true)
    protected String v2;
	@XmlElement(name = "V3", required = true)
    protected String v3;
	@XmlElement(name = "V4", required = true)
    protected String v4;
	@XmlElement(name = "V5", required = true)
    protected String v5;
	@XmlElement(name = "V6", required = true)
    protected String v6;
	@XmlElement(name = "V7", required = true)
    protected String v7;
	@XmlElement(name = "V8", required = true)
    protected String v8;
	@XmlElement(name = "V9", required = true)
    protected String v9;
	@XmlElement(name = "V10", required = true)
    protected String v10;
	@XmlElement(name = "V11", required = true)
    protected String v11;
	@XmlElement(name = "V12", required = true)
    protected String v12;
	@XmlElement(name = "V13", required = true)
    protected String v13;
	@XmlElement(name = "V14", required = true)
    protected String v14;
	@XmlElement(name = "V15", required = true)
    protected String v15;
	
	public String getKjxmXh() {
		return kjxmXh;
	}
	public void setKjxmXh(String kjxmXh) {
		this.kjxmXh = kjxmXh;
	}
	public String getKjxmDm() {
		return kjxmDm;
	}
	public void setKjxmDm(String kjxmDm) {
		this.kjxmDm = kjxmDm;
	}
	public String getKjxmMc() {
		return kjxmMc;
	}
	public void setKjxmMc(String kjxmMc) {
		this.kjxmMc = kjxmMc;
	}
	public String getKjnr() {
		return kjnr;
	}
	public void setKjnr(String kjnr) {
		this.kjnr = kjnr;
	}
	public String getDwMc() {
		return dwMc;
	}
	public void setDwMc(String dwMc) {
		this.dwMc = dwMc;
	}
	public String getDj() {
		return dj;
	}
	public void setDj(String dj) {
		this.dj = dj;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public String getJe() {
		return je;
	}
	public void setJe(String je) {
		this.je = je;
	}
	public String getV1() {
		return v1;
	}
	public void setV1(String v1) {
		this.v1 = v1;
	}
	public String getV2() {
		return v2;
	}
	public void setV2(String v2) {
		this.v2 = v2;
	}
	public String getV3() {
		return v3;
	}
	public void setV3(String v3) {
		this.v3 = v3;
	}
	public String getV4() {
		return v4;
	}
	public void setV4(String v4) {
		this.v4 = v4;
	}
	public String getV5() {
		return v5;
	}
	public void setV5(String v5) {
		this.v5 = v5;
	}
	public String getV6() {
		return v6;
	}
	public void setV6(String v6) {
		this.v6 = v6;
	}
	public String getV7() {
		return v7;
	}
	public void setV7(String v7) {
		this.v7 = v7;
	}
	public String getV8() {
		return v8;
	}
	public void setV8(String v8) {
		this.v8 = v8;
	}
	public String getV9() {
		return v9;
	}
	public void setV9(String v9) {
		this.v9 = v9;
	}
	public String getV10() {
		return v10;
	}
	public void setV10(String v10) {
		this.v10 = v10;
	}
	public String getV11() {
		return v11;
	}
	public void setV11(String v11) {
		this.v11 = v11;
	}
	public String getV12() {
		return v12;
	}
	public void setV12(String v12) {
		this.v12 = v12;
	}
	public String getV13() {
		return v13;
	}
	public void setV13(String v13) {
		this.v13 = v13;
	}
	public String getV14() {
		return v14;
	}
	public void setV14(String v14) {
		this.v14 = v14;
	}
	public String getV15() {
		return v15;
	}
	public void setV15(String v15) {
		this.v15 = v15;
	}
}
