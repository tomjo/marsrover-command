package be.cegeka.marsrover.donttouch;

import be.cegeka.marsrover.MarsRoverCommunication;

import java.util.UUID;

public class FlakyMarsRoverCommunication implements MarsRoverCommunicationAPI {

    private int commandCount;

    private final MarsRoverCommunication marsRoverCommunication;

    public FlakyMarsRoverCommunication(MarsRoverCommunication marsRoverCommunication) {
        this.marsRoverCommunication = marsRoverCommunication;
    }

    @Override
    public void sendMarsRoverCommand(UUID marsRoverId, String command, String... args) {
        if(commandCount > 1 && commandCount % 2 == 0){
            return;
        }
        this.marsRoverCommunication.sendMarsRoverCommand(marsRoverId, command, args);
        commandCount++;
    }
}
