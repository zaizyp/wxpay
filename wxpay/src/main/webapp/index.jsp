<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="multipart/form-data;charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>微信支付</title>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.4.0.js"></script>

</head>
<body>
<h1>
	<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx737e58bd1ab509c4&redirect_uri=https%3a%2f%2fwxpay.dream520studio.cn%2fwxpay%2fwx%2flogin&response_type=code&scope=snsapi_base#wechat_redirect">登录</a>
</h1>

<input type="button" id="J_selectImage" value="批量上传" />
<div id="J_imageView"></div>

<script type="text/javascript">

KindEditor.ready(function(K) {
    K.create('#textarea_id', {
            uploadJson : '../jsp/upload_json.jsp',
            fileManagerJson : '../jsp/file_manager_json.jsp',
            allowFileManager : true
    });


KindEditor.ready(function(K) {
	var editor = K.editor({
		allowFileManager : true,
		//指定上传文件参数名称
		filePostName  : "uploadFile",
		//指定上传文件请求的url。
		uploadJson : '/pic/upload',
		//上传类型，分别为image、flash、media、file
		dir : "image"
	});
	K('#J_selectImage').click(function() {
		editor.loadPlugin('multiimage', function() {
			editor.plugin.multiImageDialog({
				clickFn : function(urlList) {
					var div = K('#J_imageView');
					div.html('');
					K.each(urlList, function(i, data) {
						div.append('<img src="' + data.url + '">');
					});
					editor.hideDialog();
				}
			});
		});
	});
});
</script>

</body>
</html>