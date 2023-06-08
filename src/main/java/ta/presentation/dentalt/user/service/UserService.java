package ta.presentation.dentalt.user.service;

import ta.presentation.dentalt.user.model.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getUser(Integer id);

    List<UserDTO> getPatients();

    List<UserDTO> getDoctors();

    List<UserDTO> getAdmins();

    UserDTO createDoctor(Integer id);

    UserDTO createAdmin(Integer id);

    Integer deleteUser(Integer id);

    UserDTO restoreUser(Integer id);
}
