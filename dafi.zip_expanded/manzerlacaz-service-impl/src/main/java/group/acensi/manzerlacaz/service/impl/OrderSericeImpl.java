package group.acensi.manzerlacaz.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.acensi.manzerlacaz.dao.OrderRepository;
import group.acensi.manzerlacaz.entities.Order;
import group.acensi.manzerlacaz.service.api.OrderService;
import group.acensi.manzerlacaz.service.dto.OrderDto;
import group.acensi.manzerlacaz.service.mapper.OrderMapper;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class OrderSericeImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    
    
    
    
    @Override
    public Order createOrder(OrderDto orderDto) {
        LocalTime time = LocalTime.now();
        String preSetValue = "10:00:00";
        LocalTime preSet = LocalTime.parse(preSetValue);
        LocalDate date= LocalDate.parse("2021/06/09");
        if (time.isBefore(preSet)&& LocalDate.now()==date) {

            Order order = OrderMapper.INSTANCE.toEntity(orderDto);
            return orderRepository.save(order);
        } else {
            return null;
        }
      
        
    }

    
    @Override
    public int getCurrentWeekNumber() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    @Override
    public Long getOrderCountByDay(String day) {
        System.out.println(this.getCurrentWeekNumber() + " " + day);
        return orderRepository.countOrdersByDayAndWeekNum(this.getCurrentWeekNumber(), day);
    }

    @Override
    public Long getOrderOptionCountByDay(String option, String day) {
        System.out.println(this.getCurrentWeekNumber() + " " + day);
        return orderRepository.countOrdersOptionByDayAndWeekNum(option, this.getCurrentWeekNumber(), day);
    }

}
