function fnLlenarSweetAlertsConsulta(pIdItem){
	var vFilaContenido=$('tr[id="'+pIdItem+'"]').html();
	var vCabeceraTitulos=$('thead[id="header"]').html();
	var vTablaFinal=
	'<h6>Resumen </h6> <table class="table table-sm">'+
		'<thead class="table-secondary">'+
		vCabeceraTitulos.replace('<th class="sorting_disabled dt-center" rowspan="1" colspan="1">Acciones</th>','')+
		'</thead>'+
		'<tbody style="font-size:14px">'+
		vFilaContenido.
			replace('<td class="  dt-center"><button class="btn btn-xs btn-outline-secondary" onclick="fnLlenarSweetAlertsConsulta('+pIdItem+')"><span class="tf-icons bx bx-bookmark-alt-minus"></span> Consultar</button></td>','')+
		'</tbody>'+
	'</table> <hr>  <div id="tablaAjax" style="font-size:14px;">';
	fnArmarTablaParaSweetAlert(pIdItem);
	Swal.fire({
    	title: 'Resumen Integrantes Guardia',
        html: vTablaFinal,
        type:'success',
        confirmButtonText: 'Volver',
        customClass:'swal-wide',
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
function fnPlantillaSweetAlert(pTitulo,pTablaFinal){
	
	var vModalSWA=Swal.fire({
    	title: pTitulo,
        html: pTablaFinal,
        type:'success',
        showDenyButton: true,	
        confirmButtonText: 'Quiero registrar',
        denyButtonText: 'Volver',
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
		        
		        for(var b=0;b<response.length;b++){
		            var vArrayValores=[];
		            for(var c=0;c<vIndices.length;c++){
		                vArrayValores.push(response[b][vIndices[c]]);
		            }
		        vDatatables.row.add(vArrayValores).draw(false);
		        }       
		    }).fail(function(a,b,c){
				console.log(a,b,c);
			});
}
