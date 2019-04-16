package com.tapi.tcs.vc.menu.vo;

/**
 *【菜单DTO】
 * @author lianchao 
 * @date   2011-05-09
 * @version 1.0
*/
public class MenuDTO {
    
    /**
    * 菜单id
    */
    private String id;

    /**
     * 菜单名称
     */
    private String menuName;
    
    /**
     * url
     */
    private String url;

    /**
     * 父菜单名称
     */
    private String parentMenu;

    /**
     * 是否子菜单
     */
    private String isCildMenu;
    
    /**
     * 元素ID
     */
    private String elementId;

    /**
     * @param 
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     * @return
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param 
     * @return the menuName
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName the menuName to set
     * @return
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * @param 
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     * @return
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @param 
     * @return the parentMenu
     */
    public String getParentMenu() {
        return parentMenu;
    }

    /**
     * @param parentMenu the parentMenu to set
     * @return
     */
    public void setParentMenu(String parentMenu) {
        this.parentMenu = parentMenu;
    }

    /**
     * @param 
     * @return the isCildMenu
     */
    public String getIsCildMenu() {
        return isCildMenu;
    }

    /**
     * @param isCildMenu the isCildMenu to set
     * @return
     */
    public void setIsCildMenu(String isCildMenu) {
        this.isCildMenu = isCildMenu;
    }

    /**
     * @param 
     * @return the elementId
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * @param elementId the elementId to set
     * @return
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    


}
