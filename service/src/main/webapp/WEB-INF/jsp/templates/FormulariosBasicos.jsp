<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<c:set var="nombreFormulario" value="${requestScope.nombreFormulario}"></c:set>
<c:set var="vFormularios" value="${requestScope.formularios}"></c:set>
<form action="POST" onsubmit="return false;" name="${nombreFormulario}">
	<div class="row">
		<c:forEach var="formularios" items="${vFormularios}">
			<c:if test="${formularios.type == 'text'}">
				<div class="col-md-${formularios.cols}"
					style="display:${formularios.display}">
					<div class="mb-1">
						<label class="form-label" for="basic-default-fullname"
							id="${formularios.name}">${formularios.labelName}</label> <input
							type="${formularios.type}" name="${formularios.name}"
							class="form-control" id="${formularios.name}"
							placeholder="${formularios.placeholder}"
							value="${formularios.inputValue}"
							<c:if test="${not empty formularios.focusout}">
								onfocusout="${formularios.focusout}"
							</c:if>
							<c:if test="${not empty formularios.onchange}">
								onchange="${formularios.onchange}"
							</c:if>
							<c:if test="${not empty formularios.disabled}">
								disabled
							</c:if>
							>
							
					</div>
					<div id="${formularios.name}" class="form-text bordeSinVerificar">
					</div>
				</div>
			</c:if>
			<c:if test="${formularios.type == 'number'}">
				<div class="col-md-${formularios.cols}"
					style="display:${formularios.display}">
					<div class="mb-1">
						<label class="form-label" for="basic-default-fullname"
							id="${formularios.name}">${formularios.labelName}</label> <input
							type="${formularios.type}" name="${formularios.name}"
							class="form-control" id="${formularios.name}"
							placeholder="${formularios.placeholder}"
							value=" ${formularios.inputValue}"
							<c:if test="${not empty formularios.focusout}">
								onfocusout="${formularios.focusout}"
							</c:if>
							<c:if test="${not empty formularios.onchange}">
								onchange="${formularios.onchange}"
							</c:if>
							<c:if test="${not empty formularios.disabled}">
								disabled
							</c:if>
							>
							
					</div>
					<div id="${formularios.name}" class="form-text bordeSinVerificar">
					</div>
				</div>
			</c:if>
			<c:if test="${formularios.type == 'date'}">
				<div class="col-md-${formularios.cols}"
					style="display:${formularios.display}">
					<div class="mb-1">
						<label class="form-label" for="basic-default-fullname"
							id="${formularios.name}">${formularios.labelName}</label> <input
							type="${formularios.type}" name="${formularios.name}"
							class="form-control" id="${formularios.name}"
							placeholder="${formularios.placeholder}"
							value="" ${formularios.disabled}>
					</div>
					<div id="${formularios.name}" class="form-text bordeSinVerificar">
					</div>
				</div>
			</c:if>

			<c:if test="${formularios.type == 'select' }">
				<div class="col-md-${formularios.cols}"
					style="display:${formularios.display}">
					<div class="mb-1">
						<label class="form-label" id="${formularios.name}">${formularios.labelName}</label>
						<select class="form-select" id="${formularios.name}"
							aria-label="Default select example" name="${formularios.name}"
							
							<c:if test="${not empty formularios.focusout}">
								onfocusout="${formularios.focusout}" 
							</c:if>
							<c:if test="${not empty formularios.onchange}">
								onchange="${formularios.onchange}" 
							</c:if>
							<c:if test="${not empty formularios.disabled}">
								disabled
							</c:if>
							>
							<option value=""> </option>
							<c:set var="options" value="${formularios.selectValues}"></c:set>
							<c:forEach var="option" items="${options}">
							 	<option value="${option.value}" >${option.text}</option>	
							</c:forEach>
						</select>
						<div id="${formularios.name}" class="form-text bordeSinVerificar">
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${formularios.type =='alterno'}">
				<div id="${formularios.name}" class="col-md-${formularios.cols}"></div>
			</c:if>

		</c:forEach>
		
	</div>
</form>
<br>