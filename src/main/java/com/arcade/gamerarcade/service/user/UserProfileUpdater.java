package com.arcade.gamerarcade.service.user;


import com.arcade.beans.User;
import com.arcade.dto.UserProfileUpdateDto;

public interface UserProfileUpdater {

    void update(User user, UserProfileUpdateDto userProfileUpdateDto);

    boolean validateRequest(UserProfileUpdateDto updateDto);
}
