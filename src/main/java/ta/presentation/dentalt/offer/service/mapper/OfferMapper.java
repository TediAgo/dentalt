package ta.presentation.dentalt.offer.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ta.presentation.dentalt.offer.model.dto.OfferDTO;
import ta.presentation.dentalt.offer.model.entity.OfferEntity;


@Mapper
public interface OfferMapper {

    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);

    OfferDTO offersToDTO(OfferEntity offers);

    OfferEntity offersDTOToOffers(OfferDTO offerDTO);
}
