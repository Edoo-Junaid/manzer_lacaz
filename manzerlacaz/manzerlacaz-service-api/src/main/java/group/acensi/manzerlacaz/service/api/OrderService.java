package group.acensi.manzerlacaz.service.api;

import java.util.List;

import group.acensi.manzerlacaz.entities.Order;
import group.acensi.manzerlacaz.service.dto.OrderDto;

public interface OrderService {
    public Order createOrder(OrderDto orderDto);

    public int getCurrentWeekNumber();

    public Long getOrderCountByDay(String day);

    public Long getOrderOptionCountByDay(String option, String day);

    public int findOrder(int user_id, int menu_id);

    public void deleteOrder(int user_id, int menu_id);

}
