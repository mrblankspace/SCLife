<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%
  //获取绝对路径路径 
  String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
        + request.getServerName() + ":" + request.getServerPort()
        + path + "/";
    
    System.out.print("path");
%> 
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>我要发单</title>
<link href="../resource/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="../resource/js/jquery.min.js"></script>
<script type="text/javascript" src="../resource/js/bootstrap.min.js"></script>

<!-- 输入过滤 -->
<script src="../resource/jquery-validation-1.17.0/lib/jquery.js"></script>
<script
  src="../resource/jquery-validation-1.17.0/dist/jquery.validate.js"></script>
<script
  src="../resource/jquery-validation-1.17.0/dist/localization/messages_zh.js"></script>

<style>
.error {
	color: red;
}
</style>

</head>
<body>
  <div>
    <ul class="breadcrumb" style="margin: 0px;">
      <li>我要发单</li>
      <li>订单添加</li>
    </ul>
  </div>
  <!-- 表单在这 -->
  <form action="http://localhost:8080/SCLife/PayServelet" id=""
    class="form-horizontal" method="post">
    <h5 class="page-header alert-info"
      style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
    <!-- 开始1 -->
    <div class="container">
    <div class="row">
      <div class="col-xs-9">
        <div class="form-group
        ">
          <label class="col-xs-3 control-label">类型</label>
          <div class="col-xs-9 ">
            <select name="ordertype" class="form-control">
              <option  value="1">快递 外卖</option>
              <option  value="2">服务外包</option>
              <option  value="3">兼职</option>
              <option  value="4">其他</option>
            </select>
          </div>
          <label class="col-xs-3 control-label" style="margin-top:20px">订单描述</label>
          <div class="col-xs-9 " style="margin-top:20px">
            <textarea class="form-control" rows="3" name="textarea"></textarea>
          </div>

        </div>
      </div>
    </div>

 <div class="row">
 <div class="col-xs-9">
       <label class="col-xs-3 control-label">定金</label>  <div class="col-xs-3"><input name="inputprice" type="text" class="form-control col-md-3" ></div>
          <label class="col-xs-3 control-label">支付方式</label> 
          <div class="col-xs-3 ">
            <select name="payway" class="form-control">
              <option value="1">支付宝</option>
              <option value="2">微信</option>

            </select>
          </div>
        </div>
          
 </div>
    <div class="row" style="margin-top:20px">
      <div class="col-md-3 col-md-offset-3">
        <input type="submit" class="btn btn-success" value="提交需求" /> 
      </div>
       <div class="col-md-3 ">
        <input type="reset" class="btn btn-danger" value="重置需求" /> 
      </div>

    </div>
    </div>
  </form>
</body>
</html>