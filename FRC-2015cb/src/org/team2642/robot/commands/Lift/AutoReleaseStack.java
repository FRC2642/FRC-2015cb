package org.team2642.robot.commands.Lift;

import org.team2642.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoReleaseStack extends Command {

    public AutoReleaseStack() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Lift);
    	requires(Robot.Pickers);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.Lift.setDogs(true);
    	Robot.Lift.setPusher(false);
    	Robot.Pickers.stopMotors();
    	Timer.delay(3);
    	Robot.Lift.setPusher(true);
    	Robot.Pickers.pickerSet(0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.Lift.setDogs(false);
    	Robot.Lift.setPusher(false);
    	Robot.Pickers.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
