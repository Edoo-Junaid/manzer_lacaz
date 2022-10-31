package group.acensi.manzerlacaz.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.acensi.manzerlacaz.dao.ConfigRepository;
import group.acensi.manzerlacaz.dao.MenuRepository;
import group.acensi.manzerlacaz.dao.OrderRepository;
import group.acensi.manzerlacaz.entities.Config;
import group.acensi.manzerlacaz.entities.Order;
import group.acensi.manzerlacaz.service.api.OrderService;
import group.acensi.manzerlacaz.service.dto.OrderDto;
import group.acensi.manzerlacaz.service.mapper.OrderMapper;

@Service
public class OrderSericeImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ConfigRepository configRepository;

    // Update order if exists else create
    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        LocalDate dateOfMenu = calculateDate(orderDto.getMenu_id().intValue());

        LocalDate dateCurrent = LocalDate.now();

        LocalTime time = LocalTime.now();
        String preSetValue = configRepository.presetTime();
        System.out.println(configRepository.presetTime());
        LocalTime preSet = LocalTime.parse(preSetValue);
        if ((dateCurrent.toString().equals(dateOfMenu.toString()) && time.isAfter(preSet))
                || dateCurrent.isAfter(dateOfMenu)) {

            return null;
        } else {
            if (orderRepository.checkIfOrderExists(orderDto.getUser_id(), orderDto.getMenu_id())) {

                orderDto.setId(this.findOrderId(orderDto.getUser_id(), orderDto.getMenu_id()));
            }

            Order order = OrderMapper.INSTANCE.toEntity(orderDto);
            System.out.println(order);
            return OrderMapper.INSTANCE.toDto(orderRepository.save(order));
        }
    }

    // // Update order if exists else create
    // @Override
    // public Order createOrder(OrderDto orderDto) {
    // LocalDate dateOfMenu = calculateDate(orderDto.getMenu_id().intValue());
    // System.out.println("date of menu: " +dateOfMenu);
    // LocalDate dateCurrent = LocalDate.now();
    // System.out.println("date current: " +dateCurrent);
    // LocalTime time = LocalTime.now();
    // String preSetValue = "10:00:00";
    // System.out.println(orderDto);
    // LocalTime preSet = LocalTime.parse(preSetValue);
    // if ((dateCurrent.toString().equals(dateOfMenu.toString()) &&
    // time.isAfter(preSet))
    // || dateCurrent.isAfter(dateOfMenu)) {

    // System.out.println("order time elapsed");
    // return null;
    // } else {
    // if (orderRepository.checkIfOrderExists(orderDto.getUser_id(),
    // orderDto.getMenu_id())) {
    // System.out.println("order exists");
    // orderDto.setId(this.findOrderId(orderDto.getUser_id(),
    // orderDto.getMenu_id()));
    // }
    // System.out.println("order created");
    // Order order = OrderMapper.INSTANCE.toEntity(orderDto);
    // System.out.println(orderDto);
    // System.out.println(order);
    // System.out.println(orderRepository.save(order));
    // return orderRepository.save(order);
    // }

    // }

    // @Override
    // public OrderDto createOrder(OrderDto orderDto) {

    // LocalDate dateOfMenu = calculateDate(orderDto.getMenu_id().intValue());
    // LocalDate dateCurrent = LocalDate.now();
    // LocalTime time = LocalTime.now();
    // String preSetValue = "11:00:00";
    // LocalTime preSet = LocalTime.parse(preSetValue);
    // if ((dateCurrent.toString().equals(dateOfMenu.toString()) &&
    // time.isAfter(preSet))
    // || dateCurrent.isAfter(dateOfMenu)) {
    // System.out.println("order time elapsed");
    // return null;
    // }else {
    // if(checkOrderExists(orderDto.getUser_id(),orderDto.getMenu_id())){
    // orderDto.setId(this.findOrderId(orderDto.getUser_id(),
    // orderDto.getMenu_id()));
    // }
    // Order order = OrderMapper.INSTANCE.toEntity(orderDto);
    // return OrderMapper.INSTANCE.toDto(orderRepository.save(order));
    // }
    // }

    // Get current week number calendar
    @Override
    public int getCurrentWeekNumber() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    // Get total number of orders in a day
    @Override
    public Long getOrderCountByDay(String day) {
        return orderRepository.countOrdersByDayAndWeekNum(this.getCurrentWeekNumber(), day);
    }

    // Get total veg or non-veg orders in a day
    @Override
    public Long getOrderOptionCountByDay(String option, String day) {
        return orderRepository.countOrdersOptionByDayAndWeekNum(option, this.getCurrentWeekNumber(), day);
    }

    @Override
    public long findOrderId(Long user_id, Long menu_id) {
        return orderRepository.findOrderById(user_id, menu_id);
    }

    // Finding an order by its id and Deleting the order
    @Override
    public void deleteOrder(Long user_id, Long menu_id) {
        if (orderRepository.checkIfOrderExists(user_id, menu_id)) {
            orderRepository.deleteById(this.findOrderId(user_id, menu_id));
        }
    }

    // Calculate date using week number and day of the week
    @Override
    public LocalDate calculateDate(int id) {
        HashMap<String, Integer> Days = new HashMap<>() {
            {
                this.put("Monday", 1);
                this.put("Tuesday", 2);
                this.put("Wednesday", 3);
                this.put("Thursday", 4);
                this.put("Friday", 5);
                this.put("Saturday", 6);
                this.put("Sunday", 7);
            }
        };
        int weekNum = menuRepository.weekNumMenu(id);
        System.out.println("week num: " + weekNum);
        String day = menuRepository.weekDay(id);
        int dayOfWeek = Days.get(day);
        System.out.println("day of week: " + dayOfWeek);
        LocalDate date = LocalDate.now()
                .with(WeekFields.ISO.weekBasedYear(), Calendar.getInstance().get(Calendar.YEAR)) // year
                .with(WeekFields.ISO.weekOfWeekBasedYear(), weekNum - 1) // week of year
                .with(WeekFields.ISO.dayOfWeek(), dayOfWeek); // day of week
        return date;
    }

    // Check if order exists in database
    @Override
    public boolean checkOrderExists(Long user_id, Long menu_id) {
        return orderRepository.checkIfOrderExists(user_id, menu_id);
    }

}
