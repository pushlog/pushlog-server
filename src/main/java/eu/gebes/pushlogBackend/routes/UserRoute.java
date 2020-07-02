package eu.gebes.pushlogBackend.routes;

import eu.gebes.pushlogBackend.components.TokenGenerator;
import eu.gebes.pushlogBackend.repositories.User;
import eu.gebes.pushlogBackend.repositories.UserRepository;
import eu.gebes.pushlogBackend.response.BadRequestException;
import eu.gebes.pushlogBackend.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserRoute {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenGenerator tokenGenerator;

    @GetMapping("/user/{token}")
    User getUserData(@PathVariable String token) {

        User user = userRepository.findById(token).orElse(null);

        if (user == null)
            throw new NotFoundException("Couldn't find user with the requested token");

        return user;
    }

    @PostMapping("/user")
    User createUser(@RequestBody Map<String, String> body) {

        final String displayname = body.get("displayname");

        if (displayname == null)
            throw new BadRequestException("A displayname parameter in the body is required");


        if (displayname.length() > 32)
            throw new BadRequestException("Displayname can't be greater than 32");


        User user = new User(tokenGenerator.generateNewToken(), displayname);

        userRepository.save(user);

        return user;
    }


}
