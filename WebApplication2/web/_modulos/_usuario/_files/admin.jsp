<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="gestordeprocessos.util.Configuracoes"%>

<script>
    var container = 'processa';
    var modulo = 'usuario';
    var componente = '';
    var moduloAction = 'usuario';
    var ondeIr = 'adm_'+modulo+'_cadastro';
    //var dataAtual = '<%//=new SimpleDateFormat("ddMMYHHmmss").format(new Date())%>';
    var params = '';
    var url = '/UsuarioActions.do?m=GRID';
    var retornoStatus = '/UsuarioActions.do';
    
    var comands = {
        'Novo':{
            sel : false,
            func: "winLoad(ondeIr,'Adicionar Usuario',false,false,'3','idTitulo','','"+modulo+"','', '', '473')"
        },
        'Editar':{
            sel : true,
            func: "($('tbody tr.trSelected').length>1)?alerta('Selecione somente um registro para editar', 'ATENÇÃO', 'Você só pode editar um item por vez', '', ''):($('tbody tr.trSelected').length<1)?alerta('Você tem que selecionar pelo menos um registro para editar', 'ATENÇÃO', 'Você não selecionou nenhum registro', '', ''):winLoad(ondeIr,'Editar Usuario',false,false,'3','idTitulo','id='+id ,'"+modulo+"','', '', '478')"
        },
        'Alterar Status':{
            sel : true,
            func:"var status = statusSelecteds(); ($('tbody tr.trSelected').length<1)?alerta('Você tem que selecionar pelo menos um registro para alterar o status', 'ATENÇÃO', 'Você não selecionou nenhum registro', '', ''):loadPage('#"+container+"','"+retornoStatus+"?id='+id+'&acao='+status+'&m=STAT')"
        }
      
    };

    var configGrid = {
        title: 'Usuario',
        width: 996,
        height: jQuery(window).height()-<%=Configuracoes.OFFSET.getConfig()%>,
        sortname: "nome",
        sortorder: "asc",
        rp: 50
    }


    var columns =[
        {
            display: 'Matricula',
            name : 'id',
            width : 80,
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
            display: 'Setor',
            name : 'setor',
            width : 150,
            sortable : true,
            align: 'left'
        },
        {
            display: 'Permissao',
            name : 'permissao',
            width : 150,
            sortable : true,
            align: 'left'
        },
        {
            display: 'Cargo',
            name : 'cargo',
            width : 100,
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
        }
       
    ];


    var searchitems = [
        {
            display: 'Nome',
            name : 'nome',
            isdefault: true
        },
        {
            display: 'Matricula',
            name : 'id',
            isdefault: true
        },
        {
            display: 'Setor',
            name : 'setor',
            isdefault: true
        },
        {
            display: 'Status',
            name : 'status',
            isdefault: true
        }  
        
    ];


    callGrid(url,columns,buttons,searchitems,configGrid);
</script>