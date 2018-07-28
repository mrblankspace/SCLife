<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  //获取绝对路径路径 
  String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
        + request.getServerName() + ":" + request.getServerPort()
        + path + "/";
%> 
<!DOCTYPE html>
<base href="<%=basePath %>" />
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>我的信息</title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript"
	src="resource/js/bootstrap.min.js"></script>
</head>
<body>
	<div>
		<ul class="breadcrumb" style="margin: 0px;">	
			<li>个人信息</li>
		</ul>
	</div>
          <!-- 注意 -->
		<input type="hidden" name="userId" value="${userId}"/>
		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
		<!-- 开始1 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">昵称</label>
					<div class="col-xs-9 ">
						<p class="form-control-static">${userName}</p>

					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">邮件</label>
					<div class="col-xs-9 ">
						<p class="form-control-static">${email}</p>

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
						<p class="form-control-static">${address}</p>
					</div>
				</div>
			</div>
<div class="col-xs-5">

	  <div class="form-group ">
					<label class="col-xs-3 control-label">电话</label>
					<div class="col-xs-9 ">					
						<p class="form-control-static">${tel}</p>
					</div>
				</div>
			</div>

		</div>
		<!--结束2 -->
		
		<!-- 开始3 -->
		<div class="row">
		  &emsp;&emsp; &emsp;&emsp; &emsp;&emsp; &emsp;&emsp;&emsp; &emsp;&emsp; &emsp;&emsp; &emsp;&emsp; &emsp;&emsp; &emsp;&emsp;<input type="submit" class="btn btn-success" value="修改"/>
<div class="col-xs-5">
    <div class="form-group ">
		      <label class="col-xs-3 control-label">用户身份</label>
		      <div class="col-xs-9 ">
		        <p class="form-control-static">${indentityId}</p>
	          </div>
	        </div>
	      </div>
</div>
<!--结束3 -->
      
<div class="list-group">
  <h5 class="page-header alert-info"
      style="margin: 0px; padding: 10px; margin-bottom: 10px;">我的发布</h5>
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
</div>
<h5 class="page-header alert-info"
      style="margin: 0px; padding: 10px; margin-bottom: 10px;">我接过的单</h5>
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

</body>
</html>