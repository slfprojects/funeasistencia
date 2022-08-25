<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
<c:set var="vLinkVolver" value="${requestScope.linkVolver}"></c:set>
<c:set var="vFuncionAgregar" value="${requestScope.funcionAgregar}"></c:set>
<div class="row">
	<center>
		<button type="button" class="btn btn-dark" onclick="${vFuncionAgregar}">
           <span class="tf-icons bx bx-add-to-queue"></span> Registrar
        </button>
        <a href="${vLinkVolver}"  class="btn btn-secondary">
           <span class="tf-icons bx bx-left-arrow-circle"></span> Volver
        </a>
	</center>
</div>
<br>
