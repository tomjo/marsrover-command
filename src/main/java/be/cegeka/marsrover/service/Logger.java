package be.cegeka.marsrover.service;

import java.util.UUID;

public interface Logger {

    String fetchMarsRoverLogs(UUID marsRoverId);
}
