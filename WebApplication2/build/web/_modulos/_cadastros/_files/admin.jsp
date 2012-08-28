<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="gestordeprocessos.util.Configuracoes"%>

<script>
    var container = 'processa';
    var modulo = 'setor';
    var componente = '';
    var moduloAction = 'Setor';
    var ondeIr = 'adm_'+modulo+'_cadastro';
    var dataAtual = '<%=new SimpleDateFormat("ddMMYHHmmss").format(new Date())%>';
    var params = '';
    var url = '/SetorActions.do?m=GRID';
    var retornoStatus = '/SetorActions.do';
    
    var comands = {
      'Novo':{
         sel : false,
         func: "winLoad(ondeIr,'Adicionar Cliente',false,false,'3','idTitulo','','"+modulo+"','', '', '473')"
      },
      'Editar':{
         sel : true,
         func: "($('tbody tr.trSelected').length>1)?alerta('Selecione somente um registro para editar', 'ATENÇÃO', 'Você só pode editar um item por vez', '', ''):($('tbody tr.trSelected').length<1)?alerta('Você tem que selecionar pelo menos um registro para editar', 'ATENÇÃO', 'Você não selecionou nenhum registro', '', ''):winLoad(ondeIr,'Editar Cliente',false,false,'3','idTitulo','id='+id ,'"+modulo+"','', '', '280')"
      },
      'Apagar':{
         sel : true,
         func:"($('tbody tr.trSelected').length<1)?alerta('Você tem que selecionar pelo menos um registro para apagar', 'ATENÇÃO', 'Você não selecionou nenhum registro', '', ''):apagar(id,'"+moduloAction+"','o Módulo selecionado', '#processa','2','')"

      },
      'Alterar Status':{
         sel : true,
         func:"var status = statusSelecteds(); ($('tbody tr.trSelected').length<1)?alerta('Você tem que selecionar pelo menos um registro para alterar o status', 'ATENÇÃO', 'Você não selecionou nenhum registro', '', ''):loadPage('#"+container+"','"+retornoStatus+"?id='+id+'&acao='+status+'&m=STAT')"
      },
      'Selecionar Todos':{
         sel : false,
         func:"($('#flexiGrid tbody tr.trSelected:visible').length<1)?$('#flexiGrid tbody tr:visible').addClass('trSelected'):$('#flexiGrid tbody tr:visible').removeClass('trSelected');"
      },
      'SubMódulos':{
         sel:true,
         func:"($('tbody tr.trSelected').length>1)?alerta('Selecione somente um registro!', 'ATENÇÃO', '', '', ''):($('tbody tr.trSelected').length<1)?alerta('Você tem que selecionar pelo menos um registro!', 'ATENÇÃO', '', '', ''):abreConteudo('conteudo','submodulos','','../admin','m=<%=request.getParameter("m")%>&id='+id+'&t="+dataAtual+"')"
      }
      
    };

    var configGrid = {
        title: 'Setor',
        width: 996,
        height: jQuery(window).height()-<%=Configuracoes.OFFSET.getConfig()%>,
        sortname: "nome",
        sortorder: "asc",
        rp: 50
    }

    $(document).unbind('dblclick');
    $('tbody tr td').live('dblclick',
    function(){
        $('tbody tr').removeClass('trSelected');
        $(this).parent().addClass('trSelected');
        id=$(this).parent().find('td[abbr=id] div').html();
        eval(comands['Editar']['func']);
    }
);
    $(document).unbind('keyup');
    $(document).bind('keyup',function( e ) {
        if (e.keyCode == 46) {
            id=rowsSelecteds();
            eval(comands['Apagar']['func']);
        }
    });

    var columns =[
      {
         display: 'ID',
         name : 'id',
         width : 40,
         sortable : true,
         align: 'left',
         hide: true
      },
      {
         display: 'Nome',
         name : 'nome',
         width : 550,
         sortable : true,
         align: 'left'
      },
      {
         display: 'Nome Adm',
         name : 'nomeAdmin',
         width : 210,
         sortable : true,
         align: 'left'
      },
      {
         display: 'Status',
         name : 'status',
         width : 60,
         sortable : true,
         align: 'left'
      }
    ];

    var buttons = [
        {
            name: 'Novo',
            bclass: 'new',
            onpress : doCommand
        },

        {
            name: 'Editar',
            bclass: 'edit',
            onpress : doCommand
        },

        {
            name: 'Apagar',
            bclass: 'delete',
            onpress : doCommand
        },

        {
            name: 'Alterar Status',
            bclass: 'status',
            onpress : doCommand
        },
        {
            separator: true
        },
        {
            name: 'Selecionar Todos',
            bclass: 'selecao',
            onpress : doCommand
        },
        {
            separator: true
        },
       
        {
            separator: true
        }
    ];


    var searchitems = [
        {
            display: 'Nome',
            name : 'nome',
            isdefault: true
        }       
        
    ];


    callGrid(url,columns,buttons,searchitems,configGrid);
</script>