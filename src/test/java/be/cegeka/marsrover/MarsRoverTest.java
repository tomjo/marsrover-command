package be.cegeka.marsrover;

import be.cegeka.marsrover.acl.MarsRoverCommunication;
import be.cegeka.marsrover.command.CommandHandler;
import be.cegeka.marsrover.command.MarsRoverCommandFactory;
import be.cegeka.marsrover.domain.Location;
import be.cegeka.marsrover.domain.MarsRover;
import be.cegeka.marsrover.donttouch.MarsPlateau;
import be.cegeka.marsrover.donttouch.MarsRoverCommunicationAPI;
import be.cegeka.marsrover.service.MarsRoverLogger;
import be.cegeka.marsrover.stub.MarsPlateauStub;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static be.cegeka.marsrover.domain.Orientation.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MarsRoverTest {

    private MarsRoverCommunicationAPI api;
    private MarsPlateau marsPlateau = new MarsPlateauStub();

    @Before
    public void setUp() {
        this.marsPlateau = new MarsPlateauStub();
        this.api = new MarsRoverCommunication(marsPlateau, new CommandHandler(new MarsRoverLogger()), new MarsRoverCommandFactory());
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
}