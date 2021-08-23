package poly.vinh.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(value = DetailBillId.class)
@Table(name = "product_bills")
public class DetailBill implements Serializable{
	private static final long serialVersionUID = -2234321194735440030L;

	@Id
	@Column(name = "id_bill", nullable = false)
	private Integer idBill;
	
	@Id
	@Column(name = "id_product", nullable = false)
	private Integer idProduct;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_bill", nullable = true, insertable = false, updatable = false)
	private Bill bill;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_product", nullable = true, insertable = false, updatable = false)
	private Product product;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Column(name = "unit_price", nullable = false)
	private Double unitPrice;
	
}
