package group.acensi.dafi.service.mapper;

import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import group.acensi.dafi.entities.Order;
import group.acensi.dafi.service.dto.OrderDto;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

	Order toEntity(OrderDto dto);

	OrderDto toDto(Order order);
}
