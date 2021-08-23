package poly.vinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poly.vinh.repository.entity.Basket;
import poly.vinh.repository.entity.BasketId;

@Repository
public interface BasketRepository extends JpaRepository<Basket, BasketId> {

}
