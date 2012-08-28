
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="helpDesk.DAO.HibernateConnection"%>
<%@page import="helpDesk.entidades.Chamado"%>
<%@page import="helpDesk.DAO.ChamadoDAO"%>
<%@page import="helpDesk.entidades.Setor"%>
<%@page import="helpDesk.DAO.SetorDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="sun.text.resources.FormatData"%>
<%@page import="helpDesk.entidades.Usuario"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="gestordeprocessos.util.Configuracoes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<%
    String id = "";
    Chamado chamado = null;
    if (request.getParameter("id") != null) {
        id = request.getParameter("id").toString();
        ChamadoDAO cdao = new ChamadoDAO();
        cdao.setClasse(Chamado.class);
        cdao.setSession(HibernateConnection.getSession());
        chamado = (Chamado) cdao.get(Restrictions.eq("id", Integer.valueOf(id)), "Chamado.findAll");
        if (chamado != null) {
            id = chamado.getId().toString();
        }
    }
%>

<div id="formulario">
    <form id="idForm" action="/ChamadoActions.do" method="POST" target="#processa">
        <input type="hidden" name="mode" value="STATCANC"/>
        <input type="hidden" id="idId" name="id" value="<%=id%>"/>
        <ul class="markup">

            <li class="descricaoPro">
                <!--problema-->
                <div class="left" style="width: 30%;">
                    <label for="idServico">Motivo do Cancelamento</label>
                    <textarea name="descriProblema" data-required="É Necessário informar o motivo " rows="5" cols="50"></textarea>
                </div>
                <div class="clear"></div>
            </li>
            <li>
                <div class="right rightFix" style="width: 126px;">
                    <div class="tituloTab" style="text-align: right; width: 100%; height: 20px; font-size: 11px"><font color='#f00'>* Campos obrigatórios.</font></div>
                    <div class="btnPadraoRight" onclick="salvar()">Salvar</div>
                </div>
                <div class="clear"></div>
            </li>
        </ul>
    </form>
    <div class="clear"></div>
</div>

<script>
    salvar = function() {
        error = 0;
            
        $('#idForm').find('.required').each(function(){
            $this = $(this);
            if($this.is(':visible') && $this.val() == ''){
                error++
                msg = $(this).data('required');
                return false;
            }
        })
        if(error > 0){
            top.alerta(msg+'<BR><BR>', 'Campo Necessário', '', '2', '');
        } else {
            $('#idForm').submit();
            top.alerta('Seu Chamado foi cancelado com sucesso!<BR><BR>', 'Cancelamento', '', '2', '');
            var status = statusSelecteds();
            loadPage('#"+container+"','"+retornoStatus+"?id='+id+'&acao='+status+'&m=STATCANC');
            top.closeWindow('blackscreenWinconfirm');
            top.closeWindow('blackscreenWinload');
            top.$('#flexiGrid').flexReload(); 
            //var status = statusSelecteds(); 
            //($('tbody tr.trSelected').length<1)?alerta('Você tem que selecionar pelo menos um registro para cancelar', 'ATENÇÃO', 'Você não selecionou nenhum registro', '', ''):loadPage('#"+container+"','"+retornoStatus+"?id='+id+'&acao='+status+'&m=STATCANC');
           
            
        }
    };
       
</script>
