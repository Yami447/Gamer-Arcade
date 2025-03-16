package com.arcade.gamerarcade.api;

import com.arcade.dto.UserProfileUpdateDto;
import com.arcade.gamerarcade.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @PutMapping
    public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileUpdateDto userProfileUpdateDto) {
        return ResponseEntity.ok(userService.updateUserProfileData(userProfileUpdateDto));
    }

}
