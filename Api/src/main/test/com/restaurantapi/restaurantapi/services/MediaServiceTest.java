package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.builder.MediaDTOBuilder;
import com.restaurantapi.restaurantapi.convertor.MediaDTOConvertor;
import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.entity.Media;
import com.restaurantapi.restaurantapi.mapper.MediaMapper;
import com.restaurantapi.restaurantapi.repository.MediaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MediaServiceTest {
    @InjectMocks
    private MediaService mediaService;
    @Mock
    private MediaRepository mediaRepository;

    @Spy
    private MediaMapper mediaMapper = Mappers.getMapper(MediaMapper.class);
    byte[] content ;

    private MediaDTO mediaDTO = new MediaDTOBuilder().fileName("deneme").id(1).build();
    private Media media = mediaMapper.toEntity(mediaDTO);
    private List<MediaDTO> mediaDTOList = new ArrayList<>();
    private List<Media> mediaList = new ArrayList<>();

    @Before
    public void setUp(){
        mediaDTOList.add(mediaDTO);
        mediaList = mediaMapper.toEntityList(mediaDTOList);
        content[0]=0;
        mediaDTO.setFileContent(content);
    }

    @Test
    public void shouldGetAllMedia() {

        Mockito.when(mediaRepository.findAll()).thenReturn(mediaList);
        List<MediaDTO> res= mediaService.getAllMedia();
        Assert.assertEquals(res.get(0).getId(),mediaDTOList.get(0).getId());
    }
}
