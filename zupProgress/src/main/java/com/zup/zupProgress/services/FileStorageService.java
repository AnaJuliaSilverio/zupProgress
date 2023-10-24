package com.zup.zupProgress.services;

import com.zup.zupProgress.config.FileStorageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageService {
    private final Path finaStorageLocation;
    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.finaStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.finaStorageLocation);
        }catch (Exception e){
            throw new RuntimeException("Não foi poosivel criar o diretório");
        }
    }
    public String storeFile(MultipartFile file){
        String filename= StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try{
            if (filename.contains("..")){
                throw new RuntimeException("O arquivo contem um nome errado");
            }
            Path targetLocation = this.finaStorageLocation.resolve(filename);
            Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        }catch (Exception e){
            throw new RuntimeException("Não foi poosivel salvar o arquivo");
        }
    }

    public Resource loadFileAsResource(String filename){
        try {
            Path filePath = this.finaStorageLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) return resource;
            else throw new RuntimeException("Arquivo não encontrado");
        }catch (Exception e){
            throw new RuntimeException("Arquivo não encontrado");
        }
    }

    public String updateFile(String fileName, MultipartFile file){
        try {
            Path filePath = this.finaStorageLocation.resolve(fileName).normalize();
            Files.delete(filePath);
            return storeFile(file);
        }catch (Exception e){
            throw new RuntimeException("Não foi poosivel salvar o arquivo");
        }
    }
}
