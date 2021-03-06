package eu.gebes.pushlogBackend.repositories;

import eu.gebes.pushlogBackend.components.TokenGenerator;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Document("users")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class User {


    @Id
    @NonNull
    String userToken;

    @NonNull @Field
    String displayName;

    // tokens of all the different logs
    @DBRef
    List<Log> logs = new LinkedList<>();


}

