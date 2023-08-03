package ta.presentation.dentalt.offer.category.model.mapper;

import ta.presentation.dentalt.offer.category.model.dto.CategoryDTO;
import ta.presentation.dentalt.offer.category.model.entity.CategoryEntity;

public class CategoryConverter {

    public static CategoryDTO convertCategoryEntityToDTO(CategoryEntity category) {
        CategoryDTO dto = new CategoryDTO();

        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDiscountPercentage(category.getDiscountPercentage());

        return dto;
    }
}
