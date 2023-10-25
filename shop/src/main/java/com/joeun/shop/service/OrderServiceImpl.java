package com.joeun.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joeun.shop.dto.Order;
import com.joeun.shop.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> list() throws Exception {
        List<Order> orderList = orderMapper.list();
        return orderList;
    }
    
    @Override
    public List<Order> listByUserId(String userId) throws Exception {
        List<Order> orderList = orderMapper.listByUserId(userId);
        return orderList;
    }
    
    @Override
    public List<Order> listByGuest(Order order) throws Exception {
        List<Order> orderList = orderMapper.listByGuest(order);
        return orderList;
    }

    @Override
    public Order select(int orderNo) throws Exception {
        Order order = orderMapper.select(orderNo);
        return order;
    }

    @Override
    public int insert(Order order) throws Exception {
        int result = orderMapper.insert(order);
        return result;
    }
    
    @Override
    public int update(Order order) throws Exception {
        int result = orderMapper.update(order);
        return result;
    }
    
    @Override
    public int delete(int orderNo) throws Exception {
        int result = orderMapper.delete(orderNo);
        return result;
    }

    @Override
    public Order sumOrder(String userId) throws Exception {
        Order order = orderMapper.sumOrder(userId);
        return order;
    }
    
    @Override
    public Order sumOrderByGuest(Order order) throws Exception {
        order = orderMapper.sumOrderByGuest(order);
        return order;
    }
    
}
