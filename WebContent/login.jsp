<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
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
											<i class="icon-coffee green"></i> 请输入您的邮箱和密码
										</h4>

										<div class="space-6"></div>
										<form action="LoginServlet?flag=login" method="post"
											onsubmit="">
											<fieldset>
												邮箱：<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														id="userId" name="userEmail" value="" type="text"
														class="form-control" placeholder="请输入您的邮箱" /> <i
														class="icon-user"></i>
												</span>
												</label> 密码：<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														id="userPw" name="password" value="" type="password"
														class="form-control" placeholder="请输入您的密码" /> <i
														class="icon-lock"></i>
												</span>
												</label>  
                                                                                                                               
                                                                                                                                验证码：<label class="block clearfix"> <input type="text" name="image" class="form-control" style="width:72%;display:inline"> <img
													src="VerifyCodeServlet"> <br>
										     	<a href="LoginServlet?flag=login"
													> <i
														class="ace-icon fa fa-power-off"></i> 看不清？换一张！
												</a> <font color="red">${imageMess}</font> <br>
												<br> <br>
                                                </label> 
												<div class="clearfix">


													<a href="register.jsp"
														class="width-35 pull-left btn btn-sm btn-primary">注册</a>
													<button type="submit"
														class="width-35 pull-right btn btn-sm btn-primary">
														<i class="icon-key"></i> 登陆
													</button>


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
