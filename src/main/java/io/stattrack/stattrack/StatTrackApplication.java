package io.stattrack.stattrack;

import io.stattrack.stattrack.models.UserModel;
import io.stattrack.stattrack.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootApplication
public class StatTrackApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public static void main(String[] args) {
        SpringApplication.run(StatTrackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserModel u1 = new UserModel("user1", "fsda1", "fsdfas1@fsda.pl");
        UserModel u2 = new UserModel("user2", "fsda2", "fsdfas2@fsda.pl");
        UserModel u3 = new UserModel("user3", "fsda3", "fsdfas3@fsda.pl");

//        System.out.println("collection names: " + mongoTemplate.getCollectionNames());

//        mongoTemplate.insert(u1);

        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
//
        List<UserModel> users = userRepository.findAll();

        for (UserModel user : users) {
            System.out.println(user.getUname());
        }



    }
}
