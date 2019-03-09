package be.cegeka.marsrover.command;

import java.util.ArrayList;
import java.util.List;

import static be.cegeka.marsrover.acl.MarsRoverCommunication.MINIMUM_COMMAND_COUNT;

public class BufferedCommandHandler extends CommandHandler{

    private final List<Command> commands = new ArrayList<>();

    @Override
    public void handleCommand(Command command){
        this.commands.add(command);
        if(this.commands.size() == MINIMUM_COMMAND_COUNT){
            this.commands.forEach(Command::execute);
        }
    }
}
