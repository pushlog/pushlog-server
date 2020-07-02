package eu.gebes.pushlogBackend.repositories;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("votes")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Log {

    @Field
    long logTime;

    @Field
    String value;

    @Field
    Type type;

}

enum Type {
    INFO, ERROR
}