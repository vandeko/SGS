/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpDesk.DAO;

import helpDesk.entidades.TipoServico;
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

/**
 *
 * @author EDLAINE
 */
public class TipoServicoDAO implements DAO {

    private Session session;
    private Class classe;

    public JSONObject getGrid(int page, String nome, String ordem, int rp, Criterion restrictions) {

        JSONObject rows = new JSONObject();
        JSONArray jArray = new JSONArray();
        List list = null;
        list = list(restrictions, "TipoServico.findAll", nome, ordem, page, rp);

        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                TipoServico tp = (TipoServico) list.get(i);
                try {
                    JSONObject obj = new JSONObject();

                    obj.append("id", tp.getId());
                    obj.append("cell", tp.getId());
                    obj.append("cell", tp.getNome());
                    obj.append("cell", tp.getDescricao());
                    obj.append("cell", tp.getSituacao());
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
        if (crtrn != null) {
            c = getSession().createCriteria(getClasse()).add(crtrn).setFirstResult(page-1).setMaxResults(rp);
        } else if (page != 0 && rp != 0) {
            c = getSession().createCriteria(getClasse()).setFirstResult(page-1).setMaxResults(rp);
        } else {
            c = getSession().createCriteria(getClasse());
        }
        if (ordem.equals("asc")) {
            c.addOrder(Order.asc(nome));
        } else if (ordem.equals("desc")) {
            c.addOrder(Order.desc(nome));
        }
        return c.list();
    }

    public Object get(Criterion crtrn, String nameQuery) {
        getSession().getNamedQuery(nameQuery);
        Criteria c = null;
        c = getSession().createCriteria(getClasse()).add(crtrn);
        return (Object) c.uniqueResult();
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
}
