package ta.presentation.dentalt.operation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Integer> {

}
