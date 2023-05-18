package ta.presentation.dentalt.user.service;

import ta.presentation.dentalt.user.model.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getUser(Integer id);

    List<UserDTO> getPatients();

    List<UserDTO> getDoctors();

    List<UserDTO> getAdmins();

    UserDTO createDoctor(UserDTO userDTO);

    UserDTO createAdmin(UserDTO userDTO);

    Integer deleteUser(Integer id);

    UserDTO restoreUser(Integer id);
}
