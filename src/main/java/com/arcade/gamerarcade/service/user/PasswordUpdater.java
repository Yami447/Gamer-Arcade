package com.arcade.gamerarcade.service.user;

import com.arcade.beans.User;
import com.arcade.dto.UserProfileUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.arcade.gamerarcade.util.PasswordUtil.isValidPassword;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class PasswordUpdater implements UserProfileUpdater {
    private final PasswordEncoder passwordEncoder;
    @Override
    public void update(User user, UserProfileUpdateDto userProfileUpdateDto) {
        user.setPassword(passwordEncoder.encode(userProfileUpdateDto.getUpdatedPassword()));
    }

    @Override
    public boolean validateRequest(UserProfileUpdateDto updateDto) {
        return nonNull(updateDto) && isValidPassword(updateDto.getUpdatedPassword());
    }

}
