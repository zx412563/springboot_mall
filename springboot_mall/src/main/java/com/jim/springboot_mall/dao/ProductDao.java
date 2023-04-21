package com.jim.springboot_mall.dao;

import com.jim.springboot_mall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
