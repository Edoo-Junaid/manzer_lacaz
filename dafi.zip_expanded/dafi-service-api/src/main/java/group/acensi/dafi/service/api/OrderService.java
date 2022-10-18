package group.acensi.dafi.service.api;

import group.acensi.dafi.entities.Menu;
import group.acensi.dafi.entities.Order;
import group.acensi.dafi.service.dto.MenuDto;
import group.acensi.dafi.service.dto.OrderDto;

public interface OrderService {
    public Order createOrder(OrderDto orderDto);
}
