package ta.presentation.dentalt.operation.controller;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;
import ta.presentation.dentalt.operation.service.services.OperationService;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:read', 'doctor:read', 'patient:read')")
    public ResponseEntity<OperationDTO> getOperation(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(operationService.getOperation(id));
    }

    @GetMapping("/all")
    //@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:read', 'doctor:read', 'patient:read')")
    public ResponseEntity<List<OperationDTO>> getAllOperations() {
        return ResponseEntity.ok(operationService.getAllOperations());
    }

    @PostMapping("/create")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:create')")
    public ResponseEntity<OperationDTO> createOperation(@NonNull @RequestBody OperationDTO operationDTO) {
        return ResponseEntity.ok(operationService.createOperation(operationDTO));
    }

    @PutMapping("/change")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('admin:update')")
    public ResponseEntity<OperationDTO> changeOperation(@NonNull @RequestBody OperationDTO operationDTO) {
        return ResponseEntity.ok(operationService.changeOperation(operationDTO));
    }

    @PutMapping("/{id}/changeName")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:update')")
    public ResponseEntity<OperationDTO> changeOperationName(@NonNull @PathVariable(value = "id") Integer id,
                                                            @NonNull @RequestBody String name) {
        return ResponseEntity.ok(operationService.changeOperationName(id, name));
    }

    @PutMapping("/{id}/changeDescription")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:update')")
    public ResponseEntity<OperationDTO> changeOperationDescription(@NonNull @PathVariable(value = "id") Integer id,
                                                                   @NonNull @RequestBody String description) {
        return ResponseEntity.ok(operationService.changeOperationDescription(id, description));
    }

    @PutMapping("/{id}/changePrice")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:update')")
    public ResponseEntity<OperationDTO> changeOperationPrice(@NonNull @PathVariable(value = "id") Integer id,
                                                             @NonNull @RequestBody Double price) {
        return ResponseEntity.ok(operationService.changeOperationPrice(id, price));
    }

    @DeleteMapping("/{id}/delete")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:delete')")
    public ResponseEntity<Integer> deleteOperation(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(operationService.deleteOperation(id));
    }

    @PutMapping("/{id}/restore")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:update')")
    public ResponseEntity<OperationDTO> restoreOperation(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(operationService.restoreOperation(id));
    }
}
