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
import group.acensi.dafi.service.mapper.OrderMapper;
import group.acensi.dafi.web.payload.CreateOrderRequest;

@RestController
@RequestMapping("/api/auth/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @PostMapping("/addOrder")
    public  ResponseEntity<Order> addMenu(@RequestBody CreateOrderRequest createOrderRequest) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUser_id(createOrderRequest.user_id());
        orderDto.setMenu_id(createOrderRequest.menu_id());
        orderDto.setPayment(createOrderRequest.payment());
        orderDto.setOption(createOrderRequest.option());
        orderDto.setWeekNum(orderService.getCurrentWeekNumber());

       return new ResponseEntity<Order>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

    @PostMapping("/addWeekOrder")
    public List<Order> addWeekOrder(@RequestBody List<CreateOrderRequest> createOrderRequest) {
        List<Order> orders = new ArrayList<Order>();
        for (CreateOrderRequest order : createOrderRequest) {
            OrderDto orderDto = new OrderDto();
            orderDto.setUser_id(order.user_id());
            orderDto.setMenu_id(order.menu_id());
            orderDto.setPayment(order.payment());
            orderDto.setOption(order.option());
            orderDto.setWeekNum(orderService.getCurrentWeekNumber());
            orders.add(orderService.createOrder(orderDto));
        }
        return orders;
    }

    @PostMapping("/getOrderCountByDay")
    public Long getOrderCountByDay(@RequestBody String day) {
        return orderService.getOrderCountByDay(day);
    }
}
