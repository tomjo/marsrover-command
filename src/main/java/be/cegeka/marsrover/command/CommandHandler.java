package be.cegeka.marsrover.command;

public class CommandHandler {

    public void handleCommand(Command command){
        command.execute();
    }
}
