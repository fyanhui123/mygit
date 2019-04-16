package com.tapi.tcs.vc.vcmenu.dto;

public class Constants {
	public static final String VALID_FLAG_Y = "1";
	public static final String VALID_FLAG_N = "0";
	public static final String ARCHIVE_FLAG_Y = "1";
	public static final String ARCHIVE_FLAG_N = "0";
	public static final String SYS_PREFIX = "sys_";
	public static final String ROLE_PREFIX = "role_";
	public static final String USER_PREFIX = "user_";
	public static final String MENU_PREFIX = "menu_";
	public static final String NOTICE_STATUS_ADD = "0";
	public static final String NOTICE_STATUS_PUBLISH = "1";
	public static final String NOTICE_STATUS_DELETE = "2";
	public static final String NOTICE_STATUS_CANCEL = "3";

	public static final String QUERY_ROLE_LIST = "select distinct v.role_id as roleId " +
			"from um_v_role_menu_system v where v.user_code = ?";
	public static final String QUERY_MENU_LIST = "select distinct v.menu_id as menuId, v.menu_name as menuName, " +
			"v.parent_id as parentId, v.menu_index as menuIndex, v.menu_url as menuUrl " +
			"from um_v_role_menu_system v where v.user_code = ? and v.system_code = ? order by v.parent_id, v.menu_index";
	public static final String QUERY_SYSTEM_LIST = "select distinct v.system_id as systemId, " +
			"v.system_code as systemCode, v.chinese_name as chineseName, v.english_name as englishName, " +
			"v.menu_style as menuStyle, v.system_owner as systemOwner, v.url as url from um_v_role_menu_system v " +
			"where v.user_code = ?";
	public static final String ADD_BASECODE = "insert into um_basecode (RECORD_ID, INFO_ID, INFO_NAME, INFO_CODE, CATAGORY_LEVEL1, CATAGORY_LEVEL2, " +
			"INFO_REMARK, STANDARD_FLAG, INFO_VERSION, CREATOR, CREATE_TIME, UPDATOR, UPDATE_TIME, PUBLISH_TIME, MAPPING_SYSTEM_NAME, MAPPING_INFO_ID, " +
			"MAPPING_INFO_NAME, MAPPING_INFO_CODE, MAPPING_INFO_REMARK, MAPPING_INFO_VERSION, MAPPING_CREATOR, MAPPING_CREATE_TIME, MAPPING_UPDATOR, " +
			"MAPPING_UPDATE_TIME, MAPPING_PUBLISH_TIME) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, to_date(?, 'yyyy-mm-dd hh24:mi:ss'), NVL(?,'N/A'), ?, ?, ?, ?, NVL(?,'1'), ?, ?, ?, ?, to_date(?, 'yyyy-mm-dd hh24:mi:ss'))";
	public static final String DEL_BASECODE = "delete from um_basecode where RECORD_ID=?";
	public static final String ADD_BASECODE_VALUE = "insert into um_basecode_value (UUID_ID, RECORD_ID, VALUE_ID, VALUE_NAME, VALUE_CONTENT, VALUE_REMARK, PARENT_VALUE_ID, VALUE_START_TIME, VALUE_END_TIME, " +
			"STANDARD_FLAG, CREATOR, CREATE_TIME, UPDATOR, UPDATE_TIME, MAPPING_VALUE_ID, MAPPING_VALUE_NAME, MAPPING_VALUE_CONTENT, MAPPING_VALUE_REMARK, MAPPING_PARENT_VALUE_ID, MAPPING_VALUE_START_TIME, MAPPING_VALUE_END_TIME) values " +
			"(?, ?, ?, ?, ?, ?, ?, to_date(?, 'yyyy-mm-dd hh24:mi:ss'), to_date(?, 'yyyy-mm-dd hh24:mi:ss'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, to_date(?, 'yyyy-mm-dd hh24:mi:ss'), to_date(?, 'yyyy-mm-dd hh24:mi:ss'))";
	public static final String DEL_BASECODE_VALUE = "delete from um_basecode_value where RECORD_ID=?";

}
