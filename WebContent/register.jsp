<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="resource/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="resource/assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="resource/assets/css/ace.min.css" />
<link rel="stylesheet" href="resource/assets/css/ace-rtl.min.css" />


<!-- 输入验证 -->
<script src="resource/jquery-validation-1.17.0/lib/jquery.js"></script>
<script src="resource/jquery-validation-1.17.0/dist/jquery.validate.js"></script>
<script src="resource/jquery-validation-1.17.0/dist/localization/messages_zh.js"></script>

<style>
.error{
	color:red;
}
</style>

</head>
<body class="login-layout">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<i class="icon-leaf green"></i> <span class="red"></span> <span
									class="white">校园生活系统</span>
							</h1>
							<h4 class="blue"></h4>
						</div>
						<div class="space-6"></div>
						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="icon-coffee green"></i> 注册
										</h4>

										<div class="space-6"></div>
										<form id="commentForm" action="LoginServlet?flag=register" method="post" onsubmit="return check()">
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input id="userId"
														required name="userName"  type="text" class="form-control"
														placeholder="请输入您真实姓名" /> <i class="icon-user"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input id="userPw"
														required name="password" type="password" class="form-control"
														placeholder="请输入您的密码" /> <i class="icon-lock"></i>
												</span>
												</label>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input id="userNumber"
														required name="userEmail"  type="text" class="form-control"
														placeholder="请输入您的邮箱" /> <i class="icon-user"></i>
												</span>
												</label>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input id="userAge"
														required name="address"  type="text" class="form-control"
														placeholder="请输入您的地址" /> <i class="icon-user"></i>
												</span>
												</label>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input id="userSex"
														required name="userTel"  type="text" class="form-control"
														placeholder="请输入您的电话" /> <i class="icon-user"></i>
												</span>
												</label>
												
												<div class="clearfix">
													<button type="submit"
														class="width-35 pull-left btn btn-sm btn-primary">
														<i class="icon-key"></i> 注册
													</button>
													
													<!-- <a href="view/system/main/register.jsp" class="width-35 pull-right btn btn-sm btn-primary">注册</a> -->
													
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>

									</div>

								</div>

							</div>


					</div>

				</div>
			</div>

		</div>
</div>
	</div>

</body>
</html>