package sv.com.iblat.billing.backend.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.com.iblat.billing.backend.model.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
