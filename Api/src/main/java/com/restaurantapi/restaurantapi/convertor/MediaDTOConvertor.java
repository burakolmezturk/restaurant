package com.restaurantapi.restaurantapi.convertor;

import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.entity.Media;

public class MediaDTOConvertor {
    private MediaDTOConvertor() {
    }

    public static Media dtoToMedia(MediaDTO mediaDTO) {
        Media media = new Media();
        media.setId(mediaDTO.getId());
        media.setFileName(mediaDTO.getFileName());
        media.setFileContent(mediaDTO.getFileContent());
        return media;
    }

    public static MediaDTO mediaToDTO(Media media) {
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setId(media.getId());
        mediaDTO.setFileName(media.getFileName());
        mediaDTO.setFileContent(media.getFileContent());
        return mediaDTO;
    }
}
