package poly.vinh.repository.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product implements Serializable{
	private static final long serialVersionUID = 228260003421318444L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product", nullable = false)
	private Integer idProduct;
	
	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "price", nullable = false)
	private Double price;
	
	@Column(name = "trademark", nullable = false)
	private String trademark;

	@Column(name = "guarantee", nullable = false)
	private Double guaratee;

	@Column(name = "delivery_address", nullable = false)
	private String deliveryAddress;

	@Column(name = "detail", nullable = false)
	private String detail;
	
	@OneToMany(mappedBy = "product")
	List<DetailBill> detailBills;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Basket> baskets;
	
	@OneToMany(mappedBy = "product")
	List<ProductImage> productImages;
	
	@OneToMany(mappedBy = "product")
	List<ProductType> productTypes;
	
	@Override()
	public String toString() {
		return "";
	}
}
