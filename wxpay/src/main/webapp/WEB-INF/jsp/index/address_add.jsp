<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑地址</title>
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
  <div class="wy-header-title">添加地址</div>
</header>
<div class="weui-content">
  <div class="weui-cells weui-cells_form wy-address-edit">
    <form id="form">
	    <div class="weui-cell">
	      <div class="weui-cell__hd"><label class="weui-label wy-lab">收货人</label></div>
	      <div class="weui-cell__bd"><input class="weui-input" type="text" name="name" placeholder="景淘惠"></div>
	    </div>
	    <div class="weui-cell">
	      <div class="weui-cell__hd"><label class="weui-label wy-lab">手机号</label></div>
	      <div class="weui-cell__bd"><input class="weui-input" type="number" name="telphone" pattern="[0-9]*" placeholder="18800000000"></div>
	    </div>
	    <div class="weui-cell">
	      <div class="weui-cell__hd"><label for="name" class="weui-label wy-lab">所在地区</label></div>
	      <div class="weui-cell__bd"><input class="weui-input" id="address" type="text" value="江西省 景德镇市 昌江区" readonly="" data-code="360202" data-codes="360000,360200,360202"></div>
	      <input type="hidden" id="addressInfo" name="addressInfo"/>
	    </div>
	    <div class="weui-cell">
	      <div class="weui-cell__hd"><label class="weui-label wy-lab">邮编</label></div>
	      <div class="weui-cell__bd"><input class="weui-input" type="number" name="zip" pattern="[0-9]*" placeholder="可不填"></div>
	    </div>
	    <div class="weui-cell">
	      <div class="weui-cell__hd"><label class="weui-label wy-lab">详细地址</label></div>
	      <div class="weui-cell__bd">
	        <textarea class="weui-textarea" name="street" placeholder="瓷都大道838号景德镇学院"></textarea>
	      </div>
	    </div>
	    <div class="weui-cell weui-cell_switch">
	      <div class="weui-cell__bd">设为默认地址</div>
	      <div class="weui-cell__ft"><input class="weui-switch" name="isDefault" type="checkbox"></div>
	    </div>
	  </div>
  </form>
  <div class="weui-btn-area">
    <a class="weui-btn weui-btn_primary" href="javascript:addAddress();" id="showTooltips">保存此地址</a>
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
<script src="${pageContext.request.contextPath}/jquery-weui/js/city-picker.js"></script>
<script>
  $("#address").cityPicker({
    title: "选择出发地",
    onChange: function (picker, values, displayValues) {
      console.log(values, displayValues);
      $("#addressInfo").val(displayValues);
    }
  });
  function addAddress(){
	  //效验用户输入值
	  
	  
	  
	  $.post('${pageContext.request.contextPath}/address/add',{
		  name: $("input[name='name']").val(),
		  telphone: $("input[name='telphone']").val(),
		  addressInfo: $("input[name='addressInfo']").val(),
		  zip: $("input[name='zip']").val(),
		  street: $("textarea[name='street']").val(),
		  isDefault: $("input[name='isDefault']").is(':checked')
	  },function(res){
				  var data = JSON.parse(res);
				  if(data.status==200){
					  $.toast("添加成功");
					  self.location=document.referrer;
				  }else{
					  $.toptip('添加失败', 'error');
				  }
	  })
  }
</script>
</body>
</html>
