package ta.presentation.dentalt.offer.service.services;

import ta.presentation.dentalt.offer.model.dto.OfferDTO;
import ta.presentation.dentalt.offer.model.dto.OfferNewDateDTO;

import java.util.List;

public interface OfferService {

    OfferDTO getOffer(Integer id);

    List<OfferDTO> getAllOffers();

    OfferDTO createOffer(OfferDTO offerDTO);

    OfferDTO changeDate(Integer id, OfferNewDateDTO newDate);

    OfferDTO changeOfferPrice(Integer id, Double price);

    Integer deleteOffer(Integer id);

    OfferDTO restoreOffer(Integer id);
}
