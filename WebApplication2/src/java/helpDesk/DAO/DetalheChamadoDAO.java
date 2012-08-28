/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpDesk.DAO;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

/**
 *
 * @author EDLAINE
 */
public class DetalheChamadoDAO implements DAO{
     private Session session;
    private Class classe;

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
