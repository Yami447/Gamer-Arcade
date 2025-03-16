package com.arcade.gamerarcade.repo;

import com.arcade.beans.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    @Query("{ 'username': { '$in': ?0 } }")
    List<User> findByUsernames(List<String> usernames);
}
