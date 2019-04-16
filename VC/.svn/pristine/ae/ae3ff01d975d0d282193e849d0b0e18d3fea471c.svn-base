<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglib.jspf"%>
<html>
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
	<script type="text/javascript">
		function submitForm(){
			fm.action="/visa/index/login.do";
			fm.submit();
		}
	</script>
</head>
 <body>
	<form method="post" id="fm">
		<table border="0">
			<tr>
				<td colspan="2">单证管理系统</td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="userCode" /></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td>机构：</td>
				<td >
					<select><option>--------请选择--------</option></select>
				</td>
			</tr>
			<tr>
				<td><input type="button" value="登 录" onclick="submitForm();"/></td>
			</tr>
		</table>
	</form>
 </body>
</html>