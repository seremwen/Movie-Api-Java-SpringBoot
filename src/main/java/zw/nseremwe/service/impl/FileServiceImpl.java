package zw.nseremwe.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zw.nseremwe.service.FileService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        // Get the file name
        String filename= file.getOriginalFilename();
        // Get the file path
        String filePath= path+ File.separator + filename;
        // Create file object
        File f= new File(path);

        if(!f.exists()){
            f.mkdir();
        }
        //Copy the file or upload the file to the path
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        return filename;
    }

    @Override
    public InputStream getResourceFile(String path, String filename) throws FileNotFoundException {
        String filePath= path+ File.separator + filename;
        return new FileInputStream(filePath);
    }
}
