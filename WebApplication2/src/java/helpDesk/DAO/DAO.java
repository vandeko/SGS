
package helpDesk.DAO;

import java.util.List;
import org.hibernate.criterion.Criterion;

public interface  DAO {

    public List<Object> listAll(Criterion crtrn, String nameQuery);
    public List<Object> list(Criterion crtrn, String nameQuery, String nome, String ordem, int page, int rp);
    public Object get(Criterion crtrn, String nameQuery);
    public boolean add(Object obj);
    public boolean update(Object obj);
    public boolean delete(Object obj);
  
}
