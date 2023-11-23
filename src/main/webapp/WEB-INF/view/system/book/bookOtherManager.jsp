<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <title>图书管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <link rel="icon" href="${alfred}/resources/favicon6.ico">
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
            <label class="layui-form-label">编号:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="id" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入图书编号" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">名称:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="name" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入图书名称" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">作者:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="author" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入图书作者" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">出版社:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="press" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入出版社" style="height: 30px;border-radius: 10px">
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
<div id="newsBar" style="display: none;">
<c:if test="${type == 2}">
    <a class="layui-btn layui-btn-warm layui-btn-xs layui-btn-radius" lay-event="viewNews">详情</a>
    <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="borrow">流通</a>
</c:if>
</div>

<!-- 借阅图书的弹出层-->
<div style="display: none;padding: 20px" id="borrowDiv">
    <form class="layui-form" lay-filter="dataFrm1" id="dataFrm1" style="margin-right: 20px">
        <div class="layui-form-item">
            <label class="layui-form-label">名称:</label>
            <div class="layui-input-block">
                <input type="text" name="bookId" id="bookId" style="display: none;">
                <input type="text" name="name" placeholder="请输入图书名称" disabled autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">申请人:</label>
            <div class="layui-input-block">
                <input type="text" name="userName" id="userName" placeholder="请输入申请人" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">申请人联系方式:</label>
            <div class="layui-input-block">
                <input type="text" name="phone" id="phone" placeholder="请输入申请人联系方式" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">流通理由:</label>
            <div class="layui-input-block">
                <input type="text" name="reason" placeholder="请输入借阅理由" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">备注:</label>
            <div class="layui-input-block">
                <input type="text" name="remark" placeholder="请输入备注" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: center;padding-right: 120px">
                <button type="button"
                        class="layui-btn layui-btn-normal layui-btn-md layui-icon layui-icon-release layui-btn-radius"
                        lay-filter="doSubmit1" lay-submit="">提交
                </button>
                <button type="reset" id="dataFrmResetBtn1"
                        class="layui-btn layui-btn-warm layui-btn-md layui-icon layui-icon-refresh layui-btn-radius">重置
                </button>
            </div>
        </div>
    </form>
</div>

<%--查看新闻的div--%>
<div id="viewNewsDiv" style="padding: 10px;display: none">
    <form class="layui-form" lay-filter="dataFrm2" id="dataFrm2" style="margin-right: 20px">
        <div class="layui-form-item">
            <label class="layui-form-label">名称:</label>
            <div class="layui-input-block">
                <input type="text" name="id" style="display: none;">
                <input type="text" name="name" placeholder="请输入图书名称" disabled autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">分类:</label>
            <div class="layui-input-block">
                <input type="text" name="type" placeholder="请输入图书分类" disabled autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">作者:</label>
            <div class="layui-input-block">
                <input type="text" name="author" placeholder="请输入作者" disabled autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">出版时间:</label>
            <div class="layui-input-block">
                <input type="text" name="publishTime" placeholder="请输入出版时间" disabled autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">出版社:</label>
            <div class="layui-input-block">
                <input type="text" name="press" placeholder="请输入出版社" disabled autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">页数:</label>
            <div class="layui-input-block">
                <input type="text" name="pages" placeholder="请输入页数" disabled autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">价格:</label>
            <div class="layui-input-block">
                <input type="text" name="price" placeholder="请输入图书价格" disabled autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="margin-top: 20px">
            <label class="layui-form-label">图片</label>
            <img id="mobileCoverImg1" class="originalImg"  style="height: 50px;min-width: 50px;"/>
        </div>

    </form>
</div>

