package ta.presentation.dentalt.offer.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ta.presentation.dentalt.offer.model.dto.CategoryDTO;
import ta.presentation.dentalt.offer.model.entity.CategoryEntity;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToDTO(CategoryEntity offers);

    CategoryEntity categoryDTOToOffers(CategoryDTO offersDTO);
}
