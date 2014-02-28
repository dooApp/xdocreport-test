package xdocxreport;

import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Arrays;

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
        //fieldsMetadata.addFieldAsList("ashortvalue");
        fieldsMetadata.addFieldAsList("a");


        report.setFieldsMetadata(fieldsMetadata);
        IContext context = report.createContext();
        context.put("name", "world");

        context.put("number", new DecimalFormat("#.00000"));
        context.put("n", new DecimalFormat("#.00000"));
        context.put("averyveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryveryverylongname", "value");
        context.put("ashortvalue", Arrays.asList(new String[]{"zefezf", "zefezf", "zefezf", "zefezf", "zefezf", "zefezf", "zefezf", "zefezf", "zefezf", "zefezf"}));
        context.put("a", Arrays.asList(new Tmp[]{new Tmp(),new Tmp(),new Tmp(),new Tmp(),new Tmp(),new Tmp()}));


        // 3) Generate report by merging Java model with the ODT
        OutputStream out = new FileOutputStream(new File("ODTHelloWordWithVelocity_Out.odt"));
        report.process(context, out);
    }


    public static class Tmp {
        public double value=Math.random();


        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }

}
