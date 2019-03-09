package be.cegeka.marsrover.command;

import be.cegeka.marsrover.domain.MarsRover;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TurnLeftCommand implements Command {

    private final MarsRover marsRover;

    @Override
    public void execute() {
        marsRover.turnLeft();
    }
}
