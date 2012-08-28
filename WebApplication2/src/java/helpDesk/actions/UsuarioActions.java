package helpDesk.actions;

import gestordeprocessos.util.Configuracoes;
import gestordeprocessos.util.MySqlUtils;
import helpDesk.DAO.HibernateConnection;
import helpDesk.DAO.SetorDAO;
import helpDesk.DAO.UsuarioDAO;
import helpDesk.entidades.Setor;
import helpDesk.entidades.Usuario;
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
import prosperlib.util.HashUtils;

@WebServlet(name = "UsuarioActions", urlPatterns = {"/UsuarioActions.do"})
public class UsuarioActions extends HttpServlet {

    private Session ss = HibernateConnection.getSession();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession sessao = request.getSession();
        UsuarioDAO dao = new UsuarioDAO();
        dao.setClasse(Usuario.class);
        dao.setSession(HibernateConnection.getSession());

        HashMap<String, Integer> caseHash = new HashMap<String, Integer>();

        caseHash.put("GRID", 1);
        caseHash.put("SAVE", 2);
        caseHash.put("STAT", 3);
        caseHash.put("LOGIN", 4);
        caseHash.put("UNICOLOGIN", 5);
        caseHash.put("UNICOID", 6);

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
                    logar(request, response, out, dao);
                    break;
                case 5:
                    unicoLogin(request, out, dao);
                    break;
                case 6:
                    unicoId(request, out, dao);
                    break;
                default:
                    out.println("NÃO IMPLEMENTADO");
                    break;
            }

        } finally {
            out.close();
        }
    }

    private void listar(HttpServletRequest request, PrintWriter out, HttpSession sessao, UsuarioDAO dao) {

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

    private void inserir(HttpServletRequest request, PrintWriter out, UsuarioDAO dao) {
        try {
            String matricula = request.getParameter("matricula");
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String cargo = request.getParameter("cargo");
            String idsetor = request.getParameter("idSetor");
            String telefone = request.getParameter("telefone");
            String permissao = request.getParameter("permissao");
            String senha = HashUtils.shaHash(request.getParameter("senha"));
            String status = request.getParameter("status");

            SetorDAO sdao = new SetorDAO();
            sdao.setClasse(Setor.class);
            sdao.setSession(HibernateConnection.getSession());

            Setor setor = (Setor) sdao.get(Restrictions.eq("id", Integer.valueOf(idsetor)), "Setor.findAll");

            Usuario usuario = new Usuario();
            if (matricula != "" && permissao != "") {
                usuario.setId(Integer.valueOf(matricula));
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setCargo(cargo);
                usuario.setPass(senha);
                usuario.setTelefone(telefone);
                usuario.setStatus(Integer.valueOf(status));
                usuario.setPermissao(Integer.parseInt(permissao));
                usuario.setSetor(setor);

            } else {
                out.println("<script>alertError('Erro o campo Matricula não pode estar vazio !<BR>Por favor tente novamente.','2');</script>");
            }
            if (dao.add(usuario)) {
                out.print(Configuracoes.FLEXIGRID_RELOAD.getConfig());
            } else {
                out.println("<script>alertError('Erro ao Adicionar Item!<BR>Por favor tente novamente.','2');</script>");
            }
        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Adicionar Item!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }


    }

    private void update(HttpServletRequest request, PrintWriter out, UsuarioDAO dao) {
        try {
            String matricula = request.getParameter("matricula");
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String cargo = request.getParameter("cargo");
            String idsetor = request.getParameter("idSetor");
            String telefone = request.getParameter("telefone");
            String permissao = request.getParameter("permissao");
            String senha = HashUtils.shaHash(request.getParameter("senha"));
            String status = request.getParameter("status");

            SetorDAO sdao = new SetorDAO();
            sdao.setClasse(Setor.class);
            sdao.setSession(HibernateConnection.getSession());

            Setor setor = (Setor) sdao.get(Restrictions.eq("id", Integer.valueOf(idsetor)), "Setor.findAll");

            Usuario usuario = (Usuario) dao.get(Restrictions.eq("id", Integer.valueOf(matricula)), null, "Usuario.findAll");

            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setCargo(cargo);
            usuario.setPass(senha);
            usuario.setTelefone(telefone);
            usuario.setStatus(Integer.valueOf(status));
            usuario.setPermissao(Integer.parseInt(permissao));
            usuario.setSetor(setor);

            if (dao.update(usuario)) {
                out.print(Configuracoes.FLEXIGRID_RELOAD.getConfig());
            } else {
                out.println("<script>alertError('Erro ao Atualizar Item!<BR>Por favor tente novamente.','2');</script>");
            }
        } catch (Exception ex) {
            out.println("<script>alertError('Erro ao Atualizar Item!<BR>" + ex.getMessage() + "<BR>Por favor tente novamente.','2');</script>");
        }
    }

    private void updateStatus(HttpServletRequest request, PrintWriter out, UsuarioDAO dao) {
        String id = request.getParameter("id");
        try {
            Usuario usuario = (Usuario) dao.get(Restrictions.eq("id", Integer.valueOf(id)), null, "Usuario.findAll");
            usuario.setStatus(Integer.valueOf(request.getParameter("acao")));
            if (dao.update(usuario)) {
                out.print(Configuracoes.FLEXIGRID_RELOAD.getConfig());
            } else {
                out.println("<script>alertError('Erro ao Alterar Status do Item!<BR>Por favor tente novamente.','2');</script>");
            }
        } catch (Exception e) {
            out.println("<script>alertError('Erro no Banco de Dados!<BR>" + e.getMessage() + ".','2');</script>");
        }
    }

    private void logar(HttpServletRequest request, HttpServletResponse response, PrintWriter out, UsuarioDAO dao) {
        try {
            String login = request.getParameter("login");
            String pass = request.getParameter("senha");

            Usuario user = login(Integer.valueOf(login), pass, dao);
            out.print(user);
            if (user == null) {
                out.println("<script>top.alerta('Dados de acesso não conferem!<BR>Por favor, tente novamente.','ATENÇÃO','','2','idLogin');$('#login').val('login');$('#senha').val('senha');</script>");
            } else {

                HttpSession sessao = request.getSession();
                sessao.setAttribute("usuarioSIST", user);

                out.println("<script>top.location.href='admin.jsp';</script>");

            }
        } catch (Exception ex) {
            out.print("ERRO:" + ex.getMessage());
        }
    }

    private Usuario login(int login, String pass, UsuarioDAO dao) {
        return dao.autenticaUsuario(login, pass.toCharArray());
    }

    private void unicoLogin(HttpServletRequest request, PrintWriter out, UsuarioDAO dao) {
        String email = request.getParameter("val");
        String id = request.getParameter("id");
        Usuario usuario = null;
        try {
            if (id.equals("")) {
                usuario = (Usuario) dao.get(Restrictions.eq("email", email), null, "Usuario.findAll");
                if (usuario == null) {
                    out.print(0);
                } else {
                    out.print(1);
                }
            } else {
                usuario = (Usuario) dao.get(Restrictions.eq("email", email), Restrictions.ne("id", Integer.valueOf(id)), "Usuario.findAll");
                if (usuario == null) {
                    out.print(0);
                } else {
                    out.print(1);
                }
            }
        } catch (Exception e) {
            out.println("<script>alertError('Erro no Banco de Dados!<BR>" + e.getMessage() + ".','2');</script>");
        }

    }

    private void unicoId(HttpServletRequest request, PrintWriter out, UsuarioDAO dao) {

        String id = request.getParameter("val");
        Usuario usuario = null;
        try {
            if (id != null) {
                usuario = (Usuario) dao.get(Restrictions.eq("id", Integer.valueOf(id)), null, "Usuario.findAll");
                if (usuario == null) {
                    out.print(0);
                } else {
                    out.print(1);
                }
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
    /**
     * @return the ss
     */
}
