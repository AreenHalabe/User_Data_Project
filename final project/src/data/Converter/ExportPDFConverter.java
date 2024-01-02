package data.Converter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;


public class ExportPDFConverter implements IPDFExportConverter {
    public void convertToPDF(String username, String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader("File_Storeg_data/" + username + ".txt"))) {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            document.open();

            String line;
            while ((line = br.readLine()) != null) {
                document.add(new Paragraph(line));
            }

            document.close();
            System.out.println("Conversion to PDF successful.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error during PDF conversion: " + e.getMessage());
        }
    }
}

