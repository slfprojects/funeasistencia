<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="vTituloTarjetaSinBadges" value="${requestScope.tituloTarjetaSinBadges}"></c:set>
<div class="row">
	<h6 class="fw-bold py-1 mb-1">-</h6>
</div>
<div class="card text-center">

	<div class="card-header badge bg-dark" style="font-size:14px;"><center> ${vTituloTarjetaSinBadges} </center></div>
	<div class="card-body">
	<br>
		