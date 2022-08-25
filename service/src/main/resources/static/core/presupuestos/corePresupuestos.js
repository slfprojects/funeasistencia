var vObjetoValores={};
var vArrayGlobal=[];

function fnBuscarCliente(){
	var vCedulaCliente=
	$('input[name="txdocumentotitular"]');
	$('input[name="cdpersonatitular"]').val(null);
	
	var vDivMensaje=
	$('div[id="txdocumentotitular"]');
	vDivMensaje.html('');
	if(vCedulaCliente){
		$.ajax({
			url:'/front.funeasistencia/presupuestos.verficarClienteTitular/'+vCedulaCliente.val(),
	    	type:'GET',
		}).done(function(response){
		
			if(response.length>0){
				var vHtmlSWA='<div style="text-align:left; font-size:15px;">'+
					'<p><b>Nombre del Cliente :</b> '+response[0]['txnombres']+'</p>'+
					'<p><b>Documento del Cliente :</b> '+response[0]['tpdocumento']+'-'+response[0]['txdocumento']+'</p>'+
					'<p><b>Telefono del Cliente :</b> '+response[0]['txtelefono1']+'</p>'+
					'<p><b>Correo del Cliente :</b> '+response[0]['txcorreo']+'</p>'+
				'</div>';
				var vModalSWA=fnPlantillaSweetAlertParaValidacion('Ventana de B&uacute;squeda de Cliente',vHtmlSWA);
				vModalSWA.then((result)=> {
					if(result.isConfirmed){
						
						$('input[name="cdpersonatitular"]').val(response[0]['cdpersona']);
						vDivMensaje.removeClass('bordeSinVerificar');
						vDivMensaje.addClass('bordeVerificado');
						vDivMensaje.append('El cliente es v&aacute;lido.');
						
					}
				});
					
			}else{
				$('input[name="txdocumentotitular"]').val(null);
				vDivMensaje.removeClass('bordeVerificado');
				vDivMensaje.addClass('bordeSinVerificar');
				vDivMensaje.append('El cliente '+vCedulaCliente.val()+' no es v&aacute;lido.');
			}
			
		});
	}
}
function fnBuscarContatratacion(){
	var vContratacion=
	$('select[name="cdtipocontratacion"] option:selected');
	if(vContratacion.val()){
		$.ajax({
			url:'/front.funeasistencia/presupuestos.buscarContratacion/'+vContratacion.val(),
	    	type:'GET',
		}).done(function(response){
			
			if(response.length>0){
				
				var vOptions='';
				for(var indice=0;indice<response.length;indice++){
					vOptions+=
					'<option value="'+response[indice]['cdcontrataciondetalle']+'">'
					+response[indice]['txcontrataciondetalle']+
					'</option>';
				}
				var vSelect='<div class="col-md-12">'+
					'<div class="mb-1">'+
						'<label class="form-label" id="cdcontrataciondetalle">'+vContratacion.html()+'</label>'+
						'<select class="form-select" id="cdcontrataciondetalle"'+
							'aria-label="Default select example" name="cdcontrataciondetalle">'+
							vOptions+
						'</select>'+
					'</div>'+
				'</div>';
				var vDivInputAlterno=$('div[id="cdtipocontrataciondetalle"]');
				vDivInputAlterno.html('');
				vDivInputAlterno.append(vSelect);
				
					
			}else{
				
			}
			
		});
	}
}
function fnArmadoTabla(){
    var vDiv=$('div[id="dtconsulta"]');
    var vCabecera=window['fnConfiguracionServicios2']()[0];
    var vTabla=fnArmarTabla(vCabecera,1);
    vDiv.html('');
    vDiv.append(vTabla);
    var vDatatable=$('table[id="dt_gestion1"]');
    vDatatable.DataTable(fnRetornarConfiguracionSubTotalDT());
}

function fnValidarVista(){
	var vIdPojo=$('input[name="idPojo"]').val();
	if(vIdPojo){
		
	}else{
		fnArmadoTabla();
	}
	
}
fnValidarVista();


