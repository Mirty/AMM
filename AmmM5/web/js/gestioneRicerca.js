
$(document).ready(function () {
    var div;
    div = document.createElement("div");
    div.className="corpo_c_vantaggi";
    document.getElementById('corpo_c').appendChild(div);
    $(".corpo_c_vantaggi").hide();
    
    // ogni volta che alzo il dito da un pulsante, richiama la funzione 
    $("#filtra").keyup(function() {  
        // Preleva il valore
        var text = $("#filtra").val();
       
        $.ajax({
            // manda la chiamata a filter.json 
            url: "filter.json",
            data:{
              chiave: "q",
              valore: text 
            },
            dataType: 'json',
            // data: i dati da passare al server (POST o GET)
            success : function(data, state){
                aggiornaTabellaOggetti(data);
            },
            error : function(data, state){
                console.error(state);  // nel caso ci sia errore 
            }
        });
        
       // Funzione che viene richiamata in caso di successo
        function aggiornaTabellaOggetti(listaOggetti) {
            // Svuota la tabella in cliente.jsp dai li perché va aggiornata rispetto alla ricerca precedente
            // lascio solo l'header 
            $("#tabellaOggetti").show(); // potrei averla nascosta precedentemente nel caso di 0 oggetti restituiti
            $(".riga_dispari").remove();
            $(".riga_pari").remove();
            $(".corpo_c_vantaggi").hide();
            // Per ogni alunno trovato dal database
            // Find a <table> element with id="myTable":
            var table = document.getElementById("tabellaOggetti");
            
            var i=0;
            // uso una variabile di controllo per capire se entro o meno dentro il for
            // ovvero se la lista degli oggetti è non vuota
            var controllo=0;
            for(var oggetto in listaOggetti) {
                $(".corpo_c_vantaggi").hide();
                controllo=1;
                
                // creo una nuova riga nella tabella 
                var row = table.insertRow(); 
                if (i%2==0) {
                    row.className='riga_pari';
                }
                else {
                    row.className='riga_dispari';
                }    
                i++;
                
                // inserisco le celle nella riga creata
                var cell1 = row.insertCell();
                var cell2 = row.insertCell();
                var cell3 = row.insertCell();
                var cell4 = row.insertCell();
                var cell5 = row.insertCell();
                var cell6 = row.insertCell();
                var cell7 = row.insertCell();
                var cell8 = row.insertCell();

                // per ogni cella specifico i campi..
                // NOME - MARCA - IMMAGINE - IN STOCK - PREZZO - PESO - DESCRIZIONE - CARRELLO
                cell1.innerHTML = listaOggetti[oggetto].nome;
                cell2.innerHTML = listaOggetti[oggetto].marca;
                cell3.innerHTML = "<img src=\"imgs/obj/"+listaOggetti[oggetto].img+"\" alt=\""+listaOggetti[oggetto].nome+"\" title=\""+listaOggetti[oggetto].nome+"\" height=\"90\">";
                cell4.innerHTML = listaOggetti[oggetto].inStock;
                cell5.innerHTML = listaOggetti[oggetto].prezzo;
                cell6.innerHTML = listaOggetti[oggetto].peso;
                cell7.innerHTML = listaOggetti[oggetto].descrizione;
                cell8.innerHTML = "<a href=\"Acquirente?productId="+listaOggetti[oggetto].id+"\"><img src=\"imgs/header/carrello.png\" alt=\"Aggiungi al carrello\" height=\"40\"></a>";
            }
            if (controllo==0) { // se non esiste oggetto corrispondente alla ricerca fatta dal cliente...
                // nascondo la tabella 
                $("#tabellaOggetti").hide();
                // mostro il messaggio di 'errore'
                div.innerHTML="Non c'è nessun bene corrispondente alla ricerca <i>"+text+"</i> su <a href=\"descrizione.jsp\">Eataly.com</a>";
                $(".corpo_c_vantaggi").show();
            }
        }
    }); 
});