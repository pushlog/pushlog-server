package eu.gebes.pushlogBackend.routes;

import eu.gebes.pushlogBackend.components.TokenGenerator;
import eu.gebes.pushlogBackend.repositories.*;
import eu.gebes.pushlogBackend.response.BadRequestException;
import eu.gebes.pushlogBackend.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class LogRoute {

    @Autowired
    LogRepository logRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenGenerator tokenGenerator;

    @PostMapping("/log/")
    Log createLog(@RequestBody Map<String, String> body) {

        final String displayname = body.get("displayname");

        if (displayname == null)
            throw new BadRequestException("A displayname parameter in the body is required");

        if (displayname.length() > 32)
            throw new BadRequestException("Displayname can't be greater than 32");

        final String token = body.get("token");

        if (token == null)
            throw new BadRequestException("A token parameter in the body is required: the token of the creator user is");

        final User creator = userRepository.findById(token).orElse(null);

        if(creator == null)
            throw new NotFoundException("Couldn't find any user with that token");

        Log log = new Log(tokenGenerator.generateNewToken(), creator.getToken(), displayname);

        logRepository.save(log);

        creator.getLogs().add(log.getToken());

        userRepository.save(creator);

        return log;
    }

    @PostMapping("/log/info")
    LogEntry addLogEntry(@RequestBody Map<String, String> body) {

        final String token = body.get("token");

        if (token == null)
            throw new BadRequestException("A token paramter is required");

        final String value = body.get("value");

        if (value == null)
            throw new BadRequestException("A value paramter is required");

        Log log = logRepository.findById(token).orElse(null);

        if (log == null)
            throw new NotFoundException("Couldn't find a log with the token");

        LogEntry entry = new LogEntry(System.nanoTime(), value, false);

        log.getLogEntries().add(entry);

        logRepository.save(log);

        return entry;
    }


}
