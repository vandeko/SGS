<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="gestordeprocessos.util.Configuracoes"%>

<script>
    var container = 'processa';
    var modulo = 'tipoServico';
    var componente = '';
    var moduloAction = 'TipoServico';
    var ondeIr = 'adm_'+modulo+'_cadastro';
    //var dataAtual = '<%//=new SimpleDateFormat("ddMMYHHmmss").format(new Date())%>';
    var params = '';
    var url = '/TipoServicoActions.do?m=GRID';
    var retornoStatus = '/TipoServicoActions.do';
    
    var comands = {
        'Novo':{
            sel : false,
            func: "winLoad(ondeIr,'Adicionar Tipo Servico',false,false,'3','idTitulo','','"+modulo+"','', '', '473')"
        },
        'Editar':{
            sel : true,
            func: "($('tbody tr.trSelected').length>1)?alerta('Selecione somente um registro para editar', 'ATENÇÃO', 'Você só pode editar um item por vez', '', ''):($('tbody tr.trSelected').length<1)?alerta('Você tem que selecionar pelo menos um registro para editar', 'ATENÇÃO', 'Você não selecionou nenhum registro', '', ''):winLoad(ondeIr,'Editar Tipo Servico',false,false,'3','idTitulo','id='+id ,'"+modulo+"','', '', '473')"
        },
      
        'Alterar Status':{
            sel : true,
            func:"var status = statusSelecteds(); ($('tbody tr.trSelected').length<1)?alerta('Você tem que selecionar pelo menos um registro para alterar o status', 'ATENÇÃO', 'Você não selecionou nenhum registro', '', ''):loadPage('#"+container+"','"+retornoStatus+"?id='+id+'&acao='+status+'&m=STAT')"
        }
      
    };

    var configGrid = {
        title: 'TipoServico',
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
            hide: false
        },
        {
            display: 'Nome',
            name : 'nome',
            width : 250,
            sortable : true,
            align: 'left'
        },
        {
            display: 'Descricao',
            name : 'descricao',
            width : 580,
            sortable : true,
            align: 'left'
        },
        {
            display: 'Status',
            name : 'status',
            width : 80,
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
        }       
       
    ];

    var searchitems = [
        {
            display: 'Nome',
            name : 'nome',
            isdefault: true
        },       
          {
            display: 'Status',
            name : 'situacao',
            isdefault: true
        }
    ];


    callGrid(url,columns,buttons,searchitems,configGrid);
</script>