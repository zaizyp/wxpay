<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>支付页面</title>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<h1>支付测试</h1>

<br>
<div>金额：1分</div>
<button id="wxpay">确认支付</button>


<div id="error"></div>

<script type="text/javascript">

	function onBridgeReady(){
		WeixinJSBridge.invoke(
	      'getBrandWCPayRequest', {
	         "appId":"${data.appId}",     //公众号名称，由商户传入     
	         "timeStamp":"${data.timeStamp}",         //时间戳，自1970年以来的秒数     
	         "nonceStr":"${data.nonceStr}", //随机串     
	         "package":"${data.Pagkage}",     
	         "signType":"MD5",         //微信签名方式：     
	         "paySign":"${data.paysign}" //微信签名 
			},
			function(res){
			if(res.err_msg == "get_brand_wcpay_request:ok" ){
	      // 使用以上方式判断前端返回,微信团队郑重提示：
	            //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
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
			}
		}); 
	}
	if (typeof WeixinJSBridge == "undefined"){
	   if( document.addEventListener ){
	       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	   }else if (document.attachEvent){
	       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
	       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	   }
	}else{
	   onBridgeReady();
	}

</script>
</body>
</html>