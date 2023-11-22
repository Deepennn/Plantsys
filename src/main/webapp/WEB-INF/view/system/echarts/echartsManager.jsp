<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <title>统计分析</title>
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

<div class="layui-row layui-col-space15">
    <div class="layui-col-xs12 layui-col-md3" style="background-color: #c0c4cc">
        <div class="layui-card top-panel">
            <div class="layui-card-header">本单位总图书数</div>
            <div class="layui-card-body">
                <div class="layui-row layui-col-space5">
                    <div class="layui-col-xs9 layui-col-md9 top-panel-number">
                        ${totalNum}
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-col-xs12 layui-col-md3" style="background-color: #c0c4cc">
        <div class="layui-card top-panel">
            <div class="layui-card-header">当前在库总图书数</div>
            <div class="layui-card-body">
                <div class="layui-row layui-col-space5">
                    <div class="layui-col-xs9 layui-col-md9 top-panel-number">
                        ${num}
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-col-xs12 layui-col-md3" style="background-color: #c0c4cc">
        <div class="layui-card top-panel">
            <div class="layui-card-header">本单位借入图书数量</div>
            <div class="layui-card-body">
                <div class="layui-row layui-col-space5">
                    <div class="layui-col-xs9 layui-col-md9 top-panel-number">
                        ${addNum}
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-col-xs12 layui-col-md3" style="background-color: #c0c4cc">
        <div class="layui-card top-panel">
            <div class="layui-card-header">本单位借出图书数量</div>
            <div class="layui-card-body">
                <div class="layui-row layui-col-space5">
                    <div class="layui-col-xs9 layui-col-md9 top-panel-number">
                        ${subNum}
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="layui-row layui-col-space15">
    <div class="layui-col-xs12 layui-col-md9">
        <div id="echarts-records" style="background-color:#ffffff;min-height:400px;padding: 10px"></div>
    </div>
    <div class="layui-col-xs12 layui-col-md9">
        <div id="echarts-month" style="background-color:#ffffff;min-height:400px;padding: 10px"></div>
    </div>
</div>


<script src="${alfred}/resources/layui/layui.js"></script>
<script src="${alfred}/resources/echarts/js/echarts.min.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use(['jquery', 'layer', 'form', 'table'], function () {
        var $ = layui.jquery;

        var echartsRecords = echarts.init(document.getElementById('echarts-records'), 'walden');
        var echartsMonth = echarts.init(document.getElementById('echarts-month'), 'walden');

        $.get("${alfred}/bookBorrow/echarts.action?type=1",function(data){
            var timeData = [];
            var numData = [];
            console.log(data.data)
            timeData = data.data.timeData
            numData = data.data.numData

            /**
             * 柱状图
             */
            var optionRecords = {
                title: {
                    text: '按年统计借阅信息'
                },
                color: ['#3398DB'],
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                xAxis: {
                    type: 'category',
                    data: timeData
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '按年统计',
                        barWidth: 40, // 柱子宽度
                        data: numData,
                        type: 'bar',
                        showBackground: true,
                        backgroundStyle: {
                            color: 'rgba(180, 180, 180, 0.2)'
                        }
                    }
                ]
            };
            echartsRecords.setOption(optionRecords);
        })

        $.get("${alfred}/bookBorrow/echarts.action?type=2",function(data){
            var timeData = [];
            var numData = [];
            console.log(data.data)
            timeData = data.data.timeData
            numData = data.data.numData

            /**
             * 柱状图
             */
            var optionMonth = {
                title: {
                    text: '按月统计借阅信息'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                xAxis: {
                    type: 'category',
                    data: timeData
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '按年统计',
                        barWidth: 40, // 柱子宽度
                        data: numData,
                        type: 'line',
                        showBackground: true,
                        backgroundStyle: {
                            color: 'rgba(180, 180, 180, 0.2)'
                        }
                    }
                ]
            };
            echartsMonth.setOption(optionMonth);
        })

    });

</script>
</body>
</html>
