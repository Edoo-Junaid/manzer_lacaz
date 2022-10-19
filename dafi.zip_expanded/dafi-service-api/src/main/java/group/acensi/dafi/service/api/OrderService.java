package group.acensi.dafi.service.api;

import group.acensi.dafi.entities.Order;
import group.acensi.dafi.service.dto.OrderDto;

public interface OrderService {
    public Order createOrder(OrderDto orderDto);
    public int getCurrentWeekNumber();
    public Long getOrderCountByDay(String day);

}
