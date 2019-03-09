package be.cegeka.marsrover.acl;

import be.cegeka.marsrover.command.Command;
import be.cegeka.marsrover.command.CommandHandler;
import be.cegeka.marsrover.command.MarsRoverCommandFactory;
import be.cegeka.marsrover.domain.MarsRover;
import be.cegeka.marsrover.donttouch.MarsPlateau;
import be.cegeka.marsrover.donttouch.MarsRoverCommunicationAPI;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class MarsRoverCommunication implements MarsRoverCommunicationAPI {

    public static int MINIMUM_COMMAND_COUNT = 5;

    private final MarsPlateau marsPlateau;
    private final CommandHandler commandHandler;
    private final MarsRoverCommandFactory commandFactory;

    @Override
    public void sendMarsRoverCommand(UUID marsRoverId, String command, String... args) {
        MarsRover marsRover = marsPlateau.lookupMarsRover(marsRoverId);
        Command marsRoverCommand = commandFactory.createCommand(marsRover, command, args);
        commandHandler.handleCommand(marsRoverCommand);
    }
}
