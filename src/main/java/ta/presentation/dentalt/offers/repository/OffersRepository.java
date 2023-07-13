package ta.presentation.dentalt.offers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ta.presentation.dentalt.offers.model.entity.OffersEntity;

@Repository
public interface OffersRepository extends JpaRepository<OffersEntity, Integer> {

}
