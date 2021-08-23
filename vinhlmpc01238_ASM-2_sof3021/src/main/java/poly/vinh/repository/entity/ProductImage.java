package poly.vinh.repository.entity;

import lombok.Data;

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
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product_images")
public class ProductImage implements Serializable {
	private static final long serialVersionUID = -700725340304527592L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_image", nullable = false)
	private Integer idImage;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_product", nullable = false)
	private Product product;
	
	@Column(name = "name_image", nullable = false)
	private String nameImage;
	
	@Column(name = "alternative")
	private String alternative;
	
}
