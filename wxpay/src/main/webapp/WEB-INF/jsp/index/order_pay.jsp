<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单支付</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="">
<link rel="stylesheet" href="lib/weui.min.css">
<link rel="stylesheet" href="css/jquery-weui.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body ontouchstart>
<!--主体-->
<header class="wy-header">
  <div class="wy-header-icon-back"><span></span></div>
  <div class="wy-header-title">订单支付</div>
</header>
<div class="weui-content">
	<div class="weui-form-preview">
		<div class="weui-form-preview__hd">
			<label class="weui-form-preview__label">付款金额</label> <em
				class="weui-form-preview__value">¥2400.00</em>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">商品</label> <span
					class="weui-form-preview__value">电动打蛋机</span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">标题标题</label> <span
					class="weui-form-preview__value">名字名字名字</span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">标题标题</label> <span
					class="weui-form-preview__value">很长很长的名字很长很长的名字很长很长的名字很长很长的名字很长很长的名字</span>
			</div>
		</div>
		<div class="weui-form-preview__ft">
			<a class="weui-form-preview__btn weui-form-preview__btn_default"
				href="javascript:">辅助操作</a>
			<button type="submit"
				class="weui-form-preview__btn weui-form-preview__btn_primary"
				href="javascript:">立即支付</button>
		</div>
	</div>
</div>

<script src="lib/jquery-2.1.4.js"></script> 
<script src="lib/fastclick.js"></script> 
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>

<script src="js/jquery-weui.js"></script>
</body>
</html>
