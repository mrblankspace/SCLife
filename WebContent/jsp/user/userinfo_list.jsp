<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="d" uri="http://displaytag.sf.net"%>
<%@ page import="cn.swpu.entity.User" %>
<%@ page import="cn.swpu.entity.Order" %>
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
	<form action="OrderServlet?flag=query" method="post" class="form-inline">
		<div class="row alert alert-info" style="margin: 10px; padding: 5px; text-align: center;">
			<div class="form-group">
				<label>条件:</label> 
				<select name="condition" class="form-control">
					<option value="ordertype">订单类型</option>
					<option value="orderstatus">订单状态</option>
					<option value="ordertime">发布时间</option>
					<option value="orderreward">最低报酬</option>
				</select>
				<input type="text" name="content" value="" class="form-control" placeholder="请输入查询条件" />
			</div>
			<input type="submit" class="btn btn-danger" value="查询"> 		
		</div>
		<div class="row" style="padding: 15px;">		
			 <d:table name="${list}" pagesize="5" requestURI="OrderServlet?flag=findOrder" class="table table-hover table-striped table-bordered">
        <d:column property="catagory" title="订单类型"></d:column>
        <d:column property="send_person.username" title="发布人"></d:column>
        <d:column property="describe" title="需求描述"></d:column>
        <d:column property="order_money" title="赏金"></d:column>
        <d:column property="order_status" title="订单状态"></d:column>
        <d:column property="order_date" title="发布时间"></d:column>
        <d:column property="finish_date" title="完成时间"></d:column>         
        <d:column href="" paramId="order_id" paramProperty="order_id" title="我要接单" value="我要接单"></d:column>
        <%User user = (User)request.getAttribute("user"); %>
          <%if(user!=null&&user.getIdentityId()==1){%><d:column href="" paramId="order_id" paramProperty="order_id" title="删除" value="删除"></d:column>
          <%}%>
      </d:table>
		</div>
	</form>
</body>
</html>