
<%@page import="java.util.Date"%>
<%@page import="gestordeprocessos.util.Configuracoes"%>

<script>
    var container = 'processa';
    var modulo = 'patrimonio';
    var componente = '';
    var moduloAction = 'Patrimonio';
    var ondeIr = 'adm_'+modulo+'_cadastro';
    //var dataAtual = '<%//=new SimpleDateFormat("ddMMYHHmmss").format(new Date())%>';
    var params = '';
    var url = '/PatrimonioActions.do?m=GRID';
    var retornoStatus = '/PatrimonioActions.do';
    
    var comands = {
        'Novo':{
            sel : false,
            func: "winLoad(ondeIr,'Adicionar Patrimonio',false,false,'3','idTitulo','','"+modulo+"','', '', '510')"
        },
        'Editar':{
            sel : true,
            func: "($('tbody tr.trSelected').length>1)?alerta('Selecione somente um registro para editar', 'ATENÇÃO', 'Você só pode editar um item por vez', '', ''):($('tbody tr.trSelected').length<1)?alerta('Você tem que selecionar pelo menos um registro para editar', 'ATENÇÃO', 'Você não selecionou nenhum registro', '', ''):winLoad(ondeIr,'Editar Patriomonio',false,false,'3','idTitulo','id='+id ,'"+modulo+"','', '', '510 ')"
        },
     
        'Alterar Status':{
            sel : true,
            func:"var status = statusSelecteds(); ($('tbody tr.trSelected').length<1)?alerta('Você tem que selecionar pelo menos um registro para alterar o status', 'ATENÇÃO', 'Você não selecionou nenhum registro', '', ''):loadPage('#"+container+"','"+retornoStatus+"?id='+id+'&acao='+status+'&m=STAT')"
        }
      
    };

    var configGrid = {
        title: 'Patrimonio',
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
            width : 350,
            sortable : true,
            align: 'left'
        },
        {
            display: 'Descricao',
            name : 'descricao',
            width : 300,
            sortable : true,
            align: 'left'
        },
        {
            display: 'Setor',
            name : 'setor',
            width : 150,
            sortable : true,
            align: 'left'
        },
        {
            display: 'Status',
            name : 'status',
            width : 100,
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
        } ,
        {
            display: 'Status',
            name : 'situacao',
            isdefault: true
        },  
        {
            display: 'Codigo',
            name : 'id',
            isdefault: true
        },  
        {
            display: 'Setor',
            name : 'setor',
            isdefault: true
        }  
        
    ];


    callGrid(url,columns,buttons,searchitems,configGrid);
</script>