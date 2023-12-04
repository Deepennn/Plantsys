<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<div class="sidebar-wrapper" data-simplebar="init" >
    <div class="sidebar-header">
        <div>
<%--            <img src="assets/images/logo-icon.png" class="logo-icon" alt="logo icon">--%>
        </div>
        <div>
            <h4 class="logo-text">园林植物管理平台</h4>
        </div>
        <div class="toggle-icon ms-auto"><i class='bx bx-arrow-to-left'></i>
        </div>
    </div>
    <!--navigation-->
    <ul class="metismenu" id="menu" >

        <!-- 系统管理员 -->
        <c:if test="${admin!=null}">

            <li>
                <a href="javascript:;" class="has-arrow">
                    <div class="parent-icon"><i class='bx bx-home-circle'></i>
                    </div>
                    <div class="menu-title">用户管理</div>
                </a>
                <ul>
                    <li> <a href="userList.do"><i class="bx bx-right-arrow-alt"></i>用户列表</a>
                    </li>

                    <li> <a href="toAddUser.do"><i class="bx bx-right-arrow-alt"></i>添加用户</a>
                    </li>

                </ul>
            </li>

            <li>
                <a href="javascript:;" class="has-arrow">
                    <div class="parent-icon"><i class='bx bx-home-circle'></i>
                    </div>
                    <div class="menu-title">植物信息管理</div>
                </a>
                <ul>
                    <li> <a href="plantList.do"><i class="bx bx-right-arrow-alt"></i>植物信息列表</a>
                    </li>
                    <li> <a href="toAddPlant.do"><i class="bx bx-right-arrow-alt"></i>添加植物信息</a>
                    </li>
                </ul>
            </li>

            <li>
                <a href="javascript:;" class="has-arrow">
                    <div class="parent-icon"><i class='bx bx-home-circle'></i>
                    </div>
                    <div class="menu-title">植物分类管理</div>
                </a>
                <ul>
                    <li> <a href="plantList.do"><i class="bx bx-right-arrow-alt"></i>植物分类列表</a>
                    </li>
                    <li> <a href="toAddPlant.do"><i class="bx bx-right-arrow-alt"></i>添加植物分类</a>
                    </li>
                </ul>
            </li>

            <li>
                <a href="javascript:;" class="has-arrow">
                    <div class="parent-icon"><i class='bx bx-home-circle'></i>
                    </div>
                    <div class="menu-title">病虫害防治管理</div>
                </a>
                <ul>
                    <li> <a href="plantList.do"><i class="bx bx-right-arrow-alt"></i>病虫害列表</a>
                    </li>
                    <li> <a href="toAddPlant.do"><i class="bx bx-right-arrow-alt"></i>添加病虫害</a>
                    </li>
                </ul>
            </li>

        </c:if>

        <!-- 主管人员 -->
        <c:if test="${manager!=null}">

            <li>
                <a href="javascript:;" class="has-arrow">
                    <div class="parent-icon"><i class='bx bx-home-circle'></i>
                    </div>
                    <div class="menu-title">植物信息管理</div>
                </a>
                <ul>
                    <li> <a href="plantList.do"><i class="bx bx-right-arrow-alt"></i>植物信息列表</a>
                    </li>
                </ul>
            </li>

            <li>
                <a href="javascript:;" class="has-arrow">
                    <div class="parent-icon"><i class='bx bx-home-circle'></i>
                    </div>
                    <div class="menu-title">植物分类管理</div>
                </a>
                <ul>
                    <li> <a href="plantList.do"><i class="bx bx-right-arrow-alt"></i>植物分类列表</a>
                    </li>
                </ul>
            </li>

            <li>
                <a href="javascript:;" class="has-arrow">
                    <div class="parent-icon"><i class='bx bx-home-circle'></i>
                    </div>
                    <div class="menu-title">病虫害防治管理</div>
                </a>
                <ul>
                    <li> <a href="plantList.do"><i class="bx bx-right-arrow-alt"></i>病虫害列表</a>
                    </li>
                </ul>
            </li>

            <li>
                <a href="javascript:;" class="has-arrow">
                    <div class="parent-icon"><i class='bx bx-home-circle'></i>
                    </div>
                    <div class="menu-title">植物养护管理</div>
                </a>
                <ul>
                    <li> <a href="plantList.do"><i class="bx bx-right-arrow-alt"></i>植物养护任务</a>
                    </li>
                    <li> <a href="toAddPlant.do"><i class="bx bx-right-arrow-alt"></i>添加养护任务</a>
                    </li>
                </ul>
            </li>

            <li>
                <a href="javascript:;" class="has-arrow">
                    <div class="parent-icon"><i class='bx bx-home-circle'></i>
                    </div>
                    <div class="menu-title">植物监测管理</div>
                </a>
                <ul>
                    <li> <a href="plantList.do"><i class="bx bx-right-arrow-alt"></i>植物监测记录</a>
                    </li>
                </ul>
            </li>

        </c:if>

        <!-- 养护人员 -->
        <c:if test="${maintainer!=null}">

            <li>
                <a href="javascript:;" class="has-arrow">
                    <div class="parent-icon"><i class='bx bx-home-circle'></i>
                    </div>
                    <div class="menu-title">植物养护管理</div>
                </a>
                <ul>
                    <li> <a href="plantList.do"><i class="bx bx-right-arrow-alt"></i>植物养护任务</a>
                    </li>
                    <li> <a href="toAddPlant.do"><i class="bx bx-right-arrow-alt"></i>更新养护任务</a>
                    </li>
                </ul>
            </li>

        </c:if>

        <!-- 监测人员 -->
        <c:if test="${monitor!=null}">

            <li>
                <a href="javascript:;" class="has-arrow">
                    <div class="parent-icon"><i class='bx bx-home-circle'></i>
                    </div>
                    <div class="menu-title">植物监测管理</div>
                </a>
                <ul>
                    <li> <a href="plantList.do"><i class="bx bx-right-arrow-alt"></i>植物监测记录</a>
                    </li>
                    <li> <a href="toAddPlant.do"><i class="bx bx-right-arrow-alt"></i>添加监测记录</a>
                    </li>
                </ul>
            </li>

        </c:if>

    </ul>
    <!--end navigation-->
</div>
<footer class="page-footer">
<%--    <p class="mb-0">Copyright©</p>--%>
</footer>
