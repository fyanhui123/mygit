/**
 * 
 */
package com.tapi.tcs.vc.menu.dao.impl;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.menu.dao.MenuDao;
import com.tapi.tcs.vc.schema.model.Menu;

/**
 * 菜单Dao
 * @author lianchao
 * @date 2013-02-25
 */
public class MenuDaoImpl extends GenericDaoHibernate<Menu> implements MenuDao {

	public void createMenu(Menu menu){
		this.save(menu);
	}
	
	public List<Menu> queryMainMenuList(String parentNode, String isChild) {
		QueryRule queryRule = QueryRule.getInstance();

		if (StringUtils.isNotBlank(parentNode)) {
			queryRule.addLike("parentNode", parentNode);
		}
		if (StringUtils.isNotBlank(isChild)) {
			queryRule.addLike("isChild", isChild);
		}
		//queryRule.addAscOrder("viewSeq");
		return this.find(Menu.class, queryRule);
		
	}



}
