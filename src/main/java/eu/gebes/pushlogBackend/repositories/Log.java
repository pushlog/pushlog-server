package eu.gebes.pushlogBackend.repositories;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("logs")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Log {

    @Id
    String token;

    @Field
    LogEntry logEntry;

}
