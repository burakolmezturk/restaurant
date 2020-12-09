package com.restaurantapi.restaurantapi.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InfoServiceTest {
    @InjectMocks
    private InfoService infoService;


    @Test
    public void ShouldGetInfo() {
        String res = infoService.getInfo();
        Assert.assertNotNull(res);
    }

    @Test
    public void ShouldGetSpringProfiles() {
        String res = infoService.getSpringProfiles();
        Assert.assertNotNull(res);
    }

}
