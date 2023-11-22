<%--
  Created by IntelliJ IDEA.
  User: YQF
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>人员管理</title>
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
    <style>

    </style>
</head>
<body class="childrenBody">
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">用户姓名:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="realname" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入用户名称" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <button type="button"
                    class="layui-btn layui-btn-normal layui-icon layui-icon-search layui-btn-radius layui-btn-sm"
                    id="doSearch" style="margin-top: 4px">查询
            </button>
            <button type="reset"
                    class="layui-btn layui-btn-warm layui-icon layui-icon-refresh layui-btn-radius layui-btn-sm" style="margin-top: 4px">重置
            </button>
        </div>
    </div>
</form>

<!-- 数据表格开始 -->
<table class="layui-hide" id="userTable" lay-filter="userTable"></table>
<div id="userToolBar" style="display: none;">
    <button type="button" class="layui-btn layui-btn-sm layui-btn-radius" lay-event="add">增加</button>
</div>
<div id="userBar" style="display: none;">
    <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius" lay-event="del">删除</a>
</div>

<!-- 添加和修改的弹出层-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form" lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户姓名:</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id">
                    <input type="text" name="realname" lay-verify="required" placeholder="请输入用户姓名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">登陆名称:</label>
                <div class="layui-input-inline">
                    <input type="text" name="loginname" lay-verify="required" placeholder="请输入登陆名称" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">邮箱:</label>
                <div class="layui-input-inline">
                    <input type="text" name="email" placeholder="请输入用户邮箱" lay-verify="required|email" autocomplete="off" class="layui-input">
                </div>
            </div>
                <div class="layui-inline">
                    <label class="layui-form-label">用户性别:</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="sex" value="1" checked="checked" title="男">
                        <input type="radio" name="sex" value="0" title="女">
                    </div>
                </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户电话:</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" lay-verify="required|phone" placeholder="请输入用户电话" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">用户地址:</label>
                <div class="layui-input-inline">
                    <input type="text" name="address" placeholder="请输入用户地址" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">所属单位:</label>
                <div class="layui-input-inline">
                    <select name="deptId" id="search_education" lay-verify="required">
                        <option value="">请选择单位</option>
                    </select>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: center;padding-right: 120px">
                <button type="button"
                        class="layui-btn layui-btn-normal layui-btn-md layui-icon layui-icon-release layui-btn-radius"
                        lay-filter="doSubmit" lay-submit="">提交
                </button>
                <button type="reset"
                        class="layui-btn layui-btn-warm layui-btn-md layui-icon layui-icon-refresh layui-btn-radius">重置
                </button>
            </div>
        </div>
    </form>
</div>

<%--用户分配角色的弹出层开始--%>
<div style="display: none;padding: 10px" id="selectUserRole">
    <table class="layui-hide" id="roleTable" lay-filter="roleTable"></table>
</div>


