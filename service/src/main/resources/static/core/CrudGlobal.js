function fnConsultar(pId){
	//Hacer Chequeo de Sesion Antes
	var vNombreFormulario=$('input[name="nombreFormulario"]').val();
	
	var vArrayFormulario=$('form[name="'+vNombreFormulario+'"]').serializeArray();
	console.log(vArrayFormulario,vNombreFormulario);
	if(vArrayFormulario.length>0){
		$.ajax({
		    url:'/front.funeasistencia/verificarSesion',
		    type:'GET',
		}).done(function(response){
			
			var vResponse=response.length;
			if(vResponse>0){
				var vObjRequest={};
				for(var indicex=0;indicex<vArrayFormulario.length;indicex++){
					vObjRequest[vArrayFormulario[indicex]['name']]=vArrayFormulario[indicex]['value'];
				}
				
			    var vLinkConsulta=$('input[name="linkConsulta"]').val();
			    var vLinkACtualizar=$('input[name="linkActualizar"]').val();
			    var vMetodoDt=$('input[name="metodoDt"]').val();
			    var vIdPojo=$('input[name="idPojo"]').val();
			    var vSweetAlertEspera='';
			    console.log(vLinkConsulta,vMetodoDt,vObjRequest);
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
					console.log(response);
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
				            vArrayValores.push(response[b][vIndices[c]]);
				        }
				        vArrayValores.push('<a class="btn btn-xs btn-dark" href="'+vLinkACtualizar+'/'+response[b][vIdPojo]+'"><span class="tf-icons bx bx-bookmark-alt-minus"></span> Actualizar</a>' +
					'<button class="btn btn-xs btn-outline-secondary" onclick="fnEliminar('+response[b][vIdPojo]+')"><span class="tf-icons bx bx-trash"></span> Eliminar</button>');
				        var vNodo=vDatatables.row.add(vArrayValores).draw(false);
				        $(vNodo.node()).attr('id',response[b][vIdPojo]);
				    }       
				}).fail(function(a,b,c){
				    console.log(a,b,c);
				});
			   
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
 }
 
 function fnAgregar(){
	console.log('fnAgregar');
	$.ajax({
	    url:'/front.funeasistencia/verificarSesion',
	    type:'GET',
	}).done(function(response){
		var vResponse=response.length;
		if(vResponse>0){
			
		    var vNombreFormulario=$('input[name="nombreFormulario"]').val();
		    var vArrayFormulario=$('form[name="'+vNombreFormulario+'"]').serializeArray();
		    var vObjRequest={};
		    if(vArrayFormulario.length>0){
				if(fnValidarObjectoSiEstaVacio(vArrayFormulario)>0){
					
				}else{
					for(var indice=0;indice<vArrayFormulario.length;indice++){
						vObjRequest[vArrayFormulario[indice]['name']]=vArrayFormulario[indice]['value'];
					}
					var vLinkConsulta=$('input[name="linkAgregar"]').val();
					fnPlantillaSweetAlert('Resumen de Registro',fnRetornarValoresHtmlPorFomulario(vNombreFormulario)).then((response) =>{
						if(response.isConfirmed){
							$.ajax({
								url:vLinkConsulta,
								type:'POST',
								dataType:'JSON',
								data: JSON.stringify(vObjRequest),
								contentType:'application/json;charset=utf-8',
								beforeSend: function() {
									fnPlantillaSweetAlertEnEspera();
								}
							}).done(function(response){
								var vLinkVolver=$('input[name="linkVolver"]').val();
								fnPlantillaSweetAlertMensaje('Se ha realizado el registro.',
													vLinkVolver);
							})
						}else{
								
						}
							
					});
					
				    
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
function fnEliminarAtaudes(pIdItem){
	$.ajax({
	    url:'/front.funeasistencia/verificarSesion',
	    type:'GET',
	}).done(function(response){
		var vResponse=response.length;
		if(vResponse>0){
		    var vIdPojo='cdataud';;
			var vLinkConsulta=$('input[name="linkEliminarAtaudes"]').val();
			var vObjRequest={};
			vObjRequest[vIdPojo]=pIdItem;
			console.log(vObjRequest);
	        fnPlantillaSweetAlert('Informacíon','¿Desea eliminar el registro?').then((response) =>{
	            if(response.isConfirmed){
	                $.ajax({
	                    url:vLinkConsulta,
	                    type:'POST',
	                    dataType:'JSON',
	                    data: JSON.stringify(vObjRequest),
	                    contentType:'application/json;charset=utf-8',
	                    beforeSend: function() {
							fnPlantillaSweetAlertEnEspera();
						}
	                }).done(function(response){
						var vLinkVolver=$('input[name="linkVolver"]').val();
	                    fnPlantillaSweetAlertMensaje('Se ha eliminado el registro.',
													vLinkVolver);
	                }).fail(function(a,b,c){
						console.log(a,b,c);
					})
	            }else{
	                    
	            }
	                
	        });	
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
function fnEliminarServicios(pIdItem){
	$.ajax({
	    url:'/front.funeasistencia/verificarSesion',
	    type:'GET',
	}).done(function(response){
		var vResponse=response.length;
		if(vResponse>0){
		    var vIdPojo='cdtiposerviciooficina';
			var vLinkConsulta=$('input[name="linkEliminarServicios"]').val();
			var vObjRequest={};
			vObjRequest[vIdPojo]=pIdItem;
			console.log(vObjRequest);
	        fnPlantillaSweetAlert('Informacíon','¿Desea eliminar el registro?').then((response) =>{
	            if(response.isConfirmed){
	                $.ajax({
	                    url:vLinkConsulta,
	                    type:'POST',
	                    dataType:'JSON',
	                    data: JSON.stringify(vObjRequest),
	                    contentType:'application/json;charset=utf-8',
	                    beforeSend: function() {
							fnPlantillaSweetAlertEnEspera();
						}
	                }).done(function(response){
						var vLinkVolver=$('input[name="linkVolver"]').val();
	                    fnPlantillaSweetAlertMensaje('Se ha eliminado el registro.',
													vLinkVolver);
	                }).fail(function(a,b,c){
						console.log(a,b,c);
					})
	            }else{
	                    
	            }
	                
	        });	
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
function fnEliminarCapillas(pIdItem){
	$.ajax({
	    url:'/front.funeasistencia/verificarSesion',
	    type:'GET',
	}).done(function(response){
		var vResponse=response.length;
		if(vResponse>0){
		    var vIdPojo='cdcapilla';
			var vLinkConsulta=$('input[name="linkEliminarCapillas"]').val();
			var vObjRequest={};
			vObjRequest[vIdPojo]=pIdItem;
			console.log(vObjRequest);
	        fnPlantillaSweetAlert('Informacíon','¿Desea eliminar el registro?').then((response) =>{
	            if(response.isConfirmed){
	                $.ajax({
	                    url:vLinkConsulta,
	                    type:'POST',
	                    dataType:'JSON',
	                    data: JSON.stringify(vObjRequest),
	                    contentType:'application/json;charset=utf-8',
	                    beforeSend: function() {
							fnPlantillaSweetAlertEnEspera();
							}
	                }).done(function(response){
						var vLinkVolver=$('input[name="linkVolver"]').val();
	                    fnPlantillaSweetAlertMensaje('Se ha eliminado el registro.',
													vLinkVolver);
	                }).fail(function(a,b,c){
						console.log(a,b,c);
					})
	            }else{
	                    
	            }
	                
	        });	
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
function fnEliminar(pIdItem){
	$.ajax({
	    url:'/front.funeasistencia/verificarSesion',
	    type:'GET',
	}).done(function(response){
		var vResponse=response.length;
		if(vResponse>0){
		    var vIdPojo=$('input[name="idPojo"]').val();
			var vLinkConsulta=$('input[name="linkEliminar"]').val();
			var vObjRequest={};
			vObjRequest[vIdPojo]=pIdItem;
			console.log(vObjRequest);
	        fnPlantillaSweetAlert('Informacíon','¿Desea eliminar el registro?').then((response) =>{
	            if(response.isConfirmed){
	                $.ajax({
	                    url:vLinkConsulta,
	                    type:'POST',
	                    dataType:'JSON',
	                    data: JSON.stringify(vObjRequest),
	                    contentType:'application/json;charset=utf-8',
	                    beforeSend: function() {
							fnPlantillaSweetAlertEnEspera();
						}	
	                }).done(function(response){
						var vLinkVolver=$('input[name="linkVolver"]').val();
	                    fnPlantillaSweetAlertMensaje('Se ha eliminado el registro.',
													vLinkVolver);
	                }).fail(function(a,b,c){
						console.log(a,b,c);
					})
	            }else{
	                    
	            }
	                
	        });	
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
function fnValidarObjectoSiEstaVacio(vArrayValidacion){
	var vValidacion=0;
	for(var indice=0;indice<vArrayValidacion.length;indice++){
		var vValor=vArrayValidacion[indice]['value'].trim();
		var vNombre=vArrayValidacion[indice]['name'];
		var vDiv=$('div[id="'+vNombre+'"]');
		if(vValor){
			
			vDiv.html('');
		}else{
			console.log(vNombre);
			vValidacion+=1;
			vDiv.html('');
			vDiv.append('El campo est&aacute;o vac&iacute;o.');
		}
	}
	return vValidacion;
}
function fnEliminarProveedores(pIdItem){
	$.ajax({
	    url:'/front.funeasistencia/verificarSesion',
	    type:'GET',
	}).done(function(response){
		var vResponse=response.length;
		if(vResponse>0){
		    var vIdPojo='cdproveedor';
			var vLinkConsulta=$('input[name="linkEliminarProveedor"]').val();
			var vObjRequest={};
			vObjRequest[vIdPojo]=pIdItem;
			console.log(vObjRequest);
	        fnPlantillaSweetAlert('Informacíon','¿Desea eliminar el registro?').then((response) =>{
	            if(response.isConfirmed){
	                $.ajax({
	                    url:vLinkConsulta,
	                    type:'POST',
	                    dataType:'JSON',
	                    data: JSON.stringify(vObjRequest),
	                    contentType:'application/json;charset=utf-8',
	                    beforeSend: function() {
							fnPlantillaSweetAlertEnEspera();
						}
	                }).done(function(response){
						var vLinkVolver=$('input[name="linkVolver"]').val();
	                    fnPlantillaSweetAlertMensaje('Se ha eliminado el registro.',
													vLinkVolver);
	                }).fail(function(a,b,c){
						console.log(a,b,c);
					})
	            }else{
	                    
	            }
	                
	        });	
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