<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/header.jsp" />
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/sidebar.jsp" />
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/navbar.jsp" />

<!-- Content wrapper -->
<div class="content-wrapper">	
	<!-- Content -->
	<div class="container-xxl flex-grow-1 container-p-y">
		<div class="col-md-12 mb-3">
			<c:import url="/WEB-INF/jsp//templates/aperturaTarjeta.jsp"></c:import>
				<c:import url="/WEB-INF/jsp//templates/FormulariosBasicos.jsp"></c:import>
				
				<center>
					<button class="btn btn-dark" onclick="fnGenerarPrimerCuadro()">Generar Estadísticas</button>
				</center>

				<p id="aFecha"></p>
				<div class="row">
					
					<div class="col-md-7">
						<div id="dtconsulta"></div>
						<div id="resultadoFinal"></div>
					</div>
					<div class="col-md-5" >
					<div class="chart-container" id="cjgrafico10">
					
					</div>
					</div>
					
				</div>
				<p id="aFechaB"></p>
				<div class="row">
					<div class="col-md-7">
						<div id="dtconsulta2"></div>
						<br>
						
					</div>
					<div class="col-md-5" >
					<div class="chart-container"">
						<div id="canva"></div>
						
					</div>
					</div>
					
				</div>
				
			<c:import url="/WEB-INF/jsp//templates/cierreTarjeta.jsp"></c:import>
		</div>
	</div>
	<!-- / Content -->


	<div class="content-backdrop fade"></div>
</div>
<!-- Content wrapper -->
<c:import url="/WEB-INF/jsp//NiceAdminTemplate/footer.jsp"></c:import>
