package ta.presentation.dentalt.user;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ta.presentation.dentalt.user.model.UserDTO;
import ta.presentation.dentalt.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
//@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:read', 'doctor:read')")
    public ResponseEntity<UserDTO> getUser(@NonNull @PathVariable (value = "id") Integer id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/allPatients")
    //@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:read', 'doctor:read')")
    public ResponseEntity<List<UserDTO>> getPatients() {
        return ResponseEntity.ok(userService.getPatients());
    }

    @GetMapping("/allDoctors")
    //@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:read', 'doctor:read', 'patient:read')")
    public ResponseEntity<List<UserDTO>> getDoctors() {
        return ResponseEntity.ok(userService.getDoctors());
    }

    @GetMapping("/allAdmins")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:read')")
    public ResponseEntity<List<UserDTO>> getAdmins() {
        return ResponseEntity.ok(userService.getAdmins());
    }

    @PostMapping("/createDoctor")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:create')")
    public ResponseEntity<UserDTO> createDoctor(@NonNull @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createDoctor(userDTO));
    }

    @PostMapping("/createAdmin")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:create')")
    public ResponseEntity<UserDTO> createAdmin(@NonNull @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createAdmin(userDTO));
    }

    /*@DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PreAuthorize(value = "hasAnyAuthority('admin:delete')")
    public ResponseEntity<Integer> deleteUser(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PutMapping("/{id}/restore")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PreAuthorize(value = "hasAnyAuthority('admin:update')")
    public ResponseEntity<UserDTO> restoreUser(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(userService.restoreUser(id));
    }*/
}
