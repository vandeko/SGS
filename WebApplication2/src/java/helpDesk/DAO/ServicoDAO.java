package helpDesk.DAO;

import helpDesk.entidades.Servico;
import helpDesk.entidades.TipoServico;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author EDLAINE
 */
public class ServicoDAO implements DAO {

    private Session session;
    private Class classe;

    public JSONObject getServico(TipoServico tipo) {

        JSONObject rows = new JSONObject();

        List<Object> servicos = listAll(Restrictions.eq("tipoServico", tipo), "Servico.findAll");

        if (!servicos.isEmpty()) {
            try {

                for (int i = 0; i < servicos.size(); i++) {
                    Servico servico = (Servico) servicos.get(i);
                    rows.put(String.valueOf(servico.getId()), servico.getNome());
                }
            } catch (JSONException ex) {
                Logger.getLogger(SetorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rows;
    }

    public JSONObject getGrid(int page, String nome, String ordem, int rp, Criterion restrictions) {

        JSONObject rows = new JSONObject();
        JSONArray jArray = new JSONArray();
        List list = null;
        list = list(restrictions, "Servico.findAll", nome, ordem, page, rp);
        for (int i = 0; i < list.size(); i++) {
            Servico servico = (Servico) list.get(i);
            try {
                JSONObject obj = new JSONObject();

                obj.append("id", servico.getId());
                obj.append("cell", servico.getId());
                obj.append("cell", servico.getNome());
                obj.append("cell", servico.getTipoServico().getNome());
                obj.append("cell", servico.getSituacao());
                jArray.put(obj);

                rows.put("page", page);
                rows.put("total", list.size());
                rows.put("rows", jArray);
            } catch (JSONException ex) {
                Logger.getLogger(SetorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
