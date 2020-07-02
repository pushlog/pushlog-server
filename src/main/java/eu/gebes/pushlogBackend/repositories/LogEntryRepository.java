package eu.gebes.pushlogBackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogEntryRepository extends MongoRepository<LogEntry, String> {
}
