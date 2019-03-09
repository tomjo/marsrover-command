package be.cegeka.marsrover.command;

import be.cegeka.marsrover.domain.MarsRover;

public class MoveCommand implements Command {

    private final MarsRover marsRover;
    private final String comment;

    public MoveCommand(MarsRover marsRover, String comment) {
        this.marsRover = marsRover;
        this.comment = comment;
    }

    @Override
    public void execute() {
        marsRover.move();
    }

    @Override
    public String toString() {
        return "Move | "+comment;
    }
}
