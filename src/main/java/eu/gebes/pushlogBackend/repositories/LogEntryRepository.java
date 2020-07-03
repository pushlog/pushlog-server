package eu.gebes.pushlogBackend.repositories;

import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LogEntryRepository extends MongoRepository<LogEntry, String> {
    Optional<List<LogEntry>> findLogEntriesByTimestampIsBetweenAndLog_TokenEquals(Long start, Long end, @NonNull String log_token);
}
