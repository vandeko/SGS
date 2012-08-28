/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpDesk.DAO;

import gestordeprocessos.util.Criterias;
import helpDesk.entidades.Setor;
import helpDesk.entidades.Usuario;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import prosperlib.logging.Log;
import prosperlib.util.HashUtils;

/**
 *
 * @author EDLAINE
 */
public class UsuarioDAO implements DAO {

    public UsuarioDAO() {
    }
    private Session session;
    private Class classe;

    public JSONObject getGrid(int page, String nome, String ordem, int rp, Criterion restrictions) {

        JSONObject rows = new JSONObject();
        JSONArray jArray = new JSONArray();
        List list = null;
        list = list(restrictions, "Usuario.findAll", nome, ordem, page, rp);

        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Usuario usu = (Usuario) list.get(i);
                try {
                    JSONObject obj = new JSONObject();

                    obj.append("id", usu.getId());
                    obj.append("cell", usu.getId());
                    obj.append("cell", usu.getNome());
                    obj.append("cell", usu.getSetor().getNome());
                    if (usu.getPermissao() == 0) {
                        obj.append("cell", "Gerente");
                    } else if (usu.getPermissao() == 1) {
                        obj.append("cell", "Moderador");
                    } else if (usu.getPermissao() == 2) {
                        obj.append("cell", "Estagiario");
                    } else if (usu.getPermissao() == 3) {
                        obj.append("cell", "Comum");
                    } else if (usu.getPermissao() == 4) {
                        obj.append("cell", "Avançado");
                    }
                    obj.append("cell", usu.getCargo());
                    obj.append("cell", usu.getStatus());
                    jArray.put(obj);

                    rows.put("page", page);
                    rows.put("total", list.size());
                    rows.put("rows", jArray);
                } catch (JSONException ex) {
                    Logger.getLogger(SetorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return rows;
    }

    public List<Object> listAll(Criterion crtrn, String nameQuery) {
        getSession().getNamedQuery(nameQuery);
        Criteria c = null;
        if (crtrn != null) {
            c = getSession().createCriteria(getClasse()).add(crtrn);
        } else {
            c = getSession().createCriteria(getClasse());
        }

        return c.list();
    }

    public List<Object> list(Criterion crtrn, String nameQuery, String nome, String ordem, int page, int rp) {
        getSession().getNamedQuery(nameQuery);
        Criteria c = null;

        if (crtrn != null && !crtrn.equals("")) {
            c = getSession().createCriteria(getClasse()).add(crtrn).setFirstResult(page - 1).setMaxResults(rp);
        } else if (page != 0 && rp != 0) {
            c = getSession().createCriteria(getClasse()).setFirstResult(page - 1).setMaxResults(rp);
        }
        if (ordem.equals("asc")) {
            c.addOrder(Order.asc(nome));
        } else if (ordem.equals("desc")) {
            c.addOrder(Order.desc(nome));
        }
        return c.list();
    }

    public Object get(Criterion crtrn, Criterion crtn1, String nameQuery) {
        getSession().getNamedQuery(nameQuery);
        Criteria c = null;
        if (crtn1 == null) {
            c = getSession().createCriteria(getClasse()).add(crtrn);
            return (Object) c.uniqueResult();
        } else {
            c = getSession().createCriteria(getClasse()).add(crtrn).add(crtn1);
            return (Object) c.uniqueResult();
        }
    }

    public boolean add(Object obj) {
        boolean sucess = true;
        try {
            getSession().getTransaction().begin();
            getSession().persist(obj);
            getSession().getTransaction().commit();
        } catch (Exception e) {
            sucess = false;
        }
        return sucess;
    }

    public boolean update(Object obj) {
        boolean sucess = true;
        try {
            getSession().getTransaction().begin();
            getSession().persist(obj);
            getSession().update(obj);
            getSession().getTransaction().commit();
        } catch (Exception e) {
            sucess = false;
        }
        return sucess;
    }

    public boolean delete(Object obj) {
        boolean sucess = true;
        try {
            getSession().getTransaction().begin();
            getSession().delete(obj);
            getSession().getTransaction().commit();
        } catch (Exception e) {
            sucess = false;
        }
        return sucess;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Class getClasse() {
        return classe;
    }

    public void setClasse(Class classe) {
        this.classe = classe;
    }

    public static Usuario autenticaUsuario(char[] matricula) {
        System.out.println(matricula);

        String matriculaHash = HashUtils.shaHash(matricula);
        //System.out.println(matriculaHash);       


        HibernateConnection hconexao = new HibernateConnection();
        List<Usuario> usuarios = hconexao.getEntidades(Usuario.class, Criterias.getMatriculaCriteria(matriculaHash));
        System.out.println(usuarios);
        if (usuarios != null && usuarios.size() == 1) {
            return usuarios.get(0);
        } else {
            return null;
        }
    }

    public static Usuario autenticaUsuario(int login, char[] senha) {
        HibernateConnection hconexao = new HibernateConnection();
        List<Usuario> usuarios = hconexao.getEntidades(Usuario.class, Criterias.getLoginCriteria(login));
        if (usuarios == null) {
            return null;
        } else if (usuarios.size() == 0) {
            return null;
        } else if (usuarios.size() == 1) {
            return autenticaUsuario(senha);

        } else {
            Log.sistema().severe("Falha de integridade do banco de dados - Mais que um usuario com o mesmo login!");
            return null;
        }

    }

    @Override
    public Object get(Criterion crtrn, String nameQuery) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
