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

import static be.cegeka.marsrover.acl.MarsRoverCommunication.MINIMUM_COMMAND_COUNT;
import static be.cegeka.marsrover.domain.Orientation.EAST;
import static be.cegeka.marsrover.domain.Orientation.NORTH;
import static org.assertj.core.api.Assertions.assertThat;

public class MarsRoverUndoTest {

    private MarsRoverCommunicationAPI api;
    private MarsPlateau marsPlateau = new MarsPlateauStub();

    @Before
    public void setUp() {
        this.marsPlateau = new MarsPlateauStub();
        this.api = new MarsRoverCommunication(marsPlateau, new CommandHandler(new MarsRoverLogger()), new MarsRoverCommandFactory());
    }

    @Test
    public void whenUndoReceived_discardLastBufferedCommand() {
        UUID marsRoverId = marsPlateau.deployMarsRover(new Location(2, 2), NORTH);
        MINIMUM_COMMAND_COUNT = 3;

        api.sendMarsRoverCommand(marsRoverId, "M");
        api.sendMarsRoverCommand(marsRoverId, "M");
        api.sendMarsRoverCommand(marsRoverId, "U");
        api.sendMarsRoverCommand(marsRoverId, "R");
        api.sendMarsRoverCommand(marsRoverId, "M");

        MarsRover marsRover = marsPlateau.lookupMarsRover(marsRoverId);
        assertThat(marsRover.getLocation()).isEqualTo(new Location(3, 3));
        assertThat(marsRover.getOrientation()).isEqualTo(EAST);
    }

    @Test
    public void whenUndoReceivedButNoBufferedCommands_doesNotDoAnything() {
        UUID marsRoverId = marsPlateau.deployMarsRover(new Location(2, 2), NORTH);
        MINIMUM_COMMAND_COUNT = 3;

        api.sendMarsRoverCommand(marsRoverId, "M");
        api.sendMarsRoverCommand(marsRoverId, "M");
        api.sendMarsRoverCommand(marsRoverId, "M");
        api.sendMarsRoverCommand(marsRoverId, "U");

        MarsRover marsRover = marsPlateau.lookupMarsRover(marsRoverId);
        assertThat(marsRover.getLocation()).isEqualTo(new Location(2, 5));
        assertThat(marsRover.getOrientation()).isEqualTo(NORTH);
    }

}