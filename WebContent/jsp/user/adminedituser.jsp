<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="cn.swpu.entity.User"%>
     
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
<form action="AdminServlet?flag=save"  method="post" class="definewidth m20">
<%User user=(User)request.getAttribute("user"); %>
<input type="hidden" name="id" value="<%=user.getId() %>" />
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">昵称</td>
            <td><input type="text" name="username" value="<%=user.getUsername() %>"/></td>
        </tr>
         <tr>
            <td width="10%" class="tableleft">登陆邮箱</td>
            <td><input type="text" name="email" value="<%=user.getEmail() %>"/></td>
        </tr>
        <tr>
            <td class="tableleft">密码</td>
            <td><input type="text" name="password" value="<%=user.getPassword()%>"/></td>
        </tr>
         <tr>
            <td width="10%" class="tableleft">电话</td>
            <td><input type="text" name="tel" value="<%=user.getTel() %>"/></td>
        </tr>
         <tr>
            <td width="10%" class="tableleft">地址</td>
            <td><input type="text" name="address" value="<%=user.getAddress() %>"/></td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" class="btn btn-primary" type="button">保存</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="{:U('User/index')}";
		 });

    });
</script>