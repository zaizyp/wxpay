<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>会员中心</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="">
<link rel="stylesheet"
		href="${pageContext.request.contextPath}/jquery-weui/lib/weui.min.css">
<link rel="stylesheet"
		href="${pageContext.request.contextPath}/jquery-weui/css/jquery-weui.css">
<link rel="stylesheet"
		href="${pageContext.request.contextPath}/css/style.css">
</head>
<body ontouchstart>
<!--主体-->
<div class='weui-content'>
	<div class="wy-center-top">
		<div class="weui-media-box weui-media-box_appmsg">
			<div class="weui-media-box__hd">
				<img class="weui-media-box__thumb radius" 
				  src="${user.headimgurl}"alt="">
			</div>
			<div class="weui-media-box__bd">
				<h4 class="weui-media-box__title user-name">${user.nickname}</h4>
				<p class="user-grade">等级：普通会员</p>
				<!--  
					<p class="user-integral">
						待返还金额：<em class="num">500.0</em>元
					</p>-->
			</div>
		</div>
		<!--    <div class="xx-menu weui-flex">
     <div class="weui-flex__item"><div class="xx-menu-list"><em>987</em><p>账户余额</p></div></div>
     <div class="weui-flex__item"><div class="xx-menu-list"><em>459</em><p>我的蓝豆</p></div></div>
     <div class="weui-flex__item"><div class="xx-menu-list"><em>4</em><p>收藏商品</p></div></div>
   </div>-->
	</div>
	<div class="weui-panel weui-panel_access">
		<div class="weui-panel__hd">
			<a href="all_orders.html"
				class="weui-cell weui-cell_access center-alloder">
				<div class="weui-cell__bd wy-cell">
					<div class="weui-cell__hd">
						<img src="images/center-icon-order-all.png" alt=""
							class="center-list-icon">
					</div>
					<div class="weui-cell__bd weui-cell_primary">
						<p class="center-list-txt">全部订单</p>
					</div>
				</div> <span class="weui-cell__ft"></span>
			</a>
		</div>
		<div class="weui-panel__bd">
			<div class="weui-flex">
				<div class="weui-flex__item">
					<a href="all_orders.html" class="center-ordersModule">
					  <c:if test="${orderNum.unPaid != 0}">
	            <span class="weui-badge" style="position: absolute; top: 5px; right: 10px; 
	              font-size: 10px;">
	                ${orderNum.unPaid}
	            </span>
	          </c:if>
						<div class="imgicon">
							<img src="images/center-icon-order-dfk.png" />
						</div>
						<div class="name">待付款</div>
					</a>
				</div>
				<div class="weui-flex__item">
					<a href="all_orders.html" class="center-ordersModule">
	          <c:if test="${orderNum.notShipped != 0}">
	            <span class="weui-badge" style="position: absolute; top: 5px; right: 10px; 
	              font-size: 10px;">
	              ${orderNum.notShipped}
	            </span>
	          </c:if>
						<div class="imgicon">
							<img src="images/center-icon-order-dfh.png" />
						</div>
						<div class="name">待发货</div>
					</a>
				</div>
				<div class="weui-flex__item">
					<a href="all_orders.html" class="center-ordersModule">
	          <c:if test="${orderNum.shipped != 0}">
	            <span class="weui-badge" style="position: absolute; top: 5px; right: 10px; 
	              font-size: 10px;">
	              ${orderNum.shipped}
	            </span>
	          </c:if>
						<div class="imgicon">
							<img src="images/center-icon-order-dsh.png" />
						</div>
						<div class="name">待收货</div>
					</a>
				</div>
				<div class="weui-flex__item">
					<a href="orders.html" class="center-ordersModule">
	          <c:if test="${orderNum.notEvaluated != 0}">
	            <span class="weui-badge" style="position: absolute; top: 5px; right: 10px; 
	              font-size: 10px;">
	              ${orderNum.notEvaluated}
	            </span>
	          </c:if>
						<div class="imgicon">
							<img src="images/center-icon-order-dpj.png" />
						</div>
						<div class="name">待评价</div>
					</a>
				</div>
			</div>
		</div>
	</div>
  <!--  
	<div class="weui-panel weui-panel_access">
		<div class="weui-panel__hd">
			<a href="myburse.html"
				class="weui-cell weui-cell_access center-alloder">
				<div class="weui-cell__bd wy-cell">
					<div class="weui-cell__hd">
						<img src="images/center-icon-jk.png" alt=""
							class="center-list-icon">
					</div>
					<div class="weui-cell__bd weui-cell_primary">
						<p class="center-list-txt">我的小金库</p>
					</div>
				</div> <span class="weui-cell__ft"></span>
			</a>
		</div>
		<div class="weui-panel__bd">
			<div class="weui-flex">
				<div class="weui-flex__item">
					<a href="myburse.html" class="center-ordersModule">
						<div class="center-money">
							<em>800.0</em>
						</div>
						<div class="name">账户总额</div>
					</a>
				</div>
				<div class="weui-flex__item">
					<a href="myburse.html" class="center-ordersModule">
						<div class="center-money">
							<em>50.0</em>
						</div>
						<div class="name">返现金额</div>
					</a>
				</div>
				<div class="weui-flex__item">
					<a href="myburse.html" class="center-ordersModule">
						<div class="center-money">
							<em>550.0</em>
						</div>
						<div class="name">待返还</div>
					</a>
				</div>
				<div class="weui-flex__item">
					<a href="myburse.html" class="center-ordersModule">
						<div class="center-money">
							<em>165</em>
						</div>
						<div class="name">蓝豆</div>
					</a>
				</div>
			</div>
		</div>
	</div>
  -->

	<div class="weui-panel">
		<div class="weui-panel__bd">
			<div class="weui-media-box weui-media-box_small-appmsg">
				<div class="weui-cells">
					<a class="weui-cell weui-cell_access" href="record.html">
						<div class="weui-cell__hd">
							<img src="images/center-icon-jyjl.png" alt=""
								class="center-list-icon">
						</div>
						<div class="weui-cell__bd weui-cell_primary">
							<p class="center-list-txt">交易记录</p>
						</div> <span class="weui-cell__ft"></span>
					</a>
          <a class="weui-cell weui-cell_access" 
            href="${pageContext.request.contextPath}/collection/list"">
						<div class="weui-cell__hd">
							<img src="images/center-icon-sc.png" alt=""
								class="center-list-icon">
						</div>
						<div class="weui-cell__bd weui-cell_primary">
							<p class="center-list-txt">我的收藏</p>
						</div> <span class="weui-cell__ft"></span>
					</a>
          <a class="weui-cell weui-cell_access" 
            href="${pageContext.request.contextPath}/address/list">
						<div class="weui-cell__hd">
							<img src="images/center-icon-dz.png" alt=""
								class="center-list-icon">
						</div>
						<div class="weui-cell__bd weui-cell_primary">
							<p class="center-list-txt">地址管理</p>
						</div> <span class="weui-cell__ft"></span>
					</a>
					<!--  
          <a class="weui-cell weui-cell_access" href="card.html">
						<div class="weui-cell__hd">
							<img src="images/center-icon-yhk.png" alt=""
								class="center-list-icon">
						</div>
						<div class="weui-cell__bd weui-cell_primary">
							<p class="center-list-txt">我的银行卡</p>
						</div> <span class="weui-cell__ft"></span>
					</a>
          <a class="weui-cell weui-cell_access" href="password.html">
						<div class="weui-cell__hd">
							<img src="images/center-icon-dlmm.png" alt=""
								class="center-list-icon">
						</div>
						<div class="weui-cell__bd weui-cell_primary">
							<p class="center-list-txt">密码修改</p>
						</div> <span class="weui-cell__ft"></span>
					</a>
          <a class="weui-cell weui-cell_access" href="login.html">
						<div class="weui-cell__hd">
							<img src="images/center-icon-out.png" alt=""
								class="center-list-icon">
						</div>
						<div class="weui-cell__bd weui-cell_primary">
							<p class="center-list-txt">退出账号</p>
						</div> <span class="weui-cell__ft"></span>
					</a>
					-->
				</div>
			</div>
		</div>
	</div>
