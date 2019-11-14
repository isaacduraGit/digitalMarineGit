package org.marineDigitalJournal.reports;

import java.sql.Connection;
import java.sql.DriverManager;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Report_Generator {

	Connection c;

	private JasperReport report;

	private JasperPrint p;

	private JasperViewer j;

	public void con() {

		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String db = "jdbc:ucanaccess://ProyectosGraduacion.accdb";
			c = DriverManager.getConnection(db);

			System.out.println("Conection Successfull");
		} catch (Exception e) {

			System.out.println("Conection failed");
		}
	}

	public void open(String path) {

		try {

			report = (JasperReport) JRLoader.loadObject(getClass().getResource(path));

			p = JasperFillManager.fillReport(report, null, c);

		} catch (Exception e) {

			System.out.println("Error al generar reporte");

			System.out.println(e.getMessage() + e.getCause());

		}
	}

	public void display() {
		
		j=new JasperViewer(p);
		j.setVisible(true);
		
		
	}
	
	private void jMenuItemTestActionPerformed(java.awt.event.ActionEvent evt) {
		
		Connection connect;
		
		Report_Generator go=new Report_Generator();
		
		try {
			
			go.open("path");
			
			go.display();
		}
		catch (Exception e) {
			
			System.out.println("Error al visualizar el reporte");
			
			System.out.println(e.getMessage()+e.getCause());
			
		}
	}
	
	
	
}
