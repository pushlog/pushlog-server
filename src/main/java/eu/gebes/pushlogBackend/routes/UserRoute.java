package eu.gebes.pushlogBackend.routes;

import eu.gebes.pushlogBackend.components.TokenGenerator;
import eu.gebes.pushlogBackend.repositories.UserRepository;
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




}
