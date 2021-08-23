package poly.vinh.repository.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@IdClass(BasketId.class)
@Table(name = "baskets")
public class Basket implements Serializable {
	private static final long serialVersionUID = 4694290828600721311L;

	@Id
	@Column(name = "id_account", nullable = false)
	private Integer idAccount;
	
	@Id
	@Column(name = "id_product", nullable = false)
	private Integer idProduct;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_account", nullable = false, insertable = false, updatable = false)
	private Account account;

	@ManyToOne
	@JoinColumn(name = "id_product", nullable = false, insertable = false, updatable = false)
	private Product product;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity = 1;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "push_date", nullable = false)
	private Date pushDate = new Date();

	@Override
	public String toString() {
		return "";
	}
	
}
