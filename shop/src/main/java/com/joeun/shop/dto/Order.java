package com.joeun.shop.dto;

import lombok.Data;

@Data
public class Order {
    private int orderNo;
    private String shipName;
    private String zipCode;
    private String country;
    private String address;
    private String date;
    private String orderPw;
    private String userId;
    private int totalPrice;
    private String phone;

    // 상품 입출고
    private int amount;             // 입출고 수량
    // 상품
    private String name;            // 상품명
    private int unitPrice;          // 가격 (단가)

    // 합계
    private int sumTotalPrice;      // 총 주문 금액
    private int sumAmount;          // 총 주문 수량

    // private Product product;
    // private ProductIO productIO;
}
