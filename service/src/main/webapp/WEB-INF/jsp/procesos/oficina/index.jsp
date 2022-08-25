<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/header.jsp" />
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/sidebar.jsp" />
<jsp:include page="/WEB-INF/jsp//NiceAdminTemplate/navbar.jsp" />

<!-- Content wrapper -->
<div class="content-wrapper">
	<c:set var="vValoresOficina" value="${requestScope.valoresOficina}"></c:set>
	<c:set var="vValoresAtaudes" value="${requestScope.valoresAtaudes}"></c:set>
	<c:set var="vValoresCapillas" value="${requestScope.valoresCapillas}"></c:set>
	<c:set var="vValoresServicios" value="${requestScope.valoresServicios}"></c:set>
	<c:set var="vValoresProveedores" value="${requestScope.valoresProveedores}"></c:set>
	
	<c:set var="vLinkAgregarCapillas" value="${requestScope.linkAgregarCapillas}"></c:set>
	<c:set var="vLinkAgregarAtaudes" value="${requestScope.linkAgregarAtaudes}"></c:set>
	<c:set var="vLinkAgregarServicios" value="${requestScope.linkAgregarServicios}"></c:set>
	<c:set var="vLinkAgregarProveedores" value="${requestScope.linkAgregarProveedores}"></c:set>
	
	<c:set var="vLinkEliminarCapillas" value="${requestScope.linkEliminarCapillas}"></c:set>
	<c:set var="vLinkEliminarAtaudes" value="${requestScope.linkEliminarAtaudes}"></c:set>
	<c:set var="vLinkEliminarServicios" value="${requestScope.linkEliminarServicios}"></c:set>
	<c:set var="vLinkEliminarProveedor" value="${requestScope.linkEliminarProveedor}"></c:set>
	
	<c:set var="vLinkVolver" value="${requestScope.linkVolver}"></c:set>
	
	<input type="hidden" name="linkVolver" value="${vLinkVolver}">
	<input type="hidden" name="linkEliminarCapillas" value="${vLinkEliminarCapillas}">
	<input type="hidden" name="linkEliminarAtaudes" value="${vLinkEliminarAtaudes}">
	<input type="hidden" name="linkEliminarServicios" value="${vLinkEliminarServicios}">
	<input type="hidden" name="linkEliminarProveedor" value="${vLinkEliminarProveedor}">
	<!-- Content -->
	<div class="container-xxl flex-grow-1 container-p-y">
		<div class="row">
			
			<div class="col-md-12 mb-1">
		
					
				<c:import url="/WEB-INF/jsp//templates/aperturaTarjeta.jsp"></c:import>
					<div class="row">
						<hr>
						<h6>Detalle de Configuración</h6>
						<hr>
					    <div class="col-md-4 mb-1"">
					        <div class="card">
					        <span class="badge bg-secondary">Nombre Compañía</span>
					        <div class="card-body">
					            <p class="card-text">
					            ${vValoresOficina.txcompania}
					            </p>
					        </div>
					        </div>
					    </div>
					    <div class="col-md-8 mb-1"">
					        <div class="card">
					        <span class="badge bg-secondary">Dirección Compañía</span>
					        <div class="card-body">
					            <p class="card-text">
					            ${vValoresOficina.txdireccion}
					            </p>
					        </div>
					        </div>
					    </div>
					    <div class="col-md-4 mb-1"">
					        <div class="card">
					        <span class="badge bg-secondary">Telefono Compañía</span>
					        <div class="card-body">
					            <p class="card-text">
					            ${vValoresOficina.txtelefono1}
					            </p>
					        </div>
					        </div>
					    </div>
					    <div class="col-md-4 mb-1"">
					        <div class="card">
					        <span class="badge bg-secondary">Telefono Secundario Compañía</span>
					        <div class="card-body">
					            <p class="card-text">
					            ${vValoresOficina.txtelefono2}
					            </p>
					        </div>
					        </div>
					    </div>
					</div>
					<hr>
					<h6>Detalle de Configuración</h6>
					<hr>
					<div class="row"">
						
						<div class="table-responsive col-md-6">
						    <table class="table table-sm" id="dt1" class="display" style="width:100%;font-size:14px;">
						        <thead class="table-secondary">
						            <tr>
						                <th colspan="3">Capillas por Oficina</th>
						            </tr>
						            <tr>
						                <th>Código Capilla</th>
						                <th>Despcripción Capilla</th>
						                <th>Acciones</th>
						            </tr>
						        </thead>
						        <tbody>
						        	
						        	<c:if test="${vValoresCapillas.size()>0}">
						            <c:forEach var="vCapilla" items="${vValoresCapillas}">
						                <tr>
						                    <td>${vCapilla.cdcapilla}</td>
						                    <td>${vCapilla.txcapilla}</td>
						                    <c:if test="${vCapilla.stcapilla == '1'}">
												<td>
													<button class="btn btn-xs btn-outline-danger" onclick="fnEliminarCapillas(${vCapilla.cdcapilla})">Inhabilitar</button>
		
												</td>
											</c:if>
											<c:if test="${vCapilla.stcapilla == '0'}">
												<td>
													<button class="btn btn-xs btn-outline-info" onclick="fnEliminarCapillas(${vCapilla.cdcapilla})">Habilitar</button>
		
												</td>
											</c:if>
						                </tr>
						            </c:forEach>
						            </c:if>
						        </tbody>
						    </table>
						 
						    <center><a href="${vLinkAgregarCapillas}" class="btn btn-dark btn-xs" > Agregar</a></center>
						    <br>
						</div>
						
						
						
						<div class="table-responsive col-md-6">
							<table class="table table-sm" id="dt2" class="display" style="width:100%;font-size:14px;">
								<thead class="table-secondary">
									<tr>
										<th colspan="3">Servicios por Oficina</th>
									</tr>
									<tr>
										<th>Código Servicio</th>
										<th>Despcripción Servicio</th>
										<th>Acciones</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${vValoresServicios.size()>0}">
									<c:forEach var="vServicio" items="${vValoresServicios}">
										<tr>
											<td>${vServicio.cdtiposervicio}</td>
											<td>${vServicio.txtiposervicio}</td>
											<c:if test="${vServicio.sttiposervicio == '1'}">
												<td>
													<button class="btn btn-xs btn-outline-danger" onclick="fnEliminarServicios(${vServicio.cdtiposervicio})">Inhabilitar</button>
		
												</td>
											</c:if>
											<c:if test="${vServicio.sttiposervicio == '0'}">
												<td>
													<button class="btn btn-xs btn-outline-info" onclick="fnEliminarServicios(${vServicio.cdtiposervicio})">Habilitar</button>
		
												</td>
											</c:if>
										
										</tr>
									</c:forEach>
									</c:if>
								</tbody>
							</table>
						
							<center><a href="${vLinkAgregarServicios}" class="btn btn-dark btn-xs" > Agregar</a></center>
							<br>
						</div>
						
						
						
						<div class="table-responsive col-md-6" >
							<table class="table table-sm" id="dt3" class="display" style="width:100%;font-size:14px;">
								<thead class="table-secondary">
									<tr>
										<th colspan="4">Ataudes por Oficina</th>
									</tr>
									<tr>
										<th>Código Ataud</th>
										<th>Nombre Ataud</th>
										<th>Proveedor</th>
										<th>Acciones</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${vValoresAtaudes.size()>0}">
									<c:forEach var="vAtaud" items="${vValoresAtaudes}">
										<tr>
											<td>${vAtaud.cdataud}</td>
											<td>${vAtaud.txataud}</td>
											<td>${vAtaud.txproveedor}</td>
											<c:if test="${vAtaud.stataud == '1'}">
												<td>
													<button class="btn btn-xs btn-outline-danger" onclick="fnEliminarAtaudes(${vAtaud.cdataud})">Inhabilitar</button>
		
												</td>
											</c:if>
											<c:if test="${vAtaud.stataud == '0'}">
												<td>
													<button class="btn btn-xs btn-outline-info" onclick="fnEliminarAtaudes(${vAtaud.cdataud})">Habilitar</button>
		
												</td>
											</c:if>
											
										</tr>
									</c:forEach>
									</c:if>
								</tbody>
							</table>

							<center><a href="${vLinkAgregarAtaudes}" class="btn btn-dark btn-xs" > Agregar</a></center>
							<br>
						</div>
						
						<div class="table-responsive col-md-6" >
							<table class="table table-sm" id="dt3" class="display" style="width:100%;font-size:14px;">
								<thead class="table-secondary">
									<tr>
										<th colspan="4">Proveedores por Oficina</th>
									</tr>
									<tr>
										<th>Código Proveedor</th>
										<th>Nombre Proveedor</th>
										<th>Acciones</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${vValoresProveedores.size()>0}">
									<c:forEach var="vProveedor" items="${vValoresProveedores}">
										<tr>
											<td>${vProveedor.cdproveedor}</td>
											<td>${vProveedor.txproveedor}</td>
											<c:if test="${vProveedor.stproveedor == '1'}">
												<td>
													<button class="btn btn-xs btn-outline-danger" onclick="fnEliminarProveedores(${vProveedor.cdproveedor})">Inhabilitar</button>
		
												</td>
											</c:if>
											<c:if test="${vProveedor.stproveedor == '0'}">
												<td>
													<button class="btn btn-xs btn-outline-info" onclick="fnEliminarProveedores(${vProveedor.cdproveedor})">Habilitar</button>
		
												</td>
											</c:if>
											
										</tr>
									</c:forEach>
									</c:if>
								</tbody>
							</table>

							<center><a href="${vLinkAgregarProveedores}" class="btn btn-dark btn-xs" > Agregar</a></center>
							<br>
						</div>
						
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

