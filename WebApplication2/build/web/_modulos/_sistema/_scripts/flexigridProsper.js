//Flexigrid
var nDestaque = 0;
callGrid = function (url,columns,buttons,searchitems,configGrid){
    $("#flexiGrid").flexigrid({
        url: url,
        dataType: 'json',
        colModel :columns,
        buttons : buttons,
        searchitems : searchitems,
        sortname: configGrid['sortname'],
        sortorder: configGrid['sortorder'],
        user:configGrid['user'],
        usepager: true,
        title: configGrid['title'],
        useRp: true,
        rp: configGrid['rp'],
        showTableToggleBtn: true,
        resizable: false,
        width: configGrid['width'],
        height: configGrid['height'],
        onSuccess   : function(){gridFormat();scripts();},
        singleSelect: false
    });

    $('#flexiGrid').on('dblclick', 'tbody tr', function(){
      $(this).addClass('trSelected');
      id = $(this).find('td[abbr=id] div').text();
      eval(comands['Editar']['func']);
    })

    $(document).unbind('keyup');
    $(document).bind('keyup',function(e) {
      count = $('#blackscreenWinload').length;
      if(count == 0){
         if (e.keyCode == 46) {
            id=rowsSelecteds();
            eval(comands['Apagar']['func']);
         }
      }
    });

   doCommand();
   doMask();
}

//function doCommand(com, grid) {
//    if(com != '' && typeof com != 'undefined'){
//        if(!comands[com]['sel']){
//            eval(comands[com]['func']);
//        }else{
//            $('.trSelected', grid).each(function() {
//            var id = $(this).attr('id');
//            id = id.substring(id.lastIndexOf('row')+3);
//            eval(comands[com]['func']);
//            });
//        }
//    }
//
//}

function doCommand(com, grid) {
    if(com != '' && typeof com != 'undefined'){
        if(!comands[com]['sel']){
            eval(comands[com]['func']);
        }else{
            id=rowsSelecteds();
            idioma=idiomaSelecteds();
            eval(comands[com]['func']);
        }
    }

}

function rowsSelecteds(){
   listIds = new Array();
   $('tbody tr.trSelected td[abbr=id] div').each(function(index, element) {
      listIds.push($(this).html());
   })
   return listIds;
}

function idiomaSelecteds(){
   listIdiomas = new Array();
   $('tbody tr.trSelected td[abbr=idioma] div').each(function(index, element) {
      listIdiomas.push($(this).html());
   })
   return listIdiomas;
}

function statusSelecteds(){
   listIds = new Array();
   $('tbody tr.trSelected td[abbr=status] div').each(function(index, element) {
      listIds.push($(this).html() == 'Ativado' ? '0' : '1');
   })
   return listIds;
}

function doMask(){
//    $('select[name="qtype"]').change(function(){
//        if($('select[name="qtype"]').val()=='data'){
//            $('input[name="q"]').setMask('date');
//        }else{
//            $('input[name="q"]').unsetMask();
//        }
//    })
}

function gridFormat() {
    var lblStatus = {
        '1' : {
            css : '',
            txt : 'Ativado'
        },
        '0' : {
            css : 'cellDisable',
            txt : 'Desativado'
        },
        '2' : {
            css : 'cellFinished',
            txt : 'Finalizado'
        },
        '3' : {
            css : 'cellWaiting',
            txt : 'Aguardando Atendimento'
        },
        '4' : {
            css : 'cellReply',
            txt : 'Em atendimento'
        }
        ,'5' : {
            css : 'cellWaiting',
            txt : 'Aguardando Agendamento'
        }
        ,'6' : {
            css : 'cellImportada',
            txt : 'Cancelado'
        }
        ,'7' : {
            css : 'cellWaiting',
            txt : 'Encerrado'
        }
        ,'11' : {
            css : 'pagamento_confirmado',
            txt : 'Pagamento Confirmado'
        }
        ,'12' : {
            css : 'vencido',
            txt : 'Vencido'
        }
    };

    if ($("input[name=destaque]").length > 0) {
        nDestaque = $("input[name=destaque]:checked").length;
    }

    $('#flexiGrid tr').each( function(){
        var cell = $('td[abbr="status"] >div', this);
        if(cell.text()){
            $(this).addClass( lblStatus[cell.text()].css );
            cell.text( lblStatus[cell.text()].txt );
        }
    });
    return true;
}

function ButtonsLetterOrder(nameFieldOrder) {
    var nameField = nameFieldOrder;
    this.getNameField = getNameField;
    this.getButtons = getButtons;

    function getNameField() {
        return nameField;
    }
    function setNameField(_nameField) {
        nameField = _nameField;
    }
    function getButtons(){
        var buttonsLetterOrder =[
        {
            name: 'Todos',
            bclass:'searchLetter'
        },
        {
            name: 'A',
            bclass:'searchLetter'
        },

        {
            name: 'B',
            bclass:'searchLetter'
        },

        {
            name: 'C',
            bclass:'searchLetter'
        },

        {
            name: 'D',
            bclass:'searchLetter'
        },

        {
            name: 'E',
            bclass:'searchLetter'
        },

        {
            name: 'F',
            bclass:'searchLetter'
        },

        {
            name: 'G',
            bclass:'searchLetter'
        },

        {
            name: 'H',
            bclass:'searchLetter'
        },

        {
            name: 'I',
            bclass:'searchLetter'
        },

        {
            name: 'J',
            bclass:'searchLetter'
        },

        {
            name: 'K',
            bclass:'searchLetter'
        },

        {
            name: 'L',
            bclass:'searchLetter'
        },

        {
            name: 'M',
            bclass:'searchLetter'
        },

        {
            name: 'N',
            bclass:'searchLetter'
        },

        {
            name: 'O',
            bclass:'searchLetter'
        },

        {
            name: 'P',
            bclass:'searchLetter'
        }
        ,

        {
            name: 'Q',
            bclass:'searchLetter'
        },

        {
            name: 'R',
            bclass:'searchLetter'
        },
        {
            name: 'S',
            bclass:'searchLetter'
        },

        {
            name: 'T',
            bclass:'searchLetter'
        },

        {
            name: 'U',
            bclass:'searchLetter'
        },

        {
            name: 'V',
            bclass:'searchLetter'
        }
        ,
        {
            name: 'W',
            bclass:'searchLetter'
        },
        {
            name: 'X',
            bclass:'searchLetter'
        },

        {
            name: 'Y',
            bclass:'searchLetter'
        },

        {
            name: 'Z',
            bclass:'searchLetter'
        }];

        return buttonsLetterOrder;
    }

}

$('input').live('keypress', function(e){
    if(e.which == 13){
        return false;
    }
})

