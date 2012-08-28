<%@page import="helpDesk.DAO.UsuarioDAO"%>
<%@page import="helpDesk.DAO.HibernateConnection"%>
<%@page import="helpDesk.entidades.Chamado"%>
<%@page import="helpDesk.DAO.ChamadoDAO"%>
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
    String idDetalhe = "";
    List detalhe = null;
    Date dataAbertura = new Date(System.currentTimeMillis());
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    String data = df.format(dataAbertura);
    List setor = null;
    List tipoServico = null;
    String descricao = "";
    String patrimonio = "";
    String dataCh = "";
    String descrSer = "";
    Chamado chamado = null;
    Usuario usuario = null;
    Usuario usuarioex = null;
    String dataServico = null;
    String tipoServic = "";
    String servico = "";
    Setor set = null;
    SetorDAO sdao = new SetorDAO();
    sdao.setSession(HibernateConnection.getSession());
    sdao.setClasse(Setor.class);
    setor = sdao.listAll(null, "Setor.findAll");

    TipoServicoDAO tdao = new TipoServicoDAO();
    tdao.setSession(HibernateConnection.getSession());
    tdao.setClasse(TipoServico.class);
    tipoServico = tdao.listAll(null, "TipoServico.findAll");

    if (request.getParameter("id") != null) {
        id = request.getParameter("id").toString();
        ChamadoDAO cdao = new ChamadoDAO();
        cdao.setClasse(Chamado.class);
        cdao.setSession(HibernateConnection.getSession());
        chamado = (Chamado) cdao.get(Restrictions.eq("id", Integer.valueOf(id)), "Chamado.findAll");
        if (chamado != null) {
            id = chamado.getId().toString();
            descricao = chamado.getDescrProblema();
            if (chamado.getPatrimonio() != null) {
                patrimonio = chamado.getPatrimonio().getId().toString();
            }
            dataCh = chamado.getDataAber().toString();
            usuario = chamado.getUsuario();
            set = chamado.getSetor();
        }
    }
%>

<div id="navegacao" style="height: 500px">
    <div id="formulario">
        <form id="idForm" action="" method="POST" target="#processa">
            <input type="hidden" name="mode" value="SAVE"/>
            <input type="hidden" id="idId" name="id" value="<%=id%>"/>
            <ul class="markup">
                <li class=pUsuario">
                    <div id="idNumero"class="left" style="width: 15%;">
                        <label for="idNumero">Nº Chamado</label>
                        <input type="text" id="idNumero" name="idNumero" class="inputAdm" readonly value="<%=id%>"/>
                    </div>
                </li>
                <li>
                    <div id="idPatrimonio"class="left" style="width: 15%;">
                        <label for="idPatrimonio">Patrimonio </label>
                        <input type="text" id="idPatrimonio" name="idPatrimonio" class="inputAdm" readonly value="<%=patrimonio%>" onchange="listaSetor('idSetor', this.value);"/>
                    </div>
                    <div class="left" style="width: 35%;">
                        <label for="idSetor">Setor</label>
                        <input type="text" id="idPatrimonio" name="idPatrimonio" class="inputAdm" readonly value="<%=set.getNome()%>" />
                    </div>
                    <div class="clear"></div>
                </li>
                <li>
                    <div class="left" style="width: 30%;">
                        <label for="idServico">Descrição do Problema</label>
                        <textarea name="descriProblema" rows="5" cols="60" readonly><%if (id != "") {
                                out.print(descricao);
                            }%></textarea>
                    </div>
                    <div class="clear"></div>
                </li>
                <li>
                    <div class="left" style="width: 35%;">
                        <label for="idData">Data Abertura</label>
                        <input type="text" id="idDateA" name="dateAber" class="inputAdmin" readonly  value="<%=dataCh%>"/>
                    </div>
                    <div class="clear"></div>
                </li>
                <li style="margin-top: 19px;padding-top: 10px">
                    <hr style="height: 1px; background-color: #DDD; border: 0"/>
                </li>
                <% if (request.getParameter("id") != null) {
                        DetalheChamadoDAO dtdao = new DetalheChamadoDAO();
                        dtdao.setClasse(DetalheChamado.class);
                        dtdao.setSession(HibernateConnection.getSession());
                        detalhe = dtdao.listAll(Restrictions.eq("chamado", chamado), "DetalheChamado.findAll");
                        for (int i = 0; i < detalhe.size(); i++) {
                            DetalheChamado det = (DetalheChamado) detalhe.get(i);
                            idDetalhe = det.getId().toString();
                            descrSer = det.getDescriServico();
                            dataServico = df.format(det.getDataServico());
                            tipoServic = det.getServico().getTipoServico().getNome();
                            servico = det.getServico().getNome();
                            usuarioex = det.getUsuario();
                %>
                <li>
                    <div class="left" style="width: 35%;">
                        <label for="idData"> Data em que o Servico o foi Realizado</label>
                        <input type="text" id="idDateA" name="dateAber" class="inputAdmin" readonly  value="<%=dataServico%>"/>
                    </div>
                    <div class="clear"></div>
                </li>
                <li>
                    <div class="left" style="width: 50%;">
                        <label for="idData"> Executante do Serviço</label>
                        <input type="text" id="nomeEx" name="dateAber" class="inputAdm" readonly  value="<%= usuarioex.getNome()%>"/>
                    </div>
                    <div class="clear"></div>
                </li>
                <li>
                    <div class="left" style="width: 200px;">
                        <label for="idTipoServico">Tipo Servico</label>
                        <input type="text" id="tipoServico" name="dateAber" class="inputAdm" readonly  value="<%=tipoServic%>"/>
                    </div>
                    <div class="left" style="width: 200px;">
                        <label for="idServico">Servico</label>
                        <input type="text" id="servico" name="dateAber" class="inputAdm" readonly  value="<%=servico%>"/>
                    </div>
                    <div class="clear"></div>
                </li>                               
                <li class="descricaoSolucao">
                    <div class="left" style="width: 30%;">
                        <label for="idServico">Descrição do Servico</label>
                        <textarea readonly name="descriServico1" rows="5" cols="90"><%if (id != "") {
                                out.print(descrSer);
                            }%></textarea>
                    </div>
                    <div class="clear"></div>
                </li>   
                <%}
                    }%>
                </u>
                <div class="clear"></div>
        </form>
        <div class="clear"></div>

    </div>
