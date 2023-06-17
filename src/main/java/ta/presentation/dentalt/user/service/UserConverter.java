package ta.presentation.dentalt.user.service;

import ta.presentation.dentalt.user.model.UserDTO;
import ta.presentation.dentalt.user.model.UserEntity;

public class UserConverter {

    public static UserDTO convertUserEntityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(userEntity.getId());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setRole(userEntity.getRole());
        userDTO.setJoinedOn(userEntity.getJoinedOn());

        return userDTO;
    }

    public static UserEntity createUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(userDTO.getEmail());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setRole(userDTO.getRole());
        userEntity.setJoinedOn(userDTO.getJoinedOn());
        userEntity.setValidity(Boolean.TRUE);

        return userEntity;
    }
}
