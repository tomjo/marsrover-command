package be.cegeka.marsrover;

import be.cegeka.marsrover.donttouch.Logger;
import be.cegeka.marsrover.donttouch.MarsPlateau;
import be.cegeka.marsrover.donttouch.MarsPlateauStub;
import be.cegeka.marsrover.donttouch.MarsRoverCommunicationAPI;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class LoggerTest {

    private Logger logger;
    private MarsRoverCommunicationAPI api;
    private MarsPlateau marsPlateau = new MarsPlateauStub();

    @Before
    public void setUp() throws Exception {
        this.logger = new MarsRoverLogger();
        this.marsPlateau = new MarsPlateauStub();
        this.api = new MarsRoverCommunication(marsPlateau);
    }

    @Test
    public void onMoveCommand_shouldLogMoveCommand() {
        UUID marsRoverId = marsPlateau.deployMarsRover(new Location(2, 2), Orientation.NORTH);

        api.sendMarsRoverCommand(marsRoverId, "M", "comment");

        assertThat(logger.fetchMarsRoverHistory(marsRoverId)).isEqualTo("1) Move (2,2) -> (2,3) | comment");
    }

    @Test
    public void onTurnAndMoveCommand_shouldLogCommands() {
        UUID marsRoverId = marsPlateau.deployMarsRover(new Location(2, 2), Orientation.NORTH);

        api.sendMarsRoverCommand(marsRoverId, "RM", "comment");

        assertThat(logger.fetchMarsRoverHistory(marsRoverId)).isEqualTo("1) Turn North -> East | comment\n2) Move (2,2) -> (3,2) | comment");
    }

    @Test
    public void onTurnAndMoveCommands_shouldLogCommands() {
        UUID marsRoverId = marsPlateau.deployMarsRover(new Location(2, 2), Orientation.NORTH);

        api.sendMarsRoverCommand(marsRoverId, "R", "comment1");
        api.sendMarsRoverCommand(marsRoverId, "M", "comment2");

        assertThat(logger.fetchMarsRoverHistory(marsRoverId)).isEqualTo("1) Turn North -> East | comment1\n2) Move (2,2) -> (3,2) | comment2");
    }
}
