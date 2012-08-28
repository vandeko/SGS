<%@page import="gestordeprocessos.util.Configuracoes"%>
<%@page import="helpDesk.DAO.HibernateConnection"%>
<%@page import="helpDesk.entidades.Usuario"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="helpDesk.DAO.UsuarioDAO"%>
<%@page import="helpDesk.entidades.Setor"%>
<%@page import="helpDesk.DAO.SetorDAO"%>
<%@page import="java.util.List"%>
<%
    String id = "";
    String nome = "";
    String email = "";
    String telefone = "";
    String cargo = "";
    String status = "";
    String permissao = "";
    List setor = null;
    Usuario usuario = null;
    String senha="";
    try {

        SetorDAO sdao = new SetorDAO();
        sdao.setSession(HibernateConnection.getSession());
        sdao.setClasse(Setor.class);
        setor = sdao.listAll(null, "Setor.findAll");
        if (request.getParameter("id") != null) {
            id = request.getParameter("id");
            UsuarioDAO dao = new UsuarioDAO();
            dao.setClasse(Usuario.class);
            dao.setSession(HibernateConnection.getSession());
            usuario = (Usuario) dao.get(Restrictions.eq("id", Integer.valueOf(id)), null, "Usuario.findAll");
            if (usuario != null) {
                id = usuario.getId().toString();
                nome = usuario.getNome();
                email = usuario.getEmail();
                telefone = usuario.getTelefone();
                cargo = usuario.getCargo();
                permissao = usuario.getPermissao().toString();
                senha=usuario.getPass();
                status = usuario.getStatus().toString();
            }
        }
    } catch (Exception ex) {
        out.print(ex.getMessage());
    }
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<div id="navegacao" style="height: 355px">
    <div id="formulario">
        <form id="idForm" action="/UsuarioActions.do" method="POST" target="#processa">
            <input type="hidden" name="mode" value="SAVE"/>
            <input type="hidden" id="idId" name="id" value="<%=id%>"/>
            <ul class="markup">
                <li class="pFisica">
                    <div class="left" style="width: 150px;">
                        <label for="idMatricula">Matricula<%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                        <%if (id != "") {%>
                        <input type="text" id="idMatricula" name="matricula" class="inputAdm" readonly value="<%=id%>"/>
                        <%} else {%>
                        <input type="text" id="idMatricula" name="matricula" class="inputAdm required" data-required="Preencha o campo Matricula" onblur="unicoID($(this), 'idMatricula', '<%=id%>')"/>
                        <%}%>
                    </div>
                    <div class="left" style="width: 419px;">
                        <label for="idNome">Nome <%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                        <input type="text" id="idNome" name="nome" class="inputAdm required" data-required="Preencha o campo Nome" value="<%=nome%>"/>
                    </div>
                    <div class="left" style="width: 419px;">
                        <label for="idCargo">Cargo<%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                        <input type="text" id="idCargo" name="cargo" class="inputAdm required" data-required="Preencha o campo Cargo" value="<%=cargo%>"/>
                    </div>
                    <div class="clear"></div>
                </li>

                <li style="margin-top: 19px;">
                    <hr style="height: 1px; background-color: #DDD; border: 0"/>
                </li>

                <li>
                    <!--EMAIL-->
                    <div class="left" style="width: 259px;">
                        <label for="idEmail">Email </label>
                        <input type="text" id="idEmail" name="email" class="inputAdm"value="<%=email%>" onblur="unicoLogin($(this), 'idEmail', '<%=id%>')" />
                    </div>

                    <!--SENHA-->
                    <div class="left" style="width: 130px;">
                        <% if (id != "") {%>
                        <label for="idSenha">Alterar Senha</label>
                        <input type="password" id="idSenha" name="nsenha" class="inputAdm" value="<%=senha%>"/>
                        <%} else {%>
                        <label for="idSenha">Senha <%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                        <input type="password" id="idSenha" name="senha" class="inputAdm required" data-required="Preencha o campo senha" value="<%=senha%>"/><% }%>
                    </div>

                    <!--CONFIRMAR SENHA-->
                    <div class="left rightFix" style="width: 130px;">
                        <label for="idCSenha">Conf. Senha<%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                        <input type="password" id="idCSenha" name="senha" class="inputAdm" value="<%=senha%>"/>
                    </div>
                    <div class="clear"></div>
                </li>
                <li>
                    <div class="left rightFix" style="width: 150px;padding-right: 10px;">
                        <label for="idSetor">Setor <%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                        <select id="idSetor" name="idSetor" class="inputAdm required" data-required="Preencha o campo Setor">
                            <option value="">----</option>
                            <% if (setor != null) {
                                    try {
                                        for (Object obj : setor) {%>
                            <option  value= "<%out.print(((Setor) obj).getId());%>" 
                                     <%if (id != "") {
                                         if (usuario.getSetor().getId() != null && ((Setor) obj).getId() == usuario.getSetor().getId()) {
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
                </li>
                <li>
                    <div class="left rightFix" style="width: 150px;padding-right: 10px;">
                        <label for="idPermissao">Permissao<%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                        <select id="idPermissao" name="permissao" class="inputAdm required" data-required="Preencha o campo Permissao">
                            <%if (id != "") {
                                    if (permissao.equals("0")) {%>
                            <option  value= "0" >GERENTE</option>
                            <option  value= "1" >MODERADOR</option>
                            <option  value= "2" >ESTAGIARIO</option>
                            <option  value= "3" >COMUM</option>
                            <option  value= "4" >AVANÇADO</option>
                            <%} else if (permissao.equals("1")) {%>
                            <option  value= "1" >MODERADOR</option>
                            <option  value= "2" >ESTAGIARIO</option>
                            <option  value= "3" >COMUM</option>
                            <option  value= "4" >AVANÇADO</option>
                            <option  value= "0" >GERENTE</option>
                            <%} else if (permissao.equals("2")) {%>
                            <option  value= "2" >ESTAGIARIO</option>
                            <option  value= "3" >COMUM</option>
                            <option  value= "4" >AVANÇADO</option>
                            <option  value= "0" >GERENTE</option>
                            <option  value= "1" >MODERADOR</option>
                            <%} else if (permissao.equals("3")) {%>
                            <option  value= "3" >COMUM</option>
                            <option  value= "4" >AVANÇADO</option>
                            <option  value= "0" >GERENTE</option>
                            <option  value= "1" >MODERADOR</option>
                            <option  value= "2" >ESTAGIARIO</option>
                            <%} else if (permissao.equals("4")) {%>
                            <option  value= "4" >AVANÇADO</option>
                            <option  value= "0" >GERENTE</option>
                            <option  value= "1" >MODERADOR</option>
                            <option  value= "2" >ESTAGIARIO</option>
                            <option  value= "3" >COMUM</option>
                            <%}%>
                            <%}%>     
                            <option value="">----</option> 
                            <option  value= "0" >GERENTE</option>
                            <option  value= "1" >MODERADOR</option>
                            <option  value= "2" >ESTAGIARIO</option>
                            <option  value= "3" >COMUM</option>
                            <option  value= "4" >AVANÇADO</option>
                        </select>
                    </div>
                </li>
                <li>
                    <!--TELEFONE-->
                    <div class="left rightFix" style="width: 140px;">
                        <label for="idTelefone">Telefone</label>
                        <input type="text" id="idTelefone" name= "telefone" class="inputAdm maskTelefone" value="<%=telefone%>" />
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
    </div>
    <div class="clear"></div>
</div>
<script>
    salvar = function(){
        error = 0;
        if($('#idSenha').val() !=$('#idCSenha').val()){
            top.alerta('Os campos "senha" e "confirmação de senha" devem ser iguais<BR><BR>', 'Senha não confere', '', '2', focus);
            return false;
        }
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
            top.alerta('<BR><BR>', 'Usuario', 'Cadastro Realizado com Sucesso', '2', '');
            $('#idForm').submit();
        }
    };   
    unicoLogin = function(obj, focus, id){
        var val = obj.val();
        if(val == ''){
            return false;
        }
        if(!checkMail(val)){
            top.alerta('É Necessário informar um e-mail válido!<BR><BR>','Email Incorreto','','2',focus);
            obj.val('');
            return false
        }
        $.post("/UsuarioActions.do", {mode: 'UNICOLOGIN', val: val, id: id}, function(data){
            console.log(data);
            if(data != 0){
                if(!typeof focus == 'undefined'){
                    top.alerta('Este E-mail já foi cadastrado em nosso banco de dados!<BR><BR>','Email Duplicado','','2',focus);
                } else {
                    top.alerta('Este E-mail já foi cadastrado em nosso banco de dados!<BR><BR>','Email Duplicado','','2',focus);
                }
                obj.val('');
            }
        })
    }
    unicoID = function(obj, focus, id){
        var val = obj.val();
        if(val == ''){
            return false;
        }
       
        $.post("/UsuarioActions.do", {mode: 'UNICOID', val: val, id: id}, function(data){
            console.log(data);
            if(data != 0){
                if(!typeof focus == 'undefined'){
                    top.alerta('Este usuario já foi cadastrado em nosso banco de dados!<BR><BR>','Usuario Duplicado','','2',focus);
                } else {
                    top.alerta('Este usuario já foi cadastrado em nosso banco de dados!<BR><BR>','Usuario Duplicado','','2',focus);
                }
                obj.val('');
            }
        })
    }
</script>