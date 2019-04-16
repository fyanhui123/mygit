/**
 * 
 */
package com.tapi.tcs.vc.common.service.impl;

import java.util.ArrayList;
import java.util.Map;

import java.util.List;


import com.alibaba.fastjson.JSONObject;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.service.MenuService;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.security.authority.SecurityContext;
import com.tapi.tcs.tf.security.authority.client.MenuDTO;
import com.tapi.tcs.tf.security.authority.client.SystemDTO;
import com.tapi.tcs.vc.common.vo.MenuVo;
import com.tapi.tcs.vc.vcmenu.dao.VcMenuDao;
import com.tapi.tcs.vc.vcmenu.dto.SystemDto;

/**
 * 查询主菜单Service
 * 
 * @author lianchao
 * @Date: 2013-4-23
 * @version 1.0
 */
public class MenuServiceImpl implements MenuService {
	public VcMenuDao vcMenuDao;
	
    public VcMenuDao getVcMenuDao() {
		return vcMenuDao;
	}

	public void setVcMenuDao(VcMenuDao vcMenuDao) {
		this.vcMenuDao = vcMenuDao;
	}

	/**
     * 查询主菜单信息
     * 
     * @author lianchao
     * @date 2013-04-25
     * @return 菜单List
     */
    public List < MenuVo > queryMenuList(UserInfo userInfo)throws BusinessException  {
       // List < MenuDTO > menulst =  SecurityContext.getMenus();
    	//根据用户信息获取用户id
    	List < MenuDTO > menulst=vcMenuDao.queryMenuListInfo(userInfo.getUserCode());
        List < MenuVo > menuList = new ArrayList < MenuVo > ();
        for (MenuDTO menuDTO : menulst) {
            MenuVo menuVo = new MenuVo();
            menuVo.setMenuID(menuDTO.getMenuID());
            menuVo.setMenuName(menuDTO.getMenuName());
            menuVo.setParentID(menuDTO.getParentID());
            menuVo.setIsChild(menuDTO.getIsChild());
            menuVo.setUrl(menuDTO.getUrl());
            menuVo.setSeqNo(menuDTO.getSeqNo());
            menuList.add(menuVo);
        }
        /*MenuVo menuVo8 = new MenuVo();
        menuVo8.setMenuID("7");
        menuVo8.setMenuName("工作提醒");
        menuVo8.setParentID("-1");
        menuVo8.setUrl("#");
        menuVo8.setSeqNo(1);
        menuVo8.setIsChild("N");
        menuVo8.setIsRemind("Y");
        menuList.add(menuVo8);

        MenuVo menuVo9 = new MenuVo();
        menuVo9.setMenuID("8");
        menuVo9.setMenuName("工作列表");
        menuVo9.setParentID("7");
        menuVo9.setUrl("#");
        menuVo9.setSeqNo(1);
        menuVo9.setIsChild("N");
        menuVo9.setIsRemind("Y");
        menuList.add(menuVo9);

        MenuVo menuVo10 = new MenuVo();
        menuVo10.setMenuID("9");
        menuVo10.setMenuName("工作1");
        menuVo10.setParentID("8");
        menuVo10.setUrl("/orderManager/prePurchaseApproveQuery.do");
       // menuVo10.setUrl("/subfunc/viewEditTable.do?action=viewMulLine");
        menuVo10.setSeqNo(1);
        menuVo10.setIsChild("Y");
        menuVo10.setIsRemind("Y");
        menuList.add(menuVo10);

        MenuVo menuVo11 = new MenuVo();
        menuVo11.setMenuID("10");
        menuVo11.setMenuName("工作2");
        menuVo11.setParentID("8");
        menuVo11.setUrl("/vclevel/preLevelSet1.do");
       // menuVo11.setUrl("/subfunc/viewEditTable.do?action=viewMulLine");
        menuVo11.setSeqNo(1);
        menuVo11.setIsChild("Y");
        menuVo11.setIsRemind("Y");
        menuList.add(menuVo11);

        MenuVo menuVo12 = new MenuVo();
        menuVo12.setMenuID("11");
        menuVo12.setMenuName("TF框架版本1.4已发布,请更新!");
        menuVo12.setParentID("7");
        menuVo12.setUrl("#");
        menuVo12.setSeqNo(1);
        menuVo12.setIsChild("N");
        menuVo12.setIsRemind("N");
        menuList.add(menuVo12);

        MenuVo menuVo13 = new MenuVo();
        menuVo13.setMenuID("12");
        menuVo13.setMenuName("TF框架版本1.4已发布,请更新!");
        menuVo13.setParentID("7");
        menuVo13.setUrl("#");
        menuVo13.setSeqNo(1);
        menuVo13.setIsChild("N");
        menuVo13.setIsRemind("N");
        menuList.add(menuVo13);*/

        return menuList;
    }
    @Override
    public List <SystemDTO> querySystemList(String jsonStr) throws BusinessException {
		return null;/*
       //List <SystemDTO> systemList = SecurityContext.getSystems();
        List <SystemDTO> systemList= vcMenuDao.querySystemList(userInfo.getUserCode());
       //List <SystemDTO> systemList=new ArrayList<SystemDTO>();
    	SystemDTO systemDTO=null;
    	Map<String,String> map = JSONObject.parseObject(jsonStr, Map.class);
    	for (String key : map.keySet()) { 
    		    String value1=null;
                if (map.get(key) != null) {
                    if ("code".equals(key)) {
                    	 value1=(String) map.get(key);
                    }
                    if("message".equals(key)){
                    	 Object ob=map.get(key);
                    	 List<SystemDto> list = JSONObject.parseArray(ob.toString(),SystemDto.class);
                     	 SystemDTO d=null;
                    	 for (int i=0;i<list.size();i++){
                    		 SystemDto c=list.get(i);
                    		 d=new SystemDTO();
                    		 d.setSystemCode(c.getAppCode());
                    		 d.setSystemCName(c.getAppName());
                    		 d.setSystemURL(c.getAppUrl());
                    		 systemList.add(d);
                    	 }
                    }
                }
    		}
    	return systemList;*/
    }
}
