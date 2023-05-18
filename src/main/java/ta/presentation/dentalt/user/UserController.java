package ta.presentation.dentalt.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ta.presentation.dentalt.user.model.UserDTO;
import ta.presentation.dentalt.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'DOCTOR')")
    public ResponseEntity<UserDTO> getUser(@PathVariable (value = "id") Integer id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/allPatients")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<UserDTO>> getPatients() {
        return ResponseEntity.ok(userService.getPatients());
    }

    @GetMapping("/allDoctors")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'DOCTOR', 'PATIENT')")
    public ResponseEntity<List<UserDTO>> getDoctors() {
        return ResponseEntity.ok(userService.getDoctors());
    }

    @GetMapping("/allAdmins")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAdmins() {
        return ResponseEntity.ok(userService.getAdmins());
    }

    @PostMapping("/createDoctor")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    public ResponseEntity<UserDTO> createDoctor(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createDoctor(userDTO));
    }

    @PostMapping("/createAdmin")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    public ResponseEntity<UserDTO> createAdmin(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createAdmin(userDTO));
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    public ResponseEntity<Integer> deleteUser(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PutMapping("/{id}/restore")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    public ResponseEntity<UserDTO> restoreUser(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(userService.restoreUser(id));
    }
}
