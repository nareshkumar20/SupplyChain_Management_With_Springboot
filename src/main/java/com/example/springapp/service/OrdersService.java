package com.example.springapp.service;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.springapp.models.Orders;
import com.example.springapp.repositories.OrdersRepo;

@Service
public class OrdersService {

    @Autowired
    OrdersRepo ordersRepo;

    public String placeOrder(Orders order) {
        order.setStatus("Placed");
        System.out.println(order.getCustomerid());
        order.setId(ordersRepo.save(order).getId());
        return "Order placed with order id " + order.getId();
    }

    public String acceptOrder(int id) {
        Orders order = ordersRepo.findById(id).orElse(null);
        if (order == null) {
            return "Order not found";
        }
        order.setStatus("Accepted");
        ordersRepo.saveAndFlush(order);
        return "Order " + id + " Accepted";
    }

    public String transferOrder(int id, String deliveryBoyId) {
        Orders order = ordersRepo.findById(id).orElse(null);
        if (order == null) {
            return "Order not found";
        }
        order.setStatus("Transferred");
        order.setDeliveryboyid(deliveryBoyId);
        ordersRepo.saveAndFlush(order);
        return "Order " + id + " transfered";
    }

    public String deliveredOrder(int id) {
        Orders order = ordersRepo.findById(id).orElse(null);
        if (order == null) {
            return "Order not found";
        }
        order.setStatus("Delivered");
        ordersRepo.saveAndFlush(order);
        return "Order " + id + " delivered";
    }

    public String deleteOrder(int id) {
        try {
            ordersRepo.deleteById(id);
        } catch (Exception e) {
            return "Order not found";
        }
        return "Order " + id + " deleted";
    }

    public List<Orders> getAllOrders(int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        return ordersRepo.findAll(paging).getContent();
    }

    public Orders getOrderById(int id) {
        Orders order = ordersRepo.findById(id).orElse(null);
        if (order == null) {
            order = new Orders();
            order.setId(-1);
            order.setStatus("Order not found");
        }
        return order;
    }

    public List<Orders> getOrderByStatus(String status, int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        return ordersRepo.findByStatus(status, paging).getContent();
    }
}