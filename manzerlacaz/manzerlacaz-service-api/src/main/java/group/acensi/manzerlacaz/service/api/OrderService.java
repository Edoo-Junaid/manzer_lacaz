package group.acensi.manzerlacaz.service.api;

import java.time.LocalDate;

import group.acensi.manzerlacaz.entities.Order;
import group.acensi.manzerlacaz.service.dto.OrderDto;

public interface OrderService {
    public OrderDto createOrder(OrderDto orderDto); //creating an order

    public int getCurrentWeekNumber(); //calculating current week number

    public Long getOrderCountByDay(String day); //get total number of orders in a day

    public Long getOrderOptionCountByDay(String option, String day,int weekNum); // get total number of options(veg/non-veg in a day)

    public long findOrderId(Long user_id, Long menu_id);//finding an order by id

    public void deleteOrder(Long user_id, Long menu_id);// to delete an order

    LocalDate calculateDate(int id); //to find date of a menu record in database

    public boolean checkOrderExists(Long user_id, Long menu_id); //checks if order exists in database

    OrderDto getExistingOrder(Long user_id, Long menu_id);


}