<script src="${alfred}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.extend({
        dtree: '${alfred}/resources/layui_ext/dist/dtree'
    }).use(['jquery', 'layer', 'form', 'table', 'dtree'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var dtree = layui.dtree;
        //渲染数据表格
        tableIns = table.render({
            elem: '#userTable'   //渲染的目标对象
            , url: '${alfred}/user/findPage.action' //数据接口
            , title: '用户数据表'//数据导出来的标题
            , toolbar: "#userToolBar"   //表格的工具条
            , height: 'full-210'
            , cellMinWidth: 100 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', align: 'center',width:'55'}
                , {field: 'realname', title: '用户姓名', align: 'center',width:'110'}
                , {field: 'loginname', title: '登陆名称', align: 'center',width:'110'}
                , {field: 'phone', title: '手机号码', align: 'center',width:'140'}
                , {field: 'address', title: '用户地址', align: 'center',width:'130'}
                , {field: 'email', title: '邮箱', align: 'center',width:'130'}
                , {field: 'deptName', title: '所属单位', align: 'center',width:'130'}
                , {field: 'type', title: '用户身份', align: 'center',width:'130',templet: function (d) {
                        if (d.type == 1) {
                            return '管理员';
                        } else if (d.type == 2) {
                            return '员工';
                        } else if (d.type == 3) {
                            return '读者';
                        }
                    }}
                , {fixed: 'right', title: '操作', toolbar: '#userBar', align: 'center',width:'300'}
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
        })

        //模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchFrm").serialize();
            //alert(params);
            tableIns.reload({
                url: "${alfred}/user/findPage.action?" + params,
                page:{curr:1}
            })
        });

        //加载查询条件分类的下拉列表
        $.get("/dept/loadAllForSelect.action",function(res){
            var data=res.data;
            var dom=$("#search_education");
            var html='<option value="0">请选择单位</option>'
            $.each(data,function(index,item){
                html+='<option value="'+item.id+'">'+item.name+'</option>'
            });
            dom.html(html);
            form.render("select");
        })

        //监听头部工具栏事件
        table.on("toolbar(userTable)", function (obj) {
            switch (obj.event) {
                case 'add':
                    openAddUser();
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(userTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'del') { //删除
                layer.confirm('真的删除【' + data.realname + '】这个用户么？', function (index) {
                    //向服务端发送删除指令
                    $.post("${alfred}/user/delete.action", {id: data.id}, function (res) {
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if (layEvent === 'edit') { //编辑
                //编辑，打开修改界面
                openUpdateUser(data);
            }else if(layEvent === 'selectUserRole'){//分配权限
                openselectUserRole(data);
            }else if(layEvent === 'resetUserPwd'){//重置密码
                layer.confirm('真的重置【' + data.realname + '】这个用户的密码吗？', function (index) {
                    //向服务端发送重置密码
                    $.post("${alfred}/user/resetUserPwd.action", {id: data.id}, function (res) {
                        layer.msg(res.msg);
                    })
                });
            }
        });

        var url;
        var mainIndex;

        //打开添加页面
        function openAddUser() {
            mainIndex = layer.open({
                type: 1,
                title: '添加',
                content: $("#saveOrUpdateDiv"),
                area: ['700px', '580px'],
                success: function (index) {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    url = "${alfred}/user/save.action";
                }
            });
        }

        //打开修改页面
        function openUpdateUser(data) {
            mainIndex = layer.open({
                type: 1,
                title: '编辑',
                content: $("#saveOrUpdateDiv"),
                area: ['700px', '580px'],
                success: function (index) {
                    form.val("dataFrm", data);
                    url = "${alfred}/user/save.action";
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
                layer.close(mainIndex)
                //刷新数据 表格
                tableIns.reload();
            })
        });

        //打开分配角色的弹出层
        function openselectUserRole(data) {
            mainIndex=layer.open({
                type:1,
                title:'给【'+data.realname+'】分配角色',
                content:$("#selectUserRole"),
                area:['700px','390px'],
                btnAlign:'c',
                btn:['<div class="layui-icon layui-icon-release">确认分配</div>','<div class="layui-icon layui-icon-close">取消分配</div>'],
                yes:function (index, layero) {
                    //得到选中的数据行
                    var checkStatus = table.checkStatus('roleTable');
                    var roleData = checkStatus.data;
                    var params="id="+data.id;
                    $.each(roleData,function(i,item){
                        params+="&ids="+item.roleid;
                    });
                    //保存
                    $.post("${alfred}/user/saveUserRole.action",params,function (obj) {
                        layer.msg(obj.msg);
                        //关闭弹出层
                        layer.close(mainIndex);
                    })
                },
                success:function (index) {
                    //渲染数据表格
                    var roleTableIns = table.render({
                        elem: '#roleTable'   //渲染的目标对象
                        , url: '${alfred}/user/initUserRole.action?userid='+data.id //数据接口
                        , title: '角色列表'//数据导出来的标题
                        , cellMinWidth: 100 //设置列的最小默认宽度
                        , cols: [[   //列表数据
                              {type: 'radio'}
                            , {field: 'roleid', title: 'ID', align: 'center'}
                            , {field: 'rolename', title: '角色名称', align: 'center'}
                            , {field: 'roledesc', title: '角色备注', align: 'center'}
                        ]]
                    });
                }
            });
        }
    });

</script>
</body>
</html>
