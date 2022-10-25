package group.acensi.manzerlacaz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import group.acensi.manzerlacaz.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT id FROM Order where user_id=?1 AND menu_id=?2")
    public int findOrderById(int user_id, int menu_id);

    @Query("SELECT COUNT(o) FROM Order o INNER JOIN Menu m ON m.id=o.menu_id WHERE m.weekNum = ?1 AND m.day = ?2")
    public Long countOrdersByDayAndWeekNum(int weekNum, String day);

    @Query("SELECT COUNT(o) FROM Order o  INNER JOIN Menu m ON m.id=o.menu_id WHERE o.option=?1 AND m.weekNum = ?2 AND m.day = ?3")
    public Long countOrdersOptionByDayAndWeekNum(String option, int weekNum, String day);

    @Query("SELECT EXISTS(SELECT 1 FROM Order WHERE user_id=?1 AND menu_id=?2)")
    public boolean checkIfOrderExists(int user_id, int menu_id);

 

}
