package it.uniroma3.siw.siwprogettocinema;

import java.io.*;
import java.nio.file.*;
 
import org.springframework.web.multipart.MultipartFile;
 
public class FileUploadUtil {
     
    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
         
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }      
    }
    
    public static void deleteFile(String uploadDir, String fileName) throws IOException {
        Path path = Paths.get(uploadDir);
         
        try {
            Path filePath = path.resolve(fileName);
            Files.delete(filePath);
        } catch (IOException ioe) {        
            throw new IOException("Could not delte image file: " + fileName, ioe);
        }      
    }
    
}