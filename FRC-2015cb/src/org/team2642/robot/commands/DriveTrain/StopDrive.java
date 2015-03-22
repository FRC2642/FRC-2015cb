package org.team2642.robot.commands.DriveTrain;

import org.team2642.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopDrive extends Command {

    public StopDrive() {
        requires(Robot.driveTrain);
    }
    protected void initialize() {
    	Robot.driveTrain.stopMotors();
    }
    protected void execute() {
    }
    protected boolean isFinished() {
        return false;
    }
    protected void end() {
    }
    protected void interrupted() {
    }
}
