<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/header.jsp" />
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/sidebar.jsp" />
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/navbar.jsp" />

<!-- Content wrapper -->
<div class="content-wrapper">
	<c:set var="vMetodoDt" value="${requestScope.metodoDt}"></c:set>
	<c:set var="vLinkConsulta" value="${requestScope.linkConsulta}"></c:set>
	<c:set var="vMetodoSubDt" value="${requestScope.metodoSubDt}"></c:set>
	<c:set var="vLinkSubConsulta" value="${requestScope.linkSubConsulta}"></c:set>
	<c:set var="vIdPojo" value="${requestScope.idPojo}"></c:set>
	<c:set var="vNombreFomulario" value="${requestScope.nombreFomulario}"></c:set>
	
	<input type="hidden" name="metodoDt" value="${vMetodoDt}">
	<input type="hidden" name="linkConsulta" value="${vLinkConsulta}">
	<input type="hidden" name="metodoSubDt" value="${vMetodoSubDt}">
	<input type="hidden" name="linkSubConsulta" value="${vLinkSubConsulta}">
	<input type="hidden" name="idPojo" value="${vIdPojo}">
	<input type="hidden" name="nombreFomulario" value="${vNombreFomulario}">
	
	
	<!-- Content -->
	<div class="container-xxl flex-grow-1 container-p-y">
		<div class="col-md-12 mb-3">
			<c:import url="/WEB-INF/jsp//templates/aperturaTarjeta.jsp"></c:import>
				<c:import url="/WEB-INF/jsp//templates/FormulariosBasicos.jsp"></c:import>
				<c:import url="/WEB-INF/jsp//templates/Botones.jsp"></c:import>
				<div id="dtconsulta"></div>
			<c:import url="/WEB-INF/jsp//templates/cierreTarjeta.jsp"></c:import>
			
			
		</div>
	</div>
	<!-- / Content -->


	<div class="content-backdrop fade"></div>
</div>
<!-- Content wrapper -->
<c:import url="/WEB-INF/jsp//NiceAdminTemplate/footer.jsp"></c:import>
