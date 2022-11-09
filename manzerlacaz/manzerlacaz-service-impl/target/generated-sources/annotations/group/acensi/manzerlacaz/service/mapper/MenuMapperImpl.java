package group.acensi.manzerlacaz.service.mapper;

import group.acensi.manzerlacaz.entities.Menu;
import group.acensi.manzerlacaz.service.dto.MenuDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-09T08:27:42+0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
public class MenuMapperImpl implements MenuMapper {

    @Override
    public Menu toEntity(MenuDto dto) {
        if ( dto == null ) {
            return null;
        }

        Menu menu = new Menu();

        menu.setId( dto.getId() );
        menu.setLastModified( dto.getLastModified() );
        menu.setCreated( dto.getCreated() );
        menu.setDescription( dto.getDescription() );
        menu.setPrice( dto.getPrice() );
        menu.setDay( dto.getDay() );
        menu.setOption( dto.getOption() );
        menu.setWeekNum( dto.getWeekNum() );
        menu.setYear( dto.getYear() );

        return menu;
    }

    @Override
    public MenuDto toDto(Menu menu) {
        if ( menu == null ) {
            return null;
        }

        MenuDto menuDto = new MenuDto();

        menuDto.setId( menu.getId() );
        menuDto.setDescription( menu.getDescription() );
        menuDto.setPrice( menu.getPrice() );
        menuDto.setDay( menu.getDay() );
        menuDto.setOption( menu.getOption() );
        menuDto.setWeekNum( menu.getWeekNum() );
        menuDto.setYear( menu.getYear() );
        menuDto.setLastModified( menu.getLastModified() );
        menuDto.setCreated( menu.getCreated() );

        return menuDto;
    }
}
