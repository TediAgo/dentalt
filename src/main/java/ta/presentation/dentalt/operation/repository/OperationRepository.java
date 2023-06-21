package ta.presentation.dentalt.operation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ta.presentation.dentalt.operation.model.entity.OperationEntity;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Integer> {

}
