package ta.presentation.dentalt.offer.controller;

import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ta.presentation.dentalt.offer.model.dto.OfferDTO;
import ta.presentation.dentalt.offer.model.dto.OfferNewDateDTO;
import ta.presentation.dentalt.offer.service.services.OfferService;

import java.util.List;

@RestController
@RequestMapping("/offers")
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

    @PutMapping("{id}/changeDate")
    //@PreAuthorize("hasAnyRole('DOCTOR')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:update')")
    public ResponseEntity<OfferDTO> changeDate(@NonNull @PathVariable(value = "id") Integer id,
                                               @Valid @NonNull @RequestBody OfferNewDateDTO newDate) {
        return ResponseEntity.ok(offerService.changeDate(id, newDate));
    }

    @PutMapping("/{id}/changePrice")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:update')")
    public ResponseEntity<OfferDTO> changeOfferPrice(@NonNull @PathVariable(value = "id") Integer id,
                                                     @NonNull @RequestBody Double price) {
        return ResponseEntity.ok(offerService.changeOfferPrice(id, price));
    }

    @PutMapping("/addOperation")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('admin:update')")
    public ResponseEntity<OfferDTO> changeOffer(@NonNull @RequestBody OfferDTO offerDTO) {
        return ResponseEntity.ok(offerService.changeOffer(offerDTO));
    }

    @PutMapping("/removeOperation")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('admin:update')")
    public ResponseEntity<OfferDTO> changeOffer(@NonNull @RequestBody OfferDTO offerDTO) {
        return ResponseEntity.ok(offerService.changeOffer(offerDTO));
    }

    @PutMapping("/changeCategory")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('admin:update')")
    public ResponseEntity<OfferDTO> changeOffer(@NonNull @RequestBody OfferDTO offerDTO) {
        return ResponseEntity.ok(offerService.changeOffer(offerDTO));
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
