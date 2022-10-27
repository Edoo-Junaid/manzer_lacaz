package group.acensi.manzerlacaz.service.api;

import java.time.LocalDate;

import group.acensi.manzerlacaz.entities.Order;
import group.acensi.manzerlacaz.service.dto.OrderDto;

public interface OrderService {
    public Order createOrder(OrderDto orderDto);

    public int getCurrentWeekNumber();

    public Long getOrderCountByDay(String day);

    public Long getOrderOptionCountByDay(String option, String day);

    public long findOrderId(Long user_id, Long menu_id);

    public void deleteOrder(Long user_id, Long menu_id);

    LocalDate calculateDate(int id);

    void checkOrderExists(Long user_id, Long menu_id);


}
