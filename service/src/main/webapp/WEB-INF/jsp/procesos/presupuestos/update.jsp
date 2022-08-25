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
	
<c:set var="vCdMoneda" value="${requestScope.CdMoneda}"></c:set>
<input type="hidden" name="cdmonedapresupuestoupd" value="${vCdMoneda}">
<c:set var="mttasa" value="${requestScope.tasaPresupuesto}"></c:set>
<input type="hidden" name="mttasa" value="${mttasa}">
	
	
	
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
				<div style="text-align:left;"><span class="badge bg-dark col-md-12">Datos Generales</span></div>
				<hr>
				<c:import url="/WEB-INF/jsp/templates/FormulariosConValores.jsp"></c:import>
				<div style="text-align:left;"><span class="badge bg-dark col-md-12">Datos del Presupuestos</span></div>
				<hr>
				<c:import url="/WEB-INF/jsp/templates/FormulariosBasicos2.jsp"></c:import>
				
				<button onclick="fnAgregarObjetosUpd()" class="btn btn-dark">
					Generar
				</button>
				<hr>
				<div style="text-align:left;"><span class="badge bg-dark col-md-12">Resumen de Servicios Presupuestados</span></div>
				<hr>
				<c:import url="/WEB-INF/jsp/procesos/presupuestos/tablaDeServicios.jsp"></c:import>
				<table  class="table-responsive" style="width:100%" id="dt_servicios1">
					<thead><tr><td colspan="3"></td><td colspan="1">Divisas</td><td colspan="1"> Bolívares</td></tr></thead>
					<tbody>
						<tr>
							<td colspan="3"><center>Total del Presupuesto:</center></td>
							<td colspan="1"><p  class="form-control" id="totalDs" value="0" ></p>
							<td colspan="1"><p  class="form-control" id="totalBs" value="0" ></p>
						</tr>
					</tbody>
				</table>
				<hr>
				<center>
				<button onclick="fnActualizarServicios()" class="btn btn-dark" >
					Actualizar Presupuesto
				</button>	
				<a href="/front.funeasistencia/presupuestos.indice"  class="btn btn btn-outline-secondary" >
					Volver
				</a>
				</center>
			<c:import url="/WEB-INF/jsp/templates/cierreTarjeta.jsp"></c:import>
		</div>
	</div>
	<!-- / Content -->
	<div class="content-backdrop fade"></div>
</div>
<!-- Content wrapper -->
<c:import url="/WEB-INF/jsp//NiceAdminTemplate/footer.jsp"></c:import>
