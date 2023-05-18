package ta.presentation.dentalt.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.user.UserRepository;
import ta.presentation.dentalt.user.model.Roles;
import ta.presentation.dentalt.user.model.UserDTO;
import ta.presentation.dentalt.user.model.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO getUser(Integer id) {
        if (userRepository.findById(id).isPresent()  && userRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            return UserConverter.convertUserEntityToDTO(userRepository.findById(id).get());
        }
        return new UserDTO();
    }

    @Override
    public List<UserDTO> getPatients() {
        return userRepository.findAll()
                .stream()
                .filter(userEntity -> userEntity.getValidity().equals(Boolean.TRUE) && userEntity.getRoles().equalsIgnoreCase(String.valueOf(Roles.PATIENT)))
                .map(UserConverter::convertUserEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getDoctors() {
        return userRepository.findAll()
                .stream()
                .filter(userEntity -> userEntity.getValidity().equals(Boolean.TRUE) && userEntity.getRoles().equalsIgnoreCase(String.valueOf(Roles.DOCTOR)))
                .map(UserConverter::convertUserEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAdmins() {
        return userRepository.findAll()
                .stream()
                .filter(userEntity -> userEntity.getValidity().equals(Boolean.TRUE) && userEntity.getRoles().equalsIgnoreCase(String.valueOf(Roles.ADMIN)))
                .map(UserConverter::convertUserEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createDoctor(UserDTO userDTO) {
        if(userRepository.findById(userDTO.getId()).isPresent() && userRepository.findById(userDTO.getId()).get().getValidity().equals(Boolean.TRUE)) {
            UserEntity userToDoctor = userRepository.findById(userDTO.getId()).get();
            userToDoctor.setRoles(String.valueOf(Roles.DOCTOR));
            userRepository.save(userToDoctor);
            return UserConverter.convertUserEntityToDTO(userToDoctor);
        }
        return new UserDTO();
    }

    @Override
    public UserDTO createAdmin(UserDTO userDTO) {
        if(userRepository.findById(userDTO.getId()).isPresent() && userRepository.findById(userDTO.getId()).get().getValidity().equals(Boolean.TRUE)) {
            UserEntity userToAdmin = userRepository.findById(userDTO.getId()).get();
            userToAdmin.setRoles(String.valueOf(Roles.ADMIN));
            userRepository.save(userToAdmin);
            return UserConverter.convertUserEntityToDTO(userToAdmin);
        }
        return new UserDTO();
    }

    @Override
    public Integer deleteUser(Integer id) {
        if(userRepository.findById(id).isPresent() && userRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            UserEntity userEntityToDelete = userRepository.findById(id).get();
            userEntityToDelete.setValidity(Boolean.FALSE);
            userRepository.save(userEntityToDelete);
            return id;
        }
        return null;
    }

    @Override
    public UserDTO restoreUser(Integer id) {
        if(userRepository.findById(id).isPresent()) {
            UserEntity userEntityToRestore = userRepository.findById(id).get();
            userEntityToRestore.setValidity(Boolean.TRUE);
            userRepository.save(userEntityToRestore);
            return UserConverter.convertUserEntityToDTO(userEntityToRestore);
        }
        return new UserDTO();
    }
}
