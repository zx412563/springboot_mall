package com.jim.springboot_mall.dao.impl;

import com.jim.springboot_mall.constant.ProductCategory;
import com.jim.springboot_mall.dao.ProductDao;
import com.jim.springboot_mall.dto.ProductQueryParams;
import com.jim.springboot_mall.dto.ProductRequest;
import com.jim.springboot_mall.model.Product;
import com.jim.springboot_mall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String addFilteringSql(String sql, Map<String, Object> map, ProductQueryParams productQueryParams) {
        //查詢條件
        if (productQueryParams.getCategory() != null) {
            sql = sql + " AND category = :category";
            map.put("category", productQueryParams.getCategory().name());
        }
        if (productQueryParams.getSearch() != null) {
            sql += " AND product_name LIKE :search";
            map.put("search", "%" + productQueryParams.getSearch() + "%");
        }
        return sql;
    }

    @Override
    public void updateStock(Integer productId, Integer stock) {
        String sql = "update product set stock = :stock , last_modified_date = :lastModifiedDate " +
                " WHERE product_id = :productId";
        Map<String, Object> map = new HashMap<>();
        Date now = new Date();
        map.put("productId", productId);
        map.put("stock", stock);
        map.put("lastModifiedDate", now);
        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public Integer countPrduct(ProductQueryParams productQueryParams) {
        String sql = "SELECT COUNT(*) FROM product WHERE 1=1";
        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, productQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
        return total;

    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date" +
                " FROM  product WHERE 1=1";
        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, productQueryParams);
        //排序
        sql = sql + " ORDER BY " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();

        //分頁
        sql = sql + " LIMIT :limit OFFSET :offset ";
        map.put("limit", productQueryParams.getLimit());
        map.put("offset", productQueryParams.getOffset());

        List<Product> list = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        return list;
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date " +
                "from product where product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        List<Product> list = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }


    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) " +
                "VALUES (:productName,:category,:imageUrl,:price,:stock,:description ,:createdDate,:lastModifiedDate);";
        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescrption());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int productId = keyHolder.getKey().intValue();
        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE  product SET product_name=:productName,category=:category, image_url=:imageUrl" +
                ",price = :price, stock = :stock, description = :description ," +
                "last_modified_date = :lastModifiedDate WHERE product_id = :productId";
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescrption());
        map.put("lastModifiedDate", new Date());
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";
        Map<String, Object> map = new HashMap<>();

        map.put("productId", productId);
        namedParameterJdbcTemplate.update(sql, map);

    }
}
