<%@page import="helpDesk.entidades.Usuario"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="gestordeprocessos.util.Configuracoes"%>

<script>
   
    var container = 'processa';
    var modulo = 'acompanharChamado';
    var componente = '';
    var moduloAction = 'Chamado';
    var ondeIr = 'adm_'+modulo+'_cadastro';
    var ondeIr1 = 'adm_chamado_cadastro';
   // var dataAtual = '<%//=new SimpleDateFormat("ddMMYHHmmss").format(new Date())%>';
    var params = '';
    var url = '/ChamadoActions.do?m=GRID';
    var retornoStatus = '/ChamadoActions.do';
    
    var comands = {
        'Historico':{
            sel : true,
            func: "($('tbody tr.trSelected').length>1)?alerta('Selecione somente um registro para visualizar', 'ATENÇÃO', 'Você só pode editar um item por vez', '', ''):($('tbody tr.trSelected').length<1)?alerta('Você tem que selecionar pelo menos um registro para visualizar', 'ATENÇÃO', 'Você não selecionou nenhum registro', '', ''):winLoad(ondeIr,'Historico',false,false,'3','idTitulo','id='+id ,'"+modulo+"','', '980', '580')"
        },
        'Cancelar':{
         sel : true,
         func:"($('tbody tr.trSelected').length>1)?alerta('Selecione somente um registro para cancelar', 'ATENÇÃO', 'Você só pode cancelar um item por vez', '', ''):($('tbody tr.trSelected').length<1)?alerta('Você tem que selecionar pelo menos um registro para cancelar', 'ATENÇÃO', 'Você não selecionou nenhum registro', '', ''):winLoad(ondeIr1,'Cancelamento',false,false,'3','idTitulo','id='+id ,'"+modulo+"','', '', '280')"
        
      }    
    };

    var configGrid = {
        title: 'Chamados',
        width: 996,
        height: jQuery(window).height()-<%=Configuracoes.OFFSET.getConfig()%>,
        sortname: "id",
        sortorder: "asc",
        rp: 50
    }


    var columns =[
        {
            display: 'Num Chamado',
            name : 'id',
            width : 110,
            sortable : true,
            align: 'left',
            hide: false
        },
        {
            display: 'Data Abertura',
            name : 'dataAber',
            width : 150,
            sortable : true,
            align: 'left'
        },
        {
            display: 'Data Finalização',
            name : 'dataEnc',
            width : 150,
            sortable : true,
            align: 'left'
        },
        {
            display: 'Nome Solicitante',
            name : 'usuario',
            width : 250,
            sortable : true,
            align: 'left'
        },
        {
            display: 'Setor',
            name : 'setor',
            width : 120,
            sortable : true,
            align: 'left'
        },
        {
            display: 'Status',
            name : 'status',
            width : 150,
            sortable : true,
            align: 'left'
        }
    ];

    var buttons = [
       
        {
            name: 'Historico',
            bclass: 'edit',
            onpress : doCommand
        },

        {
            name: 'Cancelar',
            bclass: 'status',
            onpress : doCommand
        }
       
    ];


    var searchitems = [
        {
            display: 'Numero Chamado',
            name : 'id',
            isdefault: true
        },       
        {
            display: 'Matricula',
            name : 'usuario',
            isdefault: true
        },
        {
            display: 'Data Abertura',
            name : 'dataAber',
            isdefault: true
        }
    ];


    callGrid(url,columns,buttons,searchitems,configGrid);
</script>