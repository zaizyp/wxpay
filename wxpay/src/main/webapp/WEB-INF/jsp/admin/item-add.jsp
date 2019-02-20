<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style type="text/css">
/* 商品添加 规格明细 */
#spec_details li{
	list-style:none;
	width: 17%;
	display: inline-block;
	text-align: center;
	margin: 0px;
	padding: 10px 0px;
}
#spec_details ul{
	margin: 5px 0px;
	padding-left: 10px;
}
.spec_details_titel{
	background-color: #F7F7F7;
}
</style>

<div style="padding:10px 10px 10px 10px">
	<form id="itemAddForm" class="itemForm" method="post">
	    <table cellpadding="6">
	        <tr>
	            <td>商品类目:</td>
	            <td>
	            	<a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
	            	<input type="hidden" name="cid" style="width: 20px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td>商品标题:</td>
	            <td><input class="easyui-textbox" type="text" name="title" data-options="required:true,validType:'length[0,33]'" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>商品卖点:</td>
	            <td><input class="easyui-textbox" name="sellPoint" data-options="multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>商品图片:</td>
	            <td>
	            	 <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
	                 <input type="hidden" name="image"/>
	            </td>
	        </tr>
	        <br />
	        <tr>
	        	<td>商品规格</td>
	        	<td>
	        		<table id="spec_table" style="background-color: #F7F7F7;">
				        <tr class="spec_table_name">
				        	<td>规格名(如: 颜色):</td>
				            <td><input type="text" style="width: 100px;"></input></td>
				        </tr>
				        <tr class="spec_table_val">
				        	<td>规格值(如: 红色):</td>
				            <td><input type="text" style="width: 100px;" onblur="show_spec_details()"></input></td>
				            <td><input type="text" style="width: 100px;" onblur="show_spec_details()"></input></td>
				            <td><a style="width: 100px;" href="javascript:void(0)" class="easyui-linkbutton" onclick="add_spec_val(this)">添加规格值</a></td>
				            <td><a style="width: 100px;" href="javascript:void(0)" class="easyui-linkbutton" onclick="delete_spec_val(this)">删除规格值</a></td>
				        </tr>
				        <tr>
				        	<td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="add_spec(this)">添加规格</a></td>
				        	<td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="delete_spec(this)">删除规格</a></td>
				        </tr>
					</table>
				</td>
	        </tr>
	        <tr>
	        	<td>规格明细</td>
	        	<td id="spec_details">
	        		<ul class="spec_details_titel">
	        			<li class="templateIndex">库存</li>
	        			<li>价格</li>
	        			<li>图片</li>
	        		</ul>
	        	</td>
	        </tr>
	        <tr>
	            <td>商品描述:</td>
	            <td>
	                <textarea style="width:800px;height:300px;visibility:hidden;" name="desc"></textarea>
	            </td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<ul style="display: none;">
	<li><input class="easyui-textbox" type="text" name="title" data-options="required:true,validType:'length[0,33]'" style="width: 80px;"></input></li>
</ul>
<div  class="itemParamAddTemplate" style="display: none;">
	<ul>
		<li class="templateIndex">
			<input type="number" style="width: 100px;display: inline-block;" name="num"/>&nbsp;
		</li>
		<li>
			<input type="number" style="width: 100px;display: inline-block;" name="price"/>&nbsp;
		</li>
		<li>
			<input type="image" style="display: none;" name="image"/>
			<a href="javascript:void(0)" class="easyui-linkbutton delParam" data-options="plain:true,iconCls:'icon-cancel'">上传图片</a>						
		</li>
	</ul>
	<ul class="spec_details_titel">
		<li class="templateIndex">库存</li>
		<li>价格</li>
		<li>图片</li>
	</ul>
