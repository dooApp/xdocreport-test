package xdocxreport;

import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.ITemplateEngine;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldMetadata;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.template.registry.TemplateEngineRegistry;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by christophedufour on 17/02/2014.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // 1) Load ODT file and set Velocity template engine and cache it to the registry
        InputStream in = Main.class.getResourceAsStream("/sample.odt");
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

        // 2) Create Java model context
        FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
        fieldsMetadata.addFieldAsList("ashortvalue");
        fieldsMetadata.addFieldAsList("alonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglongvalue");



        report.setFieldsMetadata(fieldsMetadata);
        IContext context = report.createContext();
        context.put("name", "world");
        context.put("averyveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryverylongname", "value");
        context.put("ashortvalue", Arrays.asList(new String[]{"zefezf", "zefezf", "zefezf", "zefezf", "zefezf", "zefezf", "zefezf", "zefezf", "zefezf", "zefezf"}));
        context.put("alonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglongvalue", Arrays.asList(new String[]{"zefezf", "zefezf", "zefezf", "zefezf", "zefezf", "zefezf", "zefezf", "zefezf", "zefezf", "zefezf"}));


        // 3) Generate report by merging Java model with the ODT
        OutputStream out = new FileOutputStream(new File("ODTHelloWordWithVelocity_Out.odt"));
        report.process(context, out);
    }


}
