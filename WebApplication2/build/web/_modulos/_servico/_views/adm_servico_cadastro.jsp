
<%@page import="gestordeprocessos.util.Configuracoes"%>
<%@page import="helpDesk.DAO.HibernateConnection"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="helpDesk.entidades.Servico"%>
<%@page import="helpDesk.DAO.ServicoDAO"%>
<%@page import="java.util.List"%>
<%@page import="helpDesk.entidades.TipoServico"%>
<%@page import="helpDesk.DAO.TipoServicoDAO"%>
<%
    String id = "";
    String nome = "";
    List tiposervico = null;
    String status = "";
    Servico servico = null;
    String descricao = "";
    try {
        TipoServicoDAO sdao = new TipoServicoDAO();
        sdao.setSession(HibernateConnection.getSession());
        sdao.setClasse(TipoServico.class);
        tiposervico = sdao.listAll(null, "TipoServico.findAll");
        if (request.getParameter("id") != null) {
            id = request.getParameter("id");
            ServicoDAO dao = new ServicoDAO();
            servico = (Servico) dao.get(Restrictions.eq("id", id), "Servico.findAll");
            if (servico != null) {
                id = servico.getId().toString();
                nome = servico.getNome();
                descricao = servico.getDescricao();
                status = servico.getSituacao().toString();
            }
        }
    } catch (Exception ex) {
        out.print(ex.getMessage());
    }
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<div id="formulario">
    <form id="idForm" action="/ServicoActions.do" method="POST" target="#processa">
        <input type="hidden" name="mode" value="SAVE"/>
        <input type="hidden" id="idId" name="id" value=""/>
        <ul class="markup">
            <li class="pFisica">
                <div class="left" style="width: 419px;">
                    <label for="idNome">Nome <%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                    <input type="text" id="idNome" name="nome" class="inputAdm required" data-required="É Necessário escolher um Nome" value="<%=nome%>"/>
                </div>
                <div class="clear"></div>
            </li>
            <li>
                <div class="left rightFix" style="width: 150px;padding-right: 10px;">
                    <label for="idSetor">Tipo Servico <%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                    <select id="idSetor" name="idtipoServico" class="inputAdm required" data-required="É Necessário informar o Setor">

                        <% if (tiposervico != null) {
                                try {
                                    for (Object obj : tiposervico) {%>
                        <option  value= "<%out.print(((TipoServico) obj).getId());%>"
                                 <%if (id != "") {
                                         if (servico.getTipoServico().getId() != null && ((TipoServico) obj).getId() == servico.getTipoServico().getId()) {
                                             out.print("selected='selected'");
                                         }
                                     }%> > 
                            <% out.print(((TipoServico) obj).getNome());%>
                        </option>
                        <% }
                                } catch (Exception ex) {
                                    out.print(ex.getMessage());
                                }
                            }%>
                    </select>
                </div>
                <div class="clear"></div>
            </li>
            <li style="margin-top: 19px;">
                <hr style="height: 1px; background-color: #DDD; border: 0"/>
            </li>

            <li class="descricaoPro">
                <!--problema-->
                <div class="left" style="width: 30%;">
                    <label for="idServico">Descrição</label>
                    <textarea name="descriServico"  rows="5" cols="50">
                        <%if (id != "") {
                                out.print(descricao);
                            }%>
                    </textarea>
                </div>
                <div class="clear"></div>
            </li>
            <li>
                <div class="left rightFix" style="width: 150px;padding-right: 10px;">
                    <label for="idStatus">Situação </label>
                    <select id="idStatus" name="status" class="inputAdm ">
                        <% if (id != "") {
                                if (status.equals("1")) {%>
                        <option value="0">Ativo</option>                        
                        <option  value= "1">Desativado</option>
                        <%} else {%>                        
                        <option  value= "1">Desativado</option>
                        <option value="0">Ativo</option>                        
                        <%}%>
                        <%} else {%>
                        <option value="0">Ativo</option>                        
                        <option  value= "1">Desativado</option> <%}%>
                    </select>
                </div>
                <div class="clear"></div>
            </li>
            <li style="margin-top: 19px;">
                <hr style="height: 1px; background-color: #DDD; border: 0"/>
            </li>
            <li class="porPeriodo">
                <h3>Setar a visibilidade</h3>
                <div style="padding-top: 10px;text-align: center">
                    <input type="radio" name="visibilidade" value="0" style="padding: 10px;"checked/>Todos
                    <input type="radio" name="visibilidade" value="1" style="padding: 10px;"/>Usuario avançado
                    <input type="radio" name="visibilidade" value="2" style="padding: 10px;"/>Gerente
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
        }
    };
    

</script>