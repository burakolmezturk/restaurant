package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.builder.OrderItemDTOBuilder;
import com.restaurantapi.restaurantapi.builder.ProductDTOBuilder;
import com.restaurantapi.restaurantapi.dto.OrderItemDTO;
import com.restaurantapi.restaurantapi.dto.ProductDTO;
import com.restaurantapi.restaurantapi.services.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private ProductDTO productDTO = new ProductDTOBuilder()
            .name("Deneme")
            .id(1)
            .description("Deneme")
            .salesPrice(8)
            .purchasePrice(3)
            .build();
    private List<ProductDTO> productDTOList = new ArrayList<>();
    private Set<ProductDTO> productDTOSet = new HashSet<>();
    private OrderItemDTO orderItemDTO = new OrderItemDTOBuilder()
            .id(1)
            .piece(5)
            .price(10)
            .totalPrice(50)
            .build();
    private List<OrderItemDTO> orderItemDTOList = new ArrayList<>();

    @Before
    public void setUp() {
        productDTOSet.add(productDTO);
        productDTOList.add(productDTO);
    }

    @Test
    public void ShouldGetAllProducts() {
        Mockito.when(productService.getAllProducts()).thenReturn(productDTOList);
        List<ProductDTO> res = productController.getAllProducts();
        Assert.assertEquals(productDTOList, res);
    }

    @Test
    public void ShouldNotGetAllProducts() {
        Mockito.when(productService.getAllProducts()).thenReturn(new ArrayList<>());
        List<ProductDTO> res = productController.getAllProducts();
        Assert.assertNotEquals(productDTOList, res);
    }

    @Test
    public void ShouldGetProductById() {
        Mockito.when(productService.getProductById(1)).thenReturn(productDTO);
        ProductDTO res = productController.getProductById(1);
        Assert.assertEquals(productDTO, res);
    }

    @Test
    public void ShouldNotGetProductById() {
        Mockito.when(productService.getProductById(1)).thenReturn(new ProductDTO());
        ProductDTO res = productController.getProductById(1);
        Assert.assertNotEquals(productDTO, res);
    }

    @Test
    public void ShouldAddProduct() {
        Mockito.when(productService.addProduct(productDTO)).thenReturn(productDTO);
        ProductDTO res = productController.addProduct(productDTO);
        Assert.assertEquals(productDTO, res);
    }

    @Test
    public void ShouldPutProduct() {
        Mockito.when(productService.editProduct(productDTO, 1)).thenReturn(productDTO);
        ProductDTO res = productController.putProduct(productDTO, 1);
        Assert.assertEquals(productDTO, res);
    }

    @Test
    public void ShouldDeleteProduct() {
        Mockito.when(productService.deleteProduct(1)).thenReturn(true);
        Boolean res = productController.deleteProduct(1);
        Assert.assertEquals(true, res);
    }

    @Test
    public void ShouldNotDeleteProduct() {
        Mockito.when(productService.deleteProduct(1)).thenReturn(false);
        Boolean res = productController.deleteProduct(1);
        Assert.assertEquals(false, res);
    }

//    @Test
//    public void ShouldSellProduct() {
//        Mockito.when(productService.sellProduct(Mockito.anyList())).thenReturn(true);
//        Boolean res = productController.sellProduct(orderItemDTOList);
//        Assert.assertEquals(true, res);
//    }
//
//    @Test
//    public void ShouldNotSellProduct() {
//        Mockito.when(productService.sellProduct(Mockito.anyList())).thenReturn(false);
//        Boolean res = productController.sellProduct(orderItemDTOList);
//        Assert.assertEquals(false, res);
//    }

//    @Test
//    public void ShouldGetProductsByCategoryId() {
//        Mockito.when(productService.getProductsByCategoryId(1)).thenReturn(productDTOSet);
//        Set<ProductDTO> res = productController.getProductsByCategoryId(1);
//        Assert.assertEquals(productDTOSet, res);
//    }
}
