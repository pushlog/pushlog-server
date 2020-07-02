package eu.gebes.pushlogBackend.repositories;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("logentries")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class LogEntry {

    @Id
    long timestamp;

    @Field
    String value;

    @Field
    boolean error;
}
