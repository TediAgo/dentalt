package ta.presentation.dentalt.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDTO> getUser(@PathVariable (value = "id") Integer id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/allPatients")
    public ResponseEntity<List<UserDTO>> getPatients() {
        return ResponseEntity.ok(userService.getPatients());
    }

    @GetMapping("/allDoctors")
    public ResponseEntity<List<UserDTO>> getDoctors() {
        return ResponseEntity.ok(userService.getDoctors());
    }

    @GetMapping("/allAdmins")
    public ResponseEntity<List<UserDTO>> getAdmins() {
        return ResponseEntity.ok(userService.getAdmins());
    }

    @PostMapping("/createClient")
    public ResponseEntity<UserDTO> createPatient(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createPatient(userDTO));
    }

    @PostMapping("/createDoctor")
    public ResponseEntity<UserDTO> createDoctor(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createDoctor(userDTO));
    }

    @PostMapping("/createAdmin")
    public ResponseEntity<UserDTO> createAdmin(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createAdmin(userDTO));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Integer> deleteUser(@PathVariable(value = "id") Integer id) {
        //userService.deleteUser(id);
        //return ResponseEntity.noContent().build();
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<UserDTO> restoreUser(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(userService.restoreUser(id));
    }
}
