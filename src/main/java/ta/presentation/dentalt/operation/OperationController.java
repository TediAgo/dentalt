package ta.presentation.dentalt.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @GetMapping
    private ResponseEntity<List<OperationDTO>> getAllOperations() {
        return ResponseEntity.ok(operationService.getAllOperations());
    }

    @GetMapping
    private ResponseEntity<OperationDTO> getOperation(@RequestParam Integer id) {
        return ResponseEntity.ok(operationService.getOperation(id));
    }


}
