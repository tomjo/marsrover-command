package be.cegeka.marsrover.donttouch;

import be.cegeka.marsrover.Location;
import be.cegeka.marsrover.MarsRover;
import be.cegeka.marsrover.Orientation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MarsPlateauStub implements MarsPlateau {

    private Map<UUID, MarsRover> marsRovers = new HashMap<>();

    @Override
    public MarsRover lookupMarsRover(UUID marsRoverId) {
        return marsRovers.get(marsRoverId);
    }

    @Override
    public UUID deployMarsRover(Location location, Orientation orientation) {
        MarsRover marsRover = new MarsRover(location, orientation);
        marsRovers.put(marsRover.getId(), marsRover);
        return marsRover.getId();
    }
}