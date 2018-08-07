<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@page import="cn.swpu.entity.*"%>
<%@page import="java.util.List"%>
<%@taglib prefix="d" uri="http://displaytag.sf.net"%>
<% //获取绝对路径路径
String path = request.getContextPath(); String basePath = request.getScheme() +"://" + request.getServerName() + ":" + request.getServerPort() + path +
"/"; %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>我的信息</title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript" src="resource/js/bootstrap.min.js"></script>
</head>
<body>
  <% User user=(User)session.getAttribute("user"); %>

  <div>
    <ul class="breadcrumb" style="margin: 0px;">
      <li>个人信息</li>
    </ul>
  </div>
  <!-- 注意 -->
  <h5 class="page-header alert-info"
    style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
  <!-- 开始1 -->
  <div class="row">
    <div class="col-xs-5">
      <div class="form-group ">
        <label class="col-xs-3 control-label">昵称</label>
        <p class="col-xs-9">${user.getUsername()}</p>
      </div>
    </div>
    <div class="col-xs-5">
      <div class="form-group ">
        <label class="col-xs-3 control-label">邮件</label>
        <div class="col-xs-9 ">
          <p class="">${user.getEmail()}</p>
        </div>
      </div>
    </div>
  </div>
  <!--结束1 -->
  <!-- 开始2 -->
  <div class="row">
    <div class="col-xs-5">
      <div class="form-group ">
        <label class="col-xs-3 control-label">地址</label>
        <div class="col-xs-9 ">
          <p class="">${user.getAddress()}</p>
        </div>
      </div>
    </div>
    <div class="col-xs-5">

      <div class="form-group ">
        <label class="col-xs-3 control-label">电话</label>
        <div class="col-xs-9 ">
          <p class="">${user.getTel()}</p>
        </div>
      </div>
    </div>

  </div>
  <!--结束2 -->

  <!-- 开始3 -->
  <div class="row">
    &emsp;&emsp; &emsp;&emsp; &emsp;&emsp; &emsp;&emsp;&emsp;
    &emsp;&emsp; &emsp;&emsp; &emsp;&emsp; &emsp;&emsp; &emsp;&emsp;
    <div class="col-xs-10">
    <form class="form-inline definewidth m20"
      action="LoginServlet?flag=edit" method="post">
      &emsp;&emsp; &emsp;&emsp; &emsp;&emsp; &emsp;&emsp; <input
        type="hidden" name="id" value="<%=user.getId()%>" />
      &emsp;&emsp; &emsp;&emsp; &emsp;&emsp; &emsp;&emsp; &emsp;&emsp; 
      <input type="submit" class="btn btn-success" value="编辑"  style="float:right"/>
    </form>
    </div>
    <div class="">
      <div class="form-group ">
        <!--   <label class="col-xs-3 control-label">用户身份</label>-->
        <div class="">
          <p class="form-control-static">${indentityId}</p>
        </div>
      </div>
    </div>
  </div>
  <br>
  <!--结束3 -->

  <div class="list-group">
    <h5 class="page-header alert-info"
      style="margin: 0px; padding: 10px; margin-bottom: 10px;">我发布的单</h5>
    <div class="row" style="padding: 15px; margin: 10px 5px 5px 10px">
      <d:table name="${listBySendId}" pagesize="5"
        requestURI="http://localhost:8080/SCLife/OrderServlet?flag=findOrderByUser"
        class="table table-hover table-striped table-bordered">
        <d:column property="catagory" title="订单类型"></d:column>
        <d:column property="accept_person.username" title="接单人"></d:column>
        <d:column property="describe" title="订单描述"></d:column>
        <d:column property="order_money" title="赏金"></d:column>
        <d:column property="order_status" title="订单状态"></d:column>
        <d:column property="order_date" title="发布时间"></d:column>
        <d:column property="finish_date" title="完成时间"></d:column>
        <d:column href="OrderServlet?flag=updateOrder_Status1"
          paramId="order_id" paramProperty="order_id" title="取消订单"
          value="取消订单"></d:column>
        <d:column href="OrderServlet?flag=updateOrder_Status2"
          paramId="order_id" paramProperty="order_id" title="确认收货"
          value="确认收货"></d:column>

      </d:table>
    </div>
  </div>


  <h5 class="page-header alert-info"
    style="margin: 0px; padding: 10px; margin-bottom: 10px;">我接过的单</h5>
  <div class="row" style="padding: 15px;">
    <d:table name="${listByAcptId}" pagesize="5"
      requestURI="http://localhost:8080/SCLife/OrderServlet?flag=findOrderByUser"
      class="table table-hover table-striped table-bordered">
      <d:column property="catagory" title="订单类型"></d:column>
      <d:column property="send_person.username" title="发单人"></d:column>
      <d:column property="describe" title="订单描述"></d:column>
      <d:column property="order_money" title="赏金"></d:column>
      <d:column property="order_status" title="订单状态"></d:column>
      <d:column property="order_date" title="发布时间"></d:column>
      <d:column property="finish_date" title="完成时间"></d:column>
      <d:column href="OrderServlet?flag=updateOrder_Status3" paramId="order_id" paramProperty="order_id" title="取消订单" value="取消订单"  ></d:column>
    </d:table>
  </div>

</body>
</html>