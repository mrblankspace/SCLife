<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@ page import="cn.swpu.entity.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="resource/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="resource/assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="resource/assets/css/ace.min.css" />
<link rel="stylesheet" href="resource/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="resource/assets/css/ace-skins.min.css" />
<script src="resource/assets/js/ace-extra.min.js"></script>
<script src="resource/assets/jquery.min.js"></script>
<script>
 
</script>
</head>
<body>

  <div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
					try {
						ace.settings.check('navbar', 'fixed')
					} catch (e) {
					}
				</script>

    <div class="navbar-container" id="navbar-container">
      <div class="navbar-header pull-left">
        <a href="index.jsp" class="navbar-brand"> <small>
            <i class="icon-leaf"></i> 校园生活系统
        </small>
        </a>
        <!-- /.brand -->
      </div>
      <!-- /.navbar-header -->

      <div class="navbar-header pull-right" role="navigation">
        <ul class="nav ace-nav">
          <li class="green"><a data-toggle="dropdown"
            class="dropdown-toggle" href="#"> <i
              class="icon-envelope icon-animated-vertical"></i> <span
              class="badge badge-success">5</span>
          </a>

            <ul
              class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
              <li class="dropdown-header"><i
                class="icon-envelope-alt"></i> 5条消息</li>
            </ul></li>

          <li class="light-blue"><a data-toggle="dropdown" href="#"
            class="dropdown-toggle"> <img class="nav-user-photo"
              src="resource/assets/avatars/user.jpg" alt="Jason's Photo" />
              <span class="user-info"> <small>欢迎光临,${user.username}<%if(request.getAttribute("user")==null){%>小鲜肉<%}%></small>
            </span> <i class="icon-caret-down"></i>
          </a>
            <ul
              class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

              <li><a href="jsp/user/userinfo_show.jsp"
                target="mainframe"> <i class="icon-user"></i> 个人资料
              </a></li>

              <li class="divider"></li>

              <li><a href="system/user/logout"> <i
                  class="icon-off"></i> 退出
              </a></li>
            </ul></li>
        </ul>
        <!-- /.ace-nav -->

      </div>
      <!-- /.navbar-header -->
    </div>
    <!-- /.container -->
  </div>

  <div class="main-container" id="main-container">
    <script type="text/javascript">
					try {
						ace.settings.check('main-container', 'fixed')
					} catch (e) {
					}
				</script>

    <div class="main-container-inner">
      <a class="menu-toggler" id="menu-toggler" href="#"> <span
        class="menu-text"></span>
      </a>

      <div class="sidebar" id="sidebar">
        <script type="text/javascript">
									try {
										ace.settings.check('sidebar', 'fixed')
									} catch (e) {
									}
								</script>

        <div class="sidebar-shortcuts" id="sidebar-shortcuts">
          <div class="sidebar-shortcuts-large"
            id="sidebar-shortcuts-large">

              <a href="jsp/order/addOrder.jsp"  target="mainframe" > <big>我要发单</big></a>
          </div>

          <div class="sidebar-shortcuts-mini"
            id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span> <span
              class="btn btn-info"></span> <span class="btn btn-warning"></span>
            <span class="btn btn-danger"></span>
          </div>
        </div>
        <!-- #sidebar-shortcuts -->

        <ul class="nav nav-list">
          <li class="active"><a id="showIndex" href="OrderServlet?flag=findOrder_index" target="mainframe">
              <i class="icon-dashboard"></i> <span class="menu-text">
                主页 </span>
          </a></li>
          <li><a href="OrderServlet?flag=findOrder_waimai" target="mainframe"
            class="dropdown-toggle"> <i class="icon-desktop"></i> <span
              class="menu-text"> 快递 外卖</span> <b
              class="arrow icon-angle-down"></b>
          </a></li>
          <li><a href="OrderServlet?flag=findOrder_waibao" target="mainframe"
            class="dropdown-toggle"> <i class="icon-desktop"></i> <span
              class="menu-text">服务外包</span> <b
              class="arrow icon-angle-down"></b>
          </a>

          </li>
        
        
          <li><a href="OrderServlet?flag=findOrder_parttimejob" target="mainframe"
            class="dropdown-toggle"> <i class="icon-desktop"></i> <span
              class="menu-text">兼职 </span> <b
              class="arrow icon-angle-down"></b>
          </a>
         </li>
          <li><a href="OrderServlet?flag=findOrder_other" target="mainframe"
            class="dropdown-toggle"> <i class="icon-desktop"></i> <span
              class="menu-text">其他</span> <b
              class="arrow icon-angle-down"></b>
          </a></li>
         <%User user = (User)request.getAttribute("user"); %>
          <%if(user!=null&&user.getIdentityId()==1){%>
           <li><a href="AdminServlet?flag=showUsers" target="mainframe"
            class="dropdown-toggle"> <i class="icon-desktop"></i> <span
              class="menu-text">用户管理</span> <b
              class="arrow icon-angle-down"></b>
          </a></li>
         <%}%>
        </ul>
        <!-- /.nav-list -->

        <div class="sidebar-collapse" id="sidebar-collapse">
          <i class="icon-double-angle-left"
            data-icon1="icon-double-angle-left"
            data-icon2="icon-double-angle-right"></i>
        </div>

        <script type="text/javascript">
									try {
										ace.settings.check('sidebar',
												'collapsed')
									} catch (e) {
									}
								</script>
      </div>

      <div class="main-content" id="mains">
        <iframe id="mainframe" name="mainframe" src="OrderServlet?flag=findOrder_index"
          style="width: 100%; border: 0px;"> </iframe>

      </div>

      <script type="text/javascript">
							var height = jQuery(window).height() - 50;
							jQuery("#mainframe").attr("height",
									"" + height + "px;");
						</script>

      <div class="ace-settings-container" id="ace-settings-container">
        <div class="btn btn-app btn-xs btn-warning ace-settings-btn"
          id="ace-settings-btn">
          <i class="icon-cog bigger-150"></i>
        </div>

        <div class="ace-settings-box" id="ace-settings-box">
          <div>
            <div class="pull-left">
              <select id="skin-colorpicker" class="hide">
                <option data-skin="default" value="#438EB9">#438EB9</option>
                <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
              </select>
            </div>
            <span>&nbsp; 选择皮肤</span>
          </div>

          <div>
            <input type="checkbox" class="ace ace-checkbox-2"
              id="ace-settings-navbar" /> <label class="lbl"
              for="ace-settings-navbar"> 固定导航条</label>
          </div>

          <div>
            <input type="checkbox" class="ace ace-checkbox-2"
              id="ace-settings-sidebar" /> <label class="lbl"
              for="ace-settings-sidebar"> 固定滑动条</label>
          </div>

          <div>
            <input type="checkbox" class="ace ace-checkbox-2"
              id="ace-settings-breadcrumbs" /> <label class="lbl"
              for="ace-settings-breadcrumbs">固定面包屑</label>
          </div>

          <div>
            <input type="checkbox" class="ace ace-checkbox-2"
              id="ace-settings-rtl" /> <label class="lbl"
              for="ace-settings-rtl">切换到左边</label>
          </div>

          <div>
            <input type="checkbox" class="ace ace-checkbox-2"
              id="ace-settings-add-container" /> <label class="lbl"
              for="ace-settings-add-container"> 切换窄屏 <b></b>
            </label>
          </div>
        </div>
      </div>
      <!-- /#ace-settings-container -->
    </div>
    <!-- /.main-container-inner -->

    <a href="#" id="btn-scroll-up"
      class="btn-scroll-up btn btn-sm btn-inverse"> <i
      class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
  </div>
  <!-- /.main-container -->
  <!-- basic scripts -->




  <script type="text/javascript">
			if ("ontouchend" in document)
				document
						.write("<script src='resource/assets/js/jquery.mobile.custom.min.js'>"
								+ "<"+"script>");
		</script>
  <script src="resource/assets/js/bootstrap.min.js"></script>
  <script src="resource/assets/js/typeahead-bs2.min.js"></script>

  <!-- page specific plugin scripts -->

  <!--[if lte IE 8]>
		  <script src="resource/assets/js/excanvas.min.js"></script>
		<![endif]-->

  <script src="resource/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
  <script src="resource/assets/js/jquery.ui.touch-punch.min.js"></script>
  <script src="resource/assets/js/jquery.slimscroll.min.js"></script>
  <script src="resource/assets/js/jquery.easy-pie-chart.min.js"></script>
  <script src="resource/assets/js/jquery.sparkline.min.js"></script>
  <script src="resource/assets/js/flot/jquery.flot.min.js"></script>
  <script src="resource/assets/js/flot/jquery.flot.pie.min.js"></script>
  <script src="resource/assets/js/flot/jquery.flot.resize.min.js"></script>

  <!-- ace scripts -->

  <script src="resource/assets/js/ace-elements.min.js"></script>
  <script src="resource/assets/js/ace.min.js"></script>


</body>
</html>
