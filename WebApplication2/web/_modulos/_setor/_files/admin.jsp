<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="gestordeprocessos.util.Configuracoes"%>

<script>
    var container = 'processa';
    var modulo = 'setor';
    var componente = '';
    var moduloAction = 'Setor';
    var ondeIr = 'adm_'+modulo+'_cadastro';
    //var dataAtual = '<%//=new SimpleDateFormat("ddMMYHHmmss").format(new Date())%>';
    var params = '';
    var url = '/SetorActions.do?m=GRID';
    var retornoStatus = '/SetorActions.do';
    
    var comands = {
      'Novo':{
         sel : false,
         func: "winLoad(ondeIr,'Adicionar Setor',false,false,'3','idTitulo','','"+modulo+"','', '', '370')"
      },
      'Editar':{
         sel : true,
         func: "($('tbody tr.trSelected').length>1)?alerta('Selecione somente um registro para editar', 'ATEN��O', 'Voc� s� pode editar um item por vez', '', ''):($('tbody tr.trSelected').length<1)?alerta('Voc� tem que selecionar pelo menos um registro para editar', 'ATEN��O', 'Voc� n�o selecionou nenhum registro', '', ''):winLoad(ondeIr,'Editar Setor',false,false,'3','idTitulo','id='+id ,'"+modulo+"','', '', '370')"
      },
      'Alterar Status':{
         sel : true,
         func:"var status = statusSelecteds(); ($('tbody tr.trSelected').length<1)?alerta('Voc� tem que selecionar pelo menos um registro para alterar o status', 'ATEN��O', 'Voc� n�o selecionou nenhum registro', '', ''):loadPage('#"+container+"','"+retornoStatus+"?id='+id+'&acao='+status+'&m=STAT')"
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

  

    var columns =[
      {
         display: 'Codigo Setor',
         name : 'id',
         width : 100,
         sortable : true,
         align: 'left',
         hide: false
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
        } ,
        {
            display: 'Codigo',
            name : 'id',
            isdefault: true
        } ,
        {
            display: 'Status',
            name : 'status',
            isdefault: true
        } 
        
    ];


    callGrid(url,columns,buttons,searchitems,configGrid);
</script>