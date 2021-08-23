package poly.vinh.repository.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "bills")
public class Bill  implements Serializable {
	private static final long serialVersionUID = -7686967474337052038L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_bill", nullable = false)
	private Integer idBill;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_export", nullable = false)
	private Date dateExport = new Date();
	
	@Column(name = "id_account", nullable = false)
	private Integer idAccount;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_account", insertable = false, updatable = false)
	private Account account;
	
	@OneToMany(mappedBy = "bill")
	List<DetailBill> detailBills;
	
}
