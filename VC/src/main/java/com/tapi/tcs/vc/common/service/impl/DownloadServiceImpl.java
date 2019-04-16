package com.tapi.tcs.vc.common.service.impl;

import com.tapi.tcs.vc.baseinfo.dao.VcDocVersionInfoDao;
import com.tapi.tcs.vc.baseinfo.dao.VcPrinteryDao;
import com.tapi.tcs.vc.common.service.DownloadService;
import com.tapi.tcs.vc.schema.model.VcDestroy;
import com.tapi.tcs.vc.schema.model.VcDocInStore;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcLost;
import com.tapi.tcs.vc.schema.model.VcPrintery;
import com.tapi.tcs.vc.store.dao.DocInStoreManageDAO;
import com.tapi.tcs.vc.visausage.dao.DestroyDao;
import com.tapi.tcs.vc.visausage.dao.VcLostDao;

public class DownloadServiceImpl implements DownloadService {

	private DestroyDao destroyDao;
	private DocInStoreManageDAO docInStoreManageDao;
	private VcLostDao vcLostDao;
	private VcPrinteryDao vcPrinteryDao;
	private VcDocVersionInfoDao vcDocVersionInfoDao;

	@Override
	public String[] queryFileById(Long id, String type) {
		String fileName = null;
		String fileUrl = null;
		// 销毁证明材料
		if ("destroy".equals(type)) {
			VcDestroy vcDestroy = destroyDao.get(id);
			fileName = vcDestroy.getFileName();
			fileUrl = vcDestroy.getFilePath();
		}
		// 入库登记单
		else if ("docInstore".equals(type)) {
			VcDocInStore vcDocInStore = docInStoreManageDao.get(id);
			fileName = vcDocInStore.getFileName();
			fileUrl = vcDocInStore.getFilePath();
		}
		// 遗失证明材料
		else if ("lost".equals(type)) {
			VcLost VcLost = vcLostDao.get(id);
			fileName = VcLost.getFileName();
			fileUrl = VcLost.getFilePath();
		}
		// 
		else if ("printery".equals(type)) {
			VcPrintery printery = vcPrinteryDao.get(id);
			fileName = printery.getFileName();
			fileUrl = printery.getFilePath();
		}
		// 
		else if ("docVersionInfo".equals(type)) {
			VcDocVersionInfo docVersionInfo = vcDocVersionInfoDao.get(id);
			fileName = docVersionInfo.getFileName();
			fileUrl = docVersionInfo.getFilePath();
		}
		String[] fileInfo = { fileUrl, fileName };
		return fileInfo;
	}

	public void setDestroyDao(DestroyDao destroyDao) {
		this.destroyDao = destroyDao;
	}

	public void setDocInStoreManageDao(DocInStoreManageDAO docInStoreManageDao) {
		this.docInStoreManageDao = docInStoreManageDao;
	}

	public void setVcLostDao(VcLostDao vcLostDao) {
		this.vcLostDao = vcLostDao;
	}

	public void setVcPrinteryDao(VcPrinteryDao vcPrinteryDao) {
		this.vcPrinteryDao = vcPrinteryDao;
	}

	public void setVcDocVersionInfoDao(VcDocVersionInfoDao vcDocVersionInfoDao) {
		this.vcDocVersionInfoDao = vcDocVersionInfoDao;
	}
}
