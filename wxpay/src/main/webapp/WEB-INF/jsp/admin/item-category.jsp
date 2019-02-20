<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<!-- 商品分类数据表 -->
<table id="item_cat_dg" title="分类列表" class="easyui-treegrid"
	toolbar="#item_cat_toolbar" 
	singleSelect="true"
	data-options="
		url: '/admin/item/cat/detailsList?',
		method: 'post',
		cache:false,
		rownumbers: true,
		animate: true,
		collapsible: true,
		idField: 'id',
		treeField: 'name'
	">
	<thead>
		<tr>
			<th data-options="field:'name', width:150">分类名称</th>
			<th data-options="field:'image', formatter:formatImage, width:150">图标</th>
			<th data-options="field:'status', formatter:formatStatus, width:150">状态</th>
			<th data-options="field:'sortOrder', width:150">排序</th>
			<th data-options="field:'parentId', hidden: true" >父分类</th>
		</tr>
	</thead>
</table>
<!-- 编辑按钮 -->
<div id="item_cat_toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newItemCat()">添加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editItemCat()">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyItemCat()">删除</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="javascript:$('#item_cat_dg').treegrid('reload');">刷新</a>
</div>
<!-- 编辑表单 -->
<div id="item_cat_dlg" class="easyui-dialog" style="width:450px;height:500px;padding:10px 20px"
        closed="true" buttons="#item_cat_dlg_buttons">
    <form id="item_cat_fm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>父分类:</td>
	            <td><input id="parentId" class="easyui-combobox" name="parentId"
    					data-options="required:true,valueField:'id',textField:'text',url:'/admin/item/cat/list?id=-1'">
    			</td>
	        </tr>
	        <tr>
	            <td>分类名称:</td>
	            <td><input class="easyui-textbox" type="text" name="name" data-options="required:true,validType:'length[0,50]'" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>分类状态</td>
	            <td>
	            	<select class="easyui-combobox" name="status" data-options="required:true,multiline:true" style="width:200px;">
	            		<option value="1">正常</option>
	            		<option value="2">删除</option>
	            	</select>
	            </td>
	        </tr>
	        <tr>
	            <td>分类排序:</td>
	            <td><input class="easyui-numberbox" type="number" name="sortOrder" data-options="min:1,max:99999999,precision:0,required:true" /></td>
	        </tr>
	        <tr>
	            <td>分类图标:</td>
	            <td>
	            	 <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
	                 <input type="hidden" name="image"/>
	            </td>
	        </tr>
	    </table>
	</form>
	<script type="text/javascript">
		$(function(){
			//初始化类目选择和图片上传器
			TAOTAO.init({fun:function(node){
				//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
				//TAOTAO.changeItemParam(node, "item_cat_fm");
			}});
		})
	</script>
</div>
<div id="item_cat_dlg_buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveItemCat()">Save</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#item_cat_dlg').dialog('close')">Cancel</a>
</div>

<script type="text/javascript">

//新建分类
function newItemCat(){
    $('#item_cat_dlg').dialog('open').dialog('setTitle','新建分类');
    $('#item_cat_fm').form('clear');
    url = '/admin/item/cat/create';
}
//编辑分类
function editItemCat(){
	var row = $('#item_cat_dg').datagrid('getSelected');
	if (row){
	    $('#item_cat_dlg').dialog('open').dialog('setTitle','修改分类');
	    $('#item_cat_fm').form('load',row);
	    url = '/admin/item/cat/update?id='+row.id;
	}
}
//删除分类
function destroyItemCat(){
    var row = $('#item_cat_dg').datagrid('getSelected');
    if (row){
        $.messager.confirm('Confirm','确认删除这条分类吗?',function(r){
            if (r){
                $.post('/admin/item/cat/delete',{id:row.id},function(result){
                    if (result.status==200){
                        $('#item_cat_dg').treegrid('reload');    // reload the user data
                    } else {
                        $.messager.show({    // show error message
                            title: 'Error',
                            msg: result.msg
                        });
                    }
                },'json');
            }
        });
    }
}
//保存数据
function saveItemCat(){
    $('#item_cat_fm').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval("("+result+")");
            if (result.status==200){
                $('#item_cat_dlg').dialog('close');        // close the dialog
                $('#item_cat_dg').treegrid('reload');    // reload the user data
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
            }
        }
    });
}

//图片显示格式转换
function formatImage(value){
	if(value){
		return '<img alt="图标" height="50px" width="50px"  src="'+value+'">';
	}else {
		return "暂无图标";
	}
}
//状态显示格式转换
function formatStatus(value){
	switch (value) {
	case 1: 
		return "正常";
		
	case 2: 
		return '<font style="color:red;">删除</font>';

	default:
		return "";
	}
}

</script>