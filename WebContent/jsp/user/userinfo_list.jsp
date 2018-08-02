<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="d" uri="http://displaytag.sf.net"%>
<%@ page import="cn.swpu.entity.User" %>

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
	<form action="AdminServlet?flag=query" method="post" class="form-inline">
		<div class="row alert alert-info" style="margin: 10px; padding: 5px; text-align: center;">
			<div class="form-group">
		<label>条件:</label> 
			<select name="condition" class="form-control">
			<option value="username">用户昵称</option>
			<option value="address">用户地址</option>
			<option value="email">用户邮箱</option>
			</select>
		<input type="text" name="content" value="" class="form-control" placeholder="请输入查询条件" />
	</div>
	<input type="submit" class="btn btn-danger" value="查询">
	</div>
		<div class="row" style="padding: 15px;">		
			 <d:table name="${userList}" pagesize="8" requestURI="AdminServlet?flag=query" class="table table-hover table-striped table-bordered">
        <d:column property="id" title="用户ID"></d:column>
        <d:column property="username" title="用户昵称"></d:column>
        <d:column property="password" title="用户密码"></d:column>
        <d:column property="email" title="用户邮箱"></d:column>
        <d:column property="address" title="用户地址"></d:column>
        <d:column property="tel" title="用户电话"></d:column>
        <d:column property="identityId" title="用户身份"></d:column>         
      <d:column href="AdminServlet?flag=delete" paramId="id" paramProperty="id" title="删除" value="删除"></d:column>
       <d:column href="AdminServlet?flag=edit" paramId="id" paramProperty="id" title="编辑" value="编辑"></d:column>
        
      </d:table>
		</div>
	</form>
</body>
</html>

<script>
    $(function () {
        

		$('#addnew').click(function(){

				window.location.href="jsp/user/add.jsp";
		 });


    });

	function del(id)
	{
		
		
		if(confirm("确定要删除吗？"))
		{
		
			var url = "index.html";
			
			window.location.href=url;		
		
		}
	
	
	
	
	}
</script>