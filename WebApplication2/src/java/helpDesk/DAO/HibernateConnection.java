/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpDesk.DAO;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import prosperlib.logging.Log;


public class HibernateConnection {

    private static final SessionFactory sessionFactory;
    private static final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();

    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        if ((threadSession.get() == null) || (!threadSession.get().isOpen())) {
            Session session = sessionFactory.openSession();
            threadSession.set(session);
            return session;
        } else {
            Session session = threadSession.get();
            return session;
        }
    }

    public static void closeSession() {
        Session session = threadSession.get();
        threadSession.set(null);
        try {
            if (session != null) {
                session.close();
            }
        } catch (Exception e) {
            throw new HibernateException(e);
        }
    }
    public <E> List<E> getEntidades(Class<E> entidadeClass) {
        return getEntidades(entidadeClass, null, null);
    }

    public <E> List<E> getEntidades(Class<E> entidadeClass, Criterion criteria) {
        return getEntidades(entidadeClass, criteria, null);
    }

    public <E> List<E> getEntidades(Class<E> entidadeClass, Criterion criteria, Collection<Order> ordem) {
        Session sessao = getSession();

        List<E> retList = null;

        try {
            Criteria c = sessao.createCriteria(entidadeClass);
            if (criteria != null) {
                c.add(criteria);
            }
            if (ordem != null) {
                Iterator<Order> ordIter = ordem.iterator();
                while (ordIter.hasNext()){
                    c.addOrder(ordIter.next());
                }
            }
            System.out.println(criteria);
            System.out.println("query:"+c);
            retList = c.list();
            System.out.println("ret "+retList);
        } catch (Exception ex) {
            Log.sistema().log(Level.SEVERE, "Não foi possivel listar as entidades", ex);
        }
        return retList;
    }
    
    public static boolean isConnected() {
        return getSession().isConnected();
    }
    
}