function fnAgregarObjetos(){
	console.log("prueba");
	var vTipoServicio=$('select[name="cdtiposervicio"] option:selected').val();
	vValorForm=$('form[name="formPresupuestos2"]').serializeArray();
	var vDiv=$('div[id="cdtiposervicio"]');
	var vCdMoneda=$('select[name="cdmonedapresupuesto"] option:selected');
	vDiv.html('');
	if(vValorForm.length>0){
		var vValidacionVacios=fnValidar(vValorForm);
		if(vValidacionVacios>0){
			
		}else{
			var vValidacionRepetidos=0;
			if(vArrayGlobal.length>0){
	        	for (var index = 0; index < vArrayGlobal.length; index++) {
		            if(vArrayGlobal[index]['cdtiposervicio']==vTipoServicio){
		                vValidacionRepetidos=1;
		               
		            }
		        }
	        }
	        if(vValidacionRepetidos==0){
		
			console.log("prueba");
				if(vTipoServicio){
					var vMoneda=$('select[name="cdmoneda"] option:selected').val();
					var vTipoPago=$('select[name="cdtipopago"] option:selected').val();
					var vMtPresupuesto=$('input[name="mtpresupuesto"]').val();
					var vAlCambio=vMtPresupuesto*parseFloat($('input[name="mt_tasa_dia"]').val());
           		 	vObjetoValores={"cdtiposervicio":vTipoServicio,
           		 				"cdmoneda":vMoneda,
           		 				"mtpresupuesto":vMtPresupuesto,
           		 				"cdtipopago":vTipoPago,
           		 				"mtalcambio":vAlCambio
           		 				}
            		vArrayGlobal.push(vObjetoValores);
	            	var vValoresSelect=[];
	            	vValoresSelect=fnRetornarValoresPorFormulario('formPresupuestos2');
	            	var vDatatable=$('table[id="dt_gestion1"]');
			        var dt=vDatatable.DataTable();
			        vValoresSelect.push(fnFormatoMonto(parseFloat(vAlCambio),1));
			        vValoresSelect.push('<button class="btn btn-xs btn-outline-secondary" onclick="fnEliminarRegistroArray('+vTipoServicio+')"><span class="tf-icons bx bx-trash"></span> Eliminar </button>');
			        var vNodo=dt.row.add(vValoresSelect
			            ).draw(true);
			        $(vNodo.node()).attr('id',vTipoServicio);
			        
			        var vTotalBs=0;
			        var vTotalDs=0;
			        for(var indice2=0;indice2<vArrayGlobal.length;indice2++){
						vTotalBs=parseFloat(vArrayGlobal[indice2]['mtalcambio'])+vTotalBs;
						vTotalDs=parseFloat(vArrayGlobal[indice2]['mtpresupuesto'])+vTotalDs;
						
					}
					var vPtotalBs=$('p[id="totalBs"]');
					vPtotalBs.html('');
					vPtotalBs.html(fnFormatoMonto(vTotalBs,1));
					var vPtotalDs=$('p[id="totalDs"]'); 
					vPtotalDs.html('');
					vPtotalDs.html(fnFormatoMonto(vTotalDs,vCdMoneda.val()));
					console.log("prueba");
        		}
		       
			}else{
				 
		         vDiv.html('');
		         vDiv.append('El servicio ya fue elegido en el presupuesto.');
			}
    	}
	}else{
		
	} 
}
function fnEliminarRegistroArray(pIndice){
    var vTr=$('tr[id="'+pIndice+'"]');
    var vDatatable=$('table[id="dt_gestion1"]');
    var dt=vDatatable.DataTable();
    dt.row('#'+pIndice).remove();
    vTr.remove();
    vNumeroIndice=0;
    for (var index = 0; index < vArrayGlobal.length; index++) {
        
        if(vArrayGlobal[index]['cdtiposervicio']==pIndice){
            vNumeroIndice=index;
        }
    }
    vArrayGlobal.splice(vNumeroIndice,1); 
    var vTotalBs=0;
	var vTotalDs=0;
	for(var indice2=0;indice2<vArrayGlobal.length;indice2++){
	    vTotalBs=parseFloat(vArrayGlobal[indice2]['mtalcambio'])+vTotalBs;
	    vTotalDs=parseFloat(vArrayGlobal[indice2]['mtpresupuesto'])+vTotalDs;
	}
	var vCdMoneda=$('select[name="cdmonedapresupuesto"] option:selected');
	var vPtotalBs=$('p[id="totalBs"]');
	vPtotalBs.html('');
	vPtotalBs.html(fnFormatoMonto(vTotalBs,1));
	var vPtotalDs=$('p[id="totalDs"]'); 
	vPtotalDs.html('');
	vPtotalDs.html(fnFormatoMonto(vTotalDs,vCdMoneda.val()));  
}

