package eu.gebes.pushlogBackend.routes;

import eu.gebes.pushlogBackend.components.TokenGenerator;
import eu.gebes.pushlogBackend.repositories.*;
import eu.gebes.pushlogBackend.response.BadRequestException;
import eu.gebes.pushlogBackend.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class LogRoute {

    @Autowired
    LogRepository logRepository;

    @Autowired
    LogEntryRepository logEntryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenGenerator tokenGenerator;

    @GetMapping("/log/{logToken}/from/{start}/until/{end}")
    List<LogEntry> getEntries(@PathVariable String logToken, @PathVariable Long start, @PathVariable Long end){

        Log log = logRepository.findById(logToken).orElse(null);

        if(log == null)
            throw new NotFoundException("Couldn't find the log with that token");

        return logEntryRepository.findLogEntriesByTimestampIsGreaterThanAndTimestampIsLessThanAndLog_TokenOrderByTimestampAsc(start, end, logToken).orElse(new LinkedList<>());
    }

    @PostMapping("/log")
    Log createLog(@RequestBody Map<String, String> body) {

        final String displayname = body.get("displayname");

        if (displayname == null)
            throw new BadRequestException("A displayname parameter in the body is required");

        if (displayname.length() > 32)
            throw new BadRequestException("Displayname can't be greater than 32");

        final String userToken = body.get("userToken");

        if (userToken == null)
            throw new BadRequestException("A userToken parameter in the body is required: the token of the creator user is");

        final User creator = userRepository.findById(userToken).orElse(null);

        if(creator == null)
            throw new NotFoundException("Couldn't find any user with that token");

        Log log = new Log(tokenGenerator.generateNewToken(), creator.getToken(), displayname);

        logRepository.save(log);

        creator.getLogs().add(log);

        userRepository.save(creator);

        return log;
    }

    @PostMapping("/log/{level}")
    LogEntry addLogEntry(@RequestBody Map<String, String> body, @PathVariable String level){
      final String token = body.get("logToken");

        if (token == null)
            throw new BadRequestException("A logToken paramter is required");

        final String value = body.get("value");

        if (value == null)
            throw new BadRequestException("A value paramter is required");

        Log log = logRepository.findById(token).orElse(null);

        if (log == null)
            throw new NotFoundException("Couldn't find a log with the token");

        LogEntry entry = new LogEntry(tokenGenerator.generateNewToken(), log, System.currentTimeMillis(), value, level);

        logEntryRepository.save(entry);
        log.getLogEntries().add(entry);
        logRepository.save(log);
        return entry;
    }

}
