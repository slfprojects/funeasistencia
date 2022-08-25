<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
<c:set var="vLinkAgregar" value="${requestScope.linkAgregar}"></c:set>
<c:set var="vFuncionRegistrar" value="${requestScope.funcionRegistrar}"></c:set>
<c:set var="vFuncionConsulta" value="${requestScope.funcionConsulta}"></c:set>
<div class="row">
	<c:if test="${vFuncionConsulta!=null}">
		<center>
		<button type="button" class="btn btn-dark" onclick="${vFuncionConsulta}">
           <span class="tf-icons bx bx-search"></span> Consultar
        </button>
        <c:if test="${vLinkAgregar!=null}">
        <a href="${vLinkAgregar}"  class="btn btn-secondary">
           <span class="tf-icons bx bx-add-to-queue"></span> Añadir
        </a>
        </c:if>
		</center>
	</c:if>
	<c:if test="${vFuncionConsulta==null}">
		<center>
		<button type="button" class="btn btn-dark" onclick="${vFuncionRegistrar}">
           <span class="tf-icons bx  bx-add-to-queue"></span> Registrar
        </button>
	</center>
	</c:if>
	
</div>
<br>
