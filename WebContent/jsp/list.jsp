<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>订单查询</title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript" src="resource/js/bootstrap.min.js"></script>

</head>
<body>
	<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>后台传过来</li>
			<li>接单</li>
		</ul>
	</div>
	<form action="" method="post" class="form-inline">
		<div class="row alert alert-info" style="margin: 10px; padding: 5px; text-align: center;">
			<div class="form-group">
				<label>条件:</label> 
				<select name="condition" class="form-control">
					<option value="bookName">地点</option>
					<option value="bookAuthor">类型</option>
				</select>
				<input type="text" name="content" value="${result }" class="form-control" placeholder="请输入查询条件" />
			</div>
			<input type="submit" class="btn btn-danger" value="查询"> 		
		</div>
		<div class="row" style="padding: 15px;">		
			 <d:table name="" pagesize="5" requestURI="" class="table table-hover table-striped table-bordered">
        <d:column property="class_id" title="订单类型"></d:column>
        <d:column property="order_describe" title="需求描述"></d:column>
        <d:column property="order_money" title="赏金"></d:column>
        <d:column property="order_status" title="订单状态"></d:column>
        <d:column property="order_status" title="图书类型"></d:column>
        <d:column property="order_date" title="发布时间"></d:column>         
        <d:column href="" paramId="order_id" paramProperty="bookId" title="我要接单" value="${order_id }"></d:column>
      </d:table>
		</div>
	</form>
</body>
</html>