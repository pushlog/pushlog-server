package eu.gebes.pushlogBackend.response;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorResponse {

    @NonNull String error;


}