package eu.gebes.pushlogBackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {
}
