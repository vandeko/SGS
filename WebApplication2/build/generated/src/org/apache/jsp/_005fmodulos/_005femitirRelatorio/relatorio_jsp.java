package org.apache.jsp._005fmodulos._005femitirRelatorio;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class relatorio_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
      out.write("<style>\n");
      out.write("body {\n");
      out.write("\tfont-family:Arial, Helvetica, sans-serif;\n");
      out.write("}\n");
      out.write("table tr td {\n");
      out.write("\tpadding:15px;\n");
      out.write("}\n");
      out.write(".titulo {\n");
      out.write("\tbackground:#CCC;\n");
      out.write("\tcolor:#FFF;\n");
      out.write("\tpadding:7px;\n");
      out.write("\tfont-weight:bold;\n");
      out.write("}\n");
      out.write(".tituloColunas td{\n");
      out.write("\tbackground:#f5f4f4;\n");
      out.write("\tpadding:5px;\n");
      out.write("\tfont-size:12px;\n");
      out.write("\tfont-weight:bold;\n");
      out.write("\tborder-bottom:1px solid #CCC;\n");
      out.write("\ttext-align:left;\n");
      out.write("}\n");
      out.write(".linhaRegistros td{\n");
      out.write("\tpadding:5px;\n");
      out.write("\tborder-bottom:1px solid #CCC;\n");
      out.write("\tfont-size:12px;\n");
      out.write("\ttext-align:left;\n");
      out.write("\t\n");
      out.write("}\n");
      out.write(".data{\n");
      out.write("\ttext-align:right\n");
      out.write("}\n");
      out.write("</style>\n");
      out.write("<title>Relatório</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<table width=\"1024\" border=\"0\" align=\"center\" class=\"tabelaContainer\">\n");
      out.write("  <tr>\n");
      out.write("    <td><img src=\"topo.jpg\" border=\"0\" /></td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td class=\"titulo\">Funcionário</td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td>\n");
      out.write("    \t<table width=\"100%\" border=\"0\" align=\"center\">\n");
      out.write("        \t<tr class=\"tituloColunas\">\n");
      out.write("          \t\t<td>Numero do Chamado</td>\n");
      out.write("          \t\t<td>Aberto por</td>\n");
      out.write("          \t\t<td>Executando em Setor</td>\n");
      out.write("          \t\t<td>Executador por</td>\n");
      out.write("          \t\t<td>Data de Abertura</td>\n");
      out.write("        \t</tr>\n");
      out.write("        \t<tr class=\"linhaRegistros\">\n");
      out.write("          \t\t<td>1</td>\n");
      out.write("\t\t        <td>Maria Jose Camargo</td>\n");
      out.write("          \t\t<td>Administracao Financeira</td>\n");
      out.write("          \t\t<td>123</td>\n");
      out.write("          \t\t<td>11/07/12 00:00</td>\n");
      out.write("        \t</tr>\n");
      out.write("        \t<tr class=\"linhaRegistros\">\n");
      out.write("          \t\t<td>2</td>\n");
      out.write("\t\t        <td>Maria Jose Camargo</td>\n");
      out.write("          \t\t<td>Administracao Financeira</td>\n");
      out.write("          \t\t<td>123</td>\n");
      out.write("          \t\t<td>11/07/12 00:00</td>\n");
      out.write("        \t</tr>\n");
      out.write("        \t<tr class=\"linhaRegistros\">\n");
      out.write("          \t\t<td>4</td>\n");
      out.write("\t\t        <td>Maria Jose Camargo</td>\n");
      out.write("          \t\t<td>Administracao Financeira</td>\n");
      out.write("          \t\t<td>123</td>\n");
      out.write("          \t\t<td>11/07/12 00:00</td>\n");
      out.write("        \t</tr>\n");
      out.write("      \t</table>\n");
      out.write("      </td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("  \t<td class=\"data\">Sexta-feira 10 Agosto</td>\n");
      out.write("  </tr>\n");
      out.write("</table>\n");
      out.write("</body>\n");
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
