package slotegrator.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Jacksonized
public class ClientCredentialsGrantResponse {
    @JsonProperty(value = "token_type")
    String tokenType;
    @JsonProperty(value = "expires_in")
    Integer expiresIn;
    @JsonProperty(value = "access_token")
    String accessToken;
    @JsonProperty(value = "refresh_token")
    String refreshToken;
}
