package com.example.order_service.controller;

import com.example.order_service.config.WebClientConfig;
import com.example.order_service.dto.OrderResponseDTO;
import com.example.order_service.dto.ProductDTO;
import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepo repo;
    @Autowired
    private WebClient.Builder web;

    @PostMapping("/placeOrder")
    public Mono<ResponseEntity<OrderResponseDTO>> placeOrder(@RequestBody Order od) {
        repo.save(od);
        return web.build().get().uri("http://localhost:8081/products/"+od.getProductId()).retrieve()
                .bodyToMono(ProductDTO.class).map(ProductDTO ->{
                    OrderResponseDTO orderDto = new OrderResponseDTO();
                    orderDto.setOrderId(od.getOrderId());
                    orderDto.setProductId(od.getProductId());
                    orderDto.setQuantity(od.getQuantity());
                    //setting product details
                    orderDto.setProductName(ProductDTO.getName());
                    orderDto.setProductPrice(ProductDTO.getPrice());
                    orderDto.setTotalPrice(ProductDTO.getPrice()*od.getQuantity());
                    return ResponseEntity.ok(orderDto);
                });
    }
    @GetMapping("allOrders")
    public List getAllOrders(){
        return repo.findAll();
    }
}
