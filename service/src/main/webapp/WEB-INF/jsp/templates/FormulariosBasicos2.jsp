<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<c:set var="nombreFormulario2" value="${requestScope.nombreFormulario2}"></c:set>
<c:set var="vFormularios2" value="${requestScope.formularios2}"></c:set>
<form action="POST" onsubmit="return false;" name="${nombreFormulario2}">
	<div class="row">
		<c:forEach var="formularios" items="${vFormularios2}">
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
							<c:if test="${formularios.focusout != ''}">
								onfocusout="${formularios.focusout}"
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
							value="${formularios.inputValue}"
							<c:if test="${not empty formularios.focusout}">
								onfocusout="${formularios.focusout}"
							</c:if>
							<c:if test="${not empty formularios.onchange}">
								onchange="${formularios.onchange}"
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
							value=" ${formularios.inputValue}">
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
							<c:if test="${formularios.focusout != ''}">
								onfocusout="${formularios.focusout}"
							</c:if>
							>
							<option value=""> </option>
							<c:set var="options" value="${formularios.selectValues}"></c:set>
							<c:forEach var="option" items="${options}">
							 	<option value="${option.value}"  >${option.text}</option>	
							</c:forEach>
						</select>
					</div>
					<div id="${formularios.name}" class="form-text bordeSinVerificar">
					</div>
					
				</div>
			</c:if>


		</c:forEach>
		
	</div>
</form>
<br>