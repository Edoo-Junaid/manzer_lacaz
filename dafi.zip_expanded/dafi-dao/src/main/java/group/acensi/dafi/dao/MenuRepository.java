package group.acensi.dafi.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import group.acensi.dafi.entities.*;
public interface MenuRepository extends JpaRepository<Menu,Long>{
    
}
