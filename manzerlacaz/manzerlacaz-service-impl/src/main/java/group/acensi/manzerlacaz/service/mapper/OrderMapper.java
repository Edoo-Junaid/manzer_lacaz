package group.acensi.manzerlacaz.service.mapper;

import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import group.acensi.manzerlacaz.entities.Order;
import group.acensi.manzerlacaz.service.dto.OrderDto;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

	Order toEntity(OrderDto dto);

	OrderDto toDto(Order order);
}