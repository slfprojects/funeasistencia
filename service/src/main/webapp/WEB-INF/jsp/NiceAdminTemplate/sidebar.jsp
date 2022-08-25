<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>
    <!-- Layout wrapper -->
    <div class="layout-wrapper layout-content-navbar">
      <div class="layout-container">
        <!-- Menu -->

        <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme" >
          <div class="app-brand demo">
            <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto">
              <i class="bx bx-chevron-left bx-sm align-middle"></i>
            </a>
            <a href="index.html" class="app-brand-link">
              
              <img src="/assets/img/logo.png" alt="" heigth="40" width="30" style="border-radius: 40px;">
              <img src="/assets/img/titulo.png" alt="" heigth="30" width="160" style="border-radius: 40px;">
             
            </a>

            <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
              <i class="bx bx-chevron-left bx-sm align-middle"></i>
            </a>
          </div>

          <div class="menu-inner-shadow"></div>
          
          
          <ul class="menu-inner py-1">
            <%= session.getAttribute("MenusPorUsuario") %>
          
          </ul>
          
          
        </aside>
        <!-- / Menu -->

        <input type="hidden" name="txoficina" value="<%= session.getAttribute("txoficina") %>">
        <input type="hidden" name="cdusuario" value="<%= session.getAttribute("cdusuario") %>">
        <div id="divToast"></div>
        