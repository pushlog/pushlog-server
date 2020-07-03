package eu.gebes.pushlogBackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LogEntryRepository extends MongoRepository<LogEntry, String> {
    Optional<List<LogEntry>> findLogEntriesByTimestampIsGreaterThanAndTimestampIsLessThan(Long start, Long end);
}
