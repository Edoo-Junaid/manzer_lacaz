package group.acensi.manzerlacaz.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.acensi.manzerlacaz.entities.*;
@Repository
public interface MenuRepository extends JpaRepository<Menu,Long>{
    List<Menu> findByWeekNum(int weekNum);
}
