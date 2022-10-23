package group.acensi.manzerlacaz.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.acensi.manzerlacaz.dao.MenuRepository;
import group.acensi.manzerlacaz.dao.OrderRepository;
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
    public int findOrderId(int user_id, int menu_id) {
        return orderRepository.findOrderById(user_id, menu_id);
    }

    @Override
    public void deleteOrder(int user_id, int menu_id) {
        orderRepository.deleteById((long) findOrderId(user_id, menu_id));

    }

    @Override
    public LocalDate calculateDate(int id) {
        int weekNum = menuRepository.weekNumMenu(id);
        String day = menuRepository.weekDay(id);
        String[] strDays = { "Nil", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        int dayOfWeek = ArrayUtils.indexOf(strDays, day);
        LocalDate date = LocalDate.now()
                .with(WeekFields.ISO.weekBasedYear(), Calendar.getInstance().get(Calendar.YEAR)) // year
                .with(WeekFields.ISO.weekOfWeekBasedYear(), weekNum) // week of year
                .with(WeekFields.ISO.dayOfWeek(), dayOfWeek); // day of week
        return date;
    }

    @Override
    public Order createTrialOrder(OrderDto orderDto) {
        LocalDate menuDate = calculateDate(orderDto.getMenu_id().intValue());
        LocalDate dateCurrent = LocalDate.now();
        LocalTime time = LocalTime.now();
        String preSetValue = "10:00:00";
        LocalTime preSet = LocalTime.parse(preSetValue);
        if (dateCurrent.toString().equals(menuDate.toString()) && time.isAfter(preSet)) {
            System.out.println("order time elapsed");
            return null;
        } else {
            Order order = OrderMapper.INSTANCE.toEntity(orderDto);
            return orderRepository.save(order);
        }

    }

}