<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>购物车</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-weui/lib/weui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-weui/css/jquery-weui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<style>
  [v-cloak] {
  display: none;
}
</style>
</head>
<body ontouchstart>
<!--主体-->
<header class="wy-header">
  <div class="wy-header-icon-back"><span></span></div>
  <div class="wy-header-title">购物车</div>
</header>
<div id="shopCart" >
	<div class="weui-content" v-for="(cart,index) in cartList" :key="cart.id">
	  <div class="weui-panel weui-panel_access">
	    <div class="weui-panel__hd">
	      <span>{{cart.shop.shopName}}</span>
	      <a href="javascript:;" @click="deleteCart(cart.id)" class="wy-dele"></a>
	    </div>
	    <div class="weui-panel__bd">
	      <div class="weui-media-box_appmsg pd-10">
	        <div class="weui-media-box__hd check-w weui-cells_checkbox">
	          <label class="weui-check__label" :for="'cart-pto'+index">
	            <div class="weui-cell__hd cat-check">
	              <input @click="totalPricefunc()" :id="'cart-pto'+index" type="checkbox" class="weui-check" name="cartpro">
	              <i class="weui-icon-checked"></i>
	            </div>
	          </label>
	        </div>
	        <div class="weui-media-box__hd">
	          <a :href="'${pageContext.request.contextPath}/item/'+cart.item.id">
	            <img class="weui-media-box__thumb" :src="'${pageContext.request.contextPath}'+getImage(index)" alt="">
	          </a>
	        </div>
	        <div class="weui-media-box__bd">
	          <h1 class="weui-media-box__desc">
	            <a  v-cloak :href="'${pageContext.request.contextPath}/item/'+cart.item.id" class="ord-pro-link">{{cart.item.title}}</a>
	          </h1>
	          <p class="weui-media-box__desc">
	                               规格：<span v-cloak v-for="(spec,index) in cart.itemSku.itemSpecValue">
	                   <template v-if="index!=0">；</template>
	                   {{spec.value}}
	                 </span>
	          </p>
	          <div class="clear mg-t-10">
	            <div class="wy-pro-pri fl">¥<em v-cloak class="num font-15">{{getPrice(index)}}</em></div>
	            <div class="pro-amount fr">
	              <div id="Spinner1" class="Spinner">
	                <!-- DisDe为不可减， Decrease为可减-->
	                <a :class="{ DisDe: cart.num <= 1,Decrease: cart.num > 1 }"
	                   href="javascript:void(0)" @click="decrease(index)"><i>-</i></a>
	                <input class="Amount" v-model="cart.num" autocomplete="off" maxlength="3">
	                <!-- DisIn为不可加，Increase为可加 -->
	                <a :class="{ DisIn: !isIncrease(index),Increase: isIncrease(index) }"
	                   href="javascript:void(0)" @click="increase(index)"><i>+</i></a>
	              </div>
	            </div>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
	<!--底部导航-->
	<div class="foot-black"></div>
	<div class="weui-tabbar wy-foot-menu">
	  <div class="npd cart-foot-check-item weui-cells_checkbox allselect">
	    <label class="weui-cell allsec-well weui-check__label" for="all">
	        <div class="weui-cell__hd">
	          <input type="checkbox" class="weui-check" name="all-sec" id="all">
	          <i class="weui-icon-checked"></i>
	        </div>
	        <div class="weui-cell__bd">
	          <p class="font-14">全选</p>
	        </div>
	    </label>
	  </div>
	  <div class="weui-tabbar__item  npd">
	    <p class="cart-total-txt">合计：<i>￥</i><em v-cloak class="num font-16" id="zong1">{{totalPrice}}</em></p>
	  </div>
	  <a href="order_info2.html" class="red-color npd w-90 t-c">
	    <p class="promotion-foot-menu-label">去结算</p>
	  </a>
	</div>
</div>
<script src="${pageContext.request.contextPath}/jquery-weui/lib/jquery-2.1.4.js"></script> 
<script src="${pageContext.request.contextPath}/jquery-weui/lib/fastclick.js"></script> 
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>

<script src="${pageContext.request.contextPath}/jquery-weui/js/jquery-weui.js"></script>
<script type="text/javascript">
	var shopCart;
