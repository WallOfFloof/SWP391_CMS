<%-- 
    Document   : index
    Created on : May 27, 2025, 8:54:17 AM
    Author     : admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en" class="theme-fs-sm" data-bs-theme="light" data-bs-theme-color="default" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title>${appName} Clinic And Patient Management Dashboard</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="shortcut icon" href="assets/images/favicon.ico" />
        <!-- Core CSS -->
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <link rel="stylesheet" href="<c:url value='/assets/css/core/libs.min.css' />" />       
        <link rel="stylesheet" href="<c:url value='/assets/css/kivicare.min.css?v=1.4.1' />" />
        <link rel="stylesheet" href="<c:url value='/assets/css/custom.min.css?v=1.4.1' />" />
        <link rel="stylesheet" href="<c:url value='/assets/css/rtl.min.css?v=1.4.1' />" />
        <link rel="stylesheet" href="<c:url value='/assets/css/customizer.min.css?v=1.4.1' />" />
    </head>
    <body>

        <!-- Loader -->
        <div id="loading">
            <div class="loader simple-loader">
                <div class="loader-body">
                    <img src="assets/images/loader.gif" alt="loader" class="light-loader img-fluid" width="200">
                </div>
            </div>
        </div>

        <!-- Sidebar -->
        <aside class="sidebar sidebar-base" id="first-tour" data-toggle="main-sidebar">
            <div class="sidebar-header">
                <a href="index.jsp" class="navbar-brand">
                    <div class="logo-main">
                        <img class="logo-normal img-fluid" src="assets/images/logo.png" height="30" alt="logo">
                    </div>
                </a>
            </div>
            <div class="sidebar-body">
                <ul class="navbar-nav iq-main-menu" id="sidebar-menu">
                    <li class="nav-item">
                        <a class="nav-link" href="dashboard.jsp">
                            <i class="icon">🏠</i>
                            <span class="item-name">${dashboardTitle}</span>
                        </a>
                    </li>
                    <!-- Add more menu items here -->
                </ul>
            </div>
        </aside>

        <!-- Main Content Area -->
        <main class="main-content">
            <div class="container-fluid">
                <h1>Welcome to ${appName}!</h1>
                <p>Hello, ${username}! Here’s your dashboard overview.</p>
            </div>
        </main>

        <!-- Scripts -->
        <script src="assets/js/core/libs.min.js"></script>
        <script src="assets/js/kivicare.min.js?v=1.4.1"></script>
        <script src="assets/js/custom.min.js?v=1.4.1"></script>

    </body>
</html>
