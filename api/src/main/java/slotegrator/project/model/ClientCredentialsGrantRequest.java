package slotegrator.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Jacksonized
@Builder
public class ClientCredentialsGrantRequest {
    @JsonProperty(value = "grant_type")
    String grantType;
    String scope;
    String username;
    String password;

    public static class ClientCredentialsGrantRequestBuilder {
        public ClientCredentialsGrantRequestBuilder clientCredentialsType() {
            return grantType("client_credentials");
        }

        public ClientCredentialsGrantRequestBuilder defaultScore() {
            return scope("guest:default");
        }

        public ClientCredentialsGrantRequestBuilder passwordGrandType() {
            return grantType("password");
        }
    }
}
