package group.acensi.dafi.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.acensi.dafi.dao.OrderRepository;
import group.acensi.dafi.entities.Order;
import group.acensi.dafi.service.api.OrderService;
import group.acensi.dafi.service.dto.OrderDto;
import group.acensi.dafi.service.mapper.OrderMapper;

@Service
public class OrderSericeImpl implements OrderService{
    
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(OrderDto orderDto) {
        Order order = OrderMapper.INSTANCE.toEntity(orderDto);
        return orderRepository.save(order);
    }

    @Override
    public int getCurrentWeekNumber() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    @Override
    public Long getOrderCountByDay(String day) {
    System.out.println(this.getCurrentWeekNumber() +" "+ day);
        return orderRepository.countOrdersByDayAndWeekNum(this.getCurrentWeekNumber(), day);
    }
    
    @Override
    public Long getOrderOptionCountByDay(String option,String day) {
    System.out.println(this.getCurrentWeekNumber() +" "+ day);
        return orderRepository.countOrdersOptionByDayAndWeekNum(option,this.getCurrentWeekNumber(), day);
    }
    
    
}
