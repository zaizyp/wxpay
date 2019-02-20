<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新闻列表</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="新闻公告">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-weui/lib/weui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-weui/css/jquery-weui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body ontouchstart>
<!--主体-->
<header class="wy-header">
  <div class="wy-header-icon-back"><span></span></div>
  <div class="wy-header-title">新闻列表</div>
</header>
<div class="weui-content">
  <div class="weui-cells wy-news-list">
    <c:if test="${newsVoList.size() > 0}">
      <!-- 置顶的新闻公告 -->
	    <c:forEach items="${newsVoList}" var="newsVo">
	      <c:if test="newsVo.sortOrder==0">
			    <a class="weui-cell weui-cell_access" 
			      href="${pageContext.request.contextPath}/news/${newsVo.id}">
			      <div class="weui-cell__bd">
			        <p>${newsVo.title}</p>
			      </div>
			      <div class="weui-cell__ft"></div>
			    </a>
			  </c:if>
	    </c:forEach>
	    <!-- 普通的新闻公告 -->
	    <c:forEach items="${newsVoList}" var="newsVo">
	      <c:if test="${newsVo.sortOrder > 0}">
			    <a class="weui-cell weui-cell_access" 
			      href="${pageContext.request.contextPath}/news/${newsVo.id}">
			      <div class="weui-cell__bd">
			        <p>${newsVo.title}</p>
			      </div>
			      <div class="weui-cell__ft"></div>
			    </a>
			  </c:if>
	    </c:forEach>
    </c:if>
  </div>
</div>
<script src="${pageContext.request.contextPath}/jquery-weui/lib/jquery-2.1.4.js"></script> 
<script src="${pageContext.request.contextPath}/jquery-weui/lib/fastclick.js"></script> 
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
<script src="${pageContext.request.contextPath}/jquery-weui/js/jquery-weui.js"></script>
<script>

</script>
</body>
</html>
