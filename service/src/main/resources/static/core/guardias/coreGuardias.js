var vObjetoValores={};
var vArrayGlobal=[];

function fnAgregarObjetos(){
	var vValorSelect=$('select[name="cdpersona"] option:selected');
    var vCdPersona=vValorSelect.val();
    console.log(vCdPersona);
    var vValidacion=0;
    if(vArrayGlobal.length>0){
        for (var index = 0; index < vArrayGlobal.length; index++) {
            if(vArrayGlobal[index]['cdpersona']==vCdPersona){
                vValidacion=1;
            }
        }
    }
    if(vValidacion==0){
        if(vValorSelect){
            vObjetoValores={cdpersona:vCdPersona}
            vArrayGlobal.push(vObjetoValores);
        }
        
        
        var vDatatable=$('table[id="dt_gestion1"]');
        var dt=vDatatable.DataTable();
        var vValoresSelect=vValorSelect.html().split(",");
        vValoresSelect.push('<button  class="btn btn-xs btn-outline-secondary" onclick="fnEliminarRegistroArray('+vCdPersona+')"><span class="tf-icons bx bx-trash"></span> Eliminar </button>');
        var vNodo=dt.row.add(vValoresSelect
            ).draw(true);
        $(vNodo.node()).attr('id',vCdPersona);
    }
}

function fnArmadoTabla(){
	var vMetodoDt=$('input[name="metodoDt"]').val();
    var vDiv=$('div[id="dtconsulta1"]');
    var vCabecera=window[vMetodoDt]()[0];
    var vTabla=fnArmarTabla(vCabecera,1);
    console.log(vTabla);
    vDiv.html('');
    vDiv.append(vTabla);
    var vDatatable=$('table[id="dt_gestion1"]');
    vDatatable.DataTable(fnRetornarConfiguracionDT());
}

function fnEliminarRegistroArray(pIndice){
    var vTr=$('tr[id="'+pIndice+'"]');
    var vDatatable=$('table[id="dt_gestion1"]');
    var dt=vDatatable.DataTable();
    dt.row('#'+pIndice).remove();
    vTr.remove();
    vNumeroIndice=0;
    for (var index = 0; index < vArrayGlobal.length; index++) {
        
        if(vArrayGlobal[index]['cdpersona']==pIndice){
            vNumeroIndice=index;
        }
    }
    vArrayGlobal.splice(vNumeroIndice,1);   
}
function fnAgregar(){
	//Verificar Sesion
	$.ajax({
	    url:'/front.funeasistencia/verificarSesion',
	    type:'GET',
	}).done(function(response){
		var vResponse=response.length;
		if(vResponse>0){
			if(vArrayGlobal.length>0 ){
				var objGuardia={
					stguardia:1,
					guardiaIntegrantes:vArrayGlobal
				}
				var vTabla=$('table[id="dt_gestion1"]').prop('outerHTML');
				fnPlantillaSweetAlert('Resumen de las Guardias',vTabla).then((result) => {
		        	if (result.isConfirmed) {
							$.ajax({
								url:'/front.funeasistencia/guardias.validarExistencia',
						       	type:'GET',
							}).done(function(response2){
								console.log(response2);
								if((response2.length>0)){
									var vValidacion=response2[0][0];
									if(vValidacion==0){
										var vTablaSecundaria=
										'<p>Hace menos de 6 horas se ha registrado una <b>Guardia</b> por <b>'+response2[0][1]+'</b></p>';
										fnPlantillaSweetAlert('Importante',vTablaSecundaria).then((result) => {
											console.log(objGuardia);
											if(result.isConfirmed) {
												$.ajax({
													url:'/front.funeasistencia/guardias.cerrarGuardia',
												       type:'GET',
												}).done(function(response){
														
												});
												 $.ajax({
													url:'/front.funeasistencia/guardias.insercion',
											       	type:'POST',
											       	dataType:'JSON',
											        data: JSON.stringify(objGuardia),
											        contentType:'application/json;charset=utf-8',
											       	beforeSend: function() {
											       		fnPlantillaSweetAlertEnEspera();
											    	}
												}).done(function(response){
													console.log(response);
													fnPlantillaSweetAlertMensaje('Mensaje final guardia.',
													'/front.funeasistencia/guardias.indice');
													
													
												}).fail(function(a,b,c){
													console.log(a,b,c);
												});
											}
												 
											
										});
										
									}else{
										$.ajax({
											url:'/front.funeasistencia/guardias.insercion',
											type:'POST',
											dataType:'JSON',
											data: JSON.stringify(objGuardia),
											contentType:'application/json;charset=utf-8',
											beforeSend: function() {
											       		fnPlantillaSweetAlertEnEspera();
											    	}
										}).done(function(response){
											
											fnPlantillaSweetAlertMensaje('Se ha realizado el registro.',
													'/front.funeasistencia/servicios.indice');
										}).fail(function(a,b,c){
											console.log(a,b,c);
										});
									}
								}else{
									$.ajax({
											url:'/front.funeasistencia/guardias.insercion',
											type:'POST',
											dataType:'JSON',
											data: JSON.stringify(objGuardia),
											contentType:'application/json;charset=utf-8',
											beforeSend: function() {
											       		fnPlantillaSweetAlertEnEspera();
											    	}
										}).done(function(response){
											
											fnPlantillaSweetAlertMensaje('Se ha realizado el registro.',
													'/front.funeasistencia/servicios.indice');
										}).fail(function(a,b,c){
											console.log(a,b,c);
										});
								}
								
							}).fail(function(a,b,c){
								console.log(a,b,c);
							});
		             }
		         });
				}else{
					var vMensajeGuardia=$('div[id="mensajeGuardia"]');
					vMensajeGuardia.html('');
					vMensajeGuardia.append('No se ha seleccionado ningún empleado para la guardia.');
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
	    
	}).fail(function(a,b,c){
	    console.log(a,b,c);
	});
	
}
fnArmadoTabla();