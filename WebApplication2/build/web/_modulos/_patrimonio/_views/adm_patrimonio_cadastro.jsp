<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="helpDesk.entidades.Patrimonio"%>
<%@page import="helpDesk.DAO.PatrimonioDAO"%>
<%@page import="gestordeprocessos.util.Configuracoes"%>
<%@page import="helpDesk.DAO.HibernateConnection"%>
<%@page import="helpDesk.entidades.Setor"%>
<%@page import="helpDesk.DAO.SetorDAO"%>
<%@page import="java.util.List"%>
<%
    String id = "";
    String nome = "";
    String status = "";
    String descricao = "";
    List setor = null;
    Patrimonio patrimonio = null;
    try {
        SetorDAO sdao = new SetorDAO();
        sdao.setSession(HibernateConnection.getSession());
        sdao.setClasse(Setor.class);
        setor = sdao.listAll(null, "Setor.findAll");

        if (request.getParameter("id") != null) {
            id = request.getParameter("id");
            PatrimonioDAO pdao = new PatrimonioDAO();
            pdao.setClasse(Patrimonio.class);
            pdao.setSession(HibernateConnection.getSession());
            patrimonio = (Patrimonio) pdao.get(Restrictions.eq("id", Integer.valueOf(id)), "Patrimonio.findAll");
            if (patrimonio != null) {
                id = patrimonio.getId().toString();
                nome = patrimonio.getNome();
                descricao = patrimonio.getDescricao();
                status = patrimonio.getSituacao().toString();
            }
        }
    } catch (Exception ex) {
        out.print(ex.getMessage());
    }
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<div id="formulario">
    <form id="idForm" action="/PatrimonioActions.do" method="POST" target="#processa">
        <input type="hidden" name="mode" value="SAVE"/>
        <input type="hidden" id="idId" name="id" value="<%=id%>"/>
        <ul class="markup">
            <li class="pFisica">
                <div class="left" style="width: 150px;">
                    <label for="idMatricula">Codigo Patrimonio<%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                    <%if (id != "") {%>
                    <input type="text" id="idMatricula" name="matricula" class="inputAdm" value="<%=id%>"/>
                    <%} else {%>
                    <input type="text" id="idMatricula" name="matricula" class="inputAdm required"
                           data-required="Preencha o campo Codigo Patrimonio" value="" onblur="unicoPatrimonio($(this), 'idMatricula', '<%=id%>')"/>
                    <%}%>
                </div>
                <div class="left" style="width: 419px;">
                    <label for="idNome">Nome<%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                    <input type="text" id="idNome" name="nome" class="inputAdm required" 
                           data-required="Preencha o campo Nome" value="<%=nome%>"/>
                </div>
                <div class="clear"></div>
            </li>
            <li>
                <div class="left rightFix" style="width: 150px;padding-right: 10px;">
                    <label for="idSetor">Setor <%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                    <select id="idSetor" name="idSetor" class="inputAdm required"
                            data-required="Preencha o campo Setor">
                        <option value="">----</option>
                        <% if (setor != null) {
                                try {
                                    for (Object obj : setor) {%>
                        <option  value= "<%out.print(((Setor) obj).getId());%>"
                                 <%if (id != "") {
                                         if (patrimonio.getSetor().getId() != null && ((Setor) obj).getId() == patrimonio.getSetor().getId()) {
                                             out.print("selected='selected'");
                                         }
                                     }%> >                            
                            <% out.print(((Setor) obj).getNome());%>
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
                <div class="left" style="width: 30%;">
                    <label for="idServico">Descrição do Patrimonio</label>
                    <textarea name="descricao" rows="5" cols="50"><%if (id != "") {
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
                        <%} else if (status.equals("0")) {%>                        
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
        <div class="clear"></div>
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
            top.alerta('<BR><BR>', 'Patrimonio', 'Cadastro Realizado com Sucesso', '2', '');
        }
    };
    unicoPatrimonio = function(obj, focus, id){
        
        var val = obj.val();
        if(val == ''){
            return false;
        }
           
        $.post("/PatrimonioActions.do", {mode: 'UNICOPATRIMONIO', val: val, id: id}, function(data){
            console.log(data);
            if(data != 0){
                if(!typeof focus == 'undefined'){
                    top.alerta('Este patrimonio já existe!<BR><BR>','Patrimonio Duplicado','','2',focus);
                } else {
                    top.alerta('Este patrimonio já existe!<BR><BR>','Patrimonio Duplicado','','2',focus);
                }
                obj.val('');
            }
        })
    }

</script>