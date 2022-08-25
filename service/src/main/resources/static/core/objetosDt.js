function fnConfiguracionTipoAtaud(){
    var vConfiguracion=[];
    var vIndices=[];
        vIndices.push('cdataud');
        vIndices.push('txtipoataud');
        vIndices.push('txdescripcion');
        vIndices.push('sttipoataud');
    var vCabecera=[];
        vCabecera.push('Código Ataud');
        vCabecera.push('Tipo de Ataud');
        vCabecera.push('Descripción');
        vCabecera.push('Estatus');
        vCabecera.push('Acciones');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
};
function fnConfiguracionGuardias(){
    var vConfiguracion=[];
    var vIndices=[];
        vIndices.push('txencargado');
        vIndices.push('txoficina');
        vIndices.push('feinicioaux');
        vIndices.push('fefinalaux');
    var vCabecera=[];
        vCabecera.push('Persona Encargada');
        vCabecera.push('Oficina');
        vCabecera.push('Fecha Inicio');
        vCabecera.push('Fecha Fin');
        vCabecera.push('Acciones');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}

function fnConfiguracionGuardiaIntegrantes(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Documento');
        vCabecera.push('Nombre');
        vCabecera.push('Cargo');
        vCabecera.push('Telefono');
     var vIndices=[];
        vIndices.push('txdocumento');
        vIndices.push('txnombres');
        vIndices.push('txtipopersona');
        vIndices.push('txtelefono1');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}

function fnConfiguracionGuardiasDT1(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Documento');
        vCabecera.push('Nombre');
        vCabecera.push('Cargo');
        vCabecera.push('Acciones');
     var vIndices=[];
        vIndices.push('txdocumento');
        vIndices.push('txnombres');
        vIndices.push('txtipopersona');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}

function fnConfiguracionServicios(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Servicio');
        vCabecera.push('Moneda Pago');
        vCabecera.push('Tipo de Pago');
        vCabecera.push('Monto del Presupuesto');

        var vIndices=[];
        vIndices.push('txservicio');
        vIndices.push('txmoneda');
        vIndices.push('txtipopago');
        vIndices.push('mtpresupuesto');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}
function fnConfiguracionServicios2(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Servicio');
        vCabecera.push('Moneda Pago');
        vCabecera.push('Tipo de Pago');
        vCabecera.push('Monto del Presupuesto');
        vCabecera.push('Al Cambio en Bolívares');
		vCabecera.push('Acciones');
        var vIndices=[];
        vIndices.push('txservicio');
        vIndices.push('txmoneda');
        vIndices.push('txtipopago');
        vIndices.push('mtpresupuesto');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}

function fnConfiguracionPresupuesto(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Encargado');
        vCabecera.push('Titular');
        vCabecera.push('Fallecido');
        vCabecera.push('Capilla');
        vCabecera.push('Contrataci&oacuten / Convenio');
        vCabecera.push('Estatus');
        vCabecera.push('Acciones');
        var vIndices=[];
        vIndices.push('txpersonaencargada');
        vIndices.push('txnombretitular');
        vIndices.push('txnombrefallecido');
        vIndices.push('txcapilla');
        vIndices.push('txcontrataciondetalle');
        vIndices.push('txestatus');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}

function fnConfiguracionPersonas(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Documento');
        vCabecera.push('Nombre');
        vCabecera.push('Tipo Persona');
        vCabecera.push('Telefono Primario');
        vCabecera.push('Dirección');
        vCabecera.push('Correo');
        vCabecera.push('Acciones');
        var vIndices=[];
        vIndices.push('txdocumento');
        vIndices.push('txnombres');
        vIndices.push('txtipopersona');
        vIndices.push('txtelefono1');;
        vIndices.push('txdireccion');
        vIndices.push('txcorreo');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}
function fnConfiguracionUsuarios(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Usuario');
        vCabecera.push('Nombre');
        vCabecera.push('Documento');
        vCabecera.push('Rol');
        vCabecera.push('Correo');
        vCabecera.push('Telefono');
        vCabecera.push('Acciones');
        var vIndices=[];
        vIndices.push('cdusuario');
        vIndices.push('txnombre');
        vIndices.push('txdocumento');
        vIndices.push('txclave');
        vIndices.push('txcorreo');
        vIndices.push('txtelefono');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}
