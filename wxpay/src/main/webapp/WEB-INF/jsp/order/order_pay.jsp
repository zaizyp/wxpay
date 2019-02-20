<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>支付页面</title>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.4.0.js"></script>
</head>
<body>
<h1>支付测试</h1>
<%-- <h2>session：<% out.print(session.getAttribute("openid").toString()); %></h2> --%>

<br>
<div>金额：1分</div>
<button id="wxpay">确认支付</button>


<div id="error"></div>

<script type="text/javascript">

$(document).ready(function(){

	wx.config({
		debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		appId: "${config.appId}", // 必填，公众号的唯一标识
		timestamp: "${config.timestamp}", // 必填，生成签名的时间戳
		nonceStr: "${config.noncestr}", // 必填，生成签名的随机串
		signature: "${config.signature}",// 必填，签名
		jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表
	});

	
	wx.ready(function(){
	    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
		$("#wxpay").click(function(){
			
	    	wx.chooseWXPay({
				timestamp: "${data.timeStamp}", // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
				nonceStr: "${data.nonceStr}", // 支付签名随机串，不长于 32 位
				package: "${data.Pagkage}", // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=\*\*\*）
				signType: "MD5", // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
				paySign: "${data.paysign}", // 支付签名
				success: function (res) {
				// 支付成功后的回调函数
					$("#error").text("支付成功，订单号为："+"${data.orderId}");
					$.get("${pageContext.request.contextPath}/wx/orderQuery?orderId=${data.orderId}",
			        	function (data,status){
			        		if(status=="success"){
			            		if(data.status==200&data.data=="SUCCESS"){
			            			alert("支付成功");
			            			window.location.href="${pageContext.request.contextPath}/order/success";
			            		}else{
			            			alert("支付失败"+data.msg+data.data);
			            		}
			            	}else{
			            		alert("查询失败");
		            		}
						});
				},
				fail: function (res){
					console.log(res.errMsg);
					alert(res.errMsg);
				}
			});
		});
	});
	
	wx.error(function(res){
	    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
		$("#error").text(res);
	});
	
});

</script>
</body>
</html>