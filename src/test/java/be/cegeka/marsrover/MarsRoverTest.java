package be.cegeka.marsrover;

import be.cegeka.marsrover.donttouch.MarsPlateau;
import be.cegeka.marsrover.donttouch.MarsPlateauStub;
import be.cegeka.marsrover.donttouch.MarsRoverCommunicationAPI;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static be.cegeka.marsrover.Orientation.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MarsRoverTest {

    private MarsRoverCommunicationAPI api;
    private MarsPlateau marsPlateau = new MarsPlateauStub();

    @Before
    public void setUp() throws Exception {
        this.marsPlateau = new MarsPlateauStub();
        this.api = new MarsRoverCommunication(marsPlateau);
    }

    @Test
    public void whenMoveCommandReceived_shouldMoveMarsRover() {
        UUID marsRoverId = marsPlateau.deployMarsRover(new Location(2, 2), NORTH);

        api.sendMarsRoverCommand(marsRoverId, "M");

        MarsRover marsRover = marsPlateau.lookupMarsRover(marsRoverId);
        assertThat(marsRover.getLocation()).isEqualTo(new Location(2, 3));
        assertThat(marsRover.getOrientation()).isEqualTo(NORTH);
    }

    @Test
    public void whenTurnLeftCommandReceived_shouldTurnLeft() {
        UUID marsRoverId = marsPlateau.deployMarsRover(new Location(2, 2), NORTH);

        api.sendMarsRoverCommand(marsRoverId, "L");

        MarsRover marsRover = marsPlateau.lookupMarsRover(marsRoverId);
        assertThat(marsRover.getLocation()).isEqualTo(new Location(2, 2));
        assertThat(marsRover.getOrientation()).isEqualTo(WEST);
    }

    @Test
    public void whenTurnRightCommandReceived_shouldTurnRight() {
        UUID marsRoverId = marsPlateau.deployMarsRover(new Location(2, 2), NORTH);

        api.sendMarsRoverCommand(marsRoverId, "R");

        MarsRover marsRover = marsPlateau.lookupMarsRover(marsRoverId);
        assertThat(marsRover.getLocation()).isEqualTo(new Location(2, 2));
        assertThat(marsRover.getOrientation()).isEqualTo(EAST);
    }

    @Test
    public void whenMultipleCommandsReceived_shouldExecuteAllCommandsInOrder() {
        UUID marsRoverId = marsPlateau.deployMarsRover(new Location(2, 2), NORTH);

        api.sendMarsRoverCommand(marsRoverId, "RM");

        MarsRover marsRover = marsPlateau.lookupMarsRover(marsRoverId);
        assertThat(marsRover.getLocation()).isEqualTo(new Location(3, 2));
        assertThat(marsRover.getOrientation()).isEqualTo(EAST);
    }
}