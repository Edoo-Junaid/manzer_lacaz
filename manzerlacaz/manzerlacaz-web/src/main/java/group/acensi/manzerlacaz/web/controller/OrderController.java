package group.acensi.manzerlacaz.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group.acensi.manzerlacaz.entities.Order;
import group.acensi.manzerlacaz.service.api.OrderService;
import group.acensi.manzerlacaz.service.dto.OrderDto;
import group.acensi.manzerlacaz.web.payload.DeleteOrderRequest;
import group.acensi.manzerlacaz.web.payload.OrderOptionCountRequest;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {

    @Autowired
    private OrderService orderService;
//Add an order
    @PostMapping("/addOrder")
    public ResponseEntity<Order> addMenu(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<Order>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

    // Add orders for a week
    @PostMapping("/addWeekOrder")
    public List<Order> addWeekOrder(@RequestBody List<OrderDto> orders) {
        List<Order> ordersSaved = new ArrayList<Order>();
        for (OrderDto order : orders) {
            ordersSaved.add(orderService.createOrder(order));
        }
        return ordersSaved;
    }

    //Get total count of orders in a day
    @PostMapping("/getOrderCountByDay")
    public Long getOrderCountByDay(@RequestBody String day) {
        return orderService.getOrderCountByDay(day);
    }

    //Get total count of veg or non-veg orders in a day 
    @PostMapping("/getOrderOptionCountByDay")
    public Long getOrderOptionCountByDay(@RequestBody OrderOptionCountRequest o) {
        return orderService.getOrderOptionCountByDay(o.option(), o.day());
    }

    //Delete an order
    @PostMapping("/deleteOrder")
    public void findOrders(@RequestBody DeleteOrderRequest o) {
        orderService.deleteOrder(o.user_id(), o.menu_id());
    }
    

    //Check if order exist or not in database
    @PostMapping("/existOrNotOrder")
    public void checkIfExistOrders(@RequestBody DeleteOrderRequest o) {
        orderService.checkOrderExists(o.user_id(), o.menu_id());
    }

    //Delete a list of orders
    @PostMapping("deleteOrders")
    public void deleteOrders(@RequestBody List<DeleteOrderRequest> orders) {
        for (DeleteOrderRequest order : orders) {
            orderService.deleteOrder(order.user_id(), order.menu_id());
        }
    }
}
