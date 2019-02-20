<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <div class="weui-cell__bd"><input class="weui-input" value="${address.name}" type="text" name="name" placeholder="景淘惠"></div>
      </div>
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label wy-lab">手机号</label></div>
        <div class="weui-cell__bd"><input class="weui-input" value="${address.telphone}" type="number" name="telphone" pattern="[0-9]*" placeholder="18800000000"></div>
      </div>
      <div class="weui-cell">
        <div class="weui-cell__hd"><label for="name" class="weui-label wy-lab">所在地区</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input" id="address" type="text" 
            value="${address.province} ${address.city} ${address.area}" readonly="">
        </div>
        <input type="hidden" value="${address.province},${address.city},${address.area}" id="addressInfo" name="addressInfo"/>
      </div>
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label wy-lab">邮编</label></div>
        <div class="weui-cell__bd"><input class="weui-input" value="${address.zip}" type="number" name="zip" pattern="[0-9]*" placeholder="可不填"></div>
      </div>
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label wy-lab">详细地址</label></div>
        <div class="weui-cell__bd">
          <textarea class="weui-textarea" name="street" placeholder="瓷都大道838号景德镇学院">${address.street}</textarea>
        </div>
      </div>
			<div class="weui-cell weui-cell_switch">
				<div class="weui-cell__bd">设为默认地址</div>
				<div class="weui-cell__ft">
				  <c:if test="${address.isDefault}">
				    <input class="weui-switch" name="isDefault" type="checkbox" checked="checked">
				  </c:if>
				  <c:if test="${!address.isDefault}">
				     <input class="weui-switch" name="isDefault" type="checkbox">
				  </c:if>
				</div>
			</div>
      
	  </form>
	  <div class="weui-btn-area">
	    <a class="weui-btn weui-btn_primary" href="javascript:updateAddress();" id="showTooltips">保存此地址</a>
	    <a href="javascript:deleteAddress();" class="weui-btn weui-btn_warn">删除此地址</a>
    </div>
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
  //更新收货地址
  function updateAddress(){
    $.post('${pageContext.request.contextPath}/address/update',{
    	id: '${address.id}',
    	name: $("input[name='name']").val(),
      telphone: $("input[name='telphone']").val(),
      addressInfo: $("input[name='addressInfo']").val(),
      zip: $("input[name='zip']").val(),
      street: $("textarea[name='street']").val(),
      isDefault: $("input[name='isDefault']").is(':checked')
    },function(res){
          var data = JSON.parse(res);
          if(data.status==200){
            $.toast("更新成功");
            self.location=document.referrer;
          }else{
            $.toptip('更新失败', 'error');
          }
    })
  }
  //删除收货地址
  function deleteAddress(){
	  $.get('${pageContext.request.contextPath}/address/delete',{
		  addressId: '${address.id}'
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