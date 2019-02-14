# Mars Rover - Command pattern kata
*Adapted from https://danilsuits.github.io/mars-rover-kata/*

## Problem

A squad of robotic rovers are to be landed by NASA on a plateau on Mars. This plateau, which is curiously rectangular, must be navigated by the rovers so that their on-board cameras can get a complete view of the surrounding terrain to send back to Earth.

A rover’s position and location is represented by a combination of x and y co-ordinates and a letter representing one of the four cardinal compass points. The plateau is divided up into a grid to simplify navigation. An example position might be 0, 0, N, which means the rover is in the bottom left corner and facing North.

In order to control a rover, NASA sends a simple string of letters. The possible letters are ‘L’, ‘R’ and ‘M’. ‘L’ and ‘R’ makes the rover spin 90 degrees left or right respectively, without moving from its current spot. ‘M’ means move forward one grid point, and maintain the same heading.

Assume that the square directly North from (x, y) is (x, y+1).

## Exercise

You can communicate with the Mars Rover using the *MarsRoverCommunicationAPI*. 
This is a very primitive API which accepts the unique identifier of a Mars Rover, an instruction and possible arguments.

This API cannot be changed (it is owned by another team consisting of weird aliens from planet Retie), we can only change the implementation in the Mars Rover (*MarsRoverCommunication*).

**1. Make MarsRoverTest green (implement handling movement/turn instructions)**

    Make use of the command pattern
    
**2. Change: Since Mars is pretty far away, it takes a while for instructions to be transmitted. 
To save power we only want instructions to be executed when multiple instructions are available.**

    Buffer(queue) instructions and execute all buffered instructions as soon as you have MINIMUM_COMMAND_COUNT instructions.
    Make the MarsRoverQueueingTest pass
    
**3. Change: For debugging purposes we want to log the instructions for the Mars Rover (both received and executed)**

    Implement the MarsRoverLogger and make the MarsRoverLoggingTest pass
    
**4. Change: Sometimes we make mistakes but do not worry: as long as an instruction is still buffered it can be undone.**

    Implement undo functionality and make the MarsRoverUndoTest pass




