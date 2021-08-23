package poly.vinh.repository.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetailBillId implements Serializable {
	private static final long serialVersionUID = -9137668283161729860L;
	private Integer idBill;
	private Integer idProduct;
}