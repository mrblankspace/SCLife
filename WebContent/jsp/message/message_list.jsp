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
<title>我的消息列表</title>

<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<!-- 引入bootstrap-table样式 -->
<link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css" rel="stylesheet">
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript" src="resource/js/bootstrap.min.js"></script>
<!-- bootstrap-table.min.js -->
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<!-- 引入中文语言包 -->
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>

</head>
<body>
	<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>主页</li>
			<li>我的消息</li>
		</ul>
	</div>
 <!-- js 方式渲染表格 -->
 <div class="container-fluid" style="padding: 5px; margin: 5px">
        <div class="row">
            <div class="col-md-12">
                <div style="margin-bottom:10px">
                    <div id="toolbar" class="btn-group">
                        <button type="button" class="btn btn-default glyphicon glyphicon-remove" id="btn-del">删除</button>
                    </div>
                </div>
              
              <table id="table"></table>
            </div>
        </div>
    </div>
    </div>
 
<script>

function del(id){
	$.post("");
}
$(function() {
    /*load页面之后，加载数据*/
    initTable();
});
function initTable(){
	
$("#table").bootstrapTable({ // 对应table标签的id
	  method : 'get',
      url: "<%=request.getContextPath()%>/MessageServlet?flag=queryAllMessage", 
      cache: true, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
      contentType : "application/json", 
      dataType : 'json',
      toolbar: '#toolbar',
      showRefresh: true,
      search: true,
      striped: true,  //表格显示条纹，默认为false
      showToggle: true,
      showColumns: true,
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
              checkbox: true, // 显示一个勾选框
              align: 'center' // 居中显示
          }, {
              field: 'from_person.username', // 返回json数据中的name
              title: '发送人', // 表格表头显示文字
              align: 'center', // 左右居中
              valign: 'middle' // 上下居中
          }, {
              field: 'content',
              title: '消息内容',
              align: 'center',
              valign: 'middle'
          }, {
              field: 'date',
              title: '发送时间',
              align: 'center',
              valign: 'middle',          
          },{
              field: 'status',
              title: '状态',
              align: 'center',
              valign: 'middle'
          },{
              title: "操作",
              align: 'center',
              valign: 'middle',
              width: 160, // 定义列的宽度，单位为像素px
              formatter: function (value, row, index) {
            	//  window.alert(row.id);
                  return '<a href="MessageServlet?flag=deleteMessage&messageId='+row.id+'"<button class="btn btn-primary btn-sm" onclick="del(\'' + row.id + '\')">删除</button></a>';
              }
          }
      ],
      onLoadSuccess: function(){  //加载成功时执行
            console.info("加载成功");
      },
      onLoadError: function(){  //加载失败时执行
            console.info("加载数据失败");
      },
      onClickCell: function(row){
    	  
      }

})
}
</script>
</body>
</html>