package poly.vinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poly.vinh.repository.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

}
