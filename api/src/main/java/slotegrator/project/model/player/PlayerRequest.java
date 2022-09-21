package slotegrator.project.model.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Jacksonized
public class PlayerRequest extends AbstractPlayer {
    @JsonProperty(value = "password_change")
    String passwordChange;
    @JsonProperty(value = "password_repeat")
    String passwordRepeat;
    @JsonProperty(value = "currency_code")
    String currencyCode;
}
