package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.builder.CartDTOBuilder;
import com.restaurantapi.restaurantapi.builder.CategoryDTOBuilder;
import com.restaurantapi.restaurantapi.builder.MediaDTOBuilder;
import com.restaurantapi.restaurantapi.builder.ProductDTOBuilder;
import com.restaurantapi.restaurantapi.dto.*;
import com.restaurantapi.restaurantapi.convertor.CartDTOConvertor;
import com.restaurantapi.restaurantapi.convertor.CategoryDTOConvertor;
import com.restaurantapi.restaurantapi.convertor.ProductDTOConvertor;
import com.restaurantapi.restaurantapi.entity.Cart;
import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.entity.Customer;
import com.restaurantapi.restaurantapi.entity.Product;
import com.restaurantapi.restaurantapi.mapper.CategoryMapper;
import com.restaurantapi.restaurantapi.mapper.MediaMapper;
import com.restaurantapi.restaurantapi.mapper.ProductMapper;
import com.restaurantapi.restaurantapi.repository.CartRepository;
import com.restaurantapi.restaurantapi.repository.CategoryRepository;
import com.restaurantapi.restaurantapi.repository.ProductRepository;
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
import org.springframework.data.domain.*;

import java.util.*;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Spy
    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Spy
    private MediaMapper mediaMapper = Mappers.getMapper(MediaMapper.class);

    @Spy
    private CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Mock
    private CartRepository cartRepository;
    private MediaDTO mediaDTO = new MediaDTOBuilder()
            .fileContent(null)
            .fileName("deneme")
            .id(1).build();
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
            .categoryId(new int[1])
            .build();
    private CategoryDTO categoryDTO = new CategoryDTOBuilder()
            .id(1)
            .description("deneme")
            .name("cate")
            .image(mediaDTO)
            .build();
    private List<ProductDTO> productDTOList = new ArrayList<>();

    private List<Product> productList = new ArrayList<>();

    private Category category = categoryMapper.toEntity(categoryDTO);

    private Product product = productMapper.toEntity(productDTO);

    private Pageable pageable = PageRequest.of(0, 10);
    @Before
    public void setUp() throws Exception {

        cartDTOList.add(cartDTO);
        productDTOList.add(productDTO);

        productList = productMapper.toEntityList(productDTOList);

    }

    @Test
    public void shouldAddProduct() {

        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(category));
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);
        ProductDTO res = productService.addProduct(productDTO);
        Assert.assertNotNull(res);
        Assert.assertEquals(res.getId(), productDTO.getId());

    }

    @Test
    public void shouldDeleteProduct() {
        int id = 1;
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(ProductDTOConvertor.dtoToProduct(productDTO)));
        Mockito.when(productRepository.existsById(Mockito.any())).thenReturn(true);
        Boolean res = productService.deleteProduct(id);
        Assert.assertEquals(true, res);

    }

    @Test
    public void shouldNotDeleteProduct() {
        int id = 1;
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(ProductDTOConvertor.dtoToProduct(productDTO)));
        Mockito.when(productRepository.existsById(Mockito.any())).thenReturn(false);
        Boolean res = productService.deleteProduct(id);
        Assert.assertEquals(false, res);

    }

    @Test
    public void shouldGetAllProducts() {

        Mockito.when(productRepository.findAll()).thenReturn(productList);
        List<ProductDTO> res = productService.getAllProducts();
        Assert.assertNotNull(res);
        Assert.assertEquals(res.get(0).getId(), productDTOList.get(0).getId());
    }

    @Test
    public void shouldEditProduct() {
        int categoryId = 1;
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(product));
        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(category));
        Mockito.when(productRepository.saveAndFlush(Mockito.any())).thenReturn(product);
        ProductDTO res = productService.editProduct(productDTO, categoryId);
        Assert.assertNotNull(res);
        Assert.assertEquals(res.getId(), productDTO.getId());

    }

    @Test
    public void ShouldGetProductById() {
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(product));
        ProductDTO res = productService.getProductById(1);
        Assert.assertNotNull(res);
        Assert.assertEquals(res.getId(), productDTO.getId());
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
    @Test
    public void shouldFindByPage(){
        Mockito.when(productRepository.findAll(pageable)).thenReturn(new PageImpl<Product>(productList, pageable, 1));
        Page<ProductDTO> productDTOPage = productService.getFindPage(pageable);

        Assert.assertEquals(productDTOPage.getContent().get(0).getId(),productDTO.getId());
    }
    @Test
    public void shouldFindBySlice(){
        Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        Mockito.when(productRepository.findProductByCategories(category,pageable)).thenReturn(new PageImpl<Product>(productList, pageable, 1));
        Slice<ProductDTO> productDTOSlice = productService.getProductWithSlice(pageable,1);

        Assert.assertEquals(productDTOSlice.getContent().get(0).getId(),productDTO.getId());
    }
}
