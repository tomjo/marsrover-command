package be.cegeka.marsrover.stub;

import be.cegeka.marsrover.domain.Location;
import be.cegeka.marsrover.domain.MarsRover;
import be.cegeka.marsrover.domain.Orientation;
import be.cegeka.marsrover.donttouch.MarsPlateau;

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