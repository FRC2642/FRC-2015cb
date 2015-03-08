package org.team2642.robot.commands.Lift;

import org.team2642.robot.Robot;
import org.team2642.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetLiftToCoop extends Command {
	
	int leftbound, rightbound;
	
    public SetLiftToCoop() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	leftbound = 140;
    	rightbound = 300; // limits for encoder
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (RobotMap.liftEncoder.getDistance() < 800) {
    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
