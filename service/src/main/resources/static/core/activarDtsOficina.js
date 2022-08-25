function activarDts(){
	$('table[id="dt1"]').DataTable(fnRetornarConfiguracionDT());
 	$('table[id="dt2"]').DataTable(fnRetornarConfiguracionDT());
 	$('table[id="dt3"]').DataTable(fnRetornarConfiguracionDT());
}
activarDts();
