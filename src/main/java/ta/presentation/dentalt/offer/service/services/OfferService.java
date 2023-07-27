package ta.presentation.dentalt.offer.service.services;

import ta.presentation.dentalt.category.model.dto.CategoryDTO;
import ta.presentation.dentalt.offer.model.dto.OfferDTO;
import ta.presentation.dentalt.offer.model.dto.OfferNewDateDTO;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;

import java.util.List;

public interface OfferService {

    OfferDTO getOffer(Integer id);

    List<OfferDTO> getAllOffers();

    OfferDTO createOffer(OfferDTO offerDTO);

    OfferDTO changeOffer(Integer id, OfferDTO newDate);

    OfferDTO changeName(Integer id, String name);

    OfferDTO changeDate(Integer id, OfferNewDateDTO newDate);

    OfferDTO changePrice(Integer id, Double price);

    OfferDTO addOperation(Integer id, OperationDTO operationDTO);

    OfferDTO removeOperation(Integer offerId, Integer operationId);

    OfferDTO addCategory(Integer id, CategoryDTO categoryDTO);

    OfferDTO removeCategory(Integer offerId, Integer categoryId);

    Integer deleteOffer(Integer id);

    OfferDTO restoreOffer(Integer id);
}
