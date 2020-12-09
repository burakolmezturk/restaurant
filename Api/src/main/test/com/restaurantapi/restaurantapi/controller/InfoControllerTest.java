package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.services.InfoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InfoControllerTest {
    @InjectMocks
    private InfoController infoController;

    @Mock
    private InfoService infoService;

    @Test
    public void ShouldGetInfo() {
        Mockito.when(infoService.getInfo()).thenReturn("Not Null");
        String res = infoController.getInfo();
        Assert.assertEquals("Not Null",res);
    }
    @Test
    public void ShouldNotGetInfo() {
        Mockito.when(infoService.getInfo()).thenReturn("");
        String res = infoController.getInfo();
        Assert.assertEquals("",res);
    }
    @Test
    public void ShouldGetSpringProfile(){
        Mockito.when(infoService.getSpringProfiles()).thenReturn("Not Null");
        String res = infoController.getSpringProfile();
        Assert.assertEquals("Not Null",res);
    }
    @Test
    public void ShouldNotGetSpringProfile(){
        Mockito.when(infoService.getSpringProfiles()).thenReturn("");
        String res = infoController.getSpringProfile();
        Assert.assertEquals("",res);
    }

}
