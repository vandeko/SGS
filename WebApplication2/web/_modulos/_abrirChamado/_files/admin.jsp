
<%@page import="helpDesk.DAO.HibernateConnection"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="helpDesk.entidades.DetalheChamado"%>
<%@page import="helpDesk.DAO.DetalheChamadoDAO"%>
<%@page import="helpDesk.entidades.TipoServico"%>
<%@page import="helpDesk.DAO.TipoServicoDAO"%>
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
    String tipoH = "";
    String idDetalhe = "";
    DetalheChamado detalhe = null;
    Usuario user = (Usuario) session.getAttribute("usuarioSIST");
    Date dataAbertura = new Date(System.currentTimeMillis());
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    String data = df.format(dataAbertura);
    List setor = null;
    List tipoServico = null;

    try {

        SetorDAO sdao = new SetorDAO();
        sdao.setSession(HibernateConnection.getSession());
        sdao.setClasse(Setor.class);
        setor = sdao.listAll(null, "Setor.findAll");

        TipoServicoDAO tdao = new TipoServicoDAO();
        tdao.setSession(HibernateConnection.getSession());
        tdao.setClasse(TipoServico.class);
        tipoServico = tdao.listAll(null, "TipoServico.findAll");

        if (request.getParameter("id") != null) {
            idDetalhe = request.getParameter("idD");
            DetalheChamadoDAO dtdao = new DetalheChamadoDAO();
            dtdao.setClasse(DetalheChamado.class);
            dtdao.setSession(HibernateConnection.getSession());
            detalhe = (DetalheChamado) dtdao.get(Restrictions.eq("id", Integer.valueOf(idDetalhe)), "DetalheChamado.findAll");
            if (detalhe != null) {
                idDetalhe = detalhe.getId().toString();
            }
        }
    } catch (Exception ex) {
        out.print(ex.getMessage());
    }
