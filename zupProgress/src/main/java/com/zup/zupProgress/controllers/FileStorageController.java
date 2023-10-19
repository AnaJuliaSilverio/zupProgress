package com.zup.zupProgress.controllers;

import com.zup.zupProgress.dto.UploadFileDTO;
import com.zup.zupProgress.services.FileStorageService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/file")
@CrossOrigin("*")
public class FileStorageController {
    @Autowired
    private FileStorageService fileStorageService;
    @PostMapping("/uploadFile")
    public UploadFileDTO uploadFile(@RequestParam("file")MultipartFile file){
        var fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/download/")
                .path(fileName)
                .toUriString();
        return new UploadFileDTO(fileName,fileDownloadUri,file.getContentType(),file.getSize());

    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename, HttpServletRequest request){
        Resource resource = fileStorageService.loadFileAsResource(filename);
        String contentType ="";
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (Exception e){
            System.out.println("Não foi possivel determinar o tipo do arquivo");
        }

        if (contentType.isBlank()) contentType = "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
