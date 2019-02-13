package be.cegeka.marsrover;

import be.cegeka.marsrover.donttouch.MarsPlateau;
import be.cegeka.marsrover.donttouch.MarsRoverCommunicationAPI;

import java.util.UUID;

public class MarsRoverCommunication implements MarsRoverCommunicationAPI {

    private final MarsPlateau marsPlateau;

    public MarsRoverCommunication(MarsPlateau marsPlateau) {
        this.marsPlateau = marsPlateau;
    }

    @Override
    public void sendMarsRoverCommand(UUID marsRoverId, String command, String... args) {
        //TODO implement
    }
}
