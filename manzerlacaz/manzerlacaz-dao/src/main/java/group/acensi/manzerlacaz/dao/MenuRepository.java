package group.acensi.manzerlacaz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import group.acensi.manzerlacaz.entities.*;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByWeekNum(int weekNum);

    @Query("SELECT weekNum FROM Menu WHERE id=?1")
    public int weekNumMenu(int id);

    @Query("SELECT day FROM Menu WHERE id=?1")
    public String weekDay(int id);

    @Query("SELECT EXISTS(SELECT 1 FROM Menu WHERE weekNum=?1 AND day=?2)")
    public boolean checkIfOrderExists(int weekNumn,String day);

    @Query("SELECT id FROM Menu WHERE weekNum=?1 AND day=?2")
    public Long getIdFromDayAndWeekNum(int weekNum, String day);

}
