<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商城首页</title>
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
<!--主体-->
<div class='weui-content'>
  <!--顶部轮播-->
  <div class="swiper-container swiper-banner">
    <div class="swiper-wrapper">
	    <c:forEach items="${adImageList}" var="adImage">
					<div class="swiper-slide">
						<!-- 如果存在商品id则链接到商品详情页 -->
						<c:if test="${not empty adImage.itemId}">
							<a href="${pageContext.request.contextPath}/item/${adImage.itemId}">
								<img src="${pageContext.request.contextPath}${adImage.pic}" />
							</a>
						</c:if>
						<!-- 如果不存在商品id但存在url则链接到该url -->
						<c:if test="${empty adImage.itemId}">
							<a href="${adImage.url}">
                <img src="${pageContext.request.contextPath}${adImage.pic}" />
							</a>
						</c:if>
					</div>
				</c:forEach>
    </div>
    <div class="swiper-pagination"></div>
  </div>
  <!--图标分类-->
  <div class="weui-flex wy-iconlist-box">
    <div class="weui-flex__item">
      <a href="pro_list.html" class="wy-links-iconlist">
        <div class="img"><img src="images/icon-link1.png"></div>
        <p>精选推荐</p>
      </a>
    </div>
    <div class="weui-flex__item">
      <a href="${pageContext.request.contextPath}/item/list/26" class="wy-links-iconlist">
        <div class="img"><img src="images/icon-link2.png"></div>
        <p>酒水专场</p>
      </a>
    </div>
    <div class="weui-flex__item">
      <a href="" class="wy-links-iconlist">
        <div class="img"><img src="images/icon-link3.png"></div>
        <p>订单管理</p>
      </a>
    </div>
    <div class="weui-flex__item">
      <a href="Settled_in.html" class="wy-links-iconlist">
        <div class="img"><img src="images/icon-link4.png"></div>
        <p>商家入驻</p>
      </a>
    </div>
  </div>
  <!--新闻切换-->
  <div class="wy-ind-news">
    <i class="news-icon-laba"></i>
    <div class="swiper-container swiper-news">
      <div class="swiper-wrapper">
        <c:forEach items="${newsVoList}" var="newsVo">
          <div class="swiper-slide">
            <a href="${pageContext.request.contextPath}/news/${newsVo.id}">${newsVo.title}</a>
          </div>
        </c:forEach>
      </div>
      <div class="swiper-pagination"></div>
    </div>
    <a href="${pageContext.request.contextPath}/news/list" class="newsmore"><i class="news-icon-more"></i></a>
  </div>
  <!--精选推荐-->
  <div class="wy-Module">
    <div class="wy-Module-tit"><span>精选推荐</span></div>
    <div class="wy-Module-con">
      <div class="swiper-container swiper-jingxuan" style="padding-top:34px;">
        <div class="swiper-wrapper">
          <c:forEach items="${adItemVoList1}" var="adItemVo">
	          <div class="swiper-slide">
	            <div style="background-color: #fff;text-align: center;height: 148px;padding: 12px 0px;">
	              <a href="${pageContext.request.contextPath}/item/${adItemVo.itemId}">
	                <div style="color: #0A0000;font-weight: 900;">${adItemVo.title}</div>
	                <div style="color: #757575;">￥${adItemVo.lowPrice / 100}</div>
	                <c:if test="${not empty adItemVo.pic}">
                    <div style="width: 70%;height:70%;margin: 0 auto;"><img 
                      src="${pageContext.request.contextPath}${adItemVo.pic}" /></div>
                  </c:if>
                  <c:if test="${empty adItemVo.pic}">
                    <div style="width: 70%;height:70%;margin: 0 auto;"><img src="/upload/2018/11/14/1542165833799055.jpg" alt="" /></div>
                  </c:if>
	              </a>
	            </div>
	          </div>
			    </c:forEach>
        </div>
        <div class="swiper-pagination jingxuan-pagination"></div>
      </div>
    </div>
  </div>
  <!--酒水专场-->
  <div class="wy-Module">
    <div class="wy-Module-tit"><span>生活推荐</span></div>
    <div class="wy-Module-con">
      <div class="swiper-container swiper-jiushui" style="padding-top:34px;">
        <div class="swiper-wrapper">
          <c:forEach items="${adItemVoList2}" var="adItemVo">
            <div class="swiper-slide">
              <div style="background-color: #fff;text-align: center;height: 148px;padding: 12px 0px;">
                <a href="${pageContext.request.contextPath}/item/${adItemVo.itemId}">
                  <div style="color: #0A0000;font-weight: 900;">${adItemVo.title}</div>
                  <div style="color: #757575;">￥${adItemVo.lowPrice / 100}</div>
                  <c:if test="${not empty adItemVo.pic}">
                    <div style="width: 70%;height:70%;margin: 0 auto;"><img 
                      src="${pageContext.request.contextPath}${adItemVo.pic}" /></div>
                  </c:if>
                  <c:if test="${empty adItemVo.pic}">
                    <div style="width: 70%;height:70%;margin: 0 auto;"><img src="/upload/2018/11/14/1542165833799055.jpg" alt="" /></div>
                  </c:if>
                </a>
              </div>
            </div>
          </c:forEach>
        </div>
        <div class="swiper-pagination jingxuan-pagination"></div>
      </div>
    </div>
  </div>
  <!--猜你喜欢-->
  <div class="wy-Module">
    <div class="wy-Module-tit-line"><span>猜你喜欢</span></div>
    <div class="wy-Module-con">
      <ul class="wy-pro-list clear">
        <c:if test="${adItemVoList3.size()>0}">
 	        <c:forEach items="${adItemVoList3}" var="adItemVo">
		        <li>
		          <a href="${pageContext.request.contextPath}/item/${adItemVo.itemId}">
		            <div class="proimg"><img 
		               src="${pageContext.request.contextPath}${(fn:split(adItemVo.image,','))[0]}"></div>
		            <div class="protxt">
		              <div class="name" style="height: 36px;">${adItemVo.itemTitle}</div>
		              <div class="wy-pro-pri">¥<span>${adItemVo.lowPrice / 100}</span></div>
		            </div>
		          </a>
		        </li>
	        </c:forEach>
        </c:if>
      </ul>
      <div class="morelinks">
        <a href="${pageContext.request.contextPath}/item/list/like">查看更多 >></a>
      </div>
    </div>
  </div>
