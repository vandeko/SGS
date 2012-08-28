package org.apache.jsp._005fmodulos._005femitirRelatorio._005ffiles;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import sun.swing.PrintColorUIResource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import helpDesk.DAO.HibernateConnection;
import java.util.ArrayList;
import helpDesk.entidades.Usuario;
import helpDesk.DAO.UsuarioDAO;
import helpDesk.entidades.Setor;
import helpDesk.DAO.SetorDAO;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import helpDesk.entidades.Chamado;
import helpDesk.DAO.ChamadoDAO;
import javax.swing.JOptionPane;

public final class admin_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
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
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-type\" content=\"text/html; charset=UTF-8\"/>\n");
      out.write("        <script type=\"text/javascript\">\t\n");
      out.write("            \n");
      out.write("            mostraDiv = function(src) {\n");
      out.write("                var ativo = \"\";\n");
      out.write("                if (ativo != src) {\n");
      out.write("\n");
      out.write("                    $('#'+ativo).slideToggle('slow', null);\n");
      out.write("\n");
      out.write("                    $('#tit_'+ativo).css({\n");
      out.write("                        color: '#333',\n");
      out.write("                        fontWeight: 'bold',\n");
      out.write("                        backgroundColor: \"#efefef\"            \n");
      out.write("                    });\n");
      out.write("   \n");
      out.write("                    $('#'+src).slideToggle('slow', null);\n");
      out.write("\n");
      out.write("                    $('#tit_'+src).css({\n");
      out.write("                        color: '#00478b',\n");
      out.write("                        fontWeight: \"bold\",\n");
      out.write("                        backgroundColor: \"#efefff\"\n");
      out.write("                    });\n");
      out.write("\n");
      out.write("                    ativo = src;\n");
      out.write("                } \n");
      out.write("                if(src==\"graficoFuncionario\"){\n");
      out.write("                    \n");
      out.write("            ");
 List usuario = null;
                UsuarioDAO udao = new UsuarioDAO();
                udao.setClasse(Usuario.class);
                udao.setSession(HibernateConnection.getSession());
                usuario = udao.listAll(null, "Usuario.findAll");
                if (usuario != null) {
      out.write("\n");
      out.write("                            var myData = new Array( \n");
      out.write("            ");
 for (int i = 0; i < usuario.size(); i++) {
                    Usuario user = (Usuario) usuario.get(i);
                    int count = HibernateConnection.getSession().createQuery("select count(*) from DetalheChamado where usuario =" + user.getId() + "").uniqueResult().hashCode();
            
      out.write("\n");
      out.write("                \n");
      out.write("                        ['");
out.print(user.getNome());
      out.write('\'');
      out.write(',');
out.print(count);
      out.write("] \n");
      out.write("            ");
 if (i < usuario.size() - 1) {
                        out.print(",");
                    } else {
                        out.print("");
                    }
                }
      out.write(");\n");
      out.write("                            var grafico = new JSChart('graficoFuncionario', 'bar');\n");
      out.write("                            grafico.setDataArray(myData);\n");
      out.write("                            grafico.setTitle('Funcionario que realizaram os chamados');\n");
      out.write("                            \n");
      out.write("            ");
}
      out.write("              \n");
      out.write("               \n");
      out.write("                    }else if(src==\"graficoSetor\"){\n");
      out.write("            ");

                List setor = null;
                SetorDAO sdao = new SetorDAO();
                sdao.setClasse(Setor.class);
                sdao.setSession(HibernateConnection.getSession());
                setor = sdao.listAll(null, "Setor.findAll");

                if (setor != null) {
      out.write("\n");
      out.write("                            var myData1 = new Array( \n");
      out.write("            ");
for (int i = 0; i < setor.size(); i++) {
                    Setor set = (Setor) setor.get(i);
                    int count = HibernateConnection.getSession().createQuery("select count(*) from Chamado where setor =" + set.getId() + "").uniqueResult().hashCode();
            
      out.write("\n");
      out.write("                        ['");
out.print(set.getNome());
      out.write('\'');
      out.write(',');
out.print(count);
      out.write("] \n");
      out.write("            ");
 if (i < setor.size() - 1) {
                        out.print(",");
                    } else {
                        out.print("");
                    }
                }
      out.write(");\n");
      out.write("                            var grafico = new JSChart('graficoSetor', 'bar');\n");
      out.write("                            grafico.setDataArray(myData1);\n");
      out.write("                            grafico.setTitle('Setores com incidência de abertura de chamados');\n");
      out.write("                            \n");
      out.write("            ");
}
      out.write("\n");
      out.write("                \n");
      out.write("                    }else if(src==\"grafico\"){\n");
      out.write("            ");
  List lista = new ArrayList();
                lista.add(0);
                lista.add(1);
                lista.add(2);
                lista.add(3);
                lista.add(4);
                if (lista != null) {
      out.write("\n");
      out.write("                            var myDat = new Array(               \n");
      out.write("            ");
for (int i = 0; i < lista.size(); i++) {
                    int count = HibernateConnection.getSession().createQuery("select count(*) from Chamado where situacao=" + lista.get(i) + "").uniqueResult().hashCode();
            
      out.write("\n");
      out.write("                        ['");
if (lista.get(i).equals(0)) {
                                out.print("Aguardando Atendimento");
                            } else if (lista.get(i).equals(1)) {
                                out.print("Em Atendimento");
                            } else if (lista.get(i).equals(2)) {
                                out.print("Aguardando Agendamento");
                            } else if (lista.get(i).equals(3)) {
                                out.print("Cancelado");
                            } else if (lista.get(i).equals(4)) {
                                out.print("Encerrado");
                            }
      out.write('\'');
      out.write(',');
out.print(count);
      out.write("] \n");
      out.write("            ");
 if (i < lista.size() - 1) {
                        out.print(",");
                    } else {
                        out.print("");
                    }
                }
      out.write(");\n");
      out.write("                                var grafico = new JSChart('grafico', 'bar');\n");
      out.write("                                grafico.setDataArray(myDat);\n");
      out.write("                                grafico.setTitle('Status dos chamados');\n");
      out.write("                            \n");
      out.write("            ");
}
      out.write("                \n");
      out.write("                        }\n");
      out.write("                   \n");
      out.write("                        grafico.setTitleColor('#383535');\n");
      out.write("                        grafico.setAxisNameX('');\n");
      out.write("                        grafico.setAxisNameY('');\n");
      out.write("                        grafico.setAxisColor('#C4C4C4');\n");
      out.write("                        grafico.setAxisNameFontSize(16);\n");
      out.write("                        grafico.setAxisNameColor('#999');\n");
      out.write("                        grafico.setAxisValuesColor('#777');\n");
      out.write("                        grafico.setAxisColor('#B5B5B5');\n");
      out.write("                        grafico.setAxisWidth(1);\n");
      out.write("                        grafico.setBarValuesColor('#2F6D99');\n");
      out.write("                        grafico.setBarOpacity(0.5);\n");
      out.write("                        grafico.setAxisPaddingTop(60);\n");
      out.write("                        grafico.setAxisPaddingBottom(40);\n");
      out.write("                        grafico.setAxisPaddingLeft(45);\n");
      out.write("                        grafico.setTitleFontSize(12);\n");
      out.write("                        grafico.setBarBorderWidth(0);\n");
      out.write("                        grafico.setBarSpacingRatio(50);\n");
      out.write("                        grafico.setBarOpacity(0.9);\n");
      out.write("                        grafico.setFlagRadius(6);                \n");
      out.write("                        grafico.setTooltipOffset(3);\n");
      out.write("                        grafico.setSize(616, 321);\n");
      out.write("                        grafico.draw();\n");
      out.write("                \n");
      out.write("                    }\n");
      out.write("                    gerarRelatorio = function(){\n");
      out.write("            ");

                try {
                    JasperExportManager.exportReportToPdfFile("D:/exemplo1.jrprint", "D:/RelatorioClientes.pdf");
                } catch (JRException ex) {
                    out.print(ex.getMessage());
                }
            
      out.write("\n");
      out.write("                    }\n");
      out.write("                \n");
      out.write("                \n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"navegacao\" style=\"height: 590px\">\n");
      out.write("            <div id=\"formulario\">\n");
      out.write("                <form id=\"idForm\" action=\"\" method=\"POST\" target=\"#processa\">\n");
      out.write("                    <div id=\"chart_div\"></div>\n");
      out.write("                    <input type=\"hidden\" name=\"mode\" value=\"SAVE\"/>\n");
      out.write("                    <input type=\"hidden\" id=\"idId\" name=\"id\" value=\"\"/>\n");
      out.write("                    <ul class=\"markup\">\n");
      out.write("                        <li class=\"porPeriodo\">\n");
      out.write("                            <div style=\"padding-top: 10px;text-align: center\">\n");
      out.write("                                <input type=\"radio\" name=\"group\" value=\"Water\"style=\"padding: 10px;\"onclick=\"mostraDiv('graficoSetor')\">Por Setor\n");
      out.write("                                <input type=\"radio\" name=\"group\" value=\"Beer\"style=\"padding: 10px;\"onclick=\"mostraDiv('grafico')\">Chamado\n");
      out.write("                                <input type=\"radio\" name=\"group\" value=\"Wine\"style=\"padding: 10px;\"onclick=\"mostraDiv('graficoFuncionario')\">Funcionario \n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"clear\"></div>\n");
      out.write("                        </li>\n");
      out.write("\n");
      out.write("                        <li>\n");
      out.write("                            <div id=\"grafico\" style=\"display: none\">Loading...\n");
      out.write("                                <div class=\"right rightFix\" style=\"width: 126px;\">\n");
      out.write("                                    <div class=\"btnPadraoR\" onclick=\"gerarRelatorio()\">Relatorio</div>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"clear\"></div>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                            <div class=\"clear\"></div>\n");
      out.write("                        </li>\n");
      out.write("                        <li>\n");
      out.write("                            <div id=\"graficoFuncionario\" style=\"display: none\">Loading...\n");
      out.write("                                <div class=\"right rightFix\" style=\"width: 126px;\">\n");
      out.write("                                    <div class=\"btnPadraoR\" onclick=\"gerarRelatorio()\">Relatorio</div>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"clear\"></div>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                            <div class=\"clear\"></div>\n");
      out.write("                        </li>\n");
      out.write("                        <li>\n");
      out.write("                            <div id=\"graficoSetor\" style=\"display: none\">Loading...\n");
      out.write("                                <div class=\"right rightFix\" style=\"width: 126px;\">\n");
      out.write("\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"btnPadraoR\" onclick=\"gerarRelatorio()\">Relatorio</div>\n");
      out.write("                                <div class=\"clear\"></div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"clear\"></div>\n");
      out.write("                        </li>\n");
      out.write("                        <div class=\"clear\"></div>\n");
      out.write("                    </ul>\n");
      out.write("                </form>\n");
      out.write("                <div class=\"clear\"></div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"clear\"></div>\n");
      out.write("        </div>\n");
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
