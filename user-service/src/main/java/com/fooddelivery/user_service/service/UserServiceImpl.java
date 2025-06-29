package com.fooddelivery.user_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fooddelivery.user_service.dto.UserRequestDTO;
import com.fooddelivery.user_service.dto.UserResponseDTO;
import com.fooddelivery.user_service.model.User;
import com.fooddelivery.user_service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public List<UserResponseDTO> getAllUsers() {
		return userRepository.findAll().stream()
				.map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole()))
				.toList();
	}

	@Override
	public UserResponseDTO createUser(UserRequestDTO request) {
		User user = User.builder().name(request.getName()).email(request.getEmail()).password(request.getPassword()) // To
																														// be
																														// encrypted
																														// later
				.role(request.getRole()).build();

		User saved = userRepository.save(user);

		return new UserResponseDTO(saved.getId(), saved.getName(), saved.getEmail(), saved.getRole());
	}

	@Override
	public UserResponseDTO getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
	}

}
