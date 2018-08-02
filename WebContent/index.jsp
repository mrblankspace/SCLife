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
<!-- text fonts -->
<link rel="stylesheet" href="resource/assets/css/fonts.googleapis.com.css" />
<link rel="stylesheet" href="resource/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="resource/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="resource/assets/css/ace-skins.min.css" />
<script src="resource/assets/js/ace-extra.min.js"></script>
<script src="resource/assets/js/jquery-2.1.4.min.js"></script>
<script src="resource/assets/js/bootstrap.min.js"></script>
<script src="resource/assets/js/typeahead-bs2.min.js"></script>
<script>	
	//阅读消息
	function readMessage(){
		window.alert(1111);
	//	var a = $("#messageList > input:hidden").val();
		////window.alert(a);
	}
	function test(atag){
		$("#messageBox").modal("show");
		window.alert($(atag).children("input").val());
	}
	$(function(){
	     	$.post("${pageContext.request.contextPath }/MessageServlet?flag=queryMessage",function(data){
			var length = data.length;
			$("#showMessage").text(length);
			$("#showMessage1").text(length+"条消息");
			$(data).each(function(i,n){	
			$("#messageList").append('<li><a href="javascript:void(0)" onclick="test(this)" class="clearfix">'+
					'<input type="hidden" name="message_id" value="'+n.id+'"><img src="resource/assets/images/avatars/avatar.png" class="msg-photo" alt="'+n.to_person.username+'" />'+
					'<span class="msg-body">'+
					'<span class="msg-title">'+
							'<span class="blue">'+n.to_person.username+': </span>'+n.content+'</span>'+
						'<span class="msg-time">'+
							'<i class="ace-icon fa fa-clock-o"></i>'+
							'<span>'+n.date+'</span></span></span></a></li>');
		});
	},"json");	
	})
	
	$(document).ready(function(){
		$("#messageList").on("shown.bs.modal",function(){
			
		});
		$("#messageList").on("hidden.bs.modal",function(){
			//window.alert(1111111);
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
        <!-- /.brand -->
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
              class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
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

              <li><a href="system/user/logout"> <i
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
              class="menu-text"> 快递 外卖</span> <b
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
						
							
						    $('#messageBox').on('shown.bs.modal', function (e) {
				                // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
				                $(this).css('display', 'block');
				                var modalHeight=$(window).height() / 2 - $('#messageBox.modal-dialog').height() / 2;
				                $(this).find('.modal-dialog').css({
				                    'margin-top': modalHeight
				                });
				            });		 
	 </script>


    <a href="#" id="btn-scroll-up"
      class="btn-scroll-up btn btn-sm btn-inverse"> <i
      class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
  </div>
  <!-- /.main-container -->
  <!-- basic scripts -->
  
   <div class="col-sm-6 col-sm-offset-3 modal fade"  role="dialog" id="messageBox" aria-hidden="true" align="center">
                 <div class="widget-box">
                      <div class="widget-header">
                        <h4 class="widget-title lighter smaller">
                          <i class="ace-icon fa fa-comment blue"></i>
                          Conversation
                        </h4>
                      </div>

                      <div class="widget-body">
                        <div class="widget-main no-padding">
                          <div class="dialogs">
                            <div class="itemdiv dialogdiv">
                              <div class="user">
                                <img alt="Alexa's Avatar" src="resource/assets/images/avatars/avatar1.png" />
                              </div>

                              <div class="body">
                                <div class="time">
                                  <i class="ace-icon fa fa-clock-o"></i>
                                  <span class="green">4 sec</span>
                                </div>

                                <div class="name">
                                  <a href="#">Alexa</a>
                                </div>
                                <div class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque commodo massa sed ipsum porttitor facilisis.</div>

                                <div class="tools">
                                  <a href="#" class="btn btn-minier btn-info">
                                    <i class="icon-only ace-icon fa fa-share"></i>
                                  </a>
                                </div>
                              </div>
                            </div>

                            <div class="itemdiv dialogdiv">
                              <div class="user">
                                <img alt="John's Avatar" src="resource/assets/images/avatars/avatar.png" />
                              </div>

                              <div class="body">
                                <div class="time">
                                  <i class="ace-icon fa fa-clock-o"></i>
                                  <span class="blue">38 sec</span>
                                </div>

                                <div class="name">
                                  <a href="#">John</a>
                                </div>
                                <div class="text">Raw denim you probably haven&#39;t heard of them jean shorts Austin.</div>

                                <div class="tools">
                                  <a href="#" class="btn btn-minier btn-info">
                                    <i class="icon-only ace-icon fa fa-share"></i>
                                  </a>
                                </div>
                              </div>
                            </div>

                            <div class="itemdiv dialogdiv">
                              <div class="user">
                                <img alt="Bob's Avatar" src="resource/assets/images/avatars/user.jpg" />
                              </div>

                              <div class="body">
                                <div class="time">
                                  <i class="ace-icon fa fa-clock-o"></i>
                                  <span class="orange">2 min</span>
                                </div>

                                <div class="name">
                                  <a href="#">Bob</a>
                                  <span class="label label-info arrowed arrowed-in-right">admin</span>
                                </div>
                                <div class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque commodo massa sed ipsum porttitor facilisis.</div>

                                <div class="tools">
                                  <a href="#" class="btn btn-minier btn-info">
                                    <i class="icon-only ace-icon fa fa-share"></i>
                                  </a>
                                </div>
                              </div>
                            </div>

                            <div class="itemdiv dialogdiv">
                              <div class="user">
                                <img alt="Jim's Avatar" src="resource/assets/images/avatars/avatar4.png" />
                              </div>

                              <div class="body">
                                <div class="time">
                                  <i class="ace-icon fa fa-clock-o"></i>
                                  <span class="grey">3 min</span>
                                </div>

                                <div class="name">
                                  <a href="#">Jim</a>
                                </div>
                                <div class="text">Raw denim you probably haven&#39;t heard of them jean shorts Austin.</div>

                                <div class="tools">
                                  <a href="#" class="btn btn-minier btn-info">
                                    <i class="icon-only ace-icon fa fa-share"></i>
                                  </a>
                                </div>
                              </div>
                            </div>

                            <div class="itemdiv dialogdiv">
                              <div class="user">
                                <img alt="Alexa's Avatar" src="resource/assets/images/avatars/avatar1.png" />
                              </div>

                              <div class="body">
                                <div class="time">
                                  <i class="ace-icon fa fa-clock-o"></i>
                                  <span class="green">4 min</span>
                                </div>

                                <div class="name">
                                  <a href="#">Alexa</a>
                                </div>
                                <div class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</div>

                                <div class="tools">
                                  <a href="#" class="btn btn-minier btn-info">
                                    <i class="icon-only ace-icon fa fa-share"></i>
                                  </a>
                                </div>
                              </div>
                            </div>
                          </div>

                          <form>
                            <div class="form-actions">
                              <div class="input-group">
                                <input placeholder="Type your message here ..." type="text" class="form-control" name="message" />
                                <span class="input-group-btn">
                                  <button class="btn btn-sm btn-info no-radius" type="button">
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


  <!-- page specific plugin scripts -->

  <!--[if lte IE 8]>
		  <script src="resource/assets/js/excanvas.min.js"></script>
		<![endif]-->
<!-- 
  <script src="resource/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
  <script src="resource/assets/js/jquery.ui.touch-punch.min.js"></script>
  <script src="resource/assets/js/jquery.slimscroll.min.js"></script>
  <script src="resource/assets/js/jquery.easy-pie-chart.min.js"></script>
  <script src="resource/assets/js/jquery.sparkline.min.js"></script>
  <script src="resource/assets/js/flot/jquery.flot.min.js"></script>
  <script src="resource/assets/js/flot/jquery.flot.pie.min.js"></script>
  <script src="resource/assets/js/flot/jquery.flot.resize.min.js"></script>
 -->
  <!-- ace scripts -->

  <script src="resource/assets/js/ace-elements.min.js"></script>
  <script src="resource/assets/js/ace.min.js"></script>


</body>
</html>

