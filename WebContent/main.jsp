<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="d" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<title>工作台</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="resource/css/bootstrap.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="resource/js/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="resource/js/bootstrap.min.js"></script>
<!--在线引用js文件，离线出现各种问题-->
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>

</head>
<body>
<div style="padding:0px; margin:0px;">
 	<ul class="breadcrumb" style=" padding:0px; padding-left:20px;" >
  		<li ><a href="index.jsp" target="_parent">返回首页</a></li>
	</ul>
  <!-- 这里需要有个隐藏的订单id  后端要注意 -->
    <d:table name="${list}" pagesize="5" requestURI="OrderServlet?flag=findOrder_index" class="table table-hover table-striped table-bordered">
        <d:column property="class_id" title="订单类型"></d:column>
        <d:column property="order_describe" title="需求描述"></d:column>
        <d:column property="order_money" title="赏金"></d:column>
        <d:column property="order_status" title="订单状态"></d:column>
        <d:column property="order_status" title="图书类型"></d:column>
        <d:column property="order_date" title="发布时间"></d:column>         
        <d:column href="" paramId="order_id" paramProperty="bookId" title="我要接单" value=""></d:column>
      </d:table>
</div>


</body>
</html>
