package gestordeprocessos.util;

import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import prosperlib.bco.Status;

public class Criterias {

    public static Criterion getLoginCriteria(int uLogin) {
                System.out.println(uLogin);
        return Restrictions.eq("id", uLogin);
    }

    public static Criterion getMatriculaCriteria(String matricula) {
       // System.out.println(matricula);
        return Restrictions.eq("pass", matricula);
    }

    public static Criterion getStatusExcluidoCriteria() {
        return getStatusExcluidoCriteria("status");
    }

    public static Criterion getStatusExcluidoCriteria(String statusParametro) {
        ArrayList<Short> statusAtivos = new ArrayList<Short>();
        statusAtivos.add(Status.EXCLUIDO.getValor());

        return Restrictions.in(statusParametro, statusAtivos);
    }

    public static Criterion getStatusAtivoCriteria() {
        return getStatusAtivoCriteria("status");
    }

    public static Criterion getStatusAtivoCriteria(String statusParametro) {
        ArrayList<Short> statusAtivos = new ArrayList<Short>();
        statusAtivos.add(Status.ATIVO.getValor());
        statusAtivos.add(Status.SISTEMA.getValor());

        return Restrictions.in(statusParametro, statusAtivos);
    }

    public static Criterion getBuscaCriteria(ArrayList<Criterion> criterias) {
        Iterator<Criterion> cIter = criterias.iterator();

        if (criterias == null || criterias.size() == 0) {
            throw new IllegalArgumentException("List de criterias não pode ser null ou vazia");
        }

        if (criterias.size() == 1) {
            return criterias.get(0);
        } else {
            Criterion buscaCrit = Restrictions.or(cIter.next(), cIter.next());

            while (cIter.hasNext()) {
                buscaCrit = Restrictions.or(buscaCrit, cIter.next());
            }

            return buscaCrit;
        }
    }

    public static Criterion getFiltroCriteria(ArrayList<Criterion> criterias) {
        Iterator<Criterion> cIter = criterias.iterator();

        if (criterias == null || criterias.size() == 0) {
            throw new IllegalArgumentException("List de criterias não pode ser null ou vazia");
        }

        if (criterias.size() == 1) {
            return criterias.get(0);
        } else {
            Criterion buscaCrit = Restrictions.and(cIter.next(), cIter.next());

            while (cIter.hasNext()) {
                buscaCrit = Restrictions.and(buscaCrit, cIter.next());
            }

            return buscaCrit;
        }
    }
}