</div>

<!--底部导航-->
<div class="foot-black"></div>
<div class="weui-tabbar wy-foot-menu">
	<a href="index.html" class="weui-tabbar__item">
		<div class="weui-tabbar__icon foot-menu-home"></div>
		<p class="weui-tabbar__label">首页</p>
	</a>
	<a href="${pageContext.request.contextPath}/item/cat/list" 
	  class="weui-tabbar__item">
		<div class="weui-tabbar__icon foot-menu-list"></div>
		<p class="weui-tabbar__label">分类</p>
	</a>
	<a href="${pageContext.request.contextPath}/shopingCart/list" class="weui-tabbar__item">
	  <c:if test="${shopCartCount > 0}">
		  <span class="weui-badge" 
		     style="position: absolute; top: -.4em; right: 1em;">${shopCartCount}</span>
		</c:if>
		<div class="weui-tabbar__icon foot-menu-cart"></div>
		<p class="weui-tabbar__label">购物车</p>
	</a>
  <a href="javascript:void(0);"
		class="weui-tabbar__item weui-bar__item--on">
		<div class="weui-tabbar__icon foot-menu-member"></div>
		<p class="weui-tabbar__label">我的</p>
	</a>
</div>

<script
	src="${pageContext.request.contextPath}/jquery-weui/lib/jquery-2.1.4.js"></script>
<script
	src="${pageContext.request.contextPath}/jquery-weui/lib/fastclick.js"></script>
<script>
	$(function() {
		FastClick.attach(document.body);
	});
</script>
<script
	src="${pageContext.request.contextPath}/jquery-weui/js/jquery-weui.js"></script>
</body>
</html>
