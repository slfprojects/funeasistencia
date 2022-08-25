<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="vTituloTarjeta" value="${requestScope.tituloTarjeta}"></c:set>
<c:set var="vTituloBadge1" value="${requestScope.tituloBadge1}"></c:set>
<c:set var="vTituloBadge2" value="${requestScope.tituloBadge2}"></c:set>
<div class="row">
	<h6 class="fw-bold py-1 mb-1"><span class="text-muted fw-light">${vTituloBadge1} /</span> ${vTituloBadge2}</h6>
</div>
<div class="card text-center">
	<div class="card-header badge bg-dark" style="font-size:14px;"><center> ${tituloTarjeta} </center></div>
	<div class="card-body">
	<br>
		