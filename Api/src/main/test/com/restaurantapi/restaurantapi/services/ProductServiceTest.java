package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.builder.CartDTOBuilder;
import com.restaurantapi.restaurantapi.builder.CategoryDTOBuilder;
import com.restaurantapi.restaurantapi.builder.ProductDTOBuilder;
import com.restaurantapi.restaurantapi.dto.CartDTO;
import com.restaurantapi.restaurantapi.dto.CategoryDTO;
import com.restaurantapi.restaurantapi.dto.ProductDTO;
import com.restaurantapi.restaurantapi.convertor.CartDTOConvertor;
import com.restaurantapi.restaurantapi.convertor.CategoryDTOConvertor;
import com.restaurantapi.restaurantapi.convertor.ProductDTOConvertor;
import com.restaurantapi.restaurantapi.entity.Cart;
import com.restaurantapi.restaurantapi.repository.CartRepository;
import com.restaurantapi.restaurantapi.repository.CategoryRepository;
import com.restaurantapi.restaurantapi.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CartRepository cartRepository;

    private CartDTO cartDTO = new CartDTOBuilder()
            .id(1)
            .piece(5)
            .price(10)
            .productId(1)
            .placeId(1)
            .tableId(1)
            .totalPrice(50)
            .waiterId(1)
            .build();
    private List<CartDTO> cartDTOList = new ArrayList<>();
    private ProductDTO productDTO = new ProductDTOBuilder()
            .name("Deneme")
            .id(1)
            .description("Deneme")
            .salesPrice(8)
            .purchasePrice(3)
            .build();
    private CategoryDTO categoryDTO = new CategoryDTOBuilder()
            .id(1)
            .description("deneme")
            .image("dene")
            .name("cate")
            .build();
    private List<ProductDTO> productDTOList = new ArrayList<>();
    private Set<ProductDTO> productDTOSet = new HashSet<>();

    @Before
    public void setUp() throws Exception {

        cartDTOList.add(cartDTO);
        productDTOSet.add(productDTO);
        productDTOList.add(productDTO);
    }

    @Test
    public void shouldAddProduct() {
        int categoryId = 1;
        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(CategoryDTOConvertor.dtoToCategory(categoryDTO)));
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(ProductDTOConvertor.dtoToProduct(productDTO));
        ProductDTO res = productService.addProduct(productDTO, categoryId);
        Assert.assertNotNull(res);
        Assert.assertEquals(res, productDTO);

    }

    @Test
    public void shouldDeleteProduct() {
        int id = 1;
        Mockito.when(productRepository.existsById(Mockito.any())).thenReturn(true);
        Boolean res = productService.deleteProduct(id);
        Assert.assertEquals(true, res);

    }

    @Test
    public void shouldNotDeleteProduct() {
        int id = 1;
        Mockito.when(productRepository.existsById(Mockito.any())).thenReturn(false);
        Boolean res = productService.deleteProduct(id);
        Assert.assertEquals(false, res);

    }

    @Test
    public void shouldGetAllProducts() {
        Mockito.when(productRepository.findAll()).thenReturn(ProductDTOConvertor.dtoListToProductList(productDTOList));

        List<ProductDTO> res = productService.getAllProducts();
        Assert.assertNotNull(res);
        //Assert.assertEquals(res, productDTOList);
    }

    @Test
    public void shouldEditProduct() {
        int categoryId = 1;
        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(CategoryDTOConvertor.dtoToCategory(categoryDTO)));
        Mockito.when(productRepository.saveAndFlush(Mockito.any())).thenReturn(ProductDTOConvertor.dtoToProduct(productDTO));
        ProductDTO res = productService.editProduct(productDTO, categoryId);
        Assert.assertNotNull(res);
        Assert.assertEquals(res, productDTO);

    }

    @Test
    public void ShouldGetProductById() {
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(ProductDTOConvertor.dtoToProduct(productDTO)));
        ProductDTO res = productService.getProductById(1);
        Assert.assertNotNull(res);
        //Assert.assertEquals(res, productDTO);
    }

    @Test
    public void ShouldSellProduct() {

        List<Cart> cartList = new ArrayList<>();
        cartDTOList.stream().forEach(cartDTO1 -> cartList.add(CartDTOConvertor.dtoToCart(cartDTO1)));
        Mockito.when(cartRepository.saveAll(Mockito.any())).thenReturn(cartList);
        Boolean res = productService.sellProduct(cartDTOList);
        Assert.assertNotNull(res);
        Assert.assertEquals(true, res);
    }

    @Test
    public void NotShouldSellProduct() {

        Mockito.when(cartRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        Boolean res = productService.sellProduct(cartDTOList);
        Assert.assertNotNull(res);
        Assert.assertEquals(false, res);
    }

    @Test
    public void ShouldGetProductsByCategoryId() {

        Mockito.when(categoryRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(CategoryDTOConvertor.dtoToCategory(categoryDTO)));
        Set<ProductDTO> res = productService.getProductsByCategoryId(1);

        Assert.assertNotNull(res);

    }
}
