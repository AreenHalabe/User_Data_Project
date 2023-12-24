package data.Convert_pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pdf_File implements convertToPdf {
    private String folderPath = "File_Storage_Data";

    @Override
    public void convert_Pdf(String inputFileName, String outputFileName) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputFileName));
            document.open();

            Path filePath = Paths.get(folderPath, inputFileName);
            Files.lines(filePath).forEach(line -> {
                try {
                    document.add(new Paragraph(line));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            document.close();
            System.out.println("Conversion to PDF successful.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
