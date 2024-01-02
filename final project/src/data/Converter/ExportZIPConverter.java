package data.Converter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ExportZIPConverter implements IZIPExportConverter {
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