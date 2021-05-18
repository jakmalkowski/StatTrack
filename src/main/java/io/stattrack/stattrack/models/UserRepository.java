package io.stattrack.stattrack.models;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserRepository extends MongoRepository<UserModel, String> {

}
