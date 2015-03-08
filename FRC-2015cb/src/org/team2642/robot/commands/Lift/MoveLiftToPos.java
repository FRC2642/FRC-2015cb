package org.team2642.robot.commands.Lift;

import org.team2642.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLiftToPos extends Command {
	
	private double pos;
	
    public MoveLiftToPos(double position) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Lift);
    	pos = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.Lift.moveLiftToPos(pos);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.Lift.liftAtTarget(800));
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
