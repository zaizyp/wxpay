<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的收藏</title>
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
<!--顶部搜索-->
<header class="wy-header">
  <div class="wy-header-icon-back"><span></span></div>
  <div class="wy-header-title">我的收藏</div>
</header>
<!--主体-->
<div class="weui-content">
  <div class='proListWrap'>
		<c:forEach items="${collectionVoList}" var="collectionVo">
		  <div class="pro-items">
		    <div class="weui-media-box weui-media-box_appmsg">
		      <div class="weui-media-box__hd">
		        <a href="${pageContext.request.contextPath}/item/${collectionVo.itemId}">
		          <img class="weui-media-box__thumb" 
		            src="${pageContext.request.contextPath}${collectionVo.image}" alt="">
		        </a>
		      </div>
		      <div class="weui-media-box__bd">
		        <h1 class="weui-media-box__desc">
		          <a href="pro_info.html" class="ord-pro-link">${collectionVo.title}</a>
		        </h1>
		        <div class="wy-pro-pri">¥<em class="num font-15">${collectionVo.lowPrice}</em></div>
		        <ul class="weui-media-box__info prolist-ul">
		          <li class="weui-media-box__info__meta"><a href="javascript:deleteCollection(${collectionVo.id});" class="wy-dele"></a></li>
		        </ul>
		      </div>
		    </div>
		  </div>
		</c:forEach>
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
function deleteCollection(collId){
	$.get("${pageContext.request.contextPath}/collection/delete",{
		  collId: collId
	},function(res){
		  var data = JSON.parse(res);
      if(data.status==200){
          $.toast("删除成功");
          self.location=document.referrer;
        }else{
          $.toptip('删除失败', 'error');
        }
	})
}

</script>
</body>
</html>
