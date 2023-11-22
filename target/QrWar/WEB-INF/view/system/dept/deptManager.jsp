<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <title>单位管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <%--<link rel="icon" href="favicon.ico">--%>
    <link rel="stylesheet" href="${alfred}/resources/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${alfred}/resources/css/public.css" media="all"/>
    <link rel="stylesheet" href="${alfred}/resources/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="${alfred}/resources/layui_ext/dtree/font/dtreefont.css">
</head>
<body class="childrenBody">

<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">单位名称:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="name" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入单位名称" style="height: 30px;border-radius: 10px">
            </div>
        </div>

        <div class="layui-inline" style="margin-left: 50px">
            <button type="button"
                    class="layui-btn layui-btn-normal layui-icon layui-icon-search layui-btn-radius layui-btn-sm"
                    id="doSearch">查询
            </button>
            <button type="reset"
                    class="layui-btn layui-btn-warm layui-icon layui-icon-refresh layui-btn-radius layui-btn-sm">重置
            </button>
        </div>
    </div>
</form>

<!-- 数据表格开始 -->
<table class="layui-hide" id="newsTable" lay-filter="newsTable"></table>
<div style="display: none;" id="newsToolBar">
    <button type="button" class="layui-btn layui-btn-sm layui-btn-radius" lay-event="add">增加</button>
</div>
<div id="newsBar" style="display: none;">
    <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs layui-btn-radius" lay-event="viewNews">详情</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius" lay-event="del">删除</a>
</div>

<!-- 添加和修改的弹出层-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form" lay-filter="dataFrm" id="dataFrm" style="margin-right: 20px">
        <div class="layui-form-item">
            <label class="layui-form-label">单位名称:</label>
            <div class="layui-input-block">
                <input type="text" name="id" style="display: none;">
                <input type="text" name="name" placeholder="请输入单位名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系人:</label>
            <div class="layui-input-block">
                <input type="text" name="contacts" placeholder="请输入联系人" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">联系电话:</label>
            <div class="layui-input-block">
                <input type="text" name="phone" placeholder="请输入联系电话" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">邮箱地址:</label>
            <div class="layui-input-block">
                <input type="text" name="email" placeholder="请输入邮箱地址" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">联系地址:</label>
            <div class="layui-input-block">
                <input type="text" name="address" placeholder="请输入联系地址" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">单位性质:</label>
            <div class="layui-input-block">
                <input type="text" name="unitNature" placeholder="请输入单位性质" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: center;padding-right: 120px">
                <button type="button"
                        class="layui-btn layui-btn-normal layui-btn-md layui-icon layui-icon-release layui-btn-radius"
                        lay-filter="doSubmit" lay-submit="">提交
                </button>
                <button type="reset" id="dataFrmResetBtn"
                        class="layui-btn layui-btn-warm layui-btn-md layui-icon layui-icon-refresh layui-btn-radius">重置
                </button>
            </div>
        </div>
    </form>
</div>

<%--查看新闻的div--%>
<div id="viewNewsDiv" style="padding: 10px;display: none">
    <form class="layui-form" lay-filter="dataFrm1" id="dataFrm1" style="margin-right: 20px">
        <div class="layui-form-item">
            <label class="layui-form-label">单位名称:</label>
            <div class="layui-input-block">
                <input type="text" name="id" style="display: none;">
                <input type="text" name="name" placeholder="请输入单位名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系人:</label>
            <div class="layui-input-block">
                <input type="text" name="contacts" placeholder="请输入联系人" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">联系电话:</label>
            <div class="layui-input-block">
                <input type="text" name="phone" placeholder="请输入联系电话" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">邮箱地址:</label>
            <div class="layui-input-block">
                <input type="text" name="email" placeholder="请输入邮箱地址" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">联系地址:</label>
            <div class="layui-input-block">
                <input type="text" name="address" placeholder="请输入联系地址" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">单位性质:</label>
            <div class="layui-input-block">
                <input type="text" name="unitNature" placeholder="请输入单位性质" autocomplete="off" class="layui-input">
            </div>
        </div>
    </form>
</div>

<script src="${alfred}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use(['jquery', 'layer', 'form', 'table', 'laydate'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var laydate = layui.laydate;

        //渲染时间
        laydate.render({
            elem: '#startTime',
            type: 'datetime'
        });
        laydate.render({
            elem: '#endTime',
            type: 'datetime'
        });

        //渲染数据表格
        tableIns = table.render({
            elem: '#newsTable'   //渲染的目标对象
            , url: '${alfred}/dept/findPage.action' //数据接口
            , title: '单位信息'//数据导出来的标题
            , toolbar: "#newsToolBar"   //表格的工具条
            , height: 'full-190'
            , cellMinWidth: 100 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                , {field: 'id', title: 'ID', align: 'center'}
                , {field: 'name', title: '单位名称', align: 'center'}
                , {field: 'contacts', title: '联系人', align: 'center'}
                , {field: 'phone', title: '联系电话', align: 'center'}
                , {field: 'email', title: '邮箱地址', align: 'center'}
                , {field: 'address', title: '联系地址', align: 'center'}
                , {field: 'unitNature', title: '单位性质', align: 'center'}
                , {fixed: 'right', title: '操作', toolbar: '#newsBar', align: 'center'}
            ]],
            done:function (data, curr, count) {
                //不是第一页时，如果当前返回的数据为0那么就返回上一页
                if(data.data.length==0&&curr!=1){
                    tableIns.reload({
                        page:{
                            curr:curr-1
                        }
                    })
                }
            }
        });

        //模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchFrm").serialize();
            //alert(params);
            tableIns.reload({
                url: "${alfred}/dept/findPage.action?" + params,
                page: {curr: 1}
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(newsTable)", function (obj) {
            switch (obj.event) {
                case 'add':
                    openAddNews();
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(newsTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'del') { //删除
                layer.confirm('真的删除【' + data.name + '】这个单位吗？', function (index) {
                    //向服务端发送删除指令
                    $.post("${alfred}/dept/delete.action", {id: data.id}, function (res) {
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if (layEvent === 'edit') { //编辑
                //编辑，打开修改界面
                openUpdateNews(data);
            } else if (layEvent === 'viewNews') {//查看
                viewNews(data);
            }
        });

        var url;
        var mainIndex;

        //打开添加页面
        function openAddNews() {
            mainIndex = layer.open({
                type: 1,
                title: '添加图书信息',
                content: $("#saveOrUpdateDiv"),
                area: ['700px', '540px'],
                success: function (index) {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    url = "${alfred}/dept/save.action";
                }
            });
        }

        //打开修改页面
        function openUpdateNews(data) {
            mainIndex = layer.open({
                type: 1,
                title: '修改图书信息',
                content: $("#saveOrUpdateDiv"),
                area: ['700px', '540px'],
                success: function (index) {
                    form.val("dataFrm", data);
                    url = "${alfred}/dept/save.action";
                }
            });
        }

        //保存
        form.on("submit(doSubmit)", function (obj) {
            //序列化表单数据
            var params = $("#dataFrm").serialize();
            $.post(url, params, function (obj) {
                layer.msg(obj.msg);
                //关闭弹出层
                layer.close(mainIndex);
                //刷新数据 表格
                tableIns.reload();
            })
        });

        //查看
        function viewNews(data) {
            mainIndex = layer.open({
                type: 1,
                title: '查看单位信息',
                content: $("#viewNewsDiv"),
                area: ['700px', '540px'],
                success: function (index) {
                    form.val("dataFrm1", data);
                }
            });
        }
    });

</script>
</body>
</html>
