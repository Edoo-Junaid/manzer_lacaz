package group.acensi.manzerlacaz.service.mapper;

import group.acensi.manzerlacaz.entities.Order;
import group.acensi.manzerlacaz.service.dto.OrderDto;
import java.util.Optional;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-10T11:36:18+0400",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toEntity(OrderDto dto) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setCreated( dto.getCreated() );
        order.setId( dto.getId() );
        order.setLastModified( dto.getLastModified() );
        if ( dto.getMenu_id() != null ) {
            order.setMenu_id( dto.getMenu_id() );
        }
        order.setOption( dto.getOption() );
        order.setPayment( dto.getPayment() );
        if ( dto.getUser_id() != null ) {
            order.setUser_id( dto.getUser_id() );
        }

        return order;
    }

    @Override
    public OrderDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setCreated( order.getCreated() );
        orderDto.setId( order.getId() );
        orderDto.setLastModified( order.getLastModified() );
        orderDto.setMenu_id( order.getMenu_id() );
        orderDto.setOption( order.getOption() );
        orderDto.setPayment( order.getPayment() );
        orderDto.setUser_id( order.getUser_id() );

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
