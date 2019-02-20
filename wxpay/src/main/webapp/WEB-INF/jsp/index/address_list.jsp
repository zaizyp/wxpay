<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>地址管理</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-weui/lib/weui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-weui/css/jquery-weui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body ontouchstart>
<!--主体-->
<header class="wy-header">
  <div class="wy-header-icon-back"><span></span></div>
  <div class="wy-header-title">地址管理</div>
</header>
<div class="weui-content">
  <div class="weui-panel address-box">
    <c:forEach items="${addressList}" var="address">
      <c:if test="${address.isDefault}">
		    <div class="weui-panel__bd" onclick="confirmAddress(${address.id});">
		      <div class="weui-media-box weui-media-box_text address-list-box">
		        <a href="${pageContext.request.contextPath}/address/edit?addressId=${address.id}" class="address-edit"></a>
		        <h4 class="weui-media-box__title">
		          <span id="name${address.id}">${address.name}</span>
		          <span id="phone${address.id}">${address.telphone}</span>
		        </h4>
		        <p class="weui-media-box__desc address-txt" id="info${address.id}">
		          ${address.province}${address.city}${address.area}${address.street}
		        </p>
		        <span class="default-add">默认地址</span>
		      </div>
		    </div>
	    </c:if>
    </c:forEach>
    <c:forEach items="${addressList}" var="address">
      <c:if test="${!address.isDefault}">
		    <div class="weui-panel__bd" onclick="confirmAddress(${address.id});">
		      <div class="weui-media-box weui-media-box_text address-list-box">
		        <a href="${pageContext.request.contextPath}/address/edit?addressId=${address.id}" class="address-edit"></a>
			        <h4 class="weui-media-box__title">
			        <span id="name${address.id}">${address.name}</span>
			        <span id="phone${address.id}">${address.telphone}</span>
		        </h4>
		        <p class="weui-media-box__desc address-txt" id="info${address.id}">
		          ${address.province}${address.city}${address.area}${address.street}
		        </p>
		      </div>
		    </div>
		  </c:if>
    </c:forEach>
  </div>
  <div class="weui-btn-area">
    <a class="weui-btn weui-btn_primary" href="${pageContext.request.contextPath}/address/addPage" id="showTooltips">添加收货地址</a>
  </div>
</div>

<script src="${pageContext.request.contextPath}/jquery-weui/lib/jquery-2.1.4.js"></script> 
<script src="${pageContext.request.contextPath}/jquery-weui/lib/fastclick.js"></script> 
<script>
  $(function() {
    FastClick.attach(document.body);
    window.event? window.event.cancelBubble = true : e.stopPropagation();
  });
</script>
<script src="${pageContext.request.contextPath}/jquery-weui/js/jquery-weui.js"></script>
<script>

$(document).ready(function(){
  $("a").click(function(event){
    event.stopPropagation();
  });
});

//确认收货地址
function confirmAddress(id){
	var name = $("#name"+id).text();
	var phone = $("#phone"+id).text();
	var info = $("#info"+id).text();
	//调用父页面的确认地址方法
	window.parent.window.confirmAddress(name,phone,info);
}


</script>

</body>
</html>
