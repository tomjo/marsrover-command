package be.cegeka.marsrover;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MarsRover {

    @EqualsAndHashCode.Include
    private final UUID id;

    private Location location;
    private Orientation orientation;

    public MarsRover(Location location, Orientation orientation) {
        this.id = UUID.randomUUID();
        this.location = location;
        this.orientation = orientation;
    }
}
