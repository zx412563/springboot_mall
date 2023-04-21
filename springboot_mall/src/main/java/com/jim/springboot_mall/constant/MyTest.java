package com.jim.springboot_mall.constant;

public class MyTest {
    public static void main(String[] args) {

        ProductCategory category = ProductCategory.BOOK;
        String s = category.name();
        System.out.println(s);
        String s2 = "CAR";
        ProductCategory category1 = ProductCategory.valueOf(s2);
    }
}
