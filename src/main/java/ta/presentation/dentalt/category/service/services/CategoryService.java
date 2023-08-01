package ta.presentation.dentalt.category.service.services;

import ta.presentation.dentalt.category.model.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO getCategory(Integer id);

    List<CategoryDTO> getAllCategories();

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO changeCategory(CategoryDTO categoryDTO);

    CategoryDTO changeName(Integer id, String name);

    CategoryDTO changeDiscountPercentage(Integer id, Double discountPercentage);

    Integer deleteCategory(Integer id);

    CategoryDTO restoreCategory(Integer id);
}
