<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<title>我要接单</title>

<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<!-- 引入bootstrap-table样式 -->
<link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css" rel="stylesheet">
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript" src="resource/js/bootstrap.min.js"></script>
<!-- bootstrap-table.min.js -->
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<!-- 引入中文语言包 -->
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="resource/js/message.js"></script>
</head>
<script>
    //var global_from_person_id;
    //var user_id = ${user.id};
  	// var webSocket;
</script>
<body>
	<div style="margin: 10px;">
		<ul class="breadcrumb" style="margin: 0px;">
			<li>首页</li>
			<li>接单</li>
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
		
	</form>
   
 <!-- js 方式渲染表格 -->
 <div class="container-fluid" style="padding: 5px; margin: 5px">
        <div class="row">
            <div class="col-md-12">

                <!--<div>
                    <div id="toolbar" class="btn-group">
                        <button type="button" class="btn btn-default glyphicon glyphicon-pencil" id="btn-add">新增</button>
                        <button type="button" class="btn btn-default glyphicon glyphicon-edit" id="btn-edit">编辑</button>
                        <button type="button" class="btn btn-default glyphicon glyphicon-remove" id="btn-del">删除</button>
                    </div>
                </div>-->
                <table id="table"></table>
            </div>
        </div>
    </div>
 
<script>
$(function() {
    /*load页面之后，加载数据*/
    initTable();
   
});

$("#messageBox",parent.document).on("show.bs.modal",function(){
	var url = 'ws://'+window.location.host+'websocket/chat';
	var user_id = $("#user_id",parent.document).val();//${user.id};
	//window.alert("user_id"+user_id);
	if(user_id==null||user_id==""){
		window.alert("请登录！！！！！");
		return;
	}
	parent.webSocket = new WebSocket("ws://localhost:8080/SCLife/websocket/chat");   //打开聊天框后建立websocket连接	
	parent.webSocket.onmessage=function(e){
    	console.log('receive message',e.data);
    	var data = JSON.parse(e.data);
    	var username = data.from_person.username;
    	var content = data.content;
    	//收到消息后渲染到聊天窗
    	var dislogBody='<div class="itemdiv dialogdiv">'+
      		  '<div class="user">'+
   			  '<img alt="" src="resource/assets/images/avatars/avatar1.png" />'+
   			  '<a href="#">'+username+'</a>'+
 			  '</div>'+
			  '<div class="body">'+
   			  '<div class="time">'+
 		      '<i class="ace-icon fa fa-clock-o"></i>'+
      		  '<span class="green">'+dateFtt("yyyy-MM-dd hh:mm:ss",new Date())+'</span>'+
   			  '</div>'+
 			  '<div class="name">'+
  			  '</div>'+
		  	      '<div class="text">'+content+'</div>'+
				  '</div>';
    	$(".dialogs").append(dislogBody);
    }
    
    parent.webSocket.onclose = function(){
		console.log('webSocket closed');
	}
    parent.webSocket.onopen = function(){
    	console.log('webSocket is opening');
    }
});
$("#messageBox",parent.document).on("hidden.bs.modal",function(){
	var user_id = $("#user_id",parent.document).val();//${user.id};
	//window.alert("user_id"+user_id);
	if(user_id==null||user_id==""){
		//window.alert("请登录！！！！！");
		return;
	}
	parent.webSocket.close();
	$(".dialogs").empty();
});

$(document).ready(function(){
})

function showModal(to_id){
	parent.global_from_person_id = to_id;
	//window.alert(parent.global_from_person_id);
	//websocket = parent.webSocket; 父窗体定义  找不到？
	$("#messageBox",parent.document).modal("show");
}

function initTable(){
	
$("#table").bootstrapTable({ // 对应table标签的id
	  method : 'get',
      url: "<%=request.getContextPath()%>/OrderServlet?flag=findOrder_other", 
      cache: true, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
      contentType : "application/json", 
      dataType : 'json',
      striped: true,  //表格显示条纹，默认为false
      pagination: true, // 在表格底部显示分页组件，默认false
      pageList: [10, 20], // 设置页面可以显示的数据条数
      pageSize: 10, // 页面数据条数
      pageNumber: 1, // 首页页码
      sidePagination: 'client', // 设置为服务器端分页
      queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
          return {
              pageSize: params.limit, // 每页要显示的数据条数
              offset: params.offset, // 每页显示数据的开始行号
              sort: params.sort, // 要排序的字段
              sortOrder: params.order, // 排序规则
              dataId: $("#dataId").val() // 额外添加的参数
          }
      },
      sortName: 'id', // 要排序的字段
      sortOrder: 'desc', // 排序规则
      columns: [
          {
              checkbox: false, // 显示一个勾选框
              align: 'center' // 居中显示
          },{
              field: 'catagory', // 返回json数据中的name
              title: '订单类型', // 表格表头显示文字
              align: 'center', // 左右居中
              valign: 'middle' // 上下居中
          }, {
              field: 'send_person.username',
              title: '发布人',
              align: 'center',
              valign: 'middle'
          }, {
              field: 'describe',
              title: '需求描述',
              align: 'center',
              valign: 'middle',          
          },{
              field: 'order_money',
              title: '赏金',
              align: 'center',
              valign: 'middle'
          },{
              field: 'order_status',
              title: '订单状态',
              align: 'center',
              valign: 'middle'
          },{
              field: 'order_date',
              title: '发布时间',
              align: 'center',
              valign: 'middle'
          },{
              field: 'finish_date',
              title: '完成时间',
              align: 'center',
              valign: 'middle'
          }, {
              title: "操作",
              align: 'center',
              valign: 'middle',
              width: 160, // 定义列的宽度，单位为像素px
              formatter: function (value, row, index) {
            		// window.alert(value);
            		 // window.alert();
            	
                  return  '<c:if test="${user!=null&&user.identityId==0}"><a href="OrderServlet?flag=aceptOrder&&order_id='+row.order_id+'" <button class="btn btn-primary btn-sm" ">我要接单</button></a></c:if>'+
                		 '<c:if test="${user!=null&&user.identityId==1}">&emsp;<a href="OrderServlet?flag=deleteOrder&&order_id='+row.order_id+'"  <button class="btn btn-danger btn-sm"">删除</button></a></c:if>';
              }
          }
      ],
      onLoadSuccess: function(){  //加载成功时执行
            console.info("加载成功");
      },
      onLoadError: function(){  //加载失败时执行
            console.info("加载数据失败");
      },onDblClickRow: function(row){
    	 // window.alert(row.send_person.username);
    	  showModal(row.send_person.id);
      }

})
}
</script>
</body>
</html>