package be.cegeka.marsrover.command;

import be.cegeka.marsrover.domain.MarsRover;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TurnRightCommand implements Command {

    private final MarsRover marsRover;

    @Override
    public void execute() {
        marsRover.turnRight();
    }

    @Override
    public String toString() {
        return "Turn Right";
    }
}
