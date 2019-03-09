package be.cegeka.marsrover.command;

import be.cegeka.marsrover.service.Logger;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CommandHandler {

    private final Logger logger;

    public void handleCommand(UUID marsRoverId, Command command){
        logger.logCommandReceived(marsRoverId, command);
        executeCommand(marsRoverId, command);
    }

    protected void executeCommand(UUID marsRoverId, Command command) {
        command.execute();
        logger.logCommandExecuted(marsRoverId, command);
    }

    protected Logger getLogger() {
        return logger;
    }
}
