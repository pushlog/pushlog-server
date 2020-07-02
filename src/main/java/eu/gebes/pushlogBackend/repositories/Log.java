package eu.gebes.pushlogBackend.repositories;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.LinkedList;
import java.util.List;

@Document("logs")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Log {

    @Id @NonNull
    String token;

    @Field @NonNull
    String name;

    @Field
    List<LogEntry> logEntries = new LinkedList<>();

}
