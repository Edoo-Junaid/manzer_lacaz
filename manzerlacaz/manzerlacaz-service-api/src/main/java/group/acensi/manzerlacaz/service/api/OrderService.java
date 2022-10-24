package group.acensi.manzerlacaz.service.api;

import java.time.LocalDate;

import group.acensi.manzerlacaz.entities.Order;
import group.acensi.manzerlacaz.service.dto.OrderDto;

public interface OrderService {
    public Order createOrder(OrderDto orderDto);

    public int getCurrentWeekNumber();

    public Long getOrderCountByDay(String day);

    public Long getOrderOptionCountByDay(String option, String day);

    public int findOrderId(int user_id, int menu_id);

    public void deleteOrder(int user_id, int menu_id);

    Order createTrialOrder(OrderDto orderDto);

    LocalDate calculateDate(int id);

   void checkOrderExists(int user_id, int menu_id);

}
