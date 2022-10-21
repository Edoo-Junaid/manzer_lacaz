package group.acensi.manzerlacaz.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.acensi.manzerlacaz.dao.OrderRepository;
import group.acensi.manzerlacaz.entities.Order;
import group.acensi.manzerlacaz.service.api.OrderService;
import group.acensi.manzerlacaz.service.dto.OrderDto;
import group.acensi.manzerlacaz.service.mapper.OrderMapper;

@Service
public class OrderSericeImpl implements OrderService {

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
        System.out.println(this.getCurrentWeekNumber() + " " + day);
        return orderRepository.countOrdersByDayAndWeekNum(this.getCurrentWeekNumber(), day);
    }

    @Override
    public Long getOrderOptionCountByDay(String option, String day) {
        System.out.println(this.getCurrentWeekNumber() + " " + day);
        return orderRepository.countOrdersOptionByDayAndWeekNum(option, this.getCurrentWeekNumber(), day);
    }
    
    
    @Override
    public int findOrderId(int user_id,int menu_id){
        return orderRepository.findOrderById(user_id, menu_id);
    }
    
    @Override 
    public void deleteOrder(int user_id,int menu_id) {
        orderRepository.deleteById((long) findOrderId(user_id,menu_id));
        
    }

}
