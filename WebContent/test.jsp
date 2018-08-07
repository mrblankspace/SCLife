<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<form style='display:none' id='formpay' name='formpay' action="AddOrderServelet" method="post">
		<tr><td>跳转回主页中，请稍等</td></tr>
		<input type="submit" value="跳转">
		</form>
		
		 <script>
 	  window.onload=function(){
 		 formpay.submit();
 		 } 
 			
 	   </script>
</body>
</html>