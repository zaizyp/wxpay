<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单确认</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-weui/lib/weui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-weui/css/jquery-weui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body ontouchstart>
<!--主体-->
<header class="wy-header">
  <div class="wy-header-icon-back"><span></span></div>
  <div class="wy-header-title">订单详情</div>
</header>
<div class="weui-content">
  <!-- 默认收货地址  -->
  <div class="wy-media-box weui-media-box_text address-select">
    <div class="weui-media-box_appmsg">
      <div class="weui-media-box__hd proinfo-txt-l" style="width:20px;"><span class="promotion-label-tit"><img src="${pageContext.request.contextPath}/images/icon_nav_city.png" /></span></div>
      <div class="weui-media-box__bd">
        <a href="javascript:selectAddress();" class="weui-cell_access">
          <h4 class="address-name">
            <span id="addressName">${defaultAddress.name}</span>
            <span id="addressPhone">${defaultAddress.telphone}</span>
          </h4>
          <div class="address-txt" id="addressInfo">
            ${defaultAddress.province}${defaultAddress.city}${defaultAddress.area}${defaultAddress.street}
          </div>
        </a>
      </div>
      <div class="weui-media-box__hd proinfo-txt-l" style="width:16px;"><div class="weui-cell_access"><span class="weui-cell__ft"></span></div></div>
    </div>
  </div>
  <!-- 默认收货地址结束  -->
  
  <!-- 选择一个收货地址  -->
  <div id="" class="weui-popup__container">
	  <div class="weui-popup__overlay"></div>
	  <div class="weui-popup__modal">
      <iframe width="100%" height="100%" src="${pageContext.request.contextPath}/address/list"></iframe>
	  </div>
  </div>
  <!-- 选择收货地址结束  -->
  <form>
  <!-- 订单商品详情开始  -->
  <c:forEach items="${itemSkuVoMap}" var="itemSkuVoList">
	  <div class="wy-media-box weui-media-box_text">
	    <div class="weui-media-box__hd">${itemSkuVoList.key.shopName}</div>
	    <div class="weui-media-box__bd">
	      <c:forEach items="${itemSkuVoList.value}" var="itemSkuVo">
		      <div class="weui-media-box_appmsg ord-pro-list">
		        <div class="weui-media-box__hd">
		          <a href="${pageContext.request.contextPath}/item/${itemSkuVo.itemId}">
		            <img class="weui-media-box__thumb" 
		              src="${pageContext.request.contextPath}${(fn:split(itemSkuVo.item.image,','))[0]}" alt="">
		          </a>
		        </div>
		        <div class="weui-media-box__bd">
		          <h1 class="weui-media-box__desc">
		            <a href="${pageContext.request.contextPath}/item/${itemSkuVo.itemId}"
		              class="ord-pro-link">${itemSkuVo.item.title}</a>
		          </h1>
		          <p class="weui-media-box__desc">规格：
		            <%-- <c:forTokens items="${itemSkuVo.itemSpecValueList}" var="specValue" delims="，">
		              <span>${specValue.value}</span>

		            </c:forTokens> --%>
		            <c:forEach items="${itemSkuVo.itemSpecValueList}" var="specValue" varStatus="status">
		              <span>${specValue.value}</span>
		              <c:if test="${!status.last}">,</c:if>
		            </c:forEach>
		          </p>
		          <div class="clear mg-t-10">
		            <div class="wy-pro-pri fl">¥<em class="num font-15">${itemSkuVo.price / 100}</em></div>
		            <div class="pro-amount fr"><span class="font-13">数量×<em class="name">${itemSkuVo.num}</em></span></div>
		          </div>
		        </div>
		      </div>
	      </c:forEach>
	    </div>
	  </div>
		<!-- 买家留言开始  -->
	  <div class="weui-cells__title">买家留言</div>
	  <div class="weui-cells weui-cells_form">
	    <div class="weui-cell">
	      <div class="weui-cell__bd">
	        <textarea name="message['${itemSkuVoList.key.id}']" class="weui-textarea" placeholder="请输入留言" rows="2"></textarea>
	        <div class="weui-textarea-counter"><span>0</span>/200</div>
	      </div>
	    </div>
	  </div>
	  <!-- 买家留言结束  -->
  </c:forEach>
  <!-- 订单商品详情结束  -->
    <!-- 当前订单标识  -->
    <input type="hidden" name="currentOrderId" value="${currentOrderId}"/>
    <!-- 收货地址  -->
    <input id="deliveryAddress" type="hidden" name="deliveryAddress" />
  </form>
  <!-- 配送信息开始  -->
  <div class="weui-panel">
    <div class="weui-panel__bd">
      <div class="weui-media-box weui-media-box_small-appmsg">
        <div class="weui-cells">
          <div class="weui-cell weui-cell_access">
            <div class="weui-cell__bd weui-cell_primary">
              <p class="font-14"><span class="mg-r-10">配送方式</span><span class="fr">${deliveriesType}</span></p>
            </div>
          </div>
          <div class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__bd weui-cell_primary">
              <p class="font-14"><span class="mg-r-10">运费</span><span class="fr txt-color-red">￥<em class="num">${postFee / 100}</em></span></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- 配送信息结束  -->

  <!-- 订单总金额开始  -->
  <div class="wy-media-box weui-media-box_text">
    <div class="mg10-0 t-c">总金额：<span class="wy-pro-pri mg-tb-5">¥<em class="num font-20">${totalPrice / 100}</em></span></div>
    <div class="mg10-0"><a id="wxpay" href="javascript:wxpay();" class="weui-btn weui-btn_primary">在线支付</a></div>
  </div>
  <!--  订单总金额结束  -->
</div>
<script src="${pageContext.request.contextPath}/jquery-weui/lib/jquery-2.1.4.js"></script> 
<script src="${pageContext.request.contextPath}/jquery-weui/lib/fastclick.js"></script> 
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
<script src="${pageContext.request.contextPath}/jquery-weui/js/jquery-weui.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.4.0.js"></script>
<script type="text/javascript">
var wxParam = {};
function onBridgeReady(){
  WeixinJSBridge.invoke(
      'getBrandWCPayRequest', wxParam,
    function(res){
    if(res.err_msg == "get_brand_wcpay_request:ok" ){
  	  alert(res.err_msg);
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
            }
      });
    }
  }); 
}
function wxpay(){
	//同步收货地址信息
	var address = $("#addressName").text()+$("#addressPhone").text()+$("#addressInfo").text();
	$("#deliveryAddress").val(address);
	//获取微信支付信息
	$.post("${pageContext.request.contextPath}/order/payparam",$("form").serialize(),function(res){
		  if(res!=null){
		    //设置微信支付参数
        var config = JSON.parse(res).data;
		    console.log(config.appId+":"+config.timeStamp+":"+config.nonceStr+":"+config.Pagkage+":"+config.paySign);
	      wxParam = {
	               "appId":config.appId,     //公众号名称，由商户传入     
	               "timeStamp":config.timeStamp,         //时间戳，自1970年以来的秒数     
	               "nonceStr":config.nonceStr, //随机串     
	               "package":config.Pagkage,     
	               "signType":"MD5",         //微信签名方式：     
	               "paySign":config.paySign //微信签名 
	          };
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
			  
		  }
	})
}

//打开选择地址页面
function selectAddress(){
    $(".weui-popup__container").popup();
  }
//确认收货地址
function confirmAddress(name,phone,info){
	$("#addressName").text(name);
	$("#addressPhone").text(phone);
	$("#addressInfo").text(info);
	$.closePopup();
}

</script>
</body>
</html>