</div>
<script type="text/javascript">
	var spec_list;//商品规格列表
	//获取规格明细并显示sku列表
	function show_spec_details(){

		spec_list = new Array();//重新初始化商品规格列表
		$(".spec_table_name").each(function(i,e){
			//判断规格名是否有值，为空则不添加进去
			if($(e).find("input").val().length>0){
				var spec_details = new Object();//商品规格
				var value = new Array();//商品规格值
				
				$(e).next().find("input").each(function(j,event){
					if($(event).val().length>0)
					value.push($(event).val());
				});
				spec_details.name = $(e).find("input").val();
				spec_details.value = value;
				if(value.length>0)
				spec_list.push(spec_details);
			}
		});
		console.log("规格数组：");
		console.log(spec_list);
		generate_spec_list(spec_list);
	}
	
	//生成规格明细列表
	var temple1 = '<ul class="spec_details_titel"><li class="templateIndex">库存</li><li>价格</li><li>图片</li></ul>';
	function generate_spec_list(spec_list){
		//将之前生成的列表清空
		$("#spec_details").html(temple1);
		//添加规格名并把规格值取出
		var value_list = new Array();
		$.each(spec_list,function(i,spec_details){
			$(".spec_details_titel").children("li.templateIndex").before('<li style="width:15%;">'+spec_details.name+'</li>');
			value_list.push(spec_details.value);
		});
		console.log("规格值数组：");
		console.log(value_list);
		//根据规格名生成sku列表
		if(value_list.length>1){
			var sku = discarts(value_list);		
			console.log("sku：");
			console.log(sku);
			$.each(sku,function(i,list){
				var temple = $(".itemParamAddTemplate ul").eq(0).clone();
				$.each(list,function(j,value){
					temple.children("li.templateIndex").before('<li style="width:15%;">'+value+'</li>');
				});
				$("#spec_details").append(temple);
			});
		}else{
			//当规格只有一种时不需要进行笛卡尔积运算
			console.log("sku：");
			console.log(value_list);
			$.each(value_list[0],function(i,e){				
				var temple = $(".itemParamAddTemplate ul").eq(0).clone();
				temple.children("li.templateIndex").before('<li style="width:15%;">'+e+'</li>');
				$("#spec_details").append(temple);
			})
		}
	}
	//计算笛卡尔积
	function discarts() {
		//笛卡尔积
		var twodDscartes = function(a, b) {
			var ret = [];
			for (var i = 0; i < a.length; i++) {
				for (var j = 0; j < b.length; j++) {
					ret.push(ft(a[i], b[j]));
				}
			}
			return ret;
		}
		var ft = function(a, b) {
			if (!(a instanceof Array))
				a = [ a ];
			var ret = a.slice(0);
			ret.push(b);
			return ret;
		}
		//多个一起做笛卡尔积
		return (function(data) {
			var len = data.length;
			if (len == 0)
				return [];
			else if (len == 1)
				return data[0];
			else {
				var r = data[0];
				for (var i = 1; i < len; i++) {
					r = twodDscartes(r, data[i]);
				}
				return r;
			}
		})(arguments.length > 1 ? arguments : arguments[0]);
	}

	//添加规格
	function add_spec(e) {
		var str = '<tr class="spec_table_name">'
				+ '<td>规格名(如: 颜色):</td>'
				+ '<td><input type="text" style="width: 100px;"></input></td>'
				+ '</tr>'
				+ '<tr class="spec_table_val">'
				+ '<td>规格值(如: 红色):</td>'
				+ '<td><input type="text" style="width: 100px;" onblur="show_spec_details()"></input></td>'
				+ '<td><input type="text" style="width: 100px;" onblur="show_spec_details()"></input></td>'
				+ '<td><a style="width: 100px;" href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-small" onclick="add_spec_val(this)"><span class="l-btn-left"><span class="l-btn-text">添加规格值</span></span></a></td>'
				+ '<td><a style="width: 100px;" href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-small" onclick="delete_spec_val(this)"><span class="l-btn-left"><span class="l-btn-text">删除规格值</span></span></a></td>'
				+ '</tr>'
		$(e).parent().parent().before(str);
	}
	//删除规格
	function delete_spec(e) {
		if ($(e).parent().parent().parent().children("tr").length > 4) {
			$(e).parent().parent().prev().remove();
			$(e).parent().parent().prev().remove();
			//刷新规格详细列表
			show_spec_details();
		}
	}
	//添加规格值
	function add_spec_val(e) {
		$(e).parent().before('<td><input onblur="show_spec_details()" /></td>');
		$(e).parent().prev().find("input").attr({
			"class" : "easyui-textbox",
			"type" : "text",
			"style" : "width: 100px;",
			"data-options" : "validType:'length[0,33]'"
		})
	}
	//删除规格值
	function delete_spec_val(e) {
		if ($(e).parent().parent().children("td").length > 4){
			$(e).parent().prev().prev().remove();
			//刷新规格详细列表
			show_spec_details();
		}
	}

	var itemAddEditor;
	//页面初始化完毕后执行此方法
	$(function() {
		//创建富文本编辑器
		itemAddEditor = TAOTAO.createEditor("#itemAddForm [name=desc]");
		//初始化类目选择和图片上传器
		TAOTAO.init({
			fun : function(node) {
				//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
				TAOTAO.changeItemParam(node, "itemAddForm");
			}
		});
	});
	//提交表单
	function submitForm() {
		//有效性验证
/*		if (!$('#itemAddForm').form('validate')) {
			$.messager.alert('提示', '表单还未填写完成!');
			return;
		}
		//取商品价格，单位为“分”
		$("#itemAddForm [name=price]").val(
				eval($("#itemAddForm [name=priceView]").val()) * 100);
		//同步文本框中的商品描述
		itemAddEditor.sync();
		//取商品的规格

 		var paramJson = [];
		$("#itemAddForm .params li").each(function(i, e) {
			var trs = $(e).find("tr");
			var group = trs.eq(0).text();
			var ps = [];
			for (var i = 1; i < trs.length; i++) {
				var tr = trs.eq(i);
				ps.push({
					"k" : $.trim(tr.find("td").eq(0).find("span").text()),
					"v" : $.trim(tr.find("input").val())
				});
			}
			paramJson.push({
				"group" : group,
				"params" : ps
			});
		});
		//把json对象转换成字符串
		paramJson = JSON.stringify(paramJson);
		$("#itemAddForm [name=itemParams]").val(paramJson); */

		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		
		//同步文本框中的商品描述
		itemAddEditor.sync();
		
		//获取商品规格详情
		var itemSkuList = [];//商品SKU列表
		var names = [];
		$("#spec_details ul").each(function(i,ul){
			//第一个ul为规格名
			if(i==0){
				$(ul).children("li").each(function(j,li){
					if($(li).attr("class")=="templateIndex")
						return false;
					names.push($(li).text());
				})
			}else{
				//其余ul为规格值
				var itemSku = {};//SKU
				var itemSpecValue = [];//规格值item_spec_value
				$(ul).children("li").each(function(k,li){
					if(k==names.length)
						return false;
					itemSpecValue.push({'name':names[k],'value':$(li).text()});
				});
				itemSku.itemSpecValue = itemSpecValue;
				itemSku.num = $(ul).find('input[name="num"]').val();
				itemSku.price = $(ul).find('input[name="price"]').val();
				itemSkuList.push(itemSku);
			}
		});
		console.log(itemSkuList);
		
		
		
		$.post("/admin/item/save", 
			{
				cid : $("input[name='cid']").val(),
				title : $("input[name='title']").val(),
				sellPoint : $("input[name='sellPoint']").val(),
				image : $("input[name='image']").val(),
				itemDesc : $("textarea[name='desc']").val(),
				itemSkuList : JSON.stringify(itemSkuList),
				itemSpec : JSON.stringify(spec_list)
			}, 
			function(data) {
				var result = JSON.parse(data);
				if (result.status == 200) {
					$.messager.alert('提示', '新增商品成功!');
					location.reload();
			}
		});
	}

	function clearForm() {
		$('#itemAddForm input').val("");
		itemAddEditor.html('');
		$("#spec_details").html(temple1);
	}
</script>
