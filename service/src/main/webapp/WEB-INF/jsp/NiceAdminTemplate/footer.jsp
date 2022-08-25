<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        </div>
        <!-- / Layout page -->
      </div>

      <!-- Overlay -->
      <div class="layout-overlay layout-menu-toggle"></div>
    </div>
    <!-- / Layout wrapper -->


    <!-- Core JS -->
    <!-- build:js assets/vendor/js/core.js -->
    <script src="/assets/vendor/libs/jquery/jquery.js"></script>
    <script src="/assets/vendor/libs/popper/popper.js"></script>
    <script src="/assets/vendor/js/bootstrap.js"></script>
    <script src="/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

    <script src="/assets/vendor/js/menu.js"></script>
    <!-- endbuild -->

    <!-- Vendors JS -->
    <script src="/assets/vendor/libs/apex-charts/apexcharts.js"></script>

    <!-- Main JS -->
    <script src="/assets/js/main.js"></script>

    <!-- Page JS -->
    <script src="/assets/datatables.min.js"></script>
    <script src="/assets/sweetAlerts2/sweetalert2.min.js"></script>
    
    <c:set var="vScripts" value="${requestScope.scripts}"></c:set>
    <c:set var="vCodigoMenu" value="${requestScope.codigoMenu}"></c:set>
    <c:set var="vCodigoSubMenu" value="${requestScope.codigoSubMenu}"></c:set>
    
    <script>
      function fnMarcarSubMenu(pSubMenu){
          $('li[id="'+pSubMenu+'"]').addClass('active');
          $('li[id="'+pSubMenu+'"]').addClass('open');
      }
      function fnMarcarMenu(pMenu){
          $('li[id="'+pMenu+'"]').addClass('active');
      }
      
    </script>
    <script src="/core/activarDtsOficina.js"></script>
    
    <script src="/chartjs/Chart.min.js"></script>
    <script src="/chartjs/utils.js"></script>
    <script src="/chartjs/chartjs-plugin.js"></script>
    
    <script>
      fnMarcarMenu(${vCodigoSubMenu});
      fnMarcarSubMenu(${vCodigoMenu});
    </script>
    
    <c:forEach var="script" items="${vScripts}">
    	<script src="/core/${script}"></script>
    </c:forEach>
    
    
    
    
  </body>
</html>