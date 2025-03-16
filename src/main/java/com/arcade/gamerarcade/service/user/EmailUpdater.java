package com.arcade.gamerarcade.service.user;

import com.arcade.beans.User;
import com.arcade.dto.UserProfileUpdateDto;
import org.springframework.stereotype.Component;

import static io.micrometer.common.util.StringUtils.isNotBlank;
import static java.util.Objects.nonNull;

@Component
public class EmailUpdater implements UserProfileUpdater {
    @Override
    public void update(User user, UserProfileUpdateDto userProfileUpdateDto) {
        user.setEmail(userProfileUpdateDto.getUpdatedEmail());
    }

    @Override
    public boolean validateRequest(UserProfileUpdateDto updateDto) {
        return (nonNull(updateDto) && isNotBlank(updateDto.getUpdatedEmail()));
    }
}
