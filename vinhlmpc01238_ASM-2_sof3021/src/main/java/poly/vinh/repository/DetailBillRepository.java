package poly.vinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.vinh.repository.entity.DetailBill;
import poly.vinh.repository.entity.DetailBillId;

public interface DetailBillRepository extends JpaRepository<DetailBill, DetailBillId> {

}
