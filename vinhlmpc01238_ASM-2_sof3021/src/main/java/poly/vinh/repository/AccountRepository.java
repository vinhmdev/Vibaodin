package poly.vinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poly.vinh.repository.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	Account findByUsernameLikeAndPasswordLike(String username, String password);
	
}
