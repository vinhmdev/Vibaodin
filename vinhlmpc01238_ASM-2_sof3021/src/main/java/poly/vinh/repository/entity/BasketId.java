package poly.vinh.repository.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasketId implements Serializable {
	private static final long serialVersionUID = -2076801224899878232L;
	private Integer idAccount;
	private Integer idProduct;
//	private Account account;
//	private Product product;
}