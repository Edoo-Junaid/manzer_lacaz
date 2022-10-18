package group.acensi.dafi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group.acensi.dafi.entities.Order;
import group.acensi.dafi.service.api.OrderService;
import group.acensi.dafi.service.dto.OrderDto;
import group.acensi.dafi.service.mapper.OrderMapper;

@RestController
@RequestMapping("/api/auth/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @PostMapping("/addOrder")
    public  ResponseEntity<Order> addMenu(@RequestBody Order order) {
        System.out.println("In controller order : "+ order);
       OrderDto orderDto = OrderMapper.INSTANCE.toDto(order);
       return new ResponseEntity<Order>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }
}
