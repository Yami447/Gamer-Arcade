package com.arcade.gamerarcade.service.user;

import com.arcade.beans.User;
import com.arcade.dto.UserProfileUpdateDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

import static com.arcade.gamerarcade.util.CollectionUtils.isNotEmpty;
import static io.micrometer.common.util.StringUtils.isNotBlank;
import static java.util.Objects.nonNull;

@Component
public class UserGamesUpdater implements UserProfileUpdater {

    //TODO add validation on games (to check if they are valid or not)
    @Override
    public void update(User user, UserProfileUpdateDto userProfileUpdateDto) {
        Set<String> gamesToAdd = userProfileUpdateDto.getAddGames().stream().filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        Set<String> gamesToDelete = userProfileUpdateDto.getDeleteGames().stream().filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        if(isNotEmpty(gamesToAdd)) {
            user.getGames().addAll(gamesToAdd);
        }

        if(isNotEmpty(gamesToDelete)) {
            user.getGames().removeAll(gamesToDelete);
        }
    }

    @Override
    public boolean validateRequest(UserProfileUpdateDto updateDto) {
        return nonNull(updateDto) && (isNotEmpty(updateDto.getAddGames()) || isNotEmpty(updateDto.getDeleteGames()));
    }
}
