<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>产品详情</title>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport"
			content="width=device-width, initial-scale=1, user-scalable=no">
			<meta name="description"
				content="Write an awesome description for your new site here.">
				<link rel="stylesheet"
					href="${pageContext.request.contextPath}/jquery-weui/lib/weui.min.css">
					<link rel="stylesheet"
						href="${pageContext.request.contextPath}/jquery-weui/css/jquery-weui.css">
						<link rel="stylesheet"
							href="${pageContext.request.contextPath}/css/style.css">
</head>
<body ontouchstart>
	<div id="itemDetail">
		<!--主体-->
		<div class="weui-content">
			<!--产品详情-->
			<div class="weui-tab">
				<div class="weui-navbar"
					style="position: fixed; top: 0; left: 0; right: 0; height: 44px;">
					<a class="weui-navbar__item proinfo-tab-tit weui-bar__item--on"
						href="#tab1">商品</a> <a class="weui-navbar__item proinfo-tab-tit"
						href="#tab2">详情</a> <a class="weui-navbar__item proinfo-tab-tit"
						href="#tab3">评价</a>
				</div>
				<div class="weui-tab__bd proinfo-tab-con">
					<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
						<!--主图轮播开始-->
						<div class="swiper-container swiper-zhutu">
							<div class="swiper-wrapper">
								<c:forEach items="${fn:split(itemVo.image,',')}" var="image">
									<div class="swiper-slide">
										<img src="${pageContext.request.contextPath}${image}" />
									</div>
								</c:forEach>
							</div>
							<div class="swiper-pagination swiper-zhutu-pagination"></div>
						</div>
						<!-- 主图轮播结束 -->

						<!-- 商品信息开始 -->
						<div class="wy-media-box-nomg weui-media-box_text">
							<h4 class="wy-media-box__title">${itemVo.title}</h4>
							<div class="wy-pro-pri mg-tb-5">
								¥<em class="num font-20"> {{price}}</em>
							</div>
							<p class="weui-media-box__desc">${itemVo.sellPoint}</p>
						</div>
						<!-- 商品信息结束 -->

						<!-- 商品优惠信息开始  -->
						<div class="wy-media-box2 weui-media-box_text">
							<div class="weui-media-box_appmsg">
								<div class="weui-media-box__hd proinfo-txt-l">
									<span class="promotion-label-tit">优惠</span>
								</div>
								<div class="weui-media-box__bd">
									<div class="promotion-message clear">
										<i class="yhq"><span class="label-text">优惠券</span></i> <span
											class="promotion-item-text">满197.00减40.00</span>
									</div>
									<div class="promotion-message clear">
										<i class="yhq"><span class="label-text">优惠券</span></i> <span
											class="promotion-item-text">满197.00减40.00</span>
									</div>
									<div class="yhq-btn clear">
										<a href="yhq_list.html">去领券</a>
									</div>
								</div>
							</div>
						</div>
						<!-- 商品优惠信息结束 -->

						<!-- 商品规格开始 -->
						<div id="itemSpec" class="wy-media-box2 weui-media-box_text">
							<div v-for="(spec,spec_i) in itemSpec"
								class="weui-media-box_appmsg">
								<div class="weui-media-box__hd proinfo-txt-l">
									<span class="promotion-label-tit">{{spec.name}}</span>
								</div>
								<div class="weui-media-box__bd">
									<div class="promotion-sku clear">
										<ul>
											<li v-for="(val,index) in spec.value"
												v-on:click="addClass(spec_i,index)"
												v-bind:class="{active:specIndex[spec_i]==index}"><a
												href="javascript:;">{{val}}</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<!-- 商品规格结束 -->

						<!-- 快递信息开始 -->
						<div class="wy-media-box2 txtpd weui-media-box_text">
							<div class="weui-media-box_appmsg">
								<div class="weui-media-box__hd proinfo-txt-l">
									<span class="promotion-label-tit">送至</span>
								</div>
								<div class="weui-media-box__bd">
									<div class="promotion-message clear">
										<span class="promotion-item-text">江苏</span> <span
											class="promotion-item-text">宿迁</span> <span
											class="promotion-item-text">洋河新区</span>
									</div>
								</div>
							</div>
							<div class="weui-media-box_appmsg">
								<div class="weui-media-box__hd proinfo-txt-l">
									<span class="promotion-label-tit">运费</span>
								</div>
								<div class="weui-media-box__bd">
									<div class="promotion-message clear">
										<span class="promotion-item-text">免运费<!--<div class="wy-pro-pri">¥<span class="num">11.00</span></div>--></span>
									</div>
								</div>
							</div>
							<div class="weui-media-box_appmsg">
								<div class="weui-media-box__hd proinfo-txt-l">
									<span class="promotion-label-tit">商家</span>
								</div>
								<div class="weui-media-box__bd">
									<div class="promotion-message clear">
										<span class="promotion-item-text">蓝之蓝股份有限公司</span>
									</div>
								</div>
							</div>
							<div class="weui-media-box_appmsg">
								<div class="weui-media-box__hd proinfo-txt-l">
									<span class="promotion-label-tit">提示</span>
								</div>
								<div class="weui-media-box__bd">
									<div class="promotion-message clear">
										<span class="promotion-item-text"><p
												class="txt-color-ml">支持7天无理由退换货</p></span>
									</div>
								</div>
							</div>
						</div>
						<!-- 快递信息结束 -->
					</div>

					<!-- 商品详细信息开始 -->
					<div id="tab2" class="weui-tab__bd-item ">
						<div class="pro-detail">${itemVo.itemDesc.itemDesc}</div>
					</div>
					<!-- 商品详细信息结束 -->

					<!-- 商品评价开始 -->
					<div id="tab3" class="weui-tab__bd-item">
						<!--评价-->
						<div class="weui-panel__bd">
							<div class="wy-media-box weui-media-box_text">
								<div class="weui-cell nopd weui-cell_access">
									<div class="weui-cell__hd">
										<img src="upload/headimg.jpg" alt=""
											style="width: 20px; margin-right: 5px; display: block">
									</div>
									<div class="weui-cell__bd weui-cell_primary">
										<p>飞翔的小土豆</p>
									</div>
									<span class="weui-cell__time">2017-02-06</span>
								</div>
								<div class="comment-item-star">
									<span class="real-star comment-stars-width5"></span>
								</div>
								<p class="weui-media-box__desc">面料不错，码数也正常 男朋友穿的很合适。</p>
								<ul class="weui-uploader__files clear mg-com-img">
									<li class="weui-uploader__file"
										style="background-image: url(./upload/pro3.jpg)"></li>
									<li class="weui-uploader__file"
										style="background-image: url(./upload/pro3.jpg)"></li>
									<li class="weui-uploader__file"
										style="background-image: url(./upload/pro3.jpg)"></li>
								</ul>
							</div>
						</div>
						<div class="weui-panel__bd">
							<div class="wy-media-box weui-media-box_text">
								<div class="weui-cell nopd weui-cell_access">
									<div class="weui-cell__hd">
										<img src="upload/headimg.jpg" alt=""
											style="width: 20px; margin-right: 5px; display: block">
									</div>
									<div class="weui-cell__bd weui-cell_primary">
										<p>爱情海的事故</p>
									</div>
									<span class="weui-cell__time">2017-02-06</span>
								</div>
								<div class="comment-item-star">
									<span class="real-star comment-stars-width3"></span>
								</div>
								<p class="weui-media-box__desc">面料不错，码数也正常 男朋友面料不错，码数也正常
									男朋友穿的面料不错，码数也正常 男朋友穿的穿的很合适。</p>
								<ul class="weui-uploader__files clear mg-com-img">
									<li class="weui-uploader__file"
										style="background-image: url(./upload/pro3.jpg)"></li>
									<li class="weui-uploader__file"
										style="background-image: url(./upload/pro3.jpg)"></li>
									<li class="weui-uploader__file"
										style="background-image: url(./upload/pro3.jpg)"></li>
								</ul>
							</div>
						</div>
						<div class="weui-panel__bd">
							<div class="wy-media-box weui-media-box_text">
								<div class="weui-cell nopd weui-cell_access">
									<div class="weui-cell__hd">
										<img src="upload/headimg.jpg" alt=""
											style="width: 20px; margin-right: 5px; display: block">
									</div>
									<div class="weui-cell__bd weui-cell_primary">
										<p>爱情海的事故</p>
									</div>
									<span class="weui-cell__time">2017-02-06</span>
								</div>
								<div class="comment-item-star">
									<span class="real-star comment-stars-width3"></span>
								</div>
								<p class="weui-media-box__desc">面料不错，码数也正常 男朋友面料不错，码数也正常
									男朋友穿的面料不错，码数也正常 男朋友穿的穿的很合适。</p>
								<ul class="weui-uploader__files clear mg-com-img">
									<li class="weui-uploader__file"
										style="background-image: url(./upload/pro3.jpg)"></li>
									<li class="weui-uploader__file"
										style="background-image: url(./upload/pro3.jpg)"></li>
									<li class="weui-uploader__file"
										style="background-image: url(./upload/pro3.jpg)"></li>
								</ul>
							</div>
						</div>
						<div class="weui-panel__bd">
							<div class="wy-media-box weui-media-box_text">
								<div class="weui-cell nopd weui-cell_access">
									<div class="weui-cell__hd">
										<img src="upload/headimg.jpg" alt=""
											style="width: 20px; margin-right: 5px; display: block">
									</div>
									<div class="weui-cell__bd weui-cell_primary">
										<p>爱情海的事故</p>
									</div>
									<span class="weui-cell__time">2017-02-06</span>
								</div>
								<div class="comment-item-star">
									<span class="real-star comment-stars-width3"></span>
								</div>
								<p class="weui-media-box__desc">面料不错，码数也正常 男朋友面料不错，码数也正常
									男朋友穿的面料不错，码数也正常 男朋友穿的穿的很合适。</p>
								<ul class="weui-uploader__files clear mg-com-img">
									<li class="weui-uploader__file"
										style="background-image: url(./upload/pro3.jpg)"
										onclick="window.location.reload('gallery.html')"></li>
									<li class="weui-uploader__file"
										style="background-image: url(./upload/ban2.jpg)"
										onclick="window.location.reload('gallery.html')"></li>
									<li class="weui-uploader__file"
										style="background-image: url(./upload/pro3.jpg)"
										onclick="window.location.reload('gallery.html')"></li>
								</ul>
							</div>
						</div>
						<div class="weui-panel__bd">
							<div class="wy-media-box weui-media-box_text">
								<div class="weui-cell nopd weui-cell_access">
									<div class="weui-cell__hd">
										<img src="upload/headimg.jpg" alt=""
											style="width: 20px; margin-right: 5px; display: block">
									</div>
									<div class="weui-cell__bd weui-cell_primary">
										<p>爱情海的事故</p>
									</div>
									<span class="weui-cell__time">2017-02-06</span>
								</div>
								<div class="comment-item-star">
									<span class="real-star comment-stars-width3"></span>
								</div>
								<p class="weui-media-box__desc">面料不错，码数也正常 男朋友面料不错，码数也正常
									男朋友穿的面料不错，码数也正常 男朋友穿的穿的很合适。</p>
								<ul class="weui-uploader__files clear mg-com-img">
									<li class="weui-uploader__file"
										style="background-image: url(./upload/pro3.jpg)"></li>
									<li class="weui-uploader__file"
										style="background-image: url(./upload/pro3.jpg)"></li>
									<li class="weui-uploader__file"
										style="background-image: url(./upload/pro3.jpg)"></li>
								</ul>
							</div>
						</div>
						<a href="javascript:void(0);"
							class="weui-cell weui-cell_access weui-cell_link list-more">
							<div class="weui-cell__bd">查看更多</div> <span class="weui-cell__ft"></span>
						</a>

					</div>
					<!-- 商品评价结束 -->
				</div>
			</div>
		</div>
		<span id="tophovertree" title="返回顶部"></span>
		<!--底部导航-->
		<div class="foot-black"></div>
		<!-- 底部菜单开始 -->
		<div class="weui-tabbar wy-foot-menu">
			<a href="javascript:;" class="promotion-foot-menu-items">
				<div class="weui-tabbar__icon promotion-foot-menu-kefu"></div>
				<p class="weui-tabbar__label">客服</p>
			</a>
      <a href="javascript:;" id='show-toast' @click="addCollection()"
				class="promotion-foot-menu-items">
				<div class="weui-tabbar__icon promotion-foot-menu-collection"></div>
				<p class="weui-tabbar__label">收藏</p>
			</a>
      <a href="${pageContext.request.contextPath}/shopingCart/list" 
        class="promotion-foot-menu-items"> <span
				class="weui-badge"
				style="position: absolute; top: -.4em; right: 1em;">${shopingCartCount}</span>
				<div class="weui-tabbar__icon promotion-foot-menu-cart"></div>
				<p class="weui-tabbar__label">购物车</p>
			</a>
      <a href="javascript:;" @click="addCart"
				class="weui-tabbar__item yellow-color">
				<p class="promotion-foot-menu-label">加入购物车</p>
			</a>
      <a href="javascript:;"
				class="weui-tabbar__item red-color open-popup"
				data-target="#selcet_sku">
				<p class="promotion-foot-menu-label">立即购买</p>
			</a>
		</div>
		<!-- 底部菜单结束 -->

		<!-- 加入购物车成功界面开始 -->
		<div id="join_cart" class='weui-popup__container popup-bottom'
			style="z-index: 600;">
			<div class="weui-popup__overlay" style="opacity: 1;"></div>
			<div class="weui-popup__modal">
				<div class="modal-content">
					<div class="weui-msg" style="padding-top: 0;">
						<div class="weui-msg__icon-area">
							<i class="weui-icon-success weui-icon_msg"></i>
						</div>
						<div class="weui-msg__text-area">
							<h2 class="weui-msg__title">成功加入购物车</h2>
							<p class="weui-msg__desc">亲爱的用户，您的商品已成功加入购物车，为了保证您的商品快速送达，请您尽快到购物车结算。</p>
						</div>
						<div class="weui-msg__opr-area">
							<p class="weui-btn-area">
								<a href="${pageContext.request.contextPath}/shopingCart/list" class="weui-btn weui-btn_primary">去购物车结算</a>
								<a href="javascript:;"
									class="weui-btn weui-btn_default close-popup">不，我再看看</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 加入购物车成功界面结束 -->

		<!-- 立即购买选择商品规格界面开始 -->
		<div id="selcet_sku" class='weui-popup__container popup-bottom'
			style="z-index: 600;">
			<div class="weui-popup__overlay" style="opacity: 1;"></div>
			<div class="weui-popup__modal">
				<div class="toolbar">
					<div class="toolbar-inner">
						<a href="javascript:;" class="picker-button close-popup">关闭</a>
						<h1 class="title">商品属性</h1>
					</div>
				</div>
				<div class="modal-content">
					<div class="weui-msg" style="padding-top: 0;">
						<div id="itemSpec" class="wy-media-box2 weui-media-box_text"
							style="margin: 0;">
							<!-- sku信息开始 -->
							<div class="weui-flex" style="height: 32vw; text-align: left;">
								<!-- sku图片  -->
								<div style="width: 30%; height: 30vw;">
									<img style="height: 100%; width: auto;"
										:src="'${pageContext.request.contextPath}'+currentSku.image"
										alt="" />
								</div>
								<!-- sku信息 -->
								<div class="weui-flex__item wy-pro-pri mg-tb-5"
									style="padding: 2vw;">
									<!-- sku价格 -->
									<div>
										¥<em class="num font-20"> {{currentPrice}}</em>
									</div>
									<div style="color: #000; margin-top: 5px;">库存：{{currentSku.num}}</div>
								</div>

							</div>
							<!-- sku信息结束 -->

							<!-- sku规格值信息开始 -->
							<div v-for="(spec,spec_i) in itemSpec"
								class="weui-media-box_appmsg">
								<div class="weui-media-box__hd proinfo-txt-l">
									<span class="promotion-label-tit">{{spec.name}}</span>
								</div>
								<div class="weui-media-box__bd">
									<div class="promotion-sku clear">
										<ul>
											<li v-for="(val,index) in spec.value"
												v-on:click="addClass(spec_i,index)"
												v-bind:class="{active:specIndex[spec_i]==index}"><a
												href="javascript:;">{{val}}</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="weui-msg__opr-area">
							<p class="weui-btn-area">
								<a @click="confirmOrder()" class="weui-btn weui-btn_primary">立即购买</a>
								<a href="javascript:;"
									class="weui-btn weui-btn_default close-popup">不，我再看看</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 立即购买选择商品规格  -->

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
	<script
		src="${pageContext.request.contextPath}/jquery-weui/js/swiper.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>
	<script>
		var itemSpecStr = JSON.parse('${itemVo.itemSpec.itemSpec}');
		var itemSkuStr = JSON.parse('${itemSkuList}');
		var itemLowPrice = ${itemVo.lowPrice};
		var shopId = ${itemVo.shopId}
		var itemImage = '${itemVo.image}'.split(',');
		var itemVo;
		$(function() {
			itemVo = new Vue({
				el : "#itemDetail",
				data : {
					itemSpec : itemSpecStr,//商品规格
					itemSku : itemSkuStr,//商品SKU
					lowPrice : itemLowPrice,//最低价格,分为单位
					highPrice : 0,//最高价格,分为单位
					price : '',//价格范围
					allNum : 0,//所有库存
					image : itemImage,
					specIndex : new Array(itemSpecStr.length)
				//商品规格值选择状态

				},
				computed : {
					//当前价格
					currentPrice : function() {
						if (this.currentSku.id == -1) {
							return this.price
						} else {
							return (this.currentSku.price / 100).toFixed(2)
						}
					},
					//计算当前选中的sku
					currentSku : function() {
						var size = 0;
						var index = 0;
						for (var i = this.specIndex.length - 1; i >= 0; i--) {
							//如果当前未选择全则返回null
							if (this.specIndex[i] == -1) {
								return {
									id : -1,
									num : this.allNum,
									image : this.image[0],
									price : this.price
								};
							}
							if (i == this.specIndex.length - 1) {
								index = this.specIndex[i];
								size = this.itemSpec[i].value.length;
							} else {
								index = index + this.specIndex[i] * size;
								size = size * this.itemSpec[i].value.length;
							}
						}
						return this.itemSku[index];
					}
				},
				methods : {
					//商品规格选择添加class
					addClass : function(spec_i, index) {
						if (itemVo.specIndex[spec_i] == index) {
							Vue.set(itemVo.specIndex, spec_i, -1);
						} else {
							Vue.set(itemVo.specIndex, spec_i, index);
						}
					},
					//加入收藏
					addCollection : function(){
            $.post('${pageContext.request.contextPath}/collection/add',
	            {
            	  itemId: '${itemVo.id}'
	            },
	            function(res){
	              data = JSON.parse(res);
	              if(data.status==200){
	            	  $.toast("收藏成功");
	              }
	            }
            )						
					},
					//加入购物车
					addCart : function() {
						//判断当前是否有已选择相应的规格
						var skuId = this.currentSku.id;
						if (skuId == -1) {
                $.toptip('请先选择规格',2000, 'warning');
                return;
			      }
						$.post('${pageContext.request.contextPath}/shopingCart/add',
						  {
							   skuId : skuId,
							   shopId : shopId,
							   num : 1
						  },
						  function(res){
							  data = JSON.parse(res);
							  if(data.status==200){
								  $("#join_cart").popup();
						      console.log('加入购物车');
							  }
						  }
						)
					},
					confirmOrder : function(){
            //判断当前是否有已选择相应的规格
            var skuId = this.currentSku.id;
            if (skuId == -1) {
                $.toptip('请先选择规格',2000, 'warning');
                return;
            }
            window.location.href='${pageContext.request.contextPath}/order/pay?skuId='+skuId+'&num=1';
					}
				},
				created : function() {
					//初始化商品规格值选择状态
					for (var i = 0; i < itemSpecStr.length; i++) {
						this.specIndex[i] = -1;
					}
					//初始化最高价格以及库存总量
					for (var i = 0; i < itemSkuStr.length; i++) {
						this.allNum = this.allNum + itemSkuStr[i].num;
						if (itemSkuStr[i].price > this.highPrice)
							this.highPrice = itemSkuStr[i].price;
					}
					//初始化显示价格
					if (this.highPrice == this.lowPrice) {
						this.price = _highPrice
					} else {
						this.price = (this.lowPrice / 100).toFixed(2) + '—'
								+ (this.highPrice / 100).toFixed(2)
					}

				}
			})

			$(".swiper-zhutu").swiper({
				loop : true,
				paginationType : 'fraction',
				autoplay : 5000
			});
		})
	</script>
	<script>
		$(document).on("open", ".weui-popup-modal", function() {
			console.log("open popup");
		}).on("close", ".weui-popup-modal", function() {
			console.log("close popup");
		});
	</script>
	<script>
		$(function() {
			initTopHoverTree("tophov" + "ertree", 30, 10, 10);
		})
	</script>
	<script>
		function initTopHoverTree(hvtid, times, right, bottom) {
			$("#" + hvtid).css("right", right).css("bottmo", bottom);
			$("#" + hvtid).on("click", function() {
				goTopHovetree(times);
			})
			$(window).scroll(function() {
				if ($(window).scrollTop() > 268) {
					$("#" + hvtid).fadeIn(100);
				} else {
					$("#" + hvtid).fadeOut(100);
				}
			});
		}
		//返回顶部动画
		//goTop(500);//500ms内滚回顶部
		function goTopHovetree(times) {
			if (!!!times) {
				$(window).scrollTop(0);
				return;
			}
			var sh = $('body').scrollTop();//移动总距离
			var inter = 13.333;//ms,每次移动间隔时间
			var forCount = Math.ceil(times / inter);//移动次数
			var stepL = Math.ceil(sh / forCount);//移动步长
			var timeId = null;
			function aniHovertree() {
				!!timeId && clearTimeout(timeId);
				timeId = null;
				//console.log($('body').scrollTop());
				if ($('body').scrollTop() <= 0 || forCount <= 0) {//移动端判断次数好些，因为移动端的scroll事件触发不频繁，有可能检测不到有<=0的情况
					$('body').scrollTop(0);
					return;
				}
				forCount--;
				sh -= stepL;
				$('body').scrollTop(sh);
				timeId = setTimeout(function() {
					aniHovertree();
				}, inter);
			}
			aniHovertree();
		}
	</script>
</body>
</html>
