package ta.presentation.dentalt.offers.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ta.presentation.dentalt.offers.models.dto.OffersDTO;
import ta.presentation.dentalt.offers.models.entity.OffersEntity;


@Mapper
public interface OffersMapper {

    OffersMapper INSTANCE = Mappers.getMapper(OffersMapper.class);

    OffersDTO offersToDTO(OffersEntity offers);
    OffersEntity offersDTOToOffers(OffersDTO offersDTO);
}
