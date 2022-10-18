package group.acensi.dafi.service.impl;

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
}
