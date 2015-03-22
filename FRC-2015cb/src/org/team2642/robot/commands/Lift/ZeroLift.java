package org.team2642.robot.commands.Lift;

import org.team2642.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroLift extends Command {

    public ZeroLift() {
    	requires(Robot.Lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.Lift.moveLiftDown();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Robot.Lift.getBottomLimit() || Robot.Lift.liftAtTarget(Robot.Lift.getLiftLowBound()));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.Lift.stopLift();
    	Robot.Lift.resetLiftEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}