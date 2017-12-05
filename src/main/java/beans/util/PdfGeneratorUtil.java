package beans.util;

import java.io.*;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import com.lowagie.text.DocumentException;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;


@Component
public class PdfGeneratorUtil {
    private static String SUFFIX= ".ftlh";

    private Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
    {
        // Specify the source where the template files come from. Here I set a
        // plain directory for it, but non-file-system sources are possible too:
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/pdf");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        // Don't log exceptions inside FreeMarker that it will thrown at you anyway:
        cfg.setLogTemplateExceptions(false);
        // Wrap unchecked exceptions thrown during template processing into TemplateException-s.
        cfg.setWrapUncheckedExceptions(true);
    }

    public Path createPdf(String templateName, Map map) throws IOException, TemplateException, DocumentException {

        Template temp = cfg.getTemplate(templateName + SUFFIX);
        String fileName = UUID.randomUUID().toString();
        final File outputFile = File.createTempFile(fileName, ".pdf");

        try (FileOutputStream os = new FileOutputStream(outputFile)) {
            ITextRenderer renderer = new ITextRenderer();

            StringWriter output = new StringWriter();
            temp.process(map, output);
            //System.out.println(output.toString());

            renderer.setDocumentFromString(output.toString());
            renderer.layout();
            renderer.createPDF(os, false);
            renderer.finishPDF();

            return outputFile.toPath();
        }
    }
}