function fnConfiguracionDtEstadisticas1(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Oficina');
        vCabecera.push('Presupuestos ');
        vCabecera.push('Servicios');
        vCabecera.push('Monto Ingreso');
        vCabecera.push('Cambio a Bolívares');
        vCabecera.push('Acciones');
    vConfiguracion.push(vCabecera);
    return vConfiguracion;
}
function fnConfiguracionDtEstadisticas2(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Tipo de Pago');
        vCabecera.push('Monto Ingreso');
        vCabecera.push('Cambio a Bolívares');
    vConfiguracion.push(vCabecera);
    return vConfiguracion;
}
function fnConfiguracionOficina(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Nombre Oficina');
        vCabecera.push('Telefono Primario');
        vCabecera.push('Telefono Secundario');
        vCabecera.push('Direccion');
        vCabecera.push('Acciones');
        var vIndices=[];
        vIndices.push('txcompania');
        vIndices.push('txtelefono1');
        vIndices.push('txtelefono2');
        vIndices.push('txdireccion');;
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}
function fnConfiguracionTipoPersona(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Código Tipo Persona');
        vCabecera.push('Tipo Persona');
        vCabecera.push('Acciones');
        var vIndices=[];
        vIndices.push('cdtipopersona');
        vIndices.push('txtipopersona');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}
function fnConfiguracionTipoServicio(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Código Tipo Servicio');
        vCabecera.push('Tipo Servicio');
        vCabecera.push('Acciones');
        var vIndices=[];
        vIndices.push('cdtiposervicio');
        vIndices.push('txtiposervicio');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}
function fnConfiguracionRol(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Código Rol');
        vCabecera.push('Rol');
        vCabecera.push('Acciones');
        var vIndices=[];
        vIndices.push('cdrol');
        vIndices.push('txrol');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}
function fnConfiguracionTipoPago(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Código Tipo de Pago');
        vCabecera.push('Tipo de Pago');
        vCabecera.push('Moneda');
        vCabecera.push('Acciones');
        var vIndices=[];
        vIndices.push('cdtipopago');
        vIndices.push('txtipopago');
        vIndices.push('txmoneda');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}
function fnConfiguracionTipoContratacion(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Código Tipo de Contratación');
        vCabecera.push('Tipo de Contratación');
        vCabecera.push('Acciones');
        var vIndices=[];
        vIndices.push('cdtipocontratacion');
        vIndices.push('txtipocontratacion');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}
function fnConfiguracionTipoMoneda(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Código Moneda');
        vCabecera.push('Moneda');
        vCabecera.push('Acciones');
        var vIndices=[];
        vIndices.push('cdmoneda');
        vIndices.push('txmoneda');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}

function fnConfiguracionPresupuestoAlterno(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Documento del Solicitante');
        vCabecera.push('Nombre del Solicitante');
        vCabecera.push('Tel&eacute;fono');
        vCabecera.push('Documento del Difunto');
        vCabecera.push('Nombre del Difunto');//
        vCabecera.push('Estatus');
        vCabecera.push('Fecha');
        vCabecera.push('Vendedor');
        vCabecera.push('Oficina');
        var vIndices=[];
        vIndices.push('txdocumentotitular');
        vIndices.push('txnombretitular');
        vIndices.push('txtelefonotitular');
        vIndices.push('txdocumentofallecido');
        vIndices.push('txnombrefallecido');
        vIndices.push('txestatus');
        
        vIndices.push('txfepresupuesto');
        vIndices.push('txpersonaencargada');
        vIndices.push('txoficina');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}

function fnMenuRol(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Código Menu Rol');
        vCabecera.push('Rol');
        vCabecera.push('Menu');
        vCabecera.push('Acciones');
        var vIndices=[];
        vIndices.push('cdmenurol');
        vIndices.push('txrol');
        vIndices.push('txmenu');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}

function fnContratacionDetalle(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Código Detalle Contratación');
        vCabecera.push('Tipo de Contratación');
        vCabecera.push('Detalle de Contratación');
        vCabecera.push('Acciones');
        var vIndices=[];
        vIndices.push('cdcontrataciondetalle');
        vIndices.push('txtipocontratacion');
        vIndices.push('txcontrataciondetalle');
    vConfiguracion.push(vCabecera);
    vConfiguracion.push(vIndices);
    return vConfiguracion;
}

function fnReporteGuardias(){
    var vConfiguracion=[];
    var vCabecera=[];
 
        vCabecera.push('Vendedor');
        vCabecera.push('Cantidad de Guardias');
        vCabecera.push('Cantidad de Servicios');
        vCabecera.push('Monto Servicios Dólares');
        vCabecera.push('Monto Servicios Bolívares');
    vConfiguracion.push(vCabecera);
    return vConfiguracion;
}

function fnReportePresupuesto2(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Tipo Servicio');
        vCabecera.push('Cantidad de Presupuestos');
        vCabecera.push('Cantidad de Servicios');
        vCabecera.push('Monto Servicios Dólares');
        vCabecera.push('Monto Servicios Bolívares');
    vConfiguracion.push(vCabecera);
    return vConfiguracion;
}
function fnReportePresupuesto1(){
    var vConfiguracion=[];
    var vCabecera=[];
        vCabecera.push('Tipo Servicio');
        vCabecera.push('Cantidad de Servicios');
        vCabecera.push('Monto Servicios Dólares');
        vCabecera.push('Monto Servicios Bolívares');
    vConfiguracion.push(vCabecera);
    return vConfiguracion;
}





