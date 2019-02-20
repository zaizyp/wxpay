<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>交易记录</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">
<link rel="stylesheet" href="lib/weui.min.css">
<link rel="stylesheet" href="css/jquery-weui.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body ontouchstart>
<!--主体-->
<header class="wy-header" style="position:fixed; top:0; left:0; right:0; z-index:200;">
  <div class="wy-header-icon-back"><span></span></div>
  <div class="wy-header-title">交易记录</div>
</header>
<div class='weui-content'>
  <div class="weui-tab">
    <div class="weui-navbar" style="position:fixed; top:44px; left:0; right:0; height:44px; background:#fff;">
      <a class="weui-navbar__item proinfo-tab-tit font-14 weui-bar__item--on" href="#tab1">商品购买</a>
      <a class="weui-navbar__item proinfo-tab-tit font-14" href="#tab2">充值记录</a>
      <a class="weui-navbar__item proinfo-tab-tit font-14" href="#tab3">提现记录</a>
    </div>
    <div class="weui-tab__bd proinfo-tab-con" style="padding-top:87px;">
      <div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
        <div class="weui-panel jyjl">
          <div class="weui-panel__hd">购买商品总金额：-￥<em class="num">56.5</em></div>
          <div class="weui-panel__bd">
            <div class="weui-media-box weui-media-box_text">
              <h4 class="weui-media-box__title">-￥<em class="num">56.5</em></h4>
              <p class="weui-media-box__desc">订单号：645564231654564</p>
              <ul class="weui-media-box__info">
                <li class="weui-media-box__info__meta">交易时间：<em class="num">2015-10-16 13:00</em></li>
              </ul>
            </div>
          </div>
          <div class="weui-panel__bd">
            <div class="weui-media-box weui-media-box_text">
              <h4 class="weui-media-box__title">-￥<em class="num">56.5</em></h4>
              <p class="weui-media-box__desc">订单号：645564231654564</p>
              <ul class="weui-media-box__info">
                <li class="weui-media-box__info__meta">交易时间：<em class="num">2015-10-16 13:00</em></li>
              </ul>
            </div>
          </div>
        </div>
        
        
      </div>
      <div id="tab2" class="weui-tab__bd-item">
        <div class="weui-panel jyjl">
          <div class="weui-panel__hd">充值总金额：+￥<em class="num">1000.0</em></div>
          <div class="weui-panel__bd">
            <div class="weui-media-box weui-media-box_text">
              <h4 class="weui-media-box__title">+￥<em class="num">500.0</em></h4>
              <p class="weui-media-box__desc">充值方式：微信支付</p>
              <ul class="weui-media-box__info">
                <li class="weui-media-box__info__meta">充值时间：<em class="num">2015-10-16 13:00</em></li>
              </ul>
            </div>
          </div>
          <div class="weui-panel__bd">
            <div class="weui-media-box weui-media-box_text">
              <h4 class="weui-media-box__title">+￥<em class="num">500.0</em></h4>
              <p class="weui-media-box__desc">充值方式：微信支付</p>
              <ul class="weui-media-box__info">
                <li class="weui-media-box__info__meta">充值时间：<em class="num">2015-10-16 13:00</em></li>
              </ul>
            </div>
          </div>
        </div>
        
      </div>
      <div id="tab3" class="weui-tab__bd-item">
        <div class="weui-panel jyjl">
          <div class="weui-panel__hd">提现总金额：-￥<em class="num">500.0</em></div>
          <div class="weui-panel__bd">
            <div class="weui-media-box weui-media-box_text">
              <h4 class="weui-media-box__title">-￥<em class="num">200.0</em></h4>
              <p class="weui-media-box__desc">提现到银行卡：60002*******54564</p>
              <ul class="weui-media-box__info">
                <li class="weui-media-box__info__meta">提现时间：<em class="num">2015-10-16 13:00</em></li>
              </ul>
            </div>
          </div>
          <div class="weui-panel__bd">
            <div class="weui-media-box weui-media-box_text">
              <h4 class="weui-media-box__title">-￥<em class="num">300.0</em></h4>
              <p class="weui-media-box__desc">提现到银行卡：60002*******54564</p>
              <ul class="weui-media-box__info">
                <li class="weui-media-box__info__meta">提现时间：<em class="num">2015-10-16 13:00</em></li>
              </ul>
            </div>
          </div>
        </div>
        
      </div>
    </div>
  </div>
</div>

<script src="lib/jquery-2.1.4.js"></script> 
<script src="lib/fastclick.js"></script> 
<script type="text/javascript" src="js/jquery.Spinner.js"></script>
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
<script src="js/jquery-weui.js"></script>
</body>
</html>
