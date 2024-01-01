package data.Converter;

public interface IExportConverter {
    void convertToPDF(String data, String fileName);
    void convertToZIP(String data, String fileName);
}
