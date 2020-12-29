package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.convertor.MediaDTOConvertor;
import com.restaurantapi.restaurantapi.dto.ErrorMessage;
import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.entity.Media;
import com.restaurantapi.restaurantapi.exception.RecordNotFoundException;
import com.restaurantapi.restaurantapi.exception.SystemException;
import com.restaurantapi.restaurantapi.mapper.MediaMapper;
import com.restaurantapi.restaurantapi.repository.MediaRepository;
import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class MediaService {
    public static final String JPG_EXTENSION = ".jpg";
    public static final String PNG_EXTENSION = ".png";
    public static final String BMP_EXTENSION = ".bmp";

    public static final String PNG_CONTENT = "image/png";
    public static final String BMP_CONTENT = "image/bmp";

    @Autowired
    private MediaMapper mediaMapper;

    @Autowired
    private MediaRepository mediaRepository;

    private String generateUUID() {
        return String.valueOf(java.util.UUID.randomUUID());
    }

    private String generateFullPath(MultipartFile file, String path) {

        if (file == null) throw new SystemException(ErrorMessage.FILE_NOT_FOUND);

        String extension = JPG_EXTENSION;
        if (BMP_CONTENT.equals(file.getContentType())) {
            extension = BMP_EXTENSION;
        } else if (PNG_CONTENT.equals(file.getContentType())) {
            extension = PNG_EXTENSION;
        }
        return path + generateUUID() + extension;
    }

    public List<MediaDTO> getAllMedia() {

        List<Media> mediaList = mediaRepository.findAll();
        if (mediaList.isEmpty()) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

        List<MediaDTO> mediaDTOList = mediaMapper.toDTOList(mediaList);

        return mediaDTOList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public MediaDTO addMedia(String fileName, MultipartFile multipartFile, String path) throws IOException {
        if (multipartFile == null) throw new RecordNotFoundException(ErrorMessage.FILE_NOT_FOUND);

        if (path == null || path == "") throw new RecordNotFoundException(ErrorMessage.PATH_IS_NULL_OR_WRONG);

        Files.createDirectories(Paths.get(path));
        String filePath = generateFullPath(multipartFile, path);
        Path targetLocation = FileSystems.getDefault().getPath(filePath);
        Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        byte[] bytes = multipartFile.getBytes();

        MediaDTO mediaDTO = new MediaDTO(bytes, fileName);

        return mediaMapper.toDTO(mediaRepository.save(mediaMapper.toEntity(mediaDTO)));
    }

}
