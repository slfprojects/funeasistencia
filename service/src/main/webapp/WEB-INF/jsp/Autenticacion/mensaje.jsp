<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- =========================================================
* Sneat - Bootstrap 5 HTML Admin Template - Pro | v1.0.0
==============================================================

* Product Page: https://themeselection.com/products/sneat-bootstrap-html-admin-template/
* Created by: ThemeSelection
* License: You must have a valid license purchased in order to legally use the theme for your project.
* Copyright ThemeSelection (https://themeselection.com)

=========================================================
 -->
<!-- beautify ignore:start -->
<html
  lang="en"
  class="light-style customizer-hide"
  dir="ltr"
  data-theme="theme-default"
  data-assets-path="./assets/"
  data-template="vertical-menu-template-free"
>
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />

    <title>Funeasistencia</title>

    <meta name="description" content="" />

    <!-- Favicon -->
    <link rel="icon" href="/assets/vendor/css/fonts/favicon.ico" sizes="32x32">

    <!-- Fonts -->
    <!-- <link rel="preconnect" href="htps://fonts.googleapis.com" />
    <link rel="preconnect" href="htps://fonts.gstatic.com" crossorigin />
    <link
      href="htps://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
      rel="stylesheet"
    /> -->

    <!-- Icons. Uncomment required icon fonts -->
    <link rel="stylesheet" href="/assets/vendor/fonts/boxicons.css" />

    <!-- Core CSS -->
    <link rel="stylesheet" href="/assets/vendor/css/core.css" class="template-customizer-core-css" />
    <link rel="stylesheet" href="/assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
    <link rel="stylesheet" href="/assets/css/demo.css" />

    <!-- Vendors CSS -->
    <link rel="stylesheet" href="/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />
    
    <!-- Page CSS -->
    <!-- Page -->
    <link rel="stylesheet" href="/assets/vendor/css/pages/page-auth.css" />
    <!-- Helpers -->
    <script src="/assets/vendor/js/helpers.js"></script>

    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="/assets/js/config.js"></script>
  </head>

  <body>
    <!-- Content -->
    <div id="toast" class="bs-toast toast toast-placement-ex m-2 fade bg-dark top-0 end-0 hide" role="alert" aria-live="assertive" aria-atomic="true" data-delay="2000">
      <div class="toast-header">
        <i class="bx bx-bell me-2"></i>
        <div class="me-auto fw-semibold">Mensaje de Autenticación</div>
        <small>hace 1 minuto</small>
        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
      </div>
      <div class="toast-body">Su sesión ha culminado por favor inicie nuevamente.</div>
    </div>

    
    <div class="container-xxl">
      <div class="authentication-wrapper authentication-basic container-p-y">
        <div class="authentication-inner">
          <!-- Register -->
          <div class="card">
            <div class="card-body">
              <!-- Logo -->
              <br>
              <div class="app-brand justify-content-center">
                <a href="idex.html" class="app-brand-link gap-2">
                      <img src="/assets/img/logo.png" alt="" heigth="30" width="30" style="border-radius: 10px;">
                      <img src="/assets/img/titulo.png" alt="" heigth="30" width="180" style="border-radius: 10px;">
                </a>
                
              </div>
            

              <!-- /Logo -->
             

              <form id="formAuthentication" class="mb-3" onsubmit="return false;">
                <div class="mb-3">
                  <div class="alert alert-dark alert-dismissible mb-0" role="alert">
                        Se ha enviado la clave al correo indicado por favor revisar, (De no existir el correo o usuario indicado no llegará la clave a su buzón).
                  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                  
                  </div>
                  <br>
                  <center>
	                  <a class="btn btn btn-dark" href="/front.funeasistencia/inicio">
	                 	 <span>Ir a Iniciar Sesión</span>
	                  </a>
                  </center>
                </div>
              </form>
            </div>
          </div>
          <!-- /Register -->
        </div>
      </div>
    </div>

    <!-- / Content -->


    <!-- Core JS -->
    <!-- build:js assets/vendor/js/core.js -->
    <script src="/assets/vendor/libs/jquery/jquery.js"></script>
    <script src="/assets/vendor/libs/popper/popper.js"></script>
    <script src="/assets/vendor/js/bootstrap.js"></script>
    <script src="/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

    <script src="/assets/vendor/js/menu.js"></script>
    <!-- endbuild -->

    <!-- Vendors JS -->

    <!-- Main JS -->
    <script src="/assets/js/main.js"></script>
  </body>
</html>
