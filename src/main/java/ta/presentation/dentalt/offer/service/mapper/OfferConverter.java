package ta.presentation.dentalt.offer.service.mapper;

import ta.presentation.dentalt.category.service.mapper.CategoryConverter;
import ta.presentation.dentalt.offer.model.dto.OfferDTO;
import ta.presentation.dentalt.offer.model.entity.OfferEntity;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;
import ta.presentation.dentalt.operation.service.mapper.OperationConverter;

import java.util.stream.Collectors;

public class OfferConverter {

    public static OfferDTO convertOfferEntityToDTO (OfferEntity offerEntity) {
        OfferDTO offerDTO = new OfferDTO();

        offerDTO.setId(offerEntity.getId());
        offerDTO.setName(offerEntity.getName());
        offerDTO.setBegin(offerEntity.getBegin());
        offerDTO.setFinish(offerEntity.getFinish());
        offerDTO.setPrice(offerEntity.getPrice());
        offerDTO.setOperations(offerEntity.getOperations()
                .stream()
                .map(OperationConverter::convertOperationEntityToDTO)
                .collect(Collectors.toList()));
        offerDTO.setCategoryDTO(CategoryConverter.convertCategoryEntityToDTO(offerEntity.getCategory()));

        return offerDTO;
    }

    public static OfferEntity convertOfferDTOToEntity(OfferDTO offerDTO) {


    }
}
