package group.acensi.manzerlacaz.service.mapper;

import group.acensi.manzerlacaz.entities.Menu;
import group.acensi.manzerlacaz.service.dto.MenuDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-01T21:08:42+0400",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
public class MenuMapperImpl implements MenuMapper {

    @Override
    public Menu toEntity(MenuDto dto) {
        if ( dto == null ) {
            return null;
        }

        Menu menu = new Menu();

        menu.setCreated( dto.getCreated() );
        menu.setId( dto.getId() );
        menu.setLastModified( dto.getLastModified() );
        menu.setDay( dto.getDay() );
        menu.setDescription( dto.getDescription() );
        menu.setOption( dto.getOption() );
        menu.setPrice( dto.getPrice() );
        menu.setWeekNum( dto.getWeekNum() );

        return menu;
    }

    @Override
    public MenuDto toDto(Menu menu) {
        if ( menu == null ) {
            return null;
        }

        MenuDto menuDto = new MenuDto();

        menuDto.setCreated( menu.getCreated() );
        menuDto.setDay( menu.getDay() );
        menuDto.setDescription( menu.getDescription() );
        menuDto.setId( menu.getId() );
        menuDto.setLastModified( menu.getLastModified() );
        menuDto.setOption( menu.getOption() );
        menuDto.setPrice( menu.getPrice() );
        menuDto.setWeekNum( menu.getWeekNum() );

        return menuDto;
    }
}
