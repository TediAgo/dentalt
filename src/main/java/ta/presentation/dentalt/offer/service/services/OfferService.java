package ta.presentation.dentalt.offer.service.services;

import ta.presentation.dentalt.offer.model.dto.OfferDTO;
import ta.presentation.dentalt.offer.model.dto.OfferNewDateDTO;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;

import java.util.List;

public interface OfferService {

    OfferDTO getOffer(Integer id);

    List<OfferDTO> getAllOffers();

    OfferDTO createOffer(OfferDTO offerDTO);

    OfferDTO changeName(Integer id, String name);

    OfferDTO changeDate(Integer id, OfferNewDateDTO newDate);

    OfferDTO changePrice(Integer id, Double price);

    OfferDTO addOperations(Integer id, List<OperationDTO> operations);

    OfferDTO removeOperation(Integer offerId, Integer operationId);

    OfferDTO changeCategory(Integer offerId, Integer categoryId);

    Integer deleteOffer(Integer id);

    OfferDTO restoreOffer(Integer id);
}
