<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>

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

<script type="text/javascript">
	/* $().ready(function() {
	 $("#commentForm").validate();
	 }); */
	$().ready(function() {
		// 在键盘按下并释放及提交后验证提交表单
		$("#commentForm").validate({
			rules : {
				bookPrice : {
					number : true,
				},
				bookSum : {
					number : true,
				},
			},
			messages : {
			//可以自定义提示信息
			}
		})
	});
</script>
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
  <form action="system/book/insert" id="commentForm"
    class="form-horizontal" method="post">
    <h5 class="page-header alert-info"
      style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
    <!-- 开始1 -->
    <div class="row">
      <div class="col-xs-9">
        <div class="form-group ">
          <label class="col-xs-3 control-label">类型</label>
          <div class="col-xs-9 ">
            <select name="condition" class="form-control">
              <option value="bookName">快递 外卖</option>
              <option value="bookAuthor">服务外包</option>
              <option value="bookAuthor">兼职</option>
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
      <div class="col-xs-3 col-xs-offset-4">
        <input type="submit" class="btn btn-success" value="提交需求" /> <input
          type="reset" class="btn btn-danger" value="重置信息" />
      </div>

    </div>

  </form>
</body>
</html>