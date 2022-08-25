<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/header.jsp" />
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/sidebar.jsp" />
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/navbar.jsp" />

<!-- Content wrapper -->
<div class="content-wrapper">
	<c:set var="vMetodoAgregar" value="${requestScope.metodoAgregar}"></c:set>
	<c:set var="vFuncionAgregar" value="${requestScope.funcionAgregar}"></c:set>
	<c:set var="vLinkVolver" value="${requestScope.linkVolver}"></c:set>
	<c:set var="vMetodoDt" value="${requestScope.metodoDt}"></c:set>
	<input type="hidden" name="metodoAgregar" value="${vMetodoAgregar}">
	<input type="hidden" name="linkVolver" value="${vLinkVolver}">
	<input type="hidden" name="metodoDt" value="${vMetodoDt}">
	
	
	<!-- Content -->
	<div class="container-xxl flex-grow-1 container-p-y">
		<div class="row">
			<div class="col-md-6 mb-1">
				<c:import url="/WEB-INF/jsp//templates/aperturaTarjeta.jsp"></c:import>
					<c:import url="/WEB-INF/jsp//templates/FormulariosBasicos.jsp"></c:import>
					<div class="row">
						<center>
							<button type="button" class="btn btn-dark" onclick="${vFuncionAgregar}">
					           <span class="tf-icons bx bx-add-to-queue"></span> Seleccionar
					        </button>
					        <a href="${vLinkVolver}"  class="btn btn-secondary">
					           <span class="tf-icons bx bx-left-arrow-circle"></span> Volver
					        </a>
						</center>
					</div>
				<c:import url="/WEB-INF/jsp//templates/cierreTarjeta.jsp"></c:import>
			</div>
			<div class="col-md-6 mb-3">
				<c:import url="/WEB-INF/jsp//templates/aperturaTarjetaSinBadges.jsp"></c:import>
					<div id="dtconsulta1"></div>
					<br>
					<c:import url="/WEB-INF/jsp//templates/Botones.jsp"></c:import>
					<div id="mensajeGuardia" class="form-text" style="color:red;">

   					 </div>
				<c:import url="/WEB-INF/jsp//templates/cierreTarjeta.jsp"></c:import>
			</div>
		</div>
	</div>
	<!-- / Content -->


	<div class="content-backdrop fade"></div>
</div>
<!-- Content wrapper -->
<c:import url="/WEB-INF/jsp//NiceAdminTemplate/footer.jsp"></c:import>
