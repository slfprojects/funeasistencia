<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<form action="POST" onsubmit="return false;" name="formPresupuestos3">
<table class="table" id="dt_gestion1">
<thead class="table-secondary">
  <tr>
    <th>Tipo Servicio</th>
    <th>Moneda de Pago</th>
    <th>Tipo de Pago</th>
    <th>Monto Sin IVA</th>
    <th>Al Cambio en Bolívares</th>
    <th>Acciones</th>
  </tr>
</thead>
<tbody>
<c:set var="vFormularios" value="${requestScope.presupuestoServicios}"></c:set>
<c:set var="vServicios" value="${requestScope.servicios}"></c:set>
<c:set var="vMoneda" value="${requestScope.moneda}"></c:set>
<c:set var="vTipoPago" value="${requestScope.tipopago}"></c:set>
<c:set var="vTasaPresupuesto" value="${requestScope.tasaPresupuesto}"></c:set>
<c:set var="vCurrencySymbol" value="${requestScope.pCurrencySymbol}"></c:set>




	<c:forEach var="formulario" items="${vFormularios}">
		<tr>
			<td>
				<div class="col-md-12">
					<div class="mb-1">
						<input value="${formulario.cdpresupuestoservicio}" hidden name="cdpresupuestoservicio">
						<select class="form-select" id="cdtiposervicio" 
							aria-label="Default select example" name="cdtiposervicio">
							<c:set var="opServicios" value="${vServicios}"></c:set>
							<c:forEach var="option" items="${opServicios}">
							 	<c:choose>
							 		<c:when test="${formulario.cdtiposervicio eq option.cdtiposervicio}">
							 			<option selected value="${option.cdtiposervicio}">${option.txtiposervicio}</option>
							 		</c:when>
							 		<c:otherwise>
							 			<option value="${option.cdtiposervicio}">${option.txtiposervicio}</option>
							 		</c:otherwise>
							 	</c:choose>
							</c:forEach>

						</select>

					</div>
				</div>
			</td>
			<td>
				<div class="col-md-12">
					<div class="mb-1">
						<select class="form-select" id="cdmoneda2" onfocusout="fnBuscarMoneda2()" 
							aria-label="Default select example" name="cdmoneda2" >
							<c:set var="opMoneda" value="${vMoneda}"></c:set>
							<c:forEach var="option" items="${opMoneda}">
							 	<c:choose>
							 		<c:when test="${formulario.cdmoneda eq option.cdmoneda}">
							 			<option selected value="${option.cdmoneda}">${option.txmoneda}</option>
							 		</c:when>
							 		<c:otherwise>
							 			<option value="${option.cdmoneda}">${option.txmoneda}</option>
							 		</c:otherwise>
							 	</c:choose>
							</c:forEach>

						</select>

					</div>
				</div>
			</td>
			<td>
				<div class="col-md-12">
					<div class="mb-1">
						<select class="form-select" id="cdtipopago2" name="cdtipopago2" >
							<c:set var="opTipoPago" value="${vTipoPago}"></c:set>
							<c:forEach var="option" items="${opTipoPago}">
							 	<c:choose>
							 		<c:when test="${formulario.cdtipopago eq option.cdtipopago}">
							 			<option selected value="${option.cdtipopago}">${option.txtipopago}</option>
							 		</c:when>
							 		<c:otherwise>
							 			<option value="${option.cdtipopago}">${option.txtipopago}</option>
							 		</c:otherwise>
							 	</c:choose>
							</c:forEach>
						</select>

					</div>
				</div>
			</td>
			<td>
				<div class="col-md-12">
					<div class="mb-1">
					<fmt:formatNumber value="${formulario.mtpresupuesto}" var="formatMtPresupuesto" 
						
						currencySymbol="${vCurrencySymbol}"
						type="currency"/>
						<input
							
							type="text" name="mtpresupuesto"
							class="form-control" id="mtpresupuesto"
							placeholder=""
							value="${formatMtPresupuesto}">
					</div>
					<div id="mtpresupuesto" class="form-text" style="color: red;">
					</div>
				</div>
				
			</td>
			<td>
				<div class="col-md-12">
					<div class="mb-1">
						<fmt:setLocale value="en_US"/>
						<fmt:formatNumber value="${formulario.mtpresupuesto*vTasaPresupuesto}" var="formatMtAlCambio" 
						currencySymbol="VES"
						type="currency"/>
						<input
							disabled
							type="text" name="mtalcambio"
							class="form-control" id="mtalcambio"
							placeholder=""
							value="${formatMtAlCambio}">
					</div>
					<div id="mtpresupuesto" class="form-text" style="color: red;">
					</div>
				</div>
				
			</td>
			<td><button class="btn btn-xs btn-outline-secondary" onclick="fnEliminarServicioPresupuesto(${formulario.cdpresupuestoservicio})"><span class="tf-icons bx bx-trash"></span> Eliminar </button> </td>
		</tr>
	</c:forEach>



</tbody>

</table>

</form>