$(function(){
	var cartListObj = JSON.parse('${cartList}');
	var updateNum = new Array(cartListObj.length);
	shopCart = new Vue({
		el : '#shopCart',
		data : {
			cartList: cartListObj,
			totalPrice: 0
		},
		computed : {
			
		},
		watch: {
			//监听商品数量的变化
			cartList: function(newCart,oldCart){
				for(var i = 0;i < newCart.length; i++){
					if(newCart[i].num<1){
						var cart = newCart[i];
						cart.num = 1;
						Vue.set(this.cartList,i,cart);
						$.toptip('商品数量不能少于1个！', 1000, 'warning');
					}
					if(newCart[i].num>999||newCart[i].num>newCart[i].itemSku.num){
						if(newCart[i].itemSku.num>999){
							var cart = newCart[i];
	            cart.num = 999;
	            Vue.set(this.cartList,i,cart);
	            $.toptip('商品数量不能大于999个！', 1000, 'warning');
						}else{
              var cart = newCart[i];
              cart.num = newCart[i].itemSku.num;
              vue.set(this.cartList,i,cart);
              $.toptip('商品数量不能大于'+newCart[i].itemSku.num+'个！', 1000, 'warning');
						}
					}
				}
			}
		},
		methods : {
			//计算被选中的总金额
      totalPricefunc: function(){
        var price = 0;
        var _self = this;
        $('input[name="cartpro"]').each(function(i,e){
          if($(e).is(':checked')){
            price += (_self.cartList[i].num * _self.cartList[i].itemSku.price);
          }
        });
        this.totalPrice = (price / 100).toFixed(2);
      },
			//获取当前加号能否点击
			isIncrease: function(index){
				if(this.cartList[index].num>=999 || 
						this.cartList[index].num>=this.cartList[index].itemSku.num){
					return false;
				}
				return true;
			},
			//获取商品主图
			getImage : function(index){
				return this.cartList[index].item.image.split(',')[0]
			},
			//获取格式化的价格，单位由分转为元
			getPrice : function(index){
		    return (this.cartList[index].itemSku.price / 100).toFixed(2);
		  },
		  //更新服务器购物车商品数量（当用户在1秒内连续点击多次时只会触发一次与服务器的更新操作）
		  updataCat: function(index){
			  if(updateNum[index])
          clearTimeout(updateNum[index]);//清除上一次的任务
        var _self = this;
        updateNum[index] = setTimeout(function(){
            $.get('${pageContext.request.contextPath}/shopingCart/update',
                    {
                       cartId: _self.cartList[index].id,
                       num: _self.cartList[index].num
                    },
                    function(res){
                      data = JSON.parse(res);
                      if(data.status!=200){
                        $.toptip('操作失败！', 2000, 'error');
                      }
                    }
                  )
        }, 1000);
		  },
		  decrease: function(index){
			  if(this.cartList[index].num==1)
				  return ;
			  let cart = this.cartList[index];
			  cart.num--;
			  Vue.set(this.cartList, index, cart);
			  this.totalPricefunc();
			  this.updataCat(index);
		  },
		  increase: function(index){
			  if(this.cartList[index].itemSku.num==999)
	          return ;
			  let cart = this.cartList[index];
        cart.num++;
        Vue.set(this.cartList, index, cart);
        this.totalPricefunc();
        this.updataCat(index);
		  },
		  //从购物车中删除商品
		  deleteCart: function(cartId){
			  var _self = this;
        $.confirm("您确定要把此商品从购物车删除吗?", "确认删除?", function() {
        	$.get('${pageContext.request.contextPath}/shopingCart/delete',
        			{ cartId: cartId },
        			function(res){
        				var data = JSON.parse(res)
        				if(data.status==200){
        					_self.cartList.pop();
        					$.toast("文件已经删除!");
        				}
        	})
        }, function() {
          //取消操作
        });
		  }
		},
		mounted : function(){
			  
		}
	
	})
	
	$(".allselect").click(function () {
	    if($(this).find("input[name=all-sec]").prop("checked")){
	      $("input[name=cartpro]").each(function () {
	        $(this).prop("checked", true);
	      });
	    
	    }
	    else
	      {
	      $("input[name=cartpro]").each(function () {
	        if ($(this).prop("checked")) {
	          $(this).prop("checked", false);
	        } else {
	          $(this).prop("checked", true);
	        } 
	      });
	    
	      }
	    shopCart.totalPricefunc();
	  });
	
})

</script>


<!---全选按钮-->

<script type="text/javascript">
$(document).ready(function () {
	

});
</script>
<script>
      $(document).on("click", ".wy-dele", function() {
      });

    </script>

</body>
</html>
