package ta.presentation.dentalt.offer.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.appointment.service.implementation.AppointmentServiceImplementation;
import ta.presentation.dentalt.offer.model.dto.CategoryDTO;
import ta.presentation.dentalt.offer.model.entity.CategoryEntity;
import ta.presentation.dentalt.offer.repository.CategoryRepository;
import ta.presentation.dentalt.offer.model.mapper.CategoryConverter;
import ta.presentation.dentalt.offer.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImplementation implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceImplementation.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO getCategory(Integer id) {
        if (categoryRepository.findById(id).isEmpty() || categoryRepository.findById(id).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Category not found.");
            throw new RuntimeException("Category not found.");
        }
        return CategoryConverter.convertCategoryEntityToDTO(categoryRepository.findById(id).get());
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .filter(category -> category.getValidity().equals(Boolean.TRUE))
                .map(CategoryConverter::convertCategoryEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {
        CategoryEntity category = new CategoryEntity();
        category.setName(dto.getName());
        category.setDiscountPercentage(dto.getDiscountPercentage());
        category.setValidity(Boolean.TRUE);
        categoryRepository.save(category);
        return CategoryConverter.convertCategoryEntityToDTO(category);
    }

    @Override
    public CategoryDTO changeCategory(CategoryDTO dto) {
        if (categoryRepository.findById(dto.getId()).isEmpty() || categoryRepository.findById(dto.getId()).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Category not found.");
            throw new RuntimeException("Category not found.");
        }
        CategoryEntity category = categoryRepository.findById(dto.getId()).get();
        category.setName(dto.getName());
        category.setDiscountPercentage(dto.getDiscountPercentage());
        return CategoryConverter.convertCategoryEntityToDTO(category);
    }

    @Override
    public CategoryDTO changeName(Integer id, String name) {
        if (!categoryRepository.findById(id).isPresent() || categoryRepository.findById(id).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Category not found.");
            throw new RuntimeException("Category not found.");
        }
        CategoryEntity category = categoryRepository.findById(id).get();
        category.setName(name);
        return CategoryConverter.convertCategoryEntityToDTO(category);
    }

    @Override
    public CategoryDTO changeDiscountPercentage(Integer id, Double discountPercentage) {
        if (categoryRepository.findById(id).isEmpty() || categoryRepository.findById(id).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Category not found.");
            throw new RuntimeException("Category not found.");
        }
        CategoryEntity category = categoryRepository.findById(id).get();
        category.setDiscountPercentage(discountPercentage);
        return CategoryConverter.convertCategoryEntityToDTO(category);
    }

    @Override
    public Integer deleteCategory(Integer id) {
        if (categoryRepository.findById(id).isEmpty() || categoryRepository.findById(id).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Category not found.");
            throw new RuntimeException("Category not found.");
        }
        CategoryEntity categoryToDelete = categoryRepository.findById(id).get();
        categoryToDelete.setValidity(Boolean.FALSE);
        categoryRepository.save(categoryToDelete);
        return id;
    }

    @Override
    public CategoryDTO restoreCategory(Integer id) {
        if (categoryRepository.findById(id).isEmpty() || categoryRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            LOGGER.info("Category not found.");
            throw new RuntimeException("Category not found.");
        }
        CategoryEntity categoryToRestore = categoryRepository.findById(id).get();
        categoryToRestore.setValidity(Boolean.TRUE);
        categoryRepository.save(categoryToRestore);
        return CategoryConverter.convertCategoryEntityToDTO(categoryToRestore);
    }
}
