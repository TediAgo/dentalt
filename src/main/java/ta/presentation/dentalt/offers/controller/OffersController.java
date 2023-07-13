package ta.presentation.dentalt.offers.controller;

import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ta.presentation.dentalt.offers.model.dto.OffersDTO;
import ta.presentation.dentalt.offers.model.dto.OffersNewDateDTO;
import ta.presentation.dentalt.offers.service.services.OffersService;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OffersController {

    @Autowired
    private OffersService offersService;

    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:read', 'doctor:read', 'patient:read')")
    public ResponseEntity<OffersDTO> getOffer(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(offersService.getOffer(id));
    }

    @GetMapping("/all")
    //@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:read', 'doctor:read', 'patient:read')")
    public ResponseEntity<List<OffersDTO>> getAllOffers() {
        return ResponseEntity.ok(offersService.getAllOffers());
    }

    @PostMapping("/create")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:create')")
    public ResponseEntity<OffersDTO> createOffer(@NonNull @RequestBody OffersDTO offerDTO) {
        return ResponseEntity.ok(offersService.createOffer(offerDTO));
    }

    @PutMapping("/change")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('admin:update')")
    public ResponseEntity<OffersDTO> changeOffer(@NonNull @RequestBody OffersDTO offerDTO) {
        return ResponseEntity.ok(offersService.changeOffer(offerDTO));
    }

    @PutMapping("{id}/changeDate")
    //@PreAuthorize("hasAnyRole('DOCTOR')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:update')")
    public ResponseEntity<OffersDTO> changeDate(@NonNull @PathVariable(value = "id") Integer id,
                                                     @Valid @NonNull @RequestBody OffersNewDateDTO newDate) {
        return ResponseEntity.ok(offersService.changeDate(id, newDate));
    }

    @PutMapping("/{id}/changePrice")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:update')")
    public ResponseEntity<OffersDTO> changeOfferPrice(@NonNull @PathVariable(value = "id") Integer id,
                                                             @NonNull @RequestBody Double price) {
        return ResponseEntity.ok(offersService.changeOfferPrice(id, price));
    }

    @DeleteMapping("/{id}/delete")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:delete')")
    public ResponseEntity<Integer> deleteOffer(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(offersService.deleteOffer(id));
    }

    @PutMapping("/{id}/restore")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:update')")
    public ResponseEntity<OffersDTO> restoreOffer(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(offersService.restoreOffer(id));
    }
}
