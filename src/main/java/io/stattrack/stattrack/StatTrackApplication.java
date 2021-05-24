package io.stattrack.stattrack;

import io.stattrack.stattrack.models.UserModel;
import io.stattrack.stattrack.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class StatTrackApplication extends SpringBootServletInitializer {

//    @Autowired
//    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(StatTrackApplication.class, args);
    }
}
