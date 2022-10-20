package group.acensi.manzerlacaz.service.api;


import java.time.LocalDate;

import group.acensi.manzerlacaz.entities.Order;
import group.acensi.manzerlacaz.service.dto.OrderDto;

public interface OrderService {
    public Order createOrder(OrderDto orderDto);
    public int getCurrentWeekNumber();
    public Long getOrderCountByDay(String day);
	Long getOrderOptionCountByDay(String option,String day);
  

}