package group.acensi.dafi.web.controller;

import java.util.ArrayList;
import java.util.List;

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


@RestController
@RequestMapping("/api/auth/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @PostMapping("/addOrder")
    public  ResponseEntity<Order> addMenu(@RequestBody OrderDto orderDto) {
       return new ResponseEntity<Order>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

    @PostMapping("/addWeekOrder")
    public List<Order> addWeekOrder(@RequestBody List<OrderDto> orders) {
        List<Order> ordersSaved = new ArrayList<Order>();
        for (OrderDto order : orders) {
            ordersSaved.add(orderService.createOrder(order));
        }
        return ordersSaved;
    }

    @PostMapping("/getOrderCountByDay")
    public Long getOrderCountByDay(@RequestBody String day) {
        return orderService.getOrderCountByDay(day);
    }
}
