package ta.presentation.dentalt.offer.category.controller;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ta.presentation.dentalt.offer.category.model.dto.CategoryDTO;
import ta.presentation.dentalt.offer.category.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
//@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:read', 'doctor:read', 'patient:read')")
    public ResponseEntity<CategoryDTO> getCategory(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:read', 'doctor:read', 'patient:read')")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:create')")
    public ResponseEntity<CategoryDTO> createCategory(@NonNull @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    @PutMapping("/change")
    @PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('admin:update')")
    public ResponseEntity<CategoryDTO> changeCategory(@NonNull @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.changeCategory(categoryDTO));
    }

    @PutMapping("/{id}/changeName")
    @PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:update')")
    public ResponseEntity<CategoryDTO> changeName(@NonNull @PathVariable(value = "id") Integer id,
                                                  @NonNull @RequestBody String name) {
        return ResponseEntity.ok(categoryService.changeName(id, name));
    }

    @PutMapping("/{id}/changeDiscountPercentage")
    @PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:update')")
    public ResponseEntity<CategoryDTO> changeDiscountPercentage(@NonNull @PathVariable(value = "id") Integer id,
                                                                @NonNull @RequestBody Double discountPercentage) {
        return ResponseEntity.ok(categoryService.changeDiscountPercentage(id, discountPercentage));
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:delete')")
    public ResponseEntity<Integer> deleteCategory(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }

    @PutMapping("/{id}/restore")
    @PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:update')")
    public ResponseEntity<CategoryDTO> restoreCategory(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(categoryService.restoreCategory(id));
    }
}
