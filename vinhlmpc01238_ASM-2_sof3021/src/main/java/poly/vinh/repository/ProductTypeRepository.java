package poly.vinh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import poly.vinh.repository.entity.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
	
	@Query(value = "select distinct t.name from ProductType t")
	List<String> findTypes();

	@Query(value = "select t from ProductType t where t.product.idProduct = ?1")
	List<ProductType> findByIdProduct(Integer idProduct);
	
}
