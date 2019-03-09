package be.cegeka.marsrover.command;

import be.cegeka.marsrover.service.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static be.cegeka.marsrover.acl.MarsRoverCommunication.MINIMUM_COMMAND_COUNT;

public class BufferedCommandHandler extends CommandHandler {

    private final List<Command> commands = new ArrayList<>();

    public BufferedCommandHandler(Logger logger) {
        super(logger);
    }

    @Override
    public void handleCommand(UUID marsRoverId, Command command) {
        getLogger().logCommandReceived(marsRoverId, command);
        this.commands.add(command);
        if (this.commands.size() == MINIMUM_COMMAND_COUNT) {
            this.commands.forEach(cmd -> super.executeCommand(marsRoverId, cmd));
        }
    }
}
