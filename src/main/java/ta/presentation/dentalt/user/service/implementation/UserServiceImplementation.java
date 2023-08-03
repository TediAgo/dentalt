package ta.presentation.dentalt.user.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.appointment.service.implementation.AppointmentServiceImplementation;
import ta.presentation.dentalt.user.repository.UserRepository;
import ta.presentation.dentalt.user.model.enums.Role;
import ta.presentation.dentalt.user.model.dto.UserDTO;
import ta.presentation.dentalt.user.model.entity.UserEntity;
import ta.presentation.dentalt.user.model.mapper.UserConverter;
import ta.presentation.dentalt.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceImplementation.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO getUser(Integer id) {
        if (userRepository.findById(id).isPresent()  && userRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            return UserConverter.convertUserEntityToDTO(userRepository.findById(id).get());
        }
        LOGGER.info("User not found.");
        return new UserDTO();
    }

    @Override
    public List<UserDTO> getPatients() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole().equals(Role.PATIENT) && user.getValidity().equals(Boolean.TRUE))
                .map(UserConverter::convertUserEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getDoctors() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole().equals(Role.DOCTOR) && user.getValidity().equals(Boolean.TRUE))
                .map(UserConverter::convertUserEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAdmins() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole().equals(Role.ADMIN) && user.getValidity().equals(Boolean.TRUE))
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
        LOGGER.info("User not found.");
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
        LOGGER.info("User not found.");
        return new UserDTO();
    }
}
