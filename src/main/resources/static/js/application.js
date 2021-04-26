$(document).ready(function() {
	
    var table = $('#example').DataTable({
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
   var t =  $('#test').DataTable({
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
    
    //rimuove contorno rossa dall'input e eventuali messaggi di errore nel tag <small>
    $("input").on("click", function() {
    	$(this).removeClass("is-invalid");
    	$(this).next("small").remove();
    });
    
    
    
    
    modificaRichiamo = function(index){ 
        //abilita tutti i campi input della scheda richiamo
    	var but = $("#modificaRichiamo_" + index);
    	var index = index.toString();
    	
    	if (but.text() === 'Modifica') {
	    	$("#schedaRichiamo_" + index).find('input').each(function () {
				$(this).prop('readonly', false);
			});
			but.text("Annulla");
		} else if (but.text() === 'Annulla') {
			$("#schedaRichiamo_" + index).find('input').each(function () {
				$(this).prop('readonly', true);
			});
			but.text("Modifica");
		} 
    };

    
    
    $(".nav-item").on("click", function(){
	   $(".nav-item").find(".active").removeClass("active");
	   $(this).addClass("active");
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