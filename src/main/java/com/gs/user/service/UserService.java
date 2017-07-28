package com.gs.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gs.user.domain.User;
import com.gs.user.repository.UserRepository;
import com.gs.user.service.dto.UserDTO;
import com.gs.user.service.mapper.UserMapper;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
	this.userRepository = userRepository;
    }

    public Optional<UserDTO> findById(Long id) {
	return userRepository.findById(id).flatMap(user -> Optional.of(new UserDTO(user)));
    }

    public Optional<UserDTO> findByEmail(String email) {
	return userRepository.findByEmail(email).flatMap(user -> Optional.of(new UserDTO(user)));
    }

    public List<UserDTO> findAll() {
	return UserMapper.usersToUserDTOs(userRepository.findAll());
    }

    public UserDTO save(UserDTO userDTO) {
	User savedUser = userRepository.save(UserMapper.userDTOToUser(userDTO));
	return new UserDTO(savedUser);
    }

    public UserDTO update(UserDTO userDTO) {
	User updatedUser = userRepository.save(UserMapper.userDTOToUser(userDTO));
	return new UserDTO(updatedUser);
    }

    public void delete(Long id) {
	userRepository.delete(id);
    }

    public boolean exists(String email) {
	return userRepository.countByEmail(email) > 0 ? true : false;
    }

}
