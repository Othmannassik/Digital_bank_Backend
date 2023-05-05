package ma.emsi.digitalbankbackend.repositories;

import ma.emsi.digitalbankbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
