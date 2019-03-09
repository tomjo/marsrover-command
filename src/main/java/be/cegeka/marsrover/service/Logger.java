package be.cegeka.marsrover.service;

import be.cegeka.marsrover.command.Command;

import java.util.UUID;

public interface Logger {

    String fetchMarsRoverLogs(UUID marsRoverId);

    void logCommandReceived(UUID marsRoverId, Command command);

    void logCommandExecuted(UUID marsRoverId, Command command);
}
