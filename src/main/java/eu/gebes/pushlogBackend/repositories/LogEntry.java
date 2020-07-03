package eu.gebes.pushlogBackend.repositories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("entries")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class LogEntry {

    @JsonIgnore
    @Id
    String token;

    // in milli seconds
    @Field
    Long timestamp;
    @Field()
    String value, level;
}
