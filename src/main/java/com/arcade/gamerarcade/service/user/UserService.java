package com.arcade.gamerarcade.service.user;

import com.arcade.beans.User;
import com.arcade.dto.UserCreateDto;
import com.arcade.dto.UserProfileUpdateDto;
import com.arcade.dto.UserResponseDto;
import com.arcade.gamerarcade.repo.UserRepository;
import com.arcade.gamerarcade.transformer.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final List<UserProfileUpdater> userProfileUpdaters;

    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        userCreateDto.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        User user = UserMapper.INSTANCE.fromUserCreateDto(userCreateDto);
        User savedUser = userRepository.save(user);
        return UserMapper.INSTANCE.fromUser(savedUser);
    }

    public User updateUserProfileData(UserProfileUpdateDto userProfileUpdateDto) {
        Optional<User> userOptional = userRepository.findByUsername(userProfileUpdateDto.getUsername());
        if(userOptional.isEmpty()) {
            throw new RuntimeException("USER NOT FOUND!");
        }
        User user = userOptional.get();

        for(UserProfileUpdater userProfileUpdater : userProfileUpdaters) {
            if(userProfileUpdater.validateRequest(userProfileUpdateDto)) {
                userProfileUpdater.update(user, userProfileUpdateDto);
            }
        }

        return userRepository.save(user);
    }
}
