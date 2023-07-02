package ta.presentation.dentalt.user.service.mapper;

import ta.presentation.dentalt.user.model.dto.UserDTO;
import ta.presentation.dentalt.user.model.entity.UserEntity;

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
}
