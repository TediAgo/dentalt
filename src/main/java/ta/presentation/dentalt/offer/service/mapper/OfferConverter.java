package ta.presentation.dentalt.offer.service.mapper;

import ta.presentation.dentalt.category.service.mapper.CategoryConverter;
import ta.presentation.dentalt.offer.model.dto.OfferDTO;
import ta.presentation.dentalt.offer.model.entity.OfferEntity;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;
import ta.presentation.dentalt.operation.service.mapper.OperationConverter;

import java.util.stream.Collectors;

public class OfferConverter {

    public static OfferDTO convertOfferEntityToDTO (OfferEntity offer) {
        OfferDTO offerDTO = new OfferDTO();

        offerDTO.setId(offer.getId());
        offerDTO.setName(offer.getName());
        offerDTO.setBegin(offer.getBegin());
        offerDTO.setFinish(offer.getFinish());
        offerDTO.setPrice(offer.getPrice());
        offerDTO.setOperations(offer.getOperations()
                .stream()
                .map(OperationConverter::convertOperationEntityToDTO)
                .collect(Collectors.toList()));
        offerDTO.setCategories(offer.getCategories()
                .stream()
                .map(CategoryConverter::convertCategoryEntityToDTO)
                .collect(Collectors.toList()));

        return offerDTO;
    }
}
