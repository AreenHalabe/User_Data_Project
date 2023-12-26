package data.Convert_pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class PdfFileConverter implements convertToPdf {
    private String folderPath = "File_Storeg_data";



    @Override
    public void convert_Pdf(String inputFileName, String outputFileName) {
        Document document = new Document();

        try {
            Path filePath = Paths.get(folderPath, inputFileName);
            Path outputPath = Paths.get(folderPath, outputFileName);

            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            PdfWriter.getInstance(document, new FileOutputStream(outputPath.toString()));
            document.open();


            Files.lines(filePath).forEach(line -> {
                try {
                    document.add(new Paragraph(line));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Close the document after adding content
            document.close();
            System.out.println("success to pdf conversion.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
