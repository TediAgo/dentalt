package ta.presentation.dentalt.offers.service.services;

import ta.presentation.dentalt.offers.model.dto.OffersDTO;
import ta.presentation.dentalt.offers.model.dto.OffersNewDateDTO;

import java.util.List;

public interface OffersService {
    OffersDTO getOffer(Integer id);

    List<OffersDTO> getAllOffers();

    OffersDTO createOffer(OffersDTO offerDTO);

    OffersDTO changeOffer(OffersDTO offerDTO);

    OffersDTO changeDate(Integer id, OffersNewDateDTO newDate);

    OffersDTO changeOfferPrice(Integer id, Double price);

    Integer deleteOffer(Integer id);

    OffersDTO restoreOffer(Integer id);
}
