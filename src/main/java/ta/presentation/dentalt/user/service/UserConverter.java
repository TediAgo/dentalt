package ta.presentation.dentalt.user.service;

import ta.presentation.dentalt.user.model.UserAppointmentDTO;
import ta.presentation.dentalt.user.model.UserDTO;
import ta.presentation.dentalt.user.model.UserEntity;

public class UserConverter {

    public static UserDTO convertUserEntityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(userEntity.getId());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setRoles(userEntity.getRoles());
        userDTO.setJoinedOn(userEntity.getJoinedOn());

        return userDTO;
    }

    public static UserAppointmentDTO populateUserAppointmentDTO(UserEntity userEntity) {
        UserAppointmentDTO userAppointmentDTO = new UserAppointmentDTO();
        userAppointmentDTO.setId(userEntity.getId());
        userAppointmentDTO.setEmail(userEntity.getEmail());
        userAppointmentDTO.setFirstName(userEntity.getFirstName());
        userAppointmentDTO.setLastName(userEntity.getLastName());
        return userAppointmentDTO;
    }
}
