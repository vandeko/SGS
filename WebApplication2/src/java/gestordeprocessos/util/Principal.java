/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeprocessos.util;

import helpDesk.DAO.HibernateConnection;
import helpDesk.entidades.Setor;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Principal {

    public static void main(String[] args) throws JRException {
        repositorioProduto rep = new repositorioProduto();
        JasperPrint relat;

        //Insere mais um produto e exibe o relatório
        Setor setor = new Setor();
        setor.setId(12);
        setor.setNome("mmmm");
        
        rep.setClasse(Setor.class);
        rep.setSession(HibernateConnection.getSession());
        rep.inserir(setor);
        try {
            rep.inserir(setor);
            relat = rep.gerar();
            JasperViewer.viewReport(relat, false);
        } catch (ExcRepositorio e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }
}
