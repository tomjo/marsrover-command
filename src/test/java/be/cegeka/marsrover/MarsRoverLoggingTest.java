package be.cegeka.marsrover;

import be.cegeka.marsrover.acl.MarsRoverCommunication;
import be.cegeka.marsrover.command.CommandHandler;
import be.cegeka.marsrover.command.MarsRoverCommandFactory;
import be.cegeka.marsrover.domain.Location;
import be.cegeka.marsrover.donttouch.MarsPlateau;
import be.cegeka.marsrover.donttouch.MarsRoverCommunicationAPI;
import be.cegeka.marsrover.service.Logger;
import be.cegeka.marsrover.service.MarsRoverLogger;
import be.cegeka.marsrover.stub.MarsPlateauStub;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static be.cegeka.marsrover.acl.MarsRoverCommunication.MINIMUM_COMMAND_COUNT;
import static be.cegeka.marsrover.domain.Orientation.NORTH;
import static org.assertj.core.api.Assertions.assertThat;

public class MarsRoverLoggingTest {

    private MarsRoverCommunicationAPI api;
    private MarsPlateau marsPlateau = new MarsPlateauStub();
    private Logger logger;

    @Before
    public void setUp() {
        this.marsPlateau = new MarsPlateauStub();
        this.api = new MarsRoverCommunication(marsPlateau, new CommandHandler(), new MarsRoverCommandFactory());
        this.logger = new MarsRoverLogger();
    }

    @Test
    public void whenCommandsReceived_shouldLogReceivedCommands() {
        UUID marsRoverId = marsPlateau.deployMarsRover(new Location(2, 2), NORTH);
        MINIMUM_COMMAND_COUNT = 5;

        api.sendMarsRoverCommand(marsRoverId, "M", "comment");
        api.sendMarsRoverCommand(marsRoverId, "M", "comment");
        api.sendMarsRoverCommand(marsRoverId, "M", "comment2");

        String logs = this.logger.fetchMarsRoverLogs(marsRoverId);

        assertThat(logs).isEqualTo(String.format("Logs from Mars Rover {%s}:\n" +
                "1) [RECV] Move (2,2) -> (2,3) | comment" +
                "2) [RECV] Move (2,3) -> (2,4) | comment" +
                "3) [RECV] Move (2,3) -> (2,5) | comment2", marsRoverId.toString()));
    }

    @Test
    public void whenCommandsExecuted_shouldLogExecutedCommands() {
        UUID marsRoverId = marsPlateau.deployMarsRover(new Location(2, 2), NORTH);
        MINIMUM_COMMAND_COUNT = 3;

        api.sendMarsRoverCommand(marsRoverId, "M", "comment");
        api.sendMarsRoverCommand(marsRoverId, "M", "comment");
        api.sendMarsRoverCommand(marsRoverId, "M", "comment2");
        api.sendMarsRoverCommand(marsRoverId, "R");

        String logs = this.logger.fetchMarsRoverLogs(marsRoverId);

        assertThat(logs).isEqualTo(String.format("Logs from Mars Rover {%s}:\n" +
                "1) [RECV] Move (2,2) -> (2,3) | comment" +
                "2) [RECV] Move (2,3) -> (2,4) | comment" +
                "3) [RECV] Move (2,3) -> (2,5) | comment2" +
                "4) [EXEC] Move (2,2) -> (2,3) | comment" +
                "5) [EXEC] Move (2,3) -> (2,4) | comment" +
                "6) [EXEC] Move (2,3) -> (2,5) | comment2" +
                "4) [RECV] Turn Right North -> East", marsRoverId.toString()));
    }

}