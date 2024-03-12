package com.example.springapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapp.models.Orders;
import com.example.springapp.service.OrdersService;

@RestController
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @GetMapping("/")
    public String test() {
        return "hello";
    }

    @PostMapping("/placeOrder")
    public String placeOrder(@RequestBody Orders order) {
        return ordersService.placeOrder(order);
    }

    @PutMapping("/acceptOrder/{id}")
    public String acceptOrder(@PathVariable int id) {
        return ordersService.acceptOrder(id);
    }

    @PutMapping("/transferOrder/{id}/{deliveryBoyId}")
    public String transferOrder(@PathVariable int id, @PathVariable String deliveryBoyId) {
        return ordersService.transferOrder(id, deliveryBoyId);
    }

    @PutMapping("/deliveredOrder/{id}")
    public String deliveredOrder(@PathVariable int id) {
        return ordersService.deliveredOrder(id);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable int id) {
        return ordersService.deleteOrder(id);
    }

    @GetMapping("/getOrders/{pageNo}/{pageSize}")
    public List<Orders> getOrders(@PathVariable int pageNo, @PathVariable int pageSize) {
        return ordersService.getAllOrders(pageNo, pageSize);
    }

    @GetMapping("/getOrderById/{id}")
    public Orders getOrderById(@PathVariable int id) {
        return ordersService.getOrderById(id);
    }

    @GetMapping("/getOrderByStatus/{status}/{pageNo}/{pageSize}")
    public List<Orders> getOrderByStatus(@PathVariable String status, @PathVariable int pageNo,
            @PathVariable int pageSize) {
        return ordersService.getOrderByStatus(status, pageNo, pageSize);
    }
}
