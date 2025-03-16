package com.arcade.gamerarcade.api;

import com.arcade.dto.AddFriendDto;
import com.arcade.gamerarcade.service.FriendshipManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/friend")
@RestController
public class FriendRequestApi {
    private final FriendshipManagerService friendshipManagerService;

    @PostMapping("/add")
    public ResponseEntity<?> sendFriendRequest(@RequestBody @Validated AddFriendDto addFriendDto) {
        return ResponseEntity.ok(friendshipManagerService.sendFriendRequest(addFriendDto));
    }

    @PostMapping("/accept")
    public ResponseEntity<?> acceptFriendRequest(@RequestBody @Validated AddFriendDto addFriendDto) {
        return ResponseEntity.ok(friendshipManagerService.acceptFriendRequest(addFriendDto));
    }
}
