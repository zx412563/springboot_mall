package com.jim.springboot_mall.dao;

import com.jim.springboot_mall.constant.ProductCategory;
import com.jim.springboot_mall.dto.ProductQueryParams;
import com.jim.springboot_mall.dto.ProductRequest;
import com.jim.springboot_mall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
