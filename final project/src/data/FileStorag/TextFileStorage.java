package data.FileStorag;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.*;

public class TextFileStorage implements StoregeService{
    private String folderPath="File_Storeg_data";
    private String fileName;
    private  boolean createfile = false;

    public TextFileStorage(String fileName){
        this.fileName=fileName+".txt";
    }

    @Override
    public void uploadData(String data ) {
        Path filePath = Paths.get(folderPath, fileName);

        if (!Files.exists(filePath.getParent())) {
            try {
                Files.createDirectories(filePath.getParent());
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        if(!createfile){
            try {
                Files.write(filePath, "".getBytes());
                createfile = true;
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {
            Files.write(filePath, data.getBytes(),StandardOpenOption.APPEND);
            Files.write(filePath,"\n".getBytes(),StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
