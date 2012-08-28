package helpDesk.actions;

import gestordeprocessos.util.Configuracoes;
import gestordeprocessos.util.MySqlUtils;
import helpDesk.DAO.HibernateConnection;
import helpDesk.DAO.PatrimonioDAO;
import helpDesk.DAO.SetorDAO;
import helpDesk.entidades.Patrimonio;
import helpDesk.entidades.Setor;
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

@WebServlet(name = "PatrimonioActions", urlPatterns = {"/PatrimonioActions.do"})
public class PatrimonioActions extends HttpServlet {

    Session ss = HibernateConnection.getSession();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession sessao = request.getSession();
        PatrimonioDAO dao = new PatrimonioDAO();
        dao.setClasse(Patrimonio.class);
        dao.setSession(HibernateConnection.getSession());

        HashMap<String, Integer> caseHash = new HashMap<String, Integer>();

        caseHash.put("GRID", 1);
        caseHash.put("SAVE", 2);
        caseHash.put("STAT", 3);
        caseHash.put("UNICOPATRIMONIO", 4);
        caseHash.put("LISTSETOR", 5);

        String mode = request.getParameter("mode");
        String m = "";
        if (mode == null) {
            m = request.getParameter("m");
            mode = m;
        }

        try {
            switch (caseHash.containsKey(mode) ? caseHash.get(mode) : -1) {
                case 1:
                    listar(request, out, dao);
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
                    unicoPatrimonio(request, out, dao);
                    break;
                case 5:
                    listaSetor(request, out, dao);
                    break;
                default:
                    out.println("NÃO IMPLEMENTADO");
                    break;
            }

        } finally {
            out.close();
        }
    }

    private void listar(HttpServletRequest request, PrintWriter out, PatrimonioDAO dao) {

        try {
            int page = Integer.parseInt(MySqlUtils.mysql_real_escape_string(request.getParameter("page")));
            String nome = MySqlUtils.mysql_real_escape_string(request.getParameter("sortname"));
            String ordem = MySqlUtils.mysql_real_escape_string(request.getParameter("sortorder"));
            String coluna = MySqlUtils.mysql_real_escape_string(request.getParameter("qtype"));
            String valorProcura = MySqlUtils.mysql_real_escape_string(request.getParameter("query"));
            int rp = Integer.parseInt(MySqlUtils.mysql_real_escape_string(request.getParameter("rp")));
            Criterion restrictions = null;
            if (!valorProcura.equals("")) {
                if (coluna.equals("id")) {
                    restrictions = Restrictions.eq(coluna, Integer.valueOf(valorProcura));
                } else if (coluna.equals("situacao")) {
                    if (valorProcura.equals("ativado")) {
                        restrictions = Restrictions.eq(coluna, Integer.valueOf("1"));
                    } else if (valorProcura.equals("desativado")) {
                        restrictions = Restrictions.eq(coluna, Integer.valueOf("0"));
                    }
                } else if (coluna.equals("setor")) {
                    SetorDAO sdao = new SetorDAO();
                    sdao.setClasse(Setor.class);
                    sdao.setSession(HibernateConnection.getSession());
                    Setor setor=(Setor) sdao.get(Restrictions.eq("nome", valorProcura ),"Setor.findAll");
                    restrictions = Restrictions.eq(coluna, setor);
                } else {
                    restrictions = Restrictions.like(coluna, "%" + valorProcura + "%");
                }
            }
            out.print(dao.getGrid(page, nome, ordem, rp, restrictions));
        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Listar Itens!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }
    }

    private void inserir(HttpServletRequest request, PrintWriter out, PatrimonioDAO dao) {
        try {
            String nome = request.getParameter("nome");
            String codigo = request.getParameter("matricula");
            String descricao = request.getParameter("descricao");
            String idsetor = request.getParameter("idSetor");
            String status = request.getParameter("status");


            SetorDAO sdao = new SetorDAO();
            sdao.setClasse(Setor.class);
            sdao.setSession(HibernateConnection.getSession());

            Setor setor = (Setor) sdao.get(Restrictions.eq("id", Integer.valueOf(idsetor)), "Setor.findAll");

            out.print("SETOR: " + setor.getNome());
            out.print(nome + "-" + descricao + "-" + status + "-" + codigo);

            Patrimonio pt = new Patrimonio();
            pt.setId(Integer.valueOf(codigo));
            pt.setDescricao(descricao);
            pt.setNome(nome);
            pt.setSetor(setor);
            pt.setSituacao(Integer.valueOf(status));
            if (dao.add(pt)) {
                out.print(Configuracoes.FLEXIGRID_RELOAD.getConfig());
            } else {
                out.println("<script>alertError('Erro ao Adicionar Item!<BR>Por favor tente novamente.','2');</script>");
            }
        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Adicionar Item!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }


    }

    private void update(HttpServletRequest request, PrintWriter out, PatrimonioDAO dao) {
        try {
            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String codigo = request.getParameter("matricula");
            String descricao = request.getParameter("descricao");
            String idsetor = request.getParameter("idSetor");
            String status = request.getParameter("status");


            SetorDAO sdao = new SetorDAO();
            sdao.setClasse(Setor.class);
            sdao.setSession(HibernateConnection.getSession());

            Setor setor = (Setor) sdao.get(Restrictions.eq("id", Integer.valueOf(idsetor)), "Setor.findAll");

            out.print("SETOR: " + setor.getNome());
            out.print(nome + "-" + descricao + "-" + status + "-" + codigo);

            Patrimonio pt = (Patrimonio) dao.get(Restrictions.eq("id", Integer.valueOf(id)), "Patrimonio.findAll");
            pt.setId(Integer.valueOf(codigo));
            pt.setDescricao(descricao);
            pt.setNome(nome);
            pt.setSetor(setor);
            pt.setSituacao(Integer.valueOf(status));

            if (dao.update(pt)) {
                out.print(Configuracoes.FLEXIGRID_RELOAD.getConfig());
            } else {
                out.println("<script>alertError('Erro ao Atualizar Item!<BR>Por favor tente novamente.','2');</script>");
            }
        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Atualizar Item!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }
    }

    private void updateStatus(HttpServletRequest request, PrintWriter out, PatrimonioDAO dao) {
        String id = request.getParameter("id");
        try {
            Patrimonio pt = (Patrimonio) dao.get(Restrictions.eq("id", Integer.valueOf(id)), "Patrimonio.findAll");
            pt.setSituacao(Integer.valueOf(request.getParameter("acao")));
            if (dao.update(pt)) {
                out.print(Configuracoes.FLEXIGRID_RELOAD.getConfig());
            } else {
                out.println("<script>alertError('Erro ao Alterar Status do Item!<BR>Por favor tente novamente.','2');</script>");
            }
        } catch (Exception e) {
            out.println("<script>alertError('Erro no Banco de Dados!<BR>" + e.getMessage() + ".','2');</script>");
        }
    }

    private void unicoPatrimonio(HttpServletRequest request, PrintWriter out, PatrimonioDAO dao) {
        String id = request.getParameter("val");
        Patrimonio patrimonio = null;
        try {

            if (!id.equals("")) {
                patrimonio = (Patrimonio) dao.get(Restrictions.eq("id", Integer.valueOf(id)), "Patrimonio.findAll");
                if (patrimonio == null) {
                    out.print(0);
                } else {
                    out.print(1);
                }
            } else {
                out.print(patrimonio);
            }

        } catch (Exception e) {
            out.println("<script>alertError('Erro no Banco de Dados!<BR>" + e.getMessage() + ".','2');</script>");
        }

    }

    private void listaSetor(HttpServletRequest request, PrintWriter out, PatrimonioDAO mDao) {

        SetorDAO eDao = new SetorDAO();
        eDao.setClasse(Setor.class);
        eDao.setSession(HibernateConnection.getSession());
        try {
            String tipo = request.getParameter("setor");
            Patrimonio patrimonio = (Patrimonio) mDao.get(Restrictions.eq("id", Integer.valueOf(tipo)), "Patrimonio.findAll");
            Setor setor = (Setor) eDao.get(Restrictions.eq("id", patrimonio.getSetor().getId()), "Setor.findAll");
            out.print(mDao.getSetor(setor));
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
