package group.acensi.manzerlacaz.service.mapper;

import group.acensi.manzerlacaz.entities.Order;
import group.acensi.manzerlacaz.service.dto.OrderDto;
import java.util.Optional;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-09T11:00:58+0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toEntity(OrderDto dto) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( dto.getId() );
        order.setLastModified( dto.getLastModified() );
        order.setCreated( dto.getCreated() );
        if ( dto.getUser_id() != null ) {
            order.setUser_id( dto.getUser_id() );
        }
        if ( dto.getMenu_id() != null ) {
            order.setMenu_id( dto.getMenu_id() );
        }
        order.setPayment( dto.getPayment() );
        order.setOption( dto.getOption() );

        return order;
    }

    @Override
    public OrderDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setId( order.getId() );
        orderDto.setUser_id( order.getUser_id() );
        orderDto.setMenu_id( order.getMenu_id() );
        orderDto.setPayment( order.getPayment() );
        orderDto.setOption( order.getOption() );
        orderDto.setLastModified( order.getLastModified() );
        orderDto.setCreated( order.getCreated() );

        return orderDto;
    }

    @Override
    public OrderDto toDto(Optional<Order> order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        return orderDto;
    }
}
