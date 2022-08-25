function fnGenerarConsultaConSweetAlert(pId){
	//Hacer Chequeo de Sesion Antes
	$.ajax({
	    url:'/front.funeasistencia/verificarSesion',
	    type:'GET',
	}).done(function(response){
		var vResponse=response.length;
	
		if(vResponse>0){
			var vCriterio1=$('input[name="param1"]').val();
		    var vCriterio2=$('input[name="param2"]').val();
		    var vLinkConsulta=$('input[name="linkConsulta"]').val();
		    var vMetodoDt=$('input[name="metodoDt"]').val();
		    var vIdPojo=$('input[name="idPojo"]').val();
		    var vObjRequest={
				param1:vCriterio1,
				param2:vCriterio2,
			}
		    if(vCriterio1 && vCriterio2){
		        $.ajax({
			        url:vLinkConsulta,
			       	type:'POST',
			       	dataType:'JSON',
			        data: JSON.stringify(vObjRequest),
			        contentType:'application/json;charset=utf-8',
			        beforeSend: function() {
						vSweetAlertEspera=fnPlantillaSweetAlertEnEspera();
					}
				    }).done(function(response){
						swal.close();
				        var vIndices=window[vMetodoDt]()[1];
				        var vCabecera=window[vMetodoDt]()[0];
				        var vTabla=fnArmarTabla(vCabecera,pId);
				        var vDivConsulta=$('div[id="dtconsulta"]');
				        vDivConsulta.html('');
				        vDivConsulta.append(vTabla);
				        var vTablaDt=$('table[id="dt_gestion'+pId+'"]');
				        var vDatatables=vTablaDt.DataTable(fnRetornarConfiguracionDT());
				        for(var b=0;b<response.length;b++){
				            var vArrayValores=[];
				            for(var c=0;c<vIndices.length;c++){
								var vCdMoneda=0;
								if(vIndices[c]=='cdmoneda'){
									vCdMoneda=response[b][vIndices[c]];
								}
								if(vIndices[c]=='mtpresupuesto'){
									vArrayValores.push(fnFormatoMonto(vCdMoneda,response[b][vIndices[c]]));
								}else{
									vArrayValores.push(response[b][vIndices[c]]);
								}
				                
				            }
				            var vBotones='';
				            if(vIdPojo=="cdpresupuesto"){
								if(vIndices[c]=='stpresupuesto'){
									
									if(vIndices[c]['stpresupuesto']=='Rechazado'){
										
									}else{
										vBotones='<button class="btn btn-xs btn-outline-secondary" onclick="fnLlenarSweetAlertsConsulta('+response[b][vIdPojo]+')"><span class="tf-icons bx bx-bookmark-alt-minus"></span> Modificar</button>';
									}
									
								}else{
									vBotones='<button class="btn btn-xs btn-outline-secondary" onclick="fnLlenarSweetAlertsConsulta('+response[b][vIdPojo]+')"><span class="tf-icons bx bx-bookmark-alt-minus"></span> Consultar</button>';
								}
							}else{
								vBotones='<button class="btn btn-xs btn-outline-secondary" onclick="fnLlenarSweetAlertsConsulta('+response[b][vIdPojo]+')"><span class="tf-icons bx bx-bookmark-alt-minus"></span> Consultar</button>';
							}
				            vArrayValores.push(vBotones);
				            var vNodo=vDatatables.row.add(vArrayValores).draw(false);
				            $(vNodo.node()).attr('id',response[b][vIdPojo]);
				        }       
				    }).fail(function(a,b,c){
						console.log(a,b,c);
					});
		    }else{
		       
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
 function fnGenerarConsultaDatatableBasica(pId){
	//Hacer Chequeo de Sesion Antes
	$.ajax({
	    url:'/front.funeasistencia/verificarSesion',
	    type:'GET',
	}).done(function(response){
		var vResponse=response.length;

		if(vResponse>0){
			var vCriterio1=$('input[name="param1"]').val();
		    var vCriterio2=$('input[name="param2"]').val();
		    var vLinkConsulta=$('input[name="linkConsulta"]').val();
		    var vMetodoDt=$('input[name="metodoDt"]').val();
		    var vIdPojo=$('input[name="idPojo"]').val();
		    var vObjRequest={
				param1:vCriterio1,
				param2:vCriterio2,
			}
		    if(vCriterio1 && vCriterio2){
		        $.ajax({
			        url:vLinkConsulta,
			       	type:'POST',
			       	dataType:'JSON',
			        data: JSON.stringify(vObjRequest),
			        contentType:'application/json;charset=utf-8',
			        beforeSend: function() {
						vSweetAlertEspera=fnPlantillaSweetAlertEnEspera();
					}
				    }).done(function(response){
						swal.close();
				        var vIndices=window[vMetodoDt]()[1];
				        var vCabecera=window[vMetodoDt]()[0];
				        var vTabla=fnArmarTabla(vCabecera,pId);
				        var vDivConsulta=$('div[id="dtconsulta"]');
				        vDivConsulta.html('');
				        vDivConsulta.append(vTabla);
				        var vTablaDt=$('table[id="dt_gestion'+pId+'"]');
				        var vDatatables=vTablaDt.DataTable(fnRetornarConfiguracionDT());
				        var vBotones='';
				        for(var b=0;b<response.length;b++){
				            var vArrayValores=[];
				            for(var c=0;c<vIndices.length;c++){
								var vCdMoneda=0;
								if(vIndices[c]=='cdmoneda'){
									vCdMoneda=response[b][vIndices[c]];
								}
								if(vIndices[c]=='mt_presupuesto'){
									vArrayValores.push(fnFormatoMonto(vCdMoneda,response[b][vIndices[c]]));
								}else{
									vArrayValores.push(response[b][vIndices[c]]);
								}
								
								if(vIdPojo=="cdpresupuesto"){
									if(vIndices[c]=='txestatus'){
										
										if(response[b]['txestatus']=='Rechazado'){
											vBotones='';
										}
										if(response[b]['txestatus']=='Generado'){
											vBotones='<button class="btn btn-xs btn-outline-secondary" onclick="fnLlenarSweetAlertsSinDobleTabla('+response[b][vIdPojo]+')"><span class="tf-icons bx bx-bookmark-alt-minus"></span> Consultar</button>'+
											' <a class="btn btn-xs btn-outline-secondary" href="/front.funeasistencia/presupuestos.indice.actualizar/'+response[b][vIdPojo]+'" ><span class="tf-icons bx bx-bookmark-alt-minus"></span> Modificar</a>';
										}
										if(response[b]['txestatus']=='En Curso'){
											vBotones='<button class="btn btn-xs btn-outline-secondary" onclick="fnLlenarSweetAlertsParaCerrarServicio('+response[b][vIdPojo]+')"><span class="tf-icons bx bx-bookmark-alt-minus"></span> Consultar</button>';
										}
										if(response[b]['txestatus']=='Cerrado'){
											//vBotones='';
											vBotones='<button class="btn btn-xs btn-outline-secondary" onclick="fnLlenarSweetAlertsConsultaServicio('+response[b][vIdPojo]+')"><span class="tf-icons bx bx-bookmark-alt-minus"></span> Consultar</button>';
										}
									}
								}else{
									vBotones='<button class="btn btn-xs btn-outline-secondary" onclick="fnLlenarSweetAlertsSinDobleTabla('+response[b][vIdPojo]+')"><span class="tf-icons bx bx-bookmark-alt-minus"></span> Consultar</button>';
								}
				                
				            }
							vArrayValores.push(vBotones);
				            var vNodo=vDatatables.row.add(vArrayValores).draw(false);
				            $(vNodo.node()).attr('id',response[b][vIdPojo]);
				        }       
				    }).fail(function(a,b,c){
						console.log(a,b,c);
					});
		    }else{
		       
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
 function fnLlenarSweetAlertsSinDobleTabla(pIdItem){
	var vLinkAgregar=$('input[name="linkAgregar"]').val();
	var vIdPojo=$('input[name="idPojo"]').val();
	var vDivisor='<div style="text-align:left;"><span class="badge bg-info">$titulo$</span></div><br>';
	var vBotones='';
	if(vIdPojo=='cdpresupuesto'){
		if(vLinkAgregar){
			vBotones='<center><button class="btn btn-sm btn-dark" onclick="fnConversionServicio('+pIdItem+')">Convertir en Servicio</button>';
		}else{
			vBotones='<center><button class="btn btn-sm btn-dark" onclick="fnReversoServicio('+pIdItem+')">Reversar Servicio</button>';
		}
		
	}
	var vTablaFinal=
	vDivisor.replace('$titulo$','Detalle')+'<div id="textoPrincipal"></div>'+vDivisor.replace('$titulo$','Resumen')+' <br> <div id="tablaAjax" style="font-size:14px;"></div> <br>'+vBotones;
	fnMostrarDetalleEnTexto(pIdItem);
	fnArmarTablaParaSweetAlert(pIdItem);
	Swal.fire({
    	title: 'Detalle de la Consulta.',
        html: vTablaFinal,
        type:'success',
        confirmButtonText: 'Volver',
        customClass:'swal-wide2',
        confirmButtonColor: "#18222c",
    });
}
 function fnLlenarSweetAlertsConsultaServicio(pIdItem){
	var vLinkAgregar=$('input[name="linkAgregar"]').val();
	var vIdPojo=$('input[name="idPojo"]').val();
	var vDivisor='<div style="text-align:left;"><span class="badge bg-info">$titulo$</span></div><br>';
	var vBotones='';
	var vTablaFinal=
	vDivisor.replace('$titulo$','Detalle')+'<div id="textoPrincipal"></div>'+vDivisor.replace('$titulo$','Resumen')+' <br> <div id="tablaAjax" style="font-size:14px;"></div> <br>'+vBotones;
	fnMostrarDetalleEnTexto(pIdItem);
	fnArmarTablaParaSweetAlert(pIdItem);
	Swal.fire({
    	title: 'Detalle de la Consulta.',
        html: vTablaFinal,
        type:'success',
        confirmButtonText: 'Volver',
        customClass:'swal-wide2',
        confirmButtonColor: "#18222c",
    });
}
 function fnLlenarSweetAlertsParaCerrarServicio(pIdItem){
	var vLinkAgregar=$('input[name="linkAgregar"]').val();
	var vIdPojo=$('input[name="idPojo"]').val();
	var vDivisor='<div style="text-align:left;"><span class="badge bg-info">$titulo$</span></div><br>';
	var vBotones='<button class="btn btn-sm btn-outline-secondary" onclick="fnCerrarServicio('+pIdItem+')">CerrarServicio</button> </center> <button class="btn btn-sm btn-outline-secondary" onclick="fnRechazarServicio('+pIdItem+')">Rechazar</button> </center>';
		
	var vTablaFinal=
	vDivisor.replace('$titulo$','Detalle')+'<div id="textoPrincipal"></div>'+vDivisor.replace('$titulo$','Resumen')+' <br> <div id="tablaAjax" style="font-size:14px;"></div> <br>'+vBotones;
	fnMostrarDetalleEnTexto(pIdItem);
	fnArmarTablaParaSweetAlert(pIdItem);
	Swal.fire({
    	title: 'Detalle de la Consulta.',
        html: vTablaFinal,
        type:'success',
        confirmButtonText: 'Volver',
        customClass:'swal-wide2',
        confirmButtonColor: "#18222c",
    });
}
function fnArmarTabla(pCabecera,pId){
    var vCabeceraTabla=
    '<div class="table-responsive text-nowrap">'+
        '<table class="table table-sm" id="dt_gestion'+pId+'" class="display" style="width:100%;font-size:15px;">'+
    '<thead class="table-secondary" id="header">'
        '<tr>';
    var vCabecera='';
    for(var a=0;a<pCabecera.length;a++){
        vCabecera+='<th>'+pCabecera[a]+'</th>';
    }
    var vCierreCabeceraTabla='</tr></thead></table></div>';
    return (vCabeceraTabla+vCabecera+vCierreCabeceraTabla);
}


function fnRetornarConfiguracionSubTotalDT(){
    var vConfiguracion= {"lengthChange": false,
    "bInfo": false,
    "searching":false,
    "paging":false,
    "language": {
      "emptyTable": "No existen registros para mostrar",
      "search":"Filtar por palabra:",
      "paginate": {
          "previous": "Anterior",
          "next":"Siguiente"
        }
    },
    "columnDefs":[
		{
			className:"dt-center",targets:"_all"
		}
	],
		
	
    "bAutoWidth": false,
    "ordering": false,
    "pageLength": 5};
    return vConfiguracion;
}
function fnRetornarConfiguracionDT(){
    var vConfiguracion= {"lengthChange": false,
    "bInfo": false,
    "searching":false,
    "language": {
      "emptyTable": "No existen registros para mostrar",
      "search":"Filtar por palabra:",
      "paginate": {
          "previous": "Anterior",
          "next":"Siguiente"
        }
    },
    "columnDefs":[
		{
			className:"dt-center",targets:"_all"
		}
	],
		
	
    "bAutoWidth": false,
    "ordering": false,
    "pageLength": 5};
    return vConfiguracion;
}
function fnLlenarSweetAlertsConsulta(pIdItem){
	var vFilaContenido=$('tr[id="'+pIdItem+'"]').html();
	var vCabeceraTitulos=$('thead[id="header"]').html();
	var vLinkAgregar=$('input[name="linkAgregar"]').val();
	var vIdPojo=$('input[name="idPojo"]').val();
	var vDivisor='<div style="text-align:left;"><span class="badge bg-info">$titulo$</span></div><br>';
	var vBotones='';
	if(vIdPojo=='cdpresupuesto'){
		if(vLinkAgregar){
			vBotones='<center><button class="btn btn-sm btn-dark" onclick="fnConversionServicio('+pIdItem+')">Convertir en Servicio</button>'+
			'<button class="btn btn-sm btn-outline-secondary" onclick="fnRechazarServicio('+pIdItem+')">Rechazar</button> </center>';
		}else{
			vBotones='<center><button class="btn btn-sm btn-dark" onclick="fnReversoServicio('+pIdItem+')">Reversar Servicio</button>';
		}
		
	}
	var vTablaFinal=
	vDivisor.replace('$titulo$','Resumen')+'<div class="table-responsive text-nowrap"> <table class="table table-sm dataTable no-footer">'+
		'<thead class="table-secondary">'+
		vCabeceraTitulos.replace('<th class="sorting_disabled dt-center" rowspan="1" colspan="1">Acciones</th>','')+
		'</thead>'+
		'<tbody style="font-size:14px">'+
		vFilaContenido.
			replace('<td class="  dt-center"><button class="btn btn-xs btn-outline-secondary" onclick="fnLlenarSweetAlertsConsulta('+pIdItem+')"><span class="tf-icons bx bx-bookmark-alt-minus"></span> Consultar</button></td>','')+
		'</tbody>'+
	'</table> </div> <br>'+vDivisor.replace('$titulo$','Detalle')+'  <br> <div id="tablaAjax" style="font-size:14px;"></div> <br>'+vBotones;
	fnArmarTablaParaSweetAlert(pIdItem);
	Swal.fire({
    	title: 'Detalle de la Consulta.',
        html: vTablaFinal,
        type:'success',
        confirmButtonText: 'Volver',
        customClass:'swal-wide2',
        confirmButtonColor: "#18222c",
    });
}
function fnPlantillaSweetAlertMensaje(pTitulo,pRedirecciona){
	
	var vModalSWA=Swal.fire({
    	title: pTitulo,
        html: '<h6>Proceso realizado exitosamente.</h6>',
        type:'success',
        confirmButtonText: 'Volver',
        customClass:'swal-wide',
        confirmButtonColor: "#18222c",
    }).then((result) => {
		if(result.isConfirmed){
			window.location.replace(pRedirecciona);
		}
	})
	return vModalSWA;
		
}
function fnPlantillaSweetAlertEnEspera(){
	
	var vModalSWA=Swal.fire({
    	title: 'Realizando Transacción...',
        html: '<div class="spinner-grow" role="status">'+
            '<span class="visually-hidden">Esperando Respuesta...</span>'+
          '</div>'+
          '<div class="spinner-grow text-secondary" role="status">'+
            '<span class="visually-hidden">Esperando Respuesta...</span>'+
          '</div>'+
          '<div class="spinner-grow text-light" role="status">'+
            '<span class="visually-hidden">Esperando Respuesta...</span>'+
          '</div>',
          
          
        type:'success',
        showConfirmButton: false,
        customClass:'swal-wide',
        confirmButtonColor: "#18222c",
    }).then((result) => {
		
	})
	return vModalSWA;
		
}
function fnPlantillaSweetAlert(pTitulo,pTablaFinal){
	
	var vModalSWA=Swal.fire({
    	title: pTitulo,
        html: pTablaFinal,
        type:'success',
        showDenyButton: true,	
        confirmButtonText: 'Aplicar',
        denyButtonText: 'Volver',
        customClass:'swal-wide',
        confirmButtonColor: "#18222c",
    });
	return vModalSWA;
		
}

function fnPlantillaSweetAlertParaValidacion(pTitulo,pTablaFinal){
	
	var vModalSWA=Swal.fire({
    	title: pTitulo,
        html: pTablaFinal,
        type:'success',
        confirmButtonText: 'Seleccionar',
        customClass:'swal-wide',
        confirmButtonColor: "#18222c",
    });
	return vModalSWA;	
}


function fnArmarTablaParaSweetAlert(pIdItem){
	var vMetodoSubDt=$('input[name="metodoSubDt"]').val();
	var vLinkSubConsulta=$('input[name="linkSubConsulta"]').val();
	var vObjRequest={
		param1:pIdItem,
	}
	$.ajax({
	        url:vLinkSubConsulta,
	       	type:'POST',
	       	dataType:'JSON',
	        data: JSON.stringify(vObjRequest),
	        contentType:'application/json;charset=utf-8'
		    }).done(function(response){
		        var vIndices=window[vMetodoSubDt]()[1];
		        var vCabecera=window[vMetodoSubDt]()[0];
		        var vTabla=fnArmarTabla(vCabecera,2);
		        var vDivConsulta=$('div[id="tablaAjax"]');
		        vDivConsulta.html('');
		        vDivConsulta.append(vTabla);
		        var vTablaDt=$('table[id="dt_gestion2"]');
		        var vDatatables=vTablaDt.DataTable(fnRetornarConfiguracionDT());
		        console.log(response);
		        for(var b=0;b<response.length;b++){
		            var vArrayValores=[];
		            var vCdMoneda=0;
		            for(var c=0;c<vIndices.length;c++){
						
						if(vIndices[c]=='mtpresupuesto'){
							;
						    vArrayValores.push(
								fnFormatoMonto(parseFloat(response[b][vIndices[c]]),response[b]['cdmonedapresupuesto']
						    	)
						    );
						}else{
						    vArrayValores.push(response[b][vIndices[c]]);
						}								
		                
		            }
		        vDatatables.row.add(vArrayValores).draw(false);
		        }       
		    }).fail(function(a,b,c){
				console.log(a,b,c);
			});
}

function fnValidar(pArray){
	var vContadorErrores=0;
	var vDiv='';
	for(var indice=0;indice<pArray.length;indice++){
		if(pArray[indice]['value']){
			vDiv=$('div[id="'+pArray[indice]['name']+'"]');
			vDiv.html('');
		}else{
			vDiv=$('div[id="'+pArray[indice]['name']+'"]');
			vDiv.html('');
			vDiv.append('El campo est&aacute; vac&iacute;o');
			vContadorErrores=vContadorErrores+1;
		}
	}
	return vContadorErrores;
}
		
function fnValidarValoresRepetidos(pArray,pValor,pIndice){
	var vContadorErrores=0;
	for(var indice=0;indice<pArray.length;indice++){
		if(pArray[indice]['name']==pIndice){
			if(pArray[indice]['value']==pValor){
				var vDiv=$('div[id="'+pArray[indice]['name']+'"]');
				vDiv.html('');
				vDiv.append('El valor est&aacute; repetido en la tabla.');
				vContadorErrores=1;
			}else{
				
			}
		}else{
			
		}
	}
	return vContadorErrores;
}

function fnRetornarValoresHtmlPorFomulario(pNombreFormulario){
	var vFormulario=$('form[name="'+pNombreFormulario+'"]').serializeArray();
	var vHtml='<div class="row">';
	if(vFormulario.length>0){
		for(var indice=0;indice<vFormulario.length;indice++){
			var vElemento=document.getElementsByName(vFormulario[indice]['name']);
			var vLabelName=$('label[id="'+vFormulario[indice]['name']+'"]').html();
			if(vLabelName!='$$'){
				var vValorInput='';
				if(vElemento[0].localName=='input'){
					vValorInput=$(''+vElemento[0].localName+'[id="'+vFormulario[indice]['name']+'"]').val();
				}else{
					vValorInput=$(''+vElemento[0].localName+'[id="'+vFormulario[indice]['name']+'"] option:selected').html();
				}
				//vHtml+='<p>'+'<b>'+vLabelName+'</b>:'+vValorInput+'</p>';
				vHtml+=
				'<div class="col-md-4 mb-1">'+
				    '<div class="card">'+
				    '<span class="badge bg-dark">'+vLabelName+'</span>'+
				   ' <div class="card-body" style="text-align:left;" >'+
				       ' <p class="card-text">'+
				        	vValorInput+
				        '</p>'+
				    '</div>'+
				    '</div>'+
				'</div>';
			}else{
				
			}
		}
	}
	vHtml+='</div>';
	return vHtml;
}
function fnRetornarValoresPorFormulario(pNombreFormulario){
	var vFormulario=$('form[name="'+pNombreFormulario+'"]').serializeArray();
	var vArayRetorno=[];
	if(vFormulario.length>0){
		for(var indice=0;indice<vFormulario.length;indice++){
			var vElemento=document.getElementsByName(vFormulario[indice]['name']);
			var vValorInput='';
			if(vElemento[0].localName=='input'){
				if(vFormulario[indice]['name']=='mtpresupuesto'){
					var vNumeroFormateado='';
					
					var vCdMoneda=$('select[name="cdmonedapresupuesto"] option:selected').val();
					console.log(vCdMoneda);
					if(vCdMoneda!==undefined){
						
					}else{
						vCdMoneda=$('input[name="cdmonedapresupuestoupd"]').val();
					}
					console.log(vCdMoneda);
					vNumeroFormateado=fnFormatoMonto($(''+vElemento[0].localName+'[id="'+vFormulario[indice]['name']+'"]').val(),
					vCdMoneda);
					vValorInput=vNumeroFormateado;
				}else{
					vValorInput=$(''+vElemento[0].localName+'[id="'+vFormulario[indice]['name']+'"]').val();
				}
				
			}else{
				vValorInput=$(''+vElemento[0].localName+'[id="'+vFormulario[indice]['name']+'"] option:selected').html();
			}
			vArayRetorno.push(vValorInput);
		}
	}

	return vArayRetorno;
}
function fnFormatoMonto(pMonto,pCdMoneda){
	var vNumeroFormateado=0;
	if(pCdMoneda==1){
		var vFormatoNumero=Intl.NumberFormat('en-US',
		{
		    style:"currency",
		    currency:"VES",
		    useGrouping:true,
		    maximiumSingnificantDigits:2
	    
		});
		vNumeroFormateado=vFormatoNumero.format(pMonto);
	}
	if(pCdMoneda==2){
		var vFormatoNumero=Intl.NumberFormat('en-US',
		{
		    style:"currency",
		    currency:"USD",
		    useGrouping:true,
		    maximiumSingnificantDigits:2
		    
		});
		vNumeroFormateado=vFormatoNumero.format(pMonto);
	}
	if(pCdMoneda==3){
		var vFormatoNumero=Intl.NumberFormat('en-US',
		{
		    style:"currency",
		    currency:"EUR",
		    useGrouping:true,
		    maximiumSingnificantDigits:2
		    
		});
		vNumeroFormateado=vFormatoNumero.format(pMonto);
	}
	
	
	return vNumeroFormateado;
}

function fnValidaPersona(){
	var vCedulaCliente=$('input[name="txdocumento"]');
	var vDivMensaje=$('div[id="txdocumento"]');
	vDivMensaje.html('');
	if(vCedulaCliente){
		$.ajax({
			url:'/front.funeasistencia/personas.verficarClienteTitular/'+vCedulaCliente.val(),
	    	type:'GET',
		}).done(function(response){

			if(response.length>0){
				vCedulaCliente.val(null);
				vDivMensaje.removeClass('bordeVerificado');
				vDivMensaje.addClass('bordeSinVerificar');
				vDivMensaje.append('El Cliente con documento, '+vCedulaCliente.val()+', ya existe.');	
			}else{
				vDivMensaje.html('');
			}
			
		});
	}
}


