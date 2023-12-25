package data.Convert_pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class PdfFileConverter implements convertToPdf {
    private String folderPath = "File_Storeg_data";
    private boolean createfile = false;


    @Override
    public void convert_Pdf(String inputFileName, String outputFileName) {
        Document document = new Document();

        try {
            Path filePath = Paths.get(folderPath, inputFileName);
            Path outputPath = Paths.get(folderPath, outputFileName);

            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            // Open the document before writing to it
            PdfWriter.getInstance(document, new FileOutputStream(outputPath.toString()));
            document.open();

            // Add content to the document
            Files.lines(filePath).forEach(line -> {
                try {
                    document.add(new Paragraph(line));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Close the document after adding content
            document.close();
            System.out.println("Conversion to PDF successful.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
