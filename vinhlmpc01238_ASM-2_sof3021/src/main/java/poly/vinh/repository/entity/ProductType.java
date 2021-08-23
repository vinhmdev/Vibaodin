package poly.vinh.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product_types")
public class ProductType implements Serializable{
	private static final long serialVersionUID = 8838112147641232950L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_type", nullable = false)
	private Integer idType;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_product", nullable = false)
	private Product product;
	
	@Column(name = "name", nullable = false)
	private String name;
	
}
