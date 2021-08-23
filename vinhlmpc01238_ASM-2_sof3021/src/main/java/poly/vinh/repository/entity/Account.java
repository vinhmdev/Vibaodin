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
import lombok.ToString;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "accounts")
public class Account implements Serializable {
	private static final long serialVersionUID = 6065099433852882542L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_account", nullable = false)
	private Integer idAccount;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "is_man")
	private Boolean man;
	
	@Column(name = "is_enable", nullable = false)
	private Boolean enable = true;
	
	@Column(name = "is_admin", nullable = false)
	private Boolean admin = false;
	
	@OneToMany(mappedBy = "account")
	private List<Bill> bills;

	@OneToMany(mappedBy = "account")
	private List<Basket> baskets;
	
	//Fix recursion of lombok
	@Override
	public String toString() {
		return "";
	}
	
}
