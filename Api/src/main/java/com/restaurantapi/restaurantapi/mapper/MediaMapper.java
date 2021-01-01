package com.restaurantapi.restaurantapi.mapper;

import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.entity.Media;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MediaMapper {

    MediaDTO toDTO(Media media);
    Media toEntity(MediaDTO mediaDTO);

    List<Media> toEntityList(List<MediaDTO> mediaDTOList);
    List<MediaDTO> toDTOList(List<Media> mediaList);

}
