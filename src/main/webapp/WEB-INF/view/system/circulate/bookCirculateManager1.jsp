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
    <meta name="format-detection" content="telephone=no">
    <%--<link rel="icon" href="favicon.ico">--%>
    <link rel="stylesheet" href="${alfred}/resources/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${alfred}/resources/css/public.css" media="all"/>
    <link rel="stylesheet" href="${alfred}/resources/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="${alfred}/resources/layui_ext/dtree/font/dtreefont.css">
</head>
<body class="childrenBody">


<!-- 数据表格开始 -->
<table class="layui-hide" id="newsTable" lay-filter="newsTable"></table>
<div style="display: none;" id="newsToolBar">
</div>
<div id="newsBar" style="display: none;">
    {{# if(d.circulateStatus==0){  }}
    <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="agree1">同意</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius" lay-event="refuse1">拒绝</a>
    {{#  }}}
    {{# if(d.circulateStatus==3){  }}
    <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="agree3">已返还</a>
    {{#  }}}
</div>

<script src="${alfred}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use(['jquery', 'layer', 'form', 'table', 'laydate', 'upload'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
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
            , url: '${alfred}/bookCirculate/findPage1.action' //数据接口
            , title: '图书信息'//数据导出来的标题
            , toolbar: "#newsToolBar"   //表格的工具条
            , height: 'full-190'
            , cellMinWidth: 100 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {field: 'id', title: 'ID', align: 'center'}
                , {field: 'bookName', title: '图书名称', align: 'center'}
                , {field: 'deptName', title: '图书所属单位', align: 'center'}
                , {field: 'circulateDeptName', title: '流通单位', align: 'center'}
                , {field: 'circulateTime', title: '流通时间', align: 'center'}
                , {field: 'returnTime', title: '归还时间', align: 'center'}
                , {field: 'reason', title: '流通理由', align: 'center'}
                , {field: 'userName', title: '申请人', align: 'center'}
                , {field: 'phone', title: '申请人联系方式', align: 'center'}
                , {field: 'remark', title: '备注', align: 'center'}
                , {field: 'circulateStatus', title: '状态', align: 'center',templet: function (d) {
                        if (d.circulateStatus == '0') {
                            return '待审核';
                        } else if (d.circulateStatus == '1') {
                            return '已审核';
                        } else if (d.circulateStatus == '2') {
                            return '已结束';
                        } else if (d.circulateStatus == '3') {
                            return '归还待审核';
                        } else if (d.circulateStatus == '4') {
                            return '已结束';
                        }
                    }}
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
                url: "${alfred}/bookCirculate/findPage.action?" + params,
                page: {curr: 1}
            })
        });

        //监听行工具事件
        table.on('tool(newsTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'agree1') { //流通审核
                updateStatus(data.id, 1);
            } else if (layEvent === 'refuse1') { //流通审核
                updateStatus(data.id, 2);
            } else if (layEvent === 'agree3') { //归还审核
                updateStatus(data.id, 2);
            }
        });

        function updateStatus(id, status) {
            layer.confirm('是否确认该操作？', function (index) {
                //向服务端发送删除指令
                $.post("${alfred}/bookCirculate/update.action", {id: id, circulateStatus: status}, function (res) {
                    layer.msg(res.msg);
                    //刷新数据表格
                    tableIns.reload();
                })
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
