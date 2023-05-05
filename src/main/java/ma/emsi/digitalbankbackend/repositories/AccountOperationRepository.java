package ma.emsi.digitalbankbackend.repositories;

import ma.emsi.digitalbankbackend.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
}
