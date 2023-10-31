package com.joeun.shop.dto;

import lombok.Data;

@Data
public class ProductIO {
    private int ioNo;
    private String productId;
    private int orderNo;
    private int amount;
    private String type;
    private String ioDate;
    private String userId;
}
