package com.fooddelivery.user_service.service;

import com.fooddelivery.user_service.dto.UserRequestDTO;
import com.fooddelivery.user_service.dto.UserResponseDTO;

public interface UserService {
	UserResponseDTO createUser(UserRequestDTO userRequest);

	UserResponseDTO getUserById(Long id);
}
