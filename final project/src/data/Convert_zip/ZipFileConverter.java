package data.Convert_zip;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class ZipFileConverter implements ConvertToZip{



    @Override
    public void convertToZip(String pdfFileName, String zipFileName) {

        try (FileOutputStream fos = new FileOutputStream(zipFileName);
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(pdfFileName)) {
            // Add the PDF file to the ZIP archive
            ZipEntry ze = new ZipEntry(pdfFileName);
            zos.putNextEntry(ze);

            // Read the PDF file and write its content to the ZIP archive
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }

            zos.closeEntry();
            System.out.println("Conversion to ZIP successful.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
