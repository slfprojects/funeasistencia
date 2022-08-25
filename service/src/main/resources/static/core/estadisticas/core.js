
function fnGenerarPrimerCuadro(){
	$.ajax({
		    url:'/front.funeasistencia/verificarSesion',
		    type:'GET',
		}).done(function(response){
			var vResponse=response.length;
			if(vResponse>0){
				var vParam1=$('input[name="param1"]').val();
				var vParam2=$('input[name="param2"]').val();
				var p1=$('div[id="param1"]');
				var p2=$('div[id="param2"]');
				p1.html('');
				p2.html('');
				
				if(vParam1 && vParam2){
					vObjRequest={
						param1:vParam1,
						param2:vParam2
					};
					var vTituloFecha=$('p[id="aFecha"]');
					vTituloFecha.html('');
					
					vTituloFecha.html('<hr> <div style="text-align:left;"><span class="badge bg-dark col-md-12">Estadísticas de la Efectividad de Presupuestos y Servicios (General)</span></div>'+
					'<br> <b> <center>Desde'+vParam1+' hasta '+vParam2+'</center></b> ');
					 $.ajax({
					url:'/front.funeasistencia/estadisticas.consulta',
					type:'POST',
					dataType:'JSON',
					data: JSON.stringify(vObjRequest),
					contentType:'application/json;charset=utf-8'
					}).done(function(response){
						if(response.length>0){
							vArrayDataAux=[];
							vArrayData=[];
							vArrayLabels=[];
							vArrayColores=[];
							var vAcumuladorPresupuestos=0;
							var vAcumuladorServicios=0;
							var vServicios=0;
							var vMontoIngreso=0;
							for(var graficos=0;graficos<response.length;graficos++){
								if(response[graficos][0]!='999'){
									vAcumuladorPresupuestos+=response[graficos][2];
									vAcumuladorServicios+=response[graficos][3];
									vArrayDataAux[graficos]=response[graficos][4];
									//vArrayColores[graficos]=fnColores(graficos);
									//vArrayLabels[graficos]=response[graficos][1];
								}
							}
							vArrayData[0]= Math.round( (100-((parseInt(vAcumuladorServicios)*100)/parseInt(vAcumuladorPresupuestos))) *100)/100;
							vArrayData[1]= Math.round( (((parseInt(vAcumuladorServicios)*100)/parseInt(vAcumuladorPresupuestos))) *100)/100;
							vArrayLabels[0]='Presupuestos';
							vArrayLabels[1]='Servicios';
							vArrayColores[0]='rgb(255,0,0)';
							vArrayColores[1]='rgb(30,144,255)';
							
							var vDivGrafico=$('div[id="cjgrafico10"]');
							vDivGrafico.html('');
							vDivGrafico.append('<canvas id="cjgrafico1" height="350vw" width="550vw" ></canvas>');
							
							fnConstruirGrafico(vArrayData,vArrayLabels,vArrayColores,'cjgrafico1');
				    		var vCabecera=fnConfiguracionDtEstadisticas1()[0];
				   	 		var vTabla=fnArmarTabla(vCabecera,1);
							var vDivConsulta=$('div[id="dtconsulta"]');
				   	 		vDivConsulta.html('');
				    		vDivConsulta.append(vTabla);
				    		var vTablaDt=$('table[id="dt_gestion1"]');
				    		var vDatatables=vTablaDt.DataTable(fnRetornarConfiguracionSubTotalDT());
				    		console.log(response);
				    		for(var dt=0;dt<response.length;dt++){
								if(response[dt][1].trim()=='<b>Total General</b>'){
									vServicios=parseInt(response[dt][3]);
									vMontoIngreso=parseFloat(response[dt][4]);
								}
								
								if(response[dt][0]=='999'){
									vDatatables.row.add(
										[
											response[dt][1],
											response[dt][2],
											response[dt][3],
											fnFormatoMonto(parseFloat(response[dt][4]),2),
											fnFormatoMonto(parseFloat(response[dt][5]),1),
											''
											
										]
									).draw(false);
								}else{
									var VBoton='';
									vBoton='<button class="btn btn-xs btn-dark" onclick="fnMostrarGraficoPorOficina('+response[dt][0]+')">Consultar</button>';
									vDatatables.row.add(
										[
											response[dt][1],
											response[dt][2],
											response[dt][3],
											fnFormatoMonto(parseFloat(response[dt][4]),2),
											fnFormatoMonto(parseFloat(response[dt][5]),1),
											vBoton
											
										]
									).draw(false);
								}
								
								
							}
							var vDivResultadoFinal=$('div[id="resultadoFinal"]');//1004532 4027
							vDivResultadoFinal.html('');
							var vFinal=0;
							console.log(vMontoIngreso,vServicios);
							if(vMontoIngreso>0 && vServicios>0){
								vFinal=parseFloat(vMontoIngreso/vServicios);
							}
							
							vDivResultadoFinal.append(
								'<div class="col-md-12 mb-1">'+
							        '<div class="card">'+
							        '<span class="badge bg-label-info" style="text-align:center;font-size:14px;">Precio Promedio : '+fnFormatoMonto(vFinal,2)+'.</span>'+	
							        '</div>'+
							    '</div>'
							);
						}
					})
				}else{
					var p1=$('div[id="param1"]');
					var p2=$('div[id="param2"]');
					if(!vParam1){
						p1.html('');
						p1.append('El campo est&aacute; vac&iacute;o');
					}
					console.log(!p1.val(),!p2.val());
					if(!vParam2){
						p2.html('');
						p2.append('El campo est&aacute; vac&iacute;o');
					}

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
function fnConstruirGrafico(vArrayData,vArrayLabels,vArrayColores,vIdGrafico){
	const ctx = document.getElementById(vIdGrafico);
	const myChart = new Chart(ctx,{
	    type: 'pie',
	    data: {
	        labels: vArrayLabels,
	        datasets: [{
	            label: 'My First Dataset',
	            data: vArrayData,
	            backgroundColor: vArrayColores,
	            hoverOffset: 4
	            }]
	    },
	    plugins: [ChartDataLabels],
	    options: {
			plugins:{
				datalabels:{
					
					formatter:(value,ctx)=>{
						console.log(value,ctx);
						return value+" %";
					},
					color: 'black',
			        labels: {
			          title: {
			            font: {
			              weight: 'bold'
			            }
			          }
			        }
				}
			},
	        
	    }
	}
	

	);
}

function fnMostrarGraficoPorOficina(pCdOficina){
	$.ajax({
		    url:'/front.funeasistencia/verificarSesion',
		    type:'GET',
		}).done(function(response){
			var vResponse=response.length;
			if(vResponse>0){
				var vParam1=$('input[name="param1"]').val();
				var vParam2=$('input[name="param2"]').val();
				
				if(vParam1 && vParam2){
					vObjRequest={
						param1:vParam1,
						param2:vParam2,
						param3:pCdOficina
					};
					 $.ajax({
					url:'/front.funeasistencia/estadisticas.consulta.poroficina',
					type:'POST',
					dataType:'JSON',
					data: JSON.stringify(vObjRequest),
					contentType:'application/json;charset=utf-8'
					}).done(function(response){
						console.log(response);
						if(response.length>0){
							vArrayDataAux=[];
							vArrayData=[];
							vArrayLabels=[];
							vArrayColores=[];
							vAcumulador=0;
							for(var graficos=0;graficos<response.length;graficos++){
								if(response[graficos][0].split('-').length<2){
									vAcumulador+=parseInt(response[graficos][3]);
									vArrayDataAux[graficos]=response[graficos][3];
									vArrayColores[graficos]=fnColores(graficos);
									vArrayLabels[graficos]=response[graficos][0];
								}
							}
							for(var data=0;data<vArrayDataAux.length;data++){
								vArrayData[data]=Math.round(  ( (vArrayDataAux[data]*100)/vAcumulador)*100)/100;
							}
							var vDivCanva=$('div[id="canva"]');
							vDivCanva.html('');
							vDivCanva.append('<canvas id="cjgrafico2" height="350vw" width="550vw"></canvas>');
							var vTituloFecha=$('p[id="aFechaB"]');
							vTituloFecha.html('');
							vTituloFecha.html('<hr> <div style="text-align:left;"><span class="badge bg-dark col-md-12">Por Oficina</span></div> <hr>');
							fnConstruirGrafico(vArrayData,vArrayLabels,vArrayColores,'cjgrafico2');
				    		var vCabecera=fnConfiguracionDtEstadisticas2()[0];
				   	 		var vTabla=fnArmarTabla(vCabecera,2);
							var vDivConsulta=$('div[id="dtconsulta2"]');
							vDivConsulta.html('');
				    		vDivConsulta.append(vTabla);
							
							
				    		var vTablaDt=$('table[id="dt_gestion2"]');
				    		var vDatatables=vTablaDt.DataTable(fnRetornarConfiguracionSubTotalDT());
				    		for(var dt=0;dt<response.length;dt++){
								var titulo='';
								if(response[dt][0].split('-').length<2){
									titulo=response[dt][0];
								}else{
									titulo=response[dt][0].split('-')[0];
								}
									vDatatables.row.add(
										[
											titulo,
											//fnFormatoMonto(response[dt][2],2),
											fnFormatoMonto(parseFloat(response[dt][3]),2),
											fnFormatoMonto(response[dt][1],1)
											
										]
									).draw(false);
								
								
							}
						}
					})
				}else{
					var p1=$('div[id="param1"]');
					p1.html('');
					p1.append('El campo est&aacute; vac$iacute;o');
					var p2=$('div[id="param1"]');
					p2.html('');
					p2.append('El campo est&aacute; vac$iacute;o');
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
					    '<div class="toast-body">Ha Culminado la sesion. Sera Redireccionado al inicio en 3 segundos</div>'+
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
function fnColores(pIndex){
	var vArray=[];
	vArray[0]='rgb(255, 215, 0)';//gold
	vArray[1]='rgb(30, 144, 255)';//azul oscuro
	vArray[2]='rgb(255, 0, 0)';//rojo 
	vArray[3]='rgb(127, 255, 212)';//aquamarine
	vArray[4]='rgb(119, 136, 153)';//gris
	vArray[5]='rgb(255, 105, 180)';//rosado
	vArray[6]='rgb(30, 144, 255)';//azul rey
	vArray[7]='rgb(220, 20, 60)'; //rojo divino
	vArray[8]='rgb(240, 248, 255)';//azul alicia
	vArray[9]='rgb(127, 255, 0)';//verde loco
	vArray[10]='rgb(255, 248, 220)';//beige
	vArray[11]='rgb(240, 230, 140)';//mostaza
	return vArray[pIndex];
}