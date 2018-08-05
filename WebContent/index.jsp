<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="d" uri="http://displaytag.sf.net"%>
<%@ page import="cn.swpu.entity.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>主页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta charset="utf-8" />
<link href="resource/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="resource/assets/font-awesome/4.5.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="resource/assets/css/fonts.googleapis.com.css" />
<link rel="stylesheet" href="resource/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="resource/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="resource/assets/css/ace-skins.min.css" />
<script src="resource/assets/js/ace-extra.min.js"></script>
<script src="resource/assets/js/jquery-2.1.4.min.js"></script>
<script src="resource/js/dateformat.js"></script>
<script>
	var global_from_person_id;
	var user_id = ${user.id};
	var webSocket;
	//获取消息
	function getMessage(){
		$.post("${pageContext.request.contextPath }/MessageServlet?flag=queryMessage",function(data){
			var length = data.length;
			$("#showMessage").text(length);
			$("#showMessage1").text(length+"条未读消息");
			$("#messageList").empty();
			$(data).each(function(i,n){	
			$("#messageList").append('<li><a href="javascript:void(0)" onclick="test(this,'+n.from_person.id+')" class="clearfix">'+
					'<input type="hidden" name="message_id" value="'+n.id+'"><img src="resource/assets/images/avatars/avatar.png" class="msg-photo" alt="'+n.to_person.username+'" />'+
					'<span class="msg-body">'+
					'<span class="msg-title">'+
							'<span class="blue">'+n.from_person.username+': </span>'+n.content+'</span>'+
						'<span class="msg-time">'+
							'<i class="ace-icon fa fa-clock-o"></i>'+
							'<span>'+n.date+'</span></span></span></a></li>');
		});
	},"json");	
	}
	//阅读消息 同时建立websocket链接    单人聊天框关闭事件触发  关闭websocket连接
	function readMessage(messageId){
		//var a = $("#messageList > input:hidden").val();
		//window.alert(messageId);
		$.post("${pageContext.request.contextPath }/MessageServlet?flag=readMessage",{"messageId":messageId},function(data){
			window.alert("messageId"+messageId);//为啥子回调没成功
		},"json");
	}
	
	//点击特定消息后触发
	function test(atag,userId){
		global_from_person_id = userId;
		$("#messageBox").modal("show");
		readMessage($(atag).children("input").val());
	}
	
	$(function(){
		//ajax轮询    未读消息
		 window.setInterval("getMessage()",10000);
	})
	
	
	$(document).ready(function(){
		//注册模态框事件
		$("#messageBox").on("show.bs.modal",function(){
			var url = 'ws://'+window.location.host+'websocket/chat';
			webSocket = new WebSocket("ws://localhost:8080/SCLife/websocket/chat");   //打开聊天框后建立websocket连接
			//webSocket.send("helloworld");   
			//为发送按钮注册一个点击事件
			//var json = {"from_person_id":"3","to_person_id":"1","content":"小伙子很帅"};
			//var data=JSON.stringify(json);
			//webSocket.send(data);
			
			//回显最新的	10条数据
			/*$.post("${pageContext.request.contextPath }/MessageServlet?flag=findDialogMessage",{"user_id":user_id,"other_person_id":global_from_person_id},function(data){
				$(data).each(function(i,n){
					var username = n.from_person.username;
		        	var content = n.content;
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
				});
			},"json");*/
			
	        webSocket.onmessage=function(e){
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
	        
	        webSocket.onclose = function(){
				console.log('webSocket closed');
			}
	        webSocket.onopen = function(){
	        	console.log('webSocket is opening');
	        }
		});
		$("#messageBox").on("hidden.bs.modal",function(){
			webSocket.close();
			$(".dialogs").empty();
		});
		 //当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
		//window.onbeforeunload = function(){webSocket.close();}  //刷新好像也能触发
		//聊天框发送事件
		$("#submitBt").on("click",function(){
			//如何取   现在session里有  js代码与jsp过于耦合了  能不能不要在js代码里出现el表达式
			var from_person_id = user_id;
			var to_person_id = global_from_person_id;
			var content = $("#messageInput").val();
		    //window.alert(from_person_id+"to_person_id"+to_person_id+"content"+content);
			if(content!=""){
				//把消息渲染在自己的聊天框内 
				//window.alert("laile");
				//$(".dialogs").append('<div class="itemdiv dialogdiv"><div class="user"> <img alt="Alexas Avatar" src="resource/assets/images/avatars/avatar1.png"><div class="user"></div>');
				//$(".cloneable").clone().prependTo(".dialogs");
				//唉还是拼接吧
				var dislogBody='<div class="itemdiv dialogdiv">'+
                '<div class="user">'+
                '<img alt="" src="resource/assets/images/avatars/avatar1.png" />'+
                ' <a href="#">me:</a>'+
                '</div>'+
                '<div class="body">'+
                '<div class="time">'+
                '<i class="ace-icon fa fa-clock-o"></i>'+
                '<span class="green">'+dateFtt("yyyy-MM-dd hh:mm:ss",new Date())+'</span>'+
                '</div>'+
                '<div class="name">'+
                '</div>'+
                '<div class="text">'+content+'</div>'+
                '</div>'
                //消息渲染到聊天框
				$(".dialogs").append(dislogBody);
                var json = {"from_person_id":from_person_id,"to_person_id":to_person_id,"content":content};
                var data=JSON.stringify(json);
                //发送websocket消息 //把消息发送给聊天对象
                webSocket.send(data);
			}else{
				window.alert("消息不能为空哦");
			}
			$("#messageInput").val("");
		});
		
		
});
</script>
</head>
<body class="no-skin">
  <div class="navbar navbar-default ace-save-state" id="navbar">
    <script type="text/javascript">
					try {
						ace.settings.check('navbar', 'fixed')
					} catch (e) {
					}
				</script>
    <div class="navbar-container ace-save-state" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span><span class="icon-bar"></span>
		</button>
		
		<div class="navbar-header pull-left">
        <a href="index.jsp" class="navbar-brand"> 
			<small>
            <i class="fa fa-leaf"></i> 校园生活系统
        </small>
        </a>
      </div>
      <!-- /.navbar-header -->
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
        <ul class="nav ace-nav">
          <li class="green dropdown-modal" id="weiduMessage">
			  <a data-toggle="dropdown" class="dropdown-toggle" href="#"> 
				  <i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
				  <span class="badge badge-success" id="showMessage"></span>
          		</a>

               <ul
              class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
             	 <li class="dropdown-header"><i
                class="icon-envelope-alt" ></i><span id="showMessage1"></span>
				  </li>
				  <li class="dropdown-content">
						<ul class="dropdown-menu dropdown-navbar" id="messageList">
				        </ul>
				 </li>
                 <li class="dropdown-footer">
                  <a href="inbox.html">
                    See all messages
                    <i class="ace-icon fa fa-arrow-right"></i>
                  </a>
                </li> 
            </ul>
		 </li>

          <li class="light-blue dropdown-modal"><a data-toggle="dropdown" href="#"
            class="dropdown-toggle"> <img class="nav-user-photo"
              src="resource/assets/images/avatars/user.jpg" alt="User's Photo" />
              <span class="user-info"> <small>欢迎光临</small>,${user.username}<%if(session.getAttribute("user")==null){%>小鲜肉
   
               <ul
              class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
              <li><a href="login.jsp"> <i class="ace-icon fa fa-long-arrow-left"></i> 登陆
              </a></li>         
               </ul>
              
               <%}else{%>
            </span> <i class="icon-caret-down"></i>
          </a>
            <ul
              class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

              <li><a href="http://localhost:8080/SCLife/OrderServlet?flag=findOrderByUser"
                target="mainframe"> <i class="ace-icon fa fa-user"></i> 个人资料
              </a></li>
                                         
              <li class="divider"></li>

              <li><a href="LogoutServlet"> <i
                  class="ace-icon fa fa-power-off"></i> 退出
              </a></li>
            </ul></li>
        </ul><%} %>
        <!-- /.ace-nav -->

      </div>
      <!-- /.navbar-header -->
    </div>
    <!-- /.container -->
  </div>

  <div class="main-container ace-save-state" id="main-container">
       <script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
		</script>
      <div class="sidebar responsive ace-save-state" id="sidebar">
        <script type="text/javascript">
					try{ace.settings.loadState('sidebar')}catch(e){}
				</script>

        <div class="sidebar-shortcuts" id="sidebar-shortcuts">
          <div class="sidebar-shortcuts-large"
            id="sidebar-shortcuts-large">

              <a href="jsp/order/addOrder.jsp"  target="mainframe" > <big>我要发单</big></a>
          </div>
	     <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
          </div>
          
        </div>
        <!-- #sidebar-shortcuts -->

        <ul class="nav nav-list">
          <li class="active">
			  <a id="showIndex"  href="OrderServlet?flag=findOrder_index" target="mainframe">
              <i class="menu-icon fa fa-tachometer"></i> <span class="menu-text">
               主页 </span>
         	 </a>
			  <b class="arrow"></b>
		  </li>
         
			<li> <a href="OrderServlet?flag=findOrder_waimai" target="mainframe"
            > <i class="menu-icon fa fa-desktop"></i> <span
              class="menu-text"> 快递外卖</span> <b
              class="arrow fa fa-angle-down"></b>
          </a></li>
          <li><a href="OrderServlet?flag=findOrder_waibao" target="mainframe"
            > <i class="menu-icon fa fa-desktop"></i> <span
              class="menu-text">服务外包</span> <b
              class="arrow fa fa-angle-down"></b>
          </a>

          </li>
          <li><a href="OrderServlet?flag=findOrder_parttimejob" target="mainframe"
            > <i class="menu-icon fa fa-desktop"></i> <span
              class="menu-text">兼职</span> <b
              class="arrow fa fa-angle-down"></b>
          </a>
         </li>
          <li><a href="OrderServlet?flag=findOrder_other" target="mainframe"
            > <i class="menu-icon fa fa-desktop"></i> <span
              class="menu-text">其他</span> <b
              class="arrow fa fa-angle-down"></b>
          </a></li>
         <%User user = (User)session.getAttribute("user"); %>
          <%if(user!=null&&user.getIdentityId()==1){%>
           <li><a href="AdminServlet?flag=showUsers" target="mainframe"
            > <i class="menu-icon fa fa-desktop"></i> <span
              class="menu-text">用户管理</span> <b
              class="arrow fa fa-angle-down"></b>
          </a></li>
         <%}%>
        </ul>
        <!-- /.nav-list -->
<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>

        <script type="text/javascript">
									try {
										ace.settings.check('sidebar',
												'collapsed')
									} catch (e) {
									}
								</script>
      </div>
      <div class="main-content" id="mains" >
        <iframe id="mainframe" name="mainframe" src="OrderServlet?flag=findOrder_index"
          style="width: 100%; border: 0px;"> </iframe>
 
			 
	</div>

      <script type="text/javascript">
							var height = jQuery(window).height() - 50;
							jQuery("#mainframe").attr("height",
									"" + height + "px;");
	 
	 </script>


    <a href="#" id="btn-scroll-up"
      class="btn-scroll-up btn btn-sm btn-inverse"> <i
      class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
  </div>
  <!-- /.main-container -->
  <!-- basic scripts -->
  
  <div class="col-sm-6 col-sm-offset-3 modal fade"  role="dialog" id="messageBox" aria-hidden="true" align="center" style="position: fixed;margin-top: 5% ;padding-left: 17px;">
       <div class="widget-box">
            <div class="widget-header">
                 <h4 class="widget-title lighter smaller">
                          <i class="ace-icon fa fa-comment blue"></i>
                          Conversation
                        </h4>
            </div>
            <div class="widget-body">
                <div class="widget-main no-padding">
                          <div class="dialogs" style="height: 400px">

                          </div>

                          <form>
                            <div class="form-actions">
                              <div class="input-group">
                                <input id="messageInput" placeholder="Type your message here ..." type="text" class="form-control" name="message" />
                                <span class="input-group-btn">
                                  <button id="submitBt" class="btn btn-sm btn-info no-radius" type="button">
                                      <i class="ace-icon fa fa-share"></i>
                                      Send
                                  </button>
                                </span>
                              </div>
                            </div>
                          </form>
                        </div><!-- /.widget-main -->
            </div><!-- /.widget-body -->
       </div><!-- /.widget-box -->
  </div>


<script type="text/javascript">
			if ("ontouchend" in document)
				document
						.write("<script src='resource/assets/js/jquery.mobile.custom.min.js'>"
								+ "<"+"script>");
		</script>
<script src="resource/assets/js/bootstrap.min.js"></script>
<script src="resource/assets/js/typeahead-bs2.min.js"></script>
<script src="resource/assets/js/ace-elements.min.js"></script>
<script src="resource/assets/js/ace.min.js"></script>
</body>
</html>

