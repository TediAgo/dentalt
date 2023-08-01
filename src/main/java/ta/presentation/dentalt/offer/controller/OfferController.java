package ta.presentation.dentalt.offer.controller;

import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ta.presentation.dentalt.offer.model.dto.OfferDTO;
import ta.presentation.dentalt.offer.model.dto.OfferNewDateDTO;
import ta.presentation.dentalt.offer.service.services.OfferService;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;

import java.util.List;

@RestController
@RequestMapping("/offers")
//@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:read', 'doctor:read', 'patient:read')")
    public ResponseEntity<OfferDTO> getOffer(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(offerService.getOffer(id));
    }

    @GetMapping("/all")
    //@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:read', 'doctor:read', 'patient:read')")
    public ResponseEntity<List<OfferDTO>> getAllOffers() {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @PostMapping("/create")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:create')")
    public ResponseEntity<OfferDTO> createOffer(@NonNull @RequestBody OfferDTO offerDTO) {
        return ResponseEntity.ok(offerService.createOffer(offerDTO));
    }

    @PutMapping("{id}/changeName")
    //@PreAuthorize("hasAnyRole('DOCTOR')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:update')")
    public ResponseEntity<OfferDTO> changeName(@NonNull @PathVariable(value = "id") Integer id,
                                               @NonNull @RequestBody String name) {
        return ResponseEntity.ok(offerService.changeName(id, name));
    }

    @PutMapping("{id}/changeDate")
    //@PreAuthorize("hasAnyRole('DOCTOR')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:update')")
    public ResponseEntity<OfferDTO> changeDate(@NonNull @PathVariable(value = "id") Integer id,
                                               @Valid @NonNull @RequestBody OfferNewDateDTO newDate) {
        return ResponseEntity.ok(offerService.changeDate(id, newDate));
    }

    @PutMapping("/{id}/addOperations")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('admin:update')")
    public ResponseEntity<OfferDTO> addOperations(@NonNull @PathVariable(value = "id") Integer id,
                                                  @NonNull @RequestBody List<OperationDTO> operations) {
        return ResponseEntity.ok(offerService.addOperations(id, operations));
    }

    @PutMapping("/{offerId}/removeOperation/{operationId}")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('admin:update')")
    public ResponseEntity<OfferDTO> removeOperation(@NonNull @PathVariable(value = "offerId") Integer offerId,
                                                    @NonNull @PathVariable(value = "operationId") Integer operationId) {
        return ResponseEntity.ok(offerService.removeOperation(offerId, operationId));
    }

    @PutMapping("/{offerId}/changeCategory/{categoryId}")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('admin:update')")
    public ResponseEntity<OfferDTO> changeCategory(@NonNull @PathVariable(value = "offerId") Integer offerId,
                                                   @NonNull @PathVariable(value = "categoryId") Integer categoryId) {
        return ResponseEntity.ok(offerService.changeCategory(offerId, categoryId));
    }

    @DeleteMapping("/{id}/delete")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:delete')")
    public ResponseEntity<Integer> deleteOffer(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(offerService.deleteOffer(id));
    }

    @PutMapping("/{id}/restore")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:update')")
    public ResponseEntity<OfferDTO> restoreOffer(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(offerService.restoreOffer(id));
    }
}
