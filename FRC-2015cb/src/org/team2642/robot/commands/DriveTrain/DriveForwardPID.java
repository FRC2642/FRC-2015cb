package org.team2642.robot.commands.DriveTrain;

import org.team2642.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardPID extends Command {

    double setpoint;
	
	public DriveForwardPID(double inches) {
        requires(Robot.driveTrain);
        setpoint = inches;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.straightController.enable();
    	Robot.driveTrain.straightController.setSetpoint(setpoint);
    	Robot.driveTrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.straightController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.straightController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
