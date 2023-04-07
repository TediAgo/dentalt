package ta.presentation.dentalt.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ta.presentation.dentalt.operation.model.OperationDTO;
import ta.presentation.dentalt.operation.service.OperationService;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @GetMapping("/{id}")
    public ResponseEntity<OperationDTO> getOperation(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(operationService.getOperation(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OperationDTO>> getAllOperations() {
        return ResponseEntity.ok(operationService.getAllOperations());
    }

    @PostMapping("/create")
    public ResponseEntity<OperationDTO> createOperation(@RequestBody OperationDTO operationDTO) {
        return ResponseEntity.ok(operationService.createOperation(operationDTO));
    }

    @PutMapping("/{id}/changeName/{name}")
    public ResponseEntity<OperationDTO> changeOperationName(@PathVariable(value = "id") Integer id,
                                                            @PathVariable(value = "name") String name) {
        return ResponseEntity.ok(operationService.changeOperationName(id, name));
    }

    @PutMapping("/{id}/changeDescription/{description}")
    public ResponseEntity<OperationDTO> changeOperationDescription(@PathVariable(value = "id") Integer id,
                                                                   @PathVariable(value = "description") String description) {
        return ResponseEntity.ok(operationService.changeOperationDescription(id, description));
    }

    @PutMapping("/{id}/changePrice/{price}")
    public ResponseEntity<OperationDTO> changeOperationPrice(@PathVariable(value = "id") Integer id,
                                                             @PathVariable(value = "price") Double price) {
        return ResponseEntity.ok(operationService.changeOperationPrice(id, price));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Integer> deleteOperation(@PathVariable(value = "id") Integer id) {
        //operationService.deleteOperation(id);
        //return ResponseEntity.noContent().build();
        return ResponseEntity.ok(operationService.deleteOperation(id));
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<OperationDTO> restoreOperation(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(operationService.restoreOperation(id));
    }
}
