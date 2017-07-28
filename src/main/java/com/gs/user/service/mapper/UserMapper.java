package com.gs.user.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.gs.user.domain.User;
import com.gs.user.service.dto.UserDTO;

public class UserMapper {

    public static UserDTO userToUserDTO(User user) {
        return new UserDTO(user);
    }
    
    public static User userDTOToUser(UserDTO userDTO) {
	if (userDTO == null) {
	    return null;
	}
	
	return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), 
		userDTO.getBirthDate(), userDTO.getFavouriteTeam());
    }
    
    public static List<UserDTO> usersToUserDTOs(List<User> users) {
        return users.stream()
            .filter(Objects::nonNull)
            .map(user -> UserMapper.userToUserDTO(user))
            .collect(Collectors.toList());
    }

}
