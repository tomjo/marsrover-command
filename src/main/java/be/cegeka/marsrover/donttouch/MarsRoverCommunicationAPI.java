package be.cegeka.marsrover.donttouch;

import java.util.UUID;

public interface MarsRoverCommunicationAPI {

    void sendMarsRoverCommand(UUID marsRoverId, String command, String... args);
}
