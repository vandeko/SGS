
<%@page import="gestordeprocessos.util.Configuracoes"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="helpDesk.entidades.TipoServico"%>
<%@page import="helpDesk.DAO.HibernateConnection"%>
<%@page import="helpDesk.DAO.TipoServicoDAO"%>
<%
    String id = "";
    String nome = "";
    String descricao = "";
    String status = "";
    TipoServico tipo = null;
    try {
        if (request.getParameter("id") != null) {
            id = request.getParameter("id");
            TipoServicoDAO dao = new TipoServicoDAO();
            dao.setClasse(TipoServico.class);
            dao.setSession(HibernateConnection.getSession());
            tipo = (TipoServico) dao.get(Restrictions.eq("id", Integer.valueOf(id)), "TipoServico.findAll");
            if (tipo != null) {
                id = tipo.getId().toString();
                nome = tipo.getNome();
                descricao = tipo.getDescricao();
                status = tipo.getSituacao().toString();
            }
        }
    } catch (Exception ex) {
        out.print(ex.getMessage());
    }
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<div id="formulario">
    <form id="idForm" action="/TipoServicoActions.do" method="POST" target="#processa">
        <input type="hidden" name="mode" value="SAVE"/>
        <input type="hidden" id="idId" name="id" value="<%=id%>"/>
        <ul class="markup">
            <li class="pFisica">
                <div class="left" style="width: 419px;">
                    <label for="idNome">Nome<%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                    <input type="text" id="idNome" name="nome" class="inputAdm required" data-required="Preencher o campo Nome" value="<%=nome%>"/>
                </div>
                <div class="clear"></div>
            </li>
            <li style="margin-top: 19px;">
                <hr style="height: 1px; background-color: #DDD; border: 0"/>
            </li>

            <li class="descricaoPro">
                <!--problema-->
                <div class="left" style="width: 30%;">
                    <label for="idTipoServico">Descrição</label>
                    <textarea name="descri" rows="5" cols="50" >
                        <%if (id != "") {
                                out.print(descricao);
                            }%></textarea>
                </div>
                <div class="clear"></div>
            </li>
            <li>
                <div class="left rightFix" style="width: 150px;padding-right: 10px;">
                    <label for="idStatus">Situação </label>
                    <select id="idStatus" name="status" class="inputAdm ">
                        <% if (id != "") {
                                if (status.equals("1")) {%>
                        <option value="1">Ativo</option>                        
                        <option  value= "0">Desativado</option>
                        <%} else {%>                        
                        <option  value= "0">Desativado</option>
                        <option value="1">Ativo</option>                        
                        <%}%>
                        <%} else {%>
                        <option value="1">Ativo</option>                        
                        <option  value= "0">Desativado</option> <%}%>
                    </select>
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
            top.alerta('<BR><BR>', 'Tipo Servico', 'Cadastro Realizado com Sucesso', '2', '');
        }
    };
    

</script>