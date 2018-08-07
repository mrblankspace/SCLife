<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="sys/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="sys/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="sys/assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<p>跳转中，请稍等。。。
	 <form style='display:none'  id='formpay' name='formpay' method='post' action='https://pay.paysapi.com'>
        <input name='goodsname' id='goodsname' type='text' value= <%=request.getAttribute("goodsname")%>>
        <input name='istype' id='istype' type='text' value=<%=request.getAttribute("istype")%>>
        <input name='key' id='key' type='text' value= <%=request.getAttribute("key")%>>
        <input name='notify_url' id='notify_url' type='text' value= <%=request.getAttribute("notify_url")%>>
        <input name='orderid' id='orderid' type='text' value=${orderid}>
        <input name='orderuid' id='orderuid' type='text' value= <%=request.getAttribute("orderuid")%>>
        <input name='price' id='price' type='text' value= <%=request.getAttribute("price")%>>
        <input name='return_url' id='return_url' type='text' value= <%=request.getAttribute("return_url")%>>
        <input name='uid' id='uid' type='text' value= <%=request.getAttribute("uid")%>>
        <input type='submit' id='submitdemo1'>         
 	   </form>
 	   
 	   <script>
 	  window.onload=function(){
 		 formpay.submit();
 		 } 
 			
 	   </script>
</body>
</html>