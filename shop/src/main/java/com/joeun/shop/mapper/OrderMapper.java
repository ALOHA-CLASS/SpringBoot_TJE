package com.joeun.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joeun.shop.dto.Order;

@Mapper
public interface OrderMapper {
    // 전체 주문 목록
    public List<Order> list() throws Exception;

    // 회원 주문 내역
    public List<Order> listByUserId(String userId) throws Exception;

    // 비회원 주문 내역
    public List<Order> listByGuest(Order order) throws Exception;

    // 주문 조회
    public Order select(int orderNo) throws Exception;

    // 주문 등록
    public int insert(Order order) throws Exception;

    // 주문 수정
    public int update(Order order) throws Exception;

    // 주문 삭제
    public int delete(int orderNo) throws Exception;

    // 주문 총합계 (주문 총 금액, 총 수량)
    public Order sumOrder(String userId) throws Exception;

    // 비회원 주문 총합계 (주문 총 금액, 총 수량)
    public Order sumOrderByGuest(Order order) throws Exception;
    
}
