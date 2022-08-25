/**
 * 
 */function fnGenerarReportes1(){
    var vCriterio1=$('input[name="p1"]').val();
    var vCriterio2=$('input[name="p2"]').val();
    if(vCriterio1 || vCriterio2){
        window.location.href = '/front.funeasistencia/reportes.guardias1L/'+vCriterio1+'/'+vCriterio2
    }else{
        
    }
}
function fnGenerarReportes2(){
    var vCriterio1=$('input[name="p1"]').val();
    var vCriterio2=$('input[name="p2"]').val();
    if(vCriterio1 || vCriterio2){
        window.location.href = '/front.funeasistencia/reportes.presupuestos2L/'+vCriterio1+'/'+vCriterio2
    }else{
        
    }
    ;
}
function fnGenerarReportes3(){
    var vCriterio1=$('input[name="p1"]').val();
    var vCriterio2=$('input[name="p2"]').val();
    if(vCriterio1 || vCriterio2){
        window.location.href = '/front.funeasistencia/reportes.presupuestos1L/'+vCriterio1+'/'+vCriterio2
       
    }else{
       
    }
    ;
}

function fnGuardias(){
	
	$.ajax({
		    url:'/front.funeasistencia/verificarSesion',
		    type:'GET',
		}).done(function(response){
			var vResponse=response.length;
			if(vResponse>0){
				
				var vParam1=$('input[name="p1"]').val();
				var vParam2=$('input[name="p2"]').val();
				
				
				if(vParam1 && vParam2){
					vObjRequest={
						param1:vParam1,
						param2:vParam2
					};
					var vTituloFecha=$('p[id="aFecha"]');
					vTituloFecha.html('');
					vTituloFecha.html('<b><center><b>Resumen de Histórico de Guardias y Servicios</center>'+
					'<br> <center>Desde'+vParam1+' hasta '+vParam2+'</center></b>');
					 $.ajax({
					url:'/front.funeasistencia/reportes.consulta.guardias',
					type:'POST',
					dataType:'JSON',
					data: JSON.stringify(vObjRequest),
					contentType:'application/json;charset=utf-8'
					}).done(function(response){
						console.log(response);
						if(response.length>0){
							vAcumulador1=0;
							vAcumulador2=0;
							vAcumulador3=0;
							vAcumulador4=0;
							for(var graficos=0;graficos<response.length;graficos++){
									vAcumulador1+=parseInt(response[graficos][7]);
									vAcumulador2+=parseInt(response[graficos][8]);
									vAcumulador3+=parseFloat(response[graficos][5]);
									vAcumulador4+=parseFloat(response[graficos][6]);
								
							}
				    		var vCabecera=fnReporteGuardias()[0];
				   	 		var vTabla=fnArmarTabla(vCabecera,1);
							var vDivConsulta=$('div[id="dtconsulta"]');
				   	 		vDivConsulta.html('');
				    		vDivConsulta.append(vTabla);
				    		var vTablaDt=$('table[id="dt_gestion1"]');
				    		var vDatatables=vTablaDt.DataTable(fnRetornarConfiguracionSubTotalDT());
				    		for(var dt=0;dt<response.length;dt++){
								vDatatables.row.add(
									[
										response[dt][0],
										response[dt][1],
										response[dt][2],
										fnFormatoMonto(parseFloat(response[dt][3]),2),
										fnFormatoMonto(parseFloat(response[dt][4]),1)
									]
								).draw(false);
							}
							vDatatables.row.add(
									[
										'<b>Total General</b>',
										vAcumulador1,
										vAcumulador2,
										fnFormatoMonto(parseFloat(vAcumulador3),2),
										fnFormatoMonto(parseFloat(vAcumulador4),1),
									]
								).draw(false);
							
						}
					}).fail(function(a,b,c){
						console.log(a,b,c);
					})
				}else{
					var p1=$('div[id="param1"]');
					p1.html('');
					p1.append('El campo est&aacute; vac&iacute;o');
					var p2=$('div[id="param2"]');
					p2.html('');
					p2.append('El campo est&aacute; vac&iacute;o');
				}
			}else{
				var vDivMensaje=$('div[id="divToast"]');
				vDivMensaje.append(
					'<div id="toast" class="bs-toast toast toast-placement-ex m-2 fade bg-dark top-0 end-0 show" role="alert" aria-live="assertive" aria-atomic="true" data-delay="2000">'+
					    '<div class="toast-header">'+
					    '<i class="bx bx-bell me-2"></i>'+
					    '<div class="me-auto fw-semibold">¡Aviso!</div>'+
					    '<small>hace 2 Segundos</small>'+
					    ' <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>'+
					    '</div>'+
					    '<div class="toast-body">Ha Culminado la sesesion. Sera Redireccionado al inicio en 3 segundos</div>'+
					'</div>'
					);
				var toastPlacementExample=$('div[id="toast"]');
				toastPlacementExample.removeClass('hide').addClass('show');
				setTimeout(() => {
					    toastPlacementExample.removeClass('show').addClass('hide');
					    window.location.replace('/front.funeasistencia/inicio')
				}, 3000);
			}
		});
}
function fnPresupuestos1(){
	$.ajax({
		    url:'/front.funeasistencia/verificarSesion',
		    type:'GET',
		}).done(function(response){
			var vResponse=response.length;
			if(vResponse>0){
				var vParam1=$('input[name="p1"]').val();
				var vParam2=$('input[name="p2"]').val();
				
				if(vParam1 && vParam2){
					vObjRequest={
						param1:vParam1,
						param2:vParam2
					};
					var vTituloFecha=$('p[id="aFecha"]');
					vTituloFecha.html('');
					vTituloFecha.html('<b><center>Resumen de Histórico de los Servicios</center>'+
					'<br> <center>Desde'+vParam1+' hasta '+vParam2+'</center></b>');
					console.log(vParam1 && vParam2);
					 $.ajax({
					url:'/front.funeasistencia/reportes.consulta.presupuestos1',
					type:'POST',
					dataType:'JSON',
					data: JSON.stringify(vObjRequest),
					contentType:'application/json;charset=utf-8'
					}).done(function(response){
						console.log(response);
						if(response.length>0){
							vAcumulador1=0;
							vAcumulador2=0;
							vAcumulador3=0;
							for(var graficos=0;graficos<response.length;graficos++){
									vAcumulador1+=parseInt(response[graficos][4]);
									vAcumulador2+=parseInt(response[graficos][5]);
									vAcumulador3+=parseInt(response[graficos][6]);
							}
				    		var vCabecera=fnReportePresupuesto1()[0];
				   	 		var vTabla=fnArmarTabla(vCabecera,1);
							var vDivConsulta=$('div[id="dtconsulta"]');
				   	 		vDivConsulta.html('');
				    		vDivConsulta.append(vTabla);
				    		var vTablaDt=$('table[id="dt_gestion1"]');
				    		var vDatatables=vTablaDt.DataTable(fnRetornarConfiguracionSubTotalDT());
				    		for(var dt=0;dt<response.length;dt++){
								vDatatables.row.add(
									[
										response[dt][0],
										response[dt][1],
										fnFormatoMonto(parseFloat(response[dt][2]),2),
										fnFormatoMonto(parseFloat(response[dt][3]),1),
									]
								).draw(false);
							}
							vDatatables.row.add(
									[
										'<b>Total General</b>',
										vAcumulador1,
										fnFormatoMonto(parseFloat(vAcumulador2),2),
										fnFormatoMonto(parseFloat(vAcumulador3),1)
									]
								).draw(false);
							
						}
					}).fail(function(a,b,c){
						console.log(a,b,c);
					})
				}else{
					var p1=$('div[id="p1"]');
					p1.html('');
					p1.append('El campo est&aacute; vac&iacute;o');
					var p2=$('div[id="p2"]');
					p2.html('');
					p2.append('El campo est&aacute; vac&iacute;o');
				}
			}else{
				var vDivMensaje=$('div[id="divToast"]');
				vDivMensaje.append(
					'<div id="toast" class="bs-toast toast toast-placement-ex m-2 fade bg-dark top-0 end-0 show" role="alert" aria-live="assertive" aria-atomic="true" data-delay="2000">'+
					    '<div class="toast-header">'+
					    '<i class="bx bx-bell me-2"></i>'+
					    '<div class="me-auto fw-semibold">¡Aviso!</div>'+
					    '<small>hace 2 Segundos</small>'+
					    ' <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>'+
					    '</div>'+
					    '<div class="toast-body">Ha Culminado la sesesion. Sera Redireccionado al inicio en 3 segundos</div>'+
					'</div>'
					);
				var toastPlacementExample=$('div[id="toast"]');
				toastPlacementExample.removeClass('hide').addClass('show');
				setTimeout(() => {
					    toastPlacementExample.removeClass('show').addClass('hide');
					    window.location.replace('/front.funeasistencia/inicio')
				}, 3000);
			}
		});
}
function fnPresupuestos2(){
	$.ajax({
		    url:'/front.funeasistencia/verificarSesion',
		    type:'GET',
		}).done(function(response){
			var vResponse=response.length;
			if(vResponse>0){
				var vParam1=$('input[name="p1"]').val();
				var vParam2=$('input[name="p2"]').val();
				
				if(vParam1 && vParam2){
					vObjRequest={
						param1:vParam1,
						param2:vParam2
					};
					var vTituloFecha=$('p[id="aFecha"]');
					vTituloFecha.html('');
					vTituloFecha.html('<b><center>Resumen de Histórico de Presupuestos y Servicios</center>'+
					'<br> <center>Desde'+vParam1+' hasta '+vParam2+'</center></b>');
					 $.ajax({
					url:'/front.funeasistencia/reportes.consulta.presupuestos2',
					type:'POST',
					dataType:'JSON',
					data: JSON.stringify(vObjRequest),
					contentType:'application/json;charset=utf-8'
					}).done(function(response){
						console.log(response);
						if(response.length>0){
							vAcumulador1=0;
							vAcumulador2=0;
							vAcumulador3=0;
							vAcumulador4=0;
							for(var graficos=0;graficos<response.length;graficos++){
									vAcumulador1+=parseInt(response[graficos][5]);
									vAcumulador2+=parseInt(response[graficos][6]);
									vAcumulador3+=parseInt(response[graficos][7]);
									vAcumulador4+=parseInt(response[graficos][8]);
								
							}
				    		var vCabecera=fnReportePresupuesto2()[0];
				   	 		var vTabla=fnArmarTabla(vCabecera,1);
							var vDivConsulta=$('div[id="dtconsulta"]');
				   	 		vDivConsulta.html('');
				    		vDivConsulta.append(vTabla);
				    		var vTablaDt=$('table[id="dt_gestion1"]');
				    		var vDatatables=vTablaDt.DataTable(fnRetornarConfiguracionSubTotalDT());
				    		for(var dt=0;dt<response.length;dt++){
								vDatatables.row.add(
									[
										response[dt][0],
										response[dt][1],
										response[dt][2],
										fnFormatoMonto(parseFloat(response[dt][3]),2),
										fnFormatoMonto(parseFloat(response[dt][4]),1),
									]
								).draw(false);
							}
							vDatatables.row.add(
									[
										'<b>Total General</b>',
										vAcumulador1,
										vAcumulador2,
										fnFormatoMonto(parseFloat(vAcumulador3),2),
										fnFormatoMonto(parseFloat(vAcumulador4),1),
									]
								).draw(false);
							var vDivResultadoFinal=$('div[id="resultadoFinal"]');
							vDivResultadoFinal.html('');
							var vResta=vAcumulador1-vAcumulador2;
							
							vDivResultadoFinal.append(
								'<div class="col-md-12 mb-1">'+
							        '<div class="card">'+
							        '<span class="badge bg-label-danger" style="text-align:center;font-size:14px;">Información : Existen '+vResta+' Presupuesto(s) sin Materializar en Servicios.</span>'+
			
							        '</div>'+
							    '</div>'
							);
							
						}
					}).fail(function(a,b,c){
						console.log(a,b,c);
					})
				}else{
					var p1=$('div[id="p1"]');
					p1.html('');
					p1.append('El campo est&aacute; vac&iacute;o');
					var p2=$('div[id="p2"]');
					p2.html('');
					p2.append('El campo est&aacute; vac&iacute;o');
				}
			}else{
				var vDivMensaje=$('div[id="divToast"]');
				vDivMensaje.append(
					'<div id="toast" class="bs-toast toast toast-placement-ex m-2 fade bg-dark top-0 end-0 show" role="alert" aria-live="assertive" aria-atomic="true" data-delay="2000">'+
					    '<div class="toast-header">'+
					    '<i class="bx bx-bell me-2"></i>'+
					    '<div class="me-auto fw-semibold">¡Aviso!</div>'+
					    '<small>hace 2 Segundos</small>'+
					    ' <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>'+
					    '</div>'+
					    '<div class="toast-body">Ha Culminado la sesesion. Sera Redireccionado al inicio en 3 segundos</div>'+
					'</div>'
					);
				var toastPlacementExample=$('div[id="toast"]');
				toastPlacementExample.removeClass('hide').addClass('show');
				setTimeout(() => {
					    toastPlacementExample.removeClass('show').addClass('hide');
					    window.location.replace('/front.funeasistencia/inicio')
				}, 3000);
			}
		});
}
