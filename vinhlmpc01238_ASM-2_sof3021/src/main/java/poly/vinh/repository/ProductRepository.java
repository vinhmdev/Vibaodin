package poly.vinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poly.vinh.repository.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
