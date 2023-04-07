package ta.presentation.dentalt.user.service;

import ta.presentation.dentalt.user.model.Roles;
import ta.presentation.dentalt.user.model.UserDTO;
import ta.presentation.dentalt.user.model.UserEntity;

import java.time.LocalDateTime;

public class UserConverter {

    public static UserDTO convertUserEntityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(userEntity.getId());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setRoles(userEntity.getRoles());
        userDTO.setJoinedOn(userEntity.getJoinedOn());
        userDTO.setValidity(userEntity.getValidity());

        return userDTO;
    }

    protected static UserEntity createUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setRoles(userDTO.getRoles());
        userEntity.setJoinedOn(LocalDateTime.now());
        userEntity.setRoles(String.valueOf(Roles.PATIENT));
        userEntity.setValidity(Boolean.TRUE);

        return userEntity;
    }

    protected static UserEntity createDoctorEntity(UserDTO userDTO) {
        UserEntity doctorEntity = UserConverter.createUserEntity(userDTO);
        doctorEntity.setRoles(String.valueOf(Roles.DOCTOR));
        return doctorEntity;
    }

    protected static UserEntity createAdminEntity(UserDTO userDTO) {
        UserEntity adminEntity = UserConverter.createUserEntity(userDTO);
        adminEntity.setRoles(String.valueOf(Roles.ADMIN));
        return adminEntity;
    }
}
