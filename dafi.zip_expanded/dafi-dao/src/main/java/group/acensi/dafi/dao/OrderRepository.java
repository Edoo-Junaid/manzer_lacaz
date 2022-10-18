package group.acensi.dafi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.acensi.dafi.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{

}
