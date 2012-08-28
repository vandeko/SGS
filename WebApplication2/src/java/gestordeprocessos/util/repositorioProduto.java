package gestordeprocessos.util;

import helpDesk.DAO.HibernateConnection;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.hibernate.Session;

public class repositorioProduto {

    public repositorioProduto() {
    }

    public boolean inserir(Object obj) {
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
 private Session session;
    private Class classe;
    public JasperPrint gerar() throws ExcRepositorio {
        JasperPrint rel = null;
        try {
            Connection con = (Connection) HibernateConnection.getSession();
            HashMap map = new HashMap();
            String arquivoJasper = "relatorio.jasper";
            rel = JasperFillManager.fillReport(arquivoJasper, map, con);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return rel;
    }

    /**
     * @return the session
     */
    public Session getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * @return the classe
     */
    public Class getClasse() {
        return classe;
    }

    /**
     * @param classe the classe to set
     */
    public void setClasse(Class classe) {
        this.classe = classe;
    }
}
