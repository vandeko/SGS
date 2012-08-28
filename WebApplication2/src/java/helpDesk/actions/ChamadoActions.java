/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpDesk.actions;

import gestordeprocessos.util.Configuracoes;
import gestordeprocessos.util.MySqlUtils;
import helpDesk.DAO.*;
import helpDesk.DAO.HibernateConnection;
import helpDesk.entidades.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ChamadoActions", urlPatterns = {"/ChamadoActions.do"})
public class ChamadoActions extends HttpServlet {

    Session ss = HibernateConnection.getSession();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession sessao = request.getSession();
        ChamadoDAO cDao = new ChamadoDAO();
        cDao.setClasse(Chamado.class);
        cDao.setSession(HibernateConnection.getSession());

        HashMap<String, Integer> caseHash = new HashMap<String, Integer>();

        caseHash.put("GRID", 1);
        caseHash.put("SAVE", 2);
        caseHash.put("STAT", 3);
        caseHash.put("STATCANC", 4);
        caseHash.put("GRIDABERTO", 5);
        caseHash.put("DATEGRID", 6);


        String mode = request.getParameter("mode");
        String m = "";
        if (mode == null) {
            m = request.getParameter("m");
            mode = m;
        }

        try {
            switch (caseHash.containsKey(mode) ? caseHash.get(mode) : -1) {
                case 1:
                    listar(request, out, sessao, cDao);
                    break;
                case 2:
                    if (request.getParameter("id").equals("")) {
                        inserir(request, out, sessao, cDao);
                    } else {
                        update(request, out, cDao);
                    }
                    break;
                case 3:
                    updateStatus(request, out, cDao);
                    break;
                case 4:
                    cancelaStatus(request, out, cDao);
                    break;
                case 5:
                    gridAberto(request, out, cDao);
                    break;
                case 6:
                    dateGrid(request, out, cDao);
                    break;

                default:
                    out.println("NÃO IMPLEMENTADO");
                    break;
            }

        } finally {
            out.close();
        }
    }

    private void listar(HttpServletRequest request, PrintWriter out, HttpSession sessao, ChamadoDAO cDao) {

        try {
            Usuario usr = (Usuario) sessao.getAttribute("usuarioSIST");


            UsuarioDAO dao = new UsuarioDAO();

            int page = Integer.parseInt(MySqlUtils.mysql_real_escape_string(request.getParameter("page")));
            String nome = MySqlUtils.mysql_real_escape_string(request.getParameter("sortname"));
            String ordem = MySqlUtils.mysql_real_escape_string(request.getParameter("sortorder"));
            String coluna = MySqlUtils.mysql_real_escape_string(request.getParameter("qtype"));
            String valorProcura = MySqlUtils.mysql_real_escape_string(request.getParameter("query"));
            int rp = Integer.parseInt(MySqlUtils.mysql_real_escape_string(request.getParameter("rp")));
            Criterion restrictions = null;
            Criterion restrict = null;

            if (!valorProcura.equals("")) {
                if (coluna.equals("usuario")) {
                    dao.setClasse(Usuario.class);

                    dao.setSession(HibernateConnection.getSession());
                    Usuario usuario = (Usuario) dao.get(Restrictions.eq("id", Integer.valueOf(valorProcura)), null, "Usuario.findAll");
                    restrictions = Restrictions.like(coluna, "%" + usuario + "%");
                } else {
                    restrictions = Restrictions.like(coluna, "%" + valorProcura + "%");
                }
            }
            if (usr != null) {
                restrict = Restrictions.eq("usuario", usr);
            }
            out.print(cDao.getGrid(page, nome, ordem, rp, restrictions, usr));
        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Listar Itens!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }
    }

    private void gridAberto(HttpServletRequest request, PrintWriter out, ChamadoDAO cDao) {

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

            out.print(cDao.getGridAberto(page, nome, ordem, rp, restrictions));
        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Listar Itens!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }
    }

    private void dateGrid(HttpServletRequest request, PrintWriter out, ChamadoDAO cDao) {
        String data = request.getParameter("showdate");
        String tipo = request.getParameter("viewtype");
        try {
            // out.print("edlaine");
            out.print(cDao.getDate());

        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Listar Itens!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }
    }

    private void inserir(HttpServletRequest request, PrintWriter out, HttpSession sessao, ChamadoDAO cDao) {
        try {
            Chamado ch = new Chamado();
            Usuario user = (Usuario) sessao.getAttribute("usuarioSIST");
            String descriPro = request.getParameter("descriProblema");

            String idSetor = request.getParameter("idSetor");
            String idPatrimonio = request.getParameter("idPatrimonio");
            int status = 3;
            out.print(descriPro + "-" + user.getId() + "-" + idSetor + "-" + idPatrimonio);



            SetorDAO sdao = new SetorDAO();
            sdao.setClasse(Setor.class);
            sdao.setSession(HibernateConnection.getSession());
            Setor setor = (Setor) sdao.get(Restrictions.eq("id", Integer.valueOf(idSetor)), "Setor.findAll");

            out.print("==============passou aqui1 " + setor);
            PatrimonioDAO pdao = new PatrimonioDAO();
            pdao.setClasse(Patrimonio.class);
            pdao.setSession(HibernateConnection.getSession());

            if (idPatrimonio != null && !idPatrimonio.equals("")) {
                Patrimonio patrimonio = (Patrimonio) pdao.get(Restrictions.eq("id", Integer.valueOf(idPatrimonio)), "Patrimonio.findAll");
                if (patrimonio != null) {
                    ch.setPatrimonio(patrimonio);
                    out.print("==============passou aqui1 ");
                }
            }
            ch.setDataAber(new Date(System.currentTimeMillis()));
            out.print("==============passou aqui2 ");
            ch.setDescrProblema(descriPro);
            ch.setUsuario(user);
            ch.setSetor(setor);
            ch.setSituacao(status);
            out.print("passou aqui: " + ch.getDataAber());
            if (cDao.add(ch)) {
                out.print(Configuracoes.FLEXIGRID_RELOAD.getConfig());
            } else {
                out.println("<script>alertError('Erro ao Adicionar Item!<BR>Por favor tente novamente.','2');</script>");
            }
        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Adicionar Item!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }


    }

    private void update(HttpServletRequest request, PrintWriter out, ChamadoDAO mDao) {
        try {
            String id = request.getParameter("id");
            Chamado ch = (Chamado) mDao.get(Restrictions.eq("id", Integer.valueOf(id)), "Chamado.findAll");
            String dataAgenda = "";
            int status = 0;
            if (request.getParameter("dataAgendamento") != null) {
                dataAgenda = request.getParameter("dataAgendamento");
                if (dataAgenda != null) {
                    Date data = Date.valueOf(dataAgenda);
                    ch.setDataAgen(data);
                    status = 5;
                }
            } else {
                status = 3;
            }
            ch.setSituacao(status);
            if (mDao.update(ch)) {
                out.print(Configuracoes.FLEXIGRID_RELOAD.getConfig());
            } else {
                out.println("<script>alertError('Erro ao Atualizar Item!<BR>Por favor tente novamente.','2');</script>");
            }
        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Atualizar Item!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }
    }

    private void updateStatus(HttpServletRequest request, PrintWriter out, ChamadoDAO mDao) {
        String id = request.getParameter("id");
        try {
            Chamado md = (Chamado) mDao.get(Restrictions.eq("id", Integer.valueOf(id)), "Chamado.findAll");
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

    private void cancelaStatus(HttpServletRequest request, PrintWriter out, ChamadoDAO mDao) {
       String id = request.getParameter("id");
        try {
            Chamado md = (Chamado) mDao.get(Restrictions.eq("id", Integer.valueOf(id)), "Chamado.findAll");
            md.setSituacao(6);
            if (mDao.update(md)) {
                out.print(Configuracoes.FLEXIGRID_RELOAD.getConfig());
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
