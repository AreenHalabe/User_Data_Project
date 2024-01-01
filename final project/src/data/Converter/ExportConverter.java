package data.Converter;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ExportConverter implements IExportConverter {

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

    @Override
    public void convertToZIP(String pdfFileName, String zipFileName) {
        try (FileOutputStream fos = new FileOutputStream(zipFileName);
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(pdfFileName)) {

            ZipEntry ze = new ZipEntry(pdfFileName);
            zos.putNextEntry(ze);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }

            zos.closeEntry();
            System.out.println("Successfully converted to ZIP.");
        } catch (IOException e) {
            System.err.println("Error converting to ZIP: " + e.getMessage());
        }
    }
}

