package eu.gebes.pushlogBackend.repositories;

import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LogEntryRepository extends MongoRepository<LogEntry, String> {
    Optional<List<LogEntry>> findLogEntriesByTimestampIsGreaterThanAndTimestampIsLessThanAndLog_TokenOrderByTimestampAsc(Long timestamp, Long timestamp2, @NonNull String log_token);
}
