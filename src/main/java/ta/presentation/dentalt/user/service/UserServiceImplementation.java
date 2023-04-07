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
        if (userRepository.findById(id).isPresent()) {
            return UserConverter.convertUserEntityToDTO(userRepository.findById(id).get());
        }
        return new UserDTO();
    }

    @Override
    public List<UserDTO> getPatients() {
        return userRepository.findAll()
                .stream()
                .filter(userEntity -> userEntity.getRoles().equals(Roles.PATIENT))
                .map(UserConverter::convertUserEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getDoctors() {
        return userRepository.findAll()
                .stream()
                .filter(userEntity -> userEntity.getRoles().equals(Roles.DOCTOR))
                .map(UserConverter::convertUserEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAdmins() {
        return userRepository.findAll()
                .stream()
                .filter(userEntity -> userEntity.getRoles().equals(Roles.ADMIN))
                .map(UserConverter::convertUserEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createPatient(UserDTO userDTO) {
        UserEntity userPatientEntityToPersist = UserConverter.createUserEntity(userDTO);
        userRepository.save(userPatientEntityToPersist);
        return UserConverter.convertUserEntityToDTO(userPatientEntityToPersist);
    }

    @Override
    public UserDTO createDoctor(UserDTO userDTO) {
        UserEntity userDoctorEntityToPersist = UserConverter.createDoctorEntity(userDTO);
        userRepository.save(userDoctorEntityToPersist);
        return UserConverter.convertUserEntityToDTO(userDoctorEntityToPersist);
    }

    @Override
    public UserDTO createAdmin(UserDTO userDTO) {
       UserEntity userAdminToPersist = UserConverter.createAdminEntity(userDTO);
       userRepository.save(userAdminToPersist);
       return UserConverter.convertUserEntityToDTO(userAdminToPersist);
    }

    @Override
    public Integer deleteUser(Integer id) {
        if(userRepository.findById(id).isPresent()) {
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
