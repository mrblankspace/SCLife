<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="d" uri="http://displaytag.sf.net"%>
<%
	//获取绝对路径路径 
	String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
%> 
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>用户管理-用户查询</title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript"
	src="resource/js/bootstrap.min.js"></script>
</head>
<body>
	<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>系统管理</li>
			<li>用户管理</li>
			<li>用户查询</li>
		</ul>
	</div>
	<form action="${basePath }system/user/select" method="post" class="form-inline">
		<div class="row alert alert-info" style="margin: 0px; padding: 5px;">
			<div class="form-group">
				<label>条件:</label> 
				<select name="condition" class="form-control">
					<option value="userName">姓名</option>
					<option value="email">邮箱</option>
				</select>
				<input type="text" name="content" value="${result }" class="form-control" placeholder="请输入查询条件" />
			</div>
			<input type="submit" class="btn btn-danger" value="查询"> 
			<a href="view/system/userinfo/userinfo_add.jsp" class="btn btn-success">
				添加用户
			</a>
		</div>
		<div class="row" style="padding: 15px;">			
			<d:table name="${userList}" pagesize="5" requestURI="" class="table table-hover table-striped table-bordered">
				<d:column property="id" title="用户编号"></d:column>
				<d:column property="username" title="用户妮称"></d:column>
				<d:column property="email" title="邮件"></d:column>				
				<d:column property="address" title="地址"></d:column>
				<d:column property="tel" title="电话"></d:column>
				<d:column property="identityId" title="用户标识"></d:column>
				<d:column href="" paramId="userId" paramProperty="id" title="修改" value="修改"></d:column>
				<d:column href="" paramId="userId" paramProperty="id" title="删除" value="删除"></d:column>
			</d:table>
			
		</div>
	</form>
</body>
</html>