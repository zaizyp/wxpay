<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商城分类</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compmatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-weui/lib/weui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-weui/css/jquery-weui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body ontouchstart>
<!--顶部搜索-->
<!--主体-->
<div class="wy-content">
  <div class="category-top">
    <header class='weui-header'>
  <div class="weui-search-bar" id="searchBar">
    <form class="weui-search-bar__form">
      <div class="weui-search-bar__box">
        <i class="weui-icon-search"></i>
        <input type="search" class="weui-search-bar__input" id="searchInput" placeholder="搜索您想要的商品" required>
        <a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
      </div>
      <label class="weui-search-bar__label" id="searchText" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">
        <i class="weui-icon-search"></i>
        <span>搜索您想要的商品</span>
      </label>
    </form>
    <a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a>
  </div>
</header>
  </div>
  <aside>
    <div class="menu-left scrollbar-none" id="sidebar">
      <ul>
      	<!-- 第一个活动的一级分类 -->
      	<c:forEach items="${catList}" var="cat" begin="0" end="0" >
      		<li class="active">${cat.name}</li>
      	</c:forEach>
      	<!-- 接下来的隐藏一级分类 -->
      	<c:forEach items="${catList}" var="cat" begin="1" >
      		<li>${cat.name}</li>
      	</c:forEach>
      </ul>
    </div>
  </aside>
  <!-- 第一个活动二级分类 -->
  <c:forEach items="${catList}" var="cat" begin="0" end="0">
  	<section class="menu-right padding-all j-content">
  		<h5>${cat.name}</h5>
	    <ul>
	    	<c:forEach items="${cat.children}" var="children">
			<li class="w-3"><a href="${pageContext.request.contextPath}/item/list/${children.id}"></a> <img src="${pageContext.request.contextPath}${children.image}"><span>${children.name}</span></li>	    		
	    	</c:forEach>
	    </ul>
	</section>
  </c:forEach>
  <!-- 接下来隐藏的二级分类 -->
  <c:forEach items="${catList}" var="cat" begin="1">
  	<section class="menu-right padding-all j-content" style="display:none">
  		<h5>${cat.name}</h5>
	    <ul>
	    	<c:forEach items="${cat.children}" var="children">
			<li class="w-3"><a href="${pageContext.request.contextPath}/item/list/${children.id}"></a> <img src="${pageContext.request.contextPath}${children.image}"><span>${children.name}</span></li>	    		
	    	</c:forEach>
	    </ul>
	</section>
  </c:forEach>

</div>
<script src="${pageContext.request.contextPath}/jquery-weui/lib/jquery-2.1.4.js"></script> 
<script src="${pageContext.request.contextPath}/jquery-weui/lib/fastclick.js"></script> 
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
<script src="${pageContext.request.contextPath}/jquery-weui/js/jquery-weui.js"></script>
<script  type="text/javascript">
	$(function($){
		$('#sidebar ul li').click(function(){
			$(this).addClass('active').siblings('li').removeClass('active');
			var index = $(this).index();
			$('.j-content').eq(index).show().siblings('.j-content').hide();
		})
	})
</script>
</body>
</html>
