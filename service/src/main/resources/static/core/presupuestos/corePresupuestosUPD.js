var vObjetoValores={};
var vArrayGlobal=[];
fnDarFormatoTabla();
function fnDarFormatoTabla(){
	var vDatatable=$('table[id="dt_gestion1"]');
	var dt=vDatatable.DataTable(fnRetornarConfiguracionSubTotalDT());
}
function fnAgregarObjetosUpd(){
	var vTipoServicio=$('select[name="cdtiposervicio"] option:selected').val();
	vValorForm=$('form[name="formPresupuestos2"]').serializeArray();
	var vDiv=$('div[id="cdtiposervicio"]');
	var vMontoTasa=$('input[name="mttasa"]').val();
	var vCdMoneda=$('input[name="cdmonedapresupuestoupd"]').val();
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
				if(vTipoServicio){
					var vMoneda=$('select[name="cdmoneda"] option:selected').val();
					var vTipoPago=$('select[name="cdtipopago"] option:selected').val();
					var vMtPresupuesto=$('input[name="mtpresupuesto"]').val();
					var vMontoAlCambio=parseFloat(vMontoTasa)*parseFloat(vMtPresupuesto);
           		 	vObjetoValores={"cdtiposervicio":vTipoServicio,
           		 				"cdmoneda":vMoneda,
           		 				"mtpresupuesto":vMtPresupuesto,
           		 				"cdtipopago":vTipoPago,
           		 				"mtalcambio":vMontoAlCambio
           		 				}
            		vArrayGlobal.push(vObjetoValores);
	            	var vValoresSelect=[];
	            	vValoresSelect=fnRetornarValoresPorFormulario('formPresupuestos2');
	            	var vDatatable=$('table[id="dt_gestion1"]');
			        var dt=vDatatable.DataTable();
			        vValoresSelect.push(fnFormatoMonto(parseFloat(vMontoAlCambio),1));
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
					vPtotalDs.html(fnFormatoMonto(vTotalDs,vCdMoneda));
        		}
		       
			}else{
				 
		         vDiv.html('');
		         vDiv.append('El servicio est&aacute; repetido en el tabla.');
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
    var vMontoTasa=$('input[name="mttasa"]').val();
	var vCdMoneda=$('input[name="cdmonedapresupuestoupd"]').val();
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
	var vPtotalBs=$('p[id="totalBs"]');
	vPtotalBs.html('');
	vPtotalBs.html(fnFormatoMonto(vTotalBs,1));
	var vPtotalDs=$('p[id="totalDs"]'); 
	vPtotalDs.html('');
	vPtotalDs.html(fnFormatoMonto(vTotalDs,vCdMoneda));  
}
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
	console.log(vCdMoneda.val());
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
fnCargarFormulario();
function fnCargarFormulario(){
	var vValorForm=$('form[name="formPresupuestos3"]').serializeArray();
	var vMontoTasa=$('input[name="mttasa"]').val();
	var vCdMoneda=$('input[name="cdmonedapresupuestoupd"]').val();
	console.log(vValorForm,vMontoTasa);
	for(var indice=0;indice<vValorForm.length;indice++){
		if(vValorForm[indice]['name']=='cdmoneda2' || vValorForm[indice]['name']=='cdtipopago2' || vValorForm[indice]['name']=='mtpresupuesto' ){
			if(vValorForm[indice]['name']=='cdmoneda2'){
				vObjetoValores['cdmoneda']=vValorForm[indice]['value'];
			}else{
				if(vValorForm[indice]['name']=='mtpresupuesto'){
					vObjetoValores['mtpresupuesto']=parseFloat(vValorForm[indice]['value'].replace('$','').replace('€','').replace('.','').trim());
					vObjetoValores['mtalcambio']=parseFloat(vValorForm[indice]['value'].replace('$','').replace('€','').replace('.','').trim())*(parseFloat(vMontoTasa));
				}else{
					vObjetoValores['cdtipopago']=vValorForm[indice]['value'];
				}
				
			}
		}else{
			vObjetoValores[vValorForm[indice]['name']]=vValorForm[indice]['value'];
		}
		if((indice+1)%5==0){
			vArrayGlobal.push(vObjetoValores);
			vObjetoValores={};
		}
	}
	
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
	vPtotalDs.html(fnFormatoMonto(vTotalDs,vCdMoneda));

}
function fnEliminarServicioPresupuesto(pIdItem){
	var cdPresupuesto=$('input[name="cdpresupuesto"]').val();
	$.ajax({
	    url:'/front.funeasistencia/verificarSesion',
	    type:'GET',
	}).done(function(response){
		var vResponse=response.length;
		if(vResponse>0){
		    var vIdPojo='cdpresupuestoservicio';
			var vLinkConsulta='/front.funeasistencia/presupuestosServicios.eliminar';
			var vObjRequest={};
			vObjRequest[vIdPojo]=pIdItem;
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
	                    fnPlantillaSweetAlertMensaje('Se ha eliminado el registro.',
													'/front.funeasistencia/presupuestos.indice.actualizar/'+cdPresupuesto);
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
function fnActualizarServicios(){
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
							console.log(vObjPresupuestos);
							$.ajax({
								url:'/front.funeasistencia/presupuestos.actualizar',
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