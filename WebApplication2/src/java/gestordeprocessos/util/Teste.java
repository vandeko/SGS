/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeprocessos.util;

import helpDesk.DAO.HibernateConnection;
import helpDesk.DAO.UsuarioDAO;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author EDLAINE
 */
public class Teste {

    public static void main(String[] args) throws JRException, SQLException {
        System.out.println("Gerando relatório…");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.setClasse(UsuarioDAO.class);
        usuarioDAO.setSession(HibernateConnection.getSession());
        List listaUs = usuarioDAO.listAll(null, "Usuario.findAll");
        JasperReport pathjrxml = JasperCompileManager.compileReport("_relatorios/exemplo1.jrxml");
        JasperPrint printReport = JasperFillManager.fillReport(pathjrxml, null, new JRBeanCollectionDataSource(listaUs));
        JasperExportManager.exportReportToPdfFile(printReport, "_relatorios/exemplo1.pdf");
        System.out.println("Relatorio gerado");
    }
}
