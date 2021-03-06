package eu.gebes.pushlogBackend.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;


@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthException extends BaseException{

    public AuthException(String error) {
        super(error);
    }
}