%>
<div id="formulario">
    <form id="idForm" action="/ChamadoActions.do" method="POST" target="#processa">
        <input type="hidden" name="mode" value="SAVE"/>
        <input type="hidden" id="idId" name="id" value=""/>
        <ul class="markup">
            <h3 style="text-align: left">Dados Solicitante</h3>
            <li>
                <div class="left" style="width: 419px;">
                    <h4>Matricula: <input type="text" id="idMatricula" name="matricula"class="inputAdmin" style="width: 100px;" readonly  value="<% out.print(user.getId());%>"/></h4>
                </div>
                <div class="left" style="width: 419px;">
                    <h4>Nome: <input type="text" id="idNome" name="nome" class="inputAdmin"style="width: 350px;" readonly  value="<% out.print(user.getNome());%>"/></h4>
                </div>
                <div class="left" style="width: 419px;">
                    <h4>Email: <input type="text" id="idEmail" name="email"class="inputAdmin" style="width: 350px;" readonly  value="<% out.print(user.getEmail());%>"/></h4>
                </div>
                <div class="left" style="width: 419px;">
                    <h4>Telefone: <input type="text" id="idTelefone" name="telefone"class="inputAdmin maskTelefone" style="width: 150px;" readonly  value="<% out.print(user.getTelefone());%>"/></h4>
                </div>
                <div class="left" style="width: 419px;">
                    <h4>Cargo: <input type="text" id="idCargo" name="cargo"class="inputAdmin" style="width: 150px;" readonly  value="<% out.print(user.getCargo());%>"/></h4>
                </div>
                <div class="left" style="width: 419px;">
                    <h4>Setor: <input type="text" id="idSetor" name="setor"class="inputAdmin" style="width: 100px;" readonly  value="<% out.print(user.getSetor().getNome());%>"/></h4>
                </div>
                <div class="clear"></div>
            </li>
            <li style="margin-top: 19px;padding-top: 10px">
                <hr style="height: 1px; background-color: #DDD; border: 0"/>
            </li>
            <div class="dChamado">
                <h3 style="text-align: left">Dados Chamado</h3>
                <li>
                    <div class="left" style="width: 30%;">
                        <label for="idTipoServico">Tipo Servico<%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                        <select id="idTipoServico" name="tipo" class="inputAdm required" data-required="É Necessário informar o Tipo Serviço"  onblur="mostraDiv('idPatrimonio')" onchange="listaServico('idServico', this.value);">                                        
                            <option value="">-----------</option>
                            <% if (tipoServico != null) {
                                    try {
                                        for (Object obj : tipoServico) {%>
                            <option  value= "<%out.print(((TipoServico) obj).getNome());%>">
                                <% out.print(((TipoServico) obj).getNome());%>
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
                    <div class="left" style="width: 250px;">                 
                        <label for="idServico">Servico<%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                        <select id="idServico" name="servico" data-required="É Necessário informar o Servico" class="inputAdm  required"></select>
                    </div>
                </li>


                <li class=pUsuario">
                    <!--Parte usuario Chamado-->                    
                    <div id="idPatrimonio"class="left" style="width: 15%;padding-right: 15%;display: none">
                        <label for="idPatrimonio">Patrimonio </label>
                        <input type="text" id="idPatrimonio" name="idPatrimonio" class="inputAdm"  value="" onchange="listaSetor('idSetor', this.value);"onblur="unicoPatrimonio($(this), 'idPatrimonio', '<%=id%>')"/>
                    </div>

                    <div class="left" style="width: 30%;">
                        <label for="idSetor">Setor<%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                        <select id="idSetor" name="idSetor" class="inputAdm required" data-required="É Necessário informar o Setor">
                            <option value="">------------</option>
                            <% if (setor
                                        != null) {
                                    try {
                                        for (Object obj : setor) {%>
                            <option  value= "<%out.print(((Setor) obj).getId());%>" >
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
                <li class="descricaoPro">
                    <!--problema-->
                    <div class="left" style="width: 30%;">
                        <label for="idServico">Descrição do Problema<%=Configuracoes.CAMPO_OBRIGATORIO.getConfig()%></label>
                        <textarea name="descriProblema" data-required="É Necessário informar o problema "rows="5" cols="90"></textarea>
                    </div>
                    <div class="clear"></div>
                </li>
                <li>
                    <div class="left" style="width: 15%;padding-right: 15%;">
                        <label for="idPatrimonio">Data Abertura</label>
                        <input type="text" id="idDateA" name="dateAber" class="inputAdmin" readonly  value="<%=data%>"/>
                    </div>
                    <div class="clear"></div>
                </li>
                <li>
                    <div class="right rightFix" style="width: 126px;">
                        <div class="tituloTab" style="text-align: right; width: 100%; height: 20px; font-size: 11px"><font color='#f00'>* Campos obrigatórios.</font></div>
                        <div class="btnPadraoR" onclick="salvar()">Salvar</div>
                    </div>
                    <div class="clear"></div>
                </li>
                </u>
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
                        alerta('Seu Chamado foi realizado com sucesso!<BR><BR>', '', '', '', '');
                        location.href='admin.jsp';
                    }
                }
   
                var ativo = "";
                mostraDiv = function(src) {
        
                    if (ativo != src) {

                        $('#'+ativo).slideToggle('slow', null);

                        $('#tit_'+ativo).css({
                            color: '#333',
                            fontWeight: 'bold',
                            backgroundColor: "#efefef"            
                        });
   
                        $('#'+src).slideToggle('slow', null);

                        $('#tit_'+src).css({
                            color: '#00478b',
                            fontWeight: "bold",
                            backgroundColor: "#efefff"
                        });

                        ativo = src;
                    }  }
       
                unicoPatrimonio = function(obj, focus, id){
                    var val = obj.val();
                    if(val == ''){
                        return false;
                    }
           
                    $.post("/PatrimonioActions.do", {mode: 'UNICOPATRIMONIO', val: val, id: id}, function(data){
                        console.log(data);
                        if(data != 1){
                            if(!typeof focus == 'undefined'){
                                top.alerta('Este patrimonio não existe!<BR><BR>','Patrimonio Duplicado','','2',focus);
                            } else {
                                top.alerta('Este patrimonio não existe!<BR><BR>','Patrimonio Duplicado','','2',focus);
                            }
                            obj.val('');
                        }
                    })
                }
            </script>
