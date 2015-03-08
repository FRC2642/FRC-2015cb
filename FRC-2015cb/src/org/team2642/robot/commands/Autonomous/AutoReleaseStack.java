package org.team2642.robot.commands.Autonomous;

import org.team2642.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoReleaseStack extends Command {

    private int counter;
	
	public AutoReleaseStack() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Lift);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.Lift.setDogs(true);
    	Robot.Lift.setPusher(false);
    	Robot.Pickers.stopMotors();
    	counter = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	Robot.Lift.setDogs(true);
    	Robot.Lift.setPusher(true);
    	Robot.Pickers.pickerSet(0.5);
    	counter++;
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return (counter > 75);
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.Lift.setDogs(false);
    	Robot.Lift.setPusher(false);
    	Robot.Pickers.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
