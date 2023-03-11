package tp6.basicapi.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import tp6.basicapi.Item;
import tp6.basicapi.model.Attribut;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped

public class ExportService {
    public Response exportItem() throws JRException {
        File file = new File("src/main/resources/TemplateItemm.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(Attribut.listAll());


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), jrBeanCollectionDataSource);
        byte[] jasperResult = JasperExportManager.exportReportToPdf(jasperPrint);
        return Response.ok().type("application/pdf").entity(jasperResult).build();
    }
}
