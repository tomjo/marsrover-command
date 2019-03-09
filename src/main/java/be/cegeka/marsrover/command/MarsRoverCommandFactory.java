package be.cegeka.marsrover.command;

import be.cegeka.marsrover.domain.MarsRover;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;

public class MarsRoverCommandFactory {

    private static Map<String, Class<? extends Command>> commandMapping = new HashMap<>();

    static {
        commandMapping.put("M", MoveCommand.class);
        commandMapping.put("L", TurnLeftCommand.class);
        commandMapping.put("R", TurnRightCommand.class);
    }

    public Command createCommand(MarsRover marsRover, String command, String... args){
        try {
            Class<? extends Command> commandClass = commandMapping.get(command);
            Object[] argsArray = new Object[1 + args.length];
            argsArray[0] = marsRover;
            System.arraycopy(args, 0, argsArray, 1, args.length);
            Constructor<? extends Command> constructor = getCommandConstructor(commandClass, args.length);
            return constructor.newInstance(argsArray);
        }catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private Constructor<? extends Command> getCommandConstructor(Class<? extends Command> commandClass, int argumentCount) {
        return (Constructor<? extends Command>) stream(commandClass.getConstructors())
                .filter(c -> c.getParameterCount() == argumentCount + 1)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

}
