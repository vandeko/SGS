package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import helpDesk.DAO.ServicoDAO;
import helpDesk.entidades.TipoServico;
import helpDesk.DAO.TipoServicoDAO;
import helpDesk.entidades.Servico;
import java.io.File;
import helpDesk.entidades.Chamado;
import helpDesk.DAO.ChamadoDAO;
import javax.swing.JInternalFrame;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import java.util.HashMap;
import gestordeprocessos.util.repositorioProduto;
import net.sf.jasperreports.engine.JasperPrint;
import javax.swing.JOptionPane;
import gestordeprocessos.util.ExcRepositorio;
import net.sf.jasperreports.view.JasperViewer;
import helpDesk.DAO.PatrimonioDAO;
import helpDesk.DAO.PatrimonioDAO;
import helpDesk.entidades.Patrimonio;
import helpDesk.entidades.Patrimonio;
import org.hibernate.criterion.Restrictions;
import helpDesk.DAO.HibernateConnection;
import helpDesk.DAO.HibernateConnection;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.reflect.Array;
import helpDesk.DAO.SetorDAO;
import helpDesk.entidades.Setor;
import prosperlib.util.HashUtils;
import helpDesk.DAO.UsuarioDAO;
import java.util.List;
import java.net.URL;
import helpDesk.entidades.Usuario;

public final class teste_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            String id = "4";
            String nome = "nome";
            String descricao = "descricao";
            String visibilidade = "1";
            ///    String idTipoServico = "1";
            String status = "1";
            ServicoDAO dao = new ServicoDAO();
            dao.setClasse(Servico.class);
            dao.setSession(HibernateConnection.getSession());


            Servico serv = (Servico) dao.get(Restrictions.eq("id", Integer.valueOf(id)), "Servico.findAll");
            //   TipoServicoDAO sdao = new TipoServicoDAO();
            // sdao.setClasse(TipoServico.class);
            //sdao.setSession(HibernateConnection.getSession());
            //TipoServico tiposervico = (TipoServico) sdao.get(Restrictions.eq("id", Integer.valueOf(idTipoServico)), "TipoServico.findAll");
            //out.print(tiposervico);

            serv.setNome(nome);
            serv.setDescricao(descricao);
            serv.setVisibilidade(Integer.valueOf(visibilidade));
            serv.setSituacao(Integer.valueOf(status));
            // serv.setTipoServico(tiposervico);
            out.print(serv);
            dao.update(serv);
        
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
