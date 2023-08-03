package ta.presentation.dentalt.offer.category.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ta.presentation.dentalt.offer.category.model.dto.CategoryDTO;
import ta.presentation.dentalt.offer.category.model.entity.CategoryEntity;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToDTO(CategoryEntity offers);

    CategoryEntity categoryDTOToOffers(CategoryDTO offersDTO);
}
