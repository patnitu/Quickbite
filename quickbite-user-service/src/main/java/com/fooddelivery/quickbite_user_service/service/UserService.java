package com.fooddelivery.quickbite_user_service.service;

import java.util.List;
import java.util.Optional;

import com.fooddelivery.quickbite_user_service.dto.UserResponseDTO;
import com.fooddelivery.quickbite_user_service.model.User;

public interface UserService {
	User saveUser(User user);
	UserResponseDTO getUserById(Long id);
	UserResponseDTO getUserByEmail(String email);
    List<User> getAllUsers();
    boolean userExistsByEmail(String email);
}
