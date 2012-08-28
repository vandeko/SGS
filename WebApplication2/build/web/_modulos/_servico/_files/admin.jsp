<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="gestordeprocessos.util.Configuracoes"%>

<script>
    var container = 'processa';
    var modulo = 'servico';
    var componente = '';
    var moduloAction = 'Servico';
    var ondeIr = 'adm_'+modulo+'_cadastro';
    //var dataAtual = '<%//=new SimpleDateFormat("ddMMYHHmmss").format(new Date())%>';
    var params = '';
    var url = '/ServicoActions.do?m=GRID';
    var retornoStatus = '/ServicoActions.do';
    
    var comands = {
      'Novo':{
         sel : false,
         func: "winLoad(ondeIr,'Adicionar Servico',false,false,'3','idTitulo','','"+modulo+"','', '','600')"
      },
      'Editar':{
         sel : true,
         func: "($('tbody tr.trSelected').length>1)?alerta('Selecione somente um registro para editar', 'ATENÇÃO', 'Você só pode editar um item por vez', '', ''):($('tbody tr.trSelected').length<1)?alerta('Você tem que selecionar pelo menos um registro para editar', 'ATENÇÃO', 'Você não selecionou nenhum registro', '', ''):winLoad(ondeIr,'Editar Servico',false,false,'3','idTitulo','id='+id ,'"+modulo+"','', '', '600')"
      },
      
      'Alterar Status':{
         sel : true,
         func:"var status = statusSelecteds(); ($('tbody tr.trSelected').length<1)?alerta('Você tem que selecionar pelo menos um registro para alterar o status', 'ATENÇÃO', 'Você não selecionou nenhum registro', '', ''):loadPage('#"+container+"','"+retornoStatus+"?id='+id+'&acao='+status+'&m=STAT')"
      }
      
    };

    var configGrid = {
        title: 'Servico',
        width: 996,
        height: jQuery(window).height()-<%=Configuracoes.OFFSET.getConfig()%>,
        sortname: "nome",
        sortorder: "asc",
        rp: 50
    }

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
            name: 'Alterar Status',
            bclass: 'status',
            onpress : doCommand
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