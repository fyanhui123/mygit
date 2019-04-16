package com.tapi.tcs.vc.invoice.vo.guizhou;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dw_zkp_import_row", propOrder = { "sbh", "xmmc",
		"xmbh", "jsxm", "kxxz", "kpr", "fsrq", "fkf", "fkfmc", "kpje", "fphm",
		"mj", "dj", "zbrbz", "fbrbz", "jslx", "bz", "fpdmqc", "qtkxxz",
		"zfbz", "swdjzzh", "nsrmc", "fplb", "hbrq", "qyg", "mdg", "xhg",
		"tydh", "cbxz", "bxd", "bdh", "ccs", "ccsznj", "fhr", "lxdh", "dz",
		"bxf", "yhzh", "yhmc", "gsdjz", "zghy", "yhbm", "kpd" })
public class InvoiceGuiZhouKpInfoDetDTO implements Serializable {
	@XmlElement(name = "sbh", required = true)
	private String sbh;
//	@XmlElement(name = "glbm", required = true)
//	private String glbm;// 管理编码
	@XmlElement(name = "xmmc", required = true)
	private String xmmc; // 项目名称
	@XmlElement(name = "xmbh", required = true)
	private String xmbh;
	@XmlElement(name = "jsxm", required = true)
	private String jsxm;
	@XmlElement(name = "kxxz", required = true)
	private String kxxz;
	@XmlElement(name = "kpr", required = true)
	private String kpr; // 开票人
	@XmlElement(name = "fsrq", required = true)
	private String fsrq;  //开票日期
	@XmlElement(name = "fkf", required = true)
	private String fkf;
	@XmlElement(name = "fkfmc", required = true)
	private String fkfmc;
	@XmlElement(name = "kpje", required = true)
	private String kpje;
	@XmlElement(name = "fphm", required = true)
	private String fphm;
	@XmlElement(name = "mj", required = true)
	private String mj;
//	@XmlElement(name = "sl", required = true)
//	private String sl;
	@XmlElement(name = "dj", required = true)
	private String dj;
	@XmlElement(name = "zbrbz", required = true)
	private String zbrbz;
	@XmlElement(name = "fbrbz", required = true)
	private String fbrbz;
	@XmlElement(name = "jslx", required = true)
	private String jslx;
	@XmlElement(name = "bz", required = true)
	private String bz;
	@XmlElement(name = "fpdmqc", required = true)
	private String fpdmqc;
	@XmlElement(name = "qtkxxz", required = true)
	private String qtkxxz; // 房动产适用
	@XmlElement(name = "zfbz", required = true)
	private String zfbz;// 作废标志
	@XmlElement(name = "swdjzzh", required = true)
	private String swdjzzh;
	@XmlElement(name = "nsrmc", required = true)
	private String nsrmc;
	@XmlElement(name = "fplb", required = true)
	private String fplb;
	@XmlElement(name = "hbrq", required = true)
	private String hbrq; // 航班日期
	@XmlElement(name = "qyg", required = true)
	private String qyg; // 起运港
	@XmlElement(name = "mdg", required = true)
	private String mdg;
	@XmlElement(name = "xhg", required = true)
	private String xhg;
	@XmlElement(name = "tydh", required = true)
	private String tydh;
	@XmlElement(name = "cbxz", required = true)
	private String cbxz; // 承保险种
	@XmlElement(name = "bxd", required = true)
	private String bxd;
	@XmlElement(name = "bdh", required = true)
	private String bdh;
	@XmlElement(name = "ccs", required = true)
	private String ccs; // 车船税
	@XmlElement(name = "ccsznj", required = true)
	private String ccsznj;
	@XmlElement(name = "fhr", required = true)
	private String fhr;
	@XmlElement(name = "lxdh", required = true)
	private String lxdh;
	@XmlElement(name = "dz", required = true)
	private String dz;
	@XmlElement(name = "bxf", required = true)
	private String bxf;
	@XmlElement(name = "yhzh", required = true)
	private String yhzh;
	@XmlElement(name = "yhmc", required = true)
	private String yhmc;
	@XmlElement(name = "gsdjz", required = true)
	private String gsdjz;
	@XmlElement(name = "zghy", required = true)
	private String zghy;
	@XmlElement(name = "yhbm", required = true)
	private String yhbm;
	@XmlElement(name = "kpd", required = true)
	private String kpd;
	
	public String getSbh() {
		return sbh;
	}

	public void setSbh(String sbh) {
		this.sbh = sbh;
	}

//	public String getGlbm() {
//		return glbm;
//	}
//
//	public void setGlbm(String glbm) {
//		this.glbm = glbm;
//	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getXmbh() {
		return xmbh;
	}

	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
	}

	public String getJsxm() {
		return jsxm;
	}

	public void setJsxm(String jsxm) {
		this.jsxm = jsxm;
	}

	public String getKxxz() {
		return kxxz;
	}

	public void setKxxz(String kxxz) {
		this.kxxz = kxxz;
	}

	public String getKpr() {
		return kpr;
	}

	public void setKpr(String kpr) {
		this.kpr = kpr;
	}

