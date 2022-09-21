package slotegrator.project.model.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Jacksonized
public class PlayerResponse extends AbstractPlayer {
    Long id;
    @JsonProperty(value = "country_id")
    Long countryId;
    @JsonProperty(value = "timezone_id")
    Long timezoneId;
    String gender;
    @JsonProperty(value = "phone_number")
    Long phoneNumber;
    LocalDate birthdate;
    @JsonProperty(value = "bonuses_allowed")
    Boolean bonusesAllowed;
    @JsonProperty(value = "is_verified")
    Boolean isVerified;
}
