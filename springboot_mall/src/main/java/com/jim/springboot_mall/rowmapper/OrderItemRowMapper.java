package com.jim.springboot_mall.rowmapper;

import com.jim.springboot_mall.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {

    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItem orderItem = new OrderItem();

        orderItem.setOrderId(rs.getInt("order_id"));
        orderItem.setProductId(rs.getInt("product_id"));
        orderItem.setAmount(rs.getInt("amount"));
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setOrderItemId(rs.getInt("order_item_id"));

        //join product
        orderItem.setProductName(rs.getString("product_name"));
        orderItem.setImageUrl(rs.getString("image_url"));
        return orderItem;
    }
}
