package helpDesk.DAO;

import helpDesk.entidades.Chamado;
import helpDesk.entidades.Usuario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChamadoDAO implements DAO {

    private Session session;
    private Class classe;

    public JSONObject getGrid(int page, String nome, String ordem, int rp, Criterion restrictions, Usuario user) {

        JSONObject rows = new JSONObject();
        JSONArray jArray = new JSONArray();
        List list = null;
        if (user == null) {
            list = listChamado(restrictions, "Chamado.findAll", nome, ordem, page, rp);
        } else {
            list = listAberto(restrictions, "Chamado.findAll", nome, ordem, page, rp, Restrictions.eq("usuario", user));
        }
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Chamado ch = (Chamado) list.get(i);
                try {
                    JSONObject obj = new JSONObject();

                    obj.append("id", ch.getId());
                    obj.append("cell", ch.getId());
                    obj.append("cell", ch.getDataAber());
                    obj.append("cell", ch.getDataEnc());
                    obj.append("cell", ch.getUsuario().getNome());
                    obj.append("cell", ch.getSetor().getNome());
                    obj.append("cell", ch.getSituacao());
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

    public JSONObject getGridAberto(int page, String nome, String ordem, int rp, Criterion restrictions) {

        JSONObject rows = new JSONObject();
        JSONArray jArray = new JSONArray();
        List list = null;
        Criterion cr = null;
        cr = Restrictions.or(Restrictions.or(Restrictions.eq("situacao", 4), Restrictions.eq("situacao", 5)), Restrictions.eq("situacao", 3));

        list = listAberto(restrictions, "Chamado.findAll", nome, ordem, page, rp, cr);

        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Chamado ch = (Chamado) list.get(i);
                try {
                    JSONObject obj = new JSONObject();

                    obj.append("id", ch.getId());
                    obj.append("cell", ch.getId());
                    obj.append("cell", ch.getDataAber());
                    obj.append("cell", ch.getDataEnc());
                    obj.append("cell", ch.getUsuario().getNome());
                    obj.append("cell", ch.getSetor().getNome());
                    obj.append("cell", ch.getSituacao());
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

    public JSONObject getDate(){

        JSONObject rows = new JSONObject();
        JSONArray jArray = new JSONArray();
        List list = null;
        Criterion crn = null;
        crn = Restrictions.isNotNull("dataAgen");
        list = listAll(crn, "Chamado.findAll");
        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat sdf1 = new SimpleDateFormat("HH:mm");
        DateFormat sdf2 = new SimpleDateFormat("HH:59");

        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                JSONArray jArray1 = new JSONArray();
                Chamado ch = (Chamado) list.get(i);

                jArray1.put("Servico");
                jArray1.put("Agendamento de servico");
                jArray1.put(sdf.format(ch.getDataAgen()) + " " + sdf1.format(ch.getHora()));
                jArray1.put(sdf.format(ch.getDataAgen()) + " " + sdf2.format(ch.getHora()));
                jArray.put(jArray1);
            }
        }
        try {
            rows.put("events", jArray);
        } catch (JSONException ex) {
            Logger.getLogger(ChamadoDAO.class.getName()).log(Level.SEVERE, null, ex);
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

        public List<Object> listAberto(Criterion crtrn, String nameQuery, String nome, String ordem, int page, int rp, Criterion cr) {
        getSession().getNamedQuery(nameQuery);
        Criteria c = null;

        if (crtrn != null && !crtrn.equals("")) {
            c = getSession().createCriteria(getClasse()).add(crtrn).add(cr).setFirstResult(page - 1).setMaxResults(rp);
        } else if (page != 0 && rp != 0) {
            c = getSession().createCriteria(getClasse()).add(cr).setFirstResult(page - 1).setMaxResults(rp);
        }
        if (ordem.equals("asc")) {
            c.addOrder(Order.asc(nome));
        } else if (ordem.equals("desc")) {
            c.addOrder(Order.desc(nome));
        }
        return c.list();
    }

    public List<Object> listChamado(Criterion crtrn, String nameQuery, String nome, String ordem, int page, int rp) {
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

    @Override
    public List<Object> list(Criterion crtrn, String nameQuery, String nome, String ordem, int page, int rp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