</div>

<!--底部导航-->
<div class="foot-black"></div>
<div class="weui-tabbar wy-foot-menu">
  <a href="javascript:void(0);" class="weui-tabbar__item weui-bar__item--on">
    <div class="weui-tabbar__icon foot-menu-home"></div>
    <p class="weui-tabbar__label">首页</p>
  </a>
  <a href="${pageContext.request.contextPath}/item/cat/list" class="weui-tabbar__item">
    <div class="weui-tabbar__icon foot-menu-list"></div>
    <p class="weui-tabbar__label">分类</p>
  </a>
  <a href="${pageContext.request.contextPath}/shopingCart/list" class="weui-tabbar__item">
    <c:if test="${shopCartCount > 0}">
      <span class="weui-badge" style="position: absolute;top: -.4em;right: 1em;">${shopCartCount}</span>
    </c:if>
    <div class="weui-tabbar__icon foot-menu-cart"></div>
    <p class="weui-tabbar__label">购物车</p>
  </a>
  <a href="${pageContext.request.contextPath}/mine" class="weui-tabbar__item">
    <div class="weui-tabbar__icon foot-menu-member"></div>
    <p class="weui-tabbar__label">我的</p>
  </a>
</div>

<script src="${pageContext.request.contextPath}/jquery-weui/lib/jquery-2.1.4.js"></script> 
<script src="${pageContext.request.contextPath}/jquery-weui/lib/fastclick.js"></script> 
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script> 
<script src="${pageContext.request.contextPath}/jquery-weui/js/jquery-weui.js"></script>
<script src="${pageContext.request.contextPath}/jquery-weui/js/swiper.js"></script>
<script>
	$(".swiper-banner").swiper({
        loop: true,
        autoplay: 3000
      });
	$(".swiper-news").swiper({
		loop: true,
		direction: 'vertical',
		paginationHide :true,
        autoplay: 30000
      });
	 $(".swiper-jingxuan").swiper({
		pagination: '.swiper-pagination',
		loop: true,
		paginationType:'fraction',
        slidesPerView:3,
        paginationClickable: true,
        spaceBetween: 2
      });
	 $(".swiper-jiushui").swiper({
		pagination: '.swiper-pagination',
		paginationType:'fraction',
		loop: true,
        slidesPerView:3,
		slidesPerColumn: 2,
        paginationClickable: true,
        spaceBetween:2
      });
</script>
</body>
</html>
