package eu.gebes.pushlogBackend.repositories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
    String logToken;


    /**
     * token of the creator
     */
    @JsonIgnore
    @Field @NonNull
    String creatorUserToken;

    @Field @NonNull
    String displayName;

    @JsonIgnore
    @DBRef
    List<LogEntry> logEntries = new LinkedList<>();

}