//	public String getSl() {
//		return sl;
//	}
//
//	public void setSl(String sl) {
//		this.sl = sl;
//	}

	public String getFsrq() {
		return fsrq;
	}

	public void setFsrq(String fsrq) {
		this.fsrq = fsrq;
	}

	public String getFkf() {
		return fkf;
	}

	public void setFkf(String fkf) {
		this.fkf = fkf;
	}

	public String getFkfmc() {
		return fkfmc;
	}

	public void setFkfmc(String fkfmc) {
		this.fkfmc = fkfmc;
	}


	public String getFphm() {
		return fphm;
	}

	public void setFphm(String fphm) {
		this.fphm = fphm;
	}




	
	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

//	public String getZbbrbz() {
//		return zbbrbz;
//	}
//
//	public void setZbbrbz(String zbbrbz) {
//		this.zbbrbz = zbbrbz;
//	}
//
//	public String getFbbrbz() {
//		return fbbrbz;
//	}
//
//	public void setFbbrbz(String fbbrbz) {
//		this.fbbrbz = fbbrbz;
//	}

	public String getJslx() {
		return jslx;
	}

	public void setJslx(String jslx) {
		this.jslx = jslx;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getFpdmqc() {
		return fpdmqc;
	}

	public void setFpdmqc(String fpdmqc) {
		this.fpdmqc = fpdmqc;
	}

	public String getQtkxxz() {
		return qtkxxz;
	}

	public void setQtkxxz(String qtkxxz) {
		this.qtkxxz = qtkxxz;
	}

	public String getZfbz() {
		return zfbz;
	}

	public void setZfbz(String zfbz) {
		this.zfbz = zfbz;
	}

	public String getSwdjzzh() {
		return swdjzzh;
	}

	public void setSwdjzzh(String swdjzzh) {
		this.swdjzzh = swdjzzh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getFplb() {
		return fplb;
	}

	public void setFplb(String fplb) {
		this.fplb = fplb;
	}

	
	public String getHbrq() {
		return hbrq;
	}

	public void setHbrq(String hbrq) {
		this.hbrq = hbrq;
	}

	public String getQyg() {
		return qyg;
	}

	public void setQyg(String qyg) {
		this.qyg = qyg;
	}

	public String getMdg() {
		return mdg;
	}

	public void setMdg(String mdg) {
		this.mdg = mdg;
	}

	public String getXhg() {
		return xhg;
	}

	public void setXhg(String xhg) {
		this.xhg = xhg;
	}

	public String getTydh() {
		return tydh;
	}

	public void setTydh(String tydh) {
		this.tydh = tydh;
	}

	public String getCbxz() {
		return cbxz;
	}

	public void setCbxz(String cbxz) {
		this.cbxz = cbxz;
	}

	public String getBxd() {
		return bxd;
	}

	public void setBxd(String bxd) {
		this.bxd = bxd;
	}

	public String getBdh() {
		return bdh;
	}

	public void setBdh(String bdh) {
		this.bdh = bdh;
	}


	public String getFhr() {
		return fhr;
	}

	public void setFhr(String fhr) {
		this.fhr = fhr;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}


	public String getYhzh() {
		return yhzh;
	}

	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getGsdjz() {
		return gsdjz;
	}

	public void setGsdjz(String gsdjz) {
		this.gsdjz = gsdjz;
	}

	public String getZghy() {
		return zghy;
	}

	public void setZghy(String zghy) {
		this.zghy = zghy;
	}

	public String getYhbm() {
		return yhbm;
	}

	public void setYhbm(String yhbm) {
		this.yhbm = yhbm;
	}

	public void setKpje(String kpje) {
		this.kpje = kpje;
	}

	public void setCcs(String ccs) {
		this.ccs = ccs;
	}

	public void setCcsznj(String ccsznj) {
		this.ccsznj = ccsznj;
	}

	public void setBxf(String bxf) {
		this.bxf = bxf;
	}

	public String getKpje() {
		return kpje;
	}

	public String getCcs() {
		return ccs;
	}

	public String getCcsznj() {
		return ccsznj;
	}

	public String getBxf() {
		return bxf;
	}

	public String getMj() {
		return mj;
	}

	public void setMj(String mj) {
		this.mj = mj;
	}

	public String getZbrbz() {
		return zbrbz;
	}

	public void setZbrbz(String zbrbz) {
		this.zbrbz = zbrbz;
	}

	public String getFbrbz() {
		return fbrbz;
	}

	public void setFbrbz(String fbrbz) {
		this.fbrbz = fbrbz;
	}

	public String getKpd() {
		return kpd;
	}

	public void setKpd(String kpd) {
		this.kpd = kpd;
	}

}
