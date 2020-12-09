package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.builder.MediaDTOBuilder;
import com.restaurantapi.restaurantapi.convertor.MediaDTOConvertor;
import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.entity.Media;
import com.restaurantapi.restaurantapi.repository.MediaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MediaServiceTest {
    @InjectMocks
    private MediaService mediaService;
    @Mock
    private MediaRepository mediaRepository;

    @Test
    public void addMedia() {

    }

    @Test
    public void shouldGetAllMedia() {
        List<Media> mediaList = new ArrayList<>();
        List<MediaDTO> mediaDTOList = new ArrayList<>();
        Media media = MediaDTOConvertor.dtoToMedia(new MediaDTOBuilder().fileName("deneme").id(1).fileContent(null).build());
        mediaList.add(media);
        mediaList.stream().forEach(media1 -> mediaDTOList.add(MediaDTOConvertor.mediaToDTO(media1)));
        Mockito.when(mediaRepository.findAll()).thenReturn(mediaList);
        List<MediaDTO> res= mediaService.getAllMedia();
        Assert.assertEquals(res.get(0).getId(),mediaDTOList.get(0).getId());
    }
}