<script src="${alfred}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use(['jquery', 'layer', 'form', 'table', 'laydate', 'upload'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var laydate = layui.laydate;
        var upload = layui.upload;

        //渲染时间
        laydate.render({
            elem: '#publishTime',
            type: 'datetime'
        });
        laydate.render({
            elem: '#returnTime',
            type: 'datetime'
        });

        //渲染数据表格
        tableIns = table.render({
            elem: '#newsTable'   //渲染的目标对象
            , url: '${alfred}/book/findOtherPage.action' //数据接口
            , title: '图书信息'//数据导出来的标题
            , toolbar: "#newsToolBar"   //表格的工具条
            , height: 'full-190'
            , cellMinWidth: 100 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {field: 'id', title: '编号', align: 'center'}
                , {field: 'name', title: '图书名称', align: 'center'}
                , {field: 'deptName', title: '所属单位', align: 'center'}
                , {field: 'publishTime', title: '出版时间', align: 'center'}
                , {field: 'author', title: '作者', align: 'center'}
                , {field: 'press', title: '出版社', align: 'center'}
                , {field: 'type', title: '图书分类', align: 'center'}
                , {field: 'pages', title: '页数', align: 'center'}
                , {field: 'price', title: '价格', align: 'center'}
                // , {field: 'status', title: '借阅状态', align: 'center',templet: function (d) {
                //         return d.status == '1' ? '空闲' : '借阅中';
                //     }}
                // , {field: 'status', title: '开放状态', align: 'center',templet: function (d) {
                //         return d.isOpen == '1' ? '开放' : '隐藏';
                //     }}
                , {field: 'img', title: '图片', width:120, align:'center', templet:function (d) {
                        return '<img src="/file/downloadFile.action?path='+d.img+'"/>'}}
                , {fixed: 'right', title: '操作', toolbar: '#newsBar', align: 'center', width: 220}
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
                //动态监听表头高度变化，冻结行跟着改变高度
                $(".layui-table-header  tr").resize(function () {
                    $(".layui-table-header  tr").each(function (index, val) {
                        $($(".layui-table-fixed .layui-table-header table tr")[index]).height($(val).height());
                    });
                });
//初始化高度，使得冻结行表头高度一致
                $(".layui-table-header  tr").each(function (index, val) {
                    $($(".layui-table-fixed .layui-table-header table tr")[index]).height($(val).height());
                });
//动态监听表体高度变化，冻结行跟着改变高度
                $(".layui-table-body  tr").resize(function () {
                    $(".layui-table-body  tr").each(function (index, val) {
                        $($(".layui-table-fixed .layui-table-body table tr")[index]).height($(val).height());
                    });
                });
//初始化高度，使得冻结行表体高度一致
                $(".layui-table-body  tr").each(function (index, val) {
                    $($(".layui-table-fixed .layui-table-body table tr")[index]).height($(val).height());
                });
            }
        });

        //模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchFrm").serialize();
            //alert(params);
            tableIns.reload({
                url: "${alfred}/book/findOtherPage.action?" + params,
                page: {curr: 1}
            })
        });

        upload.render({
            elem: '#mobileTest1',
            url: '${alfred}/file/uploadFile.action',
            method: "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
            acceptMime: 'images/*',
            field: "mf",
            done: function (res, index, upload) {
                console.log(res.data)
                $('#mobileCoverImg').attr('src', "/file/downloadFile.action?path=" + res.data.src);
                $('#img').val(res.data.src);
            }
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
                layer.confirm('真的删除【' + data.name + '】这个图书么？', function (index) {
                    //向服务端发送删除指令
                    $.post("${alfred}/book/delete.action", {id: data.id}, function (res) {
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
            } else if (layEvent === 'borrow') {//查看
                openBorrow(data);
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
                    url = "${alfred}/book/save.action";
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
                    $('#mobileCoverImg').attr('src', "/file/downloadFile.action?path=" + data.img);
                    url = "${alfred}/book/save.action";
                }
            });
        }

        //打开修改页面
        function openBorrow(data) {
            mainIndex = layer.open({
                type: 1,
                title: '借阅图书',
                content: $("#borrowDiv"),
                area: ['700px', '540px'],
                success: function (index) {
                    form.val("dataFrm1", data);
                    $("#bookId").val(data.id);
                    url = "${alfred}/bookCirculate/save.action";
                }
            });
        }

        //保存
        form.on("submit(doSubmit1)", function (obj) {
            //序列化表单数据
            var params = $("#dataFrm1").serialize();
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
                title: '查看图书信息',
                content: $("#viewNewsDiv"),
                area: ['700px', '540px'],
                success: function (index) {
                    form.val("dataFrm2", data);
                    $('#mobileCoverImg1').attr('src', "/file/downloadFile.action?path=" + data.img);
                }
            });
        }
    });

</script>
<style type="text/css">
    .layui-table-cell{
        text-align:center;
        height: auto;
        white-space: normal;
    }
    .layui-table img{
        max-width:100%
    }
</style>
</body>
</html>
