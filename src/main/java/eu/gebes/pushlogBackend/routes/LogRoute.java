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
    TokenGenerator tokenGenerator;

    @PostMapping("/log/")
    Log createLog(@RequestBody Map<String, String> body) {

        final String displayname = body.get("displayname");

        if (displayname == null)
            throw new BadRequestException("A displayname parameter in the body is required");

        if (displayname.length() > 32)
            throw new BadRequestException("Displayname can't be greater than 32");

        Log log = new Log(tokenGenerator.generateNewToken(), displayname);

        logRepository.save(log);

        return log;
    }

    @PostMapping("/log/info")
    LogEntry addLogEntry(@RequestBody Map<String, String> body) {

        final String token = body.get("token");

        if(token == null)
            throw new BadRequestException("A token paramter is required");

        final String value = body.get("value");

        if(value == null)
            throw new BadRequestException("A value paramter is required");

        Log log = logRepository.findById(token).orElse(null);

        if(log == null)
            throw new NotFoundException("Couldn't find a log with the token");

        LogEntry entry = new LogEntry(System.nanoTime(), value, false);
        
        return entry;
    }


}
