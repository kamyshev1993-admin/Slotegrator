package slotegrator.project.model.player;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Jacksonized
public abstract class AbstractPlayer {
    String username;
    String email;
    String name;
    String surname;
}
