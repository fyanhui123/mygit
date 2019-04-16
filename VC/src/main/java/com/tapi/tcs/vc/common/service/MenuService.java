/**
 * 
 */
package com.tapi.tcs.vc.common.service;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.security.authority.client.SystemDTO;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.vo.MenuVo;


/**
 * 查询主菜单Service
 * 
 * @author lianchao
 * @Date: 2013-4-23
 * @version 1.0
 */
public interface MenuService {
    /**
     * 查询主菜单信息
     * 
     * @author lianchao
     * @date 2013-04-25
     * @return 菜单List
     */
    public List < MenuVo > queryMenuList(UserInfo userInfo)throws BusinessException ;
    
    /**
     * 查询系统列表
     * @return
     * @author chy
     * @date 2013-10-15
     */
    public List <SystemDTO> querySystemList( String jsonStr) throws BusinessException;

}
