package be.cegeka.marsrover.donttouch;

import be.cegeka.marsrover.domain.Location;
import be.cegeka.marsrover.domain.MarsRover;
import be.cegeka.marsrover.domain.Orientation;

import java.util.UUID;

public interface MarsPlateau {

    MarsRover lookupMarsRover(UUID marsRoverId);

    UUID deployMarsRover(Location location, Orientation orientation);
}
