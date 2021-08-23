package poly.vinh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import poly.vinh.repository.entity.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

	@Query(value = "select i from ProductImage i where i.product.idProduct = ?1 order by i.idImage desc")
	List<ProductImage> findByIdProduct(Integer idProduct);
	
	@Query(value = "select distinct i.nameImage from ProductImage i")
	List<String> findExistImages();

}
