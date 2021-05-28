package io.stattrack.stattrack.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {

    List<UserModel> findByEmail (String email);
    Optional<UserModel> findById(String id);
}
