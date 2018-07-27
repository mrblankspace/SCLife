<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>我的信息</title>
<link href="../resource/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="../resource/js/jquery.min.js"></script>
<script type="text/javascript"
	src="../resource/js/bootstrap.min.js"></script>
</head>
<body>
	<div>
		<ul class="breadcrumb" style="margin: 0px;">	
			<li>个人信息</li>
		</ul>
	</div>

		<input type="hidden" name="userId" value="${userLogin.userId }"/>
		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
		<!-- 开始1 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">昵称</label>
					<div class="col-xs-9 ">
						<p class="form-control-static">${userLogin.userName }</p>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">邮件</label>
					<div class="col-xs-9 ">
						<p class="form-control-static">${userLogin.email }</p>
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
						<p class="form-control-static">${userLogin.address}</p>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">电话</label>
					<div class="col-xs-9 ">					
						<p class="form-control-static">${userLogin.tel }</p>
					</div>
				</div>
			</div>

		</div>
		<!--结束2 -->
		
		<!-- 开始3 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户身份</label>
					<div class="col-xs-9 ">
					
						<p class="form-control-static">${userLogin.userMark }</p>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
				</div>
			</div>
		</div>
		<!--结束3 -->
      <input type="submit" class="btn btn-success" value="修改" />
	
<h5 class="page-header alert-info"
      style="margin: 0px; padding: 10px; margin-bottom: 10px;">我的发布</h5>
<h5 class="page-header alert-info"
      style="margin: 0px; padding: 10px; margin-bottom: 10px;">我接过的单</h5>


</body>
</html>