package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.convertor.MediaDTOConvertor;
import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.entity.Media;
import com.restaurantapi.restaurantapi.repository.MediaRepository;
import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MediaService {
    public static final String JPG_EXTENSION = ".jpg";
    public static final String PNG_EXTENSION = ".png";
    public static final String BMP_EXTENSION = ".bmp";

    public static final String PNG_CONTENT = "image/png";
    public static final String BMP_CONTENT = "image/bmp";


    @Autowired
    private MediaRepository mediaRepository;

    private String generateUUID() {
        return String.valueOf(java.util.UUID.randomUUID());
    }

    private String generateFullPath(MultipartFile file, String path) {
        String extension = JPG_EXTENSION;
        if (BMP_CONTENT.equals(file.getContentType())) {
            extension = BMP_EXTENSION;
        } else if (PNG_CONTENT.equals(file.getContentType())) {
            extension = PNG_EXTENSION;
        }
        return path + generateUUID() + extension;
    }

    public List<MediaDTO> getAllMedia() {

        List<MediaDTO> mediaDTOList = new ArrayList<>();
        List<Media> mediaList = mediaRepository.findAll();
        mediaList.stream().forEach(media -> mediaDTOList.add(MediaDTOConvertor.mediaToDTO(media)));
        return mediaDTOList;
    }

    public MediaDTO addMedia(String fileName, MultipartFile multipartFile, String path) throws IOException {
        Files.createDirectories(Paths.get(path));
        String filePath = generateFullPath(multipartFile,path);
        Path targetLocation = FileSystems.getDefault().getPath(filePath);
        Files.copy(multipartFile.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);

        byte[] bytes = multipartFile.getBytes();

        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setFileContent(bytes);
        mediaDTO.setFileName(fileName);
        return MediaDTOConvertor.mediaToDTO(mediaRepository.save(MediaDTOConvertor.dtoToMedia(mediaDTO)));
    }

}