function fnAgregarPresupuestos(){
	$.ajax({
	    url:'/front.funeasistencia/verificarSesion',
	    type:'GET',
	}).done(function(response){
		var vResponse=response.length;
		if(vResponse>0){
			var vValorForm=$('form[name="formPresupuestos"]').serializeArray();
			if(vValorForm.length>0 && vArrayGlobal.length>0){
				var vValidacionVacios=parseInt(fnValidar(vValorForm))+parseInt(fnValidar(vValorForm));
				if(vValidacionVacios==0){
					var vValoresHtmlForm1=fnRetornarValoresHtmlPorFomulario('formPresupuestos');
					var vValoresTablaServicios=$('table[id="dt_gestion1"]').prop('outerHTML');
					var vValorTablaTotales=$('table[id="dt_servicios1"]').prop('outerHTML');
					fnPlantillaSweetAlert('Resumen Final Presupuesto','<div style="text-align:left; font-size:15px;">'+vValoresHtmlForm1+'</div><hr> Presupuestos '+vValoresTablaServicios+vValorTablaTotales)
					.then((result)=>{
						if(result.isConfirmed){
							var vObjPresupuestos={};
							for(var indice=0;indice<vValorForm.length;indice++){
								vObjPresupuestos[vValorForm[indice]['name']]=vValorForm[indice]['value'];
							}
							vObjPresupuestos['presupuestoservicio']=vArrayGlobal;
							
							$.ajax({
								url:'/front.funeasistencia/presupuestos.insercion',
			    				type:'POST',
								dataType:'JSON',
								data: JSON.stringify(vObjPresupuestos),
								contentType:'application/json;charset=utf-8',
								beforeSend: function() {
									fnPlantillaSweetAlertEnEspera();
								}
							}).done(function(response){
								fnPlantillaSweetAlertMensaje('Se ha realizado el registro.',
													'/front.funeasistencia/presupuestos.indice');
							}).fail(function(a,b,c){
								console.log(a,b,c);
							});
						}
					});
					
				}
			}else{
				var vValidacionVacios=parseInt(fnValidar(vValorForm))+parseInt(fnValidar(vValorForm));
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
function fnConversionServicio(pCdItem){
	var vObjPresupuestos={
		cdpresupuesto:pCdItem,
		stpresupuesto:2,
		txcausaanulacion:'N/A'
	}
	Swal.fire({
    	title: 'Generar Servicio',
        html: '<p> ¿Est&aacute; seguro de Generar el Servicio?</p>',
        type:'success',
        showDenyButton: true,	
        confirmButtonText: 'Aplicar',
        denyButtonText: 'Volver',
        customClass:'swal-wide',
        confirmButtonColor: "#18222c",
    }).then((result)=>{
		if(result.isConfirmed){
			$.ajax({
			url:'/front.funeasistencia/presupuestos.generarServicio',
		    type:'POST',
			dataType:'JSON',
			data: JSON.stringify(vObjPresupuestos),
			contentType:'application/json;charset=utf-8',
			beforeSend: function() {
				fnPlantillaSweetAlertEnEspera();
			}
			}).done(function(response){
				
				fnPlantillaSweetAlertMensaje('Se ha generado el servicio.',
													'/front.funeasistencia/servicios.indice');
				
			}).fail(function(a,b,c){
				console.log(a,b,c);
			});
		}else{
			fnLlenarSweetAlertsSinDobleTabla(pCdItem);
			//fnLlenarSweetAlertsConsulta(pCdItem);
		}
	});
}
function fnCerrarServicio(pCdItem){
	var vObjPresupuestos={
		cdpresupuesto:pCdItem,
		stpresupuesto:4,
		txcausaanulacion:'N/A'
	}
	Swal.fire({
    	title: 'Cerrar Servicio',
        html: '<p> ¿Est&aacute; seguro de Cerrar el Servicio?</p>',
        type:'success',
        showDenyButton: true,	
        confirmButtonText: 'Aplicar',
        denyButtonText: 'Volver',
        customClass:'swal-wide',
        confirmButtonColor: "#18222c",
    }).then((result)=>{
		if(result.isConfirmed){
			$.ajax({
			url:'/front.funeasistencia/presupuestos.generarServicio',
		    type:'POST',
			dataType:'JSON',
			data: JSON.stringify(vObjPresupuestos),
			contentType:'application/json;charset=utf-8',
			beforeSend: function() {
				fnPlantillaSweetAlertEnEspera();
			}
			}).done(function(response){

				fnPlantillaSweetAlertMensaje('Se ha generado el servicio.',
													'/front.funeasistencia/servicios.indice');
				
			}).fail(function(a,b,c){
				console.log(a,b,c);
			});
		}else{
			fnLlenarSweetAlertsSinDobleTabla(pCdItem);
			//fnLlenarSweetAlertsConsulta(pCdItem);
		}
	});
}
function fnRechazarServicio(pCdItem){
	Swal.fire({
    	title: 'Rechazo de Presupuesto',
        html: '<p> ¿Est&aacute; seguro de Recharzar el Presupuesto?</p> <br> Causa Anulacion:<input class="form-control col-sm-3" type="text" required name="txcausaanulacion">'+
        '</div><div id="txcausaanulacion" class="form-text bordeSinVerificar"></div>',
        type:'success',
        showDenyButton: true,	
        confirmButtonText: 'Aplicar',
        denyButtonText: 'Volver',
        customClass:'swal-wide',
        confirmButtonColor: "#18222c",
    }).then((result)=>{
		if(result.isConfirmed){
			var vCausaAnulacion=$('input[name="txcausaanulacion"]').val();
			if(vCausaAnulacion){
				var vObjPresupuestos={
				cdpresupuesto:pCdItem,
				stpresupuesto:3,
				txcausaanulacion:vCausaAnulacion,
				
			}
			$.ajax({
				url:'/front.funeasistencia/presupuestos.generarServicio',
			    type:'POST',
				dataType:'JSON',
				data: JSON.stringify(vObjPresupuestos),
				contentType:'application/json;charset=utf-8',
				beforeSend: function() {
					fnPlantillaSweetAlertEnEspera();
				}
			}).done(function(response){
				fnPlantillaSweetAlertMensaje('Se ha rechazado el presupuesto.',
													'/front.funeasistencia/servicios.indice');
					
				
			}).fail(function(a,b,c){
				console.log(a,b,c);
			});
			}else{
				var vMensaje=$('div[id="txcausaanulacion"]');
				vMensaje.html('');
				vMensaje.append('Se debe llenar el campo para proceder a rechar el presupuesto.');
				fnRechazarServicio(pCdItem);
			}
			
		}else{
			fnLlenarSweetAlertsSinDobleTabla(pCdItem);
			//fnLlenarSweetAlertsConsulta(pCdItem);
		}
		
		
	});
}
function fnReversoServicio(pCdItem){
	var vObjPresupuestos={
		cdpresupuesto:pCdItem,
		stpresupuesto:1,
		txcausaanulacion:'N/A'
	}
	Swal.fire({
    	title: 'Reverso de Presupuesto',
        html: '<p> ¿Est&aacute; seguro de Reversar el Servicio?</p>',
        type:'success',
        showDenyButton: true,	
        confirmButtonText: 'Aplicar',
        denyButtonText: 'Volver',
        customClass:'swal-wide',
        confirmButtonColor: "#18222c",
    }).then((result)=>{
		if(result.isConfirmed){
			$.ajax({
			url:'/front.funeasistencia/presupuestos.generarServicio',
		    type:'POST',
			dataType:'JSON',
			data: JSON.stringify(vObjPresupuestos),
			contentType:'application/json;charset=utf-8',
			beforeSend: function() {
					fnPlantillaSweetAlertEnEspera();
				}
			}).done(function(response){
				fnPlantillaSweetAlertMensaje('Se ha reversado el servicio.','/front.funeasistencia/presupuesto.indice');
					
			}).fail(function(a,b,c){
				console.log(a,b,c);
			});
		}else{
			fnLlenarSweetAlertsSinDobleTabla(pCdItem);
			//fnLlenarSweetAlertsConsulta(pCdItem);
		}
	});
}
function fnBuscarClienteFallecido(){
	var vCedulaCliente=
	$('input[name="txdocumentofallecido"]');
	var vDivMensaje=$('div[id="txdocumentofallecido"]');
	vDivMensaje.html('');
	if(vCedulaCliente){
		$.ajax({
			url:'/front.funeasistencia/presupuestos.verficarClienteFallecido/'+vCedulaCliente.val(),
	    	type:'GET',
		}).done(function(response){
			
			if(response.length>0){
				vCedulaCliente.val(null);
				vDivMensaje.removeClass('bordeVerificado');
				vDivMensaje.addClass('bordeSinVerificar');
				vDivMensaje.append('El cliente '+vCedulaCliente.val()+' ya posee un presupuesto o servicio.');	
					
			}else{
				vDivMensaje.removeClass('bordeSinVerificar');
				vDivMensaje.addClass('bordeVerificado');
				
				vDivMensaje.append('El cliente es v&aacute;lido.');
			}
			
		});
	}
}
function fnMostrarDetalleEnTexto(pIdItem){
	
	var vLinkConsulta=$('input[name="linkSegundaFase"]').val();
	var vIndices=fnConfiguracionPresupuestoAlterno()[1];
	var vCabecera=fnConfiguracionPresupuestoAlterno()[0];
	var vObjRequest={
		param1:pIdItem,
	}
	
	 $.ajax({
		url:vLinkConsulta,
		type:'POST',
		dataType:'JSON',
		data: JSON.stringify(vObjRequest),
		contentType:'application/json;charset=utf-8'
	}).done(function(response){
		
		var vHtml='<div class="row">';

		for(var h=0;h<vCabecera.length;h++){
			
			vHtml+=
			'<div class="col-md-4 mb-1">'+
			    '<div class="card">'+
			    '<span class="badge bg-dark">'+vCabecera[h]+'</span>'+
			   ' <div class="card-body" style="text-align:left;" >'+
			       ' <p class="card-text">'+
			        	response[vIndices[h]]+
			        '</p>'+
			    '</div>'+
			    '</div>'+
			'</div>';
			//vHtml+='<div class="col-md-4" ><b>'+vCabecera[h]+'</b>: <br>'+response[vIndices[h]]+'</div>';
		}
		
						
		
		vHtml+='</div>';
		vTextoPrincipal= $('div[id="textoPrincipal"]');
		vTextoPrincipal.html('');
		vTextoPrincipal.append(vHtml);
		
	});
	
	
}

/* function fnLlenarSweetAlertsSinDobleTabla(pIdItem){
	var vLinkAgregar=$('input[name="linkAgregar"]').val();
	var vIdPojo=$('input[name="idPojo"]').val();
	var vDivisor='<div style="text-align:left;"><span class="badge bg-info">$titulo$</span></div><br>';
	var vBotones='';
	if(vIdPojo=='cdpresupuesto'){
		if(vLinkAgregar){
			vBotones='<center><button class="btn btn-sm btn-dark" onclick="fnConversionServicio('+pIdItem+')">Convertir en Servicio</button>'+
			'<button class="btn btn-sm btn-outline-secondary" onclick="fnRechazarServicio('+pIdItem+')">Rechazar</button> </center>';
		}else{
			vBotones='<br><center><button class="btn btn-sm btn-dark" onclick="fnReversoServicio('+pIdItem+')">Reversar Servicio</button>';
		}
		
	}
	var vTablaFinal=
	vDivisor.replace('$titulo$','Detalle')+'<div id="textoPrincipal" style="font-size:14px" ></div> <hr>'+vDivisor.replace('$titulo$','Resumen')+' <br> <div id="tablaAjax" style="font-size:14px;"></div> <br>'+vBotones;
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
			
	
}*/

function fnBuscarMoneda(){
	var vCdMoneda=$('select[name="cdmoneda"] option:selected');

	if(vCdMoneda.val()){
		$.ajax({
			url:'/front.funeasistencia/tipopago.consulta.moneda/'+vCdMoneda.val(),
			type:'GET'
		}).done(function(response){
			if(response.length>0){
				var vTipoPago=$('select[name="cdtipopago"]');
				vTipoPago.html('');
				var vOptions='<option value=""></option>';
				for(var i=0;i<response.length;i++){
					vOptions+='<option value="'+response[i]['cdtipopago']+'">'+response[i]['txtipopago']+'</option>';
				}
				vTipoPago.append(vOptions);
			}
		});
	}
	
	
}
function fnBuscarMoneda2(){
	var vCdMoneda=$('select[name="cdmoneda2"] option:selected');

	if(vCdMoneda.val()){
		$.ajax({
			url:'/front.funeasistencia/tipopago.consulta.moneda/'+vCdMoneda.val(),
			type:'GET'
		}).done(function(response){
			if(response.length>0){
				var vTipoPago=$('select[name="cdtipopago2"]');
				vTipoPago.html('');
				var vOptions='<option value=""></option>';
				for(var i=0;i<response.length;i++){
					vOptions+='<option value="'+response[i]['cdtipopago']+'">'+response[i]['txtipopago']+'</option>';
				}
				vTipoPago.append(vOptions);
			}
		});
	}
	
	
}

function fnLlenarMtTasa (){
	var vCdMoneda=$('select[name="cdmonedapresupuesto"] option:selected');

	if(vCdMoneda.val()){
		$.ajax({
			url:'/front.funeasistencia/presupuestos.tasacambio/'+vCdMoneda.val(),
			type:'GET'
		}).done(function(response){
	
			if(response.length>0){
				var vTipoPago=$('input[name="mt_tasa_dia"]');
				vTipoPago.val(null);
				vTipoPago.val(response[0]['mttasa']);
			}
		});
	}
}