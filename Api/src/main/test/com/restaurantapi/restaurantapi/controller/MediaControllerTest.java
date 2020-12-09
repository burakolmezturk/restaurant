package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.builder.CategoryDTOBuilder;
import com.restaurantapi.restaurantapi.builder.MediaDTOBuilder;
import com.restaurantapi.restaurantapi.dto.CategoryDTO;
import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.services.CategoryService;
import com.restaurantapi.restaurantapi.services.MediaService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MediaControllerTest {

    @InjectMocks
    private MediaController mediaController;
    @Mock
    private MediaService mediaService;

    private MediaDTO mediaDTO = new MediaDTOBuilder()
            .id(1)
            .fileName("deneme")
            .fileContent(null)
            .build();
    List<MediaDTO> mediaDTOList = new ArrayList<>();

    @Before
    public void setUp() {
        mediaDTOList.add(mediaDTO);
    }

    @Test
    public void ShouldGetMedias() {
        Mockito.when(mediaService.getAllMedia()).thenReturn(mediaDTOList);
        List<MediaDTO> res = mediaController.getAllMedia();
        Assert.assertNotNull(res);
        Assert.assertEquals(res, mediaDTOList);
    }


}
