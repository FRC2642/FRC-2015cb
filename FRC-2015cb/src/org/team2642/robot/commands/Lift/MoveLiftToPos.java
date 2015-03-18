package org.team2642.robot.commands.Lift;

import org.team2642.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveLiftToPos extends Command {
	
	double setpoint;
	
    public MoveLiftToPos(double position) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Lift);
    	setpoint = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.Lift.liftController.setSetpoint(setpoint);
    	Robot.Lift.liftController.enable();
    	SmartDashboard.putNumber("Lift setpoint", setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.Lift.getLiftEncoderDist() > Robot.Lift.getLiftHighBound()) {
    		Robot.Lift.setDogs(true);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return (Robot.Lift.liftAtTarget(position));
        return Robot.Lift.liftController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.Lift.liftController.disable();
    	Robot.Lift.stopLift();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
