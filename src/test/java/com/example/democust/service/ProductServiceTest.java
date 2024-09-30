package com.example.democust.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.democust.exception.ResourceNotFoundException;
import com.example.democust.model.Product;
import com.example.democust.repository.ProductRepository;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        product1 = new Product(1L, "Book 1", 50.0, 10);
        product2 = new Product(2L, "Book 2", 60.0, 5);
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        // Act
        List<Product> products = productService.getAllProducts();

        // Assert
        assertThat(products).hasSize(2);
        assertThat(products).contains(product1, product2);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testAddProduct() {
        // Arrange
        when(productRepository.save(product1)).thenReturn(product1);

        // Act
        Product savedProduct = productService.addProduct(product1);

        // Assert
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getBookTitle()).isEqualTo("Book 1");
        verify(productRepository, times(1)).save(product1);
    }

    @Test
    public void testGetProductById() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        // Act
        Product product = productService.getProductById(1L);

        // Assert
        assertThat(product).isNotNull();
        assertThat(product.getBookTitle()).isEqualTo("Book 1");
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetProductById_ProductNotFound() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(1L));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        Product updatedProduct = new Product(1L, "Updated Book", 70.0, 15);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        // Act
        Product product = productService.updateProduct(1L, updatedProduct);

        // Assert
        assertThat(product).isNotNull();
        assertThat(product.getBookTitle()).isEqualTo("Updated Book");
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product1);
    }

    @Test
    public void testUpdateProduct_ProductNotFound() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> productService.updateProduct(1L, product1));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteProduct() {
        // Act
        productService.deleteProduct(1L);

        // Assert
        verify(productRepository, times(1)).deleteById(1L);
    }
}
