package ta.presentation.dentalt.user.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.user.repository.UserRepository;
import ta.presentation.dentalt.user.model.enums.Role;
import ta.presentation.dentalt.user.model.dto.UserDTO;
import ta.presentation.dentalt.user.model.entity.UserEntity;
import ta.presentation.dentalt.user.service.mapper.UserConverter;
import ta.presentation.dentalt.user.service.services.UserService;

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
                .filter(userEntity -> userEntity.getValidity().equals(Boolean.TRUE) && userEntity.getRole().equals(Role.PATIENT))
                .map(UserConverter::convertUserEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getDoctors() {
        return userRepository.findAll()
                .stream()
                .filter(userEntity -> userEntity.getValidity().equals(Boolean.TRUE) && userEntity.getRole().equals(Role.DOCTOR))
                .map(UserConverter::convertUserEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAdmins() {
        return userRepository.findAll()
                .stream()
                .filter(userEntity -> userEntity.getValidity().equals(Boolean.TRUE) && userEntity.getRole().equals(Role.ADMIN))
                .map(UserConverter::convertUserEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createDoctor(Integer id) {
        if(userRepository.findById(id).isPresent() && userRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            UserEntity userToDoctor = userRepository.findById(id).get();
            userToDoctor.setRole(Role.DOCTOR);
            userRepository.save(userToDoctor);
            return UserConverter.convertUserEntityToDTO(userToDoctor);
        }
        return new UserDTO();
    }

    @Override
    public UserDTO createAdmin(Integer id) {
        if(userRepository.findById(id).isPresent() && userRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            UserEntity userToAdmin = userRepository.findById(id).get();
            userToAdmin.setRole(Role.ADMIN);
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
