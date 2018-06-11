package br.com.loov.mycash.relatorio;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class GeradorRelatorio {

	private JRBeanCollectionDataSource jrBeanCollectionDataSource;

    public GeradorRelatorio() {
       
    }

	public GeradorRelatorio(JRBeanCollectionDataSource jrBeanCollectionDataSource) {
		this.jrBeanCollectionDataSource = jrBeanCollectionDataSource;
	}

	public void geraPdf(InputStream jasper1, 
        Map<String, Object> parametros, OutputStream saida) {

        try {
    
            // preenche relatorio
            JasperPrint print = JasperFillManager.fillReport(jasper1, parametros, this.jrBeanCollectionDataSource);

            // exporta para pdf
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);

            exporter.exportReport();

        } catch (Exception e) {
        	e.printStackTrace();
        }
    }   
}
