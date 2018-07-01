$(document).ready(function() {
	
    $('#example').DataTable({
    	"language": {
    		"sEmptyTable":     "Nessun dato presente nella tabella",
    		"sInfo":           "Vista da _START_ a _END_ di _TOTAL_ elementi",
    		"sInfoEmpty":      "Vista da 0 a 0 di 0 elementi",
    		"sInfoFiltered":   "(filtrati da _MAX_ elementi totali)",
    		"sInfoPostFix":    "",
    		"sInfoThousands":  ".",
    		"sLengthMenu":     "Visualizza _MENU_ elementi",
    		"sLoadingRecords": "Caricamento...",
    		"sProcessing":     "Elaborazione...",
    		"sSearch":         "Cerca:",
    		"sZeroRecords":    "La ricerca non ha portato alcun risultato.",
    		"oPaginate": {
    			"sFirst":      "Inizio",
    			"sPrevious":   "Precedente",
    			"sNext":       "Successivo",
    			"sLast":       "Fine"
    		},
    		"oAria": {
    			"sSortAscending":  ": attiva per ordinare la colonna in ordine crescente",
    			"sSortDescending": ": attiva per ordinare la colonna in ordine decrescente"
    		}
    	}
    });
    
    //esempio di table generata tramite chiamata ajax (non piu utilizzato)
   var table =  $('#test').DataTable({
    	"deferRender": true,
    	"ajax": {
    		"serverSide":"true",
    		"url": '/getLotOfUsers',
    	    "dataSrc": ""

    	},
    	"columns": [
    		{"data": "nome"},
    		{"data": "cognome"},
    		{"data": "email"},
    		{"data": "codiceFiscale"},
    		{"defaultContent": "<button type=\"button\" class=\"btn btn-primary\">Click!</button>"}
    		
    	]
    });
   
   
    
    $('#test tbody').on( 'click', 'button', function () {
        var d = table.row( $(this).parents('tr') ).data();
        alert( 'codicefiscale: ' + d.codiceFiscale);
        window.location.href = 'http://google.com';
    } );
    
    //Rimuove alert al cambio di tab
    $('.nav-item').click(function() {
    	$('.alert').alert('close');
    });
    
    //Rimuove gli errori di validazione dei form al cambio di tab
    $('.nav-item').on('shown.bs.tab', function(e) {
    	$('input').removeClass('is-invalid');
    	$('small.text-danger').remove();
    });
    
    $('#generaIdTessera').click(function(e) {
    	$.ajax({url: "/getFirstAvailableIdTessera", success: function(result){
    		$('#inputIdTessera').val(result);
        }});
    	
    });
} );