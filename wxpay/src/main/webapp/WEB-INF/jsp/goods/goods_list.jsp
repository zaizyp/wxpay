<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>微信支付</title>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.4.0.js"></script>
</head>
<body>
<div>商品名称：手机</div>
<div>商品价格：1分</div>
<div>商品库存200</div>
<div>颜色：红色</div>
<h1>
	<a href="${pageContext.request.contextPath}/order/pay?orderId=118923">购买商品</a>
	<a href="${pageContext.request.contextPath}/order/test?orderId=118923">测试购买商品</a>
</h1>
<script type="text/javascript">

</script>

</body>
</html>