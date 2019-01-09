<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<div class="easyui-accordion" data-options="fit: true" style="height: 600px">
    <!-- 商品基础信息 -->
    <div title="商品基础信息" iconCls="icon-ok" style="overflow:auto;padding:10px;">
        <div style="padding:10px 10px 10px 10px">
            <form id="spuBasicAddForm" class="itemForm" method="post">
                <table cellpadding="5">
                    <tr>
                        <td>商品类目:</td>
                        <td>
                            <a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
                            <input type="hidden" name="catId" style="width: 280px;"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>商品标题:</td>
                        <td><input class="easyui-textbox" type="text" name="spuName" data-options="required:true" style="width: 280px;"></input></td>
                    </tr>
                    <tr>
                        <td>商品卖点:</td>
                        <td><input class="easyui-textbox" name="sellPoint" data-options="multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;"></input></td>
                    </tr>
                    <tr>
                        <td>最低价:</td>
                        <td><input class="easyui-numberbox" type="text" name="spuPrice" data-options="min:1,max:99999999,precision:2,required:true" />
                            <input type="hidden" name="spuPrice"/>
                        </td>
                    </tr>
                    <tr>
                        <td>销量(虚拟+实际):</td>
                        <td><input class="easyui-numberbox" type="text" name="saleNum" data-options="min:1,max:99999999,precision:0,required:true" /></td>
                    </tr>
                    <tr>
                        <td>排序:</td>
                        <td><input class="easyui-textbox" name="sort" data-options="multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;"></input></td>
                    </tr>
                    <tr>
                        <td>商品图片:</td>
                        <td>
                            <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
                            <input type="hidden" name="imgs"/>
                        </td>
                    </tr>
                    <#--<tr class="params hide">-->
                        <#--<td>商品规格:</td>-->
                        <#--<td>-->

                        <#--</td>-->
                    <#--</tr>-->
                </table>
                <input type="hidden" name="itemParams"/>
            </form>
            <div style="padding:5px">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitSpuBasicForm()">提交</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearSpuBasicForm()">重置</a>
            </div>
        </div>
    </div>

    <!-- 商品详情文案 -->
    <div title="商品详情文案" iconCls="icon-reload" style="padding:10px;">
        <form id="spuDescAddForm" method="post">
            <table cellpadding="5">
                <tr>
                    <td>商品描述:</td>
                    <td>
                        <textarea style="width:800px;height:300px;visibility:hidden;" name="spuDesc"></textarea>
                    </td>
                </tr>
            </table>
        </form>
        <div style="padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitSpuDescForm()">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearSpuDescForm()">重置</a>
        </div>
    </div>

    <!-- 商品sku参数信息 -->
    <div title="商品sku参数信息" iconCls="icon-reload" style="padding:10px;">
        easyui help you build your web page easily
    </div>

    <!-- 商品其他参数信息 -->
    <div title="商品其他参数信息" iconCls="icon-reload" style="padding:10px;">
        easyui help you build your web page easily
    </div>
</div>


<script type="text/javascript">
	var spuDescAddEditor ;
	//页面初始化完毕后执行此方法
	$(function(){
		//1. 创建富文本编辑器
        spuDescAddEditor = E3.createEditor("#spuDescAddForm [name=spuDesc]");
		//2. 初始化类目选择和图片上传器
        E3.initItemCat();
        E3.initPicUpload();

        //E3.init({fun:function(node){
			//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
			//E3.changeItemParam(node, "spuAddForm");
		//}});
	});

	//提交商品基础信息表单
	function submitSpuBasicForm(){
		//有效性验证
		if(!$('#spuBasicAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//取商品价格，单位为“分”
		$("#spuBasicAddForm [name=spuPrice]").val(eval($("#spuBasicAddForm [name=spuPrice]").val()) * 100);

		//取商品的规格
		/*
		var paramJson = [];
		$("#itemAddForm .params li").each(function(i,e){
			var trs = $(e).find("tr");
			var group = trs.eq(0).text();
			var ps = [];
			for(var i = 1;i<trs.length;i++){
				var tr = trs.eq(i);
				ps.push({
					"k" : $.trim(tr.find("td").eq(0).find("span").text()),
					"v" : $.trim(tr.find("input").val())
				});
			}
			paramJson.push({
				"group" : group,
				"params": ps
			});
		});
		//把json对象转换成字符串
		paramJson = JSON.stringify(paramJson);
		$("#itemAddForm [name=itemParams]").val(paramJson);
		*/
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/spu/save",$("#spuBasicAddForm").serialize(), function(data){
			if(data.code == 1){
		        $.messager.alert('提示','新增商品成功!');
            }
		});
	}

	// 提交商品详情信息
	function submitSpuDescForm() {
        //有效性验证
        if(!$('#spuBasicAddForm').form('validate')){
            $.messager.alert('提示','表单还未填写完成!');
            return ;
        }
        //同步文本框中的商品描述
        spuDescAddEditor.sync();

        $.post("/spu/saveDesc",$("#spuDescAddForm").serialize(), function(data){
            if(data.code == 1){
                clearForm();
                $.messager.alert('提示','新增商品详情成功!');
            }
        });
    }
	
	function clearSpuBasicForm(){
		$('#spuBasicAddForm').form('reset');
	}

	function clearSpuDescForm() {
        spuDescAddEditor.html('');
    }
</script>
