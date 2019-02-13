package be.cegeka.marsrover.donttouch;

import be.cegeka.marsrover.Location;
import be.cegeka.marsrover.MarsRover;
import be.cegeka.marsrover.Orientation;

import java.util.UUID;

public interface MarsPlateau {

    MarsRover lookupMarsRover(UUID marsRoverId);

    UUID deployMarsRover(Location location, Orientation orientation);
}
