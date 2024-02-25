package zw.nseremwe.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zw.nseremwe.service.FileService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file/")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    @Value("${project.poster}")
    private String path;
    @PostMapping("upload")
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile file) throws IOException{
       String uploadedFilename= fileService.uploadFile(path, file);
       return ResponseEntity.ok("File uploaded"+ uploadedFilename);
    }
    @GetMapping("{filename}")
    public void serverFileHandler(@PathVariable String filename, HttpServletResponse response) throws IOException {
       InputStream resourceFile= fileService.getResourceFile(path, filename);
       response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(resourceFile, response.getOutputStream());
    }
}
