<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/header.jsp" />
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/sidebar.jsp" />
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/navbar.jsp" />

<!-- Content wrapper -->
<div class="content-wrapper">
	<c:set var="vMetodoDt" value="${requestScope.metodoDt}"></c:set>
	<c:set var="vLinkAgregar" value="${requestScope.linkAgregar}"></c:set>
	<c:set var="vIdPojo" value="${requestScope.idPojo}"></c:set>
	<c:set var="vNombreFormulario" value="${requestScope.nombreFormulario}"></c:set>
	<c:set var="vLinkVolver" value="${requestScope.linkVolver}"></c:set>
	<input type="hidden" name="metodoDt" value="${vMetodoDt}">
	<input type="hidden" name="linkConsulta" value="${vLinkConsulta}">

	<input type="hidden" name="idPojo" value="${vIdPojo}">
	<input type="hidden" name="nombreFormulario" value="${vNombreFormulario}">
	<input type="hidden" name="linkAgregar" value="${vLinkAgregar}">
	<input type="hidden" name="linkVolver" value="${vLinkVolver}">
	<!-- Content -->
	<div class="container-xxl flex-grow-1 container-p-y">
		<div class="col-md-12 mb-3">
			<c:import url="/WEB-INF/jsp//templates/aperturaTarjeta.jsp"></c:import>
				<c:import url="/WEB-INF/jsp//templates/FormulariosConValores.jsp"></c:import>
				
				<button class="btn btn-dark" onclick="fnAgregar()"> Agregar </button>
				<a href="${vLinkVolver}" class="btn btn-outline-secondary" > Volver </a>
				
			<c:import url="/WEB-INF/jsp//templates/cierreTarjeta.jsp"></c:import>
			
			
		</div>
	</div>
	<!-- / Content -->


	<div class="content-backdrop fade"></div>
</div>
<!-- Content wrapper -->
<c:import url="/WEB-INF/jsp//NiceAdminTemplate/footer.jsp"></c:import>
