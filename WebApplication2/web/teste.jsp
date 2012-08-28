<%@page import="helpDesk.DAO.ServicoDAO"%>
<%@page import="helpDesk.entidades.TipoServico"%>
<%@page import="helpDesk.DAO.TipoServicoDAO"%>
<%@page import="helpDesk.entidades.Servico"%>
<%@page import="java.io.File"%>
<%@page import="helpDesk.entidades.Chamado"%>
<%@page import="helpDesk.DAO.ChamadoDAO"%>
<%@page import="javax.swing.JInternalFrame"%>
<%@page import="net.sf.jasperreports.swing.JRViewer"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.util.JRLoader"%>
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"%>
<%@page import="java.util.HashMap"%>
<%@page import="gestordeprocessos.util.repositorioProduto"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="gestordeprocessos.util.ExcRepositorio"%>
<%@page import="net.sf.jasperreports.view.JasperViewer"%>
<%@page import="helpDesk.DAO.PatrimonioDAO"%>
<%@page import="helpDesk.DAO.PatrimonioDAO"%>
<%@page import="helpDesk.entidades.Patrimonio"%>
<%@page import="helpDesk.entidades.Patrimonio"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="helpDesk.DAO.HibernateConnection"%>
<%@page import="helpDesk.DAO.HibernateConnection"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="helpDesk.DAO.SetorDAO"%>
<%@page import="helpDesk.entidades.Setor"%>
<%@page import="prosperlib.util.HashUtils"%>
<%@page import="helpDesk.DAO.UsuarioDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.net.URL"%>
<%@page import="helpDesk.entidades.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String id = "4";
            String nome = "nome";
            String descricao = "descricao";
            String visibilidade = "1";
            String idTipoServico = "1";
            String status = "1";
            ServicoDAO dao = new ServicoDAO();
            dao.setClasse(Servico.class);
            dao.setSession(HibernateConnection.getSession());



            TipoServicoDAO sdao = new TipoServicoDAO();
            sdao.setClasse(TipoServico.class);
            sdao.setSession(HibernateConnection.getSession());
            TipoServico tiposervico = (TipoServico) sdao.get(Restrictions.eq("id", Integer.valueOf(idTipoServico)), "TipoServico.findAll");
            out.print(tiposervico);
            Servico serv = new Servico();
            serv.setNome(nome);
            serv.setDescricao(descricao);
            serv.setVisibilidade(Integer.valueOf(visibilidade));
            serv.setSituacao(Integer.valueOf(status));
            serv.setTipoServico(tiposervico);
            out.print(serv);
            dao.add(serv);

        %>
    </body>
</html>
