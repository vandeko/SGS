/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpDesk.actions;

import gestordeprocessos.util.Configuracoes;
import gestordeprocessos.util.MySqlUtils;
import helpDesk.DAO.*;
import helpDesk.entidades.Chamado;
import helpDesk.entidades.DetalheChamado;
import helpDesk.entidades.Servico;
import helpDesk.entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
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
@WebServlet(name = "DetalheServicoActions", urlPatterns = {"/DetalheServicoActions.do"})
public class DetalheServicoActions extends HttpServlet {

    Session ss = HibernateConnection.getSession();

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession sessao = request.getSession();
        DetalheChamadoDAO mDao = new DetalheChamadoDAO();
        mDao.setClasse(DetalheChamado.class);
        mDao.setSession(HibernateConnection.getSession());

        HashMap<String, Integer> caseHash = new HashMap<String, Integer>();

        caseHash.put("GRID", 1);
        caseHash.put("SAVE", 2);
        caseHash.put("STAT", 3);
        caseHash.put("DEL", 4);

        String mode = request.getParameter("mode");
        String m = "";
        if (mode == null) {
            m = request.getParameter("m");
            mode = m;
        }

        try {
            switch (caseHash.containsKey(mode) ? caseHash.get(mode) : -1) {
                case 1:
                    listar(request, out, sessao, mDao);
                    break;
                case 2:
                    if (request.getParameter("id").equals("")) {
                        inserir(request, out, mDao);
                    } else {
                        update(request, out, mDao);
                    }
                    break;
                case 3:
                    updateStatus(request, out, mDao);
                    break;

                default:
                    out.println("NÃO IMPLEMENTADO");
                    break;
            }

        } finally {
            out.close();
        }
    }

    private void listar(HttpServletRequest request, PrintWriter out, HttpSession sessao, DetalheChamadoDAO mDao) {

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
            //out.print(mDao.getGrid(page, nome, ordem, rp, restrictions));
        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Listar Itens!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }
    }

    private void inserir(HttpServletRequest request, PrintWriter out, DetalheChamadoDAO mDao) {
        try {
            String descriServico = request.getParameter("descriServico");
            String matricula = request.getParameter("matricula");
            String idServico = request.getParameter("idServico");
            String idChamado = request.getParameter("idChamado");
            DetalheChamado md = new DetalheChamado();

            UsuarioDAO udao = new UsuarioDAO();
            udao.setClasse(Usuario.class);
            udao.setSession(HibernateConnection.getSession());
            Usuario user = null;
            List userList = udao.listAll(Restrictions.eq("permissao", 1), "Usuario.findAll");
            if (matricula == null) {
                user = (Usuario) udao.get(Restrictions.eq("id", userList.get(1)), "Usuario.findAll");
            } else {
                user = (Usuario) udao.get(Restrictions.eq("id", Integer.valueOf(matricula)), "Usuario.findAll");
            }
            ServicoDAO sdao = new ServicoDAO();
            sdao.setClasse(Servico.class);
            sdao.setSession(HibernateConnection.getSession());
            Servico servico = (Servico) sdao.get(Restrictions.eq("id", Integer.valueOf(idServico)), "Servico.findAll");

            ChamadoDAO dao = new ChamadoDAO();
            dao.setClasse(Chamado.class);
            dao.setSession(HibernateConnection.getSession());
            Chamado chamado = (Chamado) dao.get(Restrictions.eq("id", Integer.valueOf(idChamado)), "Chamado.findAll");

            if (user != null) {
                md.setUsuario(user);
            }
            if (servico != null) {
                md.setServico(servico);
            }
            if (chamado != null) {
                md.setChamado(chamado);
            }
            md.setDescriServico(descriServico);

            md.setDataServico(new Date(System.currentTimeMillis()));
            if (mDao.add(md)) {
                out.print(Configuracoes.FLEXIGRID_RELOAD.getConfig());
            } else {
                out.println("<script>alertError('Erro ao Adicionar Item!<BR>Por favor tente novamente.','2');</script>");
            }
        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Adicionar Item!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }


    }

    private void update(HttpServletRequest request, PrintWriter out, DetalheChamadoDAO mDao) {
        try {
            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String nomeAdmin = request.getParameter("nomeAdmin");

            DetalheChamado md = (DetalheChamado) mDao.get(Restrictions.eq("id", Integer.valueOf(id)), "Modulo.findAll");

            if (mDao.update(md)) {
//                out.print(Configuracoes.FLEXIGRID_RELOAD.getConfig());
            } else {
                out.println("<script>alertError('Erro ao Atualizar Item!<BR>Por favor tente novamente.','2');</script>");
            }
        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Atualizar Item!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }
    }

    private void updateStatus(HttpServletRequest request, PrintWriter out, DetalheChamadoDAO mDao) {
        String id = request.getParameter("id");
        try {
            DetalheChamado md = (DetalheChamado) mDao.get(Restrictions.eq("id", Integer.valueOf(id)), "Modulo.findAll");

            if (mDao.update(md)) {
//                out.print(Configuracoes.FLEXIGRID_RELOAD.getConfig());
            } else {
                out.println("<script>alertError('Erro ao Alterar Status do Item!<BR>Por favor tente novamente.','2');</script>");
            }
        } catch (Exception e) {
            out.println("<script>alertError('Erro no Banco de Dados!<BR>" + e.getMessage() + ".','2');</script>");
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
