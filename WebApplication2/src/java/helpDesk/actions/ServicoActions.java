/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpDesk.actions;

import gestordeprocessos.util.Configuracoes;
import gestordeprocessos.util.MySqlUtils;
import helpDesk.DAO.HibernateConnection;
import helpDesk.DAO.ServicoDAO;
import helpDesk.DAO.TipoServicoDAO;
import helpDesk.entidades.Servico;
import helpDesk.entidades.TipoServico;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author EDLAINE
 */
@WebServlet(name = "ServicoActions", urlPatterns = {"/ServicoActions.do"})
public class ServicoActions extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    Session ss = HibernateConnection.getSession();

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession sessao = request.getSession();
        ServicoDAO dao = new ServicoDAO();
        dao.setClasse(Servico.class);
        dao.setSession(HibernateConnection.getSession());

        HashMap<String, Integer> caseHash = new HashMap<String, Integer>();

        caseHash.put("GRID", 1);
        caseHash.put("SAVE", 2);
        caseHash.put("STAT", 3);
        caseHash.put("LISTSERVICO", 4);

        String mode = request.getParameter("mode");
        String m = "";
        if (mode == null) {
            m = request.getParameter("m");
            mode = m;
        }

        try {
            switch (caseHash.containsKey(mode) ? caseHash.get(mode) : -1) {
                case 1:
                    listar(request, out, sessao, dao);
                    break;
                case 2:
                    if (request.getParameter("id").equals("")) {
                        inserir(request, out, dao);
                    } else {
                        update(request, out, dao);
                    }
                    break;
                case 3:
                    updateStatus(request, out, dao);
                    break;
                case 4:
                    listaServico(request, out, dao);
                    break;
                default:
                    out.println("NÃO IMPLEMENTADO");
                    break;
            }

        } finally {
            out.close();
        }
    }

    private void listar(HttpServletRequest request, PrintWriter out, HttpSession sessao, ServicoDAO dao) {

        try {
            int page = Integer.parseInt(MySqlUtils.mysql_real_escape_string(request.getParameter("page")));
            String nome = MySqlUtils.mysql_real_escape_string(request.getParameter("sortname"));
            String ordem = MySqlUtils.mysql_real_escape_string(request.getParameter("sortorder"));
            String coluna = MySqlUtils.mysql_real_escape_string(request.getParameter("qtype"));
            String valorProcura = MySqlUtils.mysql_real_escape_string(request.getParameter("query"));
            int rp = Integer.parseInt(MySqlUtils.mysql_real_escape_string(request.getParameter("rp")));
            Criterion restrictions = null;
            if (!valorProcura.equals("")) {
                restrictions = Restrictions.like(coluna, "%" + valorProcura + "%");
            }
            
            out.print(dao.getGrid(page, nome, ordem, rp, restrictions));
        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Listar Itens!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }
    }

    private void inserir(HttpServletRequest request, PrintWriter out, ServicoDAO dao) {
        try {
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            String visibilidade = request.getParameter("visibilidade");
            String idTipoServico = request.getParameter("idTipo");
            String status = request.getParameter("status");

            TipoServicoDAO sdao = new TipoServicoDAO();
            sdao.setClasse(TipoServico.class);
            sdao.setSession(HibernateConnection.getSession());
            TipoServico Tiposervico = (TipoServico) sdao.get(Restrictions.eq("id", Integer.valueOf(idTipoServico)), "TipoServico.findAll");

            Servico serv = new Servico();
            serv.setNome(nome);
            serv.setDescricao(descricao);
            serv.setVisibilidade(Integer.valueOf(visibilidade));
            serv.setSituacao(Integer.valueOf(status));
            serv.setTipoServico(Tiposervico);
            if (dao.add(serv)) {
                out.print(Configuracoes.FLEXIGRID_RELOAD.getConfig());
            } else {
                out.println("<script>alertError('Erro ao Adicionar Item!<BR>Por favor tente novamente.','2');</script>");
            }
        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Adicionar Item!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }
    }

    private void update(HttpServletRequest request, PrintWriter out, ServicoDAO dao) {
        try {
            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            String visibilidade = request.getParameter("visibilidade");
            String idTipoServico = request.getParameter("idTipo");
            String status = request.getParameter("status");

            Servico serv = (Servico) dao.get(Restrictions.eq("id", Integer.valueOf(id)), "Servico.findAll");
            TipoServicoDAO sdao = new TipoServicoDAO();
            sdao.setClasse(TipoServico.class);
            sdao.setSession(HibernateConnection.getSession());
            TipoServico Tiposervico = (TipoServico) sdao.get(Restrictions.eq("id", Integer.valueOf(idTipoServico)), "TipoServico.findAll");

            serv.setNome(nome);
            serv.setDescricao(descricao);
            serv.setVisibilidade(Integer.valueOf(visibilidade));
            serv.setSituacao(Integer.valueOf(status));
            serv.setTipoServico(Tiposervico);

            if (dao.update(serv)) {
                out.print(Configuracoes.FLEXIGRID_RELOAD.getConfig());
            } else {
                out.println("<script>alertError('Erro ao Atualizar Item!<BR>Por favor tente novamente.','2');</script>");
            }
        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Atualizar Item!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }
    }

    private void updateStatus(HttpServletRequest request, PrintWriter out, ServicoDAO mDao) {
        String id = request.getParameter("id");
        try {
            Servico md = (Servico) mDao.get(Restrictions.eq("id", Integer.valueOf(id)), "Modulo.findAll");
            md.setSituacao(Integer.valueOf(request.getParameter("acao")));
            if (mDao.update(md)) {
                out.print(Configuracoes.FLEXIGRID_RELOAD.getConfig());
            } else {
                out.println("<script>alertError('Erro ao Alterar Status do Item!<BR>Por favor tente novamente.','2');</script>");
            }
        } catch (Exception e) {
            out.println("<script>alertError('Erro no Banco de Dados!<BR>" + e.getMessage() + ".','2');</script>");
        }
    }

    private void listaServico(HttpServletRequest request, PrintWriter out, ServicoDAO mDao) {

        TipoServicoDAO eDao = new TipoServicoDAO();
        eDao.setClasse(TipoServico.class);
        eDao.setSession(HibernateConnection.getSession());
        try {
            String tipo = request.getParameter("tipoServico");
            TipoServico servico = (TipoServico) eDao.get(Restrictions.eq("nome", tipo), "TipoServico.findAll");
            out.print(mDao.getServico(servico));
        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Listar Cidades!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
