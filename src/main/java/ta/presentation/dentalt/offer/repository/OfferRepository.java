package ta.presentation.dentalt.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ta.presentation.dentalt.offer.model.entity.OfferEntity;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Integer> {

}
