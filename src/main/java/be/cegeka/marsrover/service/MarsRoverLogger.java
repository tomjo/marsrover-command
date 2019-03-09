package be.cegeka.marsrover.service;

import be.cegeka.marsrover.command.Command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.emptyList;

public class MarsRoverLogger implements Logger {

    private Map<UUID, List<String>> logs = new HashMap<>();

    @Override
    public String fetchMarsRoverLogs(UUID marsRoverId) {
        String start = "Logs from Mars Rover {%s}:";
        StringBuilder sb = new StringBuilder(String.format(start, marsRoverId));
        int logCount = 1;
        for (String log : logs.getOrDefault(marsRoverId, emptyList())) {
            sb.append('\n').append(logCount++).append(") ").append(log);
        }
        return sb.toString();
    }

    @Override
    public void logCommandReceived(UUID marsRoverId, Command command) {
        log(marsRoverId, () -> "[RECV] " + command);
    }

    @Override
    public void logCommandExecuted(UUID marsRoverId, Command command) {
        log(marsRoverId, () -> "[EXEC] " + command);
    }

    private void log(UUID marsRoverId, Supplier<String> messageSupplier) {
        logs.merge(marsRoverId, newArrayList(messageSupplier.get()), (l,r) -> {
            l.addAll(r);
            return l;
        });
    }
}
