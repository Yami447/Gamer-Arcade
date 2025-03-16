package com.arcade.gamerarcade.service;

import com.arcade.beans.User;
import com.arcade.dto.AddFriendDto;
import com.arcade.gamerarcade.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.arcade.gamerarcade.util.CollectionUtils.*;
import static io.micrometer.common.util.StringUtils.isBlank;
import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class FriendshipManagerService {
    private final UserRepository userRepository;
    private final TaskExecutor taskExecutor;
    public List<User> sendFriendRequest(AddFriendDto addFriendDto) {
        List<User> users = fetchAndValidateUsers(List.of(addFriendDto.getSourceUsername(), addFriendDto.getTargetUsername()));

        User sourceUser = users.stream().filter(user -> user.getUsername().equals(addFriendDto.getSourceUsername())).findFirst().get();
        User targetUser = users.stream().filter(user -> user.getUsername().equals(addFriendDto.getTargetUsername())).findFirst().get();

        updateSourceUser(sourceUser, addFriendDto.getTargetUsername());
        updateTargetUser(targetUser, addFriendDto.getSourceUsername());
        //TODO remove this later
        return userRepository.saveAll(users);
    }

    public List<User> acceptFriendRequest(AddFriendDto addFriendDto) {
        List<User> users = fetchAndValidateUsers(List.of(addFriendDto.getSourceUsername(), addFriendDto.getTargetUsername()));

        User sourceUser = users.stream().filter(user -> user.getUsername().equals(addFriendDto.getSourceUsername())).findFirst().get();
        User targetUser = users.stream().filter(user -> user.getUsername().equals(addFriendDto.getTargetUsername())).findFirst().get();
        validateAcceptFriendRequest(sourceUser, targetUser, addFriendDto.getTargetUsername(), addFriendDto.getSourceUsername());
        addUserToFriend(sourceUser, addFriendDto.getTargetUsername());
        addUserToFriend(targetUser, addFriendDto.getSourceUsername());
        return userRepository.saveAll(users);
    }

    private void validateAcceptFriendRequest(User sourceUser, User targetUser, String targetUsername, String sourceUsername) {
        if(!(checkIfPresent(sourceUser.getPendingFriendRequests(), targetUsername) && checkIfPresent(targetUser.getSentFriendRequests(), sourceUsername))) {
            throw new RuntimeException("Friend request accept is invalid!");
        }
    }

    private void addUserToFriend(User user, String usernameToAdd) {
        if(isNull(user) || isBlank(usernameToAdd)) return;

        nullSafeEmptyList(user.getSentFriendRequests()).remove(usernameToAdd);
        nullSafeEmptyList(user.getPendingFriendRequests()).remove(usernameToAdd);
        if(checkIfPresent(user.getFriends(), usernameToAdd)) {
            throw new RuntimeException("User is already added as a friend!");
        }
        user.getFriends().add(usernameToAdd);
    }

    private List<User> fetchAndValidateUsers(List<String> usernames) {
        if(isEmpty(usernames)) {
            throw new RuntimeException("usernames is empty!");
        }
        List<User> users = userRepository.findByUsernames(usernames);

        if(users.size() != usernames.size()) {
            throw new RuntimeException("At least one user doesn't exist!");
        }
        return users;
    }

    private void updateTargetUser(User targetUser, String sourceUsername) {
        List<String> pendingFriendRequests = isNull(targetUser.getPendingFriendRequests()) ? new ArrayList<>() : targetUser.getPendingFriendRequests();

        if(checkIfPresent(pendingFriendRequests, sourceUsername)) {
            throw new RuntimeException("Friend Request already sent!");
        }
        pendingFriendRequests.add(sourceUsername);
        targetUser.setPendingFriendRequests(pendingFriendRequests);
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                notifyTargetUser(targetUser, sourceUsername);
            }
        });
    }

    //TODO write the notification logic
    private void notifyTargetUser(User targetUser, String sourceUsername) {
    }

    private void updateSourceUser(User sourceUser, String targetUsername) {
        List<String> sentFriendRequests =  isNull(sourceUser.getSentFriendRequests()) ? new ArrayList<>() : sourceUser.getSentFriendRequests();
        if(checkIfPresent(sentFriendRequests, targetUsername)) {
            throw new RuntimeException("Friend Request already sent!");
        }
        sentFriendRequests.add(targetUsername);
        sourceUser.setSentFriendRequests(sentFriendRequests);
    }

}
