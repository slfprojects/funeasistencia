<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/header.jsp" />
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/sidebar.jsp" />
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/navbar.jsp" />

<c:set var="vFuncionGenerar" value="${requestScope.funcionGenerar}"></c:set>
<c:set var="vFuncionMostrar" value="${requestScope.funcionMostrar}"></c:set>
<!-- Content wrapper -->
<div class="content-wrapper">	
	
	<!-- Content -->
	<div class="container-xxl flex-grow-1 container-p-y">
		<div class="col-md-12 mb-3">
			<c:import url="/WEB-INF/jsp//templates/aperturaTarjeta.jsp"></c:import>
				<div class="row">
					<hr>
					<div class="col-md-3">
						Fecha desde
						<input class="form-control" type="date" name="p1">
					</div>
					<div class="col-md-3">
						Fecha desde
						<input class="form-control" type="date" name="p2">
						<br>
					</div>
					<center>
						<button type="button" class="btn btn-dark" onclick="${vFuncionMostrar}">
				           <span class="tf-icons bx  bx-add-to-queue"></span> Consultar
				        </button>
				        <button type="button" class="btn btn-dark" onclick="${vFuncionGenerar}">
				           <span class="tf-icons bx  bx-add-to-queue"></span> Generar Reporte
				        </button>
				        <br>
				        <br>
					</center>
					<hr>
					
					<p id="aFecha"></p>
					<hr>
					<div id="dtconsulta"></div>
					<hr>
					<div id="resultadoFinal"></div>
					<hr>
				</div>
			<c:import url="/WEB-INF/jsp//templates/cierreTarjeta.jsp"></c:import>
			
			
		</div>
	</div>
	<!-- / Content -->


	<div class="content-backdrop fade"></div>
</div>
<!-- Content wrapper -->
<c:import url="/WEB-INF/jsp//NiceAdminTemplate/footer.jsp"></c:import>
